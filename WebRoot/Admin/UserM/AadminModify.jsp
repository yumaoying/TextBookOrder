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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>管理员信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"	href="<c:url value='/Admin/AdminCSS/AadminModify.css'/>">
	<link rel="stylesheet" type="text/css"	href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bb">
		<div class="adMod">
			<h2>管理员信息修改<font>请填写全部信息</font></h2>
			<hr />
			   <form action="<c:url value='/admin/AdminUserServlet'/>" method="post">
			  <input type="hidden" name="method" value="modifyAdmin">
              <input type="hidden" name="aid"   value="${form.aid }" >
				<p>
					帐户名&nbsp;:&nbsp;&nbsp;<input type="text" name="adminid" value="${form.adminid }" readonly="readonly"/>
				</p>
				<p>
					真实姓名:<input type="text" name="name" value="${form.name }" />
				</p>
				<p>
					密&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="pwd"   value="${form.pwd }" />
				</p>
				<p>
					联系方式:<input type="text" name="telphone" value="${form.telphone }" />
				</p>
				<p>
					权&nbsp;&nbsp;限: 
					  <input type="checkbox"  name="quanxian1" value="1"  <c:if test="${form.right[0] eq 1 }">checked</c:if>  style="width:40px;height:20px;margin:0;"/>教材发行人员
						 <input type="checkbox"  name="quanxian2" value="1"  <c:if test="${form.right[1] eq 1 }">checked</c:if> style="width:40px;height:20px;margin:0;"/>教材采购人员
                        <input type="checkbox"	name="quanxian3" value="1"  <c:if test="${form.right[2] eq 1 }">checked</c:if>  style="width:40px;height:20px;margin:0;" />用户管理员
                         <input type="checkbox"  name="quanxian4" value="1" <c:if test="${form.right[3] eq 1 }">checked</c:if> style="width:40px;height:20px;margin:0;" />库存管理员
                          <input type="checkbox"  name="quanxian5" value="1" <c:if test="${form.right[4] eq 1 }">checked</c:if> style="width:40px;height:20px;margin:0;" />订单管理员
			  </p>
					<p> 
				</p>
				<p class="admin_s">
					<input type="submit" value="提交" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>