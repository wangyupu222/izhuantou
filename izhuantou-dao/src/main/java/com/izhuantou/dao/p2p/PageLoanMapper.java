package com.izhuantou.dao.p2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageLoan;

/**
 * 平台贷款产品信息表（i薪贷、i抵押、i房贷、i保单、i意贷）
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PageLoanMapper extends Mapper<P2pPageLoan> {
    /**
     * 根据OID查询信息
     * 
     * @param OID
     * @return
     */
    public P2pPageLoan findPageByOID(String OID);

}
