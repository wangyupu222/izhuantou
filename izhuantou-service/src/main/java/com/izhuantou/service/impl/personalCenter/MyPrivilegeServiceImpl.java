package com.izhuantou.service.impl.personalCenter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.damain.vo.MyInvitationDaoDTO;
import com.izhuantou.damain.vo.MyPrivilegeDaoDTO;
import com.izhuantou.dao.PropPrivilegeMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.personalCenter.MyPrivilegeMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.personalCenter.MyPrivilegeService;

@Service("myPrivilegeService")
public class MyPrivilegeServiceImpl implements MyPrivilegeService {
	private static final Logger logger = LoggerFactory.getLogger(MyPrivilegeServiceImpl.class);
	@Autowired
	private MyPrivilegeMapper myPrivilegeMapper;
	@Autowired
	private PayPrivilegeSpecialpsMapper PAYPrivilegeSpecialPSMapper;
	@Autowired
	private PayDebitCreditMapper debitCreditMapper;
	@Autowired
	private PayCashPoolMapper payCashPoolDao;
	@Autowired
	private PayTransferReturnMapper transferReturnMapper;
	@Autowired
	private PayCustomerTyjMapper customertyjMapper;
	@Autowired
	private MemberMemberMapper userDao;
	@Autowired
	private PropPrivilegeMapper propPrivilegeMapper;

	/**
	 * 根据类型查看可用红包特权
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findCanUseHBByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCanUseTQRowCount(memberOID, 0);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto = myPrivilegeMapper.findCanUseTQByMemberOIDAndType(memberOID, 0, startIndex,
					pageSize);
			pageController.setData(dto);

			return pageController;
		} catch (Exception e) {
			logger.error("findCanUseHBByMemberOID(String memberOID, Integer currentPage)", e.getMessage());
			return null;
		}
	}

	/**
	 * 查看已使用的红包特权
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findUseHBByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCannotUseTQRowCountIsUse(memberOID, 1);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto = myPrivilegeMapper.findCannotUseTQByMemberOIDIsUse(memberOID, 0, startIndex,
					pageSize);

			pageController.setData(dto);
			return pageController;
		} catch (Exception e) {
			logger.error("findUseHBByMemberOID(String memberOID, Integer currentPage)", e.getMessage());
			return null;
		}

	}

	/**
	 * 已过期不可用红包特权
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findCannotUseHBByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCannotUseTQRowCountIsGQ(memberOID, 0);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto = myPrivilegeMapper.findCannotUseTQByMemberOIDIsGQ(memberOID, 0, startIndex,
					pageSize);
			pageController.setData(dto);
			return pageController;
		} catch (Exception e) {
			logger.error("findCannotUseHBByMemberOID(String memberOID, Integer currentPage)", e.getMessage());
			return null;
		}
	}

	/**
	 * 查看可用加息券
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findCanUseJxqByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCanUseTQRowCount(memberOID, 1);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto = myPrivilegeMapper.findCanUseTQByMemberOIDAndType(memberOID, 1, startIndex,
					pageSize);
			if (dto.size() > 0) {
				for (MyPrivilegeDaoDTO daodto : dto) {
					String psOID = daodto.getPsOID();
					if (StringUtil.isNotEmpty(psOID)) {
						PayPrivilegeSpecialps ps = PAYPrivilegeSpecialPSMapper.findByOID(psOID);// 遍历结果集，如果psOID存在说明是万能券,需要对出借金额进行替换
						daodto.setLowAmount(ps.getLowMoney());

					}
					daodto.setStartDate(daodto.getStartDate().substring(0, 10));
					daodto.setEndDate(daodto.getEndDate().substring(0, 10));
				}
				pageController.setData(dto);
			}
			return pageController;
		} catch (Exception e) {
			logger.error("findCanUseJxqByMemberOID(String memberOID, Integer currentPage) ", e.getMessage());
			return null;
		}

	}

	/**
	 * 查看已使用加息券
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findUseJxqByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCannotUseTQRowCountIsUse(memberOID, 1);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto = myPrivilegeMapper.findCannotUseTQByMemberOIDIsUse(memberOID, 1, startIndex,
					pageSize);
			if (dto.size() > 0) {
				for (MyPrivilegeDaoDTO daodto : dto) {
					String psOID = daodto.getPsOID();
					if (StringUtil.isNotEmpty(psOID)) {
						PayPrivilegeSpecialps ps = PAYPrivilegeSpecialPSMapper.findByOID(psOID);// 遍历结果集，如果psOID存在说明是万能券,需要对出借金额进行替换
						daodto.setLowAmount(ps.getLowMoney());
						daodto.setUseTerm(ps.getJXDay());
					}
					daodto.setStartDate(daodto.getStartDate().substring(0, 10));
					daodto.setEndDate(daodto.getEndDate().substring(0, 10));
				}
				pageController.setData(dto);
			}
			return pageController;
		} catch (Exception e) {
			logger.error("findUseJxqByMemberOID(String memberOID, Integer currentPage) ", e.getMessage());
			return null;
		}

	}

	/**
	 * 查看已过期不可用加息券
	 */
	@Override
	public Pagination<MyPrivilegeDaoDTO> findCannotUseJxqByMemberOID(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyPrivilegeDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getCannotUseTQRowCountIsGQ(memberOID, 1);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(9);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyPrivilegeDaoDTO> dto =
					myPrivilegeMapper.findCannotUseTQByMemberOIDIsGQ(memberOID, 1, startIndex,pageSize);
			if (dto.size() > 0) {
				for (MyPrivilegeDaoDTO daodto : dto) {
					String psOID = daodto.getPsOID();
					if (StringUtil.isNotEmpty(psOID)) {
						PayPrivilegeSpecialps ps = PAYPrivilegeSpecialPSMapper.findByOID(psOID);// 遍历结果集，如果psOID存在说明是万能券,需要对出借金额进行替换
						daodto.setLowAmount(ps.getLowMoney());
						daodto.setUseTerm(ps.getJXDay());
					}
					daodto.setStartDate(daodto.getStartDate().substring(0, 10));
					daodto.setEndDate(daodto.getEndDate().substring(0, 10));
				}
				pageController.setData(dto);
			}
			return pageController;
		} catch (Exception e) {
			logger.error("findUseJxqByMemberOID(String memberOID, Integer currentPage) ", e.getMessage());
			return null;
		}

	}

	/**
	 * 查看邀请记录
	 */
	@Override
	public Pagination<MyInvitationDaoDTO> findInvitationRecord(String memberOID, Integer currentPage) {
		try {
			// 分页信息
			Pagination<MyInvitationDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = myPrivilegeMapper.getInvitationRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 设置每页显示5条
			pageController.setPageSize(10);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MyInvitationDaoDTO> dto = myPrivilegeMapper.findInvitationRecord(memberOID, startIndex, pageSize);
			if (dto.size() > 0) {
				for (MyInvitationDaoDTO daodto : dto) {
					daodto.setZcsj(daodto.getZcsj().substring(0, 10));
					daodto.setAddDateTime(daodto.getAddDateTime().substring(0, 10));
				}
			}
			pageController.setData(dto);
			return pageController;
		} catch (Exception e) {
			logger.error("findInvitationRecord(String memberOID, Integer currentPage) ", e.getMessage());
			return null;
		}

	}

	/**
	 * 体验金
	 */
	@Override
	public Map<String, Object> findExperience(String memberOID) {
		try {
			String dqdata = "";
			String tydata = "";
			Map<String, Object> resultMap = new HashMap<String, Object>();
			int n = 0;
			List<PayDebitCredit> debitCreditList = debitCreditMapper.findByOutMember(memberOID);
			List<PayCashPool> payCashPool = payCashPoolDao.gainByMemberOID(memberOID);
			List<PayTransferReturn> transferReturnList = transferReturnMapper.findByCondition(memberOID);
			if (debitCreditList.size() == 0 && payCashPool.size() == 0 && transferReturnList.size() == 0) {
				n = 0;
			} else {
				n = 1;
			}
			// 体验金
			List<CustomerTyjDTO> tyjdtoc = customertyjMapper.findByMemberOID(memberOID);
			// 用户信息
			MemberMember member = userDao.findUserByOID(memberOID);
			Date zcsjTime = member.getAddDateTime();
			Map<String, String> prmap = propPrivilegeMapper.findPrivilege();
			String sdate = prmap.get("TYJSXTime");
			String TYJDate = prmap.get("TYJDate");
			boolean flag = zcsjTime.after(DateUtils.getDate(sdate));
			if (flag) {
				if (n > 0) {
					// 出借其他产品
					resultMap.put("iscj", "true");
				} else {
					resultMap.put("iscj", "false");
				}

				if (tyjdtoc.size() > 0) {
					// 出借体验标
					resultMap.put("istyj", "true");
				} else {
					resultMap.put("istyj", "false");
				}
				// 出借过其他产品，没有出借过体验标
				resultMap.put("isdq", "0");
				resultMap.put("dqsj", "0");
				resultMap.put("tysj", "0");
				resultMap.put("isjs", "0");
				if (n > 0 && tyjdtoc.size() == 0) {
					resultMap.put("isTyb", "false");
					resultMap.put("kyye", "0.00");
					resultMap.put("tyje", "0.00");
					resultMap.put("dssy", "0.00");
					resultMap.put("yssy", "0.00");
				}
				// 没有出借其他产品，没有出借体验标
				if (n == 0 && tyjdtoc.size() == 0) {
					resultMap.put("isTyb", "true");
					resultMap.put("kyye", "18888.00");
					resultMap.put("tyje", "0.00");
					resultMap.put("dssy", "0.00");
					resultMap.put("yssy", "0.00");
				}
				// 没有出借其他产品，出借过体验标
				if (n == 0 && tyjdtoc.size() > 0) {

					resultMap.put("isTyb", "true");
					resultMap.put("kyye", "0.00");
					resultMap.put("tyje", "18888.00");
					resultMap.put("yssy", "0.00");
					for (CustomerTyjDTO dto : tyjdtoc) {
						int ts = 1;
						Date tysj = dto.getAddDateTime();
						boolean flag1 = tysj.after(DateUtils.getDate(TYJDate));
						if (!flag1) {
							ts = 2;
						}
						String tysjTime = DateUtils.formatDate(tysj.getTime());
						String dqTime = DateUtils.gainPlusAndDateTime(tysjTime, ts, 0);
						tydata = DateUtils.gainPlusAndDay(tysjTime, 0, 0);
						dqdata = DateUtils.gainPlusAndDay(tysjTime, ts, 0);
						resultMap.put("tyjsy", dto.getTyjPrice());
						if (DateUtils.getJustDate(dqTime).getTime() < System.currentTimeMillis()) {
							resultMap.put("isdq", "true");
						} else {
							resultMap.put("isdq", "false");
						}
						resultMap.put("dssy", dto.getTyjPrice());
						resultMap.put("dqsj", dqdata);
						resultMap.put("tysj", tydata);
					}
				}
				// 出借过其他产品，出借过体验标
				if (n > 0 && tyjdtoc.size() > 0) {
					for (CustomerTyjDTO dto : tyjdtoc) {
						int ts = 1;
						Date tysj = dto.getAddDateTime();
						boolean flag1 = tysj.after(DateUtils.getDate(TYJDate));
						if (!flag1) {
							ts = 2;
						}
						String tysjTime = DateUtils.formatDate(tysj.getTime());
						String dqTime = DateUtils.gainPlusAndDateTime(tysjTime, ts, 0);
						tydata = DateUtils.gainPlusAndDay(tysjTime, 0, 0);
						dqdata = DateUtils.gainPlusAndDay(tysjTime, ts, 0);
						resultMap.put("tyjsy", dto.getTyjPrice());
						resultMap.put("dqsj", dqdata);
						resultMap.put("tysj", tydata);
						resultMap.put("kyye", "0.00");
						resultMap.put("tyje", "18888.00");

						if (DateUtils.getJustDate(dqTime).getTime() < System.currentTimeMillis()) {
							resultMap.put("isdq", "true");
						} else {
							resultMap.put("isdq", "false");
						}
						if ("0".equals((String) dto.getIsUsed())) {
							resultMap.put("isjs", "false");
							// 判断出借产品是不是新手
							if (payCashPool.size() > 0) {
								resultMap.put("isxs", "false");
							} else {
								resultMap.put("isxs", "true");
							}
							resultMap.put("dssy", dto.getTyjPrice());
							resultMap.put("yssy", "0.00");
						} else {
							resultMap.put("isjs", "true");
							resultMap.put("yssy", dto.getTyjPrice());
							resultMap.put("dssy", "0.00");
							resultMap.put("tyje", "0.00");
						}
					}
				}
			} else {
				resultMap.put("iscj", "new");
				resultMap.put("yssy", "0.00");
				resultMap.put("dssy", "0.00");
				resultMap.put("tyje", "0.00");
				resultMap.put("kyye", "0.00");
				resultMap.put("istyj", "new");
			}
			return resultMap;
		} catch (Exception e) {
			logger.error("findExperience(String memberOID)", e.getMessage());
			return null;
		}

	}

}
