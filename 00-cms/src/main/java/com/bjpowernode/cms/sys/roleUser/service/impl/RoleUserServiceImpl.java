package com.bjpowernode.cms.sys.roleUser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.cms.sys.roleUser.dao.IRoleUserDao;
import com.bjpowernode.cms.sys.roleUser.model.RoleUser;
import com.bjpowernode.cms.sys.roleUser.model.RoleUserCond;
import com.bjpowernode.cms.sys.roleUser.service.IRoleUserService;

/**
 * 用户角色关联Service实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Service
public class RoleUserServiceImpl implements IRoleUserService {
	@Autowired
	private IRoleUserDao dao;

	/**
	 * 批量关联用户
	 */
	@Override
	public int doConnect(String[] userids, String roleid) {
		dao.delete(new Object[] { roleid });
		List<RoleUser> list = new ArrayList<RoleUser>();
		RoleUser ru = null;
		for (String userid : userids) {
			ru = new RoleUser();
			ru.setRole_id(roleid);
			ru.setUser_id(userid);
			list.add(ru);
		}
		dao.insertBatch(list);
		return list.size();
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	@Override
	public List<RoleUser> queryAllObj(RoleUserCond cond) {
		return dao.queryAllObj(cond);
	}
}
