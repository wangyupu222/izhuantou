package com.izhuantou.service.impl.lend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.HHTTagComponent;
import com.izhuantou.dao.lend.HHTTagComponentMapper;
import com.izhuantou.service.api.lend.HHTTagComponentService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 环环投标的组成
 * 
 * @author Administrator
 *
 */
@Service("hhtTagComponentService")
public class HHTTagComponentServiceImpl extends BaseServiceImpl<HHTTagComponent> implements HHTTagComponentService {
    private static final Logger logger = LoggerFactory.getLogger(HHTTagComponentServiceImpl.class);

    @Autowired
    private HHTTagComponentMapper hhtTagComponentMapper;

    @Override
    public Map<String, Object> findByCondition(Integer page, String OID) {
	Pagination<HHTTagComponent> pageController = new Pagination<HHTTagComponent>();
	List<HHTTagComponent> list = new ArrayList<HHTTagComponent>();
	Map<String, Object> map = new HashMap<>();
	try {
	    // System.out.println(page);
	    pageController.setCurrentPage(page);
	    Integer totalNumber = (int) hhtTagComponentMapper.findCount(OID);
	    pageController.setTotalNumber(totalNumber);
	    // int syeshu = pageController.getCurrentPage();
	    // System.out.println("asdasdasdasdasd"+syeshu);
	    Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
	    list = hhtTagComponentMapper.findByOID(OID, StartPos, 10);
	    Integer totleNum = pageController.getTotalNumber();
	    Integer totalPage = pageController.getTotalPage();
	    map.put("data", list);
	    map.put("totleNum", (Integer) totleNum);
	    map.put("totalPage", (Integer) totalPage);

	    // pageController.setData(list);

	    // System.out.println("sadasdasdasdasd"+pageController.toString());

	    return map;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}

    }

}
