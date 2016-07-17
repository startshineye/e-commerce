package com.yxm.cms.cms.banner.dao.impl;

import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.yxm.cms.cms.banner.dao.IBannerDao;
import com.yxm.cms.cms.banner.model.Banner;
import com.yxm.cms.cms.banner.model.BannerCond;

/**
 * @功能说明:轮播图的dao实现类
 * @author yxm
 */
//实现BaseDao使用模板方法模式
@Repository
public class BannerDaoImpl extends BaseDao<Banner> implements IBannerDao{
	@Override
	public int insert(Banner vo) {
		String sql = "insert into cms_banner(name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{vo.getName(),vo.getOrder_num(),vo.getPicture_path(),vo.getPicture_url(),vo.getJump_url(),vo.getRemark(),vo.getStatus(),vo.getType(),vo.getTs()};
		return jdbcTemplate.update(sql,params);
	}
	@Override
	public int delete(int id) {
		String sql = "delete from cms_banner where id = ?";
		Object[] params = new Object[]{id};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int update(Banner vo) {
		System.err.println("dao的update");
		System.err.println(vo);
		String sql = "update cms_banner set name=?,order_num=?,picture_path=?,picture_url=?,jump_url=?,remark=?, status=?,type=?,ts=? where id=?";
		Object[] params = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs(), vo.getId() };
		return jdbcTemplate.update(sql, params);
        /*String sql = "update cms_banner set name = ?,order_num = ?,picture_path = ?,picture_url = ?,jump_url = ?,remark = ?,status = ?,type = ?,ts = ? where id = ?";
		Object[] params = new Object[]{vo.getName(),vo.getOrder_num(),vo.getPicture_path(),vo.getPicture_url(),vo.getJump_url(),vo.getRemark(),vo.getStatus(),vo.getStatus(),vo.getType(),vo.getTs()};
		return jdbcTemplate.update(sql, params);*/
	}
	
	@Override
	public void queryList(Map<String,Object> map,BannerCond cond) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts from cms_banner");
		sql.append(" where 1=1 ");
		sql.append(cond.getCondition());//拼接查询条件
		//此处map是接收执行结果后的值,相当于request
		queryPage(map, sql.toString(), cond, Banner.class);
	}
	@Override
	public Banner findById(int id) {
		String sql = "select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts from cms_banner where id= ?";
		Object[] params = new Object[]{id};
		return jdbcTemplate.queryForObject(sql,params,new BeanPropertyRowMapper<>(Banner.class));
	}
	@Override
	public int queryCount(BannerCond cond){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from cms_banner where 1=1");
        sql.append(cond.getCondition());
        logger.info(DataUtil.showSql(sql.toString(), cond.getArray()));
		return jdbcTemplate.queryForObject(sql.toString(),cond.getArray(),Integer.class);
	}
	@Override
	public int updateStatus(Banner vo) {
		String sql = "update cms_banner set status=? where id=?";
		Object[] params = new Object[]{vo.getId()};
		return jdbcTemplate.update(sql,params);
	}

	/*@Override
	public List<Banner> queryList(Banner banner) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
