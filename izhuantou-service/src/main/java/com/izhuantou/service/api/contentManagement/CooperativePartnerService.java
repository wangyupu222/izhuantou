package com.izhuantou.service.api.contentManagement;

import java.util.List;

import com.izhuantou.damain.p2p.P2pCooperativePartner;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.vo.CooperativePartnerDTO;

public interface CooperativePartnerService {
	/**
	 * 获取合作伙伴信息列表
	 * @return
	 */
	public List<CooperativePartnerDTO> findCooperativePartner();
	
	/**
     * 添加一条合作伙伴
     * @param cooperativePartnerDTO 合作伙伴DTOBean
     * @return 
     */
    public String addCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO);
    
    /**
     * 删除一条合作伙伴
     * @param OID 合作伙伴oid
     * @return 
     */
    public String deleteCooperativePartner(String OID);
    
    /**
     * 修改一条合作伙伴
     * @param cooperativePartnerDTO 合作伙伴DTOBean
     * @return 
     */
    public String updateCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO);
}
