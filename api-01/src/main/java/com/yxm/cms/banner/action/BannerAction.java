package com.yxm.cms.banner.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.cms.banner.model.Banner;
import com.yxm.cms.banner.service.IBannerService;
/**
 * @功能描述：轮播图控制器接口
 * @author yxm
 */
@Controller
@RequestMapping("banner")
public class BannerAction {
    @Autowired
	private IBannerService service;
    /**
     * @功能描述：返回指定终端轮播图信息（json格式文本）
     */
    @RequestMapping("list")
    @ResponseBody
    public List<Banner> queryList(Banner banner){
    	return service.queryList(banner);
    }
}
