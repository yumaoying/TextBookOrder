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
	<title>书籍查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AbookQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	
</head>
<body>
	<%@ include file="../Aleft_nav.txt" %>
	<div class="bb">
		<div class="bkQue">
			<p>
				当前位置-->书籍信息查询
			</p>
			<hr/>
			<div class="searbk">
				<form action="<c:url value='/admin/AdminTextBookServlet'/>"  method="post" >
				<input type="hidden" name="method" value="find" id="method"/>
				<p>
					请输入查询条件：<input type="text"  name="lookup"  class="adsTJ"/>
						图书分类：
					<select  name="cid" >
					  <option>&nbsp;</option>
		              <c:forEach items="${classList }" var="c">
		                <option value="${c.cid }"   <c:if test="currentcid">selected="selected"</c:if>>${c.classname }</option>
		              </c:forEach>
			        </select>
					<input type="submit" class="sub" value="提交"/>
				</p>
				<div class="group">
					<input type="radio" name="find"  value="1" class="findOne" />ISBN
					<input type="radio" name="find"  value="2"  class="find" />书名
					<input type="radio"	name="find" value="3"  class="find" />作者
					<input type="radio" name="find"	value="4" class="find" />模糊查询
				</div>
				</form>
			</div>
			<hr/>
			<div class="bkRes">
				<table border="1">
					<tr>
						<th class="adZ">ISBN</th>
						<th class="adO">教材名</th>
						<th class="adT">作者</th>
						<th class="adTh">出版社</th>
						<th class="adTh">所属分类</th>
						<th class="adF">定价</th>
						<th class="adF">进价</th>
						<th class="adF">建议售价</th>
						
						<th colspan="2">用户设置</th>
					</tr>
					<c:forEach items="${bookList }" var="textbook">
				    <tr>
						<td class="adZ"><a href="<c:url value='/admin/AdminTextBookServlet?method=load&id=${textbook.id }'/>">${textbook.isbn}</a></td>
						<td class="adO">${textbook.bookname }</td>
						<td class="adT">${textbook.author }</td>
						<td class="adTh">${textbook.publiser }</td>
						<td class="adF">${textbook.bookclass.classname }</td>
						<td class="adF">${textbook.price }</td>
						<td class="adF">${textbook.comeprice  }</td>
						<td class="adF">${textbook.saleprice }</td>
						<td class="adFi"><a href="<c:url value='/admin/AdminTextBookServlet?method=load&id=${textbook.id }'/>" >修改</a></td>
						<td class="adS"><a href="<c:url value='/admin/AdminTextBookServlet?method=delete&id=${textbook.id }'/>" >删除</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>