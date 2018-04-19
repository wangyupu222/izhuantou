package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;

/**
 * 借款产品配置表
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface WebP2pLoanProductRateInfoMapper extends Mapper<WebP2pLoanProductRateInfo> {

    public WebP2pLoanProductRateInfo findByOID(String OID);
}
