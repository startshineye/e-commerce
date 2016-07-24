package com.yxm.cms.sys.dict.dao;

import java.util.List;
import java.util.Map;

import com.yxm.cms.sys.dict.model.Dict;
import com.yxm.cms.sys.dict.model.DictCond;

/**
 * 数据字典Dao接口类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
public interface IDictDao {
	/**
	 * 新增
	 */
	int insert(Dict dict);

	/**
	 * 更新
	 */
	int update(Dict dict);

	/**
	 * 更新类型编号
	 */
	int updateType(Dict vo);

	/**
	 * 删除
	 */
	int delete(String id);

	/**
	 * 按ID查找单个实体
	 */
	Dict findById(String id);

	/**
	 * 按条件查询分页列表
	 */
	void queryList(DictCond cond, Map<String, Object> map);

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	List<Dict> queryAllObj(DictCond cond);

	/**
	 * 按条件查询记录个数
	 */
	int findCountByCond(DictCond cond);
}