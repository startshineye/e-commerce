package com.yxm.cms.cms.banner.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxm.cms.cms.banner.model.Banner;
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
	public String toinsert(@ModelAttribute("banner") Banner  banner) {
    	System.err.println("执行toinsert");
		return "cms/banner/insert";
	}
    /**
     * @功能描述:实现轮播图保存到数据库
     * @return
     */
    @RequestMapping("insert")
   //BindingResult绑定验让结果跟<form:errors path="name" />配合一起使用
	public String insert(Banner banner,BindingResult result) {
    	BannerCond cond =new BannerCond();
    	cond.setName_c(banner.getName());
    	int count = service.queryCount(cond);
    	if(count>0){
    		//如果返回值大于0回到插入页面,并且返回错误信息提示
    		//<form:errors path="name" />
    		//rejectValue第一个参数要与<form:errors path相同
    		//rejectValue第二个参数为空串
    		//rejectValue第三个参数提示信息
    		result.rejectValue("name","轮播图名称不可重复");
    		return "cms/banner/insert";
    	}
    	service.insert(banner);
		//return "redirect:list";
		return "redirect:list";
	}
    /**
     * @功能描述:跳转到update页面
     * @return
     */
    @RequestMapping("toupdate")
	public String toupdate(Map<String,Object> map,int id) {
    	//System.err.println("执行toinsert");
    	map.put("banner",service.findById(id));
		return "cms/banner/update";
	}
    /**
     * @功能描述:实现轮播图保存到数据库
     * @return
     */
    @RequestMapping("update")
   //BindingResult绑定验让结果跟<form:errors path="name" />配合一起使用
	public String update(Banner banner,BindingResult result) {
    	//构造查询条件
    	BannerCond cond =new BannerCond();
    	cond.setName_c(banner.getName());
    	cond.setId_c(banner.getId());
    	int count = service.queryCount(cond);
    	/*if(count>0){
    		//如果返回值大于0回到插入页面,并且返回错误信息提示
    		//<form:errors path="name" />
    		//rejectValue第一个参数要与<form:errors path相同
    		//rejectValue第二个参数为空串
    		//rejectValue第三个参数提示信息
    		result.rejectValue("name","轮播图名称不可重复");
    		return "cms/banner/update";
    	}*/
    	service.update(banner);
		
		return "redirect:list";
	}
	/**
	 * 日期属性编辑器
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}
}












