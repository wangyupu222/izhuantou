package com.izhuantou.service.impl.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.mobile.MobileAdvert;
import com.izhuantou.dao.mobile.MobileAdvertMapper;
import com.izhuantou.service.api.mobile.MobileAdvertService;

@Service("mobileAdvertService")
public class MobileAdvertServiceImpl implements MobileAdvertService {
    @Autowired
    private MobileAdvertMapper advertMapper;

    @Override
    public List<MobileAdvert> FindAppggy() {
	try {
	    // 0,1
	    List<MobileAdvert> list = advertMapper.findByStatus();
	    return list;
	} catch (Exception e) {
	    return null;
	}

    }

    @Override
    public List<MobileAdvert> FindApptc() {
	try {
	    // 1,1
	    List<MobileAdvert> list = advertMapper.findListByType();
	    return list;
	} catch (Exception e) {
	    return null;
	}
    }

}
