package com.izhuantou.damain.system;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * web端和app端日志
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
@Table(name = "tb_portal_log")
public class PortalLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1553468384105184840L;

	/**
	 * 日志主键
	 */
	private String logId;
	/**
	 * 日志类型
	 */
	private String type;
	/**
	 * 日志标题
	 */
	private String title;
	/**
	 * 请求地址
	 */
	private String remoteAddr;
	/**
	 * url
	 */
	private String requestUrl;
	/**
	 * 请求方式
	 */
	private String method;
	/**
	 * 请求参数
	 */
	private String params;
	/**
	 * 异常
	 */
	private String exception;
	/**
	 * 开始时间
	 */
	private Long operateDate;
	/**
	 * 结束时间
	 */
	private String timeOut;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名称
	 */
	private String username;

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

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Long getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Long operateDate) {
		this.operateDate = operateDate;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "PortalLog [logId=" + logId + ", type=" + type + ", title=" + title + ", remoteAddr=" + remoteAddr
				+ ", requestUrl=" + requestUrl + ", method=" + method + ", params=" + params + ", exception="
				+ exception + ", operateDate=" + operateDate + ", timeOut=" + timeOut + ", userId=" + userId
				+ ", username=" + username + "]";
	}

}
