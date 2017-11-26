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
	<title>入库查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AstorQuery.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
	<script language="javascript" type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js'/>"></script>
	<script src="<c:url value='/leftnav.js'/>"></script>
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="sto" style="margin-left: 336px;margin-top: 75px;">
		<p>
			当前位置-->入库记录查询
		</p>
		<hr />
		<div class="findSto" style="height: 147px;">
			<p>请输入查询条件：(支持模糊查询)</p>
			<div class="group">
			  <form name="form1" action="<c:url value='/admin/AdminStoreSevrlet'/>"  method="post">
               <input type="hidden" name="method" value="findByGroup">
				<p>
					 开始时间：<input type="text"  class="Wdate" name="starttime"  onFocus="WdatePicker({readOnly:true})"  size="10" value="${select.starttime}">
               结束时间：<input type="text"  class="Wdate" name="endtime"  onFocus="WdatePicker({readOnly:true})"  size="10" value="${select.endtime}">
               入库图书isbn<input type="text"  name="isbn" value="${select.isbn }"/>
			</p>
				<p class="gr_2">
				入库图书名<input type="text" name="bookname" value="${select.bookname } "  class="find"/>
						 <input type="submit" class="sear_sub"
						value="查询" />
				</p>
               </form>
			</div>
		</div>
		<hr />
		<div class="bookSto">
			<table border="1">
				<tr>
					<th class="fbZ">入库编号</th>
					<th class="fbZ">图书ISBN</th>
					<th class="fbZ">图书名称</th>
					<th class="fbZ">图书作者</th>
					<th class="fbZ">出版社</th>
					<th class="fbZ">出版时间</th>
				<!-- 	<th class="fbZ">类别</th> -->
					<th class="fbFi">进价</th>
					<th class="fbS">定价</th>
					<th class="fbSe">售价</th>
					<th class="fbT">进货数量</th>
					<th class="fbT">进货日期</th>
					<th class="fbTh">供应商</th>
					<th class="fbF">(供)联系方式</th>
					<th class="fbT">采购人员</th>
					<th class="fbT">库存量</th>
					<th colspan="2">操作</th>
					<th colspan="2">&nbsp;</th>
				</tr>
				 <c:forEach items="${comeList }" var="cmlist">
				<tr>
				   <td class="fbZ"><a href="<c:url value='/admin/AdminStoreSevrlet?method=loadByComeid&comeid=${cmlist.comeid }'/>" >${cmlist.comeid }</a></td>
					<td class="fbZ">${cmlist.textbook.isbn}</td>
					<td class="fbO">${cmlist.textbook.bookname }</td>
					<td class="fbT">${cmlist.textbook.author  }</td>
					<td class="fbTh">${cmlist.textbook.publiser  }</td>
					<td class="fbTh">${cmlist.textbook.publishtime  }</td>
					<%-- <td class="fbF">[${cmlist.textbook.bookclass.cid  }]${cmlist.textbook.bookclass.classname  }</td> --%>
					<td class="fbFi">${cmlist.textbook.comeprice }</td>
					<td class="fbS">${cmlist.textbook.price }</td>
					<td class="fbSe">${cmlist.textbook.saleprice }</td>
					<th class="fbSe">${cmlist.comenumber }</th>
					<th class="fbT">${cmlist.comedate }</th>
					<th class="fbTh">${cmlist.suplyer }</th>
					<th class="fbF">${cmlist.suplyphone }</th>
					<th class="fbE">${cmlist.admin.aid }</th>
					<td class="fbE">${cmlist.comenumber }</td>
					<td class="fbTen"><a href="<c:url value='/admin/AdminStoreSevrlet?method=modifyComePre&comeid=${cmlist.comeid }'/>"  >修改</a></td>
					<td class="fbEle"><a href="<c:url value='/admin/AdminStoreSevrlet?method=deleteCome&comeid=${cmlist.comeid }'/>" >删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>