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
	
	<title>所有订单</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/allOrder.css">


</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="left_nav">
			<p>所有订单</p>
			<ul>
				<li><a href="#">已提交</a></li>
				<li><a href="#">正在审核</a></li>
				<li><a href="#">等待付款</a></li>
				<li><a href="#">领书单</a></li>
			</ul>
		</div>
		<div class="main">
			<table>
				<tr>
					<th class="alO_Z">订单号</th>
					<th class="alO_O">提交日期</th>
					<th class="alO_T">提交人</th>
					<th class="alO_Th">订单详情</th>
					<th class="alO_F">电子发票</th>
					<th class="alO_Fi">是否付款</th>
				</tr>
				<tr>
					<td class="alO_Z"><a href="purchaseTracking.jsp">123456</a></td>
					<td class="alO_O">2016-02-12</td>
					<td class="alO_T">zzh</td>
					<td class="alO_Th"><a href="orderDetail.jsp">查看</a></td>
					<td class="alO_F"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_Fi"><a href="payMoney.jsp">等待付款</a></td>
				</tr>
				<tr>
					<td class="alO_Z"><a href="purchaseTracking.jsp">123456</a></td>
					<td class="alO_O">2016-02-12</td>
					<td class="alO_T">zzh</td>
					<td class="alO_Th"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_F"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_Fi"><a href="payMoney.jsp">等待付款</a></td>
				</tr>
				<tr>
					<td class="alO_Z"><a href="purchaseTracking.jsp">123456</a></td>
					<td class="alO_O">2016-02-12</td>
					<td class="alO_T">zzh</td>
					<td class="alO_Th"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_F"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_Fi"><a href="payMoney.jsp">等待付款</a></td>
				</tr>
				<tr>
					<td class="alO_Z"><a href="purchaseTracking.jsp">123456</a></td>
					<td class="alO_O">2016-02-12</td>
					<td class="alO_T">zzh</td>
					<td class="alO_Th"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_F"><a href="myEInvoice.jsp">查看</a></td>
					<td class="alO_Fi"><a href="payMoney.jsp">等待付款</a></td>
				</tr>
			</table>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
