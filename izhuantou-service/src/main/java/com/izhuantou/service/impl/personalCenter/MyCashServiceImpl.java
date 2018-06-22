package com.izhuantou.service.impl.personalCenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayChangeCard;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.pay.PayCustomerOperation;
import com.izhuantou.damain.vo.MyChangeDTO;
import com.izhuantou.damain.vo.PayCustomerDTO;
import com.izhuantou.dao.pay.PayChangeCardMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayCustomerOperationMapper;
import com.izhuantou.dao.personalCenter.MyCashMapper;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 我的消息投实现类
 * 
 * @author yangbosen
 *
 */
@Service("myCashService")
@Transactional
public class MyCashServiceImpl extends BaseServiceImpl<PayCustomerBusiness> implements MyCashService {
	private static final Logger logger = LoggerFactory.getLogger(MyCashServiceImpl.class);
	@Autowired
	private MyCashMapper myCashMapper;

	@Autowired
	private PayChangeCardMapper payChangeCardDao;

	@Autowired
	private PayCustomerOperationMapper payCustomerOperationDao;

	@Autowired
	private PayCustomerMapper PayCustomerMapper;

	/**
	 * 资金流水列表
	 */
	@Override
	public Pagination<PayCustomerBusiness> findMessage(Integer page, String memberOID, String strDate, String endDate) {

		Pagination<PayCustomerBusiness> pageController = new Pagination<>();
		try {
			/** 接受前台当前页参数 */
			if (page != null) {
				pageController.setCurrentPage(page);
			}

			Date startTime = null;
			Date endTime = null;
			if (StringUtil.isNotEmpty(strDate) && StringUtil.isNotEmpty(endDate)) {
				startTime = DateUtils.getJustDate(strDate);

				endTime = DateUtils.getJustDate(endDate);

			} else if (StringUtil.isEmpty(strDate) && StringUtil.isNotEmpty(endDate)) {
				startTime = DateUtils.getJustDate("1999-01-01");
				endTime = DateUtils.getJustDate(endDate);

			} else if (StringUtil.isNotEmpty(strDate) && StringUtil.isEmpty(endDate)) {
				startTime = DateUtils.getJustDate(strDate);
				endTime = new Date();

			} else {
				// 查询全部
				startTime = DateUtils.getJustDate("1999-01-01");
				endTime = new Date();
			}
			Integer totalnum = myCashMapper.findcountByCondition(memberOID, startTime, endTime);
			pageController.setTotalNumber(totalnum);
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			Integer pageSize = pageController.getPageSize();

			List<PayCustomerBusiness> list = myCashMapper.findCashDetialByCondition(memberOID, startIndex, pageSize,
					startTime, endTime);
			for (PayCustomerBusiness business : list) {
				business.setSj(DateUtils.formatDate(business.getAddDateTime().getTime()));
				if (business.getMoney() == null) {
					business.setMoney(new BigDecimal("0"));
				}
			}
			pageController.setData(list);
		} catch (Exception e) {
			logger.error("findMessage()", e.getMessage());
			return null;
		}
		return pageController;
	}

	/**
	 * 查银行卡的信息变更列表
	 */
	@Override
	public Map<String, Object> findMyBanckCard(String memberOID, Integer currentPage) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			// 分页信息
			Pagination<PayChangeCard> pageController = new Pagination<>();
			/** 接受前台当前页参数 */
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = this.payChangeCardDao.getRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();
			// 获取查询记录
			List<PayChangeCard> change = payChangeCardDao.findChangeCardList(memberOID, startIndex, pageSize);

			List<MyChangeDTO> changeCard = new ArrayList<MyChangeDTO>();
			for (PayChangeCard pay : change) {
				MyChangeDTO dto = new MyChangeDTO();
				Date dte = DateUtils.getDate(pay.getAddDateTime().getTime(), "");
				String addDate = DateUtils.formatDate(dte.getTime(), "");
				dto.setAddDateTime(addDate);
				dto.setRequestStatus(pay.getRequestStatus());
				dto.setSuccessStatus(pay.getSuccessStatus());
				changeCard.add(dto);
			}
			// 用来返回卡的信息
			Map<String, Object> map = new HashMap<String, Object>();
			if (changeCard.size() > 0) {
				map.put("changCardList", changeCard);
				map.put("changeCardInfo", "true");
			} else {
				map.put("changeCardInfo", "false");
			}
			map.put("Pagination", pageController);
			return map;

		} catch (Exception e) {
			logger.error(" findMyBanckCard(String memberOID)", e.getMessage());
			return null;
		}

	}

	/**
	 * 银行卡信息
	 */
	@Override
	public Map<String, Object> findMyBanckMessage(String memberOID) {
		// 用来返回卡的信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
			if (customer != null) {
				String bankNumber = customer.getBankNumber();
				// bankNumber = "尾号：" + bankNumber.substring(15, 19);
				int length = bankNumber.length();
				if (length == 16) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{8}(\\w{4})", "$1********$2");
				} else if (length == 17) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{9}(\\w{4})", "$1********$2");
				} else if (length == 18) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1********$2");
				} else if (length == 19) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{11}(\\w{4})", "$1********$2");
				} else if (length == 20) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{12}(\\w{4})", "$1********$2");
				} else if (length == 21) {
					bankNumber = bankNumber.replaceAll("(\\d{4})\\d{13}(\\w{4})", "$1********$2");
				}
				map.put("bankNumber", bankNumber);
				map.put("nameCN", customer.getNameCN());
				map.put("bankCode", customer.getBankCode());
				map.put("isCreate", "true");
				return map;
			} else {
				map.put("isCreate", "false");
				return map;
			}
		} catch (Exception e) {
			logger.error("findMyBanckMessage(String memberOID)", e.getMessage());
			return null;
		}
	}

	/**
	 * 查询银行卡充值记录充值或体现记录
	 */
	@Override
	public Map<String, Object> findRechargeRecord(String memberOID, String state, Integer currentPage) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			// 分页信息
			Pagination<PayCustomerOperation> pageController = new Pagination<>();
			// 返回分页信息和内容
			Map<String, Object> map = new HashMap<String, Object>();

			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = payCustomerOperationDao.getRowCount(memberOID, state);
			pageController.setTotalNumber(rows);
			// 设置每页显示12条
			pageController.setPageSize(12);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();
			// 获取查询记录
			List<PayCustomerOperation> select = payCustomerOperationDao.findRechargeRecord(memberOID, state, startIndex,
					pageSize);
			// 用于返回
			List<PayCustomerDTO> dto = new ArrayList<PayCustomerDTO>();
			for (PayCustomerOperation pco : select) {
				PayCustomerDTO payCustomer = new PayCustomerDTO();

				String addDate = DateUtils.formatDate(pco.getAddDateTime(), "");
				payCustomer.setAddDateTime(addDate);

				payCustomer.setContent(pco.getContent());

				payCustomer.setMoney(pco.getMoney());

				payCustomer.setRequestID(pco.getRequestID());

				dto.add(payCustomer);
			}
			map.put("Pagination", pageController);
			map.put("RechargeList", dto);

			return map;
		} catch (Exception e) {
			logger.error("findRechargeRecord(String memberOID, Integer currentPage) ", e.getMessage());
			return null;
		}
	}

	/**
	 * 提现或充值页面数据
	 */
	@Override
	public Map<String, Object> findMyCardmessage(String memberOID) {
		try {
			PayCustomer payCustomer = PayCustomerMapper.findByMemberOID(memberOID);
			Map<String, Object> map = new HashMap<String, Object>();
			if (payCustomer != null) {
				map.put("useMoney", payCustomer.getUseMoney());
				map.put("mobile", "*******" + payCustomer.getMobile().substring(7));
				String freemoney = payCustomer.getFreeline().toString();
				if (StringUtil.isNotEmpty(freemoney)) {
					map.put("freeline", payCustomer.getFreeline());
				} else {
					map.put("freeline", 0.00);
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("findMyCardmessage(String memberOID) ", e.getMessage());
			return null;
		}

	}

}
