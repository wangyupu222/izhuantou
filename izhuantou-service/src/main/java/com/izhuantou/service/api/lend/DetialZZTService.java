package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.DetialZZT;

public interface DetialZZTService {
    /** 通过OID查询转转投信息 */
    public DetialZZT findByCondition(String OID, String memberOID);
}
