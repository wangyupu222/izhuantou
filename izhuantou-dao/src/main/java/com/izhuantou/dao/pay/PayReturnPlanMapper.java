package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.vo.JoinSelectAllDTO;

/**
 * 收益返回计划（出借人）
 * 
 * @author dear
 * @version 1.0
 */
public interface PayReturnPlanMapper extends Mapper<PayReturnPlan> {
    /**
     * 查询环环头投资的相关信息 只返回3个信息
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<JoinSelectAllDTO> gainReturnPlanByMemberOIDAndState(@Param("memberOID") String memberOID,
	    @Param("state") String state);

    /**
     * 查询环环 返回所有的信息
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<PayReturnPlan> findReturnPlanByMemberOIDAndState(@Param("businessOID") String businessOID,
	    @Param("state") String state);

    /**
     * 查询环环
     * 
     * @param debitCreditOID
     * @param state
     * @return
     */
    public PayReturnPlan findMINByDebitCreditOIDAndState(@Param("debitCreditOID") String debitCreditOID,
	    @Param("state") String state);

    /**
     * 查看所有信息
     * 
     * @param debitCreditOID
     * @return
     */
    public List<PayReturnPlan> queryByreturnDate(String debitCreditOID);

    /**
     * 保存回款计划
     * 
     * @param returnPlan
     * @return
     */
    public int saveReturnPlan(PayReturnPlan returnPlan);

    /**
     * 更新回款计划
     * 
     * @param returnPlan
     * @return
     */
    public int updateReturnPlan(PayReturnPlan returnPlan);

    /**
     * 
     * @param debitCreditOID
     * @return
     */
    public PayReturnPlan findByDebitCreditOIDAndState(@Param("debitCreditOID") String debitCreditOID,
	    @Param("state") String state);

    /**
     * 根据还款计划OID查询回款计划
     * @param strRepayOID
     * @param strState
     * @return
     */
	public List<PayReturnPlan> findByRepayOIDAndState(@Param("repayOID") String repayOID, @Param("state") String state);
    
    
    
    
    
    
}
