<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>教材库查询</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/libraryQuery.css">
	<script type="text/javascript">
	 function operNum(inputDom,id){  
    //声明操作对象的value值 + 或是 -  
     var value = inputDom.value;  
     //声明的是数量对应的dom对象  
     var numDom;  
     if(value=="+"){  
       numDom=document.getElementById("buymount"+id);
       numDom.value = eval(numDom.value+"+"+1);  
     }else if(value=="-"){  
       numDom=document.getElementById(id);
       if(numDom.value<=1){  
       numDom.value=1;  
       }else{  
      numDom.value=eval(numDom.value+"-"+1);  
     }  
    }
  };
   //数量改变时
  function changeNum(id){
   numDom=document.getElementById("buymount"+id);
   num=numDom.value;
   if(!IsNum(num)){
     alert("请输入数字");
     num=1;
     numDom.focus();
   }
   numDom.value=num;
  };
function addCar(id){
        numDom=document.getElementById("buymount"+id);
        buymount=numDom.value;
        sub=document.getElementById("add"+id);
        sub.href="<c:url value='/BuyCarServlet?method=add&id="+id+"&buymount="+buymount+"'/>";
      //   location.href="<c:url value='/BuyCarServlet?method=add&id="+id+"&buymount="+buymount+"'/>";
    };
</script>
<c:if  test="${!empty requestScope.msg && requestScope.msg!=''}">
            <script type="text/javascript">
                    alert("${requestScope.msg}");
            </script>
 </c:if>
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="lq_tiaojian">
			<form action="<c:url value='/TextBookServlet'/>" method="post">
              <input type="hidden" name="method" value="findGrooupStock">
				<h3>请输入查询条件：</h3>
			<p>
					教材名：<input type="text" name="bookname"/>
					作&nbsp;者：<input type="text" name="author"/>
				</p>
				<p>
					ISBN&nbsp;：<input type="text" name="isbn"/>
					<input type="submit" value="查询" class="lq_s"/>
				</p>
			</form>
		</div>
		<div class="lq_main">
			<c:if test="${fn:length(stockList)>0 }">
			<table >
				<tr>
					<th>图书图片</th>
					<th>图书名称</th>
					<th>图书isbn</th>
					<th>图书作者</th>
					<th>库存量</th>
					<th>售价</th>
					<th>&nbsp;&nbsp; <th>
					<th>加入购物车</th>
				</tr>
				<c:forEach items="${stockList }" var="sl">
				<input type="hidden"  name="id" value="${sl.textbook.id }"/>
				<tr>
					<td class="lq_leftZ">
					    <a href="<c:url value='/TextBookServlet?method=load&sid=${sl.sid }'/>">
					    <img src="<c:url value='${sl.textbook.bookpicture }'/>"  style="width:150px;height:200px;" border="5"  alt="书籍图片暂无"/>
					    </a>
					</td>
					<td class="lq_left0">${sl.textbook.bookname }</td>
					<td class="lq_leftT" style="width:10%;">${sl.textbook.isbn}</td>
					<td class="lq_leftT" style="width:10%;">${sl.textbook.author }</td>
					<td class="lq_leftT" style="width:10%;">${sl.stocknumer}</td>
					<td class="lq_leftT" style="width:10%;">${sl.textbook.saleprice }元</td>
					<th>
					<input type="button" value="-" onclick="operNum(this,${sl.textbook.id})">  
<input type="text" size="1"  value="1" name="buymount" id="buymount${sl.textbook.id}" onchange="changeNum(${sl.textbook.id});"  >  
<input type="button" value="+" onclick="operNum(this,${sl.textbook.id})" >  <th>
					<td class="lq_leftTh"><%-- <a  onclick="addCar(${sl.textbook.id});"   href="javascript:addCar(${sl.textbook.id});" id="add${sl.textbook.id}"> --%>
					<a href="<c:url value='/TextBookServlet?method=load&sid=${sl.sid }'/>"><img src="img/shopping.png" class="addToShop"  /></a></td>
				</tr>
				</c:forEach>
			</table>
			</c:if>
			 <c:if test="${empty stockList  or  fn:length(stockList)<=0}">
              当前暂时没有图书
           </c:if>
		</div>
		<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>
	</div>
</body>
</html>
