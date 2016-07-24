package com.yxm.cms.sys.role.controller;
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

import com.yxm.cms.sys.role.model.Role;
import com.yxm.cms.sys.role.model.RoleCond;
import com.yxm.cms.sys.role.service.IRoleService;
/**
 * 角色控制器类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService service;
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(@ModelAttribute("role") Role role, HttpSession session) {
		return "/sys/role/insert";
	}
	/**
	 * 新增
	 */
	@RequestMapping("/insert")
	public String Add(Map<String, Object> map, @ModelAttribute("role") Role role, BindingResult result) {
		validate(role, result, 1);// 调用新增验证方法
		if (result.hasErrors()) {
			map.put("role", role);
			return "/sys/role/insert";
		}
		service.insert(role);
		return "redirect:/role/list";
	}
	/**
	 * 后台验证
	 */
	private void validate(Role role, BindingResult result, int method) {// method:1新增2修改
		RoleCond cond = new RoleCond();
		if (method == 2)
			cond.setId_c(role.getId());
		cond.setName_c(role.getName());
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("remark", "", "角色名称不能重复!");
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Map<String, Object> map, @RequestParam String id) {
		service.delete(id);
		return "redirect:/role/list";
	}
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam String id) {
		Role role = service.findById(id);
		map.put("role", role);
		return "/sys/role/update";
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Map<String, Object> map, @ModelAttribute("role") Role role, BindingResult result) {
		validate(role, result, 2);// 调用修改验证方法
		if (result.hasErrors()) {
			map.put("role", role);
			return "/sys/role/update";
		}
		service.update(role);
		return "redirect:/role/list";
	}
	/**
	 * 按条件查询分页列表
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") RoleCond cond) {
		service.queryList(cond, map);
		return "/sys/role/list";
	}
	/**
	 * 按角色ID查询用户
	 */
	@RequestMapping("/listuser")
	public String findUser(Map<String, Object> map, @RequestParam("id") String id) {
 		map.put("dataList", service.queryAllMap(new RoleCond(new Object[][]{{"role_id",id}})));
		Role role = service.findById(id);
		map.put("role", role);
		return "/sys/sysuser/refnopage";
	}
	/**
	 * 跳转到详细
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam String id) {
		Role role = service.findById(id);
		map.put("role", role);
		return "/sys/role/detail";
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