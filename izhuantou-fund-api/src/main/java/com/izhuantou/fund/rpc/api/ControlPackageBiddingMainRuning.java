package com.izhuantou.fund.rpc.api;

import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;

public interface ControlPackageBiddingMainRuning extends BaseService<WebP2pPackageBiddingMainRuning> {

    /**
     * 
     * @param
     * @return
     */
    String bidPackageBiddingDifferent(BiddingDTO biddto);

}
