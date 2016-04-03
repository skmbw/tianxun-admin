var UserAuthModule = function () {
    var editable = function () {

        return {
            init: function () {
                var oTable = $('#modelEditable').dataTable({
                    "lengthMenu": [
                        [10, 15, 20, 50, -1],
                        [10, 15, 20, 50, "All"]
                    ],
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
                    'sortable': false,
                    "columnDefs": [
						{
						    "data": null,
						    'sortable': false,
						    "defaultContent": "",
						    "targets": [3]
						}
                    ],
                    "columns": [
                       { "data": "employeeNo", 'sortable': false},
                       { "data": "name"},
                       { "data": "roleIds", "defaultContent": "-"}
                   ],
                   "filter" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/userAuth/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.employeeId;
                	   $('td:eq(3)', nRow).html('<a class="detail fa fa-users fa-lg" title="角色详情" href="javascript:;"></a> &nbsp;&nbsp;<a class="edit fa fa-cog fa-lg" title="设置角色" href="javascript:;"></a>');
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
                
                // 编辑
                $('#modelEditable').on("click", "a.edit", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                	$.ajax({
                    	type:"post",
                        url: ctx + "/userAuth/edit",
                        data:"employeeId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#updateBody").empty();// clean
                        	var tmpl = $("#updateTemplate").tmpl(msg).appendTo("#updateBody");
                        	var modal = $('#updateModel').modal('show');
                        	modal.on("hidden.bs.modal",function(e){
                        		$(this).removeData();
                        	});
                        	
                        	$('#roleTree').jstree({
                                'plugins': ["wholerow", "checkbox", "types"],
                                'core': {
                                    "themes" : {
                                        "responsive": false
                                    },    
                                    'data': function(node, callback) { // 使用回调的方式
                                    	var employeeId = $('#employeeId').val();
                                    	if (employeeId == undefined || employeeId == "") {
                                    		employeeId = "null";
                                    	}
                                    	if (node.id == "#") {
                                    		callback({"id":"-1","text":"系统角色", "state":{"loaded":false}, "icon":"fa fa-briefcase icon-warning"});
                                    	} else {
                                    		$.get(ctx + "/roles/tree?id=" + employeeId).done(function (d) {
                                    			callback.call({"id":"-1","text":"系统角色"}, d.children);
                                    		});
                                    	}
                                    }
                                },
                                "types" : {
                                    "default" : {
                                        "icon" : "fa fa-folder icon-warning icon-lg"
                                    },
                                    "file" : {
                                        "icon" : "fa fa-file icon-warning icon-lg"
                                    }
                                }
                            }).on("changed.jstree", function (e, data) {
                        	   $('#roleIds').val(data.selected);
                        	   if (data.action == 'select_node' || data.action == 'deselect_node') {
                           		var json = data.instance.get_json();
                               	var array = json[0].children;
                               	var selected = '';
                               	for (var i = 0; i < array.length; i++) {
                               		var leaf = array[i];
                               		if (leaf.state.selected) {
                           				selected = selected + leaf.text + ',';
                           			}
                               	}
                               	$('#roleNames').val(selected.substring(0, selected.length - 1));
                           	}
                    	    }).on("open_node.jstree", function (e, data) {
                            	var id = data.node.id;
                            	if (id == '-1') {
                            		var rootLoad = $('#rootLoad').val();
                            		if (rootLoad == "0") {
                            			$('#rootLoad').val("1");
                            			// 加载它的子节点，调用core.data加载
                        				data.instance.load_node(data.node);
                            		}
                            	}
                            });
                        	
                        	$("#saveModel").unbind("click").click(function(){
                        		var json = $('form#updateModelForm').serializeJSON();
                        		$.ajax({
                                	type:"post",
                                    contentType:"application/json; charset=UTF-8",
                                    url: ctx + "/userAuth/update",
                                    data:json,
                                    dataType:"json",
                                    success: function(msg){
                                    	var roleNames = $('#roleNames').val();
                                    	oTable.fnUpdate(roleNames, nRow, 2, false);
                                    	modal.modal('hide');
                                    	bootbox.alert("设置用户角色信息成功！");
                                    },
                                    error: function (msg) {
                                        bootbox.alert(msg.message);
                                    }
                                });
                        	});
                        },
                        error: function (msg) {
                        	bootbox.alert(msg.message);
                        }
                    });
                }));
                // 明细
                $('#modelEditable').on("click", "a.detail", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                	
                    $.ajax({
                    	type:"post",
                        url: ctx + "/userAuth/edit",
                        data:"employeeId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#detailBody").empty();// clean
                        	var tmpl = $("#detailTemplate").tmpl(msg).appendTo("#detailBody");
                        	
                        	$('#detailRoleTree').jstree({
                                'plugins': ["wholerow", "checkbox", "types"],
                                'core': {
                                    "themes" : {
                                        "responsive": false
                                    },    
                                    'data': function(node, callback) { // 使用回调的方式
                                    	var employeeId = $('#employeeId').val();
                                    	if (employeeId == undefined || employeeId == "") {
                                    		employeeId = "null";
                                    	}
                                    	if (node.id == "#") {
                                    		callback({"id":"-1","text":"员工角色", "state":{"loaded":false, "disabled": true, "opened":true}, "icon":"fa fa-briefcase icon-warning"});
                                    	} else {
                                    		$.get(ctx + "/roles/tree?enable=true&id=" + employeeId).done(function (d) {
                                    			callback.call({"id":"-1","text":"员工角色"}, d.children);
                                    		});
                                    	}
                                    }
                                },
                                "types" : {
                                    "default" : {
                                        "icon" : "fa fa-folder icon-warning icon-lg"
                                    },
                                    "file" : {
                                        "icon" : "fa fa-file icon-warning icon-lg"
                                    }
                                }
                            })
                        	
                        	var modal = $('#detailModel').modal('show');
                        	modal.on("hidden.bs.modal",function(e){
                        		$(this).removeData();
                        	});
                        },
                        error: function (msg) {
                        	bootbox.alert(msg.message);
                        }
                    });
                }));
                
                $('#queryBtn').click(function(){
                	oTable.fnClearTable();
                });
            }

        };

    }();
    
    return {
        init: function () {
        	editable.init();
        }
    };
}();