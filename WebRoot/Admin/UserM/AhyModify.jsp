<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>会员信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AhyModify.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bb">
		<div class="hyMod">
			<h2>会员信息修改<font>请填写全部信息</font></h2>
			<hr/>
			<form action="<c:url value='/admin/MemberServlet' />" method="post">
               <input type="hidden" name="method"  value="modify"/>
             <input type="hidden" name="uid"  value="${form.uid }"/>
				<p>
					学号(工号):<input type="text" name="userid" value="${form.userid }" readonly="readonly"/>
				</p>
				<p>
					用户姓名&nbsp;:<input type="text" name="username" value="${form.username}" />
				</p>
				<p>
				    <span style="color: red; font-weight: 900">${errors.username }</span>
				</p>
				<p>
					用户密码&nbsp;:<input type="password" name="userpwd" value="${form.userpwd }" />
				</p>
				<p>
				 <span style="color: red; font-weight: 900">${errors.userpwd  }</span>
				 </p>
				<p>
					邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:<input type="text" name="email" value="${form.email}" />
				</p>
				<p>
				    <span style="color: red; font-weight: 900">${errors.email }</span>
				 </p>
				<p>
					电话号码&nbsp;:<input type="text" name="telphone" value="${form.telphone}" />
				</p>
				<p>
					所在学校&nbsp;:<input type="text" name="school" value="${form.school}" />
				</p>
				<p>
				 <span style="color: red; font-weight: 900">${errors.school }</span>
				 </p>
				<p>
					学&nbsp;&nbsp;&nbsp;院:&nbsp;&nbsp;&nbsp;<input type="text" name="academy" />
				</p>
				<p>
					专&nbsp;&nbsp;&nbsp;业:&nbsp;&nbsp;&nbsp;<input type="text" name="major" value="${form.major}" />
				</p>
					<p>年&nbsp;&nbsp;&nbsp;级:&nbsp;&nbsp;&nbsp;<input type="text" name="grade" value="${form.grade}" /></p>
				<p> 用户身份：<input type="radio" name="idennty" value="0" <c:if test="${form.idennty ==0 }">checked</c:if> style="margin:0;width:50px;height:21px;" >学生&nbsp;&nbsp;
                       <input type="radio" name="idennty" value="1" <c:if test="${form.idennty ==1}">checked</c:if> style="margin:0;width:50px;height:21px;" >老师
				</p>
				<p>
				  &nbsp;
				</p>
				<p class="admin_s">
					<input type="submit" value="提交" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>