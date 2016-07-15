package com.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.cms.sys.user.model.User;
import com.yxm.cms.sys.user.model.UserCond;
import com.yxm.cms.sys.user.service.IUserService;
import com.common.dao.DictiParamUtil;

/**
 * 下拉列表实例
 * 
 * @author GZZ @2014-02-13
 */
@Controller
@RequestMapping("/exp")
public class SelectExample {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IUserService srvice;
	@Autowired
	private DictiParamUtil du;

	@RequestMapping("/select")
	public String toAdd(Map<String, Object> map, @ModelAttribute("sysuser") User sysuser) {
		map.put("item_map", du.getDictMap(100, true));// false不填加[--请选择--]项,true填加
		map.put("user_list", srvice.queryAllObj(new UserCond()));// 直接从数据库查出list或map
		return "/sys/code/selectexp";
	}

	/**
	 * Json列表(Ajax调用)
	 */
	@RequestMapping("/jsonList")
	@ResponseBody
	public Map<String, Object> ajaxJson() {
		logger.debug("Ajax列表");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsonMap", du.getDictMap(100, true));// 从字典辅助类取值
		map.put("jsonList", srvice.queryAllObj(new UserCond()));// 直接从数据库查出list或map
		return map;
	}
}
