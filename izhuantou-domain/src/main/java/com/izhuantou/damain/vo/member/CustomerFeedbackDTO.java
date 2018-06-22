package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户反馈
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
public class CustomerFeedbackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102714628992163602L;

	/**
	 * 主键
	 */
	private String oid;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别（0：未实名，1：男，2：女，）
	 */
	private String sex;
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

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	@Override
	public String toString() {
		return "CustomerFeedbackDTO [oid=" + oid + ", phone=" + phone + ", name=" + name + ", sex=" + sex
				+ ", feedbackContent=" + feedbackContent + ", serviceName=" + serviceName + ", feedbackDate="
				+ feedbackDate + ", status=" + status + "]";
	}

}
