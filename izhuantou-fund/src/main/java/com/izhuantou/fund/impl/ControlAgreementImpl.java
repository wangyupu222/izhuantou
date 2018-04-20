package com.izhuantou.fund.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.fund.api.ControlAgreement;

@Service("controlAgreement")
public class ControlAgreementImpl extends BaseServiceImpl<MemberAgreement> implements ControlAgreement {

    @Override
    public List<MemberAgreement> findBycontractType(String contractType) {
	try {
	    MemberAgreement memberAgreement = new MemberAgreement();
	    memberAgreement.setContractType(contractType);
	    return this.queryListByWhere(memberAgreement);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

}
