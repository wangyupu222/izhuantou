package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;

public interface MobileLoanApplicationService {

    public String updAppInfo(WebP2pLoanApply loanApply);

    public Pagination<LoanApplyRecordDTO> findData(String memberOID, Integer page);

    public PayCustomer findInfo(String memberOID);

    // 修改
    public String update(WebP2pLoanApply loanApply, String memberOID);

    // 出借记录修改展示
    public LoanApplyRecordDTO findDataByOID(String memberOID, String OID);

}
