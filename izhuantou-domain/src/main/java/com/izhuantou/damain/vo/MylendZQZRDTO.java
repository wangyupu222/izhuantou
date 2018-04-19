package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MylendZQZRDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6751177830562396560L;

    /**
     * biddingType 值为3
     */
    private Integer biddingType;
    /**
     * 标的名称
     */
    private String xmmc;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 还款方式（文字）
     */
    private String repaymentType;
    /**
     * 年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借时间
     */
    private String cjsjDate;
    /**
     * 到期时间
     */
    private String dqsj;
    /**
     * 标的状态（0：未发布；1：在投；2：还款中；3：已到期；4：逾期；5：已结清；）
     */
    private String hkzt;
    /**
     * 期数
     */
    private Integer tatalTerm;
    /**
     * 应收金额
     */
    private BigDecimal ysje;

    /**
     * 到期金额
     */
    private BigDecimal dsje;
    /**
     * 
     */
    private String cjsjTime;
    /**
     * 剩余返还其数
     */
    private Integer syqs;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;
    /**
     * pay_cashpool的OID
     */
    private String PaydebitOID;
    /**
     * pay_cashpool的businessOID
     */
    private String businessOID;
    /**
     * 
     */
    private String syts;

    /**
     * 产品期限（月）
     */
    private double productTerm;

    /**
     * PAY_CashPool添加的时间
     */
    private String addDateTime;
    /**
     * 现在时间和 addDateTime时间是否大于3个月
     */
    private boolean kzz;

    /**
     * 审批结果（0：待审批 ；1：通过；2:不通过；3:债转完成）
     */
    private String isAgree;

    /**
     * 对应转让价格
     */
    private BigDecimal zrsybx;
    /**
     * 转让本金
     */
    private BigDecimal zrsybj;
    /**
     * 转让的垫付利息
     */
    private BigDecimal zrsylx;
    /**
     * 转让手续费
     */
    private BigDecimal zrsxf;
    /**
     * 剩余期数
     */
    private Integer xssyqs;
    /**
     * 对应查到的预收本息
     */
    private BigDecimal jg;
    /**
     * 赎回利率
     */
    private BigDecimal shlv;
    /**
     * 用户OID
     */
    private String memberOID;

    /**
     * 移动端总期数
     */
    private String zqs;
    /**
     * 移动端应收本金
     */
    private String ysbj;
    /**
     * 移动端以收利息
     */
    private String yslx;

    private String sxf;

    public String getSxf() {
	return sxf;
    }

    public void setSxf(String sxf) {
	this.sxf = sxf;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getYsbj() {
	return ysbj;
    }

    public void setYsbj(String ysbj) {
	this.ysbj = ysbj;
    }

    public String getYslx() {
	return yslx;
    }

    public void setYslx(String yslx) {
	this.yslx = yslx;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public BigDecimal getZrsybx() {
	return zrsybx;
    }

    public void setZrsybx(BigDecimal zrsybx) {
	this.zrsybx = zrsybx;
    }

    public BigDecimal getZrsybj() {
	return zrsybj;
    }

    public void setZrsybj(BigDecimal zrsybj) {
	this.zrsybj = zrsybj;
    }

    public BigDecimal getZrsylx() {
	return zrsylx;
    }

    public void setZrsylx(BigDecimal zrsylx) {
	this.zrsylx = zrsylx;
    }

    public BigDecimal getZrsxf() {
	return zrsxf;
    }

    public void setZrsxf(BigDecimal zrsxf) {
	this.zrsxf = zrsxf;
    }

    public Integer getXssyqs() {
	return xssyqs;
    }

    public void setXssyqs(Integer xssyqs) {
	this.xssyqs = xssyqs;
    }

    public BigDecimal getJg() {
	return jg;
    }

    public void setJg(BigDecimal jg) {
	this.jg = jg;
    }

    public BigDecimal getShlv() {
	return shlv;
    }

    public void setShlv(BigDecimal shlv) {
	this.shlv = shlv;
    }

    public Integer getBiddingType() {
	return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
	this.biddingType = biddingType;
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

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getCjsjDate() {
	return cjsjDate;
    }

    public void setCjsjDate(String cjsjDate) {
	this.cjsjDate = cjsjDate;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getHkzt() {
	return hkzt;
    }

    public void setHkzt(String hkzt) {
	this.hkzt = hkzt;
    }

    public Integer getTatalTerm() {
	return tatalTerm;
    }

    public void setTatalTerm(Integer tatalTerm) {
	this.tatalTerm = tatalTerm;
    }

    public BigDecimal getYsje() {
	return ysje;
    }

    public void setYsje(BigDecimal ysje) {
	this.ysje = ysje;
    }

    public BigDecimal getDsje() {
	return dsje;
    }

    public void setDsje(BigDecimal dsje) {
	this.dsje = dsje;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public String getPaydebitOID() {
	return PaydebitOID;
    }

    public void setPaydebitOID(String paydebitOID) {
	PaydebitOID = paydebitOID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getSyts() {
	return syts;
    }

    public void setSyts(String syts) {
	this.syts = syts;
    }

    public double getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(double productTerm) {
	this.productTerm = productTerm;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public boolean isKzz() {
	return kzz;
    }

    public void setKzz(boolean kzz) {
	this.kzz = kzz;
    }

    public String getIsAgree() {
	return isAgree;
    }

    public void setIsAgree(String isAgree) {
	this.isAgree = isAgree;
    }

    @Override
    public String toString() {
	return "MylendZQZRDTO [biddingType=" + biddingType + ", xmmc=" + xmmc + ", cjje=" + cjje + ", repaymentType="
		+ repaymentType + ", nhll=" + nhll + ", cjsjDate=" + cjsjDate + ", dqsj=" + dqsj + ", hkzt=" + hkzt
		+ ", tatalTerm=" + tatalTerm + ", ysje=" + ysje + ", dsje=" + dsje + ", cjsjTime=" + cjsjTime
		+ ", syqs=" + syqs + ", ysbx=" + ysbx + ", PaydebitOID=" + PaydebitOID + ", businessOID=" + businessOID
		+ ", syts=" + syts + ", productTerm=" + productTerm + ", addDateTime=" + addDateTime + ", kzz=" + kzz
		+ ", isAgree=" + isAgree + ", zrsybx=" + zrsybx + ", zrsybj=" + zrsybj + ", zrsylx=" + zrsylx
		+ ", zrsxf=" + zrsxf + ", xssyqs=" + xssyqs + ", jg=" + jg + ", shlv=" + shlv + ", memberOID="
		+ memberOID + ", zqs=" + zqs + ", ysbj=" + ysbj + ", yslx=" + yslx + ", sxf=" + sxf + "]";
    }

}
