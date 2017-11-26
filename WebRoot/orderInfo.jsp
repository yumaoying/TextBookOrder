<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" type="text/css" href="css/purchaseTracking.css">	
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="purTrack">
		  <form action="<c:url value='/OrderServlet'/>" method="post">
		      <input type="hidden" name="method" value=" loadOrder">
				<h2>订单快速查询</h2>
				<p>请输入订单号：</p>
				<p>
					<input type="text" name="orderItemid" class="pt_dd">
				</p>
				<input type="submit" value="查询" class="pt_s"></input>
			</form>
		</div>

		<div class="main">
			<div class="status">
				<!-- <ul>
					<li  id="tijiao" onmousemove="show();" onmouseout="hid();"><div>提交订单</div></li>
					<li class="iarr"  ><img src="img/dd_arr.png" /></li>
					<li id="fcheck"><div>审核</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li  id="pay"><div>进行付款</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li  id="sckeck"><div>二次审核</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li id="gettbook"><div>领书状态</div></li>
				</ul> -->
			</div>
			<div class="tishi">
			  <center>
			          <c:choose>
		         <c:when test="${empty orderItem  }" >
		         当前没有已提交的订单，到教材库逛逛吧
		         </c:when>
		     <c:otherwise>
			<table border="1">
			    <tr>
					<th class="ddZ" colspan="8">
					    订单编号:${orderItem.order.orderid }&nbsp;&nbsp;&nbsp;
					    提交时间:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.order.ordertime }"/>&nbsp;&nbsp;&nbsp;
					  购买人：${orderItem.order.owner.username }
					</th>
				</tr>
				<tr>
					<th class="ddZ">订单编号</th>
					 <th class="ddT">图书图片</th>
					<th class="ddO">教材名称</th>
					<th class="ddF">购买数量X单价</th>
					<th class="ddO">教材审核状态</th>
					<th class="ddO">订单审核状态</th>
					<th class="ddO">订单状态</th>
					<th class="ddTh">小计</th>
					<th class="ddTh">操作</th>
				</tr>
				<tr>
					<td class="ddZ">${orderItem.orderItemid }</td>
					<td class="ddT"> <a href="<c:url value='/TextBookServlet?method=load&sid=${sl.sid }'/>"></a><img src="<c:url value='/${orderItem.stock.textbook.bookpicture }'/>" alt="暂无图书图片"/></td>
					<td class="ddO">${orderItem.stock.textbook.bookname }</td>
		            <td class="ddF"><font color="red"><b>${orderItem.stock.textbook.saleprice }</b></font>X ${orderItem.buyamount}</td>
		            <td class="ddF">
		              <c:if test="${orderItem.fckstate==0 or  orderItem.sckstate==0 }">教材信息在审核中</c:if>
		              <c:if test="${orderItem.fckstate==1  or  orderItem.sckstate==1 }">教材信息审核未通过</c:if>
		              <c:if test="${orderItem.fckstate==2  and orderItem.sckstate==2  }">教材信息审核已通过</c:if>
		            </td>
		          <td class="ddF">
		              <c:if test="${orderItem.sckstate==0 }">订单在审核中</c:if>
		              <c:if test="${orderItem.sckstate==1 }">订单审核未通过</c:if>
		              <c:if test="${orderItem.sckstate==2   }">订单审核已通过</c:if>
		            </td>
		            <td class="ddF">
		              <c:if test="${orderItem.state==0 }">未付款<a href="<c:url value='/payMoney.jsp'/>">现在付款</a></c:if>
		              <c:if test="${orderItem.state==1 }">待发货</c:if>
		              <c:if test="${orderItem.state==3 }">待领书</c:if>
		              <c:if test="${orderItem.state==4 }">交易成功</c:if>
		            </td>
					<td class="ddTh">￥<font color="red"><b>${orderItem.subtotal}</b></font>元</td>
				    <td>
				     <c:choose>
				      <c:when test="${orderItem.fckstate==0 or  orderItem.fckstate==1 or orderItem.sckstate==0 or orderItem.sckstate==1 or orderItem.state==0 or orderItem.state==4 }">
				      <a href="<c:url value='/OrderServlet?method=delete&orderItemid=${orderItem.orderItemid }'/>" ><span>删除订单</span></a>
				      </c:when>
				      <c:otherwise>订单已付款成功，若要取消请联系<a href="#">管理员</a></c:otherwise>
				      </c:choose>
				    </td>
				</tr>
			      </table>
		           	<br/>
			    </c:otherwise>
		          </c:choose>
			    </center>
			</div>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
