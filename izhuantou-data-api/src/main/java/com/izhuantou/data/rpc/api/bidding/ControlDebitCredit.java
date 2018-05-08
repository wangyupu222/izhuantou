package com.izhuantou.data.rpc.api.bidding;

import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.data.rpc.api.BaseService;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlDebitCredit extends BaseService<PayDebitCredit> {
	/**
	 * 添加新手借贷关系
	 * @param biddto
	 * @return
	 */
	public PayDebitCredit addDebitCreditNew(BiddingDTO biddto);
	/**
	 * 债转关系
	 * @param biddto
	 * @return
	 */
	public PayTransferReturn addTransferReturnZZ(BiddingDTO biddto);
	/**
	 * 投资
	 * @param biddto
	 * @return
	 */
	public PayCashPool investment(BiddingDTO biddto);
	/**
	 * 添加债转
	 * @param biddto
	 * @return
	 */
	public PayTransferReturn addTransferReturn(BiddingDTO biddto);
}
