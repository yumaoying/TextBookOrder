<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>领书单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AlsdQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="lsd">
		<div class="lsdM">
			<p class="tsxx">当前位置-->用户领书单查询</p>
			<hr/>
			<div class="lsdTJ">
			<form action="<c:url value='/admin/AdminGetbookServlet' />"  method="post">
			<input type="hidden" name="method" value="finfByuid"  />
				<h3>请输入查询条件：<input type="submit"
						value="查询" class="lq_s" />
				</h3>
				<p>
					输入用户账号:<input type="text"  name="userid"/>
				</p>
				</form>
			</div>
			<hr/>
			<div class="lsdInfo">
			<c:choose>
	  <c:when test="${empty getbooklist or fn:length(getbooklist) eq 0 }" >
		       当前没有记录
	  </c:when>
	<c:otherwise>
				<table border="1">
				    <tr>
				    	<th class="lsdZ">编号</th>
				    	<th class="lsdZ">来自订单号</th>
				    	<th class="lsdF">图书名称</th>
				    	<th class="lsdF">图书isbn</th>
				    	<th class="lsdF">图书作者</th>
				    	<th class="lsdFi">出版社</th>
				    	<th class="lsdS">出版时间</th>
				    	<th class="lsdS">领取数量</th>
				    	<th class="lsdS">可开始领取时间</th>
				    	<th class="lsdS">领取地点</th>
				    </tr>
	                     <c:forEach items="${getbooklist }" var="getbook" varStatus="i">
				    <tr>
				    	<td class="lsdZ">${i.index }</td>
				    	<td class="lsdZ">${getbook.oitemid }</td>
				    	<td class="lsdF">${getbook.textbook.bookname }</td>
				    	<td class="lsdF">${getbook.textbook.isbn }</td>
				    	<td class="lsdF">${getbook.textbook.author }</td>
				    	<td class="lsdF">${getbook.textbook.publiser }</td>
				    	<td class="lsdFi">${getbook.textbook.publishtime }</td>
				    	<td class="lsdS">${getbook.amount }</td>
				    	<td class="lsdS">${getbook.date}</td>
				    	<td class="lsdS">${getbook.location }</td>
				    </tr>
				  	    <c:if test="${i.last }">
	    <tr>
	    <%--  <th colspan="7">用户账户：${getbook.user.userid }&nbsp;&nbsp;领取时间<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getbook.date }"/>,领取地点${getbook.location }</th> --%>
	      <th colspan="12">
	        <form action="<c:url value='/admin/AdminGetbookServlet' />"  method="post">
              <input type="hidden" name="method" value="update"  />
              <input type="hidden" name="uid" value="${getbook.user.uid }"  />
                  <input type="hidden" name="oitemid" value="${getbook.oitemid }"  />
              用户账户：<input type="text" name="userid" value="${getbook.user.userid }" readonly="readonly">&nbsp;&nbsp;&nbsp;
              用户姓名：<input type="text" name="username" value="${getbook.user.username }" readonly="readonly">&nbsp;&nbsp;&nbsp;
              领取时间：<input type="text"  class="Wdate" name="date" value="${getbook.date }"  onFocus="WdatePicker({readOnly:true})"  size="20">&nbsp;&nbsp;&nbsp;
              领取地点：<input type="text" name="location" value="${getbook.location }" >&nbsp;&nbsp;&nbsp;
	          <input type="submit"  value="提交修改"    style=" background-color:rgba(158, 158, 158, 0.85);  border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" size="30">
	       <a href="<c:url value='/admin/AdminGetbookServlet?method=deleteByuid&uid=${getbook.user.uid }'/>"  onclick="return confirm('您真要要取消吗，该操作会将用户订单的登记购书状态修改为未登记状态,同时删除该用户所有领书单')" >
	           <input type="text" name="deletebyuid" value="取消登记该用户所有的领书单" size="30" readonly="readonly" style="background-color:rgba(158, 158, 158, 0.85); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;">
	       </a>
	        <a href="<c:url value='/admin/AdminGetbookServlet?method=sureGetbook&uid=${getbook.user.uid }'/>" onclick="return confirm('用户确认领书后，将会从删除该用户的领书单，是否确认领书')" >
	           <input type="text" name="sureGetbook" value="用户确认领书" size="30" readonly="readonly" style="background-color:rgba(158, 158, 158, 0.85);  border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;"  >
	       </a>
	          </form>
	      </th>
	    </tr>
	    </c:if>
				    </c:forEach>
				</table>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>