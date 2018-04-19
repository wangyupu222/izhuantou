package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 收益返回计划（出借人）
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_returnplan")
public class PayReturnPlan extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -8402527814310471981L;
    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 业务OID
     */
    @Column(name = "businessOID")
    private String businessOID;
    /**
     * 资金池OID
     */
    @Column(name = "cashPoolOID")
    private String cashPoolOID;
    /**
     * 还款OID
     */
    @Column(name = "repayOID")
    private String repayOID;
    /**
     * 借贷OID
     */
    @Column(name = "debitCreditOID")
    private String debitCreditOID;

    /**
     * 入会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 出会员OID
     */
    @Column(name = "outMemberOID")
    private String outMemberOID;

    /**
     * 返还日期
     */
    @Column(name = "returnDate")
    private Date returnDate;

    /**
     * 整体金额
     */
    private BigDecimal money;

    /**
     * 状态
     */
    private String state;

    /**
     * 本金
     */
    @Column(name = "principalMoney")
    private BigDecimal principalMoney;
    /**
     * 利息
     */
    @Column(name = "interestMoney")
    private BigDecimal interestMoney;
    /**
     * 返还类型
     */
    private String type;

    /**
     * 共还本金
     */
    @Column(name = "totalPrincipalMoney")
    private BigDecimal totalPrincipalMoney;
    /**
     * 共还利息
     */
    @Column(name = "totalInterestMoney")
    private BigDecimal totalInterestMoney;

    /**
     * 返还利率
     */
    private BigDecimal rate;

    /**
     * 次数
     */
    private Integer num;

    /**
     * 共多少次
     */
    private Integer sum;

    /**
     * 逾期罚息
     */
    private BigDecimal yqfx;

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

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getCashPoolOID() {
	return cashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	this.cashPoolOID = cashPoolOID;
    }

    public String getRepayOID() {
	return repayOID;
    }

    public void setRepayOID(String repayOID) {
	this.repayOID = repayOID;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getOutMemberOID() {
	return outMemberOID;
    }

    public void setOutMemberOID(String outMemberOID) {
	this.outMemberOID = outMemberOID;
    }

    public Date getReturnDate() {
	return returnDate;
    }

    public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
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

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
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

    public BigDecimal getRate() {
	return rate;
    }

    public void setRate(BigDecimal rate) {
	this.rate = rate;
    }

    public Integer getNum() {
	return num;
    }

    public void setNum(Integer num) {
	this.num = num;
    }

    public Integer getSum() {
	return sum;
    }

    public void setSum(Integer sum) {
	this.sum = sum;
    }

    public BigDecimal getYqfx() {
	return yqfx;
    }

    public void setYqfx(BigDecimal yqfx) {
	this.yqfx = yqfx;
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
	return "PayReturnPlan [OID=" + OID + ", businessOID=" + businessOID + ", cashPoolOID=" + cashPoolOID
		+ ", repayOID=" + repayOID + ", debitCreditOID=" + debitCreditOID + ", memberOID=" + memberOID
		+ ", outMemberOID=" + outMemberOID + ", returnDate=" + returnDate + ", money=" + money + ", state="
		+ state + ", principalMoney=" + principalMoney + ", interestMoney=" + interestMoney + ", type=" + type
		+ ", totalPrincipalMoney=" + totalPrincipalMoney + ", totalInterestMoney=" + totalInterestMoney
		+ ", rate=" + rate + ", num=" + num + ", sum=" + sum + ", yqfx=" + yqfx + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", updDateTime=" + updDateTime + ", addDateTime=" + addDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
