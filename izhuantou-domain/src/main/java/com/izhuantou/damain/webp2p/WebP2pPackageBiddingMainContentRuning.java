package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 在投团标产品内容表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "webp2p_packagebiddingmaincontentruning")
public class WebP2pPackageBiddingMainContentRuning extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1499474509111645143L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 标的名称
     */
    @Column(name = "biddingName")
    private String biddingName;

    /**
     * 借款到期时间
     */
    @Column(name = "endDay")
    private Date endDay;

    /**
     * 平台用户编号（借款人）/债转财务用户OID
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 借款金额
     */
    @Column(name = "applyAmount")
    private BigDecimal applyAmount;
    /**
     * 借款时间(满标放款时间)
     */
    @Column(name = "loanDay")
    private String loanDay;
    /**
     * 标的金额（实际投标金额）
     */
    @Column(name = "biddingAmount")
    private BigDecimal biddingAmount;
    /**
     * 借款产品类型ID
     */
    @Column(name = "loanProductRateInfoID")
    private String loanProductRateInfoID;
    /**
     * 已投金额
     */
    @Column(name = "holdingAmount")
    private BigDecimal holdingAmount;
    /**
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
     */
    @Column(name = "productType")
    private Integer productType;
    /**
     * 标的状态（0：未发布；1：在投；2：还款中；3：已完成；4：已到期；5：逾期；6：满标未发）
     */
    @Column(name = "productStatus")
    private String productStatus;
    /**
     * 还款方式（文字）
     */
    @Column(name = "repaymentType")
    private String repaymentType;
    /**
     * 标的类型（0：普通标的；1：满标标的）
     */
    @Column(name = "biddingType")
    private String biddingType;

    /**
     * 借款编号（借款申请审批后形成编号）
     */
    @Column(name = "loanNumber")
    private String loanNumber;
    /**
     * 债转借款编号（借款申请审批后形成编号）
     */
    @Column(name = "debtLoanNumber")
    private String debtLoanNumber;
    /**
     * 已还款期数
     */
    private Integer yhqs;
    /**
     * 是否推荐（0,不推荐，1,推荐）
     */
    @Column(name = "isRecommend")
    private String isRecommend = "0";
    /**
     * 是否财务参与投标(0:未参与；1:参与)
     */
    @Column(name = "isCWTB")
    private String isCWTB = "0";

    @Column(name = "debitCreditOID")
    private String debitCreditOID;
    /**
     * normal为普通标的financetransfer财务债转标的
     */
    @Column(name = "financetransfertype")
    private String financetransfertype = "normal";
    /**
     * 已还款金额
     */
    private BigDecimal yhje;

    /**
     * 还款状态（0：正常还款；1：还款逾期；2：逾期超期）
     */
    private Integer hkzt;
    /**
     * 逾期天数
     */
    private Integer yqts;
    /**
     * 债转OID
     */
    @Column(name = "debtTransferOID")
    private String debtTransferOID;
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

    public Date getEndDay() {
	return endDay;
    }

    public void setEndDay(Date endDay) {
	this.endDay = endDay;
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

    public String getLoanDay() {
	return loanDay;
    }

    public void setLoanDay(String loanDay) {
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

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
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

    public String getDebtLoanNumber() {
	return debtLoanNumber;
    }

    public void setDebtLoanNumber(String debtLoanNumber) {
	this.debtLoanNumber = debtLoanNumber;
    }

    public Integer getYhqs() {
	return yhqs;
    }

    public void setYhqs(Integer yhqs) {
	this.yhqs = yhqs;
    }

    public String getIsRecommend() {
	return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
	this.isRecommend = isRecommend;
    }

    public String getIsCWTB() {
	return isCWTB;
    }

    public void setIsCWTB(String isCWTB) {
	this.isCWTB = isCWTB;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getFinancetransfertype() {
	return financetransfertype;
    }

    public void setFinancetransfertype(String financetransfertype) {
	this.financetransfertype = financetransfertype;
    }

    public BigDecimal getYhje() {
	return yhje;
    }

    public void setYhje(BigDecimal yhje) {
	this.yhje = yhje;
    }

    public Integer getHkzt() {
	return hkzt;
    }

    public void setHkzt(Integer hkzt) {
	this.hkzt = hkzt;
    }

    public Integer getYqts() {
	return yqts;
    }

    public void setYqts(Integer yqts) {
	this.yqts = yqts;
    }

    public String getDebtTransferOID() {
	return debtTransferOID;
    }

    public void setDebtTransferOID(String debtTransferOID) {
	this.debtTransferOID = debtTransferOID;
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
	return "WEBP2P_PackageBiddingMainContentRuning [OID=" + OID + ", biddingName=" + biddingName + ", endDay="
		+ endDay + ", memberOID=" + memberOID + ", applyAmount=" + applyAmount + ", loanDay=" + loanDay
		+ ", biddingAmount=" + biddingAmount + ", loanProductRateInfoID=" + loanProductRateInfoID
		+ ", holdingAmount=" + holdingAmount + ", productType=" + productType + ", productStatus="
		+ productStatus + ", repaymentType=" + repaymentType + ", biddingType=" + biddingType + ", loanNumber="
		+ loanNumber + ", debtLoanNumber=" + debtLoanNumber + ", yhqs=" + yhqs + ", isRecommend=" + isRecommend
		+ ", isCWTB=" + isCWTB + ", debitCreditOID=" + debitCreditOID + ", financetransfertype="
		+ financetransfertype + ", yhje=" + yhje + ", hkzt=" + hkzt + ", yqts=" + yqts + ", debtTransferOID="
		+ debtTransferOID + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
		+ updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}