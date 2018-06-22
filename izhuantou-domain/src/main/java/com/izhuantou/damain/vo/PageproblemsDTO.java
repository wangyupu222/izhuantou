package com.izhuantou.damain.vo;

import java.io.Serializable;
/**
 * 帮助中心问题DTO实体
 * @author aries
 *
 */
public class PageproblemsDTO implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 5373249251429330287L;
    private String OID;
    /**
     * 问题标题
     */
    private String name;
    /**
     * 问题作者
     */
    private String author;
    /**
     * 发布内容
     */
    private String problemsContent;
    /**
     * 生效时间
     */
    private String effectiveTime;
    /**
     * 类型OID
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

    public String getProblemsContent() {
	return problemsContent;
    }

    public void setProblemsContent(String problemsContent) {
	this.problemsContent = problemsContent;
    }
    
    public String getParentOID() {
		return parentOID;
	}

	public void setParentOID(String parentOID) {
		this.parentOID = parentOID;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	@Override
	public String toString() {
		return "PageproblemsDTO [OID=" + OID + ", name=" + name + ", author=" + author + ", problemsContent="
				+ problemsContent + ", effectiveTime=" + effectiveTime + ", parentOID=" + parentOID + "]";
	}

}
