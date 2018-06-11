package com.izhuantou.third.rpc.impl.memberAgrement;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolMath;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.tool.ToolsDisplay;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.code.CodeContent;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayRepayPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.ContentMappingDTO;
import com.izhuantou.damain.vo.LeanYuBiaoDTO;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.code.CodeContentMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageMainContentMappingMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.user.SequenceDefinitionService;
import com.izhuantou.service.impl.BaseServiceImpl;
import com.izhuantou.third.rpc.api.memberAgrement.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.tsign.ControlSignService;

@Service("memberMemberAgreementService")
public class MemberMemberAgreementServiceImpl extends BaseServiceImpl<MemberMemberAgreement>
	implements MemberMemberAgreementService {
    private static final Logger logger = LoggerFactory.getLogger(MemberMemberAgreementServiceImpl.class);
    @Autowired
    private MemberMemberAgreementMapper memberAgreementDao;
    @Autowired
    private MemberAgreementMapper memberAgreMapper;
    @Autowired
    private MemberMemberMapper userDao;
    @Autowired
    private SequenceDefinitionService sequenceDefinitionService;
    @Autowired
    private ControlSignService controlSignService;
    @Autowired
    private PayCustomerMapper payCustomerMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
    @Autowired
    private WebP2pBiddingExamineMapper biddingExamineMapper;
    @Autowired
    private PayDebitCreditMapper payDebitCreditMapper;
    @Autowired
    private PayTransferReturnMapper payTransferReturnMapper;
    @Autowired
    private PayRepayPlanMapper repayPlanMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
    @Autowired
    private WebP2pPackageMainContentMappingMapper packageMainContentMappingMapper;
    @Autowired
    private CodeContentMapper codeContentMapper;
    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;

    /**
     * 查看协议
     */
    @Override
    public MemberMemberAgreement gainAgreement(MemberAgrennmentDTO memberAgrennmentDTO) {
	String type = memberAgrennmentDTO.getType();
	MemberMemberAgreement agreement = null;
	switch (type) {
	case "2":

	    agreement = memberAgreementDao.gainAgreementXYCK2(memberAgrennmentDTO.getDOID(), "2");

	    break;
	case "3":

	    agreement = memberAgreementDao.gainAgreementXYCK2(memberAgrennmentDTO.getDOID(), "3");

	    break;
	case "4":

	    agreement = memberAgreementDao.gainAgreementXYCK2(memberAgrennmentDTO.getCashPoolOID(), "4");
	    break;
	case "5":

	    agreement = memberAgreementDao.gainAgreementXYCK2(memberAgrennmentDTO.getDOID(), "5");
	    break;
	case "6":

	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "6");

	    break;
	case "7":

	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "7");

	    break;
	case "8":

	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "8");

	    break;
	case "9":

	    agreement = memberAgreementDao.gainAgreementJKZX(memberAgrennmentDTO.getLoanNumber(), "9");

	    break;
	case "11":// 房抵借款协议

	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "11");

	    break;
	case "12":// 房抵借款咨询服务协议

	    agreement = memberAgreementDao.gainAgreementJKZX(memberAgrennmentDTO.getLoanNumber(), "12");

	    break;
	case "13":// 点点债权预回购

	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "13");

	    break;

	case "16":// 以租代购借款协议
	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "16");

	    break;

	case "17":// 以租代购债权转让
	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "17");

	    break;
	case "18":// 以租代购债权预回购
	    agreement = memberAgreementDao.gainAgreementJKXYCK(memberAgrennmentDTO.getBiOID(), "18");
	    break;
	}
	// 协议英文名称
	// String name =agreement.getPdfPath();
	/**
	 * pdf转存图片
	 */
	if (agreement != null) {
	    String pdfPath = agreement.getPdfPath();
	    String pdfPic = agreement.getPdfPic();
	    String signIDs = agreement.getSignIDs();
	    if (StringUtil.isNotEmpty(pdfPath) && StringUtil.isNotEmpty(pdfPic) && StringUtil.isNotEmpty(signIDs)) {
		MemberMemberAgreement MemberMemberAgreement = new MemberMemberAgreement();
		MemberMemberAgreement.setPdfPath(pdfPath);
		MemberMemberAgreement.setPdfPic(pdfPic);
		MemberMemberAgreement.setSignIDs(signIDs);
		return MemberMemberAgreement;
	    }
	}

	return agreement;
    }

    /***
     * 查看协议模板
     */
    @Override
    public MemberAgreement gainMemberAgreement(String type) {
	try {
	    if (StringUtil.isNotEmpty(type)) {
		MemberAgreement memberAgreement = memberAgreMapper.findMemberAgreementByType(type);
		Map<String, String> dto1 = new HashMap<String, String>();
		dto1.put("xybh", "");
		dto1.put("hyzh", "");
		dto1.put("qrsj", "");
		dto1.put("xm", "");
		dto1.put("sfzh", "");
		dto1.put("dz", "");
		dto1.put("lxfs", "");
		dto1.put("yhm", "");
		dto1.put("cjqx", "");
		dto1.put("jkll", "");
		dto1.put("hkfs", "");
		dto1.put("jsrq", "");
		dto1.put("jkr", "");
		dto1.put("jkjexx", "");
		dto1.put("jkjedx", "");
		dto1.put("jkyt", "");
		dto1.put("jkqx", "");
		dto1.put("ksrq", "");
		dto1.put("zwr", "");
		dto1.put("jkxybh", "");
		dto1.put("xymc", "");
		dto1.put("zrrq", "");
		dto1.put("zrll", "");
		String content = memberAgreement.getContent();
		// 替换字符串
		content = ToolString.replaceContent(content, dto1);
		memberAgreement.setContent(content);
		return memberAgreement;
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error("gainMemberAgreement(String type)", e.getMessage());
	    return null;
	}

    }

    /**
     * 注册协议生成 name注册用户时的手机号contractType协议类型
     */
    @Override
    public String gainupdateMemberAgreement(String name, String contractType) {
	try {
	    if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(contractType)) {

		Map<String, Object> remap = new HashMap<String, Object>();
		MemberMember member = userDao.findUserByName(name);
		String memberAccount = member.getMemberAccount();
		// 协议编号
		String xybh = sequenceDefinitionService.gainSequence("new");
		remap.put("xybh", xybh);
		remap.put("hyzh", memberAccount);
		remap.put("qrsj", DateUtils.formatJustDate(new Date().getTime()));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		remap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
		remap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
		remap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
		// 获取模板
		MemberAgreement memberAgreement = memberAgreMapper.findMemberAgreementByType(contractType);

		MemberMemberAgreement arennment = new MemberMemberAgreement();
		String content = memberAgreement.getContent();
		// 替换后的内容
		content = ToolString.replaceContent(content, remap);
		String oid = StringUtil.getUUID();
		arennment.setOID(oid);
		arennment.setMemberOID(member.getOID());
		arennment.setName(memberAgreement.getName());

		arennment.setNameCN(memberAgreement.getNameCN());

		arennment.setContent(content);
		arennment.setBiddingType(memberAgreement.getBiddingType());

		arennment.setContractType(memberAgreement.getContractType());

		arennment.setXybh(xybh);
		// set没完签章 返回pdf路径
		Map<String, String> sign = controlSignService.doSignRegistAgreement(member.getOID(),
			arennment.getContent());
		arennment.setSignIDs(sign.get("signIDs"));
		arennment.setPdfPath(sign.get("pdfPath"));

		int rows = memberAgreementDao.saveMemberAgreement(arennment);
		if (rows == 1) {
		    return String.valueOf(rows);
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("gainupdateMemberAgreement(String name, String contractType, String memberAccount)",
		    e.getMessage());
	    return null;
	}

    }

    @Override
    public int countMemberAgreement(String biddingType, String contractType) {
	int count = 0;
	if (StringUtil.isNotEmpty(biddingType) && StringUtil.isNotEmpty(contractType)) {
	    count = memberAgreementDao.countMemberAgreement(biddingType, contractType);
	}
	return count;
    }

    @Override
    public void addMemberAgreement(Map<String, String> map) {
	try {
	    String strName = map.get("name");
	    String strMemberOID = map.get("memberOID");
	    if (StringUtil.isNotEmpty(strName) && StringUtil.isNotEmpty("strMemberOID")) {
		String[] temp = strName.split("-");
		MemberAgreement agreement = memberAgreMapper.findMemberAgreementByType(strName);
		map.remove("name");
		map.remove("memberOID");
		if (agreement != null) {
		    String strContent = ToolString.replaceContent(agreement.getContent(), map);
		    MemberMemberAgreement memberAgreement = new MemberMemberAgreement();
		    memberAgreement.setBiddingType(map.get("biddingType"));
		    memberAgreement.setContractType(map.get("contractType"));

		    int count = this.countMemberAgreement(map.get("biddingType"), map.get("contractType"));
		    // TODO 时间不明确
		    String name = agreement.getName() + "-" + String.valueOf(new Date()).replace("-", "") + count;

		    /*
		     * memberAgreement.put("content", strContent);
		     * memberAgreement.put("nameCN",
		     * dtoAgreement.get("nameCN"));
		     * memberAgreement.put("memberOID", strMemberOID);
		     * memberAgreement.put("enableTime",
		     * ToolDateTime.gainDateTime()); memberAgreement.put("name",
		     * name); dbControlMemberAgreement.save(memberAgreement);
		     */
		}
	    }
	} catch (Exception e) {
	    logger.error("addMemberAgreement(Map<String, String> map)", e.getMessage());
	}
    }

    @Override
    public void gainMemberXSAgreement(String memberOID, String contractType, String biddingOID, String ckOID) {
	try {
	    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	    MemberMember member = userDao.findUserByOID(memberOID);
	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(biddingOID);
	    WebP2pProductRateInfo rateInfo = productRateInfoMapper.findByOID(novice.getProductRateInfoID());
	    WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper
		    .findByOID(novice.getLoanProductRateInfoID());
	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("new");

	    Map<String, Object> replaceMap = new HashMap<String, Object>();
	    replaceMap.put("xybh", xybh);
	    replaceMap.put("xm", customer.getNameCN());
	    replaceMap.put("sfzh", member.getIdCard());
	    replaceMap.put("dz", member.getAddress());
	    replaceMap.put("lxfs", member.getMobile());
	    replaceMap.put("yhm", member.getMobile());
	    replaceMap.put("cjqx", rateInfo.getProductTerm());
	    // 借款利率
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal hnll = (loanRateInfo.getYearRate()).multiply(b1);
	    replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    // 借款利率+补贴利率=15
	    BigDecimal xsbz = new BigDecimal(15);
	    BigDecimal btll = ((BigDecimal) xsbz.subtract(hnll));
	    replaceMap.put("btll", btll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    String hkfs = rateInfo.getRepaymentType();
	    if (hkfs.equals("EPEI")) {
		replaceMap.put("hkfs", "等本等息");
	    }
	    if (hkfs.equals("BIAP")) {
		replaceMap.put("hkfs", "按月返息");
	    }
	    if (hkfs.equals("OPI")) {
		replaceMap.put("hkfs", "一次性还本付息");
	    }

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);

	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    MemberMemberAgreement magrement = new MemberMemberAgreement();
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setMemberOID(memberOID);
	    magrement.setBiddingOID(biddingOID);
	    magrement.setXybh(xybh);
	    magrement.setCkOID(ckOID);
	    magrement.setState("1");
	    magrement.setOID(StringUtil.getUUID());
	    // 进行电子签章 返回签署记录和存储的pdf文件名 ---vastsea 20171012
	    Map<String, String> sign = controlSignService.doSignFWAgreement(magrement.getMemberOID(),
		    magrement.getContent(), "xsb");
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));
	    memberAgreementDao.saveMemberAgreement(magrement);

	} catch (Exception e) {
	    logger.error("addMemberAgreement(Map<String, String> map)", e.getMessage());
	}
    }

    @Override
    public void gainMemberxsJKAgreement(String contractType, String biddingOID) {
	try {

	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(biddingOID);

	    WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper
		    .findByOID(novice.getLoanProductRateInfoID());

	    WebP2pBiddingExamine Examine = biddingExamineMapper.findByMemberOID(novice.getMemberOID());

	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("JK");

	    Map<String, Object> replaceMap = new HashMap<String, Object>();

	    replaceMap.put("xybh", xybh);
	    replaceMap.put("jkbh", novice.getLoanNumber());
	    replaceMap.put("jkr", Examine.getRealName());
	    replaceMap.put("dz", Examine.getDizhi());
	    replaceMap.put("lxfs", Examine.getMobile());
	    replaceMap.put("sfzh", Examine.getIdCard());
	    replaceMap.put("yhm", Examine.getMobile());
	    replaceMap.put("jkjexx", novice.getLoanAmount());
	    BigDecimal aBigDecimal = novice.getLoanAmount();
	    double d = aBigDecimal.doubleValue();
	    String jkjedx = ToolMath.toBigMoney(d);
	    replaceMap.put("jkjedx", jkjedx);
	    replaceMap.put("jkyt", Examine.getLoanuse());
	    replaceMap.put("jkqx", loanRateInfo.getTerm());
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
	    replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    replaceMap.put("ksrq", ToolDateTime.gainDate());
	    Date jsrq = ToolDateTime.addMonth(ToolDateTime.gainDate(), loanRateInfo.getTerm());
	    replaceMap.put("jsrq", jsrq);

	    String conten = "";
	    List<PayDebitCredit> dbList = payDebitCreditMapper.findByCondition(biddingOID);
	    for (PayDebitCredit cjr : dbList) {
		PayCustomer customer = payCustomerMapper.findByMemberOID(cjr.getOutMemberOID());
		String name = customer.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		String name2 = ToolsDisplay.getFullNameStr(customer.getNameCN());
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + cjr.getPrincipalMoney()
			+ "</td></tr>";
	    }
	    replaceMap.put("data0", conten);

	    List<PayRepayPlan> repayList = repayPlanMapper.findByBusinessOIDAndMemberOID(novice.getMemberOID(),
		    biddingOID);

	    String hkjk = "";
	    int hkqs = 0;
	    for (PayRepayPlan repay : repayList) {
		hkqs++;
		String repayDate = DateUtils.formatJustDate(repay.getRepayDate().getTime());
		BigDecimal bj = repay.getPrincipalMoney();
		BigDecimal ll = repay.getInterestMoney();
		BigDecimal jhhk = bj.add(ll);
		hkjk += "<tr><td>" + hkqs + "</td>" + "<td>" + jhhk + "</td>" + "<td>" + repayDate + "</td></tr>";
	    }
	    replaceMap.put("data1", hkjk);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
	    String loanNumber = novice.getLoanNumber();

	    MemberMemberAgreement magrement = new MemberMemberAgreement();

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);

	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setXybh(xybh);
	    magrement.setLoanNumber(loanNumber);
	    magrement.setState("1");
	    magrement.setMemberOID(novice.getMemberOID());
	    magrement.setBiddingOID(biddingOID);
	    magrement.setOID(StringUtil.getUUID());
	    Map<String, String> sign = controlSignService.doSignXDJKAgreement(magrement.getMemberOID(),
		    magrement.getContent());
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));
	    memberAgreementDao.saveMemberAgreement(magrement);
	} catch (Exception e) {
	    logger.error("gainMemberxsJKAgreement(String contractType, String biddingOID)", e.getMessage());
	}

    }

    @Override
    public void gainmemberzqhgXSAgreement(String contractType, String biddingOID) {
	try {
	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(biddingOID);
	    WebP2pProductRateInfo rateInfo = productRateInfoMapper.findByOID(novice.getProductRateInfoID());
	    WebP2pBiddingExamine Examine = biddingExamineMapper.findByMemberOID(novice.getMemberOID());
	    PayCustomer customer = payCustomerMapper.findByMemberOID(novice.getMemberOID());
	    String conten = "";
	    List<PayDebitCredit> dbList = payDebitCreditMapper.findByCondition(biddingOID);
	    for (PayDebitCredit credit : dbList) {
		PayCustomer outcustomer = payCustomerMapper.findByMemberOID(credit.getOutMemberOID());
		String name = outcustomer.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		String name2 = ToolsDisplay.getFullNameStr(outcustomer.getNameCN());
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + credit.getPrincipalMoney()
			+ "</td></tr>";

	    }
	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("ZQYHG");

	    Map<String, Object> replaceMap = new HashMap<String, Object>();
	    replaceMap.put("data1", conten);
	    replaceMap.put("xybh", xybh);
	    replaceMap.put("jkr", Examine.getRealName());

	    CodeContent code = codeContentMapper.findCodeContent(customer.getBankCityCode(), "cncity");

	    replaceMap.put("dz", code.getNameCN());

	    replaceMap.put("jkbh", novice.getLoanNumber());
	    replaceMap.put("sfzh", Examine.getIdCard());

	    replaceMap.put("jkjexx", "");
	    BigDecimal aBigDecimal = novice.getLoanAmount();
	    double d = aBigDecimal.doubleValue();
	    String jkjedx = ToolMath.toBigMoney(d);
	    replaceMap.put("jkjedx", jkjedx);
	    replaceMap.put("jkyt", Examine.getLoanuse());
	    replaceMap.put("jkqx", rateInfo.getLoanTerm());
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal hnll = rateInfo.getYearRate().multiply(b1);
	    replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    replaceMap.put("ksrq", ToolDateTime.gainDate());
	    Date jsrq = ToolDateTime.addDay(ToolDateTime.gainDate(), 15);
	    replaceMap.put("jsrq", jsrq);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
	    String loanNumber = novice.getLoanNumber();

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);
	    MemberMemberAgreement magrement = new MemberMemberAgreement();

	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setXybh(xybh);

	    magrement.setMemberOID(novice.getMemberOID());
	    magrement.setBiddingOID(biddingOID);
	    magrement.setLoanNumber(loanNumber);
	    magrement.setState("1");
	    magrement.setOID(StringUtil.getUUID());
	    Map<String, String> sign = controlSignService.doSignYQZQYSGAgreement(magrement.getMemberOID(),
		    magrement.getContent());
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));

	    memberAgreementDao.saveMemberAgreement(magrement);
	} catch (Exception e) {
	    logger.error("gainmemberzqhgXSAgreement(String contractType, String biddingOID)", e.getMessage());
	}

    }

    @Override
    public void gainmemberTbzZZTZQZRAgreement(String contractType, String biddingOID) {
	try {
	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(biddingOID);
	    // 声明转让本金、垫付利息、转让价款
	    BigDecimal advanceInterest = new BigDecimal(0);
	    BigDecimal zrbj = new BigDecimal(0);
	    BigDecimal zrzc = new BigDecimal(0);
	    zrzc = novice.getBiddingAmount();

	    // 以下代码主要生成附表一内容
	    List<PayTransferReturn> transferReturn = payTransferReturnMapper.findByBusinessOIDDifferent(biddingOID);

	    PayTransferReturn trans = null;
	    String conten = "";
	    for (PayTransferReturn transfer : transferReturn) {
		// TODO 老系统用迭代覆盖取值
		PayDebitCredit dbList = payDebitCreditMapper.queryByPKOid(transfer.getDebitCreditOID());
		zrbj = dbList.getPrincipalMoney();
		// TODO 老系统用迭代覆盖取值
		trans = transfer;

		PayCustomer outcustomer = payCustomerMapper.findByMemberOID(transfer.getOutMemberOID());
		String name = outcustomer.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		String name2 = ToolsDisplay.getFullNameStr(outcustomer.getNameCN());
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>"
			+ transfer.getPrincipalMoney() + "</td>" + "<td>" + transfer.getAdvanceInterest() + "</td>"
			+ "<td>" + transfer.getMoney() + "</td>" + "</tr>";

	    }
	    // **************计算转让方表格信息**转让本金****垫付利息***转让价款***************************************************************
	    advanceInterest = zrzc.subtract(zrbj);
	    MemberMember member = userDao.findUserByOID(trans.getInMemberOID());
	    PayDebitCredit debitCredit = null;
	    if (StringUtil.isNotEmpty(trans.getDebitCreditOID())) {
		if (payDebitCreditMapper.queryByPKOid(trans.getDebitCreditOID()) == null) {
		    debitCredit = payDebitCreditMapper.queryByPKOid(trans.getDebitCreditOIDNew());
		} else {
		    debitCredit = payDebitCreditMapper.queryByPKOid(trans.getDebitCreditOID());
		}
	    }
	    String jkbOID = debitCredit.getBusinessOID();
	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("ZQZR");
	    Map<String, Object> replaceMap = new HashMap<String, Object>();
	    replaceMap.put("data0", conten);
	    replaceMap.put("xybh", xybh);
	    replaceMap.put("zrf", "ZTW" + member.getName());// 甲方 格式：ZTW+手机号
	    CodeContent code = codeContentMapper.findCodeContent(member.getCity(), "cncity");

	    replaceMap.put("jfdz", code.getNameCN());// 甲方地址
	    replaceMap.put("fddbr", member.getNameCN());// 甲方真实姓名
	    replaceMap.put("sfzh", member.getIdCard());
	    replaceMap.put("jkbOID", jkbOID);
	    replaceMap.put("zrzc", zrzc.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让价款：转让本金+垫付利息
	    replaceMap.put("zrbj", zrbj.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让剩余本金
	    replaceMap.put("dflx", advanceInterest.setScale(2, BigDecimal.ROUND_HALF_UP));

	    replaceMap.put("zrrq", DateUtils.formatJustDate(novice.getUpdDateTime().getTime()));
	    BigDecimal sxf = new BigDecimal(0.00);
	    replaceMap.put("zrsxf", sxf.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让手续费

	    BigDecimal b2 = new BigDecimal(100);
	    BigDecimal zrll = (trans.getCreditRate()).multiply(b2);

	    replaceMap.put("zrll", zrll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    String hkfs = trans.getCreditType();
	    if (hkfs.equals("BIAP")) {
		replaceMap.put("hkfs", "按月还息到期还本");
	    } else {
		replaceMap.put("hkfs", "等本等息");
	    }

	    LeanYuBiaoDTO jkrInfo = memberAgreementDao.findByYBInfoByJKXY(novice.getLoanNumber());
	    String memberAccount = userDao.findUserByOID(jkrInfo.getJkrOID()).getMemberAccount();

	    replaceMap.put("jkrsjh", memberAccount);

	    replaceMap.put("jkrxm", jkrInfo.getJkrxm());
	    replaceMap.put("jkrsfzh", jkrInfo.getJkrsfzh());
	    String loanDay = DateUtils.formatJustDate(jkrInfo.getLoanDay().getTime());
	    String[] JKTime = loanDay.split("-");
	    replaceMap.put("jkYear", JKTime[0]);
	    replaceMap.put("jkMonth", JKTime[1]);
	    replaceMap.put("jkDay", JKTime[2]);

	    String jkxybh = memberAgreementDao.findByAgreementJKXYBHCK(jkrInfo.getBiddingOID(), "6");
	    replaceMap.put("jkxybh", jkxybh);

	    /////// bob 10.23 电子签章更新，显示签署协议的年月日
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);
	    MemberMemberAgreement magrement = new MemberMemberAgreement();

	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setMemberOID(novice.getMemberOID());
	    magrement.setBiddingOID(biddingOID);
	    magrement.setXybh(xybh);
	    magrement.setState("1");
	    magrement.setOID(StringUtil.getUUID());
	    Map<String, String> sign = controlSignService.doSignZQZRAgreement(trans.getInMemberOID(),
		    magrement.getContent());
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));
	    memberAgreementDao.saveMemberAgreement(magrement);
	} catch (Exception e) {
	    logger.error("gainmemberTbzZZTZQZRAgreement(String contractType, String biddingOID)", e.getMessage());
	}
    }

    @Override
    public void gainmemberxszqhgZZTAgreement(String contractType, String biddingOID) {
	try {

	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(biddingOID);
	    WebP2pProductRateInfo rateInfo = productRateInfoMapper.findByOID(novice.getProductRateInfoID());
	    WebP2pBiddingExamine Examine = biddingExamineMapper.findByloanNumber(novice.getLoanNumber());

	    String conten = "";
	    List<PayDebitCredit> debitCredit = payDebitCreditMapper.findByCondition(biddingOID);
	    for (PayDebitCredit debc : debitCredit) {
		PayCustomer outcustomer = payCustomerMapper.findByMemberOID(debc.getOutMemberOID());
		String name = outcustomer.getName();
		String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

		String name2 = ToolsDisplay.getFullNameStr(outcustomer.getNameCN());
		conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + debc.getPrincipalMoney()
			+ "</td></tr>";
	    }
	    PayCustomer customer = payCustomerMapper.findByMemberOID(novice.getMemberOID());

	    Map<String, Object> replaceMap = new HashMap<String, Object>();
	    replaceMap.put("data1", conten);
	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("ZQYHG");
	    replaceMap.put("xybh", xybh);
	    replaceMap.put("jkr", Examine.getRealName());

	    CodeContent code = codeContentMapper.findCodeContent(customer.getBankCode(), "cncity");
	    replaceMap.put("dz", code.getNameCN());
	    replaceMap.put("sfzh", Examine.getIdCard());
	    replaceMap.put("jkjexx", "");
	    BigDecimal aBigDecimal = new BigDecimal(0);
	    if (novice.getLoanAmount() == null) {
		aBigDecimal = novice.getBiddingAmount();
	    } else {
		aBigDecimal = novice.getLoanAmount();
	    }
	    double d = aBigDecimal.doubleValue();
	    String jkjedx = ToolMath.toBigMoney(d);
	    replaceMap.put("jkjedx", jkjedx);
	    replaceMap.put("jkyt", Examine.getLoanuse());
	    replaceMap.put("jkqx", rateInfo.getLoanTerm());
	    replaceMap.put("jkll", rateInfo.getYearRate());
	    replaceMap.put("ksrq", ToolDateTime.gainDate());
	    String loanNumber = novice.getLoanNumber();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);
	    MemberMemberAgreement magrement = new MemberMemberAgreement();

	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setXybh(xybh);
	    magrement.setMemberOID(Examine.getMemberOID());
	    magrement.setBiddingOID(biddingOID);
	    magrement.setLoanNumber(loanNumber);
	    magrement.setState("1");
	    magrement.setOID(StringUtil.getUUID());
	    Map<String, String> sign = controlSignService.doSignYQZQYSGAgreement(magrement.getMemberOID(),
		    magrement.getContent());
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));
	    memberAgreementDao.saveMemberAgreement(magrement);
	} catch (Exception e) {
	    logger.error("gainmemberxszqhgZZTAgreement(String contractType, String biddingOID)", e.getMessage());
	}
    }

    @Override
    public void gainMemberHHTAgreement(String memberOID, String contractType, String biddingOID, String ckOID) {
	try {
	    System.err.println(Thread.currentThread().getName());
	    MemberMember member = userDao.findUserByOID(memberOID);

	    WebP2pPackageBiddingMainRuning biddingMain = packageBiddingMainRuningMapper.findByOID(biddingOID);
	    WebP2pProductRateInfo rateInfo = null;
	    if (biddingMain != null) {
		rateInfo = productRateInfoMapper.findByOID(biddingMain.getProductRateInfoID());
	    } else {
		ContentMappingDTO mapping = packageMainContentMappingMapper.findBycontentOID(biddingOID);
		rateInfo = new WebP2pProductRateInfo();
		rateInfo.setProductTerm(mapping.getProductTerm());
		rateInfo.setYearRate(mapping.getYearRate());
		rateInfo.setRepaymentType(mapping.getRepaymentType());
	    }
	    Map<String, Object> replaceMap = new HashMap<String, Object>();

	    // 协议编号
	    String xybh = sequenceDefinitionService.gainSequence("HHT");
	    replaceMap.put("xybh", xybh);
	    replaceMap.put("xm", member.getNameCN());
	    replaceMap.put("sfzh", member.getIdCard());
	    replaceMap.put("dz", member.getAddress());
	    replaceMap.put("lxfs", member.getMobile());
	    replaceMap.put("yhm", member.getMobile());
	    replaceMap.put("cjqx", rateInfo.getProductTerm());
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal hnll = rateInfo.getYearRate().multiply(b1);
	    replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    String hkfs = rateInfo.getRepaymentType();
	    if (hkfs.equals("EPEI")) {
		replaceMap.put("hkfs", "等本等息");
	    }
	    if (hkfs.equals("BIAP")) {
		replaceMap.put("hkfs", "按月返息");
	    }
	    if (hkfs.equals("OPI")) {
		replaceMap.put("hkfs", "一次性还本付息");
	    }

	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date(System.currentTimeMillis()));
	    replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
	    replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
	    replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));

	    MemberAgreement agrement = memberAgreMapper.findMemberAgreementByType(contractType);
	    MemberMemberAgreement magrement = new MemberMemberAgreement();
	    String content = agrement.getContent();
	    String tz = ToolString.replaceContent(content, replaceMap);
	    magrement.setContent(tz);
	    magrement.setName(agrement.getName());
	    magrement.setNameCN(agrement.getNameCN());
	    magrement.setBiddingType(agrement.getBiddingType());
	    magrement.setContractType(agrement.getContractType());
	    magrement.setMemberOID(memberOID);
	    magrement.setBiddingOID(biddingOID);
	    magrement.setXybh(xybh);
	    magrement.setState("1");
	    magrement.setCkOID(ckOID);
	    magrement.setOID(StringUtil.getUUID());
	    Map<String, String> sign = controlSignService.doSignFWAgreement(magrement.getMemberOID(),
		    magrement.getContent(), "hht");
	    magrement.setSignIDs(sign.get("signIDs"));
	    magrement.setPdfPath(sign.get("pdfPath"));
	    memberAgreementDao.saveMemberAgreement(magrement);
	} catch (Exception e) {
	    logger.error(
		    "gainMemberHHTAgreement(String memberOID, String contractType, String biddingOID, String ckOID)",
		    e.getMessage());
	}

    }

    @Override
    public void gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID) {
	/*
	 * WebP2pPackageBiddingMainContentRuning contentRuning=
	 * packageBiddingMainContentRuningMapper.findByOID(biddingOID);
	 * 
	 * String loanProductInfoOID =contentRuning.getLoanProductRateInfoID();
	 * WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper
	 * .findByOID(loanProductInfoOID);
	 * 
	 * String bidLoanNumber = contentRuning.getLoanNumber(); String
	 * financetransfertype =contentRuning.getFinancetransfertype(); String
	 * jkrMemberOID = ""; String jkrMemberOIDTwo = ""; if
	 * (financetransfertype.equals("financetransfer")) {
	 * List<WebP2pPackageBiddingMainContentRuning>
	 * content=packageBiddingMainContentRuningMapper.findByLoanNum(
	 * bidLoanNumber); jkrMemberOID=
	 * content.get(content.size()-1).getMemberOID(); } int term =
	 * loanRateInfo.getTerm(); if(StringUtil.isNotEmpty(jkrMemberOID)){
	 * 
	 * }
	 */

    }

    @Override
    public void gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID) {
	// TODO Auto-generated method stub

    }

    @Override
    public void gainmemberzqhgHHTAgreement(String contractType, String biddingOID) {
	// TODO Auto-generated method stub

    }

    @Override
    public void gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID) {
	// TODO Auto-generated method stub

    }

    @Override
    public void gainMemberHHTNRBJKAgreement(String contractType, String biddingOID) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<MemberMemberAgreement> queryByPage(Integer page, Integer rows) {
	// TODO Auto-generated method stub
	return null;
    }

}
