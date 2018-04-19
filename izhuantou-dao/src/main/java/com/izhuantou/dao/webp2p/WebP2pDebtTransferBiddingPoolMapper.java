package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferBiddingPool;

/**
 * 债转债权池（转转投）
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pDebtTransferBiddingPoolMapper extends Mapper<WebP2pDebtTransferBiddingPool> {
    public WebP2pDebtTransferBiddingPool findByOID(String OID);
}
