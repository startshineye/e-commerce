package com.yxm.cms.sys.roleUser.service;

import java.util.List;

import com.yxm.cms.sys.roleUser.model.RoleUser;
import com.yxm.cms.sys.roleUser.model.RoleUserCond;

/**
 * 用户角色关联Service接口类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public interface IRoleUserService {
	/**
	 * 批量关联用户
	 */
	public int doConnect(String[] funcid, String roleid);
	
	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	public List<RoleUser> queryAllObj(RoleUserCond cond);
}
