package com.yxm.cms.sys.sysfunc.model;
import com.common.condition.BaseCondition;

/**
 * @类说明:功能菜单查询条件类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
public class SysFuncCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 */
	@Override
	public void addCondition() { 
		add(func_id_c, " AND T.FUNC_ID = ?");
		add(name_c, " AND T.NAME = ?");
		add(type_c, " AND T.TYPE = ?");
		add(is_leaf_c, " AND T.IS_LEAF = ?");
		add(url_c, " AND T.URL = ?");
		add(parent_id_c, " AND T.PARENT_ID = ?");
		add(order_code_c, " AND T.ORDER_CODE = ?");
		add(remark_c, " AND T.REMARK = ?");
		add(limit);
	}

	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	//页面查询条件的ID名称
	private String func_id_c;// 主键
	private String name_c;// 名称
	private String type_c;// 类型
	private Integer is_leaf_c;// 是否叶子
	private String url_c;// 地址或按钮键
	private String parent_id_c;// 父结点
	private String order_code_c;// 排序编号
	private String remark_c;// 备注
	private String limit;
	public String getFunc_id_c() {
		return func_id_c;
	}
	public void setFunc_id_c(String func_id_c) {
		this.func_id_c = func_id_c;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
	}
	public String getType_c() {
		return type_c;
	}
	public void setType_c(String type_c) {
		this.type_c = type_c;
	}
	public Integer getIs_leaf_c() {
		return is_leaf_c;
	}
	public void setIs_leaf_c(Integer is_leaf_c) {
		this.is_leaf_c = is_leaf_c;
	}
	public String getUrl_c() {
		return url_c;
	}
	public void setUrl_c(String url_c) {
		this.url_c = url_c;
	}
	public String getParent_id_c() {
		return parent_id_c;
	}
	public void setParent_id_c(String parent_id_c) {
		this.parent_id_c = parent_id_c;
	}
	public String getOrder_code_c() {
		return order_code_c;
	}
	public void setOrder_code_c(String order_code_c) {
		this.order_code_c = order_code_c;
	}
	public String getRemark_c() {
		return remark_c;
	}
	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}
	public SysFuncCond(Object[][] obj) {super(obj);}
	public SysFuncCond(){}
}