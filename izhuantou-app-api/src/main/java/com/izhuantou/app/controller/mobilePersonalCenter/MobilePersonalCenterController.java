package com.izhuantou.app.controller.mobilePersonalCenter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.rsa.RsaCodeTool;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.MobileTYJPoJo;
import com.izhuantou.damain.mobile.personalCenter.MobileCashDetialDTO;
import com.izhuantou.damain.mobile.personalCenter.MobileCenterLoanAditModelDTO;
import com.izhuantou.damain.mobile.personalCenter.MobileOpinionDTO;
import com.izhuantou.damain.mobile.personalCenter.MobileTyj;
import com.izhuantou.damain.mobile.personalCenter.MobileYQHYNewDTO;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.damain.vo.LoanApplyRecordDTO;
import com.izhuantou.damain.vo.MyInvitationDaoDTO;
import com.izhuantou.damain.vo.MyPrivilegeDaoDTO;
import com.izhuantou.damain.vo.PersonalMessageDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;
import com.izhuantou.service.api.lend.ExperienceMoneyService;
import com.izhuantou.service.api.lend.isLoginService;
import com.izhuantou.service.api.mobile.MobileTYJService;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileFormOpinionService;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileLoanApplicationService;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.service.api.personalCenter.MyPrivilegeService;
import com.izhuantou.service.api.personalCenter.MyloanService;
import com.izhuantou.service.api.personalCenter.PersonalCenterService;

/***
 * 个人中心
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "app/mobilePersonal", produces = "application/json;charset=UTF-8")
public class MobilePersonalCenterController {

    private static final Logger logger = LoggerFactory.getLogger(MobilePersonalCenterController.class);
    @Autowired
	private MyloanService myloanService;
    @Autowired
    private MyPrivilegeService myPrivilegeService;
    @Autowired
    private PersonalCenterService personalMessage;
    @Autowired
    private MobileFormOpinionService mobileFormOpinionService;
    @Autowired
    private isLoginService isloginService;
    @Autowired
    private MyCashService myCashService;
    @Autowired
    private MobileTYJService mobileTYJService;
    @Autowired
    private ExperienceMoneyService experienceMoneyService;
    @Autowired
    private MobileLoanApplicationService mobileloanApplicationService;

    /** 个人中心借款审核 */
    @RequestMapping(value = "/mobileLoanAudit")
    public String loanAudit(MobileCenterLoanAditModelDTO loanAditModelDTO, String pkversion) {
	WebP2pLoanApply loanApply = new WebP2pLoanApply();
	String memberOID = loanAditModelDTO.getMemberOID();
	// String sys_msg = (String) session.getAttribute("smsLoanApplication");
	String userName = loanAditModelDTO.getName();
	String idCard = loanAditModelDTO.getCard();
	String mobile = loanAditModelDTO.getPhone();
	String money = loanAditModelDTO.getMoney();
	String loanTerm = loanAditModelDTO.getLoanTerm();
	String loanYT = loanAditModelDTO.getProType();// 产品名
	// String mesYzm = request.getParameter("mesYzm");// 短信验证码
	// Integer loanAmount = (Integer) dtoFormApply.get("money");
	String s_province = loanAditModelDTO.getS_province();// 省
	String s_city = loanAditModelDTO.getS_city();// 城市
	String s_county = loanAditModelDTO.getS_county();// 村
	// String rate = request.getParameter("rate");// 借贷利率
	// String loanOID = request.getParameter("loanOID");//
	// 用于修改的OID，如果有值即修改没有是添加
	// String city = (String) dtoFormApply.get("city");

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    userName = rsac.serverDecrypt(userName, pkversion);
	    idCard = rsac.serverDecrypt(idCard, pkversion);
	    mobile = rsac.serverDecrypt(mobile, pkversion);
	    money = rsac.serverDecrypt(money, pkversion);
	    loanTerm = rsac.serverDecrypt(loanTerm, pkversion);
	    loanYT = rsac.serverDecrypt(loanYT, pkversion);
	    s_province = rsac.serverDecrypt(s_province, pkversion);
	    s_city = rsac.serverDecrypt(s_city, pkversion);
	    s_county = rsac.serverDecrypt(s_county, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    String city = "";
	    if (s_province != null) {
		if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
			|| s_province.equals("重庆市")) {
		    city = s_province + "," + s_county;
		} else {
		    city = s_province + "," + s_city + "," + s_county;
		}
	    }
	    loanApply.setMemberOID(memberOID);
	    loanApply.setUserName(userName);
	    loanApply.setIdCard(idCard);
	    loanApply.setMobile(mobile);
	    if (money != null) {
		loanApply.setLoanAmount(new BigDecimal(money));
	    }
	    loanApply.setLoanTerm(loanTerm);
	    loanApply.setLoanYT(loanYT);
	    loanApply.setCity(city);
	    /*
	     * loanApply.setRate(rate); loanApply.setSys_msg(sys_msg);
	     * loanApply.setMesYzm(mesYzm); loanApply.setOID(loanOID);
	     */
	    String s = mobileloanApplicationService.updAppInfo(loanApply);

	    if (s.equals("您尚未进行委托交易授权")) {
		return "/web/LoanApplication.jsp?type=1";
	    } else if (s.equals("验证码不正确")) {
		return "";
	    } else if (s.equals("1")) {
		return "";
	    }

	    return "";

	} catch (Exception ex) {
	    // this.sendErrorMessage("发生错误:" + ex.getMessage());
	    // 此处指定异常时跳转页面
	    // forward = "";
	    // this.forward(forwardErro);
	    return null;
	}

    }

    /** 个人中心借款审核修改 */
    @RequestMapping(value = "/mobileUpdloanAudit")
    public String loanAuditupd(MobileCenterLoanAditModelDTO loanAditModelDTO, String pkversion) {
	WebP2pLoanApply loanApply = new WebP2pLoanApply();
	String memberOID = loanAditModelDTO.getMemberOID();
	// String sys_msg = (String) session.getAttribute("smsLoanApplication");
	String userName = loanAditModelDTO.getName();
	String idCard = loanAditModelDTO.getCard();
	String mobile = loanAditModelDTO.getPhone();
	String money = loanAditModelDTO.getMoney();
	String loanTerm = loanAditModelDTO.getLoanTerm();
	String loanYT = loanAditModelDTO.getProType();// 产品名
	// String mesYzm = request.getParameter("mesYzm");// 短信验证码
	// Integer loanAmount = (Integer) dtoFormApply.get("money");
	String s_province = loanAditModelDTO.getS_province();// 省
	String s_city = loanAditModelDTO.getS_city();// 城市
	String s_county = loanAditModelDTO.getS_county();// 村
	// String rate = request.getParameter("rate");// 借贷利率
	// String loanOID = request.getParameter("loanOID");//
	// 用于修改的OID，如果有值即修改没有是添加
	// String city = (String) dtoFormApply.get("city");

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    userName = rsac.serverDecrypt(userName, pkversion);
	    idCard = rsac.serverDecrypt(idCard, pkversion);
	    mobile = rsac.serverDecrypt(mobile, pkversion);
	    money = rsac.serverDecrypt(money, pkversion);
	    loanTerm = rsac.serverDecrypt(loanTerm, pkversion);
	    loanYT = rsac.serverDecrypt(loanYT, pkversion);
	    s_province = rsac.serverDecrypt(s_province, pkversion);
	    s_city = rsac.serverDecrypt(s_city, pkversion);
	    s_county = rsac.serverDecrypt(s_county, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    String city = "";
	    if (s_province != null) {
		if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
			|| s_province.equals("重庆市")) {
		    city = s_province + "," + s_county;
		} else {
		    city = s_province + "," + s_city + "," + s_county;
		}
	    }
	    loanApply.setMemberOID(memberOID);
	    loanApply.setUserName(userName);
	    loanApply.setIdCard(idCard);
	    loanApply.setMobile(mobile);
	    if (money != null) {
		loanApply.setLoanAmount(new BigDecimal(money));
	    }
	    loanApply.setLoanTerm(loanTerm);
	    loanApply.setLoanYT(loanYT);
	    loanApply.setCity(city);
	    /*
	     * loanApply.setRate(rate); loanApply.setSys_msg(sys_msg);
	     * loanApply.setMesYzm(mesYzm); loanApply.setOID(loanOID);
	     */
	    String s = mobileloanApplicationService.update(loanApply, memberOID);

	    if (s.equals("验证码不正确")) {
		return "redirect:portal/personal/successLoanApplication.jsp";
	    } else if (s.equals("1")) {
		return "redirect:portal/personal/successLoanApplication.jsp";
	    }

	    return "redirect:/portal/personal/successLoanApplication.jsp";

	} catch (Exception ex) {
	    // this.sendErrorMessage("发生错误:" + ex.getMessage());
	    // 此处指定异常时跳转页面
	    // forward = "";
	    // this.forward(forwardErro);
	    return "redirect:/portal/personal/successLoanApplication.jsp";
	}

    }

    /**
     * 审核状态查询
     * 
     * @return
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileLendprocess")
    @ResponseBody
    public OpResult lendprocess(String memberOID, Integer page, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID="370518b20a5dca712804025f2e15867a";
	page = 1;
	if (StringUtil.isNotEmpty(memberOID)) {

	    Pagination<LoanApplyRecordDTO> list = myloanService.findLoanApplyByMemberOID(memberOID, page);

	    if (list != null) {

		return OpResult.getSuccessResult(list);
	    }
	    return OpResult.getFailedResult("失败");
	} else {
	    return OpResult.getFailedResult("OID为空");
	}

    }

    /**
     * 客户信息查询
     * 
     * @return
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileFindInfo")
    @ResponseBody
    public OpResult findInfo(String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
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

    /**
     * 个人中心借款记录
     * 
     * @return
     * 
     * @return json
     */
   /* @RequestMapping(value = "/mobileMyLendRecord")
    @ResponseBody
    public OpResult myLendRecord(String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    if (StringUtil.isNotEmpty(memberOID)) {
		Map<String, Object> map = new HashMap<>();
		map = myloanService.myLoanRecord(memberOID);
		List<MyLoanRecordDTO> lendRecord = (List) map.get("data");
		MobileMyLendRecord lendresult = new MobileMyLendRecord();
		List listResult = new ArrayList<>();
		for (MyLoanRecordDTO lend : lendRecord) {
		    String s = lend.getLoanSJ();
		    String s1 = lend.getNextSJ();
		    String s2 = lend.getEndSJ();
		    lendresult.setBusinessOID(lend.getBusinessOID());
		    lendresult.setLoanNumber(lend.getLoanNumber());
		    lendresult.setYearRate(lend.getYearRate().toString());
		    lendresult.setHKFS(lend.getHkfs());
		    lendresult.setJkqx(lend.getJkqx());
		    lendresult.setLoanAmount(lend.getLoanAmount());
		    lendresult.setLoanDay(lend.getLoanSJ());
		    lendresult.setNextday(lend.getNextSJ());
		    lendresult.setEndDate(lend.getEndSJ());
		    listResult.add(lendresult);
		}
		map.put("data", listResult);
		return OpResult.getSuccessResult(map);
	    }
	    return OpResult.getSuccessResult("memberOID不能为空");
	} catch (Exception e) {
	    logger.error("public OpResult myLendRecord(String memberOID, String pkversion)" + e.getMessage());
	    return null;
	}

    }*/

    /**
     * 还款
     * 
     * @return
     * 
     * @return json
     */
   /* @RequestMapping(value = "/mobileRepaymentBack")
    @ResponseBody
    public OpResult repaymentBack(String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    List<RepayMentDaoDTO> list = new ArrayList<>();
	    List<MobileMyRepayment> resultlist = new ArrayList<>();
	    if (StringUtil.isNotEmpty(memberOID)) {
		list = repaymentService.findMyRepayment(memberOID);
		for (RepayMentDaoDTO listRepaymentCollection : list) {
		    MobileMyRepayment myRepayment = new MobileMyRepayment();
		    myRepayment.setYuqiflag(listRepaymentCollection.getYuqiflag());
		    myRepayment.setRepayDate(listRepaymentCollection.getNextSJ());
		    myRepayment.setAllmoney(listRepaymentCollection.getAllmoney().toString());
		    myRepayment.setBut(listRepaymentCollection.getBut());
		    myRepayment.setInterestMoney(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setPlanOID(listRepaymentCollection.getPlanOID());
		    myRepayment.setLoandatetime(listRepaymentCollection.getLoanSJ());
		    myRepayment.setTerm(listRepaymentCollection.getTerm().toString());
		    myRepayment.setQs(listRepaymentCollection.getQs());
		    myRepayment.setPrincipalMoney(listRepaymentCollection.getPrincipalMoney().toString());
		    myRepayment.setInterestMoney(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setServiceMoney(listRepaymentCollection.getServiceMoney().toString());
		    myRepayment.setManageMoney(listRepaymentCollection.getManageMoney().toString());
		    myRepayment.setYhbj(listRepaymentCollection.getYhbj().toString());

		    
		     * myRepayment.setYqlx(listRepaymentCollection.getYqlx().
		     * toString());
		     * myRepayment.setGlzxf(listRepaymentCollection.getZxsxf().
		     * toString());
		     * myRepayment.setPtglf(listRepaymentCollection.getPtglf().
		     * toString());
		     * 
		     * myRepayment.setYqglf(listRepaymentCollection.getYqglf().
		     * toString());
		     
		    myRepayment.setYqfx(listRepaymentCollection.getYqfx().toString());
		    myRepayment.setWyj(listRepaymentCollection.getWyj().toString());
		    myRepayment.setAllje(listRepaymentCollection.getMoney().toString());
		    myRepayment.setYhbj(listRepaymentCollection.getYhbj().toString());
		    myRepayment.setYhlx(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setJksxf(listRepaymentCollection.getZxsxf().toString());
		    myRepayment.setOncepunish(listRepaymentCollection.getOncePay().toString());
		    myRepayment.setBusinessOID(listRepaymentCollection.getBusinessName());
		    resultlist.add(myRepayment);
		}
	    }
	    return OpResult.getSuccessResult(resultlist);
	} catch (Exception e) {
	    logger.error("public OpResult repaymentBack(String memberOID, String pkversion)" + e.getMessage());
	    return null;
	}

    }*/

    /**
     * 还款逾期
     * 
     * @return
     * 
     * @return json
     */
/*    @RequestMapping(value = "/mobileRepaymentBackYQ")
    @ResponseBody
    public OpResult repaymentBackYQ(String memberOID, String pkversion) {

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "370518b20a5dca712804025f2e15867a";
	if (StringUtil.isNotEmpty(memberOID)) {
	    String str = null;
	    try {

		List<RepayResultDTO> list = new ArrayList<>();
		List<MobileMyRepayment> resultlist = new ArrayList<>();
		list = myloanService.findMyRepayment(memberOID);
		for (RepayResultDTO listRepaymentCollection : list) {
		    MobileMyRepayment myRepayment = new MobileMyRepayment();
		    myRepayment.setYuqiflag(listRepaymentCollection.getYuqiflag());
		    myRepayment.setRepayDate(listRepaymentCollection.getNextSJ());
		    myRepayment.setAllmoney(listRepaymentCollection.getAllmoney().toString());
		    myRepayment.setBut(listRepaymentCollection.getBut());
		    myRepayment.setInterestMoney(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setPlanOID(listRepaymentCollection.getPlanOID());
		    myRepayment.setLoandatetime(listRepaymentCollection.getLoanSJ());
		    myRepayment.setTerm(listRepaymentCollection.getTerm().toString());
		    myRepayment.setQs(listRepaymentCollection.getQs());
		    myRepayment.setPrincipalMoney(listRepaymentCollection.getPrincipalMoney().toString());
		    myRepayment.setInterestMoney(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setServiceMoney(listRepaymentCollection.getServiceMoney().toString());
		    myRepayment.setManageMoney(listRepaymentCollection.getManageMoney().toString());
		    // myRepayment.setYhbj(listRepaymentCollection.getYhbj().toString());

		    myRepayment.setYqlx(listRepaymentCollection.getYqlx().toString());
		    myRepayment.setGlzxf(listRepaymentCollection.getZxsxf().toString());
		    myRepayment.setPtglf(listRepaymentCollection.getPtglf().toString());
		    myRepayment.setWyj(listRepaymentCollection.getYqwyj().toString());
		    myRepayment.setYqglf(listRepaymentCollection.getYqglf().toString());
		    myRepayment.setYqfx(listRepaymentCollection.getYqfx().toString());

		    myRepayment.setAllje(listRepaymentCollection.getAllmoney().toString());
		    // myRepayment.setYhbj(listRepaymentCollection.getYhbj().toString());
		    myRepayment.setYhlx(listRepaymentCollection.getInterestMoney().toString());
		    myRepayment.setJksxf(listRepaymentCollection.getZxsxf().toString());
		    myRepayment.setOncepunish(listRepaymentCollection.getOncePay().toString());
		    myRepayment.setBusinessOID(listRepaymentCollection.getBusinessName());
		    resultlist.add(myRepayment);
		}
		return OpResult.getSuccessResult(resultlist);
	    } catch (Exception e) {
		logger.error("public OpResult repaymentBack(String memberOID, String pkversion)" + e.getMessage());
		return null;
	    }
	}
	return null;
    }*/

    /**
     * 可用的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/mobilecanUseJxq")
    @ResponseBody
    public OpResult canUseJxq(String memberOID, String pkversion, Integer page) {

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	memberOID = "370518b20a5dca712804025f2e15867a";
	page = 1;
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCanUseJxqByMemberOID(memberOID, page);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已使用的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileisUseJxq")
    @ResponseBody
    public OpResult isUseJxq(String memberOID, Integer page, String pkversion) {
	// String memberOID = "370518b20a5dca712804025f2e15867a";// (String)
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	memberOID = "370518b20a5dca712804025f2e15867a";
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findUseJxqByMemberOID(memberOID, page);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已过期的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileGqJxq")
    @ResponseBody
    public OpResult GqJxq(String memberOID, Integer page, String pkversion) {
	// String memberOID = "370518b20a5dca712804025f2e15867a";// (String)
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCannotUseJxqByMemberOID(memberOID, page);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 体验金
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobileExperience")
    @ResponseBody
    public OpResult Experience(String memberOID, String pkversion) {
	// HttpSession session = request.getSession();
	// memberOID = "370518b20a5dca712804025f2e15867a";
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	MobileTYJPoJo mobileTYJ1 = mobileTYJService.tybInfo(memberOID);
	MobileTyj result = new MobileTyj();
	result.setTYDay(mobileTYJ1.getTYDay());
	result.setTYJPrincipalMoney(mobileTYJ1.getTYJPrincipalMoney());
	result.setTYJSY(mobileTYJ1.getTYJSY());
	result.setTYJhdzt(mobileTYJ1.getTYJhdzt());
	result.setTYJnhl(mobileTYJ1.getTYJnhll());
	result.setTyjFlag(mobileTYJ1.getTYJType());
	CustomerTyjDTO tyMoney = new CustomerTyjDTO();
	tyMoney = experienceMoneyService.findMember();

	result.setTyjSize(tyMoney.getExperienceMember().toString());
	if (mobileTYJ1 != null) {
	    return OpResult.getSuccessResult(result);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 邀请好友
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileinvitation")
    @ResponseBody
    public OpResult invitation(String memberOID, Integer page, String pkversion) {
	// memberOID = "370518b20a5dca712804025f2e15867a";
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Pagination<MyInvitationDaoDTO> list = myPrivilegeService.findInvitationRecord(memberOID, page);
	List<MyInvitationDaoDTO> daoDto = new ArrayList<>();
	List<MobileYQHYNewDTO> daoDtoResult = new ArrayList<>();
	daoDto = list.getData();
	for (MyInvitationDaoDTO myInvitationDaoDTO : daoDto) {
	    MobileYQHYNewDTO inviteDTO = new MobileYQHYNewDTO();
	    inviteDTO.setYqNum(list.getTotalNumber().toString());
	    inviteDTO.setName(myInvitationDaoDTO.getName());
	    inviteDTO.setNameCN(myInvitationDaoDTO.getNameCN());
	    inviteDTO.setCjsj(myInvitationDaoDTO.getAddDateTime());
	    inviteDTO.setZcsj(myInvitationDaoDTO.getZcsj());
	    inviteDTO.setMoney(myInvitationDaoDTO.getMoney().toString());
	    daoDtoResult.add(inviteDTO);
	}

	if (list != null) {
	    return OpResult.getSuccessResult(daoDtoResult);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 查询所有未读消息
     * 
     * @return json
     */
    @RequestMapping(value = "/mobilemessage")
    @ResponseBody
    public OpResult FindResultmm(String memberOID, Integer page, String pkversion) {
	// memberOID = "370518b20a5dca712804025f2e15867a";
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	String str = null;
	Pagination<PersonalMessageDTO> list = this.personalMessage.findMessage(page, memberOID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 查询所有已读消息
     * 
     * @return json
     */
    @RequestMapping(value = "/mobilehistoryMessage")
    @ResponseBody
    public OpResult FindResultzz(String memberOID, Integer page, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Pagination<PersonalMessageDTO> list = this.personalMessage.findHistoryMessage(page, memberOID);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 删除未读消息添加到已读中
     * 
     * @return
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileMessageInsert")
    public String FindResultinsert(String OID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.personalMessage.insertMessage(OID);
	return "MessageNotification";

    }

    /**
     * 接受反馈意见
     * 
     * @return
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileOpinionInsert")
    public void MobileOpinionInsert(String memberOID, String contact_way, String opinion_content, String pkversion) {
	try {
	    // 初始化操作类，包括初始化钥匙对集合
	    RsaCodeTool rsac = new RsaCodeTool();
	    try {
		// 对密文进行解密
		memberOID = rsac.serverDecrypt(memberOID, pkversion);
		contact_way = rsac.serverDecrypt(contact_way, pkversion);
		opinion_content = rsac.serverDecrypt(opinion_content, pkversion);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    MobileOpinionDTO mobileOpinionDTO = new MobileOpinionDTO();
	    if (memberOID != null) {
		mobileOpinionDTO.setMemberOID(memberOID);
	    }
	    mobileOpinionDTO.setContact_way(contact_way);
	    mobileOpinionDTO.setOpinion_content(opinion_content);
	    mobileFormOpinionService.InsertOpinion(mobileOpinionDTO);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    /**
     * 可用余额
     * 
     * @return
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileUseMoney")
    @ResponseBody
    public OpResult aviableMoney(String memberOID, String pkversion) {
	try {
	    // 初始化操作类，包括初始化钥匙对集合
	    RsaCodeTool rsac = new RsaCodeTool();
	    try {
		// 对密文进行解密
		memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    String str = null;
	    String kyye = isloginService.islogin(memberOID);
	    return OpResult.getSuccessResult(kyye);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}

    }

    /**
     * 充值记录和提现记录信息
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobilerecharge")
    @ResponseBody
    public OpResult recharge(String memberOID, Integer page, String state, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    state = rsac.serverDecrypt(state, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// memberOID="370518b20a5dca712804025f2e15867a";
	if (state.equals("CZ")) {
	    state = "recharge";
	} else if (state.equals("TX")) {
	    state = "withdrawals";
	}
	if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(state)) {
	    Map<String, Object> map = myCashService.findRechargeRecord(memberOID, state, page);
	    if (map != null) {
		return OpResult.getSuccessResult(map);
	    }
	}
	return OpResult.getFailedResult("查询记录失败");
    }

    /**
     * 资金流水列表查询
     * 
     * @return json
     */
    @RequestMapping(value = "/mobilecashDetial")
    @ResponseBody
    public OpResult FindResultcash(Integer page, String memberOID, String pkversion) {
	String str = null;

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	String strDate = "";
	String endDate = "";
	memberOID = "af40ff530a5dca71695e385f168e9457";
	if (page == null) {
	    page = 1;
	}
	Pagination<PayCustomerBusiness> list = this.myCashService.findMessage(page, memberOID, strDate, endDate);
	List<PayCustomerBusiness> cashlist = list.getData();
	List<MobileCashDetialDTO> cashlistResult = new ArrayList<>();
	for (PayCustomerBusiness pay_CustomerBusiness : cashlist) {

	    String add = "+";
	    String del = "-";
	    String lx = pay_CustomerBusiness.getType();
	    MobileCashDetialDTO cashDTO = new MobileCashDetialDTO();
	    switch (lx) {
	    case "提现":
		cashDTO.setFuhao(del);
		// dto.put("fuhao", del);
		break;
	    case "提现手续费":
		cashDTO.setFuhao(del);
		// dto.put("fuhao", del);
		break;
	    case "充值":
		cashDTO.setFuhao(add);
		// dto.put("fuhao", add);
		break;
	    case "出借":
		cashDTO.setFuhao(del);
		break;
	    case "借款到账":
		cashDTO.setFuhao(add);
		break;
	    case "借款一次性手续费":
		cashDTO.setFuhao(del);
		break;
	    case "回款":
		cashDTO.setFuhao(add);
		break;
	    }
	    cashDTO.setLx(pay_CustomerBusiness.getType());
	    cashDTO.setMoney(pay_CustomerBusiness.getMoney().toString());
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    cashDTO.setAddDateTime(formatter.format(pay_CustomerBusiness.getAddDateTime()));
	    cashDTO.setUpdDateTime(formatter.format(pay_CustomerBusiness.getUpdDateTime()));
	    cashlistResult.add(cashDTO);
	}

	if (list != null) {
	    return OpResult.getSuccessResult(cashlistResult);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}

    }

}
