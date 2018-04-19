package com.izhuantou.dao.pay;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayCustomer;

/**
 * 身份认证账户信息表（实名认证之后的账户信息在该表中）
 * 
 * @author dear
 * @version 1.0
 */
public interface PayCustomerMapper extends Mapper<PayCustomer> {
    /**
     * 查询资金账户
     * 
     * @param MemberOID
     * @return
     */
    public PayCustomer findByMemberOID(String MemberOID);

    /**
     * 查看是否注册过资金帐户
     */
    public PayCustomer findPAYCustomerByNameAndOID(@Param("name") String name, @Param("OID") String OID);

    /**
     * 根据用户名查看账户
     */
    public PayCustomer findPAYCustomerByName(String name);

    /**
     * 注册富有资金账户
     * 
     * @param customer
     * @return
     */
    public int insertCustomer(PayCustomer customer);

    /**
     * 更新用户的余额
     */
    public int updataCutomer(PayCustomer customer);
}
