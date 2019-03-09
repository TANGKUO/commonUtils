package com.tangkuo.cn.db.mysql;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.mina.core.session.IoSession;

import com.tangkuo.cn.db.bean.Device;
import com.tangkuo.cn.db.bean.ModbusBean;
import com.tangkuo.cn.db.bean.SenLastData;
import com.tangkuo.cn.db.bean.SensorMapping;
import com.tangkuo.cn.db.bean.Sensors;
import com.tangkuo.cn.db.datasource.Pool;

public interface ServerData {
//	public static final String API = "http://www.tlink.io/tlink_interface";
	public static final int PORT_8652 = 8652;  
	public static final int PORT_8651 = 8651;  
	public static final int PORT_8649 = 8649;  
	public static final int PORT_7651 = 7651;  
 
	
	public static Map<String,Pool> pool = new HashMap<String,Pool>();
	
	public static Map<String,String> deviceLink = new HashMap<String,String>();
	
	/**
	 * RTU
	 */
	public static Map<String,Long> sendDataTime = new HashMap<String,Long>();        //下一次发送时间
	public static Map<String,Map<String,String>> sensorModbus = new HashMap<String, Map<String,String>>(); //传感器协议       
	public static ConcurrentMap<String,String> currentSendSensor = new ConcurrentHashMap<String, String>();  //当前发送待处理
	public static Map<Long,Integer> sendSensorsResult = new HashMap<Long, Integer>(); //前一次数据是否已跑完
	public static Map<Long,IoSession> sessions = new HashMap<Long,IoSession>();   //客户端连接
	public static Map<Long,List<ModbusBean>> modbusList = new HashMap<Long,List<ModbusBean>>();  //设备协议
	public static Map<Long,String> devices = new HashMap<Long, String>();
//	public static Map<String,String> retData = new HashMap<String, String>();
	
	public static ConcurrentMap<Long,String> checkData = new ConcurrentHashMap<Long,String>();
	
	/***
	 * 下行发送的数据
	 */
	public static Map<String,String> downSendData = new HashMap<String, String>();
	
	
	/**
	 * 全局
	 */
	public static Map<String,Device> deviceMap = new HashMap<String, Device>();//设备信息
	public static Map<String,List<Sensors>> sensorsMap = new HashMap<String, List<Sensors>>();//设备 - 传感器
	public static Map<String,String> tockenMap = new HashMap<String, String>();//微信Tocken
	public static Map<String,Long> invalidTimeMap = new HashMap<String, Long>();//微信Tocken重新获取时间
	public static Map<Long,SenLastData> lastDataMap = new HashMap<Long, SenLastData>();//最后一次数据Map
	//传感器映射
	public static Map<Long,List<SensorMapping>> sensorMapping = new HashMap<Long, List<SensorMapping>>();
	
	/**
	 * 全局保存数据sql
	 */
	public static Queue<String> sqlQueue = new LinkedList<String>();//保存数据sql
	public static Map<String,Long> isSetQueue = new HashMap<String, Long>();  //是否将数据插入队列中
	
	
	public static Queue<String> mysqlQueue = new LinkedList<String>();//保存数据sql
	
	/**
	 * 最后一条上行数据时间
	 */
	public static Stack<Long> lastTimeStock = new Stack<Long>();
	
}
