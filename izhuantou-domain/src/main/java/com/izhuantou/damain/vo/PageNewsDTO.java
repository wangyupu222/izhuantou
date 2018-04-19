package com.izhuantou.damain.vo;

import java.io.Serializable;

public class PageNewsDTO implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = -6343724447823435393L;
    /**
     * 标题名字
     */
    private String titleName;
    /**
     * 图片OID
     */
    private String ImgOID;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 添加的时间
     */
    private String addDateTime;
    /**
     * 添加的时间
     */
    private String addTime;

    /**
     * 图片路径
     */
    private String source;
    /**
     * 内容OID
     */
    private String OID;
    /**
     * 公告内容
     */
    private String messages;

    public String getAddTime() {
	return addTime;
    }

    public void setAddTime(String addTime) {
	this.addTime = addTime;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

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

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getTitleName() {
	return titleName;
    }

    public void setTitleName(String titleName) {
	this.titleName = titleName;
    }

    public String getMessages() {
	return messages;
    }

    public void setMessages(String messages) {
	this.messages = messages;
    }

    @Override
    public String toString() {
	return "PageNewsDTO [titleName=" + titleName + ", ImgOID=" + ImgOID + ", name=" + name + ", addDateTime="
		+ addDateTime + ", addTime=" + addTime + ", source=" + source + ", OID=" + OID + ", messages="
		+ messages + "]";
    }

    public String getImgOID() {
	return ImgOID;
    }

    public void setImgOID(String imgOID) {
	ImgOID = imgOID;
    }

}
