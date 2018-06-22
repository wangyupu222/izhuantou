package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 客户行为轨迹（所有客户）
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
public class TrackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1405433395094168978L;

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 操作内容
	 */
	private String operationContent;
	/**
	 * 距离当前时间差多久
	 */
	private String timeAgo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}

}
