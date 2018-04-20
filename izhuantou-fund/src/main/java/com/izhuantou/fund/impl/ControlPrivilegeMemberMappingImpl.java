package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.fund.api.ControlPrivilegeMemberMapping;

@Service("controlPrivilegeMemberMapping")
public class ControlPrivilegeMemberMappingImpl extends BaseServiceImpl<PayPrivilegeMemberMapping>
	implements ControlPrivilegeMemberMapping {
    @Autowired
    private PayPrivilegeMemberMappingMapper privilegeMemberMappingMapper;

    @Override
    public List<PayPrivilegeMemberMapping> gainByTqOIDAndMemebrOID(String TqOID, String MemberOID) {
	try {
	    PayPrivilegeMemberMapping mapping = new PayPrivilegeMemberMapping();
	    mapping.setPrivilegeOID(TqOID);
	    mapping.setMemberOID(MemberOID);
	    return privilegeMemberMappingMapper.findByCondition(TqOID, MemberOID);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
