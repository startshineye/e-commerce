package com.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.bjpowernode.cms.sys.user.model.User;
import com.common.constant.Constant;

/**
 * @类说明:辅助工具类
 * @author GZZ
 */
public class Util {

	/**
	 * @方法说明:把组数拼接成IN语句(字符型)
	 */
	public static String ArrayToIn(String ids) {// 字符IN字符窜
		String[] arr = ids.split(",");
		if (arr.length < 1) {
			return "";
		}
		StringBuffer sb = new StringBuffer(" IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append("'" + arr[i] + "',");
			} else {
				sb.append("'" + arr[i] + "'");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * @方法说明:把组数拼接成IN语句(数值型)
	 */
	public static String ArrayToInNum(String ids) {// 数值IN字符窜
		String[] arr = ids.split(",");
		StringBuffer sb = new StringBuffer(" IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append(arr[i] + ",");
			} else {
				sb.append(arr[i]);
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * @方法说明:将NULL字符串转为""
	 */
	public static String nvl(Object str) {
		return str == null ? "" : str.toString();
	}

	/**
	 * @方法说明: 取登录用户
	 */
	public static User getUser(HttpSession session) {
		User user = (User) session.getAttribute(Constant.LOGIN_USER);
		return user;
	}

	/**
	 * @方法说明:session是否过期
	 */
	public static boolean getTimeOut(HttpSession session) {
		boolean timeOut = false;
		User user = (User) session.getAttribute(Constant.LOGIN_USER);
		if (null == user) {
			timeOut = true;
		}
		return timeOut;
	}

	/**
	 * @方法说明:取登录用户名
	 */
	public static String getLoginId(HttpSession session) {
		String login_id = null;
		User user = getUser(session);
		if (null != user && null != user.getLogin_id()) {
			login_id = user.getLogin_id();
		}
		return login_id;
	}

	/**
	 * @方法说明: 取登录用户ID
	 */
	public static String getUserId(HttpSession session) {
		String userId = null;
		User user = getUser(session);
		if (null != user && null != user.getId()) {
			userId = user.getId();
		}
		return userId;
	}

	/**
	 * @方法说明:取登录用户名字
	 */
	public static String getLoginName(HttpSession session) {
		String userName = null;
		User user = getUser(session);
		if (null != user && null != user.getName()) {
			userName = user.getName();
		}
		return userName;
	}

	/**
	 * @方法说明:向客户端输出XML文件
	 */
	public static void writeXml(HttpServletResponse response, Document document) {
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out;
		try {
			out = response.getWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @方法说明:拼加javaScript
	 */
	public static String writeJS(String path, HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>window.parent.location.replace('");
		sb.append(request.getContextPath());
		sb.append(path);
		sb.append("');</script>");
		return sb.toString();
	}
}
