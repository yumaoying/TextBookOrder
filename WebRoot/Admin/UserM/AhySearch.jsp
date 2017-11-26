<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>会员查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="viewport" content="width=device-width,user-scalable=yes">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/AhySearch.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Admin/AdminCSS/Aleft_nav.css'/>">
</head>
<body>
	<%@ include file="../Aleft_nav.txt"%>
	<div class="bb">
		<div class="bFtea">
			<p>
				当前位置-->会员信息查询 <a href="<c:url value='/Admin/UserM/AhyAdd.jsp'/>">添加会员</a>
			</p>
			<hr />
			<div class="searHy">
			<form action="<c:url value='/admin/MemberServlet' />" method="post">
      <input type="hidden" name="method"  value="findUserGroup"/>
				<p>
					请输入查询条件：<input type="text"  name="userid"  class="ahyTJ"/>
					<select name="lookup" >
                               <option value="0"></option>
                               <option value="1">&nbsp;&nbsp;&nbsp;&nbsp;所有用户&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                <option value="2">&nbsp;&nbsp;&nbsp;&nbsp;所有老师&nbsp;&nbsp;&nbsp;&nbsp;</option>
                              <option value="3">&nbsp;&nbsp;&nbsp;&nbsp;所有学生&nbsp;&nbsp;&nbsp;&nbsp;</option>
                               </select>
						<input type="submit" class="sub" value="提交"></input>
				</p>
				<div class="group">
					<input type="radio" name="find" class="findOne"  value="1"/>用户账户
					<input type="radio" name="find" class="find"  value="2"/>真实姓名 
					<input type="radio"	name="find" class="find" value="3" />联系方式 
					<input type="radio" name="find"	class="find"  value="4"/>模糊查询 
				</div>
			
				</form>
			</div>
			<hr />
			<div class="hyRes">
			 <c:if test="${ empty userlist or fn:length(userlist)<=0}">
                      当前未查找到数据
                </c:if>
                <c:if test="${fn:length(userlist)>0}">
				<table border="1">
					<tr>
						<th class="hyZ">工号</th>
						<th class="hyO">用户姓名</th>
						<th class="hyT">密码</th>
						<th class="hyTh">联系方式(邮箱)</th>
						<th class="hyF">用户身份</th>
						<th class="hyF">学校</th>
						<th colspan="2">用户设置</th>
					</tr>
					 <c:forEach items="${userlist }" var="ul" >
					<tr>
						<td class="hyZ">${ul.userid }</td>
						<td class="hyO">${ul.username }</td>
						<td class="hyT">${ul.userpwd }</td>
						<td class="hyTh">${ul.email }</td>
						<td class="hyF">   
						<input type="radio" name="idennty${ul.uid }" value="0" <c:if test="${ul.idennty==0 }">checked</c:if> readonly="readonly">学生
                         <input type="radio" name="idennty${ul.uid }" value="1" <c:if test="${ul.idennty==1 }">checked</c:if> readonly="readonly" > 老师
                         </td>
                         <td class="hyF">   ${ul.school } </td>
						<td class="hyFi"><a href="<c:url value='/admin/MemberServlet?method=modifyPre&userid=${ul.userid }'/>" >修改</a></td>
						<td class="hyS"><a href="<c:url value='/admin/MemberServlet?method=delete&uid=${ul.uid }'/>"  onclick="return confirm('删除后不可恢复，确认删除吗？')">删除</a></td>
					</tr>
					</c:forEach>
				</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>