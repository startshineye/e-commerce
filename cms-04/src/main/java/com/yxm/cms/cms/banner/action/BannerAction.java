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
 * @功能描述：轮播图控制器类
 * @author yxm
 */
@Controller
@RequestMapping("banner")
public class BannerAction {

	@Autowired
	private IBannerService service;// 注入轮播图servcie接口

	/**
	 * @功能描述：按条件取轮播图列表
	 */
	@RequestMapping("list")
	public String queryList(@ModelAttribute("cond") BannerCond cond, Map<String, Object> map) {
		service.queryList(map,cond);
		return "cms/banner/list";
	}
	/**
	 * @功能描述：跳转到新增页面
	 */
	@RequestMapping("toinsert")
	public String toinsert(@ModelAttribute("banner") Banner banner) {
		return "cms/banner/insert";
	}
	/**
	 * @功能描述：新增--保存数据
	 */
	@RequestMapping("insert")
	// BindingResult绑定验让结果跟<form:errors path="name" />配合一起使用
	public String insert(Banner banner, BindingResult result) {
		validate(banner,result,1);//1表示用insert方法
		if(result.hasErrors()){
			// 跳回到新增页面去
			return "cms/banner/insert";
		}
		service.insert(banner);
		return "redirect:list";
	}
	/**
	 * @功能描述：跳转到修改页面
	 */
	@RequestMapping("toupdate")
	public String toupdate(int id, Map<String, Object> map) {
		map.put("banner", service.findById(id));
		return "cms/banner/update";
	}
	/**
	 * @功能描述：修改--保存数据
	 */
	@RequestMapping("update")
	// BindingResult绑定验让结果跟<form:errors path="name" />配合一起使用
	public String update(Banner banner, BindingResult result) {
		validate(banner,result,2);//2表示用update方法
		if(result.hasErrors()){
			return "cms/banner/update";
		}
		service.update(banner);
		return "redirect:list";
	}
	/**
	 * @功能描述：删除数据
	 */
	@RequestMapping("delete")
	// BindingResult绑定验让结果跟<form:errors path="name" />配合一起使用
	public String delete(int id) {
		service.delete(id);
		return "redirect:list";
	}
	/**
	 * @功能描述：按条件取轮播图列表详情
	 */
	@RequestMapping("detail")
	public String detail(int id, Map<String, Object> map){
		map.put("banner", service.findById(id));
		return "cms/banner/detail";
	}
	/**
	 * @功能描述:后台验证.
	 * @param banner
	 * @param result
	 * @param method
	 */
	private void validate(Banner banner, BindingResult result,int method){
		// 构造查询条件
		BannerCond cond = new BannerCond();
		cond.setName_c(banner.getName());
		if(method==2){
			cond.setId_c(banner.getId());
		}// 调用查询记录个数的方法
		int count = service.queryCount(cond);
		if (count > 0) {
			// <form:errors path="name" />
			// rejectValue第一个参数要与form:errors path相同
			// rejectValue第二个参数为空串
			// rejectValue第三个参数提示信息
			result.rejectValue("name", "", "轮播图名称不能重复！");
		}
	}
	/**
	 * @功能描述：日期属性编辑器 把日期型字段转换成date类型
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}
}
