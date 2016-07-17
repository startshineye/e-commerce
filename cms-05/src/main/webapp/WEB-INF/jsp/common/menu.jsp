<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dtree/dhtmlxtree.css">
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/layout.css" />
<script src="${webPath}/resources/plugin/dtree/dhtmlxcommon.js"></script>
<script src="${webPath}/resources/plugin/dtree/dhtmlxtree.js"></script>

<body class="menubody">
	<div class="menu_title"></div>
	<div id="treebox"></div>
</body>
<script language="javascript">
	var tree;
	function tonclick(id) {
		if (tree.getUserData(id, "data01") !=  "") {
			var url = "${webPath}" + tree.getUserData(id, "data01");
			$("#center", parent.document.body).attr("src", url);
		}
	}
	tree = new dhtmlXTreeObject("treebox", "100%", "100%", 0);
	tree.setImagePath("${webPath}/resources/plugin/dtree/imgs/");
	tree.setOnClickHandler(tonclick);
	tree.loadXML("${webPath}/sysfunc/tree?id=1&selectID=${selectID}&ftype=MENU");
	setTimeout("tree.openAllItems(0)", 400);
 	$("#treebox").height(top.document.documentElement.clientHeight - 40-110-4);
	$(window).resize(function() {
		$("#treebox").height(top.document.documentElement.clientHeight -40-110-4);
	}); 
</script>
</html>