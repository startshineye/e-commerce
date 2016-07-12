<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>字典类型修改</title>
<%--author:GZZ,DATE:2014-02-15 00:11:52--%>
</head>
<script type="text/javascript">
	function update_onclick() {//更新记录
		form1.action = "${webPath}/dicttype/update";
		notBlank("type_code", "类型标识");//非空验证
		notBlank("type_name", "类型名称");//非空验证
		if (count > 0) {
			count = 0;
			return false;
		}
		form1.submit();
	}
	function list_back() {//返回列表
		form1.action = "${webPath}/dicttype/list";
		form1.submit();
	}
</script>
<body>
	<form:form name="form1" method="post" commandName="dicttype">
		<div class="ti">字典类型修改</div>
		<form:hidden path="id" />
		<table class="dataTab">
			<tr>
				<td class="right30">类型标识</td>
				<td class="left30"><form:input path="type_code" maxlength="50"  /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="type_code_td"><form:errors path="type_code" /></td>
			</tr>
			<tr>
				<td class="right30">类型名称</td>
				<td class="left30"><form:input path="type_name" maxlength="50"  /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="type_name_td"><form:errors path="type_name" /></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="200"  class="tarea" />
					<div id="remark_div">最大长度为200个字节</div></td>
				<td class="left30_red" id="remark_td"><form:errors path="remark" /></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="update_onclick()" value="保存"/>
			<input type="button" class="bnt" onclick="list_back()"	value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
	$('#type_code').bind('input propertychange', function() {notBlank('type_code', '类型标识');});
	$('#type_name').bind('input propertychange', function() {notBlank('type_name', '类型名称');});
	$('#remark').bind('input propertychange', function() {notifyLength(this);});
</script>
</html>