var MenuModule = function () {
	
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
						    "defaultContent": "Edit",
						    "targets": [3]
						}            
                    ],
                    "columns": [
                        { "data": "moduleName" },
                        { "data": "enabled" },
                        { "data": "orders" }
                   ],
                   "filter" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/moduleMenu/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.moduleId;
                	   
                	   var enabled = "否";
                	   if (aData.enabled == 1) {
                		   enabled = "是";
                	   }
                	   $('td:eq(1)', nRow).html(enabled);
                	   
                	   $('td:eq(3)', nRow).html('<a class="edit fa fa-pencil-square-o fa-lg" title="编辑" href="javascript:;"></a> &nbsp;<a title="删除" class="delete fa fa-trash-o fa-lg" href="javascript:;"></a>');
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
                                url: ctx + "/moduleMenu/delete",
                                data:"id=" + id,
                                dataType:"json",
                                success: function(msg){
                                	if (msg.code == 1) {
                                		bootbox.alert("删除菜单成功！");
                                		oTable.fnDeleteRow(nRow);
                                	} else {
                                		bootbox.alert("删除菜单失败！");
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
                        url: ctx + "/moduleMenu/edit",
                        data:"moduleId=" + id,
                        dataType:"json",
                        success: function(msg){
                        	$("#updateBody").empty();// clean
                        	var tmpl = $("#updateTemplate").tmpl(msg).appendTo("#updateBody");
                        	var modal = $('#updateModel').modal('show');
                        	modal.on("hidden.bs.modal",function(e){
                        		$(this).removeData();
                        	});
                        	
                        	$("#saveModel").unbind("click").click(function(){
                        		var json = $('form#updateModelForm').serializeJSON();
                        		$.ajax({
                                	type:"post",
                                    contentType:"application/json; charset=UTF-8",
                                    url: ctx + "/moduleMenu/update",
                                    data:json,
                                    dataType:"json",
                                    success: function(msg){
                                    	modal.modal('hide');
                                    	bootbox.alert("更新菜单信息成功！");
                                    	
                                    	// update table row
                                    	var jsonObj = JSON.parse(json);
                                        oTable.fnUpdate(jsonObj.moduleName, nRow, 0, false);
                                        var value = jsonObj.enabled == '1' ? "是" : "否";
                                        oTable.fnUpdate(value, nRow, 1, false);
                                        oTable.fnUpdate(jsonObj.orders, nRow, 2, false);
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