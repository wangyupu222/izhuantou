package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投中间表实体
 * 
 * @author yangbosen
 *
 */
public class HHTMidTable extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;
    private String mainOID;
    private String contentOID;
    /*
     * private String describe0; private String NO; private String addUserOID;
     * private String uppUserOID;
     */
    private Integer valid;

    private String OID1;
    private String memberOID;
    private BigDecimal applyAmount;
    private Date loanDay;
    private BigDecimal biddingAmount;
    private String loanProductRateInfoID;
    private BigDecimal holdingAmount;
    private Integer productType;
    private Integer productStatus;
    private String repaymentType;
    private Integer biddingType;
    private String loanNumber;
    private Integer isRecommend;
    private Integer isCWTB;
    private String debitCreditOID;
    private String debtLoanNumber;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getMainOID() {
	return mainOID;
    }

    public void setMainOID(String mainOID) {
	this.mainOID = mainOID;
    }

    public String getContentOID() {
	return contentOID;
    }

    public void setContentOID(String contentOID) {
	this.contentOID = contentOID;
    }

    public String getOID1() {
	return OID1;
    }

    public void setOID1(String oID1) {
	OID1 = oID1;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public BigDecimal getApplyAmount() {
	return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
	this.applyAmount = applyAmount;
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

    public String getLoanProductRateInfoID() {
	return loanProductRateInfoID;
    }

    public void setLoanProductRateInfoID(String loanProductRateInfoID) {
	this.loanProductRateInfoID = loanProductRateInfoID;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public Integer getBiddingType() {
	return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
	this.biddingType = biddingType;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public Integer getIsRecommend() {
	return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
	this.isRecommend = isRecommend;
    }

    public Integer getIsCWTB() {
	return isCWTB;
    }

    public void setIsCWTB(Integer isCWTB) {
	this.isCWTB = isCWTB;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getDebtLoanNumber() {
	return debtLoanNumber;
    }

    public void setDebtLoanNumber(String debtLoanNumber) {
	this.debtLoanNumber = debtLoanNumber;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "HHTMidTable [OID=" + OID + ", mainOID=" + mainOID + ", contentOID=" + contentOID + ", valid=" + valid
		+ ", OID1=" + OID1 + ", memberOID=" + memberOID + ", applyAmount=" + applyAmount + ", loanDay="
		+ loanDay + ", biddingAmount=" + biddingAmount + ", loanProductRateInfoID=" + loanProductRateInfoID
		+ ", holdingAmount=" + holdingAmount + ", productType=" + productType + ", productStatus="
		+ productStatus + ", repaymentType=" + repaymentType + ", biddingType=" + biddingType + ", loanNumber="
		+ loanNumber + ", isRecommend=" + isRecommend + ", isCWTB=" + isCWTB + ", debitCreditOID="
		+ debitCreditOID + ", debtLoanNumber=" + debtLoanNumber + "]";
    }

}
