<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/taglib.inc" %>
<%@ include file="/WEB-INF/inc/script.inc" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${base}">
    <title>天寻 | 登录</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body class="gray-bg" style="background-image: url('img/bg.jpg');">
        <div class="middle-box text-center animated fadeInDown" style="max-width:540px;">
            <div>
                <h1 class="logo-name"><img src="img/logo.png" /></h1>
            </div>
            <h3>&nbsp;</h3>
            <div class="wrapper wrapper-content animated fadeInRight ibox float-e-margins">
                <div class="row" style="margin-left:auto;margin-right:auto;">
                    <div class="col-lg-12 ">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title" style="filter:alpha(Opacity=40);-moz-opacity:0.4;opacity: 0.4;background-color:#373737; position:static;border-style:none;">                             
                                                          </div>
                            <h2 style="text-align:left;margin-left:35px;font-weight:bolder; color:white;margin-top:-36px;position:relative;">登录<small>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white;">欢迎回来</span></small></h2>

                          
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" action="${ctx}/login" method="post">
                                    <input type="hidden" id="msg" value="<c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION ne null}">机构代码，账号或密码错误，请检查。</c:if>">
                                    <input type="hidden" id="authCodeError" value="${authCodeError}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">机构代码</label>
                                        <div class="col-sm-8">
                                            <input type="text" id="orgCode" name="orgCode" placeholder="请输入机构代码" class="form-control" required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">账号</label>

                                        <div class="col-sm-8">
                                            <input type="text" id="account" name="username" placeholder="请输入登录账号" class="form-control" required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">密码</label>

                                        <div class="col-sm-8"><input type="password" name="password" placeholder="请输入登录密码" class="form-control" required=""></div>
                                    </div>
                                    <div class="form-group" >
                                        <label class="col-sm-2 control-label">验证码</label>
                                        <div class="col-sm-6 col-xs-6"><input type="text" class="form-control" name="authCode" placeholder="验证码" required=""></div>
                                        <div class="col-sm-1 col-xs-1" style="margin-left:-12px;"><img alt="验证码" style="margin-top:5px;" src="common/random" onclick="javascript:authCode2(this);"></div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="col-md-12 col-lg-12"><button type="submit" class="btn btn-primary block m-b" style="width:65%;margin-left:auto;margin-right:auto;">登 录</button></div>
                                    </div>

                                </form>
                                <p class="m-t"> <small>浙江中金通信息技术有限公司 版权所有 &copy; 2015</small> </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.cokie.min.js"></script>
    <script src="js/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var account = $.cookie('SPR_SEC_CACHE__account');
		$('#account').val(account);
		var orgCode = $.cookie('SPR_SEC_CACHE__orgCode');
		$('#orgCode').val(orgCode);
		var msg = $('#msg').val();//或者用返回参数也可以
		if (msg != undefined && $.trim(msg) != '') {
			if ($('#authCodeError').val() == 'true') {
				bootbox.alert('验证码填写错误！');
			} else {
				bootbox.alert(msg);
			}
			$.ajax({//通知服务端，清除标志
            	type:"post",
                url: ctx + "/employee/clean",
                dataType:"json",
                success: function(msg){
                },
                error: function (msg) {
                }
            });
		}
	});
	function authCode2(obj) {
	    var timenow = new Date().getTime();
	    obj.src = "common/random?d=" + timenow;
	}
	</script>
</body>

</html>
