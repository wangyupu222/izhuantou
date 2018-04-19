package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import java.math.BigDecimal;
import java.util.Map;

import com.izhuantou.damain.mobile.personalCenter.MobilePropertyTJDTO;

public interface MobilePropertyService {
    /**
     * 根据memberOID查看个人资产详情
     * 
     * @param memberOID
     * @return
     */
    public MobilePropertyTJDTO findIndividualProperty(String memberOID);

    /**
     * 查看我的页面三个数据
     * 
     * @param memberOID
     * @return
     */
    public MobilePropertyTJDTO findIndexProperty(String memberOID);

    /**
     * 用户充值
     * 
     * @param recharge
     * @return
     */
    public Map<String, Object> AppRecharge(String memberOID, BigDecimal money);

    /**
     * App端用户提现
     * 
     * @param withdrawal
     * @return
     */
    public Map<String, Object> Appwithdrawal(String memberOID, BigDecimal money);

}
