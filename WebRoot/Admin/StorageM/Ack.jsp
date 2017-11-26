<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书出库</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Ack.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
<script language="javascript" type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js' />"></script>
<script src="<c:url value='/leftnav.js'/>"></script>
<style>
    .find{
    height: 20px;
    width: 220px;
    }
    table{
    margin-left:13%;
    line-height: 40px;
    font-size: 22px;
    }
</style>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bck" style="margin-left: 336px;margin-top:81px;">
		<p>当前位置-->图书出库</p>
		<hr/>
		<div class="ckIn">
			<table >
			  <tr><th>图书ISBN：<th><td><input type="text" name="bookid" class="find" /></td></tr>
			  <tr><th>图书名称：<th><td><input type="text" name="bookname" class="find" /></td></tr>
			   <tr><th>作者：<th><td><input type="text" name="author"	class="find" /></td></tr>
			    <tr><th>出版社：<th><td><input type="text" name="publisher"	class="find" /></td></tr>
			     <tr><th>出库数量：<th><td><input type="text" name="outamount"	class="find" /></td></tr>
			     <tr><th>售价：<th><td><input type="text" name="saleprice"	class="find" /><td></tr>
			     <tr><th>购买人：<th><td><input type="text" name="username"	class="find" /><td></tr>
			      <tr><th>购买人ID：<th><td><input type="text" name="userid"	class="find" /><td></tr>
			      <tr><th>经办人：<th><td><input type="text" name="uid"	class="find" /><td></tr>
			      <tr><th>出库时间：<th><td><input type="text" name="outdate" value=""  class="Wdate" onFocus="WdatePicker({readOnly:true})"  style="width: 220px;"/><td></tr>
			    <tr><th colspan="2">&nbsp;</th></tr>
			    <tr><td colspan="2" ><input type="submit" value="出库" class="ck_sub" style="margin-left:104%"></td></tr>
			</table>
		</div>
	</div>
</body>
</html>