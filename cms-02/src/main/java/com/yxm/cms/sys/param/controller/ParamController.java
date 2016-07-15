package com.yxm.cms.sys.param.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxm.cms.sys.param.model.Param;
import com.yxm.cms.sys.param.model.ParamCond;
import com.yxm.cms.sys.param.service.IParamService;
/**
 * 系统参数控制器类
 * 
 * @author GZZ
 * @date 2014-02-15 11:09:23
 */
@Controller
@RequestMapping("/param")
public class ParamController {
	@Autowired
	private IParamService service;

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(@ModelAttribute("param") Param param, HttpSession session) {
		return "/sys/param/insert";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/insert")
	public String Add(Map<String, Object> map, @ModelAttribute("param") Param param, BindingResult result) {
		validate(param, result, 1);// 调用新增验证方法
		if (result.hasErrors()) {
			map.put("param", param);
			return "/sys/param/insert";
		}
		service.insert(param);
		return "redirect:/param/list";
	}

	/**
	 * 后台验证
	 */
	private void validate(Param param, BindingResult result, int method) {// method:1新增2修改
		ParamCond cond = new ParamCond();
		if (method == 2)
			cond.setId_c(param.getId());
		cond.setParam_key_c(param.getParam_key());
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("param_key", "", "参数关键字不能重复!");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Map<String, Object> map, String id) {
		service.delete(id);
		return "redirect:/param/list";
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, String id) {
		Param param = service.findById(id);
		map.put("param", param);
		return "/sys/param/update";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Map<String, Object> map, @ModelAttribute("param") Param param, BindingResult result) {
		validate(param, result, 2);// 调用修改验证方法
		if (result.hasErrors()) {
			map.put("param", param);
			return "/sys/param/update";
		}
		service.update(param);
		return "redirect:/param/list";
	}

	/**
	 * 按条件查询分页列表
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") ParamCond cond) {
		service.queryList(cond, map);
		return "/sys/param/list";
	}

	/**
	 * 跳转到详细
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, String id) {
		Param param = service.findById(id);
		map.put("paramObj", param);
		return "/sys/param/detail";
	}
	
	/**
	 * 日期属性编辑器
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}
}