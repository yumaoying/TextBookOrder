<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单详情</title>
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
			你好，订单已经提交成功，若还有订单正在审核中，请静候<br />如果你需要取消订单，请联系管理人员。请你牢记您的订单号，这是我们的唯一凭证，请在订单跟踪页面查看您的订单状态
		</p>
		<hr style="border: 2px dashed red; opacity: 0.4;" />
		<c:if  test="${!empty orders  }">
		<p class="dd_no">所属订单编号：${orders.orderid}&nbsp;&nbsp;&nbsp;
		提交时间:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orders.ordertime }"/>&nbsp;&nbsp;&nbsp;
		 购买人：${orders.owner.username }&nbsp;&nbsp;
		 交易金额：<font color="red"><b>${orders.total }元</b></font>&nbsp;&nbsp;<a href="<c:url value='/payMoney.jsp'/>">若未付款，现在付款</a>
		</p>
		<div class="order">
			<table class="dd_result">
				<tr>
					<th class="ddZ">订单编号</th>
					 <th class="ddT">图书图片</th>
					<th class="ddO">教材名称</th>
					<th class="ddF">购买数量&nbsp;x&nbsp;单价</th>
					<th class="ddO">订单审核状态</th>
					<th class="ddO">订单状态</th>
					<th class="ddTh">小计</th>
					<th class="ddTh">操作</th>
				</tr>
				<c:forEach items="${orders.orderItemList }" var="orderItem">
				<tr>
					<td class="ddZ">${orderItem.itemid }</td>
					<td class="ddT"> <a href="<c:url value='/TextBookServlet?method=load&sid=${orderItem.stock.sid }'/>"><img src="<c:url value='/${orderItem.stock.textbook.bookpicture }' />" alt="暂无图书图片" style="width:100px;height:120px;border:2px;"/></a></td>
					<td class="ddO">${orderItem.stock.textbook.bookname }</td>
		            <td class="ddF"><font color="red"><b>${orderItem.stock.textbook.saleprice }</b></font>&nbsp;&nbsp;x&nbsp;&nbsp; <font color="red"><b>${orderItem.buyamount}</b></font></td>
		            <td class="ddF">
		              <c:if test="${orderItem.fckstate==0  }">教材信息在审核中</c:if>
		              <c:if test="${orderItem.fckstate==1  }">教材信息审核未通过</c:if>
		              <c:if test="${orderItem.fckstate==2  }">教材信息审核已通过</c:if>
		            </td>
		            <td class="ddF">
		              <c:if test="${orderItem.state==0 }">未付款<a href="<c:url value='/payMoney.jsp'/>">&nbsp;现在付款&nbsp;&nbsp;</a></c:if>
		              <c:if test="${orderItem.state==1 }">待发货</c:if>
		              <c:if test="${orderItem.state==3 }">待领书</c:if>
		              <c:if test="${orderItem.state==4 }">交易成功</c:if>
		            </td>
					<td class="ddTh">￥<font color="red"><b>&nbsp;${orderItem.subtotal}&nbsp;</b></font>元</td>
				    <td>
				     <c:choose>
				      <c:when test="${orderItem.fckstate==0 or  orderItem.fckstate==1 or orderItem.sckstate==0 or orderItem.sckstate==1 or orderItem.state==0 or orderItem.state==4 }">
				      <a href="<c:url value='/OrderServlet?method=delete&orderItemid=${orderItem.orderItemid }'/>" ><span>删除定单</span></a>
				      </c:when>
				      <c:otherwise>订单已付款成功，若要取消请联系<a href="#">管理员</a></c:otherwise>
				      </c:choose>
				    </td>
				</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
</body>
</html>
