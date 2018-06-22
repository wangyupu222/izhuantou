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

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.vo.MyLoanApplyDTO;
import com.izhuantou.damain.vo.MyLoanRecordDTO;
import com.izhuantou.damain.vo.RepayMentDTO;
import com.izhuantou.damain.vo.RepayResultDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.personalCenter.MyLoanMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanApplyMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.service.api.personalCenter.MyloanService;

@Service("myloanService")
public class MyloanServiceImpl implements MyloanService {
	private static final Logger logger = LoggerFactory.getLogger(MyloanServiceImpl.class);
	@Autowired
	private WebP2pLoanApplyMapper loanApplyMapper;
	@Autowired
	private PayCustomerMapper customerMapper;
	@Autowired
	private MyLoanMapper myLoanMapper;
	@Autowired
	private MemberMemberMapper userDao;
	@Autowired
	private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

	@Override
	public String saveloanApply(MyLoanApplyDTO apply) {
		try {
			if (apply != null) {
				String city = "";
				String s_province = apply.getS_province();
				String s_county = apply.getS_county();
				String s_city = apply.getS_city();
				if (s_province != null) {
					if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
							|| s_province.equals("重庆市")) {
						city = s_province + "," + s_county;
					} else {
						city = s_province + "," + s_city + "," + s_county;
					}
				}
				WebP2pLoanApply loanApply = new WebP2pLoanApply();
				loanApply.setMemberOID(apply.getMemberOID());
				loanApply.setUserName(apply.getName());
				loanApply.setIdCard(apply.getCard());
				loanApply.setMobile(apply.getPhone());
				loanApply.setLoanAmount(apply.getMoney());
				loanApply.setLoanTerm(apply.getLoanTerm());
				loanApply.setLoanYT(apply.getLoanYT());
				loanApply.setCity(city);
				loanApply.setRate(apply.getRate());
				loanApply.setOID(StringUtil.getUUID());
				loanApply.setApplyTime(DateUtils.getDateFormatter());
				if (loanApply.getLoanYT().equals("IFD")) {
					loanApply.setLoanYT("i房贷");
				}
				if (loanApply.getLoanYT().equals("IXD")) {
					loanApply.setLoanYT("i薪贷");
				}
				if (loanApply.getLoanYT().equals("IYD")) {
					loanApply.setLoanYT("i意贷");
				}
				if (loanApply.getLoanYT().equals("IBD")) {
					loanApply.setLoanYT("i保单");
				}
				PayCustomer customer = customerMapper.findByMemberOID(loanApply.getMemberOID());
				if (customer.getEntrustRecharge() != null && "01".equals(customer.getEntrustRecharge())) {
					loanApplyMapper.insertData(loanApply);
				} else {
					return "-1";// 未进行委托授权
				}
				return "1";
			}
		} catch (Exception e) {
			logger.error("findMessage()", e.getMessage());
		}
		return null;
	}

	@Override
	public Pagination<LoanApplyRecordDTO> findLoanApplyByMemberOID(String memberOID, Integer page) {
		try {
			Pagination<LoanApplyRecordDTO> pageController = new Pagination<>();
			if (page != null) {
				pageController.setCurrentPage(page);
			}
			// 查询所有条数
			Integer totalNumber = loanApplyMapper.findCount(memberOID);
			pageController.setTotalNumber(totalNumber);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<LoanApplyRecordDTO> list = myLoanMapper.findLoanApplyByMemberOID(memberOID, startIndex, pageSize);
			PayCustomer customer = customerMapper.findByMemberOID(memberOID);
			for (LoanApplyRecordDTO apply : list) {
				String addtime=DateUtils.formartDate(apply.getAddDateTime(),"yyyy-MM-dd");
				apply.setAddDateTime(addtime);
				apply.setHandBase(customer.getHandBase());
			}
			pageController.setData(list);
			return pageController;
		} catch (Exception e) {
			logger.error("findLoanApplyByMemberOID(String memberOID, Integer page)", e.getMessage());
		}
		return null;
	}

	@Override
	public PayCustomer findInfo(String memberOID) {
		PayCustomer customer = new PayCustomer();
		try {
			customer = customerMapper.findByMemberOID(memberOID);
			return customer;
		} catch (Exception e) {
			logger.error("findInfo(String memberOID)", e.getMessage());
		}
		return null;
	}

	@Override
	public String updateLoanApply(MyLoanApplyDTO apply) {
		try {
			if (apply != null) {

				String city = "";
				String s_province = apply.getS_province();
				String s_county = apply.getS_county();
				String s_city = apply.getS_city();
				if (s_province != null) {
					if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
							|| s_province.equals("重庆市")) {
						city = s_province + "," + s_county;
					} else {
						city = s_province + "," + s_city + "," + s_county;
					}
				}
				WebP2pLoanApply loanApply = loanApplyMapper.findByOID(apply.getLoanOID());
				loanApply.setMemberOID(apply.getMemberOID());
				loanApply.setUserName(apply.getName());
				loanApply.setIdCard(apply.getCard());
				loanApply.setMobile(apply.getPhone());
				loanApply.setLoanAmount(apply.getMoney());
				loanApply.setLoanTerm(apply.getLoanTerm());
				loanApply.setLoanYT(apply.getLoanYT());
				loanApply.setCity(city);
				loanApply.setRate(apply.getRate());
				loanApply.setOID(apply.getLoanOID());

				if (loanApply.getLoanYT().equals("IFD")) {
					loanApply.setLoanYT("i房贷");
				}
				if (loanApply.getLoanYT().equals("IXD")) {
					loanApply.setLoanYT("i薪贷");
				}
				if (loanApply.getLoanYT().equals("IYD")) {
					loanApply.setLoanYT("i意贷");
				}
				if (loanApply.getLoanYT().equals("IBD")) {
					loanApply.setLoanYT("i保单");
				}
				loanApplyMapper.update(loanApply);
				return "1";
			}
		} catch (Exception e) {
			logger.error("updateLoanApply(MyLoanApplyDTO apply)", e.getMessage());
		}
		return "-1";
	}

	@Override
	public LoanApplyRecordDTO findDataByOID(String memberOID, String OID) {
		try {
			LoanApplyRecordDTO upLendRecord = new LoanApplyRecordDTO();
			upLendRecord = myLoanMapper.findBYcondition(memberOID, OID);
			return upLendRecord;
		} catch (Exception e) {
			logger.error("ufindDataByOID(String memberOID, String OID)", e.getMessage());
		}
		return null;
	}

	@Override
	public Map<String, Object> myLoanRecord(String memberOID) {
		try {
			Map<String, Object> map = new HashMap<>();
			// xyType参数,用于向前台传递贷款产品协议类型 1:普通协议 2:房抵协议 3:车抵协议
			List<MyLoanRecordDTO> Lendlist = myLoanMapper.findLoanRecordByMemberOID(memberOID);
			String name = userDao.findUserByOID(memberOID).getName();
			String xyType = "1";
			int space = 0;
			for (MyLoanRecordDTO myLendRecord : Lendlist) {
				if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())
						&& StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
					// 时间判断 返回 1,-1,0
					space = DateUtils.compare_date(myLendRecord.getLoanDay(), myLendRecord.getNextDay());
				} else {
					space = 2;
				}
				if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())) {
					myLendRecord.setLoanDay(DateUtils.formartDate(myLendRecord.getLoanDay(), "yyyy-MM-dd"));
				}

				if (StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
					myLendRecord.setNextDay(DateUtils.formartDate(myLendRecord.getNextDay(), "yyyy-MM-dd"));
				}
				myLendRecord.setSpace(space);
			}
			if (Lendlist.size() == 0) {
				Lendlist = myLoanMapper.findDDTLoanRecordByPhone(name);
				for (MyLoanRecordDTO myLendRecord : Lendlist) {
					if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())
							&& StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
						space = DateUtils.compare_date(myLendRecord.getLoanDay(), myLendRecord.getNextDay());
					} else {
						space = 2;
					}
					if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())) {
						myLendRecord.setLoanDay(DateUtils.formartDate(myLendRecord.getLoanDay(), "yyyy-MM-dd"));
					}
					if (StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
						myLendRecord.setNextDay(DateUtils.formartDate(myLendRecord.getNextDay(), "yyyy-MM-dd"));
					}
					myLendRecord.setSpace(space);
				}
				xyType = "2";
			}

			if (Lendlist == null || Lendlist.size() == 0) {
				Lendlist = myLoanMapper.findCDLoanRecordByPhone(name);
				for (MyLoanRecordDTO myLendRecord : Lendlist) {
					if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())
							&& StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
						space = DateUtils.compare_date(myLendRecord.getLoanDay(), myLendRecord.getNextDay());
					} else {
						space = 2;
					}
					if (StringUtil.isNotEmpty(myLendRecord.getLoanDay())) {
						myLendRecord.setLoanDay(DateUtils.formartDate(myLendRecord.getLoanDay(), "yyyy-MM-dd"));
					}
					if (StringUtil.isNotEmpty(myLendRecord.getNextDay())) {
						myLendRecord.setNextDay(DateUtils.formartDate(myLendRecord.getNextDay(), "yyyy-MM-dd"));
					}
					myLendRecord.setSpace(space);
				}
				xyType = "3";
			}
			map.put("xyType", xyType);
			map.put("data", Lendlist);
			return map;
		} catch (Exception e) {
			logger.error("MylendRecord(String memberOID)" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<RepayResultDTO> findMyRepayment(String memberOID) {
		try {
			List<RepayMentDTO> dtoc = myLoanMapper.findRepayByMemberOID(memberOID);
			if (dtoc.size() == 0) {
				dtoc = myLoanMapper.findZCByMemberOID(memberOID);
			}
			int HNow = Integer.valueOf(DateUtils.getHour());// 获取当前日期的小时
			int MNow = Integer.valueOf(DateUtils.getMinute());// 获取当前日期的分钟
			BigDecimal alllx = new BigDecimal(0.0);// 剩余全部利息字段声明
			BigDecimal allbj = new BigDecimal(0);// 剩余本金
			List<RepayResultDTO> resultList = new ArrayList<RepayResultDTO>();

			if (dtoc.size() > 0) {
				for (RepayMentDTO repayMent : dtoc) {
					alllx = alllx.add(repayMent.getInterestMoney());
					allbj = allbj.add(repayMent.getPrincipalMoney());
				}

				for (RepayMentDTO repayMent : dtoc) {
					RepayResultDTO result = new RepayResultDTO();
					// 修改日期格式
					String repayDate = repayMent.getRepayDate();
					result.setNextRepayTime(repayDate);
					String nowday = DateUtils.getDateFormatter();
					int space = DateUtils.daysBetween(repayDate, nowday);
					String yuqiflag = null;
					if (space >= 1) {
						yuqiflag = "yuqi";
					} else if (space == 0 && HNow >= 22 && MNow >= 0) {
						yuqiflag = "yuqi";
					} else {
						yuqiflag = "zchk";
					}
					result.setYuqiFlag(yuqiflag);
					// 借款的金额
					result.setMoney(repayMent.getMoney());
					result.setAllmoney(repayMent.getAllmoney());
					result.setDebitRate(repayMent.getDebitRate());
					result.setBusinessName(repayMent.getBusinessName());
					result.setTerm(repayMent.getTerm());
					result.setBusinessOID(repayMent.getBusinessOID());
					result.setPlanOID(repayMent.getPlanOID());
					String loantime = DateUtils.formartDate(repayMent.getLoandatetime(), "yyyy-MM-dd");
					result.setLoanTime(loantime);
					if ("zchk".equals(yuqiflag)) {
						String planRepayDay = repayMent.getRepayDate();
						String nowDay = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
						int lmonth = ToolsDatas.gainMonthCountWithDay(nowDay, planRepayDay);
						if (lmonth <= 1) {
							result.setHkFlag("zchk");
						}

						// 剩余本金
						BigDecimal surplusPrincipal = repayMent.getSurplusPrincipal();
						// 本期本金
						BigDecimal principalMoney = repayMent.getPrincipalMoney();

						BigDecimal yhbj = surplusPrincipal.add(principalMoney);
						// 应还本金
						result.setYhbj(yhbj);
						// 当期利息
						result.setInterestMoney(repayMent.getInterestMoney());
						// 罚息
						result.setDefaultInterest(repayMent.getInterestMoney());
						// 咨询服务费
						result.setServiceMoney(repayMent.getServiceMoney());
						// 平台管理费
						result.setManageMoney(repayMent.getManageMoney());
						String loanProductRateInfoID = "";
						List<WebP2pPackageBiddingMainContentRuning> mainrun = packageBiddingMainContentRuningMapper
								.findByLoanNum(repayMent.getBusinessName());
						if (mainrun.size() > 0) {
							loanProductRateInfoID = mainrun.get(0).getLoanProductRateInfoID();
						}
						if (StringUtil.isEmpty(loanProductRateInfoID)) {
							WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper
									.findByCondition(repayMent.getBusinessName());
							if (novice != null) {
								loanProductRateInfoID = novice.getLoanProductRateInfoID();
							}
						}
						if (StringUtil.isEmpty(loanProductRateInfoID)) {
							WebP2pNormalBiddingRuning normal = normalBiddingRuningMapper
									.findByLoan(repayMent.getBusinessName());
							if (normal != null) {
								loanProductRateInfoID = normal.getLoanProductRateInfoID();
							}
						}
						// 获取对应违约金利率
						BigDecimal beforeRate = loanProductRateInfoMapper.findByOID(loanProductRateInfoID)
								.getBeforeRate();
						BigDecimal penalty = repayMent.getMoney().multiply(beforeRate);
						result.setPenalty(penalty);
						// 应还总额 应还本金 + 本期利息 + 咨询费 + 平台管理费 + 违约金 + 罚息
						BigDecimal countMoney = yhbj.add(repayMent.getInterestMoney()).add(repayMent.getServiceMoney())
								.add(repayMent.getManageMoney()).add(penalty).add(repayMent.getInterestMoney());
						result.setCountMoney(countMoney);
					}

					if ("yuqi".equals(yuqiflag)) {
						// 剩余本金
						BigDecimal surplusPrincipal = repayMent.getSurplusPrincipal();
						// 本期本金
						BigDecimal principalMoney = repayMent.getPrincipalMoney();
						// 全部的应还本金 未还的本金
						BigDecimal sybj = surplusPrincipal.add(principalMoney);

						String planRepayDay = repayMent.getRepayDate();
						String realRepayDay = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
						// 逾期，计算逾期天数
						int lday = DateUtils.daysBetween(planRepayDay, realRepayDay);
						if (lday == 0) {
							lday++; // 超过还款日的22点就算逾期
						}
						// 逾期期数
						int lmonth = ToolsDatas.gainMonthCountWithDay(planRepayDay, realRepayDay);
						if (lmonth == 0) {
							lmonth += 1;
						}
						// 逾期的本金
						BigDecimal yhje = principalMoney.multiply(new BigDecimal(lmonth));
						result.setYhbj(yhje);
						// 逾期管理费用计算 全部剩余本金*0.06%*逾期天数*0.1%
						BigDecimal yqglf = sybj.multiply(new BigDecimal(0.0006)).multiply(new BigDecimal(lday))
								.setScale(2, BigDecimal.ROUND_HALF_EVEN);
						result.setYqglf(yqglf);

						// 逾期罚息（全部剩余本金+全部剩余利息）*逾期天数*0.001
						BigDecimal yqfx = allbj.add(alllx).multiply(new BigDecimal(0.001))
								.multiply(new BigDecimal(lday)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						result.setDefaultInterest(yqfx);
						// 逾期期数* 当期利息
						BigDecimal interestMoney = repayMent.getInterestMoney().multiply(new BigDecimal(lmonth));
						result.setInterestMoney(interestMoney);
						// 违约金
						BigDecimal wyj = new BigDecimal(500);
						result.setPenalty(wyj);

						// 咨询服务费
						BigDecimal serviceMoney = repayMent.getServiceMoney().multiply(new BigDecimal(lmonth));
						result.setServiceMoney(serviceMoney);
						// 平台管理费
						BigDecimal manageMoney = repayMent.getManageMoney().multiply(new BigDecimal(lmonth));
						result.setManageMoney(manageMoney);

						BigDecimal countMoney = yhje.add(interestMoney).add(serviceMoney).add(manageMoney).add(wyj)
								.add(yqglf).add(yqfx);
						result.setCountMoney(countMoney);
					}
					resultList.add(result);
					break;
				}
			}
			return resultList;
		} catch (Exception e) {
			logger.error("RepayMentDTO findMyRepayment(String memberOID)" + e.getMessage());
			return null;
		}
	}

}
