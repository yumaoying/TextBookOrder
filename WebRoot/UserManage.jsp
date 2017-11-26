<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ empty sessionScope.login_user }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
<c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
 </c:if>
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
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/UserManage.css">

</head>

<body>
	<%@ include file="head.txt"%>
	<div class="usrM">
		<h3>个人信息管理<font>带*星号必须填写</font></h3>
		<div>
			<form action="<c:url value='/UserServlet' />" method="post">
           <input type="hidden" name="method"  value="modify"/>
              <input type="hidden" name="uid"  value="${login_user.uid }"/>
           <input type="hidden" name="userid"  value="${login_user.userid }"/>
				<p>
					用户账户&nbsp;：<input type="text" name="userid1" value="${login_user.userid }"  disabled="disabled"/>无法修改
				</p>
				<p>
					用户名&nbsp;&nbsp;&nbsp;：<input type="text" name="username" value="${login_user.username}" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;</font>
                  <span style="color: red; font-weight: 900">${errors.username }</span>
				</p>
				<p>
				 密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;：<input type="password" name="userpwd" value="${login_user.userpwd }" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;</font>
                  <span style="color: red; font-weight: 900">${errors.userpwd}</span>
                  </p>
				<p>
					E-mail&nbsp;&nbsp;&nbsp;：<input type="text" name="email"
						class="email"  value="${login_user.email}" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;</font>
						<span style="color: red; font-weight: 900">${errors.email }</span>
				</p>
				<p>
					联系方式&nbsp;：<input type="text" name="telphone" value="${login_user.telphone}" />
				</p>
				<p>
					所在学校&nbsp;：<input type="text" name="school" value="${login_user.school}" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;</font><span style="color: red; font-weight: 900">${errors.school }</span>
				</p>
				<p>
					学&nbsp;&nbsp;&nbsp;&nbsp;院&nbsp;：<input type="text" name="academy" value="${login_user.academy}" />
				</p>
				<p>
					专&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：<input type="text" name="major" value="${login_user.major}" />
				</p>
				<p>
					年&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;：<input type="text" name="grade" value="${login_user.grade}" />
				</p>
				<p>
					身&nbsp;&nbsp;&nbsp;&nbsp;份&nbsp;： 
					<input type="radio" name="idennty" value="1" <c:if test="${login_user.idennty ==1}">checked</c:if> class="LoginI" readonly="readonly">教师
					<input type="radio" name="idennty" value="0" <c:if test="${login_user.idennty ==0 }">checked</c:if>  class="LoginI" readonly="readonly">学生 
				</p>
				<p class="lq_s">
					<input type="submit" value="提交修改"  />
				</p>
			</form>
		</div>
		<div>
			
		</div>
	</div>
</body>
</html>
