package com.izhuantou.service.impl.lend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.dao.lend.ExperienceMoneyMapper;
import com.izhuantou.service.api.lend.ExperienceMoneyService;

/**
 * 体验金人数获得实现类
 * 
 * @author yangbosen
 *
 */
@Service("experienceMoneyService")
public class ExperienceMoneyServiceImpl implements ExperienceMoneyService {
    private static final Logger logger = LoggerFactory.getLogger(ExperienceMoneyServiceImpl.class);
    @Autowired
    private ExperienceMoneyMapper experienceMoneyMapper;

    @Override
    public CustomerTyjDTO findMember() {

	try {
	    CustomerTyjDTO tyMoney = new CustomerTyjDTO();
	    Integer member = experienceMoneyMapper.findMember();
	    tyMoney.setExperienceMember(member);
	    return tyMoney;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findMember()", e.getMessage());

	    return null;

	}

    }

}
