package com.izhuantou.damain.mobile.personalCenter;

import java.io.Serializable;

public class MobileLoginReturnDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5890716841389951270L;
    /**
     * ：用户OID
     */
    private String OID;
    /**
     * ：是否实名
     */
    private String Smtype;

    /**
     * ：注册时间
     */
    private String addDateTime;

    /**
     * ：银行卡号
     */
    private String bankcardnumber;
    /**
     * ：银行编号
     */
    private String bankname;
    /**
     * ：身份证号
     */
    private String idCard;
    /**
     * ：是否为新手
     */
    private String isNew;
    /**
     * ：砖头账号
     */
    private String memberAccount;

    /**
     * ：手机号
     */
    private String mobile;
    /**
     * ：用户名称
     */
    private String name;
    /**
     * ：真实姓名
     */
    private String nameCN;
    /**
     * ：头像地址
     */
    private String picOID;
    /**
     * ：密保问题一
     */
    private String questionOne;
    /**
     * ：密保问题二
     */
    private String questionTwo;

    /**
     * ：真实姓名
     */
    private String realName;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getSmtype() {
	return Smtype;
    }

    public void setSmtype(String smtype) {
	Smtype = smtype;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getBankcardnumber() {
	return bankcardnumber;
    }

    public void setBankcardnumber(String bankcardnumber) {
	this.bankcardnumber = bankcardnumber;
    }

    public String getBankname() {
	return bankname;
    }

    public void setBankname(String bankname) {
	this.bankname = bankname;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getIsNew() {
	return isNew;
    }

    public void setIsNew(String isNew) {
	this.isNew = isNew;
    }

    public String getMemberAccount() {
	return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
	this.memberAccount = memberAccount;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getPicOID() {
	return picOID;
    }

    public void setPicOID(String picOID) {
	this.picOID = picOID;
    }

    public String getQuestionOne() {
	return questionOne;
    }

    public void setQuestionOne(String questionOne) {
	this.questionOne = questionOne;
    }

    public String getQuestionTwo() {
	return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
	this.questionTwo = questionTwo;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    @Override
    public String toString() {
	return "MobileLoginReturnDTO [OID=" + OID + ", Smtype=" + Smtype + ", addDateTime=" + addDateTime
		+ ", bankcardnumber=" + bankcardnumber + ", bankname=" + bankname + ", idCard=" + idCard + ", isNew="
		+ isNew + ", memberAccount=" + memberAccount + ", mobile=" + mobile + ", name=" + name + ", nameCN="
		+ nameCN + ", picOID=" + picOID + ", questionOne=" + questionOne + ", questionTwo=" + questionTwo
		+ ", realName=" + realName + "]";
    }

}
