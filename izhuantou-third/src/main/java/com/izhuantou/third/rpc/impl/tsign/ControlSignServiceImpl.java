package com.izhuantou.third.rpc.impl.tsign;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.third.rpc.api.tsign.ControlSignService;
import com.izhuantou.third.rpc.impl.ReadPropertiesl;
import com.izhuantou.third.rpc.impl.tsign.utils.FileHelper;
import com.izhuantou.third.rpc.impl.tsign.utils.HtmlToPdfUtil;
import com.izhuantou.third.rpc.impl.tsign.utils.SignUtil;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;
import com.timevale.esign.sdk.tech.impl.constants.SignType;

@Service("controlSignService")
public class ControlSignServiceImpl implements ControlSignService {
    private static final Logger logger = LoggerFactory.getLogger(ControlSignServiceImpl.class);
    private boolean flag = true;// 电子签章开关
    private static String signedFolder;

    static {
	Map<String, String> map = ReadPropertiesl.gainPropertiesMap("File.properties");
	try {
	    signedFolder = map.get("pdfFilePath");// pdf文件保存路径
	    FileHelper.createDirectory(signedFolder);// 如果文件夹不存在就创建文件夹
	    initProject();// 初始化项目
	} catch (Exception e) {
	    logger.error("从配置文件获取PDF存储路径失败", e.getMessage());
	}
    }

    /**
     * 项目初始化
     * 
     * @throws ExceptionSaveFail
     */
    public static void initProject() {

	try {
	    Map<String, String> map = ReadPropertiesl.gainPropertiesMap("sign.properties");
	    String projectId = map.get("projectId");
	    String projectSecret = map.get("projectSecret");
	    String itsmApiUrl = map.get("itsmApiUrl");
	    // 初始化项目，做全局使用，只初始化一次即可
	    SignUtil.initProject(projectId, projectSecret, itsmApiUrl);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("初始化电子签章项目失败", e.getMessage());
	}
    }

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private PayCustomerMapper payCustomerMapper;

    @Override
    public Map<String, String> doSignRegistAgreement(String memberOID, String agreementContent) {

	if (!flag)
	    return null;

	MemberMember member = userDao.findUserByOID(memberOID);

	String name = member.getName();
	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);

	String signedFileName = "zhuce-" + name + ".pdf";// 返回后保存的文件名

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("砖头网签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 砖头网公司签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);

	String ztwQZID = platformSignResult.getSignServiceId();
	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == platformSignResult.getErrCode()) { // 初始化的参数
	    SignUtil.saveSignedByStream(platformSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public Map<String, String> doSignJKZXAgreement(String memberOID, String agreementContent) {

	if (!flag)
	    return null;// 电子签章开关

	PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);

	String nameCN = customer.getNameCN();// 中文名
	String cardNO = customer.getCardNO();// 身份证号

	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	String signedFileName = "jkzxfw-ZT" + customer.getName() + "-" + nowDate + ".pdf";// 返回后保存的文件名

	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, 0);
	// 创建企业客户账号 TODO 改成钱多多
	// String userOrganizeAccountId =
	// SignUtil.addOrganizeAccount("天之云信息科技有限公司",0,OrganRegType.MERGE,"52227058XT51M4AL62","杭州城落霞峰7号",1,"艾利","220301198705170035");
	String userOrganizeAccountId = SignUtil.addOrganizeAccount("钱多多（天津）企业管理咨询有限公司", 0, OrganRegType.MERGE,
		"91120104MA05K9EF7A", "", 2, "宋明", "120101197701245010");
	// 创建个人印章
	AddSealResult userPersonSealData = SignUtil.addPersonTemplateSeal(userPersonAccountId);
	// 创建企业印章
	// 横向文字
	/*
	 * hText 生成印章中的横向文内容 如“合同专用章、财务专用章” qText 生成印章中的下弦文内容 公章防伪码（一串13位数字）
	 * 如91010086135601
	 */
	String hText = "";
	// 下弦文字
	String qText = "";
	AddSealResult userOrganizeSealData = SignUtil.addOrganizeTemplateSeal(userOrganizeAccountId, hText, qText);

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("丙方签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 砖头网签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);
	String ztwQZID = platformSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("甲方签章", 53, 9, 80);
	// 个人客户签署，签署方式：关键字定位,以文件流的方式传递pdf文档
	// bob 11.21 手绘签章
	String userSignBase = "";
	if (StringUtil.isNotEmpty(customer.getHandBase())) {
	    userSignBase = customer.getHandBase();
	    posBean = SignUtil.setKeyPosBean("甲方签章", 53, 9, 159);
	} else {
	    userSignBase = userPersonSealData.getSealData();
	}
	FileDigestSignResult userPersonSignResult = SignUtil.userPersonSignByStream(platformSignResult.getStream(),
		userPersonAccountId, userSignBase, posBean, signType);
	String khID = userPersonSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("乙方签章", 83, 15, 159);
	// 钱多多签署,坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult userOrganizeSignResult = SignUtil.userOrganizeSignByStream(
		userPersonSignResult.getStream(), userOrganizeAccountId, userOrganizeSealData.getSealData(), posBean,
		signType);
	String qyID = userOrganizeSignResult.getSignServiceId();

	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == userOrganizeSignResult.getErrCode()) {
	    SignUtil.saveSignedByStream(userOrganizeSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID + "|" + khID + "|" + qyID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public Map<String, String> doSignFWAgreement(String memberOID, String agreementContent, String agreeType) {

	if (!flag)
	    return null;// 电子签章开关

	PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	String nameCN = customer.getNameCN();// 中文名
	String cardNO = customer.getCardNO();// 身份证号

	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	String signedFileName = agreeType + "-ZT" + customer.getName() + "-" + nowDate + ".pdf";// 返回后保存的文件名

	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, 0);
	// 创建个人印章
	AddSealResult userPersonSealData = SignUtil.addPersonTemplateSeal(userPersonAccountId);

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("甲方签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 砖头网签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);
	String ztwQZID = platformSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("乙方签章", 53, 9, 80);
	// 出借客户签署，签署方式：关键字定位,以文件流的方式传递pdf文档
	FileDigestSignResult userPersonSignResult = SignUtil.userPersonSignByStream(platformSignResult.getStream(),
		userPersonAccountId, userPersonSealData.getSealData(), posBean, signType);
	String khID = userPersonSignResult.getSignServiceId();

	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == userPersonSignResult.getErrCode()) {
	    SignUtil.saveSignedByStream(userPersonSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID + "|" + khID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public Map<String, String> doSignXDJKAgreement(String memberOID, String agreementContent) {

	if (!flag)
	    return null;// 电子签章开关

	PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	String nameCN = customer.getNameCN();// 中文名
	String cardNO = customer.getCardNO();// 身份证号

	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	String signedFileName = "jiekuan-ZT" + customer.getName() + "-" + nowDate + ".pdf";// 返回后保存的文件名

	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, 0);
	// 创建企业客户账号 TODO 改成钱多多
	// String userOrganizeAccountId =
	// SignUtil.addOrganizeAccount("天之云信息科技有限公司",0,OrganRegType.MERGE,"52227058XT51M4AL62","杭州城落霞峰7号",1,"艾利","220301198705170035");
	String userOrganizeAccountId = SignUtil.addOrganizeAccount("钱多多（天津）企业管理咨询有限公司", 0, OrganRegType.MERGE,
		"91120104MA05K9EF7A", "", 2, "宋明", "120101197701245010");
	// 创建个人印章
	AddSealResult userPersonSealData = SignUtil.addPersonTemplateSeal(userPersonAccountId);
	// 创建企业印章
	/*
	 * hText 生成印章中的横向文内容 如“合同专用章、财务专用章” qText 生成印章中的下弦文内容 公章防伪码（一串13位数字）
	 * 如91010086135601
	 */
	// 横向文字
	String hText = "";
	// 下弦文字
	String qText = "";
	AddSealResult userOrganizeSealData = SignUtil.addOrganizeTemplateSeal(userOrganizeAccountId, hText, qText);

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("丁方签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 砖头网签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);
	String ztwQZID = platformSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("乙方签章", 53, 9, 80);
	// 个人客户签署，签署方式：关键字定位,以文件流的方式传递pdf文档
	// bob 11.21 手绘签章
	String userSignBase = "";
	if (StringUtil.isNotEmpty(customer.getHandBase())) {
	    userSignBase = customer.getHandBase();
	    posBean = SignUtil.setKeyPosBean("乙方签章", 53, 9, 159);
	} else {
	    userSignBase = userPersonSealData.getSealData();
	}
	FileDigestSignResult userPersonSignResult = SignUtil.userPersonSignByStream(platformSignResult.getStream(),
		userPersonAccountId, userSignBase, posBean, signType);
	String khID = userPersonSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("丙方签章", 83, 15, 159);
	// 钱多多签署,坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult userOrganizeSignResult = SignUtil.userOrganizeSignByStream(
		userPersonSignResult.getStream(), userOrganizeAccountId, userOrganizeSealData.getSealData(), posBean,
		signType);
	String qyID = userOrganizeSignResult.getSignServiceId();

	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == userOrganizeSignResult.getErrCode()) {
	    SignUtil.saveSignedByStream(userOrganizeSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID + "|" + khID + "|" + qyID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public Map<String, String> doSignZQZRAgreement(String memberOID, String agreementContent) {

	if (!flag)
	    return null;// 电子签章开关

	PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	String nameCN = customer.getNameCN();// 中文名
	String cardNO = customer.getCardNO();// 身份证号

	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	String signedFileName = "zqzr-ZT" + customer.getName() + "-" + nowDate + ".pdf";// 返回后保存的文件名

	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, 0);
	// 创建个人印章
	AddSealResult userPersonSealData = SignUtil.addPersonTemplateSeal(userPersonAccountId);

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("丙方签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 砖头网签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);
	String ztwQZID = platformSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("甲方签章", 53, 9, 80);
	// 个人客户签署，签署方式：关键字定位,以文件流的方式传递pdf文档
	FileDigestSignResult userPersonSignResult = SignUtil.userPersonSignByStream(platformSignResult.getStream(),
		userPersonAccountId, userPersonSealData.getSealData(), posBean, signType);
	String khID = userPersonSignResult.getSignServiceId();

	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == userPersonSignResult.getErrCode()) {
	    SignUtil.saveSignedByStream(userPersonSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID + "|" + khID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public Map<String, String> doSignYQZQYSGAgreement(String memberOID, String agreementContent) {

	if (!flag)
	    return null;// 电子签章开关

	PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	String nameCN = customer.getNameCN();// 中文名
	String cardNO = customer.getCardNO();// 身份证号

	// 获取流的字节数组
	byte[] srcPdfFile = HtmlToPdfUtil.generatePDF(agreementContent);
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowDate = sDateFormat.format(new java.util.Date());
	String signedFileName = "zqyhg-ZT" + customer.getName() + "-" + nowDate + ".pdf";// 返回后保存的文件名

	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, 0);
	// 创建企业客户账号 TODO 改成 鼎星耀阳
	// String userOrganizeAccountId =
	// SignUtil.addOrganizeAccount("天之云信息科技有限公司",0,OrganRegType.MERGE,"52227058XT51M4AL62","杭州城落霞峰7号",1,"艾利","220301198705170035");
	String userOrganizeAccountId = SignUtil.addOrganizeAccount("鼎星耀阳（天津）商业保理有限公司", 0, OrganRegType.MERGE,
		"91120118MA05MBLW71", "", 2, "汪莉", "120104197707184326");
	// 创建个人印章
	AddSealResult userPersonSealData = SignUtil.addPersonTemplateSeal(userPersonAccountId);
	// 创建企业印章
	/*
	 * hText 生成印章中的横向文内容 如“合同专用章、财务专用章” qText 生成印章中的下弦文内容 公章防伪码（一串13位数字）
	 * 如91010086135601
	 */
	// 横向文字
	String hText = "";
	// 下弦文字
	String qText = "";
	AddSealResult userOrganizeSealData = SignUtil.addOrganizeTemplateSeal(userOrganizeAccountId, hText, qText);

	// 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
	PosBean posBean = SignUtil.setKeyPosBean("丁方签章", 83, 15, 159);
	SignType signType = SignType.Key;
	// 贵公司签署，签署方式：坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult platformSignResult = SignUtil.platformSignByStreamm(srcPdfFile, posBean, signType);
	String ztwQZID = platformSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("乙方签章", 53, 9, 80);
	// 个人客户签署，签署方式：关键字定位,以文件流的方式传递pdf文档
	// bob 11.21 手绘签章
	String userSignBase = "";
	if (StringUtil.isNotEmpty(customer.getHandBase())) {
	    userSignBase = customer.getHandBase();
	    posBean = SignUtil.setKeyPosBean("乙方签章", 53, 9, 159);
	} else {
	    userSignBase = userPersonSealData.getSealData();
	}
	FileDigestSignResult userPersonSignResult = SignUtil.userPersonSignByStream(platformSignResult.getStream(),
		userPersonAccountId, userSignBase, posBean, signType);
	String khID = userPersonSignResult.getSignServiceId();

	posBean = SignUtil.setKeyPosBean("丙方签章", 83, 15, 159);
	// 企业客户签署,坐标定位,以文件流的方式传递pdf文档
	FileDigestSignResult userOrganizeSignResult = SignUtil.userOrganizeSignByStream(
		userPersonSignResult.getStream(), userOrganizeAccountId, userOrganizeSealData.getSealData(), posBean,
		signType);
	String qyID = userOrganizeSignResult.getSignServiceId();

	// 所有签署完成,将最终签署后的文件流保存到本地
	if (0 == userOrganizeSignResult.getErrCode()) {
	    SignUtil.saveSignedByStream(userOrganizeSignResult.getStream(), signedFolder, signedFileName);
	} else {
	    logger.error("电子签章失败");
	    return null;
	}
	Map<String, String> sign = new HashMap<String, String>();
	sign.put("signIDs", ztwQZID + "|" + khID + "|" + qyID);// 存签署记录
	sign.put("pdfPath", signedFileName);// 存pdf名
	return sign;
    }

    @Override
    public void addPersonAccount(String nameCN, String cardNO, int personArea) {
	// 创建个人客户账户
	String userPersonAccountId = SignUtil.addPersonAccount(nameCN, cardNO, personArea);
	// TODO 存表 老项目提供了该接口，没有做实现
    }

    @Override
    public void addOrganizeAccount(String name, int organType, OrganRegType regType, String organCode, String address,
	    int userType, String agentName, String agentIdNo) {
	// 创建企业客户账号
	String userOrganizeAccountId = SignUtil.addOrganizeAccount(name, organType, regType, organCode, address,
		userType, agentName, agentIdNo);
	// TODO 存表 老项目提供了该接口，没有做实现
    }

}
