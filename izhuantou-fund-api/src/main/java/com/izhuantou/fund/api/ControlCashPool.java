package com.izhuantou.fund.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.izhuantou.damain.pay.PayCashPool;

/**
 * 投标接口类
 *
 * @author fucheng
 * @date 2018-03-12
 */

public interface ControlCashPool extends BaseService<PayCashPool> {

    /**
     * 一次性手续费
     * 
     * @param strBusinessOID
     * @param string
     * @param onceProceduresMoney
     */
    void onceProceduresMoney(String strBusinessOID, String string, BigDecimal onceProceduresMoney);

    /**
     * 一对一完成投资
     * 
     * @param strBusinessOID
     * @param string
     * @param subtract
     */
    void finishInvestment(String businessOID, String memberOID, BigDecimal money);

    /**
     * 站岗资金结算修改调用方法
     * 
     * @param businessOID
     * @param memberOID
     * @param money
     * @param privilegeMoney
     */
    void finishTransferReturnUnFreeze(String businessOID, String memberOID, BigDecimal money,
	    BigDecimal privilegeMoney);

    /**
     * 投资
     * 
     * @param typeMap(String
     *            businessOID, String outMemberOID, BigDecimal money, BigDecimal
     *            interest, BigDecimal creditRate, String creditType, String
     *            returnCycle, Integer returnNumber, Timestamp beginDate,
     *            Timestamp endDate, BigDecimal privilegePrincipal, BigDecimal
     *            privilegeInterest, String tqOID, String ly))
     */
    PayCashPool investment(Map<String, Object> typeMap);

    /**
     * 
     * @param strBusinessOID
     * @return
     */
    List<PayCashPool> gainCashPoolByBusinessOID(String strBusinessOID);

    /**
     * 根据BusinessOID获取
     * 
     * @param outBusinessOID
     * @return
     */
    List<PayCashPool> gainCashPoolByBusinessOIDAndHaveMoney(String outBusinessOID);

    /**
     * 真正投资
     * 
     * @param businessOID
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    void realInvestment(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 债转
     * 
     * @param businessOID
     * @param memberOID
     * @param outMemberOID
     * @param principal
     */
    void transferReturnCashPool(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 债转 20170426 债转后的新手标 出借人投标后 增加资金流水和站内信 短信提示
     * 
     * @param businessOID
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     * @author Cannon
     * @throws ExceptionSaveFail
     */
    void transferReturn(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);

    /**
     * 贴息
     * 
     * @param businessOID
     * @param memberOID
     * @param money
     */
    void discount(String businessOID, String memberOID, BigDecimal money);

    /**
     * 一对一投资
     * 
     * @param businessOID
     * @param outMemberOID
     * @param inMemberOID
     * @param money
     */
    void investmentNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);

}
