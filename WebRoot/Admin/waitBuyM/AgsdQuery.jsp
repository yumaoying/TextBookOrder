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
	<title>购书单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AgsdQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="gsd">
		<div class="gsdM">
			<p class="tsxx">当前位置-->采购单查询</p>
			<hr/>
			<div class="gsdTJ">
				<form name="form1"  action="<c:url value='/admin/AdminWaitBuyBookServlet'/>" method="post">
				<input type="hidden" name="method" value="find">
				<h3>请输入查询条件：<input type="submit"
						value="查询" class="lq_s" />
				</h3>
				<p>
					图书ISBN或书名:<input type="text" name="lookup">
				</p>
				</form>
			</div>
			<hr/>
			<a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=findAll'/>" ><input type="text" name="tuoxiao" value="所有采购单" size="10" readonly="readonly" style=" background-color: #2196F3; border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s" ></a>
			<div class="gsdInfo">
			<c:choose>
    <c:when test="${empty waitlist or fn:length(waitlist)==0 }" >
		     当前没有待采购书籍信息
    </c:when>
    <c:otherwise>
       <a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=findAllprint'/>" ><input type="text" name="tuoxiao" value="打印采购单" size="10" readonly="readonly" style=" background-color: #2196F3; border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s" ></a>
				<table border="1">
				   <tr><th colspan="9">采购单</th></tr>
				    <tr>
				    	<th class="gsdZ">编号</th>
				    	<th class="gsdO">ISBN</th>
				    	<th class="gsdT">图书名称</th>
				    	<th class="gsdTh">作者</th>
				    	<th class="gsdF">出版社</th>
				    	<th class="gsdFi">出版日期</th>
				    	<th class="gsdS">采购数量</th>
				    	<th colspan="2">操作</th>
				    </tr>
				      <c:forEach items="${waitlist}" var="waitbook" varStatus="i">
				    <tr>
				    	<td class="gsdZ">${i.index+1  }</td>
				    	<td class="gsdO">${waitbook.isbn }</td>
				    	<td class="gsdT">${waitbook.bookname }</td>
				    	<td class="gsdTh">${waitbook.author }</td>
				    	<td class="gsdF">${waitbook.publisher }</td>
				    	<td class="gsdFi">${waitbook.publishtime }</td>
				    	<td class="gsdS">${waitbook.plantmount }</td>
				    	<td class="gsdSev"><a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=modifypre&wid=${waitbook.wid }' />"  target="_blank">修改</a></td>
				    	<td class="gsdE"><a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=deleteBywid&wid=${waitbook.wid }'/>"  >删除</a></td>
				    </tr>
				    </c:forEach>
				</table>
					<a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=deleteAll'/>" ><input type="text" name="tuoxiao" value="清空采购单" size="10" readonly="readonly" style=" background-color: rgba(128, 128, 128, 0.29); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s"   onclick="return confirm('确认清空采购单吗？此操作不可恢复')"></a>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>