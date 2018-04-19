package com.izhuantou.dao.p2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pCompanyPer;

/**
 * 公司人员信息
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pCompanyPerMapper extends Mapper<P2pCompanyPer> {
    /**
     * 根据平台编号
     * 
     * @param number
     * @return
     */
    public P2pCompanyPer findComperByNumber(String number);

}
