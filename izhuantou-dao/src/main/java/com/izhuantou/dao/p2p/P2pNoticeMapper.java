package com.izhuantou.dao.p2p;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pNotice;

/**
 * 平台公告
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pNoticeMapper extends Mapper<P2pNotice> {
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
     * 查询总数条数
     * 
     * @return
     */
    int getRowCount();

    /**
     * 根据OID查询公告消息详情
     * 
     * @param OID
     * @return
     */
    public P2pNotice findNoticeByOID(String OID);

    /**
     * 查询公告的标题名和时间
     * 
     * @return
     */
    public List<P2pNotice> findNoticeTitleName();

}
