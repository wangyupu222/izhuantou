package com.izhuantou.fund.rpc.api;

import com.izhuantou.damain.user.MemberAgreement;

/**
 * 投标相关接口接口 协议类接口
 *
 * @author fucheng
 * @date 2018-03-15
 */

public interface ControlMemberAgreement extends BaseService<MemberAgreement> {

    /**
     * 环环投服务协议
     * 
     * @param memberOID
     * @param contractType
     * @param biddingOID
     * @param ckOID
     */
    void gainMemberHHTAgreement(String memberOID, String contractType, String biddingOID, String ckOID);

    /**
     * 环环投内容表满标借款协议签订--区分已租贷购
     * 
     * @param string
     * @param realBiddingOID
     */
    void gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID);

    /**
     * 环环投逾期债权预回购协议--区分以租贷购
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID);

    /**
     * 环环投内容表满标借款协议签订
     * 
     * @param string
     * @param realBiddingOID
     */
    void gainMemberHHTNRBJKAgreement(String contractType, String biddingOID);

    /**
     * 环环投逾期债权预回购协议
     * 
     * @param string
     * @param realBiddingOID
     */
    void gainmemberzqhgHHTAgreement(String string, String realBiddingOID);

    void gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID);

    /**
     * 新手投服务协议
     * 
     * @param memberOID
     * @param contractType
     * @param biddingOID
     * @param ckOID
     */
    void gainMemberXSAgreement(String memberOID, String contractType, String biddingOID, String ckOID);

}
