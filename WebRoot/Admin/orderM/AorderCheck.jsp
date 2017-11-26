<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
<title>订单审核</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AorderCheck.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script src="<c:url value='/leftnav.js'/>"></script>
<style>
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
  .sh  {
    width: auto;
    height: 916px;
    margin: 0px;
    padding: 0px;
    }
   .shRes {
    width: auto;
    line-height: 28px;
    margin-left: 1%;
    overflow: hidden;
    }
    .shRes  p{
    height: 45px;
    width: 1200px;
    margin: 0px;
    margin-top: 5px;
    margin-bottom: 0px;
    line-height: 40px;
    font-size: 22px;
    color: red;
    }
    .find{
    height: 25px;
    width: 250px;
    font-size: 16px;
    }
    .sub{
    margin-left: 130px;
    width: 120px;
    height: 30px;
    font-size: 18px;
    }
    .ck{
    width: 96%;
    height: 680px;
    margin-left: 1%;
    overflow-y: scroll;
    overflow-x: scroll;
    }
     </style>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sh"  style="margin-left: 336px;margin-top: 75px;">
		<p class="tile">当前位置-->订单审核</p>
		<hr />
		<div class="shRes"  style="width: 1025px;" >
			
			<form action="<c:url value='/admin/AdminOrderServlet'/>"
				method="post">
				<p>
					<input type="hidden" name="method" value="findCheck">
				请选择查询条件
				<select name="selval" class="find">
					<!-- <option value="0"></option> -->
					<option value="1"
						<c:if test="${selelectvar==1 }">selected="selected"</c:if>>待初次审核</option>
					<option value="2"
						<c:if test="${selelectvar==2 }">selected="selected"</c:if>>待二次审核</option>
					<option value="3"
						<c:if test="${selelectvar==3 }">selected="selected"</c:if>>所有待审核订单（包括初审，二审）</option>
				<%-- 	<option value="4"
						<c:if test="${selelectvar==4 }">selected="selected"</c:if>>待付款</option>
				   	<option value="5" <c:if test="${selelectvar==5 }">selected="selected"</c:if>>已付款 </option> --%>
				</select>  <input type="submit"
					value="&nbsp;&nbsp;查询&nbsp;&nbsp;" class="sub">
					</p>
			</form>
			<div>
	         <hr>
	         <div class="ck">
			<table border="1">
				<tr>
					<th class="shZ">订单编号</th>
					<th class="shZ">订单详情</th>
				<!-- 	<th  class="shO">购买人账号</th> -->
					<th  class="shO">购买人</th>
					<th class="shO">图书名称</th>
					<th class="shT">图书isbn</th>
					<th class="shTh">购买数量</th>
			    	<th class="shTh">交易金额</th>
					<th class="shTh">库存量</th>
					<th class="shF">初审状态</th>
					<th class="shFi">审核日期</th>
					<th class="shS">审核时间</th>
					<th class="shF">二审状态</th>
					<th class="shFi">审核时间</th>
					<th class="shS">审核人员</th>
				</tr>
			<c:forEach items="${orderList }" var="orderItem" varStatus="i">
				<tr>
					<td class="shZ">${orderItem.orderItemid }</td>
					<td class="shZ"><a	href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${orderItem.orderItemid }'/>">详情</a></td>
					<%-- <td class="shO">${orderItem.order.owner.userid }</td> --%>
					<td class="shO">${orderItem.order.owner.username }</td>
					<td class="shO">${orderItem.stock.textbook.bookname }</td>
					<td class="shT">${orderItem.stock.textbook.isbn }</td>
					<td class="shTh">${orderItem.buyamount}</td>
					<td class="shTh">￥<font color="red"><b>${orderItem.subtotal}</b></font></td>
					<td class="shTh">${orderItem.stock.stocknumer }</td>
				<%-- 	<td class="shTh">￥<font color="red"><b>${orderItem.subtotal}</b></font></td>
				   <td class="shTh">${orderItem.stock.stocknumer }</td> --%>
					<td class="shF">
					     	<form action="<c:url value='/admin/AdminOrderServlet'/>"
														method="post">
														<input type="hidden" name="method" value="updateFckstate">
														<input type="hidden" name="orderItemid"
															value="${orderItem.orderItemid }" />
														<table>
															<tr>
																<td><input type="radio" name="fckstate" value="0"
																	<c:if test="${orderItem.fckstate eq 0 }">checked</c:if> readonly="readonly">等待审核</td>
																<td><input type="radio" name="fckstate" value="1"
																	<c:if test="${orderItem.fckstate eq 1 }">checked</c:if>>未通过</td>
																<td><input type="radio" name="fckstate" value="2"
																	<c:if test="${orderItem.fckstate eq 2 }">checked</c:if>>通过</td>
																<td><input type="submit" value="修改">
																</td>
															</tr>
											       </table>
			            </form>
		             </td>
					<th class="shFi">${orderItem.fckdate }</th>
					<th class="shS">${orderItem.fckadmin.adminid }</th>
					<td class="shF">
					     	<form action="<c:url value='/admin/AdminOrderServlet'/>"
														method="post">
														<input type="hidden" name="method" value="updateSckstate">
														<input type="hidden" name="orderItemid"
															value="${orderItem.orderItemid }" />
														<table>
															<tr>
																<td><input type="radio" name="sckstate" value="0"
																	<c:if test="${orderItem.sckstate eq 0 }">checked</c:if>  readonly="readonly">等待审核</td>
																<td><input type="radio" name="sckstate" value="1"
																	<c:if test="${orderItem.sckstate eq 1 }">checked</c:if>>未通过</td>
																<td><input type="radio" name="sckstate" value="2"
																	<c:if test="${orderItem.sckstate eq 2 }">checked</c:if>>通过</td>
																<td><input type="submit" value="修改">
																</td>
															</tr>
														</table>
			            </form>
		             </td>
					<th class="shFi">${orderItem.sckdate }</th>
					<th class="shS">${orderItem.sckadmin.adminid }</th>
				</tr>
				</c:forEach>
			</table>
			</div>
				</div>
		</div>
	</div>
</body>
</html>