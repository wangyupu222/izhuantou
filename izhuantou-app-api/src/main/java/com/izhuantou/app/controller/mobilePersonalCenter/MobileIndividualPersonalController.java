package com.izhuantou.app.controller.mobilePersonalCenter;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuiou.data.AppTransReqData;
import com.fuiou.service.FuiouService;
import com.izhuantou.app.controller.mobileDisplay.MobileDetialController;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.rsa.RsaCodeTool;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.MobilePropertyTJDTO;
import com.izhuantou.damain.vo.FuyuReturnDTO;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobilePropertyService;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.third.rpc.api.ControlPayService;

@Controller
@RequestMapping(value = "app/individual", produces = "application/json;charset=UTF-8")
public class MobileIndividualPersonalController {

    private static final Logger logger = LoggerFactory.getLogger(MobileDetialController.class);

    @Autowired
    private MobilePropertyService mobilePropertyService;
    @Autowired
    private MyCashService myCashService;
    @Autowired
    private ControlPayService controlPayService;
    
    /**
     * 富友回调的统一页面
     * 
     * @return
     */
    @RequestMapping(value = "/HXreturnApp")
    public String HXreturnApp() {

	return "HXreturnApp";
    }

    /**
     * 富友回调的统一页面
     * 
     * @return
     */
    @RequestMapping(value = "/returnApp")
    public String returnApp() {

	return "returnApp";
    }

    /** 我的 页面三个数据 */
    @RequestMapping(value = "/mobilePersonalCenter")
    @ResponseBody
    public OpResult indexmoney(String memberOID, String pkversion) {
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    if (StringUtil.isNotEmpty(memberOID)) {
		MobilePropertyTJDTO pro = mobilePropertyService.findIndexProperty(memberOID);
		if (pro == null) {
		    return OpResult.getFailedResult("暂无数据");
		} else {
		    return OpResult.getSuccessResult(pro);
		}
	    } else {
		return OpResult.getFailedResult("用户OID不能为空");
	    }

	} catch (Exception e) {
	    logger.error("public OpResult indexmoney(String memberOID,String pkversion)" + e.getMessage());
	    return null;
	}

    }

    /** 我的资产详情 */
    @RequestMapping(value = "/mobileMoneyTJ")
    @ResponseBody
    public OpResult loanAudit(String memberOID, String pkversion) {
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    if (StringUtil.isNotEmpty(memberOID)) {
		// 我的 页面三个数据
		MobilePropertyTJDTO pro = mobilePropertyService.findIndividualProperty(memberOID);
		// 详情里的
		MobilePropertyTJDTO pror = mobilePropertyService.findIndexProperty(memberOID);

		if (pror == null && pror == null) {
		    return OpResult.getFailedResult("暂无数据");
		} else {
		    pror.setZzc(pro.getZzc());
		    pror.setKyye(pro.getKyye());
		    pror.setDsbj(pro.getDsbj());
		    pror.setDslx(pro.getDslx());
		    pror.setTqsyds(pro.getTqsyds());
		    return OpResult.getSuccessResult(pror);
		}
	    } else {
		return OpResult.getFailedResult("用户OID不能为空");
	    }
	} catch (Exception e) {
	    logger.error("public OpResult loanAudit(String memberOID,String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 充值记录和提现记录信息
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/RechargeAndWithdralRecord")
    @ResponseBody
    public OpResult recharge(String memberOID, Integer currentpage, String state, String pkversion) {
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    state = rsac.serverDecrypt(state, pkversion);
	    if (state.equals("CZ")) {
		state = "recharge";
	    } else if (state.equals("TX")) {
		state = "withdrawals";
	    }
	    if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(state)) {
		Map<String, Object> map = myCashService.findRechargeRecord(memberOID, state, currentpage);
		if (map != null) {
		    return OpResult.getSuccessResult(map);
		}
	    }
	    return OpResult.getFailedResult("查询记录失败");
	} catch (Exception e) {
	    logger.error("public OpResult recharge(String memberOID,Integer currentpage, String state,String pkversion)"
		    + e.getMessage());
	    return null;
	}

    }

    /**
     * 用户充值的确认提交
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/appRecharge")
    @ResponseBody
    public OpResult rechargeWY(HttpServletRequest requset, HttpServletResponse response, String memberOID, String money,
	    String channel, String pkversion) {
	try {
	    RsaCodeTool rsac = new RsaCodeTool();
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    money = rsac.serverDecrypt(money, pkversion);

	    if (StringUtil.isNotEmpty(channel)) {
		channel = rsac.serverDecrypt(channel, pkversion);
	    } else {
		channel = "旧版本";
	    }
	    HttpSession session = requset.getSession();
	    session.setAttribute("channelRecharge", channel);
	    if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(money)) {
		AppTransReqData resultDate = controlPayService.appRecharge(memberOID, new BigDecimal(money));
		if (resultDate != null) {
		    FuiouService.app500002(resultDate, response);
		    return null;
		}
		return OpResult.getFailedResult("接口调用失败");
	    } else {
		return OpResult.getFailedResult("用户OID和钱数不能为空");
	    }
	} catch (Exception e) {

	    return null;
	}
    }

    /**
     * App充值回调
     * 
     * @param view
     */
    @RequestMapping(value = "/appRechargeCallback")
    @ResponseBody
    public String rechargeCallback(HttpServletRequest request, FuyuReturnDTO fureturn) {
	HttpSession session = request.getSession();
	String ly = (String) session.getAttribute("channelRecharge");
	Map<String, String> map = new HashMap<String, String>();
	map.put("resp_code", fureturn.getResp_code());
	map.put("login_id", fureturn.getLogin_id());
	map.put("amt", fureturn.getAmt());
	map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
	map.put("rem", fureturn.getRem());
	map.put("ly", ly);
	String rows =controlPayService.rechargeFinish(map);
	if ("1".equals(rows)) {
		return "success";
	}
	return "fail";
    }

    /**
     * 提现接口
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/appWithdrawals")
    @ResponseBody
    public OpResult withdrawal(HttpServletRequest requset, HttpServletResponse response, String memberOID, String money,
	    String channel, String pkversion) {
	try {
	    RsaCodeTool rsac = new RsaCodeTool();
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    money = rsac.serverDecrypt(money, pkversion);

	    if (StringUtil.isNotEmpty(channel)) {
		channel = rsac.serverDecrypt(channel, pkversion);
	    } else {
		channel = "旧版本";
	    }
	    HttpSession session = requset.getSession();
	    session.setAttribute("channelWithdrawal", channel);

	    if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(money)) {
		Map<String, Object> resultmap =controlPayService.appWithdrawals(memberOID, new BigDecimal(money));
		String message = null;
		if (resultmap != null) {
		    message = (String) resultmap.get("message");
		    if ("1".equals(message)) {
			AppTransReqData data = (AppTransReqData) resultmap.get("data");
			FuiouService.app500003(data, response);
			return null;
		    }
		}
		return OpResult.getFailedResult(message);
	    } else {
		return OpResult.getFailedResult("用户名和钱数不能为空");
	    }
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * App提现回调接口
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/mobilewithdrawalsCallback")
    @ResponseBody
    public String withdrawalsFinish(HttpServletRequest request, FuyuReturnDTO fureturn) {
	HttpSession session = request.getSession();
	String ly = (String) session.getAttribute("channelWithdrawal");
	Map<String, String> map = new HashMap<String, String>();
	map.put("resp_code", fureturn.getResp_code());
	map.put("login_id", fureturn.getLogin_id());
	map.put("amt", fureturn.getAmt());
	map.put("mchnt_txn_ssn", fureturn.getMchnt_txn_ssn());
	map.put("rem", fureturn.getRem());
	map.put("ly", ly);
	String rows = controlPayService.withdrawalsFinish(map);
	if ("1".equals(rows)) {
		controlPayService.withdrawalsSxfFinish(map);
		return "success";
	}
	return "fail";
    }

}
