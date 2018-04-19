package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.PayTerm;

public interface PayTermMapper extends Mapper<PayTerm> {
    /** 通过BusinessOID查找期数 */
    public PayTerm findByBusinessOID(String OID);
}
