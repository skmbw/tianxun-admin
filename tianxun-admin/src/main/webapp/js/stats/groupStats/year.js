function init() {
	drawDayStats();
	handleResourceTree();
}
$('#queryBtn').click(function() {
	drawDayStats();
});
var handleResourceTree = function() {
	var empTree;

	$('#resourceTree').jstree({
		'plugins' : [ "wholerow", "checkbox", "types" ],
		'core' : {
			"themes" : {
				"responsive" : false
			},
			'data' : {
				"url" : ctx + "/groupStats/tree"
			}
		},
		"types" : {
			"default" : {
				"icon" : "fa fa-folder icon-warning icon-lg"
			},
			"file" : {
				"icon" : "fa fa-file icon-warning icon-lg"
			}
		}
	}).on("changed.jstree", function(e, data) {
		$('#groupId').val(data.selected);
		e.preventDefault();
	}).on("open_node.jstree", function(e, data) {
		var id = data.node.id;
		if (id == '-1') {
			var rootLoad = $('#rootLoad').val();
			if (rootLoad == "0") {
				$('#rootLoad').val("1");
				// 加载它的子节点，调用core.data加载
				data.instance.load_node(data.node);
			}
		}
	});
	;
}
function drawDayStats() {
	//var form = $("form#queryForm").serializeObject();
	/*var groupId = $("#groupId").val();
	var idList = "";
	for (i = 1; i < groupId.length; i++){
		idList = idList + groupId[i];
	}
	var js = form.replace('}', ',');
	   json = js + idList + "}";*/
	var json = $("form#queryForm").serializeJSON();
	$.ajax({
		type : "post",
		contentType : "application/json; charset=UTF-8",
		url : ctx + "/groupStats/queryYear",
		data : json,
		dataType : "json",
		success : function(msg) {
			if (msg.code == 1) {
				var myChart = echarts.init(document.getElementById('main'));
				myChart.setOption(msg.data[0]);

				var myChart2 = echarts.init(document.getElementById('main2'));
				myChart2.setOption(msg.data[1]);

				var myChart3 = echarts.init(document.getElementById('main3'));
				myChart3.setOption(msg.data[2]);

				myChart.connect([ myChart2, myChart3 ]);
				myChart2.connect([ myChart, myChart3 ]);
				myChart3.connect([ myChart, myChart2 ]);

				setTimeout(function() {
					window.onresize = function() {
						myChart.resize();
						myChart2.resize();
						myChart3.resize();
					}
				}, 200)

			} else {
				bootbox.alert(msg.message);
			}
		},
		error : function(msg) {
			bootbox.alert("oh no！");
		}
	});
}