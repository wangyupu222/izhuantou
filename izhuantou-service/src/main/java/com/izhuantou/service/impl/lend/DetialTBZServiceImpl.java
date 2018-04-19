package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.DetialTBZ;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.lend.GainNumXSMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.DetialTBZService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("detialTBZService")
public class DetialTBZServiceImpl extends BaseServiceImpl<PayCustomer> implements DetialTBZService {
    private static final Logger logger = LoggerFactory.getLogger(DetialTBZServiceImpl.class);
    @Autowired
    private PayCustomerMapper customerMapper;

    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRunningMapper;

    @Autowired
    private WebP2pProductRateInfoMapper webProductRateInfoMapper;

    @Autowired
    private WebP2pLoanProductRateInfoMapper webP2pLoanProductRateInfoMapper;

    @Autowired
    private GainNumXSMapper gainNumXSMapper;

    @Autowired
    private MemberMemberMapper userdao;

    @Override
    public DetialTBZ findByCondition(String OID, String memberOID) {
	DetialTBZ tbz = new DetialTBZ();
	PayCustomer customer = new PayCustomer();
	WebP2pNoviceBiddingRuning noviceBiddingRuning = new WebP2pNoviceBiddingRuning();
	WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();
	WebP2pLoanProductRateInfo loanProductRateInfo = new WebP2pLoanProductRateInfo();
	// WEBP2P_BiddingExamine BiddingExamine = new WEBP2P_BiddingExamine();
	MemberMember user = new MemberMember();
	try {
	    int isnew = 0;
	    boolean pd = true;
	    // String MemberOID = "370518b20a5dca712804025f2e15867a";
	    // String OID = "05ccb5f4f5d3f6e25b53949d47f823dd";
	    // String OID="a47779d80a5dca717008a251fff86bd7";
	    if (memberOID != null) {
		// 根据memberOID寻找人员信息
		customer = customerMapper.findByMemberOID(memberOID);
		tbz.setAvailablemoney(customer.getUseMoney());
	    }
	    /** 判断是否参加过别的投标 */
	    if (null != memberOID && !"".equals(memberOID)) {
		int irr = gainNumXSMapper.findByMemberOID(memberOID);
		user = userdao.findUserByOID(memberOID);
		if (user != null) {
		    if (null != user.getUserType()) {
			if ("6".equals(user.getUserType())) {
			    isnew = 0;
			    pd = false;
			}
		    }
		}
		if (pd) {
		    if (irr > 0) {
			isnew = irr;
		    }
		}
	    }

	    tbz.setIsnew(isnew);
	    BigDecimal ChuShi = new BigDecimal("0.00");
	    noviceBiddingRuning = noviceBiddingRunningMapper.findByOID(OID);
	    tbz.setXmmc(noviceBiddingRuning.getBiddingName());// 项目名称
	    productRateInfo = webProductRateInfoMapper.findByOID(noviceBiddingRuning.getProductRateInfoID());
	    loanProductRateInfo = webP2pLoanProductRateInfoMapper
		    .findByOID(noviceBiddingRuning.getLoanProductRateInfoID());
	    BigDecimal fjll = (productRateInfo.getYearRate()).subtract(loanProductRateInfo.getYearRate());// 附加利率
	    BigDecimal b1 = new BigDecimal(100);
	    fjll = fjll.multiply(b1);
	    // 防止返回前台数据为空页面出错增加初始值复
	    if (fjll != null) {
		tbz.setFjll(fjll.setScale(2, BigDecimal.ROUND_DOWN));
	    } else {
		tbz.setFjll(ChuShi);
	    }

	    BigDecimal nhll = (loanProductRateInfo.getYearRate()).multiply(b1);// 年化利率
	    if (nhll != null) {
		tbz.setNhll(nhll.setScale(2, BigDecimal.ROUND_DOWN));
	    } else {
		tbz.setNhll(ChuShi);
	    }

	    BigDecimal nhlltotal = nhll.add(fjll);// 总年化利率
	    if (nhlltotal != null) {
		tbz.setNhlltotal(nhlltotal.setScale(2, BigDecimal.ROUND_DOWN));
	    } else {
		tbz.setNhlltotal(ChuShi);
	    }

	    BigDecimal temp_new = new BigDecimal(
		    (Double.parseDouble(String.valueOf(noviceBiddingRuning.getHoldingAmount())))
			    / Double.parseDouble(String.valueOf(noviceBiddingRuning.getBiddingAmount())) * 100);
	    int tzjd = temp_new.intValue();
	    tbz.setTzjd(tzjd);// 投资进度
	    tbz.setZbsl(noviceBiddingRuning.getBiddingAmount());// 标的总额
	    tbz.setSykt(noviceBiddingRuning.getBiddingAmount().subtract(noviceBiddingRuning.getHoldingAmount()));// 剩余可投

	    tbz.setProductStatus(noviceBiddingRuning.getProductStatus());// 产品状态
	    tbz.setBiddingType(noviceBiddingRuning.getBiddingType());
	    tbz.setDebitCreditOID(noviceBiddingRuning.getDebitCreditOID());
	    if (memberOID != null) {

		customer = customerMapper.findByMemberOID(memberOID);
		// 可用余额
		tbz.setUseMoney(customer.getUseMoney());
	    }
	    // 还款方式
	    if (productRateInfo.getRepaymentType().equals("OPI")) {
		tbz.setHkfs("一次性还本付息");
	    } else if (productRateInfo.getRepaymentType().equals("EPEI")) {
		tbz.setHkfs("等本等息");
	    } else {
		tbz.setHkfs("按月返息到期还本");
	    }

	    return tbz;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;
	}

    }

}
