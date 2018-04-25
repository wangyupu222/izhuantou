package com.izhuantou.fund.rpc.api;

import java.util.List;

import com.izhuantou.damain.pay.PaySeller;

public interface ControlSeller extends BaseService<PaySeller> {

    /**
     * 
     * @param defaultSeller
     * @return
     */
    List<PaySeller> gainSellerByName(String strSellerName);

}
