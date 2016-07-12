package com.bjpowernode.cms.cms.cmsbanner.dao;

import java.util.Map;

import com.bjpowernode.cms.cms.cmsbanner.model.Banner;
import com.bjpowernode.cms.cms.cmsbanner.model.BannerCond;

/**
 * @功能说明：轮播图的dao接口类
 * @author gzz
 * @date 2016-04-23
 */
public interface IBannerDao {

	int insert(Banner banner);

	int update(Banner banner);
	//更新轮播图状态
	int updateSta(Banner banner);
	
	int delete(String id);

	void queryList(BannerCond cond, Map<String, Object> map);

	Banner findById(int id);

	int findByCond(BannerCond cond);

}
