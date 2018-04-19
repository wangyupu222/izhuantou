package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.damain.lend.AuditInfo;
import com.izhuantou.service.api.BaseService;

public interface AuditInfoService extends BaseService<AuditInfo> {
    /** 头笔赚风控 */
    public List<AuditInfo> findByCondition(String OID);

    /** 环环投风控 */
    public List<AuditInfo> findByConditionHHT(String OID);

    /** 环环投车抵风控 */
    public List<AuditInfo> findByConditionHHTCD(String OID);

    /** 点点投风控 */
    public List<AuditInfo> findByConditionDDT(String OID);

    /** 转转投风控 */
    public List<AuditInfo> findByConditionZZT(String OID);
}
