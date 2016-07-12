package com.bjpowernode.cms.sys.sysfunc.dao;
import java.util.List;
import java.util.Map;

import com.bjpowernode.cms.sys.sysfunc.model.SysFunc;
import com.bjpowernode.cms.sys.sysfunc.model.SysFuncCond;

/**
 * @类说明:功能菜单Dao接口类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
public interface ISysFuncDao {

	/**
	 * @方法说明:新增记录
	 */
	 String insert(SysFunc sysfunc);

	/**
	 * @方法说明:物理删除记录(多条)
	 */
	 int delete(String id);

	/**
	 * @方法说明:按ID查找单个实体
	 */
	 SysFunc findById(String id);

	/**
	 * @方法说明:更新记录
	 */
	 int update(SysFunc sysfunc);

	/**
	 * @方法说明:按条件查询分页列表
	 */
	 void queryList(SysFuncCond cond, Map<String, Object> map);

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	 List<SysFunc> queryAllObj(SysFuncCond cond);

	/**
	 * @方法说明:按条件查询记录个数
	 */
	 int findCountByCond(SysFuncCond cond);

	/**
	 * @方法说明:修改叶子标志
	 */
	 int updateLeaf(Object[] obj);

	/**
	 * @方法说明:修改上级叶子标志
	 */
	 int updateParent(Object[] obj);
}