package com.izhuantou.data.rpc.api.bidding;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.data.rpc.api.BaseService;

/**
 * 投标接口类
 *
 * @author fucheng
 * @date 2018-03-12
 */

public interface ControlCashPool extends BaseService<PayCashPool> {

	/**
	 * 投资
	 * 
	 */
	PayCashPool investment(BiddingDTO bidto);
	
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
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void discount(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 一对一投资
	 * @param businessOID
	 * @param outMemberOID
	 * @param inMemberOID
	 * @param money
	 */
	void investmentNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);
	
}
