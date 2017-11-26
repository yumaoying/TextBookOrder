<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>书籍添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AbookAdd.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />">
	</script>	
</head>
<body>
	<%@ include file="../Aleft_nav.txt" %>
	<div class="bb">
		<h2>添加书籍<font>(带*号必须填写)</font></h2>
		<hr/>
		<div class="bookAdd">
			<form action="<c:url value='/admin/AddTextbookAervlet'/>" method="post" enctype="multipart/form-data">
				<p>
					图书ISBN：<input type="text" name="isbn" value="${textbook.isbn}"><font color="red">&nbsp;&nbsp;*</font>
   					<span style="color: red; font-weight: 900">${errors.isbn }</span>
				</p>
				<p>
					图书名称：<input type="text" name="bookname" value="${textbook.bookname }"><font color="red">&nbsp;&nbsp;*</font>
   					<span style="color: red; font-weight: 900">${errors.bookname }</span>
				</p>
				<p>
					图书图片：<input type="file" name="bookpicture" value="${textbook.bookpicture }" >
  					<span style="color: red; font-weight: 900">${errors.bookpicture }</span>
				</p>
				<p>
					作&nbsp;&nbsp;&nbsp;者&nbsp;&nbsp;：<input type="text" name="author" value="${textbook.author }">
				</p>
				<p>
					出版社&nbsp;&nbsp;：<input type="text" name="publiser" value="${textbook.publiser }"><br/>
				</p>
				<p>
					 出版时间：<input type="text"  class="Wdate" name="publishtime" value="${textbook.publishtime }"  onFocus="WdatePicker({readOnly:true})"  size="20"  style="margin-left: 20px;">
				</p>
				<p>
					定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<input type="text" name="price" value="${textbook.price }">元
				</p>
				<p>
				 进&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<input type="text" name="comeprice" value="${textbook.comeprice }">元
				 </p>
                <p>建议售价:<input type="text" name="saleprice" value="${textbook.saleprice }">元</p>
				<p class="bookDet">
					 详细信息：<textarea rows="10" cols="35"  name="details" >${textbook.details}</textarea>
				</p>
				<p>
					图书分类：
					<select  name="cid" >
		              <c:forEach items="${classList }" var="c">
		                <option value="${c.cid }">${c.classname }</option>
		              </c:forEach>
			        </select>
				</p>
				<p class="book_s">
					<input type="submit" value="提交" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>