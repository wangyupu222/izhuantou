package com.izhuantou.dao.personalCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.vo.MyLoanRecordDTO;
import com.izhuantou.damain.vo.RepayMentDTO;

public interface MyLoanMapper{
    // 借款审核
    public List<LoanApplyRecordDTO> findLoanApplyByMemberOID(
    		 @Param("memberOID") String memberOID,
    		 @Param("startIndex") Integer startIndex,
    		 @Param("pageSize") Integer pageSize);
    		 
 
    public LoanApplyRecordDTO findBYcondition(@Param("memberOID") String memberOID, @Param("OID") String OID);

    // 普通
    public List<MyLoanRecordDTO> findLoanRecordByMemberOID(@Param(value = "memberOID") String memberOID);

    // 房抵
    public List<MyLoanRecordDTO> findDDTLoanRecordByPhone(@Param(value = "phone") String phone);

    // 车抵
    public List<MyLoanRecordDTO> findCDLoanRecordByPhone(@Param(value = "phone") String phone);

    
    
    
    // 还款
    public List<RepayMentDTO> findRepayByMemberOID(String memberOID);

    public List<RepayMentDTO> findZCByMemberOID(String memberOID);

}
