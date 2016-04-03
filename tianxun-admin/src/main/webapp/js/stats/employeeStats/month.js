var EmployeeStatsModule = function() {
	var editable = function() {

		return {
			init : function() {
				var oTable = $('#modelEditable').dataTable({
					"aLengthMenu" : [ [ 5, 10, 20, 50 ], [ 5, 10, 20, 50 ] ],
//					"autoWidth" : false,
					"scrollX": true,
					"displayLength" : 10,
					"language" : {
						"lengthMenu" : "每页 _MENU_ 条记录",
						"paginate" : {
							"previous" : "上一页",
							"next" : "下一页"
						},
						"info" : "从 _START_ 到  _END_ 条，总记录为 _TOTAL_ 条",
						"infoEmpty" : "记录数为0",
					},
					"columnDefs" : [ {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 0 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 1 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 2 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 3 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 4 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 5 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 6 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 7 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 8 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 9 ]
					}, {
						"data" : null,
						'sortable' : false,
						"defaultContent" : "<center>-</center>",
						"targets" : [ 10 ]
					} ],
					"columns" : [ {
						"data" : "name"
					}, {
						"data" : "groupName"
					}, {
						"data" : "investorNum"
					}, {
						"data" : "dealInvestorNum"
					}, {
						"data" : "investorTurnRate"
					}, {
						"data" : "appointmentNum"
					}, {
						"data" : "checkinNum"
					}, {
						"data" : "ordersNum"
					}, {
						"data" : "ordersAmount"
					}, {
						"data" : "ordersTurnRate"
					}, {
						"data" : "investorUnitPrice"
					} ],
					"filter" : false,
					"processing" : false,
					"serverSide" : true,
					"ajaxSource" : ctx + "/employeeStats/queryMonth",
					"serverMethod" : "post",
					"fnRowCallback" : function(nRow, aData, displayIndex) {
						nRow.children[0].id = aData.id;

						var nf=new DecimalFormat();
					    nf.applyPattern("000.00%");
					    var investorTurnRateStr=nf.format(aData.investorTurnRate);
					    var ordersTurnRateStr=nf.format(aData.ordersTurnRate);
					    $('td:eq(4)', nRow).html(investorTurnRateStr);
					    $('td:eq(9)', nRow).html(ordersTurnRateStr);
					     
					    nf.applyPattern("0000.00");
					    var ordersAmountStr=nf.format(aData.ordersAmount/10000);
					    var investorUnitPriceStr=nf.format(aData.investorUnitPrice/10000);
					    $('td:eq(8)', nRow).html(ordersAmountStr+"万");
					    $('td:eq(10)', nRow).html(investorUnitPriceStr+"万");
						
						
						return nRow;
					},
					"fnServerData" : function(ajaxSource, dataSet, fnCallback) {
						var start = $("#date_added").val();
						var startDate = new Date(start);
						var end = $("#date_modified").val();
						var endDate = new Date(end);
						if (endDate < startDate) {
							bootbox.alert("开始时间不能大于结束时间");
							return false;
						}
						var startYear = startDate.getFullYear();
						var endYear = endDate.getFullYear();
						var startMonth = startDate.getMonth();
						var endMonth = endDate.getMonth();
						if (startYear==endYear) {
							if (endMonth-5 > startMonth) {
								bootbox.alert("请选择不超过6个月的时间间隔");
								return false;
							}
						} else {
							if (endMonth+12-5 > startMonth) {
								bootbox.alert("请选择不超过6个月的时间间隔");
								return false;
							}
						}
						
						
						var startTime = $('#date_added').val();
						var endTime = $('#date_modified').val();
						if (startTime != "") {
							startTime = startTime + "-01"
						}
						if (endTime != "") {
							endTime = endTime + "-01"
						}
						var groupId = $('#groupId').val();
						var form = '{"beginDate":"' + startTime + '","endDate":"' + endTime
								+ '","groupId":"' + groupId + '"}';
						
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
						var js = form.replace('}', ',');
						json = js + json + "}";
						$.ajax({
							"contentType" : "application/json; charset=UTF-8",
							"dataType" : 'json',
							"type" : "POST",
							"url" : ajaxSource,
							"data" : json,
							"success" : fnCallback
						});
					},
				});
				$('#queryBtn').click(function() {
					oTable.fnClearTable();
				});
			}
		};
	}();

	function GetDateStr(AddMonthCount){
	    var dd = new Date();
	     dd.setMonth(dd.getMonth()+AddMonthCount);//获取AddDayCount天后的日期
	     return dd.format('yyyy-MM');
	}

	//初始化日期输入框
	function initDateInput(){
		document.getElementById('date_modified').value = GetDateStr(0);
		document.getElementById('date_added').value = GetDateStr(-5);
	}

	Date.prototype.format = function(format){
		var o = {
		"M+": this.getMonth() + 1, //month
		"d+": this.getDate(), //day
		"h+": this.getHours(), //hour
		"m+": this.getMinutes(), //minute
		"s+": this.getSeconds(), //second
		"q+": Math.floor((this.getMonth() + 3) / 3), //quarter
		"S": this.getMilliseconds() //millisecond
		}
		if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
		if (new RegExp("(" + k + ")").test(format))
		format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		return format;
	}

	
	return {
		init : function() {
			editable.init();
			initDateInput()
		}
	};
}();
$('#downloadReport').click(function() {
	var start = $("#date_added").val();
	var startDate = new Date(start);
	var end = $("#date_modified").val();
	var endDate = new Date(end);
	if (endDate < startDate) {
		bootbox.alert("开始时间不能大于结束时间");
		return false;
	}
	var startYear = startDate.getFullYear();
	var endYear = endDate.getFullYear();
	var startMonth = startDate.getMonth();
	var endMonth = endDate.getMonth();
	if (startYear==endYear) {
		if (endMonth-5 > startMonth) {
			bootbox.alert("请选择不超过6个月的时间间隔");
			return false;
		}
	} else {
		if (endMonth+12-5 > startMonth) {
			bootbox.alert("请选择不超过6个月的时间间隔");
			return false;
		}
	}
	var startTime = $('#date_added').val();
	var endTime = $('#date_modified').val();
//	if (startTime != "") {
//		startTime = startTime + "-01"
//	}
//	if (endTime != "") {
//		endTime = endTime + "-01"
//	}
//	var groupId = $('#groupId').val();
//	var form = '{"beginDate":"' + startTime + '","endDate":"' + endTime
//			+ '","groupId":"' + groupId + '"}';
//	$.ajax({
//		type : "post",
//		contentType : "application/json; charset=UTF-8",
//		url : ctx + "/employeeStats/downloadReportMonth",
//		data : form,
//		error : function(msg) {
//			bootbox.alert("oh no！");
//		}
//	});
	
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action",ctx + "/employeeStats/downloadReportMonth");
	var input1=$("<input>");
	input1.attr("type","hidden");
	input1.attr("name","startTime");
	input1.attr("value",startTime);
	var input2=$("<input>");
	input2.attr("type","hidden");
	input2.attr("name","endTime");
	input2.attr("value",endTime);
	$("body").append(form);//将表单放置在web中
	form.append(input1);
	form.append(input2);
	form.submit();//表单提交 
});