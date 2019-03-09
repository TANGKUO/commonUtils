package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1788573265467304551L;

	private Long id;

	private String userName;

	private String password;

	private String nickName;

	private String email;

	private String mobile;

	private String weChat;

	private String userkey;

	private Long isfirstlogin;

	private Long isEnable;

	private Long status;//0正常 //1禁用

	private Long isAdmin;
	
	private int userType;//0、个人用户  1、企业用户
	
	private String address;//地址
	
	private String company;//公司名称
	
	private String contact_name;//联系人
	
	private int isComplete;//资料是否完整 //0否  1、是
	
	private String birthday;//生日
	
	private int sex;//性别  0、男  1、女
	
	private int isbind;//0未绑定微信  1、已绑定
	
	private Date addTime;
	
	private Long msgnum = 0l;//剩余短信条数
	
	private Long cfgnum = 1l; //组态可以用数
	
	private String amt;//余额
	
	private String qq;  //qq
	
	private String fax; //传真
	
	private String company_key;//企业唯一标识号
	
	private Long parentid;
	
	private String lastLoginTime;  //最后登录时间
	

	
	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getCompany_key() {
		return company_key;
	}

	public void setCompany_key(String company_key) {
		this.company_key = company_key;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public Long getMsgnum() {
		return msgnum;
	}

	public void setMsgnum(Long msgnum) {
		this.msgnum = msgnum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getIsbind() {
		return isbind;
	}

	public void setIsbind(int isbind) {
		this.isbind = isbind;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Long getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Long isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIsfirstlogin() {
		return isfirstlogin;
	}

	public void setIsfirstlogin(Long isfirstlogin) {
		this.isfirstlogin = isfirstlogin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Long isEnable) {
		this.isEnable = isEnable;
	}

	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getCfgnum() {
		return cfgnum;
	}

	public void setCfgnum(Long cfgnum) {
		this.cfgnum = cfgnum;
	}

}
