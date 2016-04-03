	
//查询统计数据
function queryData(){
	var endTime = new Date().format('yyyy-MM-dd');;
	json = "{\"endTime\":\""+endTime+"\",\"formatStr\":\"yyyy\"}";
	$.ajax({
		type:"post",
	    contentType:"application/json; charset=UTF-8",
	    url: ctx + "/performStats/queryData",
	    data:json,
	    dataType:"json",
	    success: function(msg){
	    	drawOrderDayStats(msg);
	    	drawInvestorDayStats(msg);
	    	drawAppointmentDayStats(msg);
	    },
	    error: function (msg) {
	    	bootbox.alert("服务器偷了个小懒,业绩统计失败了！");
	    }
	});
}
