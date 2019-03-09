package com.tangkuo.cn.db.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tangkuo.cn.db.mongodb.Configuration;
/**
 * 日志输出工具类方法
 * @author 61650
 *
 */
public class LogUtil {
	private static String FILE_PATH = Configuration.getValue("log_path");
	
	public static void saveTest(String content,String deviceNo){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate+"/"+deviceNo; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/test_data.log",content+"\n");
		} 
		
	}
	
	public static void saveTriggerLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/socket_trigger.log",content+"\n");
		} 
		
	}
	
	public static void saveRecoveryLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/socket_recovery.log",content+"\n");
		} 
		
	}
	
	public static void exexuteSql(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_exexuteSql.log",content+"\n");
		} 
		
	}
	
	public static void saveTestLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/mbrtu_test_sql.log",content+"\n");
		} 
		
	}
	
	public static void saveLog(String content,String deviceNo,boolean isUp){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate+"/"+deviceNo; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			if(isUp){
				appendMethod(path+"/modbus_rtu_up.log",content+"\n");
			}else{
				appendMethod(path+"/modbus_rtu_down.log",content+"\n");
			}
		} 
	}
	
	
	public static void saveJiexiLog(String content,String deviceNo){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate+"/"+deviceNo; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_jiexi_up.log",content+"\n");
		} 
	}
	
	
	public static void saveTirggerLog(String content,String deviceId){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate+"/trigger/"+deviceId; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_tirgger.log",content+"\n");
		} 
	}
	
	
	public static void saveLogToServer(String content,String deviceNo){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate+"/"+deviceNo; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu_server.log",content+"\n");
		} 
	}
	
	public static void saveSqlExeLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/mbrtu_exe_sql.log",content+"\n");
		} 
		
	}
	
	public static void saveOtherLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu_other.log",content+"\n");
		} 
		
	}
	
	
	public static void save111Log(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu_111.log",content+"\n");
		} 
		
	}
	
	public static void save222Log(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu_222.log",content+"\n");
		} 
		
	}

	public static void saveServerLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_server_data.log",content+"\n");
		} 
		
	}
	
	
	public static void saveSqlLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_sql_data.log",content+"\n");
		} 
		
	}
	
	
	
	public static void saveQueLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_que_data.log",content+"\n");
		} 
		
	}
	
	public static void saveSql(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_sql.log",content+"\n");
		} 
		
	}
	
	public static void saveOtsLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_ots.log",content+"\n");
		} 
		
	}
	
//
	public static void saveRtuLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu.log",content+"\n");
		} 
		
	}
	
	
	public static void saveRtuDownLog(String content){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String nowDate = df.format(date);
		String path=FILE_PATH+"/"+nowDate; 
		File f = new File(path); 
		if(!f.exists()){
			f.mkdirs();
		}else{
			appendMethod(path+"/modbus_rtu_down.log",content+"\n");
		} 
		
	}
	
	
    public static void appendMethod(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
