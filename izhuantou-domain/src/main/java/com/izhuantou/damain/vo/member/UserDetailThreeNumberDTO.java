package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户详情(行为轨迹)三个数据统计
 * 
 * @author sweet
 * @date 2018年6月25日
 *
 */
public class UserDetailThreeNumberDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 770029066596274735L;

	/**
	 * 投资总额
	 */
	private String investmentMoney;
	/**
	 * 投资次数
	 */
	private Integer investmentNumber;
	/**
	 * 投资产品数
	 */
	private Integer investmentProductNumber;

	public String getInvestmentMoney() {
		return investmentMoney;
	}

	public void setInvestmentMoney(String investmentMoney) {
		this.investmentMoney = investmentMoney;
	}

	public Integer getInvestmentNumber() {
		return investmentNumber;
	}

	public void setInvestmentNumber(Integer investmentNumber) {
		this.investmentNumber = investmentNumber;
	}

	public Integer getInvestmentProductNumber() {
		return investmentProductNumber;
	}

	public void setInvestmentProductNumber(Integer investmentProductNumber) {
		this.investmentProductNumber = investmentProductNumber;
	}

}
