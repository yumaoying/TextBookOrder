<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>我的电子发票</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/myEInvoice.css">
	<script language=javascript>
    function preview() { 
        bdhtml=window.document.body.innerHTML; 
         sprnstr="<!--startprint-->"; 
       eprnstr="<!--endprint-->"; 
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
        window.document.body.innerHTML=prnhtml; 
        window.print(); 
      //  window.document.body.innerHTML=bdhtml; 
      location.reload(); 
         }
</script>
</head>
<body>
	<%@ include file="head.txt"%>
	<div class="bb">
	<c:choose>
	   <c:when test="${empty orders }">
	       您好!该订单已被删除或未提交该订单，无法出具发票
	   </c:when>
	   <c:otherwise>
	        <h3 class="dzfp_ts">你好，电子发票已出具</h3>
		<input type="button" value="打印" class="print_dzfp" onclick="preview()">
		<hr style="border: 2px dashed red; opacity: 0.4;" />
		<div class="mye">
		<!--startprint-->
			<table class="dzfp_no"  border="1">
				<tr>
					<th colspan="5">教材订购系统发票管理</th>
				</tr>
				<tr>
					<td class="shtrZ">书籍ISBN</td>
					<td class="shtrO">书名</td>
					<td class="shtrT">作者</td>
					<td class="shtrTh">购买单价x购买数量</td>
					<td class="shtrF">小计</td>
				</tr>
				<c:forEach items="${orders.orderItemList }" var="orderItem">
				<c:if test="${orderItem.isregisbuy==1 }">
				<tr>
					<td class="shtrZ">${orderItem.stock.textbook.isbn }</td>
					<td class="shtrO">${orderItem.stock.textbook.bookname }</td>
					<td class="shtrT">${orderItem.stock.textbook.author }</td>
					<td class="shtrTh"><font color="red"><b>${orderItem.stock.textbook.saleprice }</b></font>X ${orderItem.buyamount}</td>
					<td class="shtrF">￥<font color="red"><b>${orderItem.subtotal}</b></font>元</td>
				</tr>
				</c:if>
				</c:forEach>
				<tr>
					<td class="dzfp_bz" colspan="5">
                 电子发票信息:订单号${orders.orderid }&nbsp;&nbsp; 
                 订单提交时间:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orders.ordertime }"/>&nbsp;&nbsp;&nbsp; 
                 购买人：${orders.owner.username }&nbsp;&nbsp;
                交易金额：￥<font color="red"><b>${orders.total }元</b></font>&nbsp;&nbsp;
                      </td>
				</tr>
				<tr>
					<td class="dzfp_cpdw" colspan="5">电子发票出具发票单位:xxx教材订购系统</td>
				</tr>
				<tr>
					<td class="dzfp_shr" colspan="5">电子发票审核人:${orderItem.sckadmin.name }</td>
				</tr>
				<tr>
					<td class="dzfp_shrq" colspan="5">电子发票出具日期:
					<fmt:formatDate value="${now }"  pattern="yyyy年MM月dd日"/>
					</td>
				</tr>
			</table>
		<!--endprint-->
		</div>
	   </c:otherwise>
	</c:choose>
		
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
