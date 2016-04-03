var AuthModule = function () {
    
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
                    "columnDefs": [
						{
						    "defaultContent": "",
						    "targets": [3]
						},
						{
						    'sortable': false,
						    "defaultContent": "",
						    "targets": [6]
						}            
                    ],
                    "columns": [
                       { "data": "authName" },
//                       { "data": "authDesc" },
                       { "data": "moduleId" },
                       { "data": "showMenu" },
                       { "data": "action" },
                       { "data": "orders" },
                       { "data": "enabled" }
                   ],
                   "filter" : false,
                   "sortable" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/authorities/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.authId;
                	   
                	   var enabled = aData.enabled;
                	   if (enabled == undefined || enabled == 0) {
                		   enabled = '否';
                	   } else if (enabled == 1) {
                		   enabled = '是';
                	   }
                	   $('td:eq(5)', nRow).html(enabled);
                	   
                	   var showMenu = aData.showMenu;
                	   if (showMenu == undefined || showMenu == false) {
                		   showMenu = '否';
                	   } else if (showMenu == true) {
                		   showMenu = '是';
                	   }
                	   $('td:eq(2)', nRow).html(showMenu);
                	   
                	   $('td:eq(6)', nRow).html('<a class="detail fa fa-bars fa-lg" title="详情" href="javascript:;"></a> &nbsp;<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a> &nbsp;<a title="删除" class="delete fa fa-trash-o fa-lg" href="javascript:;"></a>');
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
                // 删除
                $('#modelEditable').on("click", "a.delete", function (e) {
                    e.preventDefault();
                    var obj = $(this);
                    bootbox.confirm("您确定删除这条记录?", function(result) {
                        if (result) {
                        	var nRow = obj.parents('tr')[0];
                            var id = nRow.children[0].id;
                            $.ajax({
                            	type:"post",
                                url: ctx + "/authorities/delete",
                                data:"id=" + id,
                                dataType:"json",
                                success: function(msg){
                                	if (msg.code == 1) {
                                		bootbox.alert("删除权限成功！");
                                		oTable.fnDeleteRow(nRow);
                                	} else {
                                		bootbox.alert("删除权限失败！");
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
                	$.ajax({
                    	type:"post",
                        url: ctx + "/authorities/edit",
                        data:"authId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#updateBody").empty();// clean
                        	var tmpl = $("#updateTemplate").tmpl(msg).appendTo("#updateBody");
                        	
                        	// 将资源树添加到更新模板上
                        	$('#resourceTree').jstree({
                                'plugins': ["wholerow", "checkbox", "types"],
                                'core': {
                                    "themes" : {
                                        "responsive": false
                                    },    
                                    'data': function(node, callback) { // 使用回调的方式
                                    	var authId = $('#authId').val();
                                    	if (authId == undefined || authId == "") {
                                    		authId = "null";
                                    	}
                                    	if (node.id == "#") {
                                    		callback({"id":"-1","text":"系统资源", "state":{"loaded":false}, "icon":"fa fa-briefcase icon-warning"});
                                    	} else {
                                    		$.get(ctx + "/resources/tree?resourceId=" + authId).done(function (d) {
                                    			callback.call({"id":"-1","text":"系统资源"}, d.children);
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
                        	   $('#urls').val(data.selected);
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
                        	
                        	var modal = $('#updateModel').modal('show');
                        	modal.on("hidden.bs.modal",function(e){
                        		$(this).removeData();
                        	});
                        	
                        	$("#saveModel").unbind("click").click(function(){
                        		var json = $('form#updateModelForm').serializeJSON();
                        		$.ajax({
                                	type:"post",
                                    contentType:"application/json; charset=UTF-8",
                                    url: ctx + "/authorities/update",
                                    data:json,
                                    dataType:"json",
                                    success: function(msg){
                                    	modal.modal('hide');
                                    	bootbox.alert("更新权限信息成功！");
                                    	// update table row
                                    	var jsonObj = JSON.parse(json);
                                        oTable.fnUpdate(jsonObj.authName, nRow, 0, false);
//                                        oTable.fnUpdate(jsonObj.authDesc, nRow, 1, false);
                                        var menu = $("#updateModuleId").find("option:selected").text();
                                        oTable.fnUpdate(menu, nRow, 1, false);
                                        var showMenu = jsonObj.showMenu == true ? "是" : "否";
                                        oTable.fnUpdate(showMenu, nRow, 2, false);
                                        oTable.fnUpdate(jsonObj.action, nRow, 3, false);
                                        oTable.fnUpdate(jsonObj.orders, nRow, 4, false);
                                        var value = jsonObj.enabled == '1' ? "是" : "否";
                                        oTable.fnUpdate(value, nRow, 5, false);
                                        //oTable.fnUpdate(jsonObj.moduleId, nRow, 5, false);
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