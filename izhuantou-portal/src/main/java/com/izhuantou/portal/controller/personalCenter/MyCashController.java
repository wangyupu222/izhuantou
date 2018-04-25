package com.izhuantou.portal.controller.personalCenter;

import java.math.BigDecimal;
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
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.FuyuReturnDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;
import com.izhuantou.service.api.personalCenter.MyCashService;

/***
 * 个人中心
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "cash", produces = "application/json;charset=UTF-8")
public class MyCashController {
    public static final String VALIDATE_CODE = "validateCode";
    public static final String SMS_VALIDATE_CODE = "smsValidateCode";
    private static final Logger logger = LoggerFactory.getLogger(MyCashController.class);
    @Autowired
    private MyCashService myCashService;

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

    @RequestMapping(value = "/checkValidateCode")
    @ResponseBody
    public OpResult checkValidateCode(HttpServletRequest request, String yzm) {
	HttpSession session = request.getSession();
	String check = (String) session.getAttribute(VALIDATE_CODE);
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
	String str = null;
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<PayCustomerBusiness> list = this.myCashService.findMessage(Integer.parseInt(page), memberOID,
		strDate, endDate);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}

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
     * 银行卡信息
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
     * 卡的信息
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

    /**
     * 注册富友
     * 
     * @return
     */
    @RequestMapping(value = "/insertCustomer")
    public String insertCustomer(HttpServletRequest request, RedirectAttributes view, CustomerDTO customer) {
	HttpSession session = request.getSession();
	// 获取session中的手机号验证码
	String checksms = (String) session.getAttribute(SMS_VALIDATE_CODE);
	String memberOID = (String) session.getAttribute("memberOID");
	// 表单的短信验证码
	String sms = customer.getMsm();
	String rows = null;
	if (StringUtil.isNotEmpty(sms) && !sms.equals(checksms)) {// 测试>>>>>>>>>>>
	    session.removeAttribute(VALIDATE_CODE);
	    session.removeAttribute(SMS_VALIDATE_CODE);
	    rows = myCashService.insertCustomer(customer);
	    if ("1".equals(rows)) {
		return "redirect:/portal/cash/MyCard";
	    }
	}
	view.addFlashAttribute("msg", rows);
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
	    String check = (String) session.getAttribute(VALIDATE_CODE);
	    String yzm = recharge.getYzm();
	    String type = recharge.getType();
	    String memberOID = (String) session.getAttribute("memberOID");
	    recharge.setMemberOID(memberOID);
	    if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {// 测试
		if (StringUtil.isNotEmpty(type) && "1".equals(type)) {
		    Map<String, Object> resulltmap = myCashService.rechargeWY(recharge);

		    Wy500012ReqData data = (Wy500012ReqData) resulltmap.get("data");
		    FuiouService.wy500012(data, response);
		    return null;
		} else {
		    Map<String, Object> resulltmap = myCashService.recharge(recharge);
		    AppTransReqData data = (AppTransReqData) resulltmap.get("data");
		    FuiouServiceMy.p2p500405(data, response);
		    return null;
		}
	    } else {
		view.addFlashAttribute("msg", "验证码有误");
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
     * 网页版回调
     * 
     * @param view
     */
    @RequestMapping(value = "/rechargeCallback")
    public void rechargeCallback(RedirectAttributes view, FuyuReturnDTO fureturn) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("resp_code", fureturn.getResp_code());
	map.put("login_id", fureturn.getLogin_id());
	map.put("amt", fureturn.getAmt());
	map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
	map.put("rem", fureturn.getRem());
	map.put("ly", "web");
	String phone = fureturn.getLogin_id();
	String rows = myCashService.rechargeFinish(map);
	if ("1".equals(rows)) {
	    String rowss = myCashService.updateCustomerMoney(phone);
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
	    String check = (String) session.getAttribute(VALIDATE_CODE);
	    WithdrawalDTO withdrawal = new WithdrawalDTO();
	    withdrawal.setMemberOID(memberOID);
	    withdrawal.setMoney(new BigDecimal(money));
	    if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {// 测试
		Map<String, Object> resultmap = myCashService.withdrawal(withdrawal);
		if (resultmap != null) {
		    String message = (String) resultmap.get("message");
		    if ("1".equals(message)) {
			AppTransReqData data = (AppTransReqData) resultmap.get("data");
			FuiouService.p2p500003(data, response);
			return null;
		    }
		    view.addFlashAttribute("msg", message);
		    return "redirect:/portal/cash/Withdrawals";
		}
		view.addFlashAttribute("msg", "接口调用失败");
		return "redirect:/portal/cash/Withdrawals";
	    }
	    view.addFlashAttribute("msg", "验证码有误");
	    return "redirect:/portal/cash/Withdrawals";
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 提现回调接口
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/withdrawalsCallback")
    @ResponseBody
    public void withdrawalsFinish(HttpServletRequest request, HttpServletResponse response, FuyuReturnDTO fureturn) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("resp_code", fureturn.getResp_code());
	map.put("login_id", fureturn.getLogin_id());
	map.put("amt", fureturn.getAmt());
	map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
	map.put("rem", fureturn.getRem());
	map.put("ly", "web");
	String phone = fureturn.getLogin_id();
	String rows = myCashService.withdrawalsFinish(map);
	if ("1".equals(rows)) {
	    String rowss = myCashService.updateCustomerMoney(phone);
	    if ("1".equals(rowss)) {
		String row = myCashService.withdrawalsSxfFinish(map);
		if ("1".equals(row)) {
		    String ro = myCashService.updateCustomerMoney(phone); // 成功
		}
	    }
	}
    }

    /**
     * 修改支付密码 在富友页面修改，修改完以后没有回调参数
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePayPassword")
    @ResponseBody
    public String updatePayPassword(HttpServletResponse response, HttpServletRequest request) {
	try {
	    HttpSession session = request.getSession();
	    String memberOID = (String) session.getAttribute("memberOID");
	    if (StringUtil.isNotEmpty(memberOID)) {
		Map<String, Object> map = myCashService.updatePayPassword(memberOID);
		if (map != null) {
		    ResetPassWordReqData data = (ResetPassWordReqData) map.get("data");
		    FuiouService.resetPassWord(data, response);
		    return null;
		}
	    }
	    return "memberOID不能为空";
	} catch (Exception e) {
	    logger.error("updatePayPassword(HttpServletResponse response,HttpServletRequest request)", e.getMessage());
	    return "接口调用失败";
	}
    }

    /**
     * 换卡接口 未开发完成延期
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeBankCard")
    public String changeBankCard(HttpServletResponse response, HttpServletRequest request) {
	try {
	    String memberOID = request.getParameter("memberOID");// (String)
								 // session.getAttribute("memberOID");
	    Map<String, Object> resultmap = myCashService.changeBankCard(memberOID);
	    if (resultmap != null) {
		String message = (String) resultmap.get("message");
		if ("1".equals(message)) {
		    ChangeCard2ReqData data = (ChangeCard2ReqData) resultmap.get("data");
		    FuiouService.changeCard2(data, response);
		    return null;
		}
		return "redirect:/portal/cash/MyCard";
	    }
	    return "redirect:/portal/cash/MyCard";
	} catch (Exception e) {
	    logger.error("changeBankCard(HttpServletResponse response,HttpServletRequest request)", e.getMessage());
	    return "接口调用失败";
	}
    }

    /**
     * 换卡回调 未开发完成延期
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
	String retr = myCashService.changeBankCardReturn(map);
	if (StringUtil.isNotEmpty(retr)) {
	    if ("1".equals(retr)) {
		view.addFlashAttribute("msg", "申请成功");
		return "redirect:/portal/cash/MyCard";
	    }
	}
	view.addFlashAttribute("msg", "申请失败");
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
    public String authorization(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession();
	try {
	    String memberOID = request.getParameter("memberOID");
	    if (StringUtil.isNotEmpty(memberOID)) {
		Map<String, Object> resultMap = myCashService.authorization(memberOID);
		if (resultMap != null) {
		    AuthorizationReqData data = (AuthorizationReqData) resultMap.get("data");
		    FuiouServiceMy.authorization(data, response);
		    return null;
		}
	    }
	    return "redirect:/portal/personal/LoanApplication";
	} catch (Exception e) {
	    logger.error("authorization(HttpServletRequest request,HttpServletResponse response)", e.getMessage());
	    return "接口调用失败";
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
    public void authorizationCallback(String resp_code, String login_id, String mchnt_txn_ssn, String wtCzParam,
	    String wtTxparam) {

	Map<String, String> map = new HashMap<String, String>();
	map.put("resp_code", resp_code);
	map.put("login_id", login_id);
	map.put("mchnt_txn_ssn", mchnt_txn_ssn);
	map.put("wtCzParam", wtCzParam);
	map.put("wtTxparam", wtTxparam);
	String result = myCashService.authorizationCallback(map);

    }

}
