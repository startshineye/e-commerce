package com.yxm.cms.banner.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.common.http.HttpUtil;
import com.common.json.JsonUtil;
import com.yxm.cms.banner.model.Banner;
import com.yxm.cms.banner.service.IHomeService;
/**
 * @功能说明：首页面service实现类
 * @author gzz
 * @date 2016-07-07
 */
@Service
public class HomeService implements IHomeService {
	private Log logger = LogFactory.getLog(getClass());// 日志工具
	@Autowired
	private HttpUtil httpUtil;// http请求辅助工具

	@Override
	public List<Banner> queryBanner() {

		// 从api请求json文本数据
		// String json = httpUtil.get("banner/list?type=1");
		Map<String, String> params = new HashMap<>();
		params.put("type", "1");
		String json = httpUtil.post("banner/list", params);
		// 把文本转成list<banner>
		List<Banner> bannerList = JsonUtil.jsonToList(json, Banner[].class);

		return bannerList;
	}

}
