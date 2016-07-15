package com.yxm.cms.sys.sysdept.dao;

import java.util.List;
import java.util.Map;

import com.yxm.cms.sys.sysdept.model.SysDept;
import com.yxm.cms.sys.sysdept.model.SysDeptCond;

/**
 * @类说明:部门Dao接口类
 *
 * @author:高振中
 * @date:2014-07-29 16:49:42
 */
public interface ISysDeptDao {

	/**
	 * @方法说明:新增记录
	 */
	 String insert(SysDept sysdept);

	/**
	 * @方法说明:物理删除记录(多条)
	 */
	 int delete(String id);

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

	/**
	 * @方法说明:逻辑删除记录(多条)
	 */
	 int deleteLogic(String id);

	/**
	 * @方法说明:修改叶子标志
	 */
	 int updateLeaf(Object[] obj);

	/**
	 * @方法说明:修改上级叶子标志
	 */
	 int updateParent(Object[] obj);
}