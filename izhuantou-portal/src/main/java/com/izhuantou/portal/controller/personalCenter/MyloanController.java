package com.izhuantou.portal.controller.personalCenter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.vo.MyLoanApplyDTO;
import com.izhuantou.damain.vo.RepayResultDTO;
import com.izhuantou.service.api.personalCenter.MyloanService;

@Controller
@RequestMapping(value = "loan", produces = "application/json;charset=UTF-8")
public class MyloanController {

	@Autowired
	private MyloanService myloanService;

	@RequestMapping(value = "/successLoanApplication")
	public String successLoanApplication() {

		return "successLoanApplication";
	}

	@RequestMapping(value = "/LoanApplication")
	public String LoanApplication() {

		return "LoanApplication";
	}

	@RequestMapping(value = "/LoanApplactiomUpdateErro")
	public String LoanApplactiomUpdateErro() {

		return "LoanApplactiomUpdateErro";
	}

	@RequestMapping(value = "/LoanApplication_iframe")
	public String LoanApplication_iframe() {

		return "LoanApplication_iframe";
	}

	@RequestMapping(value = "/BorrowingRecords")
	public String BorrowingRecords() {

		return "BorrowingRecords";
	}

	// 还款
	@RequestMapping(value = "/Repayment")
	public String Repayment() {
		return "Repayment";
	}

	// 手绘签章
	@RequestMapping(value = "/E_signatures")
	public String E_signatures() {
		return "E_signatures";
	}
	
	// 借款审核页面用户信息
	@RequestMapping(value = "/findInfo")
	@ResponseBody
	public OpResult findInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			PayCustomer customer = new PayCustomer();
			customer = myloanService.findInfo(memberOID);
			if (customer != null) {
				return OpResult.getSuccessResult(customer);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	// 借款审核提交
	@RequestMapping(value = "/loanAudit")
	public String loanAudit(HttpServletRequest request, RedirectAttributes view, MyLoanApplyDTO loanApply) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		String sys_msg = (String) session.getAttribute("smsLoanApplication");
		String mesYzm = loanApply.getMesYzm();
		loanApply.setMemberOID(memberOID);
		if (StringUtil.isNotEmpty(mesYzm) && !mesYzm.toLowerCase().equals(sys_msg)) {// 测试关闭短信
			String s = myloanService.saveloanApply(loanApply);
			if (StringUtil.isNotEmpty(s) && s.equals("-1")) {
				view.addFlashAttribute("msg", "您尚未进行委托授权");
			} else if (StringUtil.isNotEmpty(s) && s.equals("1")) {
				return "redirect:/portal/loan/successLoanApplication";
			}
		} else {
			view.addFlashAttribute("msg", "短信验证错误");
		}
		return "redirect:/portal/loan/LoanApplication?type=1";
	}

	// 借款审核记录
	@RequestMapping(value = "/lendprocess/{page}")
	@ResponseBody
	public OpResult lendprocess(HttpServletRequest request, @PathVariable(value = "page") String page) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Pagination<LoanApplyRecordDTO> list = myloanService.findLoanApplyByMemberOID(memberOID,
					Integer.valueOf(page));
			if (list != null) {
				return OpResult.getSuccessResult(list);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}
	}

	// 借款审核记录中的修改中的内容
	@RequestMapping(value = "/findoidInfo")
	@ResponseBody
	public OpResult findoidInfo(@RequestParam(value = "OID", defaultValue = "") String OID,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			LoanApplyRecordDTO upLendRecord = new LoanApplyRecordDTO();
			upLendRecord = myloanService.findDataByOID(memberOID, OID);
			if (upLendRecord != null) {
				return OpResult.getSuccessResult(upLendRecord);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}
	}

	// 借款审核记录中修改
	@RequestMapping(value = "/updloanAudit")
	public String loanAuditupd(HttpServletRequest request, RedirectAttributes view, MyLoanApplyDTO loanApply) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		String sys_msg = (String) session.getAttribute("smsLoanApplication");
		String mesYzm = loanApply.getMesYzm();
		loanApply.setMemberOID(memberOID);
		if (StringUtil.isNotEmpty(mesYzm) && !mesYzm.toLowerCase().equals(sys_msg)) {// 测试用

			String s = myloanService.updateLoanApply(loanApply);
			if ("1".equals(s)) {
				return "redirect:/portal/loan/successLoanApplication";
			} else {
				return "redirect:/portal/loan/LoanApplication?type=1";
			}
		} else {
			view.addFlashAttribute("msg", "短信验证错误");
			return "redirect:/portal/loan/LoanApplication_iframe";
		}

	}

	/**
	 * 个人中心借款记录
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/myLoanRecord")
	@ResponseBody
	public OpResult myLendRecord(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Map<String, Object> map = myloanService.myLoanRecord(memberOID);
			return OpResult.getSuccessResult(map);
		} else {
			return null;
		}
	}

	/**
	 * 还款列表
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/repaymentBack")
	@ResponseBody
	public OpResult repayMentBack(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			List<RepayResultDTO> repay = myloanService.findMyRepayment(memberOID);
			return OpResult.getSuccessResult(repay);
		} else {
			return OpResult.getFailedResult("查询失败");
		}

	}

}
