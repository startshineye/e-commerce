<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
</head>
<body>
	<div class="ti">参照页示例</div>
	<table class="dataTab">
		<tr>
			<td class="right30">组织结构</td>
			<td><input id="anyname" readonly="readonly" onClick="getAreaRef('anyname','anyid','${webPath}');" class="inputref" />
			 <input id="anyid" class="text-m"/></td>
		</tr>
		<tr>
			<td class="right30">人员</td>
			<td><input id="uname" readonly="readonly" onClick="getUserRef('uname','uid','${webPath}');" class="inputref" />
			 <input id="uid" class="text-m" /></td>
		</tr>
	</table>
	<center class="btn_div">
		<input type="button"  value="示例按钮1" /> <input type="button"  value="示例按钮2" />
	</center>
</body>
</html>
