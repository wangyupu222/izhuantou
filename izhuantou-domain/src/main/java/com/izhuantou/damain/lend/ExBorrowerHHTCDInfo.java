package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投车抵借款人信息实体
 * 
 * @author yangbosen
 *
 */
public class ExBorrowerHHTCDInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String memberOID;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 描述
     */
    private String loanuse;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 学历
     */
    private String highesteduback;
    /**
     * 户口所在地
     */
    private String hkszd;
    /**
     * 个人收入
     */
    private BigDecimal grysr;
    /**
     * 借款金额
     */
    private BigDecimal jkze;
    /**
     * 产品编号
     */
    private String loanNumber;
    /**
     * 债转编号
     */
    private String debtLoanNumber;

    public String getDebtLoanNumber() {
	return debtLoanNumber;
    }

    public void setDebtLoanNumber(String debtLoanNumber) {
	this.debtLoanNumber = debtLoanNumber;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public BigDecimal getJkze() {
	return jkze;
    }

    public void setJkze(BigDecimal jkze) {
	this.jkze = jkze;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    public String getLoanuse() {
	return loanuse;
    }

    public void setLoanuse(String loanuse) {
	this.loanuse = loanuse;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
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

    public String getHighesteduback() {
	return highesteduback;
    }

    public void setHighesteduback(String highesteduback) {
	this.highesteduback = highesteduback;
    }

    public String getHkszd() {
	return hkszd;
    }

    public void setHkszd(String hkszd) {
	this.hkszd = hkszd;
    }

    public BigDecimal getGrysr() {
	return grysr;
    }

    public void setGrysr(BigDecimal grysr) {
	this.grysr = grysr;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ExBorrowerHHTCDInfo [memberOID=" + memberOID + ", realName=" + realName + ", loanuse=" + loanuse
		+ ", gender=" + gender + ", age=" + age + ", idCard=" + idCard + ", mobile=" + mobile
		+ ", highesteduback=" + highesteduback + ", hkszd=" + hkszd + ", grysr=" + grysr + ", jkze=" + jkze
		+ ", loanNumber=" + loanNumber + ", debtLoanNumber=" + debtLoanNumber + "]";
    }

}
