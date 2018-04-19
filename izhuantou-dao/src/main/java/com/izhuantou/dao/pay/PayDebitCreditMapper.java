package com.izhuantou.dao.pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayDebitCredit;

/**
 * 借贷关系表（实际标的）所有出借/回款成功之后就存在该记录
 * 
 * @author dear
 * @version 1.0
 */
public interface PayDebitCreditMapper extends Mapper<PayDebitCredit> {

    /***
     * 代收特权
     * 
     * @param memberOID
     * @return
     */
    List<Map<String, Object>> gainDDTTQBymemberANDstateOne(String memberOID);

    /**
     * 累计特权
     * 
     * @param memberOID
     * @return
     */
    List<Map<String, Object>> gainDDTTQBymemberANDstateTwo(String memberOID);

    /**
     * 根据inMemberOID查询
     */
    public PayDebitCredit findByOID(String OID);

    /**
     * 根据state条件查询
     */
    public List<PayDebitCredit> findByCondition(String OID);

    /**
     * 根据outMemberOID查询
     */
    public List<PayDebitCredit> findByOutMember(String OID);

    /**
     * 根据主键OID查询借贷关系
     * 
     * @param OID
     * @return
     */
    public PayDebitCredit findByDebitCreditOID(String OID);

    /**
     * 查看 loanNumber编号,OID,memberOID 用户
     * 
     * @param businessID
     * @return
     */
    public List<Map<String, Object>> findBybusinessID(String businessID);

    /**
     * 根据唯一主键 获取对应的实体类
     * 
     * @param Oid
     * @return
     */
    public PayDebitCredit queryByPKOid(String Oid);

    /**
     * 保存一条借贷关系
     * 
     * @param debitCredit
     * @return
     */
    public int saveDebitCredit(PayDebitCredit debitCredit);

    /**
     * 根据业务OID和状态查看借贷关系
     * 
     * @param businessOID
     * @param state
     * @return
     */
    public List<PayDebitCredit> findByBusinessOIDAndState(@Param("businessOID") String businessOID,
	    @Param("state") String state);

    /**
     * 更新借贷关系表
     * 
     * @param debitCredit
     * @return
     */
    public int updateDebitCredit(PayDebitCredit debitCredit);

}
