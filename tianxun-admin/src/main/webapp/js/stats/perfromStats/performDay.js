function GetDateStr(AddDayCount){
    var dd = new Date();
     dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
     return dd.format('yyyy-MM-dd');
}

//初始化日期输入框
function initDateInput(){
	document.getElementById('date_modified').value = new Date().format('yyyy-MM-dd');
	document.getElementById('date_added').value = GetDateStr(-6);
}

$('#btn_query_data').click(function (){
	queryData();
})
	
//查询统计数据
function queryData(){
	var startTime = document.getElementById('date_added').value;
	var endTime = document.getElementById('date_modified').value;
	if(endTime <= startTime){
		bootbox.alert("日期范围选择有误！");
		return;
	}
	var s = (new Date(endTime).getTime() - new Date(startTime).getTime())/(24 * 60 * 60 * 1000);
	if(s >= 31){
		bootbox.alert("日期范围不能超过30天！");
		return;
	}
	json = "{\"startTime\":\""+startTime+"\",\"endTime\":\""+endTime+"\",\"formatStr\":\"yyyy-MM-dd\"}";
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


$('#btn_data').click(function(){
    $.ajax({
    	type:"post",
        contentType:"application/json; charset=UTF-8",
        url: ctx + "/performStats/cache",
        data:"",
        dataType:"json",
        success: function(msg){
        },
        error: function (msg) {
        }
    });
});

