/* 
用途：检查开始日期是否小于等于结束日期
输入： 
s：字符串 开始日期 格式：2001-5-4

e：字符串 结束日期 格式：2002-5-4
返回： 
如果通过开始日期小于等于结束日期返回true,否则返回false 
 */

function data_compare(s, e) {
	var arr = s.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = e.split("-");
	var endtime = new Date(arrs[0], arrs[1], arrs[2]);
	var endtimes = endtime.getTime();

	if (starttimes >= endtimes) {
		// alert('开始时间大于离开时间，请检查');
		return false;
	} else
		return true;
}

/*
 * 用途：检查输入字符串是否为空或者全部都是空格 输入：str 返回： 如果全是空返回true,否则返回false
 */
function isNull(str) {
	if (str == "")
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

/*
 * 用途：判断是否是日期 输入：date：日期；匹配的格式: 2010-5-16
 * 
 * 返回：如果通过验证返回true,否则返回false
 */

function isDate(str) {
	if (isNull(str))
		return false;
	var r = str.match(/^(\d{4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[5]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);
}

/*
 * 用途：检查输入字符串是否只由英文字母和数字和下划线组成 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isNumberOr_Letter(s) {
	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
 * 用途：检查输入对象的值是否符合E-Mail格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function isEmail(str) {
	var myReg = /^[-_A-Za-z0-9]+@([\-_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if (myReg.test(str))
		return true;
	return false;
}

/*
 * 用途：检查输入手机号码是否正确 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function checkMobile(s) {
	var regu = /^[1][0-9][0-9]{9}$/;
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
 * 用途：检查输入字符串是否是数字 输入： str：字符串 返回： 如果通过验证返回true,否则返回false
 */

function isNumber(str){
	var reg = /^\d+$/;
	if (reg.test(str))
		return true;
	else
		return false;
}

/*
 * 用途：检查输入字符串是否符合时间格式 输入： time：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isTime(time) {
	var regex = /^[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$/;
	if (!regex.test(time)) {
		return false;
	}
	var hour = time.substring(0, 2);
	var minute = time.substring(3, 5);
	var second = time.substring(6);
	if (hour > 23 || hour < 0) {
		return false;
	}
	if (minute > 60 || minute < 0) {
		return false;
	}
	if (second > 60 || second < 0) {
		return false;
	}
	return true;
}

/*
 * 用途：检查输入字符串是否符合正整数格式 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

/*
 * 用途：检查输入字符串是否符合国内固话或者传真格式 输入： s：字符串 格式例如：020-87110252 返回：
 * 如果通过验证返回true,否则返回false
 */

function isTel(s) {
	var reg = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if (!reg.test(s))
		return false;
	return true;
}

/*
 * 用途：检查输入字符串是否符合身份证格式 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */

function isIDno(strIDno) {
	var aCity = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};

	var iSum = 0;
	//var info = "";
	// var strIDno = obj.value;
	var idCardLength = strIDno.length;
	if (!/^\d{17}(\d|x)$/i.test(strIDno) && !/^\d{15}$/i.test(strIDno)) {
		// alert("非法身份证号");
		return false;
	}

	// 在后面的运算中x相当于数字10,所以转换成a
	strIDno = strIDno.replace(/x$/i, "a");

	if (aCity[parseInt(strIDno.substr(0, 2))] == null) {
		// alert("非法地区");
		return false;
	}

	if (idCardLength == 18) {
		sBirthday = strIDno.substr(6, 4) + "-" + Number(strIDno.substr(10, 2)) + "-" + Number(strIDno.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
			// alert("非法生日");
			return false;
		}

		for ( var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11) * parseInt(strIDno.charAt(17 - i), 11);

		if (iSum % 11 != 1) {
			// alert("非法身份证号");
			return false;
		}
	} else if (idCardLength == 15) {
		sBirthday = "19" + strIDno.substr(6, 2) + "-" + Number(strIDno.substr(8, 2)) + "-" + Number(strIDno.substr(10, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		var dd = d.getFullYear().toString() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
		if (sBirthday != dd) {
			// alert("非法生日");
			return false;
		}
	}
	return true;
}

/*
 * 用途：检查输入字符串是否符合邮政编码格式 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */

function isZipcode(str) {
	var reg = /^\d+$/;
	if (!reg.test(str))
		return false;
	if (str.length != 6) {
		// alert("邮政编码长度必须是6位");
		return false;
	}
	return true;
}

/*
 * 用途：检查输入字符串是否符合金额格式 格式定义为带小数的正数，小数点后最多三位 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isMoney(s) {
	// var regu = "^[0-9]+[\.][0-9]{0,3}$";
	var regu = "^[0-9]+([\.][0-9]{0,4})?$";// gzz 20101026修改

	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
 * 用途：检查输入字符串是否只由汉字组成 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */

function isZh(str) {
	var reg = /^[\u4e00-\u9fa5]+$/;
	if (reg.test(str))
		return true;
	return false;
}

/*
 * 用途：检查输入对象的值是否符合整数格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function isInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}

/*
 * 用途：校验ip地址的格式 输入：strIP：ip地址 返回：如果通过验证返回true,否则返回false；
 */
function isIP(strIP) {
	if (isNull(strIP))
		return false;
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g; // 匹配IP地址的正则表达式
	if (re.test(strIP)) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)
			return true;
	}
	return false;
}

/*
 * 用途：检查输入字符串是否只由汉字、字母、数字组成 输入： value：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isChinaOrNumbOrLett(s) {// 判断是否是汉字、字母、数字组成
	var regu = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
 * 用途：检查输入字符串是否只由英文字母和数字组成 输入： s：字符串 返回： 如果通过验证返回true,否则返回false
 */
function isNumberOrLetter(s) {// 判断是否是数字或字母
	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 根据身份证号码获取生日
 * 
 * @param {Object}
 *            身份证号码
 * @return {TypeName} 生日
 */
function getBirthdayByIdCard(val) {
	var birthdayValue = "";
	val = val.replace(/(^\s*)|(\s*$)/g, "");
	if (15 == val.length) { // 15位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7);
		if (parseInt(birthdayValue) < 10) {
			birthdayValue = '20' + birthdayValue;
		} else {
			birthdayValue = '19' + birthdayValue;
		}
		birthdayValue = birthdayValue + '-' + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11);
	}
	if (18 == val.length) { // 18位身份证号码
		birthdayValue = val.charAt(6) + val.charAt(7) + val.charAt(8) + val.charAt(9) + '-' + val.charAt(10) + val.charAt(11) + '-' + val.charAt(12)
				+ val.charAt(13);
	}
	return birthdayValue;
}

/**
 * 根据身份证号码获取性别
 * 
 * @param {Object}
 *            身份证号码
 * @return {TypeName} 性别
 */
function getSexByIdCard(val) {
	var sex = "";
	if (15 == val.length) { // 15位身份证号码
		if (parseInt(val.charAt(14) / 2) * 2 != val.charAt(14))
			sex = '男';
		else
			sex = '女';
	}
	if (18 == val.length) { // 18位身份证号码
		if (parseInt(val.charAt(16) / 2) * 2 != val.charAt(16))
			sex = '男';
		else
			sex = '女';
	}
	return sex;
}

/**
 * 检测文本域输入字数，多于指定字数不能再输入
 * 
 * @param {Object}
 *            textareaId 要验证的文本域id
 * @param {Object}
 *            targetId 要显示验证结果的控件id
 * @param {Object}
 *            maxNum 指定的验证长度
 */
function validateTextArea(textareaId, targetId, maxNum) {
	var value = document.getElementById(textareaId).value;
	var length = value.length;
	var results = '您已输入';
	if (length >= maxNum) {
		value = value.substr(0, maxNum);
		// $('#remark').attr('value',value);
		document.getElementById(textareaId).value = value;
		results = '<font color="red">' + results + value.length + '个字，<br>不能继续输入！</font>';
	} else {
		results = '<font color="green">' + results + length + '个字，<br>还能输入' + (150 - length) + '个字！</font>';
	}

	// $('#validateResults').html(results);
	document.getElementById(targetId).innerHTML = results;
}
