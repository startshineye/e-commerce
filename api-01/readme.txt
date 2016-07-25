
==拓扑图中api数据接口(ssm)==

-----------------------------------------------
准备:
 1.运用:修改 maven-mvc-mybatis
 2.pom.xml:数据接口不是通过浏览器直接访问,所以去掉web相关
 3.web.xml:数据接口无界面,删掉index.jsp
 4.spring-servlet.xml:删除界面相关
 5.配置文件:jdbc.url改变为:4040/cms
 -----------------------------------------------
 开发:
 我们一般是拥有三个终端:
 需求:从数据接口取出轮播图,每个终端显示一组图(只需要一个查询条件不同)
 1.定义BannerDao:查询所有轮播图
 2.写BannerDao.xml:对接口的实现
 3.service-->serviceImpl
 4.写Action:定义controller
------------------------------------------------------
测试:
   1.新建maven builder:api
   2.浏览器输入:http:localhost:9091/banner/list.do?type=1;