package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 在投中团标产品表
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "webp2p_packagebiddingmainruning")
public class WebP2pPackageBiddingMainRuning extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -8297906921490815919L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 标的名称
     */
    @Column(name = "biddingName")
    private String biddingName;

    /**
     * 团标名称
     */
    @Column(name = "packageName")
    private String packageName;
    /**
     * 理财产品类型ID
     */
    @Column(name = "productRateInfoID")
    private String productRateInfoID;
    /**
     * 
     */
    @Column(name = "productStatus")
    private String productStatus;
    /**
     * 已投金额
     */
    @Column(name = "holdingAmount")
    private BigDecimal holdingAmount;
    /**
     * 站岗资金（未匹配标的的金额）
     */
    @Column(name = "occupyAmount")
    private BigDecimal occupyAmount;
    /**
     * 起投金额
     */
    @Column(name = "startBidAmount")
    private BigDecimal startBidAmount;
    /**
     * 是否推荐（0,不推荐，1,推荐）
     */
    @Column(name = "isRecommend")
    private String isRecommend ;
    /**
     * 上限金额
     */
    @Column(name = "sxAmount")
    private BigDecimal sxAmount;

    @Column(name = "debitCreditOID")
    private String debitCreditOID;
    /*
     * "normal"为新标，"financetransfer"为财务标的
     */
    @Column(name = "financetransfertype")
    private String financetransfertype;

    @Column(name = "appisRecommend")
    private String appisRecommend ;

    /**
     * 设置定时 0:不设置 1：设置
     */
    private String szds;

    /**
     * 定时时间
     */
    @Column(name = "dsTime")
    private Date dsTime;
    /**
     * 产品单笔限额
     */
    @Column(name = "dbxeAmount")
    private BigDecimal dbxeAmount;
    /**
     * 单笔限额开关(0:不限制 1:限制)
     */
    @Column(name = "dbxeFlag")
    private String dbxeFlag;
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

    public String getPackageName() {
	return packageName;
    }

    public void setPackageName(String packageName) {
	this.packageName = packageName;
    }

    public String getProductRateInfoID() {
	return productRateInfoID;
    }

    public void setProductRateInfoID(String productRateInfoID) {
	this.productRateInfoID = productRateInfoID;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public BigDecimal getOccupyAmount() {
	return occupyAmount;
    }

    public void setOccupyAmount(BigDecimal occupyAmount) {
	this.occupyAmount = occupyAmount;
    }

    public BigDecimal getStartBidAmount() {
	return startBidAmount;
    }

    public void setStartBidAmount(BigDecimal startBidAmount) {
	this.startBidAmount = startBidAmount;
    }

    public String getIsRecommend() {
	return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
	this.isRecommend = isRecommend;
    }

    public BigDecimal getSxAmount() {
	return sxAmount;
    }

    public void setSxAmount(BigDecimal sxAmount) {
	this.sxAmount = sxAmount;
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

    public String getAppisRecommend() {
	return appisRecommend;
    }

    public void setAppisRecommend(String appisRecommend) {
	this.appisRecommend = appisRecommend;
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

    public BigDecimal getDbxeAmount() {
	return dbxeAmount;
    }

    public void setDbxeAmount(BigDecimal dbxeAmount) {
	this.dbxeAmount = dbxeAmount;
    }

    public String getDbxeFlag() {
	return dbxeFlag;
    }

    public void setDbxeFlag(String dbxeFlag) {
	this.dbxeFlag = dbxeFlag;
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

    @Override
    public String toString() {
	return "WEBP2P_PackageBiddingMainRuning [OID=" + OID + ", biddingName=" + biddingName + ", packageName="
		+ packageName + ", productRateInfoID=" + productRateInfoID + ", productStatus=" + productStatus
		+ ", holdingAmount=" + holdingAmount + ", occupyAmount=" + occupyAmount + ", startBidAmount="
		+ startBidAmount + ", isRecommend=" + isRecommend + ", sxAmount=" + sxAmount + ", debitCreditOID="
		+ debitCreditOID + ", financetransfertype=" + financetransfertype + ", appisRecommend=" + appisRecommend
		+ ", szds=" + szds + ", dsTime=" + dsTime + ", dbxeAmount=" + dbxeAmount + ", dbxeFlag=" + dbxeFlag
		+ ", JXother=" + JXother + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
		+ updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
