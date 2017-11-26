<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>购物车</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport"
		content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/shoppingtrolley.css">
	<script type="text/javascript">
	 function operNum(inputDom,id){  
    //声明操作对象的value值 + 或是 -  
     var value = inputDom.value;  
     //声明的是数量对应的dom对象  
     var numDom;  
     if(value=="+"){  
     //获取购买数量
       numDom=document.getElementById("buymount"+id);
       numDom.value = eval(numDom.value+"+"+1);  
       
     }else if(value=="-"){  
       numDom=document.getElementById("buymount"+id);
       if(numDom.value<=1){  
       numDom.value=1;  
       }else{  
      numDom.value=eval(numDom.value+"-"+1);  
     }  
    }
    subtoprice(id);
    toprice();
  };
  //数量改变时改变小计和总价
  function changeNum(id){
   numDom=document.getElementById("buymount"+id);
   num=numDom.value;
   if(!IsNum(num)){
     alert("请输入数字");
     num=1;
     numDom.focus();
   }
   numDom.value=num;
   toprice();
  };
  //小计
  function subtoprice(id){
     numDom=document.getElementById("buymount"+id);
     saleprice=document.getElementById("saleprice"+id).value; //获得单价
     subtotalDom=document.getElementById("subtotal"+id); 
     subtotalDom.value=eval(saleprice+"*"+numDom.value); //设置小计
     return subtotalDom.value;
  };
  //全选和取消全选
  function CheckAll(form)  {
     var obj = new Array();
     obj = document.getElementsByName("order");
     for(var i=0;i<obj.length;i++){
       obj[i].checked=form.chkall.checked;
       if(obj[i].checked){
         Check(i);
       }
     }
     toprice();
  };
  //复选框被选中
 function  Check(id){
   checkDom=document.getElementById("check"+id);
   checkDom.value="no";
   carid=document.getElementById("carid"+id).value;
   buymount=document.getElementById("buymount"+id).value;
   //想要提交订单字符串,用库存编号和购买量拼接,设置check的值
    if(checkDom.checked){
     checvalue=carid+","+buymount;
     checkDom.value=checvalue;
   }else{
     checkDom.value="no";
   }
   toprice();
 };
 
 function IsNum(num){
  var reNum=/^\d*$/;
  return(reNum.test(num));
};
 
 function toprice(){
   totalpriceDom=document.getElementById("totalprice");
   totalprice=0;
     var obj = new Array();
     obj = document.getElementsByName("order");
     for(var i=0;i<obj.length;i++){
       sub=subtoprice(i);
       if(obj[i].checked){
         totalprice=eval(totalprice+"+"+sub);
       }
     }
     totalpriceDom.value=totalprice;
 };
</script>
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<h3>当前位置-->购物车页面</h3>
		<div class="sh">
			<c:choose>
		   <c:when test="${empty sessionScope.car or fn:length(sessionScope.car.cartItems) eq 0 }" >
		       购物车空空的,到教材库看看吧
		    </c:when>
		<c:otherwise>
			<form action="<c:url value='/OrderServlet'/>" method="post">
			   <input type="hidden" name="method" value="add" />
				<table class="shoptr">
				   <tr>
				   <td colspan="2">
				           <input type="checkbox" name="chkall"  value="select" checked="checked" style="width:50px;height:28px;" checked="checked"  onclick="CheckAll(this.form);"/>全选</td>
				           <td colspan="4" ></td>
				           <td colspan="2" ><span><a href="<c:url value='/BuyCarServlet?method=clear'/>"  class="shtr_s"  ><input  type="button" value="清空购物车"  class="shtr_s"/></a></span></td>
				       </tr>
					<tr>
						<th class="shtrZ"></th>
						<th class="shtrO">书籍图片</th>
						<th class="shtrT">ISBN</th>
						<th class="shtrTh">书名</th>
						<th class="shtrF">单价</th>
						<th class="shtrFi">库存量</th>
						<th class="shtrSev">购买数量</th>
						<th class="shtrS">小计</th>
						<th class="shtrE">删除</th>
					</tr>
					<c:forEach items="${sessionScope.car.cartItems }" var="cartItems" begin="0" end="${fn:length(sessionScope.car.cartItems) }" varStatus="i">
					<tr>
						<td class="shtrZ">
						    <input type="checkbox" name="order" class="carcheck" value="${cartItems.carid },${cartItems.buymount }" id="check${i.index }" style="width:50px;height:28px;"checked="checked" onclick="Check(${i.index });" />
						    <input type="hidden" name="caridid"  value="${cartItems.carid }" id="carid${i.index }"/>
						</td>
						<td class="shtrO">
						     <a href="<c:url value='/TextBookServlet?method=load&sid=${cartItems.stock.sid }'/>"><img src="<c:url value='${cartItems.stock.textbook.bookpicture }'/>"  alt="暂无图书图片"/></a>
						</td>
						<td class="shtrT"><a href="<c:url value='/TextBookServlet?method=load&sid=${cartItems.stock.sid }'/>">${cartItems.stock.textbook.isbn }</a></td>
						<td class="shtrTh">${cartItems.stock.textbook.bookname }</td>
						<td class="shtrF"><input type="text" name="saleprice"value="${cartItems.stock.textbook.saleprice }"  size="5" readonly="readonly" id="saleprice${i.index}"/></td>
						<td class="shtrFi">${cartItems.stock.stocknumer }</td>
						<td class="shtrSev">
						            <input type="button" value="-" onclick="operNum(this,${i.index})" />  
                                  <input type="text" size="1" value="${cartItems.buymount }" name="buymount" id="buymount${i.index}" onchange="changeNum(${i.index});" />  
                                   <input type="button" value="+" onclick="operNum(this,${i.index});" /> 
						</td>
						<td class="shtrS" >￥<input type="text" name="subtotal"value="${cartItems.subtotal }" size="5" id="subtotal${i.index}" readonly="readonly" style="color: red;">元</td>
						<td class="shtrE">
						     <a href="<c:url value='/BuyCarServlet?method=delete&id=${cartItems.stock.textbook.id }'/>"><img src="#" class="shtrDel" /><img	src="img/del_1.png" /></a>
						 </td>
					</tr>
					</c:forEach>
					   <tr><th colspan="5"></th><td colspan="3" >共计:￥<input type="text" name="total" class="tolprice"value="${sessionScope.car.total }" class="shtr_s" disabled="disabled" size="5" id="totalprice" style="color: red;">元</td></tr>
				</table>
				<input type="submit" value="立即购买" class="shtr_s" />
			</form>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</body>
</html>
