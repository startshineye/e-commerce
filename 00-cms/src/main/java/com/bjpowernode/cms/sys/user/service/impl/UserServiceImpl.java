package com.bjpowernode.cms.sys.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.cms.sys.user.dao.IUserDao;
import com.bjpowernode.cms.sys.user.model.User;
import com.bjpowernode.cms.sys.user.model.UserCond;
import com.bjpowernode.cms.sys.user.service.IUserService;
import com.common.dao.DictiParamUtil;
import com.common.md5.MD5Util;

/**
 * 用户Service实现类
 * 
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao dao;

	@Autowired
	private DictiParamUtil dictParam;// 字典/参数辅助工具类

	/**
	 * 新增
	 */
	@Override
	public int insert(User user) {
		user.setPassword(MD5Util.genMd5(dictParam.findValue("defalt_password")));
		return dao.insert(user);
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
	public User findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 更新
	 */
	@Override
	public int update(User user) {
		return dao.update(user);
	}

	/**
	 * 按条件查询分页列表
	 */
	@Override
	public void queryList(UserCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
	}

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	@Override
	public List<User> queryAllObj(UserCond cond) {
		return dao.queryAllObj(cond);
	}

	/**
	 * 按条件查询记录个数
	 */
	@Override
	public int findCountByCond(UserCond cond) {
		return dao.findCountByCond(cond);
	}

	/**
	 * 重置密码
	 */
	@Override
	public int updatePas(String id) {
		User user = new User();
		user.setId(id);
		user.setPassword(MD5Util.genMd5(dictParam.findValue("defalt_password")));
		return dao.updatePas(user);
	}
}