package com.bjpowernode.cms.sys.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjpowernode.cms.sys.user.model.User;
import com.bjpowernode.cms.sys.user.model.UserCond;
import com.bjpowernode.cms.sys.user.service.IUserService;
import com.common.constant.Constant;
import com.common.md5.MD5Util;
import com.common.util.Util;

/**
 * 用户管理控制器类
 * 
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IUserService service;

	/**
	 * 用户登录方法
	 */
	@RequestMapping("/userlogin")
	public String UserLogin(Map<String, Object> map, HttpSession session, @ModelAttribute("user") User user, BindingResult result) {
		// logger.debug(user.getLogin_id());
		user.setPassword(MD5Util.genMd5(user.getPassword()));

		User user1 = null;
		List<User> list = null;
		if (user.getLogin_id() != null)
			list = service.queryAllObj(new UserCond(new Object[][] { { "login_id_c", user.getLogin_id() } }));
		if (null != list && list.size() > 0)
			user1 = list.get(0);

		if (null != user1 && !user1.getPassword().equals(user.getPassword())) {
			result.rejectValue("password", "", "密码不正确!");
		} else if (null == user1) {
			result.rejectValue("login_id", "", "用户名不存在!");
		}
		if (result.hasErrors()) {
			map.put("user", user);
			return "/sys/sysuser/login";
		}
		session.setAttribute(Constant.LOGIN_USER, user1);
		return "/common/mainpage";
	}

	/**
	 * 注销
	 */
	@RequestMapping("/userlogout")
	public String UserLogout(HttpSession session, @ModelAttribute("user") User user) {
		session.setAttribute(Constant.LOGIN_USER, null);
		return "/sys/sysuser/login";
	}

	/**
	 * 转到登录页面
	 */
	@RequestMapping("/tologin")
	public String toLogin(Map<String, Object> map, @ModelAttribute("user") User user, @RequestParam(required = false) String msg) {
		if (null != msg && msg.equals("1"))
			map.put("msg", Constant.OUT_MSG);
		logger.debug(map.get("msg"));
		return "/sys/sysuser/login";
	}

	/**
	 * 转到修改密码
	 */
	@RequestMapping("/toEditPws")
	public String updatePws(Map<String, Object> map, @ModelAttribute("user") User user, HttpSession session) {
		if (Util.getTimeOut(session)) {
			return "redirect:/user/tologin?msg=1";
		}
		user = Util.getUser(session);
		map.put("user", user);
		return "/sys/sysuser/editPas";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/editPws")
	public String editPsd(Map<String, Object> map, @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		AddvalidatePsd(user, result, session);
		if (result.hasErrors()) {
			return "/sys/sysuser/editPas";
		}
		User user1 = service.findById(user.getId());
		user1.setPassword(MD5Util.genMd5(user.getPw1()));
		map.put("ok", service.update(user1));
		return "/sys/sysuser/editPas";
	}

	/**
	 * 修改密码验证
	 */
	public void AddvalidatePsd(User user, BindingResult result, HttpSession session) {
		ValidationUtils.rejectIfEmpty(result, "password", "", "原密码不能为空");
		ValidationUtils.rejectIfEmpty(result, "pw1", "", "新密码不能为空");
		User sysuser = Util.getUser(session);
		if (!MD5Util.genMd5(user.getPassword()).equals(sysuser.getPassword())) {
//			logger.debug(sysuser.getPassword());
//			logger.debug(MD5Util.genMd5(user.getPassword()));
			result.rejectValue("password", "", "原密码输入错误");
		}
		if (!user.getPw1().equals(user.getPw2())) {
			result.rejectValue("pw2", "", "密码确认不一致");
		}
		if (user.getPw1().length() < 4) {
			result.rejectValue("pw1", "", "密码不能小{4}个字符");
		}
	}
}
