package com.yxm.cms.sys.sysfunc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxm.cms.sys.sysfunc.model.SysFunc;
import com.yxm.cms.sys.sysfunc.model.SysFuncCond;
import com.yxm.cms.sys.sysfunc.service.ISysFuncService;
import com.common.util.Util;

/**
 * @类说明:功能菜单控制器类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
@Controller
@RequestMapping("/sysfunc")
public class SysFuncController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISysFuncService service; // 功能菜单Service

	/**
	 * @方法说明:生成树型XML
	 */
	@RequestMapping("/tree")
	public void showTree(@RequestParam String id, @RequestParam(required = false) String selectID, @RequestParam(required = false) String type, HttpServletResponse response,
			@RequestParam(required = false) String roleid, @RequestParam(required = false) String ftype, @RequestParam(required = false) String open, HttpSession session) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("tree");// tree
		root.addAttribute("id", "0");
		Element node = root.addElement("item");// root
		Element userdata = node.addElement("userdata");
		userdata.addAttribute("name", "data01").setText("");
		SysFunc sysfunc = service.findById(id);
		String login_id = Util.getUserId(session);
		String condition = service.getLimtCondition("Menu", login_id);
		node.addAttribute("id", id).addAttribute("text", sysfunc.getName()).addAttribute("open", "1").addAttribute("call", "1").addAttribute("select",
				"1");
		if (("radio").equals(type)) {
			root.addAttribute(type, "0");
			node.addAttribute(type, "0");
		}
		SysFuncCond cond = new SysFuncCond();
		cond.setParent_id_c(id);
		cond.setType_c(ftype);
		cond.setLimit(condition);
		service.getSonElement(node, type, cond, roleid);
 
		if (StringUtils.isNotEmpty(open)) {// 反回页面时选中指定结点
			Element el = (Element) document.selectSingleNode("//item[@id='" + open + "']");
			el.addAttribute("select", "1").addAttribute("call", "1");// 指定打开并选中的结点,执行本结点对应的click方法
		}
		
		if (StringUtils.isNotEmpty(selectID)) {// 反回页面时选中指定结点
			logger.info(selectID);
			Element el = (Element) document.selectSingleNode("//item[@id='" + selectID + "']");
			el.addAttribute("select", "1").addAttribute("call", "1");// 指定打开并选中的结点,执行本结点对应的click方法
		}
		Util.writeXml(response, document);
	}

	/**
	 * @方法说明:转到树型展示页面
	 */
	@RequestMapping("/totree")
	public String toMaintain(@RequestParam(required = false) String id, Map<String, Object> map) {
		if (StringUtils.isNotEmpty(id)) {
			map.put("id", id);
		}
		return "/sys/sysfunc/tree";
	}

	/**
	 * @方法说明:跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Map<String, Object> map, @ModelAttribute("sysfunc") SysFunc sysfunc, String id) {
		sysfunc.setParent_id(id);
		sysfunc.setIs_leaf(1);
		sysfunc.setType("MENU");
		return "/sys/sysfunc/insert";
	}

	/**
	 * @方法说明:新增记录
	 */
	@RequestMapping("/insert")
	public void Add(Map<String, Object> map, @ModelAttribute("sysfunc") SysFunc sysfunc, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = service.insert(sysfunc);
		response.getWriter().append(Util.writeJS("/sysfunc/totree?id=" + id, request)).flush();
	}

	/**
	 * @方法说明:删除记录(多条)
	 */
	@RequestMapping("/delete")
	public void delete(Map<String, Object> map, @RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pid = service.delete(id);
		response.getWriter().append(Util.writeJS("/sysfunc/totree?id=" + pid, request)).flush();
	}

	/**
	 * @方法说明:跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam String id) {
		map.put("sysfunc", service.findById(id));
		return "/sys/sysfunc/update";
	}

	/**
	 * @方法说明:修改记录
	 */
	@RequestMapping("/update")
	public void update(Map<String, Object> map, @ModelAttribute("sysfunc") SysFunc sysfunc, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = sysfunc.getFunc_id();
		service.update(sysfunc);
		response.getWriter().append(Util.writeJS("/sysfunc/totree?id=" + id, request)).flush();
	}

	/**
	 * @方法说明:跳转到详细页面
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam String id) {
		map.put("sysfunc", service.findById(id));
		return "/sys/sysfunc/detail";
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") SysFuncCond cond, String id) {
		cond.setParent_id_c(id);
		service.queryList(cond, map);
		return "/sys/sysfunc/list";
	}

	/**
	 * @方法说明:日期属性编辑器(新增/修改/查询条件中String自动转换成Date)
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}

	/**
	 * @方法说明:参照选择页
	 */
	@RequestMapping("/ref")
	public String queryRef(Map<String, Object> map, @ModelAttribute("cond") SysFuncCond cond) {
		return "/sys/sysfunc/ref";
	}

	/**
	 * @方法说明:简单列表;带查询,无增删改,分页可选
	 */
	@RequestMapping("/listSimple")
	public String queryListSimple(Map<String, Object> map, @ModelAttribute("cond") SysFuncCond cond) {
		map.put("dataList", service.queryAllObj(cond));// 不分页
		return "/sys/sysfunc/listSimple";
	}
}