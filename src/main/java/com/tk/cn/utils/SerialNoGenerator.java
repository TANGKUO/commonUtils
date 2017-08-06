package com.tk.cn.utils;

import java.util.Date;

/**
 * 序号生成器
*/
public class SerialNoGenerator {
	
	/**
	 * 生成length位流水号,规则:yyyyMMddHHmmssXXX,XX为n位随机数
	 * @param date 当前日期
	 * @param length 流水号长度
	 * @return
	 */
	public static String create(Date date, int length){
		String dateFormat = DateUtil.DATE_FORMAT_SHORT_NO;
		String day = DateUtil.formatDate(date, dateFormat);
		StringBuffer idSb = new StringBuffer(day);
		//14位日期到s+n位随机数
		int randomLength = length - dateFormat.length();
		String randomStr = RandomUtil.randomStr(randomLength, 9);
		idSb.append(randomStr);
		return idSb.toString();
	}
	
	/**
	 * 生成length位数字型随机数
	 * @param length
	 * @return
	 */
	public static String create(int length){
		String randomStr = RandomUtil.randomStr(length, 9);
		return randomStr;
	}
	
	/**
	 * 生成length位数字型流水号,规则:yyyyMMddHHmmssXXX,XX为n位随机数
	 * @param date 当前日期
	 * @param length 流水号长度
	 * @return
	 */
	public static long createLong(Date date, int length){
		String strSerialNo = create(date, length);
		return Long.parseLong(strSerialNo);
	}
	
}
