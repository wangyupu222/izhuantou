package com.izhuantou.dao.lend;

public interface GainNumXSMapper {
    /** 通过OID查询是否进行过出借 */
    public Integer findByMemberOID(String memberOID);
}
