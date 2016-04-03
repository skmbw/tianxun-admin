function drawOrderDayStats(value){
	var myChart = echarts.init(document.getElementById('mainOrder'));
	var data = value.data.order;
	var xData = [];
	var yData = [];
	for(var i=0;i<data.length;i++){
		xData[i] = data[i].date;
		yData[i] = data[i].sales/10000;
	}
	var option = {
			title : {
		        text: '销售额变化趋势图',
		        x : 'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    noDataLoadingOption:{
		    	text : '销售额统计数据为空',
		    	effect : 'whirling'
		    },
		    legend: {
		        data:['销售额'],
		        x : 'right',
		    },
		    toolbox: {
		        show : true
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            name : '日期',
		            data : xData
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name : '销售额(万元)',
		            splitArea : {show : true},
		            precision :2
		        }
		    ],
		    series : [
		        {
		            name:'销售额',
		            type:'line',
		            data:yData,
		            precision :2
		        }
		    ]
		};
		myChart.setOption(option); 
}


function drawInvestorDayStats(value){
	var myChart = echarts.init(document.getElementById('mainInvestor'));
	var data = value.data.investor;
	var xData = [];
	var yData = [];
	for(var i=0;i<data.length;i++){
		xData[i] = data[i].date;
		yData[i] = data[i].count;
	}
	var option = {
			title : {
		        text: '新增投资人变化趋势图',
		        x : 'center'
		    },
		    noDataLoadingOption:{
		    	text : '新增投资人统计数据为空',
		    	effect : 'whirling'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['新增投资人'],
		        x : 'right',
		    },
		    toolbox: {
		        show : true
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            name : '日期',
		            data : xData
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name : '人数(个)',
		            splitArea : {show : true}
		        }
		    ],
		    series : [
		        {
		            name:'新增投资人',
		            type:'line',
		            data:yData
		        }
		    ]
		};
		myChart.setOption(option); 
}

function drawAppointmentDayStats(value){
	var myChart = echarts.init(document.getElementById('mainAppointment'));
	var data = value.data.appointment;
	var xData = [];
	var yData = [];
	for(var i=0;i<data.length;i++){
		xData[i] = data[i].date;
		yData[i] = data[i].count;
	}
	var option = {
			title : {
		        text: '预约统计变化趋势图',
		        x : 'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    noDataLoadingOption:{
		    	text : '预约统计数据为空',
		    	effect : 'whirling'
		    },
		    legend: {
		        data:['新增预约数'],
		        x : 'right',
		    },
		    toolbox: {
		        show : true
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            name : '日期',
		            data : xData
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name : '数量',
		            splitArea : {show : true}
		        }
		    ],
		    series : [
		        {
		            name:'新增预约数',
		            type:'line',
		            data:yData
		        }
		    ]
		};
		myChart.setOption(option); 
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