package com.yxm.cms.sys.authority.service;

/**
 * @类说明:资源授权service接口类
 * 
 * @author GZZ
 * @date 2014-02-15 12:59:49
 */
public interface IAuthoService {
	/**
	 * @方法说明:执行菜单权限分配
	 */
	int doFunction(String[] funcid, String roleid, String type);

	/**
	 * @功能说明:是否具有此权限
	 */
	boolean HasLimit(Object[] param, String condition);

}
