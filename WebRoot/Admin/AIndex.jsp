<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>后台首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AIndex.css'/>">
	<link rel="stylesheet" type="text/css"	href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>

<body>
	<%@ include file="Aleft_nav.txt"%>
	<div class="bb">
		<div class="bmain">
			<p>消息中心</p>
			<hr />
			<div class="notRead">
				<table>
					<tr>
						<th colspan="4">未读消息</th>
						<th><a href="<c:url value='/Admin/tongzhiM/notRead.jsp'/>">更多</a></th>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
				</table>
			</div>
			<hr />
			<div class="haveRead">
				<table>
					<tr>
						<th colspan="4">已读消息</th>
						<th><a href="<c:url value='/Admin/tongzhiM/haveRead.jsp'/>">更多</a></th>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
				</table>
			</div>

			<hr/>

			<div class="dealed">
				<table>
					<tr>
						<th colspan="4">已处理消息</th>
						<th><a href="<c:url value='/Admin/tongzhiM/haveDealted.jsp'/>">更多</a></th>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
					<tr>
						<td class="RZ">用户XXXX提交订书单，其中包含脱销教材....</td>
						<td class="RO">2017/1/10</td>
						<td class="RT"><a href="#">查看</a></td>
						<td class="RTh"><a href="#">标为已读</a></td>
						<td class="RF"><a href="#">标为已处理</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
