package com.hiya3d.base.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

public class BeanUtil {

	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = new HashMap<>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				if (beanMap.get(key) != null) {
					map.put(key + "", beanMap.get(key));
				}
			}
		}

		return map;
	}
}
