<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/layout.css" />
<div align="right" class="pagediv">
	<div>
		<c:if test="${cond.curPage>1}">
			<span class="lastPage" onclick="firstPage()" title="首页">&nbsp;</span>
			<span class="nextPage" onclick="previousPage()" title="上一页">&nbsp;</span>
		</c:if>
		<c:if test="${cond.pageCount>1&&cond.curPage!=cond.pageCount}">
			<span class="firstpage" onclick="nextPage()" title="下一页">&nbsp;</span>
			<span class="previousPage" onclick="lastPage()" title="末页">&nbsp;</span>
		</c:if>
		共${cond.rowCount}条/${cond.pageCount}页&nbsp;&nbsp;
		每页<select name="pageSize"  id="pageSize" class="pageSize"  onchange="goto_Page();"></select> 条&nbsp;&nbsp;
		第<input type="text" value="${cond.curPage}" name="curPage" id="curPage"  class="pageSize"  />页
		<c:if test="${cond.pageCount>1}">
			<span class="gopage" onclick="goto_Page()" title="转到">&nbsp;</span>
		</c:if>
	</div>
</div>
<script type="text/javascript">
	var curPage = "${cond.curPage}";
	var pageCount = "${cond.pageCount}";
	function firstPage() {
		$("#curPage").val(1);
		$("#form1").submit();
	}
	function lastPage() {
		$("#curPage").val(pageCount);
		$("#form1").submit();
	}
	function previousPage() {
		if (curPage > 1) {
			$("#curPage").val(parseInt(curPage) - 1);
		}
		$("#form1").submit();
	}
	function nextPage() {
		if (parseInt(curPage) < parseInt(pageCount)) {
			$("#curPage").val(parseInt(curPage) + 1);
		}
		$("#form1").submit();
	}
	function goto_Page() {
		var p = parseInt($("#curPage").val());
		if (isNaN(p) || p<1 || p>pageCount) {
			$("#curPage").val(1);
		} else {
			$("#curPage").val(p);
		}
		$("#form1").submit();
	}
	$(document).ready(function() {
		$(function() {
			$("#curPage").bind('keypress', function(event) {
				if (event.keyCode == "13") {
					goto_Page();
				}
			});
		});
		for(var i=10;i<41;i=i+5){
			$("#pageSize").append($("<option/>").text(i).val(i));
		}
		$("#pageSize").val('${cond.pageSize}');
	});
</script>