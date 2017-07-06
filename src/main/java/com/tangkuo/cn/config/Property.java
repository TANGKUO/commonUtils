package com.tangkuo.cn.config;

/**
 * 
* @ClassName: Property
* @Description: (读取配置文件工具类)
* @author tangkuo
* @date 2017年7月6日 下午9:46:50
*
 */
public class Property {

    private static java.util.Properties property;

    private Property() {
    }

    static void init(java.util.Properties props) {
        property = props;
    }

    public static String getProperty(String key) {
        return property.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return property.getProperty(key, defaultValue);
    }

}
