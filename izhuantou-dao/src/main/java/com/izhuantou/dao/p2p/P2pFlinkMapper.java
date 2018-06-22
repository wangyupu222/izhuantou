package com.izhuantou.dao.p2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pFlink;
import com.izhuantou.damain.p2p.P2pPageBanner;

/**
 * 友情链接信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pFlinkMapper extends Mapper<P2pFlink> {
    /**
     * 查询底部链接
     * 
     * @return
     */
    public List<P2pFlink> findFlinkList();
    
    /**
     * 通过OID查询底部链接
     * @return
     */
    public P2pFlink findBottomLinkById(String OID);
    
    /**
     * 添加一条底部链接
     * @param P2pFlink 底部链接bean
     * @return 
     */
    public int addBottomLink(P2pFlink p2pFlink);
    
    /**
     * 修改一条底部链接
     * @param P2pFlink 底部链接bean
     * @return 
     */
    public int updateBottomLink(P2pFlink p2pFlink);
    
    /**
     * 删除一条底部链接
     * @param OID 底部链接id
     */
    public int deleteBottomLink(String OID);
}
