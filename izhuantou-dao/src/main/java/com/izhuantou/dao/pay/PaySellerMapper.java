package com.izhuantou.dao.pay;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PaySeller;

/**
 * 商户信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface PaySellerMapper extends Mapper<PaySeller> {
    /**
     * 根据商户名查找对应的商户信息
     * 
     * @param name
     * @return
     */
    public PaySeller gainSellerByName(String name);
}
