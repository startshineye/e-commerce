package com.yxm.cms.sys.authority.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.yxm.cms.sys.authority.dao.IAuthoDao;
import com.yxm.cms.sys.authority.model.Autho;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.UUIDGenerator;

/**
 * @功能描述:资源授权Dao实现类
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
@Repository
public class AuthoDaoImpl extends BaseDao<Autho> implements IAuthoDao {

	private String insertSql = "INSERT INTO SYS_AUTH (AUTHORITY_ID,RESOURCE_ID,RESOURCE_TYPE,VISITOR_ID) VALUES (?,?,?,?)";
	private String deleteSql = "DELETE FROM SYS_AUTH WHERE VISITOR_ID = ? AND RESOURCE_TYPE= ?";

	/**
	 * @功能描述:批量插入权限表数据
	 */
	@Override
	public int[] insertBatch(final List<Autho> list) {
		return jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Autho sysAuthority = list.get(i);
				ps.setString(1, UUIDGenerator.getUUID());
				ps.setString(2, sysAuthority.getResource_id());
				ps.setString(3, sysAuthority.getResource_type());
				ps.setString(4, sysAuthority.getVisitor_id());

			}

			public int getBatchSize() {
				return list.size();
			}
		});
	}

	/**
	 * @功能描述:按访问者ID删除权限记录
	 */
	@Override
	public int delete(Object[] obj) {
		logger.debug(DataUtil.showSql(deleteSql, obj));// 显示SQL语句
		return jdbcTemplate.update(deleteSql, obj);
	}

	/**
	 * @功能描述:是否已授权
	 */
	@Override
	public int HasLimit(Object[] obj, String condition) {
		StringBuffer sbsql = new StringBuffer(
				"SELECT COUNT(AUTHORITY_ID) FROM SYS_AUTH WHERE VISITOR_ID =? AND RESOURCE_ID =?");
		sbsql.append(condition);
		// logger.debug(DataUtil.showSql(sbsql.toString(), obj));//显示SQL语句
		return jdbcTemplate.queryForObject(sbsql.toString(), obj, Integer.class);
	}

	/**
	 * @功能描述:拼加权限字符串
	 */
	@Override
	public String getLimtCondition(String resourceType, String userId) {
		StringBuffer Condition = new StringBuffer();// ID字符串
		StringBuffer limitStr = new StringBuffer();//
		// logger.debug(resourceType+userId);
		if (StringUtils.isNotEmpty(userId)) {// 如果userId不为空
			if (!userId.equals("1")) {// admin以外的用户
				StringBuffer sb = new StringBuffer();
				sb.append(" SELECT au.resource_id ");
				sb.append("  FROM sys_role_user ru ");
				sb.append("  JOIN sys_role r ON ru.role_id = r.id ");
				sb.append("  JOIN sys_user u ON ru.user_id = u.id ");
				sb.append("  JOIN sys_auth au ON au.visitor_id = r.id ");
				sb.append(" WHERE u.id = ?");
				sb.append("   AND au.resource_type = ?");
				logger.debug(DataUtil.showSql(sb.toString(), new Object[] { userId, resourceType }));
				List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(),
						new Object[] { userId, resourceType });
				if (list.size() > 0) {
					limitStr = limitStr.append(" IN (");
					for (Map<String, Object> map : list) {
						Condition = Condition.append(",'" + map.get("resource_id") + "'");
					}
					Condition = Condition.delete(0, 1);
					limitStr.append(Condition);
					limitStr.append(") ");
				} else {
					limitStr = new StringBuffer(" AND 1=0 ");
				}
			}

		} else {
			limitStr = new StringBuffer(" AND 1=0 ");

			logger.error("用户ID为空,可能登录已过期或没有登录或者用户ID有误！");
		}

		return limitStr.toString();
	}

	/**
	 * @功能描述:按钮权限
	 */
	@Override
	public boolean getButtonLimt(String userId, String key) {
		boolean hasLimit = false;
		if (StringUtils.isNotEmpty(userId)) {// 如果userId不为空
			if (!userId.equals("1")) {// admin以外的用户
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT count(au.resource_id) FROM sys_role_user ru");
				sb.append("  JOIN sys_role r ON ru.role_id = r.id");
				sb.append("  JOIN sys_user u ON ru.user_id = u.id");
				sb.append("  JOIN sys_auth au ON au.visitor_id = r.id");
				sb.append("  JOIN sys_func f ON f.func_id = au.resource_id");
				sb.append(" WHERE f.url =? AND u.id =? AND f.type = 'BUTTON'");
				// logger.info(DataUtil.showSql(sb.toString(), new Object[] {
				// key, userId }));
				int count = jdbcTemplate.queryForObject(sb.toString(), new Object[] { key, userId }, Integer.class);
				if (count > 0) {
					hasLimit = true;
				}
			}
		} else {
			logger.error("用户ID为空,可能登录已过期或没有登录或者用户ID有误！");
		}
		return hasLimit;
	}

}
