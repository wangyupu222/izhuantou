package com.izhuantou.fund.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferApply;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
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
import com.izhuantou.fund.api.ControlCashPool;
import com.izhuantou.fund.api.ControlCustomerBusiness;
import com.izhuantou.fund.api.ControlDebitCredit;
import com.izhuantou.fund.api.ControlLoanProductRateInfo;
import com.izhuantou.fund.api.ControlMember;
import com.izhuantou.fund.api.ControlNormalBidding;
import com.izhuantou.fund.api.ControlNoviceBidding;
import com.izhuantou.fund.api.ControlPackageBiddingMainContent;
import com.izhuantou.fund.api.ControlPackageBiddingMainRuning;
import com.izhuantou.fund.api.ControlProductRateInfo;
import com.izhuantou.fund.api.ControlRepayPlan;
import com.izhuantou.fund.api.ControlReturnPlan;
import com.izhuantou.fund.api.ControlTransferReturn;
import com.izhuantou.third.api.ControlPayService;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("controlDebitCredit")
public class ControlDebitCreditImpl extends BaseServiceImpl<PayDebitCredit> implements ControlDebitCredit {

    @Autowired
    private ControlNoviceBidding controlNoviceBidding;
    @Autowired
    private ControlNormalBidding controlNormalBidding;
    @Autowired
    private ControlPackageBiddingMainContent controlPackageBidding;
    @Autowired
    private ControlLoanProductRateInfo controlLoanProductRateInfo;
    @Autowired
    private ControlRepayPlan controlRepayPlan;
    @Autowired
    private ControlCashPool controlCashPool;
    @Autowired
    private ControlProductRateInfo controlProductRateInfo;
    @Autowired
    private ControlPackageBiddingMainRuning controlPackageBiddingMainRuning;
    @Autowired
    private ControlReturnPlan controlReturnPlan;
    @Autowired
    private ControlTransferReturn controlTransferReturn;
    @Autowired
    private ControlMember controlMember;
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
    private WebP2pDebtTransferApplyMapper debtTransferApplyMapper;
    @Autowired
    private PayCashPoolMapper payCashPoolMapper;

    @Autowired
    private PayCustomerBusinessMapper customerBusinessMapper;

    @Autowired
    private PayCustomerMapper customerMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Override
    public Map<String, Object> addDebitCreditNew(Map<String, Object> map) {
	try {
	    // 原有系统中 privilegePrincipal = null,privilegeInterest = null
	    Map<String, Object> dtoDebitInfo = this.gainDebitInfo((String) map.get("biddingOID"));

	    BigDecimal privilegeInterest = new BigDecimal(0);
	    BigDecimal creditRate = new BigDecimal((String) map.get("creditRate"));
	    String businessOID = (String) map.get("biddingOID");
	    String outMemberOID = (String) map.get("memberOID");
	    String creditType = (String) map.get("creditType");
	    BigDecimal money = (BigDecimal) map.get("amount");
	    BigDecimal privilegePrincipal = (BigDecimal) map.get("privilegePrincipal");
	    String laiyuan = (String) map.get("laiyuan");
	    if (map.get("privilegeInterest") != null) {
		privilegeInterest = new BigDecimal((String) map.get("privilegeInterest"));
		creditRate = creditRate.add(privilegeInterest);
	    }
	    // 将信息存表
	    if (privilegePrincipal != null) {

		controlCashPool.investmentNew(businessOID, outMemberOID, (String) dtoDebitInfo.get("memberOID"),
			money.subtract(privilegePrincipal));
		controlCashPool.discount(businessOID, (String) dtoDebitInfo.get("memberOID"), privilegePrincipal);
	    } else {
		controlCashPool.investmentNew(businessOID, outMemberOID, (String) dtoDebitInfo.get("memberOID"), money);
	    }

	    // DebitCredit实体用于保存
	    PayDebitCredit debitCredit = new PayDebitCredit();
	    // debitCredit的主键借贷关系
	    String OId = StringUtil.getUUID();
	    debitCredit.setOID(OId);
	    debitCredit.setBusinessOID(businessOID);
	    debitCredit.setOutMemberOID(outMemberOID);
	    debitCredit.setInMemberOID((String) dtoDebitInfo.get("memberOID"));
	    debitCredit.setCreditType(creditType);
	    debitCredit.setReturnNumber((Integer) dtoDebitInfo.get("repayNumber"));
	    debitCredit.setReturnCycle((String) dtoDebitInfo.get("repayCycle"));
	    debitCredit.setCreditRate(creditRate);
	    debitCredit.setMoney(money);
	    debitCredit.setSurplusPrincipalMoney(money);
	    debitCredit.setPrincipalMoney(money);
	    debitCredit.setPrivilegePrincipal(privilegePrincipal);
	    debitCredit.setPrivilegeInterest(privilegeInterest);
	    debitCredit.setState("investment");
	    debitCredit.setReturnSurplusNumber((Integer) dtoDebitInfo.get("repayNumber"));
	    debitCredit.setLaiyuan(laiyuan);
	    if ("month".equals(dtoDebitInfo.get("repayCycle"))) {
		BigDecimal creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		debitCredit.setInterest(money.multiply(creditRateTemp)
			.multiply(new BigDecimal((Integer) dtoDebitInfo.get("repayNumber")))
			.setScale(2, BigDecimal.ROUND_HALF_EVEN));
	    } else if ("day".equals(dtoDebitInfo.get("repayCycle"))) {
		BigDecimal creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		debitCredit.setInterest(money.multiply(creditRateTemp)
			.multiply(new BigDecimal((Integer) dtoDebitInfo.get("repayNumber")))
			.setScale(2, BigDecimal.ROUND_HALF_EVEN));
	    }
	    int rows = debitCreditMapper.saveDebitCredit(debitCredit);
	    Map<String, Object> resultMap = this.setConditionMap(debitCredit);
	    return resultMap;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}

    }

    /**
     * 获取贷款信息
     * 
     * @param businessOID
     */
    private Map<String, Object> gainDebitInfo(String businessOID) {
	// 原代码位置 cn.com.hoonsoft.pay.ControlDebitCredit.gainDebitInfo(String)
	try {
	    WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(businessOID);
	    WebP2pNormalBiddingRuning normalBidding = this.normalBiddingRuningMapper.findByOID(businessOID);
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
	    System.out.println(e.getMessage());
	    return null;
	}

    }

    /**
     * 封装返回参数
     */
    private Map<String, Object> newDatapackage(Map<String, Object> map, String businessOID) {
	try {
	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    WebP2pLoanProductRateInfo loanProductRateInfo = loanProductRateInfoMapper
		    .findByOID((String) map.get("loanProductRateInfoID"));

	    returnMap.put("businessOID", businessOID);
	    returnMap.put("memberOID", (String) map.get("memberOID"));
	    returnMap.put("name", (String) map.get("biddingName"));
	    returnMap.put("debitType", loanProductRateInfo.getRepaymentType());
	    returnMap.put("loanNumber", (String) map.get("loanNumber"));
	    Integer rn = (Integer) loanProductRateInfo.getTerm();
	    returnMap.put("repayNumber", rn);
	    returnMap.put("repayCycle", "month");
	    // 年利率
	    returnMap.put("debitRate", (BigDecimal) loanProductRateInfo.getYearRate());
	    returnMap.put("money", (BigDecimal) map.get("biddingAmount"));
	    returnMap.put("holdmoney", (BigDecimal) map.get("holdingAmount"));
	    // 一次性手续费
	    returnMap.put("onceProceduresRate", (BigDecimal) loanProductRateInfo.getDisposableRate());
	    // 提前还款违约金
	    returnMap.put("earlyRepay", (BigDecimal) loanProductRateInfo.getBeforeRate());
	    // 逾期违约金（天）
	    returnMap.put("overdueRate", (BigDecimal) loanProductRateInfo.getOverdueRate());
	    // 逾期管理费（天）
	    returnMap.put("overdueExpenseRate", (BigDecimal) loanProductRateInfo.getOverdueExpenseRate());
	    // 年平台管理费率
	    returnMap.put("manageRate", (BigDecimal) loanProductRateInfo.getYearPlatformRate());
	    // 年管理咨询费率
	    returnMap.put("serviceRate", (BigDecimal) loanProductRateInfo.getYearAdviceRate());
	    return returnMap;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
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
    public PayTransferReturn addTransferReturnZZ(Map<String, Object> map) {
	PayTransferReturn dtoTransferReturn = new PayTransferReturn();
	try {
	    int isCW = 0;
	    PayTransferReturn dtoTF = new PayTransferReturn();
	    List<PayTransferReturn> dtoTFTemp = new ArrayList<PayTransferReturn>();
	    MemberMember outMember = this.userMapper.findUserByOID((String) map.get("outMemberOID"));
	    if (outMember != null && outMember.getUserType() != null) {
		if ("1".equals((String) outMember.getUserType())) {
		    isCW = 1;
		}
	    }
	    PayDebitCredit debicredit = this.debitCreditMapper.queryByPKOid((String) map.get("debitCreditOID"));
	    if (debicredit == null) {
		map.put("debitCreditOID", this.transferReturnMapper
			.getTransferReturnByOid((String) map.get("debitCreditOID")).getDebitCreditOIDNew());
		dtoTF = this.transferReturnMapper
			.gainTransferReturnByDebitCreditNewOIDAndState((String) map.get("debitCreditOID"), "finish");
	    }
	    dtoTF = this.transferReturnMapper
		    .getTransferReturnByDebitCreditOIDAndstate((String) map.get("debitCreditOID"), "finish").get(0);
	    int lDay = 0;
	    if (dtoTF != null) {
		PayDebitCredit dtoDebitCredit = this.debitCreditMapper.queryByPKOid(dtoTF.getDebitCreditOIDNew());
		Map<String, Object> debitInfo = this.gainDebitInfo((String) map.get("businessOID"));
		String loanNumber = "";
		// terry 获取标的类型
		if (debitInfo != null) {
		    loanNumber = debitInfo.get("loanNumber").toString();
		}
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		int yhDay = 0;
		int yhqs = dtoDebitCredit.getReturnNumber() - dtoDebitCredit.getReturnSurplusNumber();
		Date minReturnDate = null;
		Date maxRepayDate = null;
		PayReturnPlan dtocReturnPlan = this.payReturnPlanMapper
			.findMINByDebitCreditOIDAndState((String) map.get("debitCreditOID"), "plan");
		// 20170902 MAC 闰月最后一天满标结算错误问题修改，加入判断，原标还款日判断
		if (dtocReturnPlan != null && dtocReturnPlan.getRepayOID() != null) {
		    PayRepayPlan repay = this.repayPlanMapper.queryByOID(dtocReturnPlan.getRepayOID());
		    List<PayRepayPlan> dcrp = this.repayPlanMapper.queryByBusinessOIDAndState(repay.getBusinessOID(),
			    "finish");
		    if (dcrp != null && dcrp.size() > 0) {
			List<PayRepayPlan> dtocRepayPlan = this.repayPlanMapper
				.queryByBusinessOIDAndStateMAX((String) map.get("businessOID"), "finish");
			if (dtocRepayPlan != null) {
			    for (PayRepayPlan dto : dtocRepayPlan) {
				maxRepayDate = dto.getRepayDate();
				break;
			    }
			}
		    } else {
			maxRepayDate = null;
		    }
		}

		// 20170515 MAC 计算持有垫付利息 ：开始日期是借贷关系开始日期，如果有还款，则从最后一次已还日期开始计算
		// ，到债转日的天数
		String bDate = DateUtils.formatDate(dtoDebitCredit.getStartDateTime().getTime());
		if (null != maxRepayDate) {

		    if (null != minReturnDate) {// 不是最后一期
			String min = ToolsDatas.gainPlusAndReduceDay(minReturnDate.toString(), 1, 1);
			if (ToolsDatas.compare_date2(maxRepayDate.toString(), min.toString()) > 0) {
			    bDate = maxRepayDate.toString();
			} else {
			    bDate = ToolsDatas.gainPlusAndReduceDay(minReturnDate.toString(), 1, 1);
			}
		    }
		}
		Date returnDate = (Date) map.get("transferReturnDate");
		lDay = ToolsDatas.getDateSpace(bDate, DateUtils.formatDate(returnDate.getTime()));
		if (lDay < 0) {
		    lDay = 0;
		}

		BigDecimal creditRateDay = (new BigDecimal((String) map.get("creditRate")))
			.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);

		// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
		// 当期应垫付利息
		// 投资本金=投资总额*转让本金/转让总额
		BigDecimal tzbj = ((BigDecimal) map.get("amount")).multiply(dtoDebitCredit.getSurplusPrincipalMoney())
			.divide(dtoTF.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
		// 垫付利息=投资总额-tzbj

		BigDecimal dflx = new BigDecimal(0);
		if ("XS".equals((String) map.get("isTZorCW"))) {
		    dflx = ((BigDecimal) map.get("amount")).subtract(tzbj);
		} else {
		    if (lDay != 0) {
			dflx = tzbj.multiply(creditRateDay).multiply(new BigDecimal(lDay));
		    } else {
			dflx = ((BigDecimal) map.get("amount")).subtract(tzbj);
		    }
		}
		// 上期应垫付利息
		if (isCW > 0) {// 债转、结算财务
		    if (map.get("privilegePrincipal") != null) {
			this.controlCashPool.transferReturn((String) map.get("businessOID"),
				(String) map.get("outMemberOID"), dtoDebitCredit.getOutMemberOID(),
				((BigDecimal) map.get("amount")).add(dflx)
					.subtract((BigDecimal) map.get("privilegePrincipal")));
			this.controlCashPool.discount((String) map.get("businessOID"), (String) map.get("outMemberOID"),
				(BigDecimal) map.get("privilegePrincipal"));
		    } else {
			this.controlCashPool.transferReturn((String) map.get("businessOID"),
				(String) map.get("outMemberOID"), dtoDebitCredit.getOutMemberOID(),
				((BigDecimal) map.get("amount")).add(dflx));
		    }

		} else {
		    if (map.get("privilegePrincipal") != null) {
			this.controlCashPool.transferReturn((String) map.get("businessOID"),
				(String) map.get("outMemberOID"), dtoDebitCredit.getOutMemberOID(),
				((BigDecimal) map.get("amount")).subtract((BigDecimal) map.get("privilegePrincipal")));
			this.controlCashPool.discount((String) map.get("businessOID"), (String) map.get("outMemberOID"),
				(BigDecimal) map.get("privilegePrincipal"));
		    } else {
			this.controlCashPool.transferReturn((String) map.get("businessOID"),
				(String) map.get("outMemberOID"), dtoDebitCredit.getOutMemberOID(),
				((BigDecimal) map.get("amount")));

		    }
		}

		dtoTransferReturn.setBusinessOID((String) map.get("businessOID"));
		dtoTransferReturn.setCashPoolOID(dtoDebitCredit.getCashPoolOID());
		dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
		dtoTransferReturn.setOutMemberOID((String) map.get("outMemberOID"));
		dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
		dtoTransferReturn.setCreditType((String) map.get("creditType"));
		dtoTransferReturn.setCreditRate(new BigDecimal((String) map.get("creditRate")));
		dtoTransferReturn.setMoney(tzbj.add(dflx));
		dtoTransferReturn.setPrincipalMoney(tzbj);
		dtoTransferReturn.setAdvanceInterest(dflx);
		dtoTransferReturn.setPrivilegePrincipal(null);
		dtoTransferReturn.setPrivilegeInterest(null);
		dtoTransferReturn.setReturnSurplusNumber(dtoDebitCredit.getReturnSurplusNumber());
		dtoTransferReturn.setInterest(dtoDebitCredit.getInterest());
		dtoTransferReturn.setStartDateTime(dtoDebitCredit.getStartDateTime());
		dtoTransferReturn.setDebtLoanNumber("Z-" + loanNumber);
		dtoTransferReturn.setLaiyuan((String) map.get("laiyuan"));
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
		int rows = this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);
		// dtoTransferReturn.put("lDay", lDay);
	    } else {
		// 第一次
		PayDebitCredit dtoDebitCredit = this.debitCreditMapper.queryByPKOid((String) map.get("debitCreditOID"));
		String inOId = (String) dtoDebitCredit.getInMemberOID();
		String buisOid = (String) dtoDebitCredit.getBusinessOID();
		PayDebitCredit dtoColl = this.debitCreditMapper.findByDebitCreditOID(buisOid);
		String loanNumber = "";
		//
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		Date returnDate = null;
		Date minReturnDate = null;
		Date maxRepayDate = null;
		PayReturnPlan dtocReturnPlan = this.payReturnPlanMapper
			.findMINByDebitCreditOIDAndState((String) map.get("debitCreditOID"), "plan");
		String repayOID = "";
		// 20170902 MAC 闰月最后一天满标结算错误问题修改，加入判断，原标还款日判断
		if (dtocReturnPlan != null && StringUtils.isNotEmpty(dtocReturnPlan.getRepayOID())) {
		    PayRepayPlan repay = this.repayPlanMapper.queryByOID(dtocReturnPlan.getRepayOID());
		    List<PayRepayPlan> dcrp = this.repayPlanMapper.queryByBusinessOIDAndState(repay.getBusinessOID(),
			    "finish");
		    if (dcrp != null && dcrp.size() > 0) {
			List<PayRepayPlan> dtocRepayPlan = this.repayPlanMapper
				.queryByBusinessOIDAndStateMAX(repay.getBusinessOID(), "finish");
			if (dtocRepayPlan != null && dtocRepayPlan.size() > 0) {
			    for (PayRepayPlan dto : dtocRepayPlan) {
				maxRepayDate = (Date) dto.getRepayDate();
				break;
			    }
			}
		    } else {
			maxRepayDate = null;
		    }

		}

		// 20170515 MAC 计算持有垫付利息 ：开始日期是借贷关系开始日期，如果有还款，则从最后一次已还日期开始计算
		// ，到债转日的天数
		String bDate = dtoDebitCredit.getStartDateTime().toString();
		if (null != maxRepayDate) {

		    if (null != minReturnDate) {// 不是最后一期
			String min = ToolsDatas.gainPlusAndReduceDay(minReturnDate.toString(), 1, 1);
			if (ToolsDatas.compare_date2(maxRepayDate.toString(), min.toString()) > 0) {
			    bDate = maxRepayDate.toString();
			} else {
			    bDate = ToolsDatas.gainPlusAndReduceDay(minReturnDate.toString(), 1, 1);
			}
		    }
		}
		lDay = ToolsDatas.getDateSpace(bDate, map.get("transferReturnDate").toString());
		if (lDay < 0) {
		    lDay = 0;
		}

		BigDecimal creditRateDay = ((BigDecimal) (map.get("creditRate"))).divide(new BigDecimal("365"), 8,
			BigDecimal.ROUND_HALF_EVEN);
		// 区分不同产品，使用不同本金或剩余本金*************************************************************************************
		// 垫付利息 本金*天数*天利息*（投资额/未收到金额）
		BigDecimal advanceInterest = ((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
			.multiply(creditRateDay).multiply(new BigDecimal(lDay))
			.multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
			.divide((BigDecimal) dtoDebitCredit.getPrincipalMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
		// *************************************************************************************
		if (map.get("privilegePrincipal") != null) {
		    controlCashPool.transferReturn((String) map.get("businessOID"), (String) map.get("outMemberOID"),
			    dtoDebitCredit.getOutMemberOID(), (dtoDebitCredit.getSurplusPrincipalMoney())
				    .add(advanceInterest).subtract((BigDecimal) map.get("privilegePrincipal")));
		    this.controlCashPool.discount((String) map.get("businessOID"), (String) map.get("outMemberOID"),
			    (BigDecimal) map.get("privilegePrincipal"));
		} else {
		    controlCashPool.transferReturn((String) map.get("businessOID"), (String) map.get("outMemberOID"),
			    dtoDebitCredit.getOutMemberOID(),
			    (dtoDebitCredit.getSurplusPrincipalMoney()).add(advanceInterest));

		    if ("ZZ".equals(map.get("isTZorCW"))) {
			controlCashPool.transferReturn((String) map.get("businessOID"),
				(String) map.get("outMemberOID"), dtoDebitCredit.getOutMemberOID(),
				(BigDecimal) map.get("amount"));
		    }
		}

		dtoTransferReturn.setBusinessOID((String) map.get("businessOID"));
		dtoTransferReturn.setDebitCreditOID((String) map.get("debitCreditOID"));
		dtoTransferReturn.setOutMemberOID((String) map.get("outMemberOID"));
		dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
		dtoTransferReturn.setCreditType((String) map.get("creditType"));
		dtoTransferReturn.setCreditRate((BigDecimal) map.get("creditRate"));
		if ("ZZ".equals(map.get("isTZorCW"))) {
		    dtoTransferReturn.setMoney((BigDecimal) map.get("amount"));
		} else {
		    dtoTransferReturn.setMoney((dtoDebitCredit.getSurplusPrincipalMoney()).add(advanceInterest));
		}
		if ("ZZ".equals(map.get("isTZorCW"))) {
		    dtoTransferReturn.setPrincipalMoney((BigDecimal) dtoDebitCredit.getMoney());
		} else {
		    dtoTransferReturn.setPrincipalMoney((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney());
		}
		if ("ZZ".equals(map.get("isTZorCW"))) {
		    dtoTransferReturn.setAdvanceInterest(
			    ((BigDecimal) map.get("amount")).subtract((BigDecimal) dtoDebitCredit.getMoney()));
		} else {
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
		dtoTransferReturn.setLaiyuan((String) map.get("laiyuan"));
		// 生成主键
		dtoTransferReturn.setOID(StringUtil.getUUID());
		int rows = this.transferReturnMapper.saveTransferReturn(dtoTransferReturn);
		// dtoTransferReturn.put("lDay", lDay);
	    }

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return dtoTransferReturn;

    }

    @Override
    public void finishDebitCreditNEW(String strBusinessOID, Date beginDate) {
	// 借贷信息
	Map<String, Object> dtoDebitInfo = this.gainDebitInfo(strBusinessOID);
	List<String> lRepayOID = new ArrayList<>();
	// 还款部分
	if (dtoDebitInfo != null && "EPEI".equals(dtoDebitInfo.get("debitType"))) {
	    Integer repayNumber = (Integer) dtoDebitInfo.get("repayNumber");
	    String repayCycle = (String) dtoDebitInfo.get("repayCycle");
	    BigDecimal debitRate = (BigDecimal) dtoDebitInfo.get("debitRate");
	    if ("month".equals(repayCycle)) {
		debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
	    } else if ("day".equals(repayCycle)) {
		debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
	    }
	    // 全部本金
	    BigDecimal money = (BigDecimal) dtoDebitInfo.get("money");
	    // 统计
	    BigDecimal totalPrincipal = new BigDecimal("0");
	    @SuppressWarnings("unused")
	    BigDecimal surplusPrincipal = new BigDecimal("0");
	    BigDecimal totalInterest = new BigDecimal("0");
	    for (int i = 0; i < repayNumber; i++) {
		PayRepayPlan payRepayPlan = new PayRepayPlan();
		payRepayPlan.setBusinessOID(strBusinessOID);
		payRepayPlan.setMemberOID((String) dtoDebitInfo.get("memberOID"));
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
		    manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate")).divide(new BigDecimal("12"), 8,
			    BigDecimal.ROUND_HALF_EVEN);
		} else if ("day".equals(repayCycle)) {
		    manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate")).divide(new BigDecimal("365"),
			    8, BigDecimal.ROUND_HALF_EVEN);
		}
		// 服务费
		BigDecimal service = null;
		if ("month".equals(repayCycle)) {
		    service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate")).divide(new BigDecimal("12"),
			    8, BigDecimal.ROUND_HALF_EVEN);
		} else if ("day".equals(repayCycle)) {
		    service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate")).divide(new BigDecimal("365"),
			    8, BigDecimal.ROUND_HALF_EVEN);
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
		// controlRepayPlan.save(payRepayPlan);

		// TODO 主键问题
		lRepayOID.add(payRepayPlan.getBusinessOID()); // 临时数据 应该为主键OID
	    }
	    BigDecimal onceProceduresMoney = money.multiply((BigDecimal) dtoDebitInfo.get("onceProceduresRate"))
		    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
	    controlCashPool.onceProceduresMoney(strBusinessOID, (String) dtoDebitInfo.get("memberOID"),
		    onceProceduresMoney);
	    controlCashPool.finishInvestment(strBusinessOID, (String) dtoDebitInfo.get("memberOID"),
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
		Map<String, Object> thisdtoDebitInfo = this.gainDebitInfo((String) dtoDebitCredit.getBusinessOID());
		if ("OPI".equals(dtoDebitCredit.getCreditType())) {
		    Map<String, Object> dtoProductInfo = this.gainProductInfo((String) dtoDebitCredit.getBusinessOID());
		    if (dtoProductInfo != null) {
			// 非环环资金池投资
			// 返还期数
			Integer returnNumber = 1;
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) dtoProductInfo.get("returnCycle");
			// 利率
			BigDecimal creditRate = (BigDecimal) dtoDebitCredit.getCreditRate();
			BigDecimal creditRatePro = (BigDecimal) dtoProductInfo.get("creditRate");
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
			    endDate = (Date) dtoReturnPlan.getReturnDate();
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
			Integer returnNumber = (Integer) thisdtoDebitInfo.get("repayNumber");
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) thisdtoDebitInfo.get("repayCycle");
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
				dtoReturnPlan.setRepayOID((String) lRepayOID.get(i));
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
			Integer returnNumber = (Integer) thisdtoDebitInfo.get("repayNumber");
			// 结束时间
			String returnCycle = (String) thisdtoDebitInfo.get("repayCycle");
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
				dtoReturnPlan.setRepayOID((String) lRepayOID.get(i));
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
			    dtoReturnPlan.setRepayOID((String) lRepayOID.get(0));
			    dtoReturnPlan.setDebitCreditOID(dtoDebitCredit.getOID());
			    dtoReturnPlan.setMemberOID(dtoDebitCredit.getOutMemberOID());
			    dtoReturnPlan.setOutMemberOID(dtoDebitCredit.getInMemberOID());
			    dtoReturnPlan.setReturnDate(ToolDateTime.addMonth(beginDate, returnNumber));
			    endDate = (Date) dtoReturnPlan.getReturnDate();
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
		dtoDebitCredit.setStartDateTime(ToolDateTime.gainDateTime(beginDate));
		dtoDebitCredit.setEndDateTime(ToolDateTime.gainDateTime(endDate));
		dtoDebitCredit.setSurplusPrincipalMoney((BigDecimal) dtoDebitCredit.getPrincipalMoney());

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
    @SuppressWarnings("null")
    public Map<String, Object> gainProductInfo(String strBusinessOID) {
	try {
	    if (StringUtil.isEmpty(strBusinessOID)) {
		return null;
	    }
	    Map<String, Object> dto = new HashMap<String, Object>();
	    WebP2pNoviceBiddingRuning noviceBidding = this.noviceBiddingRuningMapper.findByOID(strBusinessOID);
	    WebP2pNormalBiddingRuning normalBidding = null;
	    WebP2pPackageBiddingMainRuning packageBidding = null;
	    WebP2pProductRateInfo productRateInfo = null;
	    if (noviceBidding == null) {
		normalBidding = normalBiddingRuningMapper.findByOID(strBusinessOID);
		if (normalBidding != null) {
		    dto.put("name", (String) normalBidding.getBiddingName());
		    dto.put("memberOID", normalBidding.getMemberOID());
		    productRateInfo = this.productRateInfoMapper.findByOID(packageBidding.getProductRateInfoID());
		}
	    } else {
		dto.put("name", noviceBidding.getBiddingName());
		dto.put("memberOID", noviceBidding.getMemberOID());
		productRateInfo = this.productRateInfoMapper.findByOID(packageBidding.getProductRateInfoID());
	    }
	    if (packageBidding != null) {

		productRateInfo = this.productRateInfoMapper.findByOID(packageBidding.getProductRateInfoID());
		dto.put("name", packageBidding.getPackageName());
		dto.put("memberOID", "");
		productRateInfo = this.productRateInfoMapper.findByOID(packageBidding.getProductRateInfoID());
		dto.put("name", packageBidding.getPackageName());
		dto.put("memberOID", null);

	    }
	    dto.put("businessOID", strBusinessOID);
	    dto.put("debitType", productRateInfo.getRepaymentType());// 等本等息"EPEI",按月返"BIAP",一次性"OPI"repaymentType
	    double productTerm = productRateInfo.getProductTerm();
	    Integer rn = new Integer(0);

	    if (productTerm > 0.9) {
		rn = Integer.parseInt(new java.text.DecimalFormat("0").format(productTerm));
		dto.put("returnCycle", "month");
	    } else {
		rn = 15;
		dto.put("returnCycle", "day");
	    }
	    dto.put("returnNumber", rn);

	    dto.put("creditRate", productRateInfo.getYearRate());

	    // 环环投提前赎回手续费（线下）
	    dto.put("hhtXXtqsh", productRateInfo.getHhtXXtqsh());
	    // 环环投提前赎回手续费（线上）
	    dto.put("hhtXStqsh", productRateInfo.getHhtXStqsh());
	    // 点点投债权转让手续费
	    dto.put("ddtZQZR", productRateInfo.getDdtZQZR());
	    return dto;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}

    }

    @Override
    public void finishTransferReturnNEW(Map<String, Object> typeMap) {
	try {
	    String cashPoolOID = "";
	    PayCashPool dtocashPool = null;
	    String debitCreditOID = null;
	    List<String> dcrtOID = new ArrayList<>();
	    List<PayTransferReturn> dtocTransferReturn = transferReturnMapper
		    .findByBusinessOIDAndState((String) typeMap.get("businessOID"), "investment");

	    int isCW = 0;
	    BigDecimal ystz = new BigDecimal(0);
	    for (PayTransferReturn dtoTransferReturn : dtocTransferReturn) {
		ystz = (BigDecimal) dtoTransferReturn.getMoney();
		cashPoolOID = (String) dtoTransferReturn.getCashPoolOID();
		debitCreditOID = (String) dtoTransferReturn.getDebitCreditOID();
		// 受让人是否是财务账号
		String outMemberOID = (String) dtoTransferReturn.getOutMemberOID();
		MemberMember outMember = userMapper.findUserByOID(outMemberOID);
		if (outMember.getUserType() != null) {
		    if ("1".equals((String) outMember.getUserType())) {
			isCW = 1;
		    }
		}
		if (StringUtil.isNotEmpty(cashPoolOID)) {
		    // 债转前资金池
		    dtocashPool = payCashPoolMapper.findByOID(cashPoolOID);
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
		List<java.util.Date> lReturnDate = new ArrayList<>();
		List<String> lReturnState = new ArrayList<>();
		BigDecimal oldAllMoney = new BigDecimal(0);
		String returnBusinessOID = null;

		// 生成新的借贷关系
		PayDebitCredit debitcreditNEW = new PayDebitCredit();
		debitcreditNEW.setOID(StringUtil.getUUID());
		debitcreditNEW.setBusinessOID((String) typeMap.get("businessOID"));
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
		debitcreditNEW.setStartDateTime(ToolDateTime.gainDateTime((Date) typeMap.get("beginDate")));

		String endDateTime = ToolsDatas.gainPlusAndReduceDay(
			DateUtils.formatDate(((Date) typeMap.get("beginDate")).getTime()),
			dtoDebitCredit.getReturnNumber(), 0);

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

		Date sqlDate = ToolDateTime.gainDate();
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
			oldAllMoney = oldAllMoney.add((BigDecimal) dtoReturnPlan.getPrincipalMoney());
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
		if (isCW < 1 && "XS".equals(typeMap.get("isTZorCW"))) {
		    Map<String, Object> dtoProductInfo = this.gainProductInfo(returnBusinessOID);
		    BigDecimal creditRatePro = null;
		    if (dtoProductInfo != null) {
			creditRatePro = (BigDecimal) dtoProductInfo.get("creditRate");
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
			dtoReturnPlanOPI.setReturnDate(ToolDateTime.addDay((Date) typeMap.get("beginDate"), 15));
			// endDate = (Date) dtoReturnPlanOPI.get("returnDate");
			// 本金
			BigDecimal principal = (BigDecimal) dtoTransferReturn.getPrincipalMoney();
			// 利息
			BigDecimal interest = ((BigDecimal) dtoTransferReturn.getMoney())
				.subtract((BigDecimal) dtoTransferReturn.getPrincipalMoney());
			// 应收利息
			BigDecimal yslx = ((BigDecimal) dtoTransferReturn.getMoney()).multiply(creditRatePro)
				.multiply(new BigDecimal(15));
			dtoReturnPlanOPI.setMoney(((BigDecimal) dtoTransferReturn.getMoney()).add(yslx).setScale(2,
				BigDecimal.ROUND_HALF_EVEN));
			dtoReturnPlanOPI.setPrincipalMoney((BigDecimal) dtoTransferReturn.getMoney());
			dtoReturnPlanOPI.setInterestMoney(yslx);
			// totalPrincipal = totalPrincipal.add(principal);
			dtoReturnPlanOPI.setTotalPrincipalMoney((BigDecimal) dtoTransferReturn.getMoney());
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
		    // dtoReturnPlan.put("returnDate", lReturnDate.get(i));
		    // 本金=投资本金/返回期数
		    BigDecimal principal = money.divide(new BigDecimal(returnNumber), 2, BigDecimal.ROUND_HALF_EVEN);
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
		    if ("CW".equals(typeMap.get("isTZorCW")) && "HH".equals(typeMap.get("tzType"))) {
			dtoReturnPlan.setType("EPEI");
		    } else {
			if ("EPEI".equals(oldType) && "HH".equals(typeMap.get("tzType"))
				&& "HH".equals(typeMap.get("isTZorCW"))) {
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
		if ("CW".equals(typeMap.get("isTZorCW")) && "XS".equals(typeMap.get("tzType"))) {

		    Map<String, Object> dtoProductInfo = this.gainProductInfo((String) typeMap.get("businessOID"));
		    // 解冻贴息

		    BigDecimal tll = ((BigDecimal) dtoProductInfo.get("creditRate")).divide(new BigDecimal("365"), 8,
			    BigDecimal.ROUND_HALF_EVEN);

		    BigDecimal proInterest = ((BigDecimal) dtoDebitCredit.getMoney()).multiply(new BigDecimal(15))
			    .multiply(tll).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		    BigDecimal ydzh = ((BigDecimal) dtoDebitCredit.getMoney()).add(proInterest);
		    // 解冻本金

		    // this.controlPay.unFreeze((String)
		    // dtoTransferReturn.getInMemberOID(), ydzh);

		    // controlCustomerBusiness
		    // 增加贴息

		    /*
		     * customerBusinessMapp Integer resultType =
		     * this.controlCustomerBusiness.saveCustomerBusiness("回款",
		     * ydzh, "回款的本金利息金额", (String)
		     * dtoTransferReturn.getInMemberOID());
		     * System.out.println("=========返回结果为:" + resultType);
		     */

		    PayCustomerBusiness bussiness = new PayCustomerBusiness();
		    bussiness.setContent("回款的本金利息金额");
		    bussiness.setMoney(ydzh);
		    bussiness.setState("已完成");
		    bussiness.setType("回款");
		    bussiness.setMemberOID((String) dtoTransferReturn.getInMemberOID());
		    PayCustomer customer = customerMapper.findByMemberOID((String) dtoTransferReturn.getInMemberOID());
		    bussiness.setCustomerOID(customer.getOID());

		    customerBusinessMapper.insertCustomerBussiness(bussiness);

		    // TODO 贴息 发送信息
		    /*
		     * DTBServiceAuth dtbServiceAuth = new DTBServiceAuth();
		     * dtbServiceAuth.addUserOID((String)
		     * dtoTransferReturn.getInMemberOID());
		     */
		    // 增加贴息
		    /*
		     * ServiceFactory.instanceMessageService(this).sendMessage(
		     * "回款", "您收到回款金额为" + ydzh + "元", "系统", dtbServiceAuth,
		     * null); DTBServiceAuth dtbServiceAuth1 = new
		     * DTBServiceAuth(); controlCustomer = (ControlCustomer)
		     * this.gainInstance(controlCustomer,
		     * ControlCustomer.class);
		     * dtbServiceAuth1.addUserOID((String) controlCustomer
		     * .gainCustomerByMemberOID((String)
		     * dtoTransferReturn.get("inMemberOID")).get("name"));
		     */
		    // 增加贴息
		    /*
		     * ServiceFactory.instanceMessageService(this).
		     * sendSMSMessage("回款", "【砖头网】您收到回款金额为" + ydzh + "元", null,
		     * "系统", dtbServiceAuth1, null);
		     */

		}
		// 特权利润扣除
		dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

		// 暂无使用
		// dtocReturnPlan
		// =controlReturnPlan.gainReturnPlanByDebitCreditOIDAndState(debitCreditOID,
		// "finish");

		// 2017.10.25开发站岗资金结算修改此处，所以债转完成时都不解冻
		controlCashPool.finishTransferReturnUnFreeze((String) dtoDebitCredit.getOID(),
			(String) dtoDebitCredit.getOutMemberOID(), transferReturnMoney, null);
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
	    System.out.println(e.getMessage());
	}
    }

    /**
     * 通过OID判断是新增还是修改
     * 
     * @param debitcreditNEW
     * @return
     */
    private PayDebitCredit insertDebitcreditNEW(PayDebitCredit debitcreditNEW) {
	try {
	    PayDebitCredit objEntity = null;
	    // 判断是否新增还是修改,通过OID
	    String strOID = debitcreditNEW.getOID();
	    if (StringUtils.isBlank(strOID)) // 表示新增
	    {
		String typeOID = ToolString.gainUUID(this);
		debitcreditNEW.setOID(typeOID);
		// version
		// refresh
		// debitcreditNEW.setUpdated(t);
		if (debitcreditNEW.getAddDateTimeStr() == null) {
		    debitcreditNEW.setAddDateTimeStr(DateUtils.getDateFormatter());

		}
		// valid
		// 保存实体
		this.save(debitcreditNEW);
		objEntity = debitcreditNEW;
		return objEntity;

	    } else {
		objEntity = this.queryByid(strOID);
		if (objEntity == null) {
		    System.out.println("该实体不存在,OID=" + strOID);
		}
		// TODO 修改实体类
		this.updateById(objEntity);
	    }
	    return objEntity;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}

    }

    @Override
    public PayCashPool investment(Map<String, Object> map) {
	try {
	    map.put("privilegePrincipal", null);
	    map.put("privilegeInterest", null);
	    map.put("tqOID", null);
	    return this.investmentImpl(map);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
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
    private PayCashPool investmentImpl(Map<String, Object> map) {
	PayCashPool dtoCashPool = null;
	try {
	    // 获取产品信息
	    Map<String, Object> dtoProductInfo = this.gainProductInfo((String) map.get("businessOID"));

	    // 返还期数
	    Integer returnNumber = (Integer) dtoProductInfo.get("returnNumber");
	    // 结束时间
	    Date endDate = null;
	    // 利率
	    BigDecimal creditRate = (BigDecimal) dtoProductInfo.get("creditRate");

	    BigDecimal creditRateTemp = null;
	    if ("month".equals(dtoProductInfo.get("returnCycle"))) {
		creditRateTemp = creditRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		endDate = ToolDateTime.addMonth((Date) map.get("beginDate"), returnNumber);
	    } else if ("day".equals(dtoProductInfo.get("returnCycle"))) {
		creditRateTemp = creditRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		endDate = ToolDateTime.addDay((Date) map.get("beginDate"), returnNumber);
	    }

	    // 类型
	    String creditType = (String) dtoProductInfo.get("debitType");
	    if ("BIAP".equals(creditType)) {
		/*
		 * String businessOID, String outMemberOID, BigDecimal money,
		 * BigDecimal interest, BigDecimal creditRate, String
		 * creditType, String returnCycle, Integer returnNumber,
		 * Timestamp beginDate, Timestamp endDate, BigDecimal
		 * privilegePrincipal, BigDecimal privilegeInterest, String
		 * tqOID, String ly
		 */

		Map<String, Object> typeMap = new HashMap<String, Object>();
		typeMap.put("businessOID", map.get("businessOID"));
		typeMap.put("outMemberOID", map.get("memberOID"));
		typeMap.put("money", map.get("money"));
		typeMap.put("interest", ((BigDecimal) map.get("money")).multiply(creditRateTemp).setScale(2,
			BigDecimal.ROUND_HALF_EVEN));
		typeMap.put("creditRate", creditRate);
		typeMap.put("creditType", creditType);
		typeMap.put("returnCycle", (String) dtoProductInfo.get("returnCycle"));
		typeMap.put("returnNumber", returnNumber);
		typeMap.put("beginDate", ToolDateTime.gainDateTime((Date) map.get("beginDate")));
		typeMap.put("endDate", ToolDateTime.gainDateTime(endDate));
		typeMap.put("privilegePrincipal", map.get("privilegePrincipal"));
		typeMap.put("privilegeInterest", map.get("privilegeInterest"));
		typeMap.put("tqOID", map.get("tqOID"));
		typeMap.put("ly", map.get("ly"));
		dtoCashPool = this.controlCashPool.investment(typeMap);

		// 统计
		BigDecimal totalInterest = new BigDecimal("0");
		for (int i = 0; i < returnNumber; i++) {
		    PayReturnPlan dtoReturnPlan = new PayReturnPlan();
		    dtoReturnPlan.setBusinessOID((String) map.get("businessOID"));
		    dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
		    dtoReturnPlan.setMemberOID((String) map.get("memberOID"));
		    if ("month".equals(dtoProductInfo.get("returnCycle"))) {
			dtoReturnPlan.setReturnDate(ToolDateTime.addMonth((Date) map.get("beginDate"), i + 1));
		    } else if ("day".equals(dtoProductInfo.get("returnCycle"))) {
			dtoReturnPlan.setReturnDate(ToolDateTime.addMonth((Date) map.get("beginDate"), i + 1));
		    }

		    // 利息
		    BigDecimal interest = ((BigDecimal) map.get("money")).multiply(creditRateTemp).setScale(2,
			    BigDecimal.ROUND_HALF_EVEN);
		    dtoReturnPlan.setInterestMoney(interest);
		    if (returnNumber == i + 1) {
			dtoReturnPlan.setMoney(
				((BigDecimal) map.get("money")).add(interest).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			dtoReturnPlan.setPrincipalMoney((BigDecimal) map.get("money"));
			dtoReturnPlan.setTotalPrincipalMoney((BigDecimal) map.get("money"));
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
		/*
		 * String businessOID, String outMemberOID, BigDecimal money,
		 * BigDecimal interest, BigDecimal creditRate, String
		 * creditType, String returnCycle, Integer returnNumber,
		 * Timestamp beginDate, Timestamp endDate, BigDecimal
		 * privilegePrincipal, BigDecimal privilegeInterest, String
		 * tqOID, String ly
		 */
		/*
		 * dtoCashPool = controlCashPool.investment(, , ,, , , , 1, ,
		 * ToolDateTime.gainDateTime(endDate), privilegePrincipal,
		 * privilegeInterest, tqOID, ly);
		 */
		Map<String, Object> typeMap = new HashMap<>();
		BigDecimal money = new BigDecimal((String) map.get("money"));
		String typeNumber = (String) map.get("returnNumber");
		BigDecimal interest = money.multiply(creditRateTemp).multiply(new BigDecimal(returnNumber)).setScale(2,
			BigDecimal.ROUND_HALF_EVEN);
		typeMap.put("businessOID", map.get("businessOID"));
		typeMap.put("outMemberOID", map.get("memberOID"));
		typeMap.put("money", money);
		typeMap.put("interest", interest);

		typeMap.put("creditRate", creditRate);
		typeMap.put("creditType", creditType);
		typeMap.put("returnCycle", dtoProductInfo.get("returnCycle"));
		typeMap.put("returnNumber", 1);
		typeMap.put("beginDate", ToolDateTime.gainDateTime((Date) map.get("beginDate")));
		typeMap.put("endDate", ToolDateTime.gainDateTime(endDate));

		typeMap.put("privilegePrincipal", map.get("privilegePrincipal"));
		typeMap.put("privilegeInterest", map.get("privilegeInterest"));
		typeMap.put("tqOID", map.get("tqOID"));
		typeMap.put("ly", map.get("ly"));
		dtoCashPool = this.controlCashPool.investment(typeMap);
		// 全部利息
		BigDecimal interestCount = money.multiply(creditRateTemp).multiply(new BigDecimal(returnNumber))
			.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		PayReturnPlan dtoReturnPlan = new PayReturnPlan();
		dtoReturnPlan.setBusinessOID((String) map.get("businessOID"));
		dtoReturnPlan.setCashPoolOID(dtoCashPool.getOID());
		dtoReturnPlan.setMemberOID((String) map.get("memberOID"));
		if ("month".equals(dtoProductInfo.get("returnCycle"))) {
		    dtoReturnPlan.setReturnDate(ToolDateTime.addMonth((Date) map.get("beginDate"), returnNumber));
		} else if ("day".equals(dtoProductInfo.get("returnCycle"))) {
		    dtoReturnPlan.setReturnDate(ToolDateTime.addDay((Date) map.get("beginDate"), returnNumber));
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

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return dtoCashPool;
    }

    @Override
    public BigDecimal gainCashPoolCash(String strBusinessOID) {
	try {
	    BigDecimal principalMoney = new BigDecimal("0");

	    List<PayCashPool> dtocCashPool = this.controlCashPool.gainCashPoolByBusinessOID(strBusinessOID);

	    for (PayCashPool dtoCashPool : dtocCashPool) {
		principalMoney = principalMoney.add((BigDecimal) dtoCashPool.getMoney());
	    }
	    return principalMoney;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public void finishInvestment(String outBusinessOID, String inBusinessOID, BigDecimal money, String ly) {
	PayDebitCredit aDto = new PayDebitCredit();
	try {
	    Map<String, Object> dtoDebitInfo = this.gainDebitInfo(inBusinessOID);

	    // 剩余本金
	    BigDecimal otherMoney = money;
	    Boolean flag = false;
	    List<PayCashPool> cpdc = this.controlCashPool.gainCashPoolByBusinessOIDAndHaveMoney(outBusinessOID);
	    int r = 0;

	    if (cpdc != null && cpdc.size() > 0) {
		r = cpdc.size();
	    }
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

		dtoDebitCredit.setCashPoolOID(dtoCashPool.getOID());
		dtoDebitCredit.setBusinessOID(inBusinessOID);
		dtoDebitCredit.setOutMemberOID(dtoCashPool.getMemberOID());
		dtoDebitCredit.setInMemberOID((String) dtoDebitInfo.get("memberOID"));
		dtoDebitCredit.setReturnNumber((Integer) dtoDebitInfo.get("repayNumber"));
		dtoDebitCredit.setReturnCycle((String) dtoDebitInfo.get("repayCycle"));
		dtoDebitCredit.setCreditType("CP");
		dtoDebitCredit.setCreditRate((BigDecimal) dtoDebitInfo.get("debitRate"));
		dtoDebitCredit.setMoney(principal);
		dtoDebitCredit.setPrincipalMoney(principal);
		dtoDebitCredit.setSurplusPrincipalMoney(principal);
		dtoDebitCredit.setState("investment");
		dtoDebitCredit.setReturnSurplusNumber((Integer) dtoDebitInfo.get("repayNumber"));
		dtoDebitCredit.setLaiyuan(dtoCashPool.getLaiyuan());
		Date sqlDate = new Date();

		dtoDebitCredit.setStartDateTime(new Date());
		String edt = ToolsDatas.gainPlusAndReduceDay(
			DateUtils.formatJustDate(dtoDebitCredit.getStartDateTime().getTime()),
			(int) dtoDebitInfo.get("repayNumber"), 0);
		dtoDebitCredit.setEndDateTime(DateUtils.getDate(edt));
		if ("month".equals(dtoDebitInfo.get("repayCycle"))) {
		    BigDecimal creditRateTemp = ((BigDecimal) dtoDebitInfo.get("debitRate"))
			    .divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		    dtoDebitCredit.setInterest(money.multiply(creditRateTemp)
			    .multiply(new BigDecimal((Integer) dtoDebitInfo.get("repayNumber")))
			    .setScale(2, BigDecimal.ROUND_HALF_EVEN));
		} else if ("day".equals(dtoDebitInfo.get("repayCycle"))) {
		    BigDecimal creditRateTemp = ((BigDecimal) dtoDebitInfo.get("debitRate"))
			    .divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		    dtoDebitCredit.setInterest(money.multiply(creditRateTemp)
			    .multiply(new BigDecimal((Integer) dtoDebitInfo.get("repayNumber")))
			    .setScale(2, BigDecimal.ROUND_HALF_EVEN));

		}
		// dtoDebitCredit = dbControlDebitCredit.save(dtoDebitCredit);
		this.debitCreditMapper.saveDebitCredit(dtoDebitCredit);

		dtoCashPool.setMoney(dtoCashPool.getMoney().subtract(principal));

		this.payCashPoolMapper.updatePayCashPool(dtoCashPool);

		this.controlCashPool.realInvestment(inBusinessOID, (String) dtoCashPool.getMemberOID(),
			(String) dtoDebitInfo.get("memberOID"), principal);
		if (flag) {
		    aDto = dtoDebitCredit;
		    break;
		}
		aDto = dtoDebitCredit;
	    }

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	// return aDto;
    }

    @Override
    public void finishDebitCredit(String strBusinessOID, Date beginDate) {
	try {
	    // 获取借贷信息
	    Map<String, Object> dtoDebitInfo = this.gainDebitInfo(strBusinessOID);
	    List<String> lRepayOID = new ArrayList<String>();
	    // 还款部分
	    if ("EPEI".equals(dtoDebitInfo.get("debitType"))) {
		Integer repayNumber = (Integer) dtoDebitInfo.get("repayNumber");
		String repayCycle = (String) dtoDebitInfo.get("repayCycle");
		BigDecimal debitRate = (BigDecimal) dtoDebitInfo.get("debitRate");
		if ("month".equals(repayCycle)) {
		    debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		} else if ("day".equals(repayCycle)) {
		    debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		}
		// 全部本金
		BigDecimal money = (BigDecimal) dtoDebitInfo.get("money");
		// 统计
		BigDecimal totalPrincipal = new BigDecimal("0");
		BigDecimal surplusPrincipal = new BigDecimal("0");
		BigDecimal totalInterest = new BigDecimal("0");
		for (int i = 0; i < repayNumber; i++) {
		    PayRepayPlan dtoRepayPlan = new PayRepayPlan();
		    dtoRepayPlan.setBusinessOID(strBusinessOID);
		    ;
		    dtoRepayPlan.setMemberOID((String) dtoDebitInfo.get("memberOID"));
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
			manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate"))
				.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		    } else if ("day".equals(repayCycle)) {
			manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate"))
				.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		    }
		    // 服务费
		    BigDecimal service = null;
		    if ("month".equals(repayCycle)) {
			service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate"))
				.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		    } else if ("day".equals(repayCycle)) {
			service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate"))
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
		BigDecimal onceProceduresMoney = money.multiply((BigDecimal) dtoDebitInfo.get("onceProceduresRate"))
			.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		controlCashPool.onceProceduresMoney(strBusinessOID, (String) dtoDebitInfo.get("memberOID"),
			onceProceduresMoney);
		controlCashPool.finishInvestment(strBusinessOID, (String) dtoDebitInfo.get("memberOID"),
			money.subtract(onceProceduresMoney));
	    }
	    // ******* bob 10.12 BIAP生成还款计划 5575-
	    if ("BIAP".equals(dtoDebitInfo.get("debitType"))) {
		Integer repayNumber = (Integer) dtoDebitInfo.get("repayNumber");
		String repayCycle = (String) dtoDebitInfo.get("repayCycle");
		BigDecimal debitRate = (BigDecimal) dtoDebitInfo.get("debitRate");
		if ("month".equals(repayCycle)) {
		    debitRate = debitRate.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		} else if ("day".equals(repayCycle)) {
		    debitRate = debitRate.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		}
		// 全部本金
		BigDecimal money = (BigDecimal) dtoDebitInfo.get("money");
		// 统计
		BigDecimal totalPrincipal = new BigDecimal("0");
		BigDecimal surplusPrincipal = new BigDecimal("0");
		BigDecimal totalInterest = new BigDecimal("0");
		for (int i = 0; i < repayNumber; i++) {
		    PayRepayPlan dtoRepayPlan = new PayRepayPlan();
		    dtoRepayPlan.setBusinessOID(strBusinessOID);
		    dtoRepayPlan.setMemberOID((String) dtoDebitInfo.get("memberOID"));
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
			manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate"))
				.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		    } else if ("day".equals(repayCycle)) {
			manage = money.multiply((BigDecimal) dtoDebitInfo.get("manageRate"))
				.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN);
		    }
		    // 服务费
		    BigDecimal service = null;
		    if ("month".equals(repayCycle)) {
			service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate"))
				.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN);
		    } else if ("day".equals(repayCycle)) {
			service = money.multiply((BigDecimal) dtoDebitInfo.get("serviceRate"))
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
		BigDecimal onceProceduresMoney = money.multiply((BigDecimal) dtoDebitInfo.get("onceProceduresRate"))
			.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	    }

	    List<PayDebitCredit> dcdc = this.findByBusinessOIDAndState(strBusinessOID, "investment");
	    // 回款部分
	    for (PayDebitCredit dtoDebitCredit : dcdc) {
		// 结束时间
		Date endDate = null;
		// 统计
		BigDecimal totalPrincipal = new BigDecimal("0");
		BigDecimal totalInterest = new BigDecimal("0");
		Map<String, Object> thisdtoDebitInfo = this.gainDebitInfo((String) dtoDebitCredit.getBusinessOID());
		if ("OPI".equals(dtoDebitCredit.getCreditType())) {
		    Map<String, Object> dtoProductInfo = this.gainProductInfo((String) dtoDebitCredit.getBusinessOID());
		    if (dtoProductInfo != null) {
			// 非环环资金池投资
			// 返还期数
			Integer returnNumber = 1;
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) dtoProductInfo.get("returnCycle");
			// 利率
			BigDecimal creditRate = (BigDecimal) dtoDebitCredit.getCreditRate();

			// 全部本金
			BigDecimal money = (BigDecimal) dtoDebitCredit.getPrincipalMoney();

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
			Integer returnNumber = (Integer) thisdtoDebitInfo.get("repayNumber");
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) thisdtoDebitInfo.get("repayCycle");
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
			Integer returnNumber = (Integer) thisdtoDebitInfo.get("repayNumber");
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) thisdtoDebitInfo.get("repayCycle");
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
			    endDate = (Date) dtoReturnPlan.getReturnDate();
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
			Integer returnNumber = (Integer) thisdtoDebitInfo.get("repayNumber");
			// 结束时间
			// Date endDate = null;
			String returnCycle = (String) thisdtoDebitInfo.get("repayCycle");
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
	    System.out.println(e.getMessage());
	}
    }

    private List<PayDebitCredit> findByBusinessOIDAndState(String businessOID, String state) {
	try {
	    PayDebitCredit pay_DebitCredit = new PayDebitCredit();
	    pay_DebitCredit.setBusinessOID(businessOID);
	    pay_DebitCredit.setState(state);
	    return this.queryListByWhere(pay_DebitCredit);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
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
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public void addCashPoolTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
	    BigDecimal money, Date transferReturnDate, String ly) {
	try {
	    Integer isCW = 0;
	    PayTransferReturn dtocTransferReturnTemp = new PayTransferReturn();
	    PayDebitCredit oldDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

	    if (oldDebitCredit == null) {

		debitCreditOID = transferReturnMapper.getTransferReturnByOid(debitCreditOID).getDebitCreditOIDNew();
		dtocTransferReturnTemp = transferReturnMapper
			.gainTransferReturnByDebitCreditNewOIDAndState(debitCreditOID, "finish");

	    }
	    String outMemberOID = "";
	    BigDecimal creditRate = new BigDecimal(0);
	    Map<String, Object> debitInfo = null;
	    debitInfo = this.gainDebitInfo(businessOID);
	    creditRate = (BigDecimal) debitInfo.get("debitRate");
	    MemberMember outMember = userMapper.findUserByOID(oldDebitCredit.getOutMemberOID());

	    if (outMember.getUserType() != null) {
		outMemberOID = (String) outMember.getOID();
		if ("1".equals((String) outMember.getUserType())) {
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
		// terry 获取标的类型
		if (debitInfo != null) {
		    loanNumber = debitInfo.get("loanNumber").toString();
		}
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		int yhDay = 0;
		int yhqs = (int) dtoDebitCredit.getReturnNumber() - (int) dtoDebitCredit.getReturnSurplusNumber();
		Date minReturnDate = null;
		PayReturnPlan dtocReturnPlan = this.controlReturnPlan
			.gainMINByDebitCreditOIDAndState((String) dtoDebitCredit.getOID(), "plan");
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
		BigDecimal creditRateDay = ((BigDecimal) debitInfo.get("debitRate")).divide(new BigDecimal("365"), 8,
			BigDecimal.ROUND_HALF_EVEN);
		// 投资本金=投资总额*转让本金/转让总额
		BigDecimal tzbj = money.multiply((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
			.divide((BigDecimal) debitInfo.get("money"), 2, BigDecimal.ROUND_HALF_EVEN);
		// 垫付利息=投资总额-tzbj
		BigDecimal dflx = money.subtract(tzbj);

		// ---------------------------------------------------------------------------
		// 剩余本金
		BigDecimal bdze = tzbj.add(dflx);
		BigDecimal otherMoney = tzbj.add(dflx);
		Boolean flag = false;
		List<PayCashPool> dtocCashPool = this.controlCashPool
			.gainCashPoolByBusinessOIDAndHaveMoney(outBusinessOID);
		int r = 0;
		List<PayCashPool> cpdc = new ArrayList<PayCashPool>();

		if (dtocCashPool != null && dtocCashPool.size() > 0) {
		    r = dtocCashPool.size();
		    for (PayCashPool payCashPool : dtocCashPool) {
			cpdc = dtocCashPool;
		    }
		}
		for (PayCashPool dtoCashPool : cpdc) {
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
			    .divide((BigDecimal) debitInfo.get("money"), 2, BigDecimal.ROUND_HALF_EVEN);
		    // 垫付利息=投资总额-tzbj
		    BigDecimal cplx = principal.subtract(cpbj);

		    dtoTransferReturn.setBusinessOID(businessOID);
		    dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
		    dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
		    dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
		    dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
		    dtoTransferReturn.setCreditType("CP");
		    dtoTransferReturn.setCreditRate((BigDecimal) debitInfo.get("debitRate"));
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
		    // dtoTransferReturn.put("lDay", lDay);
		    dtoCashPool.setMoney(((BigDecimal) dtoCashPool.getMoney()).subtract(principal));
		    payCashPoolMapper.savePayCashPool(dtoCashPool);
		    if (flag) {
			break;
		    }
		}
		// -------------------------------------------------------------------------
	    } else {
		// 第一次

		PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);
		String buisOid = (String) dtoDebitCredit.getBusinessOID();
		List<Map<String, Object>> dtoColl = debitCreditMapper.findBybusinessID(buisOid);

		String loanNumber = "";
		// terry 获取标的类型
		for (Map<String, Object> dto : dtoColl) {
		    loanNumber = dto.get("loanNumber").toString();
		}
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		Date returnDate = null;
		PayReturnPlan dtocReturnPlan = payReturnPlanMapper.findByDebitCreditOIDAndState(debitCreditOID, "plan");
		returnDate = dtocReturnPlan.getReturnDate();

		int yhDay = 0;// 已还
		int yhqs = (int) dtoDebitCredit.getReturnNumber() - (int) dtoDebitCredit.getReturnSurplusNumber();
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
		BigDecimal advanceInterest = ((BigDecimal) dtoDebitCredit.getMoney()).multiply(creditRateDay)
			.multiply(new BigDecimal(lDay)).multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
			.divide((BigDecimal) dtoDebitCredit.getPrincipalMoney(), 2, BigDecimal.ROUND_HALF_EVEN);

		BigDecimal sumTransferMoney = ((BigDecimal) dtoDebitCredit.getPrincipalMoney()).add(advanceInterest);
		// *************************************************************************************
		BigDecimal tzbj = (BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney();
		BigDecimal dflx = ((BigDecimal) debitInfo.get("money"))
			.subtract((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney());

		BigDecimal otherMoney = tzbj.add(dflx);
		Boolean flag = false;
		List<PayCashPool> cpdc = this.controlCashPool.gainCashPoolByBusinessOIDAndHaveMoney(outBusinessOID);
		if (cpdc != null && cpdc.size() > 0) {
		    for (PayCashPool dtoCashPool : cpdc) {
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
			if (sumTransferMoney.compareTo((BigDecimal) dtoDebitCredit.getMoney()) == 1) {
			    cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
				    .divide(sumTransferMoney, 2, BigDecimal.ROUND_HALF_EVEN);
			} else {
			    cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
				    .divide((BigDecimal) dtoDebitCredit.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
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
			dtoTransferReturn.setCreditRate((BigDecimal) debitInfo.get("debitRate"));
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
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void finishTransferReturnNEW(String businessOID, Date beginDate, String isTZorCW, String tzType, String ly) {
	try {
	    String cashPoolOID = "";
	    PayCashPool dtocashPool = null;
	    String debitCreditOID = null;
	    List<String> dcrtOID = new ArrayList<>();
	    List<PayTransferReturn> dtocTransferReturn = transferReturnMapper.findByBusinessOIDAndState(businessOID,
		    "investment");

	    int isCW = 0;
	    BigDecimal ystz = new BigDecimal(0);
	    if (dtocTransferReturn != null && dtocTransferReturn.size() > 0) {
		for (PayTransferReturn dtoTransferReturn : dtocTransferReturn) {
		    ystz = (BigDecimal) dtoTransferReturn.getMoney();
		    cashPoolOID = (String) dtoTransferReturn.getCashPoolOID();
		    debitCreditOID = (String) dtoTransferReturn.getDebitCreditOID();
		    // 受让人是否是财务账号
		    String outMemberOID = (String) dtoTransferReturn.getOutMemberOID();
		    MemberMember outMember = userMapper.findUserByOID(outMemberOID);
		    if (outMember.getUserType() != null) {
			if ("1".equals((String) outMember.getUserType())) {
			    isCW = 1;
			}
		    }
		    if (!"".equals(cashPoolOID) && null != cashPoolOID) {
			// 债转前资金池
			dtocashPool = payCashPoolMapper.findByOID(cashPoolOID);
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
		    int xx = dtocReturnPlan.size();
		    // dtocReturnPlan.addAll(dtocReturnPlanTemp);
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
		    Date sqlDate = new Date();

		    // OPI类型回款计划（在回款总计划中只有一条）
		    PayReturnPlan ReturnPlanOPI = null;
		    String oldType = "";
		    PayReturnPlan oldreturnOPI = null;
		    for (PayReturnPlan dtoReturnPlan : dtocReturnPlan) {
			repayMember = (String) dtoReturnPlan.getOutMemberOID();

			if ("OPI".equals((String) dtoReturnPlan.getType())) {
			    dtoReturnPlan.setState("finish");
			    oldreturnOPI = new PayReturnPlan();
			    oldreturnOPI = dtoReturnPlan;
			} else if ("plan".equals((String) dtoReturnPlan.getState())) {
			    oldType = (String) dtoReturnPlan.getType();
			    lRepayOID.add(dtoReturnPlan.getRepayOID());
			    lReturnDate.add((Date) dtoReturnPlan.getReturnDate());
			    lReturnState.add(dtoReturnPlan.getState());
			    returnBusinessOID = (String) dtoReturnPlan.getBusinessOID();
			    oldAllMoney = oldAllMoney.add((BigDecimal) dtoReturnPlan.getPrincipalMoney());
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
			Map<String, Object> dtoProductInfo = this.gainProductInfo(returnBusinessOID);
			BigDecimal creditRatePro = (BigDecimal) dtoProductInfo.get("creditRate");
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
			    // endDate = (Date)
			    // dtoReturnPlanOPI.get("returnDate");
			    // 本金
			    BigDecimal principal = (BigDecimal) dtoTransferReturn.getPrincipalMoney();
			    // 利息
			    BigDecimal interest = dtoTransferReturn.getMoney()
				    .subtract((BigDecimal) dtoTransferReturn.getPrincipalMoney());
			    // 应收利息
			    BigDecimal yslx = ((BigDecimal) dtoTransferReturn.getMoney()).multiply(creditRatePro)
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

			Map<String, Object> dtoProductInfo = this.gainProductInfo(businessOID);
			// 解冻贴息
			BigDecimal tll = ((BigDecimal) dtoProductInfo.get("creditRate")).divide(new BigDecimal("365"),
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

	} catch (

	Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    @Override
    public void addCashPoolUserTransferReturnNEW(String businessOID, String debitCreditOID, String outBusinessOID,
	    BigDecimal money, Date transferReturnDate, String ly) {
	try {
	    int isCW = 0;
	    PayTransferReturn dtocTransferReturnTemp = new PayTransferReturn();

	    PayDebitCredit oldDebitCredit = debitCreditMapper.queryByPKOid(debitCreditOID);

	    if (oldDebitCredit == null) {
		PayTransferReturn transferReturn = transferReturnMapper.getTransferReturnByOid(debitCreditOID);
		if (transferReturn != null) {
		    debitCreditOID = transferReturn.getDebitCreditOIDNew();
		}
		dtocTransferReturnTemp = transferReturnMapper
			.gainTransferReturnByDebitCreditNewOIDAndState(debitCreditOID, "finish");

	    }
	    String outMemberOID = "";
	    BigDecimal creditRate = new BigDecimal(0);
	    Map<String, Object> debitInfo = null;
	    debitInfo = this.gainDebitInfo(businessOID);
	    creditRate = (BigDecimal) debitInfo.get("debitRate");
	    MemberMember outMember = userMapper.findUserByOID(oldDebitCredit.getOutMemberOID());
	    if (outMember != null && outMember.getUserType() != null) {
		outMemberOID = (String) outMember.getOID();
		if ("1".equals((String) outMember.getUserType())) {
		    isCW = 1;
		}
	    }

	    List<PayTransferReturn> dtocTransferReturnList = transferReturnMapper
		    .getTransferReturnByDebitCreditOIDAndstate(debitCreditOID, "finish");

	    PayTransferReturn dtoTF = null;
	    for (PayTransferReturn dto : dtocTransferReturnList) {
		dtoTF = dto;
	    }
	    int lDay = 0;
	    if (dtoTF != null) {
		// 多次债转
		PayDebitCredit dtoDebitCredit = debitCreditMapper.queryByPKOid(dtoTF.getDebitCreditOIDNew());
		String loanNumber = "";
		// terry 获取标的类型
		if (debitInfo != null) {
		    loanNumber = debitInfo.get("loanNumber").toString();
		}
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		int yhDay = 0;
		int yhqs = dtoDebitCredit.getReturnNumber() - dtoDebitCredit.getReturnSurplusNumber();

		Date minReturnDate = null;
		PayReturnPlan dtocReturnPlan = payReturnPlanMapper
			.findMINByDebitCreditOIDAndState((String) dtoDebitCredit.getOID(), "plan");

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

		BigDecimal creditRateDay = ((BigDecimal) debitInfo.get("debitRate")).divide(new BigDecimal("365"), 8,
			BigDecimal.ROUND_HALF_EVEN);
		// 投资本金=投资总额*转让本金/转让总额
		BigDecimal tzbj = money.multiply((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
			.divide((BigDecimal) debitInfo.get("money"), 2, BigDecimal.ROUND_HALF_EVEN);

		// 垫付利息=投资总额-tzbj
		BigDecimal dflx = money.subtract(tzbj);

		// ---------------------------------------------------------------------------
		// 剩余本金
		BigDecimal bdze = tzbj.add(dflx);
		BigDecimal otherMoney = tzbj.add(dflx);
		Boolean flag = false;
		List<PayCashPool> cpdc = this.controlCashPool.gainCashPoolByBusinessOIDAndHaveMoney(outBusinessOID);
		int r = 0;
		if (cpdc != null && cpdc.size() > 0) {
		    for (PayCashPool dtoCashPool : cpdc) {
			PayTransferReturn dtoTransferReturn = new PayTransferReturn();
			// 投资额
			BigDecimal principal = null;

			if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() > 0) {
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
			controlCashPool.transferReturnCashPool(businessOID, (String) dtoCashPool.getMemberOID(),
				(String) dtoDebitCredit.getOutMemberOID(), principal);

			// 投资本金=投资总额*转让本金/转让总额
			BigDecimal cpbj = new BigDecimal(0);

			cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney())
				.divide((BigDecimal) debitInfo.get("money"), 2, BigDecimal.ROUND_HALF_EVEN);
			// 垫付利息=投资总额-tzbj
			BigDecimal cplx = principal.subtract(cpbj);

			dtoTransferReturn.setBusinessOID(businessOID);
			dtoTransferReturn.setCashPoolOID(dtoCashPool.getOID());
			dtoTransferReturn.setDebitCreditOID(dtoDebitCredit.getOID());
			dtoTransferReturn.setOutMemberOID(dtoCashPool.getMemberOID());
			dtoTransferReturn.setInMemberOID(dtoDebitCredit.getOutMemberOID());
			dtoTransferReturn.setCreditType("CP");
			dtoTransferReturn.setCreditRate((BigDecimal) debitInfo.get("debitRate"));
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
			// dtoTransferReturn.put("lDay", lDay);
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
		String inOId = (String) dtoDebitCredit.getInMemberOID();
		String buisOid = (String) dtoDebitCredit.getBusinessOID();
		// DTOCollection dtoColl = this.findBybusinessID(inOId,
		// buisOid);
		String loanNumber = "";
		// 截取字符拼成债转编号
		loanNumber = loanNumber.substring(0, loanNumber.indexOf("-")) + ToolsDatas.gainSystemNow();
		Date returnDate = null;
		PayReturnPlan dtocReturnPlan = payReturnPlanMapper.findByDebitCreditOIDAndState(debitCreditOID, "plan");

		if (dtocReturnPlan != null) {
		    returnDate = dtocReturnPlan.getReturnDate();
		}
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
		BigDecimal tzbj = (BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney();
		BigDecimal dflx = ((BigDecimal) debitInfo.get("money"))
			.subtract((BigDecimal) dtoDebitCredit.getSurplusPrincipalMoney());

		BigDecimal otherMoney = tzbj.add(dflx);
		Boolean flag = false;
		int r = 0;
		List<PayCashPool> cpdc = controlCashPool.gainCashPoolByBusinessOIDAndHaveMoney(outBusinessOID);
		if (cpdc != null && cpdc.size() > 0) {
		    r = cpdc.size();
		    for (PayCashPool dtoCashPool : cpdc) {
			PayTransferReturn dtoTransferReturn = new PayTransferReturn();
			// 投资额
			BigDecimal principal = null;

			if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() > 0) {
			    principal = (BigDecimal) dtoCashPool.getMoney();
			    otherMoney = otherMoney.subtract(principal);
			} else if (otherMoney.subtract(dtoCashPool.getMoney()).doubleValue() < 0) {
			    principal = otherMoney;
			    flag = true;
			} else if (otherMoney.subtract((BigDecimal) dtoCashPool.getMoney()).doubleValue() == 0) {
			    principal = otherMoney;
			    flag = true;
			}

			// 债转
			controlCashPool.transferReturnCashPool(businessOID, (String) dtoCashPool.getMemberOID(),
				(String) dtoDebitCredit.getOutMemberOID(), principal);

			// 投资本金=投资总额*转让本金/转让总额
			BigDecimal cpbj = new BigDecimal(0);
			if (sumTransferMoney.compareTo((BigDecimal) dtoDebitCredit.getMoney()) == 1) {
			    cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
				    .divide(sumTransferMoney, 2, BigDecimal.ROUND_HALF_EVEN);
			} else {
			    cpbj = principal.multiply((BigDecimal) dtoDebitCredit.getPrincipalMoney())
				    .divide((BigDecimal) dtoDebitCredit.getMoney(), 2, BigDecimal.ROUND_HALF_EVEN);
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
			dtoTransferReturn.setCreditRate((BigDecimal) debitInfo.get("debitRate"));
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
			// dtoTransferReturn.put("lDay", lDay);
			dtoCashPool.setMoney(((BigDecimal) dtoCashPool.getMoney()).subtract(principal));
			payCashPoolMapper.savePayCashPool(dtoCashPool);

			if (flag) {
			    break;
			}
		    }
		}
	    }

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    /**
     * 增加债转 普通债转
     * 
     * @param map
     * @return
     */
    @Override
    public PayTransferReturn addTransferReturn(Map<String, Object> map) {
	try {
	    BigDecimal privilegeInterest = null;
	    BigDecimal creditRate = null;
	    String businessOID = (String) map.get("businessOID");
	    String outMemberOID = (String) map.get("outMemberOID");
	    String memberOID = (String) map.get("memberOID");
	    BigDecimal money = (BigDecimal) map.get("amount");
	    String debitCreditOID = (String) map.get("debitCreditOID");
	    if (map.get("creditRate") != null) {
		creditRate = (BigDecimal) map.get("creditRate");
	    }
	    if (map.get("privilegeInterest") != null) {
		creditRate = creditRate.add(privilegeInterest);
	    }
	    BigDecimal nhll = (creditRate.multiply(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_DOWN);
	    WebP2pDebtTransferApply applyRecordes = debtTransferApplyMapper
		    .findByBusinessOIDMoneyCreditRate(debitCreditOID, money, nhll);

	    int lDay = applyRecordes.getSyqs();
	    BigDecimal months = new BigDecimal(12);
	    BigDecimal zqs = new BigDecimal(applyRecordes.getZqs());
	    // 对应的债转本金
	    BigDecimal baseMoney = (applyRecordes.getBdjg()).subtract(applyRecordes.getYslx());
	    BigDecimal yslx = ((creditRate.subtract(months)).multiply(zqs)).multiply(baseMoney);

	    // 垫付利息
	    BigDecimal advanceInterest = applyRecordes.getYslx();
	    BigDecimal privilegePrincipal = null;
	    if (map.get("privilegePrincipal") != null) {
		privilegePrincipal = (BigDecimal) map.get("privilegePrincipal");
		controlCashPool.transferReturn(businessOID, outMemberOID, memberOID,
			money.add(advanceInterest).subtract((BigDecimal) map.get("privilegePrincipal")));
		controlCashPool.discount(businessOID, outMemberOID, (BigDecimal) map.get("privilegePrincipal"));
	    } else {
		controlCashPool.transferReturn(businessOID, outMemberOID, memberOID, money.subtract(advanceInterest));
	    }
	    PayTransferReturn transferReturn = new PayTransferReturn();
	    transferReturn.setBusinessOID(businessOID);
	    transferReturn.setDebitCreditOID(debitCreditOID);
	    transferReturn.setOutMemberOID(outMemberOID);
	    transferReturn.setInMemberOID(memberOID);
	    transferReturn.setCreditType((String) map.get("creditType"));
	    transferReturn.setCreditRate(creditRate);
	    transferReturn.setMoney(money);
	    transferReturn.setPrincipalMoney(baseMoney);
	    transferReturn.setAdvanceInterest(advanceInterest);
	    transferReturn.setPrivilegePrincipal(privilegePrincipal);
	    transferReturn.setPrivilegeInterest(privilegeInterest);
	    transferReturn.setReturnSurplusNumber(applyRecordes.getSyqs());
	    transferReturn.setInterest(applyRecordes.getYslx());
	    transferReturn.setState("investment");
	    transferReturn.setLaiyuan((String) map.get("laiyuan"));

	    // cjsj AS startDateTime, 这是是开始时间
	    // DATE_ADD(cjsj, INTERVAL zqs MONTH) AS endDateTime 如果用到
	    // endDateTime 需要经过计算
	    transferReturn.setStartDateTime(DateUtils.getJustDate(applyRecordes.getCjsj()));
	    String endDateTime = DateUtils.gainPlusAndReduceDay(applyRecordes.getCjsj(), applyRecordes.getZqs(), 0);

	    transferReturn.setEndDateTime(DateUtils.getJustDate(endDateTime));
	    transferReturn.setOID(StringUtil.getUUID());
	    transferReturnMapper.saveTransferReturn(transferReturn);

	    return transferReturn;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }
}
