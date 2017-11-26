<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
 <c:if test="${!empty requestScope.msg && requestScope.msg!=''}">
	<script type="text/javascript">
		alert("${requestScope.msg}");
	</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>领书单修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AlsdModify.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="lsd">
		<div class="lsdM">
			<p>当前位置-->领书单修改</p>
			<hr/>
			<div class="lsdInfo">
			<form action="<c:url value='/admin/AdminGetbookServlet' />"  method="post">
			<input type="hidden" name="method" value="updateBygid"  />
				<input type="hidden" name="gid" value="${getbook.gid }"  />
				<input type="hidden" name="uid" value="${getbook.user.uid }"  />
				订&nbsp;单&nbsp;号&nbsp;&nbsp;：<input type="text" name="oitemid" value="${getbook.oitemid }"   readonly="readonly"/><br/>
				图书isbn&nbsp;&nbsp;:&nbsp;<input type="text" name="isbn" value="${getbook.textbook.isbn }"  readonly="readonly"/><br/>
				书&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;;&nbsp;<input type="text" name="bookname" value="${getbook.textbook.bookname }"  readonly="readonly"/><br/>
				图书作者&nbsp;&nbsp;&nbsp;:<input type="text" name="author" value="${getbook.textbook.author }"  readonly="readonly"/><br/>
				出版社&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input type="text" name="publiser" value="${getbook.textbook.publiser }"  readonly="readonly"/><br/>
				出版时间&nbsp;&nbsp;&nbsp;:<input type="text" name="publishtime" value="${getbook.textbook.publishtime }"  readonly="readonly"/><br/>
		    	领书数量&nbsp;&nbsp;&nbsp;:<input type="text" name="amount" value="${getbook.amount }" readonly="readonly"/><br/>
				领书人账户:<input type="text" name="usernid" value="${getbook.user.userid }" readonly="readonly"/><br/>
				领书人姓名:<input type="text" name="username" value="${getbook.user.username }" readonly="readonly"/><br/>
				联系方式：<input type="text" name="email" value="${getbook.user.email }" readonly="readonly"/><br/>
				领取时间&nbsp;&nbsp;&nbsp;&nbsp;:<input type="text"  class="Wdate" name="date" onFocus="WdatePicker({readOnly:true})"  size="20"  style="height:20px;margin-left: 24px;" value="${getbook.date }"><br/>
				领书地点&nbsp;&nbsp;&nbsp;:<input type="text" name="location" value="${getbook.location }"/>
				<br/>
				<input type="submit" value="提交" class="sub"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>