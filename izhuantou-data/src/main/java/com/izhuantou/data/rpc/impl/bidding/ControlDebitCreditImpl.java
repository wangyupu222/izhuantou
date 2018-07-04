package com.izhuantou.data.rpc.impl.bidding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.vo.bidding.DataPackageDTO;
import com.izhuantou.damain.vo.bidding.ProductInfoDTO;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferApply;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pDebtTransferApplyMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.data.rpc.api.bidding.ControlCashPool;
import com.izhuantou.data.rpc.api.bidding.ControlDebitCredit;
import com.izhuantou.data.rpc.impl.BaseServiceImpl;

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
	private WebP2pDebtTransferApplyMapper debtTransferApplyMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Override
	public PayDebitCredit addDebitCreditNew(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				DataPackageDTO dtoDebitInfo = this.gainDebitInfo(biddto.getBiddingOID());
				BigDecimal privilegeInterest = biddto.getPrivilegeInterest();
				BigDecimal creditRate = biddto.getCreditRate();
				String businessOID = biddto.getBiddingOID();
				String outMemberOID = biddto.getMemberOID();
				String creditType = biddto.getCreditType();
				BigDecimal money = biddto.getAmount();
				BigDecimal privilegePrincipal = biddto.getPrivilegePrincipal();
				String laiyuan = biddto.getLaiyuan();
				if (privilegeInterest != null) {
					creditRate = creditRate.add(privilegeInterest);
				}
				// 将信息存表
				if (privilegePrincipal != null) {
					controlCashPool.investmentNew(businessOID, outMemberOID, dtoDebitInfo.getMemberOID(),
							money.subtract(privilegePrincipal));

					controlCashPool.discount(businessOID, dtoDebitInfo.getMemberOID(), privilegePrincipal);
				} else {
					controlCashPool.investmentNew(businessOID, outMemberOID, dtoDebitInfo.getMemberOID(), money);
				}

				// DebitCredit实体用于保存
				PayDebitCredit debitCredit = new PayDebitCredit();
				String OId = StringUtil.getUUID();
				debitCredit.setOID(OId);
				debitCredit.setBusinessOID(businessOID);
				debitCredit.setOutMemberOID(outMemberOID);
				debitCredit.setInMemberOID(dtoDebitInfo.getMemberOID());
				debitCredit.setCreditType(creditType);
				debitCredit.setReturnNumber(dtoDebitInfo.getRepayNumber());
				debitCredit.setReturnCycle(dtoDebitInfo.getRepayCycle());
				debitCredit.setCreditRate(creditRate);
				debitCredit.setMoney(money);
				debitCredit.setSurplusPrincipalMoney(money);
				debitCredit.setPrincipalMoney(money);
				debitCredit.setPrivilegePrincipal(privilegePrincipal);
				debitCredit.setPrivilegeInterest(privilegeInterest);
				debitCredit.setState("investment");
				debitCredit.setReturnSurplusNumber(dtoDebitInfo.getRepayNumber());
				debitCredit.setLaiyuan(laiyuan);
				if ("month".equals(dtoDebitInfo.getRepayCycle())) {
					BigDecimal creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					debitCredit.setInterest(
							money.multiply(creditRateTemp).multiply(new BigDecimal(dtoDebitInfo.getRepayNumber()))
									.setScale(2, BigDecimal.ROUND_HALF_EVEN));
				} else if ("day".equals(dtoDebitInfo.getRepayCycle())) {
					BigDecimal creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					debitCredit.setInterest(
							money.multiply(creditRateTemp).multiply(new BigDecimal(dtoDebitInfo.getRepayNumber()))
									.setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				debitCreditMapper.saveDebitCredit(debitCredit);
				return debitCredit;
			}
			return null;
		} catch (Exception e) {
			logger.error("PayDebitCredit addDebitCreditNew(BiddingDTO biddto)" + e.getMessage());
			return null;
		}

	}

	/**
	 * 获取贷款信息
	 * 
	 * @param businessOID
	 */
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
						return this.newDatapackage(this.setConditionMap(packageBidding), businessOID);
					}

				} else {
					return this.newDatapackage(this.setConditionMap(normalBidding), businessOID);
				}

			} else {
				return this.newDatapackage(this.setConditionMap(noviceBid), businessOID);

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

	/**
	 * 将一个类加入map
	 * 
	 */
	public Map<String, Object> setConditionMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (this.getValueByFieldName(fieldName, obj) != null)
				map.put(fieldName, this.getValueByFieldName(fieldName, obj));
		}

		return map;
	}

	/**
	 * 根据属性名获取该类此属性的值
	 * 
	 * @param fieldName
	 * @param object
	 * @return
	 */
	private Object getValueByFieldName(String fieldName, Object object) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		try {
			Method method = object.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(object, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 增加债转 普通债转
	 * 
	 * @param businessOID
	 *            债转标OID
	 * @param debitCreditOID
	 *            真实借贷关系表OID
	 * @param debitCreditOID
	 *            借贷OID
	 * @param outMemberOID
	 *            出借人OID
	 * @param money
	 *            出借金额
	 * @param creditRate
	 *            标的利率（年）
	 * @param creditType
	 *            投资类型 EPEI 等本等息 EPEIFP 等本等息冻结本金
	 * @param isTZorCW
	 *            债转时是投资还是财务满标 (XS/DD/HH/ZZ/CW) ly: 数据入口 App/Web
	 */
	@SuppressWarnings("unused")
	@Override
	public PayTransferReturn addTransferReturnZZ(BiddingDTO biddto) {
		PayTransferReturn dtoTransferReturn = new PayTransferReturn();
		try {
			if (biddto != null) {
				int isCW = 0;
				PayTransferReturn dtoTF = new PayTransferReturn();
				List<PayTransferReturn> dtoTFTemp = new ArrayList<PayTransferReturn>();
				MemberMember outMember = this.userMapper.findUserByOID(biddto.getOutMemberOID());
				if (outMember != null && outMember.getUserType() != null) {
					if ("1".equals((String) outMember.getUserType())) {
						isCW = 1;
					}
				}
				PayDebitCredit debicredit = this.debitCreditMapper.queryByPKOid(biddto.getDebitCreditOID());
				if (debicredit == null) {
					PayTransferReturn trre = transferReturnMapper.getTransferReturnByOid(biddto.getDebitCreditOID());
					String debitCreditOID = trre.getDebitCreditOIDNew();
					biddto.setDebitCreditOID(debitCreditOID);
					dtoTF = transferReturnMapper
							.gainTransferReturnByDebitCreditNewOIDAndState(biddto.getDebitCreditOID(), "finish");
				} else {
					// TODO 老系统迭代取出
					List<PayTransferReturn> trtr = transferReturnMapper
							.getTransferReturnByDebitCreditOIDAndstate(biddto.getDebitCreditOID(), "finish");
					for (PayTransferReturn trans : trtr) {
						dtoTF = trans;
					}
				}

				int lDay = 0;
				if (dtoTF != null) {
					PayDebitCredit dtoDebitCredit = this.debitCreditMapper.queryByPKOid(dtoTF.getDebitCreditOIDNew());
					DataPackageDTO debitInfo = this.gainDebitInfo(biddto.getBiddingOID());
					String loanNumber = "";
					if (debitInfo != null) {
						loanNumber = debitInfo.getLoanNumber();
					}
					// 截取字符拼成债转编号
					loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
					int yhDay = 0;
					int yhqs = dtoDebitCredit.getReturnNumber() - dtoDebitCredit.getReturnSurplusNumber();
					Date minReturnDate = null;
					Date maxRepayDate = null;
					PayReturnPlan dtocReturnPlan = this.payReturnPlanMapper
							.findMINByDebitCreditOIDAndState(biddto.getDebitCreditOID(), "plan");
					// 20170902 MAC 闰月最后一天满标结算错误问题修改，加入判断，原标还款日判断

					if (dtocReturnPlan != null && StringUtils.isNotEmpty(dtocReturnPlan.getRepayOID())) {
						minReturnDate = dtocReturnPlan.getReturnDate();
						PayRepayPlan repay = this.repayPlanMapper.queryByOID(dtocReturnPlan.getRepayOID());
						List<PayRepayPlan> dcrp = repayPlanMapper.queryByBusinessOIDAndState(repay.getBusinessOID(),
								"finish");
						if (dcrp != null && dcrp.size() > 0) {
							PayRepayPlan dtocRepayPlan = this.repayPlanMapper
									.queryByBusinessOIDAndStateMAX(repay.getBusinessOID(), "finish");
							if (dtocRepayPlan != null) {
								maxRepayDate = dtocRepayPlan.getRepayDate();
							}
						} else {
							maxRepayDate = null;
						}
					}
					// 20170515 MAC 计算持有垫付利息 ：开始日期是借贷关系开始日期，如果有还款，则从最后一次已还日期开始计算
					// ，到债转日的天数
					Date bDate = dtoDebitCredit.getStartDateTime();
					if (null != maxRepayDate) {
						if (null != minReturnDate) {// 不是最后一期
							String min = ToolsDatas
									.gainPlusAndReduceDay(DateUtils.formatJustDate(minReturnDate.getTime()), 1, 1);
							if (ToolsDatas.compare_date2(DateUtils.formatJustDate(maxRepayDate.getTime()),
									min.toString()) > 0) {
								bDate = maxRepayDate;
							} else {
								String bdate = ToolsDatas
										.gainPlusAndReduceDay(DateUtils.formatJustDate(minReturnDate.getTime()), 1, 1);
								bDate = DateUtils.getDate(bdate);
							}
						}
					}
					Date returnDate = biddto.getTransferReturnDate();
					lDay = ToolsDatas.getDateSpace(DateUtils.formatDate(bDate.getTime()),
							DateUtils.formatDate(returnDate.getTime()));
					if (lDay < 0) {
						lDay = 0;
					}
					BigDecimal creditRateDay = biddto.getCreditRate().divide(new BigDecimal("365"), 8,
							BigDecimal.ROUND_HALF_EVEN);
					// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
					// 当期应垫付利息
					// 投资本金=投资总额*转让本金/转让总额
					BigDecimal tzbj = biddto.getAmount().multiply(dtoDebitCredit.getSurplusPrincipalMoney())
							.divide(dtoTF.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
					// 垫付利息=投资总额-tzbj
					BigDecimal dflx = new BigDecimal(0);
					if ("XS".equals(biddto.getIsTZorCW())) {
						dflx = biddto.getAmount().subtract(tzbj);
					} else {
						if (lDay != 0) {
							dflx = tzbj.multiply(creditRateDay).multiply(new BigDecimal(lDay));
						} else {
							dflx = biddto.getAmount().subtract(tzbj);
						}
					}
					// 上期应垫付利息
					if (isCW > 0) {// 债转、结算财务
						if (biddto.getPrivilegePrincipal() != null) {
							this.controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									dtoDebitCredit.getOutMemberOID(),
									biddto.getAmount().add(dflx).subtract(biddto.getPrivilegePrincipal()));
							this.controlCashPool.discount(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									biddto.getPrivilegePrincipal());
						} else {
							this.controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									dtoDebitCredit.getOutMemberOID(), biddto.getAmount().add(dflx));
						}

					} else {
						if (biddto.getPrivilegePrincipal() != null) {
							this.controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									dtoDebitCredit.getOutMemberOID(),
									biddto.getAmount().subtract(biddto.getPrivilegePrincipal()));
							this.controlCashPool.discount(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									biddto.getPrivilegePrincipal());
						} else {
							this.controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									dtoDebitCredit.getOutMemberOID(), biddto.getAmount());
						}
					}
					dtoTransferReturn.setBusinessOID(biddto.getBiddingOID());
					dtoTransferReturn.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
					dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
					dtoTransferReturn.setOutMemberOID(biddto.getOutMemberOID());
					dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
					dtoTransferReturn.setCreditType(biddto.getCreditType());
					dtoTransferReturn.setCreditRate(biddto.getCreditRate());
					dtoTransferReturn.setMoney(tzbj.add(dflx));
					dtoTransferReturn.setPrincipalMoney(tzbj);
					dtoTransferReturn.setAdvanceInterest(dflx);
					dtoTransferReturn.setPrivilegePrincipal(null);
					dtoTransferReturn.setPrivilegeInterest(null);
					dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
					dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
					dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
					dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
					dtoTransferReturn.setLaiyuan(biddto.getLaiyuan());
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
					// 生成主键
					dtoTransferReturn.setOID(StringUtil.getUUID());
					this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);
				} else {
					// 第一次
					PayDebitCredit dtoDebitCredit = this.debitCreditMapper.queryByPKOid(biddto.getDebitCreditOID());
					String inOId = dtoDebitCredit.getInMemberOID();
					String buisOid = dtoDebitCredit.getBusinessOID();
					PayDebitCredit dtoColl = this.debitCreditMapper.findByDebitCreditOID(buisOid);
					String loanNumber = "";
					// 截取字符拼成债转编号
					loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
					Date returnDate = null;
					Date minReturnDate = null;
					Date maxRepayDate = null;
					PayReturnPlan dtocReturnPlan = payReturnPlanMapper
							.findMINByDebitCreditOIDAndState(biddto.getDebitCreditOID(), "plan");
					// 20170902 MAC 闰月最后一天满标结算错误问题修改，加入判断，原标还款日判断
					if (dtocReturnPlan != null && StringUtils.isNotEmpty(dtocReturnPlan.getRepayOID())) {
						minReturnDate = dtocReturnPlan.getReturnDate();
						PayRepayPlan repay = this.repayPlanMapper.queryByOID(dtocReturnPlan.getRepayOID());
						List<PayRepayPlan> dcrp = this.repayPlanMapper
								.queryByBusinessOIDAndState(repay.getBusinessOID(), "finish");
						if (dcrp != null && dcrp.size() > 0) {
							PayRepayPlan dtocRepayPlan = this.repayPlanMapper
									.queryByBusinessOIDAndStateMAX(repay.getBusinessOID(), "finish");
							if (dtocRepayPlan != null) {
								maxRepayDate = dtocRepayPlan.getRepayDate();
							}
						} else {
							maxRepayDate = null;
						}
					}
					// 20170515 MAC 计算持有垫付利息 ：开始日期是借贷关系开始日期，如果有还款，则从最后一次已还日期开始计算
					// ，到债转日的天数
					Date bDate = dtoDebitCredit.getStartDateTime();
					if (null != maxRepayDate) {
						if (null != minReturnDate) {// 不是最后一期
							String min = ToolsDatas
									.gainPlusAndReduceDay(DateUtils.formatJustDate(minReturnDate.getTime()), 1, 1);
							if (ToolsDatas.compare_date2(DateUtils.formatJustDate(maxRepayDate.getTime()), min) > 0) {
								bDate = maxRepayDate;
							} else {
								String bdate = ToolsDatas
										.gainPlusAndReduceDay(DateUtils.formatJustDate(minReturnDate.getTime()), 1, 1);
								bDate = DateUtils.getDate(bdate);
							}
						}
					}
					lDay = DateUtils.daysBetween(bDate, biddto.getTransferReturnDate());
					if (lDay < 0) {
						lDay = 0;
					}
					BigDecimal creditRateDay = biddto.getCreditRate().divide(new BigDecimal("365"), 8,
							BigDecimal.ROUND_HALF_EVEN);
					// 区分不同产品，使用不同本金或剩余本金*************************************************************************************
					// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
					BigDecimal advanceInterest = dtoDebitCredit.getSurplusPrincipalMoney().multiply(creditRateDay)
							.multiply(new BigDecimal(lDay)).multiply(dtoDebitCredit.getPrincipalMoney())
							.divide(dtoDebitCredit.getPrincipalMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
					// *************************************************************************************
					if (biddto.getPrivilegePrincipal() != null) {
						controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
								dtoDebitCredit.getOutMemberOID(), dtoDebitCredit.getSurplusPrincipalMoney()
										.add(advanceInterest).subtract(biddto.getPrivilegePrincipal()));
						this.controlCashPool.discount(biddto.getBiddingOID(), biddto.getOutMemberOID(),
								biddto.getPrivilegePrincipal());
					} else {
						controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
								dtoDebitCredit.getOutMemberOID(),
								dtoDebitCredit.getSurplusPrincipalMoney().add(advanceInterest));
						if ("ZZ".equals(biddto.getIsTZorCW())) {
							controlCashPool.transferReturn(biddto.getBiddingOID(), biddto.getOutMemberOID(),
									dtoDebitCredit.getOutMemberOID(), biddto.getAmount());
						}
					}
					dtoTransferReturn.setBusinessOID(biddto.getBiddingOID());
					dtoTransferReturn.setDebitCreditOID(biddto.getDebitCreditOID());
					dtoTransferReturn.setOutMemberOID(biddto.getOutMemberOID());
					dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
					dtoTransferReturn.setCreditType(biddto.getCreditType());
					dtoTransferReturn.setCreditRate(biddto.getCreditRate());
					if ("ZZ".equals(biddto.getIsTZorCW())) {
						dtoTransferReturn.setMoney(biddto.getAmount());
						dtoTransferReturn.setPrincipalMoney(dtoDebitCredit.getMoney());
						dtoTransferReturn.setAdvanceInterest(biddto.getAmount().subtract(dtoDebitCredit.getMoney()));
					} else {
						dtoTransferReturn.setMoney((dtoDebitCredit.getSurplusPrincipalMoney()).add(advanceInterest));
						dtoTransferReturn.setPrincipalMoney((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney());
						dtoTransferReturn.setAdvanceInterest(advanceInterest);
					}
					dtoTransferReturn.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
					dtoTransferReturn.setPrivilegePrincipal(null);
					dtoTransferReturn.setPrivilegeInterest(null);
					dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
					dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
					dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
					dtoTransferReturn.setEndDateTime(dtoDebitCredit.getEndDateTime());
					dtoTransferReturn.setState("investment");
					dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
					dtoTransferReturn.setLaiyuan(biddto.getLaiyuan());
					// 生成主键
					dtoTransferReturn.setOID(StringUtil.getUUID());
					int rows = this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);
				}
			}
		} catch (Exception e) {
			logger.error("PayTransferReturn addTransferReturnZZ(BiddingDTO biddto)"+e.getMessage());
		}
		return dtoTransferReturn;

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
	public PayCashPool investment(BiddingDTO biddto) {
		PayCashPool dtoCashPool = null;
		try {
			if (biddto != null) {
				// 获取产品信息
				ProductInfoDTO dtoProductInfo = this.gainProductInfo(biddto.getBiddingOID());
				// 返还期数
				Integer returnNumber = dtoProductInfo.getReturnNumber();
				// 结束时间
				Date endDate = null;
				// 利率
				BigDecimal creditRate = dtoProductInfo.getCreditRate();

				BigDecimal creditRateTemp = null;
				if ("month".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber);
				} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addDay(biddto.getBeginDate(), returnNumber);
				}
				// 类型按月返息，到期返本
				String creditType = dtoProductInfo.getDebitType();
				if ("BIAP".equals(creditType)) {
					BiddingDTO bidto = new BiddingDTO();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(
							biddto.getAmount().multiply(creditRateTemp).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(returnNumber);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());
					dtoCashPool = this.controlCashPool.investment(bidto);
					// 统计
					BigDecimal totalInterest = new BigDecimal("0");
					for (int i = 0; i < returnNumber; i++) {
						PayReturnPlan dtoReturnPlan = new PayReturnPlan();
						dtoReturnPlan.setBusinessOID(bidto.getBiddingOID());
						dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
						dtoReturnPlan.setMemberOID(bidto.getMemberOID());
						if ("month".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						}
						BigDecimal interest = bidto.getAmount().multiply(creditRateTemp).setScale(2,
								BigDecimal.ROUND_HALF_EVEN);
						dtoReturnPlan.setInterestMoney(interest);
						if (returnNumber == i + 1) {
							dtoReturnPlan
									.setMoney(bidto.getAmount().add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(bidto.getAmount());
							dtoReturnPlan.setTotalPrincipalMoney(bidto.getAmount());
						} else {
							dtoReturnPlan.setMoney(interest);
							dtoReturnPlan.setPrincipalMoney(new BigDecimal("0"));
							dtoReturnPlan.setTotalPrincipalMoney(new BigDecimal("0"));
						}
						totalInterest = totalInterest.add(interest);
						dtoReturnPlan.setTotalInterestMoney(totalInterest);
						dtoReturnPlan.setRate(creditRateTemp);
						dtoReturnPlan.setNum(i + 1);
						dtoReturnPlan.setSum(returnNumber);
						dtoReturnPlan.setState("agentPlan");
						dtoReturnPlan.setType(creditType);
						dtoReturnPlan.setYqfx(new BigDecimal("0"));
						dtoReturnPlan.setOID(StringUtil.getUUID());
						payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
					}

				} else if ("OPI".equals(creditType)) {

					BiddingDTO bidto = new BiddingDTO();
					BigDecimal money = biddto.getAmount();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(money.multiply(creditRateTemp).multiply(new BigDecimal(returnNumber)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(1);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());

					dtoCashPool = this.controlCashPool.investment(bidto);
					// 全部利息
					BigDecimal interestCount = money.multiply(creditRateTemp).multiply(new BigDecimal(returnNumber))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					PayReturnPlan dtoReturnPlan = new PayReturnPlan();
					dtoReturnPlan.setBusinessOID(biddto.getBiddingOID());
					dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
					dtoReturnPlan.setMemberOID(biddto.getMemberOID());
					if ("month".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber));
					} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addDay(biddto.getBeginDate(), returnNumber));
					}
					dtoReturnPlan.setInterestMoney(interestCount);
					dtoReturnPlan.setMoney(money.add(interestCount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					dtoReturnPlan.setPrincipalMoney(money);
					dtoReturnPlan.setTotalPrincipalMoney(money);
					dtoReturnPlan.setTotalInterestMoney(interestCount);
					dtoReturnPlan.setRate(creditRate);
					dtoReturnPlan.setNum(1);
					dtoReturnPlan.setSum(1);
					dtoReturnPlan.setState("agentPlan");
					dtoReturnPlan.setType(creditType);
					dtoReturnPlan.setYqfx(new BigDecimal("0"));
					dtoReturnPlan.setOID(StringUtil.getUUID());
					payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
				}
			}
		} catch (Exception e) {
			logger.error("investment(BiddingDTO biddto)"+e.getMessage());
		}
		return dtoCashPool;
	}
	/**
	 * 增加债转 普通债转
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public PayTransferReturn addTransferReturn(BiddingDTO biddto) {
		try {
			if (biddto != null) {

				BigDecimal privilegeInterest = biddto.getPrivilegeInterest();
				BigDecimal creditRate = biddto.getCreditRate();
				String businessOID = biddto.getBiddingOID();
				String outMemberOID = biddto.getOutMemberOID();
				BigDecimal money = biddto.getAmount();
				String debitCreditOID = biddto.getDebitCreditOID();
				String creditType = biddto.getCreditType();
				String laiyuan = biddto.getLaiyuan();
				if (privilegeInterest != null) {
					creditRate = creditRate.add(privilegeInterest);
				}
				WebP2pDebtTransferApply applyRecordes = debtTransferApplyMapper
						.findByBusinessOIDMoneyCreditRate(debitCreditOID, money, creditRate);

				int lDay = applyRecordes.getSyqs();
				BigDecimal months = new BigDecimal(12);
				BigDecimal zqs = new BigDecimal(applyRecordes.getZqs());
				// 对应的债转本金
				BigDecimal baseMoney = applyRecordes.getBdjg().subtract(applyRecordes.getYslx());
				BigDecimal yslx = (creditRate.subtract(months).multiply(zqs)).multiply(baseMoney);

				// 垫付利息
				BigDecimal advanceInterest = applyRecordes.getYslx();
				BigDecimal privilegePrincipal = null;
				if (privilegeInterest != null) {
					controlCashPool.transferReturn(businessOID, outMemberOID, applyRecordes.getMemberOID(),
							money.add(advanceInterest).subtract(privilegeInterest));
					controlCashPool.discount(businessOID, outMemberOID, privilegeInterest);
				} else {
					controlCashPool.transferReturn(businessOID, outMemberOID, applyRecordes.getMemberOID(),
							money.subtract(advanceInterest));
				}
				PayTransferReturn transferReturn = new PayTransferReturn();
				transferReturn.setBusinessOID(businessOID);
				transferReturn.setDebitCreditOID(debitCreditOID);
				transferReturn.setOutMemberOID(outMemberOID);
				transferReturn.setInMemberOID(applyRecordes.getMemberOID());
				transferReturn.setCreditType(creditType);
				transferReturn.setCreditRate(creditRate);
				transferReturn.setMoney(money);
				transferReturn.setPrincipalMoney(baseMoney);
				transferReturn.setAdvanceInterest(advanceInterest);
				transferReturn.setPrivilegePrincipal(privilegePrincipal);
				transferReturn.setPrivilegeInterest(privilegeInterest);
				transferReturn.setReturnSurplusNumber(applyRecordes.getSyqs());
				transferReturn.setInterest(applyRecordes.getYslx());
				transferReturn.setState("investment");
				transferReturn.setLaiyuan(laiyuan);
				transferReturn.setStartDateTime(DateUtils.getJustDate(applyRecordes.getCjsj()));
				String endDateTime = DateUtils.gainPlusAndReduceDay(applyRecordes.getCjsj(), applyRecordes.getZqs(), 0);

				transferReturn.setEndDateTime(DateUtils.getJustDate(endDateTime));
				transferReturn.setOID(StringUtil.getUUID());
				transferReturnMapper.saveTransferReturn(transferReturn);
				return transferReturn;
			}
			return null;
		} catch (Exception e) {
			logger.error("PayTransferReturn addTransferReturn(BiddingDTO biddto)"+e.getMessage());
			return null;
		}
	}

	@Override
	public PayCashPool investmentRed(BiddingDTO biddto) {
		PayCashPool dtoCashPool = null;
		try {
			if (biddto != null){
				// 获取产品信息
				ProductInfoDTO dtoProductInfo = this.gainProductInfo(biddto.getBiddingOID());
				// 返还期数
				Integer returnNumber = dtoProductInfo.getReturnNumber();
				// 结束时间
				Date endDate = null;
				// 利率
				BigDecimal creditRate = dtoProductInfo.getCreditRate();

				BigDecimal creditRateTemp = null;
				if ("month".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber);
				} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addDay(biddto.getBeginDate(), returnNumber);
				}
				// 类型按月返息，到期返本
				BigDecimal allRedAmount = biddto.getAllRedAmount();
				String creditType = dtoProductInfo.getDebitType();
				if ("BIAP".equals(creditType)) {
					BiddingDTO bidto = new BiddingDTO();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(
							biddto.getAmount().add(allRedAmount).multiply(creditRateTemp).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(returnNumber);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());
					bidto.setAllRedAmount(allRedAmount);
					dtoCashPool = this.controlCashPool.investmentRed(bidto);
					// 统计
					BigDecimal totalInterest = new BigDecimal("0");
					for (int i = 0; i < returnNumber; i++) {
						PayReturnPlan dtoReturnPlan = new PayReturnPlan();
						dtoReturnPlan.setBusinessOID(bidto.getBiddingOID());
						dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
						dtoReturnPlan.setMemberOID(bidto.getMemberOID());
						if ("month".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						}
						// 利息
						BigDecimal interest = bidto.getAmount().add(allRedAmount).multiply(creditRateTemp).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						dtoReturnPlan.setInterestMoney(interest);
						if (returnNumber == i + 1) {
							dtoReturnPlan.setMoney(bidto.getAmount().add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(bidto.getAmount().add(allRedAmount));
							dtoReturnPlan.setTotalPrincipalMoney(bidto.getAmount().add(allRedAmount));
						} else {
							dtoReturnPlan.setMoney(interest);
							dtoReturnPlan.setPrincipalMoney(new BigDecimal("0"));
							dtoReturnPlan.setTotalPrincipalMoney(new BigDecimal("0"));
						}
						totalInterest = totalInterest.add(interest);
						dtoReturnPlan.setTotalInterestMoney(totalInterest);
						dtoReturnPlan.setRate(creditRateTemp);
						dtoReturnPlan.setNum(i + 1);
						dtoReturnPlan.setSum(returnNumber);
						dtoReturnPlan.setState("agentPlan");
						dtoReturnPlan.setType(creditType);
						dtoReturnPlan.setYqfx(new BigDecimal("0"));
						dtoReturnPlan.setOID(StringUtil.getUUID());
						payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
					}
				} else if ("OPI".equals(creditType)){
					BiddingDTO bidto = new BiddingDTO();
					BigDecimal money = biddto.getAmount();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(money.add(allRedAmount).multiply(creditRateTemp).multiply(new BigDecimal(returnNumber)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(1);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());
					bidto.setAllRedAmount(allRedAmount);
					
					dtoCashPool = this.controlCashPool.investmentRed(bidto);
					// 全部利息
					BigDecimal interestCount = money.add(allRedAmount).multiply(creditRateTemp).multiply(new BigDecimal(returnNumber))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					
					PayReturnPlan dtoReturnPlan = new PayReturnPlan();
					dtoReturnPlan.setBusinessOID(biddto.getBiddingOID());
					dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
					dtoReturnPlan.setMemberOID(biddto.getMemberOID());
					if ("month".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber));
					} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addDay(biddto.getBeginDate(), returnNumber));
					}
					dtoReturnPlan.setInterestMoney(interestCount);
					dtoReturnPlan.setMoney(money.add(allRedAmount).add(interestCount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					dtoReturnPlan.setPrincipalMoney(money.add(allRedAmount));
					dtoReturnPlan.setTotalPrincipalMoney(money.add(allRedAmount));
					dtoReturnPlan.setTotalInterestMoney(interestCount);
					dtoReturnPlan.setRate(creditRate);
					dtoReturnPlan.setNum(1);
					dtoReturnPlan.setSum(1);
					dtoReturnPlan.setState("agentPlan");
					dtoReturnPlan.setType(creditType);
					dtoReturnPlan.setYqfx(new BigDecimal("0"));
					dtoReturnPlan.setOID(StringUtil.getUUID());
					payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
				}
			}
		} catch (Exception e) {
			logger.error("investmentRed(BiddingDTO biddto, BigDecimal allRedAmount)"+e.getMessage());
		}
		return dtoCashPool;
	}

	@Override
	public PayCashPool investmentRedAndPrivilege(BiddingDTO biddto) {
		PayCashPool dtoCashPool = null;
		try {
			if (biddto != null){
				// 获取产品信息
				ProductInfoDTO dtoProductInfo = this.gainProductInfo(biddto.getBiddingOID());
				// 返还期数
				Integer returnNumber = dtoProductInfo.getReturnNumber();
				// 结束时间
				Date endDate = null;
				// 利率
				BigDecimal creditRate = dtoProductInfo.getCreditRate();
				
				BigDecimal creditRateTemp = null;
				if ("month".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber);
				} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
					creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
					endDate = ToolDateTime.addDay(biddto.getBeginDate(), returnNumber);
				}
				// 类型
				BigDecimal allRedAmount = biddto.getAllRedAmount();
				String creditType = dtoProductInfo.getDebitType();
				if ("BIAP".equals(creditType)) {
					BiddingDTO bidto = new BiddingDTO();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(
							biddto.getAmount().add(allRedAmount).multiply(creditRateTemp).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(returnNumber);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());
					bidto.setAllRedAmount(allRedAmount);
					dtoCashPool = this.controlCashPool.investmentRedAndPrivilege(bidto);
					// 统计
					BigDecimal totalInterest = new BigDecimal("0");
					for (int i = 0; i < returnNumber; i++) {
						PayReturnPlan dtoReturnPlan = new PayReturnPlan();
						dtoReturnPlan.setBusinessOID(bidto.getBiddingOID());
						dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
						dtoReturnPlan.setMemberOID(bidto.getMemberOID());
						if ("month".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
							dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(bidto.getBeginDate(), i + 1));
						}
						// 利息
						BigDecimal interest = bidto.getAmount().add(allRedAmount).multiply(creditRateTemp).setScale(2, BigDecimal.ROUND_HALF_EVEN);
						dtoReturnPlan.setInterestMoney(interest);
						if (returnNumber == i + 1) {
							dtoReturnPlan
									.setMoney(bidto.getAmount().add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							dtoReturnPlan.setPrincipalMoney(bidto.getAmount().add(allRedAmount));
							dtoReturnPlan.setTotalPrincipalMoney(bidto.getAmount().add(allRedAmount));
						} else {
							dtoReturnPlan.setMoney(interest);
							dtoReturnPlan.setPrincipalMoney(new BigDecimal("0"));
							dtoReturnPlan.setTotalPrincipalMoney(new BigDecimal("0"));
						}
						totalInterest = totalInterest.add(interest);
						dtoReturnPlan.setTotalInterestMoney(totalInterest);
						dtoReturnPlan.setRate(creditRateTemp);
						dtoReturnPlan.setNum(i + 1);
						dtoReturnPlan.setSum(returnNumber);
						dtoReturnPlan.setState("agentPlan");
						dtoReturnPlan.setType(creditType);
						dtoReturnPlan.setYqfx(new BigDecimal("0"));
						dtoReturnPlan.setOID(StringUtil.getUUID());
						payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
					}
				} else if ("OPI".equals(creditType)){
					BiddingDTO bidto = new BiddingDTO();
					BigDecimal money = biddto.getAmount();
					bidto.setBiddingOID(biddto.getBiddingOID());
					bidto.setOutMemberOID(biddto.getMemberOID());
					bidto.setAmount(biddto.getAmount());
					bidto.setInterest(money.add(allRedAmount).multiply(creditRateTemp).multiply(new BigDecimal(returnNumber)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN));
					bidto.setCreditRate(creditRate);
					bidto.setReturnCycle(dtoProductInfo.getReturnCycle());
					bidto.setReturnNumber(1);
					bidto.setBeginDate(biddto.getBeginDate());
					bidto.setEndDate(endDate);
					bidto.setPrivilegePrincipal(biddto.getPrivilegePrincipal());
					bidto.setPrivilegeInterest(biddto.getPrivilegeInterest());
					bidto.setTqOID(biddto.getTqOID());
					bidto.setLaiyuan(biddto.getLaiyuan());
					bidto.setAllRedAmount(allRedAmount);

					dtoCashPool = this.controlCashPool.investmentRedAndPrivilege(bidto);
					// 全部利息
					BigDecimal interestCount = money.add(allRedAmount).multiply(creditRateTemp).multiply(new BigDecimal(returnNumber))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					
					PayReturnPlan dtoReturnPlan = new PayReturnPlan();
					dtoReturnPlan.setBusinessOID(biddto.getBiddingOID());
					dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
					dtoReturnPlan.setMemberOID(biddto.getMemberOID());
					if ("month".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(biddto.getBeginDate(), returnNumber));
					} else if ("day".equals(dtoProductInfo.getReturnCycle())) {
						dtoReturnPlan.setReturnDate(ToolDateTime.addDay(biddto.getBeginDate(), returnNumber));
					}
					dtoReturnPlan.setInterestMoney(interestCount);
					dtoReturnPlan.setMoney(money.add(allRedAmount).add(interestCount).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					dtoReturnPlan.setPrincipalMoney(money.add(allRedAmount));
					dtoReturnPlan.setTotalPrincipalMoney(money.add(allRedAmount));
					dtoReturnPlan.setTotalInterestMoney(interestCount);
					dtoReturnPlan.setRate(creditRate);
					dtoReturnPlan.setNum(1);
					dtoReturnPlan.setSum(1);
					dtoReturnPlan.setState("agentPlan");
					dtoReturnPlan.setType(creditType);
					dtoReturnPlan.setYqfx(new BigDecimal("0"));
					dtoReturnPlan.setOID(StringUtil.getUUID());
					payReturnPlanMapper.saveReturnPlan(dtoReturnPlan);
				}
			}
		} catch (Exception e) {
			logger.error("investmentRed(BiddingDTO biddto, BigDecimal allRedAmount)"+e.getMessage());
		}
		return dtoCashPool;
	}
}
