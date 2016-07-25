package com.yxm.cms.banner.service;
import java.util.List;
import com.yxm.cms.banner.model.Banner;
/**
 * @功能说明：首页面service接口
 * @author gzz
 * @date 2016-07-07
 */
public interface IHomeService {
	/**
	 * @功能说明：查询轮播图列表
	 */
	public List<Banner> queryBanner();
}
