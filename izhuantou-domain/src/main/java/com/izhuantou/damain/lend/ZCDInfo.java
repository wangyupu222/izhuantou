package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * 车抵债转信息
 * 
 * @author yangbosen
 *
 */
public class ZCDInfo extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户OID
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
    private String age;
    /**
     * 身份证好
     */
    private String idCard;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 学历哦
     */
    private String highesteduback;
    /**
     * 户口所在地
     */
    private String hkszd;
    /**
     * 个人应收入
     */
    private String grysr;

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

    public String getAge() {
	return age;
    }

    public void setAge(String age) {
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

    public String getGrysr() {
	return grysr;
    }

    public void setGrysr(String grysr) {
	this.grysr = grysr;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ZCDInfo [memberOID=" + memberOID + ", realName=" + realName + ", loanuse=" + loanuse + ", gender="
		+ gender + ", age=" + age + ", idCard=" + idCard + ", mobile=" + mobile + ", highesteduback="
		+ highesteduback + ", hkszd=" + hkszd + ", grysr=" + grysr + "]";
    }

}
