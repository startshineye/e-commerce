<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色列表</title>
<%--author:GZZ,DATE:2014-02-15 12:59:49--%>
</head>

<body>
	<form:form name="form1" method="post" commandName="cond">
		<div class="ti">角色查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">名称</td>
				<td><form:input path="name_c" /></td>
				<td align="right">备注</td>
				<td><form:input path="remark_c" /></td>
				<td></td><td></td><td></td>
				<td>
					<input type="button" class="bnt" onclick="query_onclick()" value="查询" /> 
					<input type="button" class="bnt" onclick="query_clean()"  value="清空"/>
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">角色列表</td>
				<td class="bu">
 
					<input type="button" class="bnt" onclick="add_onclick()" value="新增" />
					<input type="button" class="bnt" onclick="delete_onclick_mu()" value="删除" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>名称</th>
				<th>备注</th>
				<th>关联用户</th>
				<th>功能授权</th>
				<th>数据授权</th>
				<th>删除</th>
				<th>修改</th>
				<th>详情</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz['id']}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['name']}</td>
					<td>${wz['remark']}</td>
					<td class="td-user" onclick="connect_user('${wz['id']}')"></td>
					<td class="td-shou" onclick="func_Au('${wz['id']}')"></td>
					<td class="td-shou" onclick="orga_Au('${wz['id']}')"></td>
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
		form1.action = "${webPath}/role/toAdd";
		form1.submit();
	}
	function delete_onclick(id) {//删除单条记录
		if (confirm('确定删除吗？')) {
			form1.action = "${webPath}/role/delete?id=" + id;
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
		form1.action = "${webPath}/role/delete?id=" + ids;
		form1.submit();
	}
	function to_update_onclick(id) {//转到修改页面
		form1.action = "${webPath}/role/toUpdate?id=" + id;
		form1.submit();
	}
	function query_onclick() {//按条件查询
		form1.action = "${webPath}/role/list";
		form1.submit();
	}
	function query_clean() {//清空查询条件
		$("#name_c").val("");
		$("#ts_c").val("");
		$("#remark_c").val("");
	}
	function detail_onclick(id) {//查看单条记录详细
		var text, url, webpath, winWidth, winHeight, widId;
		text = "";
		webpath = "${webPath}";
		url = "${webPath}/role/detail?id=" + id;
		winHeight = 550;
		winWidth = 500;
		widId = 'win001';
		top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
	}
	function orga_Au(id){//区域授权操作
		var text,url,webpath,winWidth,winHeight,widId;
		text="区域授权";
		url ='${webPath}/authority/toorgaaunew?roleid=' + id+"&rand=<%=new Random()%>";
		webpath="${webPath}";
		winWidth=600;
		winHeight = $(top.window).height()-20;
		widId='win001';
		top.ShowWin(text,url,webpath,winWidth,winHeight,widId);
	}
	function func_Au(id){//功能授权操作
		var text,url,webpath,winWidth,winHeight,widId;
		text="功能授权";
		url ='${webPath}/authority/tofuncaunew?roleid=' + id+"&rand=<%=new Random()%>";
		webpath="${webPath}";
		winWidth=600;
		winHeight = $(top.window).height()-20;
		widId='win001';
		top.ShowWin(text,url,webpath,winWidth,winHeight,widId);
	}
	function connect_user(id){//关联用户
		var text,url,webpath,winWidth,winHeight,widId;
		text="关联用户";
		url = "${webPath}/role/listuser?id=" + id+"&rand=<%=new Random()%>";
			webpath = "${webPath}";
			winWidth = 600;
			winHeight = $(top.window).height()-20;
			widId = 'win001';
			top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
	}
</script>
</html>