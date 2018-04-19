package com.izhuantou.common;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fuiou.data.AppTransReqData;
import com.fuiou.http.WebUtils;
import com.fuiou.util.ConfigReader;
import com.fuiou.util.SecurityUtils;
import com.izhuantou.common.utils.StringUtil;

public class FuiouServiceMy {

    public static void authorization(AuthorizationReqData data, HttpServletResponse response) throws Exception {
	if (data == null) {
	    throw new Exception("请求参数为空");
	}
	Map<String, String> paramMap = new HashMap();
	paramMap.put("mchnt_cd", StringUtil.isEmpty(data.getMchnt_cd()) ? "" : data.getMchnt_cd().trim());
	paramMap.put("mchnt_txn_ssn",
		StringUtil.isEmpty(data.getMchnt_txn_ssn()) ? "" : data.getMchnt_txn_ssn().trim());
	paramMap.put("login_id", StringUtil.isEmpty(data.getLogin_id()) ? "" : data.getLogin_id().trim());
	paramMap.put("busi_tp", StringUtil.isEmpty(data.getBusi_tp()) ? "" : data.getBusi_tp().trim());
	paramMap.put("page_notify_url",
		StringUtil.isEmpty(data.getPage_notify_url()) ? "" : data.getPage_notify_url().trim());

	paramMap.put("signature", SecurityUtils.sign(data.createSignValue()));
	String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/authConfig.action", paramMap,
		"utf-8");
	OutputStream out = response.getOutputStream();
	out.write(result.getBytes("utf-8"));
	out.flush();
    }

    public static void p2p500405(AppTransReqData appTransReqData, HttpServletResponse response) throws Exception {
	if (appTransReqData == null) {
	    throw new Exception("请求参数为空");
	}
	Map<String, String> paramMap = new HashMap<String, String>();
	paramMap.put("mchnt_cd",
		StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
	paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? ""
		: appTransReqData.getMchnt_txn_ssn().trim());
	paramMap.put("login_id",
		StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
	paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
	paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? ""
		: appTransReqData.getPage_notify_url().trim());
	paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? ""
		: appTransReqData.getBack_notify_url().trim());
	paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
	String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/500405.action", paramMap, "utf-8");
	OutputStream out = response.getOutputStream();
	out.write(result.getBytes("utf-8"));
	out.flush();
    }
}
