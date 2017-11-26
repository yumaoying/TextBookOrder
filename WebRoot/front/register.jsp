<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	
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
			<form>
				<div class="reg_m">
					<div class="r_left">必填信息</div>
					<div class="r_right">
						注册账号&nbsp;：<input type="text" name="userid" /><br /> 真实姓名&nbsp;：<input
							type="text" name="username" />
					</div>
				</div>
				<hr />
				<div class="reg_p">
					<div class="r_left">密码信息</div>
					<div class="r_right">
						登录密码&nbsp;：<input type="text" name="userpwd" /><br />
						确认密码&nbsp;：<input type="text" name="surepwd" />
					</div>
				</div>
				<hr />
				<div class="reg_o">
					<div class="r_left">其他信息</div>
					<div class="r_right">
						联系方式&nbsp;：<input type="text" name="telphone" /><br />
						邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;：<input type="text" name="email" /><br />
						学&nbsp;&nbsp;&nbsp;&nbsp;校&nbsp;：<input type="text" name="school" /><br />
						学&nbsp;&nbsp;&nbsp;&nbsp;院&nbsp;：<input type="text" name="academy" /><br />
						专&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：<input type="text" name="major" /><br />
						注册身份&nbsp;： <input type="radio" name="idennty" class="regInti"
							value="教师" />教师 <input type="radio" name="idennty"
							class="regInti" value="学生" checked />学生 <br />
					</div>
				</div>
				<div class="sub">
					<input type="submit" value="确定注册" class="reg_s" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
