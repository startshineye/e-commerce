<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图详情</title>
</head>
<body>
<div class="ti">轮播图详细</div>
	<table class="dataTab">
		<tr>
			<td class="right30">轮播图名称</td>
			<td>${banner.name}</td>
		</tr>
		<tr>
			<td class="right30">顺序</td>
			<td>${banner.order_num}</td>
		</tr>
		<tr>
			<td class="right30">存储路径</td>
			<td>${banner.picture_path}</td>
		</tr>
		<tr>
			<td class="right30">展示路径</td>
			<td>${banner.picture_url}</td>
		</tr>
		<tr>
			<td class="right30">状态</td>
			<td>${banner.status_name}</td>
		</tr>
		<tr>
			<td class="right30">所属终端</td>
			<td>${banner.type_name}</td>
		</tr>
		<tr>
			<td class="right30">时间戳</td>
			<td><fmt:formatDate value="${banner.ts}" type="both" /></td>
		</tr>
		<tr>
			<td class="right30">跳转地址</td>
			<td>${banner.jump_url}</td>
		</tr>
		<tr>
			<td class="right30">备注</td>
			<td>${banner.remark}</td>
		</tr>								 
	</table>
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