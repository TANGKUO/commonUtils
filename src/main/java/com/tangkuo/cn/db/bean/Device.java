package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Device implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7219238753929226433L;
	
	private Long id;

	private Long userId;

	private String deviceName;

	private String defaultTimescale;//数据发送间隔时间

	private String faultDelay;//导出数据周期

	private String deviceNo;

	private Long isShare;

	
	private String agreement;

	private String lat;

	private String lng;

	private Long sequence;

	private Date createDate;

	private String remark;
	
	private String iocUrl;
	
	private Long isDelete;//逻辑删除  0、未删除  1、已删除
	
	private Long isSetLink;  //是否设置连接
	
	
	private String sendVal;  //发送值
	
	private long sendDataType;  //发送数据类型  1 字符串 2十六进制
	
	private long sendType;  //发送类型 1 马上发送 2 周期发送
	 
	private long  sendcycle;  //发送周期值，秒为单位，最小30秒
	
	private long isSendConfig;  //是否配置发送  0 否，1 是
	
	private String linktype;  //条形码
	
	private String parentUser;   //所属父级
	
	private String wx_deId;//微信的设备ID
	
	
	
	private List<Sensors> sensorsList = new ArrayList<Sensors>();
	
	
	
	
	public String getWx_deId() {
		return wx_deId;
	}

	public void setWx_deId(String wx_deId) {
		this.wx_deId = wx_deId;
	}

	public List<Sensors> getSensorsList() {
		return sensorsList;
	}

	public void setSensorsList(List<Sensors> sensorsList) {
		this.sensorsList = sensorsList;
	}

	public String getIocUrl() {
		return iocUrl;
	}

	public void setIocUrl(String iocUrl) {
		this.iocUrl = iocUrl;
	}
	public Long getIsDelete() {
		return isDelete;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDefaultTimescale() {
		return defaultTimescale;
	}

	public void setDefaultTimescale(String defaultTimescale) {
		this.defaultTimescale = defaultTimescale;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getFaultDelay() {
		return faultDelay;
	}

	public void setFaultDelay(String faultDelay) {
		this.faultDelay = faultDelay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsShare() {
		return isShare;
	}

	public void setIsShare(Long isShare) {
		this.isShare = isShare;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	



	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIsSetLink() {
		return isSetLink;
	}

	public void setIsSetLink(Long isSetLink) {
		this.isSetLink = isSetLink;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public long getSendcycle() {
		return sendcycle;
	}

	public void setSendcycle(long sendcycle) {
		this.sendcycle = sendcycle;
	}

	public long getSendDataType() {
		return sendDataType;
	}

	public void setSendDataType(long sendDataType) {
		this.sendDataType = sendDataType;
	}

	public long getSendType() {
		return sendType;
	}

	public void setSendType(long sendType) {
		this.sendType = sendType;
	}

	public String getSendVal() {
		return sendVal;
	}

	public void setSendVal(String sendVal) {
		this.sendVal = sendVal;
	}

	public long getIsSendConfig() {
		return isSendConfig;
	}

	public void setIsSendConfig(long isSendConfig) {
		this.isSendConfig = isSendConfig;
	}

	public String getLinktype() {
		return linktype;
	}

	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}

	public String getParentUser() {
		return parentUser;
	}

	public void setParentUser(String parentUser) {
		this.parentUser = parentUser;
	}

	
}
