package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pPerLoanCenterInfo;

/**
 * 房抵产品分个人标的录入后会存在WebP2pPerLoanCenterInfo下 后台录入
 * 
 * @author dear
 * @version 1.0
 */
public interface WebP2pPerLoanCenterInfoMapper extends Mapper<WebP2pPerLoanCenterInfo> {

    public WebP2pPerLoanCenterInfo findByOID(String OID);
}
