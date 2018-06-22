package com.izhuantou.dao.pay;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayCustomerBusiness;

/**
 * 账户业务记录表个人中心-资金流水
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PayCustomerBusinessMapper {

	/**
	 * 查找
	 * 
	 * @param customer
	 * @return
	 */
	public Integer findByMemberOID(String memberOID);

	/**
	 * 添加一条成功的记录
	 * 
	 * @param customerBusiness
	 * @return
	 */
	public int insertCustomerBussiness(PayCustomerBusiness customerBusiness);

	/**
	 * 根据会员OID获取客提现记录
	 */
	public int countByMemberOID(String memberOID);

	/**
	 * 根据会员OID获取投资总额
	 * 
	 * @param oid
	 * @return
	 */
	public BigDecimal countTzMoney(String oid);
	
	/**
	 * 根据会员OID获取投资次数
	 * @param oid
	 */
	public Integer countTzNumber(String oid);

}
