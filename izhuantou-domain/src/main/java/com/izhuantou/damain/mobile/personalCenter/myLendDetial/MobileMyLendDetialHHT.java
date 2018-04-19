package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileMyLendDetialHHT extends BasePojo {

    /**
     * 个人中心我的出借环环投详情
     */
    private static final long serialVersionUID = 1L;

    private Date xtTime;
    /**
     * 产品信息OID
     */
    private String productInfoOID;
    /**
     * 
     */
    private String OID;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 已收本息
     */
    private BigDecimal ysbx;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * 产品状态
     */
    private String productStatus;
    /**
     * 
     */
    private BigDecimal sxAmount;
    /**
     * 持有资金
     */
    private BigDecimal holdingAmount;
    /**
     * 收益期数
     */
    private String syqs;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 
     */
    private String creditType;

    /**
     * 出借时间
     */
    private Date cjsjTime;
    /**
     * 出借时间YYYY-MM-DD
     */
    private String cjsj;

    /**
     * 下个还款日
     */
    private Date xghkr;
    /**
     * 下个还款日 YYYY-MM-DD
     */
    private String xghkrTime;

    /**
     * 
     */
    private Date dqsj;
    /**
     * 
     */
    private String dqsjTime;
    // jiaxishijian
    private String jxsj;

    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 年化利率
     */
    private String nhll;

    /**
     * 收益可投
     */
    private String sykt;

    /**
     * 还款状态
     */
    private String hkzt;

    /**
     * 特权名
     */
    private String privilegeName;
    /**
     * 特权利率
     */
    private String privilegeRange;
    /**
     * 特权期数
     */
    private Integer privilegeTerm;
    /**
     * 特权收益
     */
    private String tqsy;
    /**
     * 
     */
    private Integer judeg;

    /**
     * 规则参数
     */
    private String rule;
    /**
     * 规则介绍
     */
    private String ruleIntroduce;

    /**
     * 加息
     */
    private BigDecimal JXother;

    public BigDecimal getJXother() {
	return JXother;
    }

    public void setJXother(BigDecimal jXother) {
	JXother = jXother;
    }

    public String getRule() {
	return rule;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }

    public String getRuleIntroduce() {
	return ruleIntroduce;
    }

    public void setRuleIntroduce(String ruleIntroduce) {
	this.ruleIntroduce = ruleIntroduce;
    }

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public String getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(String privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public Integer getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(Integer privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public String getTqsy() {
	return tqsy;
    }

    public void setTqsy(String tqsy) {
	this.tqsy = tqsy;
    }

    public Integer getJudeg() {
	return judeg;
    }

    public void setJudeg(Integer judeg) {
	this.judeg = judeg;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getSykt() {
	return sykt;
    }

    public void setSykt(String sykt) {
	this.sykt = sykt;
    }

    public String getJxsj() {
	return jxsj;
    }

    public void setJxsj(String jxsj) {
	this.jxsj = jxsj;
    }

    public String getXghkrTime() {
	return xghkrTime;
    }

    public void setXghkrTime(String xghkrTime) {
	this.xghkrTime = xghkrTime;
    }

    public String getDqsjTime() {
	return dqsjTime;
    }

    public void setDqsjTime(String dqsjTime) {
	this.dqsjTime = dqsjTime;
    }

    public String getCjsj() {
	return cjsj;
    }

    public void setCjsj(String cjsj) {
	this.cjsj = cjsj;
    }

    public Date getXtTime() {
	return xtTime;
    }

    public void setXtTime(Date xtTime) {
	this.xtTime = xtTime;
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

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
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

    public String getSyqs() {
	return syqs;
    }

    public void setSyqs(String syqs) {
	this.syqs = syqs;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public Date getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(Date cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public Date getXghkr() {
	return xghkr;
    }

    public void setXghkr(Date xghkr) {
	this.xghkr = xghkr;
    }

    public Date getDqsj() {
	return dqsj;
    }

    public void setDqsj(Date dqsj) {
	this.dqsj = dqsj;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendDetialHHT [xtTime=" + xtTime + ", productInfoOID=" + productInfoOID + ", OID=" + OID
		+ ", tqOID=" + tqOID + ", businessOID=" + businessOID + ", CashPoolOID=" + CashPoolOID + ", ysbx="
		+ ysbx + ", zqs=" + zqs + ", productStatus=" + productStatus + ", sxAmount=" + sxAmount
		+ ", holdingAmount=" + holdingAmount + ", syqs=" + syqs + ", money=" + money + ", creditType="
		+ creditType + ", cjsjTime=" + cjsjTime + ", cjsj=" + cjsj + ", xghkr=" + xghkr + ", xghkrTime="
		+ xghkrTime + ", dqsj=" + dqsj + ", dqsjTime=" + dqsjTime + ", jxsj=" + jxsj + ", xmmc=" + xmmc
		+ ", cjje=" + cjje + ", nhll=" + nhll + ", sykt=" + sykt + ", hkzt=" + hkzt + ", privilegeName="
		+ privilegeName + ", privilegeRange=" + privilegeRange + ", privilegeTerm=" + privilegeTerm + ", tqsy="
		+ tqsy + ", judeg=" + judeg + ", rule=" + rule + ", ruleIntroduce=" + ruleIntroduce + ", JXother="
		+ JXother + "]";
    }

}
