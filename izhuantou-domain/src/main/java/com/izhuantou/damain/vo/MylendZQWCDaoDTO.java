package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MylendZQWCDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7226942068486762162L;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 转让金额
     */
    private BigDecimal zrje;
    /**
     * 出借金额
     */
    private BigDecimal cjje;
    /**
     * 逾期年化利率
     */
    private BigDecimal nhll;
    /**
     * 出借时间
     */
    private String cjsj;
    /**
     * 审批结果（0：待审批 ；1：通过；2:不通过；3:债转完成）
     */
    private String isAgree;
    /**
     * 转让时间
     */
    private String zrsj;

    /**
     * 剩余天数
     */
    private Integer syts;
    /**
     * 手续费
     */
    private String sxf;
    /**
     * 申请时间
     */
    private String applyTime;
    /**
     * 审批内容
     */
    private String approvalRemark;

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public String getIsAgree() {
	return isAgree;
    }

    public void setIsAgree(String isAgree) {
	this.isAgree = isAgree;
    }

    public String getApprovalRemark() {
	return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
	this.approvalRemark = approvalRemark;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public BigDecimal getZrje() {
	return zrje;
    }

    public void setZrje(BigDecimal zrje) {
	this.zrje = zrje;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public String getCjsj() {
	return cjsj;
    }

    public void setCjsj(String cjsj) {
	this.cjsj = cjsj;
    }

    public String getZrsj() {
	return zrsj;
    }

    public void setZrsj(String zrsj) {
	this.zrsj = zrsj;
    }

    public Integer getSyts() {
	return syts;
    }

    public void setSyts(Integer syts) {
	this.syts = syts;
    }

    public String getSxf() {
	return sxf;
    }

    public void setSxf(String sxf) {
	this.sxf = sxf;
    }

    public String getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(String applyTime) {
	this.applyTime = applyTime;
    }

    @Override
    public String toString() {
	return "MylendZQWCDaoDTO [xmmc=" + xmmc + ", zrje=" + zrje + ", cjje=" + cjje + ", nhll=" + nhll + ", cjsj="
		+ cjsj + ", isAgree=" + isAgree + ", zrsj=" + zrsj + ", syts=" + syts + ", sxf=" + sxf + ", applyTime="
		+ applyTime + ", approvalRemark=" + approvalRemark + "]";
    }

}
