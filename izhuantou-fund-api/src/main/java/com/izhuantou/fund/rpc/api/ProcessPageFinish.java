package com.izhuantou.fund.rpc.api;

import com.izhuantou.damain.vo.bidding.BiddingDTO;

/**
 * 投标的完成后的接口
 *
 * @author fucheng
 * @date 2018-03-06
 */
public interface ProcessPageFinish {
	/**
	 * 新手满标
	 * @param biddto
	 * @return
	 */
	String noviceBiddingFinish(String biddingOID);
	/**
	 * 新手债转标满标
	 * @param biddto
	 * @return
	 */
	String noviceZzBiddingFinish(BiddingDTO biddto);
	
	/**
	 * 环环匹配
	 * @return
	 */
	String packageBiddingMainRuningMatching(String ly);
	
	
}
