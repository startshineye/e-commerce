package com.yxm.cms.sys.roleUser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yxm.cms.sys.roleUser.dao.IRoleUserDao;
import com.yxm.cms.sys.roleUser.model.RoleUser;
import com.yxm.cms.sys.roleUser.model.RoleUserCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.UUIDGenerator;

/**
 * 用户角色关联Dao实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Repository
public class RoleUserDaoImpl extends BaseDao<RoleUser> implements IRoleUserDao {

	/**
	 * 批量插入用户角色关联表数据
	 */
	@Override
	public int[] insertBatch(final List<RoleUser> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO SYS_ROLE_USER (ID,ROLE_ID, USER_ID) VALUES (?,?,?)");
		return jdbcTemplate.batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				RoleUser sysRoleUser = list.get(i);
				ps.setString(1, UUIDGenerator.getUUID());
				ps.setString(2, sysRoleUser.getRole_id());
				ps.setString(3, sysRoleUser.getUser_id());
			}

			public int getBatchSize() {
				return list.size();
			}
		});
	}

	/**
	 * 按角色删除用户角色关联表记录
	 */
	@Override
	public int delete(Object[] obj) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM SYS_ROLE_USER WHERE ROLE_ID = ? ");
		// logger.debug(DataUtil.showSql(sb.toString(), obj));//显示SQL语句
		return jdbcTemplate.update(sb.toString(), obj);
	}

	/**
	 * 是否已关联
	 */
	@Override
	public int HasConnect(Object[] obj) {
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT COUNT(T.ID) FROM SYS_ROLE_USER T WHERE T.ROLE_ID= ? AND T.USER_ID=? ");
		return jdbcTemplate.queryForObject(sbsql.toString(), obj, Integer.class);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)-根据需要替换成自己的SQL
	 */
	@Override
	public List<RoleUser> queryAllObj(RoleUserCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT  u.NAME username ,");
		sb.append("         r.NAME AS ROLEname ,");
		sb.append("         T.TS ,");
		sb.append("         T.REMARK ,");
		sb.append("         T.ROLE_ID");
		sb.append(" FROM    SYS_ROLE_USER T");
		sb.append("         JOIN sys_user u ON t.USER_ID = u.ID");
		sb.append("         JOIN sys_role r ON t.role_id = r.ID");
		sb.append(" WHERE   1 = 1");
		sb.append(cond.getCondition());
		logger.debug(DataUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<RoleUser>(RoleUser.class));
	}
}
