package com.izhuantou.data.rpc.api.bidding;

import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.data.rpc.api.BaseService;

/**
 * 新手标的相关接口
 *
 * @author fucheng
 * @date 2018-03-06
 */
public interface ControlNoviceBidding extends BaseService<WebP2pNoviceBiddingRuning> {

	/**
	 * 新手标的投标流程
	 * 
	 * @return
	 */
	public String bidNoviceBiddingNew(BiddingDTO biddto);
	/**
	 * 新手标的投标流程
	 * 
	 * @return
	 */
	public String bidNoviceBiddingZz(BiddingDTO biddto);
	
	
	
}
