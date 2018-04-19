package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class RechargeDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1814683363420879113L;

    /**
     * 用户OID
     */
    private String memberOID;

    /**
     * 钱数
     */
    private BigDecimal money;
    /**
     * 银行的机构代码
     */
    private String card_id;
    /**
     * 类型
     */
    private String type;
    /**
     * 地址
     */
    private String url;
    /**
     * 验证码
     */
    private String yzm;

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getYzm() {
	return yzm;
    }

    public void setYzm(String yzm) {
	this.yzm = yzm;
    }

    public String getCard_id() {
	return card_id;
    }

    public void setCard_id(String card_id) {
	this.card_id = card_id;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public BigDecimal getMoney() {
	return money;
    }

    @Override
    public String toString() {
	return "RechargeDTO [memberOID=" + memberOID + ", money=" + money + ", card_id=" + card_id + ", type=" + type
		+ ", url=" + url + ", yzm=" + yzm + "]";
    }

}
