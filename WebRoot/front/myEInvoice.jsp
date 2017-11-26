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
	<title>我的电子发票</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/myEInvoice.css">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<h3 class="dzfp_ts">你好，电子发票已出具</h3>
		<input type="button" value="打印" class="print_dzfp">
		<hr style="border: 2px dashed red; opacity: 0.4;" />
		<div class="mye">
			<table class="dzfp_no">
				<tr>
					<th colspan="5">教材订购系统发票管理</th>
				</tr>
				<tr>
					<td class="shtrZ">书籍ISBN</td>
					<td class="shtrO">书名</td>
					<td class="shtrT">作者</td>
					<td class="shtrTh">书籍数量</td>
					<td class="shtrF">购买人</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="shtrZ">123456789</td>
					<td class="shtrO">数据库原理</td>
					<td class="shtrT">王曼</td>
					<td class="shtrTh">100</td>
					<td class="shtrF">cat</td>
				</tr>
				<tr>
					<td class="dzfp_bz" colspan="5">电子发票备注信息:订单号(123456)</td>
				</tr>
				<tr>
					<td class="dzfp_cpdw" colspan="5">电子发票出发票单位</td>
				</tr>
				<tr>
					<td class="dzfp_shr" colspan="5">电子发票审核人</td>
				</tr>
				<tr>
					<td class="dzfp_shrq" colspan="5">电子发票审核日期</td>
				</tr>
			</table>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
