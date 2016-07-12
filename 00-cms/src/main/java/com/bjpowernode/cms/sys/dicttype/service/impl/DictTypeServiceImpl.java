package com.bjpowernode.cms.sys.dicttype.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjpowernode.cms.sys.dict.dao.IDictDao;
import com.bjpowernode.cms.sys.dict.model.Dict;
import com.bjpowernode.cms.sys.dicttype.dao.IDictTypeDao;
import com.bjpowernode.cms.sys.dicttype.model.DictType;
import com.bjpowernode.cms.sys.dicttype.model.DictTypeCond;
import com.bjpowernode.cms.sys.dicttype.service.IDictTypeService;

/**
 * 字典类型Service实现类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
@Service
public class DictTypeServiceImpl implements IDictTypeService {
	@Autowired
	private IDictTypeDao dao;

	@Autowired
	private IDictDao dictDao;

	/**
	 * 新增
	 */
	@Override
	public int insert(DictType dicttype) {
		return dao.insert(dicttype);
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	/**
	 * 按ID查找单个实体
	 */
	@Override
	public DictType findById(String id) {
		return dao.findById(id);
	}

	/**
	 * 更新
	 */
	@Override
	@Transactional
	public int update(DictType dicttype) {
		Dict dict = new Dict();
		dict.setType_code(dicttype.getType_code());
		dict.setType_id(dicttype.getId());
		dictDao.updateType(dict);
		return dao.update(dicttype);
	}

	/**
	 * 按条件查询分页列表
	 */
	@Override
	public void queryList(DictTypeCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
	}

	/**
	 * 按条件查询记录个数
	 */
	@Override
	public int findCountByCond(DictTypeCond cond) {
		return dao.findCountByCond(cond);
	}
}