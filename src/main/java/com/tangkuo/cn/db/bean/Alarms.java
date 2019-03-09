package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.Date;

public class Alarms implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1592810337028552661L;
	private Long id;

	private Long deviceId;
	
	private Long userId;

	private String deviceName;

	private Long sensorId;

	private String alarmType;

	private String heightValue;

	private String belowValue;

	private String duration;

	private String timeFarme;

	private String startTime;

	private String endTime;

	private String dayOfWeek;

	private Long active;

	private String targetModel;

	private String contacts;

	private String targetMessage;

	private String switcher;

	private String gear;

	private Date addDate;
	
	private Long isAlarm;  //是否报警 0 否 1是
	
	private Date alarmDate; //报警时间

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Long getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(Long isAlarm) {
		this.isAlarm = isAlarm;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	

	public String getBelowValue() {
		return belowValue;
	}

	public void setBelowValue(String belowValue) {
		this.belowValue = belowValue;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}

	public String getHeightValue() {
		return heightValue;
	}

	public void setHeightValue(String heightValue) {
		this.heightValue = heightValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSwitcher() {
		return switcher;
	}

	public void setSwitcher(String switcher) {
		this.switcher = switcher;
	}

	public String getTargetMessage() {
		return targetMessage;
	}

	public void setTargetMessage(String targetMessage) {
		this.targetMessage = targetMessage;
	}

	public String getTargetModel() {
		return targetModel;
	}


	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public String getTimeFarme() {
		return timeFarme;
	}

	public void setTimeFarme(String timeFarme) {
		this.timeFarme = timeFarme;
	}

	public void setTargetModel(String targetModel) {
		this.targetModel = targetModel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
