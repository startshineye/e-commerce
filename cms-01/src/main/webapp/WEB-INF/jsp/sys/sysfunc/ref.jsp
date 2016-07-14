<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能菜单列表<%--author:高振中,DATE:2014-07-28 11:06:18--%></title>
</head>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dtree/dhtmlxtree.css">
<script src="${webPath}/resources/plugin/dtree/dhtmlxcommon.js"></script>
<script src="${webPath}/resources/plugin/dtree/dhtmlxtree.js"></script>
<body>
	<table class="titleTab">
		<tr>
			<td class="ti">功能菜单选择</td>
			<td class="bu">
				<input type="button" class="bnt" onclick="javascript:returnValue();" value="确定" />
				<input type="button" class="bnt" onclick="javascript:top.dhxWins.window('win001').close()" value="关闭" />
			</td>
		</tr>
	</table>
	<div id="treebox"></div>
</body>
<script type="text/javascript">
	treeHeight = $(top.window).height() - 190;
	tree = new dhtmlXTreeObject("treebox", "100%", treeHeight, 0);
	tree.enableCheckBoxes(1);
	tree.enableSingleRadioMode(true);
	tree.setImagePath("${webPath}/resources/plugin/dtree/imgs/");
	tree.loadXML("${webPath}/sysfunc/tree?id=1&type=${param.type}");
	setTimeout("tree.openAllItems(0)", 800);
	function returnValue() {
		if (tree.getAllChecked() == "") {
			alert("请选择结点!");
			return false;
		}
		var id = tree.getAllChecked();
		//var id = tree.getAllCheckedBranches();
		$(top.frames['center'].frames['myframe'].document).find('#${param.rvalue}').val(id);
		$(top.frames['center'].frames['myframe'].document).find('#${param.rname}').val(tree.getItemText(id));
		top.dhxWins.window("win001").close();
	}
	
</script>
</html>