package com.izhuantou.damain.pay;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 特权红包加息与产品配置信息关系映射
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_privilegeproductmapping")
public class PayPrivilegeProductMapping extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 760526142131185200L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;

    /**
     * 特权对应记录OID
     */
    @Column(name = "privilegeOID")
    private String privilegeOID;
    /**
     * 产品配置信息对应记录OID
     */
    @Column(name = "productInfoOID")
    private String productInfoOID;
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
    /**
     * 加息天数
     */
    @Column(name = "JXDay")
    private Integer JXDay;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getPrivilegeOID() {
	return privilegeOID;
    }

    public void setPrivilegeOID(String privilegeOID) {
	this.privilegeOID = privilegeOID;
    }

    public String getProductInfoOID() {
	return productInfoOID;
    }

    public void setProductInfoOID(String productInfoOID) {
	this.productInfoOID = productInfoOID;
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

    public Integer getJXDay() {
	return JXDay;
    }

    public void setJXDay(Integer jXDay) {
	JXDay = jXDay;
    }

    @Override
    public String toString() {
	return "PayPrivilegeProductMapping [OID=" + OID + ", privilegeOID=" + privilegeOID + ", productInfoOID="
		+ productInfoOID + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", updDateTime=" + updDateTime + ", addDateTime="
		+ addDateTime + ", refresh=" + refresh + ", version=" + version + ", JXDay=" + JXDay + "]";
    }

}
