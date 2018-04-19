package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.DetialDDT;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.DetialDDTService;

@Service("detialDDTService")
public class DetialDDTServiceImpl implements DetialDDTService {
    private static final Logger logger = LoggerFactory.getLogger(AuditInfoServiceImpl.class);

    @Autowired

    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Autowired
    private WebP2pBiddingExamineMapper biddingExamineMapper;

    @Autowired
    private PayCashPoolOperationMapper cashPoolOperationMapper;

    @Autowired
    private PayCustomerMapper customerMapper;

    @Override
    public DetialDDT findByCondition(String OID, String memberOID) {
	DetialDDT ddt = new DetialDDT();
	WebP2pNormalBiddingRuning normalBiddingRuning = new WebP2pNormalBiddingRuning();
	WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();
	List<PayCashPoolOperation> cashlist = new ArrayList<>();
	WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	PayCustomer customer = new PayCustomer();
	try {

	    if (memberOID != null) {
		customer = customerMapper.findByMemberOID(memberOID);
		ddt.setUseMoney(customer.getUseMoney());

	    }

	    // String OID = "";
	    normalBiddingRuning = normalBiddingRuningMapper.findByOID(OID);
	    productRateInfo = productRateInfoMapper.findByOID(normalBiddingRuning.getProductRateInfoID());
	    ddt.setOID(normalBiddingRuning.getOID());
	    ddt.setXmmc(normalBiddingRuning.getBiddingName());
	    // 防止返回前台数据为空页面出错增加初始值复
	    if (productRateInfo.getYearRate() != null) {
		ddt.setNhll(productRateInfo.getYearRate().setScale(2, BigDecimal.ROUND_HALF_UP));
	    } else {
		ddt.setNhll(new BigDecimal("0.00"));
	    }

	    Double productTerm = (Double) productRateInfo.getProductTerm();
	    int cjqx = productTerm.intValue();
	    ddt.setCjqx(cjqx);

	    // 还款方式
	    if (productRateInfo.getRepaymentType().equals("OPI")) {
		ddt.setHkfs("一次性还本付息");
	    } else if (productRateInfo.getRepaymentType().equals("EPEI")) {
		ddt.setHkfs("等本等息");
	    } else {
		ddt.setHkfs("按月返息到期还本");
	    }

	    biddingExamine = biddingExamineMapper.findByloanNumber(normalBiddingRuning.getLoanNumber());
	    if (biddingExamine != null) {
		ddt.setXueli(biddingExamine.getHighesteduback());
	    }

	    ddt.setXmze(normalBiddingRuning.getApplyAmount());
	    ddt.setQtje(normalBiddingRuning.getStartBidAmount());
	    ddt.setIsCWTB(normalBiddingRuning.getIsCWTB());

	    cashlist = cashPoolOperationMapper.findByBusinessOID(OID);
	    int r = cashlist.size();
	    ddt.setYtrs(r);

	    ddt.setProductStatus(Integer.valueOf(normalBiddingRuning.getProductStatus()));

	    BigDecimal kt = (normalBiddingRuning.getBiddingAmount().subtract(normalBiddingRuning.getHoldingAmount()));
	    ddt.setKtje(kt);

	    BigDecimal temp_new = new BigDecimal(
		    Double.parseDouble(String.valueOf(normalBiddingRuning.getHoldingAmount()))
			    / Double.parseDouble(String.valueOf(normalBiddingRuning.getBiddingAmount())) * 100);
	    int tzjd = temp_new.intValue();
	    ddt.setTzjd(tzjd);

	    return ddt;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;
	}

    }

}
