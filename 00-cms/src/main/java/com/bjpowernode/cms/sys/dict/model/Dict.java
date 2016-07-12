package com.bjpowernode.cms.sys.dict.model;

/**
 * 数据字典实体类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public class Dict {

	private String id;// 主键
	private Integer type_code;// 类型编码
	private Integer data_key;// 数据键
	private String data_value;// 数据值
	private String type_id;// 类型名称
	private String remark;// 备注

	public Integer getType_code() {
		return type_code;
	}

	public void setType_code(Integer type_code) {
		this.type_code = type_code;
	}

	public Integer getData_key() {
		return data_key;
	}

	public void setData_key(Integer data_key) {
		this.data_key = data_key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData_value() {
		return data_value;
	}

	public void setData_value(String data_value) {
		this.data_value = data_value;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}