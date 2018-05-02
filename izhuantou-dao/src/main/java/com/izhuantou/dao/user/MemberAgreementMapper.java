package com.izhuantou.dao.user;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberAgreement;

/**
 * 协议模板
 * 
 * @author dear
 * @version 1.0
 */
public interface MemberAgreementMapper extends Mapper<MemberAgreement> {

    /**
     * 查看协议模板
     * 
     * @param contractType
     * @return
     */
    public MemberAgreement findMemberAgreementByType(String contractType);

}
