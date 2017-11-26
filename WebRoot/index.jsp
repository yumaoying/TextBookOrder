<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<style>
	   .icon {
		margin:10px;
		border: solid 2px gray;
		width: 200px;
		height: 280px;
		text-align: center;
		float: left;
	 }
	</style>
	<c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
 </c:if>
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="left_nav">
			<ul>
				<li><a href="#">最多购买</a></li>
				<li><a href="#">猜你喜欢</a></li>
			</ul>
		</div>
		<div class="main">
		   <c:if test="${fn:length(stockList)>0 }">
                <c:forEach items="${stockList }" var="sl">
                 <div class="icon">
                 <a href="<c:url value='/TextBookServlet?method=load&sid=${sl.sid }'/>"><img src="<c:url value='${sl.textbook.bookpicture }' />" border="0" style="width:160px;height:190px;"   alt="暂无图片"/></a> <br/>
                 <a href="<c:url value='/TextBookServlet?method=load&sid=${sl.sid }'/>">${sl.textbook.bookname }</a><br/>
             </div>
              </c:forEach>
              </c:if> 
           <c:if test="${fn:length(stockList)<=0}">
            当前没有图书
           </c:if>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
</body>
</html>
