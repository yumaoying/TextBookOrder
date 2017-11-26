<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>入库表详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/ArkDetail.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script type="text/javascript">
		function setMethod(method) {
			var ele = document.getElementById("method");
			ele.value = method;
		}
	</script>
	
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sto">
		<div class="rkDetail">
		<p class="tsxx">
			当前位置-->入库表详细信息
		</p>
		<hr/>
		<div class="rkDd">
			<div class="bookPic" style="width: 200px; height: 276px;margin-right:200px;">
			    <img src="<c:url value='${come.textbook.bookpicture }' />" title="${come.textbook.bookname }图书图片" border="0" style="width:100%;height:100%;"/>
			</div>
			<p>图书编号:&nbsp;<input type="text" name="id" value="${come.textbook.id }"/></p>
			<p>图书ISBN:&nbsp;<input type="text" name="isbn" value="${come.textbook.isbn}"></p>
		
			<p>图书名称:&nbsp;&nbsp;<input type="text" name="bookname" value="${come.textbook.bookname }"></p>
			
			<p>作者:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="author" value="${come.textbook.author }"></p>
			<p>出版社:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="publiser" value="${come.textbook.publiser }"></p>
			<p>出版时间:&nbsp;&nbsp;<input type="text" name="publishtime"  value="${come.textbook.publishtime }" ></p>
			<p>定价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price" value="${come.textbook.price }">元</p>
			<p>详细信息:&nbsp;<textarea rows="8" cols="40"  name="details" >${come.textbook.details}</textarea></p>
			<p>图书分类:&nbsp;&nbsp;<font>${come.textbook.bookclass.classname }</font>
			<p>进价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="comeprice" value="${come.textbook.comeprice }">元</p>
			<p>售价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="saleprice" value="${come.textbook.saleprice }">元</p>
			<p>进货数量:&nbsp;&nbsp;<input type="text" name="comenumber" value="${come.comenumber }">册</p>
	
			<p>进货日期:&nbsp;&nbsp;<input type="text" name="comedate" value="${come.comedate }"></p>
			<p>供应商:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="suplyer" value="${come.suplyer }"></p>
			<p>联系方式:&nbsp;<input type="text" name="suplyphone" value="${come.suplyphone }"></p>
			<p>采购人员:&nbsp;&nbsp;<font>${come.admin.adminid }(${come.admin.name })</font></p>
		</div>
		</div>
	</div>
</body>
</html>