<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="left_nav">
			<ul>
				<li><a href="#">最多购买</a></li>
				<li><a href="#">猜你喜欢</a></li>
			</ul>
		</div>
		<div class="main">
			<p>主要书籍介绍</p>
			<p>主要书籍介绍</p>
			<p>主要书籍介绍</p>
			<p>主要书籍介绍</p>
		</div>
	</div>

	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
