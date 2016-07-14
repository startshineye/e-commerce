<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图列表</title>
</head>
<body>
	 
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
</body>
<script type="text/javascript">
	 
</script>
</html>