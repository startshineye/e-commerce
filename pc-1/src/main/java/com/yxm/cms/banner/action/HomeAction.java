package com.yxm.cms.banner.action;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yxm.cms.banner.service.IHomeService;
/**
 * @功能说明：首页面控制器类
 * @author gzz
 * @date 2016-07-07
 */
@Controller
public class HomeAction {
	@Autowired
	private IHomeService service;//首页service接口
	@RequestMapping("home")
	public String toHome(Map<String ,Object> map){
		//map.put("bannerList", service.queryBanner());
		return "index";
	}
}
