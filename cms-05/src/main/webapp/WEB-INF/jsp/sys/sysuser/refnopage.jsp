<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>关联用户</title>
<script type="text/javascript">
	function do_Authority(roleid) {
		var ids = "";
		$(":checkbox[name='chk']").each(function() {
			if ($(this).attr("checked")) {
				ids += "," + $(this).attr("value");
			}
		});
		$.ajax({ //更新组织结构授权
			type : "POST",
			url : "${webPath}/roleuser/doconnect",
			data : {
				roleid : roleid,
				userids : ids
			},
			success : function(msg) {
				if (confirm('数据已经成功授权, 关闭当前窗口吗?')) {
					parent.dhxWins.window('win001').close();
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="ti">角色信息</div>
	<table class="dataTab">
		<tr>
			<td>角色名称：</td>
			<td>${role.name}</td>
		</tr>
	</table>
	<center class="btn_div" >
		<input type="button" class="bnt" value="确定" onclick="do_Authority('${role.id}');" />
		<input type="button" class="bnt" value="关闭" onclick="top.dhxWins.window('win001').close();" />
	</center>
	<div class="ti">用户列表</div>
	<table class="dataTab">
		<tr>
			<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
			<th>姓名</th>
			<th>登录名</th>
			<th>性别</th>
			<th>部门</th>
			<th>电话</th>
		</tr>
		<c:forEach items="${dataList}" var="wz" varStatus="vs">
			<tr>
				<td class="sqe_w">${vs.count}<c:if test="${wz['id']!= '1'}">
						<%--管理员不显示复选框--%>
						<input type="checkbox" name="chk" value="${wz['id']}"
							<c:if test="${!empty wz['u_id']}"> <%--以关联的用户口打上钩--%>
								checked="checked"
							</c:if> />
					</c:if></td>
				<td>${wz['NAME']}</td>
				<td>${wz['LOGIN_ID']}</td>
				<td>${wz['SEX_STAT']}</td>
				<td>${wz['ORA_NAME']}</td>
				<td>${wz['TEL']}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
