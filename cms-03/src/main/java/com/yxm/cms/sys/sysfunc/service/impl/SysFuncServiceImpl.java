package com.yxm.cms.sys.sysfunc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.cms.sys.authority.dao.IAuthoDao;
import com.yxm.cms.sys.authority.service.IAuthoService;
import com.yxm.cms.sys.sysfunc.dao.ISysFuncDao;
import com.yxm.cms.sys.sysfunc.model.SysFunc;
import com.yxm.cms.sys.sysfunc.model.SysFuncCond;
import com.yxm.cms.sys.sysfunc.service.ISysFuncService;

/**
 * @类说明:功能菜单Service实现类
 *
 * @author:高振中
 * @date:2014-07-29 11:08:22
 */
@Service
public class SysFuncServiceImpl implements ISysFuncService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISysFuncDao dao; // 功能菜单Dao
	@Autowired
	private IAuthoDao authDao;// 权限DAO
	@Autowired
	private IAuthoService sysAuthorityService;

	/**
	 * @方法说明:查找全部子结点(递规)
	 */
	@Override
	public void getSonElement(Element node, String type, SysFuncCond cond, String roleid) {
		List<SysFunc> chlist = dao.queryAllObj(cond);
		for (SysFunc sysfunc : chlist) {
			Element nodeSon = node.addElement("item");
			nodeSon.addAttribute("id", sysfunc.getFunc_id()).addAttribute("text", sysfunc.getName());
			Element userdata = nodeSon.addElement("userdata");
			userdata.addAttribute("name", "data01").setText(sysfunc.getUrl() != null ? sysfunc.getUrl() : "");
			if (("radio").equals(type)) {
				nodeSon.addAttribute(type, "0");
			}
			if ((sysfunc.getIs_leaf() == 1) && null != roleid && !"".equals(roleid)
					&& sysAuthorityService.HasLimit(new Object[] { roleid, sysfunc.getFunc_id() }, "")) {// 查看是已授权
				nodeSon.addAttribute("checked", "1");
			}
			if (sysfunc.getIs_leaf() != 1) {// 不是叶子结点,继续查找子结点
				cond.setParent_id_c(sysfunc.getFunc_id());
				getSonElement(nodeSon, type, cond, roleid);
			}
		}
	}

	/**
	 * @方法说明:新增记录
	 */
	@Override
	public String insert(SysFunc sysfunc) {
		dao.updateLeaf(new Object[] { sysfunc.getParent_id() });
		sysfunc.setIs_leaf(1);
		return dao.insert(sysfunc);
	}

	/**
	 * @方法说明:删除记录(多条)
	 */
	@Override
	public String delete(String id) {
		SysFunc sysfunc = dao.findById(id);
		String pid = sysfunc.getParent_id();
		dao.delete(id);
		dao.updateParent(new Object[] { pid });
		return pid;
	}

	/**
	 * @方法说明:按ID查找单个实体
	 */
	@Override
	public SysFunc findById(String id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明:更新记录
	 */
	@Override
	public int update(SysFunc sysfunc) {
		return dao.update(sysfunc);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 */
	@Override
	public void queryList(SysFuncCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
	}

	/**
	 * @方法说明:按条件查询不分页列表(使用范型)
	 */
	@Override
	public List<SysFunc> queryAllObj(SysFuncCond cond) {
		return dao.queryAllObj(cond);
	}

	/**
	 * @方法说明:按条件查询记录个数
	 */
	@Override
	public int findCountByCond(SysFuncCond cond) {
		return dao.findCountByCond(cond);
	}

	/**
	 * @功能说明:从数据中查找本用户权限字符窜
	 */
	@Override
	public String getLimtCondition(String resourceType, String user_id) {
		String limtCondition = "";
		try {
			String condition = authDao.getLimtCondition(resourceType, user_id);
			if (!condition.equals("")) {
				limtCondition = " AND FUNC_ID " + condition;
			}
		} catch (Exception e1) {
			limtCondition = " AND 1=0 ";
			System.err.println("获取权限条件时出错了!!");
			e1.printStackTrace();
		}
		return limtCondition;
	}
}