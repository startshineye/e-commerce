<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能菜单新增<%--author:高振中,DATE:2014-07-28 11:06:18--%></title>
</head>
<script type="text/javascript">
	function toSave() {//保存记录
		//请修改或替换如下验证方法
		checkBlank([['name', '名称']]);
		if (count > 0) {
			count = 0;
			return false;
		}
		toAction("${webPath}/sysfunc/insert");
	}
</script>
<body>
	<form:form id="form1" method="post" commandName="sysfunc">
		<div class="ti">功能菜单新增</div>
		<form:hidden path="parent_id" />
		<table class="dataTab">
			<tr>
				<td class="right30">名称</td>
				<td class="left30"><form:input path="name" maxlength="50"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
			<tr>
				<td class="right30">类型</td>
				<td class="left30">菜单 <form:radiobutton path="type" value="MENU" /> 按钮 <form:radiobutton path="type" value="BUTTON" /></td>
				<td class="left30_red" id="type_td"></td>
			</tr>
			<tr>
				<td class="right30">地址或按钮键</td>
				<td class="left30"><form:input path="url" class="text-m"  /></td>
				<td class="left30_red" id="url_td"></td>
			</tr>
			<tr>
				<td class="right30">排序编号</td>
				<td class="left30"><form:input path="order_code" maxlength="10"  class="text-m" /></td>
				<td class="left30_red" id="order_code_td"></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="200"  class="tarea" />
					<div id="remark_div">最大长度为200个字节</div></td>
				<td class="left30_red" id="remark_td"><form:errors path="remark" /></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="toSave();" value="保存"/>
			<input type="button" class="bnt" onclick="toAction('${webPath}/sysfunc/list');" value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
	//输入实时验证;1:文本框,下拉框2:日期框3:文本域
	realTimeCheck([[1,'name', '名称'],[3,'remark', '备注']]);
</script>
</html>