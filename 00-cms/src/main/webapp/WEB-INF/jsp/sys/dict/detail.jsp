<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典详细</title>
<%--author:GZZ,DATE:2014-02-15 00:11:52--%>
</head>
<body>
	<div class="ti">数据字典详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30">类型编码</td>
			<td>${dict.type_code}</td>
		</tr>
		<tr>
			<td class="right30">数据键</td>
			<td>${dict.data_key}</td>
		</tr>
		<tr>
			<td class="right30">数据值</td>
			<td>${dict.data_value}</td>
		</tr>
		<tr>
			<td class="right30">类型名称</td>
			<td>${dict.type_id}</td>
		</tr>
		<tr>
			<td class="right30">备注</td>
			<td>${dict.remark}</td>
		</tr>
	</table>
</body>
</html>