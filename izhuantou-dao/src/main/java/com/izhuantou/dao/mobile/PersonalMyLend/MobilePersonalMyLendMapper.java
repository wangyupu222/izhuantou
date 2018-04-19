package com.izhuantou.dao.mobile.PersonalMyLend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.RecordeFromDebitCreditDTO;

public interface MobilePersonalMyLendMapper {
    /** 查询我的出借环环投出借中详情 */
    public MobileMyLendDetialHHT findHHTDetial(@Param("payCashPoolOID") String payCashPoolOID,
	    @Param("memberOID") String memberOID);

    /** 查询我的出借环环投出借完成详情 */
    public MobileMyLendDetialWCHHT findHHTWCDetial(@Param("payCashPoolOID") String payCashPoolOID,
	    @Param("memberOID") String memberOID);

    /** 查询环环剩余可投 */
    public String FindSykt(String mainOID);

    /** 查询列表用于环环投出借中判断（暂时不知字段意义） */
    public List<RecordeFromDebitCreditDTO> findByMainOID(String mainOID);

    /** 查询我的出借点点投出借中详情 */
    public MobileMyLendDetialDDT findDDTDetial(@Param("memberOID") String memberOID,
	    @Param("debitCreditOID") String debitCreditOID);

    /** 查询我的出借点点投完成详情 */
    public MobileMyLendDetialWCDDT findDDTWCDetial(@Param("memberOID") String memberOID,
	    @Param("debitCreditOID") String debitCreditOID);

}
