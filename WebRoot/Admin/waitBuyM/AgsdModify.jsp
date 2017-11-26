<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
	<title>修改购书单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AgsdModify.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="gsd">
		<div class="gsdM">
			<p>当前位置-->采购单修改</p>
			<hr/>
			<div class="gsdInfo">
               <form  name="form2" action="<c:url value='/admin/AdminWaitBuyBookServlet'/>" method="post">
               <input type="hidden" name="method" value="modify">
				编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:<input type="text" name="wid"  value="${waitbuybook.wid }"  readonly="readonly"><br/>
				图书ISBN:<input type="text" name="isbn"  value="${waitbuybook.isbn }" ><br/>
				书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name="bookname"  value="${waitbuybook.bookname }" ><br/>
				作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:<input type="text" name="author" value="${waitbuybook.author }" ><br/>
		    	出&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;社:<input type="text" name="publisher" value="${waitbuybook.publisher }"  ><br/>
				出版时间：<input type="text"  class="Wdate" name="publishtime" onFocus="WdatePicker({readOnly:true})"  size="20" value="${waitbuybook.publishtime }"  style="height:20px;margin-left: 24px;"/><br/>
				采购数量&nbsp;:<input type="text" name="plantmount" onchange="changeNum();"  value="${waitbuybook.plantmount }"><br>
				<input type="submit" value="提交" class="sub"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>