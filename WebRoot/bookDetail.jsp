<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>书籍详情</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"	content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/bookDetail.css">
</head>
<script type="text/javascript">
	 function operNum(inputDom,id){  
    //声明操作对象的value值 + 或是 -  
     var value = inputDom.value;  
     //声明的是数量对应的dom对象  
     var numDom;  
     if(value=="+"){  
       numDom=document.getElementById(id);
     //  numDom = inputDom.previousSibling;  
       numDom.value = eval(numDom.value+"+"+1);  
     }else if(value=="-"){  
       numDom=document.getElementById(id);
     //  numDom = inputDom.nextSibling;  
       if(numDom.value<=1){  
       numDom.value=1;  
       }else{  
      numDom.value=eval(numDom.value+"-"+1);  
     }  
    }
   };
</script>
<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<img src="<c:url value='${stock.textbook.bookpicture }'/>"  style="width:200px;height:270px;"  alt="书籍图片暂时缺失"/>
		<div class="main">
		  <form id="form" action="<c:url value='/BuyCarServlet'/>" method="post">
		     <input type="hidden" name="method"  value="addToCar"/>
		     <input type="hidden" name="sid" value="${stock.sid }"/>
             <input type="hidden" name="id" value="${stock.textbook.id }"/>
			<table>
				<tr>
					<td class="bd_leftZ">书名：</td>
					<td class="bd_leftO">${stock.textbook.bookname }</td>
				</tr>
				<tr>
					<td class="bd_leftZ">作者：</td>
					<td class="bd_leftO">${stock.textbook.author }</td>
				</tr>
				<tr>
					<td class="bd_leftZ">ISBN：</td>
					<td class="bd_leftO">${stock.textbook.isbn }</td>
				</tr>
				<tr>
					<td class="bd_leftZ">出版社：</td>
					<td class="bd_leftO">${stock.textbook.publiser }</td>
				</tr>
				<tr class="bkOther">
					<td class="bd_leftZ">详细信息：</td>
					<td class="bd_leftO">${stock.textbook.details }</td>
				</tr>
				<tr>
					<td class="bd_leftZ">库存数量：</td>
					<td class="bd_leftO">${stock.stocknumer }</td>
				</tr>
				<tr>
					<td class="bd_leftZ">价格：</td>
					<td class="bd_leftO">${stock.textbook.saleprice }</td>
				</tr>
				
				<tr class="bd_tj">
				   	<td class="bd_leftZ">购买数量：</td>
					<td  >
					   <input type="button" value="-" onclick="operNum(this,${stock.textbook.id})" style="width:100px;font-weight:bold;"/>  
                        <input type="text" size="1" value="1" name="buymount" id="${stock.textbook.id}" style="width:100px;font-weight:bold;"/>  
                        <input type="button" value="+" onclick="operNum(this,${stock.textbook.id });" style="width:100px;font-weight:bold;"/> 
					      <input type="submit" value="加入购物车"  class="addToSh"/>
					 </td>
				</tr>
			 </table>
			</form>
		</div>
	</div>
	<a href="<c:url value='/shoppingtrolley.jsp'/>"><img src="<c:url value='/img/shopping.png'/>" alt="shopping" id="shoppping" /></a>

</body>
</html>
