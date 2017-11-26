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
	<title>订单跟踪</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"
		content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/purchaseTracking.css">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="purTrack">
			<form>
				<h2>订单快速查询</h2>
				<p>请输入订单号：</p>
				<p>
					<input type="text" name="searchD" class="pt_dd">
				</p>
				<input type="submit" value="查询" class="pt_s"></input>
			</form>
		</div>

		<div class="main">
			<div class="status">
				<ul>
					<li><div>提交订单</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li><div>审核</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li><div>进行付款</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li><div>二次审核</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li><div>领书状态</div></li>
				</ul>
			</div>
			<div class="tishi">Hello</div>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
