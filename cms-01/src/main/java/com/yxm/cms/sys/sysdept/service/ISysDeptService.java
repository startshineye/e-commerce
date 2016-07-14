package com.yxm.cms.sys.sysdept.service;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.yxm.cms.sys.sysdept.model.SysDept;
import com.yxm.cms.sys.sysdept.model.SysDeptCond;

/**
 * @类说明:部门Service接口类
 *
 * @author:高振中
 * @date:2014-07-29 16:49:42
 */
public interface ISysDeptService {

	/**
	 * @方法说明:查找全部子结点(递规)
	 */
	void getSonElement(Element node, String type, SysDeptCond cond, String roleid);

	/**
	 * @方法说明:新增记录
	 */
	String insert(SysDept sysdept);

	/**
	 * @方法说明:删除记录(多条)
	 */
	String delete(String id);

	/**
	 * @方法说明:按ID查找单个实体
	 */
	SysDept findById(String id);

	/**
	 * @方法说明:更新记录
	 */
	int update(SysDept sysdept);

	/**
	 * @方法说明:按条件查询分页列表
	 */
	void queryList(SysDeptCond cond, Map<String, Object> map);

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	List<SysDept> queryAllObj(SysDeptCond cond);

	/**
	 * @方法说明:按条件查询记录个数
	 */
	int findCountByCond(SysDeptCond cond);
}