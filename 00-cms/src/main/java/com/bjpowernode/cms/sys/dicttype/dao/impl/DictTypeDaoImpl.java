package com.bjpowernode.cms.sys.dicttype.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.sys.dicttype.dao.IDictTypeDao;
import com.bjpowernode.cms.sys.dicttype.model.DictType;
import com.bjpowernode.cms.sys.dicttype.model.DictTypeCond;
import com.common.dao.BaseDao;
import com.common.util.UUIDGenerator;
import com.common.util.Util;

/**
 * 字典类型Dao实现类
 *
 * @author GZZ
 * @date 2014-02-15 00:11:52
 */
@Repository
public class DictTypeDaoImpl extends BaseDao<DictType> implements IDictTypeDao {

	private final String insertSql = "INSERT INTO SYS_DICT_TYPE (ID,TYPE_CODE,TYPE_NAME,REMARK ) VALUES (?,?,?,?) ";
	private final String selectSql = "SELECT T.TYPE_CODE,T.ID,T.TYPE_NAME,T.REMARK FROM SYS_DICT_TYPE T WHERE 1=1";
	private final String updateSql = "UPDATE SYS_DICT_TYPE SET TYPE_CODE=?,TYPE_NAME=?,REMARK=? WHERE ID=? ";

	/**
	 * 新增
	 */
	@Override
	public int insert(DictType vo) {
		Object[] params = new Object[] { UUIDGenerator.getUUID(), vo.getType_code(), vo.getType_name(),
				vo.getRemark(), };
		return jdbcTemplate.update(insertSql, params);
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(String ids) {
		String updateStr = "DELETE FROM SYS_DICT_TYPE WHERE ID " + Util.ArrayToIn(ids);
		return jdbcTemplate.update(updateStr);
	}

	/**
	 * 按ID查找单个实体
	 */
	@Override
	public DictType findById(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectSql + " AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id },
				new BeanPropertyRowMapper<DictType>(DictType.class));
	}

	/**
	 * 更新
	 */
	@Override
	public int update(DictType vo) {
		Object[] params = new Object[] { vo.getType_code(), vo.getType_name(), vo.getRemark(), vo.getId() };
		return jdbcTemplate.update(updateSql, params);
	}

	/**
	 * 按条件查询分页列表
	 */
	@Override
	public void queryList(DictTypeCond cond, Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectSql + cond.getCondition());
		queryPage(map, sb.toString(), cond, DictType.class);
	}

	/**
	 * 按条件查询记录个数
	 */
	@Override
	public int findCountByCond(DictTypeCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM SYS_DICT_TYPE T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}
}