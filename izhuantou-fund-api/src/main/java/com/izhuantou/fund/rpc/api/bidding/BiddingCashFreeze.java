package com.izhuantou.fund.rpc.api.bidding;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.fund.rpc.api.BaseService;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface BiddingCashFreeze extends BaseService<PayDebitCredit> {
	/**
	 * 新手
	 * @param biddto
	 * @return
	 */
	public String addDebitCreditNew(BiddingDTO biddto);
	/**
	 * 债转
	 * @param biddto
	 * @return
	 */
	public String addTransferReturnZZ(BiddingDTO biddto);

	/**
	 * 投资
	 * 
	 * @param map(String
	 *            businessOID, String memberOID, BigDecimal money, Date
	 *            beginDate, String ly)
	 * @return
	 */
	public String investment(BiddingDTO biddto);


	/**
	 * @author dear
	 * @param map
	 * @return
	 */
	public String addTransferReturn(BiddingDTO biddto);
	
	/**
	 * 使用红包投资
	 * @param biddto
	 * @param allRedAmount
	 * @return
	 */
	public String investmentRed(BiddingDTO biddto);
	
	/**
	 * 使用红包和加息券投资
	 * @param biddto
	 * @return
	 */
	public String investmentRedAndPrivilege(BiddingDTO biddto);
}
