<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
 </c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<form class="main" action="<c:url value='/UserServlet' />" method="post">
		 <input type="hidden" name="method"  value="login"/>
			<p class="main_h">登陆入口</p>
			<div class="main_1">
				<p>
					用户名：&nbsp;<input type="text" name="userid" value="${sessionScope.userid }" class="sr"></input>
				</p>
				<p>
					密&nbsp;&nbsp;码：&nbsp;<input type="password" name="userpwd" value="${sessionScope.userpwd }" class="sr"></input>
				</p>
				<p>
					登陆身份：&nbsp;<input type="radio" name="idennty" value="教师" class="rad" <c:if test="${sessionScope.login_user.idennty eq 0 }">checked</c:if>>教师 &nbsp;&nbsp;&nbsp; 
					<input type="radio" name="idennty" value="0" class="rad"  checked >学生
				</p>
				<p id="ad">
					<input type="radio" name="idennty" value="2" class="rad" id="ad">管理员
				</p>
				<p class="main_f">
					<a href="<c:url value='/findPassw.jsp'/>">忘记密码</a>&nbsp;&nbsp;|&nbsp; <a
						href="<c:url value='/register.jsp'/>">注册</a> <input type="checkbox" class="rem" name="rem" />记住我
				</p>
				<p>
					<input type="submit" value="提交" class="bt" /> <input type="reset"
						value="重置" class="bt" />
				</p>
			</div>
		</form>
	</div>
</body>
</html>
