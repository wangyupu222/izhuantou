package com.izhuantou.service.api.personalCenter;

import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.pay.PayCustomerBusiness;

/**
 * 资金流水
 * 
 * @author yangbosen
 *
 */
public interface MyCashService {

    /**
     * 资金流水列表
     * 
     * @param page
     * @param memberOID
     * @param strDate
     * @param endDate
     * @return
     */
    public Pagination<PayCustomerBusiness> findMessage(Integer page, String memberOID, String strDate, String endDate);

    /**
     * 查询银行卡更改信息列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findMyBanckCard(String memberOID, Integer currentPage);

    /**
     * 银行卡信息
     * 
     * @param member
     * @return
     */
    public Map<String, Object> findMyBanckMessage(String memberOID);

    /**
     * 查询银行卡充值记录充值
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Map<String, Object> findRechargeRecord(String memberOID, String state, Integer currentPage);


    /**
     * 充值页面或提现页面数据
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findMyCardmessage(String memberOID);

}
