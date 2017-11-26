<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
	<c:if test="${ empty sessionScope.admin }">
   <script type="text/javascript">
     location.href="<c:url value='/login.jsp'/>";
   </script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>脱销教材和库存预警</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AstorQuery.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
<script src="../../leftnav.js"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sto" style="margin-left: 336px;margin-top: 75px;">
		<p>当前位置-->(脱销教材和库存预警)</p>
		<hr />
	<div class="findSto" style="height: 97px;">
	  <div class="group">
	<form action="<c:url value='/admin/AdminLackServlet'/>" method="post">
  <input type="hidden" name="method" value="findGrooupStock">
<p>  图书ISBN：<input type="text" name="isbn" />
  图书名称:<input type="text" name="bookname" />
作者&nbsp;&nbsp;：<input type="text" name="author" /></p>
 <p> 出版社：<input type="text" name="publiser" >
  库存量<b>&nbsp;&nbsp;< </b><input type="text" name="stocknumer" />
  <input type="submit" value="查询库存" />
  <a href="<c:url value='/admin/AdminLackServlet?method=findLackOrderItem'/>" ><input type="text" name="tuoxiao" value="查找脱销教材" size="10" readonly="readonly" style=" background-color: rgba(128, 128, 128, 0.29); border: 1;border: 2px rgba(139, 69, 19, 0.12);height: 35px;text-align: center;"></a>
</p>
  
  </form>
  </div>
  </div>
  	<hr />
	 <div class="bookSto" style="width:96%">
	  <c:if test="${fn:length(orderItemList)>0 }" >
  <table border="1">
    <tr>
       <th>来自订单编号</th>
       <th>订单数量</th>
       <th>库存编号</th>
       <th>图书ISBN：</th>
       <th>图书名称:</th>
       <th>当前库存数量</th>
       <th>登记缺书状态</th>
       <th colspan="3">操作</th>
    </tr>
    <c:forEach items="${orderItemList }" var="orderItem">
       <tr>
       <th>${orderItem.orderItemid }</th>
       <th>${orderItem.buyamount }</th>
       <th><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${orderItem.stock.sid }'/>" >${orderItem.stock.sid }</a></th>
       <th>${orderItem.stock.textbook.isbn }</th>
       <th>${orderItem.stock.textbook.bookname }</th>
       <th>${orderItem.stock.stocknumer }</th>
       <th> 
         <input type="radio" name="islack${orderItem.orderItemid }" <c:if test="${orderItem.islack==0 }">checked</c:if> readonly="readonly">未登记缺书
         <input type="radio" name="islack${orderItem.orderItemid }" <c:if test="${orderItem.islack==1 }">checked</c:if> readonly="readonly">已登记缺书
       <th>
          <c:if test="${orderItem.islack==1 }"><a href="<c:url value='/admin/AdminLackServlet?method=findBysid&stockid=${orderItem.stock.sid }'/>" >查看登记缺书</a></c:if>
           <c:if test="${orderItem.islack==0 }">
          <a href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${orderItem.stock.sid}&itemid=${orderItem.orderItemid }&stocknumer=${orderItem.stock.stocknumer}&buyamount=${orderItem.buyamount}'/>" >登记缺书</a>		
          </c:if>
       </th>
      </tr>
    </c:forEach>
    </table>
    </c:if>
  <c:if test="${fn:length(stockList)>0 }">
  <table border="1">
  <tr><th>库存编号</th>
  <th>图书编号</th>
  <th>图书ISBN：</th>
  <th>图书名称:</th>
  <th>当前库存数量</th>
  <th>登记缺书状态</th>
  <th>是否登记缺书</th>
 <!--  <th colspan="2">操作</th> -->
  </tr>
  <c:forEach items="${stockList }" var="sl" >
  <tr>
  <th>${sl.sid }</th>
  <th><a href="<c:url value='/admin/AdminStoreSevrlet?method=loaBySid&sid=${sl.sid }'/>">${sl.textbook.id }</a></th>
  <th>${sl.textbook.isbn}</th>
  <th>${sl.textbook.bookname }</th>
  <th>${sl.stocknumer }</th>
  <th>
  <c:if test="${sl.islack==0 }">未登记缺书</c:if>
  <c:if test="${sl.islack==1 }">已登记缺书</c:if>
  </th>
   <th> 
   <a href="<c:url value='/admin/AdminLackServlet?method=addLackPre&stockid=${sl.sid }'/>" >
    登记缺书</a></th>
  </tr>
  </c:forEach>
  </table>
  </c:if>
    </div>
  </div>
</body>
</html>