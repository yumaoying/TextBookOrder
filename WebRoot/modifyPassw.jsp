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
	<title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/findPassw.css">

</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="findPI">
			<h2>修改密码</h2>
			<form action="<c:url value='/UserServlet' />" method="post">
             <input type="hidden" name="method"  value="modifyPwd"/>
              <input type="hidden" name="uid"  value="${login_user.uid  }"/>
				<p>
					新密码&nbsp;&nbsp;:<input type="password" name="userpwd" value="${login_user.userpwd }" />
                   <span style="color: red; font-weight: 900">${errors.userpwd }</span>
				</p>
				<p>
					确认密码:<input type="password" name="surepwd" value="${surepwd }" />
                  <span style="color: red; font-weight: 900">${errors.surepwd }</span>
				</p>
				<p>
					验证码&nbsp;&nbsp;:<input type="text" name="vercode" class="vercode" />
					<img src="#" id="vCode" alt="验证码图片"   src="<c:url value='/ValidCodeServlet?name=vercode'/>" border="2"/>
			     <a href="javascript:_change()" style="font-size: 12;">看不清，换一张</a>
				</p>
				<p>
					<input type="submit" value="提交" class="reg_s" />
				</p>
			</form>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
	<script type="text/javascript">
    	function _change() {
    		var img = document.getElementById("vCode");
    		img.src = "<c:url value='/ValidCodeServlet?name=vercode&'/>" + new Date().getTime();
    	}
    </script>
</body>
</html>
