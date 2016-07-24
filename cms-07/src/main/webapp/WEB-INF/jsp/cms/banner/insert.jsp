<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图新增</title>
</head>
<body>
<form:form id="form1" action="" modelAttribute="banner" method="post">
		<div class="ti">轮播图新增</div>
		<table class="dataTab">
			<tr>
				<td class="right30">轮播图名称</td>
				<td class="left30"><form:input path="name" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
 			<tr>
				<td class="right30">顺序</td>
				<td class="left30"><form:input path="order_num" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="order_num_td"></td>
			</tr>
			<tr>
				<td class="right30">跳转地址</td>
				<td class="left30"><form:input path="jump_url" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="jump_url_td"></td>
			</tr>
			<tr>
				<td class="right30">终端类型</td>
				<td class="left30"><form:select path="type" items="${typeMap}"/></td>
				<td class="left30_red" id="type_td"></td>
			</tr>
			<tr>
			<td class="right30">ts</td>
		       <td class="left30"><form:input path="ts" format="yyyy-MM-dd HH:mm:ss" onClick="WdatePicker
               ({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="text-m" /></td>
				<td class="left30_red" id="name_td"></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark"  class="tarea"  /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="remark_td"></td>
			</tr>
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="insert_onclick();" value="保存"/>
			<input type="button" class="bnt" onclick="toAction('${webPath}/banner/list');"value="返回"/>
		</center>	 
</form:form>
</body>
<script type="text/javascript">
//实时验证
realTimeCheck([[1,"name","轮播图名称"],[1,"order_num","顺序"],[1,"jump_url","跳转地址"]]);
function insert_onclick(){
// 	notBlank("name","轮播图名称")
// 	notBlank("order_num","顺序")
// 	notBlank("jump_url","跳转地址")
	checkBlank([["name","轮播图名称"],["order_num","顺序"],["jump_url","跳转地址"]]);//非空验证
	notNumber("order_num","顺序");
	alert(count);
	if(count>0){
		count=0;
		return false;
	}
	toAction('${webPaht}/banner/insert');
}
</script>
</html>