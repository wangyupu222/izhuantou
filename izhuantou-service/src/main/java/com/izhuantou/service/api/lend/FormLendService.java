package com.izhuantou.service.api.lend;

import com.izhuantou.damain.p2p.P2pPageLoan;
import com.izhuantou.damain.vo.FromLendInfo;

public interface FormLendService {
    /** 把前台接受到的实体插入到数据库中 */
    public String updFrom(FromLendInfo lendInfo);

    public P2pPageLoan findByOID(String OID);
}
