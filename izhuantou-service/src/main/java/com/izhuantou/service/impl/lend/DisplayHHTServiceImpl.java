package com.izhuantou.service.impl.lend;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	public Pagination<DisplayHHT> showProductsByPage(Integer page, String status,String sortp) {

		List<DisplayHHT> lendList = new ArrayList<DisplayHHT>();
		List<DisplayHHT> lendListResult = new ArrayList<DisplayHHT>();
		Pagination<DisplayHHT> pageController = new Pagination<>();
		try {
			StringBuilder strsortword = new StringBuilder("");
			if (!StringUtils.isBlank(sortp)) {
				String[] words = sortp.split("2");
				strsortword.append(",allbidding.");
				strsortword.append(words[0]);
				strsortword.append("  ");
				strsortword.append(words[1]);
			} else {
				strsortword.append("  ");
			}
			sortp=strsortword.toString();
			
			if (page != null) {
				pageController.setCurrentPage(page);
			}
			if (StringUtils.isNotEmpty(status) && "portion".equals(status)) {

				Integer totalNumber = displayHHTMapper.getPageZtCount();
				pageController.setTotalNumber(totalNumber);

				Integer totalPage = pageController.getTotalPage();
				pageController = new Pagination<DisplayHHT>(pageController.getCurrentPage(), totalPage, totalNumber);
				// 起始位置
				Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
				// 每页条数
				Integer pageSize = pageController.getPageSize();
				lendList = displayHHTMapper.findByConditionZt(startIndex, pageSize,sortp);

			} else {
				Integer totalNumber = displayHHTMapper.getPageCount();
				pageController.setTotalNumber(totalNumber);
				Integer totalPage = pageController.getTotalPage();
				pageController = new Pagination<DisplayHHT>(pageController.getCurrentPage(), totalPage, totalNumber);
				// 起始位置
				Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
				// 每页条数
				Integer pageSize = pageController.getPageSize();
				lendList = displayHHTMapper.findByCondition(startIndex, pageSize,sortp);
			}

			for (DisplayHHT displayHHT : lendList) {

				if (displayHHT.getHkfs().equals("OPI")) {
					displayHHT.setHkfs("一次性还本付息");
				} else if (displayHHT.getHkfs().equals("EPEI")) {
					displayHHT.setHkfs("等本等息");
				} else {
					displayHHT.setHkfs("按月返息到期还本");
				}
				lendListResult.add(displayHHT);

			}
			pageController.setData(lendListResult);
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
