package com.izhuantou.damain.vo;

import java.io.Serializable;

public class PageproblemsDTO implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 5373249251429330287L;
    private String OID;
    /**
     * 问题名称
     */
    private String name;
    /**
     * 发布内容
     */
    private String problemsContent;

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

    @Override
    public String toString() {
	return "PageproblemsDTO [OID=" + OID + ", name=" + name + ", problemsContent=" + problemsContent + "]";
    }

}
