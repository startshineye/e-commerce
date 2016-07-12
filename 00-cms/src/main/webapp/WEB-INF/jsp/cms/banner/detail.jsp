<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图详细<%--author:www.bjpowernode.com,DATE:2016-03-30 13:26:29--%></title>
</head>
<body>
	<div class="ti">轮播图新增</div>
	<table class="dataTab">
		<tr>
			<td class="right30">名称</td>
			<td class="left30">${banner.name}</td>
		</tr>
		<tr>
			<td class="right30">顺序</td>
			<td class="left30"></td>
		</tr>
		<tr>
			<td class="right30">图片</td>
			<td class="left30"><img alt="" src="${banner.picture_url}" style="width: 200px;height: 200px"></td>
		</tr>
		<tr>
			<td class="right30">跳转地址</td>
			<td class="left30"></td>
		</tr>
		<tr>
			<td class="right30">注态</td>
			<td class="left30">${banner.status_name}</td>
		</tr>		
		<tr>
			<td class="right30">备注</td>
			<td class="left30"></td>
		</tr>
		<tr>
			<td class="right30">所属终端</td>
			<td class="left30">${banner.type_name}</td>
		</tr>
		</table>
</body>
</html>