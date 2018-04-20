package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PaySeller;
import com.izhuantou.fund.api.ControlSeller;

@Service("controlSeller")
public class ControlSellerImpl extends BaseServiceImpl<PaySeller> implements ControlSeller {

    @Override
    public List<PaySeller> gainSellerByName(String strSellerName) {
	try {
	    PaySeller paySeller = new PaySeller();
	    paySeller.setName(strSellerName);
	    return this.queryListByWhere(paySeller);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
