package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

/**
 * 出借记录实体
 * 
 * @author yangbosen
 *
 */
public class LendRecord extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 手机号 */
    private String mobile;
    /** 出借金额 */
    private BigDecimal money;
    /** 出借时间 */
    private Date addDateTime;
    /** 手机号 */
    private String name;

    private String principalMoney;

    private String nameCN;

    public String getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(String principalMoney) {
	this.principalMoney = principalMoney;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String sj;

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
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
	return "LendRecord [mobile=" + mobile + ", money=" + money + ", addDateTime=" + addDateTime + ", name=" + name
		+ ", principalMoney=" + principalMoney + ", nameCN=" + nameCN + ", sj=" + sj + "]";
    }

}
