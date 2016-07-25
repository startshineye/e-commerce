package com.yxm.cms.banner.dao;

import java.util.List;

import com.yxm.cms.banner.model.Banner;

/**
 * 轮播图Dao接口
 * @author yxm
 */
public interface IBannerDao {
   /**
    * 查询轮播图列表
    * @param banner
    * @return
    */
	public List<Banner> queryList(Banner banner);
}
