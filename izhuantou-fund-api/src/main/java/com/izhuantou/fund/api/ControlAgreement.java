package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.user.MemberAgreement;

public interface ControlAgreement extends BaseService<MemberAgreement> {

    public List<MemberAgreement> findBycontractType(String contractType);

}
