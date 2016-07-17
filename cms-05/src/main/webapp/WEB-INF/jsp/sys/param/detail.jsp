<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统参数详细</title>
<%--author:GZZ,DATE:2014-02-15 11:09:23--%>
</head>
<body>
	<div class="ti">系统参数详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30">参数名称</td>
			<td>${paramObj.name}</td>
		</tr>
		<tr>
			<td class="right30">参数键</td>
			<td>${paramObj.param_key}</td>
		</tr>
		<tr>
			<td class="right30">备注</td>
			<td>${paramObj.remark}</td>
		</tr>
		<tr>
			<td class="right30">参数值</td>
			<td>${paramObj.param_value}</td>
		</tr>
	</table>
</body>
</html>