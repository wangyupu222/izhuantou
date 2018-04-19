package com.izhuantou.service.api.lend;

import com.izhuantou.damain.vo.CustomerTyjDTO;

/**
 * control调用的接口展示体验金人数
 * 
 * @author yanybosen
 *
 */
public interface ExperienceMoneyService {
    /**
     * 
     * @return 展示体验人数
     */
    public CustomerTyjDTO findMember();
}
