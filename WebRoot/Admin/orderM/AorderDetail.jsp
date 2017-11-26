<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AorderDetail.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
 <style>
 .borDet{
 margin-left: 336px;
    margin-top: 75px;
    padding: 0px;
    width: auto;
    height: 1080px;
    top: 0px;
 }
    .tile {
    width: auto;
    height: 40px;
    line-height: 40px;
    font-size: 22px;
    color: red;
    margin-top: 90px;
    margin-bottom: 0px;
    vertical-align: middle;
    }
    .orInfo{
    width: 100%;
    height: 49%;
    margin-left: 7%;
    margin-right: 2%;
    overflow-y: scroll;
    margin-top: 0px;
    position: relative;
    }
 </style>
	<script src="<c:url value='/leftnav.js'/>"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="borDet" >
		<p class="tile">当前位置-->订单详细信息</p>
		<hr />
		<div class="orInfo">
		  <p>
				订单编号：&nbsp;&nbsp;&nbsp;<input type="text" name="orderItemid"  value="${orderItem.orderItemid }" class="find" style="width:300px;" />
			</p>
			<p>
				订单号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="itemid"  value="${orderItem.itemid }" class="find" style="width:300px;" />
			</p>
			<p>
				总订单号：&nbsp;&nbsp;&nbsp;<input type="text" name="itemid"  value="${orderItem.order.orderid	}" class="find" style="width:300px;" />
			</p>
			<p>
				购买账户：&nbsp;&nbsp;&nbsp;<input type="text" name="userid"
					class="find"  value="${orderItem.order.owner.userid }"/>
			</p>
			<p>
				购买姓名：&nbsp;&nbsp;&nbsp;<input type="text" name="userid"
					class="find"  value="${orderItem.order.owner.username }"/>
			</p>
			<p>
			图书ISBN：&nbsp;&nbsp;&nbsp;${orderItem.stock.textbook.isbn}
			<a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${orderItem.stock.sid }'/>" target="_blank">&nbsp;&nbsp;点击查看库存详情</a>
			</p>
			<p>
			图书名称:：&nbsp;&nbsp;&nbsp;${orderItem.stock.textbook.bookname}
			</p>
			<p>
			图书售价:：&nbsp;&nbsp;&nbsp;${orderItem.stock.textbook.saleprice}
			</p>
			<p>
				订单日期：&nbsp;&nbsp;<input type="text" name="ordertime" class="find" value="${orderItem.order.ordertime }">
			</p>
			<p>
			订购数量：&nbsp;&nbsp;${orderItem.buyamount}
			</p>
			<p>
			当前库存：&nbsp;&nbsp;${orderItem.stock.stocknumer}
			</p>
				<p>
			是否登记缺书：
			  <c:if test="${orderItem.islack eq 0}">
				<a	href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${orderItem.stock.sid}&itemid=${orderItem.orderItemid }&stocknumer=${orderItem.stock.stocknumer}&buyamount=${orderItem.buyamount}'/>">登记缺书</a>
		</c:if>
					  <c:if test="${orderItem.islack eq 1}">已登记缺书</c:if>
			</p>
				<p>
			交易金额：&nbsp;&nbsp;￥<font color="red"><b>${orderItem.subtotal}</b>元
	</font>
			</p>
			<form action="<c:url value='/admin/AdminOrderServlet'/>"
															method="post">
															<input type="hidden" name="method" value="updateFckstate">
															<input type="hidden" name="orderItemid" value="${orderItem.orderItemid }" />
			<p>
				初审状态：&nbsp;&nbsp;
					
																<input type="radio" name="fckstate" value="0"	<c:if test="${orderItem.fckstate eq 0 }"  >checked</c:if>  disabled>等待审核
																<input type="radio" name="fckstate" value="1"
																		<c:if test="${orderItem.fckstate eq 1 }">checked</c:if>>未通过
															    <input type="radio" name="fckstate" value="2"
																		<c:if test="${orderItem.fckstate eq 2 }">checked</c:if>>通过
															  <input type="submit" value="修改">
				<%-- 	<input type="radio" name="fckstate" value="0" <c:if test="${orderItem.fckstate eq 0 }">checked</c:if> readonly="readonly">等待审核
	               <input type="radio" name="fckstate" value="1"<c:if test="${orderItem.fckstate eq 1 }">checked</c:if> readonly="readonly">未通过
			    	<input type="radio" name="fckstate" value="2"  <c:if test="${orderItem.fckstate eq 2 }">checked</c:if>  readonly="readonly">通过 --%>
				</p>	
				</form>
				<p>
				 初审人员：&nbsp;&nbsp;${orderItem.fckadmin.name }
				</p>			
				<p>
				初审日期：&nbsp;&nbsp;${orderItem.fckdate }
				</p>		
						<form   name ="form3" action="<c:url value='/admin/AdminOrderServlet'/>"
															method="post">
															<input type="hidden" name="method" value="updateState">
															<input type="hidden" name="orderItemid"value="${orderItem.orderItemid }" />
			<p>
				订单状态：&nbsp;&nbsp;
													<input type="radio" name="state" value="0"<c:if test="${orderItem.state eq 0}">checked</c:if>>未付款
													<input type="radio" name="state" value="1"	<c:if test="${orderItem.state eq 1}">checked</c:if>>已付款
													<input type="radio" name="state" value="2"	<c:if test="${orderItem.state eq 2}" >checked</c:if> disabled="disabled">已发货
													<input type="radio" name="state" value="3"	<c:if test="${orderItem.state eq 3}">checked</c:if> disabled="disabled">已领书
													<c:if test="${orderItem.state eq 0 or  orderItem.state eq 1 }"><input type="submit" value="修改"></c:if>		
			</p>
			</form>
		
			<p>
				是否登记购书：<c:if test="${orderItem.isregisbuy eq 0}">
															<a 	href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${orderItem.stock.sid}&itemid=${orderItem.orderItemid }&stocknumer=${orderItem.stock.stocknumer}&buyamount=${orderItem.buyamount}'/>">登记缺书</a>
												 </c:if> 
												 <c:if test="${orderItem.isregisbuy eq 1}">已登记购书</c:if>
			</p>
				<form  name ="form2" action="<c:url value='/admin/AdminOrderServlet'/>" method="post">
															<input type="hidden" name="method" value="updateSckstate">
															<input type="hidden" name="orderItemid"value="${orderItem.orderItemid }" />
			<p>
				是否二次审核：	
					<input type="radio" name="sckstate" value="0"	<c:if test="${orderItem.sckstate eq 0 }">checked</c:if>>等待审核
					<input type="radio" name="sckstate" value="1"<c:if test="${orderItem.sckstate eq 1 }">checked</c:if>>未通过
					<input type="radio" name="sckstate" value="2"	<c:if test="${orderItem.sckstate eq 2 }">checked</c:if>>通过
		    		<input type="submit" value="修改">											
			</p>
					</form>
				<p>
				 二审人员：&nbsp;&nbsp;${orderItem.sckadmin.name }
				</p>			
				<p>
				二审日期：&nbsp;&nbsp;${orderItem.sckdate }
				</p>		
			<%-- <p>
				是否领书&nbsp;&nbsp;：	
			<input type="radio" name="state2" value="2"	<c:if test="${orderItem.state eq 2 or  orderItem.state eq 1 or orderItem.state  eq 0}">checked</c:if> readonly="readonly">未领书
		   <input type="radio" name="state2" value="3"<c:if test="${orderItem.state eq 3}">checked</c:if> readonly="readonly">已领书
			</p>
			<p>
				是否出库&nbsp;&nbsp;：<input type="radio" name="state3" value="2"	<c:if test="${orderItem.state eq 2}">checked</c:if> readonly="readonly">已发货
				<input type="radio" name="state3" value="2"	<c:if test="${orderItem.state eq 0 or orderItem.state eq 1}">checked</c:if> readonly="readonly">未发货
			</p> --%>
		</div>
	</div>
</body>
</html>