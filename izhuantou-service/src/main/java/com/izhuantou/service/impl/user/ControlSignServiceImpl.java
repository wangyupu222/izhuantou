package com.izhuantou.service.impl.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.user.ControlSignService;
import com.izhuantou.service.impl.tsign.utils.FileHelper;
import com.izhuantou.service.impl.tsign.utils.HtmlToPdfUtil;
import com.izhuantou.service.impl.tsign.utils.SignUtil;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
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

    /**
     * 1砖头网注册协议（电子签章）
     */
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

}
