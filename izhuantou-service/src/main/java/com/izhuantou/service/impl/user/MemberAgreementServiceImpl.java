package com.izhuantou.service.impl.user;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;
import com.izhuantou.dao.user.MemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.user.ControlSignService;
import com.izhuantou.service.api.user.MemberAgreementService;
import com.izhuantou.service.api.user.SequenceDefinitionService;

@Service("memberAgreementService")
public class MemberAgreementServiceImpl implements MemberAgreementService {
    private static final Logger logger = LoggerFactory.getLogger(MemberAgreementServiceImpl.class);
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
    public String gainupdateMemberAgreement(String name, String contractType, String memberAccount) {
	try {
	    if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(contractType)
		    && StringUtil.isNotEmpty(memberAccount)) {
		Map<String, Object> remap = new HashMap<String, Object>();
		MemberMember member = userDao.findUserByName(name);
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

}
