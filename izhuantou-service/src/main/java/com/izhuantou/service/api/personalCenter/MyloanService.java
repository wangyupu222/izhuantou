package com.izhuantou.service.api.personalCenter;

import java.util.List;
import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.vo.MyLoanApplyDTO;
import com.izhuantou.damain.vo.RepayResultDTO;

public interface MyloanService {
	/**
	 * 保存借款申请
	 * 
	 * @param apply
	 * @return
	 */
	public String saveloanApply(MyLoanApplyDTO apply);
	/**
	 * 查看借款审核记录
	 * @param memberOID
	 * @param page
	 * @return
	 */
	public Pagination<LoanApplyRecordDTO> findLoanApplyByMemberOID(String memberOID, Integer page);
	/**
	 * 用户信息
	 * @param memberOID
	 * @return
	 */
	public PayCustomer findInfo(String memberOID);

	/**
	 * 更新借款的申请
	 * @param loanApply
	 * @return
	 */
	public String updateLoanApply(MyLoanApplyDTO loanApply);
	/**
	 * 
	 * @param memberOID
	 * @param OID
	 * @return
	 */
	public LoanApplyRecordDTO findDataByOID(String memberOID, String OID);
	
	/**
	 * 我的借款记录
	 * @param memberOID
	 * @return
	 */
    public Map<String,Object> myLoanRecord(String memberOID);
	
	
    /**
	 * 查看用户待还款
	 * @param memberOID
	 * @return
	 */
	List<RepayResultDTO> findMyRepayment(String memberOID);
	
	
	
	
	
	
}
