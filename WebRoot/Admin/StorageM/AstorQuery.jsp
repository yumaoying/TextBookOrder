<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
	<title>库存查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AstorQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script src="<c:url value='/leftnav.js'/>"></script>
	<style>
	.sear_sub{
	background-color: #2196F3;
    border-radius: 10px;
    width: 120px;
    height: 30px;
    font-size: 18px;
	}
	</style>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sto" style="margin-left: 336px;margin-top: 75px;">
		<p>
			当前位置-->库存查询
		</p>
		<hr />
		<div class="findSto" style="height: 135px;">
			<p>请输入查询条件：(支持模糊查询)</p>
			<div class="group">
			 <form action="<c:url value='/admin/AdminStoreSevrlet'/>" method="post">
           <input type="hidden" name="method" value="findGrooupStock"> 
				<p>
				图书ISBN：<input type="text" name="isbn" />
            图书名称:<input type="text" name="bookname" />
            作者&nbsp;&nbsp;：<input type="text" name="author" /><br>
            出版社：<input type="text" name="publiser" >
            图书分类：<select  name="cid"  class="find">
              <option value="0">&nbsp;</option>
              <c:forEach items="${classList }" var="c">
                <option value="${c.cid }" <c:if test="${c.cid eq textbook.bookclass.cid }">selected="selected"</c:if> >${c.classname }</option>
              </c:forEach>
          </select>
          &nbsp;	&nbsp;库存量	&nbsp;	&nbsp; <<%-- <input 	type="label" value="<" class=" lab"/> --%> <input type="text" name="stocknumer"  />
						 <input type="submit" class="sear_sub"
						value="查询" />
			</p>
				<p  class="gr_2">
				&nbsp;	&nbsp; 
				</p>
               </form>
			</div>
		</div>
		<hr />
		<div class="bookSto"  style="width:98%;height:460px;">
			<table border="1">
				<tr>
					<th class="fbZ">库存编号</th>
					<th class="fbZ">图书ISBN</th>
					<th class="fbO">图书名称</th>
				<!-- 	<th class="fbT">作者</th> -->
			<!-- 		<th class="fbTh">出版社</th> -->
					<th class="fbF">所属类别</th>
					<th class="fbO">进价&nbsp;</th>
					<th class="fbO">定价&nbsp;</th>
					<th class="fbO">售价&nbsp;</th>
					<th class="fbO">库存量</th>
					<th class="fbO">标记缺书</th>
					<th colspan="2">操作</th>
					
				<!-- 	<th class="fbTwl">添加入库</th> -->
		<!-- 			<th class="fbThirth">添加出库</th> -->
				</tr>
				 <c:forEach items="${stockList }" var="sl" >
				<tr>
				   <td class="fbZ"><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${sl.sid }'/>">${sl.sid }</a></td>
					<td class="fbZ">${sl.textbook.isbn}</td>
					<td class="fbO">${sl.textbook.bookname }</td>
				<%-- 	<td class="fbT">${sl.textbook.author  }</td> --%>
			<%-- 		<td class="fbTh">${sl.textbook.publiser  }</td> --%>
					<td class="fbF">[${sl.textbook.bookclass.cid  }]${sl.textbook.bookclass.classname  }</td>
					<td class="fbO">${sl.textbook.comeprice }</td>
					<td class="fbO">${sl.textbook.price }</td>
					<td class="fbO">${sl.textbook.saleprice }</td>
					<td class="fbO">${sl.stocknumer }</td>
					<td class="fbO">
					          <c:if test="${sl.islack==0 }">
                             <a href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${sl.sid }'/>" >
                              <input type="checkbox" name="islack${sl.sid }"  value="1">标记</a></c:if>
                         <c:if test="${sl.islack==1 }">已标记</c:if>
                        </td>
					<td class="fbTen"><a href="<c:url value='/admin/AdminStoreSevrlet?method=modifyStockPre&sid=${sl.sid }'/>">修改</a></td>
					<td class="fbEle"><a href="<c:url value='/admin/AdminStoreSevrlet?method=deleteStock&sid=${sl.sid }'/>">删除</a></td>
				<!-- 	<td class="fbTwl"><a href="#">出库</a></td> -->
					<%-- <td class="fbThirth">  <a href="<c:url value='/admin/AdminOutServlet?method=addOut&sid=${sl.sid }&uid=${orderItem.order.owner.uid }&outamount=${orderItem.buyamount }&itemid=${orderItem.orderItemid }'/>" >出库</a></td> --%>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>