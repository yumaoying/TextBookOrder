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
	<title>注册页面</title>
	<meta http-equiv="viewport"
		content="width=device-width,user-scalable=yes">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/register.css">

</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<h2>
			当前位置-->注册页面<font>请填写所有信息</font>
		</h2>
		<div class="reg_inform">
			
			<hr />
			 <form action="<c:url value='/UserServlet' />" method="post">
            <input type="hidden" name="method"  value="register"/>
				<div class="reg_m">
					<div class="r_left">必填信息</div>
					<div class="r_right">
						注册账号&nbsp;：<input type="text" name="userid" value="${form.userid }" />
					<span style="color: red; font-weight: 900">${errors.userid }</span><br /> 
						真实姓名&nbsp;：<input type="text" name="username" value="${form.username}" />
                    <span style="color: red; font-weight: 900">${errors.username }</span>
					</div>
				</div>
				<hr />
				<div class="reg_p">
					<div class="r_left">密码信息</div>
					<div class="r_right">
						登录密码&nbsp;：<input type="password" name="userpwd" value="${form.userpwd }" />
                    <span style="color: red; font-weight: 900">${errors.userpwd }</span><br />
						确认密码&nbsp;：<input type="password" name="surepwd" value="${surepwd }" />
                     <span style="color: red; font-weight: 900">${errors.surepwd }</span>
					</div>
				</div>
				<hr />
				<div class="reg_o">
					<div class="r_left">其他信息</div>
					<div class="r_right">
						联系方式&nbsp;：<input type="text" name="telphone" value="${form.telphone}" /><br />
						邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;：<input type="text" name="email"/>  <span style="color: red; font-weight: 900">${errors.email }</span><br />
						学&nbsp;&nbsp;&nbsp;&nbsp;校&nbsp;：<input type="text" name="school" value="${form.school}" /><span style="color: red; font-weight: 900">${errors.school }</span><br />
						学&nbsp;&nbsp;&nbsp;&nbsp;院&nbsp;：	<input type="text" name="academy" value="${form.academy}" /><br />
						专&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：<input type="text" name="major" value="${form.major}" /><br />
						年&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;：<input type="text" name="grade" value="${form.grade}" /><br />
						注册身份&nbsp;： 
						<input type="radio" name="idennty" value="0" checked="checked">学生
                       <input type="radio" name="idennty" value="1">老师 <br />
					</div>
				</div>
				<div class="sub">
					<input type="submit" value="确定注册" class="reg_s"  style="margin-top:81px;"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
