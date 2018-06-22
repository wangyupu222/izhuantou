package com.izhuantou.fund.rpc.impl.repay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.MapUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.bidding.DataPackageDTO;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.Webp2pFinanceDebtTransfer;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.Webp2pFinanceDebtTransferMapper;
import com.izhuantou.fund.rpc.api.ControlCashPool;
import com.izhuantou.fund.rpc.api.ControlCustomerBusiness;
import com.izhuantou.fund.rpc.api.repay.RepayProcess;
import com.izhuantou.service.impl.personalCenter.MyloanServiceImpl;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.message.SendMessageService;

/**
 * 之前业务逻辑中 还款的方法是 ControDebitCreat中
 * 
 * @author Administrator
 *
 */
@Service("repayProcess")
public class RepayProcessImpl implements RepayProcess {
	private static final Logger logger = LoggerFactory.getLogger(MyloanServiceImpl.class);
	@Autowired
	private PayRepayPlanMapper payRepayPlanMapper;
	@Autowired
	private ControlCashPool controlCashPool;
	@Autowired
	private ControlPayService controlPay;
	@Autowired
	private PayReturnPlanMapper payReturnPlanMapper;
	@Autowired
	private MemberMemberMapper memberMapper;
	@Autowired
	private PayDebitCreditMapper payDebitCreditMapper;
	@Autowired
	private PayCashPoolMapper payCashPoolMapper;
	@Autowired
	private MessageContentBusinessMapper messageContentBusinessMapper;
	@Autowired
	private ControlCustomerBusiness controlCustomerBusiness;
	@Autowired
	private SendMessageService sendMessageService;
	@Autowired
	private PayCustomerMapper payCustomerMapper;
	@Autowired
	private MessageSmsHistoryMapper messageSmsHistoryMapper;
	@Autowired
	private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;
	@Autowired
	private WebP2pBiddingExamineMapper biddingExamineMapper;
	@Autowired
	private Webp2pFinanceDebtTransferMapper financeDebtTransferMapper;
	@Autowired
	private PayPrivilegeMapper payPrivilegeMapper;
	@Autowired
	private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

	@Override
	public String normalRepay(String repayOID, BigDecimal yqfx) {
		try {

			if (StringUtil.isNotEmpty(repayOID)) {
				PayRepayPlan dtoRepayPlan = payRepayPlanMapper.queryByOID(repayOID);
				BigDecimal allbj = new BigDecimal(0);// 剩余全部本金
				BigDecimal alllx = new BigDecimal(0);// 剩余全部利息
				List<PayRepayPlan> dtoc = payRepayPlanMapper.findByMemberOIDAndState(dtoRepayPlan.getMemberOID(),
						"plan");
				if (dtoc.size() > 0) {
					for (PayRepayPlan repayMent : dtoc) {
						alllx = alllx.add(repayMent.getInterestMoney());
						allbj = allbj.add(repayMent.getPrincipalMoney());
					}
					// 管理费
					BigDecimal manageMoney = dtoRepayPlan.getManageMoney();
					if (manageMoney.compareTo(new BigDecimal(0)) != 0) {
						controlCashPool.manageMoney(repayOID, dtoRepayPlan.getMemberOID(), manageMoney);
					}

					// 咨询费
					BigDecimal serviceMoney = dtoRepayPlan.getServiceMoney();
					if (serviceMoney.compareTo(new BigDecimal(0)) != 0) {
						controlCashPool.serviceMoney(repayOID, dtoRepayPlan.getMemberOID(), serviceMoney);
					}
					// money(为每期应还钱数) + yqfx(逾期罚息)=用户需要还的所有金额
					BigDecimal allFreeze = dtoRepayPlan.getMoney().add(yqfx);

					// 所有冻结减去管理费和咨询费
					allFreeze = allFreeze.subtract(manageMoney).subtract(serviceMoney);

					// 回款计划
					List<PayReturnPlan> returnList = payReturnPlanMapper.findByRepayOIDAndState(repayOID, "plan");
					int i = 1;
					for (PayReturnPlan dtoReturnPlan : returnList) {

						BigDecimal returnyqfx = yqfx;
						BigDecimal returnAllMoney = dtoReturnPlan.getMoney();
						// 还款全部资金
						BigDecimal repprincipalMoney = dtoRepayPlan.getPrincipalMoney();
						// 还款利息
						BigDecimal repinterestMoney = dtoRepayPlan.getInterestMoney();
						// 回款本金
						BigDecimal returnprincipalMoney = dtoReturnPlan.getPrincipalMoney();
						// 回款利息
						BigDecimal returninterestMoney = dtoReturnPlan.getInterestMoney();

						// 贴息 或加息
						// 抵押产品本金为0除数为0判断
						BigDecimal repayInterestMoney = new BigDecimal(0);
						if (repprincipalMoney.compareTo(new BigDecimal(0.00)) != 0) {
							// （回款本金 / 还款本金 ）* 利息 = 出借人应得利息
							repayInterestMoney = repinterestMoney.multiply(returnprincipalMoney)
									.divide(repprincipalMoney, 2, BigDecimal.ROUND_HALF_EVEN);
						}
						// 根据出借比计算罚息返回比例
						if (repprincipalMoney.compareTo(new BigDecimal(0.00)) != 0) {
							returnyqfx = returnyqfx.multiply(returnprincipalMoney).divide(repprincipalMoney, 2,
									BigDecimal.ROUND_HALF_EVEN);
						}
						dtoReturnPlan.setYqfx(new BigDecimal(0));

						if (returnyqfx.compareTo(new BigDecimal(0)) > 0) {
							// 逾期罚息转至出借人手中，但不超出出借人逾期收益
							controlPay.transferFreezeToFreeze(dtoReturnPlan.getOutMemberOID(),
									dtoReturnPlan.getMemberOID(), returnyqfx);
							MemberMember member = memberMapper.findUserByOID(dtoReturnPlan.getMemberOID());
							String isCW = "0";
							if (member != null) {
								String usertype = member.getUserType();
								if (StringUtil.isNotEmpty(usertype)) {
									isCW = usertype;
								}
							}
							if ("1".equals(isCW)) {// 如果该用户为财务账号，解冻罚息
								controlPay.unFreeze(dtoReturnPlan.getMemberOID(), returnyqfx);
							}
							allFreeze = allFreeze.subtract(returnyqfx);
							dtoReturnPlan.setYqfx(returnyqfx);
						}
						PayDebitCredit dtoDebitCredit = payDebitCreditMapper
								.queryByPKOid(dtoReturnPlan.getDebitCreditOID());
						if ("EPEINEW".equals(dtoReturnPlan.getType())) {
							MemberMember member = memberMapper.findUserByOID(dtoReturnPlan.getMemberOID());
							String isCW = "0";
							if (member != null) {
								String usertype = member.getUserType();
								if (StringUtil.isNotEmpty(usertype)) {
									isCW = usertype;
								}
							}
							if (returninterestMoney.compareTo(repayInterestMoney) > 0) {// 计划>占比
								controlCashPool.discount(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
										returninterestMoney.subtract(repayInterestMoney));// 贴给出借用户
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(repayInterestMoney),
										allFreeze);// 还款人还占比利息+本金
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(repayInterestMoney)));
								allFreeze = allFreeze.subtract(returnprincipalMoney.add(repayInterestMoney));
							} else if (returninterestMoney.compareTo(repayInterestMoney) < 0) {
								controlCashPool.help(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
										repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(returninterestMoney),
										allFreeze.subtract(repayInterestMoney.subtract(returninterestMoney)));
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(returninterestMoney)));
								allFreeze = allFreeze.subtract(returnprincipalMoney.add(repayInterestMoney));

							} else {
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(returninterestMoney),
										allFreeze);
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(returninterestMoney)));
								allFreeze = allFreeze.subtract(returnprincipalMoney.add(returninterestMoney));

							}
							if ("1".equals(isCW)) {
								// 解冻 是否最后一期
								if (dtoReturnPlan.getNum().equals(dtoReturnPlan.getSum())) {
									controlCashPool.finishRepayNCP(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getMoney());
									dtoDebitCredit.setState("finish");

								} else {
									controlCashPool.finishRepayNCP(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getMoney());
								}
							}
						}

						if ("EPEI".equals(dtoReturnPlan.getType()) || "EPEIFP".equals(dtoReturnPlan.getType())) {
							if (returninterestMoney.compareTo(repayInterestMoney) > 0) {
								controlCashPool.discount(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
										returninterestMoney.subtract(repayInterestMoney));
								controlCashPool.repayNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(repayInterestMoney),
										allFreeze);
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(repayInterestMoney)));

								allFreeze = allFreeze.subtract(returnprincipalMoney.add(repayInterestMoney));
							} else if (returninterestMoney.compareTo(repayInterestMoney) < 0) {
								controlCashPool.help(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
										repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.repayNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(returninterestMoney),
										allFreeze.subtract(repayInterestMoney.subtract(returninterestMoney)));
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(returninterestMoney)));
								allFreeze = allFreeze.subtract(returnprincipalMoney.add(repayInterestMoney));
							} else {
								controlCashPool.repayNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(returninterestMoney),
										allFreeze);
								dtoReturnPlan.setMoney(
										returnAllMoney.subtract(returnprincipalMoney.add(returninterestMoney)));
								allFreeze = allFreeze.subtract(returnprincipalMoney.add(returninterestMoney));
							}
							// 解冻 是否最后一期
							if (dtoReturnPlan.getNum().equals(dtoReturnPlan.getSum())) {
								if ("EPEI".equals(dtoReturnPlan.getType())) {
									controlCashPool.finishRepay(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getMoney());

								} else if ("EPEIFP".equals(dtoReturnPlan.getType())) {
									controlCashPool.finishRepay(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getTotalPrincipalMoney()
													.add(dtoReturnPlan.getInterestMoney()));

								}

								dtoDebitCredit.setState("finish");

							} else {
								if ("EPEI".equals(dtoReturnPlan.getType())) {
									controlCashPool.finishRepay(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getMoney());

								} else if ("EPEIFP".equals(dtoReturnPlan.getType())) {
									controlCashPool.finishRepay(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
											dtoReturnPlan.getInterestMoney());
								}
							}

						} else if ("CP".equals(dtoReturnPlan.getType())) {
							PayCashPool cashpool = payCashPoolMapper.findByOID(dtoReturnPlan.getCashPoolOID());

							if ("investment".equals(dtoReturnPlan.getState())) {
								if ("OPI".equals(cashpool.getCreditType())) {
									// 本金复投
									controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
											dtoReturnPlan.getMemberOID(), returnprincipalMoney, allFreeze);// 转账给出借人
									allFreeze = allFreeze.subtract(returnprincipalMoney);
									cashpool.setMoney(cashpool.getMoney().add(dtoReturnPlan.getPrincipalMoney()));
									cashpool.setInterest(cashpool.getInterest().add(dtoReturnPlan.getInterestMoney()));
									payCashPoolMapper.updatePayCashPool(cashpool);
									if (returninterestMoney.compareTo(repayInterestMoney) < 0) {
										controlCashPool.help(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
												repayInterestMoney.subtract(returninterestMoney));
										controlCashPool.repayNCPNew(dtoReturnPlan.getOID(),
												dtoReturnPlan.getOutMemberOID(), dtoReturnPlan.getMemberOID(),
												returninterestMoney,
												allFreeze.subtract(repayInterestMoney.subtract(returninterestMoney)));
										dtoReturnPlan.setMoney(returnAllMoney.subtract(returninterestMoney));

										allFreeze = allFreeze.subtract(repayInterestMoney);

									} else {
										controlCashPool.repayNCPNew(dtoReturnPlan.getOID(),
												dtoReturnPlan.getOutMemberOID(), dtoReturnPlan.getMemberOID(),
												returninterestMoney, allFreeze);
										dtoReturnPlan.setMoney(returnAllMoney.subtract(returninterestMoney));

										allFreeze = allFreeze.subtract(returninterestMoney);
									}
								}
							}
						}
						/**
						 * 抵押类产品还款 BIAP还款 新增 还款自动划拨用户回款
						 */
						if ("BIAP".equals(dtoReturnPlan.getType())) {
							controlCashPool.repayNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
									dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(returninterestMoney),
									allFreeze);
							dtoReturnPlan
									.setMoney(returnAllMoney.subtract(returnprincipalMoney.add(returninterestMoney)));
							allFreeze = allFreeze.subtract(returnprincipalMoney.add(returninterestMoney));

							// 回款，直接解冻
							controlPay.unFreeze(dtoReturnPlan.getMemberOID(), dtoReturnPlan.getMoney());
							BigDecimal tqInterest = new BigDecimal("0");
							if (dtoReturnPlan.getNum().equals(dtoReturnPlan.getSum())) {
								// 如果最后一期，读取加息券信息，划拨贴息
								PayDebitCredit debitCredit = payDebitCreditMapper
										.findByOID(dtoReturnPlan.getDebitCreditOID());
								String tqOID = debitCredit.getPrivilegeOID();
								if (StringUtil.isNotEmpty(tqOID)) {
									tqInterest = debitCredit.getPrivilegeInterest();
									if (tqInterest != null && tqInterest.compareTo(new BigDecimal(0)) > 0) {
										///////////////////////// 特权收益支付///////////////////////////////////////
										if (tqInterest.compareTo(new BigDecimal(0)) > 0) {
											controlCashPool.discount(dtoReturnPlan.getBusinessOID(),
													dtoReturnPlan.getMemberOID(), tqInterest);
											controlPay.unFreeze(dtoReturnPlan.getMemberOID(), tqInterest);
										}

										if (tqInterest.compareTo(new BigDecimal(0)) > 0) {
											PayCustomer customer = payCustomerMapper
													.findByMemberOID(dtoReturnPlan.getMemberOID());
											MessageContentBusiness business = new MessageContentBusiness();
											business.setTitle("回款");
											String content = "回款的加息劵收益为" + tqInterest + "元";
											business.setContent(content);
											business.setSendUser("系统");
											business.setReceiveUserOID(dtoReturnPlan.getMemberOID());
											business.setOID(StringUtil.getUUID());
											messageContentBusinessMapper.saveMessageBusiness(business);

											controlCustomerBusiness.saveCustomerBusiness("回款", tqInterest, "回款的特权收益为",
													dtoReturnPlan.getMemberOID());

											MessageSmsHistory smsHistory = new MessageSmsHistory();
											smsHistory.setTitle("回款");
											String smsContent = "【砖头网】您回款的加息劵收益为" + tqInterest + "元";
											smsHistory.setContent(smsContent);
											smsHistory.setSendUser("系统");
											smsHistory.setReceiveUser(customer.getName());
											Map<String, String> result = sendMessageService
													.sendMessage(customer.getName(), content);
											String state = result.get("Description");
											smsHistory.setState(state);
											messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
										}
									}
								}
								dtoDebitCredit.setState("finish");
								// 修改标的状态
								WebP2pNormalBiddingRuning normalbidding = normalBiddingRuningMapper
										.findByOID(dtoReturnPlan.getBusinessOID());
								normalbidding.setProductStatus("5");
								normalBiddingRuningMapper.updateNormalBiddingBuning(normalbidding);
							}
							controlPay.updateAccount(dtoReturnPlan.getMemberOID());
							// 回款添加免费额度
							BigDecimal freeline = new BigDecimal("0");
							PayCustomer customer = payCustomerMapper.findByMemberOID(dtoReturnPlan.getMemberOID());
							if (customer.getFreeline() != null) {
								freeline = customer.getFreeline();
							}
							freeline = freeline.add(dtoReturnPlan.getMoney().add(tqInterest));
							customer.setFreeline(freeline);
							payCustomerMapper.updataCutomer(customer);
						}

						BigDecimal surplusPrincipalMoney = dtoDebitCredit.getSurplusPrincipalMoney();
						surplusPrincipalMoney = surplusPrincipalMoney.subtract(dtoReturnPlan.getPrincipalMoney());
						dtoDebitCredit.setSurplusPrincipalMoney(surplusPrincipalMoney);

						Integer returnSurplusNumber = dtoDebitCredit.getReturnSurplusNumber();
						returnSurplusNumber = returnSurplusNumber - 1;
						dtoDebitCredit.setReturnSurplusNumber(returnSurplusNumber);

						BigDecimal interest = dtoDebitCredit.getInterest();
						BigDecimal interestMoney = dtoReturnPlan.getInterestMoney();
						interest = interest.subtract(interestMoney);

						payDebitCreditMapper.updateDebitCredit(dtoDebitCredit);
						dtoReturnPlan.setState("finish");
						payReturnPlanMapper.updateReturnPlan(dtoReturnPlan);

						if (!"BIAP".equals(dtoReturnPlan.getType())) {
							if (dtoReturnPlan.getNum().equals(dtoReturnPlan.getSum())) {
								// 最后一期回款后给客户发送还款结清短信
								List<WebP2pBiddingExamine> biddinglist = biddingExamineMapper
										.findByMemberOID(dtoReturnPlan.getMemberOID());
								String username = "";
								String jkbh = "";
								BigDecimal hkje = new BigDecimal(0);
								WebP2pBiddingExamine examine = biddinglist.get(biddinglist.size() - 1);
								if (i == 1) {
									PayCustomer customer = payCustomerMapper.findByMemberOID(examine.getMemberOID());

									MessageSmsHistory smsHistory = new MessageSmsHistory();
									smsHistory.setTitle("还款");
									String nowDate = DateUtils.formartDate(new Date(), "yyyy-MM-dd");
									String smsContent = "【砖头网】 尊敬的 " + username + "先生/女士，" + "您申请的编号为" + jkbh + "的借款，金额"
											+ hkje + "元整，已于" + nowDate + "完成最后一期还款，至此全部结清，请您知悉，特此告知！";
									smsHistory.setContent(smsContent);
									smsHistory.setSendUser("系统");
									smsHistory.setReceiveUser(customer.getName());
									Map<String, String> result = sendMessageService.sendMessage(customer.getName(),
											smsContent);
									String state = result.get("Description");
									smsHistory.setState(state);
									messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
								}
								i++;
							}
						}
					}

				}
				dtoRepayPlan.setState("finish");
				payRepayPlanMapper.updateRepayPlan(dtoRepayPlan);
				return "1";
			}
		} catch (Exception e) {
			logger.error("normalRepay(String repayOID, BigDecimal yqfx)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String beforeRepay(String businessOID) {
		try {
			if(StringUtil.isEmpty(businessOID)){
				return null;
			}
			int i = 0;
			// 出借人全部冻结
			BigDecimal allFreeze = null;
			// 所有剩余本金
			BigDecimal allPrincipalMoney = null;
			// 档期利息
			BigDecimal allInterestMoney = null;
			// 所有已还本金
			BigDecimal finishAllPrincipalMoney = new BigDecimal(0);
			// 对每个人的回款本金加总，后面比较还款总数，进行贴息
			BigDecimal perAllReturnPrinMoney = new BigDecimal(0);
			DataPackageDTO dtoDebitInfo = this.gainDebitInfo(businessOID);

			// 提前还款违约金
			BigDecimal earlyRepayMoney = dtoDebitInfo.getMoney().multiply(dtoDebitInfo.getEarlyRepay());
			// ----------查询借款人未还款记录---------
			List<PayRepayPlan> repayPlanTemp = payRepayPlanMapper.queryByBusinessOIDAndState(businessOID, "plan");
			// ----------查询借款人已还款记录---------
			List<PayRepayPlan> repayPlanFinish = payRepayPlanMapper.queryByBusinessOIDAndState(businessOID, "finish");

			for (PayRepayPlan finish : repayPlanFinish) {
				// 求得借款人已还本金总和
				finishAllPrincipalMoney = finishAllPrincipalMoney.add(finish.getPrincipalMoney());
			}

			for (PayRepayPlan dtoRepayPlan : repayPlanTemp) {
				if (i == 0) {
					// 所有剩余本金
					allPrincipalMoney = dtoRepayPlan.getPrincipalMoney().add(dtoRepayPlan.getSurplusPrincipal());
					// 一个月罚息
					BigDecimal interestMoney = dtoRepayPlan.getInterestMoney();
					// 当期利息
					allInterestMoney = interestMoney;

					if ("BIAP".equals(dtoDebitInfo.getDebitType())) {
						// 一个月罚息重置为0
						interestMoney = new BigDecimal(0);
					}
					// -----------当期月利息+一个月的罚息----------
					allFreeze = allPrincipalMoney.add(interestMoney).add(allInterestMoney).add(earlyRepayMoney)
							.add(dtoRepayPlan.getManageMoney()).add(dtoRepayPlan.getServiceMoney());
					controlCashPool.freezeRepay(businessOID, dtoRepayPlan.getMemberOID(), allFreeze);
					// 管理费
					BigDecimal manageMoney = dtoRepayPlan.getManageMoney();
					if (manageMoney.compareTo(new BigDecimal(0)) != 0) {
						controlCashPool.manageMoney(dtoRepayPlan.getOID(), dtoRepayPlan.getMemberOID(),
								dtoRepayPlan.getManageMoney());
					}

					// 咨询费
					BigDecimal serviceMoney = dtoRepayPlan.getServiceMoney();
					if (serviceMoney.compareTo(new BigDecimal(0)) != 0) {
						controlCashPool.serviceMoney(dtoRepayPlan.getOID(), dtoRepayPlan.getMemberOID(),
								dtoRepayPlan.getServiceMoney());
					}
					// 违约金
					if (earlyRepayMoney.compareTo(new BigDecimal(0)) != 0) {
						controlCashPool.earlyRepayPenalty(businessOID, dtoRepayPlan.getMemberOID(), earlyRepayMoney);
					}

				}
				dtoRepayPlan.setState("finish");
				payRepayPlanMapper.updateRepayPlan(dtoRepayPlan);

				allFreeze = allFreeze.subtract(dtoRepayPlan.getManageMoney()).subtract(dtoRepayPlan.getServiceMoney())
						.subtract(earlyRepayMoney);

				List<PayReturnPlan> dtocReturnPlan = payReturnPlanMapper.findByRepayOIDAndState(dtoRepayPlan.getOID(),
						"plan");

				for (PayReturnPlan dtoReturnPlan : dtocReturnPlan) {
					if (i == 0) {
						PayDebitCredit dtoDebitCredit = payDebitCreditMapper
								.queryByPKOid(dtoReturnPlan.getDebitCreditOID());
						// TODO 此处debitCredit的 OID 不能做为其他表的主键
						Webp2pFinanceDebtTransfer ifTransfer = financeDebtTransferMapper
								.findByOID(dtoDebitCredit.getOID());
						if (ifTransfer != null) {
							ifTransfer.setProductStatus("10");
							financeDebtTransferMapper.updateFinanceDebt(ifTransfer);
						}
						// 对应还款计划一期利息
						BigDecimal repinterestMoney = dtoRepayPlan.getInterestMoney();
						// 回款计划一期利息
						BigDecimal returninterestMoney = dtoReturnPlan.getInterestMoney();
						// 对应回款计划中一期还款本金
						BigDecimal returnPrincipalMoney = dtoReturnPlan.getPrincipalMoney();
						// 对应还款计划一期还款本金
						BigDecimal repprincipalMoney = dtoRepayPlan.getPrincipalMoney();
						// --------通过占比得到该member的对应还款利息--------
						BigDecimal repayInterestMoney = new BigDecimal(0.00);
						if (repprincipalMoney.compareTo(new BigDecimal(0.00)) != 0) {
							repayInterestMoney = repinterestMoney.multiply(returnPrincipalMoney)
									.divide(repprincipalMoney, 2, BigDecimal.ROUND_HALF_EVEN);
						}
						// --------借款人对应的所有待还本金-----
						BigDecimal allRpPrincipalMoeny = dtoDebitCredit.getSurplusPrincipalMoney();

						// 还款全部资金
						BigDecimal returnprincipalMoney = dtoDebitCredit.getSurplusPrincipalMoney();
						// 一个月利息+档期利息
						BigDecimal retruninterestMoney = returninterestMoney;// 回款计划一期利息
						if ("BIAP".equals((String) dtoDebitCredit.getCreditType())) {
							retruninterestMoney = new BigDecimal(0);
						}
						// 要还的利息 // 罚息+月利息
						BigDecimal interestMoney = dtoReturnPlan.getInterestMoney().add(retruninterestMoney);

						if ("EPEI".equals(dtoReturnPlan.getType()) || "EPEIFP".equals(dtoReturnPlan.getType())) {
							MemberMember member = memberMapper.findUserByOID(dtoReturnPlan.getMemberOID());
							String isCW = "0";
							if (member != null) {
								String utype = member.getUserType();
								if (StringUtil.isNotEmpty(utype)) {
									isCW = utype;
								}
							}
							// 根据还款计划利息和投资占比计算出 出借用户应得 一个月利息+一个月的罚息1
							// （出借的出借的剩余本金 / 借款提前还的所有本金）* （一个月利息+一个月罚息）=
							// 出借人应得的利息
							repayInterestMoney = ((dtoRepayPlan.getInterestMoney().add(allInterestMoney)))
									.multiply(returnprincipalMoney)
									.divide(allPrincipalMoney, 2, BigDecimal.ROUND_HALF_EVEN);

							// ------比较回款中的罚息+月利息 和 还款中根据占比得到的罚息+月利息------
							if (interestMoney.compareTo(repayInterestMoney) > 0) {
								controlCashPool.discount(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
										interestMoney.subtract(repayInterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(repayInterestMoney),
										allFreeze);
								dtoReturnPlan.setYqfx(retruninterestMoney);
								// 所有冻结-还给每个出借用户的钱数
								allFreeze = allFreeze.subtract(returnprincipalMoney).subtract(repayInterestMoney);

							} else if (interestMoney.compareTo(repayInterestMoney) < 0) {
								controlCashPool.help(dtoRepayPlan.getOID(), dtoRepayPlan.getMemberOID(),
										interestMoney.subtract(repayInterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(interestMoney),
										allFreeze.subtract(interestMoney.subtract(repayInterestMoney)));
								dtoReturnPlan.setYqfx(retruninterestMoney);
								allFreeze = allFreeze.subtract(returnprincipalMoney).subtract(repayInterestMoney);
							} else {
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(repayInterestMoney),
										allFreeze);
								dtoReturnPlan.setYqfx(retruninterestMoney);
								allFreeze = allFreeze.subtract(returnprincipalMoney).subtract(repayInterestMoney);

								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(returnprincipalMoney)
										.add(repayInterestMoney);
							}
							if ("1".equals(isCW)) {// 出借用户为财务解冻
								controlCashPool.finishRepay(dtoReturnPlan.getOID(), // 解冻
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(interestMoney));
							}

						} else if ("CP".equals(dtoReturnPlan.getType())) {
							PayCashPool dtoCashPool = payCashPoolMapper.findByOID(dtoReturnPlan.getCashPoolOID());
							dtoCashPool.setMoney(dtoCashPool.getMoney().add(returnprincipalMoney));
							dtoCashPool.setInterest(dtoCashPool.getInterest().add(interestMoney));
							payCashPoolMapper.updatePayCashPool(dtoCashPool);
							if (returninterestMoney.compareTo(repayInterestMoney) > 0) {
								controlCashPool.discount(dtoReturnPlan.getOID(), // 利息
										dtoReturnPlan.getMemberOID(), returninterestMoney.subtract(repayInterestMoney));
								controlCashPool.discount(dtoReturnPlan.getOID(), // 罚息
										dtoReturnPlan.getMemberOID(), returninterestMoney.subtract(repayInterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(repayInterestMoney).add(repayInterestMoney), allFreeze);
								dtoReturnPlan.setYqfx(returninterestMoney);

								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);

								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);
							} else if (returninterestMoney.compareTo(repayInterestMoney) < 0) {
								controlCashPool.help(dtoRepayPlan.getOID(), // 利息
										dtoRepayPlan.getMemberOID(), repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.help(dtoRepayPlan.getOID(), // 罚息
										dtoRepayPlan.getMemberOID(), repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(returninterestMoney).add(returninterestMoney),
										allFreeze.subtract(repayInterestMoney.subtract(returninterestMoney))
												.subtract(repayInterestMoney.subtract(returninterestMoney)));

								dtoReturnPlan.setYqfx(returninterestMoney);

								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);

								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);
							} else {
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(returninterestMoney).add(returninterestMoney),
										allFreeze);
								dtoReturnPlan.setYqfx(returninterestMoney);
								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);
								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);

							}

						} else if ("EPEINEW".equals(dtoReturnPlan.getType())) {
							MemberMember member = memberMapper.findUserByOID(dtoReturnPlan.getMemberOID());
							String isCW = "0";
							if (member != null) {
								String utype = member.getUserType();
								if (StringUtil.isNotEmpty(utype)) {
									isCW = utype;
								}
							}
							if (returninterestMoney.compareTo(repayInterestMoney) > 0) {
								controlCashPool.discount(dtoReturnPlan.getOID(), // 利息
										dtoReturnPlan.getMemberOID(), returninterestMoney.subtract(repayInterestMoney));
								controlCashPool.discount(dtoReturnPlan.getOID(), // 罚息
										dtoReturnPlan.getMemberOID(), returninterestMoney.subtract(repayInterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(repayInterestMoney).add(repayInterestMoney), allFreeze);
								dtoReturnPlan.setYqfx(returninterestMoney);
								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);
								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);
							} else if (returninterestMoney.compareTo(repayInterestMoney) < 0) {
								controlCashPool.help(dtoRepayPlan.getOID(), // 利息
										dtoRepayPlan.getMemberOID(), repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.help(dtoRepayPlan.getOID(), // 罚息
										dtoRepayPlan.getMemberOID(), repayInterestMoney.subtract(returninterestMoney));
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(returninterestMoney).add(returninterestMoney),
										allFreeze.subtract(repayInterestMoney.subtract(returninterestMoney))
												.subtract(repayInterestMoney.subtract(returninterestMoney)));

								dtoReturnPlan.setYqfx(returninterestMoney);

								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);

								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);
							} else {
								controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), dtoReturnPlan.getOutMemberOID(),
										dtoReturnPlan.getMemberOID(),
										allRpPrincipalMoeny.add(returninterestMoney).add(returninterestMoney),
										allFreeze);
								dtoReturnPlan.setYqfx(returninterestMoney);
								allFreeze = allFreeze.subtract(allRpPrincipalMoeny).subtract(repayInterestMoney)
										.subtract(repayInterestMoney);
								// --------------回款加总------------
								perAllReturnPrinMoney = perAllReturnPrinMoney.add(allRpPrincipalMoeny)
										.add(returninterestMoney).add(returninterestMoney);

							}
							if ("1".equals(isCW)) {// 出借用户为财务解冻
								controlCashPool.finishRepay(dtoReturnPlan.getOID(), // 解冻
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(interestMoney));
							}

						} else if ("BIAP".equals(dtoReturnPlan.getType())) {
							MemberMember member = memberMapper.findUserByOID(dtoReturnPlan.getMemberOID());
							String isCW = "0";
							if (member != null) {
								String utype = member.getUserType();
								if (StringUtil.isNotEmpty(utype)) {
									isCW = utype;
								}
							}
							repayInterestMoney = dtoReturnPlan.getInterestMoney();
							// 划拨冻结
							controlCashPool.repayNCPNew(dtoReturnPlan.getOID(), // 短信
									dtoReturnPlan.getOutMemberOID(), dtoReturnPlan.getMemberOID(),
									returnprincipalMoney.add(repayInterestMoney), allFreeze);
							allFreeze = allFreeze.subtract(returnprincipalMoney).subtract(repayInterestMoney);
							// 资金走账，完成
							controlCashPool.finishRepay(dtoReturnPlan.getOID(), dtoReturnPlan.getMemberOID(),
									returnprincipalMoney.add(repayInterestMoney));

							MessageContentBusiness business = new MessageContentBusiness();
							business.setTitle("回款");
							String content = "您收到还款金额为" + returnprincipalMoney.add(repayInterestMoney) + "元";
							business.setContent(content);
							business.setSendUser("系统");
							business.setReceiveUserOID(dtoReturnPlan.getMemberOID());
							business.setOID(StringUtil.getUUID());
							messageContentBusinessMapper.saveMessageBusiness(business);

							PayCustomer customer = payCustomerMapper.findByMemberOID(dtoReturnPlan.getMemberOID());
							MessageSmsHistory smsHistory = new MessageSmsHistory();
							smsHistory.setTitle("提现");
							String smsContent = "【砖头网】您收到还款金额为" + returnprincipalMoney.add(repayInterestMoney) + "元";
							smsHistory.setContent(smsContent);
							smsHistory.setSendUser("系统");
							smsHistory.setReceiveUser(customer.getName());
							Map<String, String> result = sendMessageService.sendMessage(customer.getName(), content);
							String state = result.get("Description");
							smsHistory.setState(state);
							messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);

							perAllReturnPrinMoney = perAllReturnPrinMoney.add(returnprincipalMoney)
									.add(repayInterestMoney);

							if ("1".equals(isCW)) {// 出借用户为财务解冻
								controlCashPool.finishRepay(dtoReturnPlan.getOID(), // 解冻
										dtoReturnPlan.getMemberOID(), returnprincipalMoney.add(interestMoney));
							}

							String tqOID = dtoDebitCredit.getPrivilegeOID();
							if (StringUtil.isNotEmpty(tqOID)) {
								// 获取计息时间和当前时间进行差值计算，按天计算加息利息
								String startTime = DateUtils.formartDate(dtoDebitCredit.getStartDateTime(),
										"yyyy-MM-dd");
								String now = DateUtils.getDateFormatter();
								int days = DateUtils.daysBetween(startTime, now);
								// 获取加息券利率，加息天数，以及出借本金相关参数，计算利率
								PayPrivilege tq = payPrivilegeMapper.findByOID(tqOID);
								BigDecimal pMoney = dtoDebitCredit.getMoney();
								int jxDay = tq.getPrivilegeTerm();
								BigDecimal tqInterest = new BigDecimal(0);
								if (jxDay <= days) {// 如果加息券的加息天数比差值小，按加息天数计算
									tqInterest = pMoney.multiply(tq.getPrivilegeRange()).multiply(new BigDecimal(jxDay))
											.divide(new BigDecimal(36500), 2, BigDecimal.ROUND_HALF_EVEN);
								} else {// 如果加息券的加息天数比差值大，按当前相差天数计算
									tqInterest = pMoney.multiply(tq.getPrivilegeRange()).multiply(new BigDecimal(days))
											.divide(new BigDecimal(36500), 2, BigDecimal.ROUND_HALF_EVEN);
								}

								if (tqInterest != null && tqInterest.compareTo(new BigDecimal(0)) > 0) {
									controlCashPool.discount(dtoReturnPlan.getBusinessOID(),
											dtoReturnPlan.getMemberOID(), tqInterest);
									controlPay.unFreeze(dtoReturnPlan.getMemberOID(), tqInterest);

									PayCustomer cust = payCustomerMapper.findByMemberOID(dtoReturnPlan.getMemberOID());

									MessageContentBusiness busi = new MessageContentBusiness();
									business.setTitle("回款");
									String cont = "回款的加息劵收益为" + tqInterest + "元";
									business.setContent(cont);
									business.setSendUser("系统");
									business.setReceiveUserOID(dtoReturnPlan.getMemberOID());
									business.setOID(StringUtil.getUUID());
									messageContentBusinessMapper.saveMessageBusiness(busi);

									controlCustomerBusiness.saveCustomerBusiness("回款", tqInterest, "回款的特权收益为",
											dtoReturnPlan.getMemberOID());

									MessageSmsHistory history = new MessageSmsHistory();
									history.setTitle("回款");
									String smcontent = "【砖头网】您回款的加息劵收益为" + tqInterest + "元";
									history.setContent(smcontent);
									history.setSendUser("系统");
									history.setReceiveUser(customer.getName());
									Map<String, String> resul = sendMessageService.sendMessage(cust.getName(), content);
									String se = resul.get("Description");
									history.setState(se);
									messageSmsHistoryMapper.saveSMSMessageHistory(history);

								}
								dtoDebitCredit.setPrivilegeInterest(tqInterest);
							}

							WebP2pNormalBiddingRuning norma = normalBiddingRuningMapper
									.findByOID(dtoReturnPlan.getBusinessOID());
							norma.setProductStatus("5");
							normalBiddingRuningMapper.updateNormalBiddingBuning(norma);

						}
						dtoDebitCredit.setReturnSurplusNumber(0);
						dtoDebitCredit.setEndDateTime(new Date());
						dtoDebitCredit.setState("earlyFinish");// 提前结清状态
						payDebitCreditMapper.updateDebitCredit(dtoDebitCredit);
					}
					if (i > 0) {
						dtoReturnPlan.setInterestMoney(new BigDecimal(0));
					}
					if (i == 0) {// 如果提前结清，则当期回款状态为finish,其余期数状态为earlyFinish
						dtoReturnPlan.setState("finish");// 提前结清状态
					} else {
						dtoReturnPlan.setState("earlyFinish");// 提前结清状态
					}

					payReturnPlanMapper.updateReturnPlan(dtoReturnPlan);
				}
				i++;
				if (allFreeze.compareTo(new BigDecimal(0)) > 0) {
					controlCashPool.help(dtoRepayPlan.getBusinessOID(), dtoRepayPlan.getMemberOID(), allFreeze);
				}
			}
			if (repayPlanTemp.size() > 0) {

				PayRepayPlan repay = repayPlanTemp.get(0);
				if (repay != null) {
					List<WebP2pBiddingExamine> exalist = biddingExamineMapper.findByMemberOID(repay.getMemberOID());
					String username = "";
					String jkbh = "";
					BigDecimal hkje = new BigDecimal(0);
					PayCustomer cus = payCustomerMapper.findByMemberOID(repay.getMemberOID());
					WebP2pBiddingExamine xamine = exalist.get(exalist.size() - 1);

					username = xamine.getRealName();
					hkje = xamine.getApplyAmount();
					jkbh = xamine.getLoanNumber();
					String nowda = DateUtils.formartDate(new Date(), "yyyy-MM-dd");
					MessageSmsHistory history = new MessageSmsHistory();
					history.setOID(StringUtil.getUUID());
					history.setTitle("还款");
					String scontent = "【砖头网】提前还款结清通知：尊敬的" + username + "先生/女士：您申请的编号为" + jkbh + "的借款，" + "金额" + hkje
							+ "元整，已于" + nowda + "完成提前还款且全部结清，请您知悉，特此告知！";
					history.setContent(scontent);
					history.setSendUser("系统");
					history.setReceiveUser(cus.getName());
					Map<String, String> resul = sendMessageService.sendMessage(cus.getName(), scontent);
					String se = resul.get("Description");
					history.setState(se);
					messageSmsHistoryMapper.saveSMSMessageHistory(history);
				}
			}
			return "1";
		} catch (Exception e) {
			logger.error("beforeRepay(String businessOID, Date repayDate)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String afterRepay(String repayOID) {
		try {
			// 实际还款日
			String realRepayDay = DateUtils.getDateFormatter();
			PayRepayPlan dtoRepayPlan = payRepayPlanMapper.queryByOID(repayOID);
			if (dtoRepayPlan != null) {
				BigDecimal allbj = new BigDecimal(0);// 剩余本金
				BigDecimal alllx = new BigDecimal(0);// 剩余利息
				List<PayRepayPlan> dtoc = payRepayPlanMapper.findByMemberOIDAndState(dtoRepayPlan.getMemberOID(),
						"plan");

				List<String> yqhk = new ArrayList<String>();

				if (dtoc.size() > 0) {
					for (PayRepayPlan repayMent : dtoc) {
						alllx = alllx.add(repayMent.getInterestMoney());
						allbj = allbj.add(repayMent.getPrincipalMoney());
						String jhhk = DateUtils.formartDate(repayMent.getRepayDate(), "yyyy-MM-dd");
						if (DateUtils.compare_date2(realRepayDay, jhhk) >= 0) {
							String palnOID = repayMent.getOID();
							yqhk.add(palnOID);
						}
					}
					// 计划还款日
					String planRepayDay = DateUtils.formatJustDate(dtoRepayPlan.getRepayDate().getTime());
					int lday = 0;// 逾期天数
					BigDecimal yqglf = new BigDecimal(0);
					BigDecimal yqwyj = new BigDecimal(500);
					BigDecimal yqfx = new BigDecimal(0);
					BigDecimal zero = new BigDecimal(0);
					// 逾期，计算逾期天数
					lday = DateUtils.daysBetween(planRepayDay, realRepayDay);
					if (lday == 0) {
						lday++;// 20170823 cannon 超过还款日的22点就算逾期
					}
					// 逾期本金+逾期利息+全部剩余本金*0.06%*逾期天数+500+（全部剩余本金+全部剩余利息）*0.1%*逾期天数+应还平台管理费+应还管理咨询费

					yqglf = allbj.multiply(new BigDecimal(0.0006)).multiply(new BigDecimal(lday)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
					yqfx = (allbj.add(alllx)).multiply(new BigDecimal(0.001)).multiply(new BigDecimal(lday)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);

					controlCashPool.freezeRepay(repayOID, dtoRepayPlan.getMemberOID(), yqglf.add(yqwyj).add(yqfx));
					// 逾期管理费
					controlCashPool.overdueManage(dtoRepayPlan.getBusinessOID(), dtoRepayPlan.getMemberOID(), yqglf);
					// 逾期违约金
					controlCashPool.overduePenalty(dtoRepayPlan.getBusinessOID(), dtoRepayPlan.getMemberOID(), yqwyj);
					for (String yqhkOID : yqhk) {
						this.normalRepay(yqhkOID, yqfx);
						yqfx = zero;
					}
				}
				return "1";
			}
		} catch (Exception e) {
			logger.error("noviceZzBiddingFinish(BiddingDTO biddto)" + e.getMessage());
		}
		return null;
	}

	private DataPackageDTO gainDebitInfo(String businessOID) {
		try {
			WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(businessOID);
			WebP2pNormalBiddingRuning normalBidding = null;// TODO 到这就空指针
															// this.normalBiddingRuningMapper.findByOID(businessOID);
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

}
