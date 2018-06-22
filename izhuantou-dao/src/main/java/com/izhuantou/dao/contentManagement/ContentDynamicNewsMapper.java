package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
/**
 * 关于我们-砖头动态
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentDynamicNewsMapper extends Mapper<P2pToutiaoNews>{
	 /**
     * 查询砖头动态总条数
     * 
     * @return
     */
    int getRowCount();
    
	/**
     * 分页查询砖头动态
     * 
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<P2pToutiaoNews> findDynamicNewsByPage(@Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);
    
    /**
     * 根据OID查询砖头动态具体内容
     * @param OID 公告id
     * @return
     */
    public P2pToutiaoNews findDynamicNewsByOID(String OID);
    
    /**
     * 添加一条砖头动态
     * @param P2pToutiaoNews 砖头动态bean
     * @return 
     */
    public int addDynamicNews(P2pToutiaoNews p2pToutiaoNews);
    
    /**
     * 修改一条砖头动态
     * @param P2pToutiaoNews 砖头动态bean
     * @return 
     */
    public int updateDynamicNews(P2pToutiaoNews p2pToutiaoNews);
    
    /**
     * 删除一条公告 实际上是修改valid（有效性）值为0
     * @param OID 砖头动态oid
     * @return
     */
    public int deleteDynamicNews(String OID);
}
