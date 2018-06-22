package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 会员行为轨迹(某个用户的详情)
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
	/**
	 * 操作记录
	 */
	private Map<String, List<String>> operatingRecord;

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

	public Map<String, List<String>> getOperatingRecord() {
		return operatingRecord;
	}

	public void setOperatingRecord(Map<String, List<String>> operatingRecord) {
		this.operatingRecord = operatingRecord;
	}

}
