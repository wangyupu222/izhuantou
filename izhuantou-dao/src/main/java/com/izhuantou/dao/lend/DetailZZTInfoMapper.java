package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DetailZZTInfo;

/**
 * 转转投标的信息
 * 
 * @author Administrator
 *
 */
public interface DetailZZTInfoMapper extends Mapper<DetailZZTInfo> {
    /** 根据mainOID查询转转投信息 */
    public DetailZZTInfo findByMainOID(String OID);
}
