package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.HHTZZInfo;

/** 环环投债转信息 */
public interface HHTZZInfoService {

    public HHTZZInfo findByOID(String OID);

    /** 以租代购 */
    public HHTZZInfo findYZDG(String OID);
}
