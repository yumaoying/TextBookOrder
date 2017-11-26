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
	<title>采购单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AgsdQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value="/My97DatePicker/WdatePicker.js" />"></script>
<script language=javascript>
    function preview() { 
        bdhtml=window.document.body.innerHTML; 
         sprnstr="<!--startprint-->"; 
       eprnstr="<!--endprint-->"; 
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); 
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); 
        window.document.body.innerHTML=prnhtml; 
        window.print(); 
      //  window.document.body.innerHTML=bdhtml; 
      location.reload(); 
         }
</script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="gsd">
		<div class="gsdM">
			<p class="tsxx">当前位置-->采购单打印</p>
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
			<a href="<c:url value='/admin/AdminWaitBuyBookServlet?method=findAllprint'/>" ><input type="text" name="tuoxiao" value="所有采购单" size="10" readonly="readonly" style=" background-color: #2196F3; border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s" ></a>
			<div class="gsdInfo">
			<c:choose>
    <c:when test="${empty waitlist or fn:length(waitlist)==0 }" >
		     当前没有待采购书籍信息
    </c:when>
    <c:otherwise>
        <input type="text" name="tuoxiao" value="打印" size="10" readonly="readonly" style=" background-color: rgba(128, 128, 128, 0.47); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;" class="lq_s"  onclick="preview()">
             	<!--startprint-->
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
				    </tr>
				      <c:forEach items="${waitlist}" var="waitbook" varStatus="i">
				    <tr>
				    	<td class="gsdZ">${i.index  }</td>
				    	<td class="gsdO">${waitbook.isbn }</td>
				    	<td class="gsdT">${waitbook.bookname }</td>
				    	<td class="gsdTh">${waitbook.author }</td>
				    	<td class="gsdF">${waitbook.publisher }</td>
				    	<td class="gsdFi">${waitbook.publishtime }</td>
				    	<td class="gsdS">${waitbook.plantmount }</td>
				    </tr>
				    </c:forEach>
				</table>
				<!--endprint-->
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

</body>
</html>