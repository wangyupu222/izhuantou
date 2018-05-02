package com.izhuantou.damain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 协议模板
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "member_agreement")
public class MemberAgreement extends BasePojo {

    /**
    * 
    */
    private static final long serialVersionUID = 7903287692853744009L;

    /**
     * 协议英文名称
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 协议英文名称
     */
    private String name;
    /**
     * 协议中文名称
     */
    @Column(name = "nameCN")
    private String nameCN;
    /**
     * 协议内容
     */
    private String content;

    /**
     * 标的/项目类型（0：注册；1：散标；2：团标；3：新手标；4：债转）
     */
    @Column(name = "biddingType")
    private String biddingType;
    /**
     * 合同类型（1 砖头网注册协议 2 抵押借款协议 3 砖头网点点投服务协议 4 砖头网环环投服务协议 5 砖头网新手标服务协议 6
     * 砖头网信贷借款协议 7 砖头网债权转让协议 8 逾期债权预收购协议 9 砖头网转转投服务协议 ）
     */
    @Column(name = "contractType")
    private String contractType;

    /**
     * 启用时间（根据该时间决定前台何时调用合同模板）
     */
    @Column(name = "enableTime")
    private Date enableTime;
    /**
     * 停用时间（根据该时间决定前台何时调用合同模板）
     */
    @Column(name = "disableTime")
    private Date disableTime;

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
     * 添加的时间
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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getBiddingType() {
	return biddingType;
    }

    public void setBiddingType(String biddingType) {
	this.biddingType = biddingType;
    }

    public String getContractType() {
	return contractType;
    }

    public void setContractType(String contractType) {
	this.contractType = contractType;
    }

    public Date getEnableTime() {
	return enableTime;
    }

    public void setEnableTime(Date enableTime) {
	this.enableTime = enableTime;
    }

    public Date getDisableTime() {
	return disableTime;
    }

    public void setDisableTime(Date disableTime) {
	this.disableTime = disableTime;
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
	return "MemberAgreement [OID=" + OID + ", name=" + name + ", nameCN=" + nameCN + ", content=" + content
		+ ", biddingType=" + biddingType + ", contractType=" + contractType + ", enableTime=" + enableTime
		+ ", disableTime=" + disableTime + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID="
		+ addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime
		+ ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
