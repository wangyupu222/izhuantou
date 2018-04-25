package com.izhuantou.fund.rpc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.fund.rpc.api.ControlReturnPlan;

@Service("controlReturnPlan")
public class ControlReturnPlanImpl extends BaseServiceImpl<PayReturnPlan> implements ControlReturnPlan {

    @Autowired
    private PayReturnPlanMapper payReturnPlanDao;

    @Override
    public List<PayReturnPlan> queryByreturnDate(String debitCreditOID) {

	return this.payReturnPlanDao.queryByreturnDate(debitCreditOID);
    }

    @Override
    public List<PayReturnPlan> gainReturnPlanByDebitCreditOIDAndState(String debitCreditOID, String state) {
	PayReturnPlan payReturnPlan = new PayReturnPlan();
	payReturnPlan.setDebitCreditOID(debitCreditOID);
	payReturnPlan.setState(state);
	try {
	    return this.queryListByWhere(payReturnPlan);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<PayReturnPlan> gainReturnPlanByDebitCreditOID(String strDebitCreditOID) {
	try {
	    PayReturnPlan payReturnPlan = new PayReturnPlan();
	    payReturnPlan.setDebitCreditOID(strDebitCreditOID);
	    return this.queryListByWhere(payReturnPlan);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public PayReturnPlan gainMINByDebitCreditOIDAndState(String strDebitCreditOID, String strState) {
	try {
	    return this.payReturnPlanDao.findMINByDebitCreditOIDAndState(strDebitCreditOID, strState);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
