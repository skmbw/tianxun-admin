var CheckinModule = function () {
    var editable = function () {

        return {
            init: function () {
                var oTable = $('#modelEditable').dataTable({
                    "lengthMenu": [
                        [10, 15, 20, 50, -1],
                        [10, 15, 20, 50, "All"]
                    ],
                    "scrollX":true,
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
						    'sortable': false,
						    "defaultContent": "",
						    "targets": [4]
						}            
                    ],
                    "columns": [
                       { "data": "signDate" },
                       { "data": "employeeNo" },
                       { "data": "employeeName" },
                       { "data": "count" }
                   ],
                   "filter" : false,
                   "sortable" : false,
                   "processing": false,
                   "serverSide": true,
                   "ajaxSource": ctx + "/checkin/query",
                   "serverMethod": "post",
                   "fnRowCallback" : function(nRow, aData, displayIndex) {
                	   nRow.children[0].id = aData.employeeId;
                	   var signDate = aData.signDate;
                	   if (signDate != undefined && signDate != '') {
                		   signDate = signDate.substring(0, 10);
                		   $('td:eq(0)', nRow).html(signDate);
                	   }
                	   $('td:eq(4)', nRow).html('<a class="detail fa fa-bars fa-lg" title="签到详情" href="javascript:;"></a>');
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
                
                // 明细
                $('#modelEditable').on("click", "a.detail", (function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
                    var id = nRow.children[0].id;
                    var date = nRow.children[0].innerText;
                	window.location.href = ctx + '/checkin/detail/' + id + '/'+ date;
                }));
                
                $('#queryBtn').click(function(){
                	var signDate = $('#signDate').val();
    				var reg = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
    				if (signDate != '' && !reg.test(signDate)) {
    					bootbox.alert('日期格式不正确！');
    					return;
    				}
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