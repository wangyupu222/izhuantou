package com.izhuantou.service.impl.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class ReadPropertiesl {

    /**
     * 读取properties文件 返回
     * 
     * @throws IOException
     */
    public Properties readSMS() throws IOException {
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SMS.properties");// 获取类加载路径，读取src下的文件
	Properties properties = new Properties();
	try {
	    properties.load(inputStream);
	    inputStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    inputStream.close();
	}
	return properties;
    }

    /** 根据key值获取SMS中的数据 */
    public static Map<String, String> gainSMSMap() {
	ReadPropertiesl too = new ReadPropertiesl();
	Properties properties;
	Map<String, String> map = new HashMap<String, String>();
	try {
	    properties = too.readSMS();
	    Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
	    while (it.hasNext()) {
		Entry<Object, Object> entry = it.next();
		String key = (String) entry.getKey();
		String value = (String) entry.getValue();
		map.put(key, value);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return map;

    }

    public static String gainSMSValue(String strName) {
	Map<String, String> map = gainSMSMap();
	return map.get(strName);
    }

    /**
     * 读取properties文件 返回
     * 
     * @throws IOException
     */
    public Properties readProperties(String propertiesName) throws IOException {
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesName);// 获取类加载路径，读取src下的文件
	Properties properties = new Properties();
	try {
	    properties.load(inputStream);
	    inputStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    inputStream.close();
	}
	return properties;
    }

    /** 根据key值获取properties中的数据 */
    public static Map<String, String> gainPropertiesMap(String propertiesName) {
	ReadPropertiesl too = new ReadPropertiesl();
	Properties properties;
	Map<String, String> map = new HashMap<String, String>();
	try {
	    properties = too.readProperties(propertiesName);
	    Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
	    while (it.hasNext()) {
		Entry<Object, Object> entry = it.next();
		String key = (String) entry.getKey();
		String value = (String) entry.getValue();
		map.put(key, value);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return map;

    }

    public static String gainPropertiesValue(String strName, String propertiesName) {
	Map<String, String> map = gainPropertiesMap(propertiesName);
	return map.get(strName);
    }

}
