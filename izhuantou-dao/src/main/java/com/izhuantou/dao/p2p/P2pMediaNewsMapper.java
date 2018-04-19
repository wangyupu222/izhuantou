package com.izhuantou.dao.p2p;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pMediaNews;

/**
 * 媒体报道
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pMediaNewsMapper extends Mapper<P2pMediaNews> {

    /**
     * 分页查询出所有的媒体报道
     * 
     * @param parentOID
     * @return
     */
    public List<P2pMediaNews> findMediaNewsByPage(@Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 查询总数条数
     * 
     * @return
     */
    int getRowCount();

    /**
     * 根据OID查询媒体报道详情
     * 
     * @param OID
     * @return
     */
    public P2pMediaNews findMediaNewsByOID(String OID);

    /**
     * 查询媒体报道的标题名和时间,
     * 
     * @return
     */
    public List<P2pMediaNews> findMediaNewsTitleName();

}
