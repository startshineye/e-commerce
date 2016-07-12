package com.bjpowernode.cms.sys.sysfunc.model;

/**
 * @类说明:功能菜单实体类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
public class SysFunc {

	// 数据库中的字段
	private String func_id;// 主键
	private String name;// 名称
	private String type;// 类型
	private Integer is_leaf;// 是否叶子
	private String url;// 地址或按钮键
	private String parent_id;// 父结点
	private String order_code;// 排序编号
	private String remark;// 备注
	// 此处可添加查询显示辅助字段

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}