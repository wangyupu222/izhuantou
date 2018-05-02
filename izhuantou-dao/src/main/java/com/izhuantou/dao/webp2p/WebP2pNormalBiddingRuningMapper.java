package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;

/**
 * 在投中散标表
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pNormalBiddingRuningMapper extends Mapper<WebP2pNormalBiddingRuning> {

    public WebP2pNormalBiddingRuning findByOID(String OID);

    public WebP2pNormalBiddingRuning findByLoan(String LoanNum);
}
