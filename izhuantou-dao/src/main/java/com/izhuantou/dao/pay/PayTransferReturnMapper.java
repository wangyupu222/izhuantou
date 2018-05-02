package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayTransferReturn;

/**
 * 债转资金记录
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PayTransferReturnMapper extends Mapper<PayTransferReturn> {

    public List<PayTransferReturn> findByBusinenssOID(String OID);

    /** 根据outMemberOID和条件查询数据 */
    public List<PayTransferReturn> findByCondition(String OID);

    /**
     * 根据memberOID查询债转标 状态是creditType='OPI'
     * 
     * @param memberOID
     * @return
     */
    public PayTransferReturn gainByOutMemberOID(String memberOID);

    /**
     * 根据memberOID查询债转标 状态是creditType='OPI' and state='investment'
     * 
     * @param memberOID
     * @return
     */
    public PayTransferReturn gainByMemberOIDANDState(String memberOID);

    /**
     * 根据新的新oid和状态查询
     * 
     * @param DebitCreditOIDNew
     * @param state
     * @return
     */
    public PayTransferReturn gainTransferReturnByDebitCreditNewOIDAndState(
	    @Param("debitCreditOIDNew") String debitCreditOIDNew, @Param("state") String state);

    /**
     * 根据debitCreditOID和用户oid查询
     * 
     * @param debitCreditOID
     * @param memberOID
     * @return
     */
    public PayTransferReturn gainTransferReturnByDebitCreditOIDAndOutMemberOID(
	    @Param("debitCreditOID") String debitCreditOID, @Param("memberOID") String memberOID);

    public List<PayTransferReturn> getTransferReturnByDebitCreditOIDAndstate(
	    @Param("debitCreditOID") String debitCreditOID, @Param("state") String state);

    /**
     * 根据Oid获取对象
     * 
     * @param OID
     * @return
     */
    public PayTransferReturn getTransferReturnByOid(String OID);

    /**
     * 保存债转信息
     * 
     * @param dtoTransferReturn
     * @return
     */
    public int saveTransferReturn(PayTransferReturn dtoTransferReturn);

    /**
     * 
     * @param businessOID
     * @param state
     * @return
     */
    public List<PayTransferReturn> findByBusinessOIDAndState(@Param("businessOID") String businessOID,
	    @Param("state") String state);

    /**
     * 更新债转信息
     * 
     * @param transferReturn
     * @return
     */
    public int updataTransferReturn(PayTransferReturn transferReturn);

    /**
     * 再债转新手标被购买生成债权转让协议时，由于合并的需要，需要区别合并时出账户于入账户都是财务用户的那条记录
     * 
     * @param businessOID
     */
    public List<PayTransferReturn> findByBusinessOIDDifferent(String businessOID);

}
