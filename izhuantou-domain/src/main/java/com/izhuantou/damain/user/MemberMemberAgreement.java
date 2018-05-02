package com.izhuantou.damain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 用户协议
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "member_memberagreement")
public class MemberMemberAgreement extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 58232816540658197L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;
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
    private String enableTime;
    /**
     * 停用时间（根据该时间决定前台何时调用合同模板）
     */
    @Column(name = "disableTime")
    private String disableTime;

    /**
     * 标的OID
     */
    @Column(name = "biddingOID")
    private String biddingOID;
    /**
     * 协议编号
     */
    private String xybh;
    /**
     * 协议状态（1：进行中2：已关闭）
     */
    private String state;
    /**
     * 借款编号“ZT12312341” 审批通过后生成
     */
    @Column(name = "loanNumber")
    private String loanNumber;
    /**
     * 投资后查看协议编号
     */
    @Column(name = "ckOID")
    private String ckOID;
    /**
     * 电子签章的签署记录
     */
    @Column(name = "signIDs")
    private String signIDs;
    /**
     * pdf存储路径
     */
    @Column(name = "pdfPath")
    private String pdfPath;
    /**
     * pdf转存图片
     */
    @Column(name = "pdfPic")
    private String pdfPic;
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

    public boolean isRefresh() {
	return refresh;
    }

    public void setRefresh(boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

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

    public String getEnableTime() {
	return enableTime;
    }

    public void setEnableTime(String enableTime) {
	this.enableTime = enableTime;
    }

    public String getDisableTime() {
	return disableTime;
    }

    public void setDisableTime(String disableTime) {
	this.disableTime = disableTime;
    }

    public String getBiddingOID() {
	return biddingOID;
    }

    public void setBiddingOID(String biddingOID) {
	this.biddingOID = biddingOID;
    }

    public String getXybh() {
	return xybh;
    }

    public void setXybh(String xybh) {
	this.xybh = xybh;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getLoanNumber() {
	return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
	this.loanNumber = loanNumber;
    }

    public String getCkOID() {
	return ckOID;
    }

    public void setCkOID(String ckOID) {
	this.ckOID = ckOID;
    }

    public String getSignIDs() {
	return signIDs;
    }

    public void setSignIDs(String signIDs) {
	this.signIDs = signIDs;
    }

    public String getPdfPath() {
	return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
	this.pdfPath = pdfPath;
    }

    public String getPdfPic() {
	return pdfPic;
    }

    public void setPdfPic(String pdfPic) {
	this.pdfPic = pdfPic;
    }

    @Override
    public String toString() {
	return "MemberMemberAgreement [OID=" + OID + ", memberOID=" + memberOID + ", name=" + name + ", nameCN="
		+ nameCN + ", content=" + content + ", biddingType=" + biddingType + ", contractType=" + contractType
		+ ", enableTime=" + enableTime + ", disableTime=" + disableTime + ", biddingOID=" + biddingOID
		+ ", xybh=" + xybh + ", state=" + state + ", loanNumber=" + loanNumber + ", ckOID=" + ckOID
		+ ", signIDs=" + signIDs + ", pdfPath=" + pdfPath + ", pdfPic=" + pdfPic + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
