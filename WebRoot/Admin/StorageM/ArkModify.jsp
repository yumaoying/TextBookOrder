<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>入库信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/ArkModify.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js'/>"></script>
<style>

  . subpic{
  margin-top: 20px;
    width: 100px;
    height: 24px;
    font-size: 17px;
    border: 1px solid blue;
    border-radius: 10px;
    background-image: url(../../img/buttonB.jpg);
    background-repeat: no-repeat;
   }
</style>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="stor">
		<div class="rkM">
			<div class="icon" style="height:277px;">
			    <img src="<c:url value='${come.textbook.bookpicture }'/>"  border="0" style="width:100%;height:100%;"/>
			    <form id="form2" action="<c:url value='/admin/UpLoadPicture'/>"  method="post" enctype="multipart/form-data">
             <input type="hidden" name="id" value="${come.textbook.id }"/>
					重新上传图片<input type="file" name="bookpicture" value="${come.textbook.bookpicture }" >
				<input type="submit" value="上传" class="subpic">
				</form>
			</div>
			<p class="tsxx">当前位置-->入库信息修改</p>
			<hr/>
			<div class="rkDd">
				<form id="form1" action="<c:url value='/admin/AdminStoreSevrlet'/>" method="post" class="form1">
					<input type="hidden" name="method" value="modifyCome" />
						<input type="hidden" name="comeid" value="${come.comeid }"/>
					<input type="hidden" name="id" value="${come.textbook.id }"/>
					<input type="hidden" name="bookpicture" value="${come.textbook.bookpicture }"/>
					图书ISBN:&nbsp;<input type="text" name="isbn" value="${come.textbook.isbn}">
					<span style="color: red; font-weight: 900">${errors.isbn }</span>
					<br/>
					图书名称:&nbsp;&nbsp;<input type="text" name="bookname" value="${come.textbook.bookname }">
					<span style="color: red; font-weight: 900">${errors.bookname }</span>
					<br/>
					作者:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="author" value="${come.textbook.author }"><br/>
					出版社:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="publiser" value="${come.textbook.publiser }"><br/>
					出版时间:&nbsp;&nbsp;<input type="text"  class="Wdate" name="publishtime" value="${come.textbook.publishtime }"  onFocus="WdatePicker({readOnly:true})"  size="20"><br/>
					定价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price" value="${come.textbook.price }">元<br/>
					详细信息:&nbsp;<textarea rows="10" cols="20"  name="details" >${come.textbook.details}</textarea><br/>
					图书分类:&nbsp;&nbsp;
					<select  name="cid">
					    <c:forEach items="${classList }" var="c">
							<option value="${c.cid }" <c:if test="${c.cid eq textbook.bookclass.cid }">selected="selected"</c:if> >${c.classname }</option>
						</c:forEach>
					</select>
					<br>
					进价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="comeprice" value="${come.textbook.comeprice }">元<br/>
				  售价:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="saleprice" value="${come.textbook.saleprice }">元<br/>
					进货数量:&nbsp;&nbsp;<input type="text" name="comenumber" value="${come.comenumber }">册
					<span style="color: red; font-weight: 900">${errors.comenumber }</span>
					<br/>
					进货日期:&nbsp;&nbsp;<input type="text" name="comedate" value="${come.comedate }" class="Wdate" onFocus="WdatePicker({readOnly:true})"><br/>
					供应商:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="suplyer" value="${come.suplyer }"><br/>
					联系方式:&nbsp;<input type="text" name="suplyphone" value="${come.suplyphone }"><br/>
					采购人员:&nbsp;&nbsp;
					<select  name="aid" >
					    <c:forEach items="${adminList }" var="admin">
							<option value="${admin.aid }"  <c:if test="${come.admin.aid==admin.aid }">selected="selected"</c:if> >${admin.name }(${admin.adminid })</option>
						</c:forEach>
					</select><br>
					<input type="submit" value="修改" class="sub"/>
				</form>
			</div>
		</div>
	</div>	
			
			

</body>
</html>