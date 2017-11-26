<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>欢迎页面</title>
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/welcome.css'/>">
</head>

<body>
	<div class="wel" id="wel_1">
		<img src="<c:url value='/img/welBack.jpg'/>" class="w_img1">
		<div class="info1">
			<h1>欢迎使用我们的系统</h1>
			<p>我们的系统采用.......</p>
			<div class="btgroup">
				<ul>
					<li><a href="<c:url value='/TextBookServlet?method=findAllStock' />">首页</a></li>
					<li><a href="<c:url value='/TextBookServlet?method=findAllStock' />">教材库查询</a></li>
					<li><a href="login.jsp">登陆</a></li>
					<li><a href="login.jsp">其它</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="wel">
		<div class="img2">
			<img src="#">
		</div>
	</div>
	<div class="wel">
		<div class="img3">
			<img src="#">
		</div>
	</div>
	<div class="wel">
		<div class="img4">
			<img src="#">
		</div>
	</div>
</body>
</html>
