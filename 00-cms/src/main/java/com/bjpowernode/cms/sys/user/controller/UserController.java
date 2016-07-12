package com.bjpowernode.cms.sys.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjpowernode.cms.sys.roleUser.model.RoleUserCond;
import com.bjpowernode.cms.sys.roleUser.service.IRoleUserService;
import com.bjpowernode.cms.sys.user.model.User;
import com.bjpowernode.cms.sys.user.model.UserCond;
import com.bjpowernode.cms.sys.user.service.IUserService;
import com.common.dao.DictiParamUtil;

/**
 * 用户控制器类
 * 
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IUserService service;// 用户service
	@Autowired
	private DictiParamUtil du;// 数据字典辅助类
	@Autowired
	private IRoleUserService roleUserService;

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Map<String, Object> map, @ModelAttribute("user") User user, HttpSession session) {
		map.put("sexmap", du.getDictMap(100, false));
		return "/sys/user/insert";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/insert")
	public String Add(Map<String, Object> map, @ModelAttribute("user") User user, BindingResult result) {
		validate(user, result, 1);// 调用新增验证方法
		if (result.hasErrors()) {
			return "/sys/user/insert";
		}
		service.insert(user);
		return "redirect:/user/list";
	}

	/**
	 * 后台验证
	 */
	private void validate(User user, BindingResult result, int method) {// method:1新增2修改
		UserCond cond = new UserCond();
		logger.debug(user.getId());
		if (method == 2)
			cond.setId_c(user.getId());
		cond.setLogin_id_c(user.getLogin_id());
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("login_id", "", "登录名不能重复!");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Map<String, Object> map, @RequestParam String id) {
		service.delete(id);
		return "redirect:/user/list";
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam String id) {
		User user = service.findById(id);
		map.put("sexmap", du.getDictMap(100, false));
		map.put("user", user);
		return "/sys/user/update";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(Map<String, Object> map, @ModelAttribute("user") User user, BindingResult result) {
		validate(user, result, 2);// 调用修改验证方法
		if (result.hasErrors()) {
			return "/sys/user/update";
		}
		service.update(user);
		return "redirect:/user/list";
	}

	/**
	 * 按条件查询分页列表
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") UserCond cond) {
		service.queryList(cond, map);
		return "/sys/user/list";
	}

	/**
	 * 跳转到详细
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam String id) {
		User user = service.findById(id);
		map.put("dataList", roleUserService.queryAllObj(new RoleUserCond(new Object[][] { { "user_id_c", id } })));
		map.put("user", user);
		return "/sys/user/detail";
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

	/**
	 * 按条件查询分页列表(参照选择页)
	 */
	@RequestMapping("/ref")
	public String queryRef(Map<String, Object> map, @ModelAttribute("cond") UserCond cond) {
		service.queryList(cond, map);
		return "/sys/user/ref";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updatePas")
	@ResponseBody
	public Integer updatePas(Map<String, Object> map, String id) {
		return service.updatePas(id);
	}
}