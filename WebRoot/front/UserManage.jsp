<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	
	<title>个人信息修改</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='../css/head.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='../css/UserManage.css' />">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="usrM">
		<h3>个人信息管理<font>请填写所有信息</font></h3>
		<div>
			<form action="<c:url value='/UserServlet' />" method="post">
           <input type="hidden" name="method"  value="modify"/>
				
				<p>
					用户账户&nbsp;：<input type="text" name="userid" value="${login_user.userid }"  disabled="disabled"/>
				</p>
				<p>
					用户名&nbsp;&nbsp;&nbsp;：<input type="text" name="username" value="${login_user.username}" />
                  <span style="color: red; font-weight: 900">${errors.username }</span>
				</p>
				<p>
					E-mail&nbsp;&nbsp;&nbsp;：<input type="text" name="email"
						class="email" />
				<span style="color: red; font-weight: 900">${errors.email }</span>
				</p>
				<p>
					联系方式&nbsp;：<input type="text" name="telphone" />
				</p>
				<p>
					所在学校&nbsp;：<input type="text" name="school" />
				  <span style="color: red; font-weight: 900">${errors.school }</span>
				</p>
				<p>
					学&nbsp;&nbsp;&nbsp;&nbsp;院&nbsp;：<input type="text" name="academy" />
				</p>
				<p>
					专&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：<input type="text" name="telphone" />
				</p>
				<p>
					身&nbsp;&nbsp;&nbsp;&nbsp;份&nbsp;： <input type="radio" name="LoginI"
						value="" class="LoginI">教师 <input type="radio"
						name="LoginI" value="" class="LoginI" checked>学生
				</p>
				<p class="lq_s">
					<input type="submit" value="提交修改"  />
				</p>
			</form>
		</div>
	</div>
</body>
</html>
