package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayCustomerDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8396266789220288400L;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作金额
     */
    private BigDecimal money;

    /**
     * 请求标识
     */
    private String requestID;

    /**
     * 添加的时间
     */
    private String addDateTime;

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getRequestID() {
	return requestID;
    }

    public void setRequestID(String requestID) {
	this.requestID = requestID;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    @Override
    public String toString() {
	return "PayCustomerDTO [content=" + content + ", money=" + money + ", requestID=" + requestID + ", addDateTime="
		+ addDateTime + "]";
    }

}
