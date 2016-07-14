package com.yxm.cms.cms.banner.service;

import java.util.Map;

import com.yxm.cms.cms.banner.dao.IBannerDao;
import com.yxm.cms.cms.banner.model.Banner;
import com.yxm.cms.cms.banner.model.BannerCond;
/**
 * @功能说明:轮播图的service接口
 * @author yxm
 */
public interface IBannerService {
	/**
	    * @功能:插入轮播图
	    * @param banner
	    * @return
	    */
		int insert(Banner banner);
		/**
	    * @功能:根据id删除轮播图
	    * @param banner
	    * @return
	    */
		int delete(int id);
	    /**
	    * @功能:更改轮播图
	    * @param banner
	    * @return
	    */
		int update(Banner banner);
	   /**
	    * @功能:分页查询轮播图
	    * @param banner
	    * @return
	    */
		public void queryList(Map<String, Object> map, BannerCond cond);
		
	   /**
	    * @功能:根据id查询轮播图
	    * @param banner
	    * @return
	    */
		Banner findById(int id);
}
