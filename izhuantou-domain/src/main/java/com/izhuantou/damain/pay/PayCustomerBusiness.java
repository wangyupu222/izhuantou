package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.izhuantou.damain.BasePojo;

/**
 * 账户业务记录表个人中心-资金流水
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "pay_customerbusiness")
public class PayCustomerBusiness extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -4167967975403659499L;

    /**
     * 客户OID
     */
    @Id
    @Column(name = "customerOID")
    private String customerOID;

    /**
     * 唯一标识OID
     */
    @Column(name = "OID")
    private String OID;
    /**
     * 用户OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作金额
     */
    private BigDecimal money;
    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作状态
     */
    private String state;

    /**
     * 数据来源 app、web
     * 
     * @return
     */
    private String laiyuan;

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
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;
    /**
     * 是否更新
     */
    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 
     */
    @Column(name = "dbName")
    private String dbName;
    /**
     * 
     */
    @Column(name = "tableName")
    private String tableName;
    /**
     * 
     */
    @Column(name = "linkOID")
    private String linkOID;
    /**
     * 
     */
    @Column(name = "linkDBName")
    private String linkDBName;
    /**
     * 
     */
    @Column(name = "linkTableName")
    private String linkTableName;

    public String getCustomerOID() {
	return customerOID;
    }

    public void setCustomerOID(String customerOID) {
	this.customerOID = customerOID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getLaiyuan() {
	return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
	this.laiyuan = laiyuan;
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

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
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

    public String getDbName() {
	return dbName;
    }

    public void setDbName(String dbName) {
	this.dbName = dbName;
    }

    public String getTableName() {
	return tableName;
    }

    public void setTableName(String tableName) {
	this.tableName = tableName;
    }

    public String getLinkOID() {
	return linkOID;
    }

    public void setLinkOID(String linkOID) {
	this.linkOID = linkOID;
    }

    public String getLinkDBName() {
	return linkDBName;
    }

    public void setLinkDBName(String linkDBName) {
	this.linkDBName = linkDBName;
    }

    public String getLinkTableName() {
	return linkTableName;
    }

    public void setLinkTableName(String linkTableName) {
	this.linkTableName = linkTableName;
    }

    /**
     * 返回给页面的时间
     */
    @Transient
    private String sj;

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    @Override
    public String toString() {
	return "PAY_CustomerBusiness [customerOID=" + customerOID + ", OID=" + OID + ", memberOID=" + memberOID
		+ ", content=" + content + ", money=" + money + ", type=" + type + ", state=" + state + ", laiyuan="
		+ laiyuan + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
		+ ", refresh=" + refresh + ", version=" + version + ", dbName=" + dbName + ", tableName=" + tableName
		+ ", linkOID=" + linkOID + ", linkDBName=" + linkDBName + ", linkTableName=" + linkTableName + ", sj="
		+ sj + "]";
    }

}
