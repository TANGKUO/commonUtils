package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.Date;


public class Sensors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4036627440649845701L;

	private Long id;

	private Long userId;
	
	private Long deviceId;

	private String deviceName;

	private String sensorName;

	private Long sensorTypeId;

	private String unit;

	private String decimalPlacse;

	private String switcher;

	private String tag;

	private Long isLine;

	private Long isOpen;

	private String iocUrl;

	private Date createDate;

	private Date lastUpdateDate;
	
	private String defaultTimescale;
	
	private int isDelete;//逻辑删除  0、未删除  1、已删除
	
	
	private int isMapping;  //是否映射值  1 是，0 否
	
	private int isHide;	//是否隐藏	0、否 1、是
	
	private SensorMapping sensorMapping;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getDefaultTimescale() {
		return defaultTimescale;
	}

	public void setDefaultTimescale(String defaultTimescale) {
		this.defaultTimescale = defaultTimescale;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getDecimalPlacse() {
		return decimalPlacse;
	}

	public void setDecimalPlacse(String decimalPlacse) {
		this.decimalPlacse = decimalPlacse;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIocUrl() {
		return iocUrl;
	}

	public void setIocUrl(String iocUrl) {
		this.iocUrl = iocUrl;
	}

	public Long getIsLine() {
		return isLine;
	}

	public void setIsLine(Long isLine) {
		this.isLine = isLine;
	}

	public Long getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Long isOpen) {
		this.isOpen = isOpen;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}


	public Long getSensorTypeId() {
		return sensorTypeId;
	}

	public void setSensorTypeId(Long sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}

	public String getSwitcher() {
		return switcher;
	}

	public void setSwitcher(String switcher) {
		this.switcher = switcher;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public SensorMapping getSensorMapping() {
		return sensorMapping;
	}

	public void setSensorMapping(SensorMapping sensorMapping) {
		this.sensorMapping = sensorMapping;
	}

	public int getIsMapping() {
		return isMapping;
	}

	public void setIsMapping(int isMapping) {
		this.isMapping = isMapping;
	}

	public int getIsHide() {
		return isHide;
	}

	public void setIsHide(int isHide) {
		this.isHide = isHide;
	}
}
