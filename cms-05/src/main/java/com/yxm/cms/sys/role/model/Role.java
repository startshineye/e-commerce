package com.yxm.cms.sys.role.model;

/**
 * 角色实体类
 *
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public class Role {

	private String id;// 主键
	private String remark;// 备注
	private String name;// 名称
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
 
}