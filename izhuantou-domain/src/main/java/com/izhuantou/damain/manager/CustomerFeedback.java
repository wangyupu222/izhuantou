package com.izhuantou.damain.manager;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 用户反馈信息
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
@Table(name = "tb_customer_feedback")
public class CustomerFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1377222470269646766L;

	/**
	 * 主键
	 */
	private String oid;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 反馈内容
	 */
	private String feedbackContent;
	/**
	 * 反馈时间
	 */
	private Date feedbackDate;
	/**
	 * 类型（0：客服登记，1：客户反馈）
	 */
	private Integer type;
	/**
	 * 状态（0：未回复，1：解决中，2：已回复，3：未解决，4：已解决）
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Date addDatetime;
	/**
	 * 修改时间
	 */
	private Date updDatetime;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddDatetime() {
		return addDatetime;
	}

	public void setAddDatetime(Date addDatetime) {
		this.addDatetime = addDatetime;
	}

	public Date getUpdDatetime() {
		return updDatetime;
	}

	public void setUpdDatetime(Date updDatetime) {
		this.updDatetime = updDatetime;
	}

	@Override
	public String toString() {
		return "CustomerFeedback [oid=" + oid + ", name=" + name + ", phone=" + phone + ", feedbackContent="
				+ feedbackContent + ", feedbackDate=" + feedbackDate + ", type=" + type + ", status=" + status
				+ ", addDatetime=" + addDatetime + ", updDatetime=" + updDatetime + "]";
	}

}
