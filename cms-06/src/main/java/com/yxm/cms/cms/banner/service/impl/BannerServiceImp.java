package com.yxm.cms.cms.banner.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.DictiParamUtil;
import com.yxm.cms.cms.banner.dao.IBannerDao;
import com.yxm.cms.cms.banner.model.Banner;
import com.yxm.cms.cms.banner.model.BannerCond;
import com.yxm.cms.cms.banner.service.IBannerService;

@Service
public class BannerServiceImp implements IBannerService{
   
	//属性注入
	@Autowired
	private IBannerDao dao;
	@Autowired
	private DictiParamUtil dict;// 字典参数辅助类
	
	@Override
	public int insert(Banner banner) {
		banner.setStatus(1);// 让轮播图默认可用
		banner.setTs(new Date());
		return dao.insert(banner);
	}

	@Override
	public int delete(int id) {
		return dao.delete(id);
	}

	@Override
	public int update(Banner banner) {
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
}
