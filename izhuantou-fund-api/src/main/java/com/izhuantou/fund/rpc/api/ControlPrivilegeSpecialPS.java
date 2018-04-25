package com.izhuantou.fund.rpc.api;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayPrivilegeSpecialps;

public interface ControlPrivilegeSpecialPS extends BaseService<PayPrivilegeSpecialps> {

    /**
     * 根据用户的出借进行，保存特殊加息券的相关配置信息 保存状态和时间，用于后续发券
     * 
     * @param memberOID
     * @param amount
     * @param biddingOID
     */
    void savePrivilegePs(String memberOID, BigDecimal amount, String biddingOID);

}
