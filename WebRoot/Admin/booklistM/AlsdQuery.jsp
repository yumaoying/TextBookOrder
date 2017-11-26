<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
<c:if test="${!empty requestScope.msg && requestScope.msg!=''}">
	<script type="text/javascript">
		alert("${requestScope.msg}");
	</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>领书单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AlsdQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="lsd">
		<div class="lsdM">
			<p class="tsxx">当前位置-->领书单查询</p>
			<hr/>
			<div class="lsdTJ">
			<form action="<c:url value='/admin/AdminGetbookServlet' />"  method="post">
			<input type="hidden" name="method" value="findGroup"  />
				<h3>请输入查询条件：<input type="submit"
						value="查询" class="lq_s" />
				</h3>
				<p>
					查询条件:<input type="text"  name="lookup"  />
				</p>
				</form>
			</div>
			<hr/>
			<div class="lsdInfo">
			<c:choose>
	  <c:when test="${empty getbooklist or fn:length(getbooklist) eq 0 }" >
		       当前没有记录
	  </c:when>
	<c:otherwise>
				<table border="1">
				    <tr>
				    	<th class="lsdZ">编号</th>
				    	<th class="lsdZ">来自订单号</th>
				    	<th class="lsdO">用户编号</th>
				    	<th class="lsdT">用户账号</th>
				    	<th class="lsdTh">用户姓名</th>
				    	<th class="lsdF">图书名称</th>
				    	<th class="lsdF">图书isbn</th>
				    	<th class="lsdF">图书作者</th>
				    	<th class="lsdFi">出版社</th>
				    	<th class="lsdS">出版时间</th>
				    	<th class="lsdS">领取数量</th>
				    	<th class="lsdS">登记时间</th>
				    	<th class="lsdS">领取地点</th>
				    	<th class="lsdS">确认领书</th>
				    	<th colspan="2">操作</th>
				    	<th colspan="2">操作2</th>
				    </tr>
	                     <c:forEach items="${getbooklist }" var="getbook" varStatus="i">
				    <tr>
				    	<td class="lsdZ">${i.index }</td>
				    	<td class="lsdZ">${getbook.oitemid }</td>
				    	<td class="lsdO">${getbook.user.uid }</td>
				    	<td class="lsdT">${getbook.user.userid }</td>
				    	<td class="lsdTh">${getbook.user.username }</td>
				    	<td class="lsdF">${getbook.textbook.bookname }</td>
				    	<td class="lsdF"><a href="<c:url value='/admin/AdminTextBookServlet?method=load&id=${getbook.textbook.id }'/>">${getbook.textbook.isbn }</a></td>
				    	<td class="lsdF">${getbook.textbook.author }</td>
				    	<td class="lsdF">${getbook.textbook.publiser }</td>
				    	<td class="lsdFi">${getbook.textbook.publishtime }</td>
				    	<td class="lsdS">${getbook.amount }</td>
				    	<td class="lsdS">${getbook.date}</td>
				    	<td class="lsdS">${getbook.location }</td>
				    	<th><a href="<c:url value='/admin/AdminGetbookServlet?method=sureGetbooByoid&oid=${getbook.oitemid }&gid=${getbook.gid } '/>"  onclick="return confirm('确认收货?此操作不可恢复!')">确认收货</a></th>
				      <th><a href="<c:url value='/admin/AdminGetbookServlet?method=updateBygidPre&gid=${getbook.gid } '/>" target="_blank">修改</a></th>
				      <th><a href="<c:url value='/admin/AdminGetbookServlet?method=deleteBygid&gid=${getbook.gid } '/>" onclick="return confirm('该操作会删除该领书单，但不会修改订单的登记购书状态，是否删除?')">删除</a></th>
				    	<th><a href="<c:url value='/admin/AdminGetbookServlet?method=deleteoitemid&oitemid=${getbook.oitemid } '/>"  onclick="return confirm('您真要要取消吗，该操作会将用户订单的登记购书状态修改为未登记状态,同时删除该领书单信息')">取消登记购书</a></th>
	                     <th><a href="<c:url value='/admin/AdminGetbookServlet?method=finfByuid&userid=${getbook.user.userid } '/>" target="_blank">查看该用户的领书单记录</a></th>
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