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
<title>已有图书入库</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Ark.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
<script language="javascript" type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js'/>"></script>
<!-- <script src="../../leftnav.js"></script> -->
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bookRK" style="margin-left: 336px;margin-top: 75px;">
		<p>当前位置-->图书入库</p>
		<hr />
		<div class="tkInfo">
    <form id="form1" action="<c:url value='/admin/AdminStoreSevrlet'/>" >
    <input type="hidden" name="method" value="existBookCome" />
    	<p>
   请选择入库图书
   <select  name="id" >
              <c:forEach items="${bookList }" var="blist">
                <option value="${blist.id }">【${blist.isbn }】${blist.bookname }${blist.comeprice }</option>
              </c:forEach>
          </select>
		</p>
			<p>
				进货数量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"  name="comenumber" value="${come.comenumber }">本
			</p>
			<p>
				进货日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="comedate" value="${come.comedate }"  class="Wdate" onFocus="WdatePicker({readOnly:true})">
			</p>
			<p>
				供应商：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="suplyer" value="${come.suplyer }">
			</p>
			<p>
			  供应商联系方式：&nbsp;<input type="text" name="suplyphone" value="${come.suplyphone }"  class="find" >
			</p>
			<p>
			采购人员：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  name="aid" >
              <c:forEach items="${adminList }" var="admin">
                <option value="${admin.aid }" >${admin.name }(${admin.adminid })</option>
              </c:forEach>
          </select>
			</p>
			<p>
				<input type="submit" value="添加入库" class="rk_sub">
			</p>
			</form>
			<p>
			   <a href="<c:url value='/admin/AdminTextBookServlet?method=addPre'/>">新书入库请先再书籍管理页面添加图书后，在入库</a>
			</p>
		</div>
	</div>
</body>
</html>