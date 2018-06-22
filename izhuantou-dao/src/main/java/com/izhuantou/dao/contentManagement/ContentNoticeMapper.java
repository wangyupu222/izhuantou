package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pNotice;
/**
 * 关于我们-平台公告
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentNoticeMapper extends Mapper<P2pNotice>{
	 /**
     * 查询公告总条数
     * 
     * @return
     */
    int getRowCount();
    
	/**
     * 分页查询出所有的公告
     * 
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<P2pNotice> findNoticeByPage(@Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);
    
    /**
     * 根据OID查询公告具体内容
     * @param OID 公告id
     * @return
     */
    public P2pNotice findNoticeByOID(String OID);
    
    /**
     * 添加一条公告
     * @param P2pNotice 公告bean
     * @return 
     */
    public int addNotice(P2pNotice p2pNotice);
    
    /**
     * 修改一条公告
     * @param P2pNotice 公告bean
     * @return 
     */
    public int updateNotice(P2pNotice p2pNotice);
    
    /**
     * 删除一条公告 实际上是修改valid（有效性）值为0
     * @param OID 公告oid
     * @return
     */
    public int deleteNotice(String OID);
}
