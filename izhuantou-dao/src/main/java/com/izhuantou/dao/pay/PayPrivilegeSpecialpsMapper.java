package com.izhuantou.dao.pay;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;

/**
 * 回款触发加息券 特殊加息券补充说明
 * 
 * @author dear
 * @version 1.0
 */
public interface PayPrivilegeSpecialpsMapper extends Mapper<PayPrivilegeSpecialps> {
    public PayPrivilegeSpecialps findByOID(String OID);

    /**
     * 更新
     * 
     * @param privilegeSpecialPS
     * @return
     */
    public int updatePrivilegeSpecialPS(PayPrivilegeSpecialps privilegeSpecialPS);
    /**
     * 保存
     * @param privilegeSpecialPS
     * @return
     */
    public int savePrivilegeSpecialPS(PayPrivilegeSpecialps privilegeSpecialPS);
}
