package com.tangkuo.cn.db.bean;

import java.io.Serializable;
import java.util.Date;
public class SenLastData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4036627440649845701L;

	private Long id;
	
	private Long userId;//所属用户ID
	
	private Long sensorId;
	
	private int isAlarm;//前条数据是否报警  //0、否  1、是
	
	private Date alarmDate;//报警时间
	
	private String val;//数值 最后一次
	
	private String lat;//精度  -- 传感器类型为定位型
	
	private String lng;//纬度   -- 传感器类型为定位型
	
	private String height;//高度  -- 传感器类型为定位型
	
	private String speed;//速度  -- 传感器类型为定位型
	
	private String dangwei;//档位  -- 传感器类型为开关型
	
	private Date updateDate;//更新时间

	private Date heartbeatDate;//心跳包时间
	
	private int is_update;//是否更新  0更新  1新增
	
	private int isHeartbeat;//是否属于心跳包  0否  1是
	
	private boolean isAlarms;
	
	
	public boolean isAlarms() {
		return isAlarms;
	}

	public void setAlarms(boolean isAlarms) {
		this.isAlarms = isAlarms;
	}

	public int getIsHeartbeat() {
		return isHeartbeat;
	}

	public void setIsHeartbeat(int isHeartbeat) {
		this.isHeartbeat = isHeartbeat;
	}

	public int getIs_update() {
		return is_update;
	}

	public void setIs_update(int is_update) {
		this.is_update = is_update;
	}

	public Date getHeartbeatDate() {
		return heartbeatDate;
	}

	public void setHeartbeatDate(Date heartbeatDate) {
		this.heartbeatDate = heartbeatDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public String getDangwei() {
		return dangwei;
	}

	public void setDangwei(String dangwei) {
		this.dangwei = dangwei;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(int isAlarm) {
		this.isAlarm = isAlarm;
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

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	
}
