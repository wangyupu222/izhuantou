package com.izhuantou.service.api.contentManagement;

import java.util.List;

import com.izhuantou.damain.p2p.P2pFlink;
import com.izhuantou.damain.vo.FlinkDTO;

public interface BottomLinkService {
	/**
	 * 获取底部链接信息列表
	 * @return
	 */
	public List<FlinkDTO> findBottomLink();
	
	/**
     * 添加一条底部链接
     * @param flinkDTO 底部链接DTOBean
     * @return 
     */
    public String addBottomLink(FlinkDTO flinkDTO);
    
    /**
     * 删除一条底部链接
     * @param OID 底部链接oid
     * @return 
     */
    public String deleteBottomLink(String OID);
    
    /**
     * 修改一条底部链接
     * @param flinkDTO 底部链接DTOBean
     * @return 
     */
    public String updateBottomLink(FlinkDTO flinkDTO);
}
