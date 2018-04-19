package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 资金池操作记录 显示资金操作状态，如冻结金额
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "pay_cashpooloperation")
public class PayCashPoolOperation extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 4151658872325285022L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 业务OID
     */
    private String businessOID;
    /**
     * 入会员OID
     */
    @Column(name = "inMemberOID")
    private String inMemberOID;
    /**
     * 出会员OID
     */
    @Column(name = "outMemberOID")
    private String outMemberOID;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作金额
     */
    private BigDecimal money;
    /**
     * 
     * 数据来源 app\web
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

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getInMemberOID() {
	return inMemberOID;
    }

    public void setInMemberOID(String inMemberOID) {
	this.inMemberOID = inMemberOID;
    }

    public String getOutMemberOID() {
	return outMemberOID;
    }

    public void setOutMemberOID(String outMemberOID) {
	this.outMemberOID = outMemberOID;
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

    @Override
    public String toString() {
	return "PAY_CashPoolOperation [OID=" + OID + ", businessOID=" + businessOID + ", inMemberOID=" + inMemberOID
		+ ", outMemberOID=" + outMemberOID + ", content=" + content + ", money=" + money + ", laiyuan="
		+ laiyuan + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
		+ ", refresh=" + refresh + ", version=" + version + "]";
    }

}
