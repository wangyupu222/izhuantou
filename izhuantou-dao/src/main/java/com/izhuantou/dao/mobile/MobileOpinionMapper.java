package com.izhuantou.dao.mobile;

import com.izhuantou.damain.mobile.personalCenter.MobileOpinionDTO;

/**
 * 移动端意见反馈
 * 
 * @author yangbosen
 *
 */
public interface MobileOpinionMapper {
    /**
     * 把提交的信息插入到数据库中
     * 
     * @param mobileOpinionDTO
     */
    public void insertOpinion(MobileOpinionDTO mobileOpinionDTO);
}
