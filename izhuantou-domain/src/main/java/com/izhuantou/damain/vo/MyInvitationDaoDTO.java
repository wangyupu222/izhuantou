package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MyInvitationDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7052745967424308576L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 真实姓名
     */
    private String nameCN;
    /**
     * 注册时间
     */
    private String zcsj;
    /**
     * 用户的OID
     */
    private String OID;

    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 用户的OID
     */
    private String memberOID;
    /**
     * 用户的OID
     */
    private String addDateTime;
    /**
     * 用户的OID
     */
    private String type;

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

    public String getZcsj() {
	return zcsj;
    }

    public void setZcsj(String zcsj) {
	this.zcsj = zcsj;
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

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    @Override
    public String toString() {
	return "MyInvitationDaoDTO [name=" + name + ", nameCN=" + nameCN + ", zcsj=" + zcsj + ", OID=" + OID
		+ ", money=" + money + ", memberOID=" + memberOID + ", addDateTime=" + addDateTime + ", type=" + type
		+ "]";
    }

}
