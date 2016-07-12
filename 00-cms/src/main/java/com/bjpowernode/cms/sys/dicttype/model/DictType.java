package com.bjpowernode.cms.sys.dicttype.model;

/**
 * 字典类型实体类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public class DictType {

	private String id;// 主键

	private Integer type_code;// 类型标识

	private String type_name;// 类型名称

	private String remark;// 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType_code() {
		return type_code;
	}

	public void setType_code(Integer type_code) {
		this.type_code = type_code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}