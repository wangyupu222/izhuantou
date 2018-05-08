package com.izhuantou.damain.vo.bidding;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 投标所需参数
 * 
 * @author dear
 * @version 1.0
 */
public class BiddingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8238690759478637420L;
	/**
	 * 用户OID
	 */
	private String memberOID;
	/**
	 * 标的OID
	 */
	private String biddingOID;
	/**
	 * 投标钱数
	 */
	private BigDecimal amount;
	/**
	 * 特权OID
	 */
	private String tqOID;
	/**
	 * 来源
	 */
	private String laiyuan;

	private String mappingOID;

	/**
	 * 年利率
	 */
	private BigDecimal creditRate;
	/**
	 * OPI：一次性还本付息 BIAP：先息后本 EPEI：等本等息
	 */
	private String creditType;
	/**
	 * 特权金额
	 */
	private BigDecimal privilegePrincipal;
	/**
	 * 特权利率
	 */
	private BigDecimal privilegeInterest;
	/**
	 * 借贷关系OID
	 */
	private String debitCreditOID;
	/**
	 * 出借人OID
	 */
	private String outMemberOID;
	/**
	 * 债转日期
	 */
	private Date transferReturnDate;
	/**
	 * 开始时间
	 */
	private Date beginDate;

	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 
	 */
	private String isTZorCW;
	/**
	 * 
	 */
	private String tzType;

	/**
	 * 
	 */
	private BigDecimal interest;
	/**
	 * "day" ,"month"
	 */
	private String returnCycle;
	/**
	 * 返回期数
	 */
	private Integer returnNumber;
	/**
	 * 借款期数
	 */
	private Integer term;

	public Integer getReturnNumber() {
		return returnNumber;
	}

	public void setReturnNumber(Integer returnNumber) {
		this.returnNumber = returnNumber;
	}

	public String getReturnCycle() {
		return returnCycle;
	}

	public void setReturnCycle(String returnCycle) {
		this.returnCycle = returnCycle;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMemberOID() {
		return memberOID;
	}

	public void setMemberOID(String memberOID) {
		this.memberOID = memberOID;
	}

	public String getBiddingOID() {
		return biddingOID;
	}

	public void setBiddingOID(String biddingOID) {
		this.biddingOID = biddingOID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTqOID() {
		return tqOID;
	}

	public void setTqOID(String tqOID) {
		this.tqOID = tqOID;
	}

	public String getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}

	public String getMappingOID() {
		return mappingOID;
	}

	public void setMappingOID(String mappingOID) {
		this.mappingOID = mappingOID;
	}

	public BigDecimal getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(BigDecimal creditRate) {
		this.creditRate = creditRate;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getPrivilegePrincipal() {
		return privilegePrincipal;
	}

	public void setPrivilegePrincipal(BigDecimal privilegePrincipal) {
		this.privilegePrincipal = privilegePrincipal;
	}

	public BigDecimal getPrivilegeInterest() {
		return privilegeInterest;
	}

	public void setPrivilegeInterest(BigDecimal privilegeInterest) {
		this.privilegeInterest = privilegeInterest;
	}

	public String getDebitCreditOID() {
		return debitCreditOID;
	}

	public void setDebitCreditOID(String debitCreditOID) {
		this.debitCreditOID = debitCreditOID;
	}

	public String getOutMemberOID() {
		return outMemberOID;
	}

	public void setOutMemberOID(String outMemberOID) {
		this.outMemberOID = outMemberOID;
	}

	public Date getTransferReturnDate() {
		return transferReturnDate;
	}

	public void setTransferReturnDate(Date transferReturnDate) {
		this.transferReturnDate = transferReturnDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getIsTZorCW() {
		return isTZorCW;
	}

	public void setIsTZorCW(String isTZorCW) {
		this.isTZorCW = isTZorCW;
	}

	public String getTzType() {
		return tzType;
	}

	public void setTzType(String tzType) {
		this.tzType = tzType;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "BiddingDTO [memberOID=" + memberOID + ", biddingOID=" + biddingOID + ", amount=" + amount + ", tqOID="
				+ tqOID + ", laiyuan=" + laiyuan + ", mappingOID=" + mappingOID + ", creditRate=" + creditRate
				+ ", creditType=" + creditType + ", privilegePrincipal=" + privilegePrincipal + ", privilegeInterest="
				+ privilegeInterest + ", debitCreditOID=" + debitCreditOID + ", outMemberOID=" + outMemberOID
				+ ", transferReturnDate=" + transferReturnDate + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", isTZorCW=" + isTZorCW + ", tzType=" + tzType + ", interest=" + interest + ", returnCycle="
				+ returnCycle + ", returnNumber=" + returnNumber + ", term=" + term + "]";
	}

}
