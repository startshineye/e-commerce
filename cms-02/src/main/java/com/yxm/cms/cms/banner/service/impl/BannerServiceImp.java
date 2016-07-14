package com.yxm.cms.cms.banner.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.cms.cms.banner.dao.IBannerDao;
import com.yxm.cms.cms.banner.model.Banner;
import com.yxm.cms.cms.banner.model.BannerCond;
import com.yxm.cms.cms.banner.service.IBannerService;

@Service
public class BannerServiceImp implements IBannerService{
   
	//属性注入
	@Autowired
	private IBannerDao dao;
	
	@Override
	public int insert(Banner banner) {
		return dao.insert(banner);
	}

	@Override
	public int delete(int id) {
		return dao.delete(id);
	}

	@Override
	public int update(Banner banner) {
		return dao.update(banner);
	}

	@Override
	public void queryList(Map<String, Object> map, BannerCond cond) {
		dao.queryList(map, cond);
	}

	@Override
	public Banner findById(int id) {
		return dao.findById(id);
	}

}
