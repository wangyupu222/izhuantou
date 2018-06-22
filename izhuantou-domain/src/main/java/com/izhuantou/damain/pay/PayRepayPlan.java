package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 借款人还款计划
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "pay_repayplan")
public class PayRepayPlan extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1371768045910436033L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 业务OID
     */
    @Column(name = "businessOID")
    private String businessOID;
    /**
     * 出会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 还款日期
     */
    @Column(name = "repayDate")
    private Date repayDate;

    /**
     * 每期要还总体金额
     */
    private BigDecimal money;

    /**
     * 状态
     */
    private String state;

    /**
     * 每期的本金
     */
    @Column(name = "principalMoney")
    private BigDecimal principalMoney;
    /**
     * 每期利息
     */
    @Column(name = "interestMoney")
    private BigDecimal interestMoney;

    /**
     * 已还本金合计
     */
    @Column(name = "totalPrincipalMoney")
    private BigDecimal totalPrincipalMoney;
    /**
     * 已还利息合计
     */
    @Column(name = "totalInterestMoney")
    private BigDecimal totalInterestMoney;

    /**
     * 剩余本金
     */
    @Column(name = "surplusPrincipal")
    private BigDecimal surplusPrincipal;

    /**
     * 管理费
     */
    @Column(name = "manageMoney")
    private BigDecimal manageMoney;
    /**
     * 服务费
     */
    @Column(name = "serviceMoney")
    private BigDecimal serviceMoney;

    /**
     * 利率
     */
    private BigDecimal rate;
    /**
     * 逾期管理费
     */
    private BigDecimal yqglf;
    /**
     * 逾期违约金
     */
    private BigDecimal yqwyj;
    /**
     * 逾期罚息
     */
    private BigDecimal yqfx;
    /**
     * 是否逾期（0：未逾期；1还款逾期）
     */
    @Column(name = "isYQ")
    private String isYQ;

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

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public Date getRepayDate() {
	return repayDate;
    }

    public void setRepayDate(Date repayDate) {
	this.repayDate = repayDate;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public BigDecimal getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(BigDecimal principalMoney) {
	this.principalMoney = principalMoney;
    }

    public BigDecimal getInterestMoney() {
	return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
	this.interestMoney = interestMoney;
    }

    public BigDecimal getTotalPrincipalMoney() {
	return totalPrincipalMoney;
    }

    public void setTotalPrincipalMoney(BigDecimal totalPrincipalMoney) {
	this.totalPrincipalMoney = totalPrincipalMoney;
    }

    public BigDecimal getTotalInterestMoney() {
	return totalInterestMoney;
    }

    public void setTotalInterestMoney(BigDecimal totalInterestMoney) {
	this.totalInterestMoney = totalInterestMoney;
    }

    public BigDecimal getSurplusPrincipal() {
	return surplusPrincipal;
    }

    public void setSurplusPrincipal(BigDecimal surplusPrincipal) {
	this.surplusPrincipal = surplusPrincipal;
    }

    public BigDecimal getManageMoney() {
	return manageMoney;
    }

    public void setManageMoney(BigDecimal manageMoney) {
	this.manageMoney = manageMoney;
    }

    public BigDecimal getServiceMoney() {
	return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
	this.serviceMoney = serviceMoney;
    }

    public BigDecimal getRate() {
	return rate;
    }

    public void setRate(BigDecimal rate) {
	this.rate = rate;
    }

    public BigDecimal getYqglf() {
	return yqglf;
    }

    public void setYqglf(BigDecimal yqglf) {
	this.yqglf = yqglf;
    }

    public BigDecimal getYqwyj() {
	return yqwyj;
    }

    public void setYqwyj(BigDecimal yqwyj) {
	this.yqwyj = yqwyj;
    }

    public BigDecimal getYqfx() {
	return yqfx;
    }

    public void setYqfx(BigDecimal yqfx) {
	this.yqfx = yqfx;
    }

    public String getIsYQ() {
	return isYQ;
    }

    public void setIsYQ(String isYQ) {
	this.isYQ = isYQ;
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
	return "PAY_RepayPlan [OID=" + OID + ", businessOID=" + businessOID + ", memberOID=" + memberOID
		+ ", repayDate=" + repayDate + ", money=" + money + ", state=" + state + ", principalMoney="
		+ principalMoney + ", interestMoney=" + interestMoney + ", totalPrincipalMoney=" + totalPrincipalMoney
		+ ", totalInterestMoney=" + totalInterestMoney + ", surplusPrincipal=" + surplusPrincipal
		+ ", manageMoney=" + manageMoney + ", serviceMoney=" + serviceMoney + ", rate=" + rate + ", yqglf="
		+ yqglf + ", yqwyj=" + yqwyj + ", yqfx=" + yqfx + ", isYQ=" + isYQ + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
