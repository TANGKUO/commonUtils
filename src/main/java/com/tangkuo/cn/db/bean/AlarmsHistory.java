package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.Date;

public class AlarmsHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long userId;

	private Long deviceId;

	private String deviceName;

	private Long sensorID;

	private String sensorName;

	private Long alarmsId;

	private String triggerVal;

	private String triggerEmail;

	private String triggerMobile;

	private String triggerWeChat;

	private String triggerContent;

	private Date triggerDate;
	
	private Long status;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getAlarmsId() {
		return alarmsId;
	}

	public void setAlarmsId(Long alarmsId) {
		this.alarmsId = alarmsId;
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

	public Long getSensorID() {
		return sensorID;
	}

	public void setSensorID(Long sensorID) {
		this.sensorID = sensorID;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getTriggerContent() {
		return triggerContent;
	}

	public void setTriggerContent(String triggerContent) {
		this.triggerContent = triggerContent;
	}

	public Date getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(Date triggerDate) {
		this.triggerDate = triggerDate;
	}

	public String getTriggerEmail() {
		return triggerEmail;
	}

	public void setTriggerEmail(String triggerEmail) {
		this.triggerEmail = triggerEmail;
	}

	public String getTriggerMobile() {
		return triggerMobile;
	}

	public void setTriggerMobile(String triggerMobile) {
		this.triggerMobile = triggerMobile;
	}

	public String getTriggerVal() {
		return triggerVal;
	}

	public void setTriggerVal(String triggerVal) {
		this.triggerVal = triggerVal;
	}

	public String getTriggerWeChat() {
		return triggerWeChat;
	}

	public void setTriggerWeChat(String triggerWeChat) {
		this.triggerWeChat = triggerWeChat;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
