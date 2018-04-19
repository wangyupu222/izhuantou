package com.izhuantou.damain.mobile.detial;

/**
 * 出借记录实体
 * 
 * @author yangbosen
 *
 */
public class MobileLendRecord {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * name：手机号
     */
    private String name;
    /**
     * nameCN：名字
     */
    private String nameCN;
    /**
     * addDateTime：出借时间
     */
    private String addDateTime;
    /**
     * money：金额
     */
    private String money;
    /**
     * principalMoney：金额
     */
    private String principalMoney;

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

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(String principalMoney) {
	this.principalMoney = principalMoney;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "LendRecord [name=" + name + ", nameCN=" + nameCN + ", addDateTime=" + addDateTime + ", money=" + money
		+ ", principalMoney=" + principalMoney + "]";
    }

}
