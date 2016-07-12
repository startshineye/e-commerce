package com.bjpowernode.cms.cms.cmsbanner.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.cms.cmsbanner.dao.IBannerDao;
import com.bjpowernode.cms.cms.cmsbanner.model.Banner;
import com.bjpowernode.cms.cms.cmsbanner.model.BannerCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;

@Repository
public class BannerDaoImpl extends BaseDao<Banner> implements IBannerDao {

	@Override
	public int insert(Banner vo) {// 新增数据时可能有一些字段是没有办法给出的,把那样的字段不写在insert语句里
		String sql = "INSERT INTO CMS_BANNER (NAME,ORDER_NUM,PICTURE_PATH,PICTURE_URL,JUMP_URL,"
				+ "REMARK,STATUS,TYPE,TS) VALUES (?,?,?,?,?,?,?,?,?) ";
		Object[] obj = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int update(Banner vo) {// 更新数据时可能有一些字段是没有办法给出的,把那样的字段不写在update语句里
		String sql = "UPDATE CMS_BANNER SET NAME=?,ORDER_NUM=?,PICTURE_PATH=?,PICTURE_URL=?,JUMP_URL=?,"
				+ "REMARK=?,STATUS=?,TYPE=?,TS=? WHERE ID=? ";
		Object[] obj = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs(), vo.getId() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM CMS_BANNER WHERE ID in (" + id + ")";
		return jdbcTemplate.update(sql);
	}

	@Override
	// 用来显示枚举字典表字段的data_value的
	// SELECT
	// T.ID,T.NAME,T.ORDER_NUM,T.PICTURE_PATH,T.PICTURE_URL,T.JUMP_URL,T.REMARK,T.STATUS,
	// T.TYPE,T.CREATE_TIME,T.CREATOR,T.UPDATE_TIME,T.UPDATER,d.data_value
	// type_name
	// FROM CMS_BANNER T join sys_dict d on t.type=d.data_key and type_code=201
	// WHERE 1=1

	public void queryList(BannerCond cond, Map<String, Object> map) {
		String sql = "SELECT T.ID,T.NAME,T.ORDER_NUM,T.PICTURE_PATH,T.PICTURE_URL,T.JUMP_URL,T.REMARK,T.STATUS,"
				+ "T.TYPE,T.TS FROM CMS_BANNER T WHERE 1=1" + cond.getCondition() + " ORDER BY id";
		queryPage(map, sql, cond, Banner.class);// 调用basedao中的分页查询方法//带范型的查询
	}

	@Override
	public Banner findById(int id) {
		String sql = "SELECT T.ID,T.NAME,T.ORDER_NUM,T.PICTURE_PATH,T.PICTURE_URL,T.JUMP_URL,T.REMARK,T.STATUS,"
				+ "T.TYPE,T.TS FROM CMS_BANNER T WHERE ID=?";
		// 一定要确保查询只有一个 ，不能多也不能少
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Banner>(Banner.class));
	}

	// 做验证用的,按条件查询记录的个数
	@Override
	public int findByCond(BannerCond cond) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(1) FROM CMS_BANNER T WHERE 1=1");
		sb.append(cond.getCondition());
		logger.info(DataUtil.showSql(sb.toString(), cond.getArray()));// 显示查询语句
		return jdbcTemplate.queryForObject(sb.toString(), cond.getArray(), Integer.class);
	}

	@Override
	// 更新轮播图状态
	public int updateSta(Banner banner) {
		String sql = "update cms_banner set status=? where id=?";
		Object[] obj = new Object[] { banner.getStatus(), banner.getId() };
		return jdbcTemplate.update(sql, obj);
	}

}
