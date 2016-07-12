package com.bjpowernode.cms.sys.param.model;
import java.util.Date;

import com.common.condition.BaseCondition;
/**
 * 系统参数条件查询类
 *
 * @author GZZ
 * @date 2014-02-15 11:09:23
 */
public class ParamCond extends BaseCondition  {
	@Override
	public void addCondition() { 
		add(name_c, " AND T.NAME LIKE ?", 3);
		add(param_key_c, " AND T.PARAM_KEY = ?");
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(ts_c, " AND T.TS = ?");
		add(param_value_c, " AND T.PARAM_VALUE LIKE ?", 3);
		add(dr_c, " AND T.DR = ?");
		add(usable_c, " AND T.USABLE = ?");
		add(id_c, " AND T.ID <> ?");
	}

	private String id_c;// 主键

	private String name_c;// 参数名称

	private String param_key_c;// 参数键

	private String remark_c;// 备注

	private Date ts_c;// 时间戳

	private String param_value_c;// 参数值

	private Integer dr_c;// 删除标记

	private Integer usable_c;// 是否启用

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

	public String getParam_key_c() {
		return param_key_c;
	}
	public void setParam_key_c(String param_key_c) {
		this.param_key_c = param_key_c;
	}

	public String getRemark_c() {
		return remark_c;
	}
	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}

	public Date getTs_c() {
		return ts_c;
	}
	public void setTs_c(Date ts_c) {
		this.ts_c = ts_c;
	}

	public String getParam_value_c() {
		return param_value_c;
	}
	public void setParam_value_c(String param_value_c) {
		this.param_value_c = param_value_c;
	}

	public Integer getDr_c() {
		return dr_c;
	}
	public void setDr_c(Integer dr_c) {
		this.dr_c = dr_c;
	}

	public Integer getUsable_c() {
		return usable_c;
	}
	public void setUsable_c(Integer usable_c) {
		this.usable_c = usable_c;
	}
	public ParamCond(Object[][] obj) {super(obj);}
	public ParamCond(){}
}