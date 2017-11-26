<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
	<title>出库查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AstorQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
	<script src="../../leftnav.js"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sto" style="margin-left: 336px;margin-top: 75px;">
		<p>
			当前位置-->出库记录查询
		</p>
		<hr />
		<div class="findSto" style="height: 136px;">
			<p>请输入查询条件：(支持模糊查询)</p>
			<div class="group">
			 <form name="form1" action="<c:url value='/admin/AdminOutServlet'/>"  method="post">
            <input type="hidden" name="method" value="findByGroup">
				<p>
					开始时间：<input type="text"  class="Wdate" name="starttime"  onFocus="WdatePicker({readOnly:true})"  size="10" value="${select.starttime}">
               结束时间：<input type="text"  class="Wdate" name="endtime"  onFocus="WdatePicker({readOnly:true})"  size="10" value="${select.endtime}">
               出库图书isbn:<input type="text"  name="isbn" value="${select.isbn }" class="find"/><br>
               出库图书名<input type="text" name="bookname" value="${select.bookname } " class="find"/>
               操作人员:  <input type="text" name="adminid"  class="find">
               交易用户:   <input type="text" name="userid"  class="find">    
               <input type="submit" class="sear_sub"	value="查询"  class="sear_sub"/>
			</p>
               </form>
			</div>
		</div>
		<hr />
		<div class="bookSto" style="width:98%">
			<table  border="1">
				<tr>
					<th class="fbZ">出库编号</th>
					<th class="fbZ">库存编号</th>
					<th class="fbz">订单编号</th>
					<th class="fbZ">图书ISBN</th>
					<th class="fb0">图书名称</th>
					<th class="fbSe">售价</th>
					<th   class="ftT" >当前库存量</th>
					<th class="fbT">出库数量</th>
					<th class="fbT">出库日期</th>
					<th class="fbTh">交易人账户</th>
				   <th class="fbTh">交易姓名</th>
					<th class="fbF">(用户)手机号码</th>
				   <th class="fbF">(用户)邮箱</th>
					<th class="fbT">经办人</th>
					<th class="fbT">经办人联系方式</th>
					<th colspan="2">操作</th>
				<!-- 	<th colspan="2">&nbsp;</th> -->
				</tr>
			   <c:forEach items="${outlist }" var="tl">
				<tr>
				   <td class="fbZ">${tl.outid }</td>
				   <td class="fbZ"><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${tl.stock.sid }'/>" >${tl.stock.sid }</a></td>
					<td class="fbZ">${tl.itemid }</td>
					<td class="fbZ">${tl.stock.textbook.isbn }</td>
					<td class="fbO">${tl.stock.textbook.bookname }</td>
					<td class="fbSe">${tl.stock.textbook.saleprice }</td>
					<th class="fbSe">${tl.stock.stocknumer }</th>
					<th class="fbT">${tl.outamount }</th>
					<th class="fbT"><fmt:formatDate pattern="yyyy-MM-dd" value="${tl.outdate }"/></th>
					<th class="fbTh">${tl.user.userid }</th>
					<th class="fbF">${tl.user.username }</th>
					<th class="fbF">${tl.user.telphone }</th>
					<th class="fbF">${tl.user.email }</th>
					<th class="fbE">${tl.admin.adminid }(${tl.admin.name })</th>
					<td class="fbE">${tl.admin.telphone }(${tl.admin.email  })</td>
					<td class="fbTen"> <a href="<c:url value='/admin/AdminOutServlet?method=modifyPre&outid=${tl.outid }'/>" target="_blank" >修改</a></td>
					<td class="fbEle"><a href="<c:url value='/admin/AdminStoreSevrlet?method=deleteCome&comeid=${cmlist.comeid }'/>" >删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>