package com.izhuantou.service.api.personalCenter;

import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;

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
     * 注册富友
     * 
     * @param customer
     * @return
     */
    public String insertCustomer(CustomerDTO customerDto);

    /**
     * 充值页面或提现页面数据
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findMyCardmessage(String memberOID);

    /**
     * 用户充值
     * 
     * @param recharge
     * @return
     */
    public Map<String, Object> rechargeWY(RechargeDTO recharge);

    /**
     * 
     * @param recharge
     * @return
     */
    public Map<String, Object> recharge(RechargeDTO recharge);

    /**
     * 通过富友返回的信息去增加记录
     * 
     * @param data
     * @return
     */
    public String rechargeFinish(Map<String, String> data);

    /**
     * 通过memberOID改变数据库
     * 
     * @param memberOID
     * @return
     */
    public String updateCustomerMoney(String phone);

    /**
     * 富友提现
     * 
     * @return
     */
    public Map<String, Object> withdrawal(WithdrawalDTO withdrawal);

    /**
     * 提现返回
     * 
     * @param map
     * @return
     */
    public String withdrawalsFinish(Map<String, String> map);

    /**
     * 提现手续费接口
     * 
     * @param map
     * @return
     */
    public String withdrawalsSxfFinish(Map<String, String> map);

    /**
     * 修改提现密码
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> updatePayPassword(String memberOID);

    /**
     * 申请换卡
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> changeBankCard(String memberOID);

    /**
     * 申请换卡回调
     * 
     * @return
     */
    public String changeBankCardReturn(Map<String, String> map);

    /**
     * PC金账户授权配置
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> authorization(String memberOID);

    /**
     * 授权回调
     * 
     * @param map
     * @return
     */
    public String authorizationCallback(Map<String, String> map);

}
