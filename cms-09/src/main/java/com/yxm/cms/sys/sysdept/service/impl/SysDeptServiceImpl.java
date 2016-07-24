package com.yxm.cms.sys.sysdept.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.cms.sys.authority.service.IAuthoService;
import com.yxm.cms.sys.sysdept.dao.ISysDeptDao;
import com.yxm.cms.sys.sysdept.model.SysDept;
import com.yxm.cms.sys.sysdept.model.SysDeptCond;
import com.yxm.cms.sys.sysdept.service.ISysDeptService;

/**
 * @类说明:部门Service实现类
 *
 * @author:高振中
 * @date:2014-07-29 16:49:42
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISysDeptDao dao; // 部门Dao
	@Autowired
	private IAuthoService authoService;// 系统权限service

	/**
	 * @方法说明:查找全部子结点(递规)
	 */
	@Override
	public void getSonElement(Element node, String type, SysDeptCond cond, String roleid) {
		List<SysDept> chlist = dao.queryAllObj(cond);
		for (SysDept sysdept : chlist) {
			Element nodeSon = node.addElement("item");
			nodeSon.addAttribute("id", sysdept.getId()).addAttribute("text", sysdept.getName());
			if (("radio").equals(type)) {
				nodeSon.addAttribute(type, "0");
			}
			if (sysdept.getIs_leaf() == 1 && StringUtils.isNotEmpty(roleid)
					&& authoService.HasLimit(new Object[] { roleid, sysdept.getId() }, "")) {// 查看是已授权
				nodeSon.addAttribute("checked", "1");
			}
			if (sysdept.getIs_leaf() != 1) {// 不是叶子结点,继续查找子结点
				cond.setParent_id_c(sysdept.getId());
				getSonElement(nodeSon, type, cond, roleid);
			}
		}
	}

	/**
	 * @方法说明:新增记录
	 */
	@Override
	public String insert(SysDept sysdept) {
		sysdept.setIs_leaf(1);
		dao.updateLeaf(new Object[] { sysdept.getParent_id() });
		return dao.insert(sysdept);
	}

	/**
	 * @方法说明:删除记录(多条)
	 */
	@Override
	public String delete(String id) {
		SysDept sysdept = dao.findById(id);
		String pid = sysdept.getParent_id();
		dao.delete(id);
		dao.updateParent(new Object[] { pid });
		return pid;
	}

	/**
	 * @方法说明:按ID查找单个实体
	 */
	@Override
	public SysDept findById(String id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 */
	@Override
	public int update(SysDept sysdept) {
		return dao.update(sysdept);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 */
	@Override
	public void queryList(SysDeptCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	@Override
	public List<SysDept> queryAllObj(SysDeptCond cond) {
		return dao.queryAllObj(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 */
	@Override
	public int findCountByCond(SysDeptCond cond) {
		return dao.findCountByCond(cond);
	}
}