package com.izhuantou.service.api.user;

import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;

public interface MemberAgreementService {

    /**
     * 查看协议
     * 
     * @return
     */
    public MemberMemberAgreement gainAgreement(MemberAgrennmentDTO memberAgrennmentDTO);

    /**
     * 查看协议模板
     * 
     * @return
     */
    public MemberAgreement gainMemberAgreement(String type);

    /**
     * 注册协议生成 name注册用户时的手机号contractType协议类型
     * 
     * @param name
     * @param contractType
     * @param memberAccount
     */
    public String gainupdateMemberAgreement(String name, String contractType, String memberAccount);

}
