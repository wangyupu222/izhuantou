package com.izhuantou.dao.p2p;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    FileInfo findPageByOID(String OID);

    /**
     * 保存FileInfo
     * @param info
     * @return
     */
    int saveFileInfo(FileInfo info);
    
    /**
     * 批量插入图片信息
     * @param files
     * @return
     */
    int saveFileInfoList(@Param("files")List<FileInfo> files);
    /**
     * 根据OID列表查询图片信息
     * @return
     */
    List<FileInfo> findPageByOIDList(@Param("OIDS")List<String> OIDS);
    
}
