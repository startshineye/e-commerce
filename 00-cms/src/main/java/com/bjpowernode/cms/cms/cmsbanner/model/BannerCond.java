package com.bjpowernode.cms.cms.cmsbanner.model;
import java.util.Date;
import com.common.condition.BaseCondition;

/**
 * @类说明:轮播图查询条件类
 *
 * @author:www.bjpowernode.com
 * @date:2016-03-30 13:26:29
 **/
public class BannerCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(id_c, " AND T.ID <> ?");
		add(name_c, " AND T.NAME = ?");
		add(order_num_c, " AND T.ORDER_NUM = ?");
		add(picture_path_c, " AND T.PICTURE_PATH LIKE ?", 3);
		add(picture_url_c, " AND T.PICTURE_URL LIKE ?", 3);
		add(jump_url_c, " AND T.JUMP_URL LIKE ?", 3);
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(status_c, " AND T.STATUS = ?");
		add(type_c, " AND T.TYPE = ?");
		add(create_time_c, " AND T.CREATE_TIME = ?");
		add(creator_c, " AND T.CREATOR LIKE ?", 3);
		add(update_time_c, " AND T.UPDATE_TIME = ?");
		add(updater_c, " AND T.UPDATER LIKE ?", 3);
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉!!!!!!
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面日期类型自动转换
	private Integer id_c;// 主键
	private String name_c;// 名称
	private Integer order_num_c;// 顺序
	private String picture_path_c;// 存储路径
	private String picture_url_c;// 访问地址
	private String jump_url_c;// 跳转地址
	private String remark_c;// 备注
	private Integer status_c;// 状态
	private Integer type_c;// 所属终端
	private Date create_time_c;// 创建时间
	private String creator_c;// 创建人
	private Date update_time_c;// 修改时间
	private String updater_c;// 修改人

	public Integer getId_c() {
		return id_c;
	}
	public void setId_c(Integer id_c) {
		this.id_c = id_c;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
	}
	public Integer getOrder_num_c() {
		return order_num_c;
	}
	public void setOrder_num_c(Integer order_num_c) {
		this.order_num_c = order_num_c;
	}
	public String getPicture_path_c() {
		return picture_path_c;
	}
	public void setPicture_path_c(String picture_path_c) {
		this.picture_path_c = picture_path_c;
	}
	public String getPicture_url_c() {
		return picture_url_c;
	}
	public void setPicture_url_c(String picture_url_c) {
		this.picture_url_c = picture_url_c;
	}
	public String getJump_url_c() {
		return jump_url_c;
	}
	public void setJump_url_c(String jump_url_c) {
		this.jump_url_c = jump_url_c;
	}
	public String getRemark_c() {
		return remark_c;
	}
	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}
	public Integer getStatus_c() {
		return status_c;
	}
	public void setStatus_c(Integer status_c) {
		this.status_c = status_c;
	}
	public Integer getType_c() {
		return type_c;
	}
	public void setType_c(Integer type_c) {
		this.type_c = type_c;
	}
	public Date getCreate_time_c() {
		return create_time_c;
	}
	public void setCreate_time_c(Date create_time_c) {
		this.create_time_c = create_time_c;
	}
	public String getCreator_c() {
		return creator_c;
	}
	public void setCreator_c(String creator_c) {
		this.creator_c = creator_c;
	}
	public Date getUpdate_time_c() {
		return update_time_c;
	}
	public void setUpdate_time_c(Date update_time_c) {
		this.update_time_c = update_time_c;
	}
	public String getUpdater_c() {
		return updater_c;
	}
	public void setUpdater_c(String updater_c) {
		this.updater_c = updater_c;
	}

	//new 条件对象时用构造方法赋值:适用只有少量查询条件并且不需要重复使用的情况
	public BannerCond(Object[][] obj) {super(obj);}
	public BannerCond(){}
}