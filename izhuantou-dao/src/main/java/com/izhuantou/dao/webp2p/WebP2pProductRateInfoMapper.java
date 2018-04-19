package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;

/**
 * 理财产品配置及费率表
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface WebP2pProductRateInfoMapper extends Mapper<WebP2pProductRateInfo> {

    public WebP2pProductRateInfo findByOID(String OID);
}
