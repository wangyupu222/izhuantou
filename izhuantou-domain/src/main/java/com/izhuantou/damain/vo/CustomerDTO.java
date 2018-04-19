package com.izhuantou.damain.vo;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2808731231845085360L;

    /**
     * memberOID
     */
    private String memberOID;
    /**
     * name
     */
    private String name;

    /**
     * 持卡人姓名
     */
    private String cardName;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 绑卡银行 银行编号
     */
    private String bindBank;
    /**
     * 开户地址
     */
    private String city;
    /**
     * 银行卡号
     */
    private String bankNumber;
    /**
     * 银行预留手机号
     */
    private String phone;
    /**
     * 提现密码
     */
    private String withdrawalsPwd;
    /**
     * 手机验证码
     */
    private String msm;
    /**
     * 银行名字
     */
    private String banckName;
    /**
     * 秘钥
     */
    private String pkversion;

    public String getPkversion() {
	return pkversion;
    }

    public void setPkversion(String pkversion) {
	this.pkversion = pkversion;
    }

    public String getBanckName() {
	return banckName;
    }

    public void setBanckName(String banckName) {
	this.banckName = banckName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getCardName() {
	return cardName;
    }

    public void setCardName(String cardName) {
	this.cardName = cardName;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getBindBank() {
	return bindBank;
    }

    public void setBindBank(String bindBank) {
	this.bindBank = bindBank;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getBankNumber() {
	return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
	this.bankNumber = bankNumber;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getWithdrawalsPwd() {
	return withdrawalsPwd;
    }

    public void setWithdrawalsPwd(String withdrawalsPwd) {
	this.withdrawalsPwd = withdrawalsPwd;
    }

    public String getMsm() {
	return msm;
    }

    public void setMsm(String msm) {
	this.msm = msm;
    }

    @Override
    public String toString() {
	return "CustomerDTO [memberOID=" + memberOID + ", name=" + name + ", cardName=" + cardName + ", idCard="
		+ idCard + ", bindBank=" + bindBank + ", city=" + city + ", bankNumber=" + bankNumber + ", phone="
		+ phone + ", withdrawalsPwd=" + withdrawalsPwd + ", msm=" + msm + ", banckName=" + banckName
		+ ", pkversion=" + pkversion + "]";
    }

}
