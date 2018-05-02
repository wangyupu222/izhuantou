package com.izhuantou.third.rpc.api;

import java.math.BigDecimal;
import java.util.Map;

import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.Wy500012ReqData;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.FuyuReturnDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;

public interface ControlPayService {

    /**
     * 商户P2P网站免登录用户更换银行卡接口
     * 
     * @param strSellerName
     * @param strMemberOID
     * @param strRequestID
     *            FuiouService.changeCard2(data, response);
     */
    Map<String, Object> changeCard(String strMemberOID);

    /**
     * 更换银行卡回调
     * 
     * @param map
     * @return
     */
    String changeBankCardReturn(Map<String, String> map);

    /**
     * 用户开户注册 富友
     * 
     * @param customerDto
     */
    String createCustomerAccount(CustomerDTO customerDto);

    /**
     * 授权回调
     * 
     * @param map
     */
    String authorizationFinish(FuyuReturnDTO result);

    /**
     * 充值成功回调
     * 
     * @param map
     */
    String rechargeFinish(Map<String, String> map);

    /**
     * 提现回调
     * 
     * @param map
     */
    String withdrawalsFinish(Map<String, String> map);

    /**
     * 商户P2P网站免登录提现接口
     * 
     * @param withdrawal
     */
    Map<String, Object> withdrawals(WithdrawalDTO withdrawal);

    /**
     * 商户APP个人用户免登录提现
     * 
     * @param recharge
     * @return
     */
    Map<String, Object> appWithdrawals(String memberOID, BigDecimal money);

    /**
     * 查询银行卡更换结果
     * 
     * @param strMemberOID
     * @param requestID
     */
    void queryChangeCard(String strMemberOID, String requestID);

    /**
     * 划拨 (个人与个人之间)
     * 
     * @param strSellerName
     * @param strOutMemberOID
     * @param strInMemberOID
     * @param money
     */
    String transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money);

    /**
     * 划拨预冻结
     * 
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    String transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 解冻（12，13，14的冻结资金解冻）
     * 
     * @param memberOID
     * @param money
     */
    String unFreeze(String memberOID, BigDecimal money);

    /**
     * 冻结
     * 
     * @param memberOID
     * @param money
     */
    String freeze(String memberOID, BigDecimal money);

    /**
     * 余额查询
     * 
     * @param strSellerName
     * @param outMemberOID
     * @return
     */
    Map<String, Object> BalanceAction(String outMemberOID);

    /**
     * 同步资金账户 富友余额查询
     * 
     * @param strMemberOID
     */
    void updateAccount(String strMemberOID);

    /**
     * PC端个人用户免登录快捷充值
     * 
     * @param recharge
     * @return
     */
    AppTransReqData recharge(RechargeDTO recharge);

    /**
     * P2P免登录直接跳转网银界面充值接口
     * 
     * @param recharge
     * @return
     */
    Wy500012ReqData rechargeWY(RechargeDTO recharge);

    /**
     * 用户密码修改重置免登陆接口(网页版)
     * 
     * @param memberOID
     * @return
     */
    ResetPassWordReqData updatePayPassword(String memberOID);

    /**
     * 提现手续费接口
     * 
     * @param map
     * @return
     */
    String withdrawalsSxfFinish(Map<String, String> map);

    /**
     * PC金账户免登陆授权配置（短信通知+委托交易）
     * 
     * @param strMemberOID
     */
    AuthorizationReqData authorization(String strMemberOID);

    /**
     * 商户APP个人用户免登录快捷充值
     * 
     * @param recharge
     * @return
     */
    AppTransReqData appRecharge(String memberOID, BigDecimal money);

    /**
     * 用户密码修改重置免登陆接口(app版)
     * 
     * @param memberOID
     * @return
     */
    ResetPassWordReqData appUpdatePayPassword(String memberOID);

    /**
     * 划拨冻结到冻结
     * 
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    String transferFreezeToFreeze(String outMemberOID, String inMemberOID, BigDecimal money);

}
