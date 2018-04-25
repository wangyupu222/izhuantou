package com.izhuantou.fund.rpc.impl;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.fund.rpc.api.ControlMember;

@Service("controlMember")
public class ControlMemberImpl extends BaseServiceImpl<MemberMember> implements ControlMember {
    /** 根据条件查询用户信息 */
    @Override
    public MemberMember findMemberByOID(String memberOID) {
	try {
	    MemberMember member = new MemberMember();
	    member.setOID(memberOID);
	    return this.queryOne(member);
	} catch (Exception e) {
	    return null;
	}

    }

    @Override
    public String queryDate() {

	return "10:00";
    }

}
