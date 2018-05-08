package com.izhuantou.data.rpc.api.bidding;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.data.rpc.api.BaseService;

public interface ControlCustomerBusiness extends BaseService<PayCustomerBusiness> {
	
	/**
	 * 增加贴息
	 * 
	 * @param string
	 * @param ydzh
	 * @param string2
	 * @param inMemberOID
	 */
	public Integer  saveCustomerBusiness(String type, BigDecimal money, String strContent,String strMemberOID);

}
