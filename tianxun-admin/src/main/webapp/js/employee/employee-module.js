var EmployeeModule = function () {
	var deptTree = function() {
		$('#deptTree').jstree({
			'plugins' : {"wholerow":{}, "checkbox":{
				"three-state" : false
			}, "types":{}, "radio":{}},
			'core' : {
				"themes" : {
					"responsive" : false
				},
				'data' : {
					"url" : ctx + "/employeeGroup/tree",
					"data" : function(node) {

					}
				}
			}
		}).on("changed.jstree", function(e, data) {
			$('#deptId').val(data.node.id);
			$('#dept').val(data.node.text);
		});
	};
	var editable = function () {

        return {
            init: function () {
                var oTable = $('#modelEditable').dataTable({
                    "lengthMenu": [
                        [10, 15, 20, 50],
                        [10, 15, 20, 50]
                    ],
                    "autoWidth":false,
                    "displayLength": 10,
                    "language": {
                        "lengthMenu": "每页 _MENU_ 条记录",
                        "paginate": {
                            "previous": "上一页",
                            "next": "下一页"
                        },
                        "info" : "从 _START_ 到  _END_ 条，总记录为 _TOTAL_ 条",    
                        "infoEmpty" : "记录数为0",
                    },
                    "sortable": false,
                    "columnDefs": [
						{
							"data": null,
						    'sortable': false,
						    "defaultContent": "",
						    "targets": [12]
						}
                    ],
                    "columns": [
                       { "data": "employeeNo","defaultContent": "" },
                       { "data": "name","defaultContent": "" },
                       { "data": "account","defaultContent": "" },
                       { "data": "gender","defaultContent": "" },
                       { "data": "idcard" ,"defaultContent": ""},
                       { "data": "mobile","defaultContent": "" },
                       { "data": "department","defaultContent": "" },
                       { "data": "captain","defaultContent": "" },
                       { "data": "position","defaultContent": "" },
                       { "data": "planner","defaultContent": "" },
                       { "data": "posPadId","defaultContent": "" },
                       { "data": "state","defaultContent": "" }
                   ],
                   "filter" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/employee/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.employeeId;
                	   var gender = aData.gender;
                	   var operation = "";
                	   if (gender == 1) {
                		   $('td:eq(3)', nRow).html('男');
                	   } else if (gender == 2) {
                		   $('td:eq(3)', nRow).html('女');
                	   }
                	   var captain = aData.captain;
                	   if (captain == true) {
                		   $('td:eq(7)', nRow).html('是');
                	   } else if (captain == false) {
                		   $('td:eq(7)', nRow).html('否');
                	   }
                	   
                	   var planer = aData.planner;
                	   if (planer == true) {
                		   $('td:eq(9)', nRow).html('是');
                	   } else if (planer == false) {
                		   $('td:eq(9)', nRow).html('否');
                	   }
                	   
                	   var posPad = $.trim(aData.posPadId);
                	   if (posPad != undefined && posPad != '') {
                		   $('td:eq(10)', nRow).html('已分配');
                		   operation = '<a class="detail fa fa-bars fa-lg" title="详情" href="javascript:;"></a>'+
             			  ' &nbsp;<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a>'+
            			  ' &nbsp;<a title="重置密码" class="password fa fa-unlock-alt fa-lg" href="javascript:;"></a>'+
            			  ' &nbsp;<a title="回收设备" class="unbind fa fa-chain-broken fa-lg" href="javascript:;"></a>';
                	   } else {
                		   $('td:eq(10)', nRow).html('未分配');
                		   operation = '<a class="detail fa fa-bars fa-lg" title="详情" href="javascript:;"></a>'+
             			  ' &nbsp;<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a>'+
             			  ' &nbsp;<a class="password fa fa-unlock-alt fa-lg" title="重置密码" href="javascript:;"></a>'+
             			  ' &nbsp;<a class="device fa fa-tablet fa-lg" title="分配设备" href="javascript:;"></a>';
                	   }
                	   
                	   var state = aData.state;
                	   if (state == 1) {
                		   $('td:eq(11)', nRow).html('在职');
                	   } else if (state == 0) {
                		   $('td:eq(11)', nRow).html('离职');
                	   }
                	   if (aData.admin == 1 && (aData.employeeNo == aData.orgCode) && (aData.orgId==aData.idcard)) {
                		   $('td:eq(12)', nRow).html('');
                		   $('td:eq(4)', nRow).html('');
                	   } else {
                		   $('td:eq(12)', nRow).html(operation);
                	   }
                       return nRow;
                   },

                   "fnServerData" : function(ajaxSource, dataSet, fnCallback) {
	                   var json = "";
	                   var s;
	                   for (var i = 0; i < dataSet.length; i++) {
	                       s = dataSet[i];
	                   	   if (s.name == 'sEcho') {
	                   		   json = json + '"echo":' + s.value;
		                   } else if (s.name == 'iDisplayStart') {
		                	   json = json + ',"start":' + s.value;
		                   } else if (s.name == 'iDisplayLength') {
		                	   json = json + ',"pageSize":' + s.value;
		                   }
	                   }
	                   var form = $("form#queryForm").serializeJSON();
                   	   var js = form.replace('}', ',');
                   	   json = js + json + "}";
                       $.ajax({
                       	"contentType":"application/json; charset=UTF-8",
                           "dataType" : 'json',
                           "type" : "POST",
                           "url" : ajaxSource,
                           "data" : json,
                           "success" : fnCallback
                       });
                   },
                });
                // 修改密码
                $('#modelEditable').on("click", "a.password", function (e) {
                    e.preventDefault();
                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                    $("#updatePasswdBody").empty();
                    var msg = {"employeeId" : id};
                	var tmpl = $("#updatePasswdTemplate").tmpl(msg).appendTo("#updatePasswdBody");
                	var modal = $('#updatePasswd').modal('show');
                	modal.on("hidden.bs.modal",function(e){
                		$(this).removeData();
                	});
                	
                	$('.i-checks').iCheck({
                        radioClass: 'iradio_square-green'
                    });
                	
                    var validator = $('#updatePasswdForm').validate({
                    	rules: {
                            state: {
                                required: true
                            },
                            loginPwd: {
                                required: true,
                                rangelength:[6,20]
                            },
                            workPwd: {
                                required: true,
                                rangelength:[6,20],
                                equalTo:"#newpasswd"
                            }
                        },
                        messages: {
                        	state: {required:"密码类型"},
                            loginPwd: {required:"新密码不能为空！", equalTo : "两次输入密码不一致！"},
                            workPwd: {required:"重复新密码不能为空！", equalTo : "两次输入密码不一致！"}
                        },
                        success: function (label, element) {
                            var icon = $(element).parent('.input-icon').children('i');
                            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                            icon.removeClass("fa-warning").addClass("fa-check");
                        },

                        submitHandler: function (form) {
                            var json = $('form#updatePasswdForm').serializeJSON();
                    		$.ajax({
                            	type:"post",
                                contentType:"application/json; charset=UTF-8",
                                url: ctx + "/employee/updateInfo",
                                data:json,
                                dataType:"json",
                                success: function(msg){
	                            	if (msg.code == 1) {
		                        		modal.modal('hide');
		                        		bootbox.alert("重置员工密码成功！");
		                        	} else {
		                        		bootbox.alert(msg.message);
		                        	}
                                },
                                error: function (msg) {
                                    bootbox.alert(msg.message);
                                }
                            });
                        }
                    });
                });
                // 编辑
                $('#modelEditable').on("click", "a.edit", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                    window.location.href = ctx + "/employee/edit/" + id;
                }));
                // 详情
                $('#modelEditable').on("click", "a.detail", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                    $.ajax({
                    	type:"post",
                        url: ctx + "/employee/detail/" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#updateBody").empty();// clean
                        	var tmpl = $("#updateTemplate").tmpl(msg).appendTo("#updateBody");
                        	var modal = $('#updateModel').modal('show');
                        	modal.on("hidden.bs.modal",function(e){
                        		$(this).removeData();
                        	});
                        },
                        error: function (msg) {
                        	bootbox.alert('获取员工详情错误！');
                        }
                    });
                }));
                
                // 分配设备
                $('#modelEditable').on("click", "a.device", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var ele = nRow.children[0];
                    var id = ele.id;
                	var employeeNo = ele.innerText; // $(ele).text();//亦可
                    var modal = $('#bindDevice').modal('show');
                	modal.on("hidden.bs.modal",function(e){
                		$(this).removeData();
                	});
                	
                	// clean
                	$('#employeeNo').val(employeeNo);
                	$('#posId').val('');
                	$('#padId').val('');
                	$('#posPadId').val('');
                	
                	// 失去焦点查询//改成按钮了
                	//$('#padId').blur(function(){
                	$('#queryDevice').unbind("click").click(function(){
                		timeout(this, 1);
                		var padId = $.trim($('#padId').val());
                		if (padId == '') {
                			return;
                		}
                		$.ajax({
                        	type:"post",
                            url: ctx + "/device/queryPosPad",
                            data:"padId=" + padId,
                            dataType:"json",
                            success: function(msg){
                            	$('#posId').val(msg.posId);
                            	$('#posPadId').val(msg.id);
                            },
                            error: function (msg) {
                                bootbox.alert('查询没有查询到数据！');
                            }
                        });
                	});
                	
                	// 分配
                	$('#saveBindDevice').unbind("click").click(function(){
                		timeout(this, 2);
                		var padId = $.trim($('#padId').val());
                		var posId = $.trim($('#posId').val());
                		var posPadId = $.trim($('#posPadId').val());
                		if (padId == '' || posId == '' || posPadId == '') {
                			bootbox.alert('设备信息不能为空！');
                			return;
                		}
                		var employeeNo = $.trim($('#employeeNo').val());
                		if (employeeNo == '') {
                			bootbox.alert('员工信息不能为空！');
                			return;
                		}
                		// 执行分配
                		$.ajax({
                        	type:"post",
                        	contentType:"application/json; charset=UTF-8",
                            url: ctx + "/device/doDistribution",
                            data:JSON.stringify({id: posPadId, employeeNo : employeeNo}),
                            dataType:"json",
                            success: function(msg){
                            	if (msg.code == 1) {
                            		modal.modal('hide');
                            		bootbox.alert("员工设备分配成功！");
                            		var operation = '<a class="detail fa fa-bars fa-lg" title="详情" href="javascript:;"></a>'+
                       			  	' &nbsp;<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a>'+
                       			  	' &nbsp;<a title="重置密码" class="password fa fa-unlock-alt fa-lg" href="javascript:;"></a>'+
                       			  	' &nbsp;<a title="回收设备" class="unbind fa fa-chain-broken fa-lg" href="javascript:;"></a>';
                                    oTable.fnUpdate('已分配', nRow, 10, false);
                                    $('td:eq(12)', nRow).html(operation);
                            	} else {
                            		bootbox.alert("员工设备分配失败！");
                            	}
                            },
                            error: function (msg) {
                                bootbox.alert('没有查询到设备数据！');
                            }
                        });
                	});
                }));
                
                // 回收设备
                $('#modelEditable').on("click", "a.unbind", (function (e) {
                    e.preventDefault();
                    var obj = $(this);
                    var nRow = $(this).parents('tr')[0];
                    bootbox.dialog({
                    	title: "温馨提示：",
                        message: "您确定回收该员工的设备吗?",
                        buttons: {
                        	danger: {
                                label: "取 消",
                                className: "btn btn-default",
                                callback: function() {
                                }
                            },
	                        main: {
	                            label: "确 定",
	                            className: "btn btn-primary",
	                            callback: function() {
	                            	var nRow = obj.parents('tr')[0];
	                                var id = nRow.children[0].id;
	                                $.ajax({
	                                	type:"post",
	                                	contentType:"application/json; charset=UTF-8",
	                                    url: ctx + "/employee/updateInfo",
	                                    data: JSON.stringify({state:1,employeeId:id}),
	                                    dataType:"json",
	                                    success: function(msg){
	                                    	if (msg.code == 1) {
	                                    		bootbox.alert("回收员工设备成功！");
	                                    		var operation = '<a class="detail fa fa-bars fa-lg" title="详情" href="javascript:;"></a>'+
	                               			   ' &nbsp;<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a>'+
	                               			   ' &nbsp;<a title="重置密码" class="password fa fa-unlock-alt fa-lg" href="javascript:;"></a>'+
	                               			   ' &nbsp;<a title="分配设备" class="device fa fa-tablet fa-lg" href="javascript:;"></a>';
	                                    		oTable.fnUpdate('未分配', nRow, 10, false);
	                                    		$('td:eq(12)', nRow).html(operation);
	                                    	} else {
	                                    		bootbox.alert("回收员工设备失败！");
	                                    	}
	                                    },
	                                    error: function (msg) {
	                                    	bootbox.alert('Oh, No, 出错了，请联系管理人员！');
	                                    }
	                                });
	                            }
	                        }
                        }
                    });
                }));
                
                $('#queryBtn').click(function(){
                	timeout(this, 1);
                	oTable.fnClearTable();
                });
            }

        };

    }();
    
    return {
        init: function () {
        	editable.init();
        	deptTree();
        }
    };
}();