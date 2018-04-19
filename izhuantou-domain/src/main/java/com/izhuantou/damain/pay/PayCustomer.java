package com.izhuantou.damain.pay;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 身份认证账户信息表（实名认证之后的账户信息在该表中）
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "pay_customer")
public class PayCustomer extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 商户OID
     */
    @Column(name = "sellerOID")
    private String sellerOID;
    /**
     * 会员OID
     */
    @Column(name = "memberOID")
    private String memberOID;

    /**
     * 登陆名
     */
    private String name;

    /**
     * 客户姓名
     */
    @Column(name = "nameCN")
    private String nameCN;

    /**
     * 证件类型
     */
    @Column(name = "cardType")
    private String cardType;

    /**
     * 身份证号码/证件
     */
    @Column(name = "cardNO")
    private String cardNO;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 开户行地区代码
     */
    @Column(name = "bankCityCode")
    private String bankCityCode;

    /**
     * 开户行行别
     */
    @Column(name = "bankCode")
    private String bankCode;

    /**
     * 户名
     */
    @Column(name = "bankNameCN")
    private String bankNameCN;

    /**
     * 帐号
     */
    @Column(name = "bankNumber")
    private String bankNumber;

    /**
     * 提现密码
     */
    @Column(name = "withdrawalsPassword")
    private String withdrawalsPassword;

    /**
     * 登录密码
     */
    @Column(name = "loginPassword")
    private String loginPassword;

    /**
     * 请求标识
     */
    @Column(name = "requestID")
    private String requestID;

    /**
     * 账户金额
     */
    private BigDecimal money;

    /**
     * 冻结金额
     */
    @Column(name = "frozenMoney")
    private BigDecimal frozenMoney;
    /**
     * 可用余额
     */
    @Column(name = "useMoney")
    private BigDecimal useMoney;
    /**
     * 未转结余额
     */
    @Column(name = "noTransferMoney")
    private BigDecimal noTransferMoney;
    /**
     * 委托充值
     */
    @Column(name = "entrustRecharge")
    private String entrustRecharge;
    /**
     * 委托提现
     */
    /**
     * 黑名单
     */
    private String blacklist;
    /**
     * 手绘签章base64编码
     */
    @Column(name = "handBase")
    private String handBase;

    @Column(name = "entrustWithdrawals")
    private String entrustWithdrawals;
    /**
     * 免费额度
     */
    private BigDecimal freeline;
    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private String NO;
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

    public String getSellerOID() {
	return sellerOID;
    }

    public void setSellerOID(String sellerOID) {
	this.sellerOID = sellerOID;
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

    public String getCardType() {
	return cardType;
    }

    public void setCardType(String cardType) {
	this.cardType = cardType;
    }

    public String getCardNO() {
	return cardNO;
    }

    public void setCardNO(String cardNO) {
	this.cardNO = cardNO;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getBankCityCode() {
	return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
	this.bankCityCode = bankCityCode;
    }

    public String getBankCode() {
	return bankCode;
    }

    public void setBankCode(String bankCode) {
	this.bankCode = bankCode;
    }

    public String getBankNameCN() {
	return bankNameCN;
    }

    public void setBankNameCN(String bankNameCN) {
	this.bankNameCN = bankNameCN;
    }

    public String getBankNumber() {
	return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
	this.bankNumber = bankNumber;
    }

    public String getWithdrawalsPassword() {
	return withdrawalsPassword;
    }

    public void setWithdrawalsPassword(String withdrawalsPassword) {
	this.withdrawalsPassword = withdrawalsPassword;
    }

    public String getLoginPassword() {
	return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
	this.loginPassword = loginPassword;
    }

    public String getRequestID() {
	return requestID;
    }

    public void setRequestID(String requestID) {
	this.requestID = requestID;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public BigDecimal getFrozenMoney() {
	return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
	this.frozenMoney = frozenMoney;
    }

    public BigDecimal getUseMoney() {
	return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
	this.useMoney = useMoney;
    }

    public BigDecimal getNoTransferMoney() {
	return noTransferMoney;
    }

    public void setNoTransferMoney(BigDecimal noTransferMoney) {
	this.noTransferMoney = noTransferMoney;
    }

    public String getEntrustRecharge() {
	return entrustRecharge;
    }

    public void setEntrustRecharge(String entrustRecharge) {
	this.entrustRecharge = entrustRecharge;
    }

    public String getBlacklist() {
	return blacklist;
    }

    public void setBlacklist(String blacklist) {
	this.blacklist = blacklist;
    }

    public String getHandBase() {
	return handBase;
    }

    public void setHandBase(String handBase) {
	this.handBase = handBase;
    }

    public String getEntrustWithdrawals() {
	return entrustWithdrawals;
    }

    public void setEntrustWithdrawals(String entrustWithdrawals) {
	this.entrustWithdrawals = entrustWithdrawals;
    }

    public BigDecimal getFreeline() {
	return freeline;
    }

    public void setFreeline(BigDecimal freeline) {
	this.freeline = freeline;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public String getNO() {
	return NO;
    }

    public void setNO(String nO) {
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
	return "PAY_Customer [OID=" + OID + ", sellerOID=" + sellerOID + ", memberOID=" + memberOID + ", name=" + name
		+ ", nameCN=" + nameCN + ", cardType=" + cardType + ", cardNO=" + cardNO + ", mobile=" + mobile
		+ ", email=" + email + ", bankCityCode=" + bankCityCode + ", bankCode=" + bankCode + ", bankNameCN="
		+ bankNameCN + ", bankNumber=" + bankNumber + ", withdrawalsPassword=" + withdrawalsPassword
		+ ", loginPassword=" + loginPassword + ", requestID=" + requestID + ", money=" + money
		+ ", frozenMoney=" + frozenMoney + ", useMoney=" + useMoney + ", noTransferMoney=" + noTransferMoney
		+ ", entrustRecharge=" + entrustRecharge + ", blacklist=" + blacklist + ", handBase=" + handBase
		+ ", entrustWithdrawals=" + entrustWithdrawals + ", freeline=" + freeline + ", describe0=" + describe0
		+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
