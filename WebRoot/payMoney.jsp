<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	
	<title>支付页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="css/head.css">
	<link rel="stylesheet" type="text/css" href="css/payMoney.css">


</head>

<body>
	<%@ include file="head.txt"%>
	<div class="bb">
		<div class="pay">
			<h3>!!当前只支持支付宝支付</h3>
			<p>
				请输入支付宝账号：<input type="text" name="payID">
			</p>
			<p>
				是否开具电子发票: <input type="radio" name="isNE" value="是" class="isNE">是
				&nbsp;&nbsp; <input type="radio" name="isNE" value="否" class="isNE"
					checked="checked">否
			</p>
			<p>
				<input type="submit" class="pay_sub" />
			</p>

		</div>
	</div>
</body>
</html>
