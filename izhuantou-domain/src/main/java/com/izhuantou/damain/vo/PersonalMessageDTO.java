package com.izhuantou.damain.vo;

import java.io.Serializable;

public class PersonalMessageDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4838886072380141009L;
	/**
	 * 消息总数
	 */
	private Integer countMessageAll;
	/**
	 * 未读消息总数
	 */
	private Integer countMessage;
	/**
	 * 已读消息数量
	 */
	private Integer countHistoryMessage;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发送人
	 */
	private String sendUser;
	/**
	 * 发送时间
	 */
	private String sendDateTime;
	/**
	 * OID
	 */
	private String oid;
	/**
	 * 内容
	 */
	private String  content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getSendDateTime() {
		return sendDateTime;
	}
	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCountHistoryMessage() {
		return countHistoryMessage;
	}
	public void setCountHistoryMessage(Integer countHistoryMessage) {
		this.countHistoryMessage = countHistoryMessage;
	}
	public Integer getCountMessageAll() {
		return countMessageAll;
	}
	public void setCountMessageAll(Integer countMessageAll) {
		this.countMessageAll = countMessageAll;
	}
	public Integer getCountMessage() {
		return countMessage;
	}
	public void setCountMessage(Integer countMessage) {
		this.countMessage = countMessage;
	}
	@Override
	public String toString() {
		return "PersonalMessageDTO [countMessageAll=" + countMessageAll + ", countMessage=" + countMessage
				+ ", countHistoryMessage=" + countHistoryMessage + ", title=" + title + ", sendUser=" + sendUser
				+ ", sendDateTime=" + sendDateTime + ", oid=" + oid + ", content=" + content + "]";
	}
	
	
	
	
}
