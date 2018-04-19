package com.izhuantou.damain.user;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 用户特权
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "member_memberprivilege")
public class MemberMemberPrivilege extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 4672927250881623732L;
    /**
     * 唯一标识OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 特权OID
     */
    @Column(name = "tqOID")
    private String tqOID;
    /**
     * 协议英文名称
     */
    private String name;
    /**
     * 协议中文名称
     */
    @Column(name = "nameCN")
    private String nameCN;
    /**
     * 协议类型
     */
    private String type;

    /**
     * 特权金额
     */
    @Column(name = "tqAmount")
    private BigDecimal tqAmount;

    /**
     * 开始时间
     */
    @Column(name = "tqbTime")
    private Date tqbTime;

    /**
     * 结束时间
     */
    @Column(name = "tqEndTime")
    private Date tqEndTime;

    /**
     * 产品期限（月）
     */
    @Column(name = "productTerm")
    private String productTerm;

    /**
     * 特权条件
     */
    @Column(name = "tqTJ")
    private String tqTJ;
    /**
     * 特权利率
     */
    private BigDecimal tqll;
    /**
     * 特权状态(0：未使用 (默认)1：已使用2：已过期)
     */
    private String state;
    /**
     * 用户产品名称
     */
    @Column(name = "biddingName")
    private String biddingName;
    /**
     * 特权规则
     */
    private String tqgz;

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
     * 添加的时间
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

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
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

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public BigDecimal getTqAmount() {
	return tqAmount;
    }

    public void setTqAmount(BigDecimal tqAmount) {
	this.tqAmount = tqAmount;
    }

    public Date getTqbTime() {
	return tqbTime;
    }

    public void setTqbTime(Date tqbTime) {
	this.tqbTime = tqbTime;
    }

    public Date getTqEndTime() {
	return tqEndTime;
    }

    public void setTqEndTime(Date tqEndTime) {
	this.tqEndTime = tqEndTime;
    }

    public String getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(String productTerm) {
	this.productTerm = productTerm;
    }

    public String getTqTJ() {
	return tqTJ;
    }

    public void setTqTJ(String tqTJ) {
	this.tqTJ = tqTJ;
    }

    public BigDecimal getTqll() {
	return tqll;
    }

    public void setTqll(BigDecimal tqll) {
	this.tqll = tqll;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public String getTqgz() {
	return tqgz;
    }

    public void setTqgz(String tqgz) {
	this.tqgz = tqgz;
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

    @Override
    public String toString() {
	return "MEMBER_MemberPrivilege [OID=" + OID + ", memberOID=" + memberOID + ", tqOID=" + tqOID + ", name=" + name
		+ ", nameCN=" + nameCN + ", type=" + type + ", tqAmount=" + tqAmount + ", tqbTime=" + tqbTime
		+ ", tqEndTime=" + tqEndTime + ", productTerm=" + productTerm + ", tqTJ=" + tqTJ + ", tqll=" + tqll
		+ ", state=" + state + ", biddingName=" + biddingName + ", tqgz=" + tqgz + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}