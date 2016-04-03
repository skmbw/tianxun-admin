//查询统计数据
function queryData(){
	var json = "";
	$.ajax({
		type:"post",
	    contentType:"application/json; charset=UTF-8",
	    url: ctx + "/platSum/queryProductSale",
	    data:json,
	    dataType:"json",
	    success: function(msg){
	    	if(msg.data != null){
	    		productSale(msg.data);
	    	}else{
	    		bootbox.alert("产品销售数据为空!");
	    	}
	    },
	    error: function (msg) {
	    	bootbox.alert("服务器偷了个小懒,产品销售统计失败了！");
	    }
	});
}

function productSale(value){
	var myChart = echarts.init(document.getElementById('productSale'));
	option = {
		    title : {
		        text: '产品销售额占比',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    noDataLoadingOption:{
		    	text : '数据暂时为空',
		    	effect : 'whirling'
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'right',
		        data:value.names
		    },
		    calculable : true,
		    series : [
		        {
		            name:'产品销售额（万元）',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:value.serial
		        }
		    ]
		};
		                    
	myChart.setOption(option); 	                    
}
