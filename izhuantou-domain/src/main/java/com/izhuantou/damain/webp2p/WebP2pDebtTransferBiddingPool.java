package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 债转债权池（转转投）
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "webp2p_debttransferbiddingpool")
public class WebP2pDebtTransferBiddingPool extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -6889620667104152807L;
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
     * 是否财务参与投标(0:未参与；1:参与)
     */
    @Column(name = "isCWTB")
    private String isCWTB = "0";

    /**
     * 出借金额
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
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：散债转；5：团标债转）
     */
    @Column(name = "productType")
    private Integer productType;
    /**
     * 标的状态（0：未发布；1：在投；2：还款中；3：已到期；4：逾期；5：已结清；）
     */
    @Column(name = "productStatus")
    private String productStatus;
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
     * 债转时间
     */
    @Column(name = "transferTime")
    private String transferTime;
    /**
     * 剩余期限
     */
    private Integer yhqs;
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
     * 是否推荐（0,不推荐，1,推荐）
     */

    private String isRecommend = "0";
    /**
     * 借贷关系OID
     */
    @Column(name = "debitCreditOID")
    private String debitCreditOID;

    /**
     * 借款到期时间
     */
    @Column(name = "endDay")
    private Date endDay;
    /**
     * 债转借款编号（借款申请审批后形成编号）
     * 
     * @author Cannon
     */
    @Column(name = "debtLoanNumber")
    private String debtLoanNumber;

    /**
     * 设置定时 0：未设置 1：已设置
     */
    private String szds;
    /**
     * 定时时间
     */
    @Column(name = "dsTime")
    private Date dsTime;

    /**
     * 额外加息
     */
    @Column(name = "JXother")
    private BigDecimal JXother;
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

    public String getIsCWTB() {
	return isCWTB;
    }

    public void setIsCWTB(String isCWTB) {
	this.isCWTB = isCWTB;
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

    public String getTransferTime() {
	return transferTime;
    }

    public void setTransferTime(String transferTime) {
	this.transferTime = transferTime;
    }

    public Integer getYhqs() {
	return yhqs;
    }

    public void setYhqs(Integer yhqs) {
	this.yhqs = yhqs;
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

    public String getIsRecommend() {
	return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
	this.isRecommend = isRecommend;
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

    public String getDebtLoanNumber() {
	return debtLoanNumber;
    }

    public void setDebtLoanNumber(String debtLoanNumber) {
	this.debtLoanNumber = debtLoanNumber;
    }

    public String getSzds() {
	return szds;
    }

    public void setSzds(String szds) {
	this.szds = szds;
    }

    public Date getDsTime() {
	return dsTime;
    }

    public void setDsTime(Date dsTime) {
	this.dsTime = dsTime;
    }

    public BigDecimal getJXother() {
	return JXother;
    }

    public void setJXother(BigDecimal jXother) {
	JXother = jXother;
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

}
