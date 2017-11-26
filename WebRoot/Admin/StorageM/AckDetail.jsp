<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出库详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AckDetail.css'/>">
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
		<div class="ckDetail">
		<p class="tsxx">
			当前位置-->出库表详细信息
		</p>
		<hr/>
		<div class="ckDd">
			<p style="font-weight: 900; color: red" class="tsxx">${msg }</p>
			<div class="bookPic">
			    <img src="<c:url value='${come.textbook.bookpicture }' />" title="${outbook.stock.textbook.bookname }图书图片" border="0" style="width:100%;height:100%;"/>
			</div>
			<p> 出库编号:<input type="text" name="outid" value="${outbook.outid }" /></p>
			<p>库存编号:<input type="text" name="sid" value="${outbook.stock.sid }"></p>
			<p>来自订单:<input type="text" name="itemid" value="${outbook.itemid }" ></p>
			<p>图书isbn:<input type="text" name="isbn" value="${outbook.stock.textbook.isbn }">
			<p>图书名称:<input type="text" name="bookname" value="${outbook.stock.textbook.bookname }"></p>
			<p>图书作者:<input type="text" name="author" value="${outbook.stock.textbook.author }"></p>
			<p>图书售价:<input type="text" name="price" value="${outbook.stock.textbook.textbook.saleprice }">元</p>
			<p>出版社:&nbsp;&nbsp;&nbsp;<input type="text" name="publiser" value="${outbook.stock.textbook.publiser }"></p>
			<p>出库数量:<input type="text" name="outamount" value="${outbook.outamount }">本</p>
			<p>购买人员:<input  type="text" name="username" value="${outbook.user.username }【${outbook.user.username }】" readonly="readonly"/></p>
			<p>经办人员:<input type="text" name="name" value="${outbook.admin.adminid }【${outbook.admin.name }】"></p>
			<p>出库时间:<input type="text" name="name" value="${outbook.outdate }"></p>
		</div>
		</div>
	</div>
</body>
</html>