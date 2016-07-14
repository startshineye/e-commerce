package com.yxm.cms.sys.authority.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.cms.sys.authority.service.IAuthoService;
import com.yxm.cms.sys.role.model.Role;
import com.yxm.cms.sys.role.service.IRoleService;
/**
 * @类说明:资源授权控制器类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Controller
@RequestMapping("/authority")
public class AuthoController {
	@Autowired
	private IRoleService roleService;// 角色service
	@Autowired
	private IAuthoService sysAuthorityService;// 角色service
	/**
	 * 转到部门授权页面
	 */
	@RequestMapping("/toorgaaunew")
	public String toOrgaAuNew(Map<String, Object> map, @RequestParam String roleid) {
		Role role = roleService.findById(roleid);
		map.put("role", role);
		return "/sys/authority/oraAuth";
	}
	/**
	 * 转到菜单授权页面
	 */
	@RequestMapping("/tofuncaunew")
	public String toFuncAuNew(Map<String, Object> map, @RequestParam String roleid) {
		Role role = roleService.findById(roleid);
		map.put("role", role);
		return "/sys/authority/funcAuth";
	}
	/**
	 * 执行菜单授权
	 */
	@ResponseBody
	@RequestMapping("/doauthfunction")
	public String doFuncAu(@RequestParam String roleid, @RequestParam String checkedids) {
		String[] funcid = checkedids.split(",");
		sysAuthorityService.doFunction(funcid, roleid, "Menu");
		return "OK";
	}
	/**
	 * 执行部门授权
	 */
	@ResponseBody
	@RequestMapping("/doauthorganize")
	public String doOrgaAu(@RequestParam String roleid, @RequestParam String checkedids) {
		String[] funcid = checkedids.split(",");
		sysAuthorityService.doFunction(funcid, roleid, "Area");
		return "OK";
	}
}
