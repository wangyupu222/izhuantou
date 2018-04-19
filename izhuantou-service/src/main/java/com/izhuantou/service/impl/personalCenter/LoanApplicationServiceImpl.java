package com.izhuantou.service.impl.personalCenter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.izhuantou.service.api.personalCenter.LoanApplicationService;

/** 借款审核 */

@Service("loanApplicationService")
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class);
    @Autowired
    private WebP2pLoanApplyMapper loanApplyMapper;
    @Autowired
    private PayCustomerMapper customerMapper;
    @Autowired
    private MyLoanMapper cjShenHeMapper;

    @Override
    public String updAppInfo(WebP2pLoanApply loanApply) {
	try {
	    PayCustomer customer = new PayCustomer();

	    loanApply.setOID(StringUtil.getUUID());
	    loanApply.setApplyTime(new Timestamp(System.currentTimeMillis()));
	    loanApply.setAddDateTime(new Timestamp(System.currentTimeMillis()));

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
		// 未进行委托授权
		return "-1";
	    }
	    // 成功
	    return "1";
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findMessage()", e.getMessage());
	    return null;

	}

    }

    @Override
    public Pagination<WebP2pLoanApply> findData(String memberOID, Integer page) {
	try {
	    Pagination<WebP2pLoanApply> pageController = new Pagination<>();
	    List<WebP2pLoanApply> list = new ArrayList<>();
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
	    loanApply.setUpdDateTime(new Timestamp(System.currentTimeMillis()));
	    Integer ss = loanApplyMapper.findByOID(loanApply.getOID()).getVersion();// ceshi
	    /** 根据OID找到版本号 */
	    Integer version = loanApplyMapper.findByOID(loanApply.getOID()).getVersion();
	    loanApply.setVersion(version);
	    loanApplyMapper.update(loanApply);
	    return "1";
	} catch (Exception e) {
	    return "-1";
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
