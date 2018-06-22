package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.personalCenter.MyLoanMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanApplyMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileLoanApplicationService;

/** 借款审核 */

@Service("mobileloanApplicationService")
public class MobileLoanApplicationServiceImpl implements MobileLoanApplicationService {
	private static final Logger logger = LoggerFactory.getLogger(MobileLoanApplicationServiceImpl.class);
	
	@Autowired
    private WebP2pLoanApplyMapper loanApplyMapper;
    @Autowired
    private  PayCustomerMapper customerMapper;
    @Autowired
    private MyLoanMapper myLoanMapper;

    @Override
    public String updAppInfo(WebP2pLoanApply loanApply) {
	PayCustomer customer = new PayCustomer();

	loanApply.setOID(StringUtil.getUUID());
	loanApply.setApplyTime(DateUtils.getDateFormatter());
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
    public Pagination<LoanApplyRecordDTO> findData(String memberOID, Integer page) {
    	try {
			Pagination<LoanApplyRecordDTO> pageController = new Pagination<>();
			if (page!= null) {
				pageController.setCurrentPage(page);
			}
			// 查询所有条数
			Integer totalNumber = loanApplyMapper.findCount(memberOID);
			pageController.setTotalNumber(totalNumber);
			Integer totalPage = pageController.getTotalPage();
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();
			
			List<LoanApplyRecordDTO> list = 
					myLoanMapper.findLoanApplyByMemberOID(memberOID, startIndex, pageSize);
			for(LoanApplyRecordDTO apply:list){
				apply.setAddDateTime(DateUtils.formartDate(apply.getAddDateTime(), "yyyy-MM-dd"));
			}
			pageController.setData(list);
			return pageController;
		} catch (Exception e) {
			logger.error("findData(String memberOID, Integer page)", e.getMessage());
		}
		return null;
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
    public LoanApplyRecordDTO findDataByOID(String memberOID, String OID) {
	try {
	    LoanApplyRecordDTO upLendRecord = new LoanApplyRecordDTO();

	    upLendRecord = myLoanMapper.findBYcondition(memberOID, OID);
	    return upLendRecord;
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

}
