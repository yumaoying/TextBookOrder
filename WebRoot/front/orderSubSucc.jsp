<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>提交成功页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="viewport"
	content="width=device-width,user-scalable=yes">
<link rel="stylesheet" type="text/css" href="css/head.css">
<link rel="stylesheet" type="text/css" href="css/orderSubSucc.css">

</head>
<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<p class="dd_ts">
			你好，订单已经提交成功，正在审核中，请静候<br />如果你需要取消订单，请联系管理人员。请你牢记您的订单号，这是我们的唯一凭证，请在订单跟踪页面查看您所有订单状态
		</p>
		<hr style="border: 2px dashed red; opacity: 0.4;" />
		<p class="dd_no">订单编号：123456</p>
		<div class="order">
			<table class="dd_result">
				<tr>
					<th class="ddZ">书籍ISBN</th>
					<th class="ddO">图书名称</th>
					<th class="ddT">图书图片</th>
					<th class="ddTh">作者</th>
					<th class="ddF">购买数量</th>
					<th class="ddFi">购买人</th>
					<th class="ddS">购买日期</th>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
				<tr>
					<td class="ddZ">123456</td>
					<td class="ddO">算法分析与设计</td>
					<td class="ddT"><img src="#" /></td>
					<td class="ddTh">王珊</td>
					<td class="ddF">100</td>
					<td class="ddFi">me</td>
					<td class="ddS">2017-02-27</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
