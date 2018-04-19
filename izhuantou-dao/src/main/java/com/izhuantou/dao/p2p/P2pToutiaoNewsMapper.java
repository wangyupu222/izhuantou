package com.izhuantou.dao.p2p;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pToutiaoNews;

/**
 * 头条新闻信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pToutiaoNewsMapper extends Mapper<P2pToutiaoNews> {

    /**
     * 分页查询出所有的新闻动态 (砖头动态)
     * 
     * @param parentOID
     * @return
     */
    public List<P2pToutiaoNews> findToutiaoNewsByPage(@Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 查询总数条数
     * 
     * @param parentOID
     * @return
     */
    int getRowCount();

    /**
     * 根据OID查询头条详情（砖头动态）
     * 
     * @param OID
     * @return
     */
    public P2pToutiaoNews findToutiaoNewsByOID(String OID);

}
