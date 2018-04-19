package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import com.izhuantou.damain.mobile.personalCenter.MobileOpinionDTO;

public interface MobileFormOpinionService {
    /**
     * 将前端传来的意见写入到数据库中
     * 
     * @param mobileOpinionDTO
     */
    public void InsertOpinion(MobileOpinionDTO mobileOpinionDTO);

}
