<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <c:if test="${ empty sessionScope.login_user }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
				<li><a href="<c:url value='/OrderServlet?method=findByUid'/>">已提交</a></li>
				<li><a href="<c:url value='/OrderServlet?method=allCkstateByuid'/>">正在审核</a></li>
				<li><a href="<c:url value='/OrderServlet?method=orderState&state=0'/>">待付款</a></li>
				<li><a href="<c:url value='/OrderServlet?method=orderState&state=1'/>">待发货</a></li>
		<%-- 		<li><a href="<c:url value='/OrderServlet?method=orderState&state=2'/>">待领书</a></li> --%>
				<li><a href="<c:url value='/GetBookServlet?method=findByuid'/>" >我的领书单</a></li>
			</ul>
		</div>
		<div class="main">
		  <c:choose>
		         <c:when test="${ (empty orderList or fn:length(orderList) eq 0)  }" >
		         当前没有记录，到教材库逛逛吧
		         </c:when>
		         <c:otherwise>
			<table border="1">
				<tr>
					<th class="alO_Z">订单号</th>
					<th class="alO_O">提交日期</th>
					<th class="alO_T">提交人</th>
					<th class="alO_Th">订单详情</th>
					<th class="alO_F">电子发票</th>
				</tr>
				<c:forEach items="${orderList }" var="order">
				<tr>
					<td class="alO_Z"><a href="<c:url value='/OrderServlet?method=loadOrderByOid&orderid=${order.orderid }'/>" >${order.orderid }</a></td>
					<td class="alO_O"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime  }"/></td>
					<td class="alO_T">${order.owner.username }</td>
					<td class="alO_Th"><a href="<c:url value='/OrderServlet?method=loadOrderByOid&orderid=${order.orderid }'/>">查看</a></td>
					<td class="alO_F"><a href="<c:url value='/OrderServlet?method=loadOpenBill&orderid=${order.orderid }'/>" >查看</a></td>
				</tr>
				</c:forEach>
			</table>
			  </c:otherwise>
		     </c:choose>
		     <c:if test="${!empty getbooklist or fn:length(getbooklist) !=0 }" >
		              <table border="1">
	    <tr>
	     <th>编号</th>
		 <th>图书名称</th>
	     <th>图书isbn</th>
	     <th>图书作者</th>
	     <th>出版社</th>
	     <th>出版时间</th>
	     <th>领取数量</th>
	    </tr>
	    <c:forEach items="${getbooklist }" var="getbook" varStatus="i">
	    <tr>
	     <th>${i.index }</th>
	     <th>${getbook.textbook.bookname }</th>
	     <th>${getbook.textbook.isbn }</th>
	     <th>${getbook.textbook.author }</th>
	     <th>${getbook.textbook.publiser }</th>
	     <th>${getbook.textbook.publishtime }</th>
	     <th>${getbook.amount }</th>
	    </tr>
	    <c:if test="${i.last }">
	    <tr>
	    <%--  <th colspan="7">用户账户：${getbook.user.userid }&nbsp;&nbsp;领取时间<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getbook.date }"/>,领取地点${getbook.location }</th> --%>
	      <th colspan="7">
              用户账户：${getbook.user.userid }&nbsp;&nbsp;&nbsp;
              领取时间：${getbook.date }后均可领取&nbsp;&nbsp;&nbsp;
              领取地点：${getbook.location }&nbsp;&nbsp;&nbsp;
	           <a href="<c:url value='/GetBookServlet?method=sureGetbook&uid=${getbook.user.uid }'/>"  onclick="return confirm('此操作不可恢复，是否确认领书')" >
	           <input type="text" name="sureGetbook" value="用户确认领书" size="10" readonly="readonly" style=" background-color: rgba(128, 128, 128, 0.29); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;"  >
	           </a>
	      </th>
	    </tr>
	    </c:if>
	    </c:forEach>
	  </table>
		      
	            </c:if>
		   
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
