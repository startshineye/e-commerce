<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>部门列表<%--author:高振中,DATE:2014-07-29 16:49:42--%></title>
</head>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/layout.css" />
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dtree/dhtmlxtree.css">
<script src="${webPath}/resources/plugin/dtree/dhtmlxcommon.js"></script>
<script src="${webPath}/resources/plugin/dtree/dhtmlxtree.js"></script>
<body class="menubody">
	<div id="treebox" class="menu_bar"></div>
	<div class="content">
		<iframe id="myframe" width="100%" height="100%" frameborder="0"></iframe>
	</div>
</body>

<script type="text/javascript">
	var tree;
	function tonclick(id) {
		//var src = "${webPath}/sysdept/list?id=" + id;
		var src = "${webPath}/sysdept/detail?id=" + id;
		$('#myframe').attr('src', src);
	}
	tree = new dhtmlXTreeObject("treebox", "100%", "100%", 0);
	tree.setImagePath("${webPath}/resources/plugin/dtree/imgs/");
	tree.setOnClickHandler(tonclick);
	tree.loadXML("${webPath}/sysdept/tree?id=1&selectID=${id}");
	setTimeout("tree.openAllItems(0)", 400);
	$("#myframe").width($(window).width()-210);
	$(window).resize(function() {
		$("#myframe").width($(window).width()-210);
	});
</script>
</html>