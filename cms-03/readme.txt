-----------------------------------------------
1.后台验证:轮播图名称不能为空:
   sql语句:select count(1) from cms_banner where name = "ss";
       方法:为了让此方法通用,查询参数应该包含很多.(优化查询)[以后别人再用就不用自己再写一个方法了]
      int queryCount(BannerCond cond);
   controller:
               运用Spring提供的API
       BindingResult必须和<form:form erros>搭配     
------------------------------------------------
3.更新操作
Controller:
在:toUpdate()方法上需要把数据回显到update页面上去.
@RequestMapping("toupdate")
	public String toupdate(int id, Map<String, Object> map) {
		map.put("banner", service.findById(id));
		return "cms/banner/update";
	}
修改时候验证:思考:修改时候验证还能那样写吗?
显然是不可以的,因为加入我没有修改就提交保存按钮,这是合法的,但是他也会报错,加入重写的sql语句,(查询数据库时候需要把本条件名排除掉)
Select name from cms_banner where name == zs and id<>111 (本名称);

Service
中加入
banner.setStatus(1);// 让轮播图默认可用
banner.setTs(new Date());
类似于新增,页面等就是拷贝新增的页面
页面的状态不可修改,所以我们加入隐藏标签.
--------------------------------------------------
