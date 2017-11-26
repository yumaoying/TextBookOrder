<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if> --%>
 <c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
     </c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>会员添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AhyAdd.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt" %>
    <div class="bb">
    	<h3>添加会员<font>请填写全部信息</font></h3>
		<hr/>
		<div class="hyAdd">
			    <form action="<c:url value='/admin/MemberServlet' />" method="post">
                 <input type="hidden" name="method"  value="add"/>
				<p>用户账户&nbsp;:<input type="text" name="userid" value="${form.userid }" /><img src= "<c:url value='/img/biTian.png' />" /><span style="color: red; font-weight: 900">${errors.userid }</span></p>
				<p>用户姓名&nbsp;:<input type="text" name="username" value="${form.username}" /><img src= "<c:url value='/img/biTian.png' />" /><span style="color: red; font-weight: 900">${errors.username }</span></p>
				<p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:<input type="password" name="userpwd" value="${form.userpwd }" /><img src= "<c:url value='/img/biTian.png' />" /><span style="color: red; font-weight: 900">${errors.userpwd }</span></p>
				<p>所在学校&nbsp;:<input type="text" name="school" value="${form.school}" /><img src= "<c:url value='/img/biTian.png' />" /><span style="color: red; font-weight: 900">${errors.school }</span></p>
				<p>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;:<input type="text" name="email"/><img src= "<c:url value='/img/biTian.png' />" /><span style="color: red; font-weight: 900">${errors.email }</span></p>
				<p>电话号码&nbsp;:<input type="text" name="telphone" value="${form.telphone}" /></p>
				<p>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院:&nbsp;<input type="text" name="academy" value="${form.academy}" /></p>
				<p>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:&nbsp;<input type="text" name="major"/></p>
				<p>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:&nbsp;<input type="text" name="grade" value="${form.grade}" /></p>
				<div class="ad_rig">
					<div class="l_wrap">用户身份&nbsp;:</div>
					<div class="wrap">
						<input type="radio" id="checkbox_a1" class="A_right"  name="idennty" value="1"  /><label for="checkbox_a1">教师</label>
						<input type="radio" id="checkbox_a2" class="A_right"  name="idennty" value="0"  checked/><label for="checkbox_a2">学生</label>
					</div>	
				</div>
				<p class="admin_s"><input type="submit" value="提交"/></p>
			</form>
		</div>
	</div>
</body>
</html>