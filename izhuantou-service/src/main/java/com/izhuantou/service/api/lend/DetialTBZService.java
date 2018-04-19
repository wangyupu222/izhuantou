package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.DetialTBZ;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.service.api.BaseService;

public interface DetialTBZService extends BaseService<PayCustomer> {
    /** 头笔赚标的详情 */
    public DetialTBZ findByCondition(String OID, String memberOID);
}
