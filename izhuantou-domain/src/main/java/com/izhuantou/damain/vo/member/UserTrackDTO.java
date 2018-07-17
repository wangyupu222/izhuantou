package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户详情（行为轨迹）
 * 
 * @author sweet
 * @date 2018年6月13日
 *
 */
public class UserTrackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582002395065127351L;
	/**
	 * 投资时间 
	 */
	private Date addDateTime;
	/**
	 * 投资产品名称
	 */
	private String productName;
	/**
	 * 投资产品ID
	 */
	private String productOID;
	/**
	 * 投资金额
	 */
	private BigDecimal money;

	public Date getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(Date addDateTime) {
		this.addDateTime = addDateTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductOID() {
		return productOID;
	}

	public void setProductOID(String productOID) {
		this.productOID = productOID;
	}
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "UserTrackDTO [addDateTime=" + addDateTime + ", productName=" + productName + ", productOID="
				+ productOID + ", money=" + money + "]";
	}
	
	

}
