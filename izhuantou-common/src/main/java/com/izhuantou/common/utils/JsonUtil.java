package com.izhuantou.common.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * JSON字符串的工具类
 *
 * @author fucheng
 * @date 2018-01-17
 */
public class JsonUtil {

    /**
     * 将任意对象转换为String类型
     */
    public static String toStr(Object obj) {
	if (obj == null)
	    return "";
	return JSON.toJSONString(obj);

    }

    /**
     * 将字符串按照指定类型，转换为对象 注意：对象的属性，需要有set方法；否则，赋值不会成功
     */
    public static <T> T toObj(String json, Class<T> clazz) {
	return JSON.parseObject(json, clazz);
    }

    /**
     * 将字符串按照指定类型，转换为对象的列表
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
	return JSON.parseArray(json, clazz);
    }

    /**
     * 将对象先转换为字符串，再转换为对象的列表
     */
    public static <T> List<T> toList(Object obj, Class<T> clazz) {
	return JSON.parseArray(JSON.toJSONString(obj), clazz);
    }

    /**
     * 将字符串转换为Map
     */
    public static Map<String, Object> toMap(String json) {
	return JSON.parseObject(json, Map.class);
    }

    /**
     * 格式化json字符串。增加空格和回车，用于json字符串的打印
     */
    public static String formatJson(String jsonStr) {
	if (null == jsonStr || "".equals(jsonStr))
	    return "";
	StringBuilder sb = new StringBuilder();
	char last = '\0';
	char current = '\0';
	int indent = 0;
	boolean isInQuotationMarks = false;
	for (int i = 0; i < jsonStr.length(); i++) {
	    last = current;
	    current = jsonStr.charAt(i);
	    switch (current) {
	    case '"':
		if (last != '\\') {
		    isInQuotationMarks = !isInQuotationMarks;
		}
		sb.append(current);
		break;
	    case '{':
	    case '[':
		sb.append(current);
		if (!isInQuotationMarks) {
		    sb.append('\n');
		    indent++;
		    addIndentBlank(sb, indent);
		}
		break;
	    case '}':
	    case ']':
		if (!isInQuotationMarks) {
		    sb.append('\n');
		    indent--;
		    addIndentBlank(sb, indent);
		}
		sb.append(current);
		break;
	    case ',':
		sb.append(current);
		if (last != '\\' && !isInQuotationMarks) {
		    sb.append('\n');
		    addIndentBlank(sb, indent);
		}
		break;
	    default:
		sb.append(current);
	    }
	}
	return sb.toString();
    }

    /**
     * formatJson用到的私有方法：添加space
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
	for (int i = 0; i < indent; i++) {
	    sb.append('\t');
	}
    }
}
