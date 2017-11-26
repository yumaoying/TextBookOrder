<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>新书入库</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/ArkNew.css'/>">
<link rel="stylesheet" type="text/css" href="../AdminCSS/Aleft_nav.css">
<script src="../../leftnav.js"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="rk_new">
		<p>当前位置-->新书入库</p>
		<hr />
		<div class="rkIn">
		<p>请先到书籍添加页面添加图书，再入库</p>
		<form id="form1" action="<c:url value='/admin/AdminComeServlet'/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="method" value="newBookCome" />
			<p>
				图书编号ISBN：<input type="text" name="isbn"  value="${come.textbook.isbn}" class="find" /> <span style="color: red; font-weight: 900">${errors.isbn }</span>
			</p>
			<p>
				图书名称&nbsp;&nbsp;：<input type="text" name="bookname" class="find"  value="${come.textbook.bookname }" />  <span style="color: red; font-weight: 900">${errors.bookname }</span>
			</p>
			<p>
				作&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text" name="author"
					class="find" value="${come.textbook.author }" />
			</p>
			<p>
				出版社&nbsp;&nbsp;&nbsp;：<input type="text" name="publiser"
					class="find"  value="${come.textbook.publiser }"/>
			</p>
			<p>
				出版时间&nbsp;&nbsp;：<input type="text" name="publishtime" class="Wdate,find"  value="${come.textbook.publishtime }"  onFocus="WdatePicker({readOnly:true})"  size="10"/>
			</p>
			<p>
				进&nbsp;&nbsp;&nbsp;&nbsp;价：<input type="text" name="comePrice"
					class="find"  value="${come.textbook.comeprice }" />
			</p>
			<p>
				定&nbsp;&nbsp;&nbsp;&nbsp;价：<input type="text" name="price"
					class="find" value="${come.textbook.price }" />
			</p>
			<p>
				售&nbsp;&nbsp;&nbsp;&nbsp;价：<input type="text" name="saleprice"
					class="find"  value="${come.textbook.saleprice }"/>
			</p>
			<p>
				所属类别：<select  name="cid" class="find" >
              <c:forEach items="${classList }" var="c">
                <option value="${c.cid }" <c:if test="${c.cid eq textbook.bookclass.cid }">selected="selected"</c:if> >${c.classname }</option>
              </c:forEach>
          </select>
			</p>
			<p>
				图书详情描述：<input type="text" name="details" class="find" value="${come.textbook.details}" />
			</p>
			<p>
				图&nbsp;&nbsp;&nbsp;&nbsp;片：<input type="text" name="bookpicture"
					class="find" />
			</p>
			<p>
				入库数量&nbsp;&nbsp;：<input type="text" name="comenumber" class="find" />
			</p>
			<p>
			  供应商：<input type="text" name="suplyer" value="${come.suplyer }"  class="find" >
			</p>
			<p>
			  供应商联系方式：<input type="text" name="suplyphone" value="${come.suplyphone }"  class="find" >
			</p>
			<p>
				经办人(采购人员)：<select  name="aid"  class="find"  >
              <c:forEach items="${adminList }" var="admin">
                <option value="${admin.aid }"  <c:if test="${come.admin.aid==admin.aid }">selected="selected"</c:if> >${admin.name }(${admin.adminid })</option>
              </c:forEach>
          </select>
			</p>
			<p>
				入库时间&nbsp;&nbsp;：<input type="text" name="comedate" value="${come.comedate }" class="Wdate,find" onFocus="WdatePicker({readOnly:true})" >
			</p>
			<p>
				<input type="submit" value="添加入库" class="rk_sub">
			</p>
			</form>
		</div>
	</div>
</body>
</html>