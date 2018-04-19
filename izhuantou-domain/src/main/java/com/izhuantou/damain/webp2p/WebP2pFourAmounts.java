package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.izhuantou.damain.BasePojo;

/**
 * 首页平台四个数据显示实体类
 * 
 * @author yangbosen
 *
 */
public class WebP2pFourAmounts extends BasePojo {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String OID;
    /**
     * 累计交易额
     */
    private BigDecimal money;
    /**
     * 为用户赚钱收益
     */
    private BigDecimal interestMoney;
    /**
     * 项目总数
     */
    private long business;
    /**
     * 累计用户数
     */
    private Integer member;
    /**
     * 安全运营时间
     */
    private long sumDay;

    private String addUserOID;

    private String updUserOID;

    /**
     * 累计交易额(美式格式表示)
     */
    private String money1;
    /**
     * 为用户赚钱收益(美式格式表示)
     */
    private String insertMoney1;
    /**
     * 累计用户数(美式格式表示)
     */
    private String member1;
    /**
     * 安全运营时间(美式格式表示)
     */
    private String sumDay1;

    public String getMoney1() {
	return money1;
    }

    public void setMoney1(String money1) {
	this.money1 = money1;
    }

    public String getInsertMoney1() {
	return insertMoney1;
    }

    public void setInsertMoney1(String insertMoney1) {
	this.insertMoney1 = insertMoney1;
    }

    public String getMember1() {
	return member1;
    }

    public void setMember1(String member1) {
	this.member1 = member1;
    }

    public String getSumDay1() {
	return sumDay1;
    }

    public void setSumDay1(String sumDay1) {
	this.sumDay1 = sumDay1;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getInterestMoney() {
	return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
	this.interestMoney = interestMoney;
    }

    public long getBusiness() {
	return business;
    }

    public void setBusiness(long business) {
	this.business = business;
    }

    public Integer getMember() {
	return member;
    }

    public void setMember(Integer member) {
	this.member = member;
    }

    public String getAddUserOID() {
	return addUserOID;
    }

    public void setAddUserOID(String addUserOID) {
	this.addUserOID = addUserOID;
    }

    public String getUpdUserOID() {
	return updUserOID;
    }

    public void setUpdUserOID(String updUserOID) {
	this.updUserOID = updUserOID;
    }

    public long getSumDay() {
	return sumDay;
    }

    public void setSumDay(long sumDay) {
	this.sumDay = sumDay;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "WEBP2P_fourAmounts [OID=" + OID + ", money=" + money + ", interestMoney=" + interestMoney
		+ ", business=" + business + ", member=" + member + ", sumDay=" + sumDay + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", money1=" + money1 + ", insertMoney1=" + insertMoney1 + ", member1="
		+ member1 + ", sumDay1=" + sumDay1 + "]";
    }

}
