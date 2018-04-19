package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.AuditInfo;

/**
 * 风控信息审核
 * 
 * @author yangbosen
 *
 */
public interface AuditInfoMapper extends Mapper<AuditInfo> {

    /** 根据OID查找风控审核信息 */
    public List<AuditInfo> findByMainOID(String mainOID);
}
