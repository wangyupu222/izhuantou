package com.izhuantou.damain.vo;

import java.io.Serializable;

public class FlinkDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1063030339244090551L;

    /**
     * 序列号定义名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String linkUrl;

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
	return "FlinkDTO [name=" + name + ", linkUrl=" + linkUrl + "]";
    }

}
