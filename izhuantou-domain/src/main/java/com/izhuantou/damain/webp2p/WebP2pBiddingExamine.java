package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 发标申请（小贷提交的申请）
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "webp2p_biddingexamine")
public class WebP2pBiddingExamine extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 8264181223478527724L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 小贷主表OID
     */
    @Column(name = "xdmainOID")
    private String xdmainOID;
    /**
     * 借款编号“ZT12312341” 审批通过后生成
     */
    @Column(name = "loanNumber")
    private String loanNumber;

    /**
     * 平台用户编号（申请人/借款人）
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 真实姓名
     */
    @Column(name = "realName")
    private String realName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 借款用途
     */
    private String loanuse;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 身份证
     */
    @Column(name = "idCard")
    private String idCard;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 申请金额
     */
    @Column(name = "applyAmount")
    private BigDecimal applyAmount;

    /**
     * 申请时间
     */
    @Column(name = "applyTime")
    private String applyTime;
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
     * 处理状态（0：待审核；1：已通过；2：未通过;3:已分发）
     */
    @Column(name = "isAgree")
    private String isAgree;
    /**
     * 借款产品类型ID
     */
    @Column(name = "loanProductRateInfoID")
    private String loanProductRateInfoID;
    /**
     * 理财产品类型ID
     */
    @Column(name = "productRateInfoID")
    private String productRateInfoID;
    /**
     * 增加放到网贷平台新字段(编码已转码)
     */
    /**
     * 婚姻状况
     */
    private String ismarry;
    /**
     * 学历
     */
    private String highesteduback;
    /**
     * 入学年份
     */
    private String rxnf;
    /**
     * 工作所在地
     */
    private String dizhi;
    /**
     * 户口所在地
     */
    private String hkszd;
    /**
     * 公司行业(经营范围)
     */
    private String jyfw;
    /**
     * 职位
     */
    private String zhiwei;
    /**
     * 工作收入(每月薪金)
     */
    private String myxj;
    /**
     * 累计工作年限
     */
    private String ljgznx;
    /**
     * 有无购房
     */
    private String ywgf;
    /**
     * 有无购车
     */
    private String ywgc;
    /**
     * 有无房贷
     */
    private String ywfd;
    /**
     * 有无车贷
     */
    private String ywcd;
    /**
     * 民族
     */
    private String minzu;
    /**
     * 
     * 公司性质
     */
    private String dwxz;
    /**
     * 
     * 个人月收入
     */

    private String grysr;
    /**
     * 
     * 所在行业
     */

    private String sshy;

    /**
     * 工作城市
     */
    private String gzcs;

    private String hkfs;
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

    public Boolean isRefresh() {
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

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getGzcs() {
	return gzcs;
    }

    public void setGzcs(String gzcs) {
	this.gzcs = gzcs;
    }

    public String getMinzu() {
	return minzu;
    }

    public void setMinzu(String minzu) {
	this.minzu = minzu;
    }

    public String getDwxz() {
	return dwxz;
    }

    public void setDwxz(String dwxz) {
	this.dwxz = dwxz;
    }

    public String getGrysr() {
	return grysr;
    }

    public void setGrysr(String grysr) {
	this.grysr = grysr;
    }

    public String getSshy() {
	return sshy;
    }

    public void setSshy(String sshy) {
	this.sshy = sshy;
    }

    public String getXdmainOID() {
	return xdmainOID;
    }

    public void setXdmainOID(String xdmainOID) {
	this.xdmainOID = xdmainOID;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getLoanuse() {
	return loanuse;
    }

    public void setLoanuse(String loanuse) {
	this.loanuse = loanuse;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
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

    public BigDecimal getApplyAmount() {
	return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
	this.applyAmount = applyAmount;
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

    public String getApplyTime() {
	return applyTime;
    }

    public void setApplyTime(String applyTime) {
	this.applyTime = applyTime;
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

    public String getLoanProductRateInfoID() {
	return loanProductRateInfoID;
    }

    public void setLoanProductRateInfoID(String loanProductRateInfoID) {
	this.loanProductRateInfoID = loanProductRateInfoID;
    }

    public String getIsmarry() {
	return ismarry;
    }

    public void setIsmarry(String ismarry) {
	this.ismarry = ismarry;
    }

    public String getHighesteduback() {
	return highesteduback;
    }

    public void setHighesteduback(String highesteduback) {
	this.highesteduback = highesteduback;
    }

    public String getRxnf() {
	return rxnf;
    }

    public void setRxnf(String rxnf) {
	this.rxnf = rxnf;
    }

    public String getDizhi() {
	return dizhi;
    }

    public void setDizhi(String dizhi) {
	this.dizhi = dizhi;
    }

    public String getHkszd() {
	return hkszd;
    }

    public void setHkszd(String hkszd) {
	this.hkszd = hkszd;
    }

    public String getJyfw() {
	return jyfw;
    }

    public void setJyfw(String jyfw) {
	this.jyfw = jyfw;
    }

    public String getZhiwei() {
	return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
	this.zhiwei = zhiwei;
    }

    public String getMyxj() {
	return myxj;
    }

    public void setMyxj(String myxj) {
	this.myxj = myxj;
    }

    public String getLjgznx() {
	return ljgznx;
    }

    public void setLjgznx(String ljgznx) {
	this.ljgznx = ljgznx;
    }

    public String getYwgf() {
	return ywgf;
    }

    public void setYwgf(String ywgf) {
	this.ywgf = ywgf;
    }

    public String getYwgc() {
	return ywgc;
    }

    public void setYwgc(String ywgc) {
	this.ywgc = ywgc;
    }

    public String getYwfd() {
	return ywfd;
    }

    public void setYwfd(String ywfd) {
	this.ywfd = ywfd;
    }

    public String getYwcd() {
	return ywcd;
    }

    public void setYwcd(String ywcd) {
	this.ywcd = ywcd;
    }

    public String getProductRateInfoID() {
	return productRateInfoID;
    }

    public void setProductRateInfoID(String productRateInfoID) {
	this.productRateInfoID = productRateInfoID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "WEBP2P_BiddingExamine [OID=" + OID + ", xdmainOID=" + xdmainOID + ", loanNumber=" + loanNumber
		+ ", memberOID=" + memberOID + ", realName=" + realName + ", gender=" + gender + ", loanuse=" + loanuse
		+ ", age=" + age + ", idCard=" + idCard + ", mobile=" + mobile + ", applyAmount=" + applyAmount
		+ ", applyTime=" + applyTime + ", approvalUserID=" + approvalUserID + ", approvalUserName="
		+ approvalUserName + ", approvalTime=" + approvalTime + ", approvalRemark=" + approvalRemark
		+ ", isAgree=" + isAgree + ", loanProductRateInfoID=" + loanProductRateInfoID + ", productRateInfoID="
		+ productRateInfoID + ", ismarry=" + ismarry + ", highesteduback=" + highesteduback + ", rxnf=" + rxnf
		+ ", dizhi=" + dizhi + ", hkszd=" + hkszd + ", jyfw=" + jyfw + ", zhiwei=" + zhiwei + ", myxj=" + myxj
		+ ", ljgznx=" + ljgznx + ", ywgf=" + ywgf + ", ywgc=" + ywgc + ", ywfd=" + ywfd + ", ywcd=" + ywcd
		+ ", minzu=" + minzu + ", dwxz=" + dwxz + ", grysr=" + grysr + ", sshy=" + sshy + ", gzcs=" + gzcs
		+ ", hkfs=" + hkfs + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
		+ updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
