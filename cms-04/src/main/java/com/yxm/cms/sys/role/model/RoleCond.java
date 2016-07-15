package com.yxm.cms.sys.role.model;

import com.common.condition.BaseCondition;

/**
 * 角色条件查询类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public class RoleCond extends BaseCondition {
	@Override
	public void addCondition() {
		add(name_c, " AND T.NAME = ?");
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(id_c, " AND T.ID <> ?");
		add(role_id, " AND ROLE_ID = ?");
	}

	private String role_id;
	private String id_c;// 主键
	private String name_c;// 名称
	private String remark_c;// 备注

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getId_c() {
		return id_c;
	}

	public void setId_c(String id_c) {
		this.id_c = id_c;
	}

	public String getName_c() {
		return name_c;
	}

	public void setName_c(String name_c) {
		this.name_c = name_c;
	}

	public String getRemark_c() {
		return remark_c;
	}

	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}

	public RoleCond(Object[][] obj) {
		super(obj);
	}

	public RoleCond() {
	}
}