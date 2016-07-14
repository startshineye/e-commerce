<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据字典新增</title>
<%--author:GZZ,DATE:2014-02-15 00:11:52--%>
</head>
<body>
	<form:form name="form1" method="post" commandName="dict">
		<div class="ti">数据字典新增</div>
		<form:hidden path="type_code"/>
		<form:hidden path="type_id" />
		<table class="dataTab">
			<tr>
				<td class="right30">数据键</td>
				<td class="left30"><form:input path="data_key" maxlength="50"  /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="data_key_td"><form:errors path="data_key" /></td>
			</tr>
			<tr>
				<td class="right30">数据值</td>
				<td class="left30"><form:input path="data_value" maxlength="50"  /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="data_value_td"><form:errors path="data_value" /></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="300"  class="tarea" />
					<div id="remark_div">最大长度为300个字节</div></td>
				<td class="left30_red" id="remark_td"><form:errors path="remark" /></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="insert_onclick()" value="保存"/>
			<input type="button" class="bnt" onclick="list_back()"	value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
	$('#data_key').bind('input propertychange', function() {notBlank('data_key', '数据键');});
	$('#data_value').bind('input propertychange', function() {notBlank('data_value', '数据值');});
	$('#remark').bind('input propertychange', function() {notifyLength(this);});
	
	function insert_onclick() {//保存记录
		form1.action = "${webPath}/dict/insert";
		notBlank("data_key", "数据键");//非空验证
		notBlank("data_value", "数据值");//非空验证
		if (count > 0) {
			count = 0;
			return false;
		}
		form1.submit();
	}
	function list_back() {//返回列表
		form1.action = "${webPath}/dict/list";
		form1.submit();
	}
</script>
</html>