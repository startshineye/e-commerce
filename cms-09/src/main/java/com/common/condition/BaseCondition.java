package com.common.condition;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.common.constant.Constant;
import com.common.util.DataUtil;

/**
 * @功能说明:拼加页面查询条件的基础类
 * @author gzz
 */
public abstract class BaseCondition {
	private Log logger = LogFactory.getLog(getClass());// 日志类
	private List<Object> paramList = new ArrayList<Object>();// 参数值
	private StringBuffer condition = new StringBuffer();// 条件语句

	private Integer pageSize = Constant.PAGE_SIZE;// 页大小(每页记录条)
	private Integer rowCount;// 记录总数
	private Integer pageCount;// 总页数
	private Integer curPage = 1;// 当前页码

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(String类型)
	 */
	protected void add(String value, String strSQL) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(Long类型)
	 */

	protected void add(Long value, String strSQL) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(Boolean类型)
	 */
	protected void add(Boolean value, String strSQL) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(BigDecimal类型)
	 */
	protected void add(BigDecimal value, String strSQL) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(Integer类型)
	 */
	protected void add(Integer value, String strSQL) {
		if (null != value && !"".equals(strSQL) && null != strSQL) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件使用等于大于小于....运算符(Date类型)
	 */
	protected void add(Date value, String strSQL) {
		if (null != value && !"".equals(strSQL) && null != strSQL) {
			condition.append(" " + strSQL);
			paramList.add(value);
		}
	}

	/**
	 * @功能: 拼加条件
	 */
	protected void add(String strSQL) {
		if (null != strSQL && !"".equals(strSQL)) {
			condition.append(" " + strSQL);
		}
	}

	/**
	 * @功能: 拼加条件使用like关键字模糊查询时
	 * 
	 * @param value
	 *            :属性名称
	 * @param strSQL
	 *            :参数SQL字符
	 * @param posLike
	 *            :字句中百分号出现位置
	 * @return strSQL:拼加后SQL字符包括占位符
	 */
	protected void add(String value, String strSQL, int pos) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
			if (pos == 1) {
				paramList.add("%" + value);
			} else if (pos == 2) {
				paramList.add(value + "%");
			} else if (pos == 3) {
				paramList.add("%" + value + "%");
			}
		}
	}

	/**
	 * @功能: 拼加IN字句条件
	 */
	protected void addIn(String value, String strSQL) {
		if (null != strSQL && null != value && !"".equals(strSQL) && !"".equals(value)) {
			condition.append(" " + strSQL);
		}
	}

	/**
	 * @功能: 将List转为数组
	 */
	public Object[] getArray() {
		return paramList.toArray();
	}

	/**
	 * @功能: 取条件字符串
	 */
	public String getCondition() {
		// 清除查询条件
		condition.setLength(0);
		paramList.clear();

		addCondition();
		return condition.toString();
	}

	/**
	 * @功能: 拼加条件方法
	 */
	public abstract void addCondition();

	public BaseCondition() {
	}

	public BaseCondition(Object[][] obj) {
		Method method;
		Class<?> paraClass;
		try {
			for (Object[] o : obj) {
				if (o[1].getClass().getName().contains("Integer")) {
					paraClass = Integer.class;
				} else if (o[1].getClass().getName().contains("Date")) {
					paraClass = Date.class;
				} else {
					paraClass = String.class;
				}
				method = this.getClass().getDeclaredMethod("set" + DataUtil.firstUpper(o[0].toString()), paraClass);
				method.invoke(this, o[1]);
			}
		} catch (Exception e) {
			logger.error("构造条件赋值时发生的错误,请核对条件字段名称.");
			e.printStackTrace();
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
}
