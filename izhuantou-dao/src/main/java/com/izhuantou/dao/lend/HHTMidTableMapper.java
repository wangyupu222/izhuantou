package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.HHTMidTable;

/**
 * 环环投中间表
 * 
 * @author yangbosen
 *
 */
public interface HHTMidTableMapper extends Mapper<HHTMidTable> {

    public List<HHTMidTable> findByOID(String OID);
}
