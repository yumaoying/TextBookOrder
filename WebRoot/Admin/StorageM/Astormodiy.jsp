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
	<title>库存详情与修改</title>
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
	<style>
	  .bookPic{
	  width: 200px;
    height: 276px;
    margin-right: 58px;
    float: right;
    margin-top: 100px;
    border: 1px solid black;
	  }
	  
	</style>
</head>
<body>
	<%@ include file="../Aleft_nav.txt" %>
	<div class="bb">
		<h2>库存详情与修改<font>(请谨慎操作)</font></h2>
		<hr/>
		<div class="bookAdd">
		<div class="bookPic" style="width: 200px; height: 276px;margin-right:200px;">
			    <img src="<c:url value='${stock.textbook.bookpicture }' />" title="${stock.textbook.bookname }图书图片" border="0" style="width:100%;height:100%;"/>
			</div>
			  <form id="form1" action="<c:url value='/admin/AdminStoreSevrlet'/>" method="post">
               <input type="hidden" name="method" value="modifyStock" />
              <input type="hidden" name="sid" value="${stock.sid }"/>
              <input type="hidden" name="id" value="${stock.textbook.id }"/>
               <input type="hidden" name="bookpicture" value="${stock.textbook.bookpicture }"/>
				<p>
					图书ISBN：<input type="text" name="isbn" value="${stock.textbook.isbn}"><font color="red">&nbsp;&nbsp;*</font>
   					<span style="color: red; font-weight: 900">${errors.isbn }</span>
				</p>
				<p>
					图书名称：&nbsp;<input type="text" name="bookname" value="${stock.textbook.bookname }"><font color="red">&nbsp;&nbsp;*</font>
   					<span style="color: red; font-weight: 900">${errors.bookname }</span>
				</p>
				<p>
					作&nbsp;&nbsp;&nbsp;者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="author" value="${stock.textbook.author }">
				</p>
				<p>
					出版社&nbsp;&nbsp;&nbsp;&nbsp;：<input type="text" name="publiser" value="${stock.textbook.publiser }"><br/>
				</p>
				<p>
					 出版时间：<input type="text"  class="Wdate" name="publishtime" value="${stock.textbook.publishtime }"  onFocus="WdatePicker({readOnly:true})"  size="10">
				</p>
				<p>
					定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：&nbsp;&nbsp;<input type="text" name="price" value="${stock.textbook.price }">元
				</p>
				<p>
				 进&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：&nbsp;&nbsp;<input type="text" name="comeprice" value="${stock.textbook.comeprice }">元
				 </p>
                <p>建议售价:&nbsp;&nbsp;&nbsp;<input type="text" name="saleprice" value="${stock.textbook.saleprice }">元</p>
				<p class="bookDet">
					 详细信息：&nbsp;<textarea rows="10" cols="35"  name="details" >${stock.textbook.details}</textarea>
				</p>
				<p>
					图书分类：
					<select  name="cid" >
		              <c:forEach items="${classList }" var="c">
		                <option value="${c.cid }">${c.classname }</option>
		              </c:forEach>
			        </select>
				</p>
					<p>
				库存量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="stocknumer" value="${stock.stocknumer }">册
				</p>
				<p class="book_s">
					<input type="submit" value="提交" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>