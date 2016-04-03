var AuthAdd = function () {

	var handleValidation = function() {
            var formAdd = $('#addForm');

            var validator = formAdd.validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: false,
                ignore: "",
                rules: {
                	authName: {
                        minlength: 2,
                        required: true
                    },
//                    authDesc: {
//                        minlength: 2,
//                        required: true
//                    },
                    moduleId: {
                        required: true
                    },
                    orders: {
                        required: true,
                        number: true
                    },
                    enabled: {
                    	required: true
                    },
                    urls:{
                    	required: true
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    var icon = $(element).parent('.input-icon').children('i');
                    icon.removeClass('fa-check').addClass("fa-warning");  
                    icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group   
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    
                },

                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                    icon.removeClass("fa-warning").addClass("fa-check");
                },

                submitHandler: function (form) {
                	var showMenu = $('#showMenu').val();
                    var action = $('#action').val();
                    if (showMenu == 'true' && action == '') {
                    	bootbox.alert(" 显式权限，默认动作不能为空。");
                    	return;
                    }
                    var json = $('form#addForm').serializeJSON();
                    
                    $.ajax({
                    	type:"post",
                        contentType:"application/json; charset=UTF-8",
                        url: ctx + "/authorities/doAdd",
                        data:json,
                        dataType:"json",
                        success: function(msg){
                        	if (msg.code == 1) {
                        		bootbox.dialog({
                                    message: "新增权限成功！",
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
                        		bootbox.alert("新增权限失败，请检查您的输入是否有误！");
                        	}
                        },
                        error: function (msg) {
                            bootbox.alert('新增权限失败，请联系管理员！');
                        }
                    });
                }
            });
    }

	var handleResourceTree = function() {
		$('#resourceTree').jstree({
			'plugins' : [ "wholerow", "checkbox", "types" ],
			'core' : {
				"themes" : {
					"responsive" : false
				},
				'data' : {
					"url" : ctx + "/resources/tree?resourceId=null",
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
			$('#urls').val(data.selected);
		});
	};
	
    return {
        init: function () {
            handleValidation();
            handleResourceTree();
        }
    };
}();