package com.izhuantou.dao.p2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pFlink;

/**
 * 友情链接信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pFlinkMapper extends Mapper<P2pFlink> {
    /**
     * 查询友情链接
     * 
     * @return
     */
    public List<P2pFlink> findFlinkList();

}
