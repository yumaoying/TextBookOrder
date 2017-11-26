<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>提交成功页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="viewport"
	content="width=device-width,user-scalable=yes">
<link rel="stylesheet" type="text/css" href="css/head.css">
<link rel="stylesheet" type="text/css" href="css/orderSubSucc.css">

</head>
<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<p class="dd_ts">
			你好，订单已经提交成功，正在审核中，请静候<br />如果你需要取消订单，请联系管理人员。请你牢记您的订单号，这是我们的唯一凭证，请在订单跟踪页面查看您所有订单状态
		</p>
		<hr style="border: 2px dashed red; opacity: 0.4;" />
		<p class="dd_no">总订单编号：${orders.orderid}</p>
		<div class="order">
			<table class="dd_result">
			   <tr>
			       <th class="ddZ" colspan="7">
					   总订单编号:${orders.orderid}&nbsp;&nbsp;&nbsp;
					    提交时间:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orders.ordertime }"/>&nbsp;&nbsp;&nbsp;
					   交易金额：<font color="red"><b>${orders.total }元</b></font>&nbsp;&nbsp;
					   购买人：${orders.owner.username }
					</th>
				</tr>
				<tr>
					<th class="ddZ">订单编号</th>
					 <th class="ddT">图书图片</th>
					<th class="ddO">教材名称</th>
					<th class="ddO">作者</th>
					<th class="ddF">单价:</th>
					<th class="ddTh">购买数量</th>
					<th class="ddTh">小计</th>
				</tr>
				<c:forEach items="${orders.orderItemList }" var="orderItem">
				<tr>
					<td class="ddZ">${orderItem.itemid }</td>
					<td class="ddT"><img src="<c:url value='/${orderItem.stock.textbook.bookpicture }'/>" alt="暂无图书图片"/></td>
					<td class="ddO">${orderItem.stock.textbook.bookname }</td>
					<td class="ddO">${orderItem.stock.textbook.author }</td>
		            <td class="ddF">${orderItem.stock.textbook.saleprice }</td>
					<td class="ddTh">${orderItem.buyamount}</td>
					<td class="ddTh"><font color="red"><b>￥${orderItem.subtotal}元</b></font></td>
				</tr>
				</c:forEach>
			</table>
			     <p class="dd_no"  height="40px"  >本次交易金额：<font color="red"><b>${orders.total }元</b></font>&nbsp;&nbsp;<a href="<c:url value='/payMoney.jsp'/>">现在付款</a></p>
		</div>
	</div>
</body>
</html>
