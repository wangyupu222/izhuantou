package com.izhuantou.portal.controller.lend;

import java.math.BigDecimal;
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

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.AuditInfo;
import com.izhuantou.damain.lend.DDTPrivilege;
import com.izhuantou.damain.lend.DetialDDT;
import com.izhuantou.damain.lend.DetialHHT;
import com.izhuantou.damain.lend.DetialTBZ;
import com.izhuantou.damain.lend.DetialZZT;
import com.izhuantou.damain.lend.ExBorrowerHHTCDInfo;
import com.izhuantou.damain.lend.ExDDTInfo;
import com.izhuantou.damain.lend.HHTLendRecord;
import com.izhuantou.damain.lend.HHTPrivilege;
import com.izhuantou.damain.lend.HHTZZInfo;
import com.izhuantou.damain.lend.LendRecord;
import com.izhuantou.damain.lend.LendRecordDDT;
import com.izhuantou.damain.lend.TransferMark;
import com.izhuantou.damain.lend.TyPro;
import com.izhuantou.damain.p2p.P2pPageLoan;
import com.izhuantou.damain.vo.FromLendInfo;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.service.api.lend.AuditInfoService;
import com.izhuantou.service.api.lend.DetialDDTService;
import com.izhuantou.service.api.lend.DetialHHTService;
import com.izhuantou.service.api.lend.DetialTBZService;
import com.izhuantou.service.api.lend.DetialZZTService;
import com.izhuantou.service.api.lend.ExBorrowerDetialService;
import com.izhuantou.service.api.lend.FormLendService;
import com.izhuantou.service.api.lend.HHTTagComponentService;
import com.izhuantou.service.api.lend.HHTZZInfoService;
import com.izhuantou.service.api.lend.LendRecordDDTService;
import com.izhuantou.service.api.lend.LendRecordService;
import com.izhuantou.service.api.lend.PrivilegeService;
import com.izhuantou.service.api.lend.TransferMarkService;
import com.izhuantou.service.api.lend.TyProDetailsService;
import com.izhuantou.service.api.lend.isLoginService;

/***
 * 用于显示我的出借
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "detial", produces = "application/json;charset=UTF-8")
public class DetiallendController {
    @Autowired
    private DetialTBZService detialTBZService;
    @Autowired
    private TransferMarkService transferMarkService;
    @Autowired
    private ExBorrowerDetialService exBorrowerDetialService;
    @Autowired
    private LendRecordService lendRecordService;
    @Autowired
    private AuditInfoService auditInfoService;
    @Autowired
    private DetialHHTService detialHHTService;
    @Autowired
    private HHTTagComponentService hhtTagComponentService;
    @Autowired
    private HHTZZInfoService hhtZZInfoService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private DetialDDTService detialDDTService;
    @Autowired
    private LendRecordDDTService lendRecordDDTService;
    @Autowired
    private DetialZZTService detialZZTService;
    @Autowired
    private isLoginService isloginService;
    @Autowired
    private TyProDetailsService tyProDetailsService;
    @Autowired
    private FormLendService formLendService;

    /** 体验金登录判断 */
    @RequestMapping(value = "/isTyjUse")
    @ResponseBody
    public OpResult TyProDecide(HttpServletRequest request) {

	try {
	    HttpSession session = request.getSession();
	    String memberOID = (String) session.getAttribute("memberOID");
	    // String useMoney = isloginService.islogin(memberOID);
	    TyPro ty = tyProDetailsService.findByCondition(memberOID);
	    return OpResult.getSuccessResult(ty);
	} catch (Exception e) {
	    return null;
	}

    }

    /** 判断是否登录 */
    @RequestMapping(value = "/islogin")
    @ResponseBody
    public OpResult islogin(HttpServletRequest request) {

	try {
	    HttpSession session = request.getSession();
	    String memberOID = (String) session.getAttribute("memberOID");
	    String useMoney = isloginService.islogin(memberOID);
	    return OpResult.getSuccessResult(useMoney);

	} catch (Exception e) {
	    return null;
	}

    }

    /**
     * 转转投风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/auditZZTInfo")
    @ResponseBody
    public OpResult findAuditZZTInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	List<AuditInfo> list = auditInfoService.findByConditionZZT(OID);
	return OpResult.getSuccessResult(list);
    }

    /**
     * 
     * ZZT出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/zztlendRecord/{page}")
    @ResponseBody
    public OpResult FindLendRecordZZT(@PathVariable(value = "page") String page,
	    @RequestParam(value = "OID", defaultValue = "") String OID) {
	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	Pagination<LendRecord> lr = lendRecordService.findByZZTBusinessOID(Integer.parseInt(page), OID);

	return OpResult.getSuccessResult(lr);
    }

    /**
     * 转转投标原借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/detailExZZT")
    @ResponseBody
    public OpResult detailExZZT(@RequestParam(value = "OID", defaultValue = "") String OID) {
	WebP2pBiddingExamine zzt = exBorrowerDetialService.findByConditionZZT(OID);

	return OpResult.getSuccessResult(zzt);
    }

    /**
     * 转转投标的基本信息
     * 
     * @return json
     */
    @RequestMapping(value = "/detailZZT")
    @ResponseBody
    public OpResult detailZZT(@RequestParam(value = "OID", defaultValue = "") String OID, HttpServletRequest request) {
	String str = null;
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	DetialZZT ddt = detialZZTService.findByCondition(OID, memberOID);

	return OpResult.getSuccessResult(ddt);
    }

    /**
     * 点点投加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/ddtprivilege")
    @ResponseBody
    public OpResult privilegeDDT(@RequestParam(value = "OID", defaultValue = "") String OID,
	    HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	String str = null;
	List<DDTPrivilege> list = privilegeService.findDDTPrivilege(OID, memberOID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 点点投风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/auditDDTInfo")
    @ResponseBody
    public OpResult findAuditDDTInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	List<AuditInfo> list = auditInfoService.findByConditionDDT(OID);
	return OpResult.getSuccessResult(list);
    }

    /**
     * 
     * DDT出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/ddtlendRecord/{page}")
    @ResponseBody
    public OpResult FindLendRecordDDT(@PathVariable(value = "page") String page,
	    @RequestParam(value = "OID", defaultValue = "") String OID) {
	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	Pagination<LendRecordDDT> lr = lendRecordDDTService.findByBusinessOID(Integer.parseInt(page), OID);

	return OpResult.getSuccessResult(lr);
    }

    /**
     * 点点投标原借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/detailExDDT")
    @ResponseBody
    public OpResult detailExDDT(@RequestParam(value = "OID", defaultValue = "") String OID) {
	ExDDTInfo ddt = exBorrowerDetialService.findDDT(OID);

	return OpResult.getSuccessResult(ddt);
    }

    /**
     * 点点投标的基本信息
     * 
     * @return json
     */
    @RequestMapping(value = "/detailDDT")
    @ResponseBody
    public OpResult detailDDT(@RequestParam(value = "OID", defaultValue = "") String OID, HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	DetialDDT ddt = detialDDTService.findByCondition(OID, memberOID);

	return OpResult.getSuccessResult(ddt);
    }

    /**
     * 环环投加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/hhtprivilege")
    @ResponseBody
    public OpResult privilege(@RequestParam(value = "OID", defaultValue = "") String OID, HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	String str = null;
	List<HHTPrivilege> list = privilegeService.findPrivilege(OID, memberOID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 环环投车抵借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/exBorrowHHTCDZInfo")
    @ResponseBody
    public OpResult findexBorrowHHTCDZInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	String str = null;
	ExBorrowerHHTCDInfo cdInfo = exBorrowerDetialService.findCDHHT(OID);

	return OpResult.getSuccessResult(cdInfo);
    }

    /**
     * 环环投车抵风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/auditCDHHTInfo")
    @ResponseBody
    public OpResult findCDAuditHHTInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	List<AuditInfo> list = auditInfoService.findByConditionHHTCD(OID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 环环投风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/auditHHTInfo")
    @ResponseBody
    public OpResult findAuditHHTInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	List<AuditInfo> list = auditInfoService.findByConditionHHT(OID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 
     * 环环投原标借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/exHHTDetial")
    @ResponseBody
    public OpResult FindResulExHHTDetial(@RequestParam(value = "OID", defaultValue = "") String OID) {
	WebP2pBiddingExamine biddingExamine = exBorrowerDetialService.findByConditionHHT(OID);

	return OpResult.getSuccessResult(biddingExamine);
    }

    /**
     * 环环投债转信息
     * 
     * @param OID
     * @return
     */
    @RequestMapping(value = "/hhtZZInfo")
    @ResponseBody
    public OpResult FindResultHHTZZInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	HHTZZInfo info = hhtZZInfoService.findByOID(OID);

	return OpResult.getSuccessResult(info);
    }

    /**
     * 环环投出借记录
     * 
     * @param page
     * @param OID
     * @return
     */

    @RequestMapping(value = "/hhtLendRecord/{page}")
    @ResponseBody
    public OpResult FindResultHHTLendRecord(@PathVariable(value = "page") String page,
	    @RequestParam(value = "OID", defaultValue = "") String OID) {
	Pagination<HHTLendRecord> hht = detialHHTService.findByCondition(Integer.parseInt(page), OID);

	return OpResult.getSuccessResult(hht);
    }

    /**
     * 标的组成
     * 
     * @param page
     * @param OID
     * @return
     */

    @RequestMapping(value = "/hhtTagComponent/{page}")
    @ResponseBody
    public OpResult FindResultHHTTagComponent(@PathVariable(value = "page") String page,
	    @RequestParam(value = "OID", defaultValue = "") String OID) {
	Map<String, Object> map = hhtTagComponentService.findByCondition(Integer.valueOf(page), OID);
	return OpResult.getSuccessResult(map);
    }

    /**
     * 环环投标基本信息
     * 
     * @param OID
     * @param request
     * @return
     */
    @RequestMapping(value = "/detialHHT")
    @ResponseBody
    public OpResult FindResultHHT(@RequestParam(value = "OID", defaultValue = "") String OID,
	    HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	DetialHHT hht = detialHHTService.findByCondition(OID, memberOID);
	System.err.println(hht);
	return OpResult.getSuccessResult(hht);
    }

    /**
     * 
     * 头笔赚标详情
     * 
     * @return json
     */
    @RequestMapping(value = "/detialTBZ")
    @ResponseBody
    public OpResult FindResultTBZ(@RequestParam(value = "OID", defaultValue = "") String OID,
	    HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	DetialTBZ tbz = detialTBZService.findByCondition(OID, memberOID);

	return OpResult.getSuccessResult(tbz);
    }

    /**
     * 
     * 转让标的信息
     * 
     * @return json
     */
    @RequestMapping(value = "/detialmark")
    @ResponseBody
    public OpResult FindResulttbMark(@RequestParam(value = "OID", defaultValue = "") String OID,
	    HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	TransferMark mark = transferMarkService.findByCondition(OID, memberOID);

	return OpResult.getSuccessResult(mark);
    }

    /**
     * 
     * 原标借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/exDetial")
    @ResponseBody
    public OpResult FindResulExDetial(@RequestParam(value = "OID", defaultValue = "") String OID) {
	WebP2pBiddingExamine biddingExamine = exBorrowerDetialService.findByCondition(OID);

	return OpResult.getSuccessResult(biddingExamine);
    }

    /**
     * 
     * 出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/lendRecord/{page}")
    @ResponseBody
    public OpResult FindLendRecord(@PathVariable(value = "page") String page,
	    @RequestParam(value = "OID", defaultValue = "") String OID) {
	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	Pagination<LendRecord> lr = new Pagination<LendRecord>();
	String memberOID = null;
	DetialTBZ tbz = detialTBZService.findByCondition(OID, memberOID);
	if (tbz.getDebitCreditOID() != null) {
	    lr = lendRecordService.findByBusinessZCOID(Integer.parseInt(page), OID);

	} else {
	    lr = lendRecordService.findByBusinessOID(Integer.parseInt(page), OID);
	}

	return OpResult.getSuccessResult(lr);
    }

    /**
     * 风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/auditInfo")
    @ResponseBody
    public OpResult findAuditInfo(@RequestParam(value = "OID", defaultValue = "") String OID) {
	List<AuditInfo> list = auditInfoService.findByCondition(OID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 返回主页
     * 
     * @return index.jsp
     */

    @RequestMapping(value = "/Newtzdetails")
    public String findByAllTBZ(@RequestParam(value = "OID", defaultValue = "") String OID, HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	DetialTBZ tbz = new DetialTBZ();
	tbz = detialTBZService.findByCondition(OID, memberOID);
	try {
	    if (tbz.getDebitCreditOID() == null && tbz.getBiddingType().equals("0")) {
		return "Newtzdetails";
	    } else {
		return "NewZZtzdetails";
	    }
	} catch (Exception e) {
	    return "NewZZtzdetails";
	}

    }

    @RequestMapping(value = "/Zhuantzdetails")
    public String Zhuantzdetails() {
	return "Zhuantzdetails";
    }

    @RequestMapping(value = "/Diantzdetails")
    public String Diantzdetails() {
	return "Diantzdetails";
    }

    @RequestMapping(value = "/hhDetails_iframe")
    public String hhDetails_iframe() {
	return "hhDetails_iframe";
    }

    @RequestMapping(value = "/hhDetails_iframe_CD")
    public String hhDetials_iframe_CD() {
	return "hhDetails_iframe_CD";
    }

    @RequestMapping(value = "/hhDetails_iframe_new")
    public String hhDetails_iframe_new() {
	return "hhDetails_iframe_new";
    }

    @RequestMapping(value = "/hhDetails_iframe_ye")
    public String hhDetails_iframe_ye() {
	return "hhDetails_iframe_ye";
    }

    @RequestMapping(value = "/hhDetails_iframe_Z")
    public String hhDetails_iframe_Z() {
	return "hhDetails_iframe_Z";
    }

    @RequestMapping(value = "/hhDetails_iframe_ZCD")
    public String hhDetails_iframe_ZCD() {
	return "hhDetails_iframe_ZCD";
    }

    @RequestMapping(value = "/HHtzdetails")
    public String findByAllHHT() {
	return "HHtzdetails";
    }

    @RequestMapping(value = "/tzdetailsnew")
    public String tzdetailsnew() {
	return "tzdetailsnew";
    }

    @RequestMapping(value = "/ztloandetails")
    public String ztloandetails() {
	return "ztloandetails";
    }

    @RequestMapping(value = "/ztloan")
    public String ztloan() {
	return "ztloan";
    }

    public static final String SMS_VALIDATE_CODE = "smsValidateCode";

    /** 我要出借 */
    @RequestMapping(value = "/FormMyLend")
    public String FormLend(HttpServletRequest request, RedirectAttributes view) {
	String OID = request.getParameter("OID");
	FromLendInfo formLendInfo = new FromLendInfo();
	boolean tokenSign = true;

	if (true) {

	    try {
		HttpSession session = request.getSession();
		String sys_yzmsg = (String) session.getAttribute(SMS_VALIDATE_CODE);
		String yzm_reg = request.getParameter("mesYzm");
		String name = request.getParameter("name");
		String card = request.getParameter("card");
		String phone = request.getParameter("phone");
		String purpose = request.getParameter("purpose");
		String money = request.getParameter("money");
		String city = request.getParameter("city");
		String loanTerm = request.getParameter("loanTerm");
		String s_province = request.getParameter("s_province");
		String s_city = request.getParameter("s_city");
		String s_county = request.getParameter("s_county");
		formLendInfo.setName(name);
		formLendInfo.setCard(card);
		formLendInfo.setPhone(phone);
		formLendInfo.setPurpose(purpose);
		formLendInfo.setMoney(new BigDecimal(money));
		formLendInfo.setCity(city);
		formLendInfo.setLoanTerm(loanTerm);
		formLendInfo.setS_province(loanTerm);
		formLendInfo.setSys_yzmsg(sys_yzmsg);
		formLendInfo.setS_province(s_province);
		formLendInfo.setS_county(s_county);
		formLendInfo.setS_city(s_city);
		formLendInfo.setSys_yzmsg(sys_yzmsg);
		formLendInfo.setYzm_reg(yzm_reg);

		String s = formLendService.updFrom(formLendInfo);

		if (s.equals("验证码不正确")) {
		    view.addFlashAttribute("msg", "短信验证错误");
		    return "redirect:ztloandetails?OID=" + OID;
		} else if (s.equals("1")) {
		    return "wyjd09";
		}
		return "wyjd09";
	    } catch (Exception ex) {
		// 此处指定异常时跳转页面
		return "redirect:/portal/detail/errorpage";
	    }
	} else {
	    view.addFlashAttribute("msg", "请在刷新页面后，再进行提交！");
	    return "redirect:/portal/detail/ztloandetails?OID=" + OID;
	}

    }

    /**
     * 我要出借loan
     * 
     * @return json
     */
    @RequestMapping(value = "/pageLoan")
    @ResponseBody
    public OpResult pageLoan(@RequestParam(value = "OID", defaultValue = "") String OID) {
	P2pPageLoan loan = formLendService.findByOID(OID);

	return OpResult.getSuccessResult(loan);
    }

}
