package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.DetialDDT;

public interface DetialDDTService {
    /** 通过OID查询点点投信息 */
    public DetialDDT findByCondition(String OID, String memberOID);
}
