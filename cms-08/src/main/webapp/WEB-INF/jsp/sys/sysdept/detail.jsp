<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门详细<%--author:高振中,DATE:2014-07-29 16:49:42--%></title>
</head>
<script type="text/javascript">
	function delete_onClick() {//提交到删除方法
		var is_leaf = "${sysdept.is_leaf}";
		if (is_leaf == "0") {
			alert("非叶子节点不可删除！");
			return false;
		}
		if (!confirm("确认删除吗？")) {
			return false;
		}
		toAction("${webPath}/sysdept/delete?id=${sysdept.id}");
	}
	//选择页代码(连同下面的<参照页范例>代码同时复制到自己页面)
	function getSysDeptRef(name1, name2, path) {
		var url, winWidth, winHeight, widId;
		url = path + "/sysdept/ref?rname=" + name1 + "&rvalue=" + name2
				+ "&type=radio";
		winWidth = 500;
		winHeight = $(top.window).height() - 20;
		widId = 'win001';
		top.ShowWin('部门详细', url, path, winWidth, winHeight, widId);
	}
</script>
<body>
	<form id="form1" method="post">
		<table class="titleTab">
			<tr>
				<td class="ti">部门详细</td>
				<td class="bu"><input type="button" class="bnt"
					onclick="toAction('${webPath}/sysdept/toAdd?id=${sysdept.id}')"
					value="新增下级" /> <input type="button" class="bnt"
					onclick="toAction('${webPath}/sysdept/toUpdate?id=${sysdept.id}')"
					value="编辑" /> <input type="button" class="bnt"
					onclick="javascript:delete_onClick()" value="删除" /></td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<td class="right30_blue">名称</td>
				<td>${sysdept.name}</td>
			</tr>
			<tr>
				<td class="right30_blue">父结点</td>
				<td>${sysdept.parent_id}</td>
			</tr>
			<tr>
				<td class="right30_blue">是否叶子</td>
				<td>${sysdept.is_leaf}</td>
			</tr>
			<tr>
				<td class="right30_blue">排序编号</td>
				<td>${sysdept.order_code}</td>
			</tr>
			<tr>
				<td class="right30_blue">备注</td>
				<td>${sysdept.remark}</td>
			</tr>
		</table>
	</form>
</body>
</html>