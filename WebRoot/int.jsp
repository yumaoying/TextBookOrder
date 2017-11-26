<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<script src="<c:url value='/Admin/Aleftnav.js'/>"></script>
	</head>
	<body>
		<div class="btop">
			<img src="<c:url value='/img/logo.png'/>"/>
			<h3>教材订购后台管理系统</h3>
			<div class="bt_right">
				<ul>
					<li><a href="<c:url value='/Admin/AIndex.jsp'/>">后台首页</a></li>
					<li>欢迎登录,<a href="<c:url value='/login.jsp'/>">${admin.userid}(${admin.username})</a></li>
					<li><a href="<c:url value='/UserServlet?method=quit'/>">退出</a></li>
				</ul>
			</div>
		</div>
		<div class="bleft_nav">
			<ul id="tree">
				<li>用户管理
					<ul>
						<li><a href="<c:url value='/Admin/UserM/AhySearch.jsp '/>">会员管理</a></li>
						<li><a href="<c:url value='/Admin/UserM/AadminSearch.jsp'/>">管理员管理</a></li>
					</ul>
				</li>
				<li>书籍管理
					<ul>
						<li><a href="<c:url value='/admin/AdminTextBookServlet?method=findAll'/>">书籍查询</a></li>
						<li><a href="<c:url value='/admin/AdminTextBookServlet?method=addPre'/>">书籍添加</a></li>
						<li><a href="<c:url value='/admin/AdminBookClassServlet?method=findAll' />">分类管理</a></li>
					</ul>
				</li>
				<li>订单管理
					<ul>
						<li><a href="#">订单查询</a></li>
						<li><a href="#">审核</a></li>
					</ul>
				</li>
				<li>书单管理
					<ul>
						<li><a href="#">临时缺书单</a></li>
						<li><a href="#">所有缺书单</a></li>
						<li><a href="#">购书单</a></li>
						<li><a href="#">领书单管理</a></li>
						<li><a href="#">临时出库表</a></li>
						<li><a href="#">库存表</a></li>
					</ul>
				</li>
				<li>库存管理
					<ul>
						<li><a href="<c:url value='/admin/AdminStoreSevrlet?method=stockPre' />">库存查询</a></li>
						<li><a href="<c:url value='/admin/AdminStoreSevrlet?method=comePre' />">书籍入库</a></li>
						<li><a href="<c:url value='/Admin/StorageM/ArkQuery.jsp'/>">入库查询</a></li>
						<li><a href="<c:url value='/admin/AdminOutServlet?method=outPre' />">登记出库</a></li>
						<li><a href="<c:url value='/Admin/StorageM/AckQuery.jsp'/>">出库查询</a></li>
						<li><a href="<c:url value='/admin/AdminStoreSevrlet?method=findAllCome' />">进货通知</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>