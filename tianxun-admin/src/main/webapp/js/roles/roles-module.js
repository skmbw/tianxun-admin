var RolesModule = function () {
	
    var editable = function () {

        return {
            init: function () {
                var oTable = $('#modelEditable').dataTable({
                	//"autoWidth": false,
                    "scrollX": true,
                	"lengthMenu": [
                        [10, 15, 20, 50, -1],
                        [10, 15, 20, 50, "All"]
                    ],
//                    "auths": {"edit": "/roles/edit",
//                    	"delete": "/roles/edit",
//                    	"auth": "/roles/auth"
//                    	},
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
						    "targets": [2]
						}            
                    ],
                    "columns": [
                        { "data": "roleName" },
//                        { "data": "roleDesc" },
                        { "data": "enabled" }
//                        { "data": "priority" }
                   ],
                   "filter" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/roles/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.roleId;
                	   var enabled = aData.enabled = 1 ? '是' : '否';
                   	   //$('td:eq(4)', nRow).html('<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a> &nbsp;<a title="删除" class="delete fa fa-trash-o fa-lg" href="javascript:;"></a> &nbsp;<a class="auth fa fa-lock fa-lg" title="角色权限" href="javascript:;"></a>');
                	   $('td:eq(1)', nRow).html(enabled);
                	   $('td:eq(2)', nRow).html(__auths);
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
                                url: ctx + "/roles/delete",
                                data:"id=" + id,
                                dataType:"json",
                                success: function(msg){
                                	if (msg.code == 1) {
                                		bootbox.alert("删除角色成功！");
                                		oTable.fnDeleteRow(nRow);
                                	} else {
                                		bootbox.alert("删除角色失败！");
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
                        url: ctx + "/roles/edit",
                        data:"roleId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#updateBody").empty();// clean
                        	var tmpl = $("#updateTemplate").tmpl(msg).appendTo("#updateBody");
                        	var s = false;
                        	// 将权限树添加到更新模板上
                        	$('#authTree').jstree({
                                'plugins': ["wholerow", "checkbox", "types"],
                                'core': {
                                    "themes" : {
                                        "responsive": false
                                    },    
                                    'data': function(node, callback) { // 使用回调的方式
                                    	var roleId = $('#roleId').val();
                                    	if (roleId == undefined || roleId == "") {
                                    		roleId = "null";
                                    	}
                                    	if (node.id == "#") {
                                    		callback({"id":"-1","text":"系统权限", "state":{"loaded":false}, "icon":"fa fa-briefcase icon-warning"});
                                    	} else {
                                    		$.get(ctx + "/authorities/tree?roleId=" + roleId).done(function (d) {
                                    			callback.call({"id":"-1","text":"系统权限"}, d.children);
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
                            	if (data.action == 'select_node' || data.action == 'deselect_node') {
                            		var json = data.instance.get_json();
                                	var array = json[0].children;
                                	var selected = '';
                                	for (var i = 0; i < array.length; i++) {
                                		var leafArray = array[i].children;
                                		for (var j = 0; j < leafArray.length; j++) {
                                			var leaf = leafArray[j];
                                			if (leaf.state.selected) {
                                				selected = selected + leaf.id + ',';
                                			}
                                		}
                                	}
                                	$('#authIds').val(selected);
                            	}
                    	    }).on("open_node.jstree", function (e, data) {
                            	var id = data.node.id;
                            	if (id == '-1') {
                            		s = true;
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
                        		if (!s) { // 不点开直接保存
                        			modal.modal('hide');
                        			return;
                        		}
                        		$.ajax({
                                	type:"post",
                                    contentType:"application/json; charset=UTF-8",
                                    url: ctx + "/roles/update",
                                    data:json,
                                    dataType:"json",
                                    success: function(msg){
                                    	modal.modal('hide');
                                    	bootbox.alert("更新角色信息成功！");
                                    	
                                    	// update table row
                                    	var jsonObj = JSON.parse(json);
                                    	oTable.fnUpdate(jsonObj.roleName, nRow, 0, false);
//                                    	oTable.fnUpdate(jsonObj.roleDesc, nRow, 1, false);
                                        var value = jsonObj.enabled == '1' ? "是" : "否";
                                        oTable.fnUpdate(value, nRow, 1, false);
//                                        oTable.fnUpdate(jsonObj.priority, nRow, 3, false);
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
                
                // 权限
                $('#modelEditable').on("click", "a.auth", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                	
                    $.ajax({
                    	type:"post",
                        url: ctx + "/roles/edit",
                        data:"roleId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#detailBody").empty();// clean
                        	var tmpl = $("#detailTemplate").tmpl(msg).appendTo("#detailBody");
                        	
                        	$('#detailAuthTree').jstree({
                                'plugins': ["wholerow", "checkbox", "types"],
                                'core': {
                                    "themes" : {
                                        "responsive": false
                                    },    
                                    'data': function(node, callback) { // 使用回调的方式
                                    	var roleId = $('#roleId').val();
                                    	if (roleId == undefined || roleId == "") {
                                    		roleId = "null";
                                    	}
                                    	if (node.id == "#") {
                                    		callback({"id":"-1","text":"系统权限", "state":{"loaded":false, "disabled": true, "opened":true}, "icon":"fa fa-briefcase icon-warning"});
                                    	} else {
                                    		$.get(ctx + "/authorities/tree?enabled=0&roleId=" + roleId).done(function (d) {
                                    			callback.call({"id":"-1","text":"系统权限"}, d.children);
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