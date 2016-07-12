package com.bjpowernode.cms.sys.roleUser.model;

/**
 * 用户角色关联实体类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public class RoleUser {
	private String id;
	private String role_id;
	private String user_id;
	private String username;
	private String rolename;
 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}