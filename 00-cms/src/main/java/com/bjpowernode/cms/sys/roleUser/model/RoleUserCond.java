package com.bjpowernode.cms.sys.roleUser.model;

import com.common.condition.BaseCondition;

/**
 * @类说明:角色用户关联查询条件类
 *
 * @author:gaozhenzhong@idstaff.com
 * @date:2015-06-05 20:31:17
 */
public class RoleUserCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 */
	@Override
	public void addCondition() {
		add(id_c, " AND T.ID LIKE ?", 3);
		add(user_id_c, " AND T.USER_ID = ?");
		add(role_id_c, " AND T.ROLE_ID = ?");
	}

	// 页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉!!!!!!
	private String id_c;// 主键
	private String user_id_c;// 用户
	private String role_id_c;// role_id

	public String getId_c() {
		return id_c;
	}

	public void setId_c(String id_c) {
		this.id_c = id_c;
	}

	public String getUser_id_c() {
		return user_id_c;
	}

	public void setUser_id_c(String user_id_c) {
		this.user_id_c = user_id_c;
	}

	public String getRole_id_c() {
		return role_id_c;
	}

	public void setRole_id_c(String role_id_c) {
		this.role_id_c = role_id_c;
	}

	// new 条件对象时用构造方法赋值:适用只有少量查询条件并且不需要重复使用的情况
	public RoleUserCond(Object[][] obj) {
		super(obj);
	}

	public RoleUserCond() {
	}
}