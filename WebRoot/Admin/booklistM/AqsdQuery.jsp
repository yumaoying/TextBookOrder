<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
	<title>缺书单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AqsdQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="qsd">
		<div class="qsdM">
			<p class="tsxx">当前位置-->缺书单列表   <a href="<c:url value='/Admin/booklistM/AqAdd.jsp'/>">添加缺书单</a></p>
			<hr/>
			<div class="qsdTJ">
				<form>
				<h3>缺书单查询
				</h3>
				<p>
					 <a href="<c:url value='/admin/AdminLackServlet?method=findAll' />"  ><input type="text" name="tuoxiao" value="所有缺书单" size="10" readonly="readonly" style=" background-color: #2196F3; border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s" ></a>
				</p>
				</form>
			</div>
			<hr/>
			<div class="qsdInfo">
			<c:if test="${ empty lacklist or fn:length(lacklist)<=0}">
                当前系统中没有缺书单数据
              </c:if>
              <c:if test="${fn:length(lacklist)>0}">
				<table border="1">
				    <tr>
				    	<th class="qsdZ">编号</th>
				    	<th class="qsdO">来自库存编号</th>
				    	<th class="qsdT">来自订单号</th>
				    	<th class="qsdTh">登记缺书数量</th>
				    	<th class="qsdF">登记日期</th>
				    	<th colspan="2">操作</th>
				    </tr>
				    <c:forEach items="${lacklist }" var="lack">
				    <tr>
				    	<td class="qsdZ">${lack.lackid }</td>
				    	<td class="qsdO"><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${lack.stockid }'/>" >${lack.stockid }</a></td>
				    	<td class="qsdT"><a	href="<c:url value='/admin/AdminOrderServlet?method=findOrderItemByItemid&orderItemid=${lack.itemid }'/>">${lack.itemid }</a>
				    	<td class="qsdTh">${lack.amount }</td>
				    	<td class="qsdF"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lack.lackdate }"/></td>
				    	<td class="qsdSev"><a href="<c:url value='/admin/AdminLackServlet?method=findBysid&stockid=${lack.stockid }'/>" >修改</a></td>
				    	<td class="qsdE"><a href="<c:url value='/admin/AdminLackServlet?method=delete&stockid=${lack.stockid }&itemid=${lack.itemid }'/>">删除</a></td>
				    </tr>
       </c:forEach>
				</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>