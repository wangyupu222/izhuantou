package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.CustomerTyjDTO;

/**
 * 体验金数据展示Dao层
 * 
 * @author yangbosen
 *
 */
public interface ExperienceMoneyMapper extends Mapper<CustomerTyjDTO> {
    /** 查找体验金人数 */
    public Integer findMember();

}
