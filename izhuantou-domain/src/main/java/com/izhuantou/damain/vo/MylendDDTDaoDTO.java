package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MylendDDTDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1299378072362418849L;

    /**
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
     */
    private Integer productType;

    /**
     * 标的状态（0：未发布；1：在投；2：还款中；3：已到期；4：逾期；5：已结清；）
     */
    private String hkzt;

    /**
     * 点点投Oid
     */
    private String biOID;

    /**
     * 贷款类型
     */
    private String creditType;

    /**
     * 返还次数
     */
    private Integer zqs;

    /**
     * 在投点点id
     */
    private String DOID;
    /**
     * 出借金额
     */
    private BigDecimal cjje;

    /**
     * 标的名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private BigDecimal nhll;

    /**
     * 出借时间
     */
    private String cjsjTime;

    /**
     * 应收本息
     */
    private BigDecimal ysbx;
    /**
     * 
     */
    private boolean fullscale;
    /**
     * 剩余返还其数
     */
    private Integer syqs;

    /**
     * 特权OID
     */
    private String tqOID;

    /**
     * 计息时间
     */
    private String jxsj;
    /**
     * 到期时间
     */
    private String dqsj;

    /**
     * 下个回款日
     */
    private String xghkr;

    /**
     * 期数
     */
    private Integer tatalTerm;

    /**
     * 应收利息
     */
    private BigDecimal yslx;
    /**
     * 开始时间
     */
    private String startDateTime;

    /**
     * 结束时间
     */
    private String endDateTime;

    public Integer getTatalTerm() {
	return tatalTerm;
    }

    public void setTatalTerm(Integer tatalTerm) {
	this.tatalTerm = tatalTerm;
    }

    public BigDecimal getYslx() {
	return yslx;
    }

    public void setYslx(BigDecimal yslx) {
	this.yslx = yslx;
    }

    public String getStartDateTime() {
	return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
	this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
	return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
	this.endDateTime = endDateTime;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
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

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public boolean isFullscale() {
	return fullscale;
    }

    public void setFullscale(boolean fullscale) {
	this.fullscale = fullscale;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getJxsj() {
	return jxsj;
    }

    public void setJxsj(String jxsj) {
	this.jxsj = jxsj;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    @Override
    public String toString() {
	return "MylendDDTDaoDTO [productType=" + productType + ", hkzt=" + hkzt + ", biOID=" + biOID + ", creditType="
		+ creditType + ", zqs=" + zqs + ", DOID=" + DOID + ", cjje=" + cjje + ", xmmc=" + xmmc + ", nhll="
		+ nhll + ", cjsjTime=" + cjsjTime + ", ysbx=" + ysbx + ", fullscale=" + fullscale + ", syqs=" + syqs
		+ ", tqOID=" + tqOID + ", jxsj=" + jxsj + ", dqsj=" + dqsj + ", xghkr=" + xghkr + ", tatalTerm="
		+ tatalTerm + ", yslx=" + yslx + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
		+ "]";
    }

}
