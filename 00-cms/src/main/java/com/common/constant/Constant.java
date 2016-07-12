package com.common.constant;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bjpowernode.cms.sys.param.dao.IParamDao;
import com.bjpowernode.cms.sys.param.model.Param;
import com.bjpowernode.cms.sys.param.model.ParamCond;

@Component
public class Constant {

	// 登录时放到Session中使用
	public static String LOGIN_USER = "LOGIN_USER"; // 用户登录信息，存放user对象
	public static Integer PAGE_SIZE = 15;// 每页记录个数
	public static String OUT_MSG = "登录已过期,请重新登录!";// 登录过期提示信息
	public static String DATABASE;
	public static String DBUSER;

	@Autowired
	private IParamDao dao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Log logger = LogFactory.getLog(getClass());

	@PostConstruct
	public void init() {
		DatabaseMetaData dbMetaData;
		try {
			dbMetaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
			DATABASE = dbMetaData.getDatabaseProductName();
			ResultSet rs = dbMetaData.getTables(null, null, null, null);
			rs.next();
			DBUSER = rs.getString(1);
		} catch (SQLException e) {
			logger.error("获取数据产品名称时出错");
			e.printStackTrace();
		}
		initPageSize();
	}

	public void initPageSize() {
		Param param = dao.findByCond(new ParamCond(new Object[][] { { "param_key_c", "PAGE_SIZE" } }));
		if (!"".equals(param.getParam_value())) {
			PAGE_SIZE = Integer.valueOf(param.getParam_value());
			//logger.debug(PAGE_SIZE);
		}
	}
}
