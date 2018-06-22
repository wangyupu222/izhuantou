package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 客户列表4个数展示
 * 
 * @author sweet
 * @date 2018年6月22日
 *
 */
public class CustomerFourNumberDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7279107718880743502L;

	/**
	 * 注册客户数
	 */
	private Integer registerNumber;
	/**
	 * 实名客户数
	 */
	private Integer realNameNumber;
	/**
	 * 出借客户数
	 */
	private Integer lendNumber;
	/**
	 * 风险测评客户数
	 */
	private Integer riskNumber;

	public Integer getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Integer getRealNameNumber() {
		return realNameNumber;
	}

	public void setRealNameNumber(Integer realNameNumber) {
		this.realNameNumber = realNameNumber;
	}

	public Integer getLendNumber() {
		return lendNumber;
	}

	public void setLendNumber(Integer lendNumber) {
		this.lendNumber = lendNumber;
	}

	public Integer getRiskNumber() {
		return riskNumber;
	}

	public void setRiskNumber(Integer riskNumber) {
		this.riskNumber = riskNumber;
	}

}
