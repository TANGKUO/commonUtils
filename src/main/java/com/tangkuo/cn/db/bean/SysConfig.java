package com.tangkuo.cn.db.bean;

import java.io.Serializable;

public class SysConfig  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1788573265467304551L;
	
	private Long id;
	
	private String syskey;//默认为1
	
	private String wxtocken;
	
	private String fialTime;//失效时间yyyy-mm-dd hh:mm:ss
	
	private String wxappid;
	
	private String secret;
	
	private String remark;

	public String getSyskey() {
		return syskey;
	}

	public void setSyskey(String syskey) {
		this.syskey = syskey;
	}

	public String getFialTime() {
		return fialTime;
	}

	public void setFialTime(String fialTime) {
		this.fialTime = fialTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getWxtocken() {
		return wxtocken;
	}

	public void setWxtocken(String wxtocken) {
		this.wxtocken = wxtocken;
	}

	

}
