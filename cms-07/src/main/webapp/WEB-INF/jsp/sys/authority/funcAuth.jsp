<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dtree/dhtmlxtree.css">
<script src="${webPath}/resources/plugin/dtree/dhtmlxcommon.js"></script>
<script src="${webPath}/resources/plugin/dtree/dhtmlxtree.js"></script>
<body />
<div class="ti">角色信息</div>
<table class="dataTab">
	<tr>
		<td>角色名称：</td>
		<td>${role.name}</td>
	</tr>
</table>
<center class="btn_div">
	<input type="button" class="bnt" value="确定" onclick="javascript:do_Authority('${role.id}');" />
	<input type="button" class="bnt" value="关闭" onclick="top.dhxWins.window('win001').close();" />
</center>
<div id="treebox"></div>
</body>
<script type="text/javascript">
	var tree;
	treeHeight = $(top.window).height()-190;
	tree = new dhtmlXTreeObject("treebox", "100%", treeHeight, 0);
	tree.enableCheckBoxes(1);
	tree.enableThreeStateCheckboxes(true);
	tree.setImagePath("${webPath}/resources/plugin/dtree/imgs/");
	tree.loadXML("${webPath}/sysfunc/tree?id=1&roleid=${role.id}");
	setTimeout("tree.openAllItems(0)", 1000);
	function do_Authority(roleid) {
		var menuCheckedIds = tree.getAllCheckedBranches();
		$.ajax({ //更新功能菜单授权
			type : "POST",
			url : "${webPath}/authority/doauthfunction",
			data : {
				roleid : roleid,
				checkedids : menuCheckedIds
			},
			success : function(msg) {
				//alert(msg);
				if (confirm('功能菜单已经成功授权, 关闭当前窗口吗?')) {
					parent.dhxWins.window('win001').close();
				}
			}
		});
	}
</script>
</html>