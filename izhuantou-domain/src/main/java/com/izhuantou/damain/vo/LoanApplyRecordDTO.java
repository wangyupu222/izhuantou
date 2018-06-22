package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 借款审核记录
 * @author dear
 *
 */
public class LoanApplyRecordDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6895068216981374043L;
	/**
     * 登录用户OID
     */
    private String memberOID;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 增加记录时间
     */
    private String addDateTime;
    /**
     * 借款标题
     */
    private String loanYT;
    /**
     * 借款金额
     */
    private BigDecimal loanAmount;
    /**
     * 借款期数
     */
    private String loanTerm;
    /**
     * 初审
     */
    private String first;
    /**
     * 复审
     */
    private String second;
    /**
     * 
     */
    private String OID;
    
    private String handBase;
    
    public String getHandBase() {
		return handBase;
	}

	public void setHandBase(String handBase) {
		this.handBase = handBase;
	}

	public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getLoanYT() {
	return loanYT;
    }

    public void setLoanYT(String loanYT) {
	this.loanYT = loanYT;
    }

    public BigDecimal getLoanAmount() {
	return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
	this.loanAmount = loanAmount;
    }

    public String getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
	this.loanTerm = loanTerm;
    }

    public String getFirst() {
	return first;
    }

    public void setFirst(String first) {
	this.first = first;
    }

    public String getSecond() {
	return second;
    }

    public void setSecond(String second) {
	this.second = second;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
	public String toString() {
		return "LoanApplyRecordDTO [memberOID=" + memberOID + ", idCard=" + idCard + ", mobile=" + mobile
				+ ", userName=" + userName + ", addDateTime=" + addDateTime + ", loanYT=" + loanYT + ", loanAmount="
				+ loanAmount + ", loanTerm=" + loanTerm + ", first=" + first + ", second=" + second + ", OID=" + OID
				+ ", handBase=" + handBase + "]";
	}

}
