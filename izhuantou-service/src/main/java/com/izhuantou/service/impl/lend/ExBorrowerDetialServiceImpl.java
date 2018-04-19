package com.izhuantou.service.impl.lend;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.ExBorrowerHHTCDInfo;
import com.izhuantou.damain.lend.ExDDTInfo;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferBiddingPool;
import com.izhuantou.damain.webp2p.WebP2pMecLoanCenterInfo;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPerLoanCenterInfo;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.lend.ExBorrowerHHTCDInfoMapper;
import com.izhuantou.dao.lend.ZCDInfoMpper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pDebtTransferBiddingPoolMapper;
import com.izhuantou.dao.webp2p.WebP2pMecLoanCenterInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPerLoanCenterInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.ExBorrowerDetialService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 转让标信息
 * 
 * @author jasen
 *
 */
@Service("exBorrowerDetialService")
public class ExBorrowerDetialServiceImpl extends BaseServiceImpl<WebP2pBiddingExamine>
	implements ExBorrowerDetialService {
    private static final Logger logger = LoggerFactory.getLogger(DisplayZZTServiceImpl.class);

    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRunningMapper;

    @Autowired
    private WebP2pBiddingExamineMapper webP2pBiddingExamineMapper;

    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;

    @Autowired
    private ExBorrowerHHTCDInfoMapper exBorrowerHHTCDInfoMapper;

    @Autowired
    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

    @Autowired
    private WebP2pPerLoanCenterInfoMapper perLoanCenterInfoMapper;

    @Autowired
    private WebP2pMecLoanCenterInfoMapper mecLoanCenterInfoMapper;

    @Autowired
    private WebP2pDebtTransferBiddingPoolMapper debtTransferBiddingPoolMapper;

    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Autowired
    private ZCDInfoMpper zcdMapper;

    @Override
    public WebP2pBiddingExamine findByCondition(String OID) {

	try {
	    // String OID = "05ccb5f4f5d3f6e25b53949d47f823dd";
	    WebP2pNoviceBiddingRuning noviceBiddingRuning = new WebP2pNoviceBiddingRuning();
	    noviceBiddingRuning = noviceBiddingRunningMapper.findByOID(OID);
	    WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	    biddingExamine = webP2pBiddingExamineMapper.findByloanNumber(noviceBiddingRuning.getLoanNumber());// 原标人信息
	    biddingExamine.setRealName(getFullNameStr(biddingExamine.getRealName()));// 原标人姓名
	    String idCard = biddingExamine.getIdCard();
	    idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{3}[A-Z0-9]{1})", "$1**********$2");
	    biddingExamine.setIdCard(idCard);// 身份证号
	    String memberPhone = biddingExamine.getMobile();
	    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
	    biddingExamine.setMobile(memberPhone);// 电话

	    return biddingExamine;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}

    }

    // 姓名隐藏
    public static String getFullNameStr(String full_name) {
	if (full_name == null)
	    return " ";
	String replace_string = "*";
	for (int i = 1; i < full_name.length() - 1; i++) {
	    replace_string = replace_string + "*";
	}
	full_name = full_name.substring(0, 1) + replace_string;
	return full_name;
    }

    /** 环环投车抵债转原借款人信息 */

    /** 环环投原借款人信息 */
    @Override
    public WebP2pBiddingExamine findByConditionHHT(String OID) {
	try {
	    // String OID = "05ccb5f4f5d3f6e25b53949d47f823dd";
	    WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();
	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);

	    WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	    biddingExamine = webP2pBiddingExamineMapper
		    .findByloanNumber(packageBiddingMainContentRuning.getLoanNumber());// 原标人信息
	    biddingExamine.setRealName(getFullNameStr(biddingExamine.getRealName()));// 原标人姓名
	    String idCard = biddingExamine.getIdCard();
	    idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{3}[A-Z0-9]{1})", "$1**********$2");
	    biddingExamine.setIdCard(idCard);// 身份证号
	    String memberPhone = biddingExamine.getMobile();
	    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
	    biddingExamine.setMobile(memberPhone);// 电话

	    return biddingExamine;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
    }

    /** 环环投车抵信息 */
    @Override
    public ExBorrowerHHTCDInfo findCDHHT(String OID) {
	try {

	    WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();
	    // 借款人信息
	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);
	    ExBorrowerHHTCDInfo exBorrowerHHTCDInfo = new ExBorrowerHHTCDInfo();

	    exBorrowerHHTCDInfo = exBorrowerHHTCDInfoMapper.findByOID(packageBiddingMainContentRuning.getLoanNumber());
	    exBorrowerHHTCDInfo.setLoanNumber(packageBiddingMainContentRuning.getLoanNumber());
	    exBorrowerHHTCDInfo.setDebtLoanNumber(packageBiddingMainContentRuning.getDebtLoanNumber());

	    exBorrowerHHTCDInfo.setRealName(getFullNameStr(exBorrowerHHTCDInfo.getRealName()));// 原标人姓名
	    String idCard = exBorrowerHHTCDInfo.getIdCard();
	    idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{3}[A-Z0-9]{1})", "$1**********$2");
	    exBorrowerHHTCDInfo.setIdCard(idCard);// 身份证号
	    String memberPhone = exBorrowerHHTCDInfo.getMobile();
	    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
	    exBorrowerHHTCDInfo.setMobile(memberPhone);// 电话
	    if (exBorrowerHHTCDInfo.getHighesteduback().equals("zhuanke")) {
		exBorrowerHHTCDInfo.setHighesteduback("专科");
	    } else if (exBorrowerHHTCDInfo.getHighesteduback().equals("xiaoxue")) {
		exBorrowerHHTCDInfo.setHighesteduback("小学");
	    } else if (exBorrowerHHTCDInfo.getHighesteduback().equals("chuzhong")) {
		exBorrowerHHTCDInfo.setHighesteduback("初中");
	    } else if (exBorrowerHHTCDInfo.getHighesteduback().equals("gaozhong")) {
		exBorrowerHHTCDInfo.setHighesteduback("高中");
	    } else if (exBorrowerHHTCDInfo.getHighesteduback().equals("ssyjs")) {
		exBorrowerHHTCDInfo.setHighesteduback("硕士研究所");
	    } else if (exBorrowerHHTCDInfo.getHighesteduback().equals("bsyjs")) {
		exBorrowerHHTCDInfo.setHighesteduback("博士研究生");
	    } else {
		exBorrowerHHTCDInfo.setHighesteduback("本科");
	    }
	    if (exBorrowerHHTCDInfo.getGender().equals("1")) {
		exBorrowerHHTCDInfo.setGender("男");
	    } else {
		exBorrowerHHTCDInfo.setGender("女");
	    }

	    exBorrowerHHTCDInfo.setJkze(packageBiddingMainContentRuning.getApplyAmount());

	    return exBorrowerHHTCDInfo;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}

    }

    /** DDT元借款人 */
    @Override
    public ExDDTInfo findDDT(String OID) {

	WebP2pNormalBiddingRuning normalBiddingRuning = new WebP2pNormalBiddingRuning();

	try {
	    normalBiddingRuning = normalBiddingRuningMapper.findByOID(OID);
	    String type = normalBiddingRuning.getHxProductType();
	    String loanCenterinfo = normalBiddingRuning.getHxLoanCenterInfo();
	    WebP2pPerLoanCenterInfo perLoanCenterInfo = new WebP2pPerLoanCenterInfo();
	    ExDDTInfo exDDTInfo = new ExDDTInfo();
	    WebP2pMecLoanCenterInfo mecLoanCenterInfo = new WebP2pMecLoanCenterInfo();
	    // 个人信息
	    if ("per".equals(type)) {
		perLoanCenterInfo = perLoanCenterInfoMapper.findByOID(loanCenterinfo);
		exDDTInfo.setStyle("per");
		exDDTInfo.setLoanuse(perLoanCenterInfo.getBiddingContent());// 标的详情
		exDDTInfo.setRealName(getFullNameStr(perLoanCenterInfo.getCustomername()));// 真实姓名
		exDDTInfo.setGender(perLoanCenterInfo.getSex());// 性别
		exDDTInfo.setAge(perLoanCenterInfo.getAge());// 年龄

		exDDTInfo.setDywxx(perLoanCenterInfo.getCollateral());// 抵押物信息

		String idCard = perLoanCenterInfo.getCertificatesid();
		idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
		idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{3}[A-Z0-9]{1})", "$1**********$2");
		exDDTInfo.setIdcard(idCard);// 身份证

		String memberPhone = perLoanCenterInfo.getPhone();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		exDDTInfo.setMobile(memberPhone);// 手机号
		exDDTInfo.setHkszd(perLoanCenterInfo.getDomicile());// 户籍所在地
		exDDTInfo.setHyzk(perLoanCenterInfo.getIsMerried());// 婚姻状态

		/*
		 * DTBCodeSuitMulti codeSuitMulti1 = new DTBCodeSuitMulti();
		 * codeSuitMulti1.assignCode("education",
		 * ServiceFactory.instanceCodeService(this).gainCode("xueliCode"
		 * )); dtoe.setReplaceData(codeSuitMulti1);
		 */
		exDDTInfo.setEducation(perLoanCenterInfo.getEducation());// 教育程度
		// 信息披露
		// fengkongDto =
		// controlNormalBiddingRuning.findBiddingPC(mainOID);
		// this.assignAttribute("dtocListShenheInfoCollection",
		// fengkongDto);
		if (perLoanCenterInfo.getEducation().equals("zhuanke")) {
		    exDDTInfo.setEducation("专科");
		} else if (perLoanCenterInfo.getEducation().equals("xiaoxue")) {
		    exDDTInfo.setEducation("小学");
		} else if (perLoanCenterInfo.getEducation().equals("chuzhong")) {
		    exDDTInfo.setEducation("初中");
		} else if (perLoanCenterInfo.getEducation().equals("gaozhong")) {
		    exDDTInfo.setEducation("高中");
		} else if (perLoanCenterInfo.getEducation().equals("ssyjs")) {
		    exDDTInfo.setEducation("硕士研究所");
		} else if (perLoanCenterInfo.getEducation().equals("bsyjs")) {
		    exDDTInfo.setEducation("博士研究生");
		} else {
		    exDDTInfo.setEducation("本科");
		}
		if (perLoanCenterInfo.getSex().equals("1")) {
		    exDDTInfo.setGender("男");
		} else {
		    exDDTInfo.setGender("女");
		}

	    }

	    // 企业
	    if ("mec".equals(type)) {
		exDDTInfo.setStyle("per");
		mecLoanCenterInfo = mecLoanCenterInfoMapper.findByOID(loanCenterinfo);
		exDDTInfo.setDywxx(mecLoanCenterInfo.getCollateral());// 抵押物信息
		exDDTInfo.setLoanuse(mecLoanCenterInfo.getBiddingContent());// 标的详情
		exDDTInfo.setJkqy(mecLoanCenterInfo.getCompany());// 借款企业

		Date dateTime = mecLoanCenterInfo.getEstablishedTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		exDDTInfo.setClsj(sdf.format(dateTime));// 成立时间
		exDDTInfo.setQydz(mecLoanCenterInfo.getCompanyAddress());// 企业地址
		exDDTInfo.setSshy(mecLoanCenterInfo.getTrade());// 所属行业
		exDDTInfo.setZczb(mecLoanCenterInfo.getRegisteredCapital());// 注册资本
		exDDTInfo.setXyzk(mecLoanCenterInfo.getCreditStatus());// 信用状况

	    }

	    return exDDTInfo;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}

    }

    /** ZZT原借款人信息 */
    @Override
    public WebP2pBiddingExamine findByConditionZZT(String OID) {
	try {
	    // String OID = "05ccb5f4f5d3f6e25b53949d47f823dd";
	    WebP2pDebtTransferBiddingPool debtTransferBiddingPool = new WebP2pDebtTransferBiddingPool();
	    debtTransferBiddingPool = debtTransferBiddingPoolMapper.findByOID(OID);
	    WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	    biddingExamine = webP2pBiddingExamineMapper.findByloanNumber(debtTransferBiddingPool.getLoanNumber());// 原标人信息
	    biddingExamine.setRealName(getFullNameStr(biddingExamine.getRealName()));// 原标人姓名
	    String idCard = biddingExamine.getIdCard();
	    idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\d{3}[A-Z0-9]{1})", "$1**********$2");
	    biddingExamine.setIdCard(idCard);// 身份证号
	    String memberPhone = biddingExamine.getMobile();
	    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
	    biddingExamine.setMobile(memberPhone);// 电话
	    WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();
	    productRateInfo = productRateInfoMapper.findByOID(debtTransferBiddingPool.getProductRateInfoID());

	    // 还款方式
	    if (productRateInfo.getRepaymentType().equals("OPI")) {
		biddingExamine.setHkfs("一次性还本付息");
	    } else if (productRateInfo.getRepaymentType().equals("EPEI")) {
		biddingExamine.setHkfs("等本等息");
	    } else {
		biddingExamine.setHkfs("按月返息到期还本");
	    }

	    return biddingExamine;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
    }

}
