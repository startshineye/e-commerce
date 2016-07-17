package com.yxm.cms.sys.param.dao;

import java.util.List;
import java.util.Map;

import com.yxm.cms.sys.param.model.Param;
import com.yxm.cms.sys.param.model.ParamCond;

/**
 * 系统参数Dao接口类
 * 
 * @author GZZ
 * @date 2014-02-15 11:09:23
 */
public interface IParamDao {
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
	 * 按键去更新值
	 */
	int updateByKey(Param vo);

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

	/**
	 * 按条件查找单个实体
	 */
	Param findByCond(ParamCond cond);
}