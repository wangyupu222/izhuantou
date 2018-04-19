package com.izhuantou.damain.vo;

import java.io.Serializable;

public class MemberAgrennmentDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1977685233305081206L;

    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 
     */
    private String DOID;
    /**
     * 类型
     */
    private String type;
    /**
     * 成功OID(可通过此id查协议)businessOID
     */
    private String biOID;
    /**
     * 资金池oID
     */
    private String CashPoolOID;
    /**
     * 借款标题
     */
    private String loanNumber;

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    @Override
    public String toString() {
	return "MemberAgrennmentDTO [memberOID=" + memberOID + ", DOID=" + DOID + ", type=" + type + ", biOID=" + biOID
		+ ", CashPoolOID=" + CashPoolOID + ", loanNumber=" + loanNumber + "]";
    }

}
