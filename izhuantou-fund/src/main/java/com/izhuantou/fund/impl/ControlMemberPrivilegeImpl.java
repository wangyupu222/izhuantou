package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.user.MemberMemberPrivilege;
import com.izhuantou.fund.api.ControlMemberPrivilege;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("controlMemberPrivilege")
public class ControlMemberPrivilegeImpl extends BaseServiceImpl<MemberMemberPrivilege>
	implements ControlMemberPrivilege {

    @Override
    public List<MemberMemberPrivilege> findByOIDAndsatate(String OID) {
	MemberMemberPrivilege member_MemberPrivilege = new MemberMemberPrivilege();
	member_MemberPrivilege.setMemberOID(OID);
	member_MemberPrivilege.setState("0");
	List<MemberMemberPrivilege> list = this.queryListByWhere(member_MemberPrivilege);
	if (list != null && list.size() > 0) {
	    return list;
	} else {
	    return null;
	}

    }

}
