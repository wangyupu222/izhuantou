package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pMediaNews;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
import com.izhuantou.damain.vo.PageNewsDTO;
/**
 * 关于我们-媒体报道
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentMediaNewsMapper extends Mapper<P2pMediaNews>{
	 /**
     * 查询媒体报道总条数
     * 
     * @return
     */
    int getRowCount(PageNewsDTO pageNewsDTO);
    
	/**
     * 分页查询媒体报道
     * 
     * @param startIndex 
     * @param pageSize
     * @return
     */
    public List<P2pMediaNews> findMediaNewsByPage(@Param("pnDTO") PageNewsDTO pageNewsDTO,@Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);
    
    /**
     * 根据OID查询媒体报道具体内容
     * @param OID 媒体报道id
     * @return
     */
    public P2pMediaNews findMediaNewsByOID(String OID);
    
    /**
     * 添加一条媒体报道
     * @param P2pMediaNews 媒体报道bean
     * @return 
     */
    public int addMediaNews(P2pMediaNews p2pMediaNews);
    
    /**
     * 修改一条媒体报道
     * @param P2pMediaNews 媒体报道bean
     * @return 
     */
    public int updateMediaNews(P2pMediaNews p2pMediaNews);
    
    /**
     * 删除一条媒体报道
     * @param OID 媒体报道oid
     * @return 
     */
    public int deleteMediaNews(String OID);
}
