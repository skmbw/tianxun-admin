//查询统计数据
function queryData(){
	var json = "";
	$.ajax({
		type:"post",
	    contentType:"application/json; charset=UTF-8",
	    url: ctx + "/platSum/queryGroupDataSum",
	    data:json,
	    dataType:"json",
	    success: function(msg){
	    	if(msg.data != null){
	    		groupDataSum(msg.data);
	    	}else{
	    		bootbox.alert("团队销售数据为空!");
	    	}
	    },
	    error: function (msg) {
	    	bootbox.alert("服务器偷了个小懒,业绩统计失败了！");
	    }
	});
}

function groupDataSum(value){
	var myChart = echarts.init(document.getElementById('groupDataSum'));
	option = {
		    title : {
		        text: '团队销售汇总',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['销售额（万元）']
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : value.groupNames
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'销售额（万元）',
		            type:'bar',
		            data: value.groupValues,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
		                    
	myChart.setOption(option); 	                    
}
