package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询个人中心我的出借
 * 
 * @author Administrator
 *
 */
public class MylendResultDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3742528096773495899L;

    /**
     * 在投中团标产品表OID
     */
    private String OID;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;

    /**
     * 产品期限（月）
     */
    private Integer zqs;

    /**
     * 标的的状态
     */
    private String productStatus;

    /**
     * 出借开始时间
     */
    private String cjsjTime;
    /**
     * 下个还款日
     */
    private String xghkr;

    /**
     * 到期时间
     */
    private String dqsj;

    /**
     * 团标名称
     */
    private String xmmc;

    /**
     * 出借金额（本金）
     */
    private BigDecimal cjje;
    /**
     * 年化利率
     */
    private BigDecimal nhll;

    /**
     * 回款状态
     */
    private String hkzt;
    /**
     * 特权名字
     */
    private String privilegeName;

    /**
     * 特权域(红包数额/加息百分位)
     */
    private BigDecimal privilegeRange;
    /**
     * 特权期限
     */
    private Integer privilegeTerm;

    /**
     * 特权收益
     */
    private BigDecimal tqsy;

    /**
     * 
     */
    private Integer judeg;

    /**
     * // 额外加息利率
     */
    private BigDecimal JXother;

    /**
     * 资金池特权OID
     */
    private String tqOID;

    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 结算时间
     */
    private String Timejssj;

    /**
     * 计息时间
     */
    private String jxsj;

    /**
     * 一个协议查询要用到的
     */
    private Integer ifzz;

    /**
     * 
     */
    private String DOID;

    /**
     * 
     */
    private boolean fullscale;

    /**
     * debitCreditOID
     */
    private String cond;

    /**
     * 查看协议的一个使用
     */
    private String charge;
    /**
     * 贷款类型
     */
    private String creditType;

    /**
     * 状态
     */
    private Integer productType;

    /**
     * 特权类型(0:红包；1:加息)
     */
    private String privilegeType;

    /**
     * 最低额度
     */
    private BigDecimal lowAmount;

    /**
     * 限制说明
     */
    private String ruleIntroduce;

    /**
     * 限制
     */
    private Integer rule;

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

    public BigDecimal getLowAmount() {
	return lowAmount;
    }

    public void setLowAmount(BigDecimal lowAmount) {
	this.lowAmount = lowAmount;
    }

    public String getPrivilegeType() {
	return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
	this.privilegeType = privilegeType;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public String getCharge() {
	return charge;
    }

    public void setCharge(String charge) {
	this.charge = charge;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public boolean isFullscale() {
	return fullscale;
    }

    public void setFullscale(boolean fullscale) {
	this.fullscale = fullscale;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public Integer getIfzz() {
	return ifzz;
    }

    public void setIfzz(Integer ifzz) {
	this.ifzz = ifzz;
    }

    public String getJxsj() {
	return jxsj;
    }

    public void setJxsj(String jxsj) {
	this.jxsj = jxsj;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getTimejssj() {
	return Timejssj;
    }

    public void setTimejssj(String timejssj) {
	Timejssj = timejssj;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public BigDecimal getJXother() {
	return JXother;
    }

    public void setJXother(BigDecimal jXother) {
	JXother = jXother;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
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

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public BigDecimal getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(BigDecimal privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public Integer getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(Integer privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public BigDecimal getTqsy() {
	return tqsy;
    }

    public void setTqsy(BigDecimal tqsy) {
	this.tqsy = tqsy;
    }

    public Integer getJudeg() {
	return judeg;
    }

    public void setJudeg(Integer judeg) {
	this.judeg = judeg;
    }

    @Override
    public String toString() {
	return "MylendResultDTO [OID=" + OID + ", ysbx=" + ysbx + ", zqs=" + zqs + ", productStatus=" + productStatus
		+ ", cjsjTime=" + cjsjTime + ", xghkr=" + xghkr + ", dqsj=" + dqsj + ", xmmc=" + xmmc + ", cjje=" + cjje
		+ ", nhll=" + nhll + ", hkzt=" + hkzt + ", privilegeName=" + privilegeName + ", privilegeRange="
		+ privilegeRange + ", privilegeTerm=" + privilegeTerm + ", tqsy=" + tqsy + ", judeg=" + judeg
		+ ", JXother=" + JXother + ", tqOID=" + tqOID + ", CashPoolOID=" + CashPoolOID + ", Timejssj="
		+ Timejssj + ", jxsj=" + jxsj + ", ifzz=" + ifzz + ", DOID=" + DOID + ", fullscale=" + fullscale
		+ ", cond=" + cond + ", charge=" + charge + ", creditType=" + creditType + ", productType="
		+ productType + ", privilegeType=" + privilegeType + ", lowAmount=" + lowAmount + ", ruleIntroduce="
		+ ruleIntroduce + ", rule=" + rule + "]";
    }

}
