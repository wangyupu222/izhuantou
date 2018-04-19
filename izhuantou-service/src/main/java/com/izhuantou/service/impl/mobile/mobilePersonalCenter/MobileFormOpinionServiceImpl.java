package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.MobileOpinionDTO;
import com.izhuantou.dao.mobile.MobileOpinionMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileFormOpinionService;

@Service("mobileFormOpinionService")
public class MobileFormOpinionServiceImpl implements MobileFormOpinionService {
    private static final Logger logger = LoggerFactory.getLogger(MobileFormOpinionServiceImpl.class);
    @Autowired
    MobileOpinionMapper mobileOpinionMapper;

    /**
     * 将意见存入到数据库中
     */
    @Override
    public void InsertOpinion(MobileOpinionDTO mobileOpinionDTO) {

	try {
	    Date time = new Date();
	    mobileOpinionDTO.setOID(StringUtil.getUUID());
	    mobileOpinionDTO.setCommit_time(time);
	    mobileOpinionDTO.setAddDateTime(time);
	    mobileOpinionDTO.setUpdDateTime(time);
	    mobileOpinionMapper.insertOpinion(mobileOpinionDTO);
	} catch (Exception e) {
	    logger.error("findMessage()", e.getMessage());
	}
    }

}
