package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 平台贷款产品信息表（i薪贷、i抵押、i房贷、i保单、i意贷）
 * 
 * @author yangbosen
 * @version 1.0
 */
@Table(name = "p2p_pageloan")
public class P2pPageLoan extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 7762320129562088715L;

    @Id
    @Column(name = "OID")
    private String OID;

    /**
     * 借款标题定义
     */
    private String title;
    /**
     * 借款类型简介定义
     */
    private String summary;

    /**
     * 借款借款类型
     */
    private String miaoshu;

    /**
     * 申请额度定义
     */
    private String quota;
    /**
     * 借款期限定义
     */
    private String term;
    /**
     * 还款方式定义
     */
    private String mode;

    /**
     * 申请对象定义
     */
    private String jobject;

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
    @Column(name = "valid")
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
     * 
     */
    private String parentOID;
    /**
     * 
     */
    private String branchEntity;
    /**
     * 
     */
    private String dataPath;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getSummary() {
	return summary;
    }

    public void setSummary(String summary) {
	this.summary = summary;
    }

    public String getMiaoshu() {
	return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
	this.miaoshu = miaoshu;
    }

    public String getQuota() {
	return quota;
    }

    public void setQuota(String quota) {
	this.quota = quota;
    }

    public String getTerm() {
	return term;
    }

    public void setTerm(String term) {
	this.term = term;
    }

    public String getMode() {
	return mode;
    }

    public void setMode(String mode) {
	this.mode = mode;
    }

    public String getJobject() {
	return jobject;
    }

    public void setJobject(String jobject) {
	this.jobject = jobject;
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

    public String getParentOID() {
	return parentOID;
    }

    public void setParentOID(String parentOID) {
	this.parentOID = parentOID;
    }

    public String getBranchEntity() {
	return branchEntity;
    }

    public void setBranchEntity(String branchEntity) {
	this.branchEntity = branchEntity;
    }

    public String getDataPath() {
	return dataPath;
    }

    public void setDataPath(String dataPath) {
	this.dataPath = dataPath;
    }

    @Override
    public String toString() {
	return "P2P_PageLoan [OID=" + OID + ", title=" + title + ", summary=" + summary + ", miaoshu=" + miaoshu
		+ ", quota=" + quota + ", term=" + term + ", mode=" + mode + ", jobject=" + jobject + ", describe0="
		+ describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid="
		+ valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + ", parentOID=" + parentOID + ", branchEntity=" + branchEntity + ", dataPath="
		+ dataPath + "]";
    }

}
