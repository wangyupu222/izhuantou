package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.TyPro;

/**
 * 体验金判断
 * 
 * @author yangbosen
 *
 */
public interface TyProDetailsService {
    /** 根据memberOID查找判断是否能使用体验金 */
    public TyPro findByCondition(String memberOID);
}
