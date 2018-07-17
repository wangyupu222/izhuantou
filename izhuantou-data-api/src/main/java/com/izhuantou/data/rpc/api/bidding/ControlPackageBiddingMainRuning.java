package com.izhuantou.data.rpc.api.bidding;

import java.math.BigDecimal;

import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.data.rpc.api.BaseService;

public interface ControlPackageBiddingMainRuning extends BaseService<WebP2pPackageBiddingMainRuning> {
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	String bidPackageBiddingDifferent(BiddingDTO biddto);
	
	/**
	 * 红包投资
	 */
	String bidPackageBiddingRed(BiddingDTO biddto);
	
	/**
	 * 红包和加息券投资
	 */
	String bidPackageBiddingRedAndPrivilege(BiddingDTO biddto);
}
