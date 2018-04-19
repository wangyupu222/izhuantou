package com.izhuantou.service.impl.mobile;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.service.api.mobile.AvailablemoneyService;

@Service("availablemoneyService")
public class AvailablemoneyServiceImpl implements AvailablemoneyService {

    @Autowired
    private PayCustomerMapper customerMapper;

    @Override
    public BigDecimal countAvailableMoney(String memberOID) {
	PayCustomer customer = new PayCustomer();
	try {
	    customer = customerMapper.findByMemberOID(memberOID);
	    BigDecimal money = customer.getMoney();
	    BigDecimal frozenMoney = customer.getFrozenMoney();
	    BigDecimal availablemoney = new BigDecimal("0");

	    if (money != null) {

		if (frozenMoney == null) {
		    frozenMoney = new BigDecimal(0.00);
		}
		availablemoney = money.subtract(frozenMoney);
	    }
	    return availablemoney;
	} catch (Exception e) {
	    // TODO: handle exception
	}

	return null;
    }

}
