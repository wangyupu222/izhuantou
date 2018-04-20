package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlPackageBiddingMainContent extends BaseService<WebP2pPackageBiddingMainContentRuning> {

    List<WebP2pPackageBiddingMainContentRuning> findByContentAndLoanPTOID(String biddingOID);

    List<WebP2pPackageBiddingMainContentRuning> findPackageBiddingMainContentRuningByOID(String biddingOID);

    List<WebP2pPackageBiddingMainContentRuning> findByLoanNum(String bidLoanNumber);

}
