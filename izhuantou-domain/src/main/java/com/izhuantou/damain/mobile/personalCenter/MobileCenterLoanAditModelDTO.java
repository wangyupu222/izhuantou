package com.izhuantou.damain.mobile.personalCenter;

/**
 * 个人中心借款 申请借款与借款审核修改的移动端口入参DTO
 * 
 * @author yangbosen
 *
 */
public class MobileCenterLoanAditModelDTO {
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 借款金额
     */
    private String money;
    /**
     * 借款类型
     */
    private String proType;
    /**
     * 省
     */
    private String s_province;
    /**
     * 市
     */
    private String s_city;
    /**
     * 区
     */
    private String s_county;
    /**
     * 借款期限
     */
    private String loanTerm;

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCard() {
	return card;
    }

    public void setCard(String card) {
	this.card = card;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getProType() {
	return proType;
    }

    public void setProType(String proType) {
	this.proType = proType;
    }

    public String getS_province() {
	return s_province;
    }

    public void setS_province(String s_province) {
	this.s_province = s_province;
    }

    public String getS_city() {
	return s_city;
    }

    public void setS_city(String s_city) {
	this.s_city = s_city;
    }

    public String getS_county() {
	return s_county;
    }

    public void setS_county(String s_county) {
	this.s_county = s_county;
    }

    public String getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
	this.loanTerm = loanTerm;
    }

    @Override
    public String toString() {
	return "MobileCenterLoanAditModelDTO [memberOID=" + memberOID + ", name=" + name + ", card=" + card + ", phone="
		+ phone + ", money=" + money + ", proType=" + proType + ", s_province=" + s_province + ", s_city="
		+ s_city + ", s_county=" + s_county + ", loanTerm=" + loanTerm + "]";
    }

}
