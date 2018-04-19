package com.izhuantou.service.impl.lend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayZZT;
import com.izhuantou.dao.lend.DisplayZZTMapper;
import com.izhuantou.service.api.lend.DisplayZZTService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("displayZZTService")
public class DisplayZZTServiceImpl extends BaseServiceImpl<DisplayZZT> implements DisplayZZTService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayZZTServiceImpl.class);
    @Autowired
    private DisplayZZTMapper displayZZTMapper;

    @Override
    public Pagination<DisplayZZT> showProductsByPage(Integer page, String sortp) {
	List<DisplayZZT> lendList = new ArrayList<DisplayZZT>();
	List<DisplayZZT> lendListResult = new ArrayList<DisplayZZT>();
	Pagination<DisplayZZT> pageController = new Pagination<>();
	try {

	    /** 接受前台参数 */
	    pageController.setCurrentPage(page);
	    /** 接受前台参数 */
	    String sorttemp = pageController.getSortp();
	    StringBuilder strsortword = new StringBuilder("");
	    /** 分割排序参数 */
	    if (!StringUtils.isBlank(sorttemp)) {
		String[] words = sorttemp.split("2");
		strsortword.append(",allbidding.");
		strsortword.append(words[0]);
		strsortword.append("  ");
		strsortword.append(words[1]);
	    } else {
		strsortword.append("  ");
	    }

	    Integer totalNumber = (int) displayZZTMapper.getPageCount();
	    pageController.setTotalNumber(totalNumber);
	    Integer totalPage = pageController.getTotalPage();
	    if (pageController.getCurrentPage() != null) {
		pageController = new Pagination<DisplayZZT>(pageController.getCurrentPage(), totalPage, totalNumber);
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;

		lendList = this.displayZZTMapper.findByCondition(strsortword, StartPos, pageController.getPageSize());
		for (DisplayZZT displayZZT : lendList) {

		    if (displayZZT.getHkfs().equals("OPI")) {
			displayZZT.setHkfs("一次性还本付息");
		    } else if (displayZZT.getHkfs().equals("EPEI")) {
			displayZZT.setHkfs("等本等息");
		    } else {
			displayZZT.setHkfs("按月返息到期还本");
		    }
		    lendListResult.add(displayZZT);
		}
		pageController.setData(lendListResult);
	    } else {
		lendList = this.displayZZTMapper.findByCondition(strsortword, 0, 10);

		for (DisplayZZT displayZZT : lendList) {

		    if (displayZZT.getHkfs().equals("OPI")) {
			displayZZT.setHkfs("一次性还本付息");
		    } else if (displayZZT.getHkfs().equals("EPEI")) {
			displayZZT.setHkfs("等本等息");
		    } else {
			displayZZT.setHkfs("按月返息到期还本");
		    }
		    lendListResult.add(displayZZT);
		}
		pageController.setData(lendListResult);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
	return pageController;

    }

    @Override
    public List<DisplayZZT> findProductList() {
	List<DisplayZZT> lendList = new ArrayList<DisplayZZT>();
	try {
	    lendList = this.displayZZTMapper.findList();
	    for (DisplayZZT displayZZT : lendList) {

		if (displayZZT.getHkfs().equals("OPI")) {
		    displayZZT.setHkfs("一次性还本付息");
		} else if (displayZZT.getHkfs().equals("EPEI")) {
		    displayZZT.setHkfs("等本等息");
		} else {
		    displayZZT.setHkfs("按月返息到期还本");
		}
	    }
	    return lendList;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}

    }

}
