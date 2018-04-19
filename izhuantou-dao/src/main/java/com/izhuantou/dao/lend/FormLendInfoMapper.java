package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pZtloan;

/**
 * 我要借款
 * 
 * @author yangbosen
 *
 */
public interface FormLendInfoMapper extends Mapper<P2pZtloan> {

    public void insertData(P2pZtloan zt);
}
