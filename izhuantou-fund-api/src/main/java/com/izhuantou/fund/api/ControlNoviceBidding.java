package com.izhuantou.fund.api;

import java.util.Map;

import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;

/**
 * 新手标的相关接口
 *
 * @author fucheng
 * @date 2018-03-06
 */
public interface ControlNoviceBidding extends BaseService<WebP2pNoviceBiddingRuning> {

    /**
     * 新手标的投标流程
     * 
     * @param map（String
     *            memberOID, String biddingOID, // 标的OID BigDecimal amount, //
     *            用户输入的金额 String tqOID, // 特权OID String channel //来源）
     * @return
     */
    public Integer bidNomalBidding(Map<String, Object> map);

}
