var EmployeeAdd = function () {

	var handleValidation = function() {
            var formAdd = $('#addForm');
            $('#save').click(function(){
             	timeout(this, 1);
             	formAdd.submit();
             });
            jQuery.validator.addMethod("idcardLength", function(value, element) { 
    			var len = $.trim(value);
    			if (len == '') {
    				return true;
    			}
    			if (len.length != 15 && len.length != 18) {
    				return false;
    			}
    			return true;
    		}, "身份证只能是15位或18位长度！");
            jQuery.validator.addMethod("idcardRegex", function(value, element) { 
    			var len = $.trim(value);
    			if (len == '') {
    				return true;
    			}
    			var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3})|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx]))$/;
    			if (!reg.test(len)) {
    				return false;
    			}
    			return true;
    		}, "身份证格式不正确！");
            var validator = formAdd.validate({
            	rules: {
                    name: {
                        required: true,
                        rangelength:[2,20]
                    },
                    gender: {
                        required: true,
                        number:true
                    },
                    account: {
                        required: true,
                        remote: {
                            url: ctx + "/employee/check",
                            type: "post",
                            dataType: "json",
                            data: {type : 1}
                        }
                    },
                    employeeNo: {
                        required: true,
                        rangelength:[3,20],
                        remote: {
                            url: ctx + "/employee/check",
                            type: "post",
                            dataType: "json",
                            data: {type : 2}
                        }
                    },
                    mobile: {
                        required: true,
                        number: true,
                        rangelength:[11,11],
                        remote: {
                            url: ctx + "/employee/check",
                            type: "post",
                            dataType: "json",
                            data: {type : 3}
                        }
                    },
                    email: {
                        //required: true,
                        email: true,
                        rangelength:[4,120]
                    },
                    department: {
                        //required: true,
                        rangelength:[1,30]
                    },
                    position: {
                        //required: true,
                        rangelength:[1,30]
                    },
                    loginPwd: {
                        required: true,
                        rangelength:[6,20]
                    },
                    workPwd: {
                        required: true,
                        rangelength:[6,20]
                    },
//                    planner: {
//                        required: true
//                    },
//                    file: {
//                        required: true
//                    },
//                    admin: {
//                        required: true
//                    },
                    entryTime: {
                        //required: true,
                        date: true
                    },
                    birthday: {
                        //required: true,
                        date: true
                    },
                    address: {
                        //required: true,
                        rangelength:[4,120]
                    },
                    idcard: {
                    	idcardLength:true,
                    	idcardRegex:true,
                    	remote: {
                            url: ctx + "/employee/check",
                            type: "post",
                            dataType: "json",
                            data: {type : 4}
                        }
                    }
                },
                messages: {
                	account: {remote:"该账号已经存在！"},
                    loginPwd: {required:"登录密码不能为空！"},
                    workPwd: {required:"工作密码不能为空！"},
                    employeeNo: {remote:"该工号已经存在！"},
                    mobile: {rangelength:"手机号码格式不正确！",remote:"该手机号码已经存在！"},
                    idcard: {remote:"该身份证格式不正确，或已经存在！"}
                },
                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    // set success class to the control group
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                    icon.removeClass("fa-warning").addClass("fa-check");
                },

                submitHandler: function (form) {
                    var json = $('form#addForm').serializeObject();
                    
                    $.ajaxFileUpload({
                        url: ctx + '/employee/doAdd',
                        type: 'post',
                        secureuri: false,
                        fileElementId: 'file',
                        dataType: 'json',
                        data: json,
                        success: function(data, status){
                        	if (data.code == 1) {
                        		bootbox.dialog({
                                    message: data.message,
                                    title: "温馨提示：",
                                    buttons: {
                                      main: {
                                        label: "确定",
                                        className: "btn btn-primary",
                                        callback: function() {
                                        	window.location.reload();
                                        }
                                      }
                                    }
                                });
                        	} else {
                        		bootbox.alert(data.message);
                        	}
                        },
                        error: function(msg, status, e){
                        	bootbox.alert("新增员工出错。");
                        }
                    });
                }
            });
    }

	var deptTree = function() {
		$('#deptTree').jstree({
			'plugins' : {"wholerow":{}, "checkbox":{
				"three-state" : false
			}, "types":{}, "radio":{}},
			'core' : {
				"themes" : {
					"responsive" : false
				},
				'data' : {
					"url" : ctx + "/employeeGroup/tree",
					"data" : function(node) {

					}
				}
			}
		}).on("changed.jstree", function(e, data) {
			$('#deptId').val(data.node.id);
			$('#dept').val(data.node.text);
		});
	};
	
    return {
        init: function () {
        	deptTree();
            handleValidation();
        }
    };
}();