package com.yxm.cms.banner.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yxm.cms.banner.dao.IBannerDao;
import com.yxm.cms.banner.model.Banner;
import com.yxm.cms.banner.service.IBannerService;
@Service
public class BannerService implements IBannerService{	
	@Autowired
    private IBannerDao  dao;
	@Override
	public List<Banner> queryList(Banner banner) {
		return dao.queryList(banner);
	}

}
