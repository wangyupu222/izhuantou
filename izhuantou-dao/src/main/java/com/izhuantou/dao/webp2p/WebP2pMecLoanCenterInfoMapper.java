package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pMecLoanCenterInfo;

/**
 * 房抵产品企业标的的录入后会存在HX_PerLoanCenterInfo下 后台录入
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pMecLoanCenterInfoMapper extends Mapper<WebP2pMecLoanCenterInfo> {
    public WebP2pMecLoanCenterInfo findByOID(String OID);
}
