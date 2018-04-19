package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCHHT;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeProductMapping;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.mobile.PersonalMyLend.MobilePersonalMyLendMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeProductMappingMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileMyLendDetialService;

@Service("mobileMyLendDetialService")
public class MobileMyLendDetialServiceImpl implements MobileMyLendDetialService {
    @Autowired
    MobilePersonalMyLendMapper personalMyLendMapper;
    @Autowired
    PayReturnPlanMapper payReturnPlan;
    @Autowired
    private PayCashPoolMapper payCashPoolDao;
    @Autowired
    private PayPrivilegeMapper payPrivilegeMapper;
    @Autowired
    private PayDebitCreditMapper PAY_DebitCreditMapper;
    @Autowired
    private WebP2pNormalBiddingRuningMapper WEBP2PNormalBiddingRuningMapper;

    @Autowired
    private PayPrivilegeProductMappingMapper payPrivilegeProductMappingMapper;

    @Autowired
    private PayPrivilegeMemberMappingMapper payPrivilegeMemberMappingMapper;

    @Autowired
    WebP2pProductRateInfoMapper productRateInfoMapper;

    /** 个人中心我的出借环环投详情 */
    @Override
    public MobileMyLendDetialHHT FindByCashPool(String payCashPoolOID, String memberOID) {
	try {
	    MobileMyLendDetialHHT hhtlendDTO = new MobileMyLendDetialHHT();
	    hhtlendDTO = personalMyLendMapper.findHHTDetial(payCashPoolOID, memberOID);
	    String businessOID = hhtlendDTO.getBusinessOID();
	    List<PayReturnPlan> rp = payReturnPlan.findReturnPlanByMemberOIDAndState(businessOID, "agentPlan");
	    if (rp != null && rp.size() > 0) {
		if (hhtlendDTO.getCreditType() != null && hhtlendDTO.getCreditType().equals("OPI")) {
		    // 出借时间截取
		    Timestamp s = new Timestamp(hhtlendDTO.getCjsjTime().getTime());
		    String cjsj = s.toString().substring(0, 10);
		    hhtlendDTO.setCjsj(cjsj);
		    // 下个还款日截取
		    Timestamp s1 = new Timestamp(hhtlendDTO.getXghkr().getTime());
		    String xghkr = s1.toString().substring(0, 10);
		    hhtlendDTO.setXghkrTime(xghkr);
		    hhtlendDTO.setJxsj(cjsj);
		    // dqsj
		    Timestamp s3 = new Timestamp(hhtlendDTO.getDqsj().getTime());
		    String dqsj1 = s3.toString();
		    hhtlendDTO.setDqsjTime(DateUtils.gainPlusAndDay(dqsj1, 1, 1).substring(0, 10));
		    // 添加可投金额 terry
		    BigDecimal kt = hhtlendDTO.getSxAmount().subtract(hhtlendDTO.getHoldingAmount());
		    // hhtlendDTO.getOID();
		    String sykt = personalMyLendMapper.FindSykt("027e0f710a5dca710d8976c18d67ca23");
		    hhtlendDTO.setSykt(sykt);
		} else {
		    // 出借时间截取
		    Timestamp s = new Timestamp(hhtlendDTO.getCjsjTime().getTime());
		    String cjsj = s.toString().substring(0, 10);

		    String syqs = hhtlendDTO.getSyqs();

		    // 下个还款日截取
		    Timestamp s1 = new Timestamp(hhtlendDTO.getXghkr().getTime());
		    String xghkrt = s1.toString();
		    xghkrt = DateUtils.gainPlusAndDay(xghkrt, 1, 0).substring(0, 10);
		    hhtlendDTO.setXghkrTime(xghkrt);
		    // 加息时间
		    hhtlendDTO.setJxsj(cjsj);
		    // 带取时间
		    hhtlendDTO.setDqsjTime(DateUtils.gainPlusAndReduceDay(cjsj, Integer.parseInt(syqs), 0));
		    // 出借时间
		    hhtlendDTO.setCjsj(cjsj);
		    // 添加可投金额 terry
		    BigDecimal kt = hhtlendDTO.getSxAmount().subtract(hhtlendDTO.getHoldingAmount());
		}
		Timestamp xt = new Timestamp(hhtlendDTO.getXtTime().getTime());
		String xtTime = xt.toString().substring(0, 10);
		String dqTime = hhtlendDTO.getDqsjTime();
		int ds = DateUtils.daysBetween(xtTime, dqTime);
		int c = hhtlendDTO.getMoney().compareTo((new BigDecimal(0.00)));
		if (c == 0) {
		    hhtlendDTO.setHkzt("zzhkz");
		} else {
		    if (personalMyLendMapper.findByMainOID(hhtlendDTO.getBusinessOID()).size() > 0) {
			hhtlendDTO.setHkzt("zzhkz");
		    } else {
			hhtlendDTO.setHkzt("yyz");
		    }
		}

		if (hhtlendDTO.getTqOID() != null) {

		    // 查询特权信息
		    PayPrivilege tqInfo = payPrivilegeMapper.findByOID(hhtlendDTO.getTqOID());
		    // 特权名字
		    String privilegeName = tqInfo.getPrivilegeName();
		    // 特权域(红包数额/加息百分位)
		    BigDecimal privilegeRange = tqInfo.getPrivilegeRange();

		    // 特权期限(天数)
		    int privilegeTerm = tqInfo.getPrivilegeTerm();

		    // 特殊券判别
		    if (privilegeTerm == 0) {
			WebP2pProductRateInfo product = productRateInfoMapper.findByOID(hhtlendDTO.getProductInfoOID());
			privilegeTerm = (int) (product.getProductTerm() * 30);
		    }

		    // 计算特权收益
		    BigDecimal tqsy = new BigDecimal(0);
		    tqsy = tqsy.add(hhtlendDTO.getCjje());
		    tqsy = tqsy.multiply(privilegeRange).divide(new BigDecimal("36500"), 8, BigDecimal.ROUND_HALF_EVEN)
			    .multiply(new BigDecimal(privilegeTerm)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		    hhtlendDTO.setPrivilegeName(privilegeName);

		    hhtlendDTO.setPrivilegeRange(privilegeRange.toString());

		    hhtlendDTO.setPrivilegeTerm(privilegeTerm);
		    hhtlendDTO.setTqsy(tqsy.toString());
		    hhtlendDTO.setJudeg(1);
		}

		// 替换个人中心利率显示 terry
		PayCashPool payCashPool = payCashPoolDao.findByOID(hhtlendDTO.getCashPoolOID());
		BigDecimal zero = new BigDecimal(0);
		if (payCashPool.getJXother() != null && (payCashPool.getJXother().compareTo(zero) > 0)) {
		    // 额外加息利率
		    BigDecimal jx = payCashPool.getJXother();
		    // 原产品预收本息
		    BigDecimal ysbx = hhtlendDTO.getYsbx();
		    hhtlendDTO.setJXother(jx);
		    ysbx = ysbx.add(payCashPool.getJXinterest());
		    hhtlendDTO.setYsbx(ysbx);
		} else {
		    hhtlendDTO.setJXother(new BigDecimal(0));
		}

	    }
	    return hhtlendDTO;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    /** 我的出借点点投进行中详情 */
    @Override
    public MobileMyLendDetialDDT FindByDDTDebitCreditOID(String memberOID, String debitCreditOID) {
	try {
	    MobileMyLendDetialDDT mobileMyLendDetialDDT = new MobileMyLendDetialDDT();
	    mobileMyLendDetialDDT = personalMyLendMapper.findDDTDetial(memberOID, debitCreditOID);
	    mobileMyLendDetialDDT.setProductStatus(mobileMyLendDetialDDT.getHkzt());

	    if (mobileMyLendDetialDDT.getHkzt().equals("4")) {
		mobileMyLendDetialDDT.setHkzt("yq");
		// 出借时间截取
		Timestamp s = new Timestamp(mobileMyLendDetialDDT.getCjsjTime().getTime());
		String cjsj = s.toString().substring(0, 10);
		mobileMyLendDetialDDT.setCjsj(cjsj);
		// 下个还款日截取
		Timestamp xghk = new Timestamp(mobileMyLendDetialDDT.getXghkr().getTime());
		String xghkr = xghk.toString().substring(0, 10);
		mobileMyLendDetialDDT.setXghkrTime(xghkr);
		// dq时间截取
		Timestamp dq = new Timestamp(mobileMyLendDetialDDT.getDqsj().getTime());
		String dqsj = dq.toString().substring(0, 10);
		mobileMyLendDetialDDT.setDqsjTime(dqsj);

	    } else {
		mobileMyLendDetialDDT.setHkzt("zzhkz");
		// 出借时间截取
		Timestamp s = new Timestamp(mobileMyLendDetialDDT.getCjsjTime().getTime());
		String cjsj = s.toString().substring(0, 10);
		mobileMyLendDetialDDT.setCjsj(cjsj);

		Date xg = mobileMyLendDetialDDT.getXghkr();
		Timestamp dq = new Timestamp(mobileMyLendDetialDDT.getDqsj().getTime());
		Timestamp jx = new Timestamp(mobileMyLendDetialDDT.getJxsj().getTime());
		if (xg == null) {
		    mobileMyLendDetialDDT.setXghkrTime("");
		} else {
		    // 下个还款日截取
		    Timestamp xghk = new Timestamp(mobileMyLendDetialDDT.getXghkr().getTime());
		    String xghkr = xghk.toString().substring(0, 10);
		    mobileMyLendDetialDDT.setXghkrTime(xghkr);
		}
		if (dq == null) {
		    mobileMyLendDetialDDT.setDqsjTime("");
		} else {
		    // dq时间截取
		    Timestamp dq1 = new Timestamp(mobileMyLendDetialDDT.getDqsj().getTime());
		    String dqsj = dq1.toString().substring(0, 10);
		    mobileMyLendDetialDDT.setDqsjTime(dqsj);
		}
		if (dq == null) {
		    mobileMyLendDetialDDT.setJxsjTime("");
		} else {
		    String jxsj = jx.toString().substring(0, 10);
		    mobileMyLendDetialDDT.setJxsjTime(jxsj);
		}
	    }

	    if (mobileMyLendDetialDDT.getTqOID() != null) {

		WebP2pNormalBiddingRuning normal = WEBP2PNormalBiddingRuningMapper
			.findByOID(mobileMyLendDetialDDT.getBiOID());
		// 特权
		List<PayPrivilegeProductMapping> tqTemp = payPrivilegeProductMappingMapper
			.findByOID(normal.getProductRateInfoID());
		if (tqTemp.size() > 0) {
		    for (PayPrivilegeProductMapping product : tqTemp) {
			String tqOID = mobileMyLendDetialDDT.getTqOID();
			if (tqOID.equals(product.getPrivilegeOID())) {
			    PayPrivilege tqInfo = payPrivilegeMapper.findByOID(tqOID);
			    String privilegeName = tqInfo.getPrivilegeName();
			    BigDecimal lowAmount = tqInfo.getLowAmount();
			    BigDecimal privilegeRange = tqInfo.getPrivilegeRange();
			    int privilegeTerm = tqInfo.getPrivilegeTerm();
			    String privilegeType = tqInfo.getPrivilegeType();
			    int rule = 0;
			    String ruleIntroduce = "";
			    if (tqInfo.getRule() != null) {
				rule = (int) tqInfo.getRule();
			    }
			    if (StringUtil.isNotEmpty(tqInfo.getRuleIntroduce())) {
				ruleIntroduce = tqInfo.getRuleIntroduce();
			    }
			    List<PayPrivilegeMemberMapping> pmm = payPrivilegeMemberMappingMapper.findByCondition(tqOID,
				    memberOID);
			    if (pmm.size() == 0) {
				mobileMyLendDetialDDT.setJudeg(0);
			    } else {
				for (PayPrivilegeMemberMapping ite : pmm) {
				    BigDecimal tqsy = new BigDecimal(0);
				    Date enddate = DateUtils.getDate(ite.getEndDate().getTime());
				    String jsDate = DateUtils.formatDate(enddate, "");
				    Date startdate = DateUtils.getDate(ite.getStartDate().getTime());
				    String ksDate = DateUtils.formatDate(enddate, "");
				    tqsy = tqsy.add(mobileMyLendDetialDDT.getCjje());
				    BigDecimal privilegeRange_new = privilegeRange.divide(new BigDecimal(100));
				    // 特权收益
				    tqsy = tqsy.multiply(privilegeRange_new)
					    .divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
					    .multiply(new BigDecimal(privilegeTerm))
					    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
				    mobileMyLendDetialDDT.setTqsy(tqsy);
				    mobileMyLendDetialDDT.setPrivilegeName(privilegeName);
				    mobileMyLendDetialDDT.setPrivilegeTerm(privilegeTerm);
				    mobileMyLendDetialDDT.setPrivilegeRange(privilegeRange);
				    mobileMyLendDetialDDT.setPrivilegeType(privilegeType);
				    mobileMyLendDetialDDT.setLowAmount(lowAmount);
				    mobileMyLendDetialDDT.setRule(rule);
				    mobileMyLendDetialDDT.setRuleIntroduce(ruleIntroduce);
				    mobileMyLendDetialDDT.setJudeg(1);
				}
			    }
			}
		    }
		}

	    }

	    return mobileMyLendDetialDDT;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    /** 点点投已完成出借详情 */
    @Override
    public MobileMyLendDetialWCDDT FindByDDTWCDebitCreditOID(String memberOID, String debitCreditOID) {
	try {
	    MobileMyLendDetialWCDDT mobileMyLendDetialWCDDT = new MobileMyLendDetialWCDDT();
	    mobileMyLendDetialWCDDT = personalMyLendMapper.findDDTWCDetial(memberOID, debitCreditOID);
	    if (mobileMyLendDetialWCDDT != null) {
		// 出借时间截取
		Timestamp s = new Timestamp(mobileMyLendDetialWCDDT.getCjsjTime().getTime());
		String cjsj = s.toString().substring(0, 10);
		mobileMyLendDetialWCDDT.setCjsj(cjsj);
		// 计息时间截取
		Timestamp jx = new Timestamp(mobileMyLendDetialWCDDT.getStartDateTime().getTime());
		String jxsj = jx.toString().substring(0, 10);
		mobileMyLendDetialWCDDT.setJxsjTime(jxsj);
		// dqsj
		PayDebitCredit debitCredit = new PayDebitCredit();
		debitCredit = PAY_DebitCreditMapper.findByDebitCreditOID(mobileMyLendDetialWCDDT.getDOID());
		// dqsj时间截取
		Timestamp dq = new Timestamp(debitCredit.getUpdDateTime().getTime());
		String dqsj = dq.toString().substring(0, 10);
		mobileMyLendDetialWCDDT.setDqsjTime(dqsj);

		BigDecimal cjje = mobileMyLendDetialWCDDT.getCjje();
		BigDecimal yslx = mobileMyLendDetialWCDDT.getYslx();

		mobileMyLendDetialWCDDT.setYsbx(yslx.toString());

		mobileMyLendDetialWCDDT.setJxsjTime(dqsj);
		if (mobileMyLendDetialWCDDT.getTqOID() != null) {

		    WebP2pNormalBiddingRuning normal = WEBP2PNormalBiddingRuningMapper
			    .findByOID(mobileMyLendDetialWCDDT.getBiOID());
		    // 特权
		    List<PayPrivilegeProductMapping> tqTemp = payPrivilegeProductMappingMapper
			    .findByOID(normal.getProductRateInfoID());
		    if (tqTemp.size() > 0) {
			for (PayPrivilegeProductMapping product : tqTemp) {
			    String tqOID = mobileMyLendDetialWCDDT.getTqOID();
			    if (tqOID.equals(product.getPrivilegeOID())) {
				PayPrivilege tqInfo = payPrivilegeMapper.findByOID(tqOID);
				String privilegeName = tqInfo.getPrivilegeName();
				BigDecimal lowAmount = tqInfo.getLowAmount();
				BigDecimal privilegeRange = tqInfo.getPrivilegeRange();
				int privilegeTerm = tqInfo.getPrivilegeTerm();
				String privilegeType = tqInfo.getPrivilegeType();
				int rule = 0;
				String ruleIntroduce = "";
				if (tqInfo.getRule() != null) {
				    rule = (int) tqInfo.getRule();
				}
				if (StringUtil.isNotEmpty(tqInfo.getRuleIntroduce())) {
				    ruleIntroduce = tqInfo.getRuleIntroduce();
				}
				List<PayPrivilegeMemberMapping> pmm = payPrivilegeMemberMappingMapper
					.findByCondition(tqOID, memberOID);
				if (pmm.size() == 0) {
				    mobileMyLendDetialWCDDT.setJudeg(0);
				} else {
				    for (PayPrivilegeMemberMapping ite : pmm) {
					BigDecimal tqsy = new BigDecimal(0);
					Date enddate = DateUtils.getDate(ite.getEndDate().getTime());
					String jsDate = DateUtils.formatDate(enddate, "");
					Date startdate = DateUtils.getDate(ite.getStartDate().getTime());
					String ksDate = DateUtils.formatDate(enddate, "");
					tqsy = tqsy.add(mobileMyLendDetialWCDDT.getCjje());
					BigDecimal privilegeRange_new = privilegeRange.divide(new BigDecimal(100));
					// 特权收益
					tqsy = tqsy.multiply(privilegeRange_new)
						.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
						.multiply(new BigDecimal(privilegeTerm))
						.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					mobileMyLendDetialWCDDT.setTqsy(tqsy);
					mobileMyLendDetialWCDDT.setPrivilegeName(privilegeName);
					mobileMyLendDetialWCDDT.setPrivilegeTerm(privilegeTerm);
					mobileMyLendDetialWCDDT.setPrivilegeRange(privilegeRange);
					mobileMyLendDetialWCDDT.setPrivilegeType(privilegeType);
					mobileMyLendDetialWCDDT.setLowAmount(lowAmount);
					mobileMyLendDetialWCDDT.setRule(rule);
					mobileMyLendDetialWCDDT.setRuleIntroduce(ruleIntroduce);
					mobileMyLendDetialWCDDT.setJudeg(1);
				    }
				}
			    }
			}
		    }

		}

	    }
	    return mobileMyLendDetialWCDDT;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    /** 环环投已完成 */
    @Override
    public MobileMyLendDetialWCHHT FindByCashPoolWC(String payCashPoolOID, String memberOID) {
	try {
	    MobileMyLendDetialWCHHT mobileMyLendDetialWCHHT = new MobileMyLendDetialWCHHT();
	    mobileMyLendDetialWCHHT = personalMyLendMapper.findHHTWCDetial(payCashPoolOID, memberOID);
	    String businessOID = mobileMyLendDetialWCHHT.getBussinessOID();

	    // 出借时间截取
	    Timestamp s = new Timestamp(mobileMyLendDetialWCHHT.getCjsjTime().getTime());
	    String cjsj = s.toString().substring(0, 10);
	    mobileMyLendDetialWCHHT.setCjsj(cjsj);

	    int zqs = mobileMyLendDetialWCHHT.getZqs();

	    // 出借时间截取
	    Timestamp dq = new Timestamp(mobileMyLendDetialWCHHT.getDqsj().getTime());
	    String dqsj = dq.toString().substring(0, 10);
	    mobileMyLendDetialWCHHT.setDqsjTime(dqsj);
	    // 计息时间
	    Timestamp js = new Timestamp(mobileMyLendDetialWCHHT.getTimejssj().getTime());
	    String jssj = js.toString().substring(0, 10);
	    mobileMyLendDetialWCHHT.setJssj(jssj);
	    mobileMyLendDetialWCHHT.setJxsjTime(cjsj);
	    // mobileMyLendDetialWCHHT.setCjje(mobileMyLendDetialWCHHT.getCjje());

	    if (mobileMyLendDetialWCHHT.getTqOID() != null) {

		// 查询特权信息
		PayPrivilege tqInfo = payPrivilegeMapper
			.findByOID(/* hhtlendDTO.getTqOID() */"029c16d6f5d3f6e21609c7d64268f5ab");
		// 特权名字
		String privilegeName = tqInfo.getPrivilegeName();
		// 特权域(红包数额/加息百分位)
		BigDecimal privilegeRange = tqInfo.getPrivilegeRange();

		// 特权期限(天数)
		int privilegeTerm = tqInfo.getPrivilegeTerm();

		// 特殊券判别
		if (privilegeTerm == 0) {
		    WebP2pProductRateInfo product = productRateInfoMapper
			    .findByOID(mobileMyLendDetialWCHHT.getProductInfoOID());
		    privilegeTerm = (int) (product.getProductTerm() * 30);
		    // (int) ((double) product.get("productTerm") * 30);
		}

		// 计算特权收益
		BigDecimal tqsy = new BigDecimal(0);
		tqsy = tqsy.add(mobileMyLendDetialWCHHT.getCjje());
		tqsy = tqsy.multiply(privilegeRange).divide(new BigDecimal("36500"), 8, BigDecimal.ROUND_HALF_EVEN)
			.multiply(new BigDecimal(privilegeTerm)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		mobileMyLendDetialWCHHT.setPrivilegeName(privilegeName);

		mobileMyLendDetialWCHHT.setPrivilegeRange(privilegeRange);

		mobileMyLendDetialWCHHT.setPrivilegeTerm(privilegeTerm);
		mobileMyLendDetialWCHHT.setTqsy(tqsy);
		mobileMyLendDetialWCHHT.setJudeg(1);
		// hhtlendDTO.setRule(rule);
		// dto.put("ruleIntroduce", ruleIntroduce);
	    }

	    // 替换个人中心利率显示 terry
	    PayCashPool payCashPool = payCashPoolDao.findByOID(mobileMyLendDetialWCHHT.getCashPoolOID());
	    BigDecimal zero = new BigDecimal(0);
	    if (payCashPool.getJXother() != null && (payCashPool.getJXother().compareTo(zero) > 0)) {
		// 额外加息利率
		BigDecimal jx = payCashPool.getJXother();
		// 原产品预收本息
		BigDecimal ysbx = mobileMyLendDetialWCHHT.getYsbx();
		mobileMyLendDetialWCHHT.setJXother(jx);
		ysbx = ysbx.add(payCashPool.getJXinterest());
		mobileMyLendDetialWCHHT.setYsbx(ysbx);
	    } else {
		mobileMyLendDetialWCHHT.setJXother(new BigDecimal(0));
	    }

	    return mobileMyLendDetialWCHHT;
	} catch (Exception e) {
	    return null;
	}

    }

}
