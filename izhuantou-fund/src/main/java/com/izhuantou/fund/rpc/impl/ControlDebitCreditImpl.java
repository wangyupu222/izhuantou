package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.MapUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.vo.bidding.DataPackageDTO;
import com.izhuantou.damain.vo.bidding.ProductInfoDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlCashPool;
import com.izhuantou.fund.rpc.api.ControlCustomerBusiness;
import com.izhuantou.fund.rpc.api.ControlDebitCredit;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.message.SendMessageService;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("controlDebitCredit")
public class ControlDebitCreditImpl extends BaseServiceImpl<PayDebitCredit> implements ControlDebitCredit {

	private static final Logger logger = LoggerFactory.getLogger(ControlDebitCreditImpl.class);
	@Autowired
	private ControlCashPool controlCashPool;
	@Autowired
	private ControlPayService controlPay;
	@Autowired
	private ControlCustomerBusiness controlCustomerBusiness;
	@Autowired
	private PayDebitCreditMapper debitCreditMapper;
	@Autowired
	private PayTransferReturnMapper transferReturnMapper;
	@Autowired
	private MemberMemberMapper userMapper;
	@Autowired
	private PayReturnPlanMapper payReturnPlanMapper;
	@Autowired
	private PayRepayPlanMapper repayPlanMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
	@Autowired
	private PayCashPoolMapper payCashPoolMapper;
	@Autowired
	private PayCustomerMapper customerMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private MessageContentBusinessMapper messageContentBusinessMapper;
	@Autowired
	private MessageSmsHistoryMapper messageSmsHistoryMapper;
	@Autowired
	private  SendMessageService sendMessageService;

	
	private DataPackageDTO gainDebitInfo(String businessOID) {
		try {
			WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(businessOID);
			WebP2pNormalBiddingRuning normalBidding = null;// TODO 到这就空指针 this.normalBiddingRuningMapper.findByOID(businessOID);
			WebP2pPackageBiddingMainContentRuning packageBidding = this.packageBiddingMainContentRuningMapper
					.findByOID(businessOID);

			if (noviceBid == null) {
				if (normalBidding == null) {
					if (packageBidding == null) {
						System.out.println("没有正确的数据请核对数据流程");
						return null;
					} else {
						return this.newDatapackage(MapUtils.setConditionMap(packageBidding), businessOID);
					}

				} else {
					return this.newDatapackage(MapUtils.setConditionMap(normalBidding), businessOID);
				}

			} else {
				return this.newDatapackage(MapUtils.setConditionMap(noviceBid), businessOID);

			}
		} catch (Exception e) {
			logger.error("DataPackageDTO gainDebitInfo(String businessOID)" + e.getMessage());
			return null;
		}

	}

	/**
	 * 封装返回参数
	 */
	private DataPackageDTO newDatapackage(Map<String, Object> map, String businessOID) {
		try {
			WebP2pLoanProductRateInfo loanProductRateInfo = loanProductRateInfoMapper
					.findByOID((String) map.get("loanProductRateInfoID"));
			DataPackageDTO packageDto = new DataPackageDTO();
			packageDto.setBusinessOID(businessOID);
			packageDto.setMemberOID((String) map.get("memberOID"));
			packageDto.setName((String) map.get("biddingName"));
			packageDto.setDebitType(loanProductRateInfo.getRepaymentType());
			packageDto.setLoanNumber((String) map.get("loanNumber"));
			packageDto.setRepayNumber(loanProductRateInfo.getTerm());
			packageDto.setRepayCycle("month");
			packageDto.setDebitRate(loanProductRateInfo.getYearRate());
			packageDto.setMoney((BigDecimal) map.get("biddingAmount"));
			packageDto.setHoldmoney((BigDecimal) map.get("holdingAmount"));
			packageDto.setOnceProceduresRate(loanProductRateInfo.getDisposableRate());
			packageDto.setEarlyRepay(loanProductRateInfo.getBeforeRate());
			packageDto.setOverdueRate(loanProductRateInfo.getOverdueRate());
			packageDto.setOverdueExpenseRate(loanProductRateInfo.getOverdueExpenseRate());
			packageDto.setManageRate(loanProductRateInfo.getOverdueExpenseRate());
			packageDto.setServiceRate(loanProductRateInfo.getYearAdviceRate());

			return packageDto;
		} catch (Exception e) {
			logger.error("DataPackageDTO newDatapackage(Map<String, Object> map, String businessOID)" + e.getMessage());
			return null;
		}
	}



	@Override
	public void finishDebitCreditNEW(String strBusinessOID, Date beginDate) {
		// 借贷信息
		DataPackageDTO dtoDebitInfo = this.gainDebitInfo(strBusinessOID);
		List<String> lRepayOID = new ArrayList<>();
		// 还款部分
		if (dtoDebitInfo != null && "EPEI".equals(dtoDebitInfo.getDebitType())) {
			Integer repayNumber = dtoDebitInfo.getRepayNumber();
			String repayCycle = dtoDebitInfo.getRepayCycle();
			BigDecimal debitRate = dtoDebitInfo.getDebitRate();
			if ("month".equals(repayCycle)) {
				debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
			} else if ("day".equals(repayCycle)) {
				debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
			}
			// 全部本金
			BigDecimal money = dtoDebitInfo.getMoney();
			// 统计
			BigDecimal totalPrincipal = new BigDecimal("0");
			@SuppressWarnings("unused")
			BigDecimal surplusPrincipal = new BigDecimal("0");
			BigDecimal totalInterest = new BigDecimal("0");
			for (int i = 0; i < repayNumber; i++) {
				PayRepayPlan payRepayPlan = new PayRepayPlan();
				payRepayPlan.setBusinessOID(strBusinessOID);
				payRepayPlan.setMemberOID(dtoDebitInfo.getMemberOID());
				if ("month".equals(repayCycle)) {
					payRepayPlan.setRepayDate(ToolDateTime.addMonth(beginDate, i + 1));
				} else if ("day".equals(repayCycle)) {
					payRepayPlan.setRepayDate(ToolDateTime.addDay(beginDate, i + 1));
				}
				// 本金
				BigDecimal principal = money.divide(new BigDecimal(repayNumber), 2, BigDecimal.ROUND_HALF_EVEN);
				// 利息
				BigDecimal interest = money.multiply(debitRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				// 管理费
				BigDecimal manage = null;
				if ("month".equals(repayCycle)) {
					manage = money.multiply(dtoDebitInfo.getManageRate()).divide(new BigDecimal("12"), 8,
							BigDecimal.ROUND_HALF_EVEN);
				} else if ("day".equals(repayCycle)) {
					manage = money.multiply(dtoDebitInfo.getManageRate()).divide(new BigDecimal("365"), 8,
							BigDecimal.ROUND_HALF_EVEN);
				}
				// 服务费
				BigDecimal service = null;
				if ("month".equals(repayCycle)) {
					service = money.multiply(dtoDebitInfo.getServiceRate()).divide(new BigDecimal("12"), 8,
							BigDecimal.ROUND_HALF_EVEN);
				} else if ("day".equals(repayCycle)) {
					service = money.multiply(dtoDebitInfo.getServiceRate()).divide(new BigDecimal("365"), 8,
							BigDecimal.ROUND_HALF_EVEN);
				}
				payRepayPlan.setMoney(
						principal.add(interest).add(manage).add(service).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				payRepayPlan.setPrincipalMoney(principal);
				payRepayPlan.setInterestMoney(interest);
				totalPrincipal = totalPrincipal.add(principal);
				payRepayPlan.setTotalPrincipalMoney(totalPrincipal);
				payRepayPlan.setSurplusPrincipal(money.subtract(totalPrincipal));
				totalInterest = totalInterest.add(interest);
				payRepayPlan.setTotalInterestMoney(totalInterest);
				payRepayPlan.setManageMoney(manage);
				payRepayPlan.setServiceMoney(service);
				payRepayPlan.setRate(debitRate);
				payRepayPlan.setState("plan");
				payRepayPlan.setOID(StringUtil.getUUID());
				// 保存还款计划
				repayPlanMapper.saveRepayPlan(payRepayPlan);
				lRepayOID.add(payRepayPlan.getBusinessOID()); // 临时数据 应该为主键OID
																// 上边定义了此集合
			}
			BigDecimal onceProceduresMoney = money.multiply(dtoDebitInfo.getOnceProceduresRate()).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
			controlCashPool.onceProceduresMoney(strBusinessOID, dtoDebitInfo.getMemberOID(), onceProceduresMoney);
			controlCashPool.finishInvestment(strBusinessOID, dtoDebitInfo.getMemberOID(),
					money.subtract(onceProceduresMoney));
		}

		List<PayDebitCredit> list = debitCreditMapper.findByBusinessOIDAndState(strBusinessOID, "investment");

		if (list != null && list.size() > 0) {
			// 回款部分
			for (PayDebitCredit dtoDebitCredit : list) {
				// 结束时间
				Date endDate = null;
				// 统计
				BigDecimal totalPrincipal = new BigDecimal("0");
				BigDecimal totalInterest = new BigDecimal("0");

				DataPackageDTO thisdtoDebitInfo = this.gainDebitInfo(dtoDebitCredit.getBusinessOID());
				if ("OPI".equals(dtoDebitCredit.getCreditType())) {

					ProductInfoDTO dtoProductInfo = this.gainProductInfo(dtoDebitCredit.getBusinessOID());
					if (dtoProductInfo != null) {
						// 非环环资金池投资
						// 返还期数
						Integer returnNumber = 1;
						// 结束时间
						// Date endDate = null;
						String returnCycle = dtoProductInfo.getReturnCycle();
						// 利率
						BigDecimal creditRate = dtoDebitCredit.getCreditRate();
						BigDecimal creditRatePro = dtoProductInfo.getCreditRate();
						// 全部本金
						BigDecimal money = (BigDecimal) dtoDebitCredit.getPrincipalMoney();

						if ("month".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							creditRatePro = creditRatePro.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
						} else if ("day".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
							creditRatePro = creditRatePro.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
						}

						if ("month".equals(returnCycle)) {
							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID("");
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, 1));
							endDate = dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
									BigDecimal.ROUND_HALF_EVEN);
							// 利息
							BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipal = totalPrincipal.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipal);
							totalInterest = totalInterest.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterest);
							dtoReturnPlan.setRate(creditRate);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(returnNumber);
							dtoReturnPlan.setState("agentPlan");
							dtoReturnPlan.setType("OPI");
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());
							payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

						} else if ("day".equals(returnCycle)) {

							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID((String) lRepayOID.get(0));
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addDay(beginDate, 15));
							endDate = dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money;
							// 利息
							BigDecimal interest = money.multiply(creditRatePro).multiply(new BigDecimal(15)).setScale(2,
									BigDecimal.ROUND_HALF_EVEN);
							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipal = totalPrincipal.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipal);
							totalInterest = totalInterest.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterest);
							dtoReturnPlan.setRate(creditRatePro);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(1);
							dtoReturnPlan.setState("agentPlan");
							dtoReturnPlan.setType("OPI");
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());
							payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
						}
					}
					if (thisdtoDebitInfo != null) {
						// 资金池投资
						BigDecimal totalPrincipalm = new BigDecimal("0");
						BigDecimal totalInterestm = new BigDecimal("0");

						// 返还期数
						Integer returnNumber = thisdtoDebitInfo.getRepayNumber();
						String returnCycle =thisdtoDebitInfo.getRepayCycle();
						// 利率
						BigDecimal creditRate =dtoDebitCredit.getCreditRate();
						// 全部本金
						BigDecimal money = dtoDebitCredit.getPrincipalMoney();
						if ("month".equals(returnCycle)) {

							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							for (int i = 0; i < returnNumber; i++) {
								PayReturnPlan dtoReturnPlan = new PayReturnPlan();
								dtoReturnPlan.setBusinessOID(strBusinessOID);
								dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
								dtoReturnPlan.setRepayOID(lRepayOID.get(i));
								dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
								dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
								dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
								dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, i + 1));
								endDate = dtoReturnPlan.getReturnDate();
								// 本金
								BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
										BigDecimal.ROUND_HALF_EVEN);
								// 利息
								BigDecimal interest = money.multiply(creditRate).setScale(2,
										BigDecimal.ROUND_HALF_EVEN);
								dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
								dtoReturnPlan.setPrincipalMoney(principal);
								dtoReturnPlan.setInterestMoney(interest);
								totalPrincipalm = totalPrincipalm.add(principal);
								dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
								totalInterestm = totalInterestm.add(interest);
								dtoReturnPlan.setTotalInterestMoney(totalInterestm);
								dtoReturnPlan.setRate(creditRate);
								dtoReturnPlan.setNum(1);
								dtoReturnPlan.setSum(returnNumber);
								dtoReturnPlan.setState("plan");
								dtoReturnPlan.setType("EPEINEW");
								dtoReturnPlan.setYqfx(new BigDecimal("0"));
								dtoReturnPlan.setOID(StringUtil.getUUID());
								payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

							}
						}
					}
				}

				if ("EPEI".equals(dtoDebitCredit.getCreditType()) || "EPEIFP".equals(dtoDebitCredit.getCreditType())
						|| "CP".equals(dtoDebitCredit.getCreditType())) {

					String returnType =dtoDebitCredit.getCreditType();
					if (thisdtoDebitInfo != null) {
						// 资金池投资
						BigDecimal totalPrincipalm = new BigDecimal("0");
						BigDecimal totalInterestm = new BigDecimal("0");
						if ("OPI".equals(returnType)) {
							returnType = "EPEI";
						}

						// 返还期数
						Integer returnNumber = thisdtoDebitInfo.getRepayNumber();
						// 结束时间
						String returnCycle = thisdtoDebitInfo.getRepayCycle();
						// 利率
						BigDecimal creditRate =dtoDebitCredit.getCreditRate();
						// 全部本金
						BigDecimal money =dtoDebitCredit.getPrincipalMoney();
						if ("month".equals(returnCycle)) {

							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							for (int i = 0; i < returnNumber; i++) {
								PayReturnPlan dtoReturnPlan = new PayReturnPlan();
								dtoReturnPlan.setBusinessOID(strBusinessOID);
								dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
								dtoReturnPlan.setRepayOID((String) lRepayOID.get(i));
								dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
								dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
								dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
								dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, i + 1));
								endDate = dtoReturnPlan.getReturnDate();
								// 本金
								BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
										BigDecimal.ROUND_HALF_EVEN);
								// 利息
								BigDecimal interest = money.multiply(creditRate).setScale(2,
										BigDecimal.ROUND_HALF_EVEN);
								dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
								dtoReturnPlan.setPrincipalMoney(principal);
								dtoReturnPlan.setInterestMoney(interest);
								totalPrincipalm = totalPrincipalm.add(principal);
								dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
								totalInterestm = totalInterestm.add(interest);
								dtoReturnPlan.setTotalInterestMoney(totalInterestm);
								dtoReturnPlan.setRate(creditRate);
								dtoReturnPlan.setNum(i + 1);
								dtoReturnPlan.setSum(returnNumber);
								dtoReturnPlan.setState("plan");
								dtoReturnPlan.setType(returnType);
								dtoReturnPlan.setYqfx(new BigDecimal("0"));
								dtoReturnPlan.setOID(StringUtil.getUUID());
								payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
							}

						} else if ("day".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
									.multiply(new BigDecimal(returnNumber));
							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID(lRepayOID.get(0));
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, returnNumber));
							endDate = dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money;
							// 利息
							BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipalm = totalPrincipalm.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
							totalInterestm = totalInterestm.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterestm);
							dtoReturnPlan.setRate(creditRate);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(returnNumber);
							dtoReturnPlan.setState("plan");
							dtoReturnPlan.setType(returnType);
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());
							payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

						}
					}
					dtoDebitCredit.setInterest(totalInterest);
				}
				dtoDebitCredit.setState("finishInvestment");
				dtoDebitCredit.setStartDateTime(beginDate);
				dtoDebitCredit.setEndDateTime(endDate);
				dtoDebitCredit.setSurplusPrincipalMoney(dtoDebitCredit.getPrincipalMoney());
				// 更新借贷关系表
				debitCreditMapper.updateDebitCredit(dtoDebitCredit);
			}
		}
	}

	/**
	 * 获取产品信息
	 * 
	 * @param businessOID
	 *            标的OID creditType 贷款类型 BIAP 先息后本 OPI 一次性本息 returnNumber 返还期数
	 *            returnCycle 返还周期 creditRate 借款利率
	 */
	public ProductInfoDTO gainProductInfo(String strBusinessOID) {
		try {
			if (StringUtil.isNotEmpty(strBusinessOID)) {
				WebP2pNoviceBiddingRuning noviceBidding =this.noviceBiddingRuningMapper.findByOID(strBusinessOID);
				WebP2pProductRateInfo productRateInfo = null;
				ProductInfoDTO infodto =null;
				if (noviceBidding != null) {
					infodto=new ProductInfoDTO();
					infodto.setName(noviceBidding.getBiddingName());
					infodto.setMemberOID(noviceBidding.getMemberOID());
					productRateInfo = this.productRateInfoMapper.findByOID(noviceBidding.getProductRateInfoID());
					
				} else {
					WebP2pNormalBiddingRuning normalBidding= null;// TODO 到这就空指针  normalBiddingRuningMapper.findNormalByOID(strBusinessOID);
					if (normalBidding!= null) {
						infodto=new ProductInfoDTO();
						infodto.setName(normalBidding.getBiddingName());
						productRateInfo = this.productRateInfoMapper.findByOID(normalBidding.getProductRateInfoID());
					} else {
						WebP2pPackageBiddingMainRuning packageBidding=this.packageBiddingMainRuningMapper.findByOID(strBusinessOID);
						if (packageBidding != null) {
							infodto=new ProductInfoDTO();
							infodto.setName(packageBidding.getBiddingName());
							productRateInfo = this.productRateInfoMapper.findByOID(packageBidding.getProductRateInfoID());
						}
					}
				}
				
				if (infodto != null&&productRateInfo!=null) {
					infodto.setBusinessOID(strBusinessOID);
					infodto.setDebitType(productRateInfo.getRepaymentType());
					double productTerm = productRateInfo.getProductTerm();
					Integer rn = new Integer(0);
					if (productTerm > 0.9) {
						rn = Integer.parseInt(new java.text.DecimalFormat("0").format(productTerm));
						infodto.setReturnCycle("month");
					} else {
						rn = 15;
						infodto.setReturnCycle("day");
					}
					infodto.setReturnNumber(rn);
					infodto.setCreditRate(productRateInfo.getYearRate());
					infodto.setHhtXXtqsh(productRateInfo.getHhtXXtqsh());
					infodto.setHhtXStqsh(productRateInfo.getHhtXStqsh());
					infodto.setDdtZQZR(productRateInfo.getDdtZQZR());

					return infodto;
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("ProductInfoDTO gainProductInfo(String strBusinessOID)" + e.getMessage());
			return null;
		}

	}

	@Override
	public void finishTransferReturnNEW(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				String debitCreditOID = null;
				List<String> dcrtOID = new ArrayList<>();
				List<PayTransferReturn> dtocTransferReturn = transferReturnMapper
						.findByBusinessOIDAndState(biddto.getBiddingOID(), "investment");
				int isCW = 0;
				for (PayTransferReturn dtoTransferReturn : dtocTransferReturn) {
					debitCreditOID = dtoTransferReturn.getDebitCreditOID();
					// 受让人是否是财务账号
					String outMemberOID = dtoTransferReturn.getOutMemberOID();
					MemberMember outMember = userMapper.findUserByOID(outMemberOID);
					if (outMember.getUserType() != null) {
						if ("1".equals((String) outMember.getUserType())) {
							isCW = 1;
						}
					}
					// 债转前借贷关系
					PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);
					// 原始还款用户
					String repayMember = "";
					dtoDebitCredit.setState("transferReturn");
					debitCreditMapper.updateDebitCredit(dtoDebitCredit);
					// 为重新定义回款计划准备
					List<PayReturnPlan> dtocReturnPlan = payReturnPlanMapper.queryByreturnDate(debitCreditOID);
					dcrtOID.add(debitCreditOID);
					int returnNumber = 0;
					List<String> lRepayOID = new ArrayList<>();
					List<Date> lReturnDate = new ArrayList<>();
					List<String> lReturnState = new ArrayList<>();
					BigDecimal oldAllMoney = new BigDecimal(0);
					String returnBusinessOID = null;

					// 生成新的借贷关系
					PayDebitCredit debitcreditNEW = new PayDebitCredit();
					debitcreditNEW.setOID(StringUtil.getUUID());
					debitcreditNEW.setBusinessOID(biddto.getBiddingOID());
					debitcreditNEW.setInMemberOID(dtoTransferReturn.getInMemberOID());
					debitcreditNEW.setOutMemberOID(dtoTransferReturn.getOutMemberOID());
					debitcreditNEW.setCashPoolOID(dtoTransferReturn.getCashPoolOID());
					debitcreditNEW.setCreditRate(dtoTransferReturn.getCreditRate());
					debitcreditNEW.setCreditType(dtoTransferReturn.getCreditType());
					debitcreditNEW.setReturnNumber(dtoDebitCredit.getReturnNumber());
					debitcreditNEW.setReturnSurplusNumber(dtoTransferReturn.getReturnSurplusNumber());
					debitcreditNEW.setState("finishInvestment");
					debitcreditNEW.setPrivilegePrincipal(dtoTransferReturn.getPrivilegePrincipal());
					debitcreditNEW.setPrivilegeInterest(dtoTransferReturn.getPrivilegeInterest());
					debitcreditNEW.setPrincipalMoney(dtoTransferReturn.getPrincipalMoney());
					debitcreditNEW.setStartDateTime(ToolDateTime.gainDateTime(biddto.getBeginDate()));

					String endDateTime = ToolsDatas.gainPlusAndReduceDay(
							DateUtils.formatDate(biddto.getBeginDate().getTime()), dtoDebitCredit.getReturnNumber(), 0);

					debitcreditNEW.setEndDateTime(DateUtils.getJustDate(endDateTime));
					debitcreditNEW.setReturnCycle(dtoDebitCredit.getReturnCycle());
					debitcreditNEW.setSurplusPrincipalMoney(dtoTransferReturn.getPrincipalMoney());
					debitcreditNEW.setLaiyuan(dtoTransferReturn.getLaiyuan());
					BigDecimal transferReturnMoney = new BigDecimal("0");
					transferReturnMoney = transferReturnMoney.add((BigDecimal) dtoTransferReturn.getMoney());
					debitcreditNEW.setMoney(transferReturnMoney);
					debitcreditNEW.setInterest(dtoTransferReturn.getInterest());
					debitcreditNEW.setAddDateTimeStr(DateUtils.getDateFormatter());

					debitCreditMapper.saveDebitCredit(debitcreditNEW);
					String oldType = "";
					for (PayReturnPlan dtoReturnPlan : dtocReturnPlan) {
						repayMember = dtoReturnPlan.getOutMemberOID();
						if ("OPI".equals((String) dtoReturnPlan.getType())) {
							dtoReturnPlan.setState("finish");

						} else if ("plan".equals((String) dtoReturnPlan.getState())) {
							oldType = (String) dtoReturnPlan.getType();
							lRepayOID.add(dtoReturnPlan.getRepayOID());
							lReturnDate.add(dtoReturnPlan.getReturnDate());
							lReturnState.add(dtoReturnPlan.getState());
							returnBusinessOID = dtoReturnPlan.getBusinessOID();
							oldAllMoney = oldAllMoney.add(dtoReturnPlan.getPrincipalMoney());
							returnNumber++;
						}
						payReturnPlanMapper.updateReturnPlan(dtoReturnPlan);
					}

					BigDecimal creditRate = debitcreditNEW.getCreditRate();
					if ("month".equals(debitcreditNEW.getReturnCycle())) {
						creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(debitcreditNEW.getReturnCycle())) {
						creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					BigDecimal totalPrincipal = new BigDecimal("0");
					BigDecimal totalInterest = new BigDecimal("0");
					BigDecimal money = (BigDecimal) dtoTransferReturn.getPrincipalMoney();
					if (isCW < 1 && "XS".equals(biddto.getIsTZorCW())) {
						ProductInfoDTO dtoProductInfo = this.gainProductInfo(returnBusinessOID);
						BigDecimal creditRatePro = null;
						if (dtoProductInfo != null) {
							creditRatePro = dtoProductInfo.getCreditRate();
							creditRatePro = creditRatePro.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
						}

						if (dtoProductInfo != null) {
							PayReturnPlan dtoReturnPlanOPI = new PayReturnPlan();
							dtoReturnPlanOPI.setOID(StringUtil.getUUID());
							dtoReturnPlanOPI.setBusinessOID(returnBusinessOID);
							dtoReturnPlanOPI.setCashPoolOID(debitcreditNEW.getCashPoolOID());
							dtoReturnPlanOPI.setRepayOID(lRepayOID.get(0));
							dtoReturnPlanOPI.setDebitCreditOID(debitcreditNEW.getOID());
							dtoReturnPlanOPI.setMemberOID(debitcreditNEW.getOutMemberOID());
							dtoReturnPlanOPI.setOutMemberOID(repayMember);
							dtoReturnPlanOPI.setReturnDate(ToolDateTime.addDay(biddto.getBeginDate(), 15));
							// 利息
							BigDecimal interest =dtoTransferReturn.getMoney()
									.subtract((BigDecimal) dtoTransferReturn.getPrincipalMoney());
							// 应收利息
							BigDecimal yslx = ((BigDecimal) dtoTransferReturn.getMoney()).multiply(creditRatePro)
									.multiply(new BigDecimal(15));
							dtoReturnPlanOPI.setMoney(((BigDecimal) dtoTransferReturn.getMoney()).add(yslx).setScale(2,
									BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlanOPI.setPrincipalMoney(dtoTransferReturn.getMoney());
							dtoReturnPlanOPI.setInterestMoney(yslx);
							// totalPrincipal = totalPrincipal.add(principal);
							dtoReturnPlanOPI.setTotalPrincipalMoney(dtoTransferReturn.getMoney());
							totalInterest = totalInterest.add(interest);
							dtoReturnPlanOPI.setTotalInterestMoney(yslx);
							dtoReturnPlanOPI.setRate(creditRatePro);
							dtoReturnPlanOPI.setNum(1);
							dtoReturnPlanOPI.setSum(1);
							dtoReturnPlanOPI.setState("agentPlan");
							dtoReturnPlanOPI.setType("OPI");
							dtoReturnPlanOPI.setYqfx(new BigDecimal("0"));
							payReturnPlanMapper.saveReturnPlan(dtoReturnPlanOPI);
						}
					}

					for (int i = 0; i < returnNumber; i++) {
						PayReturnPlan dtoReturnPlan = new PayReturnPlan();
						dtoReturnPlan.setOID(StringUtil.getUUID());
						dtoReturnPlan.setBusinessOID(returnBusinessOID);
						dtoReturnPlan.setCashPoolOID(debitcreditNEW.getCashPoolOID());
						dtoReturnPlan.setRepayOID(lRepayOID.get(i));
						dtoReturnPlan.setDebitCreditOID(debitcreditNEW.getOID());
						dtoReturnPlan.setMemberOID(debitcreditNEW.getOutMemberOID());
						dtoReturnPlan.setOutMemberOID(repayMember);
						dtoReturnPlan.setReturnDate(lReturnDate.get(i));
						// 本金=投资本金/返回期数
						BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
								BigDecimal.ROUND_HALF_EVEN);
						// 利息 =剩余本金*标的月利率
						BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);

						dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
						dtoReturnPlan.setPrincipalMoney(principal);
						dtoReturnPlan.setInterestMoney(interest);
						totalPrincipal = totalPrincipal.add(principal);
						dtoReturnPlan.setTotalPrincipalMoney(principal.multiply(new BigDecimal(i + 1)));
						totalInterest = totalInterest.add(interest);
						dtoReturnPlan.setTotalInterestMoney(interest.multiply(new BigDecimal(i + 1)));
						dtoReturnPlan.setRate(creditRate);
						dtoReturnPlan.setNum(i + 1);
						dtoReturnPlan.setSum(returnNumber);
						dtoReturnPlan.setYqfx(new BigDecimal("0"));
						dtoReturnPlan.setState("plan");
						if ("CW".equals(biddto.getIsTZorCW()) && "HH".equals(biddto.getTzType())) {
							dtoReturnPlan.setType("EPEI");
						} else {
							if ("EPEI".equals(oldType) && "HH".equals(biddto.getTzType())
									&& "HH".equals(biddto.getIsTZorCW())) {
								dtoReturnPlan.setType("CP");
							} else {
								dtoReturnPlan.setType(oldType);
							}
						}
						payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
					}
					dtoTransferReturn.setState("finish");
					dtoTransferReturn.setDebitCreditOIDNew(debitcreditNEW.getOID());
					transferReturnMapper.updataTransferReturn(dtoTransferReturn);

					debitcreditNEW.setInterest(totalInterest);
					debitCreditMapper.updateDebitCredit(debitcreditNEW);

					// 新手结算
					// ********************************************************************************************************
					if ("CW".equals(biddto.getIsTZorCW()) && "XS".equals(biddto.getTzType())) {
						String inmemberOID=dtoTransferReturn.getInMemberOID();
						String mobile=customerMapper.findByMemberOID(inmemberOID).getName();
						ProductInfoDTO dtoProductInfo = this.gainProductInfo(biddto.getBiddingOID());
						// 解冻贴息
						BigDecimal tll = dtoProductInfo.getCreditRate().divide(new BigDecimal("365"), 8,
								BigDecimal.ROUND_HALF_EVEN);
						BigDecimal proInterest = ((BigDecimal) dtoDebitCredit.getMoney()).multiply(new BigDecimal(15))
								.multiply(tll).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal ydzh = ((BigDecimal) dtoDebitCredit.getMoney()).add(proInterest);

						controlCustomerBusiness.saveCustomerBusiness("回款", ydzh, "回款的本金利息金额",
								dtoTransferReturn.getInMemberOID());
						
						MessageContentBusiness messageBusiness = new MessageContentBusiness();
						String mOID = StringUtil.getUUID();
						messageBusiness.setOID(mOID);
						messageBusiness.setTitle("回款");
						String content = "您收到回款金额为" + ydzh + "元";
						messageBusiness.setContent(content);
						messageBusiness.setSendUser("系统");
						messageBusiness.setReceiveUserOID(dtoTransferReturn.getInMemberOID());
						messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

						MessageSmsHistory smsHistory = new MessageSmsHistory();
						String sOID = StringUtil.getUUID();
						smsHistory.setOID(sOID);
						smsHistory.setTitle("回款");
						String scontent = "【砖头网】您收到回款金额为" + ydzh + "元";
						smsHistory.setContent(scontent);
						smsHistory.setSendUser("系统");
						smsHistory.setReceiveUser("测试");
						smsHistory.setReceiveUser(mobile);
						
						Map<String, String> result =sendMessageService.sendMessage(mobile, scontent);
						String state = result.get("Description");
						smsHistory.setState(state);
						messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
						PayCustomer customerdto = customerMapper.findByMemberOID(dtoTransferReturn.getInMemberOID());
						BigDecimal mfed = new BigDecimal("0");
						if (customerdto.getFreeline() != null) {
							mfed = mfed.add(customerdto.getFreeline());
						}
						// 当前回款本息加原有免费额度
						customerdto.setFreeline(mfed.add(ydzh));
						customerMapper.updataCutomer(customerdto);
						controlPay.updateAccount(dtoTransferReturn.getInMemberOID());

					}
					// 特权利润扣除
					dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

					// 开发站岗资金结算修改此处，所以债转完成时都不解冻
					controlCashPool.finishTransferReturnUnFreeze(dtoDebitCredit.getOID(),
							dtoDebitCredit.getOutMemberOID(), transferReturnMoney, null);
				}

				// 债转后修改原债转回款计划状态
				if (dcrtOID.size() > 0) {
					for (int d = 0; d < dcrtOID.size(); d++) {
						List<PayReturnPlan> putReturnPlan = payReturnPlanMapper.queryByreturnDate(dcrtOID.get(d));
						for (PayReturnPlan dtorp : putReturnPlan) {
							if (!"OPI".equals((String) dtorp.getType())) {
								if ("plan".equals((String) dtorp.getState())) {
									// 此处如果把已还“finish”改为“transferReturn”，结算会错误
									dtorp.setState("transferReturn");
									payReturnPlanMapper.updateReturnPlan(dtorp);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("finishTransferReturnNEW(BiddingDTO biddto)" + e.getMessage());
		}
	}

	/**
	 * 投资的具体业务流程
	 * 
	 * @param map（String
	 *            businessOID, String memberOID, BigDecimal money, Date
	 *            beginDate, BigDecimal privilegePrincipal, BigDecimal
	 *            privilegeInterest, String tqOID, String ly） 开始日期
	 *            privilegePrincipal 特权本金 privilegeInterest 特权利率 投资开始时加入 ly:
	 *            数据来源 App/Web
	 * @return
	 */
	@Override
	public BigDecimal gainCashPoolCash(String strBusinessOID) {
		try {
			BigDecimal principalMoney = new BigDecimal("0");

			List<PayCashPool> dtocCashPool =payCashPoolMapper.findPayCashPoolByBusiness(strBusinessOID);

			for (PayCashPool dtoCashPool : dtocCashPool) {
				principalMoney = principalMoney.add(dtoCashPool.getMoney());
			}
			return principalMoney;
		} catch (Exception e) {
			logger.error("gainCashPoolCash(String strBusinessOID)"+e.getMessage());
			return null;
		}
	}

	@Override
	public void finishInvestment(String outBusinessOID, String inBusinessOID, BigDecimal money, String ly) {
		try {
			DataPackageDTO dtoDebitInfo = this.gainDebitInfo(inBusinessOID);
			// 剩余本金
			BigDecimal otherMoney = money;
			Boolean flag = false;
			List<PayCashPool> cpdc = payCashPoolMapper.findByBusinessOIDAndHaveMoney(outBusinessOID);
			for (PayCashPool dtoCashPool : cpdc) {
				// 投资额
				BigDecimal principal = null;

				if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() > 0) {
					principal = (BigDecimal) dtoCashPool.getMoney();
					otherMoney = otherMoney.subtract(principal);
				} else if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() < 0) {
					principal = otherMoney;
					flag = true;
				} else if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() == 0) {
					principal = otherMoney;
					flag = true;
				}

				PayDebitCredit dtoDebitCredit = new PayDebitCredit();
				dtoDebitCredit.setOID(StringUtil.getUUID());
				dtoDebitCredit.setCashPoolOID(dtoCashPool.getOID());
				dtoDebitCredit.setBusinessOID(inBusinessOID);
				dtoDebitCredit.setOutMemberOID(dtoCashPool.getMemberOID());
				dtoDebitCredit.setInMemberOID(dtoDebitInfo.getMemberOID());
				dtoDebitCredit.setReturnNumber(dtoDebitInfo.getRepayNumber());
				dtoDebitCredit.setReturnCycle(dtoDebitInfo.getRepayCycle());
				dtoDebitCredit.setCreditType("CP");
				dtoDebitCredit.setCreditRate(dtoDebitInfo.getDebitRate());
				dtoDebitCredit.setMoney(principal);
				dtoDebitCredit.setPrincipalMoney(principal);
				dtoDebitCredit.setSurplusPrincipalMoney(principal);
				dtoDebitCredit.setState("investment");
				dtoDebitCredit.setReturnSurplusNumber(dtoDebitInfo.getRepayNumber());
				dtoDebitCredit.setLaiyuan(dtoCashPool.getLaiyuan());
				dtoDebitCredit.setStartDateTime(new Date());
				String edt = ToolsDatas.gainPlusAndReduceDay(
						DateUtils.formatJustDate(dtoDebitCredit.getStartDateTime().getTime()),
						dtoDebitInfo.getRepayNumber(), 0);
				dtoDebitCredit.setEndDateTime(DateUtils.getDate(edt));
				if ("month".equals(dtoDebitInfo.getRepayCycle())) {
					BigDecimal creditRateTemp =dtoDebitInfo.getDebitRate()
							.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					dtoDebitCredit.setInterest(money.multiply(creditRateTemp)
							.multiply(new BigDecimal(dtoDebitInfo.getRepayNumber()))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN));
				} else if ("day".equals(dtoDebitInfo.getRepayCycle())) {
					BigDecimal creditRateTemp = (dtoDebitInfo.getDebitRate())
							.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					dtoDebitCredit.setInterest(money.multiply(creditRateTemp)
							.multiply(new BigDecimal(dtoDebitInfo.getRepayNumber()))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN));

				}
				this.debitCreditMapper.saveDebitCredit(dtoDebitCredit);

				dtoCashPool.setMoney(dtoCashPool.getMoney().subtract(principal));

				this.payCashPoolMapper.updatePayCashPool(dtoCashPool);
 
				this.controlCashPool.realInvestment(inBusinessOID,dtoCashPool.getMemberOID(),
						dtoDebitInfo.getMemberOID(), principal);
				if (flag) {
					break;
				}
			}

		} catch (Exception e) { 
			logger.error("finishInvestment(String outBusinessOID, String inBusinessOID, BigDecimal money, String ly)"+e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void finishDebitCredit(String strBusinessOID, Date beginDate) {
		try {
			// 获取借贷信息
			DataPackageDTO dtoDebitInfo = this.gainDebitInfo(strBusinessOID);
			List<String> lRepayOID = new ArrayList<String>();
			// 还款部分
			if ("EPEI".equals(dtoDebitInfo.getDebitType())) {
				Integer repayNumber =dtoDebitInfo.getRepayNumber();
				String repayCycle =  dtoDebitInfo.getRepayCycle();
				BigDecimal debitRate =dtoDebitInfo.getDebitRate();
				if ("month".equals(repayCycle)) {
					debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
				} else if ("day".equals(repayCycle)) {
					debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
				}
				// 全部本金
				BigDecimal money =dtoDebitInfo.getMoney();
				// 统计
				BigDecimal totalPrincipal = new BigDecimal("0");
				BigDecimal totalInterest = new BigDecimal("0");
				for (int i = 0; i < repayNumber; i++) {
					PayRepayPlan dtoRepayPlan = new PayRepayPlan();
					dtoRepayPlan.setBusinessOID(strBusinessOID);
					dtoRepayPlan.setMemberOID(dtoDebitInfo.getMemberOID());
					if ("month".equals(repayCycle)) {
						dtoRepayPlan.setRepayDate(ToolDateTime.addMonth(beginDate, i + 1));
					} else if ("day".equals(repayCycle)) {
						dtoRepayPlan.setRepayDate(ToolDateTime.addDay(beginDate, i + 1));
					}
					// 本金
					BigDecimal principal = money.divide(new BigDecimal(repayNumber), 2, BigDecimal.ROUND_HALF_EVEN);
					// 利息
					BigDecimal interest = money.multiply(debitRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					// 管理费
					BigDecimal manage = null;
					if ("month".equals(repayCycle)) {
						manage = money.multiply(dtoDebitInfo.getManageRate())
								.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(repayCycle)) {
						manage = money.multiply(dtoDebitInfo.getManageRate())
								.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					// 服务费
					BigDecimal service = null;
					if ("month".equals(repayCycle)) {
						service = money.multiply(dtoDebitInfo.getServiceRate())
								.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(repayCycle)) {
						service = money.multiply(dtoDebitInfo.getServiceRate())
								.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					dtoRepayPlan.setMoney(
							principal.add(interest).add(manage).add(service).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					dtoRepayPlan.setPrincipalMoney(principal);
					dtoRepayPlan.setInterestMoney(interest);
					totalPrincipal = totalPrincipal.add(principal);
					dtoRepayPlan.setTotalPrincipalMoney(totalPrincipal);
					dtoRepayPlan.setSurplusPrincipal(money.subtract(totalPrincipal));
					totalInterest = totalInterest.add(interest);
					dtoRepayPlan.setTotalInterestMoney(totalInterest);
					dtoRepayPlan.setManageMoney(manage);
					dtoRepayPlan.setServiceMoney(service);
					dtoRepayPlan.setRate(debitRate);
					dtoRepayPlan.setState("plan");
					dtoRepayPlan.setOID(StringUtil.getUUID());

					this.repayPlanMapper.saveRepayPlan(dtoRepayPlan);

					lRepayOID.add(dtoRepayPlan.getOID());
				}
				BigDecimal onceProceduresMoney = money.multiply(dtoDebitInfo.getOnceProceduresRate())
						.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				controlCashPool.onceProceduresMoney(strBusinessOID,dtoDebitInfo.getMemberOID(),
						onceProceduresMoney);
				controlCashPool.finishInvestment(strBusinessOID,dtoDebitInfo.getMemberOID(),
						money.subtract(onceProceduresMoney));
			}
			// ******* bob 10.12 BIAP生成还款计划 5575-
			if ("BIAP".equals(dtoDebitInfo.getDebitType())) {
				Integer repayNumber =dtoDebitInfo.getRepayNumber();
				String repayCycle =dtoDebitInfo.getRepayCycle();
				BigDecimal debitRate =dtoDebitInfo.getDebitRate();
				if ("month".equals(repayCycle)) {
					debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
				} else if ("day".equals(repayCycle)) {
					debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
				}
				// 全部本金
				BigDecimal money =dtoDebitInfo.getMoney();
				// 统计
				BigDecimal totalPrincipal = new BigDecimal("0");
				BigDecimal totalInterest = new BigDecimal("0");
				for (int i = 0; i < repayNumber; i++) {
					PayRepayPlan dtoRepayPlan = new PayRepayPlan();
					dtoRepayPlan.setBusinessOID(strBusinessOID);
					dtoRepayPlan.setMemberOID(dtoDebitInfo.getMemberOID());
					if ("month".equals(repayCycle)) {
						dtoRepayPlan.setRepayDate(ToolDateTime.addMonth(beginDate, i + 1));
					} else if ("day".equals(repayCycle)) {
						dtoRepayPlan.setRepayDate(ToolDateTime.addDay(beginDate, i + 1));
					}
					// 本金
					BigDecimal principal = new BigDecimal(0.00);
					if (i == repayNumber - 1) {
						// 本金
						principal = money;
					}

					// 利息
					BigDecimal interest = money.multiply(debitRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					// 管理费
					BigDecimal manage = null;
					if ("month".equals(repayCycle)) {
						manage = money.multiply(dtoDebitInfo.getManageRate())
								.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(repayCycle)) {
						manage = money.multiply((BigDecimal) dtoDebitInfo.getManageRate())
								.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					// 服务费
					BigDecimal service = null;
					if ("month".equals(repayCycle)) {
						service = money.multiply(dtoDebitInfo.getServiceRate())
								.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(repayCycle)) {
						service = money.multiply(dtoDebitInfo.getServiceRate())
								.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					dtoRepayPlan.setMoney(
							principal.add(interest).add(manage).add(service).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					dtoRepayPlan.setPrincipalMoney(principal);
					dtoRepayPlan.setInterestMoney(interest);
					totalPrincipal = totalPrincipal.add(principal);
					dtoRepayPlan.setTotalPrincipalMoney(totalPrincipal);
					dtoRepayPlan.setSurplusPrincipal(money.subtract(totalPrincipal));
					totalInterest = totalInterest.add(interest);
					dtoRepayPlan.setTotalInterestMoney(totalInterest);
					dtoRepayPlan.setManageMoney(manage);
					dtoRepayPlan.setServiceMoney(service);
					dtoRepayPlan.setRate(debitRate);
					dtoRepayPlan.setState("plan");
					dtoRepayPlan.setOID(StringUtil.getUUID());

					this.repayPlanMapper.saveRepayPlan(dtoRepayPlan);
					// TODO 主键问题
					lRepayOID.add((String) dtoRepayPlan.getOID());
				}
			}

			List<PayDebitCredit> dcdc = this.findByBusinessOIDAndState(strBusinessOID, "investment");
			// 回款部分
			for (PayDebitCredit dtoDebitCredit : dcdc) {
				// 结束时间
				Date endDate = null;
				// 统计
				BigDecimal totalPrincipal = new BigDecimal("0");
				BigDecimal totalInterest = new BigDecimal("0");
				DataPackageDTO thisdtoDebitInfo = this.gainDebitInfo(dtoDebitCredit.getBusinessOID());
				if ("OPI".equals(dtoDebitCredit.getCreditType())) {
					ProductInfoDTO dtoProductInfo = this.gainProductInfo(dtoDebitCredit.getBusinessOID());
					if (dtoProductInfo != null) {
						// 非环环资金池投资
						// 返还期数
						Integer returnNumber = 1;
						// 结束时间
						// Date endDate = null;
						String returnCycle =dtoProductInfo.getReturnCycle();
						// 利率
						BigDecimal creditRate =dtoDebitCredit.getCreditRate();

						// 全部本金
						BigDecimal money =dtoDebitCredit.getPrincipalMoney();

						if ("month".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
						} else if ("day".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
						}

						if ("month".equals(returnCycle)) {

							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID("");
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, 1));
							endDate = (Date) dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
									BigDecimal.ROUND_HALF_EVEN);
							// 利息
							BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);

							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipal = totalPrincipal.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipal);
							totalInterest = totalInterest.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterest);
							dtoReturnPlan.setRate(creditRate);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(returnNumber);
							dtoReturnPlan.setState("agentPlan");
							dtoReturnPlan.setType("OPI");
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());

							this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

						} else if ("day".equals(returnCycle)) {

							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID(lRepayOID.get(0));
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addDay(beginDate, 15));
							endDate = (Date) dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money;
							// 利息
							BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipal = totalPrincipal.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipal);
							totalInterest = totalInterest.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterest);
							dtoReturnPlan.setRate(creditRate);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(1);
							dtoReturnPlan.setState("agentPlan");
							dtoReturnPlan.setType("OPI");
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());

							this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
						}
					}
					if (thisdtoDebitInfo != null) {
						// 资金池投资
						BigDecimal totalPrincipalm = new BigDecimal("0");
						BigDecimal totalInterestm = new BigDecimal("0");
						// 返还期数
						Integer returnNumber =thisdtoDebitInfo.getRepayNumber();
						// 结束时间
						// Date endDate = null;
						String returnCycle = thisdtoDebitInfo.getRepayCycle();
						// 利率
						BigDecimal creditRate = (BigDecimal) dtoDebitCredit.getCreditRate();
						// 全部本金
						BigDecimal money = (BigDecimal) dtoDebitCredit.getPrincipalMoney();
						if ("month".equals(returnCycle)) {

							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							for (int i = 0; i < returnNumber; i++) {
								PayReturnPlan dtoReturnPlan = new PayReturnPlan();
								dtoReturnPlan.setBusinessOID(strBusinessOID);
								dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
								dtoReturnPlan.setRepayOID(lRepayOID.get(i));
								dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
								dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
								dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
								dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, i + 1));
								endDate = (Date) dtoReturnPlan.getReturnDate();
								// 本金
								BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
										BigDecimal.ROUND_HALF_EVEN);
								// 利息
								BigDecimal interest = money.multiply(creditRate).setScale(2,
										BigDecimal.ROUND_HALF_EVEN);
								dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
								dtoReturnPlan.setPrincipalMoney(principal);
								dtoReturnPlan.setInterestMoney(interest);
								totalPrincipalm = totalPrincipalm.add(principal);
								dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
								totalInterestm = totalInterestm.add(interest);
								dtoReturnPlan.setTotalInterestMoney(totalInterestm);
								dtoReturnPlan.setRate(creditRate);
								dtoReturnPlan.setNum(i + 1);
								dtoReturnPlan.setSum(returnNumber);
								dtoReturnPlan.setState("plan");
								dtoReturnPlan.setType("EPEINEW");
								dtoReturnPlan.setYqfx(new BigDecimal("0"));
								dtoReturnPlan.setOID(StringUtil.getUUID());

								this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
							}
						}
					}
				}

				if ("EPEI".equals(dtoDebitCredit.getCreditType()) || "EPEIFP".equals(dtoDebitCredit.getCreditType())
						|| "CP".equals(dtoDebitCredit.getCreditType())) {
					// 产品信息

					String returnType = (String) dtoDebitCredit.getCreditType();
					if (thisdtoDebitInfo != null) {
						// 资金池投资
						BigDecimal totalPrincipalm = new BigDecimal("0");
						BigDecimal totalInterestm = new BigDecimal("0");
						if ("OPI".equals(returnType)) {
							returnType = "EPEI";
						}
						// 返还期数
						Integer returnNumber =thisdtoDebitInfo.getRepayNumber();
						// 结束时间
						// Date endDate = null;
						String returnCycle =thisdtoDebitInfo.getRepayCycle();
						// 利率
						BigDecimal creditRate =dtoDebitCredit.getCreditRate();
						// 全部本金
						BigDecimal money =dtoDebitCredit.getPrincipalMoney();
						if ("month".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							for (int i = 0; i < returnNumber; i++) {
								PayReturnPlan dtoReturnPlan = new PayReturnPlan();
								dtoReturnPlan.setBusinessOID(strBusinessOID);
								dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
								dtoReturnPlan.setRepayOID(lRepayOID.get(i));
								dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
								dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
								dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
								dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, i + 1));
								endDate =dtoReturnPlan.getReturnDate();
								// 本金
								BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
										BigDecimal.ROUND_HALF_EVEN);
								// 利息
								BigDecimal interest = money.multiply(creditRate).setScale(2,
										BigDecimal.ROUND_HALF_EVEN);
								dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
								dtoReturnPlan.setPrincipalMoney(principal);
								dtoReturnPlan.setInterestMoney(interest);
								totalPrincipalm = totalPrincipalm.add(principal);
								dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
								totalInterestm = totalInterestm.add(interest);
								dtoReturnPlan.setTotalInterestMoney(totalInterestm);
								dtoReturnPlan.setRate(creditRate);
								dtoReturnPlan.setNum(i + 1);
								dtoReturnPlan.setSum(returnNumber);
								dtoReturnPlan.setState("plan");
								dtoReturnPlan.setType(returnType);
								dtoReturnPlan.setYqfx(new BigDecimal("0"));
								dtoReturnPlan.setOID(StringUtil.getUUID());

								this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
							}

						} else if ("day".equals(returnCycle)) {
							creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
									.multiply(new BigDecimal(returnNumber));
							PayReturnPlan dtoReturnPlan = new PayReturnPlan();
							dtoReturnPlan.setBusinessOID(strBusinessOID);
							dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
							dtoReturnPlan.setRepayOID(lRepayOID.get(0));
							dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
							dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
							dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
							dtoReturnPlan.setReturnDate(ToolDateTime.addDay(beginDate, returnNumber));
							endDate =dtoReturnPlan.getReturnDate();
							// 本金
							BigDecimal principal = money;
							// 利息
							BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(principal);
							dtoReturnPlan.setInterestMoney(interest);
							totalPrincipalm = totalPrincipalm.add(principal);
							dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
							totalInterestm = totalInterestm.add(interest);
							dtoReturnPlan.setTotalInterestMoney(totalInterestm);
							dtoReturnPlan.setRate(creditRate);
							dtoReturnPlan.setNum(1);
							dtoReturnPlan.setSum(returnNumber);
							dtoReturnPlan.setState("plan");
							dtoReturnPlan.setType(returnType);
							dtoReturnPlan.setYqfx(new BigDecimal("0"));
							dtoReturnPlan.setOID(StringUtil.getUUID());

							this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

						}
						dtoDebitCredit.setInterest(totalInterestm);

					}

				}
				// ********* bob 10.12 BIAP 回款计划 抵押类产品 ******************
				if ("BIAP".equals(dtoDebitCredit.getCreditType())) {

					// 产品信息
					String returnType = (String) dtoDebitCredit.getCreditType();
					if (thisdtoDebitInfo != null) {
						BigDecimal totalPrincipalm = new BigDecimal("0");
						BigDecimal totalInterestm = new BigDecimal("0");
						// 返还期数
						Integer returnNumber =thisdtoDebitInfo.getRepayNumber();
						// 结束时间
						// Date endDate = null;
						String returnCycle =thisdtoDebitInfo.getRepayCycle();
						// 利率
						BigDecimal creditRate =dtoDebitCredit.getCreditRate();
						// 全部本金
						BigDecimal money =dtoDebitCredit.getPrincipalMoney();
						if ("month".equals(returnCycle)) {

							creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
							for (int i = 0; i < returnNumber; i++) {
								PayReturnPlan dtoReturnPlan = new PayReturnPlan();
								dtoReturnPlan.setBusinessOID(strBusinessOID);
								dtoReturnPlan.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
								dtoReturnPlan.setRepayOID(lRepayOID.get(i));
								dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
								dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
								dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
								dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, i + 1));
								endDate =dtoReturnPlan.getReturnDate();
								// 本金
								BigDecimal principal = new BigDecimal(0.00);
								if (i == returnNumber - 1) {
									principal = money;
								}
								// 利息
								BigDecimal interest = money.multiply(creditRate).setScale(2,
										BigDecimal.ROUND_HALF_EVEN);
								dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
								dtoReturnPlan.setPrincipalMoney(principal);
								dtoReturnPlan.setInterestMoney(interest);
								totalPrincipalm = totalPrincipalm.add(principal);
								dtoReturnPlan.setTotalPrincipalMoney(totalPrincipalm);
								totalInterestm = totalInterestm.add(interest);
								dtoReturnPlan.setTotalInterestMoney(totalInterestm);
								dtoReturnPlan.setRate(creditRate);
								dtoReturnPlan.setNum(i + 1);
								dtoReturnPlan.setSum(returnNumber);
								dtoReturnPlan.setState("plan");
								dtoReturnPlan.setType(returnType);
								dtoReturnPlan.setYqfx(new BigDecimal("0"));
								dtoReturnPlan.setOID(StringUtil.getUUID());

								this.payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
							}
						}
						dtoDebitCredit.setInterest(totalInterestm);
					}
				}
				dtoDebitCredit.setState("finishInvestment");
				dtoDebitCredit.setStartDateTime(ToolDateTime.gainDateTime(beginDate));
				dtoDebitCredit.setEndDateTime(ToolDateTime.gainDateTime(endDate));
				dtoDebitCredit.setSurplusPrincipalMoney(dtoDebitCredit.getPrincipalMoney());

				debitCreditMapper.updateDebitCredit(dtoDebitCredit);
			}

		} catch (Exception e) {
			logger.error("finishDebitCredit(String strBusinessOID, Date beginDate)"+e.getMessage());
		}
	}

	private List<PayDebitCredit> findByBusinessOIDAndState(String businessOID, String state) {
		try {
			PayDebitCredit pay_DebitCredit = new PayDebitCredit();
			pay_DebitCredit.setBusinessOID(businessOID);
			pay_DebitCredit.setState(state);
			return this.queryListByWhere(pay_DebitCredit);
		} catch (Exception e) {
			logger.error("List<PayDebitCredit> findByBusinessOIDAndState(String businessOID, String state)"+e.getMessage());
			return null;
		}
	}

	@Override
	public List<PayDebitCredit> gainDebitCreditByBusinessOID(String biddingOID) {
		try {
			PayDebitCredit pay_DebitCredit = new PayDebitCredit();
			pay_DebitCredit.setBusinessOID(biddingOID);
			pay_DebitCredit.setState("finishInvestment");
			return this.queryListByWhere(pay_DebitCredit);
		} catch (Exception e) {
			logger.error("gainDebitCreditByBusinessOID(String biddingOID)");
			return null;
		}
	}

	@Override
	public void addCashPoolTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
			BigDecimal money, Date transferReturnDate, String ly) {
		try {
			Integer isCW = 0;
			PayDebitCredit oldDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

			if (oldDebitCredit == null) {
				debitCreditOID =
						transferReturnMapper.getTransferReturnByOid(debitCreditOID).getDebitCreditOIDNew();
			}
			BigDecimal creditRate = new BigDecimal(0);
			DataPackageDTO debitInfo = this.gainDebitInfo(businessOID);
			creditRate =debitInfo.getDebitRate();
			MemberMember outMember = userMapper.findUserByOID(oldDebitCredit.getOutMemberOID());
			if (outMember.getUserType() != null) {
				if ("1".equals(outMember.getUserType())) {
					isCW = 1;
				}
			}
			List<PayTransferReturn> dtocTransferReturnList = transferReturnMapper
					.getTransferReturnByDebitCreditOIDAndstate(debitCreditOID, "finish");
			PayTransferReturn dtoTF = new PayTransferReturn();
			
			for (PayTransferReturn dto : dtocTransferReturnList) {
				dtoTF = dto;
			}
			int lDay = 0;
			if (dtoTF != null) {
				// 多次债转
				PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(dtoTF.getDebitCreditOIDNew());
				String loanNumber = "";
				
				if (debitInfo != null) {
					loanNumber = debitInfo.getLoanNumber();
				}
				// 截取字符拼成债转编号
				loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
				Date minReturnDate = null;
				PayReturnPlan dtocReturnPlan =
						payReturnPlanMapper.findMINByDebitCreditOIDAndState(dtoDebitCredit.getOID(), "plan");
				if (dtocReturnPlan != null) {
					minReturnDate = (Date) dtocReturnPlan.getReturnDate();
				}
				String bDate = dtoDebitCredit.getStartDateTime().toString();
				if (null != minReturnDate) {
					bDate = ToolsDatas.gainPlusAndReduceDay(minReturnDate.toString(), 1, 1);
				}

				lDay = ToolsDatas.getDateSpace(bDate, transferReturnDate.toString());
				if (lDay < 0) {
					lDay = 0;
				}
				// 投资本金=投资总额*转让本金/转让总额
				BigDecimal tzbj = money.multiply((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
						.divide(debitInfo.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
				// 垫付利息=投资总额-tzbj
				BigDecimal dflx = money.subtract(tzbj);
				// 剩余本金
				BigDecimal otherMoney = tzbj.add(dflx);
				Boolean flag = false;
				List<PayCashPool> dtocCashPool =  payCashPoolMapper.findByBusinessOIDAndHaveMoney(outBusinessOID);

				for (PayCashPool dtoCashPool : dtocCashPool) {
					PayTransferReturn dtoTransferReturn = new PayTransferReturn();
					// 投资额
					BigDecimal principal = null;

					if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() > 0) {
						principal = (BigDecimal) dtoCashPool.getMoney();
						otherMoney = otherMoney.subtract(principal);
					} else if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() < 0) {
						principal = otherMoney;
						flag = true;
					} else if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() == 0) {
						principal = otherMoney;
						flag = true;
					}

					// 债转
					this.controlCashPool.transferReturnCashPool(businessOID, (String) dtoCashPool.getMemberOID(),
							(String) dtoDebitCredit.getOutMemberOID(), principal);

					// 投资本金=投资总额*转让本金/转让总额
					BigDecimal cpbj = new BigDecimal(0);

					cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
							.divide(debitInfo.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
					// 垫付利息=投资总额-tzbj
					BigDecimal cplx = principal.subtract(cpbj);

					dtoTransferReturn.setBusinessOID(businessOID);
					dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
					dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
					dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
					dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
					dtoTransferReturn.setCreditType("CP");
					dtoTransferReturn.setCreditRate(debitInfo.getDebitRate());
					dtoTransferReturn.setMoney(cpbj.add(cplx));
					dtoTransferReturn.setPrincipalMoney(cpbj);
					dtoTransferReturn.setAdvanceInterest(cplx);
					dtoTransferReturn.setPrivilegePrincipal(new BigDecimal(0));
					dtoTransferReturn.setPrivilegeInterest(new BigDecimal(0));
					dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
					dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
					dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
					dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
					dtoTransferReturn.setLaiyuan(dtoCashPool.getLaiyuan());
					if (isCW > 0) {
						dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
					} else {
						Date stday = DateUtils.getDate(dtoDebitCredit.getStartDateTime().getTime());
						Date eDay = ToolDateTime.addDay(stday, lDay);

						dtoTransferReturn.setEndDateTime(eDay);
					}
					dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
					dtoTransferReturn.setState("investment");
					dtoTransferReturn.setDebitCreditOIDNew("");
					dtoTransferReturn.setOID(StringUtil.getUUID());

					this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);
					dtoCashPool.setMoney(dtoCashPool.getMoney().subtract(principal));
					payCashPoolMapper.savePayCashPool(dtoCashPool);
					if (flag) {
						break;
					}
				}
				// -------------------------------------------------------------------------
			} else {
				// 第一次
				PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);
				String buisOid =dtoDebitCredit.getBusinessOID();
				
				WebP2pNoviceBiddingRuning novice=noviceBiddingRuningMapper.findByOID(buisOid);
				WebP2pNormalBiddingRuning normal= null;// TODO 到这就空指针 normalBiddingRuningMapper.findNormalByOID(buisOid);
				WebP2pPackageBiddingMainContentRuning content=
											packageBiddingMainContentRuningMapper.findByOID(buisOid);
				String loanNumber = "";
				if(novice!=null&&StringUtils.isNotEmpty(novice.getLoanNumber())){
					loanNumber = novice.getLoanNumber();
				}
				if(normal!=null&&StringUtils.isNotEmpty(normal.getLoanNumber())){
					loanNumber = normal.getLoanNumber();
				}
				if(content!=null&&StringUtils.isNotEmpty(content.getLoanNumber())){
					loanNumber = content.getLoanNumber();
				}
				// 截取字符拼成债转编号
				loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();

				int yhDay = 0;// 已还
				int yhqs = (int) dtoDebitCredit.getReturnNumber() - (int) dtoDebitCredit.getReturnSurplusNumber();
				String syhkr = ToolsDatas.gainPlusAndReduceDay(dtoDebitCredit.getStartDateTime().toString(), yhqs, 0);
				yhDay = ToolsDatas.getDateSpace(dtoDebitCredit.getStartDateTime().toString(), syhkr);
				if (isCW > 0) {
					lDay = DateUtils.daysBetween(dtoDebitCredit.getStartDateTime(), transferReturnDate);
					lDay = lDay - yhDay;
				} else {
					lDay =  DateUtils.daysBetween(dtoDebitCredit.getStartDateTime(),transferReturnDate);
					lDay = lDay - yhDay;
				}
				if (lDay < 0) {
					lDay = 0;
				}
				BigDecimal creditRateDay =creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
				// 区分不同产品，使用不同本金或剩余本金*************************************************************************************
				// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
				BigDecimal advanceInterest = (dtoDebitCredit.getMoney().multiply(creditRateDay)
						.multiply(new BigDecimal(lDay)).multiply(dtoDebitCredit.getPrincipalMoney()))
						.divide(dtoDebitCredit.getPrincipalMoney(), 2, BigDecimal.ROUND_HALF_EVEN);

				BigDecimal sumTransferMoney = ((BigDecimal) dtoDebitCredit.getPrincipalMoney()).add(advanceInterest);
				
				BigDecimal tzbj =dtoDebitCredit.getSurplusPrincipalMoney();
				
				BigDecimal dflx =debitInfo.getMoney().subtract(dtoDebitCredit.getSurplusPrincipalMoney());
						

				BigDecimal otherMoney = tzbj.add(dflx);
				Boolean flag = false;
				List<PayCashPool> cpdc = payCashPoolMapper.findByBusinessOIDAndHaveMoney(outBusinessOID);
				if (cpdc != null && cpdc.size() > 0) {
					for (PayCashPool dtoCashPool : cpdc) {
						PayTransferReturn dtoTransferReturn = new PayTransferReturn();
						// 投资额
						BigDecimal principal = null;

						if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() > 0) {
							principal =dtoCashPool.getMoney();
							otherMoney = otherMoney.subtract(principal);
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() < 0) {
							principal = otherMoney;
							flag = true;
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() == 0) {
							principal = otherMoney;
							flag = true;
						}
						// 债转
						this.controlCashPool.transferReturnCashPool(businessOID,dtoCashPool.getMemberOID(),
								dtoDebitCredit.getOutMemberOID(), principal);
						// 投资本金=投资总额*转让本金/转让总额
						BigDecimal cpbj = new BigDecimal(0);
						if (sumTransferMoney.compareTo(dtoDebitCredit.getMoney()) == 1) {
							cpbj = principal.multiply(dtoDebitCredit.getPrincipalMoney())
									.divide(sumTransferMoney, 2, BigDecimal.ROUND_HALF_EVEN);
						} else {
							cpbj = principal.multiply(dtoDebitCredit.getPrincipalMoney())
									.divide(dtoDebitCredit.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
						}
						// 垫付利息=投资总额-tzbj
						BigDecimal cplx = principal.subtract(cpbj);
						dtoTransferReturn.setBusinessOID(businessOID);
						dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
						dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
						dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
						dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
						dtoTransferReturn.setCreditType("CP");
						dtoTransferReturn.setCreditRate(debitInfo.getDebitRate());
						dtoTransferReturn.setMoney(cpbj.add(cplx));
						dtoTransferReturn.setPrincipalMoney(cpbj);
						dtoTransferReturn.setAdvanceInterest(cplx);
						dtoTransferReturn.setPrivilegePrincipal(new BigDecimal(0));
						dtoTransferReturn.setPrivilegeInterest(new BigDecimal(0));
						dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
						dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
						dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
						dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
						dtoTransferReturn.setLaiyuan(dtoCashPool.getLaiyuan());
						if (isCW > 0) {
							dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						} else {
							Date stday = dtoDebitCredit.getStartDateTime();
							Date eDay = ToolDateTime.addDay(stday, lDay);

							dtoTransferReturn.setEndDateTime(eDay);
						}
						dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						dtoTransferReturn.setState("investment");
						dtoTransferReturn.setDebitCreditOIDNew("");
						this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);

						// dtoTransferReturn.put("lDay", lDay);
						dtoCashPool.setMoney(dtoCashPool.getMoney().subtract(principal));
						payCashPoolMapper.savePayCashPool(dtoCashPool);
						if (flag) {
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("addCashPoolTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,"
			+"BigDecimal money, Date transferReturnDate, String ly)");
		}
	}

	@Override
	public void finishTransferReturnNEW(String businessOID, Date beginDate, String isTZorCW, String tzType, String ly) {
		try {
			String debitCreditOID = null;
			List<String> dcrtOID = new ArrayList<>();
			List<PayTransferReturn> dtocTransferReturn = transferReturnMapper.findByBusinessOIDAndState(businessOID,
					"investment");
			int isCW = 0;
			if (dtocTransferReturn != null && dtocTransferReturn.size() > 0) {
				for (PayTransferReturn dtoTransferReturn : dtocTransferReturn) {
					debitCreditOID = dtoTransferReturn.getDebitCreditOID();
					// 受让人是否是财务账号
					String outMemberOID =dtoTransferReturn.getOutMemberOID();
					MemberMember outMember = userMapper.findUserByOID(outMemberOID);
					if (outMember.getUserType() != null) {
						if ("1".equals((String) outMember.getUserType())) {
							isCW = 1;
						}
					}
					// 债转前借贷关系
					PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);
					// 原始还款用户
					String repayMember = "";
					dtoDebitCredit.setState("transferReturn");
					this.save(dtoDebitCredit);
					// 为重新定义回款计划准备
					List<PayReturnPlan> dtocReturnPlan = payReturnPlanMapper.queryByreturnDate(debitCreditOID);

					dcrtOID.add(debitCreditOID);
					int returnNumber = 0;

					List<String> lRepayOID = new ArrayList<>();
					List<Date> lReturnDate = new ArrayList<>();
					List<String> lReturnState = new ArrayList<>();
					BigDecimal oldAllMoney = new BigDecimal(0);
					String returnBusinessOID = null;

					// 生成新的借贷关系
					PayDebitCredit debitcreditNEW = new PayDebitCredit();
					debitcreditNEW.setBusinessOID(businessOID);
					debitcreditNEW.setInMemberOID(dtoTransferReturn.getInMemberOID());
					debitcreditNEW.setOutMemberOID(dtoTransferReturn.getOutMemberOID());
					debitcreditNEW.setCashPoolOID(dtoTransferReturn.getCashPoolOID());
					debitcreditNEW.setCreditRate(dtoTransferReturn.getCreditRate());
					debitcreditNEW.setCreditType(dtoTransferReturn.getCreditType());
					debitcreditNEW.setReturnNumber(dtoDebitCredit.getReturnNumber());
					debitcreditNEW.setReturnSurplusNumber(dtoTransferReturn.getReturnSurplusNumber());
					debitcreditNEW.setState("finishInvestment");
					debitcreditNEW.setPrivilegePrincipal(dtoTransferReturn.getPrivilegePrincipal());
					debitcreditNEW.setPrivilegeInterest(dtoTransferReturn.getPrivilegeInterest());
					debitcreditNEW.setPrincipalMoney(dtoTransferReturn.getPrincipalMoney());
					debitcreditNEW.setStartDateTime(beginDate);
					String endDate = ToolsDatas.gainPlusAndReduceDay(DateUtils.formatDate(beginDate.getTime()),
							(int) dtoDebitCredit.getReturnNumber(), 0);
					debitcreditNEW.setEndDateTime(DateUtils.getDate(endDate));
					debitcreditNEW.setReturnCycle(dtoDebitCredit.getReturnCycle());
					debitcreditNEW.setSurplusPrincipalMoney(dtoTransferReturn.getPrincipalMoney());
					debitcreditNEW.setLaiyuan(dtoTransferReturn.getLaiyuan());
					BigDecimal transferReturnMoney = new BigDecimal("0");
					transferReturnMoney = transferReturnMoney.add((BigDecimal) dtoTransferReturn.getMoney());
					debitcreditNEW.setMoney(transferReturnMoney);
					debitcreditNEW.setInterest(dtoTransferReturn.getInterest());
					// 新借贷关系返回 DTO Newdbc
					this.save(debitcreditNEW);
					PayDebitCredit Newdbc = debitcreditNEW;

					String oldType = "";
					for (PayReturnPlan dtoReturnPlan : dtocReturnPlan) {
						repayMember =dtoReturnPlan.getOutMemberOID();

						if ("OPI".equals(dtoReturnPlan.getType())) {
							dtoReturnPlan.setState("finish");
						} else if ("plan".equals(dtoReturnPlan.getState())) {
							oldType =dtoReturnPlan.getType();
							lRepayOID.add(dtoReturnPlan.getRepayOID());
							lReturnDate.add(dtoReturnPlan.getReturnDate());
							lReturnState.add(dtoReturnPlan.getState());
							returnBusinessOID = dtoReturnPlan.getBusinessOID();
							oldAllMoney = oldAllMoney.add( dtoReturnPlan.getPrincipalMoney());
							returnNumber++;
						}
						payReturnPlanMapper.updateReturnPlan(dtoReturnPlan);
					}

					BigDecimal creditRate = (BigDecimal) Newdbc.getCreditRate();
					if ("month".equals(Newdbc.getReturnCycle())) {
						creditRate = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					} else if ("day".equals(Newdbc.getReturnCycle())) {
						creditRate = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					}
					BigDecimal totalPrincipal = new BigDecimal("0");
					BigDecimal totalInterest = new BigDecimal("0");
					BigDecimal money = dtoTransferReturn.getPrincipalMoney();
					if (isCW < 1 && "XS".equals(isTZorCW)) {
						ProductInfoDTO dtoProductInfo = this.gainProductInfo(returnBusinessOID);
						BigDecimal creditRatePro =  dtoProductInfo.getCreditRate();
						creditRatePro = creditRatePro.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);

						if (dtoProductInfo != null) {
							PayReturnPlan dtoReturnPlanOPI = new PayReturnPlan();
							dtoReturnPlanOPI.setBusinessOID(returnBusinessOID);
							dtoReturnPlanOPI.setCashPoolOID(Newdbc.getCashPoolOID());
							dtoReturnPlanOPI.setRepayOID(lRepayOID.get(0));
							dtoReturnPlanOPI.setDebitCreditOID(Newdbc.getOID());
							dtoReturnPlanOPI.setMemberOID(Newdbc.getOutMemberOID());
							dtoReturnPlanOPI.setOutMemberOID(repayMember);
							dtoReturnPlanOPI.setReturnDate(ToolDateTime.addDay(beginDate, 15));
							// 利息
							BigDecimal interest = dtoTransferReturn.getMoney()
									.subtract(dtoTransferReturn.getPrincipalMoney());
							// 应收利息
							BigDecimal yslx = (dtoTransferReturn.getMoney()).multiply(creditRatePro)
									.multiply(new BigDecimal(15));
							dtoReturnPlanOPI.setMoney(
									dtoTransferReturn.getMoney().add(yslx).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlanOPI.setPrincipalMoney(dtoTransferReturn.getMoney());
							dtoReturnPlanOPI.setInterestMoney(yslx);
							dtoReturnPlanOPI.setTotalPrincipalMoney(dtoTransferReturn.getMoney());
							totalInterest = totalInterest.add(interest);
							dtoReturnPlanOPI.setTotalInterestMoney(yslx);
							dtoReturnPlanOPI.setRate(creditRatePro);
							dtoReturnPlanOPI.setNum(1);
							dtoReturnPlanOPI.setSum(1);
							dtoReturnPlanOPI.setState("agentPlan");
							dtoReturnPlanOPI.setType("OPI");
							dtoReturnPlanOPI.setYqfx(new BigDecimal("0"));
							dtoReturnPlanOPI.setOID(StringUtil.getUUID());
							payReturnPlanMapper.saveReturnPlan(dtoReturnPlanOPI);
						}
					}

					for (int i = 0; i < returnNumber; i++) {
						PayReturnPlan dtoReturnPlan = new PayReturnPlan();
						dtoReturnPlan.setBusinessOID(returnBusinessOID);
						dtoReturnPlan.setCashPoolOID(Newdbc.getCashPoolOID());
						dtoReturnPlan.setRepayOID(lRepayOID.get(i));
						dtoReturnPlan.setDebitCreditOID(Newdbc.getOID());
						dtoReturnPlan.setMemberOID(Newdbc.getOutMemberOID());
						dtoReturnPlan.setOutMemberOID(repayMember);
						dtoReturnPlan.setReturnDate(lReturnDate.get(i));
						// 本金=投资本金/返回期数
						BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2,
								BigDecimal.ROUND_HALF_EVEN);
						// 利息 =剩余本金*标的月利率
						BigDecimal interest = money.multiply(creditRate).setScale(2, BigDecimal.ROUND_HALF_EVEN);

						dtoReturnPlan.setMoney(principal.add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
						dtoReturnPlan.setPrincipalMoney(principal);
						dtoReturnPlan.setInterestMoney(interest);
						totalPrincipal = totalPrincipal.add(principal);
						dtoReturnPlan.setTotalPrincipalMoney(principal.multiply(new BigDecimal(i + 1)));
						totalInterest = totalInterest.add(interest);
						dtoReturnPlan.setTotalInterestMoney(interest.multiply(new BigDecimal(i + 1)));
						dtoReturnPlan.setRate(creditRate);
						dtoReturnPlan.setNum(i + 1);
						dtoReturnPlan.setSum(returnNumber);
						dtoReturnPlan.setYqfx(new BigDecimal("0"));
						dtoReturnPlan.setState("plan");
						if ("CW".equals(isTZorCW) && "HH".equals(tzType)) {
							dtoReturnPlan.setType("EPEI");
						} else {
							if ("EPEI".equals(oldType) && "HH".equals(tzType) && "HH".equals(isTZorCW)) {
								dtoReturnPlan.setType("CP");
							} else {
								dtoReturnPlan.setType(oldType);
							}
						}
						dtoReturnPlan.setOID(StringUtil.getUUID());
						payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);

					}
					dtoTransferReturn.setState("finish");
					dtoTransferReturn.setDebitCreditOIDNew(Newdbc.getOID());
					transferReturnMapper.updataTransferReturn(dtoTransferReturn);
					Newdbc.setInterest(totalInterest);
					debitCreditMapper.updateDebitCredit(Newdbc);
					// 新手结算
					if ("CW".equals(isTZorCW) && "XS".equals(tzType)) {

						ProductInfoDTO dtoProductInfo = this.gainProductInfo(businessOID);
						// 解冻贴息
						BigDecimal tll = dtoProductInfo.getCreditRate().divide(new BigDecimal("365"),
								8, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal proInterest = dtoDebitCredit.getMoney().multiply(new BigDecimal(15)).multiply(tll)
								.setScale(2, BigDecimal.ROUND_HALF_EVEN);
						BigDecimal ydzh = ((BigDecimal) dtoDebitCredit.getMoney()).add(proInterest);
						// 解冻本金
						controlPay.unFreeze((String) dtoTransferReturn.getInMemberOID(), ydzh);
						// 增加贴息
						this.controlCustomerBusiness.saveCustomerBusiness("回款", ydzh, "回款的本金利息金额",
								(String) dtoTransferReturn.getInMemberOID());
						// TODO 加入特权组,发送消息

						/*
						 * DTBServiceAuth dtbServiceAuth = new DTBServiceAuth();
						 * dtbServiceAuth.addUserOID((String)
						 * dtoTransferReturn.get("inMemberOID")); // 增加贴息
						 * ServiceFactory.instanceMessageService(this).
						 * sendMessage("回款", "您收到回款金额为" + ydzh + "元", "系统",
						 * dtbServiceAuth, null); DTBServiceAuth dtbServiceAuth1
						 * = new DTBServiceAuth(); controlCustomer =
						 * (ControlCustomer) this.gainInstance(controlCustomer,
						 * ControlCustomer.class);
						 * dtbServiceAuth1.addUserOID((String) controlCustomer
						 * .gainCustomerByMemberOID((String)
						 * dtoTransferReturn.get("inMemberOID")).get("name"));
						 * // 增加贴息 ServiceFactory.instanceMessageService(this).
						 * sendSMSMessage("回款", "【砖头网】您收到回款金额为" + ydzh + "元",
						 * null, "系统", dtbServiceAuth1, null);
						 */

					}
					// 特权利润扣除
					dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);
					/*
					 * dtocReturnPlan =
					 * payReturnPlanMapper.findByDebitCreditOIDAndState(
					 * debitCreditOID, "finish");
					 * 
					 * Integer dtocReturnPlanSize = dtocReturnPlan.size();
					 */
					controlCashPool.finishTransferReturnUnFreeze((String) dtoDebitCredit.getOID(),
							(String) dtoDebitCredit.getOutMemberOID(), transferReturnMoney, null);
				}
			}

			// 债转后修改原债转回款计划状态
			if (dcrtOID.size() > 0) {
				for (int d = 0; d < dcrtOID.size(); d++) {
					List<PayReturnPlan> putReturnPlan = payReturnPlanMapper.queryByreturnDate((String) dcrtOID.get(d));

					for (PayReturnPlan dtorp : putReturnPlan) {
						if (!"OPI".equals((String) dtorp.getType())) {
							if ("plan".equals((String) dtorp.getState())) {
								// 此处如果把已还“finish”改为“transferReturn”，结算会错误
								dtorp.setState("transferReturn");
								payReturnPlanMapper.updateReturnPlan(dtorp);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("finishTransferReturnNEW(String businessOID, Date beginDate, String isTZorCW, String tzType, String ly)"+e.getMessage());
		}
	}

	@Override
	public void addCashPoolUserTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
			BigDecimal money, Date transferReturnDate, String ly) {
		try {
			int isCW = 0;
			PayTransferReturn dtoTF = null;

			PayDebitCredit oldDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

			if (oldDebitCredit == null) {
				PayTransferReturn transferReturn = transferReturnMapper.getTransferReturnByOid(debitCreditOID);
				if (transferReturn != null) {
					debitCreditOID = transferReturn.getDebitCreditOIDNew();
				}
				dtoTF = transferReturnMapper
						.gainTransferReturnByDebitCreditNewOIDAndState(debitCreditOID, "finish");

			}else{
				List<PayTransferReturn> dtocTransferReturnList = transferReturnMapper
						.getTransferReturnByDebitCreditOIDAndstate(debitCreditOID, "finish");
				for (PayTransferReturn dto : dtocTransferReturnList) {
					dtoTF = dto;
				}
			}
			BigDecimal creditRate = new BigDecimal(0);
			DataPackageDTO debitInfo = this.gainDebitInfo(businessOID);
			
			creditRate =debitInfo.getDebitRate();
			MemberMember outMember = userMapper.findUserByOID(oldDebitCredit.getOutMemberOID());
			if (outMember != null && outMember.getUserType() != null) {
				if ("1".equals(outMember.getUserType())) {
					isCW = 1;
				}
			}
			
			int lDay = 0;
			if (dtoTF != null) {
				// 多次债转
				PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(dtoTF.getDebitCreditOIDNew());
				String loanNumber = "";
				if (debitInfo != null) {
					loanNumber = debitInfo.getLoanNumber();
				}
				// 截取字符拼成债转编号
				loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
				Date minReturnDate = null;
				PayReturnPlan dtocReturnPlan = payReturnPlanMapper
						.findMINByDebitCreditOIDAndState((String) dtoDebitCredit.getOID(), "plan");

				if (dtocReturnPlan != null) {
					minReturnDate =dtocReturnPlan.getReturnDate();
				}
				String bDate = dtoDebitCredit.getStartDateTime().toString();
				if (null != minReturnDate) {
					bDate = ToolsDatas.gainPlusAndReduceDay(DateUtils.formatJustDate(minReturnDate.getTime()), 1, 1);
				}

				lDay = ToolsDatas.getDateSpace(bDate, DateUtils.formatJustDate(transferReturnDate.getTime()));
				if (lDay < 0) {
					lDay = 0;
				}
				// 投资本金=投资总额*转让本金/转让总额
				BigDecimal tzbj = money.multiply(dtoDebitCredit.getSurplusPrincipalMoney())
						.divide(debitInfo.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);

				// 垫付利息=投资总额-tzbj
				BigDecimal dflx = money.subtract(tzbj);

				BigDecimal otherMoney = tzbj.add(dflx);
				Boolean flag = false;
				List<PayCashPool> cpdc = payCashPoolMapper.findByBusinessOIDAndHaveMoney(outBusinessOID);
				if (cpdc != null && cpdc.size() > 0) {
					for (PayCashPool dtoCashPool : cpdc) {
						PayTransferReturn dtoTransferReturn = new PayTransferReturn();
						// 投资额
						BigDecimal principal = null;

						if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() > 0) {
							principal =dtoCashPool.getMoney();
							otherMoney = otherMoney.subtract(principal);
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() < 0) {
							principal = otherMoney;
							flag = true;
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() == 0) {
							principal = otherMoney;
							flag = true;
						}

						// 债转
						controlCashPool.transferReturnCashPool(businessOID,dtoCashPool.getMemberOID(),
								dtoDebitCredit.getOutMemberOID(), principal);

						// 投资本金=投资总额*转让本金/转让总额
						BigDecimal cpbj = new BigDecimal(0);

						cpbj = principal.multiply(dtoDebitCredit.getSurplusPrincipalMoney())
								.divide(debitInfo.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
						// 垫付利息=投资总额-tzbj
						BigDecimal cplx = principal.subtract(cpbj);

						dtoTransferReturn.setBusinessOID(businessOID);
						dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
						dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
						dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
						dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
						dtoTransferReturn.setCreditType("CP");
						dtoTransferReturn.setCreditRate((BigDecimal) debitInfo.getDebitRate());
						dtoTransferReturn.setMoney(cpbj.add(cplx));
						dtoTransferReturn.setPrincipalMoney(cpbj);
						dtoTransferReturn.setAdvanceInterest(cplx);
						dtoTransferReturn.setPrivilegePrincipal(new BigDecimal(0));
						dtoTransferReturn.setPrivilegeInterest(new BigDecimal(0));
						dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
						dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
						dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
						dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
						dtoTransferReturn.setLaiyuan(dtoCashPool.getLaiyuan());
						if (isCW > 0) {
							dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						} else {
							Date stday = dtoDebitCredit.getStartDateTime();
							Date eDay = ToolDateTime.addDay(stday, lDay);

							dtoTransferReturn.setEndDateTime(eDay);
						}
						dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						dtoTransferReturn.setState("investment");
						dtoTransferReturn.setDebitCreditOIDNew("");
						dtoTransferReturn.setOID(StringUtil.getUUID());
						transferReturnMapper.saveTransferReturn(dtoTransferReturn);
						dtoCashPool.setMoney(((BigDecimal) dtoCashPool.getMoney()).subtract(principal));
						payCashPoolMapper.savePayCashPool(dtoCashPool);
						if (flag) {
							break;
						}
					}
				}
				// -------------------------------------------------------------------------
			} else {
				// 第一次
				PayDebitCredit dtoDebitCredit = this.queryByid(debitCreditOID);
				String loanNumber = "";
				// 截取字符拼成债转编号
				loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
				int yhDay = 0;// 已还
				int yhqs = dtoDebitCredit.getReturnNumber() - dtoDebitCredit.getReturnSurplusNumber();
				String syhkr = ToolsDatas.gainPlusAndReduceDay(dtoDebitCredit.getStartDateTime().toString(), yhqs, 0);
				yhDay = ToolsDatas.getDateSpace(dtoDebitCredit.getStartDateTime().toString(), syhkr);
				if (isCW > 0) {
					// CW
					lDay = ToolsDatas.getDateSpace(dtoDebitCredit.getStartDateTime().toString(),
							transferReturnDate.toString());
					lDay = lDay - yhDay;
				} else {
					lDay = ToolsDatas.getDateSpace(dtoDebitCredit.getStartDateTime().toString(),
							transferReturnDate.toString());
					lDay = lDay - yhDay;
				}
				if (lDay < 0) {
					lDay = 0;
				}

				BigDecimal creditRateDay = (creditRate).divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
				// 区分不同产品，使用不同本金或剩余本金*************************************************************************************
				// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
				BigDecimal advanceInterest = dtoDebitCredit.getMoney().multiply(creditRateDay)
						.multiply(new BigDecimal(lDay)).multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
						.divide((BigDecimal) dtoDebitCredit.getPrincipalMoney(), 2, BigDecimal.ROUND_HALF_EVEN);

				BigDecimal sumTransferMoney = dtoDebitCredit.getPrincipalMoney().add(advanceInterest);
				// *************************************************************************************
				BigDecimal tzbj = dtoDebitCredit.getSurplusPrincipalMoney();
				BigDecimal dflx =debitInfo.getMoney()
						.subtract(dtoDebitCredit.getSurplusPrincipalMoney());

				BigDecimal otherMoney = tzbj.add(dflx);
				Boolean flag = false;
				List<PayCashPool> cpdc =payCashPoolMapper.findByBusinessOIDAndHaveMoney(outBusinessOID); 
					
				if (cpdc != null && cpdc.size() > 0) {
					for (PayCashPool dtoCashPool : cpdc) {
						PayTransferReturn dtoTransferReturn = new PayTransferReturn();
						// 投资额
						BigDecimal principal = null;

						if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() > 0) {
							principal =dtoCashPool.getMoney();
							otherMoney = otherMoney.subtract(principal);
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() < 0) {
							principal = otherMoney;
							flag = true;
						} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() == 0) {
							principal = otherMoney;
							flag = true;
						}

						// 债转
						controlCashPool.transferReturnCashPool(businessOID,dtoCashPool.getMemberOID(),
								dtoDebitCredit.getOutMemberOID(), principal);

						// 投资本金=投资总额*转让本金/转让总额
						BigDecimal cpbj = new BigDecimal(0);
						if (sumTransferMoney.compareTo(dtoDebitCredit.getMoney()) == 1) {
							cpbj = principal.multiply(dtoDebitCredit.getPrincipalMoney())
									.divide(sumTransferMoney, 2, BigDecimal.ROUND_HALF_EVEN);
						} else {
							cpbj = principal.multiply(dtoDebitCredit.getPrincipalMoney())
									.divide(dtoDebitCredit.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
						}

						// }

						// 垫付利息=投资总额-tzbj
						BigDecimal cplx = principal.subtract(cpbj);

						dtoTransferReturn.setBusinessOID(businessOID);
						dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
						dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
						dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
						dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
						dtoTransferReturn.setCreditType("CP");
						dtoTransferReturn.setCreditRate(debitInfo.getDebitRate());
						dtoTransferReturn.setMoney(cpbj.add(cplx));
						dtoTransferReturn.setPrincipalMoney(cpbj);
						dtoTransferReturn.setAdvanceInterest(cplx);
						dtoTransferReturn.setPrivilegePrincipal(new BigDecimal(0));
						dtoTransferReturn.setPrivilegeInterest(new BigDecimal(0));
						dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
						dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
						dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
						dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
						dtoTransferReturn.setLaiyuan(dtoCashPool.getLaiyuan());
						if (isCW > 0) {
							dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						} else {
							Date stday = dtoDebitCredit.getStartDateTime();
							Date eDay = ToolDateTime.addDay(stday, lDay);

							dtoTransferReturn.setEndDateTime(eDay);
						}
						dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
						dtoTransferReturn.setState("investment");
						dtoTransferReturn.setDebitCreditOIDNew("");
						transferReturnMapper.saveTransferReturn(dtoTransferReturn);
						dtoCashPool.setMoney(dtoCashPool.getMoney().subtract(principal));
						payCashPoolMapper.savePayCashPool(dtoCashPool);
						if (flag) {
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("addCashPoolUserTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,"
			+"BigDecimal money, Date transferReturnDate, String ly)");
			
		}

	}

}
