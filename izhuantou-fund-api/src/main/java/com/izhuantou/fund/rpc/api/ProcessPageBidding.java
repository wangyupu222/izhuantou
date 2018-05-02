package com.izhuantou.fund.rpc.api;

import com.izhuantou.damain.vo.bidding.BiddingDTO;

/**
 * 投标的相关接口
 *
 * @author fucheng
 * @date 2018-03-06
 */
public interface ProcessPageBidding {
    /**
     * 新手标
     * 
     * @param biddto
     * @return
     */
    public String ProcessPageNewChujiesave(BiddingDTO biddto);

    /**
     * 环环投
     * 
     * @param biddto
     * @return
     */
    public String ProcessPageHHChujiesave(BiddingDTO biddto);

    /**
     * 点点投
     * 
     * @param biddto
     * @return
     */
    public String ProcessPageDDChujiesave(BiddingDTO biddto);

    /**
     * 转转
     * 
     * @param biddto
     * @return
     */
    public String ProcessPageZZChujiesave(BiddingDTO biddto);
}
