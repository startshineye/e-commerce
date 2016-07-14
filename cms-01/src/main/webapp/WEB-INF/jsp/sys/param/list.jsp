<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统参数列表</title>
<%--author:GZZ,DATE:2014-02-15 11:09:23--%>
</head>
<body>
	<form:form name="form1" id="form1" method="post"  modelAttribute="cond">
		<div class="ti">系统参数查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">参数名称</td>
				<td><form:input path="name_c" /></td>
				<td align="right">参数键</td>
				<td><form:input path="param_key_c" /></td>
				<td align="right">备注</td>
				<td><form:input path="remark_c" /></td>
				<td align="right">参数值</td>
				<td><form:input path="param_value_c" /></td>
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
		<table class="titleTab">
			<tr>
				<td class="ti">系统参数列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="新增" onclick="add_onclick()" />
					<input type="button" class="bnt" value="删除" onclick="delete_onclick_mu()" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>参数名称</th>
				<th>参数键</th>
				<th>参数值</th>
				<th>备注</th>
				<th>删除</th>
				<th>修改</th>
				<th>详情</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz['id']}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['name']}</td>
					<td>${wz['param_key']}</td>
					<td>${wz['param_value']}</td>
					<td>${wz['remark']}</td>
					<td class="td-del" onclick="delete_onclick('${wz['id']}')"></td>
					<td class="td-upd" onclick="to_update_onclick('${wz['id']}')"></td>
					<td class="td-det" onclick="detail_onclick('${wz['id']}')"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
	function add_onclick() {//转到新增页面
		form1.action = "${webPath}/param/toAdd";
		form1.submit();
	}
	function delete_onclick(id) {//删除单条记录
		if (confirm('确定删除吗？')) {
			form1.action = "${webPath}/param/delete?id=" + id;
			form1.submit();
		}
	}
	function delete_onclick_mu() {//删除多条记录
		var ids = "";
		var idsObj = $(":checkbox[name='chk']:checked").each(function() {
			ids += $(this).val() + ",";
		});
		if (idsObj.length == 0) {
			alert("请选择记录！");
			return;
		}
		if (!confirm("确定删除这些数据吗？")) {
			return;
		}
		form1.action = "${webPath}/param/delete?id=" + ids;
		form1.submit();
	}
	function to_update_onclick(id) {//转到修改页面
		form1.action = "${webPath}/param/toUpdate?id=" + id;
		form1.submit();
	}
	function query_onclick() {//按条件查询
		form1.action = "${webPath}/param/list";
		form1.submit();
	}
	function query_clean() {//清空查询条件
		$("#name_c").val("");
		$("#param_key_c").val("");
		$("#remark_c").val("");
		$("#ts_c").val("");
		$("#param_value_c").val("");
		
		//$("input:radio[name='usable_c']").attr("checked",false);
	}
	function detail_onclick(id) {//查看单条记录详细
		var text, url, webpath, winWidth, winHeight, widId;
		text = "";
		webpath = "${webPath}";
		url = "${webPath}/param/detail?id=" + id;
		winHeight = 550;
		winWidth = 500;
		widId = 'win001';
		top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
	}
 
</script>
</html>