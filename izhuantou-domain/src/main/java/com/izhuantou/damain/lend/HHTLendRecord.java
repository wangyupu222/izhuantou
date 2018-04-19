package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投出借记录
 * 
 * @author yangbosen
 *
 */
public class HHTLendRecord extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String memeberOID;
    /**
     * 手机号
     */
    private String name;
    /**
     * 姓名
     */
    private String nameCN;
    /**
     * 金额
     */
    private BigDecimal principalMoney;
    /**
     * 出借时间（时间戳）
     */
    private Date addDateTime;
    /**
     * 出借时间YY-MM-DD
     */
    private String sj;

    private String money;

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    public String getMemeberOID() {
	return memeberOID;
    }

    public void setMemeberOID(String memeberOID) {
	this.memeberOID = memeberOID;
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

    public BigDecimal getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(BigDecimal principalMoney) {
	this.principalMoney = principalMoney;
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
	return "HHTLendRecord [memeberOID=" + memeberOID + ", name=" + name + ", nameCN=" + nameCN + ", principalMoney="
		+ principalMoney + ", addDateTime=" + addDateTime + ", sj=" + sj + ", money=" + money + "]";
    }

}
