package com.yxm.cms.sys.sysdept.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yxm.cms.sys.sysdept.model.SysDept;
import com.yxm.cms.sys.sysdept.model.SysDeptCond;
import com.yxm.cms.sys.sysdept.service.ISysDeptService;
import com.common.util.Util;

/**
 * @类说明:部门控制器类
 *
 * @author:高振中
 * @date:2014-07-29 16:49:42
 */
@Controller
@RequestMapping("/sysdept")
public class SysDeptController {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISysDeptService service; // 部门Service

	/**
	 * @方法说明:生成树型XML
	 */
	@RequestMapping("/tree")
	public void showTree(@RequestParam String id, String selectID, String type, String roleid, HttpServletResponse response) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("tree");// tree
		root.addAttribute("id", "0");
		Element node = root.addElement("item");// root
		SysDept sysdept = service.findById(id);
		node.addAttribute("id", id).addAttribute("text", sysdept.getName()).addAttribute("open", "1").addAttribute("call", "1").addAttribute("select",
				"1");
		if (("radio").equals(type)) {
			root.addAttribute(type, "0");
			node.addAttribute(type, "0");
		}
		SysDeptCond cond = new SysDeptCond();
		cond.setParent_id_c(id);
		service.getSonElement(node, type, cond, roleid);
		if (StringUtils.isNotEmpty(selectID)) {// 反回页面时选中指定结点
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
		return "/sys/sysdept/tree";
	}

	/**
	 * @方法说明:跳转到新增页面
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Map<String, Object> map, @ModelAttribute("sysdept") SysDept sysdept, String id) {
		sysdept.setParent_id(id);
		sysdept.setIs_leaf(1);
		return "/sys/sysdept/insert";
	}

	/**
	 * @方法说明:新增记录
	 */
	@RequestMapping("/insert")
	public void Add(Map<String, Object> map, @ModelAttribute("sysdept") SysDept sysdept, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = service.insert(sysdept);
		response.getWriter().append(Util.writeJS("/sysdept/totree?id=" + id, request)).flush();
	}

	/**
	 * @方法说明:删除记录(多条)
	 */
	@RequestMapping("/delete")
	public void delete(Map<String, Object> map, @RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pid = service.delete(id);
		response.getWriter().append(Util.writeJS("/sysdept/totree?id=" + pid, request)).flush();
	}

	/**
	 * @方法说明:跳转到修改页面
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(Map<String, Object> map, @RequestParam String id) {
		map.put("sysdept", service.findById(id));
		return "/sys/sysdept/update";
	}

	/**
	 * @方法说明:修改记录
	 */
	@RequestMapping("/update")
	public void update(Map<String, Object> map, @ModelAttribute("sysdept") SysDept sysdept, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = sysdept.getId();
		service.update(sysdept);
		response.getWriter().append(Util.writeJS("/sysdept/totree?id=" + id, request)).flush();
	}

	/**
	 * @方法说明:跳转到详细页面
	 */
	@RequestMapping("/detail")
	public String detail(Map<String, Object> map, @RequestParam String id) {
		map.put("sysdept", service.findById(id));
		return "/sys/sysdept/detail";
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 */
	@RequestMapping("/list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") SysDeptCond cond, String id) {
		cond.setParent_id_c(id);
		service.queryList(cond, map);
		return "/sys/sysdept/list";
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
	public String queryRef(Map<String, Object> map, @ModelAttribute("cond") SysDeptCond cond) {
		return "/sys/sysdept/ref";
	}

	/**
	 * @方法说明:简单列表;带查询,无增删改,分页可选
	 */
	@RequestMapping("/listSimple")
	public String queryListSimple(Map<String, Object> map, @ModelAttribute("cond") SysDeptCond cond) {
		map.put("dataList", service.queryAllObj(cond));// 不分页
		return "/sys/sysdept/listSimple";
	}
}