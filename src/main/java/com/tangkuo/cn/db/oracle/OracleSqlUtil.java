package com.tangkuo.cn.db.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tangkuo.cn.db.bean.Alarms;
import com.tangkuo.cn.db.bean.AlarmsHistory;
import com.tangkuo.cn.db.bean.Device;
import com.tangkuo.cn.db.bean.ModbusBean;
import com.tangkuo.cn.db.bean.SenLastData;
import com.tangkuo.cn.db.bean.SensorMapping;
import com.tangkuo.cn.db.bean.Sensors;
import com.tangkuo.cn.db.bean.User;
import com.tangkuo.cn.db.datasource.LogUtil;
import com.tangkuo.cn.db.datasource.SaveDataUtil;
import com.tangkuo.cn.db.mongodb.Configuration;
import com.tangkuo.cn.db.mysql.ServerData;

public class OracleSqlUtil {
	/**
	 * 根据设备序列号查询设备信息
	 */
	public static Device queryDeviceByNo(String deviceNo) {
		Connection con = null;
		Statement stm = null;
		Device device = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_device where isdelete = 0 and deviceNo = '" + deviceNo.trim() + "'";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				device = new Device();
				device.setId(rs.getLong("id"));
				device.setUserId(rs.getLong("userid"));
				device.setDeviceNo(rs.getString("deviceno"));
				device.setDeviceName(rs.getString("devicename"));
				device.setDefaultTimescale(rs.getString("defaulttimescale"));
				device.setAgreement(rs.getString("agreement"));
				device.setFaultDelay(rs.getString("faultdelay"));
				device.setIsSendConfig(rs.getLong("isSendconfig"));
				device.setIsSetLink(rs.getLong("issetlink"));
				device.setIsShare(rs.getLong("isshare"));
				device.setLinktype(rs.getString("linktype"));
				device.setSendcycle(rs.getLong("sendcycle"));
				device.setSendDataType(rs.getLong("senddatatype"));
				device.setSendType(rs.getLong("sendtype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return device;
	}

	/**
	 * 根据ID查询设备信息
	 */
	public static Device queryDeviceById(Long id) {
		Connection con = null;
		Statement stm = null;
		Device device = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_device where isdelete = 0 and id = " + id + "";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				device = new Device();
				device.setId(rs.getLong("id"));
				device.setUserId(rs.getLong("userid"));
				device.setDeviceNo(rs.getString("deviceno"));
				device.setDeviceName(rs.getString("devicename"));
				device.setDefaultTimescale(rs.getString("defaulttimescale"));
				device.setAgreement(rs.getString("agreement"));
				device.setFaultDelay(rs.getString("faultdelay"));
				device.setIsSendConfig(rs.getLong("isSendconfig"));
				device.setIsSetLink(rs.getLong("issetlink"));
				device.setIsShare(rs.getLong("isshare"));
				device.setLinktype(rs.getString("linktype"));
				device.setSendcycle(rs.getLong("sendcycle"));
				device.setSendDataType(rs.getLong("senddatatype"));
				device.setSendType(rs.getLong("sendtype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return device;
	}

	/***
	 * 查询设备modbus协议
	 */
	public static ModbusBean queryModbusBy(String sql) {
		Connection con = null;
		Statement stm = null;
		ModbusBean modbus = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				modbus = new ModbusBean();
				modbus.setAddress(rs.getString("address"));
				modbus.setBias(rs.getString("bias"));
				modbus.setCycle(rs.getString("cycle"));
				modbus.setData(rs.getString("data"));
				modbus.setDatatype(rs.getString("datatype"));
				modbus.setFuncode(rs.getString("funcode"));
				modbus.setOrderStr(rs.getString("orderStr"));
				modbus.setSensorId(rs.getString("sensorId"));
				modbus.setLinktype(rs.getString("linktype"));
				modbus.setSymbol(rs.getString("symbol"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return modbus;
	}

	/**
	 * 根据设备信息查传感器
	 */
	public static List<Sensors> querySensorsList(String condition) {
		Connection con = null;
		Statement stm = null;
		List<Sensors> list = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_sensors where isdelete = 0 " + condition + "";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (null != rs) {
				list = new ArrayList<Sensors>();
				while (rs.next()) {
					Sensors objSensors = new Sensors();
					objSensors.setId(rs.getLong("id"));
					objSensors.setSensorTypeId(rs.getLong("sensortypeid"));
					objSensors.setIsLine(rs.getLong("isline"));
					objSensors.setSensorName(rs.getString("sensorname"));
					objSensors.setDecimalPlacse(rs.getString("decimalplacse"));
					objSensors.setDefaultTimescale(rs.getString("defaulttimescale"));
					objSensors.setDeviceName(rs.getString("devicename"));
					objSensors.setIocUrl(rs.getString("iocurl"));
					objSensors.setIsDelete(rs.getInt("isdelete"));
					objSensors.setIsLine(rs.getLong("isline"));
					objSensors.setIsHide(rs.getInt("ishide"));
					objSensors.setIsOpen(rs.getLong("isopen"));
					objSensors.setIsMapping(rs.getInt("ismapping"));
					objSensors.setUnit(rs.getString("unit"));
					list.add(objSensors);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return list;
	}

	/**
	 * 根据传感器ID信息查传感器映射信息
	 */
	public static List<SensorMapping> querySensorMappingBySensorId(Long id) {
		Connection con = null;
		Statement stm = null;
		List<SensorMapping> list = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_sensormapping where sensorid = " + id + "";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (null != rs) {
				list = new ArrayList<SensorMapping>();
				while (rs.next()) {
					SensorMapping objSensorMapping = new SensorMapping();
					objSensorMapping.setField1(rs.getString("field1"));
					objSensorMapping.setField2(rs.getString("field2"));
					objSensorMapping.setField3(rs.getString("field3"));
					objSensorMapping.setField4(rs.getString("field4"));
					objSensorMapping.setId(rs.getLong("id"));
					objSensorMapping.setSensorId(rs.getLong("sensorId"));
					list.add(objSensorMapping);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return list;
	}

	/**
	 * 根据传感器Id查询最后一条数据
	 */
	public static SenLastData querySenLastData(Long sensorId) {
		Connection con = null;
		Statement stm = null;
		SenLastData objSenLastData = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_senlastdata where sensorid = " + sensorId + " order by id asc";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				objSenLastData = new SenLastData();
				objSenLastData.setId(rs.getLong("id"));
				objSenLastData.setUserId(rs.getLong("userid"));
				objSenLastData.setSensorId(sensorId);
				objSenLastData.setIsAlarm(rs.getInt("isalarm"));
				objSenLastData.setVal(rs.getString("val"));
				objSenLastData.setLat(rs.getString("lat"));
				objSenLastData.setLng(rs.getString("lng"));
				objSenLastData.setHeight(rs.getString("height"));
				objSenLastData.setSpeed(rs.getString("speed"));
				objSenLastData.setDangwei(rs.getString("dangwei"));
				objSenLastData.setUpdateDate(rs.getDate("updatedate"));
				objSenLastData.setHeartbeatDate(rs.getDate("heartbeatdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return objSenLastData;
	}

	/**
	 * 更新心跳包时间
	 */
	public static void updateHearbateData_bak(SenLastData bean) {
		Connection con = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			StringBuffer sql = new StringBuffer("update t_senlastdata set heartbeatDate= to_date('"
					+ df.format(bean.getHeartbeatDate()) + "','yyyy-MM-dd HH24:mi:ss') where id = ")
							.append(bean.getId());
			LogUtil.saveServerLog("心跳包:" + sql);
			stm.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	public static void updateHearbateData(SenLastData bean) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuffer sql = new StringBuffer("update t_senlastdata set heartbeatDate= to_date('"
					+ df.format(bean.getHeartbeatDate()) + "','yyyy-MM-dd HH24:mi:ss') where id = ")
							.append(bean.getId());
			LogUtil.saveServerLog("心跳包:" + sql);
			ServerData.sqlQueue.offer(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加心跳包数据
	 */
	public static void addSenLastHearbateData_bak(SenLastData bean) {
		Connection con = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
					+ "values (seq_senlastdata.nextval,to_date('" + dayDate + "','yyyy-MM-dd HH24:mi:ss'), '"
					+ bean.getDangwei() + "', to_date('" + df.format(bean.getHeartbeatDate())
					+ "','yyyy-MM-dd HH24:mi:ss'), null, " + bean.getIsAlarm() + ",'" + bean.getLat() + "', '"
					+ bean.getLng() + "', " + bean.getSensorId() + ", null, ''," + bean.getUserId() + ", '')";
			LogUtil.saveServerLog("添加最后一条数据:" + sql);
			// System.out.println(sql);
			stm.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	public static void addSenLastHearbateData(SenLastData bean) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			String sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
					+ "values (seq_senlastdata.nextval,to_date('" + dayDate + "','yyyy-MM-dd HH24:mi:ss'), '"
					+ bean.getDangwei() + "', to_date('" + df.format(bean.getHeartbeatDate())
					+ "','yyyy-MM-dd HH24:mi:ss'), null, " + bean.getIsAlarm() + ",'" + bean.getLat() + "', '"
					+ bean.getLng() + "', " + bean.getSensorId() + ", null, ''," + bean.getUserId() + ", '')";
			LogUtil.saveServerLog("添加最后一条数据:" + sql);
			// System.out.println(sql);
			ServerData.sqlQueue.offer(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新最后一条数据
	 * 
	 * @param bean
	 */
	public static void updateSenLastData(SenLastData bean) {
		Connection con = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			StringBuffer sql = new StringBuffer("update t_senlastdata ");
			sql.append("set isAlarm = ").append(bean.getIsAlarm());
			sql.append(",alarmDate = to_date('" + dayDate + "','yyyy-MM-dd HH24:mi:ss') ,val = '").append(bean.getVal())
					.append("',lat = '").append(bean.getLat()).append("',lng='");
			sql.append(bean.getLng()).append("',dangwei = '").append(bean.getDangwei())
					.append("',updateDate= to_date('" + df.format(bean.getUpdateDate())
							+ "','yyyy-MM-dd HH24:mi:ss'),heartbeatDate= to_date('" + df.format(bean.getHeartbeatDate())
							+ "','yyyy-MM-dd HH24:mi:ss')");
			sql.append(" where id = ").append(bean.getId());
			LogUtil.saveServerLog("更新最后一条数据:" + sql);
			stm.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	// public static void updateSenLastData(SenLastData bean){
	// try {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	// StringBuffer sql = new StringBuffer("update t_senlastdata ");
	// sql.append("set isAlarm = ").append(bean.getIsAlarm());
	// sql.append(",alarmDate = to_date('"+dayDate+"','yyyy-MM-dd HH24:mi:ss')
	// ,val = '").append(bean.getVal()).append("',lat =
	// '").append(bean.getLat()).append("',lng='");
	// sql.append(bean.getLng()).append("',dangwei =
	// '").append(bean.getDangwei()).append("',updateDate=
	// to_date('"+df.format(bean.getUpdateDate())+"','yyyy-MM-dd
	// HH24:mi:ss'),heartbeatDate=
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
	// HH24:mi:ss')");
	// sql.append(" where id = ").append(bean.getId());
	// LogUtil.saveServerLog("更新最后一条数据:"+sql);
	// ServerData.sqlQueue.offer(sql.toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/***
	 * 添加最后一条数据
	 * 
	 * @param bean
	 */
	public static void addSenLastData(SenLastData bean) {
		Connection con = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
					+ "values (seq_senlastdata.nextval,to_date('" + dayDate + "','yyyy-MM-dd HH24:mi:ss'), '"
					+ bean.getDangwei() + "', to_date('" + df.format(bean.getHeartbeatDate())
					+ "','yyyy-MM-dd HH24:mi:ss'), null, " + bean.getIsAlarm() + ",'" + bean.getLat() + "', '"
					+ bean.getLng() + "', " + bean.getSensorId() + ", null, to_date('" + df.format(bean.getUpdateDate())
					+ "','yyyy-MM-dd HH24:mi:ss')," + bean.getUserId() + ",'" + bean.getVal() + "')";
			LogUtil.saveServerLog("添加最后一条数据:" + sql);
			stm.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}
	// public static void addSenLastData(SenLastData bean){
	// try {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	//
	// String sql ="insert into t_senlastdata (ID, ALARMDATE, DANGWEI,
	// HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE,
	// USERID, VAL)"+
	// "values (seq_senlastdata.nextval,to_date('"+dayDate+"','yyyy-MM-dd
	// HH24:mi:ss'), '"+bean.getDangwei()+"',
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
	// HH24:mi:ss'), null, "+bean.getIsAlarm()+",'"+bean.getLat()+"',
	// '"+bean.getLng()
	// +"', "+bean.getSensorId()+", null,
	// to_date('"+df.format(bean.getUpdateDate())+"','yyyy-MM-dd
	// HH24:mi:ss'),"+bean.getUserId()+",'"+bean.getVal()+"')";
	// LogUtil.saveServerLog("添加最后一条数据:"+sql);
	// ServerData.sqlQueue.offer(sql);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 更新报警器
	 * 
	 * @param bean
	 */
	public static void updateAlarms(Alarms bean) {
		Connection con = null;
		Statement stm = null;
		try {
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			StringBuffer sql = new StringBuffer("update t_alarms set isAlarm = ");
			sql.append(bean.getIsAlarm())
					.append(",alarmDate = to_date('" + dayDate + "','yyyy-MM-dd HH24:mi:ss') where id = ")
					.append(bean.getId());
			LogUtil.saveTirggerLog("更新报警器sql:" + sql, bean.getDeviceId() + "");
			stm.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	// public static void updateAlarms(Alarms bean){
	// try {
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	// StringBuffer sql = new StringBuffer("update t_alarms set isAlarm = ");
	// sql.append(bean.getIsAlarm()).append(",alarmDate =
	// to_date('"+dayDate+"','yyyy-MM-dd HH24:mi:ss') where id =
	// ").append(bean.getId());
	// LogUtil.saveServerLog("更新最后一条数据:"+sql);
	// ServerData.sqlQueue.offer(sql.toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 批量更新传感器状态
	 * 
	 * @throws SQLException
	 */
	public static void updateSensorsStatus(List<Sensors> list) {
		Connection conn = null;
		Statement stm = null;
		try {
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Sensors objSensors = list.get(i);
					String sql = "update t_sensors set isLine = 1 where id = " + objSensors.getId() + "";
					stm.addBatch(sql);
					if (i == list.size() - 1) {
						int[] count = stm.executeBatch();
						LogUtil.saveTestLog("批量更新传感器在线状态返回结果" + toString(count));
						break;
					}
					if (i % 10 == 0 && i != 0) {
						int[] count = stm.executeBatch();
						LogUtil.saveTestLog("批量更新传感器在线状态返回结果" + toString(count));
					}
					LogUtil.saveTestLog("更新传感器在线状态:" + sql);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}

	}

	private static String toString(int[] count) {
		StringBuffer sb = new StringBuffer();
		for (int i : count) {
			sb.append(i).append(",");
		}
		return sb.toString();
	}

	// 备份的是上一个方法
	// public static void updateSensorsStatus(List<Sensors> list){
	// try {
	// if(list!=null && list.size()>0){
	// for (int i = 0; i < list.size(); i++) {
	// Sensors objSensors = list.get(i);
	// String sql = "update t_sensors set isLine = 1 where id =
	// "+objSensors.getId()+"";
	//// System.out.println("更新状态:"+sql);
	// ServerData.sqlQueue.offer(sql);//加入至队列中
	//
	// }
	// }
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	//
	// }

	/**
	 * 批量更新最后一条数据 -- 心跳包
	 * 
	 * @throws SQLException
	 */
	public static void updateBatchHeartbeat(List<SenLastData> list) {
		Connection conn = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SenLastData bean = list.get(i);
					String sql = "";
					if (bean.getIs_update() == 1) {// 新增
						sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
								+ "values (seq_senlastdata.nextval,to_date('" + dayDate
								+ "','yyyy-MM-dd HH24:mi:ss'), '" + bean.getDangwei() + "', to_date('"
								+ df.format(bean.getHeartbeatDate()) + "','yyyy-MM-dd HH24:mi:ss'), null, "
								+ bean.getIsAlarm() + ",'" + bean.getLat() + "', '" + bean.getLng() + "', "
								+ bean.getSensorId() + ", null, ''," + bean.getUserId() + ", '')";
					} else {// 更新
						sql = "update t_senlastdata set heartbeatDate= to_date('" + df.format(bean.getHeartbeatDate())
								+ "','yyyy-MM-dd HH24:mi:ss') where id = " + bean.getId();
					}

					// System.out.println("更新状态:"+sql);
					stm.addBatch(sql);
					if (i == list.size() - 1) {
						stm.executeBatch();
						break;
					}
					if (i % 10 == 0 && i != 0) {
						stm.executeBatch();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}

	}
	// public static void updateBatchHeartbeat(List<SenLastData> list){
	//
	// try {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	//
	// if(list!=null && list.size()>0){
	// for (int i = 0; i < list.size(); i++) {
	// SenLastData bean = list.get(i);
	// String sql="";
	// if(bean.getIs_update() == 1){//新增
	// sql ="insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE,
	// HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"+
	// "values (seq_senlastdata.nextval,to_date('"+dayDate+"','yyyy-MM-dd
	// HH24:mi:ss'), '"+bean.getDangwei()+"',
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
	// HH24:mi:ss'), null, "+bean.getIsAlarm()+",'"+bean.getLat()+"',
	// '"+bean.getLng()
	// +"', "+bean.getSensorId()+", null, '',"+bean.getUserId()+", '')";
	// }else{//更新
	// sql = "update t_senlastdata set heartbeatDate=
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd HH24:mi:ss')
	// where id = "+bean.getId();
	// }
	//
	//// System.out.println("更新状态:"+sql);
	// ServerData.sqlQueue.offer(sql);
	// }
	// }
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	//
	// }

	/**
	 * 批量更新最后一条数据
	 * 
	 * @throws SQLException
	 */
	public static void addBatchData(List<String> list) {
		Connection conn = null;
		Statement stm = null;
		try {
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String sql = list.get(i);
					if (sql.indexOf("_200003086") != -1 || sql.indexOf("_200003090") != -1) {
						LogUtil.saveSqlExeLog(new Date() + "队列sql总数大于500直接保存:" + sql);
					}
					stm.addBatch(sql);
					if (i == list.size() - 1) {
						stm.executeBatch();
						break;
					}
					if (i % 10 == 0 && i != 0) {
						stm.executeBatch();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			LogUtil.saveQueLog("队列sql总数大于500直接保存异常:" + e1.getMessage());
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}

	}

	// 备份在上面
	public static void addBatchSensorData(List<String> list) {
		try {
			// 如果队列中的sql数量大于500 则直接插入数据库
			Long isSaveQueue = ServerData.isSetQueue.get("isSetQueue");
			if (isSaveQueue == 1l || isSaveQueue - 1 == 0) {// 不放入队列中
				addBatchData(list);
			} else {// 放入队列中
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String sql = list.get(i);
						ServerData.sqlQueue.offer(sql);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 批量新增数据 备份原有保存
	 */
	public static void updateBatchLastData(List<SenLastData> list, String deviceNo) {
		Connection conn = null;
		Statement stm = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SenLastData bean = list.get(i);
					String sql = "";
					if (bean.getIs_update() == 1) {// 新增
						if (bean.getIsHeartbeat() == 0) {
							sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
									+ "values (seq_senlastdata.nextval,to_date('" + dayDate
									+ "','yyyy-MM-dd HH24:mi:ss'), '" + bean.getDangwei() + "', to_date('"
									+ df.format(bean.getHeartbeatDate()) + "','yyyy-MM-dd HH24:mi:ss'), null, "
									+ bean.getIsAlarm() + ",'" + bean.getLat() + "', '" + bean.getLng() + "', "
									+ bean.getSensorId() + ", null, to_date('" + df.format(bean.getUpdateDate())
									+ "','yyyy-MM-dd HH24:mi:ss')," + bean.getUserId() + ",'" + bean.getVal() + "')";
						} else {// 是心跳包新增
							sql = "insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE, HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"
									+ "values (seq_senlastdata.nextval,to_date('" + dayDate
									+ "','yyyy-MM-dd HH24:mi:ss'), '" + bean.getDangwei() + "', to_date('"
									+ df.format(bean.getHeartbeatDate()) + "','yyyy-MM-dd HH24:mi:ss'), null, "
									+ bean.getIsAlarm() + ",'" + bean.getLat() + "', '" + bean.getLng() + "', "
									+ bean.getSensorId() + ", null, ''," + bean.getUserId() + ", '')";
						}
					} else {// 更新
						if (bean.getIsHeartbeat() == 0) {// 更新数据
							sql = "update t_senlastdata set isAlarm = '" + bean.getIsAlarm() + "',alarmDate = to_date('"
									+ dayDate + "','yyyy-MM-dd HH24:mi:ss') ,val = '" + bean.getVal() + "',lat = '"
									+ bean.getLat() + "',lng='" + bean.getLng() + "',dangwei = '" + bean.getDangwei()
									+ "',updateDate= to_date('" + df.format(bean.getUpdateDate())
									+ "','yyyy-MM-dd HH24:mi:ss'),heartbeatDate= to_date('"
									+ df.format(bean.getHeartbeatDate())
									+ "','yyyy-MM-dd HH24:mi:ss') where sensorId = " + bean.getSensorId();
						}
						// else{//更新心跳包
						// sql = "update t_senlastdata set heartbeatDate=
						// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
						// HH24:mi:ss') where id = "+bean.getId();
						// }
					}
					LogUtil.saveTest("更新sql:" + sql, deviceNo);
					if (!"".equals(sql)) {
						stm.addBatch(sql);
					}
					if (i == list.size() - 1) {
						stm.executeBatch();
						conn.commit();
						break;
					}
					if (i % 10 == 0 && i != 0) {
						stm.executeBatch();
						conn.commit();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}

	}
	// public static void updateBatchLastData(List<SenLastData> list){
	// try {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	// if(list!=null && list.size()>0){
	// for (int i = 0; i < list.size(); i++) {
	// SenLastData bean = list.get(i);
	// String sql="";
	// if(bean.getIs_update() == 1){//新增
	// if(bean.getIsHeartbeat() == 0){
	// sql ="insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE,
	// HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"+
	// "values (seq_senlastdata.nextval,to_date('"+dayDate+"','yyyy-MM-dd
	// HH24:mi:ss'), '"+bean.getDangwei()+"',
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
	// HH24:mi:ss'), null, "+bean.getIsAlarm()+",'"+bean.getLat()+"',
	// '"+bean.getLng()
	// +"', "+bean.getSensorId()+", null,
	// to_date('"+df.format(bean.getUpdateDate())+"','yyyy-MM-dd
	// HH24:mi:ss'),"+bean.getUserId()+",'"+bean.getVal()+"')";
	// }else{//是心跳包新增
	// sql ="insert into t_senlastdata (ID, ALARMDATE, DANGWEI, HEARTBEATDATE,
	// HEIGHT, ISALARM, LAT, LNG, SENSORID, SPEED, UPDATEDATE, USERID, VAL)"+
	// "values (seq_senlastdata.nextval,to_date('"+dayDate+"','yyyy-MM-dd
	// HH24:mi:ss'), '"+bean.getDangwei()+"',
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd
	// HH24:mi:ss'), null, "+bean.getIsAlarm()+",'"+bean.getLat()+"',
	// '"+bean.getLng()
	// +"', "+bean.getSensorId()+", null, '',"+bean.getUserId()+", '')";
	// }
	//
	// }else{//更新
	// if(bean.getIsHeartbeat() == 0){//更新数据
	// sql = "update t_senlastdata set isAlarm =
	// '"+bean.getIsAlarm()+"',alarmDate = to_date('"+dayDate+"','yyyy-MM-dd
	// HH24:mi:ss') ,val = '"+bean.getVal()+"',lat =
	// '"+bean.getLat()+"',lng='"+bean.getLng()+"',dangwei =
	// '"+bean.getDangwei()+"',updateDate=
	// to_date('"+df.format(bean.getUpdateDate())+"','yyyy-MM-dd
	// HH24:mi:ss'),heartbeatDate=
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd HH24:mi:ss')
	// where id = "+bean.getId();
	// }else{//更新心跳包
	// sql = "update t_senlastdata set heartbeatDate=
	// to_date('"+df.format(bean.getHeartbeatDate())+"','yyyy-MM-dd HH24:mi:ss')
	// where id = "+bean.getId();
	// }
	//
	// }
	//
	//// System.out.println("更新状态:"+sql);
	// ServerData.sqlQueue.offer(sql);//将sql加入到队列中
	// }
	// }
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	//
	// }

	/**
	 * 查询所有激活触发器
	 */
	public static List<Alarms> queryAllAlarms(Long deviceId, Long sensorId) {
		Connection conn = null;
		Statement stm = null;
		List<Alarms> list = null;
		try {
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			String sql = "select * from t_alarms where active = 1 and deviceid = " + deviceId + " and sensorid = "
					+ sensorId;
			ResultSet rs = stm.executeQuery(sql);
			if (null != rs) {
				list = new ArrayList<Alarms>();
				while (rs.next()) {
					Alarms objAlarms = new Alarms();
					objAlarms.setId(rs.getLong("id"));
					objAlarms.setActive(rs.getLong("active"));
					objAlarms.setAlarmType(rs.getString("alarmtype"));
					objAlarms.setBelowValue(rs.getString("belowvalue"));
					objAlarms.setContacts(rs.getString("contacts"));
					objAlarms.setDayOfWeek(rs.getString("dayofweek"));
					objAlarms.setDeviceId(rs.getLong("deviceid"));
					objAlarms.setDuration(rs.getString("duration"));
					objAlarms.setEndTime(rs.getString("endtime"));
					objAlarms.setGear(rs.getString("gear"));
					objAlarms.setHeightValue(rs.getString("heightvalue"));
					objAlarms.setIsAlarm(rs.getLong("isalarm"));
					objAlarms.setSensorId(rs.getLong("sensorid"));
					objAlarms.setStartTime(rs.getString("starttime"));
					objAlarms.setSwitcher(rs.getString("switcher"));
					objAlarms.setTargetMessage(rs.getString("targetmessage"));
					objAlarms.setTargetModel(rs.getString("targetmodel"));
					objAlarms.setTimeFarme(rs.getString("timefarme"));
					objAlarms.setUserId(rs.getLong("userId"));
					objAlarms.setDeviceName(rs.getString("devicename"));
					list.add(objAlarms);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}
		return list;
	}

	/**
	 * 查询微信tocken
	 */
	public static String getTocken() {
		String tocken = "";
		Connection con = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_sysconfig where syskey = 1";
			// System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				tocken = rs.getString("wxtocken");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return tocken;
	}

	public static Sensors querySesnsorsById(long sensorId) {
		Connection con = null;
		Statement stm = null;
		Sensors sensors = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_sensors where isdelete = 0 and id = " + sensorId;
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				sensors = new Sensors();
				sensors.setId(rs.getLong("id"));
				sensors.setUserId(rs.getLong("userid"));
				sensors.setDeviceId(rs.getLong("deviceId"));
				sensors.setDeviceName(rs.getString("devicename"));
				sensors.setDefaultTimescale(rs.getString("defaulttimescale"));
				sensors.setUnit(rs.getString("unit"));
				sensors.setIsMapping(rs.getInt("isMapping"));
				sensors.setSensorName(rs.getString("sensorName"));
				sensors.setSensorTypeId(rs.getLong("sensorTypeId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return sensors;
	}

	public static User queryUserById(long id) {
		Connection con = null;
		Statement stm = null;
		User user = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_user where id =  " + id;
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("userName"));
				user.setMsgnum(rs.getLong("msgnum"));
				user.setMobile(rs.getString("mobile"));
				user.setUserkey(rs.getString("userkey"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return user;
	}

	/**
	 * 更新数据
	 * 
	 * @param bean
	 */
	public static void updateBySql(String sql) {
		Connection con = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			LogUtil.saveServerLog("根据sql更新数据" + sql);
			stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}
	// 备份的在上面
	// public static void updateBySql(String sql){
	// ServerData.sqlQueue.offer(sql);//加入队列中统一处理
	// }

	/**
	 * 保存数据
	 * 
	 * @param bean
	 */
	public static void execute_bak(String sql) {
		Connection con = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			LogUtil.saveServerLog("根据sql保存数据" + sql);
			stm.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	public static void execute(String sql) {
		try {
			LogUtil.saveServerLog("根据sql保存数据" + sql);
			ServerData.sqlQueue.offer(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveAlarmHistory(AlarmsHistory bean) {
		Connection con = null;
		Statement stm = null;
		try {
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "insert into t_alarmshistory (ID, ALARMSID, DEVICEID, DEVICENAME, MESSAGE, SENSORID, SENSORNAME, STATUS, TRIGGERCONTENT, TRIGGERDATE, TRIGGEREMAIL, TRIGGERMOBILE, TRIGGERVAL, TRIGGERWECHAT, USERID)"
					+ "values (seq_alarmshistory.nextval, " + bean.getAlarmsId() + ", " + bean.getDeviceId() + ", '"
					+ bean.getDeviceName() + "', null, " + bean.getSensorID() + ", '" + bean.getSensorName() + "', "
					+ bean.getStatus() + ", '" + bean.getTriggerContent() + "', to_date('" + dayDate
					+ "','yyyy-MM-dd HH24:mi:ss'), '" + bean.getTriggerEmail() + "', '" + bean.getTriggerMobile()
					+ "', '" + bean.getTriggerVal() + "', '" + bean.getTriggerWeChat() + "', " + bean.getUserId() + ")";
			LogUtil.saveServerLog("添加报警记录:" + sql);
			stm.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}
	// public static void saveAlarmHistory(AlarmsHistory bean){
	// try {
	// String dayTime = SaveDataUtil.getDayTime();
	// String dayDate = SaveDataUtil.getDayDate()+" "+dayTime;
	//
	// String sql = "insert into t_alarmshistory (ID, ALARMSID, DEVICEID,
	// DEVICENAME, MESSAGE, SENSORID, SENSORNAME, STATUS, TRIGGERCONTENT,
	// TRIGGERDATE, TRIGGEREMAIL, TRIGGERMOBILE, TRIGGERVAL, TRIGGERWECHAT,
	// USERID)"+
	// "values (seq_alarmshistory.nextval, "+bean.getAlarmsId()+",
	// "+bean.getDeviceId()+", '"+bean.getDeviceName()+"', null,
	// "+bean.getSensorID()+", '"+bean.getSensorName()+
	// "', "+bean.getStatus()+", '"+bean.getTriggerContent()+"',
	// to_date('"+dayDate+"','yyyy-MM-dd HH24:mi:ss'),
	// '"+bean.getTriggerEmail()+"', "+bean.getTriggerMobile()+",
	// '"+bean.getTriggerVal()+"', '"+bean.getTriggerWeChat()+"',
	// "+bean.getUserId()+")";
	// LogUtil.saveServerLog("添加报警记录:"+sql);
	// ServerData.sqlQueue.offer(sql);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static int getCount(String sql) {
		Connection con = null;
		Statement stm = null;
		int count = 0;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return count;
	}

	public static List<ModbusBean> queryModbusByDeviceId(String deviceId, String linktype) {
		Connection con = null;
		Statement stm = null;
		List<ModbusBean> list = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select t1.address,t1.bias,t1.cycle,t1.data,t1.datatype,t1.funcode,t1.orderstr,t1.sensorid,t1.symbol from t_modbus t1,"
					+ "t_device t2,t_sensors t3 where t3.deviceid = t2.id and  t1.sensorid = t3.id  and t3.isdelete = 0 and t2.id = "
					+ deviceId + " and t1.linktype = '" + linktype + "' "
					+ " and t1.address is not null and t1.bias is not null and t1.funcode is not null order by to_number(translate(t1.address,'0123456789'||t1.address,'0123456789')),to_number(translate(t1.funcode,'0123456789'||t1.funcode,'0123456789')),"
					+ " to_number(translate(t1.bias,'0123456789'||t1.bias,'0123456789')), to_number(translate(t1.data,'0123456789'||t1.data,'0123456789')) asc";
			ResultSet rs = stm.executeQuery(sql);
			ModbusBean modbus = null;
			if (null != rs) {
				list = new ArrayList<ModbusBean>();
				while (rs.next()) {
					modbus = new ModbusBean();
					modbus.setAddress(rs.getString("address"));
					modbus.setBias(rs.getString("bias"));
					modbus.setCycle(rs.getString("cycle"));
					modbus.setData(rs.getString("data"));
					modbus.setDatatype(rs.getString("datatype"));
					modbus.setFuncode(rs.getString("funcode"));
					modbus.setOrderStr(rs.getString("orderStr"));
					modbus.setSensorId(rs.getString("sensorId"));
					modbus.setSymbol(rs.getString("symbol"));
					list.add(modbus);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return list;
	}

	public static List<ModbusBean> queryModbusByDeviceNo(String deviceNo, String linktype, String addr, String bias,
			String funcode) {
		Connection con = null;
		Statement stm = null;
		List<ModbusBean> list = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select t1.address,t1.bias,t1.cycle,t1.data,t1.datatype,t1.funcode,t1.orderstr,t1.sensorid,t1.symbol from t_modbus t1,t_device t2,t_sensors t3 where t3.deviceid = t2.id and  t1.sensorid = t3.id  and t3.isdelete = 0 and t2.deviceno = '"
					+ deviceNo + "' and t1.linktype = '" + linktype + "' and t1.address = '" + addr
					+ "' and t1.bias = '" + bias + "' and t1.funcode = '" + funcode + "' "
					+ "order by  to_number(translate(t1.address,'0123456789'||t1.address,'0123456789')),to_number(translate(t1.funcode,'0123456789'||t1.funcode,'0123456789')),"
					+ "to_number(translate(t1.bias,'0123456789'||t1.bias,'0123456789')),to_number(translate(t1.data,'0123456789'||t1.data,'0123456789')) asc";
			ResultSet rs = stm.executeQuery(sql);
			ModbusBean modbus = null;
			if (null != rs) {
				list = new ArrayList<ModbusBean>();
				while (rs.next()) {
					modbus = new ModbusBean();
					modbus.setAddress(rs.getString("address"));
					modbus.setBias(rs.getString("bias"));
					modbus.setCycle(rs.getString("cycle"));
					modbus.setData(rs.getString("data"));
					modbus.setDatatype(rs.getString("datatype"));
					modbus.setFuncode(rs.getString("funcode"));
					modbus.setOrderStr(rs.getString("orderStr"));
					modbus.setSensorId(rs.getString("sensorId"));
					modbus.setSymbol(rs.getString("symbol"));
					list.add(modbus);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return list;
	}

	public static String querySenLast(String deviceNo, String bias, String addr) {
		Connection con = null;
		Statement stm = null;
		String result = "";
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select value from t_senlast where deviceNo = '" + deviceNo + "' and bias = '" + bias
					+ "' and addr = '" + addr + "'";
			ResultSet rs = stm.executeQuery(sql);
			if (null != rs && rs.next()) {
				result = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return result;
	}

	/**
	 * 查询设备信息
	 */
	public static String getDeviceInfo(String deviceNo) {
		Connection con = null;
		Statement stm = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> result = new HashMap<String, Object>();
		String jsonStr = "";
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "SELECT t1.id as device_id,t1.deviceNo,t1.agreement,t2.id as sensor_id,t2.sensorTypeId FROM t_device t1,t_sensors t2 WHERE t1.id = t2.deviceId AND t1.isDelete = 0 AND t2.isDelete = 0 AND t1.deviceNo = '"
					+ deviceNo.trim() + "' order by t2.id asc";
			ResultSet rs = stm.executeQuery(sql);
			int num = 0;
			while (rs.next()) {
				result = new HashMap<String, Object>();
				result.put("sensors_id", rs.getString("sensor_id"));
				result.put("seneors_type", rs.getString("sensorTypeId"));
				resultList.add(result);
				if (num == 0) {
					resultMap.put("device_id", rs.getString("device_id"));
					resultMap.put("device_no", deviceNo);
					if (null != rs.getString("agreement")) {
						resultMap.put("agreement", rs.getString("agreement"));
					}
				}
				num++;
			}
			if (num > 0) {
				resultMap.put("flag", "00");
				resultMap.put("msg", "");
				resultMap.put("deviceItems", resultList);
				jsonStr = JsonUtil.parseJSON(resultMap);
			} else {
				resultMap.put("flag", "01");
				resultMap.put("msg", "没查到相关数据");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}

		return jsonStr;
	}

	/**
	 * 根据设备序列号查询设备信息
	 */
	public static String queryDeviceLink(String deviceNo) {
		Connection con = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_devicelink where deviceNo = '" + deviceNo.trim() + "'";
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("deviceno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
		return "";
	}

	/**
	 * 根据设备序列号查询设备信息
	 */
	public static void queryAllDeviceLink() {
		Connection con = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			String sql = "select * from t_devicelink";
			ResultSet rs = stm.executeQuery(sql);
			if (null != rs) {
				while (rs.next()) {
					ServerData.deviceLink.put(rs.getString("deviceno"), rs.getString("deviceno"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(con);
		}
	}

	public static void saveDeviceLink(String deviceNo) {
		String deviceStr = queryDeviceLink(deviceNo);
		if ("".equals(deviceStr)) {
			Connection con = null;
			Statement stm = null;
			try {
				con = OracleConnection.getConnection();
				stm = con.createStatement();
				String sql = "insert into t_devicelink values(seq_devicelink.nextval,'" + deviceNo.trim() + "')";
				stm.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				OracleConnection.closeStatement(stm);
				OracleConnection.closeCon(con);
			}
		}
	}

	public static List<Map<String, String>> getSendDataConfig(String linktype) {
		Connection con = null;
		ResultSet rs = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery("select ip,port from t_send_data_config  where linktype='" + linktype + "'");
			if (null != rs) {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				while (rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("ip", rs.getString("ip"));
					map.put("port", rs.getString("port"));
					list.add(map);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeRs(rs);
			OracleConnection.closeCon(con);
		}
		return null;
	}

	/***
	 * 保存在线设备
	 * 
	 * @param linktype
	 */
	public static void saveOnliedevice(String deviceNo) {
		Connection con = null;
		ResultSet rs = null;
		Statement stm = null;
		try {
			con = OracleConnection.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery("select * from t_online_device where deviceNo='" + deviceNo + "'");
			if (null != rs && rs.next()) {
				String dayTime = SaveDataUtil.getDayTime();
				String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
				stm.executeUpdate("update t_online_device set linktime = to_date('" + dayDate
						+ "','yyyy-MM-dd HH24:mi:ss') where id = " + rs.getString("id"));
			} else {
				stm.execute("insert into t_online_device values(seq_online_device.nextval,'"
						+ Configuration.getValue("ip") + "','modbus',sysdate,null,'" + deviceNo + "')");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnection.closeRs(rs);
			if (null != stm) {
				OracleConnection.closeStatement(stm);
			}
			OracleConnection.closeCon(con);
		}
	}

	/**
	 * 批量更新在线设备的链接时间
	 * 
	 * @throws SQLException
	 */
	public static void updateOnliedeviceTime() {
		Connection conn = null;
		Statement stm = null;
		try {
			conn = OracleConnection.getConnection();
			stm = conn.createStatement();
			int i = 0;
			String dayTime = SaveDataUtil.getDayTime();
			String dayDate = SaveDataUtil.getDayDate() + " " + dayTime;
			for (Map.Entry<Long, String> device : ServerData.devices.entrySet()) {
				String deviceNo = device.getValue();
				if (null != deviceNo && !"".equals(deviceNo)) {
					String sql = "update t_online_device set updatetime = to_date('" + dayDate
							+ "','yyyy-MM-dd HH24:mi:ss') where deviceNo = '" + deviceNo + "'";
					stm.addBatch(sql);
					if (i == ServerData.sessions.size() - 1) {
						int[] count = stm.executeBatch();
						LogUtil.saveOtsLog("统计在线状态返回结果" + toString(count));
						break;
					}
					if (i % 10 == 0 && i != 0) {
						int[] count = stm.executeBatch();
						LogUtil.saveOtsLog("统计在线状态返回结果" + toString(count));
					}
					i++;
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			OracleConnection.closeStatement(stm);
			OracleConnection.closeCon(conn);
		}

	}
}
