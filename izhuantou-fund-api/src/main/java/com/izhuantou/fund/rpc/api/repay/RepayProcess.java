package com.izhuantou.fund.rpc.api.repay;

import java.math.BigDecimal;

public interface RepayProcess {
	/**
	 * 还款计划的OID和逾期罚息
	 * @param planOID
	 * @param yqfx
	 */
	String normalRepay(String planOID,BigDecimal yqfx);
	 
	/**
	 * 提前还款
	 */
	String beforeRepay(String businessOID);
	/**
	 * 逾期还款
	 */
	String afterRepay(String planOID);
}
