package com.bjpowernode.cms.cms.cmsbanner.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.cmsbanner.model.Banner;
import com.bjpowernode.cms.cms.cmsbanner.model.BannerCond;
import com.bjpowernode.cms.cms.cmsbanner.service.IBannerService;
import com.common.dao.DictiParamUtil;

///通常情况：Controller 只做验证与跳转，页面数据初始化
@Controller
@RequestMapping("banner")
public class BannerAction {
	@Autowired
	private IBannerService service;

	@Autowired
	private DictiParamUtil dict;// 字典参数辅助类
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(this.getClass());// 日志类

	@RequestMapping("insert")
	// BindingResult用来描述错误信息并且把报错信息带回到页面上
	public String insert(Banner banner, BindingResult result, Map<String, Object> map, MultipartFile image) {
		validate(banner, result, 1);// 调用验证方法，1新增的验证
		if (result.hasErrors()) {
			map.put("type", dict.getDictMap(10, false));// 准备终端类型下拉列表数
			map.put("banner", banner);// 把这个对象扔到request
			return "cms/banner/insert";
		}

		service.insert(banner, image);
		return "redirect:/banner/list";
	}

	private void validate(Banner banner, BindingResult result, int method) {
		BannerCond cond = new BannerCond();
		if (method == 2) {
			cond.setId_c(banner.getId());
		}
		cond.setName_c(banner.getName());// 前端给name转成查询条件
		if (service.findByCond(cond) > 0) {
			// 第一个参数要提示的字段的名称一致，要与error标签的path一致
			// 第二个参数错误码，
			// 第三个参数提示信息
			// 要与页面spring标签中的form:error标签同时使用
			//
			result.rejectValue("name", "", "名称已经存在!");
		}
	}

	@RequestMapping("toinsert")
	public String toinsert(Banner banner, Map<String, Object> map) {
		map.put("type", dict.getDictMap(10, false));// 准备终端类型下拉列表数
		return "cms/banner/insert";
	}

	@RequestMapping("update")
	public String update(Banner banner, BindingResult result,  MultipartFile image) {
		validate(banner, result, 2);// 调用验证方法，2更新的验证
		service.update(banner, image);
		return "redirect:/banner/list";
	}

	@RequestMapping("toupdate")
	public String toupadte(int id, Map<String, Object> map) {
		map.put("type", dict.getDictMap(10, false));// 准备终端类型下拉列表数
		map.put("banner", service.findById(id));
		return "cms/banner/update";
	}

	@RequestMapping("detail")
	public String detail(Integer id, Map<String, Object> map) {
		map.put("banner", service.findById(id));
		return "cms/banner/detail";
	}

	@RequestMapping("delete")
	public String delete(String id) {
		service.delete(id);
		return "redirect:/banner/list";// 重定向到列表页面
	}

	@RequestMapping("list")
	public String queryList(@ModelAttribute("cond") BannerCond cond, Map<String, Object> map) {
		service.queryList(cond, map);
		map.put("type", dict.getDictMap(10, true));// 准备终端类型下拉列表数
		map.put("status", dict.getDictMap(11, true));// 准备轮播图状态下拉列表数
		return "cms/banner/list";
	}

	@RequestMapping("updatesta")
	public String updatesta(Banner banner) {
		service.updateSta(banner);// 调用更新状态的方法
		return "redirect:/banner/list";
	}
}
