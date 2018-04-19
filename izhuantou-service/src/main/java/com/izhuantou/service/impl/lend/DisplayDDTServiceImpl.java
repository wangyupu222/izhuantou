package com.izhuantou.service.impl.lend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayDDT;
import com.izhuantou.dao.lend.DisplayDDTMapper;
import com.izhuantou.service.api.lend.DisplayDDTService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("displayDDTService")
public class DisplayDDTServiceImpl extends BaseServiceImpl<DisplayDDT> implements DisplayDDTService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayDDTServiceImpl.class);
    @Autowired
    private DisplayDDTMapper displayDDTMapper;

    @Override
    public Pagination<DisplayDDT> showProductsByPage(Integer page, String sortp) {
	List<DisplayDDT> lendList = new ArrayList<DisplayDDT>();
	List<DisplayDDT> lendListResult = new ArrayList<DisplayDDT>();
	Pagination<DisplayDDT> pageController = new Pagination<>();
	try {

	    /** 接受前台页码参数 */
	    pageController.setCurrentPage(page);
	    /** 接受前台排序参数 */
	    String sorttemp = sortp;
	    /** 给排序参数做切割判断 */
	    StringBuilder strsortword = new StringBuilder("");
	    if (!StringUtils.isBlank(sorttemp)) {
		String[] words = sorttemp.split("2");
		strsortword.append(",");
		strsortword.append(words[0]);
		strsortword.append("  ");
		strsortword.append(words[1]);
	    } else {
		strsortword.append("  ");
	    }

	    Integer totalNumber = (int) displayDDTMapper.getPageCount();
	    pageController.setTotalNumber(totalNumber);

	    Integer totalPage = pageController.getTotalPage();
	    if (pageController.getCurrentPage() != null) {
		pageController = new Pagination<DisplayDDT>(pageController.getCurrentPage(), totalPage, totalNumber);
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lendList = this.displayDDTMapper.findByCondition(strsortword, StartPos, pageController.getPageSize());
		for (DisplayDDT displayDDT : lendList) {
		    if (displayDDT.getHkfs().equals("OPI")) {
			displayDDT.setHkfs("一次性还本付息");
		    } else if (displayDDT.getHkfs().equals("EPEI")) {
			displayDDT.setHkfs("等本等息");
		    } else {
			displayDDT.setHkfs("按月返息到期还本");
		    }
		    // 将遍历结果，添加进lendList2
		    lendListResult.add(displayDDT);
		}
		pageController.setData(lendListResult);
	    } else {
		lendList = this.displayDDTMapper.findByCondition(strsortword, 0, 10);
		for (DisplayDDT displayDDT : lendList) {
		    if (displayDDT.getHkfs().equals("OPI")) {
			displayDDT.setHkfs("一次性还本付息");
		    } else if (displayDDT.getHkfs().equals("EPEI")) {
			displayDDT.setHkfs("等本等息");
		    } else {
			displayDDT.setHkfs("按月返息到期还本");
		    }

		    // 将遍历结果，添加进lendList2
		    lendListResult.add(displayDDT);
		}
		pageController.setData(lendListResult);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("showProductsByPage()", e.getMessage());
	    return null;
	}
	return pageController;

    }

    @Override
    public List<DisplayDDT> findProductList() {
	List<DisplayDDT> lendList = new ArrayList<DisplayDDT>();
	try {

	    lendList = this.displayDDTMapper.findList();
	    for (DisplayDDT displayDDT : lendList) {
		if (displayDDT.getHkfs().equals("OPI")) {
		    displayDDT.setHkfs("一次性还本付息");
		} else if (displayDDT.getHkfs().equals("EPEI")) {
		    displayDDT.setHkfs("等本等息");
		} else {
		    displayDDT.setHkfs("按月返息到期还本");
		}

	    }
	    return lendList;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("showProductsByPage()", e.getMessage());
	    return null;
	}

    }

}
