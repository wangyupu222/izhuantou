package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.ExBorrowerHHTCDInfo;

/**
 * 环环投车抵借款人信息实体
 * 
 * @author Administrator
 *
 */
public interface ExBorrowerHHTCDInfoMapper extends Mapper<ExBorrowerHHTCDInfo> {
    public ExBorrowerHHTCDInfo findByOID(String OID);

}
