package com.izhuantou.fund.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayTransferReturn;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlDebitCredit extends BaseService<PayDebitCredit> {

    public Map<String, Object> addDebitCreditNew(Map<String, Object> map);

    public PayTransferReturn addTransferReturnZZ(Map<String, Object> map);

    /**
     * 新手标满标流程
     * 
     * @param string
     * @param sqlDate
     */
    public void finishDebitCreditNEW(String string, Date sqlDate);

    /**
     * 债转 全部转出后完成债转
     * 
     * @param typeMap
     *            businessOID 标的OID beginDate 债转日期 isTZorCW 投资类型(XS/DD/HH/ZZ/CW)
     *            isTZorCW 投资类型(XS/DD/HH/ZZ) ly : 数据来源 App/Web
     */
    public void finishTransferReturnNEW(Map<String, Object> typeMap);

    /**
     * 投资
     * 
     * @param map(String
     *            businessOID, String memberOID, BigDecimal money, Date
     *            beginDate, String ly)
     * @return
     */
    public PayCashPool investment(Map<String, Object> map);

    /**
     * 获取资金池资金
     * 
     * @param mainBiddingOID
     * @return
     */
    public BigDecimal gainCashPoolCash(String strBusinessOID);

    /**
     * 完成投资
     * 
     * @param mainBiddingOID
     *            主标OID
     * @param realBiddingOID
     *            内容OID
     * @param mayInvestAmount
     *            钱数
     * @param ly
     *            数据来源 App/Web
     */
    public void finishInvestment(String outBusinessOID, String inBusinessOID, BigDecimal money, String ly);

    /**
     * 开始借贷 真实借贷与还款有关系
     * 
     * @param strBusinessOID
     *            标的OID
     * @param beginDate
     *            开始借贷时间
     */
    public void finishDebitCredit(String strBusinessOID, Date beginDate);

    public List<PayDebitCredit> gainDebitCreditByBusinessOID(String biddingOID);

    /**
     * 增加债转 资金池债转 真实债转
     * 
     * @param businessOID
     * @param debitCreditOID
     * @param outBusinessOID
     * @param money
     * @param transferReturnDate
     * @param ly
     */
    public void addCashPoolTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
	    BigDecimal money, Date transferReturnDate, String ly);

    /**
     * 债转 全部转出后完成债转
     * 
     * @param realBiddingOID
     * @param nowDateTime
     * @param string
     * @param string2
     * @param ly
     */
    public void finishTransferReturnNEW(String businessOID, Date beginDate, String isTZorCW, String tzType, String ly);

    /**
     * 增加债转 资金池债转 真实债转
     * 
     * @param businessOID
     *            债转标OID
     * @param debitCreditOID
     *            真实借贷关系表OID
     * @param outBusinessOID
     *            出借标OID
     * @param money
     *            出借金额
     * @param transferReturnDate
     * @param ly
     *            数据来源 App/Web
     */
    public void addCashPoolUserTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
	    BigDecimal money, Date transferReturnDate, String ly);

    /**
     * @author dear
     * @param map
     * @return
     */
    public PayTransferReturn addTransferReturn(Map<String, Object> map);
}
