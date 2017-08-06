/**
 * 
 */
package com.tk.cn.utils;

import java.util.Random;
public class RandomUtil {
	
	/**
	 * 随机生成一个数
	 * @param range 范围 
	 * @return
	 */
	public static int randomNum(int range){
		return randomNum(1, range)[0];
	}
	
	/**
	 * 随机生成count个数
	 * @param count 个数
	 * @param range 范围 
	 * @return int数组
	 */
	public static int[] randomNum(int count, int range){
		Random random = new Random();
		int[] randomNum = new int[count];
		for(int i=0;i<count;i++){
			randomNum[i] = random.nextInt(range);
		}
		return randomNum;
	}
	
	/**
	 * 随机生成count个数(范围为0-9)
	 * @param count 个数
	 * @param range 范围 
	 * @return 字符串
	 */
	public static String randomStr(int count, int range){
		Random random = new Random();
		StringBuffer randomNum = new StringBuffer();
		for(int i=0;i<count;i++){
			randomNum.append(random.nextInt(range));
		}
		return randomNum.toString();
	}

}
