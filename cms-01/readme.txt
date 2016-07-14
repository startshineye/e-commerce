
轮播图增删改查:
   1.查看各轮播图字段:(红色字段是跟业务有关,而非红色和业务无关))
      主键(id),名称(name),排序号(order_by),文件存储路径(picture_path),文件展示路径 
      (picture_url),返回地址(jump_url),状态(status)[显示轮播图是否可用],类型(type)[轮播图所属终 
      端类型],时间蹉(ts)[轮播图创建时间,修改时间](文件存储路径和文件展示路径是通过静态代理实现),
2.项目准备:
    * run configmaven builder新建:Name:cms baseDirecty:cms-1 golds:jetty:run
3.菜单维护
    点击菜单维护cms管理增加”广告位管理”,则增加了一个广告位
4.开发
   后台(从dao开始开发5个方法):cms:banner: controllerservicedaomodel

 
 