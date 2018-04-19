package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.TransferMark;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.service.api.lend.TransferMarkService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 转让标信息
 * 
 * @author jasen
 *
 */
@Service("transferMarkService")
public class TransferMarkServiceImpl extends BaseServiceImpl<WebP2pBiddingExamine> implements TransferMarkService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayZZTServiceImpl.class);
    @Autowired
    private PayCustomerMapper customerMapper;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRunningMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper WEBP2P_LoanProductRateInfoMapper;
    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private PayDebitCreditMapper debitCreditMapper;

    @Override
    public TransferMark findByCondition(String OID, String memberOID) {
	try {
	    TransferMark mark = new TransferMark();
	    PayCustomer customer = new PayCustomer();
	    WebP2pLoanProductRateInfo loanProductRateInfo = new WebP2pLoanProductRateInfo();

	    WebP2pNoviceBiddingRuning noviceBiddingRuning = new WebP2pNoviceBiddingRuning();
	    WebP2pNoviceBiddingRuning noviceBiddingRuningByCondition = new WebP2pNoviceBiddingRuning();
	    PayDebitCredit debitCredit = new PayDebitCredit();

	    noviceBiddingRuning = noviceBiddingRunningMapper.findByOID(OID);
	    if (memberOID != null) {
		customer = customerMapper.findByMemberOID(memberOID);
	    }

	    loanProductRateInfo = WEBP2P_LoanProductRateInfoMapper
		    .findByOID(noviceBiddingRuning.getLoanProductRateInfoID());

	    BigDecimal b1 = new BigDecimal(100);
	    mark.setYbjkll((loanProductRateInfo.getYearRate()).multiply(b1));

	    MemberMember memberMember = new MemberMember();
	    memberMember = userDao.findUserByOID(noviceBiddingRuning.getMemberOID());
	    mark.setZrr(getFullNameStr(memberMember.getNameCN()));
	    mark.setYbze(noviceBiddingRuning.getLoanAmount());
	    mark.setDebitCreditOID(noviceBiddingRuning.getDebitCreditOID());

	    debitCredit = debitCreditMapper.findByOID(OID);
	    BigDecimal dslx = (noviceBiddingRuning.getBiddingAmount().subtract(debitCredit.getPrincipalMoney()));
	    dslx.toString().substring(0, dslx.toString().indexOf(".") + 3);
	    mark.setDqdslx(dslx);

	    mark.setDsbj(debitCredit.getPrincipalMoney().toString().substring(0,
		    debitCredit.getPrincipalMoney().toString().indexOf(".") + 3));

	    noviceBiddingRuningByCondition = noviceBiddingRunningMapper
		    .findByCondition(noviceBiddingRuning.getLoanNumber());

	    mark.setOID(noviceBiddingRuningByCondition.getOID());
	    return mark;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
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
