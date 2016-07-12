package com.bjpowernode.cms.sys.sysfunc.service;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.bjpowernode.cms.sys.sysfunc.model.SysFunc;
import com.bjpowernode.cms.sys.sysfunc.model.SysFuncCond;

/**
 * @类说明:功能菜单Service接口类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
public interface ISysFuncService {

	/**
	 * @方法说明:查找全部子结点(递规)
	 */
	void getSonElement(Element node, String type, SysFuncCond cond, String roleid);

	/**
	 * @方法说明:新增记录
	 */
	String insert(SysFunc sysfunc);

	/**
	 * @方法说明:删除记录(多条)
	 */
	String delete(String id);

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

	String getLimtCondition(String resourceType, String user_id);
}