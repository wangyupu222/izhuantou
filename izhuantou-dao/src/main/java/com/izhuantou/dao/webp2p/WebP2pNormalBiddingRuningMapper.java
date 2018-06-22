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
	/**
	 * 根据标的OID
	 * @param OID
	 * @return
	 */
    WebP2pNormalBiddingRuning findByOID(String OID);
    /**
     * 编号查询
     * @param LoanNum
     * @return
     */
    WebP2pNormalBiddingRuning findByLoan(String LoanNum);
    /**
     * 更新表
     * @param normalbidding
     * @return
     */
    int updateNormalBiddingBuning(WebP2pNormalBiddingRuning normalbidding);
    
    
}
