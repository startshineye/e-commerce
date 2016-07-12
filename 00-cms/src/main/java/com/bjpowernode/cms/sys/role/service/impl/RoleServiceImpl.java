package com.bjpowernode.cms.sys.role.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.cms.sys.role.dao.IRoleDao;
import com.bjpowernode.cms.sys.role.model.Role;
import com.bjpowernode.cms.sys.role.model.RoleCond;
import com.bjpowernode.cms.sys.role.service.IRoleService;

/**
 * 角色Service实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao dao;

	/**
	 * 新增
	 */
	@Override
	public int insert(Role role) {
		return dao.insert(role);
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(String id) {
		// return dao.deleteLogic(id);//逻辑删除
		return dao.delete(id);// 物理删除
	}

	/**
	 * 按ID查找单个实体
	 */
	@Override
	public Role findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 更新
	 */
	@Override
	public int update(Role role) {
		return dao.update(role);
	}

	/**
	 * 按条件查询分页列表
	 */
	@Override
	public void queryList(RoleCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
	}

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	@Override
	public List<Role> queryAllObj(RoleCond cond) {
		return dao.queryAllObj(cond);
	}

	/**
	 * 按条件查询不分页列表(不使用类型)
	 */
	@Override
	public List<Map<String, Object>> queryAllMap(RoleCond cond) {
		return dao.queryAllMap(cond);
	}

	/**
	 * 按条件查询记录个数
	 */
	@Override
	public int findCountByCond(RoleCond cond) {
		return dao.findCountByCond(cond);
	}
}