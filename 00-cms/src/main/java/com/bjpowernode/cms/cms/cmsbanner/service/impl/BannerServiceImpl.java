package com.bjpowernode.cms.cms.cmsbanner.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.cmsbanner.dao.IBannerDao;
import com.bjpowernode.cms.cms.cmsbanner.model.Banner;
import com.bjpowernode.cms.cms.cmsbanner.model.BannerCond;
import com.bjpowernode.cms.cms.cmsbanner.service.IBannerService;
import com.common.dao.DictiParamUtil;
import com.common.util.FileUtil;

@Service
public class BannerServiceImpl implements IBannerService {
	@Autowired
	private IBannerDao dao;
	@Autowired
	private DictiParamUtil dict;// 字典参数辅助类
	private final Log logger = LogFactory.getLog(this.getClass());// 日志类

	@Override
	public int insert(Banner banner, MultipartFile image) {// 新增数据时有些值不需要在页面上给定

		saveFile(banner, image);// 调用保存文件方法
		banner.setStatus(1);// 让状态直接为启用状态
		banner.setTs(new Date());
		return dao.insert(banner);
	}

	// 保存文件
	private void saveFile(Banner banner, MultipartFile image) {

		String fileRoot = dict.findValue("fileRoot");// 到参数表去查找那文件写入根路径
		String banner_path = "banner/";// 轮播图保存的路径
		String originalName = image.getOriginalFilename();// 原始文件名客户端文件系统里文件名称:01-绘图1.png
		// 为防止保存后文件名重复，使用毫秒数字符串来做来文件名.
		String newFileName = new Date().getTime() + originalName.substring(originalName.lastIndexOf("."));
	
		String fullName = fileRoot + banner_path + newFileName;
		logger.info(fullName);
		banner.setPicture_path(fullName);// set文件存储路径
		banner.setPicture_url(dict.findValue("imgUrl") + banner_path + newFileName);// 文件展示路径

		try {
			FileUtil.createDir(fullName);// 如果路径不存在,我们要去创建 这个路径
			image.transferTo(new File(fullName));// 保存文件
		} catch (IllegalStateException | IOException e) {

			logger.error("存保文件时出现异常!");
			e.printStackTrace();

		}
	}

	@Override
	public int update(Banner banner, MultipartFile image) {// 更新数据时有些值不需要在页面上给定
		if (!image.isEmpty()) { // 如果文件不存在 ，不去执行文件删除
			deleteFile(banner.getId() + "");// 调用删除文件方法
			saveFile(banner, image);
		} // 调用保存文件方法
		banner.setTs(new Date());
		return dao.update(banner);
	}

	@Override
	public int delete(String id) {
		deleteFile(id);// 调用删除文件方法
		return dao.delete(id);// 删除数据方法
	}

	// 删除文件
	private void deleteFile(String ids) {
		String id[] = ids.split(",");
		for (String string : id) {
			Banner banner = dao.findById(new Integer(string));
			if (banner.getPicture_path() != null && !banner.getPicture_path().equals("")) {// 如果存储路径不为空
				File file = new File(banner.getPicture_path());// 按路径去创建文件
				if (file.exists())// 如果这个文件存
					file.delete();// 最终才可以执行删除操作
			}
		}
	}

	@Override
	public void queryList(BannerCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);

		// 翻译字典值
		@SuppressWarnings("unchecked")
		List<Banner> list = (List<Banner>) map.get("dataList");
		for (Banner banner : list) {
			banner.setType_name(dict.findDictValue(10, banner.getType()));
			// 轮播图状态字典值
			banner.setStatus_name(dict.findDictValue(11, banner.getStatus()));
		}
	}

	@Override
	public Banner findById(int id) {
		Banner banner = dao.findById(id);
		// 补充终端类型字典值
		banner.setType_name(dict.findDictValue(10, banner.getType()));
		// 轮播图状态字典值
		banner.setStatus_name(dict.findDictValue(11, banner.getStatus()));
		return banner;
	}

	@Override
	public int findByCond(BannerCond cond) {
		return dao.findByCond(cond);
	}

	@Override
	// 更新轮播图状态

	public int updateSta(Banner banner) {
		return dao.updateSta(banner);
	}

}
