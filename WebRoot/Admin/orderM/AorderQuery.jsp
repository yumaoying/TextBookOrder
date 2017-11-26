<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
 <c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
     </c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AorderQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript"  src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
	<script src="<c:url value='/leftnav.js'/>"></script>
<script type="text/javascript">
    function Check(itemid,bid,uid,buyamount,address){
       checkDom=document.getElementById("isregisbuy"+itemid);
       //登记购书被选中
       if(checkDom.checked){
          checkValue=bid+","+uid+","+buyamount+","+address;
          checkDom.value=checkValue;
          location.href="<c:url value='/admin/AdminGetbookServlet?method=add&orderItemid="+itemid+"&bid="+bid+"&uid="+uid+"&amount="+buyamount+"&location="+address+"'/>" ;
       }else{
         checkDom.value="nodate";
         
       }
    };
    
	function setMethod(method) {
		var ele = document.getElementById("method");
		ele.value = method;
	};
 </script>

 
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bord"   style="margin-left: 336px;margin-top: 75px;">
		<p>当前位置-->订单查询</p>
		<hr />
		<div class="searor" style="height:143px">
		<form action="<c:url value='/admin/AdminOrderServlet'/>"
				method="post">
				<input type="hidden" name="method" value="findOrder" >
			<p>
				请输入查询条件：<input type="text"  name="content"/> 
				 开始时间：
				<input type="text" class="Wdate" name="starttime" onFocus="WdatePicker({readOnly:true})" size="10" value="${start}" style="height: 25px;width: 130px;"> 
				结束时间：<input type="text" 	class="Wdate" name="endtime" onFocus="WdatePicker({readOnly:true})" size="10" value="${end}" style="height: 25px;width: 130px;"> 
					
			</p>
			<div class="group">
				   <input type="radio"name="lookup" value="0" class="find" style="width: 31px;height: 21px;">所有 
					<input type="radio"name="lookup" value="1" class="find"  style="width: 31px;height: 21px;">订单号 
					<input type="radio"name="lookup" value="2" class="find"  style="width: 31px;height: 21px;">购买人
					 <input type="radio"name="lookup" value="4" class="find"  style="width: 31px;height: 21px;">订单日期 
					<input type="radio"name="lookup" value="3" class="find"  style="width: 31px;height: 21px;">模糊查询 
		            	 <label>请选择订单状态：</label>
				 <select name="selval" style="width:120px;">
					<option value="0">&nbsp;</option>
					<option value="1"  <c:if test="${selelectvar==1 }">selected="selected"</c:if>>待初次审核</option>
					<option value="2" <c:if test="${selelectvar==2 }">selected="selected"</c:if>>待二次审核</option>
					<option value="3" <c:if test="${selelectvar==3 }">selected="selected"</c:if>>所有待审核订单（包括初审，二审）</option>
					<option value="4" <c:if test="${selelectvar==4 }">selected="selected"</c:if>>待付款</option>
					<option value="5" <c:if test="${selelectvar==5 }">selected="selected"</c:if>>待发货 </option>
					<option value="6" <c:if test="${selelectvar==6 }">selected="selected"</c:if>>待领书 </option>
					<option value="7" <c:if test="${selelectvar==7 }">selected="selected"</c:if>>交易成功</option>
				</select>
			</div>
			<p>
			<input type="submit" class="sub" value="查询" style="background-color: #2196F3;border-radius: 10px;">
			</p>
			        </form>
		</div>
		<hr />
		<div class="orRes" style="height: 443px;">
		<c:choose>
					<c:when test="${empty orderList or fn:length(orderList) eq 0 }">
		         当前没有记录
		         </c:when>
					<c:otherwise>
			<table  border="1">
			  <%--  <tr><th  colspan="13">所属订单编号:${orderItem.order.orderid	}&nbsp;&nbsp;&nbsp; 提交时间:<fmt:formatDate
											pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.order.ordertime }" />&nbsp;&nbsp;&nbsp;
										购买人：${orderItem.order.owner.username }<th></tr>
				<tr> --%>
				    <tr><!-- <td   colspan="13"> --><!-- <table border="1"><tr> -->
					<th  class="orO">订单编号</th>
					<th class="orO">订单详情</th>
					<th class="orO">购买人</th>
					<th class="orT">购买数量</th>
					<th class="orTh">小计</th>
					<th class="orF">当前库存量</th>
					<th class="orFi" >订单初审</th>
					<th class="orS"  >订单二次审核</th>
					<th class="orSe">订单状态</th>
					<th class="orE">登记缺书</th>
					<th class="orN">进入审核</th>
					<th class="orT">登记购书</th>
					<th colspan="3">操作</th>
				<!-- 	</tr> -->
				<!-- 	</table> -->
				</tr>
				<c:forEach items="${orderList }" var="orderItem" varStatus="i">
				<tr>
					<td class="orO">${orderItem.orderItemid }</td>
					<td class="orO"><a	href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${orderItem.orderItemid }'/>"  target="_blank">详情</a></td>
					<th class="orO">${orderItem.order.owner.username }</th>
					<td class="orT">${orderItem.buyamount}</td>
					<td class="orTh">￥<font color="red"><b>${orderItem.subtotal}</b></font></td>
					<td class="orF">${orderItem.stock.stocknumer}</td>
					<td class="orFi">
					          <form>
								<input type="radio" name="fckstate${orderItem.orderItemid }" value="0"<c:if test="${orderItem.fckstate eq 0 }">checked</c:if>>等待审核<br>
								<input type="radio" name="fckstate${orderItem.orderItemid }" value="1"	<c:if test="${orderItem.fckstate eq 1 }">checked</c:if>>未通过<br>
					    	  	<input type="radio" name="fckstate${orderItem.orderItemid }" value="2"<c:if test="${orderItem.fckstate eq 2 }">checked</c:if>>通过<br>							
					         </form>
					</td>
					<td class="orS">
					            <input type="radio" name="sckstate${orderItem.orderItemid }" value="0"<c:if test="${orderItem.sckstate eq 0 }">checked</c:if>>等待审核<br>
								<input type="radio" name="sckstate${orderItem.orderItemid }" value="1"	<c:if test="${orderItem.sckstate eq 1 }">checked</c:if>>未通过<br>
					    		<input type="radio" name="sckstate${orderItem.orderItemid }" value="2"<c:if test="${orderItem.sckstate eq 2 }">checked</c:if>>通过		<br>					
					<td class="orSe">
					<!-- <td> -->
					<form   name ="form3" action="<c:url value='/admin/AdminOrderServlet'/>"
															method="post">
															<input type="hidden" name="method" value="updateState">
															<input type="hidden" name="orderItemid"
																value="${orderItem.orderItemid }" />
															<table>
																<tr>
																	<td><input type="radio" name="state" value="0"
																		<c:if test="${orderItem.state eq 0}">checked</c:if>>未付款</td>
																	<td><input type="radio" name="state" value="1"
																		<c:if test="${orderItem.state eq 1}">checked</c:if>>已付款</td>
																	<td><input type="radio" name="state" value="2"
																		<c:if test="${orderItem.state eq 2}">checked</c:if> disabled="disabled">已发货</td>
																	<td><input type="radio" name="state" value="3"
																		<c:if test="${orderItem.state eq 3}">checked</c:if> disabled="disabled">已领书</td>
																	<td><c:if test="${orderItem.state eq 0 or  orderItem.state eq 1 }"><input type="submit" value="修改"></c:if></td>
																</tr>
															</table>
								</form>
					</td>
					<td class="orE"><c:if test="${orderItem.islack eq 0}">
															<a
																href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${orderItem.stock.sid}&itemid=${orderItem.orderItemid }&stocknumer=${orderItem.stock.stocknumer}&buyamount=${orderItem.buyamount}&&uid=${orderItem.order.owner.uid  }'/>">登记缺书</a>
														</c:if> <c:if test="${orderItem.islack eq 1}">已登记缺书</c:if></td>
					<td class="orN"><a href="<c:url value='/admin/AdminOrderServlet?method=entercheck&orderItemid=${orderItem.orderItemid }'/>">审核</a></td>
					<td class="orT">
					<c:choose>
															<c:when test="${orderItem.isregisbuy== 0 and orderItem.state==2 }">
															<form  name="rg" action="<c:url value='/admin/AdminGetbookServlet'/>"   method="post">
															<input type="hidden" name="method" value="add">
															<input type="hidden" name="orderItemid" value="${orderItem.orderItemid }">
															<input type="hidden" name="bid" value="${orderItem.stock.textbook.id }">
															<input type="hidden" name="uid" value="${orderItem.order.owner.uid }">
															<input type="hidden" name="amount" value="${orderItem.buyamount}">
															<input type="hidden" name="location" value="${orderItem.order.owner.school }">
																<input type="checkbox" name="isregisbuy"
																	class="isregisbuy" value=""
																	id="isregisbuy${orderItem.orderItemid }"
																	style="width:50px;height:28px;"
																	onclick="rg.submit();" >
		                                                          登记购书(生成领书单)
		                                                     </form>
		                                                     </c:when>
															<c:when test="${orderItem.isregisbuy== 1 }">
																<input type="checkbox" name="isregisbuy" checked="checked"
																	style="width:50px;height:28px;"  disabled="disabled"/>已登记购书
															</c:when>
															<c:otherwise>
															   教材未发货，无法登记购书
															</c:otherwise>
														</c:choose>
					</td>
					<td class="orEl">
					<a  href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${orderItem.orderItemid }'/>">详情</a></td>
					<td class="orTw">
					               <c:choose>
										 <c:when test="${orderItem.isdelete !=1}">
													 <a  href="<c:url value='/admin/AdminOrderServlet?method=delete&orderItemid=${orderItem.orderItemid }'/>" ><input type="button" >删除订单</a>
											   </c:when>
										   <c:otherwise>
												 <a href="<c:url value='/admin/AdminOrderServlet?method=delete&orderItemid=${orderItem.orderItemid }'/>"   >删除订单</a>
										    </c:otherwise>
							  </c:choose>
			     </td>
			     </tr>
				</c:forEach>
			</table>
	<!-- 	<hr> -->
					</c:otherwise>
				</c:choose>
		</div>
	</div>
</body>
</html>