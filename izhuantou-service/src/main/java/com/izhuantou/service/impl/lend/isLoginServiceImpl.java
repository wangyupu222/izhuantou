package com.izhuantou.service.impl.lend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.service.api.lend.isLoginService;

/**
 * 判断登录
 * 
 * @author Administrator
 *
 */
@Service("isLoginService")
public class isLoginServiceImpl implements isLoginService {

    private static final Logger logger = LoggerFactory.getLogger(isLoginServiceImpl.class);

    @Autowired
    private PayCustomerMapper customerMapper;

    @Override
    public String islogin(String memberOID) {
	PayCustomer customer = new PayCustomer();
	try {
	    if (memberOID != null) {
		customer = customerMapper.findByMemberOID(memberOID);
	    } else {
		return "0.00";
	    }
	    String useMoney = customer.getUseMoney().toString();
	    return useMoney;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("islogin()", e.getMessage());
	    return null;

	}
    }

}
