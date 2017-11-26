<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>书籍分类查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AbookclassQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt" %>
	<div class="bb">
		<div class="bklist">
			<div class="addBKL">
			    <h2>添加图书分类</h2>
				<form action="<c:url value='/admin/AdminBookClassServlet'/>" method="post">
					<input type="hidden" name="method" value="add" />
					 图书类名 :<input type="text" name="classname" value="${classname }" /> 
					 <input type="submit" value="添加分类" />
				</form>
			</div>
			<hr/>
			<div class="modBKL">
				<h2>修改分类</h2>
			    <form action="<c:url value='/admin/AdminBookClassServlet'/>" method="post">
			    	<input type="hidden" name="method" value="modify" />
			    	<input type="hidden" name="cid" value="${bookclass.cid }" />
			    	分类名称：<input type="text" name="classname" value="${bookclass.classname }"/>
			    	<input type="submit" value="修改分类"/>
			    </form>
			</div>
			<hr>
			<div class="searBKL">
				<h2>分类列表</h2>
				<table >
			    	<tr id="th" bordercolor="rgb(78,78,78)">
			    		<th class="bkO">分类名称</th>
			    		<th class="bkT">操作</th>
			    	</tr>
					<c:forEach items="${classList }" var="c">    
					    	<tr bordercolor="rgb(78,78,78)">
					    		<td class="bkO">${c.classname }</td>
					    		<td class="bkT">
					    		  <a href="<c:url value='/admin/AdminBookClassServlet?method=modifyPre&cid=${c.cid }'/>">修改</a> |
					    		  <a onclick="return confirm('您真要删除该分类吗?')" href="<c:url value='/admin/AdminBookClassServlet?method=delete&cid=${c.cid }'/>">删除</a>
					    		</td>
					    	</tr>
					</c:forEach>
				  </table>
			  </div>
		</div>
	</div>
</body>
</html>