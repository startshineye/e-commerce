package com.bjpowernode.cms.cms.cmsbanner.model;
import java.util.Date;

/**
 * @类说明:轮播图实体类
 * @author:www.bjpowernode.com
 * @date:2016-03-30 13:26:29
 **/
public class Banner {

	//数据库中的字段
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面日期类型自动转换
	private Integer id;// 主键
	private String name;// 名称
	private Integer order_num;// 顺序
	private String picture_path;// 存储路径
	private String picture_url;// 访问地址
	private String jump_url;// 跳转地址
	private String remark;// 备注
	private Integer status;// 状态
	private Integer type;// 所属终端
	private Date ts;// 创建时间
 

	//此处可添加查询显示辅助字段
	
	private String type_name;
	private String status_name;

	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getJump_url() {
		return jump_url;
	}
	public void setJump_url(String jump_url) {
		this.jump_url = jump_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}