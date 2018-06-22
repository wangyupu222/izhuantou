package com.izhuantou.third.rpc.api.memberagrement;

import java.util.Map;

import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;
import com.izhuantou.third.rpc.api.BaseService;

public interface MemberMemberAgreementService extends BaseService<MemberMemberAgreement> {

    /**
     * 1.查看协议
     * 
     * @return
     */
    MemberMemberAgreement gainAgreement(MemberAgrennmentDTO memberAgrennmentDTO);

    /**
     * 2.查看协议模板
     * 
     * @return
     */
    MemberAgreement gainMemberAgreement(String type);

    /**
     * 3.注册协议生成 name注册用户时的手机号contractType协议类型
     * 
     * @param name
     * @param contractType
     * @param memberAccount
     */
    String gainupdateMemberAgreement(String name, String contractType);

    /**
     * 新手投服务协议
     * 
     * @param memberOID
     * @param contractType
     * @param biddingOID
     * @param ckOID
     */
    void gainMemberXSAgreement(String memberOID, String contractType, String biddingOID, String ckOID);

    /**
     * 新手标满标后生成借款协议
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainMemberxsJKAgreement(String contractType, String biddingOID);

    /**
     * 砖头网新手标债权预回购协议
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberzqhgXSAgreement(String contractType, String biddingOID);

    /**
     * 财务标的满标后生成债权转让协议 出借人购买债转后的头笔赚生成债权转让协议
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberTbzZZTZQZRAgreement(String contractType, String biddingOID);

    /**
     * 头笔赚到期财务手动债转--债权预回购协议
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberxszqhgZZTAgreement(String contractType, String biddingOID);

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
     * 环环投逾期债权预回购协议--区分以租贷购
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID);

    /**
     * 环环投（债转后）出借人购买
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID);

    /**
     * 环环投逾期债权预回购协议--区分以租贷购
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainmemberzqhgHHTAgreement(String contractType, String biddingOID);

    /**
     * 环环投内容表满标借款协议签订--区分已租贷购
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID);

    /**
     * 环环投内容表满标借款协议签订
     * 
     * @param contractType
     * @param biddingOID
     */
    void gainMemberHHTNRBJKAgreement(String contractType, String biddingOID);

}
