<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>出库修改</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Ack.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />">
<script src="../../leftnav.js"></script>
   <script type="text/javascript">
   function changeNum(){
     
     if(!IsNum(num)){
       alert("请输入数字");
      this.focus();
     }
    };
    
    function IsNum(num){
     var reNum=/^\d*$/;
     return(reNum.test(num));
   };
   </script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bck"  style="margin-left: 336px;margin-top: 75px;">
		<p>当前位置-->出库修改</p>
		<hr />
		<div class="ckIn">
		<form action="<c:url value='/admin/AdminOutServlet'/>" method="post">
    <input type="hidden" name="method" value="update">
    <input type="hidden" name="uid" value="${outbook.user.uid }"/>
   <input type="hidden" name="outid" value="${outbook.outid }"/>
		   <span>(修改需谨慎,如非必要,请勿修改,此操作仅修改出库记录，对教材库存并无影响，若操作对库存有影响，请同时修改该教材对应的库存记录)</span>
			<p>
				库存编号:<a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${outbook.stock.sid }'/>"><input type="text" name="sid" value="${outbook.stock.sid }" readonly="readonly" class="find"></a>
			</p>
			<p>
				来自订单:<input type="text" name="itemid" value="${outbook.itemid }"  readonly="readonly"  >
			</p>
			<p>图书isbn:<input type="text" name="isbn" value="${outbook.stock.textbook.isbn }"  readonly="readonly" >
			<p>图书名称:<input type="text" name="bookname" value="${outbook.stock.textbook.bookname }"  readonly="readonly" ></p>
			<p>图书作者:<input type="text" name="author" value="${outbook.stock.textbook.author }"  readonly="readonly" ></p>
			<p>图书售价:<input type="text" name="price" value="${outbook.stock.textbook.saleprice }"  readonly="readonly" >元/本</p>
			<p>
			   出库数量:<input type="text" name="outamount" value="${outbook.outamount }" class="find" >
			</p>
			<p>
			  出库日期:<input type="text" name="outdate" value="${outbook.outdate }" class="Wdate,find" onFocus="WdatePicker({readOnly:true})"  >
			</p>
			<p>
				购买人员:<input type="text" name="username"	 value="${outbook.user.username}"	class="find" readonly="readonly" />
			</p>
			<p>经办人员:<select name="aid" class="find" >
            <c:forEach items="${adminList }" var="al">
              <option  value="${al.aid }" <c:if test="${outbook.admin.aid==al.aid }">selected="selected"</c:if>>用户账户【${al.adminid}】，用户姓名【(${al.name })】</option>
            </c:forEach> </select>
            </p>
			<p>
				<input type="submit" value="出库" class="ck_sub">
			</p>
		</form>
		
		</div>
	</div>
</body>
</html>