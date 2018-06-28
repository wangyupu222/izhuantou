package com.izhuantou.third.rpc.impl.memberagrement;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
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
import com.izhuantou.damain.p2p.FileInfo;
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
import com.izhuantou.damain.vo.flow.CarLoanMemberInfoBaseDTO;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.code.CodeContentMapper;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayRepayPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.user.MemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pCarLoanCenterInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageMainContentMappingMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.user.SequenceDefinitionService;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.tsign.ControlSignService;
import com.izhuantou.third.rpc.impl.BaseServiceImpl;
import com.izhuantou.third.rpc.impl.ReadPropertiesl;

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
	@Autowired
	private FileInfoMapper fileInfoMapper;
	@Autowired
	private WebP2pCarLoanCenterInfoMapper carLoanCenterInfoMapper;

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
				String oids = this.pdfToPicture(sign.get("pdfPath"));
				if (StringUtil.isNotEmpty(oids)) {
					arennment.setPdfPic(oids);
				}
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
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

			List<WebP2pBiddingExamine> examines = biddingExamineMapper.findByMemberOID(novice.getMemberOID());
			WebP2pBiddingExamine examine = null;
			if (examines.size() > 0) {
				examine = examines.get(examines.size() - 1);
			}

			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("JK");

			Map<String, Object> replaceMap = new HashMap<String, Object>();

			replaceMap.put("xybh", xybh);
			replaceMap.put("jkbh", novice.getLoanNumber());
			replaceMap.put("jkr", examine.getRealName());
			replaceMap.put("dz", examine.getDizhi());
			replaceMap.put("lxfs", examine.getMobile());
			replaceMap.put("sfzh", examine.getIdCard());
			replaceMap.put("yhm", examine.getMobile());
			replaceMap.put("jkjexx", novice.getLoanAmount());
			BigDecimal aBigDecimal = novice.getLoanAmount();
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			replaceMap.put("jkjedx", jkjedx);
			replaceMap.put("jkyt", examine.getLoanuse());
			replaceMap.put("jkqx", loanRateInfo.getTerm());
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
			replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
			replaceMap.put("ksrq", DateUtils.formatJustDate(new Date().getTime()));
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
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
			List<WebP2pBiddingExamine> examines = biddingExamineMapper.findByMemberOID(novice.getMemberOID());
			WebP2pBiddingExamine examine = null;
			if (examines.size() > 0) {
				examine = examines.get(examines.size() - 1);
			}
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
			replaceMap.put("jkr", examine.getRealName());

			CodeContent code = codeContentMapper.findCodeContent(customer.getBankCityCode(), "cncity");

			replaceMap.put("dz", code.getNameCN());

			replaceMap.put("jkbh", novice.getLoanNumber());
			replaceMap.put("sfzh", examine.getIdCard());

			replaceMap.put("jkjexx", "");
			BigDecimal aBigDecimal = novice.getLoanAmount();
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			replaceMap.put("jkjedx", jkjedx);
			replaceMap.put("jkyt", examine.getLoanuse());
			replaceMap.put("jkqx", rateInfo.getLoanTerm());
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = rateInfo.getYearRate().multiply(b1);
			replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
			replaceMap.put("ksrq", DateUtils.formatJustDate(new Date().getTime()));
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
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
			replaceMap.put("ksrq", DateUtils.formatJustDate(new Date().getTime()));
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
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
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);
		} catch (Exception e) {
			logger.error(
					"gainMemberHHTAgreement(String memberOID, String contractType, String biddingOID, String ckOID)",
					e.getMessage());
		}

	}

	@Override
	public void gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID) {

		try {

			WebP2pPackageBiddingMainContentRuning contentRuning = packageBiddingMainContentRuningMapper
					.findByOID(biddingOID);
			String loanProductInfoOID = contentRuning.getLoanProductRateInfoID();
			WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper.findByOID(loanProductInfoOID);

			String bidLoanNumber = contentRuning.getLoanNumber();
			String financetransfertype = contentRuning.getFinancetransfertype();
			String jkrMemberOID = "";
			String jkrMemberOIDTwo = "";
			if ("financetransfer".equals(financetransfertype)) {
				List<WebP2pPackageBiddingMainContentRuning> content = packageBiddingMainContentRuningMapper
						.findByLoanNum(bidLoanNumber);

				jkrMemberOID = content.get(content.size() - 1).getMemberOID();
			}
			int term = loanRateInfo.getTerm();
			CarLoanMemberInfoBaseDTO jkyh = null;
			if (StringUtil.isNotEmpty(jkrMemberOID)) {
				List<CarLoanMemberInfoBaseDTO> basin = carLoanCenterInfoMapper
						.findCarLoanMemberInfoBase(contentRuning.getMemberOID());
				if (basin.size() > 0) {
					jkyh = basin.get(basin.size() - 1);
					jkrMemberOIDTwo = contentRuning.getMemberOID();
				}
			} else {
				List<CarLoanMemberInfoBaseDTO> basin = carLoanCenterInfoMapper.findCarLoanMemberInfoBase(jkrMemberOID);
				if (basin.size() > 0) {
					jkyh = basin.get(basin.size() - 1);
					jkrMemberOIDTwo = jkrMemberOID;
				}
			}
			MemberMember member = userDao.findUserByOID(jkrMemberOIDTwo);
			List<PayDebitCredit> cjr = payDebitCreditMapper.findByCondition(biddingOID);
			String conten = "";
			for (PayDebitCredit debit : cjr) {
				PayCustomer cjrxx = payCustomerMapper.findByMemberOID(debit.getOutMemberOID());
				String name = cjrxx.getName();
				String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

				String name2 = ToolsDisplay.getFullNameStr(String.valueOf(cjrxx.getNameCN()));
				conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + debit.getPrincipalMoney()
						+ "</td></tr>";
			}
			Map<String, Object> replaceMap = new HashMap<String, Object>();

			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("ZQYHG");
			replaceMap.put("data1", conten);
			PayCustomer cjrxx = payCustomerMapper.findByMemberOID(contentRuning.getMemberOID());
			replaceMap.put("xybh", xybh);
			replaceMap.put("jkr", jkyh.getRealName());
			CodeContent code = codeContentMapper.findCodeContent(member.getCity(), "cncity");
			replaceMap.put("dz", code.getNameCN());
			replaceMap.put("sfzh", jkyh.getIdCard());
			BigDecimal aBigDecimal = new BigDecimal(0);
			if (contentRuning.getApplyAmount() == null) {
				aBigDecimal = contentRuning.getHoldingAmount();
			} else {
				aBigDecimal = contentRuning.getApplyAmount();
			}
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			// ---------保理费率---------
			switch (term) {
			case 6:
				replaceMap.put("blfl", "0.5");
				break;
			case 12:
				replaceMap.put("blfl", "0.65");
				break;
			case 18:
				replaceMap.put("blfl", "0.80");
				break;
			case 24:
				replaceMap.put("blfl", "0.95");
				break;
			case 36:
				replaceMap.put("blfl", "1.10");
				break;
			default:
				break;
			}

			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
			replaceMap.put("ksrq", DateUtils.formatJustDate(new Date().getTime()));
			java.sql.Date jsrq = ToolDateTime.addDay(ToolDateTime.gainDate(), 15);
			String loanNumber = contentRuning.getLoanNumber();
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
			magrement.setMemberOID(jkyh.getMemberOID());
			magrement.setBiddingOID(biddingOID);
			magrement.setLoanNumber(loanNumber);
			magrement.setState("1");
			magrement.setOID(StringUtil.getUUID());
			Map<String, String> sign = controlSignService.doSignYQZQYSGAgreement(magrement.getMemberOID(),
					magrement.getContent());

			magrement.setSignIDs(sign.get("signIDs"));
			magrement.setPdfPath(sign.get("pdfPath"));
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);
		} catch (Exception e) {
			logger.error("gainmemberzqhgHHTAgreementDifferent(String contractType, String biddingOID)", e.getMessage());
		}
	}

	@Override
	public void gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID) {
		try {

			// 声明转让本金、垫付利息、转让价款
			BigDecimal advanceInterest = new BigDecimal(0);
			BigDecimal zrbj = new BigDecimal(0);
			BigDecimal zrzc = new BigDecimal(0);
			WebP2pPackageBiddingMainContentRuning contentRuning = packageBiddingMainContentRuningMapper
					.findByOID(biddingOID);
			zrzc = contentRuning.getBiddingAmount();
			List<PayTransferReturn> trsfs = payTransferReturnMapper.findByBusinessOIDDifferent(biddingOID);
			String conten = "";
			for (PayTransferReturn trans : trsfs) {
				PayCustomer cjrxx = payCustomerMapper.findByMemberOID(trans.getOutMemberOID());
				String name = cjrxx.getName();
				String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String name2 = ToolsDisplay.getFullNameStr(String.valueOf(cjrxx.getNameCN()));
				conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + trans.getPrincipalMoney()
						+ "</td>" + "<td>" + trans.getAdvanceInterest() + "</td>" + "<td>" + trans.getMoney() + "</td>"
						+ "</tr>";
			}
			PayTransferReturn transferReturn = trsfs.get(0);
			PayDebitCredit debit = payDebitCreditMapper.queryByPKOid(transferReturn.getDebitCreditOID());
			zrbj = debit.getSurplusPrincipalMoney();
			advanceInterest = zrzc.subtract(zrbj);

			PayTransferReturn transferzrfxx = trsfs.get(trsfs.size() - 1);
			MemberMember zrfxx = userDao.findUserByOID(transferzrfxx.getInMemberOID());
			PayDebitCredit ea = null;
			if (StringUtil.isNotEmpty(transferzrfxx.getDebitCreditOID())) {
				ea = payDebitCreditMapper.queryByPKOid(transferzrfxx.getDebitCreditOID());
				if (ea == null) {
					ea = payDebitCreditMapper.queryByPKOid(transferzrfxx.getDebitCreditOIDNew());
				}
			}
			String jkbOID = ea.getBusinessOID();
			String jkrOID = ea.getInMemberOID();
			Map<String, Object> replaceMap = new HashMap<String, Object>();
			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("ZQZR");
			replaceMap.put("data0", conten);
			replaceMap.put("xybh", xybh);
			replaceMap.put("zrf", "ZTW" + zrfxx.getName());// 甲方 格式：ZTW+手机号
			CodeContent code = codeContentMapper.findCodeContent(zrfxx.getCity(), "cncity");
			replaceMap.put("jfdz", code.getNameCN());// 甲方地址
			replaceMap.put("fddbr", zrfxx.getNameCN());// 甲方真实姓名
			replaceMap.put("sfzh", zrfxx.getIdCard());// 甲方身份证号
			replaceMap.put("jkbOID", jkbOID);

			replaceMap.put("zrzc", zrzc.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让价款：转让本金+垫付利息
			replaceMap.put("zrbj", zrbj.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让剩余本金
			replaceMap.put("dflx", advanceInterest.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让垫付利息
			replaceMap.put("zrrq", DateUtils.getJustDate(contentRuning.getUpdDateTime().getTime()));// 转让日期

			BigDecimal b1 = new BigDecimal(0.002);
			BigDecimal sxf = new BigDecimal(0.00);
			replaceMap.put("zrsxf", sxf.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让手续费

			BigDecimal b2 = new BigDecimal(100);
			BigDecimal zrll = transferzrfxx.getCreditRate().multiply(b2);

			replaceMap.put("zrll", zrll.setScale(2, BigDecimal.ROUND_HALF_UP));// 转让利息

			String hkfs = transferzrfxx.getCreditType();
			if (hkfs.equals("BIAP")) {
				replaceMap.put("hkfs", "按月还息到期还本");
			} else {
				replaceMap.put("hkfs", "等本等息");
			}

			LeanYuBiaoDTO jkrInfo = memberAgreementDao.findByYBInfoByJKXY(contentRuning.getLoanNumber());

			MemberMember jkr = userDao.findUserByOID(jkrInfo.getJkrOID());
			replaceMap.put("jkrsjh", jkr.getMemberAccount());
			replaceMap.put("jkrxm", jkrInfo.getJkrxm());
			replaceMap.put("jkrsfzh", jkrInfo.getJkrsfzh());
			String loanDay = DateUtils.formatJustDate(jkrInfo.getLoanDay().getTime());
			String[] JKTime = loanDay.split("-");
			replaceMap.put("jkYear", JKTime[0]);
			replaceMap.put("jkMonth", JKTime[1]);
			replaceMap.put("jkDay", JKTime[2]);
			String judge = contentRuning.getLoanNumber().substring(0, 3);
			String xyType = "6";
			if ("ICD".equals(judge)) {
				xyType = "16";
			}
			String jkxybh = memberAgreementDao.findByAgreementJKXYBHCK(jkrInfo.getBiddingOID(), xyType);
			replaceMap.put("jkxybh", jkxybh);
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
			magrement.setMemberOID(contentRuning.getMemberOID());
			magrement.setBiddingOID(biddingOID);
			magrement.setState("1");
			magrement.setXybh(xybh);
			magrement.setOID(StringUtil.getUUID());

			Map<String, String> sign = controlSignService.doSignZQZRAgreement(transferzrfxx.getInMemberOID(),
					magrement.getContent());
			magrement.setSignIDs(sign.get("signIDs"));
			magrement.setPdfPath(sign.get("pdfPath"));
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);
		} catch (Exception e) {
			logger.error("gainmemberHHTZQZRCHUJIEAgreement(String contractType, String biddingOID)", e.getMessage());
		}
	}

	@Override
	public void gainmemberzqhgHHTAgreement(String contractType, String biddingOID) {
		try {

			WebP2pPackageBiddingMainContentRuning contentRuning = packageBiddingMainContentRuningMapper
					.findByOID(biddingOID);
			String loanProductInfoOID = contentRuning.getLoanProductRateInfoID();
			WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper.findByOID(loanProductInfoOID);

			WebP2pBiddingExamine jkyh = biddingExamineMapper.findByloanNumber(contentRuning.getLoanNumber());
			List<PayDebitCredit> cjr = payDebitCreditMapper.findByCondition(biddingOID);
			String conten = "";
			for (PayDebitCredit debit : cjr) {
				PayCustomer cjrxx = payCustomerMapper.findByMemberOID(debit.getOutMemberOID());
				String name = cjrxx.getName();
				String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String name2 = ToolsDisplay.getFullNameStr(cjrxx.getNameCN());
				conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + debit.getPrincipalMoney()
						+ "</td></tr>";
			}
			Map<String, Object> replaceMap = new HashMap<String, Object>();
			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("ZQYHG");
			replaceMap.put("data1", conten);
			replaceMap.put("xybh", xybh);
			replaceMap.put("jkr", jkyh.getRealName());
			PayCustomer customer = payCustomerMapper.findByMemberOID(contentRuning.getMemberOID());
			CodeContent code = codeContentMapper.findCodeContent(customer.getBankCityCode(), "cncity");
			replaceMap.put("dz", code.getNameCN());
			replaceMap.put("sfzh", jkyh.getIdCard());

			BigDecimal aBigDecimal = new BigDecimal(0);
			if (contentRuning.getApplyAmount() == null) {
				aBigDecimal = contentRuning.getHoldingAmount();
			} else {
				aBigDecimal = contentRuning.getApplyAmount();
			}
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			replaceMap.put("jkjedx", jkjedx);
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
			replaceMap.put("ksrq", ToolDateTime.gainDate());
			java.sql.Date jsrq = ToolDateTime.addDay(ToolDateTime.gainDate(), 15);
			String loanNumber = contentRuning.getLoanNumber();
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
			magrement.setMemberOID(jkyh.getMemberOID());
			magrement.setBiddingOID(biddingOID);
			magrement.setLoanNumber(loanNumber);
			magrement.setState("1");
			magrement.setOID(StringUtil.getUUID());

			Map<String, String> sign = controlSignService.doSignYQZQYSGAgreement(magrement.getMemberOID(),
					magrement.getContent());

			magrement.setSignIDs(sign.get("signIDs"));
			magrement.setPdfPath(sign.get("pdfPath"));
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);
		} catch (Exception e) {
			logger.error("gainmemberzqhgHHTAgreement(String contractType, String biddingOID)", e.getMessage());
		}

	}

	@Override
	public void gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID) {
		try {

			WebP2pPackageBiddingMainContentRuning contentRuning = packageBiddingMainContentRuningMapper
					.findByOID(biddingOID);
			String loanProductInfoOID = contentRuning.getLoanProductRateInfoID();
			WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper.findByOID(loanProductInfoOID);
			List<CarLoanMemberInfoBaseDTO> carLoans = carLoanCenterInfoMapper
					.findCarLoanMemberInfoBase(contentRuning.getMemberOID());
			CarLoanMemberInfoBaseDTO jkyh = null;
			if (carLoans.size() > 0) {
				jkyh = carLoans.get(carLoans.size() - 1);
			}
			Map<String, Object> replaceMap = new HashMap<String, Object>();
			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("JK");
			replaceMap.put("xybh", xybh);
			replaceMap.put("jkbh", contentRuning.getLoanNumber());
			replaceMap.put("jkr", jkyh.getRealName());
			replaceMap.put("lxfs", jkyh.getMobile());
			replaceMap.put("yhm", jkyh.getMobile());
			replaceMap.put("sfzh", jkyh.getIdCard());// 身份证号
			replaceMap.put("jkjexx", contentRuning.getApplyAmount());// 借款金额
			BigDecimal aBigDecimal = contentRuning.getApplyAmount();
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			replaceMap.put("jkjedx", jkjedx);
			replaceMap.put("jkyt", jkyh.getLoanuse());// 借款用途
			replaceMap.put("jkqx", loanRateInfo.getTerm());// 借款期限
			replaceMap.put("cjh", jkyh.getCjh());// 车架号
			replaceMap.put("cph", jkyh.getCph());// 车牌号
			replaceMap.put("fdjh", jkyh.getFdjh());// 发动机号
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
			replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));// 借款年利率
			replaceMap.put("ksrq", ToolDateTime.gainDate());// 借款开始日期
			java.sql.Date jsrq = ToolDateTime.addMonth(ToolDateTime.gainDate(), loanRateInfo.getTerm());// 借款结束日期
			replaceMap.put("jsrq", jsrq);
			String loanNumber = contentRuning.getLoanNumber();
			String conten = "";
			List<PayDebitCredit> cjr = payDebitCreditMapper.findByCondition(biddingOID);
			for (PayDebitCredit debit : cjr) {
				PayCustomer cjrxx = payCustomerMapper.findByMemberOID(debit.getOutMemberOID());
				String name = cjrxx.getName();
				String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String name2 = ToolsDisplay.getFullNameStr(cjrxx.getNameCN());
				conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + debit.getPrincipalMoney()
						+ "</td></tr>";
			}

			replaceMap.put("data0", conten);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
			replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
			replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
			String hkjk = "";
			int hkqs = 0;
			List<PayRepayPlan> repays = repayPlanMapper.findByBusinessOIDAndMemberOID(contentRuning.getMemberOID(),
					biddingOID);
			for (PayRepayPlan repay : repays) {
				hkqs++;
				BigDecimal bj = repay.getPrincipalMoney();
				BigDecimal ll = repay.getInterestMoney();
				BigDecimal jhhk = bj.add(ll);
				hkjk += "<tr><td>" + hkqs + "</td>" + "<td>" + jhhk + "</td>" + "<td>" + repay.getRepayDate()
						+ "</td></tr>";
			}
			replaceMap.put("data1", hkjk);

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
			magrement.setLoanNumber(loanNumber);
			magrement.setState("1");
			magrement.setBiddingOID(biddingOID);
			magrement.setMemberOID(jkyh.getMemberOID());
			magrement.setOID(StringUtil.getUUID());
			Map<String, String> sign = controlSignService.doSignXDJKAgreement(magrement.getMemberOID(),
					magrement.getContent());

			magrement.setSignIDs(sign.get("signIDs"));
			magrement.setPdfPath(sign.get("pdfPath"));
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);

		} catch (Exception e) {
			logger.error("gainMemberHHTNRBJKAgreementDifferent(String contractType, String biddingOID)",
					e.getMessage());
		}

	}

	@Override
	public void gainMemberHHTNRBJKAgreement(String contractType, String biddingOID) {
		try {

			WebP2pPackageBiddingMainContentRuning contentRuning = packageBiddingMainContentRuningMapper
					.findByOID(biddingOID);
			String loanProductInfoOID = contentRuning.getLoanProductRateInfoID();
			WebP2pLoanProductRateInfo loanRateInfo = loanProductRateInfoMapper.findByOID(loanProductInfoOID);

			List<WebP2pBiddingExamine> examines = biddingExamineMapper.findByMemberOID(contentRuning.getMemberOID());
			WebP2pBiddingExamine jkyh = null;
			if (examines.size() > 0) {
				jkyh = examines.get(examines.size() - 1);
			}
			// 协议编号
			String xybh = sequenceDefinitionService.gainSequence("JK");

			Map<String, Object> replaceMap = new HashMap<String, Object>();
			replaceMap.put("xybh", xybh);
			replaceMap.put("jkbh", contentRuning.getLoanNumber());
			replaceMap.put("jkr", jkyh.getRealName());
			replaceMap.put("dz", jkyh.getDizhi());
			replaceMap.put("lxfs", jkyh.getMobile());
			replaceMap.put("yhm", jkyh.getMobile());
			replaceMap.put("sfzh", jkyh.getIdCard());
			replaceMap.put("jkjexx", contentRuning.getApplyAmount());
			BigDecimal aBigDecimal = contentRuning.getApplyAmount();
			double d = aBigDecimal.doubleValue();
			String jkjedx = ToolMath.toBigMoney(d);
			replaceMap.put("jkjedx", jkjedx);
			replaceMap.put("jkyt", jkyh.getLoanuse());
			replaceMap.put("jkqx", loanRateInfo.getTerm());
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal hnll = loanRateInfo.getYearRate().multiply(b1);
			replaceMap.put("jkll", hnll.setScale(2, BigDecimal.ROUND_HALF_UP));
			replaceMap.put("ksrq", ToolDateTime.gainDate());
			java.sql.Date jsrq = ToolDateTime.addMonth(ToolDateTime.gainDate(), loanRateInfo.getTerm());
			replaceMap.put("jsrq", jsrq);
			String loanNumber = contentRuning.getLoanNumber();
			String conten = "";

			List<PayDebitCredit> cjr = payDebitCreditMapper.findByCondition(biddingOID);
			for (PayDebitCredit debit : cjr) {
				PayCustomer cjrxx = payCustomerMapper.findByMemberOID(debit.getOutMemberOID());
				String name = cjrxx.getName();
				String name1 = name.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String name2 = ToolsDisplay.getFullNameStr(cjrxx.getNameCN());
				conten += "<tr><td>" + name2 + "</td>" + "<td>" + name1 + "</td>" + "<td>" + debit.getPrincipalMoney()
						+ "</td></tr>";
			}
			replaceMap.put("data0", conten);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			replaceMap.put("nian", Integer.valueOf(calendar.get(Calendar.YEAR)));
			replaceMap.put("yue", Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
			replaceMap.put("ri", Integer.valueOf(calendar.get(Calendar.DATE)));
			String hkjk = "";
			int hkqs = 0;
			List<PayRepayPlan> repays = repayPlanMapper.findByBusinessOIDAndMemberOID(contentRuning.getMemberOID(),
					biddingOID);
			for (PayRepayPlan repay : repays) {
				hkqs++;
				BigDecimal bj = repay.getPrincipalMoney();
				BigDecimal ll = repay.getInterestMoney();
				BigDecimal jhhk = bj.add(ll);
				hkjk += "<tr><td>" + hkqs + "</td>" + "<td>" + jhhk + "</td>" + "<td>" + repay.getRepayDate()
						+ "</td></tr>";
			}
			replaceMap.put("data1", hkjk);

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
			magrement.setLoanNumber(loanNumber);
			magrement.setState("1");
			magrement.setBiddingOID(biddingOID);
			magrement.setMemberOID(contentRuning.getMemberOID());
			magrement.setOID(StringUtil.getUUID());
			Map<String, String> sign = controlSignService.doSignXDJKAgreement(magrement.getMemberOID(),
					magrement.getContent());
			magrement.setSignIDs(sign.get("signIDs"));
			magrement.setPdfPath(sign.get("pdfPath"));
			String oids = this.pdfToPicture(sign.get("pdfPath"));
			if (StringUtil.isNotEmpty(oids)) {
				magrement.setPdfPic(oids);
			}
			memberAgreementDao.saveMemberAgreement(magrement);
		} catch (Exception e) {
			logger.error("gainMemberHHTNRBJKAgreement(String contractType, String biddingOID)", e.getMessage());
		}

	} 

	private String pdfToPicture(String name) {
		try {
			Map<String, String> resMap = ReadPropertiesl.gainPropertiesMap("File.properties");
			String path = resMap.get("pdfFilePath");
			String picPath = resMap.get("pdfPicPath");
			path = path + name;
			Document document = new Document();
			document.setFile(path);
			float scale = 2.5f;// 缩放比例
			float rotation = 0f;// 旋转角度

			List<FileInfo> files = new ArrayList<FileInfo>();
			StringBuilder builder = new StringBuilder();
			// 添加pdf单张图片到集合
			for (int i = 0; i < document.getNumberOfPages(); i++) {
				BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
						org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
				RenderedImage rendImage = image;
				FileInfo info = new FileInfo();
				// 日期路径
				String relativePath = DateUtils.dayTimestamp().toString();
				File file = new File(picPath + relativePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				Long datetime = new Date().getTime();
				// 图片名称
				String filename = datetime.toString();
				File fil = new File(file + File.separator + filename);
				try {
					ImageIO.write(rendImage, "png", fil);
					image.flush();
				} catch (IOException e) {
					logger.error("String pdfToPicture(String name)" + e.getMessage());
				} finally {
					image = null;
				}

				String oid = StringUtil.getUUID();
				builder.append(oid + ",");
				info.setOID(oid);
				info.setName(name);
				info.setSource("upload");
				info.setPhysicalName(filename);
				info.setRelativePath(relativePath);
				files.add(info);
			}
			fileInfoMapper.saveFileInfoList(files);
			document.dispose();
			return builder.toString();
		} catch (Exception e) {
			logger.error("String pdfToPicture(String name)" + e.getMessage());
		}
		return null;
	}
}
