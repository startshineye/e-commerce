package com.bjpowernode.cms.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.cms.sys.user.model.User;
import com.bjpowernode.cms.sys.user.model.UserCond;

/**
 * 用户Dao接口类
 *
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
public interface IUserDao {
	/**
	 * 新增
	 */
	int insert(User user);

	/**
	 * 新增
	 */
	int insertCustomer(User vo);

	/**
	 * 物理删除
	 */
	int delete(String id);

	/**
	 * 按ID查找单个实体
	 */
	User findById(String id);

	/**
	 * 更新
	 */
	int update(User user);

	/**
	 * 按条件查询分页列表
	 */
	void queryList(UserCond cond, Map<String, Object> map);

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	List<User> queryAllObj(UserCond cond);

	/**
	 * 按条件查询记录个数
	 */
	int findCountByCond(UserCond cond);

	/**
	 * 重置密码
	 */
	int updatePas(User vo);
}