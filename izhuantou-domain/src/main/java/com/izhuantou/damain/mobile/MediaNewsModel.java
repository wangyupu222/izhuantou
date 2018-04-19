package com.izhuantou.damain.mobile;

/**
 * 移动端媒体动态实体
 * 
 * @author yangbosen
 *
 */
public class MediaNewsModel {

    /**
     * 媒体公告
     */
    private static final long serialVersionUID = 1L;
    // ：标题
    private String name;
    /**
     * ：时间
     */
    private String updDateTime;
    /**
     * ：图片地址
     */
    private String uploadImgOID;
    /**
     * ：内容详情
     */
    private String messages;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(String updDateTime) {
	this.updDateTime = updDateTime;
    }

    public String getUploadImgOID() {
	return uploadImgOID;
    }

    public void setUploadImgOID(String uploadImgOID) {
	this.uploadImgOID = uploadImgOID;
    }

    public String getMessages() {
	return messages;
    }

    public void setMessages(String messages) {
	this.messages = messages;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MediaNewsModel [name=" + name + ", updDateTime=" + updDateTime + ", uploadImgOID=" + uploadImgOID
		+ ", messages=" + messages + "]";
    }

}
