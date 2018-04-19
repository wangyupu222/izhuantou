package com.izhuantou.dao.webp2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;

/**
 * 在投团标产品内容表
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pPackageBiddingMainContentRuningMapper extends Mapper<WebP2pPackageBiddingMainContentRuning> {
    public WebP2pPackageBiddingMainContentRuning findByOID(String OID);

    /**
     * 通过normal和loanNum查找
     * 
     * @param loanNum
     * @return
     */
    public WebP2pPackageBiddingMainContentRuning findByLoanNum(String loanNum);

    /**
     * 根据主表产品OID
     * 
     * @return
     */
    public List<WebP2pPackageBiddingMainContentRuning> findRealBiddingByMainOID(String mainOID);

    /**
     * 更新在投团标产品内容表
     * 
     * @return
     */
    public int updatePackageBiddingMainContentRuning(WebP2pPackageBiddingMainContentRuning contentRuning);

}