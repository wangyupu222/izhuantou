package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCHHT;

public interface MobileMyLendDetialService {

    /** 个人中心我的出借环环出借中投详情接口 */
    public MobileMyLendDetialHHT FindByCashPool(String payCashPoolOID, String memberOID);

    /** 个人中心我的出借环环出借完成详情接口 */
    public MobileMyLendDetialWCHHT FindByCashPoolWC(String payCashPoolOID, String memberOID);

    /** 个人中心我的出借点点出借中投详情接口 */
    public MobileMyLendDetialDDT FindByDDTDebitCreditOID(String memberOID, String debitCreditOID);

    /** 个人中心我的出借点点出借中已完成详情接口 */
    public MobileMyLendDetialWCDDT FindByDDTWCDebitCreditOID(String memberOID, String debitCreditOID);

}
