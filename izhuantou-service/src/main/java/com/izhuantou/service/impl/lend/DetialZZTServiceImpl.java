package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.DetailZZTInfo;
import com.izhuantou.damain.lend.DetialZZT;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferBiddingPool;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.lend.DetailZZTInfoMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pDebtTransferBiddingPoolMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.DetialZZTService;

@Service("detialZZTService")
public class DetialZZTServiceImpl implements DetialZZTService {
    private static final Logger logger = LoggerFactory.getLogger(AuditInfoServiceImpl.class);

    @Autowired
    private WebP2pDebtTransferBiddingPoolMapper debtTransferBiddingPoolMapper;

    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Autowired
    private PayDebitCreditMapper debitCreditMapper;

    @Autowired
    private DetailZZTInfoMapper detailZZTInfoMapper;

    @Autowired
    private MemberMemberMapper user;

    @Autowired
    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

    @Autowired
    private PayCustomerMapper customerMapper;

    @Override
    public DetialZZT findByCondition(String OID, String memberOID) {

	DetialZZT zzt = new DetialZZT();
	WebP2pDebtTransferBiddingPool debtTransferBiddingPool = new WebP2pDebtTransferBiddingPool();
	WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();
	List<PayDebitCredit> list = new ArrayList<>();
	PayCustomer customer = new PayCustomer();
	DetailZZTInfo detailZZTInfo = new DetailZZTInfo();
	WebP2pNormalBiddingRuning normalBiddingRuning = new WebP2pNormalBiddingRuning();
	try {

	    if (memberOID != null) {
		// 根据memberOID寻找人员信息
		customer = customerMapper.findByMemberOID(memberOID);
		zzt.setAvailablemoney(customer.getUseMoney());
	    }
	    debtTransferBiddingPool = debtTransferBiddingPoolMapper.findByOID(OID);
	    productRateInfo = productRateInfoMapper.findByOID(debtTransferBiddingPool.getProductRateInfoID());
	    list = debitCreditMapper.findByCondition(OID);
	    // 统计已投人数
	    int r = list.size();
	    // 防止返回前台数据为空页面出错增加初始值复
	    if (productRateInfo.getYearRate() != null) {
		zzt.setNhll(productRateInfo.getYearRate().setScale(2, BigDecimal.ROUND_HALF_UP));
	    } else {
		zzt.setNhll(new BigDecimal("0.00"));
	    }

	    zzt.setHkfs(productRateInfo.getRepaymentType());

	    zzt.setYtrs(r);// 已投人数

	    Double productTerm = (Double) productRateInfo.getProductTerm();
	    int cjqx = productTerm.intValue();
	    BigDecimal yb = new BigDecimal(100);
	    BigDecimal tzjd = (((BigDecimal) debtTransferBiddingPool.getHoldingAmount()).multiply(yb)
		    .divide((BigDecimal) debtTransferBiddingPool.getBiddingAmount(), 0, BigDecimal.ROUND_HALF_UP));
	    zzt.setTzjd(tzjd);// 投资进度

	    BigDecimal kt = ((BigDecimal) debtTransferBiddingPool.getBiddingAmount())
		    .subtract((BigDecimal) debtTransferBiddingPool.getHoldingAmount());
	    zzt.setKtje(kt);// 可投金额

	    String jkrOID = debtTransferBiddingPool.getMemberOID();

	    detailZZTInfo = detailZZTInfoMapper.findByMainOID(jkrOID);

	    zzt.setZbsl(detailZZTInfo.getBdjg());// 转让价格
	    String zrName = user.findUserByOID(detailZZTInfo.getMemberOID()).getNameCN();
	    zzt.setZrName(getFullNameStr(zrName));// 转让人
	    zzt.setYuanMoney(detailZZTInfo.getCjje());// 原标金额
	    zzt.setDsbj(detailZZTInfo.getYsbj());// 代收本金
	    zzt.setYbnh(detailZZTInfo.getNhll());// 原标年化

	    zzt.setDqds_interest(detailZZTInfo.getYslx());// 当前代收利息
	    zzt.setBiOID(detailZZTInfo.getBusinessOID());
	    normalBiddingRuning = normalBiddingRuningMapper.findByOID(detailZZTInfo.getBusinessOID());
	    int a = (int) normalBiddingRuning.getProductType();
	    zzt.setProductType(a);// 产品状态
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal hnll = ((BigDecimal) zzt.getNhll()).multiply(b1);
	    zzt.setOID(debtTransferBiddingPool.getOID());
	    zzt.setXmmc(debtTransferBiddingPool.getBiddingName());
	    zzt.setNhll(hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    zzt.setCjqx(cjqx);
	    // 还款方式
	    if (productRateInfo.getRepaymentType().equals("OPI")) {
		zzt.setHkfs("一次性还本付息");
	    } else if (productRateInfo.getRepaymentType().equals("EPEI")) {
		zzt.setHkfs("等本等息");
	    } else {
		zzt.setHkfs("按月返息到期还本");
	    }
	    zzt.setXmze(debtTransferBiddingPool.getBiddingAmount());// 项目金额
	    zzt.setProductStatus(debtTransferBiddingPool.getProductStatus());
	    return zzt;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
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

}
