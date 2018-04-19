package com.izhuantou.common.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 字符、字符串数组、字符串等相关工具类
 *
 * @author fucheng
 * @date 2018-01-17
 */
public class StringUtil {

    /**
     * 判断字符串是否为null、空字符串、或者是多个空字符串。
     */
    public static boolean isEmpty(String str) {
	if (str == null || str.trim().length() == 0) {
	    return true;
	}

	return false;
    }

    /**
     * 判断字符串是否不为null、空字符串、或者是多个空字符串。
     */
    public static boolean isNotEmpty(String str) {
	if (str == null || str.trim().length() == 0) {
	    return false;
	}
	return true;
    }

    /**
     * 判断字符串是否为UUID(此处UUID为36位,格式为"8-4-4-4-16"的字符串)
     */
    public static boolean isUUID(String str) {
	if (str == null) {
	    return false;
	}
	if (str.length() != 36) {
	    return false;
	}
	return true;
    }

    /**
     * 判断字符串不为空，且长度小于等于最大值
     */
    public static boolean isLtLength(String str, int maxLength) {
	if (isNotEmpty(str) && str.trim().length() <= maxLength) {
	    return true;
	}
	return false;
    }

    /**
     * 获取长度为32的UUID字符串
     */
    public static String getUUID() {
	return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 缩略字符串(不区分中英文字符)
     * 
     * @param str
     *            目标字符串
     * @param length
     *            截取长度
     * @return
     */
    public static String abbr(String str, int length) {
	if (str == null) {
	    return "";
	}
	try {
	    StringBuilder strb = new StringBuilder();
	    int currentLength = 0;
	    for (char c : replaceHtml(StringEscapeUtils.unescapeHtml(str)).toCharArray()) {
		currentLength += String.valueOf(c).getBytes("GBK").length;
		if (currentLength <= length - 3) {
		    strb.append(c);
		} else {
		    strb.append("...");
		    break;
		}
	    }
	    return strb.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    return "";
	}
    }

    /**
     * 替换掉HTML标签方法
     * 
     * @param html
     * @return
     */
    public static String replaceHtml(String html) {
	if (isEmpty(html)) {
	    return "";
	}
	String regEx = "<.+?>";
	Pattern p = Pattern.compile(regEx);
	Matcher m = p.matcher(html);
	String string = m.replaceAll("");
	return string;
    }

}
