package com.tangkuo.cn.utils;

import java.util.HashMap;

/**
 * 
 * @author Administrator
 *
 */
public class ContextHashMap extends HashMap {

	public ContextHashMap() {
		super();
	}

	public String getString(String key) {
		return null == key ? "" : (String) get(key);
	}

}
