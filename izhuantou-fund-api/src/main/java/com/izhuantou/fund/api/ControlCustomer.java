package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.pay.PayCustomer;

public interface ControlCustomer extends BaseService<PayCustomer> {

    /**
     * 根据用户账号查询富有信息
     * 
     * @param memberOID
     * @return
     */
    public PayCustomer gainCustomerByMemberOID(String memberOID);

    public List<PayCustomer> gainCustomersByMemberOID(String memberOID);

}
