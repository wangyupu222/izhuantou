package com.izhuantou.dao.pay;

import java.util.List;

import com.izhuantou.damain.vo.CustomerTyjDTO;

/**
 * 体验金
 * 
 * @author dear
 * @version 1.0
 */
public interface PayCustomerTyjMapper {
    /**获取体验金总数 */
    public Integer findTyjRowCount();

    /** 根据memberOID查询体验金 */
    public List<CustomerTyjDTO> findByMemberOID(String memberOID);
    
    /**
     * 根据会员OID查询使用体验金用户
     * @param memberOID
     * @return
     */
	public int countByMemberOID(String memberOID);
    
    
    
    
}
