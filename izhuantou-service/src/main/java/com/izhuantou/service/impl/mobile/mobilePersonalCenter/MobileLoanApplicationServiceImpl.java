package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.UpdLendRecord;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.personalCenter.MyLoanMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanApplyMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileLoanApplicationService;

/** 借款审核 */

@Service("mobileloanApplicationService")
public class MobileLoanApplicationServiceImpl implements MobileLoanApplicationService {
    @Autowired
    WebP2pLoanApplyMapper loanApplyMapper;
    @Autowired
    PayCustomerMapper customerMapper;
    @Autowired
    MyLoanMapper cjShenHeMapper;

    @Override
    public String updAppInfo(WebP2pLoanApply loanApply) {
	PayCustomer customer = new PayCustomer();

	loanApply.setOID(StringUtil.getUUID());
	loanApply.setApplyTime(new Timestamp(System.currentTimeMillis()));
	if (loanApply.getLoanYT().equals("IFD")) {
	    loanApply.setLoanYT("i房贷");
	}
	if (loanApply.getLoanYT().equals("IXD")) {
	    loanApply.setLoanYT("i薪贷");
	}
	if (loanApply.getLoanYT().equals("IYD")) {
	    loanApply.setLoanYT("i意贷");
	}
	if (loanApply.getLoanYT().equals("IBD")) {
	    loanApply.setLoanYT("i保单");
	}

	customer = customerMapper.findByMemberOID(loanApply.getMemberOID());
	if (customer.getEntrustRecharge() != null && "01".equals(customer.getEntrustRecharge())) {

	    loanApplyMapper.insertData(loanApply);

	} else {

	    return "您尚未进行委托交易授权";
	}
	return "1";

    }

    @Override
    public Pagination<WebP2pLoanApply> findData(String memberOID, Integer page) {
	try {
	    Pagination<WebP2pLoanApply> pageController = new Pagination<>();
	    List<WebP2pLoanApply> list = new ArrayList<>();
	    memberOID = "370518b20a5dca712804025f2e15867a";

	    /** 接受前台当前页参数 */
	    pageController.setCurrentPage(page);
	    // 查询所有条数
	    Integer totalNumber = loanApplyMapper.findCount(memberOID);
	    pageController.setTotalNumber(totalNumber);
	    Integer totalPage = pageController.getTotalPage();
	    if (pageController.getCurrentPage() != null) {
		pageController = new Pagination<WebP2pLoanApply>(pageController.getCurrentPage(), totalPage,
			totalNumber);
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		list = loanApplyMapper.findData(memberOID, StartPos, pageController.getPageSize());
		for (WebP2pLoanApply webp2p_LoanApply : list) {
		    Date date = webp2p_LoanApply.getApplyTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		    webp2p_LoanApply.setSj(sdf.format(date));
		}
	    }
	    pageController.setData(list);
	    return pageController;

	} catch (Exception e) {
	    return null;
	}

    }

    @Override
    public PayCustomer findInfo(String memberOID) {
	PayCustomer customer = new PayCustomer();
	try {
	    customer = customerMapper.findByMemberOID(memberOID);
	    return customer;
	} catch (Exception e) {
	    return null;
	}

    }

    @Override
    public String update(WebP2pLoanApply loanApply, String memberOID) {
	try {
	    List<WebP2pLoanApply> list = new ArrayList<>();
	    // loanApply.setLoanTerm("50");
	    // loanApply.setMemberOID("370518b20a5dca712804025f2e15867a");
	    if (loanApply.getLoanYT().equals("IFD")) {
		loanApply.setLoanYT("i房贷");
	    }
	    if (loanApply.getLoanYT().equals("IXD")) {
		loanApply.setLoanYT("i薪贷");
	    }
	    if (loanApply.getLoanYT().equals("IYD")) {
		loanApply.setLoanYT("i意贷");
	    }
	    if (loanApply.getLoanYT().equals("IBD")) {
		loanApply.setLoanYT("i保单");
	    }

	    /** 根据OID找到版本号 */
	    Integer version = loanApplyMapper.findByOID(loanApply.getOID()).getVersion();
	    loanApply.setVersion(version);
	    loanApplyMapper.update(loanApply);

	    return null;
	} catch (Exception e) {
	    return null;
	}

    }

    @Override
    public UpdLendRecord findDataByOID(String memberOID, String OID) {
	try {
	    UpdLendRecord upLendRecord = new UpdLendRecord();

	    upLendRecord = cjShenHeMapper.findBYcondition(memberOID, OID);
	    return upLendRecord;
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

}
