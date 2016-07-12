<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CMS管理系统</title>
<style>html,body{height:100%}html{overflow-y:auto}</style>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/layout.css" />
<link rel="icon" href="${webPath}/resources/img/favicon.ico" />
<link rel="shortcut icon" href="${webPath}/resources/img/favicon.ico" />
<link rel="bookmark" href="${webPath}/resources/img/favicon.ico" />
<%--引入dhtmlwidows --%>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dwindows/dhtmlxwindows.css">
<link rel="stylesheet" type="text/css" href="${webPath}/resources/plugin/dwindows/skins/dhtmlxwindows_dhx_skyblue.css">
<script language="javascript" src="${webPath}/resources/plugin/dwindows/dhtmlxcommon.js"></script>
<script language="javascript" src="${webPath}/resources/plugin/dwindows/dhtmlxwindows.js"></script>
<script language="javascript" src="${webPath}/resources/plugin/dwindows/dhtmlxcontainer.js"></script>
</head>
<body class="mainbody">
		<div class="header" id="header"></div>
		<div class="container" id="container">
			<div class="menu_bar" id="menu_bar">
				<iframe id="tree_iframe" src="${webPath}/common/menu" width="100%" frameborder="0"></iframe>
			</div>
		<div class=buttonbar id="b-line" onmouseout="backout()" onmouseover="backvoer()" onclick="show_hide();"></div>
		<div class="content" id="content">
			<iframe id="center" name="center" width="100%" frameborder="0"></iframe>
		</div>
	</div>
</body>
<script language="javascript">
	//窗口高度自动适应不同分辨率.作者：gzz
	$("#header").load("${webPath}/common/top");

	//隐藏侧边菜单栏.作者：gzz
	var sideWidth = $("#menu_bar").width();
	function show_hide() {//隐藏侧边栏
		if ($("#menu_bar").width() == 0) {
			$("#menu_bar").width(sideWidth);
			$("#content").css("margin-left", sideWidth + $("#b-line").width());
			$("#b-line").css("background-image", "url(${webPath}/resources/img/main/left.png)");
		} else {
			$("#menu_bar").width(0);
			$("#content").css("margin-left", $("#b-line").width());
			$("#b-line").css("background-image", "url(${webPath}/resources/img/main/right.png)");
		}
	}
	function backout() {
		$("#b-line").css("background-color", "#e6e6fa");
	}
	function backvoer() {
		$("#b-line").css("background-color", "#d0d0d0");
	}

	function winResize() {
		//窗口高度自动适应不同分辨率.作者：gzz
		var pageHgight = top.document.documentElement.clientHeight - 110 -4;
		$("#center").height(pageHgight);
		$("#tree_iframe").height(pageHgight);
		$("#container").height(pageHgight);
	}
	$(window).resize(function() {
		winResize();
	});
	$(document).ready(function() {
		winResize();
	});
</script>
</html>
