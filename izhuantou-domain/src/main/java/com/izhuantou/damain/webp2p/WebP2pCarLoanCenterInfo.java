package com.izhuantou.damain.webp2p;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 已租贷购详细信息录入表
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "webp2p_carloancenterinfo")
public class WebP2pCarLoanCenterInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -5456606745203335556L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 借款人姓名
     */
    private String customername;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 身份证号
     */
    private String certificatesid;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 最高学历
     */
    private String education;
    /**
     * 户籍所在地
     */
    private String domicile;
    /**
     * 标的描述
     */
    @Column(name = "biddingContent")
    private String biddingContent;
    /**
     * 抵押物信息
     */
    private String collateral;
    /**
     * 申请金额
     */
    @Column(name = "applyAmount")
    private BigDecimal applyAmount;
    /**
     * 申请日期
     */
    @Column(name = "applyDate")
    private Date applyDate;
    /**
     * 申请借款期限
     */
    private String loandeadline;
    /**
     * 审核状态
     */
    @Column(name = "isAgree")
    private String isAgree;
    /**
     * 贷款产品类型
     */
    private String dkcplx;
    /**
     * 备注信息 bob 10.17
     */
    private String beizhu;
    // 备注复审
    private String beizhufs;
    /**
     * 标的编号(复审通过时生成)
     */
    @Column(name = "LoanNumber")
    private String LoanNumber;
    /**
     * 借款人年收入(为已租代购产品复用添加字段) cannon 10.31
     */
    @Column(name = "annualSalary")
    private BigDecimal annualSalary;
    /**
     * 车牌号
     */
    @Column(name = "plateNum")
    private String plateNum;
    /**
     * 车架号
     */
    @Column(name = "chassisNum")
    private String chassisNum;
    /**
     * 发动机号
     */
    @Column(name = "engineNo")
    private String engineNo;

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

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getCustomername() {
	return customername;
    }

    public void setCustomername(String customername) {
	this.customername = customername;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getAge() {
	return age;
    }

    public void setAge(String age) {
	this.age = age;
    }

    public String getCertificatesid() {
	return certificatesid;
    }

    public void setCertificatesid(String certificatesid) {
	this.certificatesid = certificatesid;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getEducation() {
	return education;
    }

    public void setEducation(String education) {
	this.education = education;
    }

    public String getDomicile() {
	return domicile;
    }

    public void setDomicile(String domicile) {
	this.domicile = domicile;
    }

    public String getBiddingContent() {
	return biddingContent;
    }

    public void setBiddingContent(String biddingContent) {
	this.biddingContent = biddingContent;
    }

    public String getCollateral() {
	return collateral;
    }

    public void setCollateral(String collateral) {
	this.collateral = collateral;
    }

    public BigDecimal getApplyAmount() {
	return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
	this.applyAmount = applyAmount;
    }

    public Date getApplyDate() {
	return applyDate;
    }

    public void setApplyDate(Date applyDate) {
	this.applyDate = applyDate;
    }

    public String getLoandeadline() {
	return loandeadline;
    }

    public void setLoandeadline(String loandeadline) {
	this.loandeadline = loandeadline;
    }

    public String getIsAgree() {
	return isAgree;
    }

    public void setIsAgree(String isAgree) {
	this.isAgree = isAgree;
    }

    public String getDkcplx() {
	return dkcplx;
    }

    public void setDkcplx(String dkcplx) {
	this.dkcplx = dkcplx;
    }

    public String getBeizhu() {
	return beizhu;
    }

    public void setBeizhu(String beizhu) {
	this.beizhu = beizhu;
    }

    public String getBeizhufs() {
	return beizhufs;
    }

    public void setBeizhufs(String beizhufs) {
	this.beizhufs = beizhufs;
    }

    public String getLoanNumber() {
	return LoanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	LoanNumber = loanNumber;
    }

    public BigDecimal getAnnualSalary() {
	return annualSalary;
    }

    public void setAnnualSalary(BigDecimal annualSalary) {
	this.annualSalary = annualSalary;
    }

    public String getPlateNum() {
	return plateNum;
    }

    public void setPlateNum(String plateNum) {
	this.plateNum = plateNum;
    }

    public String getChassisNum() {
	return chassisNum;
    }

    public void setChassisNum(String chassisNum) {
	this.chassisNum = chassisNum;
    }

    public String getEngineNo() {
	return engineNo;
    }

    public void setEngineNo(String engineNo) {
	this.engineNo = engineNo;
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

    @Override
    public String toString() {
	return "WEBP2P_CarLoanCenterInfo [OID=" + OID + ", customername=" + customername + ", sex=" + sex + ", age="
		+ age + ", certificatesid=" + certificatesid + ", phone=" + phone + ", education=" + education
		+ ", domicile=" + domicile + ", biddingContent=" + biddingContent + ", collateral=" + collateral
		+ ", applyAmount=" + applyAmount + ", applyDate=" + applyDate + ", loandeadline=" + loandeadline
		+ ", isAgree=" + isAgree + ", dkcplx=" + dkcplx + ", beizhu=" + beizhu + ", beizhufs=" + beizhufs
		+ ", LoanNumber=" + LoanNumber + ", annualSalary=" + annualSalary + ", plateNum=" + plateNum
		+ ", chassisNum=" + chassisNum + ", engineNo=" + engineNo + ", describe0=" + describe0 + ", NO=" + NO
		+ ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
