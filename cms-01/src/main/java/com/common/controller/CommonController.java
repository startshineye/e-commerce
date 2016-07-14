package com.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.util.Util;

/**
 * 首页的跳转类
 * 
 * @author GZZ
 * @2013-03-14
 */
/**
 * @author gzz_gzz@163.com
 * @date 2015年1月27日下午1:35:26
 * @功能描述:
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * @功能描述: 进入主页面的跳转
	 */
	@RequestMapping("/mainpage")
	public String mainpage() {
		return "/common/mainpage";
	}

	/**
	 * @功能描述: 主框架页顶部
	 */
	@RequestMapping("/top")
	public String top() {
		return "/common/top";
	}

	/**
	 * @功能描述: 转向欢迎页面
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "/common/welcome";
	}

	/**
	 * @功能描述: 转向菜单页面
	 */
	@RequestMapping("/menu")
	public String menu() {
		return "/common/menu";
	}
	/**
	 * @功能描述: 转向转向公共的导入页面
	 */
	@RequestMapping("/importExcel")
	public String importExcel() {
		return "/common/importExcel";
	}
	/**
	 * @功能描述: 转向选择参照示例页
	 */
	@RequestMapping("/ref")
	public String ref() {
		return "/common/commonref";
	}

	@RequestMapping("/timeout")
	@ResponseBody
	public Map<String, Object> ajaxJson(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		String timeout = Util.getTimeOut(session) ? "1" : "0";
		map.put("timeout", timeout);
		return map;
	}
}
