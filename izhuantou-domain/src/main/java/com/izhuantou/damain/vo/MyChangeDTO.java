package com.izhuantou.damain.vo;

import java.io.Serializable;

public class MyChangeDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7841041311630667165L;
    /**
     * 添加时间
     */
    private String addDateTime;
    /**
     * 请求状态
     */
    private String requestStatus;
    /**
     * 请求状态
     */
    private String successStatus;

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getRequestStatus() {
	return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
	this.requestStatus = requestStatus;
    }

    public String getSuccessStatus() {
	return successStatus;
    }

    public void setSuccessStatus(String successStatus) {
	this.successStatus = successStatus;
    }

    @Override
    public String toString() {
	return "MyChangeDTO [addDateTime=" + addDateTime + ", requestStatus=" + requestStatus + ", successStatus="
		+ successStatus + "]";
    }

}
