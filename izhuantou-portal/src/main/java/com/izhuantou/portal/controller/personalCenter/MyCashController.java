package com.izhuantou.portal.controller.personalCenter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ChangeCard2ReqData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.Wy500012ReqData;
import com.fuiou.service.FuiouService;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.common.FuiouServiceMy;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.constant.Constant;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.FuyuReturnDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.third.rpc.api.ControlPayService;

/***
 * 个人中心
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "cash", produces = "application/json;charset=UTF-8")
public class MyCashController {
	private static final Logger logger = LoggerFactory.getLogger(MyCashController.class);
	@Autowired
	private MyCashService myCashService;
	@Autowired
	private ControlPayService controlPayService;

	/**
	 * 富友回调的统一页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Operation_success")
	public String Operation_success() {

		return "Operation_success";
	}

	/**
	 * 资金流水页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CapitalDetailed")
	public String CapitalDetailed() {

		return "CapitalDetailed";
	}

	/**
	 * 银行卡信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/MyCard")
	public String MyCard() {

		return "MyCard";
	}

	/**
	 * 充值页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Recharge_new")
	public String Recharge_new() {

		return "Recharge_new";
	}

	/**
	 * 充值页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Recharge_new2")
	public String Rechargenew2() {

		return "Recharge_new2";
	}

	/**
	 * 提现页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/Withdrawals")
	public String Withdrawals() {

		return "Withdrawals";
	}

	/**
	 * 注册富友的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addbankcard")
	public String addbankcard() {

		return "addbankcard";
	}

	@RequestMapping(value = "/checkValidateCode")
	@ResponseBody
	public OpResult checkValidateCode(HttpServletRequest request, String yzm) {
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute(Constant.VALIDATE_CODE);
		if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {
			return OpResult.getSuccessResult("1");
		}
		return OpResult.getSuccessResult("0");
	}

	/**
	 * 资金流水列表查询
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/cashDetial/{page}", method = RequestMethod.GET)
	@ResponseBody
	public OpResult FindResultcash(@PathVariable(value = "page") String page,
			@RequestParam(value = "strDate", defaultValue = "") String strDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate, HttpServletRequest request) {
		Long startTime = new Date().getTime();
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		Pagination<PayCustomerBusiness> list = this.myCashService.findMessage(Integer.parseInt(page), memberOID,
				strDate, endDate);
		if (list != null) {
			Long time = new Date().getTime() - startTime;
			logger.info("资金流水列表查询花费时间{}",time);
			return OpResult.getSuccessResult(list);
		} else {
			return OpResult.getFailedResult("查询失败");
		}

	}

	/**
	 * 查询银行卡更改信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/myCash")
	@ResponseBody
	public OpResult myCash(HttpServletRequest request, Integer currentPage) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Map<String, Object> map = myCashService.findMyBanckCard(memberOID, currentPage);
			if (map != null) {
				return OpResult.getSuccessResult(map);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID不能为空");
		}
	}

	@RequestMapping(value = "/myCashmessage")
	@ResponseBody
	public OpResult myCashMessage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Map<String, Object> map = myCashService.findMyBanckMessage(memberOID);
			if (map != null) {
				return OpResult.getSuccessResult(map);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID不能为空");
		}
	}

	/**
	 * 充值记录和提现记录信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/recharge")
	@ResponseBody
	public OpResult recharge(HttpServletRequest request, Integer currentpage, String state) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(state)) {
			Map<String, Object> map = myCashService.findRechargeRecord(memberOID, state, currentpage);
			if (map != null) {
				return OpResult.getSuccessResult(map);
			}
		}
		return OpResult.getFailedResult("查询记录失败");
	}

	/**
	 * 充值和提现页面数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cardmsg")
	@ResponseBody
	public OpResult cardmsg(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Map<String, Object> map = myCashService.findMyCardmessage(memberOID);
			if (map != null) {
				return OpResult.getSuccessResult(map);
			}
		}
		return OpResult.getFailedResult("查询记录失败");
	}

	/**
	 * 注册富友
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertCustomer")
	public String insertCustomer(HttpServletRequest request, RedirectAttributes view, CustomerDTO customer) {
		HttpSession session = request.getSession();
		// 获取session中的手机号验证码
		String checksms = (String) session.getAttribute(Constant.SMS_VALIDATE_CODE);
		String memberOID = (String) session.getAttribute("memberOID");
		customer.setMemberOID(memberOID);
		// 表单的短信验证码
		String sms = customer.getMsm();
		String rows = null;
		if (StringUtil.isNotEmpty(sms) && !sms.equals(checksms)) {// 测试>>>>>>>>>>>
			session.removeAttribute(Constant.VALIDATE_CODE);
			session.removeAttribute(Constant.SMS_VALIDATE_CODE);
			rows = controlPayService.createCustomerAccount(customer);
			if ("1".equals(rows)) {
				return "redirect:/portal/cash/MyCard";
			}
		}
		view.addFlashAttribute(Constant.MSG, rows);
		return "redirect:/portal/cash/addbankcard";
	}

	/**
	 * 用户充值的确认提交
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/WYrecharge")
	public String rechargeWY(HttpServletRequest request, HttpServletResponse response, RedirectAttributes view,
			RechargeDTO recharge) {
		try {
			HttpSession session = request.getSession();
			// 获取session中的手机号验证码
			String check = (String) session.getAttribute(Constant.VALIDATE_CODE);
			String yzm = recharge.getYzm();
			String type = recharge.getType();
			String memberOID = (String) session.getAttribute("memberOID");
			recharge.setMemberOID(memberOID);
			if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {// 测试
				if (StringUtil.isNotEmpty(type) && "1".equals(type)) {
					Wy500012ReqData data = controlPayService.rechargeWY(recharge);
					if (data != null) {
						FuiouService.wy500012(data, response);
						return null;
					}
					view.addFlashAttribute(Constant.MSG, "接口调用失败");
					return "redirect:/portal/cash/Recharge_new";
				} else {
					AppTransReqData data = controlPayService.recharge(recharge);
					if (data != null) {
						FuiouServiceMy.p2p500405(data, response);
						return null;
					}
					view.addFlashAttribute(Constant.MSG, "接口调用失败");
					return "redirect:/portal/cash/Recharge_new2";
				}
			} else {
				view.addFlashAttribute(Constant.MSG, "验证码有误");
				if ("1".equals(type)) {
					return "redirect:/portal/cash/Recharge_new";
				} else {
					return "redirect:/portal/cash/Recharge_new2";
				}
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 提现接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/withdrawalmonry")
	public String withdrawal(HttpServletRequest request, RedirectAttributes view, HttpServletResponse response) {
		try {
			String yzm = request.getParameter("yzm");
			String money = request.getParameter("money");
			HttpSession session = request.getSession();
			String memberOID = (String) session.getAttribute("memberOID");
			String check = (String) session.getAttribute(Constant.VALIDATE_CODE);
			WithdrawalDTO withdrawal = new WithdrawalDTO();
			withdrawal.setMemberOID(memberOID);
			withdrawal.setMoney(new BigDecimal(money));
			if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {// 测试
				Map<String, Object> resultmap = controlPayService.withdrawals(withdrawal);
				if (resultmap != null) {
					String message = (String) resultmap.get("message");
					if ("1".equals(message)) {
						AppTransReqData data = (AppTransReqData) resultmap.get("data");
						FuiouService.p2p500003(data, response);
						return null;
					}
					view.addFlashAttribute(Constant.MSG, message);
					return "redirect:/portal/cash/Withdrawals";
				}
				view.addFlashAttribute(Constant.MSG, "接口调用失败");
				return "redirect:/portal/cash/Withdrawals";
			}
			view.addFlashAttribute(Constant.MSG, "验证码有误");
			return "redirect:/portal/cash/Withdrawals";
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 修改支付密码 在富友页面修改，修改完以后没有回调参数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePayPassword")
	public String updatePayPassword(HttpServletResponse response, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String memberOID = (String) session.getAttribute("memberOID");
			if (StringUtil.isNotEmpty(memberOID)) {
				ResetPassWordReqData data = controlPayService.updatePayPassword(memberOID);
				if (data != null) {
					FuiouService.resetPassWord(data, response);
					return null;
				}
			}
			return "redirect:/portal/personal/Securitypre";
		} catch (Exception e) {
			logger.error("updatePayPassword(HttpServletResponse response,HttpServletRequest request)", e.getMessage());
			return "接口调用失败";
		}
	}

	/**
	 * 换卡接口
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeBankCard")
	public String changeBankCard(HttpServletResponse response, RedirectAttributes view, HttpServletRequest request) {
		try {
			String memberOID = request.getParameter("memberOID");
			String message = null;
			if (StringUtil.isNotEmpty(memberOID)) {
				Map<String, Object> resultmap = controlPayService.changeCard(memberOID);
				if (resultmap != null) {
					message = (String) resultmap.get("message");
					if ("1".equals(message)) {
						ChangeCard2ReqData data = (ChangeCard2ReqData) resultmap.get("data");
						FuiouService.changeCard2(data, response);
						return null;
					}
				}
			}
			view.addFlashAttribute(Constant.MSG, message);
			return "redirect:/portal/cash/MyCard";
		} catch (Exception e) {
			logger.error("changeBankCard(HttpServletResponse response,HttpServletRequest request)", e.getMessage());
			return "接口调用失败";
		}
	}

	/**
	 * 换卡回调
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeCardCallback")
	public String changeBankCardReturn(RedirectAttributes view, FuyuReturnDTO fureturn) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Login_id", fureturn.getLogin_id());
		map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
		map.put("resp_code", fureturn.getResp_code());
		String retr = controlPayService.changeBankCardReturn(map);
		if (StringUtil.isNotEmpty(retr)) {
			if ("1".equals(retr)) {
				view.addFlashAttribute(Constant.MSG, "申请成功");
				return "redirect:/portal/cash/MyCard";
			}
		}
		view.addFlashAttribute(Constant.MSG, "申请失败");
		return "redirect:/portal/cash/MyCard";

	}

	/**
	 * pc账户授权
	 * 
	 * @param view
	 * @param fureturn
	 * @return
	 */
	@RequestMapping(value = "/authorization")
	public String authorization(HttpServletRequest request, RedirectAttributes view, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String memberOID = (String) session.getAttribute("memberOID");
			if (StringUtil.isNotEmpty(memberOID)) {
				AuthorizationReqData data = controlPayService.authorization(memberOID);
				if (data != null) {
					FuiouServiceMy.authorization(data, response);
					return null;
				}
			}
			view.addFlashAttribute(Constant.MSG, "接口调用失败");
			return "redirect:/portal/loan/LoanApplication";
		} catch (Exception e) {
			logger.error("authorization(HttpServletRequest request,HttpServletResponse response)", e.getMessage());
			return "接口调用失败";
		}
	}

	
	
	/**
	 * web充值回调接口  
	 * 
	 * @param view
	 */
	//TODO 以下为富友回调
	@RequestMapping(value = "/rechargeCallback")
	public void rechargeCallback(FuyuReturnDTO fureturn) {
		Map<String, String> map = new HashMap<String, String>();
		if (fureturn != null) {
			map.put("resp_code", fureturn.getResp_code());
			map.put("login_id", fureturn.getLogin_id());
			map.put("amt", fureturn.getAmt());
			map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
			map.put("rem", fureturn.getRem());
			map.put("ly", "web");
			controlPayService.rechargeFinish(map);
		}
	}

	/**
	 * 提现回调接口
	 */
	@RequestMapping(value = "/withdrawalsCallback")
	@ResponseBody
	public void withdrawalsFinish(FuyuReturnDTO fureturn) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resp_code", fureturn.getResp_code());
		map.put("login_id", fureturn.getLogin_id());
		map.put("amt", fureturn.getAmt());
		map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
		map.put("rem", fureturn.getRem());
		map.put("ly", "web");
		String rows = controlPayService.withdrawalsFinish(map);
		if ("1".equals(rows)) {
			controlPayService.withdrawalsSxfFinish(map);

		}
	}

	/**
	 * 授权回调
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/authorizationCallback")
	@ResponseBody
	public void authorizationCallback(FuyuReturnDTO result) {
		if (result != null) {
			String rows = controlPayService.authorizationFinish(result);
			if ("1".equals(rows)) {
				logger.info("授权成功");
			} else {
				logger.error(rows);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
