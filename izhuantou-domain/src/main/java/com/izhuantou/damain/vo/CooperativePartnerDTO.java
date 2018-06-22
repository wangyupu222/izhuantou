package com.izhuantou.damain.vo;

import java.io.Serializable;

import javax.persistence.Column;

public class CooperativePartnerDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131846312373506897L;
	/**
	 * OID
	 */
	private String OID;
    /**
     * 合作伙伴名称
     */
    private String name;
    /**
     * 图片路径
     */
    private String picimg;
    /**
     * 图片oid
     */
    private String picimgOID;
    /**
     * 合作伙伴链接地址
     */
    private String linkUrl;
    /**
     * 父结点OID
     */
    private String parentOID;
    /**
     * 序号
     */
    private Integer NO;
    /**
     * 图片真实名字
     */
    private String pName;
    /**
     * 物理名
     */
    private String physicalName;
    
    
	public Integer getNO() {
		return NO;
	}
	public void setNO(Integer nO) {
		NO = nO;
	}
	public String getPicimgOID() {
		return picimgOID;
	}
	public void setPicimgOID(String picimgOID) {
		this.picimgOID = picimgOID;
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
	public String getPicimg() {
		return picimg;
	}
	public void setPicimg(String picimg) {
		this.picimg = picimg;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getParentOID() {
		return parentOID;
	}
	public void setParentOID(String parentOID) {
		this.parentOID = parentOID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	@Override
	public String toString() {
		return "CooperativePartnerDTO [OID=" + OID + ", name=" + name + ", picimg=" + picimg + ", picimgOID="
				+ picimgOID + ", linkUrl=" + linkUrl + ", parentOID=" + parentOID + ", NO=" + NO + ", pName=" + pName
				+ ", physicalName=" + physicalName + "]";
	}
    
}
