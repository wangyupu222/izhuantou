package com.izhuantou.damain.lend;

import java.math.BigDecimal;
import java.util.Date;

import com.izhuantou.damain.BasePojo;

/**
 * 转转投详情信息
 * 
 * @author yangbosen
 *
 */
public class DetailZZTInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String OID;
    private String debitCreditOID;
    /**
     * 产品OID
     */
    private String businessOID;
    /**
     * 借款人OID
     */
    private String memberOID;
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
    private BigDecimal nhll;
    /**
     * 产品类型
     */
    private Integer producttype;
    /**
     * 出借时间
     */
    private Date cjsj;
    /**
     * 已收期数
     */
    private BigDecimal syqx;
    /**
     * 总期数
     */
    private String zqs;
    /**
     * 
     */
    private BigDecimal ysbx;
    /**
     * 已收本金
     */
    private BigDecimal ysbj;
    /**
     * 
     */
    private String beizhu;
    /**
     * 
     */
    private String sxf;
    /**
     * 以收利息
     */
    private BigDecimal yslx;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 
     */
    private String approvalUserID;
    /**
     * 
     */
    private String approvalUserName;
    /**
     * 
     */
    private Date approvalTime;
    /**
     * 
     */
    private String approvalRemark;
    /**
     * 
     */
    private Integer isAgree;
    /**
     * 
     */
    private Integer isFinish;

    // 转让价格
    private BigDecimal bdjg;

    public BigDecimal getBdjg() {
	return bdjg;
    }

    public void setBdjg(BigDecimal bdjg) {
	this.bdjg = bdjg;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getDebitCreditOID() {
	return debitCreditOID;
    }

    public void setDebitCreditOID(String debitCreditOID) {
	this.debitCreditOID = debitCreditOID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
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

    public Integer getProducttype() {
	return producttype;
    }

    public void setProducttype(Integer producttype) {
	this.producttype = producttype;
    }

    public Date getCjsj() {
	return cjsj;
    }

    public void setCjsj(Date cjsj) {
	this.cjsj = cjsj;
    }

    public BigDecimal getSyqx() {
	return syqx;
    }

    public void setSyqx(BigDecimal syqx) {
	this.syqx = syqx;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public BigDecimal getYsbj() {
	return ysbj;
    }

    public void setYsbj(BigDecimal ysbj) {
	this.ysbj = ysbj;
    }

    public String getBeizhu() {
	return beizhu;
    }

    public void setBeizhu(String beizhu) {
	this.beizhu = beizhu;
    }

    public String getSxf() {
	return sxf;
    }

    public void setSxf(String sxf) {
	this.sxf = sxf;
    }

    public BigDecimal getYslx() {
	return yslx;
    }

    public void setYslx(BigDecimal yslx) {
	this.yslx = yslx;
    }

    public Date getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(Date applyTime) {
	this.applyTime = applyTime;
    }

    public String getApprovalUserID() {
	return approvalUserID;
    }

    public void setApprovalUserID(String approvalUserID) {
	this.approvalUserID = approvalUserID;
    }

    public String getApprovalUserName() {
	return approvalUserName;
    }

    public void setApprovalUserName(String approvalUserName) {
	this.approvalUserName = approvalUserName;
    }

    public Date getApprovalTime() {
	return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
	this.approvalTime = approvalTime;
    }

    public String getApprovalRemark() {
	return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
	this.approvalRemark = approvalRemark;
    }

    public Integer getIsAgree() {
	return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
	this.isAgree = isAgree;
    }

    public Integer getIsFinish() {
	return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
	this.isFinish = isFinish;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DetailZZTInfo [OID=" + OID + ", debitCreditOID=" + debitCreditOID + ", businessOID=" + businessOID
		+ ", memberOID=" + memberOID + ", xmmc=" + xmmc + ", cjje=" + cjje + ", nhll=" + nhll + ", producttype="
		+ producttype + ", cjsj=" + cjsj + ", syqx=" + syqx + ", zqs=" + zqs + ", ysbx=" + ysbx + ", ysbj="
		+ ysbj + ", beizhu=" + beizhu + ", sxf=" + sxf + ", yslx=" + yslx + ", applyTime=" + applyTime
		+ ", approvalUserID=" + approvalUserID + ", approvalUserName=" + approvalUserName + ", approvalTime="
		+ approvalTime + ", approvalRemark=" + approvalRemark + ", isAgree=" + isAgree + ", isFinish="
		+ isFinish + ", bdjg=" + bdjg + "]";
    }

}
