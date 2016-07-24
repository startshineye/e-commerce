package com.yxm.cms.sys.roleUser.dao;
import java.util.List;

import com.yxm.cms.sys.roleUser.model.RoleUser;
import com.yxm.cms.sys.roleUser.model.RoleUserCond;
/**
 * 用户角色关联Dao实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public interface IRoleUserDao {
	/**
	 * 批量插入用户角色关联表数据
	 */
	 int[] insertBatch(List<RoleUser> list);
	/**
	 * 按角色删除用户角色关联表记录
	 */
	 int delete(Object[] obj);
	/**
	 * 是否已关联
	 */
	 int HasConnect(Object[] obj);
	
	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	 List<RoleUser> queryAllObj(RoleUserCond cond);
}
