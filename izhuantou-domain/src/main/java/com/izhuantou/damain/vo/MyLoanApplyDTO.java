package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人中心我的借款审核
 * @author dear
 * @version 1.0
 */
public class MyLoanApplyDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1435378269121859453L;
	/**
	 * 用户OID
	 */
	private String memberOID;
	/**
	 * 用户姓名
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
	private BigDecimal money;
	/**
	 * 借款期限
	 */
	private String loanTerm;
	/**
	 * 产品名称
	 */
	private String loanYT;
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
	 * 借贷利率
	 */
	private String rate;
	/**
	 * 借款OID
	 */
	private String loanOID;
	/**
	 * 短信
	 */
	private String mesYzm;
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
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	public String getLoanYT() {
		return loanYT;
	}
	public void setLoanYT(String loanYT) {
		this.loanYT = loanYT;
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
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getLoanOID() {
		return loanOID;
	}
	public void setLoanOID(String loanOID) {
		this.loanOID = loanOID;
	}
	public String getMesYzm() {
		return mesYzm;
	}
	public void setMesYzm(String mesYzm) {
		this.mesYzm = mesYzm;
	}
	@Override
	public String toString() {
		return "MyLoanaApplyDTO [memberOID=" + memberOID + ", name=" + name + ", card=" + card + ", phone=" + phone
				+ ", money=" + money + ", loanTerm=" + loanTerm + ", loanYT=" + loanYT + ", s_province=" + s_province
				+ ", s_city=" + s_city + ", s_county=" + s_county + ", rate=" + rate + ", loanOID=" + loanOID
				+ ", mesYzm=" + mesYzm + "]";
	}
	
	
	
}
