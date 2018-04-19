package com.izhuantou.dao.personalCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.ListRepaymentCollection;
import com.izhuantou.damain.vo.MyLendRecord;
import com.izhuantou.damain.vo.UpdLendRecord;

public interface MyLoanMapper extends Mapper<UpdLendRecord> {
    // 借款审核
    public List<UpdLendRecord> findBYmemberOID(String memberOID);

    public UpdLendRecord findBYcondition(@Param("memberOID") String memberOID, @Param("OID") String OID);

    // 普通
    public List<MyLendRecord> findList(@Param(value = "memberOID") String memberOID);

    // 房抵
    public List<MyLendRecord> findFDList(@Param(value = "phone") String phone);

    // 车抵
    public List<MyLendRecord> findCDList(@Param(value = "phone") String phone);

    // 还款
    public List<ListRepaymentCollection> findByMemberOID(String memberOID);

    public List<ListRepaymentCollection> findZCByMemberOID(String memberOID);

}
