package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;

/**
 * 在投新手标
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pNoviceBiddingRuningMapper extends Mapper<WebP2pNoviceBiddingRuning> {
    /**
     * 产品的OID
     * 
     * @param OID
     * @return
     */
    public WebP2pNoviceBiddingRuning findByOID(String OID);

    public WebP2pNoviceBiddingRuning findByCondition(String loanNum);

    /**
     * 更新
     * 
     * @param noviceBiddingRuning
     * @return
     */
    public int updateNoviceBiddingRuningByOID(WebP2pNoviceBiddingRuning noviceBiddingRuning);
    // public WEBP2P_NoviceBiddingRuning findByLoan(String loanNum);
}
