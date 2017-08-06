/**
 * 
 */
package com.tk.cn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtil {
	/**
	 * trim map为空的
	 * @param map
	 * @return
	 */
	public static Map trim(Map map){
		if(map==null || map.isEmpty()){
			return map;
		}
		Map noNullMap = new HashMap();
		Set<Entry> set = map.entrySet();
		for(Entry entry: set){
			if(entry.getValue()!=null){
				noNullMap.put(entry.getKey(), entry.getValue());
			}
		}
		return noNullMap;
	}
}	
