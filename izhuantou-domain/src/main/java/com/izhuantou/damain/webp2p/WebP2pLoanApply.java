package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 借款申请管理（已注册用户）(网贷系统审批申请匹配小贷数据)
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "webp2p_loanapply")
public class WebP2pLoanApply extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -162603207931043376L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 平台用户编号（申请人/借款人）
     */
    @Column(name = "memberOID")
    private String memberOID;
    /**
     * 姓名
     */
    @Column(name = "userName")
    private String userName;
    /**
     * 身份证
     */
    @Column(name = "idCard")
    private String idCard;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 借款金额
     */
    @Column(name = "loanAmount")
    private BigDecimal loanAmount;
    /**
     * 借款期限
     */
    @Column(name = "loanTerm")
    private String loanTerm;
    /**
     * 借款用途
     */
    @Column(name = "loanYT")
    private String loanYT;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 借款利率
     */
    private String rate;

    /**
     * 产品债权池类型（0：未分配；1：新手；2：散标；3：团标；4：债转；）
     */
    @Column(name = "productType")
    private Integer productType = 0;
    /**
     * 申请时间
     */
    @Column(name = "applyTime")
    private Date applyTime;
    /**
     * 申请时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;

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
    private String approvalTime;
    /**
     * 审批内容
     */
    @Column(name = "approvalRemark")
    private String approvalRemark;
    /**
     * 处理状态（0：待审核；1：已通过；2：未通过）
     */
    @Column(name = "isAgree")
    private String isAgree;
    /**
     * 整体结果是否完成（0：未完成；1：完成）
     */
    @Column(name = "isFinish")
    private String isFinish;
    /**
     * 是否放款（0：未放；1：已放款）
     */
    @Column(name = "isPayment")
    private String isPayment = "0";
    /**
     * 放款时间
     */
    @Column(name = "paymentTime")
    private String paymentTime;
    /**
     * 借款产品类型ID
     */
    @Column(name = "loanProductRateInfoID")
    private String loanProductRateInfoID;

    /**
     * 小贷主表OID
     */
    @Column(name = "xdmainOID")
    private String xdmainOID;
    /**
     * 小贷标的申请OID
     */
    @Column(name = "BiddingApplyOID")
    private String BiddingApplyOID;

    /**
     * 审批通过后生成
     */
    @Column(name = "loanNumber")
    private String loanNumber;
    /**
     * 身份证OID（图片）
     */
    @Column(name = "sfzOID")
    private String sfzOID;
    /**
     * 住址证明OID（证明）
     */
    @Column(name = "addressproveOID")
    private String addressproveOID;
    /**
     * 工作证明OID（图片）
     */
    private String workcertificate;
    /**
     * 信用报告OID（图片）
     */
    private String creditreport;
    /**
     * 审批意见表OID（图片）
     */
    private String spyj;

    /**
     * 备注
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

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public BigDecimal getLoanAmount() {
	return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
	this.loanAmount = loanAmount;
    }

    public String getLoanTerm() {
	return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
	this.loanTerm = loanTerm;
    }

    public String getLoanYT() {
	return loanYT;
    }

    public void setLoanYT(String loanYT) {
	this.loanYT = loanYT;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getRate() {
	return rate;
    }

    public void setRate(String rate) {
	this.rate = rate;
    }

    public Integer getProductType() {
	return productType;
    }

    public void setProductType(Integer productType) {
	this.productType = productType;
    }

    public Date getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(Date applyTime) {
	this.applyTime = applyTime;
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

    public String getApprovalTime() {
	return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
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

    public String getIsPayment() {
	return isPayment;
    }

    public void setIsPayment(String isPayment) {
	this.isPayment = isPayment;
    }

    public String getPaymentTime() {
	return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
	this.paymentTime = paymentTime;
    }

    public String getLoanProductRateInfoID() {
	return loanProductRateInfoID;
    }

    public void setLoanProductRateInfoID(String loanProductRateInfoID) {
	this.loanProductRateInfoID = loanProductRateInfoID;
    }

    public String getXdmainOID() {
	return xdmainOID;
    }

    public void setXdmainOID(String xdmainOID) {
	this.xdmainOID = xdmainOID;
    }

    public String getBiddingApplyOID() {
	return BiddingApplyOID;
    }

    public void setBiddingApplyOID(String biddingApplyOID) {
	BiddingApplyOID = biddingApplyOID;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getSfzOID() {
	return sfzOID;
    }

    public void setSfzOID(String sfzOID) {
	this.sfzOID = sfzOID;
    }

    public String getAddressproveOID() {
	return addressproveOID;
    }

    public void setAddressproveOID(String addressproveOID) {
	this.addressproveOID = addressproveOID;
    }

    public String getWorkcertificate() {
	return workcertificate;
    }

    public void setWorkcertificate(String workcertificate) {
	this.workcertificate = workcertificate;
    }

    public String getCreditreport() {
	return creditreport;
    }

    public void setCreditreport(String creditreport) {
	this.creditreport = creditreport;
    }

    public String getSpyj() {
	return spyj;
    }

    public void setSpyj(String spyj) {
	this.spyj = spyj;
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

    /**
     * 返回给页面的时间
     */
    private String sj;
    /**
     * 
     */
    private String first;
    /**
     * 
     */
    private String second;

    public String getSj() {
	return sj;
    }

    public void setSj(String sj) {
	this.sj = sj;
    }

    public String getFirst() {
	return first;
    }

    public void setFirst(String first) {
	this.first = first;
    }

    public String getSecond() {
	return second;
    }

    public void setSecond(String second) {
	this.second = second;
    }

    @Override
    public String toString() {
	return "WEBP2P_LoanApply [OID=" + OID + ", memberOID=" + memberOID + ", userName=" + userName + ", idCard="
		+ idCard + ", mobile=" + mobile + ", loanAmount=" + loanAmount + ", loanTerm=" + loanTerm + ", loanYT="
		+ loanYT + ", city=" + city + ", rate=" + rate + ", productType=" + productType + ", applyTime="
		+ applyTime + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", approvalUserID="
		+ approvalUserID + ", approvalUserName=" + approvalUserName + ", approvalTime=" + approvalTime
		+ ", approvalRemark=" + approvalRemark + ", isAgree=" + isAgree + ", isFinish=" + isFinish
		+ ", isPayment=" + isPayment + ", paymentTime=" + paymentTime + ", loanProductRateInfoID="
		+ loanProductRateInfoID + ", xdmainOID=" + xdmainOID + ", BiddingApplyOID=" + BiddingApplyOID
		+ ", loanNumber=" + loanNumber + ", sfzOID=" + sfzOID + ", addressproveOID=" + addressproveOID
		+ ", workcertificate=" + workcertificate + ", creditreport=" + creditreport + ", spyj=" + spyj
		+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", refresh=" + refresh + ", version=" + version + ", sj=" + sj
		+ ", first=" + first + ", second=" + second + "]";
    }

}
