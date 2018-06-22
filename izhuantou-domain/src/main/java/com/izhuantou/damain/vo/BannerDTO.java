package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class BannerDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2132057079180912432L;
    /**
     * 通过页面传来的 parentOID(从哪里来的还不知道)查询 p2p_pagebanner表 获取了跳转地址,名字和图片路径id: picimg
     * ,通过图片路径id:picimg可以在fiel_info获得图片的OID
     * 
     */

    private String OID;
    /**
     * 定义名称
     */
    private String name;
    /**
     * 图片路径
     */
    private String picimg;
    /**
     * banner缩略图
     */
    private String picimgOID;
    /**
     * web广告跳转地址
     */
    private String jumpurl;
    /**
     * app广告跳转地址
     */
    private String appurl;
    /**
     * 父结点OID
     */
    private String parentOID;
    /**
     * 图片真实名字
     */
    private String pName;
    /**
     * 物理名
     */
    private String physicalName;
    /**
     * 轮播广告类型(0：已下线，1：投放中)
     */
    private Integer state;
    /**
     * 投放起始时间
     */
    private String startTime;
    /**
     * 投放终止时间
     */
    private String endTime;
    
    public String getAppurl() {
		return appurl;
	}

	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getPicimgOID() {
		return picimgOID;
	}

	public void setPicimgOID(String picimgOID) {
		this.picimgOID = picimgOID;
	}

	public String getpName() {
	return pName;
    }

    public void setpName(String pName) {
	this.pName = pName;
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

    public String getJumpurl() {
	return jumpurl;
    }

    public void setJumpurl(String jumpurl) {
	this.jumpurl = jumpurl;
    }

    public String getParentOID() {
	return parentOID;
    }

    public void setParentOID(String parentOID) {
	this.parentOID = parentOID;
    }

    public String getPhysicalName() {
	return physicalName;
    }

    public void setPhysicalName(String physicalName) {
	this.physicalName = physicalName;
    }

    @Override
	public String toString() {
		return "BannerDTO [OID=" + OID + ", name=" + name + ", picimg=" + picimg + ", picimgOID=" + picimgOID
				+ ", jumpurl=" + jumpurl + ", appurl=" + appurl + ", parentOID=" + parentOID + ", pName=" + pName
				+ ", physicalName=" + physicalName + ", state=" + state + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

}
