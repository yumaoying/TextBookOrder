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
	
	<title>书籍详情</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/bookDetail.css">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<img src="#" alt="书籍图片暂时缺失" />
		<div class="main">
			<table>
				<tr>
					<td class="bd_leftZ">书名：</td>
					<td class="bd_leftO">明日</td>
				</tr>
				<tr>
					<td class="bd_leftZ">作者：</td>
					<td class="bd_leftO">Hello菜菜</td>
				</tr>
				<tr>
					<td class="bd_leftZ">ISBN：</td>
					<td class="bd_leftO">98738273</td>
				</tr>
				<tr>
					<td class="bd_leftZ">出版社：</td>
					<td class="bd_leftO">明日出版社</td>
				</tr>
				<tr class="bkOther">
					<td class="bd_leftZ">详细信息：</td>
					<td class="bd_leftO">这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这是一本很有意思的数这思的数这是一本很有意思的数v这是一本很有意思的数</td>
				</tr>
				<tr>
					<td class="bd_leftZ">库存数量：</td>
					<td class="bd_leftO">333</td>
				</tr>
				<tr class="bd_tj">
					<td colspan="2"><input type="button" value="加入购物车"
						class="addToSh" /></td>
				</tr>
			</table>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>

</body>
</html>
