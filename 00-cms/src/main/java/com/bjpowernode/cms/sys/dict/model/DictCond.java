package com.bjpowernode.cms.sys.dict.model;
import com.common.condition.BaseCondition;
/**
 * 数据字典条件查询类
 * 
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public class DictCond extends BaseCondition {
	public String getId_c() {
		return id_c;
	}
	public void setId_c(String id_c) {
		this.id_c = id_c;
	}
	@Override
	public void addCondition() {
		add(type_code_c, " AND T.TYPE_CODE = ?");
		add(data_key_c, " AND T.DATA_KEY = ?");
		add(data_value_c, " AND T.DATA_VALUE LIKE ?", 3);
		add(type_id_c, " AND T.TYPE_ID = ?");
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(id_c, " AND T.ID <> ?");
		add(data_key_1, " AND T.DATA_KEY > ? and T.DATA_KEY < 100");
		add(data_key_2, " AND T.DATA_KEY < ?");
	}
	private String id_c;// 主键
	private Integer type_code_c;// 类型编码
	private Integer data_key_c;// 数据键
	private String data_value_c;// 数据值
	private String type_id_c;// 类型名称
	private String remark_c;// 备注
	private Integer data_key_1;// 数据键
	private Integer data_key_2;// 数据键
	
	
 
	public Integer getData_key_1() {
		return data_key_1;
	}
	public void setData_key_1(Integer data_key_1) {
		this.data_key_1 = data_key_1;
	}
	public Integer getData_key_2() {
		return data_key_2;
	}
	public void setData_key_2(Integer data_key_2) {
		this.data_key_2 = data_key_2;
	}
	public Integer getType_code_c() {
		return type_code_c;
	}
	public void setType_code_c(Integer type_code_c) {
		this.type_code_c = type_code_c;
	}
	public Integer getData_key_c() {
		return data_key_c;
	}
	public void setData_key_c(Integer data_key_c) {
		this.data_key_c = data_key_c;
	}
	public String getData_value_c() {
		return data_value_c;
	}
	public void setData_value_c(String data_value_c) {
		this.data_value_c = data_value_c;
	}
	public String getType_id_c() {
		return type_id_c;
	}
	public void setType_id_c(String type_id_c) {
		this.type_id_c = type_id_c;
	}
	public String getRemark_c() {
		return remark_c;
	}
	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}
	public DictCond() {
	}
	public DictCond(Object[][] obj) {
		super(obj);
	}
}