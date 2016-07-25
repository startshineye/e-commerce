<%@ page language="java" pageEncoding="UTF-8"%>
<% request.setAttribute("webPath", request.getContextPath());%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>xxx商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${webPath}/resources/css/style.css" />
<link rel="stylesheet" href="${webPath}/resources/css/luara.top.css" />
<script src="${webPath}/resources/js/jquery-1.8.3.min.js"></script>
<script src="${webPath}/resources/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
	<div class="com_head">
		<table>
			<tr>
				<td class="td150" onclick="goHome()">首页</td>
				<td class="td150" onclick="goHome()">商家中心</td>
				<td class="td150" onclick="goHome()">平台政策</td>
				<td class="td150" onclick="goHome()">手机版</td>
				<td class="td150" onclick="goHome()">个人设置</td>
				<td class="td150" onclick="goHome()">我的订单</td>
				<td class="td150" onclick="goHome()">购物车</td>
				<td class="td150" onclick="goHome()">登录</td>
				<td class="td150" onclick="goHome()">注册</td>
			</tr>
		</table>
	</div>
	<div class="com_banner">
		<%-- <ul>
			<c:forEach items="${bannerList}" var="banner" varStatus="vs" >
				<li><a target="_blank" href="${banner.jump_url}"><img src="${banner.picture_url}" alt="${vs.count}" /></a></li>
			</c:forEach>
		</ul>
		<ol>
			<c:forEach items="${bannerList}">
				<li></li>
			</c:forEach>
		</ol> --%>
		<ul>
			<li><img src="${webPath}/resources/img/e6efc32e70a94924a3bc15a4107d0d49.jpg" alt="1" /></li>
			<li><img src="${webPath}/resources/img/300bcc5885fb4b7692d36f4d33b427b2.jpg" alt="2" /></li>
			<li><img src="${webPath}/resources/img/bbfdea94089b4af9beb896c4f0be45d0.jpg" alt="3" /></li>
			<li><img src="${webPath}/resources/img/0cd0f3c63b6b4306893406a27cc23e5a.jpg" alt="4" /></li>
			<li><img src="${webPath}/resources/img/ef73d2204a334d5a8bb5a3a183ba4a31.jpg" alt="5" /></li>
			<li><img src="${webPath}/resources/img/24179954b9554ebd8dba74b07ef46650.jpg" alt="6" /></li>
		</ul>
		<ol>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ol>
	</div>
	<script>
		$(function() {
			<!--调用Luara示例-->
			$(".com_banner").luara({
				width : "1200",
				height : "380",
				interval : 5000,
				selected : "seleted",
				deriction : "top"
			});
		});
	</script>
	<div class="com_point">
		<ul>
			<li><i><img src="${webPath}/resources/img/1.png"></i><span>直连一线制造 </span></li>
			<li><i><img src="${webPath}/resources/img/2.png"></i><span>7天无忧退换货</span></li>
			<li><i><img src="${webPath}/resources/img/3.png"></i><span>平台先行赔付</span></li>
			<li><i><img src="${webPath}/resources/img/4.png"></i><span>全品类包邮</span></li>
		</ul>
	</div>
	<div class="com_ad">
		<a target="_blank" href=""> <img src="${webPath}/resources/img/7bfb6cf88de14c8ba87dae3eb1243776.jpg">
		</a> <a target="_blank" href=""> <img src="${webPath}/resources/img/cd673ec6691848ef90b1bae70d79c7b0.jpg">
		</a> <a target="_blank" href=""> <img src="${webPath}/resources/img/7bfb6cf88de14c8ba87dae3eb1243776.jpg">
		</a> <a target="_blank" href=""> <img src="${webPath}/resources/img/a1e41f08fa774dbe98e4ad5253c476dd.jpg">
		</a> <a target="_blank" href=""> <img src="${webPath}/resources/img/6f6ae1c918774532b7c0cc30be868679.jpg">
		</a> <a target="_blank" href=""> <img src="${webPath}/resources/img/8cca8c3635d1420d8d3971af07a84c1d.jpg">
		</a>
	</div>
	<div class="com_footer">
		<table align="center">
			<tr>
				<td class="td150" onclick="goHome()">关于xxx</td>
				<td class="td150" onclick="goHome()">加入xxx</td>
				<td class="td150" onclick="goHome()">联系我们</td>
				<td class="td150" onclick="goHome()">官方微博</td>
			</tr>
		</table>
		<p>
			◎POWERNODE.COM 2016 版权所有 <br>
		</p>
	</div>
</body>
<script>
	function goHome() {
		window.open("http://www.yxm.com");
	}
</script>
</html>