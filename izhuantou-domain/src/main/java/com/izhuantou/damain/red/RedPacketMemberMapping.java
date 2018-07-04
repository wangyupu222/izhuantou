package com.izhuantou.damain.red;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

/**
 * 红包与用户关系
 * 
 * @author sweet
 * @date 2018年6月29日
 *
 */
@Table(name = "redpacketmembermapping")
public class RedPacketMemberMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3524602732316804406L;
	/**
	 * OID
	 */
	private String OID;
	/**
	 * 红包OID
	 */
	private String redOID;

	/**
	 * 用户OID
	 */
	private String memberOID;
	/**
	 * 最低使用金额
	 */
	private BigDecimal lowMoney;
	/**
	 * 红包面值
	 */
	private BigDecimal redAmount;
	/**
	 * 是否使用
	 */
	private String isUsed;
	/**
	 * 产品类型
	 */
	private String productType;
	/**
	 * 产品期限
	 */
	private String productTerm;

	/**
	 * 开始时间
	 */
	private Date beginDateTime;
	/**
	 * 结束时间
	 */
	private Date endDateTime;
	/**
	 * 出借产品OID
	 */
	private String businessOID;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 资金池OID
	 */
	private String cashPoolOID;
	/**
	 * 描述
	 */
	private String describe0;
	/**
	 * 序号
	 */
	private Integer NO;
	/**
	 * 增加用户OID
	 */
	private String addUserOID;
	/**
	 * 修改用户OID
	 */
	private String updUserOID;
	/**
	 * 是否有效
	 */
	private Boolean valid;

	/**
	 * 添加的时间
	 */
	private Date addDateTime;
	/**
	 * 添加的时间
	 */
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

	public String getRedOID() {
		return redOID;
	}

	public void setRedOID(String redOID) {
		this.redOID = redOID;
	}

	public String getMemberOID() {
		return memberOID;
	}

	public void setMemberOID(String memberOID) {
		this.memberOID = memberOID;
	}

	public BigDecimal getLowMoney() {
		return lowMoney;
	}

	public void setLowMoney(BigDecimal lowMoney) {
		this.lowMoney = lowMoney;
	}

	public BigDecimal getRedAmount() {
		return redAmount;
	}

	public void setRedAmount(BigDecimal redAmount) {
		this.redAmount = redAmount;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductTerm() {
		return productTerm;
	}

	public void setProductTerm(String productTerm) {
		this.productTerm = productTerm;
	}

	public Date getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(Date beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getBusinessOID() {
		return businessOID;
	}

	public void setBusinessOID(String businessOID) {
		this.businessOID = businessOID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCashPoolOID() {
		return cashPoolOID;
	}

	public void setCashPoolOID(String cashPoolOID) {
		this.cashPoolOID = cashPoolOID;
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
		return "RedPacketMemberMapping [OID=" + OID + ", redOID=" + redOID + ", memberOID=" + memberOID + ", lowMoney="
				+ lowMoney + ", redAmount=" + redAmount + ", isUsed=" + isUsed + ", productType=" + productType
				+ ", productTerm=" + productTerm + ", beginDateTime=" + beginDateTime + ", endDateTime=" + endDateTime
				+ ", businessOID=" + businessOID + ", userType=" + userType + ", cashPoolOID=" + cashPoolOID
				+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
				+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
				+ ", refresh=" + refresh + ", version=" + version + "]";
	}

}
