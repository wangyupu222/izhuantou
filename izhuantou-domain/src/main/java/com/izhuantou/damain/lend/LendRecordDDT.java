package com.izhuantou.damain.lend;

import java.util.Date;

import com.izhuantou.damain.BasePojo;

/**
 * DDT出借记录实体
 * 
 * @author yangbosen
 *
 */
public class LendRecordDDT extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * OID
     */
    private String OID;
    /**
     * 金额
     */
    private String money;
    /**
     * 手机号
     */
    private String name;
    /**
     * 姓名
     */
    private String nameCN;
    /**
     * 出借时间
     */
    private Date addDateTime;
    private String sj;

    private String principalMoney;

    public String getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(String principalMoney) {
	this.principalMoney = principalMoney;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
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

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "LendRecordDDT [OID=" + OID + ", money=" + money + ", name=" + name + ", nameCN=" + nameCN
		+ ", addDateTime=" + addDateTime + ", sj=" + sj + ", principalMoney=" + principalMoney + "]";
    }

}
