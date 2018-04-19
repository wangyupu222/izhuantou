package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.izhuantou.damain.BasePojo;

/**
 * 理财产品配置及费率表
 * 
 * @author yangbosen
 * @version 1.0
 */
public class WebP2pProductRateInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 6104872195449930958L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 产品Id（用户自定义编码）
     */
    @Column(name = "productID")
    private String productID;
    /**
     * 产品名称
     */
    @Column(name = "productName")
    private String productName;
    /**
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
     */
    @Column(name = "productType")
    private String productType;
    /**
     * 还款方式（文字）
     */
    @Column(name = "repaymentType")
    private String repaymentType;
    /**
     * 产品期限（月）
     */
    @Column(name = "productTerm")
    private double productTerm;

    /**
     * 借款期限（月）
     */
    @Column(name = "loanTerm")
    private double loanTerm;

    /**
     * 年利率
     */
    @Column(name = "yearRate")
    private BigDecimal yearRate;
    /**
     * 环环投提前赎回手续费（线下）
     */
    private BigDecimal hhtXXtqsh;
    /**
     * 环环投提前赎回手续费（线上）
     */
    private BigDecimal hhtXStqsh;

    /**
     * 点点投债权转让手续费
     */
    @Column(name = "ddtZQZR")
    private BigDecimal ddtZQZR;
    /**
     * 团标资金池优先策略
     */
    private String fullstrategy;

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

    public String getProductID() {
	return productID;
    }

    public void setProductID(String productID) {
	this.productID = productID;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public double getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(double productTerm) {
	this.productTerm = productTerm;
    }

    public double getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(double loanTerm) {
	this.loanTerm = loanTerm;
    }

    public BigDecimal getYearRate() {
	return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
	this.yearRate = yearRate;
    }

    public BigDecimal getHhtXXtqsh() {
	return hhtXXtqsh;
    }

    public void setHhtXXtqsh(BigDecimal hhtXXtqsh) {
	this.hhtXXtqsh = hhtXXtqsh;
    }

    public BigDecimal getHhtXStqsh() {
	return hhtXStqsh;
    }

    public void setHhtXStqsh(BigDecimal hhtXStqsh) {
	this.hhtXStqsh = hhtXStqsh;
    }

    public BigDecimal getDdtZQZR() {
	return ddtZQZR;
    }

    public void setDdtZQZR(BigDecimal ddtZQZR) {
	this.ddtZQZR = ddtZQZR;
    }

    public String getFullstrategy() {
	return fullstrategy;
    }

    public void setFullstrategy(String fullstrategy) {
	this.fullstrategy = fullstrategy;
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
	return "WEBP2P_ProductRateInfo [OID=" + OID + ", productID=" + productID + ", productName=" + productName
		+ ", productType=" + productType + ", repaymentType=" + repaymentType + ", productTerm=" + productTerm
		+ ", loanTerm=" + loanTerm + ", yearRate=" + yearRate + ", hhtXXtqsh=" + hhtXXtqsh + ", hhtXStqsh="
		+ hhtXStqsh + ", ddtZQZR=" + ddtZQZR + ", fullstrategy=" + fullstrategy + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
