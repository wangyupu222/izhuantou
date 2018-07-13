package com.izhuantou.fund.rpc.impl.bidding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
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
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlCashPool;
import com.izhuantou.fund.rpc.api.bidding.BiddingCashFreeze;
import com.izhuantou.fund.rpc.impl.BaseServiceImpl;
import com.izhuantou.third.rpc.api.ControlPayService;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("biddingCashFreeze")
public class BiddingCashFreezeImpl extends BaseServiceImpl<PayDebitCredit> implements BiddingCashFreeze {

	private static final Logger logger = LoggerFactory.getLogger(BiddingCashFreezeImpl.class);

	@Autowired
	private ControlPayService controlPay;
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
	@Autowired
	private ControlCashPool controlCashPool;
	@Autowired
	private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

	/**
	 * 更改完的新手
	 */
	@Override
	public String addDebitCreditNew(BiddingDTO biddto) {
		try {
			String result="";
			if (biddto != null) {
				DataPackageDTO dtoDebitInfo = this.gainDebitInfo(biddto.getBiddingOID());
				BigDecimal privilegeInterest = biddto.getPrivilegeInterest();
				BigDecimal creditRate = biddto.getCreditRate();
				String outMemberOID = biddto.getMemberOID();
				BigDecimal money = biddto.getAmount();
				BigDecimal privilegePrincipal = biddto.getPrivilegePrincipal();
				if (privilegeInterest != null) {
					creditRate = creditRate.add(privilegeInterest);
				}
				// 将信息存表
				if (privilegePrincipal != null) {
					result=controlPay.transferFreeze(outMemberOID, dtoDebitInfo.getMemberOID(), money.subtract(privilegePrincipal));
					if(StringUtil.isNotEmpty(result)&&"1".equals(result)){
						result=controlPay.transferFreeze("discountAccount", dtoDebitInfo.getMemberOID(), privilegePrincipal);
					}
				} else {
					result=controlPay.transferFreeze(outMemberOID, dtoDebitInfo.getMemberOID(), money);
				}
			}
			return result;
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
			WebP2pNormalBiddingRuning normalBidding =this.normalBiddingRuningMapper.findByOID(businessOID);
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
					WebP2pNormalBiddingRuning normalBidding=normalBiddingRuningMapper.findByOID(strBusinessOID);
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
	public String investment(BiddingDTO biddto) {
		String result="";
		try {
			if (biddto.getPrivilegePrincipal() == null) {
				result=controlPay.freeze(biddto.getMemberOID(),biddto.getAmount());
			} else {
				result=controlPay.freeze(biddto.getMemberOID(),
						biddto.getAmount().subtract(biddto.getPrivilegePrincipal()));
			}
		} catch (Exception e) {
			logger.error("investment(BiddingDTO biddto)"+e.getMessage());
		}
		return result;
	}
	@SuppressWarnings("unused")
	@Override
	public String addTransferReturnZZ(BiddingDTO biddto) {
		String result="";
		try {
			if (biddto != null) {
				int isCW = 0;
				PayTransferReturn dtoTF = new PayTransferReturn();
				MemberMember outMember = this.userMapper.findUserByOID(biddto.getOutMemberOID());
				if (outMember != null &&StringUtil.isNotEmpty(outMember.getUserType())) {
					if ("1".equals(outMember.getUserType())) {
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
					// TODO 取最后一个按时间排序
					List<PayTransferReturn> trRe = transferReturnMapper
							.getTransferReturnByDebitCreditOIDAndstate(biddto.getDebitCreditOID(), "finish");
					dtoTF=trRe.get(trRe.size()-1);
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
				
					if (dtocReturnPlan != null && StringUtils.isNotEmpty(dtocReturnPlan.getRepayOID())) {
						minReturnDate = dtocReturnPlan.getReturnDate();
						PayRepayPlan repay = this.repayPlanMapper.queryByOID(dtocReturnPlan.getRepayOID());
						List<PayRepayPlan> dcrp = 
								this.repayPlanMapper.queryByBusinessOIDAndState(repay.getBusinessOID(),"finish");
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
							BigDecimal money=biddto.getAmount().add(dflx).subtract(biddto.getPrivilegePrincipal());
							result=controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(), money);
							if(StringUtil.isNotEmpty(result)&&"1".equals(result)){
								result=controlPay.transferFreeze("discountAccount", 
										biddto.getOutMemberOID(), biddto.getPrivilegePrincipal());
							}
						} else {
							result=controlPay.transferFreeze(biddto.getOutMemberOID(), 
									dtoDebitCredit.getOutMemberOID(), biddto.getAmount().add(dflx));
						}

					} else {
						if (biddto.getPrivilegePrincipal() != null) {
							result=controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(),
										biddto.getAmount().subtract(biddto.getPrivilegePrincipal()));
							if(StringUtil.isNotEmpty(result)){
								result=	controlPay.transferFreeze("discountAccount", 
										biddto.getOutMemberOID(), biddto.getPrivilegePrincipal());
							}
						} else {
							result=controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(), biddto.getAmount());
						}
					}
				} else {
					// 第一次
					PayDebitCredit dtoDebitCredit = this.debitCreditMapper.queryByPKOid(biddto.getDebitCreditOID());
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
				
					if (biddto.getPrivilegePrincipal() != null) {
						result=controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(), dtoDebitCredit.getSurplusPrincipalMoney()
								.add(advanceInterest).subtract(biddto.getPrivilegePrincipal()));
						if(StringUtil.isNotEmpty(result)&&"1".equals(result)){
							result=controlPay.transferFreeze("discountAccount", biddto.getOutMemberOID(), biddto.getPrivilegePrincipal());
						}
						
					} else {
						result=controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(), 
								dtoDebitCredit.getSurplusPrincipalMoney().add(advanceInterest));
						if ("ZZ".equals(biddto.getIsTZorCW())) {
							result=	controlPay.transferFreeze(biddto.getOutMemberOID(), dtoDebitCredit.getOutMemberOID(), biddto.getAmount());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("PayTransferReturn addTransferReturnZZ(BiddingDTO biddto)"+e.getMessage());
		}
		return result;

	}
	/**
	 * 增加债转 普通债转
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public String addTransferReturn(BiddingDTO biddto) {
		String result="";
		try {
			if (biddto != null) {
				BigDecimal privilegeInterest = biddto.getPrivilegeInterest();
				BigDecimal creditRate = biddto.getCreditRate();
				String outMemberOID = biddto.getOutMemberOID();
				BigDecimal money = biddto.getAmount();
				String debitCreditOID = biddto.getDebitCreditOID();
				if (privilegeInterest != null) {
					creditRate = creditRate.add(privilegeInterest);
				}
				WebP2pDebtTransferApply applyRecordes = debtTransferApplyMapper
						.findByBusinessOIDMoneyCreditRate(debitCreditOID, money, creditRate);
				// 垫付利息
				BigDecimal advanceInterest = applyRecordes.getYslx();
				if (privilegeInterest != null) {
					result=controlPay.transferFreeze(outMemberOID, applyRecordes.getMemberOID(),
									money.add(advanceInterest).subtract(privilegeInterest));
					if(StringUtil.isNotEmpty(result)){
						result=	controlPay.transferFreeze("discountAccount", outMemberOID, privilegeInterest);
					}
				} else {
					result=controlPay.transferFreeze(outMemberOID, applyRecordes.getMemberOID(), money.subtract(advanceInterest));
				}
			}
		} catch (Exception e) {
			logger.error("PayTransferReturn addTransferReturn(BiddingDTO biddto)"+e.getMessage());
			return null;
		}
		return result;
	}


	@Override
	public String investmentRed(BiddingDTO biddto) {
		String result = "";
		try {
			if (biddto.getAllRedAmount() == null){
				result=controlPay.freeze(biddto.getMemberOID(),biddto.getAmount());
			}else{
				result=controlPay.freeze(biddto.getMemberOID(),biddto.getAmount());
				if ("1".equals(result)){
				// 红包贴息
				controlCashPool.discountRed(biddto.getBiddingOID(), biddto.getMemberOID(), biddto.getAllRedAmount());
				}
			}
		} catch (Exception e) {
			logger.error("investmentRed(BiddingDTO biddto, BigDecimal allRedAmount)"+e.getMessage());
		}
		return result;
	}


	@Override
	public String investmentRedAndPrivilege(BiddingDTO biddto) {
		String result = "";
		try {
			if (biddto.getAllRedAmount() == null){
				result=controlPay.freeze(biddto.getMemberOID(),biddto.getAmount());
			}else{
				result=controlPay.freeze(biddto.getMemberOID(),biddto.getAmount());
				if ("1".equals(result)){
				// 红包贴息
				controlCashPool.discountRed(biddto.getBiddingOID(), biddto.getMemberOID(), biddto.getAllRedAmount());
				}
			}
		} catch (Exception e) {
			logger.error("investmentRed(BiddingDTO biddto, BigDecimal allRedAmount)"+e.getMessage());
		}
		return result;
	}
	
}
