package com.izhuantou.fund.rpc.api;

import java.util.List;

import com.izhuantou.damain.pay.PayReturnPlan;

public interface ControlReturnPlan extends BaseService<PayReturnPlan> {

    /**
     * 获取回款计划
     * 
     * @param debitCreditOID
     * @return
     */
    List<PayReturnPlan> queryByreturnDate(String debitCreditOID);

    /**
     * 
     * @param debitCreditOID
     * @param string
     * @return
     */
    List<PayReturnPlan> gainReturnPlanByDebitCreditOIDAndState(String debitCreditOID, String string);

    /**
     * 
     * @param string
     * @return
     */
    List<PayReturnPlan> gainReturnPlanByDebitCreditOID(String string);

    /**
     * 
     * @param strDebitCreditOID
     * @param strState
     * @return
     */
    PayReturnPlan gainMINByDebitCreditOIDAndState(String strDebitCreditOID, String strState);

}
