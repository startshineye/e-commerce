package com.yxm.cms.cms.banner.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxm.cms.cms.banner.model.BannerCond;
import com.yxm.cms.cms.banner.service.IBannerService;

/**
 * @功能描述:轮播图的controller
 * @author yxm
 */

//通常情况：Controller 只做验证与跳转，页面数据初始化
@Controller
@RequestMapping("banner")
public class BannerAction {
	
    @Autowired
	private IBannerService service;//注入轮播图service接口
   
    /**
     * @功能描述:获取轮播图列表
     * @return
     */
    @RequestMapping("list")
	public String queryList(@ModelAttribute("cond") BannerCond cond, Map<String, Object> map) {
		//service.queryList(cond, map);
    	service.queryList(map, cond);
		return "cms/banner/list";
	}
    
    /**
     * @功能描述:跳转到insert页面
     * @return
     */
    @RequestMapping("toinsert")
	public String toinsert() {
		return "cms/banner/insert";
	}
}












