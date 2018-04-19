package com.izhuantou.dao.pay;

import java.util.List;

import com.izhuantou.damain.pay.PayCustomerTyj;
import com.izhuantou.damain.vo.CustomerTyjDTO;

/**
 * 体验金
 * 
 * @author dear
 * @version 1.0
 */
public interface PayCustomerTyjMapper {
    /** 查询整个体验金列表 */
    public List<PayCustomerTyj> findTyj();

    /** 根据memberOID查询体验金 */
    public List<CustomerTyjDTO> findByMemberOID(String memberOID);
}
