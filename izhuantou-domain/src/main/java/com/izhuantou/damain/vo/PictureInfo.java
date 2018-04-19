package com.izhuantou.damain.vo;

import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class PictureInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String OID;
    /**
     * 图片地址
     */
    private String name;
    /**
     * 图片来源
     */
    private String source;
    /**
     * 物理地址名
     */
    private String physicalName;
    /**
     * 路径
     */
    private String relativePath;
    /**
     * 长度
     */
    private String length;
    /**
     * 
     */
    private String contentOID;
    /**
     * 更新时间
     */
    private Date uploadDateTime;
    /**
     * md5加密值
     */
    private String md5;
    /**
     * 
     */
    /*
     * private String describe0;
     *//**
      * 
      */
    /*
     * private Integer valid;
     *//**
      * 
      */
    /*
     * private Integer version;
     *//**
      * 
      *//*
	 * private Integer refresh;
	 */

    private Object selectImg;

    private String selectImgFileName;

    public Object getSelectImg() {
	return selectImg;
    }

    public void setSelectImg(Object selectImg) {
	this.selectImg = selectImg;
    }

    public String getSelectImgFileName() {
	return selectImgFileName;
    }

    public void setSelectImgFileName(String selectImgFileName) {
	this.selectImgFileName = selectImgFileName;
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

    public String getPhysicalName() {
	return physicalName;
    }

    public void setPhysicalName(String physicalName) {
	this.physicalName = physicalName;
    }

    public String getRelativePath() {
	return relativePath;
    }

    public void setRelativePath(String relativePath) {
	this.relativePath = relativePath;
    }

    public String getLength() {
	return length;
    }

    public void setLength(String length) {
	this.length = length;
    }

    public String getContentOID() {
	return contentOID;
    }

    public void setContentOID(String contentOID) {
	this.contentOID = contentOID;
    }

    public Date getUploadDateTime() {
	return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
	this.uploadDateTime = uploadDateTime;
    }

    public String getMd5() {
	return md5;
    }

    public void setMd5(String md5) {
	this.md5 = md5;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "PictureInfo [OID=" + OID + ", name=" + name + ", source=" + source + ", physicalName=" + physicalName
		+ ", relativePath=" + relativePath + ", length=" + length + ", contentOID=" + contentOID
		+ ", uploadDateTime=" + uploadDateTime + ", md5=" + md5 + ", selectImg=" + selectImg
		+ ", selectImgFileName=" + selectImgFileName + "]";
    }

}
