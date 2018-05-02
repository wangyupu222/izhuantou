package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayRepayPlan;

/**
 * 借款人还款计划
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PayRepayPlanMapper extends Mapper<PayRepayPlan> {

    public List<PayRepayPlan> findByBusinessOID(String OID);

    public PayRepayPlan queryByOID(String OID);

    public List<PayRepayPlan> queryByBusinessOIDAndState(@Param("OID") String OID, @Param("state") String state);

    public PayRepayPlan queryByBusinessOIDAndStateMAX(@Param("businessOID") String businessOID,
	    @Param("state") String state);

    /**
     * 保存还款计划
     * 
     * @param repayPlan
     * @return
     */
    public int saveRepayPlan(PayRepayPlan repayPlan);

    /**
     * 根据用户OID和产品OId查询计划
     * 
     * @param memberOID
     * @param businessOID
     * @return
     */
    public List<PayRepayPlan> findByBusinessOIDAndMemberOID(@Param("memberOID") String memberOID,
	    @Param("businessOID") String businessOID);

}
