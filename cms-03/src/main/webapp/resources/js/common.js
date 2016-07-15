//用来存放公用多次使用的JS代码
/*******************************************************************************
 * (作者:GZZ)通用弹出窗口方法
 ******************************************************************************/
var dhxWins;
function ShowWin(text, url, webpath, winWidth, winHeight, widId) {
	dhxWins = new dhtmlXWindows();
	dhxWins.setImagePath(webpath + "/resources/plugin/dwindows/imgs/");
	var left = (top.document.body.clientWidth - winWidth) / 2;
	var top1 = (top.document.body.clientHeight - winHeight) / 2;
	var w1 = dhxWins.createWindow(widId, left, top1, winWidth, winHeight);
	w1.setText(text);
	w1.setModal(true);
	w1.attachURL(url);
}
/*******************************************************************************
 * (作者:GZZ)当页面加载时调用表格隔行变色方法
 ******************************************************************************/
window.onload = function() {
	TablesTrColor();
};
/*******************************************************************************
 * (作者:GZZ)表格隔行变色方法
 ******************************************************************************/
function TablesTrColor() {
	// table 中设置class属性[class= dataTab]
	$("table[class=dataTab]").each(function() {
		var _this = $(this);
		// 设置偶数行和奇数行颜色
		_this.find("tr:even").css("background-color", "#e0e0e0");
		_this.find("tr:odd").css("background-color", "#f0f0f0");
		// 鼠标移动隔行变色hover用法关键
		_this.find("tr").hover(function() {
			$(this).attr("bColor", $(this).css("background-color")).css("background-color", "#bebebe");// .css("cursor",
			// "pointer")
		}, function() {
			$(this).css("background-color", $(this).attr("bColor"));
		});
	});
}
/*******************************************************************************
 * (作者:GZZ)复选框选中全部
 ******************************************************************************/
function checkAll() {
	$(":checkbox[name='chk']").each(function() {
		this.checked=document.getElementById("chkAll").checked;
	});
}
/*******************************************************************************
 * 作者:TY
 ******************************************************************************/
(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	};
})(jQuery);

/*******************************************************************************
 * (作者:GZZ)限制输入框的长度*
 ******************************************************************************/
var matchWords = 0;
function notifyLength(obj) {
	var realLen = $(obj).val().replace(/[^\x00-\xff]/g, "**").length;
	var maxLen = $(obj).attr("maxlength");
	var spanName = $(obj).attr("name") + "_div";
	if (realLen <= maxLen) {
		matchWords = $(obj).val().length;
		var remain = maxLen - realLen;
		$("#" + spanName).html("还可以输入<font color=\"#CE0000\">" + remain + "</font>字母或<font color=\"#CE0000\">" + (Math.round(remain / 2 - 0.5)) + "</font>汉字");
	} else {
		alert("超过最大长度,请您调整,超出部分将被截掉");
		$(obj).val($(obj).val().substring(0, matchWords));
	}
}
/*******************************************************************************
 * (作者:GZZ)默认的非空验证方法
 ******************************************************************************/
var count = 0;
function notBlank(id, name) {
	$("#" + id + "_td").html("");
	if ($("#" + id).val() == "") {
		$("#" + id + "_td").html("["+name + "]不能为空!");
		count++;
	}
}
/*******************************************************************************
 * (作者:yxm)默认的数字验证方法
 ******************************************************************************/
var count = 0;
function notNumber(id,name){
	$("#" + id + "_td").html("");
	if (!isNumber($("#" + id).val())) {
		$("#" + id + "_td").html("["+name + "]不能为非数字!");
		count++;
	}
}
/*******************************************************************************
 * (作者:GZZ)通用提交方法
 ******************************************************************************/
function toAction(action) {
	$('#form1').attr('action', action);
	$('#form1').submit();
}
/*******************************************************************************
 * (作者:GZZ)取得选中的ID,中间用豆号分隔
 ******************************************************************************/
function getIds() {
	var ids = "";
	$("input:checkbox[name='chk']:checked").each(function() {
		ids += $(this).val() + ",";
	});
	return ids;
}
/*******************************************************************************
 * (作者:GZZ)清空查询条件
 ******************************************************************************/
function clearCond(cond) {
	$.each(cond, function(key, val) {
		$("#" + val).val("");
	});
}
/*******************************************************************************
 * (作者:GZZ)删除验证
 ******************************************************************************/
function checkDel(ids) {
	if (ids.length == 0) {
		alert("请选择记录！");
		return false;
	}
	if (!confirm("确定删除这些记录吗？")) {
		return false;
	}
	return true;
}
/*******************************************************************************
 * (作者:GZZ)用户确认
 ******************************************************************************/
function UserConfirm(msg) {
 	if (!confirm(msg)){
		return false;
	}
	return true;
}
/*******************************************************************************
 * (作者:GZZ)默认的非空验证方法(多值)
 ******************************************************************************/
function checkBlank(arr) {
	$.each(arr, function(key, val) {
		notBlank(val[0], val[1]);
	});
}
/*******************************************************************************
 * (作者:GZZ)实时非空验证方法(多值)
 ******************************************************************************/
function realTimeCheck(arr) {
	$.each(arr, function(key, val) {
		if(val[0]==1){
			$('#'+val[1]).bind('input propertychange', function() {notBlank(val[1], val[2]);});
		} else if(val[0]==2){
			$('#'+val[1]).bind('blur', function() {notBlank(val[1], val[2]);});
		}else if(val[0]==3){
			$('#'+val[1]).bind('input propertychange', function() {notifyLength(this);});
		}
	});
}
/*******************************************************************************
 * (作者:GZZ)弹出蔗遮罩层防止重复提交
 ******************************************************************************/
function popupMasker() {

	var p=document.createElement("DIV");
	p.id="MyAlertBoxMasker";
	p.style.position="absolute";
	p.style.width=document.body.clientWidth;
	p.style.height=document.body.clientHeight;
	p.style.zIndex='998';
	p.style.top='0';
	p.style.left='0';
	p.style.backgroundColor="gray";
	p.style.opacity='0.5';
	p.style.filter="alpha(opacity=80)";
	//内容层
	var p1=document.createElement("DIV");
	var top=parseInt(parseInt(document.body.scrollHeight)*0.25)+document.body.scrollTop;
	p1.id="MyAlertBox";
	p1.style.position="absolute";
	var left=document.documentElement.offsetHeight/2;
	var left=0;
	p1.style.zIndex='999';
	p1.style.top=top+'px';
	p1.style.left=left+'px';

	p1.innerHTML="<div style='font-size:30px;color: blue;'>保存中请稍候.....</div>";//这里是浮动层的具体HTML内容
	document.body.appendChild(p);
	document.body.appendChild(p1);
	//上面就是出现的代码。-----------

	//下面代码是关闭的
//	document.body.removeChild(document.getElementById('MyAlertBoxMasker'));
//	document.body.removeChild(document.getElementById('MyAlertBox'));
}
/*******************************************************************************
 * (作者:GZZ)回显图片[参数说明：fileObj文件对象,imgid图片控件ID,width显示宽度(可选),width显示高度(可选)]
 ******************************************************************************/
function showImg(fileObj,imgid,width,height) {//回显图片
	if(width==undefined){
		width=200;
	}
	if(height==undefined){
		height=120;
	}
	var reader = new FileReader();
	reader.onload = function(e) {
		$('#'+imgid).attr('src', e.target.result).width(width).height(height);
	};
	reader.readAsDataURL(fileObj.files[0]);
}
/*******************************************************************************
 * (作者:GZZ)获取文件扩展名(转成小写)带点
 ******************************************************************************/
function getFile(fname){
	fname=fname.toLowerCase();
	var result =/\.[^\.]+/.exec(fname);
	return result;
}
 
function imgFile (fname){
	if(getFile(fname)==".jpg"||getFile(fname)==".gif"|| getFile(fname)==".jpeg"|| getFile(fname)==".png"){
		return true;
	}
	return false;
}