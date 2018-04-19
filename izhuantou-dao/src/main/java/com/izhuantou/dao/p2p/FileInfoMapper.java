package com.izhuantou.dao.p2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.FileInfo;

/**
 * 图片信息
 * 
 * @author dear
 * @version 1.0
 */
public interface FileInfoMapper extends Mapper<FileInfo> {
    /**
     * 根据OID查询图片信息
     * 
     * @param OID
     * @return
     */
    public FileInfo findPageByOID(String OID);

}
