package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 客户反馈相关信息导出DTO
 * 
 * @author sweet
 * @date 2018年6月15日
 *
 */
public class CustomerExcelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3107469488533549292L;

	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 反馈内容
	 */
	private String feedbackContent;
	/**
	 * 最近反馈
	 */
	private String serviceName;
	/**
	 * 反馈时间
	 */
	private String feedbackDate;
	/**
	 * 状态（0：未回复，1：解决中，2：已回复，3：未解决，4：已解决）
	 */
	private String status;
	/**
	 * 会话内容
	 */
	private String chatContent;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

}
