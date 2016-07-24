package com.yxm.cms.sys.dict.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.yxm.cms.sys.dict.model.Dict;
import com.yxm.cms.sys.dict.model.DictCond;
import com.yxm.cms.sys.dict.service.IDictService;
import com.yxm.cms.sys.dicttype.service.IDictTypeService;

/**
 * 数据字典控制器类
 * 
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private IDictService service;
	@Autowired
	private IDictTypeService typeService;

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(@ModelAttribute("dict") Dict dict, HttpSession session, @RequestParam String type_id, @RequestParam Integer typecode) {
		dict.setType_id(type_id);
		dict.setType_code(typecode);
		return "/sys/dict/insert";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/insert")
	public String Add(Map<String, Object> map, @ModelAttribute("dict") Dict dict, BindingResult result) {
		validate(dict, result, 1);// 调用新增验证方法
		if (result.hasErrors()) {
			map.put("dict", dict);
			return "/sys/dict/insert";
		}
		service.insert(dict);
		return "redirect:/dict/list?type_id=" + dict.getType_id();
	}

	/**
	 * 后台验证
	 */
	private void validate(Dict dict, BindingResult result, int method) {// mothed:1新增2修改
		DictCond cond = new DictCond();
		if (method == 2)
			cond.setId_c(dict.getId());
		cond.setData_key_c(dict.getData_key());
		cond.setType_code_c(dict.getType_code());
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("data_key", "", "数据关键字不能重复!");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Map<String, Object> map, @RequestParam String id, @RequestParam String type_id) {
		service.delete(id);
		return "redirect:/dict/list?type_id=" + type_id;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam String id) {
		Dict dict = service.findById(id);
		map.put("dict", dict);
		return "/sys/dict/update";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Map<String, Object> map, @ModelAttribute("dict") Dict dict, BindingResult result) {
		validate(dict, result, 2);// 调用修改验证方法
		if (result.hasErrors()) {
			map.put("dict", dict);
			return "/sys/dict/update";
		}
		service.update(dict);
		return "redirect:/dict/list?type_id=" + dict.getType_id();
	}

	/**
	 * 按条件查询分页列表
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") DictCond cond, @RequestParam("type_id") String type_id) {
		map.put("dicttype", typeService.findById(type_id));
		cond.setType_id_c(type_id);
		service.queryList(cond, map);
		return "/sys/dict/list";
	}

	/**
	 * 跳转到详细
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam("id") String id) {
		Dict dict = service.findById(id);
		map.put("dict", dict);
		return "/sys/dict/detail";
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