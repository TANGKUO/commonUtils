package com.tk.cn.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 *
 */
public class StringUtil {
	
	/**大写字母模式*/
	public static final Pattern PATTERN_UPPER_LETTER = Pattern.compile("[A-Z]");
	/**数字、字母模式*/
	public static final Pattern PATTERN_LETTER_NUM = Pattern.compile("[A-Za-z0-9]");
	
	
	/**
	 * 把单词首字母转成大写
	 * @param word
	 * @return
	 */
	public static String convertFirstLetter2Upper(String word) {
		return (word.charAt(0) + "").toUpperCase() + word.substring(1);
	}
	
	
	/**
	 * Camel命名->下划线命名，如：lastUpdateTime -> last_update_time 
	 * @param source
	 * @return
	 */
	public static String camelNaming2Underline(String source) {
		StringBuffer buffer = new StringBuffer();
		
		Matcher matcher = PATTERN_UPPER_LETTER.matcher(source);
		String upperLetter = null;
		while (matcher.find()) {
			upperLetter = matcher.group(0);
			matcher.appendReplacement(buffer, "_" + upperLetter.toLowerCase());
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
	
	/**
	 * Pascal命名->Camel命名
	 * @param source
	 * @return
	 */
	public static String pascalNaming2Camel(String source) {
		return (source.charAt(0) + "").toLowerCase() + source.substring(1);
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isStrEmpty(Object value) {
		return (value==null||"null".equals(value.toString()) || value.toString().isEmpty());
	}
	
	/**
	 * 判断字符是否非空
	 * @param str 目标字符
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(Object value){
		return isStrEmpty(value)?"":value.toString().trim(); 
	}
	public static float toFloat(Object value){
		return isStrEmpty(value)?0:Float.parseFloat(value.toString().trim()); 
	}
	public static Double toDouble(Object value){
		return isStrEmpty(value)?0:Double.parseDouble(value.toString().trim()); 
	}
	public static long toLong(Object value){
		return isStrEmpty(value)?0:Long.parseLong(value.toString().trim()); 
	}
	public static long toShort(Object value){
		return isStrEmpty(value)?0:Short.parseShort(value.toString().trim()); 
	}
	public static long toByte(Object value){
		return isStrEmpty(value)?0:Byte.parseByte(value.toString().trim()); 
	}
	public static int toInt(Object value){
		return isStrEmpty(value)?0:Integer.parseInt(value.toString().trim()); 
	}
	public static boolean equals(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equalsIgnoreCase(val2);
	}
	
	public static boolean equalsIgnoreCase(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equalsIgnoreCase(val2);
	}
	/**
	 * 掩饰卡号
	 * @param card
	 */
	public static String coverCard(String card){
		if(card!=null && card.length()>10){
			char[] cs = card.toCharArray();
			for(int i=6;i<cs.length-4;i++)
			cs[i]='*';
		
			card = new String(cs);
		}
		return card;
	}
	
	/**
	 * 掩饰姓名
	 * @param card
	 */
	public static String hiddenName(String name){
		if(name!=null && name.length()>=2){
			char[] cs = name.toCharArray();
			for(int i=1;i<=cs.length-1;i++)
			cs[i]='*';
		
			name = new String(cs);
		}
		return name;
	}
	
	/**
	 * 判断字符串是否包含字符
	 * @param str
	 * @return
	 */
	public static boolean hasLetter(String str){
		String regex=".*[a-zA-Z]+.*";
		Matcher m=Pattern.compile(regex).matcher(str);
		return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(equalsIgnoreCase("", ""));
		System.out.println(hiddenName("你123456"));
		System.out.println(hasLetter("123B2323dgfdgfdg"));
		
	}
	
}
