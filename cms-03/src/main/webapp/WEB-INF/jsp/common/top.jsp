<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/layout.css" />
<body class="top_body">
	<table class="toptab">
		<tr>
			<td><div class="logo_div"></div></td>
			<td class="title_td"></td>
			<td><div class="left_div"></div></td>
		</tr>
	</table>
	<div class="top_foot_out">
		<div class="top_foot">
			<span class="timediv">时间: <span id="mtime"></span> &nbsp;&nbsp;你好!${sessionScope.LOGIN_USER.name} &nbsp;&nbsp;
			</span> <span class="logout" onclick="logout()" title="退出">&nbsp;</span>&nbsp;&nbsp; &nbsp;&nbsp;
		</div>
	</div>
</body>
<script language="javascript">
	function get_time() {
		$("#mtime").html(new Date().toLocaleString());
	}
	get_time();
	window.setInterval("get_time()", 1000);
	function logout() {
		top.location.href = "${webPath}/user/userlogout";
	}
</script>
</html>
