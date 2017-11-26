<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AadminSearch.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bb">
		<div class="bFtea">
		
			<p>
				当前位置-->管理员信息查询 <a href="<c:url value='/Admin/UserM/AadminAdd.jsp'/>">添加管理员</a>
			</p>
			<hr/>
			<div class="searad">
				<form action="<c:url value='/admin/AdminUserServlet' />" method="post">
                 <input type="hidden" name="method"  value="findGroup"/>
				<p>
					请输入查询条件：<input type="text" name="lookup"  class="adsTJ"/>
					<input type="submit" class="sub" value="提交"/>
				</p>
				<div class="group">
					<input type="radio" name="find" value="1"  class="findOne" />用户账户
					<input type="radio" name="find"   value="2" class="find" />真实姓名
					<input type="radio"	name="find"   value="3" class="find" />联系方式
					<input type="radio" name="find"	  value="4" class="find" />模糊查询
				</div>
				</form>
			</div>
			<hr/>
			<div class="adRes">
				<table border="1">
					<tr>
						<th class="adZ">管理员编号</th>
						<th class="adO">帐户名</th>
						<th class="adT">真实姓名</th>
						<th class="adTh">密码</th>
						<th class="adF">联系方式</th>
						<th class="adF">邮箱</th>
						<th class="adFi">操作权限</th>
						<th colspan="2">用户设置</th>
					</tr>
					<c:forEach items="${adminList }" var="alist">
				       <tr>
				         <td>${alist.aid }</td>
				         <td>${alist.adminid }</td>
				         <td>${alist.name }</td>
				        <td>${alist.pwd }</td>
				         <td>${alist.telphone }</td>
				         <td>${alist.email }</td>
				         <td>
				          <input type="checkbox"  name="quanxian1" <c:if test="${alist.right[0] eq 1 }">checked</c:if>   disabled="disabled"/>教材发行
						 <input type="checkbox"  name="quanxian2"   <c:if test="${alist.right[1] eq 1 }">checked</c:if>  disabled="disabled"/>教材采购
                        <input type="checkbox"	name="quanxian3"  <c:if test="${alist.right[2] eq 1 }">checked</c:if> disabled="disabled"  />用户管理
                         <input type="checkbox"  name="quanxian4"  <c:if test="${alist.right[3] eq 1 }">checked</c:if>  disabled="disabled"/>库存管理
                          <input type="checkbox"  name="quanxian5"   <c:if test="${alist.right[4] eq 1 }">checked</c:if>  disabled="disabled"/>订单管理
				         </td>
				        <td><c:if test="${alist.adminid eq 'sys' }">超级管理员</c:if><c:if test="${alist.adminid  ne 'sys' }"><a href="<c:url value='/admin/AdminUserServlet?method=modifyPre&aid=${alist.aid }'/>">修改</a></c:if></td>
				        <td><c:if test="${alist.adminid eq 'sys' }">超级管理员</c:if><c:if test="${alist.adminid ne  'sys' }"><a href="<c:url value='/admin/AdminUserServlet?method=deleteAdmin&aid=${alist.aid }'/>">删除</a></c:if></td>
				       </tr>
				   </c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>