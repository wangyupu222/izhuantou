package com.izhuantou.damain.vo.flow;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarLoanMemberInfoBaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3824372762676351207L;

    /**
     * 用户OID
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
     * 标的描述
     */
    private String loanuse;
    /**
     * 借款人姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 最高学历
     */
    private String highesteduback;

    /**
     * 户籍所在地
     */
    private String hkszd;
    /**
     * 借款人年收入(为已租代购产品复用添加字段) cannon 10.31
     */
    private BigDecimal grysr;

    /**
     * 车架号
     */
    private String cjh;

    /**
     * 车牌号
     */
    private String cph;

    /**
     * 发动机号
     */
    private String fdjh;

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

    public String getLoanuse() {
	return loanuse;
    }

    public void setLoanuse(String loanuse) {
	this.loanuse = loanuse;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
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

    public String getCjh() {
	return cjh;
    }

    public void setCjh(String cjh) {
	this.cjh = cjh;
    }

    public String getCph() {
	return cph;
    }

    public void setCph(String cph) {
	this.cph = cph;
    }

    public String getFdjh() {
	return fdjh;
    }

    public void setFdjh(String fdjh) {
	this.fdjh = fdjh;
    }

    @Override
    public String toString() {
	return "CarLoanMemberInfoBaseDTO [memberOID=" + memberOID + ", idCard=" + idCard + ", mobile=" + mobile
		+ ", loanuse=" + loanuse + ", realName=" + realName + ", gender=" + gender + ", age=" + age
		+ ", highesteduback=" + highesteduback + ", hkszd=" + hkszd + ", grysr=" + grysr + ", cjh=" + cjh
		+ ", cph=" + cph + ", fdjh=" + fdjh + "]";
    }

}
