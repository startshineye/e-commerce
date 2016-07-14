package com.yxm.cms.sys.user.model;
import com.common.condition.BaseCondition;
/**
 * 用户条件查询类
 * 
 * @author GZZ
 * @date 2014-02-16 00:23:30
 */
public class UserCond extends BaseCondition {
	@Override
	public void addCondition() {
		add(id_c, " AND T.ID <> ?");
		add(org_name_c, " AND O.NAME LIKE ?", 3);
		add(login_id_c, " AND T.LOGIN_ID = ?");
		add(user_type_c, " AND T.USER_TYPE LIKE ?", 3);
		add(name_c, " AND T.NAME LIKE ?", 3);
		add(e_mail_c, " AND T.E_MAIL LIKE ?", 3);
		add(id_card_c, " AND T.ID_CARD LIKE ?", 3);
		add(tel_c, " AND T.TEL LIKE ?", 3);
	}

	private String id_c;// 主键
	private String org_name_c;// 部门
	private String login_id_c;// 登录名
	private String user_type_c;// 用户类型
	private String name_c;// 姓名
	private String e_mail_c;// 电邮
	private String id_card_c;// 身份证号
	private String tel_c;// 联系电话

	public String getOrg_name_c() {
		return org_name_c;
	}
	public void setOrg_name_c(String org_name_c) {
		this.org_name_c = org_name_c;
	}
	public String getId_c() {
		return id_c;
	}
	public void setId_c(String id_c) {
		this.id_c = id_c;
	}
	public String getLogin_id_c() {
		return login_id_c;
	}
	public void setLogin_id_c(String login_id_c) {
		this.login_id_c = login_id_c;
	}
	public String getUser_type_c() {
		return user_type_c;
	}
	public void setUser_type_c(String user_type_c) {
		this.user_type_c = user_type_c;
	}
	public String getName_c() {
		return name_c;
	}
	public void setName_c(String name_c) {
		this.name_c = name_c;
	}

	public String getE_mail_c() {
		return e_mail_c;
	}
	public void setE_mail_c(String e_mail_c) {
		this.e_mail_c = e_mail_c;
	}

	public String getId_card_c() {
		return id_card_c;
	}
	public void setId_card_c(String id_card_c) {
		this.id_card_c = id_card_c;
	}

	public String getTel_c() {
		return tel_c;
	}
	public void setTel_c(String tel_c) {
		this.tel_c = tel_c;
	}
	public UserCond (Object[][] obj) {super(obj);}
	public UserCond() {
	}
}