package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 借款产品配置表
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "webp2p_loanproductrateinfo")
public class WebP2pLoanProductRateInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -494929289102990060L;
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
     * 还款方式(等本等息,先息后本)
     */
    @Column(name = "repaymentType")
    private String repaymentType;
    /**
     * 期限（月）
     */
    private Integer term;
    /**
     * 本次还款日期
     */
    private String hksj;

    /**
     * 一次性手续费
     */
    @Column(name = "disposableRate")
    private BigDecimal disposableRate;
    /**
     * 提前还款违约金
     */
    @Column(name = "beforeRate")
    private BigDecimal beforeRate;
    /**
     * 逾期违约金（天）
     */
    @Column(name = "overdueRate")
    private BigDecimal overdueRate;
    /**
     * 逾期管理费（天）
     */
    @Column(name = "overdueExpenseRate")
    private BigDecimal overdueExpenseRate;
    /**
     * 年利率
     */
    @Column(name = "yearRate")
    private BigDecimal yearRate;
    /**
     * 年平台管理费率
     */
    @Column(name = "yearPlatformRate")
    private BigDecimal yearPlatformRate;
    /**
     * 年管理咨询费率
     */
    @Column(name = "yearAdviceRate")
    private BigDecimal yearAdviceRate;

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

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public Integer getTerm() {
	return term;
    }

    public void setTerm(Integer term) {
	this.term = term;
    }

    public String getHksj() {
	return hksj;
    }

    public void setHksj(String hksj) {
	this.hksj = hksj;
    }

    public BigDecimal getDisposableRate() {
	return disposableRate;
    }

    public void setDisposableRate(BigDecimal disposableRate) {
	this.disposableRate = disposableRate;
    }

    public BigDecimal getBeforeRate() {
	return beforeRate;
    }

    public void setBeforeRate(BigDecimal beforeRate) {
	this.beforeRate = beforeRate;
    }

    public BigDecimal getOverdueRate() {
	return overdueRate;
    }

    public void setOverdueRate(BigDecimal overdueRate) {
	this.overdueRate = overdueRate;
    }

    public BigDecimal getOverdueExpenseRate() {
	return overdueExpenseRate;
    }

    public void setOverdueExpenseRate(BigDecimal overdueExpenseRate) {
	this.overdueExpenseRate = overdueExpenseRate;
    }

    public BigDecimal getYearRate() {
	return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
	this.yearRate = yearRate;
    }

    public BigDecimal getYearPlatformRate() {
	return yearPlatformRate;
    }

    public void setYearPlatformRate(BigDecimal yearPlatformRate) {
	this.yearPlatformRate = yearPlatformRate;
    }

    public BigDecimal getYearAdviceRate() {
	return yearAdviceRate;
    }

    public void setYearAdviceRate(BigDecimal yearAdviceRate) {
	this.yearAdviceRate = yearAdviceRate;
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
	return "WEBP2P_LoanProductRateInfo [OID=" + OID + ", productID=" + productID + ", productName=" + productName
		+ ", repaymentType=" + repaymentType + ", term=" + term + ", hksj=" + hksj + ", disposableRate="
		+ disposableRate + ", beforeRate=" + beforeRate + ", overdueRate=" + overdueRate
		+ ", overdueExpenseRate=" + overdueExpenseRate + ", yearRate=" + yearRate + ", yearPlatformRate="
		+ yearPlatformRate + ", yearAdviceRate=" + yearAdviceRate + ", describe0=" + describe0 + ", NO=" + NO
		+ ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}