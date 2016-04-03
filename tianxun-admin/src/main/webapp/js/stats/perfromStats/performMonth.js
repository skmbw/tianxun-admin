function GetDateStr(AddDayCount){
     var dd = new Date();
     dd.setMonth(dd.getMonth() - 6);
     return dd.format('yyyy-MM');
}

//初始化日期输入框
function initDateInput(){
	document.getElementById('date_modified').value = new Date().format('yyyy-MM');
	document.getElementById('date_added').value = GetDateStr(-6);
}

$('#btn_query_data').click(function (){
	queryData();
})
	
//查询统计数据
function queryData(){
	var daysInMonth = new Array([0],[31],[28],[31],[30],[31],[30],[31],[31],[30],[31],[30],[31]);
	var startTime = document.getElementById('date_added').value+"-01";
	var endTime = document.getElementById('date_modified').value;
	var date = new Date(endTime);
	var strYear = date.getFullYear();     
    var strMonth = date.getMonth()+1;   
    if(strYear%4 == 0 && strYear%100 != 0){   
       daysInMonth[2] = 29;   
    }  
    endTime = endTime +"-"+daysInMonth[strMonth];
	if(endTime < startTime){
		bootbox.alert("日期范围选择有误！");
		return;
	}
	var s = (new Date(endTime).getMonth() - new Date(startTime).getMonth());
	if(s >= 7){
		bootbox.alert("日期范围不能超过6个月！");
		return;
	}
	json = "{\"startTime\":\""+startTime+"\",\"endTime\":\""+endTime+"\",\"formatStr\":\"yyyy-MM\"}";
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
