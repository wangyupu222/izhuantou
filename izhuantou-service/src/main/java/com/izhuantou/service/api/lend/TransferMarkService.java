package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.TransferMark;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.service.api.BaseService;

/**
 * 转让标信息
 * 
 * @author jasen
 *
 */
public interface TransferMarkService extends BaseService<WebP2pBiddingExamine> {

    public TransferMark findByCondition(String OID, String memberOID);
}
