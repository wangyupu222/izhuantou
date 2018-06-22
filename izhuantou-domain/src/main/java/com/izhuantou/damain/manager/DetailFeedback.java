package com.izhuantou.damain.manager;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 反馈内容详情
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
@Table(name = "tb_detail_feedback")
public class DetailFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4211426269853884771L;

	/**
	 * 主键
	 */
	private String oid;
	/**
	 * 反馈信息oid
	 */
	private String customerFeedbackOid;
	/**
	 * 反馈内容
	 */
	private String feedbackContent;
	/**
	 * 反馈时间
	 */
	private Date feedbackDate;
	/**
	 * 反馈人
	 */
	private String feedbackPerson;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCustomerFeedbackOid() {
		return customerFeedbackOid;
	}

	public void setCustomerFeedbackOid(String customerFeedbackOid) {
		this.customerFeedbackOid = customerFeedbackOid;
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

	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	@Override
	public String toString() {
		return "DetailFeedback [oid=" + oid + ", customerFeedbackOid=" + customerFeedbackOid + ", feedbackContent="
				+ feedbackContent + ", feedbackDate=" + feedbackDate + ", feedbackPerson=" + feedbackPerson + "]";
	}

}
