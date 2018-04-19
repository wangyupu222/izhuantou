package com.izhuantou.damain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class FromLendInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 钱数
     */
    private BigDecimal money;
    /**
     * 
     */
    private String purpose;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 城市（省+市+县）
     */
    private String city;
    /**
     * 期数
     */
    private String loanTerm;
    /**
     * 登记时间
     */
    private Date addDateTime;
    /**
     * 省
     */
    private String s_province;
    /**
     * 市
     */
    private String s_city;
    /**
     * 县
     */
    private String s_county;
    /**
     * 短信验证码前端传的
     */
    private String yzm_reg;
    /**
     * 系统短信验证码
     */
    private String sys_yzmsg;

    public String getSys_yzmsg() {
	return sys_yzmsg;
    }

    public void setSys_yzmsg(String sys_yzmsg) {
	this.sys_yzmsg = sys_yzmsg;
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

    public String getYzm_reg() {
	return yzm_reg;
    }

    public void setYzm_reg(String yzm_reg) {
	this.yzm_reg = yzm_reg;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getPurpose() {
	return purpose;
    }

    public void setPurpose(String purpose) {
	this.purpose = purpose;
    }

    public String getCard() {
	return card;
    }

    public void setCard(String card) {
	this.card = card;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
	this.loanTerm = loanTerm;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "FromLendInfo [name=" + name + ", phone=" + phone + ", money=" + money + ", purpose=" + purpose
		+ ", card=" + card + ", city=" + city + ", loanTerm=" + loanTerm + ", addDateTime=" + addDateTime
		+ ", s_province=" + s_province + ", s_city=" + s_city + ", s_county=" + s_county + ", yzm_reg="
		+ yzm_reg + ", sys_yzmsg=" + sys_yzmsg + "]";
    }

}
