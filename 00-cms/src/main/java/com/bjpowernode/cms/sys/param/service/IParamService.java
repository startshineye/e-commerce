package com.bjpowernode.cms.sys.param.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.cms.sys.param.model.Param;
import com.bjpowernode.cms.sys.param.model.ParamCond;

/**
 * 系统参数Service接口类
 *
 * @author GZZ
 * @date 2014-02-15 11:09:23
 */
public interface IParamService {
	/**
	 * 新增
	 */
	int insert(Param param);

	/**
	 * 删除
	 */
	int delete(String id);

	/**
	 * 按ID查找单个实体
	 */
	Param findById(String id);

	/**
	 * 更新
	 */
	int update(Param param);

	/**
	 * 按条件查询分页列表
	 */
	void queryList(ParamCond cond, Map<String, Object> map);

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	List<Param> queryAllObj(ParamCond cond);

	/**
	 * 按条件查询记录个数
	 */
	int findCountByCond(ParamCond cond);
}