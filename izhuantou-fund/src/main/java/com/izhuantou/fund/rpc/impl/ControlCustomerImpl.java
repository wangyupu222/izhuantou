package com.izhuantou.fund.rpc.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.fund.rpc.api.ControlCustomer;

@Service("controlCustomer")
public class ControlCustomerImpl extends BaseServiceImpl<PayCustomer> implements ControlCustomer {

    @Override
    public PayCustomer gainCustomerByMemberOID(String memberOID) {
	try {
	    PayCustomer customer = new PayCustomer();
	    customer.setMemberOID(memberOID);
	    return this.queryOne(customer);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<PayCustomer> gainCustomersByMemberOID(String memberOID) {
	try {
	    PayCustomer customer = new PayCustomer();
	    customer.setMemberOID(memberOID);
	    return this.queryListByWhere(customer);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
