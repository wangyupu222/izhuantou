package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.util.Date;

public class UrgentNoticeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4606536291118681541L;
	/**
	 * OID
	 */
	private String OID;
	/**
     * 公告标题
     */
    private String name;
	/**
     * 作者
     */
    private String author;
    /**
     * 类型
     */
    private String type;
	/**
     * 公告内容
     */
    private String messages;
    /**
     * 发布起始时间
     */
    private String startTime;
    /**
     * 发布终止时间
     */
    private String endTime;
    /**
     * 父结点OID
     */
    private String parentOID;
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getParentOID() {
		return parentOID;
	}
	public void setParentOID(String parentOID) {
		this.parentOID = parentOID;
	}
	@Override
	public String toString() {
		return "urgentNoticeDTO [OID=" + OID + ", name=" + name + ", author=" + author + ", type=" + type
				+ ", messages=" + messages + ", startTime=" + startTime + ", endTime=" + endTime + ", parentOID="
				+ parentOID + "]";
	}
    
}
