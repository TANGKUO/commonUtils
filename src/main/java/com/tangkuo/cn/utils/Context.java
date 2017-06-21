package com.tangkuo.cn.utils;

import org.apache.commons.collections4.MapUtils;

/**
 * 
 * @author Administrator
 *
 */
public class Context {
	
	/**
	 * 
	 * @return
	 */
	public T context(final ContextHashMap,final T entity,final Map<String,String> filedMap,String[] excludeFileds){
		if(MapUtils.isNotEmpty(ContextHashMap) && MapUtils.isNotEmpty(filedMap)){
			NameFilter nameFilter = (soure,name,value) -> filedMap.get(name);
			PropertyFilter pf = (soure,name,value) -> !ArrayUtils.contains(excludeFileds.name);
			String context = JSON.toJSONString(ContextHashMap,new SerialiseFilter[]{nameFilter,pf});
			T vo = JSON.parsonObject(ContextHashMap,(class<T>) entity.getclass());
			return vo;
			
		}
		return entity;
	}

}
