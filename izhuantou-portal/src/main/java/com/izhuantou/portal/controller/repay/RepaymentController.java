package com.izhuantou.portal.controller.repay;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.fund.rpc.api.repay.RepayProcess;

@Controller
@RequestMapping("repay")
public class RepaymentController {
 
	@Autowired
	private RepayProcess repayProcess;
	// 还款成功页面
	@RequestMapping(value = "/success_repayment")
	public String success_repayment() {
		return "success_repayment";
	}
	@RequestMapping("/normalRepay")
	public String normalRepay(String planOID){
		if(StringUtil.isNotEmpty(planOID)){
			String row=repayProcess.normalRepay(planOID, new BigDecimal("0")); 
			if(StringUtil.isNotEmpty(row)){
				return "redirect:/portal/repay/success_repayment";
			}
		}
		return "redirect:/portal/loan/Repayment";
	}

	@RequestMapping("/beforeRepay") 
	public String beforeRepay(String businessOID){
		String row=repayProcess.beforeRepay(businessOID);
		if(StringUtil.isNotEmpty(row)){
			return "redirect:/portal/repay/success_repayment";
		}
		return "redirect:/portal/loan/Repayment";
	} 
	
	@RequestMapping("/afterRepay")
	public String afterRepay(String planOID){
		String row=repayProcess.afterRepay(planOID);
		if(StringUtil.isNotEmpty(row)){
			return "redirect:/portal/repay/success_repayment";
		}
		return "redirect:/portal/loan/Repayment";
	}
	
}
