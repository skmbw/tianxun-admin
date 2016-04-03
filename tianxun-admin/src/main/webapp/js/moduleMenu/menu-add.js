var MenuAdd = function () {

	var handleValidation = function() {
            var formAdd = $('#addForm');

            var validator = formAdd.validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: false,
                ignore: "",
                rules: {
                	moduleName: {
                        minlength: 2,
                        required: true
                    },
                    orders: {
                        required: true,
                        number: true
                    },
                    enabled: {
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
                    var json = $('form#addForm').serializeJSON();
                    
                    $.ajax({
                    	type:"post",
                        contentType:"application/json; charset=UTF-8",
                        url: ctx + "/moduleMenu/doAdd",
                        data:json,
                        dataType:"json",
                        success: function(msg){
                        	if (msg.code == 1) {
                        		bootbox.dialog({
                                    message: "新增菜单成功！",
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
                        		bootbox.alert("新增菜单失败，请检查您的输入是否有误！");
                        	}
                        },
                        error: function (msg) {
                            bootbox.alert(msg.message);
                        }
                    });
                }
            });
    }

    return {
        init: function () {
            handleValidation();
        }
    };
}();