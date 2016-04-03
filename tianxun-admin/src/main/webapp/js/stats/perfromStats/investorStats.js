//查询统计数据
function queryData(){
	var json = "";
	$.ajax({
		type:"post",
	    contentType:"application/json; charset=UTF-8",
	    url: ctx + "/investorStats/queryData",
	    data:json,
	    dataType:"json",
	    success: function(msg){
	    	if(msg.data != null){
	    		drawGenderStats(msg.data.gender);
		    	drawAgeStats(msg.data.age);
		    	drawAssetesStats(msg.data.assetes);
		    	if(msg.data.maxPro != null && msg.data.maxPro != null){
		    		drawAreaStats(msg.data.province,msg.data.city,msg.data.maxPro,msg.data.maxCity);	
		    	}
	    	}else{
	    		bootbox.alert("投资人统计数据为空!");
	    	}
	    },
	    error: function (msg) {
	    	bootbox.alert("服务器偷了个小懒,业绩统计失败了！");
	    }
	});
}

function drawGenderStats(value){
	var myChart = echarts.init(document.getElementById('mainGender'));
	option = {
		    title : {
		        text: '投资人男女比例',
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
		        data:['男','女','未知']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'性别分布',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data: value
		        }
		    ]
		};
	myChart.setOption(option); 	                    
}
function drawAgeStats(value){
	var myChart = echarts.init(document.getElementById('mainAge'));
	option = {
		    title : {
		        text: '投资人年龄分布比例',
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
		        data:['60岁以上','50-60岁','40-50岁','30-40岁','30岁以下','未知']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'年龄分布',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data: value
		        }
		    ]
		};
	myChart.setOption(option); 	                    
}
function drawAssetesStats(value){
	var myChart = echarts.init(document.getElementById('mainAssetes'));
	option = {
		    title : {
		        text: '投资人资产分布占比',
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
		        data:['1000万以上','500-1000万','300-500万','100-300万','100万及以下']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'资产分布',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data: value
		        }
		    ]
		};
	myChart.setOption(option); 	                    
}

function drawAreaStats(province,city,maxPro,maxCity){
	var myChart = echarts.init(document.getElementById('mainArea'));
	option = {
			title : {
		        text: '投资人区域分布',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    heatmap: {
                minAlpha: 0.1,
                data: province
            },
            dataRange: {
                x : 'right',
                min: 0,
                max: maxPro,
                color: ['orangered','yellow','lightskyblue'],
                text:['High','Low'],           // 文本，默认为数值文本
                calculable : true
            },
		    series : [
		        {
		            tooltip: {
		                trigger: 'item',
		                formatter: '{b}:{c}'
		            },
		            name: '省级分布',
		            type: 'map',
		            mapType: 'china',
		            mapLocation: {
		                x: 'left',
		                y: 'center',
		                width: '50%'
		            },
		            hoverable:false,
		            roam: false,
		            scaleLimit:{max:100, min:1},
		            selectedMode : 'single',
		            itemStyle:{
		            	normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:province
		        }
		    ],
		    animation: false
		};
		myChart.on('mapSelected', function (param){
		    var selected = param.selected;
		    var selectedProvince;
		    var name;
		    for (var i = 0, l = option.series[0].data.length; i < l; i++) {
		        name = option.series[0].data[i].name;
		        option.series[0].data[i].selected = selected[name];
		        if (selected[name]) {
		            selectedProvince = name;
		        }
		    }
		    if (typeof selectedProvince == 'undefined') {
		        option.series.splice(1);
		        option.legend = null;
		        option.dataRange = null;
		        myChart.setOption(option, true);
		        return;
		    }
		    option.series[1] = {
		        name: '投资人区域分布',
		        type: 'map',
		        mapType: selectedProvince,
		        itemStyle:{
		            normal:{label:{show:true}},
		            emphasis:{label:{show:true}}
		        },
		        mapLocation: {
		            x: '45%',
		            y: '10%'
		        },
		        roam: false,
		        hoverable:false,
	            scaleLimit:{max:1, min:1},
		        data:city
		    };
		    option.legend = {
		        x:'right',
		        data:['投资人区域分布']
		    };
		   
		    option.dataRange = {
		        orient: 'horizontal',
		        x: 'right',
		        min: 0,
		        max: maxCity,
		        color:['orangered','yellow','lightskyblue'],
		        text:['高','低'],           // 文本，默认为数值文本
		        splitNumber:0,
		        calculable : true
		    };
		    myChart.setOption(option, true);
		})
		myChart.setOption(option);	                    
}

$('#btn_data').click(function(){
        $.ajax({
        	type:"post",
            contentType:"application/json; charset=UTF-8",
            url: ctx + "/investorStats/cache",
            data:"",
            dataType:"json",
            success: function(msg){
            },
            error: function (msg) {
            }
        });
});