package com.izhuantou.damain.red;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 红包配置信息
 * 
 * @author sweet
 * @date 2018年6月29日
 *
 */
@Table(name = "redpacket")
public class RedPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -104302545475818364L;

	/**
	 * OID
	 */
	private String OID;

	/**
	 * 红包名
	 */
	private String redName;

	/**
	 * 红包面值
	 */
	private BigDecimal redAmount;
	/**
	 * 最低使用金额
	 */
	private BigDecimal lowMoney;
	/**
	 * 有效期限
	 */
	private String expiryDate;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 产品类型
	 */
	private String productType;
	/**
	 * 产品期限
	 */
	private String productTerm;

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

	public String getRedName() {
		return redName;
	}

	public void setRedName(String redName) {
		this.redName = redName;
	}

	public BigDecimal getRedAmount() {
		return redAmount;
	}

	public void setRedAmount(BigDecimal redAmount) {
		this.redAmount = redAmount;
	}

	public BigDecimal getLowMoney() {
		return lowMoney;
	}

	public void setLowMoney(BigDecimal lowMoney) {
		this.lowMoney = lowMoney;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
		return "RedPacket [OID=" + OID + ", redName=" + redName + ", redAmount=" + redAmount + ", lowMoney=" + lowMoney
				+ ", expiryDate=" + expiryDate + ", userType=" + userType + ", productType=" + productType
				+ ", productTerm=" + productTerm + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID="
				+ addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime
				+ ", updDateTime=" + updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
	}

}
