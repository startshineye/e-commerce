<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<%--author:GZZ,DATE:2014-02-16 00:23:30--%>
</head>

<body>
	<form:form name="form1" method="post" commandName="cond">
		<div class="ti">用户查询</div>
		<table class="SearchTab">
			<tr>
				<td align="right">部门</td>
				<td><form:input path="org_name_c" /></td>
				<td align="right">登录名</td>
				<td><form:input path="login_id_c" /></td>
				<td align="right">姓名</td>
				<td><form:input path="name_c" /></td>
				<td align="right">联系电话</td>
				<td><form:input path="tel_c" /></td>
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
					<input type="button" class="bnt" value="清空" onclick="query_clean()" />
				</td>
			</tr>
		</table>
		<table class="titleTab">
			<tr>
				<td class="ti">用户列表</td>
				<td class="bu">
					<input type="button" class="bnt" value="新增" onclick="add_onclick()" />
					<input type="button" class="bnt" value="删除" onclick="delete_onclick_mu()" />
				</td>
			</tr>
		</table>
		<table class="dataTab">
			<tr>
				<th><input type="checkbox" id="chkAll" onclick="checkAll()">序号</th>
				<th>部门</th>
				<th>登录名</th>
				<th>性别</th>
				<th>姓名</th>
 				<th>联系电话</th>
				<th>备注</th>
				<th>删除</th>
				<th>修改</th>
				<th>重置密码</th>
				<th>详情</th>
			</tr>
			<c:forEach items="${dataList}" var="wz" varStatus="vs">
				<tr>
					<td class="sqe_w" ><input type="checkbox" name="chk" value="${wz['id']}" /> <u:sequence index="${vs.count}" /></td>
					<td>${wz['org_name']}</td>
					<td>${wz['login_id']}</td>
					<td>${wz['sex_stat']}</td>
					<td>${wz['name']}</td>
					<td>${wz['tel']}</td>
					<td>${wz['remark']}</td>
					<td class="td-del" onclick="delete_onclick('${wz['id']}')"></td>
					<td class="td-upd" onclick="to_update_onclick('${wz['id']}')"></td>
					<td class="td-user" onclick="update_pas('${wz['id']}')"></td>
					<td class="td-det" onclick="detail_onclick('${wz['id']}')"></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/WEB-INF/jsp/page.jsp" />
	</form:form>
</body>
<script type="text/javascript">
	function add_onclick() {//转到新增页面
		form1.action = "${webPath}/user/toAdd";
		form1.submit();
	}
	
	function tree_onclick() {//转到新增页面
		var text, url, webpath, winWidth, winHeight, widId;
		text = "";
		webpath = "${webPath}";
		url = "${webPath}/systype/totree";
		winHeight = 450;
		winWidth = 700;
		widId = 'win001';
		top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
	}
	
	function delete_onclick(id) {//删除单条记录
		if (confirm('确定删除吗？')) {
			form1.action = "${webPath}/user/delete?id=" + id;
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
		form1.action = "${webPath}/user/delete?id=" + ids;
		form1.submit();
	}
	function to_update_onclick(id) {//转到修改页面
		form1.action = "${webPath}/user/toUpdate?id=" + id;
		form1.submit();
	}
	function query_onclick() {//按条件查询
		form1.action = "${webPath}/user/list";
		form1.submit();
	}
	function query_clean() {//清空查询条件
		$("#organize_id_c").val("");
		$("#org_name_c").val("");
		$("#name_c").val("");
		$("#e_mail_c").val("");
		$("#id_card_c").val("");
		$("#tel_c").val("");
		$("#login_id_c").val("");
		
	}
	function detail_onclick(id) {//查看单条记录详细
		var text, url, webpath, winWidth, winHeight, widId;
		text = "";
		webpath = "${webPath}";
		url = "${webPath}/user/detail?id=" + id;
		winHeight = 550;
		winWidth = 500;
		widId = 'win001';
		top.ShowWin(text, url, webpath, winWidth, winHeight, widId);
	}
	function update_pas(id) {//更新密码
		$.ajax({
	        url: "${webPath}/user/updatePas",
	        data: {id:id},
	        success: function (data) {
	        	if (data == "1"){
 					alert("该用户密码已重置为[系统参数]中的默认密码!");
	        	} 	
	        }
	    });
	}
</script>
</html>