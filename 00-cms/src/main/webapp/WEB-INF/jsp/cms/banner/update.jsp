<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图新增<%--author:www.bjpowernode.com,DATE:2016-03-30 13:26:29--%></title>
</head>
<body>
	<form:form id="form1" method="post" modelAttribute="banner"  enctype="multipart/form-data"  >
		<form:input path="id"/>
		<form:input path="status"/>
		<form:input path="picture_path"/>
		<form:input path="picture_url"/>
		<div class="ti">轮播图新增</div>
		
		<table class="dataTab">
			<tr>
				<td class="right30">名称</td>
				<td class="left30"><form:input path="name" maxlength="255"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
			<tr>
				<td class="right30">顺序</td>
				<td class="left30"><form:input path="order_num" maxlength="10"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="order_num_td"><form:errors path="order_num" /></td>
			</tr>
			<tr>
				<td class="right30">原图片</td>
				<td class="left30"><img alt="" src="${banner.picture_url}" style="width: 200px;height: 200px"></td>
				<td class="left30_red"></td>
			</tr>
			<tr>
				<td class="right30">图片</td>
				<td class="left30"><input type="file" name="image"/><font color="#CE0000">*</font></td>
				<td class="left30_red" id="picture_path_td"><form:errors path="picture_path" /></td>
			</tr>
			<tr>
				<td class="right30">跳转地址</td>
				<td class="left30"><form:input path="jump_url" maxlength="255"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="jump_url_td"><form:errors path="jump_url" /></td>
			</tr>
			<tr>
				<td class="right30">状态</td>
				<td class="left30">${banner.status_name}</td>
				<td class="left30_red" id="jump_url_td"><form:errors path="jump_url" /></td>
			</tr>
			<tr>
				<td class="right30">备注</td>
				<td class="left30"><form:textarea path="remark" maxlength="512"  class="tarea" />
					<div id="remark_div">最大长度为512个字节</div></td>
				<td class="left30_red" id="remark_td"><form:errors path="remark" /></td>
			</tr>
			<tr>
				<td class="right30">所属终端</td>
<!-- 				spring标签有自动赋值功能 -->
				<td class="left30"><form:select path="type" maxlength="3" items="${type}"  class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="type_td"><form:errors path="type" /></td>
			</tr>
			</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="toSave();" value="保存"/>
			<input type="button" class="bnt" onclick="toAction('${webPath}/banner/list');" value="返回"/>
		</center>
	</form:form>
</body>
<script type="text/javascript">
	function toSave() {//保存记录
		checkBlank([['name', '名称'],['order_num', '顺序']]);
		if (count > 0) {
			count = 0;
			return false;
		}
		popupMasker();//弹出遮罩层防止重复提交
		toAction("${webPath}/banner/update");
	}
</script>
</html>