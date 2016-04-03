var EmployeeStatsModule = function() {
	var editable = function() {

		return {
			init : function() {
				var oTable = $('#modelEditable')
						.dataTable(
								{
									"aLengthMenu" : [ [ 5, 10, 20, 50 ],
											[ 5, 10, 20, 50 ] ],
//									"autoWidth" : false,
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
									"columnDefs" : [
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 0 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 1 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 2 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 3 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 4 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 5 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 6 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 7 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 8 ]
											},
											{
												"data" : null,
												'sortable' : false,
												"defaultContent" : "<center>-</center>",
												"targets" : [ 9 ]
											},
											{
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
									"ajaxSource" : ctx
											+ "/employeeStats/queryYear",
									"serverMethod" : "post",
									"fnRowCallback" : function(nRow, aData,
											displayIndex) {
										nRow.children[0].id = aData.id;

										var nf = new DecimalFormat();
										nf.applyPattern("000.00%");
										var investorTurnRateStr = nf
												.format(aData.investorTurnRate);
										var ordersTurnRateStr = nf
												.format(aData.ordersTurnRate);
										$('td:eq(4)', nRow).html(
												investorTurnRateStr);
										$('td:eq(9)', nRow).html(
												ordersTurnRateStr);

										nf.applyPattern("0000.00");
										var ordersAmountStr = nf
												.format(aData.ordersAmount / 10000);
										var investorUnitPriceStr = nf
												.format(aData.investorUnitPrice / 10000);
										$('td:eq(8)', nRow).html(
												ordersAmountStr + "万");
										$('td:eq(10)', nRow).html(
												investorUnitPriceStr + "万");

										return nRow;
									},
									"fnServerData" : function(ajaxSource,
											dataSet, fnCallback) {
										var json = "";
										var s;
										for (var i = 0; i < dataSet.length; i++) {
											s = dataSet[i];
											if (s.name == 'sEcho') {
												json = json + '"echo":'
														+ s.value;
											} else if (s.name == 'iDisplayStart') {
												json = json + ',"start":'
														+ s.value;
											} else if (s.name == 'iDisplayLength') {
												json = json + ',"pageSize":'
														+ s.value;
											}
										}
										// var form =
										// $("form#queryForm").serializeJSON();
										// var js = form.replace('}', ',');
										json = "{" + json + "}";
										$
												.ajax({
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

	return {
		init : function() {
			editable.init();
		}
	};
}();
$('#downloadReport').click(function() {
	var form = $("<form>");// 定义一个form表单
	form.attr("style", "display:none");
	form.attr("target", "");
	form.attr("method", "post");
	form.attr("action", ctx + "/employeeStats/downloadReportYear");
	$("body").append(form);// 将表单放置在web中
	form.submit();// 表单提交
});