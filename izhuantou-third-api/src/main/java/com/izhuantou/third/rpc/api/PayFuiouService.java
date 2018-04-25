package com.izhuantou.third.rpc.api;

import java.math.BigDecimal;

import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ChangeCard2ReqData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.data.QueryChangeCardRspData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.UnFreezeRspData;
import com.fuiou.data.Wy500012ReqData;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;

public interface PayFuiouService {

    /**
     * 商户P2P网站免登录用户更换银行卡接口
     * 
     * @param strSellerName
     * @param strMemberOID
     * @param strRequestID
     *            FuiouService.changeCard2(data, response);
     */
    ChangeCard2ReqData changeCard(String strMemberOID, String strRequestID);

    /**
     * 用户开户注册 富友
     * 
     * @param customerDto
     */
    String createCustomerAccount(PayCustomer payCustomer);

    /**
     * 同步资金账户 富友余额查询
     * 
     * @param strMemberOID
     */
    QueryBalanceRspData updateAccount(String strMemberOID);

    /**
     * PC金账户免登陆授权配置（短信通知+委托交易）
     * 
     * @param strMemberOID
     */
    AuthorizationReqData authorization(String strMemberOID);

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
     * 商户P2P网站免登录提现接口
     * 
     * @param withdrawal
     */
    AppTransReqData withdrawals(WithdrawalDTO withdrawal);

    /**
     * 用户密码修改重置免登陆接口(网页版)
     * 
     * @param memberOID
     * @return
     */
    ResetPassWordReqData updatePayPassword(String memberOID);

    /**
     * 商户APP个人用户免登录快捷充值
     * 
     * @param recharge
     * @return
     */
    AppTransReqData appRecharge(String memberOID, BigDecimal money);

    /**
     * 商户APP个人用户免登录提现
     * 
     * @param recharge
     * @return
     */
    AppTransReqData appWithdrawals(WithdrawalDTO withdrawal);

    /**
     * 用户密码修改重置免登陆接口(app版)
     * 
     * @param memberOID
     * @return
     */
    ResetPassWordReqData appUpdatePayPassword(String memberOID);

    /**
     * 划拨 (个人与个人之间)
     * 
     * @param strSellerName
     * @param strOutMemberOID
     * @param strInMemberOID
     * @param money
     */
    // TODO 此方法和下边的方法一致， 没有存表，不知什么意思
    // void transferMember(String strSellerName, String strOutMemberOID, String
    // strInMemberOID, BigDecimal money);

    /**
     * 划拨 (个人与个人之间)
     * 
     * @param strSellerName
     * @param strOutMemberOID
     * @param strInMemberOID
     * @param money
     */
    CommonRspData transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money);

    /**
     * 划拨预冻结
     * 
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    CommonRspData transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 冻结到冻结接口
     * 
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    UnFreezeRspData transferFreezeToFreeze(String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 解冻（12，13，14的冻结资金解冻）
     * 
     * @param memberOID
     * @param money
     */
    UnFreezeRspData unFreeze(String memberOID, BigDecimal money);

    /**
     * 冻结
     * 
     * @param memberOID
     * @param money
     */
    CommonRspData freeze(String memberOID, BigDecimal money);

    /**
     * 余额查询
     * 
     * @param strSellerName
     * @param outMemberOID
     * @return
     */
    QueryBalanceRspData BalanceAction(String outMemberOID);

    /**
     * 查询银行卡更换结果
     * 
     * @param strMemberOID
     * @param requestID
     */
    QueryChangeCardRspData queryChangeCard(String strMemberOID, String requestID);

}
