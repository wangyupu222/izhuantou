package com.izhuantou.dao.pay;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayPrivilege;

/**
 * 特权信息
 * 
 * @author dear
 * @version 1.0
 */
public interface PayPrivilegeMapper extends Mapper<PayPrivilege> {
    /** 根据OID查找特权信息 */
    public PayPrivilege findByOID(String OID);
}
