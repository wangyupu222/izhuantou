package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProcessChujieDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8200275878915935712L;
    /**
     * 类型
     */
    private String type;
    /**
     * 标的OID
     */
    private String biddingOID;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * 
     */
    private String mappingOID;
    /**
     * 出借金额
     */
    private BigDecimal amount;

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getBiddingOID() {
	return biddingOID;
    }

    public void setBiddingOID(String biddingOID) {
	this.biddingOID = biddingOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getMappingOID() {
	return mappingOID;
    }

    public void setMappingOID(String mappingOID) {
	this.mappingOID = mappingOID;
    }

    public BigDecimal getAmount() {
	return amount;
    }

    public void setAmount(BigDecimal amount) {
	this.amount = amount;
    }

    @Override
    public String toString() {
	return "ProcessChujieDTO [type=" + type + ", biddingOID=" + biddingOID + ", memberOID=" + memberOID + ", tqOID="
		+ tqOID + ", mappingOID=" + mappingOID + ", amount=" + amount + "]";
    }

}
