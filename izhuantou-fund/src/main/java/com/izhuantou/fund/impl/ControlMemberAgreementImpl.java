package com.izhuantou.fund.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolMath;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.tool.ToolsDisplay;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.flow.CarLoanMemberInfoBaseDTO;
import com.izhuantou.damain.webp2p.WebP2pCarLoanCenterInfo;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.fund.api.ControlAgreement;
import com.izhuantou.fund.api.ControlCarLoanCenterInfo;
import com.izhuantou.fund.api.ControlCustomer;
import com.izhuantou.fund.api.ControlDebitCredit;
import com.izhuantou.fund.api.ControlLoanProductRateInfo;
import com.izhuantou.fund.api.ControlMember;
import com.izhuantou.fund.api.ControlMemberAgreement;
import com.izhuantou.fund.api.ControlPackageBiddingMainContent;
import com.izhuantou.fund.api.ControlRepayPlan;

/**
 * 投标相关接口的实现类
 *
 * @author fucheng
 * @date 2018-03-15
 */
@Service("controlMemberAgreement")
public class ControlMemberAgreementImpl extends BaseServiceImpl<MemberAgreement> implements ControlMemberAgreement {

    @Autowired
    private ControlCustomer controlCustomer;
    @Autowired
    private ControlAgreement controlAgreement;
    @Autowired
    private ControlDebitCredit controlDebitCredit;
    @Autowired
    private ControlPackageBiddingMainContent controlPackageBiddingMainContent;
    @Autowired
    private ControlCarLoanCenterInfo controlCarLoanCenterInfo;
    @Autowired
    private ControlRepayPlan controlRepayPlan;
    @Autowired
    private ControlLoanProductRateInfo controlLoanProductRateInfo;
    @Autowired
    private ControlMember controlMember;

    @Override
    public void gainMemberHHTAgreement(String memberOID, String contractType, String biddingOID, String ckOID) {

    }

    @Override
    public void gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID) {
	try {
	    // TODO 一些参数无法进行封装 需要对实体和数据库进行数据核实
	    Map<String, Object> aDto = new HashMap<>();
	    WebP2pCarLoanCenterInfo jkyh = new WebP2pCarLoanCenterInfo();
	    WebP2pPackageBiddingMainContentRuning bdxx = new WebP2pPackageBiddingMainContentRuning();
	    MemberAgreement jgDto = new MemberAgreement();
	    List<PayDebitCredit> bidding = controlDebitCredit.gainDebitCreditByBusinessOID(biddingOID);
	    List<WebP2pPackageBiddingMainContentRuning> eDto = this.controlPackageBiddingMainContent
		    .findByContentAndLoanPTOID(biddingOID);
	    if (eDto != null && eDto.size() > 0) {
		bdxx = eDto.get(0);
	    }
	    List<CarLoanMemberInfoBaseDTO> yh1 = this.controlCarLoanCenterInfo
		    .gainCarLoanMemberInfoBase((String) bdxx.getMemberOID());

	    if (yh1 != null && yh1.size() > 0) {
		// jkyh = yh1.get(0);
	    }
	    // InterfaceSequenceService interfaceSequenceService =
	    // ServiceFactory.instanceSequenceService(this);
	    // String xybh = interfaceSequenceService.gainSequence("JK");
	    // aDto.put("xybh", xybh);
	    aDto.put("jkbh", bdxx.getLoanNumber());
	    // TODO 参数封装问题
	    // aDto.put("jkr", jkyh.get("realName"));
	    // aDto.put("lxfs", jkyh.get("mobile"));
	    // aDto.put("yhm", jkyh.get("mobile"));
	    // aDto.put("sfzh", jkyh.get("idCard"));// 身份证号
	    aDto.put("jkjexx", bdxx.getApplyAmount());// 借款金额
	    BigDecimal aBigDecimal = (BigDecimal) bdxx.getApplyAmount();
	    double d = aBigDecimal.doubleValue();
	    String jkjedx = ToolMath.toBigMoney(d);
	    aDto.put("jkjedx", jkjedx);
	    // aDto.put("jkyt", jkyh.get("loanuse"));// 借款用途
	    // aDto.put("jkqx", bdxx.get("term"));// 借款期限
	    // aDto.put("cjh", jkyh.get("cjh"));// 车架号
	    // aDto.put("cph", jkyh.get("cph"));// 车牌号
	    // aDto.put("fdjh", jkyh.get("fdjh"));// 发动机号
	    BigDecimal b1 = new BigDecimal(100);
	    // BigDecimal hnll = ((BigDecimal)
	    // bdxx.get("yearRate")).multiply(b1);
	    // aDto.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));//
	    // 借款年利率
	    aDto.put("ksrq", ToolDateTime.gainDate());// 借款开始日期
	    // java.sql.Date jsrq =
	    // ToolDateTime.addMonth(ToolDateTime.gainDate(), (Integer)
	    // bdxx.get("term"));// 借款结束日期
	    // aDto.put("jsrq", jsrq);
	    String loanNumber = (String) bdxx.getLoanNumber();
	    String conten = "";
	    List<PayDebitCredit> cjr = this.controlDebitCredit.gainDebitCreditByBusinessOID(biddingOID);
	    for (PayDebitCredit dto : cjr) {
		PayCustomer cjrxx = controlCustomer.gainCustomerByMemberOID((String) dto.getOutMemberOID());
		String name = (String) cjrxx.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		// 出借人 terry(20170512)
		String name2 = ToolsDisplay.getFullNameStr(String.valueOf(cjrxx.getNameCN()));
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + dto.getPrincipalMoney()
			+ "</td></tr>";
	    }
	    aDto.put("data0", conten);
	    /////// bob 10.23 电子签章更新，显示签署协议的年月日
	    /////// /////////////////////////////////////////////////////////////
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    aDto.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    aDto.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    aDto.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
	    String hkjk = "";
	    int hkqs = 0;
	    List<PayRepayPlan> hk = this.controlRepayPlan.findRepayPlan((String) bdxx.getMemberOID(), biddingOID);
	    for (PayRepayPlan dto : hk) {
		hkqs++;
		BigDecimal bj = (BigDecimal) dto.getPrincipalMoney();
		BigDecimal ll = (BigDecimal) dto.getInterestMoney();
		BigDecimal jhhk = bj.add(ll);
		hkjk += "<tr><td>" + hkqs + "</td>" + "<td>" + jhhk + "</td>" + "<td>" + dto.getRepayDate()
			+ "</td></tr>";
	    }
	    aDto.put("data1", hkjk);
	    List<MemberAgreement> re = this.controlAgreement.findBycontractType(contractType);
	    for (MemberAgreement dto : re) {
		String content = (String) dto.getContent();
		String tz = ToolString.replaceContent(content, aDto);
		jgDto.setContent(tz);
		jgDto.setName(dto.getName());
		jgDto.setNameCN(dto.getNameCN());
		jgDto.setBiddingType(dto.getBiddingType());
		jgDto.setContractType(contractType);
		// jgDto.put("xybh", xybh);
		// jgDto.setloanNumber loanNumber);
		// jgDto.put("state", "1");
		// jgDto.put("biddingOID", biddingOID);
		// jgDto.put("memberOID", bdxx.getMemberOID());
	    }
	    // 进行电子签章 返回签署记录和存储的pdf文件名 ---vastsea 20171012
	    // TODO 电子签名
	    // DTO sign = controlSign.doSignXDJKAgreement((String)
	    // jgDto.get("memberOID"), (String) jgDto.get("content"));
	    // jgDto.put("signIDs", (String) sign.get("signIDs"));
	    // jgDto.put("pdfPath", (String) sign.get("pdfPath"));
	    // 进行电子签章 返回签署记录和存储的pdf文件名 ---vastsea 20171012
	    this.save(jgDto);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID) {
	try {
	    WebP2pPackageBiddingMainContentRuning bdxx = new WebP2pPackageBiddingMainContentRuning();
	    WebP2pCarLoanCenterInfo jkyh = new WebP2pCarLoanCenterInfo();
	    List<WebP2pPackageBiddingMainContentRuning> eDto = this.controlPackageBiddingMainContent
		    .findPackageBiddingMainContentRuningByOID(biddingOID);
	    if (eDto != null && eDto.size() > 0) {
		bdxx = eDto.get(0);
	    }
	    String jkrMemberOID = "";
	    String jkrMemberOIDTwo = "";
	    WebP2pPackageBiddingMainContentRuning natureBid = new WebP2pPackageBiddingMainContentRuning();
	    WebP2pPackageBiddingMainContentRuning temp = this.controlPackageBiddingMainContent.queryByid(biddingOID);
	    String loanProductInfoOID = (String) temp.getLoanProductRateInfoID();
	    String bidLoanNumber = (String) temp.getLoanNumber();
	    String financetransfertype = (String) temp.getFinancetransfertype();
	    if (financetransfertype.equals("financetransfer")) {
		List<WebP2pPackageBiddingMainContentRuning> tempNative = this.controlPackageBiddingMainContent
			.findByLoanNum(bidLoanNumber);
		for (WebP2pPackageBiddingMainContentRuning dto : tempNative) {
		    natureBid = dto;
		}
		jkrMemberOID = (String) natureBid.getMemberOID();
	    }
	    // ----------保理费率------------
	    WebP2pLoanProductRateInfo loanProductInfo = this.controlLoanProductRateInfo.queryByid(loanProductInfoOID);
	    int term = (int) loanProductInfo.getTerm();
	    List<CarLoanMemberInfoBaseDTO> yh1 = new ArrayList<>();
	    if (jkrMemberOID.equals("")) {
		yh1 = this.controlCarLoanCenterInfo.gainCarLoanMemberInfoBase((String) bdxx.getMemberOID());
		jkrMemberOIDTwo = (String) bdxx.getMemberOID();
	    } else {
		yh1 = controlCarLoanCenterInfo.gainCarLoanMemberInfoBase(jkrMemberOID);
		jkrMemberOIDTwo = jkrMemberOID;
	    }
	    MemberMember tempForCity = this.controlMember.queryByid(jkrMemberOIDTwo);
	    for (CarLoanMemberInfoBaseDTO dto : yh1) {
		// jkyh.add(dto);
	    }
	    Map<String, Object> aDto = new HashMap<>();
	    MemberAgreement jgDto = new MemberAgreement();
	    List<PayCustomer> dz = new ArrayList<>();
	    String conten = "";
	    List<PayDebitCredit> cjr = controlDebitCredit.gainDebitCreditByBusinessOID(biddingOID);
	    for (PayDebitCredit dto : cjr) {
		PayCustomer cjrxx = controlCustomer.gainCustomerByMemberOID((String) dto.getOutMemberOID());
		String name = (String) cjrxx.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		// 出借人脱敏 terry(20170512)
		String name2 = ToolsDisplay.getFullNameStr(String.valueOf(cjrxx.getNameCN()));
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + dto.getPrincipalMoney()
			+ "</td></tr>";
	    }

	    aDto.put("data1", conten);
	    List<PayCustomer> aCollection = this.controlCustomer.gainCustomersByMemberOID(bdxx.getMemberOID());

	    for (PayCustomer dto : aCollection) {
		dz.add(dto);
	    }
	    // InterfaceSequenceService interfaceSequenceService =
	    // ServiceFactory.instanceSequenceService(this);
	    // String xybh = interfaceSequenceService.gainSequence("ZQYHG ");
	    // aDto.put("xybh", xybh);
	    // aDto.put("jkr", jkyh.get("realName"));
	    // InterfaceCodeService interfaceCodeService =
	    // ServiceFactory.instanceCodeService(this);
	    // DTBCodeSuitMulti dtbCodeSuitMulti = new DTBCodeSuitMulti();
	    // dtbCodeSuitMulti.assignCode("city",
	    // interfaceCodeService.gainCode("cncity"));
	    // tempForCity.setReplaceData(dtbCodeSuitMulti);
	    aDto.put("dz", tempForCity.getCity());
	    // aDto.put("sfzh", jkyh.get("idCard"));
	    BigDecimal aBigDecimal = new BigDecimal(0);
	    if ((bdxx.getApplyAmount()) == null) {
		aBigDecimal = (BigDecimal) bdxx.getHoldingAmount();
	    } else {
		aBigDecimal = (BigDecimal) bdxx.getApplyAmount();
	    }
	    double d = aBigDecimal.doubleValue();
	    String jkjedx = ToolMath.toBigMoney(d);
	    // ---------保理费率---------
	    switch (term) {
	    case 6:
		aDto.put("blfl", "0.5");
		break;
	    case 12:
		aDto.put("blsxf", "0.65");
		break;
	    case 18:
		aDto.put("blsxf", "0.80");
		break;
	    case 24:
		aDto.put("blsxf", "0.95");
		break;
	    case 36:
		aDto.put("blsxf", "1.10");
		break;
	    default:
		break;
	    }

	    BigDecimal b1 = new BigDecimal(100);
	    // BigDecimal hnll = ((BigDecimal)
	    // bdxx.get("yearRate")).multiply(b1);
	    aDto.put("ksrq", ToolDateTime.gainDate());
	    java.sql.Date jsrq = ToolDateTime.addDay(ToolDateTime.gainDate(), 15);
	    String loanNumber = (String) bdxx.getLoanNumber();
	    /////// bob 10.23 电子签章更新，显示签署协议的年月日
	    /////// /////////////////////////////////////////////////////////////
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    aDto.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    aDto.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    aDto.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
	    List<MemberAgreement> re = this.findBycontractType(contractType);
	    for (MemberAgreement dto : re) {
		String content = (String) dto.getContent();
		String tz = ToolString.replaceContent(content, aDto);
		jgDto.setContent(tz);
		;
		jgDto.setName(dto.getName());
		jgDto.setNameCN(dto.getNameCN());
		jgDto.setBiddingType(dto.getBiddingType());
		jgDto.setContractType(dto.getContractType());
		// jgDto.put("xybh", xybh);
		// jgDto.put("memberOID", jkyh.get("memberOID"));
		// jgDto.put("biddingOID", biddingOID);
		// jgDto.put("loanNumber", loanNumber);
		// jgDto.put("state", "1");
	    }
	    // 进行电子签章 返回签署记录和存储的pdf文件名 ---vastsea 20171012
	    // DTO sign = controlSign.doSignYQZQYSGAgreement((String)
	    // jgDto.get("memberOID"),
	    // (String) jgDto.get("content"));
	    // jgDto.put("signIDs", (String) sign.get("signIDs"));
	    // jgDto.put("pdfPath", (String) sign.get("pdfPath"));
	    // 进行电子签章 返回签署记录和存储的pdf文件名 ---vastsea 20171012
	    this.save(jgDto);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void gainMemberHHTNRBJKAgreement(String contractType, String biddingOID) {
	// TODO 参数太乱，需要时间整理，理清数据和业务流程

    }

    private List<MemberAgreement> findBycontractType(String contractType) {
	try {
	    MemberAgreement memberAgreement = new MemberAgreement();
	    memberAgreement.setContractType(contractType);
	    return this.queryListByWhere(memberAgreement);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public void gainmemberzqhgHHTAgreement(String string, String realBiddingOID) {
	// TODO 参数太乱，需要时间整理，理清数据和业务流程

    }

    @Override
    public void gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID) {
	// TODO 参数太乱，需要时间整理，理清数据和业务流程

    }

    @Override
    public void gainMemberXSAgreement(String memberOID, String contractType, String biddingOID, String ckOID) {
	// TODO 未完成
	try {
	    PayCustomer memberXX = controlCustomer.gainCustomerByMemberOID(memberOID);
	    MemberMember xx = controlMember.findMemberByOID(memberOID);

	    // DTOCollection bidding =
	    // dbControlNoviceBiddingRuning.findByNoviceBiddingRuningOID(biddingOID);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
}
