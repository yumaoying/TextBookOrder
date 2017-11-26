<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>修改缺书单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AqsdModify.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script type="text/javascript">
	function setMethod(method) {
		var ele = document.getElementById("method");
		ele.value = method;
	}
</script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="qsd">
		<div class="qsdM">
			<p>当前位置-->提交缺书单</p>
			<hr/>
			<div class="qsdInfo">
			  <form action="<c:url value='/admin/AdminLackServlet'/>" method="post">
             <input type="hidden" name="method" value="add" />
				缺书来源:<br/>
				<c:if test="${lackbook.itemid!=0}">
				&nbsp;&nbsp;来自订单号:<a	href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${lackbook.itemid }'/>"><input type="text" name="itemid" value="${lackbook.itemid }" ></a><br/>
			  </c:if>
				&nbsp;&nbsp;库存编号:&nbsp;&nbsp;<a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${lackbook.stockid }'/>" >
				  <input type="text" name="stockid" value="${lackbook.stockid }" readonly="readonly"></a><br/>
				缺书数量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="amount" value="${lackbook.amount }"><br/>
			<input type="submit" value="登记缺书" />
          </form>
			</div>
		</div>
	</div>
</body>
</html>