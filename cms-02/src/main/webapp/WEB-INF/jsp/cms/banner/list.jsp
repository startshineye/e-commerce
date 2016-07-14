<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图列表</title>
</head>
<body>
<form:form id="form1" action="" modelAttribute="cond" method="post">
<!-- 查询模块: -->
<div class="ti">轮播图查询</div>
 	<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" /></td>
				<td align="right">状态</td>
				<td><form:input path="status_c" /></td>
				<td align="right">顺序</td>
				<td><form:input path="order_num_c" /></td>
				<td align="right">终端类型</td>
				<td><form:input path="type_c" /></td>
 
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="query_onclick()" /> 
					<input type="button" class="bnt" value="清空"	onclick="query_clean()" />
				</td>
			</tr>
		</table>
		
<!-- 轮播图新增列表 -->
			 <table class="titleTab">
				<tr>
					<td class="ti">轮播图列表</td>
					<td class="bu">
						<input type="button" class="bnt" value="新增" onclick="toAction('${webPath}/banner/toinsert');" />
					</td>
				</tr>
			</table>
<!-- 后台数据显示:用于显示从controller返回的数据 -->
	 <table class="dataTab">
	 <!-- 标题显示 -->
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>名称</th>
				<th>顺序</th>
				<th>状态</th>
				<th>所属终端</th>
				<th>时间戳</th>
				<th>备注</th>
				<th>删除</th>
				<th>修改</th>
				<th>详细</th>
			</tr>
	<!-- 数据显示 -->
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz.id}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz.name}</td>
					<td>${wz.order_num}</td>
					<td>${wz.status}</td>
					<td>${wz.type}</td>
					<td><fmt:formatDate value="${wz.ts}" type="both" /></td>
					<td>${wz.remark}</td>
					<td class="td-del" onclick="delete_onclick('${wz.id}')"></td>
					<td class="td-upd" onclick="to_update_onclick('${wz.id}')"></td>
					<td class="td-det" onclick="detail_onclick('${wz.id}')"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
		</form:form>
</body>
<script type="text/javascript">
	 
</script>
</html>