package com.izhuantou.damain.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 商户信息表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_seller")
public class PaySeller extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -5341331322700566013L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 商户号
     */
    @Column(name = "ID")
    private String ID;
    /**
     * 商户名
     */
    private String name;
    /**
     * 商户中文名
     */
    @Column(name = "nameCN")
    private String nameCN;
    /**
     * 描述
     */
    private String describe0;

    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "addUserOID")
    private String addUserOID;

    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */

    private Boolean valid;

    /**
     * 修改时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;

    /**
     * 增加时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 是否更新
     */
    private Boolean refresh;

    /**
     * 版本号
     */
    private Integer version;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getID() {
	return ID;
    }

    public void setID(String iD) {
	ID = iD;
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

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
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

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
    public String toString() {
	return "PaySeller [OID=" + OID + ", ID=" + ID + ", name=" + name + ", nameCN=" + nameCN + ", describe0="
		+ describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid="
		+ valid + ", updDateTime=" + updDateTime + ", addDateTime=" + addDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
