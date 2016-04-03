<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/taglib.inc" %>
<%@ include file="/WEB-INF/inc/script.inc" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${base}">
    <title>天寻 | 登录超时</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="lock-word animated fadeInDown">
    <span class="first-word">登录</span><span>超时</span>
</div>
    <div class="middle-box text-center lockscreen animated fadeInDown">
        <div>
            <div class="m-b-md">
            <img alt="image" class="img-circle circle-border" src="img/profile_small.jpg">
            </div>
            <h3>${account}</h3>
            <p>您长时间没有操作，已经帮您锁屏！</p>
            <form class="m-t" role="form" action="${ctx}/login">
                <input type="hidden" id="username" name="username" value="${account}">
                <input type="hidden" id="orgCode" name="orgCode" value="${orgCode}">
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width">登录</button>
            </form>
            <div>
            	<a href="${ctx}/login">不是 ${account} ?</a>
            </div>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
