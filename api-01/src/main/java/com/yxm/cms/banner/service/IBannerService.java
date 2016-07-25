package com.yxm.cms.banner.service;

import java.util.List;

import com.yxm.cms.banner.model.Banner;

/**
 * 轮播图service接口
 * @author yxm
 *
 */
public interface IBannerService {
	/**
	 * 查询轮播图列表
	 * @param banner
	 * @return
	 */
  public List<Banner> queryList(Banner banner);
}
