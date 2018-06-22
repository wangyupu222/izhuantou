package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 反馈详情(会话展示)
 * 
 * @author sweet
 * @date 2018年6月15日
 *
 */
public class FeedBackDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5012013417628113546L;

	/**
	 * 日期（年月+当天所在时间段）
	 */
	private String date;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 反馈人
	 */
	private String feedbackPerson;

	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
