package com.izhuantou.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	
	/**
	 * 将一个类加入map
	 * 
	 */
	public static Map<String, Object> setConditionMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (getValueByFieldName(fieldName, obj) != null)
				map.put(fieldName,getValueByFieldName(fieldName, obj));
		}

		return map;
	}

	/**
	 * 根据属性名获取该类此属性的值
	 * 
	 * @param fieldName
	 * @param object
	 * @return
	 */
	private static Object getValueByFieldName(String fieldName, Object object) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		try {
			Method method = object.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(object, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}

	}
}
