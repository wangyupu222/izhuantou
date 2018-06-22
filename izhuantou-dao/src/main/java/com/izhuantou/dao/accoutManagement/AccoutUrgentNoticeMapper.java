package com.izhuantou.dao.accoutManagement;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pUrgentNotice;
import com.izhuantou.damain.vo.UrgentNoticeDTO;
/**
 * 账号管理-紧急公告
 * 
 * @author liyang
 * @version 1.0
 */
public interface AccoutUrgentNoticeMapper extends Mapper<P2pUrgentNotice>{
    /**
     * 查询紧急公告总条数
     * @param urgentNoticeDTO 紧急公告DTOBean
     * @return
     */
	public int getRowCount(UrgentNoticeDTO urgentNoticeDTO);
    
    /**
     * 根据条件查询所有的紧急公告
     * @param urgentNoticeDTO 紧急公告DTOBean
     * @param startIndex 查询起始条数
     * @param pageSize 每页显示条数
     */
    public List<P2pUrgentNotice> findUrgentNotice(@Param("unDTO") UrgentNoticeDTO urgentNoticeDTO,@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
    
    /**
     * 根据OID查询紧急公告内容
     * @param OID 紧急公告oid
     * @return
     */
    public P2pUrgentNotice findUrgentNoticeByOID(String OID);
    
    /**
     * 添加一条紧急公告
     * @param P2pUrgentNotice 紧急公告bean
     * @return 
     */
    public int addUrgentNotice(P2pUrgentNotice p2pUrgentNotice);
    
    /**
     * 修改一条紧急公告
     * @param P2pUrgentNotice 紧急公告bean
     * @return 
     */
    public int updateUrgentNotice(P2pUrgentNotice p2pUrgentNotice);
    
    /**
     * 删除一条紧急公告
     * @param OID 紧急公告oid
     * @return
     */
    public int deleteUrgentNotice(String OID);
}
