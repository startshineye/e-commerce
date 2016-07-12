<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form name="form1" method="post" commandName="user">
		<div class="ti">修改密码</div>
		<form:hidden path="id" />
		<table class="dataTab">
			<tr>
				<td class="right30">姓名</td>
				<td><form:hidden path="name" />${user.name}</td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">登录名</td>
				<td><form:hidden path="login_id" />${user.login_id}</td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">旧密码</td>
				<td><form:password path="password" /> <font color="#CE0000">*</font></td>
				<td class="left30_red"><form:errors path="password" /></td>
			</tr>
			<tr>
				<td class="right30">新密码</td>
				<td><form:password path="pw1" /> <font color="#CE0000">*</font></td>
				<td class="left30_red"><form:errors path="pw1" /></td>
			</tr>
			<tr>
				<td class="right30">新密码确认</td>
				<td><form:password path="pw2" /> <font color="#CE0000">*</font></td>
				<td class="left30_red"><form:errors path="pw2" /></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="save_onclick()" value="保存" />
		</center>
	</form:form>
</body>
<script type="text/javascript">
	function save_onclick() {
		form1.action = "${webPath}/user/editPws";
		form1.submit();
	}
	if ('${ok}' == "1") {
		alert("密码修改成功!");
	}
</script>
</html>


