package com.yxm.cms.sys.dicttype.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxm.cms.sys.dicttype.model.DictType;
import com.yxm.cms.sys.dicttype.model.DictTypeCond;
import com.yxm.cms.sys.dicttype.service.IDictTypeService;
/**
 * 字典类型控制器类
 * 
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
@Controller
@RequestMapping("/dicttype")
public class DictTypeController {
	@Autowired
	private IDictTypeService service;
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(@ModelAttribute("dicttype") DictType dicttype, HttpSession session) {
		return "/sys/dicttype/insert";
	}
	/**
	 * 新增
	 */
	@RequestMapping("/insert")
	public String Add(Map<String, Object> map, @ModelAttribute("dicttype") DictType dicttype, BindingResult result) {
		validate(dicttype, result, 1);// 调用新增验证方法
		if (result.hasErrors()) {
			map.put("dicttype", dicttype);
			return "/sys/dicttype/insert";
		}
		service.insert(dicttype);
		return "redirect:/dicttype/list";
	}
	/**
	 * 后台验证
	 */
	private void validate(DictType dicttype, BindingResult result, int method) {// method:1新增2修改
		DictTypeCond cond = new DictTypeCond();
		if (method == 2)
			cond.setId_c(dicttype.getId());
		cond.setType_code_c(dicttype.getType_code());
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("type_code", "", "类型代码不能重复!");
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Map<String, Object> map, @RequestParam("id") String id) {
		service.delete(id);
		return "redirect:/dicttype/list";
	}
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam("id") String id) {
		DictType dicttype = service.findById(id);
		map.put("dicttype", dicttype);
		return "/sys/dicttype/update";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Map<String, Object> map, @ModelAttribute("dicttype") DictType dicttype, BindingResult result) {
		validate(dicttype, result, 2);// 调用修改验证方法
		if (result.hasErrors()) {
			map.put("dicttype", dicttype);
			return "/sys/dicttype/update";
		}
		service.update(dicttype);
		return "redirect:/dicttype/list";
	}
	/**
	 * 按条件查询分页列表
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") DictTypeCond cond ) {
		service.queryList(cond, map);
		return "/sys/dicttype/list";
	}
	/**
	 * 跳转到详细
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam("id") String id) {
		DictType dicttype = service.findById(id);
		map.put("dicttype", dicttype);
		return "/sys/dicttype/detail";
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