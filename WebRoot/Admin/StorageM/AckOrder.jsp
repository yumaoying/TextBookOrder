<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>图书出库</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Ack.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />">
<script src="../../leftnav.js"></script>
   <script type="text/javascript">
   function changeNum(){
     
     if(!IsNum(num)){
       alert("请输入数字");
      this.focus();
     }
    };
    
    function IsNum(num){
     var reNum=/^\d*$/;
     return(reNum.test(num));
   };
   </script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bck"  style="margin-left: 336px;margin-top: 75px;">
		<p>当前位置-->待发货订单出库</p>
		<hr />
		<div class="ckIn">
			<div class="bookSto" style=" width: 1150px;">
	   <p>		<a href="<c:url value='/admin/AdminOutServlet?method=waitOut'/>" >
    <input type="text" name="tuoxiao" value="查找待发货订单" size="10" readonly="readonly" style="background-color: rgba(128, 128, 128, 0.52); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;"></a>
    </p>
    <hr>
			<c:choose>
		   <c:when test="${empty orderItemList or fn:length(orderItemList) eq 0 }" >
		       当前没待发货订单
		    </c:when>
       <c:otherwise>
			<table border="1" style="text-align:center">
				<tr>
					<th class="fbZ">所属总订单编号</th>
					<th class="fbZ">来自订单编号</th>
					<th class="fbO">库存编号</th>
					<th class="fbT">订单数量</th>
					<th class="fbTh">当前库存数量</th>
					<th class="fbF">订单提交时间</th>
					<th class="fbFi">购买者账号</th>
					<th class="fbS">图书ISBN：</th>
					<th class="fbSe">图书名称:</th>
					<th class="fbE">交易金额</th>
					<th colspan="2" >出库</th>
				</tr>
				 <c:forEach items="${orderItemList }" var="orderItem">
				<tr>
				   <td class="fbZ">${orderItem.order.orderid }</td>
					<td class="fbZ"><a	href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${orderItem.orderItemid }'/>">${orderItem.orderItemid }</a></td>
					<td class="fbO"><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${orderItem.stock.sid }'/>" >${orderItem.stock.sid }</a></td>
					<td class="fbT">${orderItem.buyamount }</td>
					<td class="fbTh">${orderItem.stock.stocknumer }</td>
					<td class="fbF"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderItem.order.ordertime }"/></td>
					<td class="fbFi">${orderItem.order.owner.userid }</td>
					<td class="fbS">${orderItem.stock.textbook.isbn }</td>
					<td class="fbSe">${orderItem.stock.textbook.bookname }</td>
					<td class="fbE">￥<font color="red"><b>${orderItem.subtotal}</b></font></td>
					<td class="fbN">
                              <a href="<c:url value='/admin/AdminOutServlet?method=addOut&sid=${orderItem.stock.sid }&uid=${orderItem.order.owner.uid }&outamount=${orderItem.buyamount }&itemid=${orderItem.orderItemid }'/>" >发货出库</a>		
                        </td>
				</tr>
				</c:forEach>
			</table>
			</c:otherwise>
          </c:choose>
		</div>
		</div>
	</div>
</body>
</html>