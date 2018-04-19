package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileMyLendDetialWCHHT extends BasePojo {

    /**
     * HHT完成列表
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 产品信息OID
     */
    private String productInfoOID;
    /**
     * 
     */
    private String creditType;
    /**
     * OID
     */
    private String OID;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * businessOID
     */
    private String bussinessOID;

    /**
     * 
     */
    private Date dqsj;
    /**
     * 
     */
    private String dqsjTime;

    /**
     * 总期数
     */
    private Integer tatalTerm;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;
    /**
     * 还款状态
     */
    private String hkzt;

    /**
     * 出借时间
     */
    private Date cjsjTime;
    /**
     * 出借时间
     */
    private String cjsj;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 
     */
    private BigDecimal sxAmount;
    /**
     * 持有资金
     */
    private BigDecimal holdingAmount;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 年化利率
     */
    private String nhll;
    /**
     * 结束时间
     */
    private Date Timejssj;
    /**
     * 结束时间
     */
    private String jssj;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 标OID
     */
    private String biOID;
    /**
     * 总期数
     */
    private Integer zqs;
    /**
     * decribelOID
     */
    private String DOID;
    /**
     * 加息
     */
    private BigDecimal JXother;

    /**
     * 
     */
    private String fullscale;
    /**
     * 收益期数
     */
    private String syqs;

    /**
     * 加息时间
     */
    private Date jxsj;
    /**
     * 加息时间
     */
    private String jxsjTime;

    /**
     * 下个还款日
     */
    private Date xghkr;
    /**
     * 下个还款日
     */
    private String xghkrTime;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 
     */
    private Integer judeg;

    /** 特权收益 */
    /**
     * 特权收益
     */
    private BigDecimal tqsy;
    /**
     * 特权名称
     */
    private String privilegeName;
    /**
     * 特权期数
     */
    private Integer privilegeTerm;
    /**
     * 特权利率
     */
    private BigDecimal privilegeRange;
    /**
     * 特权类型
     */
    private String privilegeType;
    /**
     * 最低呢金额
     */
    private BigDecimal lowAmount;
    /**
     * 规则
     */
    private Integer rule;
    /**
     * 规则介绍
     */
    private String ruleIntroduce;

    public BigDecimal getJXother() {
	return JXother;
    }

    public void setJXother(BigDecimal jXother) {
	JXother = jXother;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public String getProductInfoOID() {
	return productInfoOID;
    }

    public void setProductInfoOID(String productInfoOID) {
	this.productInfoOID = productInfoOID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getBussinessOID() {
	return bussinessOID;
    }

    public void setBussinessOID(String bussinessOID) {
	this.bussinessOID = bussinessOID;
    }

    public BigDecimal getSxAmount() {
	return sxAmount;
    }

    public void setSxAmount(BigDecimal sxAmount) {
	this.sxAmount = sxAmount;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Date getTimejssj() {
	return Timejssj;
    }

    public void setTimejssj(Date timejssj) {
	Timejssj = timejssj;
    }

    public String getJssj() {
	return jssj;
    }

    public void setJssj(String jssj) {
	this.jssj = jssj;
    }

    public Integer getTatalTerm() {
	return tatalTerm;
    }

    public void setTatalTerm(Integer tatalTerm) {
	this.tatalTerm = tatalTerm;
    }

    public BigDecimal getTqsy() {
	return tqsy;
    }

    public void setTqsy(BigDecimal tqsy) {
	this.tqsy = tqsy;
    }

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public Integer getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(Integer privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public BigDecimal getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(BigDecimal privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public String getPrivilegeType() {
	return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
	this.privilegeType = privilegeType;
    }

    public BigDecimal getLowAmount() {
	return lowAmount;
    }

    public void setLowAmount(BigDecimal lowAmount) {
	this.lowAmount = lowAmount;
    }

    public Integer getRule() {
	return rule;
    }

    public void setRule(Integer rule) {
	this.rule = rule;
    }

    public String getRuleIntroduce() {
	return ruleIntroduce;
    }

    public void setRuleIntroduce(String ruleIntroduce) {
	this.ruleIntroduce = ruleIntroduce;
    }

    public Integer getJudeg() {
	return judeg;
    }

    public void setJudeg(Integer judeg) {
	this.judeg = judeg;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getCjsj() {
	return cjsj;
    }

    public void setCjsj(String cjsj) {
	this.cjsj = cjsj;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public String getFullscale() {
	return fullscale;
    }

    public void setFullscale(String fullscale) {
	this.fullscale = fullscale;
    }

    public String getSyqs() {
	return syqs;
    }

    public void setSyqs(String syqs) {
	this.syqs = syqs;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public Date getJxsj() {
	return jxsj;
    }

    public void setJxsj(Date jxsj) {
	this.jxsj = jxsj;
    }

    public String getJxsjTime() {
	return jxsjTime;
    }

    public void setJxsjTime(String jxsjTime) {
	this.jxsjTime = jxsjTime;
    }

    public Date getDqsj() {
	return dqsj;
    }

    public void setDqsj(Date dqsj) {
	this.dqsj = dqsj;
    }

    public String getDqsjTime() {
	return dqsjTime;
    }

    public void setDqsjTime(String dqsjTime) {
	this.dqsjTime = dqsjTime;
    }

    public Date getXghkr() {
	return xghkr;
    }

    public void setXghkr(Date xghkr) {
	this.xghkr = xghkr;
    }

    public String getXghkrTime() {
	return xghkrTime;
    }

    public void setXghkrTime(String xghkrTime) {
	this.xghkrTime = xghkrTime;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendDetialWCHHT [CashPoolOID=" + CashPoolOID + ", productInfoOID=" + productInfoOID
		+ ", creditType=" + creditType + ", OID=" + OID + ", tqOID=" + tqOID + ", bussinessOID=" + bussinessOID
		+ ", dqsj=" + dqsj + ", dqsjTime=" + dqsjTime + ", tatalTerm=" + tatalTerm + ", ysbx=" + ysbx
		+ ", hkzt=" + hkzt + ", cjsjTime=" + cjsjTime + ", cjsj=" + cjsj + ", xmmc=" + xmmc + ", sxAmount="
		+ sxAmount + ", holdingAmount=" + holdingAmount + ", cjje=" + cjje + ", nhll=" + nhll + ", Timejssj="
		+ Timejssj + ", jssj=" + jssj + ", productType=" + productType + ", biOID=" + biOID + ", zqs=" + zqs
		+ ", DOID=" + DOID + ", JXother=" + JXother + ", fullscale=" + fullscale + ", syqs=" + syqs + ", jxsj="
		+ jxsj + ", jxsjTime=" + jxsjTime + ", xghkr=" + xghkr + ", xghkrTime=" + xghkrTime + ", productStatus="
		+ productStatus + ", judeg=" + judeg + ", tqsy=" + tqsy + ", privilegeName=" + privilegeName
		+ ", privilegeTerm=" + privilegeTerm + ", privilegeRange=" + privilegeRange + ", privilegeType="
		+ privilegeType + ", lowAmount=" + lowAmount + ", rule=" + rule + ", ruleIntroduce=" + ruleIntroduce
		+ "]";
    }

}
