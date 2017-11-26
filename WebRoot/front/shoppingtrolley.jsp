<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	
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
</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<h3>当前位置-->购物车页面</h3>
		<div class="sh">
			<form>
				<table class="shoptr">
					<tr>
						<th class="shtrZ"></th>
						<th class="shtrO">书籍图片</th>
						<th class="shtrT">ISBN</th>
						<th class="shtrTh">书名</th>
						<th class="shtrF">作者</th>
						<th class="shtrFi">出版社</th>
						<th class="shtrS">库存数量</th>
						<th class="shtrSev">购买数量</th>
						<th class="shtrE">删除</th>
					</tr>
					<tr>
						<td class="shtrZ"><input type="checkbox" /></td>
						<td class="shtrO"><img src="#" alt="暂无" /></td>
						<td class="shtrT"><a href="bookDetail.jsp">123456789</a></td>
						<td class="shtrTh">数据库原理</td>
						<td class="shtrF">胡可</td>
						<td class="shtrFi">中华出版社</td>
						<td class="shtrS">2000</td>
						<td class="shtrSev"><input type="text" name=""/></td>
						<td class="shtrE"><a href="#" class="shtrDel"><img
								src="img/del_1.png" /></a></td>
					</tr>
					<tr>
						<td class="shtrZ"><input type="checkbox" /></td>
						<td class="shtrO"><img src="#" alt="暂无" /></td>
						<td class="shtrT"><a href="bookDetail.jsp">123456789</a></td>
						<td class="shtrTh">数据库原理</td>
						<td class="shtrF">胡可</td>
						<td class="shtrFi">中华出版社</td>
						<td class="shtrS">2000</td>
						<td class="shtrSev"><input type="text" name=""/></td>
						<td class="shtrE"><a href="#" class="shtrDel"><img
								src="img/del_1.png" /></a></td>
					</tr>
					<tr>
						<td class="shtrZ"><input type="checkbox" /></td>
						<td class="shtrO"><img src="#" alt="暂无" /></td>
						<td class="shtrT"><a href="bookDetail.jsp">123456789</a></td>
						<td class="shtrTh">数据库原理</td>
						<td class="shtrF">胡可</td>
						<td class="shtrFi">中华出版社</td>
						<td class="shtrS">2000</td>
						<td class="shtrSev"><input type="text" name=""/></td>
						<td class="shtrE"><a href="#" class="shtrDel"><img
								src="img/del_1.png" /></a></td>
					</tr>
				</table>
				
				<input type="submit" value="立即购买" class="shtr_s" />
			</form>
		</div>
	</div>
</body>
</html>
