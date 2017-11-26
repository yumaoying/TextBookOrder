<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理员添加</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
		<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AadminAdd.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
		
	</head>
	<body>
		<%@ include file="../Aleft_nav.txt" %>
		<div class="bb">
			<div class="adAdd">
				<h2>添加管理员<font>请填写全部信息</font></h2>
				<hr />
				<form action="<c:url value='/admin/AdminUserServlet'/>" method="post">
                <input type="hidden" name="method" value="addAdmin">
					<p>
						账户姓名:<input type="text" name="adminid" value="${form.adminid }" />
    					<span style="color: red; font-weight: 900">${errors.adminid }</span>
					</p>
					<p>
						真实姓名:<input type="text" name="name" value="${form.name }" /><span style="color: red; font-weight: 900">${errors.name }</span>
					</p>
					<p>
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="text" name="pwd" value="${form.pwd }" /><span style="color: red; font-weight: 900">${errors.pwd }</span>
					</p>
					<p>
						确认密码:<input type="text" name="surepwd" value="" /><span style="color: red; font-weight: 900">${errors.surepwd }</span>
					</p>
					<p>
						联系方式:<input type="text" name="telphone" value="${form.telphone }" /><span style="color: red; font-weight: 900">${errors.telphone }</span>
					</p>
					<p>
						Email&nbsp;&nbsp;&nbsp;: <input type="text" name="email" value="${form.email }" /><span style="color: red; font-weight: 900">${errors.email }</span>
					</p>
					<div class="ad_rig">
						<div class="l_wrap">操作权限:</div>
						<div class="wrap">
						<input type="checkbox" id="checkbox_a1" class="A_right"  name="quanxian1" value="1" checked /><label  for="checkbox_a1">教材发行人员</label>
						  <input type="checkbox"	id="checkbox_a2" class="A_right" checked name="quanxian2" value="1" /><label  for="checkbox_a2">教材采购人员</label>
                        <input type="checkbox"	id="checkbox_a3" class="A_right" checked  name="quanxian3" value="1" /><label	 for="checkbox_a3">用户管理员</label>
                         <input type="checkbox" id="checkbox_a4" class="A_right" checked  name="quanxian4" value="1"/><label  for="checkbox_a4">库存管理员</label>
                          <input type="checkbox" id="checkbox_a5" class="A_right" checked  name="quanxian5" value="1"/><label for="checkbox_a5">订单管理员</label>
						</div>
					</div>
					<p class="admin_s">
						<input type="submit" value="提交" />
					</p>
				</form>
			</div>
		</div>
	</body>
</html>
