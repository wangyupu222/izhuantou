package com.izhuantou.fund.rpc.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.vo.bidding.BiddingDTO;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlDebitCredit extends BaseService<PayDebitCredit> {

	/**
	 * 新手标满标流程
	 * 
	 * @param string
	 * @param sqlDate
	 */
	 void finishDebitCreditNEW(String string, Date sqlDate);

	/**
	 * 债转 全部转出后完成债转
	 * 
	 * @param typeMap
	 *            businessOID 标的OID beginDate 债转日期 isTZorCW 投资类型(XS/DD/HH/ZZ/CW)
	 *            isTZorCW 投资类型(XS/DD/HH/ZZ) ly : 数据来源 App/Web
	 */
	 void finishTransferReturnNEW(BiddingDTO biddto);

	/**
	 * 获取资金池站岗资金资金
	 * 
	 * @param mainBiddingOID
	 * @return
	 */
	 BigDecimal gainCashPoolCash(String strBusinessOID);

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
	 void finishInvestment(String outBusinessOID, String inBusinessOID, BigDecimal money, String ly);

	/**
	 * 开始借贷 真实借贷与还款有关系
	 * 
	 * @param strBusinessOID
	 *            标的OID
	 * @param beginDate
	 *            开始借贷时间
	 */
	 void finishDebitCredit(String strBusinessOID, Date beginDate);
	/**
	 * 更具产品OID查询借贷关系
	 * @param biddingOID
	 * @return
	 */
	 List<PayDebitCredit> gainDebitCreditByBusinessOID(String biddingOID);

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
	 void addCashPoolTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
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
	 void finishTransferReturnNEW(String businessOID, Date beginDate, String isTZorCW, String tzType, String ly);

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
	 void addCashPoolUserTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
			BigDecimal money, Date transferReturnDate, String ly);


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
