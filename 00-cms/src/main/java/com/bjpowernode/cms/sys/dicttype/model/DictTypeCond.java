package com.bjpowernode.cms.sys.dicttype.model;

import java.util.Date;

import com.common.condition.BaseCondition;

/**
 * 字典类型条件查询类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public class DictTypeCond extends BaseCondition {
	@Override
	public void addCondition() {
		add(type_code_c, " AND T.TYPE_CODE = ?");
		add(type_name_c, " AND T.TYPE_NAME LIKE ?", 3);
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(ts_c, " AND T.TS = ?");
		add(id_c, " AND T.ID <> ?");
	}

	private String id_c;// 主键
	private Integer type_code_c;// 类型标识
	private String type_name_c;// 类型名称
	private String remark_c;// 备注
	private Date ts_c;// 时间戳

	public String getId_c() {
		return id_c;
	}

	public void setId_c(String id_c) {
		this.id_c = id_c;
	}

	public Integer getType_code_c() {
		return type_code_c;
	}

	public void setType_code_c(Integer type_code_c) {
		this.type_code_c = type_code_c;
	}

	public String getType_name_c() {
		return type_name_c;
	}

	public void setType_name_c(String type_name_c) {
		this.type_name_c = type_name_c;
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
}