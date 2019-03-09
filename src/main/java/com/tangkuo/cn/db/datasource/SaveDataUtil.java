package com.tangkuo.cn.db.datasource;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.tangkuo.cn.db.mongodb.Configuration;
import com.tangkuo.cn.db.mongodb.MongoDBUtil;
import com.tangkuo.cn.db.mysql.ServerData;
import com.tangkuo.cn.db.oracle.OracleSqlUtil;
import com.tk.cn.utils.JsonUtil;

public class SaveDataUtil {

	/**
	 * 获取当天具体时间
	 */
	public static String getDayTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(cal.getTime());
	}

	/**
	 * 获取当天日期
	 */
	public static String getDayDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(cal.getTime());
	}

	/**
	 * 获取长时间
	 */
	public static long getLongTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowDate = df.format(date);
		return Long.parseLong(nowDate);
	}

	public static long getLongTime(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dfDate = df.parse(date);
			df = new SimpleDateFormat("yyyyMMddHHmmss");
			date = df.format(dfDate);
			return Long.parseLong(date);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String decimalFormat(String value, String decimal) {
		if ((decimal != null && !"".equals(decimal) && !"0".equals(decimal)) && (null != value && !"".equals(value))) {
			int decimals = Integer.parseInt(decimal);
			String format = "#0.";
			for (int i = 0; i < decimals; i++) {
				format += "0";
			}
			DecimalFormat formater = new DecimalFormat(format);
			value = formater.format(Double.parseDouble(value)).toString();
			return value;
		} else if (null != value && !"".equals(value)) {
			Double val = Double.parseDouble(value);
			return val.intValue() + "";
		} else {
			return value;
		}
	}

	public static String getNowDateTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}

	/**
	 * 获取当前月
	 */
	public static int getNowMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间
	 */
	public static Long getNowTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.parseLong(df.format(calendar.getTime()));
	}

	// 25分钟后
	public static Long getAfterTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MINUTE, 25);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.parseLong(df.format(calendar.getTime()));
	}
}
