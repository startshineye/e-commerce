<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户选择列表</title>
<%--author:GZZ,DATE:2014-02-16 00:23:30--%>
</head>
<script type="text/javascript">
	function query_onclick() {//按条件查询
		form1.action = "${webPath}/user/ref?rname=${param.rname}&rvalue=${param.rvalue}";
		form1.submit();	}
	function query_clean() {//清空查询条件
		$("#organize_id_c").val("");
		$("#org_name_c").val("");
		$("#name_c").val("");
		$("#e_mail_c").val("");
		$("#id_card_c").val("");
		$("#tel_c").val("");
	}
	function return_value() {//反回值
		var id = $("input[name='chk']:checked").val();
		var ename = $("input[name='chk']:checked").attr('title');
		if (typeof (id) == "undefined") {
			alert("请选择记录!");
			return false;
		}
		$(parent.frames["center"].document).find('#${param.rvalue}').val(id);
		$(parent.frames["center"].document).find('#${param.rname}').val(ename);
		top.dhxWins.window("win001").close();
	}
</script>
<body>
	<form:form name="form1" method="post" commandName="cond">
		<div class="ti">用户查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">部门</td>
				<td><form:input path="org_name_c" /></td>
				<td align="right">登录名</td>
				<td><form:input path="login_id_c" /></td>
				<td align="right">姓名</td>
				<td><form:input path="name_c" /></td>
				<td align="right"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">身份证号</td>
				<td><form:input path="id_card_c" /></td>
				<td align="right">联系电话</td>
				<td><form:input path="tel_c" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="query_onclick()" /> 
					<input type="button" class="bnt" value="清空"	onclick="query_clean()" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">用户选择列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="确定" onclick="return_value()" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><%--<input type="checkbox" id="chkAll" onclick="checkAll()"> --%>序号</th>
				<th>部门</th>
				<th>登录名</th>
				<th>性别</th>
				<th>姓名</th>
				<th>联系电话</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="radio" name="chk" value="${wz['id']}" title="${wz['name']}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['org_name']}</td>
					<td>${wz['login_id']}</td>
					<td>${wz['sex_stat']}</td>
					<td>${wz['name']}</td>
					<td>${wz['tel']}</td>
					<td>${wz['remark']}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
</html>