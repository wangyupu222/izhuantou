package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.fund.api.ControlRepayPlan;

@Service("controlRepayPlan")
public class ControlRepayPlanImpl extends BaseServiceImpl<PayRepayPlan> implements ControlRepayPlan {

    @Override
    public List<PayRepayPlan> findRepayPlan(String memberOID, String businessOID) {
	try {
	    PayRepayPlan pay_RepayPlan = new PayRepayPlan();
	    pay_RepayPlan.setBusinessOID(businessOID);
	    pay_RepayPlan.setMemberOID(memberOID);
	    return this.queryListByWhere(pay_RepayPlan);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
