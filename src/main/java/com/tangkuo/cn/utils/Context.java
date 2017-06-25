package com.tangkuo.cn.utils;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * 
 * @author Administrator
 *
 */
public class Context<T extends Object> {

	/**
	 * 
	 * @return
	 */
	public T context(final ContextHashMap contextHashMap, final T entity, final Map<String, String> filedMap,
			String[] excludeFileds) {
		if (MapUtils.isNotEmpty(contextHashMap) && MapUtils.isNotEmpty(filedMap)) {
			NameFilter nameFilter = (soure, name, value) -> filedMap.get(name);
			PropertyFilter pf = (soure, name, value) -> !ArrayUtils.contains(excludeFileds, name);
			String context = JSON.toJSONString(contextHashMap, new SerializeFilter[] { nameFilter, pf });
			T vo = JSON.parseObject(context, (Class<T>) entity.getClass());
			return vo;
		}
		return entity;
	}

	/**
	 * 
	 * @return
	 */
	public T includecontext(final ContextHashMap contextHashMap, final T entity, final Map<String, String> filedMap,
			String[] excludeFileds) {
		if (MapUtils.isNotEmpty(contextHashMap) && MapUtils.isNotEmpty(filedMap)) {
			NameFilter nameFilter = (soure, name, value) -> filedMap.get(name);
			PropertyFilter pf = (soure, name, value) -> !ArrayUtils.contains(excludeFileds, name);
			String context = JSON.toJSONString(contextHashMap, new SerializeFilter[] { nameFilter, pf });
			T vo = JSON.parseObject(context, (Class<T>) entity.getClass());
			return vo;

		}
		return entity;
	}

}
