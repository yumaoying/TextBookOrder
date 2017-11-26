<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>订单跟踪</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"
		content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/purchaseTracking.css">	

<script>
   function show(){
      document.getElementById("no_content").style.display="block";
   };
   function  hid(){
       document.getElementById("no_content").style.display="none";
   };
   
</script>
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="purTrack">
				<h2>订单快速查询</h2>
				<p>请输入订单号：</p>
					<form action="<c:url value='/OrderServlet' />" method="post">
		          <input type="hidden" name="method" value="loadOrder">
				<p>
					<input type="text" name="itemid" class="pt_dd">
				</p>
				<input type="submit" value="查询" class="pt_s"></input>
			</form>
		</div>

		<div class="main">
			<div class="status">
				<ul>
					<li  id="tijiao" onmousemove="show();" onmouseout="hid();"><div>提交订单</div></li>
					<li class="iarr"  ><img src="img/dd_arr.png" /></li>
					<li id="fcheck" onmousemove="show();" onmouseout="hid();"><div>审核</div></li>
					<li class="iarr"><img src="img/dd_arr.png" /></li>
					<li  id="pay" onmousemove="show();" onmouseout="hid();"><div>进行付款</div></li>
					<li class="iarr" onmousemove="show();" onmouseout="hid();"><img src="img/dd_arr.png" /></li>
					<li  id="sckeck" onmousemove="show();" onmouseout="hid();"><div>二次审核</div></li>
					<li class="iarr" onmousemove="show();" onmouseout="hid();"><img src="img/dd_arr.png" /></li>
					<li id="gettbook" onmousemove="show();" onmouseout="hid();"><div>领书状态</div></li>
				</ul>
			</div>
			<div class="tishi" id="no_content">
			      <input  type="hidden" name="ordertime"  value="${orderItem.order.ordertime }" id="order_date">
			     <input  type="hidden" name="state"  value="${orderItem.state }" id="order_state">
			      <input  type="hidden" name="fckstate"  value="${orderItem.fckstate }" id="order_fckstate">
			      <input  type="hidden" name="sckstate"  value="${orderItem.fckstate }" id="order_sckstate">
			      <input type="hidden" name="fckdate" value="${orderItem.fckstate }" id="order_fdate">
			      <input type="hidden" name="scksdate" value="${orderItem.fckstate }" id="order_sdate">
			      <input type="hidden" name="paydate" value="${orderItem.paydate }" id="paydate">
			      <input type="hidden" name="outdate" value="${orderItem.outdate }" id="outdate">
			 
			   <c:choose >
			        <c:when test="${ empty orderItem }" >
			           <div     style="display:none;">
			               当前没有查询到你要追踪的订单信息
			            </div>
			       </c:when>
			       <c:otherwise>
			    <div  >
			        ${orderItem.itemid }订单已提交，提交时间提交时间:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.order.ordertime }"/>&nbsp;&nbsp;&nbsp;
			        <div> 购买人：${orderItem.order.owner.username }&nbsp;&nbsp; 购买数籍：${orderItem.stock.textbook.bookname }&nbsp;&nbsp;   交易金额：<font color="red"><b>${orderItem.subtotal }元</b></font>&nbsp;&nbsp;</div>
			    </div>
			    <div>
			                      <c:if test="${orderItem.fckstate ==0 }">
			                                   <div>工作人员在审核中，请静候....</div>
			                          </c:if>
			                           <c:if    test="${orderItem.fckstate ==1 }">
			                                  <div>由于部分原因，教材信息审核未通过，工作人员正在为您处理</div>
			                          </c:if>
			                           <c:if  test="${orderItem.fckstate ==2}">
			                                  <div>教材信息审核已通过</div>
			                          </c:if>
			    </div>
			    <div>
			            <c:if test="${orderItem.state ==0 }">
			                                  此订单还未付款，若教材审核已通过，请尽快进行付款
			              </c:if>
			               <c:if test="${orderItem.state ==1 }">
			                                  <div>订单已付款成功，付款日期<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.paydate }"/>，等待发货中</div>
			                 </c:if>
			    </div>
			    <div>
			           <c:if test="${orderItem.sckstate ==0 }">
			                                   <div>工作人员在审核中，请静候....</div>
			            </c:if>
			             <c:if    test="${orderItem.sckstate ==1 }">
			                                 <div>由于部分原因，审核未通过，工作人员正在为您处理</div>
			              </c:if>
			               <c:if  test="${orderItem.sckstate ==2}">
			                                  <div>审核已通过</div>
			                </c:if>
			     </div>
			    <div>
			                 <c:if test="${orderItem.state==0 or  orderItem.state==1 }">
			                                  <div>工作人员正在为您处理订单，等待发货中</div>
			               </c:if>
			                <c:if test="${orderItem.state ==2 }">   
			                                  <div>  工作人员已经为你发货<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.outdate  }"/></div>
			                 </c:if>      
			                  <c:if  test="${orderItem.isregisbuy ==1}">
			                                  <div>
			                                  工作人员已登记购书，你可以到领书单页面查看待领教材信息，你可以查看领书单信息，到${orderItem.order.owner.school   }教材库进行领书
			                                  </div>
			                     </c:if>
			                 <c:if test="${orderItem.state ==3 }">   
			                                  <div>  你已成功领取教材！感谢您的使用！</div>
			                 </c:if>    
			     </div>
			               </c:otherwise>
                </c:choose>
			 <%--    <table class="dd_result" style="display:none;"    id="orderinfo">
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
			</table> --%>
			</div>
			</div>
			</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
