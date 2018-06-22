package com.izhuantou.damain.vo;

import java.io.Serializable;

import javax.persistence.Column;

public class FlinkDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1063030339244090551L;
    
    /**
     * id
     */
    private String OID;
    /**
     * 序列号定义名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 序号
     */
    private Integer NO;
    /**
     * 父类id
     */
    private String parentOID;
    
    
    public String getParentOID() {
		return parentOID;
	}

	public void setParentOID(String parentOID) {
		this.parentOID = parentOID;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public Integer getNO() {
		return NO;
	}

	public void setNO(Integer nO) {
		NO = nO;
	}

	public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLinkUrl() {
	return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
	this.linkUrl = linkUrl;
    }

    @Override
	public String toString() {
		return "FlinkDTO [OID=" + OID + ", name=" + name + ", linkUrl=" + linkUrl + ", NO=" + NO + ", parentOID="
				+ parentOID + "]";
	}

}
