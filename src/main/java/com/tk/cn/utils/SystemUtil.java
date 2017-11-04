package com.tk.cn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: SystemUtil
 * @Description: (读取系统工具类)
 * @author tangkuo
 * @date 2017年11月4日 上午11:11:47
 *
 */
public class SystemUtil {

	private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

	private static String entityClass = "tangkuo";

	public static void main(String[] args) {
		// 获取工程所在路径
		logger.info("SystemUtil.main :【{}】", System.getProperty("user.dir"));
		logger.info("SystemUtil.main isNotEmpty:【{}】", isNotEmpty("tangkuo"));
		logger.info("SystemUtil.main isEmpty:【{}】", isEmpty(""));
		createEntitiyClass();
	}

	public static void createEntitiyClass() {

		String createClassEntity = getHomeDir("src/com/tangkuo/cn/system") + entityClass + ".java";
		logger.info("SystemUtil.main createEntitiyClass:【{}】", createClassEntity);

	}

	/**
	 * 
	 * @Title: getHomeDir
	 * @Description: (获取项目根路径所在路径)
	 * @param str
	 *            String
	 * @return int 返回类型
	 */
	private static String getHomeDir(String str) {
		return System.getProperty("user.dir");
	}

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: (判断参数是否为空)
	 * @param str
	 *            String
	 * @return boolean 返回类型
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0 || str.equals("") || str.matches("\\s*");
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: (判断参数不为空)
	 * @param str
	 * @return boolean 返回类型
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 
	 * @Title: repalceChars
	 * @Description: (将路径转义)
	 * @return void 返回类型
	 */
	public static String repalceChars(String str) {
		String regex = "\\\\"; 
		String replacement = "/";
		return str.replaceAll(regex, replacement);
	}
}
