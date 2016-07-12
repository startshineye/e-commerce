<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轮播图列表<%--author:www.yxm.com,DATE:2016-03-30 13:26:29--%></title>
</head>
<body>
<!-- modelAttribute的值要与跳转到此页面的controller中方法里的参数的名称一致 -->
	<form:form id="form1" method="post"  modelAttribute="cond">
		<div class="ti">轮播图查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" class="text-m" /></td>
				<td align="right">状态</td>
				<td><form:select path="status_c" items="${status}" class="text-m" /></td>	
 				<td align="right">终端类型</td>
				<td><form:select path="type_c" items="${type}" class="text-m" /></td>
				<td align="right">顺序号</td>
				<td><form:input path="order_num_c" class="text-m" /></td>
 
			</tr>
			<tr>
				<td></td>
				<td></td>	
 				<td></td>
				<td></td>
				<td></td>	<td></td>
				<td></td>
				<td>
					<input type="button" class="bnt" value="查询" onclick="toAction('${webPath}/banner/list');" /> 
					<input type="button" class="bnt" value="清空"	onclick="toClear();" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">轮播图列表</td>
				<td class="bu">
					<input type="button"  class="bnt" value="新增" onclick="toAction('${webPath}/banner/toinsert');" />
					<input type="button"  class="bnt" value="删除" onclick="toDelete(getIds());" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll();">序号</th>
				<th>名称</th>
				<th>顺序</th>
				<th>跳转地址</th>
				<th>备注</th>
				<th>状态</th>
				<th>所属终端</th>
				<th>时间戳</th>
				<th>操作</th>
				<th>删除</th>
				<th>修改</th>
				<th>详细</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz.id}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz.name}</td>
					<td>${wz.order_num}</td>
					<td>${wz.jump_url}</td>
					<td>${wz.remark}</td>
					<td>${wz.status_name}</td>
					<td>${wz.type_name}</td>
					<td><fmt:formatDate value="${wz.ts}" type="both" /></td>
					<td class="td-shou-70">
	 					<c:if test="${wz.status==1}">
							<input type="button" class="bnt" value="禁用" onclick="toAction('${webPath}/banner/updatesta?id=${wz.id}&status=0');">
						</c:if>
						<c:if test="${wz.status==0}">
							<input type="button" class="bnt" value="启用" onclick="toAction('${webPath}/banner/updatesta?id=${wz.id}&status=1');">
						</c:if>
					</td>
					<td class="td-del" onclick="toDelete('${wz.id}')"></td>
					<td class="td-upd" onclick="toAction('${webPath}/banner/toupdate?id=${wz.id}');"></td>
					<td class="td-det" onclick="toDetail('${wz.id}');"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
 
	function toDelete(ids) { 
	 	if(ids==""){
	 		alert("请选择记录!")
	 		return false;
	 	}
		toAction('${webPath}/banner/delete?id='+ids);
	}
	function toClear() {//清空查询条件
		clearCond(['name_c']);
		toAction('${webPath}/cmsadvertising/list');//清空后直接查询全部记录
	}
	function toDetail(id) {//查看单条记录详细
		top.ShowWin('详细页面','${webPath}/banner/detail?id='+id,'${webPath}',600,600,'win001');
	}
</script>
</html>