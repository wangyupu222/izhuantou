package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayPrivilegeProductMapping;

/**
 * 特权红包加息与产品配置信息关系映射
 * 
 * @author dear
 * @version 1.0
 */
public interface PayPrivilegeProductMappingMapper extends Mapper<PayPrivilegeProductMapping> {
    /** 根据OID查找特权信息 */
    public List<PayPrivilegeProductMapping> findByOID(String OID);

    /**
     * 根据特权OID和特权状态查看 特权天数
     * 
     * @param tqOID
     * @param pudcOID
     * @return
     */
    public PayPrivilegeProductMapping findDayByTqPudcOID(@Param("tqOID") String tqOID,
	    @Param("pudcOID") String pudcOID);

}
