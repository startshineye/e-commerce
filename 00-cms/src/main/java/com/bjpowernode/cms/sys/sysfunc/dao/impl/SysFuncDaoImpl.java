package com.bjpowernode.cms.sys.sysfunc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.sys.sysfunc.dao.ISysFuncDao;
import com.bjpowernode.cms.sys.sysfunc.model.SysFunc;
import com.bjpowernode.cms.sys.sysfunc.model.SysFuncCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.UUIDGenerator;
import com.common.util.Util;

/**
 * @类说明:功能菜单Dao实现类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
@Repository
public class SysFuncDaoImpl extends BaseDao<SysFunc> implements ISysFuncDao {
	private final String insertSql = "INSERT INTO SYS_FUNC (FUNC_ID,NAME,TYPE,IS_LEAF,URL,PARENT_ID,ORDER_CODE,REMARK ) VALUES (?,?,?,?,?,?,?,? ) ";
	private final String updateSql = "UPDATE SYS_FUNC SET NAME=?,TYPE=?,IS_LEAF=?,URL=?,PARENT_ID=?,ORDER_CODE=?,REMARK=? WHERE FUNC_ID=? ";
	private StringBuilder selectSql = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 */
	public SysFuncDaoImpl() {
		selectSql.append("SELECT T.FUNC_ID,T.NAME,T.TYPE,T.IS_LEAF,T.URL,T.PARENT_ID,T.ORDER_CODE,T.REMARK FROM SYS_FUNC T WHERE 1=1");
	}

	/**
	 * @方法说明:新增记录
	 */
	@Override
	public String insert(SysFunc vo) {
		// DataUtil.trim(vo);//去掉字符串型字段值前后的空格
		// Integer
		// id=jdbcTemplate.queryForObject("SELECT SYS_FUNC_SEQ.NEXTVAL FROM
		// DUAL",
		// Integer.class);
		String id = UUIDGenerator.getUUID();
		Object[] params = new Object[] { id, vo.getName(), vo.getType(), vo.getIs_leaf(), vo.getUrl(), vo.getParent_id(), vo.getOrder_code(),
				vo.getRemark() };

		jdbcTemplate.update(insertSql, params);
		return id;
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 */
	@Override
	public int delete(String ids) {
		String updateStr = "DELETE FROM SYS_FUNC WHERE FUNC_ID" + Util.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(updateStr);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 */
	@Override
	public SysFunc findById(String id) {
		StringBuilder sb = new StringBuilder(selectSql);
		sb.append(" AND T.FUNC_ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<SysFunc>(SysFunc.class));
	}

	/**
	 * @方法说明:更新记录
	 */
	@Override
	public int update(SysFunc vo) {
		Object[] params = new Object[] { vo.getName(), vo.getType(), vo.getIs_leaf(), vo.getUrl(), vo.getParent_id(), vo.getOrder_code(),
				vo.getRemark(), vo.getFunc_id() };
		logger.debug(DataUtil.showSql(updateSql, params));// 显示SQL语句
		return jdbcTemplate.update(updateSql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 */
	@Override
	public void queryList(SysFuncCond cond, Map<String, Object> map) {
		StringBuilder sb = new StringBuilder(selectSql);
		sb.append(cond.getCondition());
		logger.debug(DataUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		queryPage(map, sb.toString(), cond, SysFunc.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 */
	@Override
	public List<SysFunc> queryAllObj(SysFuncCond cond) {
		StringBuilder sb = new StringBuilder(selectSql);
		sb.append(cond.getCondition());
		sb.append(" ORDER BY ORDER_CODE");
		// logger.debug(DataUtil.showSql(sb.toString(), cond));//显示SQL语句
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<SysFunc>(SysFunc.class));
	}

	/**
	 * @方法说明:按条件查询记录个数
	 */
	@Override
	public int findCountByCond(SysFuncCond cond) {
		String countSql = "SELECT COUNT(T.FUNC_ID) FROM SYS_FUNC T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}
 

	/**
	 * @方法说明:修改叶子标志
	 */
	@Override
	public int updateLeaf(Object[] obj) {
		String sbsql = "UPDATE  SYS_FUNC SET IS_LEAF=0 WHERE FUNC_ID=? ";
		return jdbcTemplate.update(sbsql, obj);
	}

	/**
	 * @方法说明:修改上级叶子标志
	 */
	@Override
	public int updateParent(Object[] obj) {
		int count;
		String sql = "SELECT COUNT(P.FUNC_ID) FROM SYS_FUNC P WHERE P.PARENT_ID = ?";
		count = jdbcTemplate.queryForObject(sql, obj, Integer.class);
		count = count == 0 ? 1 : 0;
		sql = "UPDATE SYS_FUNC SET IS_LEAF = " + count + " WHERE FUNC_ID = ?";
		return jdbcTemplate.update(sql, obj);
	}
}