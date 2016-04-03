var RolesAdd = function () {

	var handleValidation = function() {
            var formAdd = $('#addForm');

            var validator = formAdd.validate({
                rules: {
                	roleName: {
                        minlength: 2,
                        rangelength:[2,20],
                        required: true
                    },
//                    roleDesc: {
//                        minlength: 2,
//                        rangelength:[2,20],
//                        required: true
//                    },
//                    priority: {
//                        required: true,
//                        number: true
//                    },
                    enabled: {
                    	required: true
                    },
                    authIds: {
                    	required: true
                    }
                },

                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                    icon.removeClass("fa-warning").addClass("fa-check");
                },

                submitHandler: function (form) {
                    var json = $('form#addForm').serializeJSON();
                    
                    $.ajax({
                    	type:"post",
                        contentType:"application/json; charset=UTF-8",
                        url: ctx + "/roles/doAdd",
                        data:json,
                        dataType:"json",
                        success: function(msg){
                        	if (msg.code == 1) {
                        		bootbox.dialog({
                                    message: "新增角色成功！",
                                    title: "温馨提示",
                                    buttons: {
                                      success: {
                                        label: "确定",
                                        className: "btn btn-primary",
                                        callback: function() {
                                        	window.location.reload();
                                        }
                                      }
                                    }
                                });
                        	} else {
                        		bootbox.alert("新增角色失败，请检查您的输入是否有误！");
                        	}
                        },
                        error: function (msg) {
                            bootbox.alert('新增角色失败，请联系管理员！');
                        }
                    });
                }
            });
    }

	var handleAuthTree = function() {
		$('#authTree').jstree({
			'plugins' : [ "wholerow", "checkbox", "types" ],
			'core' : {
				"themes" : {
					"responsive" : false
				},
				'data' : {
					"url" : ctx + "/authorities/tree",
					"data" : function(node) {

					}
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
			if (data.action == 'select_node' || data.action == 'deselect_node') {
        		var json = data.instance.get_json();
            	var array = json[0].children;
            	var selected = '';
            	for (var i = 0; i < array.length; i++) {
            		var leafArray = array[i].children;
            		for (var j = 0; j < leafArray.length; j++) {
            			var leaf = leafArray[j];
            			if (leaf.state.selected) {
            				selected = selected + leaf.id + ',';
            			}
            		}
            	}
            	$('#authIds').val(selected);
        	}
		});
	};
	
    return {
        init: function () {
            handleValidation();
            handleAuthTree();
        }
    };
}();