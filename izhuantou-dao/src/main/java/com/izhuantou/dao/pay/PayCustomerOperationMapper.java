package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayCustomerOperation;

/**
 * 用户账户操作记录
 * 
 * @author dear
 * @version 1.0
 */
public interface PayCustomerOperationMapper extends Mapper<PayCustomerOperation> {

    /**
     * 查询用户充值或提现记录
     * 
     * @return
     */
    public List<PayCustomerOperation> findRechargeRecord(@Param("memberOID") String memberOID,
	    @Param("state") String state, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查询总条数
     * 
     * @return
     */
    int getRowCount(@Param("memberOID") String memberOID, @Param("state") String state);

    /**
     * 插入一条记录
     * 
     * @param operation
     * @return
     */
    public int insertRechargeRecord(PayCustomerOperation operation);

    /**
     * 查询用户充值或提现记录
     * 
     * @return
     */
    public List<PayCustomerOperation> findAppRechargeRecord(@Param("memberOID") String memberOID,
	    @Param("state") String state);

}
