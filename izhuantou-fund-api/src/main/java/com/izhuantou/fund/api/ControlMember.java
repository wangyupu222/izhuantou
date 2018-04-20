package com.izhuantou.fund.api;

import com.izhuantou.damain.user.MemberMember;

public interface ControlMember extends BaseService<MemberMember> {

    /**
     * 根据用户账号查询用户信息
     * 
     * @param memberOID
     * @return
     */
    public MemberMember findMemberByOID(String memberOID);

    /**
     * test
     */
    public String queryDate();
}
