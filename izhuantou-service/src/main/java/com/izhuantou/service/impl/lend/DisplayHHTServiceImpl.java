package com.izhuantou.service.impl.lend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayHHT;
import com.izhuantou.dao.lend.DisplayHHTMapper;
import com.izhuantou.service.api.lend.DisplayHHTService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("displayHHTService")
public class DisplayHHTServiceImpl extends BaseServiceImpl<DisplayHHT> implements DisplayHHTService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayHHTServiceImpl.class);
    @Autowired
    private DisplayHHTMapper displayHHTMapper;

    @Override
    public Pagination<DisplayHHT> showProductsByPage(Integer page, String sortp) {

	List<DisplayHHT> lendList = new ArrayList<DisplayHHT>();
	List<DisplayHHT> lendListResult = new ArrayList<DisplayHHT>();
	Pagination<DisplayHHT> pageController = new Pagination<>();
	try {

	    /** 接受前台当前页参数 */
	    pageController.setCurrentPage(page);
	    /** 接受前台排序参数 */
	    String sorttemp = sortp;
	    /** 分割排序参数 */
	    StringBuilder strsortword = new StringBuilder("");
	    if (!StringUtils.isBlank(sorttemp)) {
		String[] words = sorttemp.split("2");
		strsortword.append(",allbidding.");
		strsortword.append(words[0]);
		strsortword.append("  ");
		strsortword.append(words[1]);
	    } else {
		strsortword.append("  ");
	    }

	    Integer totalNumber = (int) displayHHTMapper.getPageCount();
	    pageController.setTotalNumber(totalNumber);

	    Integer totalPage = pageController.getTotalPage();
	    if (pageController.getCurrentPage() != null) {
		pageController = new Pagination<DisplayHHT>(pageController.getCurrentPage(), totalPage, totalNumber);
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lendList = this.displayHHTMapper.findByCondition(strsortword, StartPos, pageController.getPageSize());
		for (DisplayHHT displayHHT : lendList) {

		    if (displayHHT.getHkfs().equals("OPI")) {
			displayHHT.setHkfs("一次性还本付息");
		    } else if (displayHHT.getHkfs().equals("EPEI")) {
			displayHHT.setHkfs("等本等息");
		    } else {
			displayHHT.setHkfs("按月返息到期还本");
		    }
		    // 将遍历结果，添加进lendListResult
		    lendListResult.add(displayHHT);

		}
		pageController.setData(lendListResult);

	    } else {
		lendList = this.displayHHTMapper.findByCondition(strsortword, 0, 10);
		for (DisplayHHT displayHHT : lendList) {

		    if (displayHHT.getHkfs().equals("OPI")) {
			displayHHT.setHkfs("一次性还本付息");
		    } else if (displayHHT.getHkfs().equals("EPEI")) {
			displayHHT.setHkfs("等本等息");
		    } else {
			displayHHT.setHkfs("按月返息到期还本");
		    }
		    // 将遍历结果，添加进lendListResult
		    lendListResult.add(displayHHT);
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
    public List<DisplayHHT> findProductList() {

	List<DisplayHHT> lendList = new ArrayList<DisplayHHT>();
	// List<DisplayHHT> lendListResult = new ArrayList<DisplayHHT>();
	try {

	    lendList = this.displayHHTMapper.findList();
	    for (DisplayHHT displayHHT : lendList) {

		if (displayHHT.getHkfs().equals("OPI")) {
		    displayHHT.setHkfs("一次性还本付息");
		} else if (displayHHT.getHkfs().equals("EPEI")) {
		    displayHHT.setHkfs("等本等息");
		} else {
		    displayHHT.setHkfs("按月返息到期还本");
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
