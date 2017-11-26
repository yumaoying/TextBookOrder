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
	<title>忘记密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/findPassw.css">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="findPI">
			<h2>找回密码</h2>
			<form>
				<p>
					新密码&nbsp;&nbsp;:<input type="text" name="newpwd" />
				</p>
				<p>
					确认密码:<input type="text" name="surepwd" />
				</p>
				<p>
					验证码&nbsp;&nbsp;:<input type="text" name="vercode" class="vercode" />
					<img src="#" alt="验证码图片" />
				</p>
				<p>
					<input type="submit" value="提交" class="reg_s" />
				</p>
			</form>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
