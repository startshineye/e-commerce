<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图新增</title>
</head>
<body>
<form:form id="form1" action="" modelAttribute="cond" method="post">
		<div class="ti">轮播图新增</div>
		<table class="dataTab">
			<tr>
				<td class="right30">轮播图名称</td>
				<td class="left30"><form:input path="name" class="text-m" /><font color="#CE0000">*</font></td>
				<td class="left30_red" id="name_td"><form:errors path="name" /></td>
			</tr>
 
		</table>
		<center class="btn_div">
			<input type="button" class="bnt" onclick="insert_onclick()" value="保存"/>
			<input type="button" class="bnt" onclick="list_back()"	value="返回"/>
		</center>	 
</form:form>
</body>
<script type="text/javascript">
</script>
</html>