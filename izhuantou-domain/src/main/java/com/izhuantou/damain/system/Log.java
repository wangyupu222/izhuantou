package com.izhuantou.damain.system;

import java.util.Map;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.BasePojo;

/**
 * 日志类 记录用户的操作行为
 * 
 * @author fucheng
 * @Date 2018-06-13
 *
 */
@Table(name = "tb_operation_log")
public class Log extends BasePojo {
    private static final long serialVersionUID = 1L;

    private String logId; // 日志主键
    private String type; // 日志类型
    private String title; // 日志标题
    private String remoteAddr; // 请求地址
    private String requestUrl; // url
    private String method; // 请求方式
    private String params; // 提交参数
    private String exception; // 异常
    private String operateDate; // 开始时间
    private String timeOut; // 结束时间
    private String username; // 登录账号

    public String getLogId() {
	return logId;
    }

    public void setLogId(String logId) {
	this.logId = logId;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getRemoteAddr() {
	return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
	this.remoteAddr = remoteAddr;
    }

    public String getRequestUrl() {
	return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
	this.requestUrl = requestUrl;
    }

    public String getMethod() {
	return method;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    public String getParams() {
	return params;
    }

    public void setParams(String params) {
	this.params = params;
    }

    /**
     * 设置请求参数
     * 
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
	if (paramMap == null) {
	    return;
	}
	StringBuilder params = new StringBuilder();
	for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
	    params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
	    String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
	    params.append(
		    StringUtil.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
	}
	this.params = params.toString();
    }

    public String getException() {
	return exception;
    }

    public void setException(String exception) {
	this.exception = exception;
    }

    public String getOperateDate() {
	return operateDate;
    }

    public void setOperateDate(String operateDate) {
	this.operateDate = operateDate;
    }

    public String getTimeOut() {
	return timeOut;
    }

    public void setTimeOut(String timeOut) {
	this.timeOut = timeOut;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public String toString() {
	return "Log [logId=" + logId + ", type=" + type + ", title=" + title + ", remoteAddr=" + remoteAddr
		+ ", requestUrl=" + requestUrl + ", method=" + method + ", params=" + params + ", exception="
		+ exception + ", operateDate=" + operateDate + ", timeOut=" + timeOut + ", username=" + username + "]";
    }

}
