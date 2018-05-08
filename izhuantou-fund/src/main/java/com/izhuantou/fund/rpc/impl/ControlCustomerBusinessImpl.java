package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.fund.rpc.api.ControlCustomerBusiness;
@Service("controlCustomerBusiness")
public class ControlCustomerBusinessImpl extends BaseServiceImpl<PayCustomerBusiness> implements ControlCustomerBusiness{
	@Autowired
	private PayCustomerBusinessMapper customerBusinessMapper;
	
	@Autowired
	private PayCustomerMapper payCustomerMapper;
	
	@Override
	public Integer saveCustomerBusiness(String type, BigDecimal money, String strContent, String strMemberOID) {
		 // 根据用户账号查询富有信息
		try{
			PayCustomer payCustomer = payCustomerMapper.findByMemberOID(strMemberOID);
			if(payCustomer != null){
				PayCustomerBusiness dtoCustomerBusiness = new PayCustomerBusiness();
				  dtoCustomerBusiness.setContent(strContent);
			      dtoCustomerBusiness.setMoney(money);
			      dtoCustomerBusiness.setState("已完成");
			      dtoCustomerBusiness.setType(type);
			      dtoCustomerBusiness.setMemberOID(strMemberOID);
			      dtoCustomerBusiness.setCustomerOID(payCustomer.getOID());
			      dtoCustomerBusiness.setOID(StringUtil.getUUID());
			      customerBusinessMapper.insertCustomerBussiness(dtoCustomerBusiness);
				return 0;
			}else{
				return 1;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return -1;
		}
		
		
	}

}
