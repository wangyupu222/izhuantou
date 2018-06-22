package com.izhuantou.service.api.accoutManagement;

import java.util.List;
import java.util.Map;

import com.izhuantou.damain.vo.UrgentNoticeDTO;

public interface UrgentNoticeService {
	
	/**
	 * 按条件查询紧急公告信息列表
	 * @param currentPage 页数
     * @param urgentNoticeDTO 紧急公告DTOBean
	 * @return
	 */
	public Map<String,Object> findUrgentNotice(Integer currentPage,UrgentNoticeDTO urgentNoticeDTO);
	
	/**
     * 添加一条紧急公告
     * @param UrgentNoticeDTO 紧急公告bean
     * @return 
     */
    public String addUrgentNotice(UrgentNoticeDTO urgentNoticeDTO);
    
    /**
     * 删除一条紧急公告
     * @param OID 紧急公告oid
     * @return 
     */
    public String deleteUrgentNotice(String OID);
    
    /**
     * 修改一条紧急公告
     * @param UrgentNoticeDTO 紧急公告bean
     * @return 
     */
    public String updateUrgentNotice(UrgentNoticeDTO urgentNoticeDTO);
}
