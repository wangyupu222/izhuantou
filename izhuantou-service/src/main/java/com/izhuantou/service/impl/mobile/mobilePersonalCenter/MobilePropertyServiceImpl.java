package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

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

import com.fuiou.data.AppTransReqData;
import com.fuiou.util.SecurityUtils;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.MobilePropertyTJDTO;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerOperation;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PaySeller;
import com.izhuantou.damain.pay.PayWithdrawalRecord;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.damain.vo.JoinSelectAllDTO;
import com.izhuantou.damain.vo.MyLentTBZDaoDTO;
import com.izhuantou.damain.vo.MylendDDTDaoDTO;
import com.izhuantou.damain.vo.MylendDaoDTO;
import com.izhuantou.damain.vo.MylendZZTDaoDTO;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.mobile.IndividualPropertyMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayCustomerOperationMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeProductMappingMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.pay.PaySellerMapper;
import com.izhuantou.dao.pay.PayWithdrawalRecordMapper;
import com.izhuantou.dao.personalCenter.MylendMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobilePropertyService;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.service.impl.user.ReadPropertiesl;

@Service("mobilePropertyService")
public class MobilePropertyServiceImpl implements MobilePropertyService {
    private static final Logger logger = LoggerFactory.getLogger(MobilePropertyServiceImpl.class);
    @Autowired
    private MylendMapper mylendMapper;

    @Autowired
    private IndividualPropertyMapper individualPropertyMapper;
    @Autowired
    private PayCustomerOperationMapper PayCustomerOperationDao;

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private PayReturnPlanMapper payReturnPlanDao;

    @Autowired
    private UserService userService;

    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;

    @Autowired
    private PayPrivilegeProductMappingMapper privilegeProductMappingMapper;

    @Autowired
    private PayPrivilegeMapper privilegeMapper;

    @Autowired
    private PayCustomerTyjMapper payCustomertyjMapper;

    @Autowired
    private PayCashPoolMapper payCashPoolDao;

    @Autowired
    private PayCustomerMapper payCustomerMapper;

    @Autowired
    private PaySellerMapper paySellerMapper;

    @Autowired
    private PayCustomerMapper PayCustomerMapper;

    @Autowired
    private PayWithdrawalRecordMapper PayWithdrawalRecordMapper;

    /**
     * 根据memberOID查看个人资产详情
     */
    @Override
    public MobilePropertyTJDTO findIndividualProperty(String memberOID) {
	try {

	    // 用于返回所有的数据
	    MobilePropertyTJDTO money = new MobilePropertyTJDTO();
	    BigDecimal TBZAllcjje = new BigDecimal(0);
	    BigDecimal DDTAllcjje = new BigDecimal(0);
	    BigDecimal HHTAllcjje = new BigDecimal(0);
	    BigDecimal ZZTAllcjje = new BigDecimal(0);

	    BigDecimal TBZWCAllcjje = new BigDecimal(0);
	    BigDecimal DDTWCAllcjje = new BigDecimal(0);
	    BigDecimal HHTWCAllcjje = new BigDecimal(0);
	    BigDecimal ZZTWCAllcjje = new BigDecimal(0);
	    BigDecimal ljcj = new BigDecimal(0);

	    // 持有中的头笔赚
	    List<MyLentTBZDaoDTO> tbzlist = mylendMapper.findTBZhavingBymemberOID(memberOID);
	    // 点点投持有列表
	    List<MylendDDTDaoDTO> ddtdtoList = individualPropertyMapper.findAppfindDDThavingBymemberOID(memberOID);
	    // 环环投持有
	    List<MylendDaoDTO> hhtList = individualPropertyMapper.findAppHHhavingBymemberOID(memberOID);
	    // 转转投持有
	    List<MylendZZTDaoDTO> zztlist = individualPropertyMapper.findAppZZThavingBymemberOID(memberOID);

	    // 查询完成的头笔赚
	    List<MyLentTBZDaoDTO> tbzWCdto = mylendMapper.findTBZWCBymemberOID(memberOID);
	    // 点点投完成
	    List<MylendDDTDaoDTO> ddtWClist = individualPropertyMapper.findAppDDTWCBymemberOID(memberOID);
	    // 环环已经完成的列表
	    List<MylendDaoDTO> hhtWCList = individualPropertyMapper.findAppHHTWCBymemberOID(memberOID);
	    // 转转投完成列表
	    List<MylendZZTDaoDTO> zztWClist = individualPropertyMapper.findAppZZTWCBymemberOID(memberOID);

	    // 头笔赚的完成的钱数
	    if (tbzWCdto.size() > 0) {
		for (MyLentTBZDaoDTO tbzwc : tbzWCdto) {
		    BigDecimal TBZWCcjje = tbzwc.getCjje();
		    TBZWCAllcjje = TBZWCAllcjje.add(TBZWCcjje);
		}
		ljcj = ljcj.add(TBZWCAllcjje);
	    }
	    // 点点投完成的钱数
	    if (ddtWClist.size() > 0) {
		for (MylendDDTDaoDTO ddtwc : ddtWClist) {
		    BigDecimal DDWCcjje = ddtwc.getCjje();
		    DDTWCAllcjje = DDTWCAllcjje.add(DDWCcjje);
		}
		ljcj = ljcj.add(DDTWCAllcjje);
	    }
	    // 环环完成
	    if (hhtWCList.size() > 0) {
		for (MylendDaoDTO hhtwc : hhtWCList) {
		    BigDecimal HHWCcjje = hhtwc.getCjje();
		    HHTWCAllcjje = HHTWCAllcjje.add(HHWCcjje);
		}
		ljcj = ljcj.add(HHTWCAllcjje);
	    }
	    // 转转完成
	    if (zztWClist.size() > 0) {
		for (MylendZZTDaoDTO zzwc : zztWClist) {
		    BigDecimal ZZWCcjje = zzwc.getCjje();
		    ZZTWCAllcjje = ZZTWCAllcjje.add(ZZWCcjje);
		}
		ljcj = ljcj.add(ZZTWCAllcjje);
	    }
	    // 新手出借金额
	    if (tbzlist.size() > 0) {
		for (MyLentTBZDaoDTO tbz : tbzlist) {
		    BigDecimal TBZcjje = tbz.getCjje();
		    TBZAllcjje = TBZAllcjje.add(TBZcjje);
		}
		ljcj = ljcj.add(TBZAllcjje);
	    }
	    // 点点投持有的
	    if (ddtdtoList.size() > 0) {
		for (MylendDDTDaoDTO ddt : ddtdtoList) {
		    BigDecimal DDTcjje = ddt.getCjje();
		    DDTAllcjje = DDTAllcjje.add(DDTcjje);
		}
		ljcj = ljcj.add(DDTAllcjje);
	    }
	    // 环环持有金额
	    if (hhtList.size() > 0) {
		for (MylendDaoDTO hht : hhtList) {
		    BigDecimal HHTcjje = hht.getCjje();
		    HHTAllcjje = HHTAllcjje.add(HHTcjje);
		}
		ljcj = ljcj.add(HHTAllcjje);
	    }
	    // 转转投
	    if (zztlist.size() > 0) {
		for (MylendZZTDaoDTO zzt : zztlist) {
		    BigDecimal ZZTcjje = zzt.getCjje();
		    ZZTAllcjje = ZZTAllcjje.add(ZZTcjje);
		}
		ljcj = ljcj.add(ZZTAllcjje);
	    }
	    money.setDDchujie(DDTAllcjje);
	    money.setHHchujie(HHTAllcjje);
	    money.setZZchujie(ZZTAllcjje);
	    money.setLjcj(ljcj);

	    // 用户累计充值
	    BigDecimal yhljcz = new BigDecimal(0);
	    List<PayCustomerOperation> userljcz = PayCustomerOperationDao.findAppRechargeRecord(memberOID, "recharge");

	    if (userljcz.size() > 0) {
		for (PayCustomerOperation oper : userljcz) {
		    yhljcz = yhljcz.add(oper.getMoney());
		}
	    }
	    money.setLjcz(yhljcz);
	    // 用户累计提现
	    BigDecimal yhljtx = new BigDecimal(0);
	    List<PayCustomerOperation> userljtx = PayCustomerOperationDao.findAppRechargeRecord(memberOID,
		    "withdrawals");
	    if (userljtx.size() > 0) {
		for (PayCustomerOperation oper : userljtx) {
		    yhljtx = yhljtx.add(oper.getMoney());
		}
	    }
	    money.setLjtx(yhljtx);
	    MemberMember member = userDao.findUserByOID(memberOID);
	    Date addtime = member.getAddDateTime();

	    Integer registerdays = DateUtils.daysBetween(addtime, new Date());
	    String registertime = DateUtils.formatJustDate(addtime.getTime());
	    money.setRegisterdays(registerdays);
	    money.setRegistertime(registertime);

	    return money;
	} catch (Exception e) {
	    logger.error("findIndividualProperty(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * app我的页面三个数据
     */
    @Override
    public MobilePropertyTJDTO findIndexProperty(String memberOID) {
	try {
	    // 用于返回所有的数据
	    MobilePropertyTJDTO money = new MobilePropertyTJDTO();
	    // 累计特权收益
	    BigDecimal tqsy = new BigDecimal(0);
	    List<Map<String, Object>> dstq = new ArrayList<Map<String, Object>>();
	    List<Map<String, Object>> ljtq = userService.gainDDTTQBymemberANDstate(memberOID, "2");
	    List<Map<String, Object>> ddljtq = userService.gainByMemebrOIDAndTQ(memberOID, "finishInvestment");
	    dstq.addAll(ljtq);
	    dstq.addAll(ddljtq);
	    if (dstq.size() > 0) {
		for (Map<String, Object> mp : dstq) {
		    String tqOID = (String) mp.get("tqOID");
		    PayPrivilege tq = privilegeMapper.findByOID(tqOID);
		    Integer privilegeTerm = tq.getPrivilegeTerm();
		    WebP2pPackageBiddingMainRuning runing = packageBiddingMainRuningMapper
			    .findByOID((String) mp.get("businessOID"));
		    if (privilegeTerm == 0) {
			privilegeTerm = privilegeProductMappingMapper
				.findDayByTqPudcOID(tqOID, runing.getProductRateInfoID()).getJXDay();
		    }
		    BigDecimal privilegeRange = tq.getPrivilegeRange();
		    BigDecimal privilegeRange_new = privilegeRange.divide(new BigDecimal(100));
		    BigDecimal ljtqsy = new BigDecimal(0);
		    ljtqsy = ljtqsy.add((BigDecimal) mp.get("principalMoney"));
		    ljtqsy = ljtqsy.multiply(privilegeRange_new)
			    .divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
			    .multiply(new BigDecimal(privilegeTerm)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		    if ("earlyFinish".equals(mp.get("state"))) {
			if (mp.get("privilegeInterest") != null && !"".equals(mp.get("privilegeInterest"))) {
			    ljtqsy = (BigDecimal) mp.get("privilegeInterest");
			}
		    }
		    tqsy = tqsy.add(ljtqsy);
		}
	    }
	    // 体验金收益收益 也是属于特权收益中的
	    List<CustomerTyjDTO> dtoTYJ = payCustomertyjMapper.findByMemberOID(memberOID);
	    if (dtoTYJ.size() > 0) {
		for (CustomerTyjDTO tyj : dtoTYJ) {
		    String isUsed = tyj.getIsUsed();
		    if ("1".equals(isUsed)) {
			tqsy = tqsy.add(tyj.getTyjPrice());
		    }
		}
	    }
	    // 加息已收 加息券的
	    BigDecimal jxys = new BigDecimal(0);
	    // 加息未收
	    BigDecimal jxws = new BigDecimal(0);
	    // 添加加息标额外收益
	    List<PayCashPool> payCash = payCashPoolDao.gainByMemberOID(memberOID);
	    if (payCash.size() > 0) {
		for (PayCashPool pay : payCash) {
		    if ("finishInvestment".equals(pay.getState()) && null != pay.getJXinterest()) {
			jxys = jxys.add(pay.getJXinterest());
		    }
		    if ("investment".equals(pay.getState()) && null != pay.getJXinterest()) {
			jxws = jxws.add(pay.getJXinterest());
		    }
		}
	    }
	    BigDecimal yjzq = new BigDecimal(0.0);
	    List<JoinSelectAllDTO> dtor = payReturnPlanDao.gainReturnPlanByMemberOIDAndState(memberOID, "finish");
	    List<JoinSelectAllDTO> dtorr = payReturnPlanDao.gainReturnPlanByMemberOIDAndState(memberOID,
		    "transferReturn");
	    if (dtor.size() > 0) {
		for (JoinSelectAllDTO all : dtor) {
		    yjzq = yjzq.add(all.getInterestMoney());
		}
	    }
	    if (dtorr.size() > 0) {
		for (JoinSelectAllDTO all : dtorr) {
		    yjzq = yjzq.add(all.getInterestMoney());
		}
	    }

	    // 累计收益
	    BigDecimal ljsy = new BigDecimal(0);
	    ljsy = ljsy.add(yjzq).add(tqsy).add(jxys);
	    money.setLjsy(ljsy);

	    PersonalDTO indexNumber = userService.findIndexNumber(memberOID);
	    // 获取待收本金/待收利息
	    BigDecimal dsbj = new BigDecimal(0);
	    BigDecimal dslx = new BigDecimal(0);
	    // 代收本金
	    dsbj = indexNumber.getCybj();
	    // 代收利息
	    dslx = dslx.add(indexNumber.getYjsy());
	    money.setDsbj(dsbj);
	    money.setDslx(dslx);

	    PayCustomer payCustomer = payCustomerMapper.findByMemberOID(memberOID);
	    // 可用余额
	    BigDecimal useMoney = payCustomer.getUseMoney();
	    money.setKyye(useMoney);
	    BigDecimal zzc = new BigDecimal(0.0);
	    BigDecimal tqsyds =indexNumber.getTqsyds();
	    zzc = useMoney.add(dslx).add(tqsyds).add(dsbj);
	    money.setTqsyds(tqsyds);
	    money.setZzc(zzc);
	    return money;
	} catch (Exception e) {
	    logger.error("findIndexProperty(String memberOID)", e.getMessage());
	    return null;
	}
    }

    /**
     * app充值
     */
    @Override
    public Map<String, Object> AppRecharge(String memberOID, BigDecimal money) {
	try {

	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    String sellerName = ReadPropertiesl.gainPropertiesValue("defaultSeller", "Pay.properties");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    AppTransReqData data = new AppTransReqData();

	    BigDecimal fen = new BigDecimal(100);
	    BigDecimal fenMoney = money.multiply(fen);
	    String ID = paySeller.getID();
	    // 商户代码
	    data.setMchnt_cd(ID);
	    // 流水号
	    String strRequestID = StringUtil.getUUID().substring(2);
	    data.setMchnt_txn_ssn(strRequestID);
	    // 用户的资金账户
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    // 用户登录名
	    data.setLogin_id(customer.getName());
	    // 充值金额
	    data.setAmt(String.valueOf(fenMoney.intValue()));
	    Map<String, String> pMap = ReadPropertiesl.gainPropertiesMap("Pay.properties");
	    data.setPage_notify_url(pMap.get("mobilerechargeCallbackURL"));
	    data.setBack_notify_url(pMap.get("appRechargeForwardURL"));
	    String fsjm = SecurityUtils.sign(data.createSignValue());
	    resultmap.put("data", data);
	    return resultmap;
	} catch (Exception e) {
	    logger.error("AppRecharge(String memberOID, BigDecimal money)", e.getMessage());
	    return null;
	}
    }

    @Override
    public Map<String, Object> Appwithdrawal(String memberOID, BigDecimal money) {
	try {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    if (StringUtil.isEmpty(memberOID)) {
		resultMap.put("message", "membeerOID不能为空");
		return resultMap;
	    }
	    String txfl = ReadPropertiesl.gainPropertiesValue("txfl", "privilege.properties");
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    BigDecimal useMoney = customer.getUseMoney();
	    // 提现金额小于可用余额
	    if (money.compareTo(useMoney) <= 0) {
		// 免费额度
		BigDecimal mfed = new BigDecimal("0");
		// 判断用户是否有免费额度 terry
		if (null != customer.getFreeline()) {
		    mfed = mfed.add(customer.getFreeline());
		}
		// 提现金额
		BigDecimal txje = money;
		// 提现金额减去可用免费额度获取差额
		BigDecimal ce = txje.subtract(mfed);
		BigDecimal freeline = new BigDecimal("0");
		// 提现手续费
		BigDecimal sxf = new BigDecimal("0");

		if (ce.compareTo(new BigDecimal("0")) > 0) {
		    sxf = sxf.add(ce.multiply(new BigDecimal(txfl))).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		    if (sxf.compareTo(new BigDecimal("0")) == 0) {
			sxf = new BigDecimal("0.01");
		    }
		}
		// 替换用户可用额度
		if (ce.compareTo(new BigDecimal("0")) <= 0) {
		    freeline = ce.abs();
		}
		String strRequestID = StringUtil.getUUID().substring(2);
		PayWithdrawalRecord withdrawalRecord = new PayWithdrawalRecord();
		String oid = StringUtil.getUUID();
		withdrawalRecord.setMemberOID(memberOID);
		withdrawalRecord.setOID(oid);
		withdrawalRecord.setSxf(sxf);
		withdrawalRecord.setMfed(freeline);
		withdrawalRecord.setMfedbefor(mfed);
		withdrawalRecord.setMoney(money);
		withdrawalRecord.setStatus("0");
		withdrawalRecord.setRequestID(strRequestID);
		int rows = PayWithdrawalRecordMapper.saveWithdrawalrecord(withdrawalRecord);
		if (rows == 1) {
		    Map<String, String> propMap = ReadPropertiesl.gainPropertiesMap("Pay.properties");
		    String sellerName = propMap.get("defaultSeller");
		    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    AppTransReqData data = new AppTransReqData();
		    // 商户代码
		    data.setMchnt_cd(paySeller.getID());
		    // 流水号
		    data.setMchnt_txn_ssn(strRequestID);
		    // 用户登录名
		    data.setLogin_id(customer.getName());
		    // 提现金额
		    data.setAmt(String.valueOf(fenMoney.intValue()));

		    // 商户返回地址
		    data.setPage_notify_url(propMap.get("mobilewithdrawalsCallbackURL"));
		    // 商户后台通知地址
		    data.setBack_notify_url(propMap.get("appWithdrawalsForwardURL"));
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    resultMap.put("message", "1");
		    resultMap.put("data", data);
		} else {
		    resultMap.put("message", "提现失败");
		}
		return resultMap;
	    } else {
		resultMap.put("message", "可用余额不足");
		return resultMap;
	    }
	} catch (Exception e) {
	    logger.error("withdrawal(WithdrawalDTO withdrawal) ", e.getMessage());
	    return null;
	}

    }

}
