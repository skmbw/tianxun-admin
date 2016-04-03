var CheckinDetail = function () {
	var initial = function() {
		var markers = [];
		var map = new BMap.Map('map');
    	var point = new BMap.Point(120.156, 30.277);
    	map.centerAndZoom(point, 12);
    	map.enableScrollWheelZoom(true);
    	
    	map.addControl(new BMap.NavigationControl());
    	map.setCurrentCity("杭州");
    	
    	/**
    	 * 渲染地图
    	 */
    	renderMap = function(data) {
    		// 删除覆盖物，如果不删除，会一直保留，有问题
    		for (var i = 0; i < markers.length; i++) {
				map.removeOverlay(markers[i]);
			}
    		markers =[]; // 清空已有marker
    		for (var i = 0; i < data.length; i++) {(function(){
        		var check = data[i];
        		var pt = new BMap.Point(check.px, check.py);
        		// 如果经度和纬度相同的话，就只有一个marker，但是marker上的label可能不一样
        		var marker = new BMap.Marker(pt);// 创建标注
        		var label = new BMap.Label((i + 1),{offset:new BMap.Size(4,2)});// 显示是第几个标注，同一个点，后面会盖掉前面的
        		label.setStyle({color : "#fff", fontSize : "16px",
        			padding:"0px", border :"0px"});
        		
            	marker.setLabel(label);
            	
            	markers.push(marker);
            	map.addOverlay(marker);
        		var tips = "姓名：" + check.employeeName + "<br>";
        		tips += "时间：" + check.signTime + "<br>";
        		tips += "地点：" + check.location;
            	var infoWindow = new BMap.InfoWindow(
            			"<div style='font-size:12px;lineHeight:1.8em;'>" 
            			+ tips + "</div>", {enableMessage:false});
            	marker.addEventListener("click", function(e){
            		map.openInfoWindow(infoWindow, pt);
            	});
        	})();}
    	}
    	
    	// 加载地图要显示的数据
    	initData = function() {
    		var json = $("form#queryForm").serializeJSON();
            $.ajax({
            	"contentType":"application/json; charset=UTF-8",
                "dataType" : 'json',
                "type" : "POST",
                "url" : ctx + "/checkin/detailQuery",
                "data" : json,
                "success" : function(data){
                	var js = {list : data};
                	$("#updateBody").empty();
                	var tmpl = $("#updateTemplate").tmpl(js).appendTo("#updateBody");
                	
                	renderMap(data);
                }
            });
    	}();
        
    	
        /**
         * 当签到记录被点击是，显示marker
         */
        showMarker = function(index) {
        	for (var i = 0; i < markers.length; i++) {
        		markers[i].setAnimation(null);
        	}
        	var locs = index.split(',');
        	if (locs.length != 2) {
        		return;
        	}
        	for (var i = 0; i < markers.length; i++) {
        		var marker = markers[i];
        		var p = marker.point; // 经纬度
        		if (locs[0] == p.lng && locs[1] == p.lat) {
        			marker.setAnimation(BMAP_ANIMATION_BOUNCE);
        			break;
            	}
        	}
        };
        
        // 点击，列表中的每一行
        $('#updateBody').on("click", "tr", (function (e) {
            e.preventDefault();
            // id是经度和纬度，中间用逗号分隔
            showMarker(this.id);
        }));
        
        // 查询，重新加载数据
    	$('#queryBtn').click(function(){
    		var startDate = $('#startDate').val();
    		var signDate = $('#signDate').val();
    		var reg = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
    		if ((startDate != '' && !reg.test(startDate)) || (signDate != '' && !reg.test(signDate))) {
    			bootbox.alert('日期格式不正确！');
    			return;
    		}
    		
    		initData();
    		
//    		var json = $("form#queryForm").serializeJSON();
//    		$.ajax({
//            	"contentType":"application/json; charset=UTF-8",
//                "dataType" : 'json',
//                "type" : "POST",
//                "url" : ctx + "/checkin/detailQuery",
//                "data" : json,
//                "success" : function(data){
//                	var js = {list : data};
//                	$("#updateBody").empty();
//                	var tmpl = $("#updateTemplate").tmpl(js).appendTo("#updateBody");
//                	
//                	// 渲染地图
//                	renderMap(data);
//                }
//            });
    	});
	};
	
    return {
        init: function () {
        	initial();
        }
    };
}();