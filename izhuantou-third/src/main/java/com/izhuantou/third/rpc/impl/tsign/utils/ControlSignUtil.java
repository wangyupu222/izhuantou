package com.izhuantou.third.rpc.impl.tsign.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.izhuantou.third.rpc.impl.ReadPropertiesl;


public class ControlSignUtil {

    private static final Logger logger = LoggerFactory.getLogger(ControlSignUtil.class);
    // 签署后pdf文件存储的路径 并加载类时初始化电子签章项目---
    private static String signedFolder;
    static {
	Map<String, String> map = ReadPropertiesl.gainPropertiesMap("File.properties");
	try {
	    signedFolder = map.get("pdfFilePath");// pdf文件保存路径
	    FileHelper.createDirectory(signedFolder);// 如果文件夹不存在就创建文件夹
	    initProject();// 初始化项目
	} catch (Exception e) {
	    logger.error("从配置文件获取PDF存储路径失败", e.getMessage());
	}
    }

    /**
     * 项目初始化
     * 
     * @throws ExceptionSaveFail
     */
    public static void initProject() {

	try {
	    Map<String, String> map = ReadPropertiesl.gainPropertiesMap("sign.properties");
	    String projectId = map.get("projectId");
	    String projectSecret = map.get("projectSecret");
	    String itsmApiUrl = map.get("itsmApiUrl");
	    // 初始化项目，做全局使用，只初始化一次即可
	    SignUtil.initProject(projectId, projectSecret, itsmApiUrl);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("初始化电子签章项目失败", e.getMessage());
	}
    }

    /**
     * 获取初始化后的值
     * 
     * @return
     */
    public static String getSignedFolder() {

	return signedFolder;
    }

}
