var productChartModule = function () {
    var editable = function () {

        return {
            init: function () {
            	var option = {
                	    title : {
                	        text: '产品销售速率',
                	        z:0,
                	    },
                	    tooltip : {
                	        trigger: 'axis',
            	        	formatter: function (params,ticket,callback) {
            	                console.debug(params)
            	                var res = '详细信息 : <br/>';
            	                res += '募集第' + parseInt(params[0].name) + '天';
            	                for (var i = 0, l = params.length; i < l; i++) {
            	                    res += '<br/>' + params[i].seriesName + ' : <br/>完成度：';
            	                    res += params[i].value == '-' ? '-': fmoney(params[i].value,2) + '%';
            	                    res += '<br/>累计成交额：';
            	                    res	+= params[i].series.amount[params[i].dataIndex] == undefined ? '-': (fmoney(params[i].series.amount[parseInt(params[0].name)-1]/10000) + '万元');
            	                }
            	                /*setTimeout(function (){
            	                    // 仅为了模拟异步回调
*/    	                	                    callback(ticket, res);
            	                /*}, 1000)*/
//            	                return 'loading';
								return res;
            	            }
                	    },
                	    dataZoom: {
                	        show: true,
                	        start : 0,
                	        end : 100,
//                	        realtime:true
                	    },
//                	    dataRange: {
//                	    	show:true,
//                	        min: 0,
//                	        max: 100,
//                	        orient: 'horizontal',
//                	        y: 30,
//                	        x: 'center',
//                	        //text:['高','低'],           // 文本，默认为数值文本
//                	        color:['lightgreen','orange']
//                	    },
                	    legend: {
                	        data:[],
                	    	z:0
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
//                	            mark : {show: true},
//                	            dataView : {show: true, readOnly: false},
//                	            magicType : {show: true, type: ['line', 'bar']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            name: "天",
                	            boundaryGap : false,
                	            data : [],
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value',
                	            name: "百分比",
                	            lineStyle:'width:10px',
                	            axisLabel : {
                	                formatter: '{value} %'
                	            },
                	        	splitNumber:5,
                	        	min:0,
                	        	max:100
                	        }
                	    ],
                	    series : [
                	    ]
                	};
            	//格式化金额
            	function fmoney(s, n) {
            		if(s == 0){
            			return '0.00';
            		}
            	   if(s == ""|| undefined == s){
            		   return "--";
            	   }
                   n = n > 0 && n <= 20 ? n : 2;  
                   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位  
                   var l = s.split(".")[0].split("").reverse(),  
                   r = s.split(".")[1];  
                   t = "";  
                   for(i = 0; i < l.length; i++ )  
                   {  
                      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
                   }
                   var str = t.split("").reverse().join("") + "." + r.substring(0,2);
                   return str;//保留2位小数  如果要改动 把substring 最后一位数改动就可  
                }
            	var state = $('#state').val();
            	if(1 == state){
            		//Autocomplete
//                    $("#productNoSearch").autocomplete({
//                        source: function(query,process){
//                            $.post(ctx + "/statsProduct/product/"+query.term,{"matchCount":200},function(data){
//                            	var ebean = []
//                            	for (var int = 0; int < data.length; int++) {
//                            		var temp = [];
//                            		temp.label = data[int].briefName +"  "+ data[int].productCode;
//                            		temp.value = data[int].productCode;
//                            		ebean.push(temp);
//                        		}
//                                return process(ebean);
//                            });
//                        },
//                        search:function(event, ui){
//                        	$("#productNo").val("");
//                        },
//                        select: function(event, ui) {
//                            $("#productNoSearch").val(ui.item.label);
//                            $("#productNo").val(ui.item.value);
//                            var uistr =ui.item;
//                            // 阻止事件的默认行为
//                            event.preventDefault(); 
//                        }
//                    });
            		$('#productStat').addClass('active');
            		$('#productTable').removeClass('active');
            		$('#productProgress').removeClass('active');
            		require.config({
        	            paths: {
        	                echarts: './js/echart'
        	            }
        	        });
        	        require(
        	            [
        	                'echarts',
        	                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        	                'echarts/chart/bar'
        	            ],
        	            function (ec) {
        	            	var myChart = ec.init(document.getElementById('chart'));
        	                var list =[];
     	                    $("#productStatList option:selected").each(function(){
     	                       list.push('"' + $(this).val()+ '"');
     	                    });
        	                $.ajax({
        	                	"contentType":"application/json; charset=UTF-8",
                                "dataType" : 'json',
                                "type" : "POST",
                                "url" : ctx + "/productStats/productSaleRate",
                                "data" : '{"productIdList":['+ list + ']}',
                                "success" : function(msg){
                                	if (msg.code == 0) {
                                		bootbox.alert(msg.message);
                                		return;
                                	}
                                	if (msg.code == 1) {
                                		if(msg.data.productNameList.length == 0 || msg.data.productNameList == undefined){
                                    		option.series = [{}];
                                			option.legend.data = [];
                                			option.xAxis[0].data = [];
                                			myChart.hideLoading();
                                    		myChart.setOption(option);
                                    		return ;
                                		}
                                		option.legend.data = msg.data.productNameList;
                                		var num = msg.data.maxSalesTerm;
                                		if(num > 0){
                                			var array = new Array();
                                			for(i = 1;i <= num+1;i++){
                                				array.push(i);
                                			}
                                			option.xAxis[0].data = array;
                                		}
                                		option.series = msg.data.list;
                                		myChart.hideLoading();
                                		myChart.setOption(option);
                                	} else {
                                		bootbox.alert("获取产品销售数据失败！");
                                		myChart.setOption(option);
                                	}
                                }
        	                })
        	                
//        	                myChart.setOption(option);
        	            }
        	        );
            	}else if(2 == state){
            		$('#productTable').addClass('active');
            		$('#productStat').removeClass('active');
            		$('#productProgress').removeClass('active');
            		$('#productStatTable').css('display','display');
            		$('#queryBtn').html('查询');
            		var oTable = $('#productStatEditable').dataTable({
                        "lengthMenu": [
                            [10, 15, 20, 50],
                            [10, 15, 20, 50]
                        ],
                        autoWidth:false,
                        "displayLength": 10,
                        "language": {
                            "lengthMenu": "每页 _MENU_ 条记录",
                            "zeroRecords":"数据为空",
                            "paginate": {
                                "previous": "上一页",
                                "next": "下一页"
                            },
                            "info" : "从 _START_ 到  _END_ 条，总记录为 _TOTAL_ 条",    
                            "infoEmpty" : "记录数为0",
                        },
                        "columnDefs": [          
                        ],
                        "columns": [
                           { "data": "briefName" ,'sortable': false,"defaultContent": "" },
                           { "data": "groupName" ,'sortable': false,"defaultContent": "" },
                           { "data": "totalOrderNum" ,'sortable': false,"defaultContent": "" },
                           { "data": "totalSales" ,'sortable': false,"defaultContent": "" },
                       ],
                       "filter" : false,
                       "processing": false,
                       "serverSide": true,
                       "ajaxSource": ctx + "/productStats/productEmpStats",
                       "serverMethod": "post",
                       "fnRowCallback" : function(nRow, aData, displayIndex) {
                    	   var briefName = 
                    		   "<center>" + aData.briefName + "</center>";
                    	   var groupName = 
                    		   "<center>" + aData.groupName + "</center>";
                    	   var totalOrderNum = 
                    		   "<center>" + aData.totalOrderNum + "</center>";
                    	   var totalSales = "";
                    	   if(aData.totalSales == undefined){
                    		   totalSales = "";
                    	   }else{
                    		   totalSales = 
                        		   "<center>" + fmoney(aData.totalSales/10000) + " 万</center>";
                    	   }
                    	   $('td:eq(0)', nRow).html(briefName);
                    	   $('td:eq(1)', nRow).html(groupName);
                    	   $('td:eq(2)', nRow).html(totalOrderNum);
                    	   $('td:eq(3)', nRow).html(totalSales);
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
    		                	   json = json + ',"page":' + s.value;
    		                   } else if (s.name == 'iDisplayLength') {
    		                	   json = json + ',"pageSize":' + s.value;
    		                   }
    	                   }
    	                   var list =[];
    	                   $("#productList option:selected").each(function(){
    	                      list.push('"' + $(this).val()+ '"');
    	                   });
    	                   var form = $("form#queryForm").serializeJSON();
                       	   var js = form.replace('}', '');
                       	   json = js + json + ',"productIdList":[' + list +']}';
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
            	}else{
            		$('#productProgress').addClass('active');
            		$('#productStat').removeClass('active');
            		$('#productTable').removeClass('active');
            		
            		require.config({
        	            paths: {
        	                echarts: './js/echart'
        	            }
        	        });
        	        require(
        	            [
        	                'echarts',
        	                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        	                'echarts/chart/bar'
        	            ],
        	            function (ec) {
        	                var myChart = ec.init(document.getElementById('chartProgress'));
        	                var zrColor = require('zrender/tool/color');
        	                var colorList = [
        	                  '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
        	                  '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0'
        	                ];
        	                var itemStyle = {
        	                	    normal: {
        	                	        color: function(params) {
        	                	          if (params.dataIndex < 0) {
        	                	            // for legend
        	                	            return zrColor.lift(
        	                	              colorList[colorList.length - 1], params.seriesIndex * 0.1
        	                	            );
        	                	          }
        	                	          else {
        	                	            // for bar
        	                	            return zrColor.lift(
        	                	              colorList[params.dataIndex], params.seriesIndex * 0.1
        	                	            );
        	                	          }
        	                	        }
        	                	    }
        	                	};
        	                var option = {
        	                	    title : {
        	                	        text: '产品募集进度'
        	                	    },
        	                	    tooltip : {
        	                			trigger: "item",
        	                			axisPointer: {
        	                				type: "shadow",
        	                				shadowStyle: {
        	                					color: "rgba(255, 255, 255, 0.1)",
        	                					width: "auto",
        	                					type: "default"
        	                				}
        	                			},
        	                	      	formatter: function(params) {
        	                	          // for text color
//        	                	          console.debug(params);
        	                	          var res = '<div style="color:' + '">';
        	                	          //        	                	            res += '<strong>' + params[0].seriesName[params[0].dataIndex] + ' : '+params[0].value + '%</strong>'
        	                	          res += '<strong>' + params.seriesName + ' : '+fmoney(params.value) + '%</strong>'
        	                	          res += '<br/>募集规模:' + fmoney(params.series.scale/10000) + '万元<br/>已募集金额：' + fmoney(params.series.amount/10000) + '万元'; 
        	                	          res += '</div>';
        	                	          return res;
        	                	        }
        	                		},
        	                	    legend: {
        	                	    	data:[],
        	                	    	orient:'horizontal',
		                	    		x: 'right', // 'center' | 'left' | {number},
		                	    	    y: 'center', // 'center' | 'bottom' | {number}
		                	    	    data:[],
		                	    	    z:0,
        	                	    },
        	                	    grid:{
        	                	    	x:'20',
        	                	    	x2:'300'
        	                	    },
        	                	    toolbox: {
        	                	        show : true,
        	                	        feature : {
//        	                	            dataView : {show: true, readOnly: false},
//        	                	            restore : {show: true},
        	                	            saveAsImage : {show: true}
        	                	        }
        	                	    },
        	                	    xAxis : [
        	                	        {
        	                	            type : 'value',
        	                	            axisLabel : {
        	                	                formatter: '{value} %'
        	                	            },
        	                	            scale:true,
        	                	        	splitNumber:10,
        	                	        	min:0,
        	                	        	max:100,
        	                	        }
        	                	    ],
        	                	    yAxis : [
        	                	        {
        	                	            type : 'category',
        	                	            data : [''],
        	                	        	
        	                	        }
        	                	    ],
        	                	    series : [
        	                	        {
        	                	            name:'',
        	                	            type:'bar',
        	                	            itemStyle: itemStyle,
        	                	            data:[30, 40, 20, 60, 100, 70]
        	                	        },
        	                	    ]
        	                	};
        	                $.ajax({
        	                	"contentType":"application/json; charset=UTF-8",
                                "dataType" : 'json',
                                "type" : "POST",
                                "url" : ctx + "/productStats/productRecruitProgress",
                                "data" : '',
                                "success" : function(msg){
                                	if (msg.code == 0) {
                                		bootbox.alert(msg.message);
                                		return;
                                	}
                                	if (msg.code == 1) {
//                                		option.legend.data = msg.data.productNameList;
                                		if(msg.data.productNameList == undefined || msg.data.productNameList.length == 0){
                                			option.yAxis[0].data = [];
                                			option.legend.data = [];
                                			option.series[0].name = [];
                                    		option.series[0].data = [];
                                    		option.series[0].scale = [];
                                    		myChart.hideLoading();
                                    		myChart.setOption(option);
                                    		return;
                                		}
                                		var num = msg.data.productNameList.length;
                                		var nameList = new Array();
                                		for(var i = 0;i < num;i+=1){
                                			nameList.push(msg.data.productNameList[i]);
//                                			if(msg.data.productNameList[i+1] != undefined){
//                                				nameList.push(msg.data.productNameList[i+1]);
//                                			}
                                			if(i != num - 1){
                                				nameList.push('');
                                			}
                                		}
                                		option.legend.data = nameList;
                                		if(num > 0){
                                			var array = new Array();
//                                			var arr = new Array();
                                			for(i = 0;i < num;i++){
                                				
                                				var p = {
                                						name : msg.data.productNameList[i],
                                						data: [msg.data.productRecruitProgressEleBeanList[i].recruitProgress],
                                						type: "bar",
                                						scale: msg.data.productRecruitProgressEleBeanList[i].scale,
                                						amount:msg.data.productRecruitProgressEleBeanList[i].amount
                                				}
                                				array.push(p);
//                                				arr.push(p);
                                			}
                                			option.series = array;
//                                			option.legend.data = arr;
                                		}
//                                		option.grid.x = '10%';
                                		myChart.hideLoading();
                                		myChart.setOption(option);
//                                		console.log(JSON.stringify(option));
//                                		console.debug(option)
                                	} else {
                                		bootbox.alert("获取产品募集销售数据失败！");
                                		myChart.setOption(option);
                                	}
                                }
        	                })
        	                
//        	                myChart.setOption(option);
        	            }
        	        );
            	}
            	//添加比较
	        	$('#compareBtn').click(function(){
	        		
	        		var list =[];
	                    $("#productStatList option:selected").each(function(){
	                       list.push('"' + $(this).val()+ '"');
	                    });
                    require.config({
        	            paths: {
        	                echarts: './js/echart'
        	            }
        	        });
        	        require(
        	            [
        	                'echarts',
        	                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        	                'echarts/chart/bar'
        	            ],
        	            function (ec) {
        	            	var myChart = ec.init(document.getElementById('chart'));
        	            	$.ajax({
        	                	"contentType":"application/json; charset=UTF-8",
                                "dataType" : 'json',
                                "type" : "POST",
                                "url" : ctx + "/productStats/productSaleRate",
                                "data" : '{"productIdList":['+ list + ']}',
                                "success" : function(msg){
                                	if (msg.code == 0) {
                                		bootbox.alert(msg.message);
                                		option.series = [{}];
                            			option.legend.data = [];
                            			option.xAxis[0].data = [];
                            			myChart.hideLoading();
                                		myChart.setOption(option);
                                		return;
                                	}
                                	if (msg.code == 1) {
                                		if(msg.data.productNameList.length == 0 || msg.data.productNameList == undefined){
                                    		option.series = [{}];
                                			option.legend.data = [];
                                			option.xAxis[0].data = [];
                                			myChart.hideLoading();
                                    		myChart.setOption(option);
                                    		return ;
                                		}
                                		option.legend.data = msg.data.productNameList;
                                		var num = msg.data.maxSalesTerm;
                                		if(num > 0){
                                			var array = new Array();
                                			for(i = 1;i <= num+1;i++){
                                				array.push(i);
                                			}
                                			option.xAxis[0].data = array;
                                		}
                                		option.series = msg.data.list;
                                		myChart.hideLoading();
                                		myChart.setOption(option);
                                	} else {
                                		bootbox.alert("获取产品销售数据失败！");
                                		myChart.setOption(option);
                                	}
                                }
        	                })
        	        	})
        	        });
                //查询
                $('#queryBtn').click(function(){
                	oTable.fnClearTable();
                });
                $('#cancleQuery').click(function(){
                	//重置按钮 清空datepicker缓存
                	$('#endStart').datepicker('setDate', null);
                	$('#endEnd').datepicker('setDate', null);
                });
                //报表下载
                $('#downloadBtn').click(function(){
                	var list =[];
                    $("#productList option:selected").each(function(){
                       list.push($(this).val());
                    });
//                    var json = $("form#queryForm").serializeJSON();
                	
                	var form=$("<form>");
                	form.attr("style","display:none");
                	form.attr("target","");
                	form.attr("method","post");
                	form.attr("action",ctx+"/productStats/productEmpStatsDownload?productIdList="+list.toString());
                	
//                	var paramsInput=$("<input>");
//                	paramsInput.attr("type","hidden");
//                	paramsInput.attr("name","params");
//                	paramsInput.attr("value", json);
                	
//                	form.append(paramsInput);
                	
                	$("body").append(form);
                	
                	form.submit();
//	                window.open(ctx+"/productStats/productEmpStatsDownload?productIdList="+list.toString());
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