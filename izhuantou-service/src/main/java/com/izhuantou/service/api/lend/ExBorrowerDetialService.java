package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.ExBorrowerHHTCDInfo;
import com.izhuantou.damain.lend.ExDDTInfo;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.service.api.BaseService;

public interface ExBorrowerDetialService extends BaseService<WebP2pBiddingExamine> {
    /** 头笔赚元借款人信息 */
    public WebP2pBiddingExamine findByCondition(String OID);

    /** 环环投元借款人信息 */
    public WebP2pBiddingExamine findByConditionHHT(String OID);

    /** 环环投车抵元借款人信息 */
    public ExBorrowerHHTCDInfo findCDHHT(String OID);

    /** 点点投元借款人信息 */
    public ExDDTInfo findDDT(String OID);

    /** 转转投元借款人信息 */
    public WebP2pBiddingExamine findByConditionZZT(String OID);

}
