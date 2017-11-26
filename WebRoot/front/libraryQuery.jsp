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
	
	<title>教材库查询</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/libraryQuery.css">


</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="lq_tiaojian">
			<form>
				<h3>请输入查询条件：</h3>
				<p>
					教材名：<input type="text" name="jcname" /> 作&nbsp;者：<input
						type="text" name="author" />
				</p>
				<p>
					ISBN&nbsp;：<input type="text" name="isbn" /> <input type="submit"
						value="查询" class="lq_s" />
				</p>
			</form>
		</div>
		<div class="lq_main">
			<table>
				<tr>
					<th>图书图片</th>
					<th>图书名称</th>
					<th>售价</th>
					<th>加入购物车</th>
				</tr>
				<tr>
					<td class="lq_leftZ"><a href="<c:url value='/bookDetail.jsp'/>"><img src="#"
							alt="书籍图片暂无"></a></td>
					<td class="lq_leftO">数据库原理教程第5版</td>
					<td class="lq_leftT">33元</td>
					<td class="lq_leftTh">
						<a href="#"><img src="img/shopping.png"	class="addToShop"/></a>
					</td>
				</tr>
				<tr>
					<td class="lq_leftZ"><a href="<c:url value='/bookDetail.jsp'/>"><img src="#"
							alt="书籍图片暂无"></a></td>
					<td class="lq_leftO">数据库原理教程第5版</td>
					<td class="lq_leftT">33元</td>
					<td class="lq_leftTh">
						<a href="#"><img src="img/shopping.png"	class="addToShop"/></a>
					</td>
				</tr>
				<tr>
					<td class="lq_leftZ"><a href="<c:url value='/bookDetail.jsp'/>"><img src="#"
							alt="书籍图片暂无"></a></td>
					<td class="lq_leftO">数据库原理教程第5版</td>
					<td class="lq_leftT">33元</td>
					<td class="lq_leftTh">
						<a href="#"><img src="img/shopping.png"	class="addToShop"/></a>
					</td>
				</tr>
				<tr>
					<td class="lq_leftZ"><a href="<c:url value='/bookDetail.jsp'/>"><img src="#"
							alt="书籍图片暂无"></a></td>
					<td class="lq_leftO">数据库原理教程第5版</td>
					<td class="lq_leftT">33元</td>
					<td class="lq_leftTh">
						<a href="#"><img src="img/shopping.png"	class="addToShop"/></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>

</body>
</html>
