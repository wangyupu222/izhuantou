package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;

/**
 * 发标申请（小贷提交的申请）
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface WebP2pBiddingExamineMapper extends Mapper<WebP2pBiddingExamine> {

    public WebP2pBiddingExamine findByloanNumber(String loanNumber);

    /**
     * 根据memberOID查询
     * 
     * @param memberOID
     * @return
     */
    public WebP2pBiddingExamine findByMemberOID(String memberOID);

}
