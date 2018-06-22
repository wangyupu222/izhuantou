package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 财务自动债转表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "webp2p_financedebttransfer")
public class Webp2pFinanceDebtTransfer extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4283543482838008160L;

	/**
	 * OID
	 */
	@Id
	@Column(name = "OID")
	private String OID;

	/**
	 * 标的名称
	 */
	@Column(name = "biddingName")
	private String biddingName;

	/**
	 * 起投金额
	 */
	@Column(name = "startBidAmount")
	private BigDecimal startBidAmount;
	/**
	 * 平台用户编号（借款人）
	 */
	@Column(name = "memberOID")
	private String memberOID;
	/**
	 * 借款金额
	 */
	@Column(name = "loanAmount")
	private BigDecimal loanAmount;
	/**
	 * 借款时间(满标放款时间)
	 */
	@Column(name = "loanDay")
	private Date loanDay;
	/**
	 * 标的金额（实际投标金额）
	 */
	@Column(name = "biddingAmount")
	private BigDecimal biddingAmount;
	/**
	 * 已投金额
	 */
	@Column(name = "holdingAmount")
	private BigDecimal holdingAmount;
	/**
	 * 借款产品类型ID
	 */
	@Column(name = "loanProductRateInfoID")
	private String loanProductRateInfoID;
	/**
	 * 理财产品类型ID
	 */
	@Column(name = "productRateInfoID")
	private String productRateInfoID;
	/**
	 * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
	 */
	@Column(name = "productType")
	private Integer productType;
	/**
	 * 标的状态（0：未发布；1：在投；2：还款中；3：已到期；4：逾期；）
	 */
	@Column(name = "productStatus")
	private String productStatus;
	/**
	 * 标的类型（0：普通标的；1：满标标的；2：保理标的）
	 */
	@Column(name = "biddingType")
	private String biddingType;
	/**
	 * 借款编号（借款申请审批后形成编号）
	 */
	@Column(name = "loanNumber")
	private String loanNumber;
	/**
	 * 投资编号
	 */
	@Column(name = "debitCreditOID")
	private String debitCreditOID;
	/**
	 * 借款到期时间
	 */
	@Column(name = "endDay")
	private Date endDay;

	/**
	 * 借贷关系状态（0：未转；1：已转）
	 */
	@Column(name = "debtTransferStatus")
	private String debtTransferStatus;
	/**
	 * 债转借款编号（借款申请审批后形成编号）
	 * 
	 * @author Cannon
	 */
	@Column(name = "debtLoanNumber")
	private String debtLoanNumber;

	/**
	 * 本金
	 */
	@Column(name = "principalMoney")
	private BigDecimal principalMoney;
	/**
	 * 垫付利息
	 */
	@Column(name = "advanceInterest")
	private BigDecimal advanceInterest;

	/**
	 * 债转OID
	 */
	@Column(name = "transferOID")
	private String transferOID;

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

	public String getBiddingName() {
		return biddingName;
	}

	public void setBiddingName(String biddingName) {
		this.biddingName = biddingName;
	}

	public BigDecimal getStartBidAmount() {
		return startBidAmount;
	}

	public void setStartBidAmount(BigDecimal startBidAmount) {
		this.startBidAmount = startBidAmount;
	}

	public String getMemberOID() {
		return memberOID;
	}

	public void setMemberOID(String memberOID) {
		this.memberOID = memberOID;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(Date loanDay) {
		this.loanDay = loanDay;
	}

	public BigDecimal getBiddingAmount() {
		return biddingAmount;
	}

	public void setBiddingAmount(BigDecimal biddingAmount) {
		this.biddingAmount = biddingAmount;
	}

	public BigDecimal getHoldingAmount() {
		return holdingAmount;
	}

	public void setHoldingAmount(BigDecimal holdingAmount) {
		this.holdingAmount = holdingAmount;
	}

	public String getLoanProductRateInfoID() {
		return loanProductRateInfoID;
	}

	public void setLoanProductRateInfoID(String loanProductRateInfoID) {
		this.loanProductRateInfoID = loanProductRateInfoID;
	}

	public String getProductRateInfoID() {
		return productRateInfoID;
	}

	public void setProductRateInfoID(String productRateInfoID) {
		this.productRateInfoID = productRateInfoID;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getBiddingType() {
		return biddingType;
	}

	public void setBiddingType(String biddingType) {
		this.biddingType = biddingType;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getDebitCreditOID() {
		return debitCreditOID;
	}

	public void setDebitCreditOID(String debitCreditOID) {
		this.debitCreditOID = debitCreditOID;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getDebtTransferStatus() {
		return debtTransferStatus;
	}

	public void setDebtTransferStatus(String debtTransferStatus) {
		this.debtTransferStatus = debtTransferStatus;
	}

	public String getDebtLoanNumber() {
		return debtLoanNumber;
	}

	public void setDebtLoanNumber(String debtLoanNumber) {
		this.debtLoanNumber = debtLoanNumber;
	}

	public BigDecimal getPrincipalMoney() {
		return principalMoney;
	}

	public void setPrincipalMoney(BigDecimal principalMoney) {
		this.principalMoney = principalMoney;
	}

	public BigDecimal getAdvanceInterest() {
		return advanceInterest;
	}

	public void setAdvanceInterest(BigDecimal advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	public String getTransferOID() {
		return transferOID;
	}

	public void setTransferOID(String transferOID) {
		this.transferOID = transferOID;
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
		return "Webp2pFinanceDebtTransfer [OID=" + OID + ", biddingName=" + biddingName + ", startBidAmount="
				+ startBidAmount + ", memberOID=" + memberOID + ", loanAmount=" + loanAmount + ", loanDay=" + loanDay
				+ ", biddingAmount=" + biddingAmount + ", holdingAmount=" + holdingAmount + ", loanProductRateInfoID="
				+ loanProductRateInfoID + ", productRateInfoID=" + productRateInfoID + ", productType=" + productType
				+ ", productStatus=" + productStatus + ", biddingType=" + biddingType + ", loanNumber=" + loanNumber
				+ ", debitCreditOID=" + debitCreditOID + ", endDay=" + endDay + ", debtTransferStatus="
				+ debtTransferStatus + ", debtLoanNumber=" + debtLoanNumber + ", principalMoney=" + principalMoney
				+ ", advanceInterest=" + advanceInterest + ", transferOID=" + transferOID + ", describe0=" + describe0
				+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
				+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
				+ ", version=" + version + "]";
	}

}
