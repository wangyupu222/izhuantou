package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 债转申请管理表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "webp2p_debttransferapply")
public class WebP2pDebtTransferApply extends BasePojo {

    private static final long serialVersionUID = -5773616254575411735L;
    /**
     * 借贷关系OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 借贷关系OID
     */
    @Column(name = "debitCreditOID")
    private String debitCreditOID;
    /**
     * 标的OID
     */
    @Column(name = "businessOID")
    private String businessOID;
    /**
     * 申请人OID
     */
    @Column(name = "memberOID")
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
     * 逾期年化利率
     */
    private BigDecimal nhll;
    /**
     * 标的类型，1新手2散标3团标4债转
     */
    private Integer producttype;
    /**
     * 出借时间
     */
    private String cjsj;
    /**
     * 剩余期数
     */
    private Integer syqs;
    /**
     * 总期数
     */
    private Integer zqs;
    /**
     * 应收本息
     */
    private BigDecimal ysbx;
    /**
     * 备注
     */
    private String beizhu;

    /**
     * 应收本金
     */
    private BigDecimal ysbj;
    /**
     * 手续费
     */
    private String sxf;
    /**
     * 应收利息
     */
    private BigDecimal yslx;

    /**
     * 申请时间
     */
    @Column(name = "applyTime")
    private Date applyTime;

    /**
     * 审批用户Id
     */
    @Column(name = "approvalUserID")
    private String approvalUserID;
    /**
     * 审批用户姓名
     */
    @Column(name = "approvalUserName")
    private String approvalUserName;
    /**
     * 审批时间
     */
    @Column(name = "approvalTime")
    private Date approvalTime;
    /**
     * 审批内容
     */
    @Column(name = "approvalRemark")
    private String approvalRemark;
    /**
     * 审批结果（0：待审批 ；1：通过；2:不通过；3:债转完成）
     */
    @Column(name = "isAgree")
    private String isAgree;
    /**
     * 整体结果（0：不成功；1：成功）
     */
    @Column(name = "isFinish")
    private String isFinish;
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

    /**
     * 标的价格
     */
    private BigDecimal bdjg;

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

    public String getCjsj() {
	return cjsj;
    }

    public void setCjsj(String cjsj) {
	this.cjsj = cjsj;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public Integer getZqs() {
	return zqs;
    }

    public void setZqs(Integer zqs) {
	this.zqs = zqs;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public String getBeizhu() {
	return beizhu;
    }

    public void setBeizhu(String beizhu) {
	this.beizhu = beizhu;
    }

    public BigDecimal getYsbj() {
	return ysbj;
    }

    public void setYsbj(BigDecimal ysbj) {
	this.ysbj = ysbj;
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

    public String getIsAgree() {
	return isAgree;
    }

    public void setIsAgree(String isAgree) {
	this.isAgree = isAgree;
    }

    public String getIsFinish() {
	return isFinish;
    }

    public void setIsFinish(String isFinish) {
	this.isFinish = isFinish;
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

    public BigDecimal getBdjg() {
	return bdjg;
    }

    public void setBdjg(BigDecimal bdjg) {
	this.bdjg = bdjg;
    }

    @Override
    public String toString() {
	return "WEBP2P_DebtTransferApply [OID=" + OID + ", debitCreditOID=" + debitCreditOID + ", businessOID="
		+ businessOID + ", memberOID=" + memberOID + ", xmmc=" + xmmc + ", cjje=" + cjje + ", nhll=" + nhll
		+ ", producttype=" + producttype + ", cjsj=" + cjsj + ", syqs=" + syqs + ", zqs=" + zqs + ", ysbx="
		+ ysbx + ", beizhu=" + beizhu + ", ysbj=" + ysbj + ", sxf=" + sxf + ", yslx=" + yslx + ", applyTime="
		+ applyTime + ", approvalUserID=" + approvalUserID + ", approvalUserName=" + approvalUserName
		+ ", approvalTime=" + approvalTime + ", approvalRemark=" + approvalRemark + ", isAgree=" + isAgree
		+ ", isFinish=" + isFinish + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
		+ updDateTime + ", refresh=" + refresh + ", version=" + version + ", bdjg=" + bdjg + "]";
    }

}
