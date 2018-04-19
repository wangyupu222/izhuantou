package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * 还款期数
 * 
 * @author yangbosen
 *
 */
public class PayTerm extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 已还期数
     */
    private String yhnum;
    /**
     * 所有期数
     */
    private String allNum;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 用户OID
     */
    private String memberOID;

    public String getYhnum() {
	return yhnum;
    }

    public void setYhnum(String yhnum) {
	this.yhnum = yhnum;
    }

    public String getAllNum() {
	return allNum;
    }

    public void setAllNum(String allNum) {
	this.allNum = allNum;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "PayTerm [yhnum=" + yhnum + ", allNum=" + allNum + ", businessOID=" + businessOID + ", memberOID="
		+ memberOID + "]";
    }

}
