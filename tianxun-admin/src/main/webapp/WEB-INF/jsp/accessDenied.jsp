<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/taglib.inc" %>
<%@ include file="/WEB-INF/inc/script.inc" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${base}">
    <title>天寻 | 访问拒绝</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h2>Access Denied</h2>
        <h3 class="font-bold">您没有访问系统的权限！</h3>

        <div class="error-desc">
            对不起，您没有访问系统的权限，请联系您的系统管理员，维护相应的访问权限。
<!--             You can go back to main page: <br/><a href="index.html" class="btn btn-primary m-t">Dashboard</a> -->
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
