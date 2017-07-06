package com.tangkuo.cn.sign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangkuo.cn.utils.Md5Utils;


public abstract class comeonpayUtils {
	private static Logger log = LoggerFactory.getLogger(comeonpayUtils.class);
	
    private comeonpayUtils() {
    }

    /**
     * 给TOP请求做MD5签名。
     * 
     * @param sortedParams 所有字符型的TOP请求参数
     * @param secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signRequestNew(Map<String, String> sortedParams, String secret) throws IOException {
    	// 第一步：把字典按Key的字母顺序排序
    	List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value) && !"sign".equals(key)) {
                query.append(key).append("=").append(value);
            }
        }
        
        log.info("获取当APP请求参数，签名前值为：" + query.toString());
        return Md5Utils.encryptMD5(query.toString(), secret);
    }
    
    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        }
        if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        }  
        if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        }
        if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } 
        return null;
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     * 
     * @param bytes 文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        if ("JPG".equals(suffix)) {
            return "image/jpeg";
        } 
        if ("GIF".equals(suffix)) {
            return "image/gif";
        } 
        if ("PNG".equals(suffix)) {
            return "image/png";
        } 
        if ("BMP".equals(suffix)) {
            return "image/bmp";
        }
        return "application/octet-stream";
    }
}
