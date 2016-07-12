package com.bjpowernode.cms.sys.roleUser.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjpowernode.cms.sys.roleUser.service.IRoleUserService;
/**
 * 用户角色关联Dao实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Controller
@RequestMapping("/roleuser")
public class RoleUserController {
	@Autowired
	private IRoleUserService sysroleuserService;
	/**
	 * 批量关联用户
	 */
	@ResponseBody
	@RequestMapping("/doconnect")
	public String doConnect(@RequestParam String roleid, @RequestParam String userids) {
		if (null != userids && !userids.equals("")) {
			userids = userids.substring(1);
		}
		String ids[] = userids.split(",");
		sysroleuserService.doConnect(ids, roleid);
		return "OK";
	}
}
