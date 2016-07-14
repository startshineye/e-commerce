<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户详细</title>
<%--author:GZZ,DATE:2014-02-16 00:23:30--%>
</head>
<body>
	<div class="ti">用户详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30">部门</td>
			<td>${user.org_name}</td>
		</tr>
		<tr>
			<td class="right30">登录名</td>
			<td>${user.login_id}</td>
		</tr>
		<tr>
			<td class="right30">性别</td>
			<td>${user.sex_stat}</td>
		</tr>
		<tr>
			<td class="right30">姓名</td>
			<td>${user.name}</td>
		</tr>
		<tr>
			<td class="right30">电邮</td>
			<td>${user.e_mail}</td>
		</tr>
		<tr>
			<td class="right30">身份证号</td>
			<td>${user.id_card}</td>
		</tr>
		<tr>
			<td class="right30">联系电话</td>
			<td>${user.tel}</td>
		</tr>
		<tr>
			<td class="right30">备注</td>
			<td>${user.remark}</td>
		</tr>
		<tr>
			<td class="right30">时间戳</td>
			<td><fmt:formatDate value="${user.ts}" type="both" /></td>
			<%--type取值还有date,time,中文年月日时分秒等--%>
		</tr>
	</table>
	<table class="titleTab">
			<tr>
				<td class="ti">关联角色列表</td>
				<td class="bu">
				</td>
			</tr>
	</table>
	<table class="dataTab">
			<tr>
				<th>序号</th>
				<th>用户</th>
				<th>角色</th>
				<th>时间戳</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" >${vs.count}</td>
					<td>${wz.username}</td>
					<td>${wz.rolename}</td>
					<td><fmt:formatDate value="${wz.ts}" type="both" /></td>
					<td>${wz.remark}</td>
				</tr>
			</c:forEach>
	</table>			
</body>
</html>