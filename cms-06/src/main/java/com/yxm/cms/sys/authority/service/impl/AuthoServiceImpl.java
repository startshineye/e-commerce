package com.yxm.cms.sys.authority.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.cms.sys.authority.dao.IAuthoDao;
import com.yxm.cms.sys.authority.model.Autho;
import com.yxm.cms.sys.authority.service.IAuthoService;
@Service
/**
 * @类说明:资源授权service实现类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public class AuthoServiceImpl implements IAuthoService {
	@Autowired
	private IAuthoDao dao;
	/**
	 * @方法说明:执行菜单权限分配
	 */
	@Override
	public int doFunction(String[] funcids, String roleid, String type) {
		dao.delete(new Object[]{roleid, type});
		List<Autho> list = new ArrayList<Autho>();
		Autho auth = null;
		for (String funcid : funcids) {
			auth = new Autho();
			auth.setVisitor_id(roleid);
			auth.setResource_id(funcid);
			auth.setResource_type(type);
			list.add(auth);

		}
		dao.insertBatch(list);
		return list.size();
	}
	/**
	 * @功能说明:是否具有此权限
	 */
	@Override
	public boolean HasLimit(Object[] param, String condition) {
		return dao.HasLimit(param, condition) > 0 ? true : false;
	}
}
