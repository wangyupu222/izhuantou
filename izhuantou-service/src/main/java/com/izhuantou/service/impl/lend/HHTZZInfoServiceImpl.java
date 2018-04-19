package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.HHTZZInfo;
import com.izhuantou.damain.lend.PayTerm;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.dao.lend.HHTTagComponentMapper;
import com.izhuantou.dao.lend.PayTermMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.service.api.lend.HHTZZInfoService;

/**
 * HHT债转信息
 * 
 * @author Administrator
 */
@Service("hhtZZInfoService")
public class HHTZZInfoServiceImpl implements HHTZZInfoService {
    private static final Logger logger = LoggerFactory.getLogger(LendRecordServiceImpl.class);
    @Autowired
    private MemberMemberMapper user;
    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
    @Autowired
    private PayTransferReturnMapper transferReturnMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
    @Autowired
    private PayRepayPlanMapper repayPlanMapper;
    @Autowired
    private HHTTagComponentMapper hhtTagComponentMapper;
    @Autowired
    private PayTermMapper payTermMapper;

    @Override
    public HHTZZInfo findByOID(String OID) {
	HHTZZInfo hhtZZInfo = new HHTZZInfo();
	WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();
	List<PayTransferReturn> transferReturn = new ArrayList<>();
	MemberMember member = new MemberMember();
	WebP2pLoanProductRateInfo loanProductRateInfo;
	List<PayRepayPlan> repayPlan = new ArrayList<>();
	try {
	    // String OID = "3b1f795b0a5dca71208b9f38be742f9f";
	    // String OID = "3ccb735b0a5dca713a91e5c855beac54";
	    // String OID = "24294fa50a5dca715837e35805d4d19f";
	    // 24294fa50a5dca715837e35805d4d19f
	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);
	    transferReturn = transferReturnMapper.findByBusinenssOID(OID);
	    BigDecimal zzbj = new BigDecimal(0);

	    for (PayTransferReturn pay_TransferReturn : transferReturn) {
		if (pay_TransferReturn.getInMemberOID().equals(pay_TransferReturn.getOutMemberOID())) {
		    zzbj = zzbj.add((BigDecimal) pay_TransferReturn.getPrincipalMoney());
		    break;
		} else {
		    if (pay_TransferReturn.getInMemberOID().toString()
			    .equals(packageBiddingMainContentRuning.getMemberOID().toString())) {
			zzbj = zzbj.add((BigDecimal) pay_TransferReturn.getPrincipalMoney());
		    }
		}
	    }

	    BigDecimal bdbj = (BigDecimal) packageBiddingMainContentRuning.getBiddingAmount();
	    member = user.findUserByOID(packageBiddingMainContentRuning.getMemberOID());

	    hhtZZInfo.setZzbh(packageBiddingMainContentRuning.getDebtLoanNumber());// 债转编号
	    hhtZZInfo.setZzr(getFullNameStr(member.getNameCN()));// 转让人
	    hhtZZInfo.setZrje(packageBiddingMainContentRuning.getBiddingAmount());// 转让金额
	    hhtZZInfo.setDsbj(zzbj);// 代收本金

	    hhtZZInfo.setDqdslx(bdbj.subtract(zzbj));// 当前代收利息
	    hhtZZInfo.setJkze(packageBiddingMainContentRuning.getApplyAmount());

	    BigDecimal b1 = new BigDecimal(100);

	    loanProductRateInfo = loanProductRateInfoMapper
		    .findByOID(packageBiddingMainContentRuning.getLoanProductRateInfoID());

	    BigDecimal nhll = (loanProductRateInfo.getYearRate().multiply(b1));// 年化利率
	    nhll = nhll.setScale(2, BigDecimal.ROUND_HALF_UP);

	    repayPlan = repayPlanMapper.findByBusinessOID(OID);

	    String hkzt = "正常还款";
	    Date date = new Date();

	    for (PayRepayPlan pay_RepayPlan : repayPlan) {
		Date dateTime = pay_RepayPlan.getRepayDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if ("finish".equals(pay_RepayPlan.getState())) {
		    // yhqs++;
		} else if ("plan".equals(pay_RepayPlan.getState())) {
		    if (compare_date2(sdf.format(date), sdf.format(dateTime)) > 0) {
			hkzt = "逾期";
		    }
		}
	    }
	    if (loanProductRateInfo.getRepaymentType().equals("OPI")) {
		hhtZZInfo.setHkfs("一次性还本付息");
	    } else if (loanProductRateInfo.getRepaymentType().equals("EPEI")) {
		hhtZZInfo.setHkfs("等本等息");
	    } else {
		hhtZZInfo.setHkfs("按月返息到期还本");
	    }

	    hhtZZInfo.setHkzt(hkzt);
	    hhtZZInfo.setNhll(nhll);
	    hhtZZInfo.setYbnh(nhll);

	    hhtZZInfo.setDebitCreditOID(packageBiddingMainContentRuning.getDebitCreditOID());

	    PayTerm payterm = new PayTerm();
	    payterm = payTermMapper.findByBusinessOID(OID);
	    if (payterm != null) {
		hhtZZInfo.setAllNum(payterm.getAllNum());
		hhtZZInfo.setYhnum(payterm.getYhnum());
	    }

	    return hhtZZInfo;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByOID()", e.getMessage());
	    return null;
	}

    }

    /** 以租代购 */
    public HHTZZInfo findYZDG(String OID) {
	HHTZZInfo hhtZZInfo = new HHTZZInfo();
	WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();
	List<PayTransferReturn> transferReturn = new ArrayList<>();
	MemberMember member = new MemberMember();
	WebP2pLoanProductRateInfo loanProductRateInfo;
	List<PayRepayPlan> repayPlan = new ArrayList<>();
	try {

	    // String OID = "3b1f795b0a5dca71208b9f38be742f9f";
	    // String OID = "3ccb735b0a5dca713a91e5c855beac54";
	    // String OID = "24294fa50a5dca715837e35805d4d19f";
	    // 24294fa50a5dca715837e35805d4d19f
	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);
	    transferReturn = transferReturnMapper.findByBusinenssOID(OID);
	    BigDecimal zzbj = new BigDecimal(0);

	    for (PayTransferReturn pay_TransferReturn : transferReturn) {
		if (pay_TransferReturn.getInMemberOID().equals(pay_TransferReturn.getOutMemberOID())) {
		    zzbj = zzbj.add((BigDecimal) pay_TransferReturn.getPrincipalMoney());
		    break;
		} else {
		    if (pay_TransferReturn.getInMemberOID().toString()
			    .equals(packageBiddingMainContentRuning.getMemberOID().toString())) {
			zzbj = zzbj.add((BigDecimal) pay_TransferReturn.getPrincipalMoney());
		    }
		}
	    }

	    BigDecimal bdbj = (BigDecimal) packageBiddingMainContentRuning.getBiddingAmount();
	    member = user.findUserByOID(packageBiddingMainContentRuning.getMemberOID());

	    hhtZZInfo.setZzbh(packageBiddingMainContentRuning.getDebtLoanNumber());// 债转编号
	    hhtZZInfo.setZzr(getFullNameStr(member.getNameCN()));// 转让人
	    hhtZZInfo.setZrje(packageBiddingMainContentRuning.getBiddingAmount());// 转让金额
	    hhtZZInfo.setDsbj(zzbj);// 代收本金

	    hhtZZInfo.setDqdslx(bdbj.subtract(zzbj));// 当前代收利息

	    BigDecimal b1 = new BigDecimal(100);

	    loanProductRateInfo = loanProductRateInfoMapper
		    .findByOID(packageBiddingMainContentRuning.getLoanProductRateInfoID());

	    BigDecimal nhll = (loanProductRateInfo.getYearRate().multiply(b1));// 年化利率
	    nhll = nhll.setScale(2, BigDecimal.ROUND_HALF_UP);

	    repayPlan = repayPlanMapper.findByBusinessOID(OID);

	    String hkzt = "正常还款";
	    Date date = new Date();

	    for (PayRepayPlan pay_RepayPlan : repayPlan) {
		Date dateTime = pay_RepayPlan.getRepayDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if ("finish".equals(pay_RepayPlan.getState())) {
		    // yhqs++;
		} else if ("plan".equals(pay_RepayPlan.getState())) {
		    if (compare_date2(sdf.format(date), sdf.format(dateTime)) > 0) {
			hkzt = "逾期";
		    }
		}
	    }
	    if (loanProductRateInfo.getRepaymentType().equals("OPI")) {
		hhtZZInfo.setHkfs("一次性还本付息");
	    } else if (loanProductRateInfo.getRepaymentType().equals("EPEI")) {
		hhtZZInfo.setHkfs("等本等息");
	    } else {
		hhtZZInfo.setHkfs("按月返息到期还本");
	    }

	    hhtZZInfo.setHkzt(hkzt);
	    hhtZZInfo.setNhll(nhll);
	    hhtZZInfo.setYbnh(nhll);
	    // hhtZZInfo.setDebitCreditOID(hhtTagComponentMapper.findByHHTOID(OID));
	    hhtZZInfo.setDebitCreditOID(packageBiddingMainContentRuning.getDebitCreditOID());
	    return hhtZZInfo;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByOID()", e.getMessage());
	    return null;
	}
    }

    // 姓名隐藏
    public static String getFullNameStr(String full_name) {
	if (full_name == null)
	    return " ";
	String replace_string = "*";
	for (int i = 1; i < full_name.length() - 1; i++) {

	    replace_string = replace_string + "*";

	}
	full_name = full_name.substring(0, 1) + replace_string;
	return full_name;
    }

    // 逾期还款判断
    public static int compare_date2(String DATE1, String DATE2) {

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	try {
	    Date dt1 = df.parse(DATE1);
	    Date dt2 = df.parse(DATE2);
	    if (dt1.getTime() > dt2.getTime()) {
		System.out.println("dt1 在dt2前");
		return 1;
	    } else if (dt1.getTime() < dt2.getTime()) {
		System.out.println("dt1在dt2后");
		return -1;
	    } else {
		return 0;
	    }
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
	return 0;
    }

}
