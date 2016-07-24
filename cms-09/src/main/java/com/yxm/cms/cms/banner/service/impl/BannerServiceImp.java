package com.yxm.cms.cms.banner.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.common.dao.DictiParamUtil;
import com.common.util.FileUtil;
import com.yxm.cms.cms.banner.dao.IBannerDao;
import com.yxm.cms.cms.banner.model.Banner;
import com.yxm.cms.cms.banner.model.BannerCond;
import com.yxm.cms.cms.banner.service.IBannerService;

@Service
public class BannerServiceImp implements IBannerService {
	// 属性注入
	@Autowired
	private IBannerDao dao;
	@Autowired
	private DictiParamUtil dict;// 字典参数辅助类
	@Override
	public int insert(Banner banner, MultipartFile img) {
		if(!img.isEmpty()){
			saveFile(banner,img);
		}
		banner.setStatus(1);// 让轮播图默认可用
		banner.setTs(new Date());
		return dao.insert(banner);
	}
	@Override
	public int delete(String ids) {
		//删除文件磁盘中的文件
		System.err.println(ids);
		String[] id = ids.split(",");
		for (String string : id) {
			Banner banner = dao.findById(new Integer(string));// 到数据库表中查询出记录
			deleteFile(banner.getPicture_path());// 调用文件删除方法
		}
		//2.删除数据库文件
		return dao.delete(ids);
	}
	@Override
	public int update(Banner banner,MultipartFile img){
		//创建文件
		if(!img.isEmpty()){
			deleteFile(banner.getPicture_path());//删除文件
			saveFile(banner, img);// 调用文件保存
		}
		banner.setStatus(1);// 让轮播图默认可用
		banner.setTs(new Date());
		return dao.update(banner);
	}
	@Override
	public void queryList(Map<String, Object> map, BannerCond cond) {
		dao.queryList(map, cond);
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
	public int queryCount(BannerCond cond) {
		return dao.queryCount(cond);
	}

	@Override
	public int updateStatus(Banner banner) {
		return dao.updateStatus(banner);
	}
	/**
	 * 保存（上传）文件
	 * @param banner
	 * @param img
	 */
	private void saveFile(Banner banner, MultipartFile img){
		// 文件上传
		// 1.获取文件扩展名
		String exName = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
		// 2.给文件取不重复的名称，
		long longTime = new Date().getTime();//取时间长整数值
		String fullName = longTime + exName;//贫家全文件名
		String banner_path = "banner/";// 定义轮播图文件保存路径
		String fileRoot = dict.findValue("fileRoot");//保存文件的根路径
		String fullPath = fileRoot + banner_path + fullName;//文件完整路径
		banner.setPicture_path(fullPath);// 设置文件保存路径
		String fileUrl = dict.findValue("imgUrl");// 文件展示前缀
		banner.setPicture_url(fileUrl + banner_path + fullName);// 设置文件展示路径
		try{
			FileUtil.createDir(fullPath);
			img.transferTo(new File(fullPath)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除文件
	 * @param banner
	 * @param img
	 */
	private void deleteFile(String filePath) {// 删除文件
		File file = new File(filePath);// 在内存创建一个文件
		file.delete();
	}
}
