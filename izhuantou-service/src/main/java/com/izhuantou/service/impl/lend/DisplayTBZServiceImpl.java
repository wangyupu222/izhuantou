package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayTBZ;
import com.izhuantou.dao.lend.DisplayTBZMapper;
import com.izhuantou.service.api.lend.DisplayTBZService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("displayTBZService")
public class DisplayTBZServiceImpl extends BaseServiceImpl<DisplayTBZ> implements DisplayTBZService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayTBZServiceImpl.class);
    @Autowired
    private DisplayTBZMapper displayTBZMapper;

    @Override
    public Pagination<DisplayTBZ> showProductsByPage(Integer page, String sortp) {
	Pagination<DisplayTBZ> pageController = new Pagination<>();
	List<DisplayTBZ> lendList = new ArrayList<DisplayTBZ>();
	List<DisplayTBZ> lendListResult = new ArrayList<DisplayTBZ>();
	try {

	    /** 接受前台当前页参数 */
	    pageController.setCurrentPage(page);
	    /** 接受前台排序参数 */
	    String sorttemp = sortp;
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

	    Integer totalNumber = (int) displayTBZMapper.getPageCount();
	    pageController.setTotalNumber(totalNumber);
	    Integer totalPage = pageController.getTotalPage();

	    if (pageController.getCurrentPage() != null) {
		pageController = new Pagination<DisplayTBZ>(pageController.getCurrentPage(), totalPage, totalNumber);
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lendList = this.displayTBZMapper.findByCondition(strsortword, StartPos, pageController.getPageSize());
		for (DisplayTBZ displayTBZ : lendList) {

		    if (displayTBZ.getCjqx() == 0.5) {
			displayTBZ.setCjqx((double) 15);
		    }

		    if (displayTBZ.getHkfs().equals("OPI")) {
			displayTBZ.setHkfs("一次性还本付息");
		    } else if (displayTBZ.getHkfs().equals("EPEI")) {
			displayTBZ.setHkfs("等本等息");
		    } else {
			displayTBZ.setHkfs("按月返息到期还本");
		    }

		    /** 进度 **/
		    BigDecimal temp_new = new BigDecimal(
			    (Double.parseDouble(String.valueOf(displayTBZ.getHoldingAmount())))
				    / Double.parseDouble(String.valueOf(displayTBZ.getBiddingAmount())) * 100);
		    double tzjd = temp_new.intValue();
		    displayTBZ.setJd(tzjd);

		    lendListResult.add(displayTBZ);
		}
		pageController.setData(lendListResult);
	    } else {
		lendList = this.displayTBZMapper.findByCondition(strsortword, 0, 10);
		for (DisplayTBZ displayTBZ : lendList) {

		    if (displayTBZ.getCjqx() == 0.5) {
			displayTBZ.setCjqx((double) 15);
		    }

		    if (displayTBZ.getHkfs().equals("OPI")) {
			displayTBZ.setHkfs("一次性还本付息");
		    } else if (displayTBZ.getHkfs().equals("EPEI")) {
			displayTBZ.setHkfs("等本等息");
		    } else {
			displayTBZ.setHkfs("按月返息到期还本");
		    }
		    lendListResult.add(displayTBZ);
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
    public List<DisplayTBZ> findTBZList() {
	List<DisplayTBZ> lendList = new ArrayList<DisplayTBZ>();
	List<DisplayTBZ> lendListResult = new ArrayList<DisplayTBZ>();
	try {

	    lendList = this.displayTBZMapper.findList();
	    for (DisplayTBZ displayTBZ : lendList) {

		if (displayTBZ.getCjqx() == 0.5) {
		    displayTBZ.setCjqx((double) 15);
		}

		if (displayTBZ.getHkfs().equals("OPI")) {
		    displayTBZ.setHkfs("一次性还本付息");
		} else if (displayTBZ.getHkfs().equals("EPEI")) {
		    displayTBZ.setHkfs("等本等息");
		} else {
		    displayTBZ.setHkfs("按月返息到期还本");
		}

		/** 进度 **/
		BigDecimal temp_new = new BigDecimal((Double.parseDouble(String.valueOf(displayTBZ.getHoldingAmount())))
			/ Double.parseDouble(String.valueOf(displayTBZ.getBiddingAmount())) * 100);
		double tzjd = temp_new.intValue();
		displayTBZ.setJd(tzjd);

		lendListResult.add(displayTBZ);
	    }
	    return lendListResult;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
    }

}
