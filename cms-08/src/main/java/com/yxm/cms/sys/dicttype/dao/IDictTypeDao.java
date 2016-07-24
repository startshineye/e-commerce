package com.yxm.cms.sys.dicttype.dao;

import java.util.Map;

import com.yxm.cms.sys.dicttype.model.DictType;
import com.yxm.cms.sys.dicttype.model.DictTypeCond;

/**
 * 字典类型Dao接口类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public interface IDictTypeDao {
	/**
	 * 新增
	 */
	int insert(DictType dicttype);

	/**
	 * 更新
	 */
	int update(DictType dicttype);

	/**
	 * 删除
	 */
	int delete(String id);

	/**
	 * 按ID查找单个实体
	 */
	DictType findById(String id);

	/**
	 * 按条件查询分页列表
	 */
	void queryList(DictTypeCond cond, Map<String, Object> map);

	/**
	 * 按条件查询记录个数
	 */
	int findCountByCond(DictTypeCond cond);
}