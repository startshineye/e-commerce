package com.yxm.cms.sys.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yxm.cms.sys.user.dao.IUserDao;
import com.yxm.cms.sys.user.model.User;
import com.yxm.cms.sys.user.model.UserCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.UUIDGenerator;
import com.common.util.Util;

/**
 * 用户Dao实现类
 * 
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

	private String insertSql = "INSERT INTO SYS_USER (ID,DEPT_ID,LOGIN_ID,PASSWORD,SEX_STAT,NAME,TEL,REMARK) VALUES (?,?,?,?,?,?,?,?)";
	private String updateSql = "UPDATE SYS_USER SET DEPT_ID=?,LOGIN_ID=?,PASSWORD=?,SEX_STAT=?,NAME=?,TEL=?,REMARK=? WHERE ID=?";
	private StringBuilder selectSql = new StringBuilder();

	/**
	 * 构造方法用于拼加SELECT-SQL及其它的初始化工作
	 */
	public UserDaoImpl() {
		selectSql.append("SELECT T.ID,T.DEPT_ID,T.LOGIN_ID,T.PASSWORD,T.SEX_STAT,T.NAME,");
		selectSql.append(" T.TEL,T.REMARK,O.NAME ORG_NAME");
		selectSql.append(" FROM SYS_USER T JOIN SYS_dept O ON T.DEPT_ID = O.ID");
		selectSql.append(" WHERE 1 = 1");
	}

	/**
	 * 新增
	 */
	@Override
	public int insert(User vo) {
		Object[] params = new Object[] { UUIDGenerator.getUUID(), vo.getDept_id(), vo.getLogin_id(), vo.getPassword(), vo.getSex_stat(), vo.getName(),
				vo.getTel(), vo.getRemark() };
		logger.debug(DataUtil.showSql(insertSql, params));// 显示SQL语句
		return jdbcTemplate.update(insertSql, params);
	}

	/**
	 * 新增
	 */
	@Override
	public int insertCustomer(User vo) {
		Object[] params = new Object[] { vo.getId(), vo.getDept_id(), vo.getLogin_id(), vo.getPassword(), vo.getSex_stat(), vo.getName(), vo.getTel(),
				vo.getRemark(), };
		logger.debug(DataUtil.showSql(insertSql, params));// 显示SQL语句
		return jdbcTemplate.update(insertSql, params);
	}

	/**
	 * 物理删除
	 */
	@Override
	public int delete(String ids) {
		String updateStr = "DELETE FROM SYS_USER WHERE ID" + Util.ArrayToIn(ids);
		return jdbcTemplate.update(updateStr);
	}

	/**
	 * 按ID查找单个实体
	 */
	@Override
	public User findById(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectSql + " AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
	}

	/**
	 * 更新
	 */
	@Override
	public int update(User vo) {
		Object[] params = new Object[] { vo.getDept_id(), vo.getLogin_id(), vo.getPassword(), vo.getSex_stat(), vo.getName(), vo.getTel(),
				vo.getRemark(), vo.getId() };
		return jdbcTemplate.update(updateSql, params);
	}

	/**
	 * 重置密码
	 */
	@Override
	public int updatePas(User vo) {
		String sql = "UPDATE SYS_USER SET  password=?  WHERE ID=? ";
		Object[] params = new Object[] { vo.getPassword(), vo.getId() };
		return jdbcTemplate.update(sql, params);
	}

	/**
	 * 按条件查询分页列表
	 */
	@Override
	public void queryList(UserCond cond, Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectSql + cond.getCondition());
		sb.append(" order by T.DEPT_ID desc");
		queryPage(map, sb.toString(), cond, User.class);// (不使用范型)
	}

	/**
	 * 按条件查询不分页列表(使用类型)
	 */
	@Override
	public List<User> queryAllObj(UserCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectSql + cond.getCondition());
		// logger.debug(DataUtil.showSql(sb.toString(), cond.getArray()));
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<User>(User.class));
	}

	/**
	 * 按条件查询记录个数
	 */
	@Override
	public int findCountByCond(UserCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM SYS_USER T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

}