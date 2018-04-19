package com.izhuantou.app.controller.mobileDisplay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.izhuantou.damain.lend.AuditInfo;
import com.izhuantou.damain.lend.DetialDDT;
import com.izhuantou.damain.lend.DetialHHT;
import com.izhuantou.damain.lend.DetialTBZ;
import com.izhuantou.damain.lend.DetialZZT;
import com.izhuantou.damain.lend.ExBorrowerHHTCDInfo;
import com.izhuantou.damain.lend.ExDDTInfo;
import com.izhuantou.damain.lend.HHTLendRecord;
import com.izhuantou.damain.lend.HHTPrivilege;
import com.izhuantou.damain.lend.HHTTagComponent;
import com.izhuantou.damain.lend.HHTZZInfo;
import com.izhuantou.damain.lend.LendRecord;
import com.izhuantou.damain.lend.LendRecordDDT;
import com.izhuantou.damain.lend.TransferMark;
import com.izhuantou.damain.mobile.detial.HHTTagConsist;
import com.izhuantou.damain.mobile.detial.HHTTagInfo;
import com.izhuantou.damain.mobile.detial.MobileDetialDDT;
import com.izhuantou.damain.mobile.detial.MobileDetialHHT;
import com.izhuantou.damain.mobile.detial.MobileDetialTBZ;
import com.izhuantou.damain.mobile.detial.MobileLendRecord;
import com.izhuantou.damain.mobile.detial.MobilePrivilegeHHT;
import com.izhuantou.damain.mobile.detial.PhotoInfo;
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
import com.izhuantou.service.api.mobile.AvailablemoneyService;
import com.izhuantou.service.api.mobile.TBZLendRecordService;

/***
 * 用于显示我的出借详情
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "app/mobileDetial", produces = "application/json;charset=UTF-8")
public class MobileDetialController {
    private static final Logger logger = LoggerFactory.getLogger(MobileDetialController.class);

    @Autowired
    private TBZLendRecordService TBZLendRecordService;
    @Autowired
    private DetialTBZService detialTBZService;
    @Autowired
    private TransferMarkService transferMarkService;
    @Autowired
    private ExBorrowerDetialService exBorrowerDetialService;
    @Autowired
    private AvailablemoneyService availablemoneyService;
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

    /**
     * 安卓端头笔赚详情显示
     * 
     * @param OID
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobileTBZDetial")
    @ResponseBody
    public OpResult FindResultTBZ(String OID, String pkversion, String memberOID) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);

	    BigDecimal availableMoney = new BigDecimal("0");

	    /** 标的详情 */
	    DetialTBZ tbz = detialTBZService.findByCondition(OID, memberOID);

	    /** 转让标信息 */
	    TransferMark mark = transferMarkService.findByCondition(OID, memberOID);
	    if (memberOID != null) {
		availableMoney = availablemoneyService.countAvailableMoney(memberOID);
	    }

	    MobileDetialTBZ tbzDetial = new MobileDetialTBZ();
	    tbzDetial.setXmmc(tbz.getXmmc());
	    // tbzDetial.setNhll(tbz.getNhlltotal().toString());
	    tbzDetial.setTzjd(tbz.getTzjd().toString());
	    if (tbz.getDebitCreditOID() != null) {
		// 转让标
		tbzDetial.setZrjg(tbz.getZbsl().toString());
		tbzDetial.setZrr(mark.getZrr());
		tbzDetial.setNhll(mark.getYbjkll().setScale(2, BigDecimal.ROUND_DOWN).toString());
		tbzDetial.setDsbj(mark.getDsbj());
		tbzDetial.setDslx(mark.getDqdslx().toString());
		tbzDetial.setXmze(mark.getYbze().toString());
		tbzDetial.setCond(mark.getDebitCreditOID());
		tbzDetial.setFjll(tbz.getFjll().toString());
	    } else {
		/** 原标借款人信息 */
		WebP2pBiddingExamine biddingExamine = exBorrowerDetialService.findByCondition(OID);
		// 原标
		tbzDetial.setXmze(tbz.getZbsl().toString());
		tbzDetial.setLoanuse(biddingExamine.getLoanuse());
		tbzDetial.setRealName(biddingExamine.getRealName());
		tbzDetial.setGender(biddingExamine.getGender());
		tbzDetial.setAge(biddingExamine.getAge().toString());
		tbzDetial.setMinzu(biddingExamine.getMinzu());
		tbzDetial.setIdCard(biddingExamine.getIdCard());
		tbzDetial.setMobile(biddingExamine.getMobile());
		tbzDetial.setHighesteduback(biddingExamine.getHighesteduback());
		tbzDetial.setHkszd(biddingExamine.getHkszd());
		tbzDetial.setGzcs(biddingExamine.getGzcs());
		tbzDetial.setLjgznx(biddingExamine.getLjgznx());
		tbzDetial.setZhiwei(biddingExamine.getZhiwei());
		tbzDetial.setDwxz(biddingExamine.getDwxz());
		tbzDetial.setSshy(biddingExamine.getSshy());
		tbzDetial.setGrysr(biddingExamine.getGrysr());
		tbzDetial.setYwgc(biddingExamine.getYwgc());
		tbzDetial.setYwgf(biddingExamine.getYwgf());
		tbzDetial.setFjll(tbz.getFjll().toString());
		tbzDetial.setCond("0");

	    }
	    tbzDetial.setKtje(tbz.getSykt().toString());
	    tbzDetial.setHkfs(tbz.getHkfs());
	    tbzDetial.setKyzj(availableMoney.toString());

	    return OpResult.getSuccessResult(tbzDetial);
	} catch (Exception e) {
	    logger.error("FindResultTBZ(String OID, String pkversion, String memberOID)" + e.getMessage());
	    return null;
	}

    }

    /**
     * 
     * 安卓点点投详情显示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileDDTDetial")
    @ResponseBody
    public OpResult detailDDT(String OID, String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);

	    DetialDDT ddt = detialDDTService.findByCondition(OID, memberOID);
	    ExDDTInfo ddtEx = exBorrowerDetialService.findDDT(OID);
	    MobileDetialDDT ddtResult = new MobileDetialDDT();

	    ddtResult.setNhll(ddt.getNhll().toString());
	    ddtResult.setTzjd(ddt.getTzjd().toString());
	    ddtResult.setXmze(ddt.getXmze().toString());
	    ddtResult.setCjqx(ddt.getCjqx().toString());
	    ddtResult.setKtje(ddt.getKtje().toString());
	    ddtResult.setHkfs(ddt.getHkfs());
	    ddtResult.setRealName(ddtEx.getRealName());
	    ddtResult.setGender(ddtEx.getGender());
	    ddtResult.setAge(ddtEx.getAge());
	    ddtResult.setHighesteduback(ddtEx.getEducation());
	    ddtResult.setIdCard(ddtEx.getIdcard());
	    ddtResult.setHkszd(ddtEx.getHkszd());
	    ddtResult.setIsmarry(ddtEx.getHyzk());
	    ddtResult.setQyName(ddtEx.getJkqy());
	    ddtResult.setSshy(ddtEx.getSshy());
	    ddtResult.setClsj(ddtEx.getClsj());
	    ddtResult.setZczb(ddtEx.getZczb());
	    ddtResult.setQydizhi(ddtEx.getQydz());
	    ddtResult.setXyzk(ddtEx.getXyzk());

	    return OpResult.getSuccessResult(ddtResult);
	} catch (Exception e) {
	    logger.error("public OpResult detailDDT(String OID, String memberOID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /** 安卓环环投详情显示 */
    @RequestMapping(value = "/mobileHHTDetial")
    @ResponseBody
    public OpResult FindResultHHT(String OID, String pkversion, String memberOID) {

	String str = null;
	MobileDetialHHT hhtResult = new MobileDetialHHT();

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);

	    DetialHHT hht = detialHHTService.findByCondition(OID, memberOID);
	    hhtResult.setJxother(hht.getJxother().toString());
	    hhtResult.setNhll(hht.getNhll().toString());
	    hhtResult.setZbsl(hht.getZbsl().toString());
	    hhtResult.setCjqx(String.valueOf(hht.getCjqx()));
	    hhtResult.setKtje(hht.getKtje().toString());
	    hhtResult.setHkfs(hht.getRepaymentType());
	    return OpResult.getSuccessResult(hhtResult);
	} catch (Exception e) {
	    logger.error(
		    "public OpResult FindResultHHT(String OID, String pkversion, String memberOID)" + e.getMessage());
	    return null;
	}

    }

    /**
     * 安卓转转投详情显示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileZZTDetial")
    @ResponseBody
    public OpResult detailZZT(String OID, String memberOID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    DetialZZT ddt = detialZZTService.findByCondition(OID, memberOID);
	    return OpResult.getSuccessResult(ddt);
	} catch (Exception e) {
	    logger.error("public OpResult detailZZT(String OID, String memberOID, String pkversion) " + e.getMessage());
	    return null;
	}
    }

    /**
     * 环环投加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileHHTPrivilege")
    @ResponseBody
    public OpResult privilege(String OID, String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    MobilePrivilegeHHT hhtPrivilege = new MobilePrivilegeHHT();
	    List<MobilePrivilegeHHT> listResult = new ArrayList<>();
	    List<HHTPrivilege> list = privilegeService.findPrivilege(OID, memberOID);
	    for (HHTPrivilege hhtPrivilegeList : list) {
		hhtPrivilege.setLowAmount(hhtPrivilegeList.getLowAmount());
		hhtPrivilege.setPrivilegeName(hhtPrivilegeList.getPrivilegeName());
		hhtPrivilege.setPrivilegeTerm(hhtPrivilegeList.getPrivilegeTerm());
		hhtPrivilege.setRuleIntroduce(hhtPrivilegeList.getRuleIntroduce());
		hhtPrivilege.setKsDate(hhtPrivilegeList.getKsDate());
		hhtPrivilege.setJsDate(hhtPrivilegeList.getJsDate());
		hhtPrivilege.setPrivilegeOID(hhtPrivilegeList.getPrivilegeOID());
		hhtPrivilege.setMappingOID(hhtPrivilegeList.getMappingOID());
		listResult.add(hhtPrivilege);

	    }
	    return OpResult.getSuccessResult(listResult);
	} catch (Exception e) {
	    logger.error("public OpResult privilege(String OID, String memberOID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 环环标的组成
     * 
     * @param page
     * @param OID
     * @return
     */

    @RequestMapping(value = "/mobilehhtTagComponent")
    @ResponseBody
    public OpResult FindResultHHTTagComponent(Integer page, String OID, String pkversion) {

	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);

	    Map<String, Object> map = new HashMap<>();
	    if (page == null) {
		page = 1;
	    }
	    map = hhtTagComponentService.findByCondition(page, OID);
	    List<HHTTagComponent> tagList = new ArrayList<>();
	    List<HHTTagConsist> tagListResult = new ArrayList<>();
	    tagList = (List) map.get("data");

	    for (HHTTagComponent tag : tagList) {
		HHTTagConsist tagConsist = new HHTTagConsist();
		tagConsist.setJkmc(tag.getJkmc());
		tagConsist.setJkje(tag.getJkje().toString());
		tagConsist.setJkll(tag.getJkll().toString());
		tagConsist.setZqs(tag.getTerm());
		tagConsist.setSyqs(tag.getSyqs());
		tagConsist.setBiddingOID(tag.getOID());
		tagConsist.setFinancetransfertype(tag.getFinancetransfertype());
		tagConsist.setDebitCreditOID(tag.getDebitCreditOID());
		tagListResult.add(tagConsist);
	    }
	    map.put("data", tagListResult);
	    return OpResult.getSuccessResult(map);
	} catch (Exception e) {
	    logger.error("public OpResult FindResultHHTTagComponent(Integer page, String OID, String pkversion)"
		    + e.getMessage());
	    return null;
	}
    }

    /**
     * 头笔赚投风控信息
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileTBZAuditInfo")
    @ResponseBody
    public OpResult findAuditInfo(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    List<AuditInfo> list = auditInfoService.findByCondition(OID);
	    List<PhotoInfo> result = new ArrayList<>();
	    for (AuditInfo auditInfo : list) {
		PhotoInfo info = new PhotoInfo();
		info.setPhotoOID(auditInfo.getPhotoOID());
		info.setPsnr(auditInfo.getNameCN());
		result.add(info);
	    }
	    return OpResult.getSuccessResult(result);
	} catch (Exception e) {
	    logger.error("public OpResult findAuditInfo(String OID, String pkversion) " + e.getMessage());
	    return null;
	}
    }

    /**
     * 点点投风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileDDTAuditInfo")
    @ResponseBody
    public OpResult findAuditDDTInfo(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {

	    List<AuditInfo> list = auditInfoService.findByConditionDDT(OID);
	    List<PhotoInfo> result = new ArrayList<>();
	    for (AuditInfo auditInfo : list) {
		PhotoInfo info = new PhotoInfo();
		info.setPhotoOID(auditInfo.getPhotoOID());
		info.setPsnr(auditInfo.getNameCN());
		result.add(info);
	    }
	    return OpResult.getSuccessResult(result);
	} catch (Exception e) {
	    logger.error("public OpResult findAuditInfo(String OID, String pkversion) " + e.getMessage());
	    return null;
	}
    }

    /**
     * 
     * 头笔赚出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileTBZlendRecord")
    @ResponseBody
    public OpResult FindLendRecord(Integer page, String OID, String pkversion) {

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    if (page == null) {
		page = 1;
	    }
	    Pagination lr = new Pagination<LendRecord>();
	    String memberOID = null;
	    DetialTBZ tbz = detialTBZService.findByCondition(OID, memberOID);
	    if (tbz.getDebitCreditOID() != null) {
		lr = lendRecordService.findByBusinessZCOID(page, OID);
		List<LendRecord> list1 = lr.getData();
		List<MobileLendRecord> list = new ArrayList<>();
		for (LendRecord lendRecord : list1) {
		    MobileLendRecord lend = new MobileLendRecord();
		    lend.setName(lendRecord.getMobile());
		    lend.setMoney(lendRecord.getMoney().toString());
		    lend.setAddDateTime(lendRecord.getSj());
		    lend.setPrincipalMoney(lendRecord.getPrincipalMoney());
		    lend.setNameCN(getFullNameStr(lendRecord.getNameCN()));
		    list.add(lend);
		}
		lr.setData(list);
	    } else {
		lr = lendRecordService.findByBusinessOID(page, OID);
		List<LendRecord> list1 = lr.getData();
		List<MobileLendRecord> list = new ArrayList<>();
		for (LendRecord lendRecord : list1) {
		    MobileLendRecord lend = new MobileLendRecord();
		    lend.setName(lendRecord.getMobile());
		    lend.setMoney(lendRecord.getMoney().toString());
		    lend.setAddDateTime(lendRecord.getSj());
		    lend.setPrincipalMoney(lendRecord.getPrincipalMoney());
		    lend.setNameCN(getFullNameStr(lendRecord.getNameCN()));
		    list.add(lend);
		}
		lr.setData(list);
	    }

	    return OpResult.getSuccessResult(lr);
	} catch (Exception e) {
	    logger.error(
		    "public OpResult FindLendRecord(Integer page, String OID, String pkversion) " + e.getMessage());
	    return null;
	}

    }

    /**
     * 环环投出借记录
     * 
     * @param page
     * @param OID
     * @return
     */

    @RequestMapping(value = "/mobileHHTLendRecord")
    @ResponseBody
    public OpResult FindResultHHTLendRecord(Integer page, String OID, String pkversion) {

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	String str = null;
	try {
	    Pagination hhtresult = detialHHTService.findByCondition(page, OID);
	    List<HHTLendRecord> hht = hhtresult.getData();
	    List<MobileLendRecord> resultList = new ArrayList<>();
	    for (HHTLendRecord hhtLendRecord : hht) {
		MobileLendRecord result = new MobileLendRecord();
		result.setName(hhtLendRecord.getName());
		result.setNameCN(getFullNameStr(getFullNameStr(hhtLendRecord.getNameCN())));
		result.setAddDateTime(hhtLendRecord.getSj());
		result.setMoney(hhtLendRecord.getMoney());
		result.setPrincipalMoney(hhtLendRecord.getPrincipalMoney().toString());
		resultList.add(result);
	    }
	    hhtresult.setData(resultList);
	    return OpResult.getSuccessResult(hhtresult);
	} catch (Exception e) {
	    logger.error("public OpResult FindResultHHTLendRecord(Integer page, String OID, String pkversion)  "
		    + e.getMessage());
	    return null;
	}
    }

    /**
     * 
     * DDT出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/ddtLendRecord")
    @ResponseBody
    public OpResult FindLendRecordDDT(Integer page, String OID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    if (page == null) {
		page = 1;
	    }
	    String str = null;
	    Pagination lr = lendRecordDDTService.findByBusinessOID(page, OID);
	    List<LendRecordDDT> ddt = lr.getData();
	    List<MobileLendRecord> resultList = new ArrayList<>();
	    for (LendRecordDDT DDtLendRecord : ddt) {
		MobileLendRecord result = new MobileLendRecord();
		result.setName(DDtLendRecord.getName());
		result.setNameCN(getFullNameStr(DDtLendRecord.getNameCN()));
		result.setAddDateTime(DDtLendRecord.getSj());
		result.setMoney(DDtLendRecord.getMoney());
		result.setPrincipalMoney(DDtLendRecord.getPrincipalMoney().toString());
		resultList.add(result);
	    }
	    lr.setData(resultList);
	    return OpResult.getSuccessResult(lr);
	} catch (Exception e) {
	    logger.error(
		    "public OpResult FindLendRecordDDT(Integer page, String OID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 
     * ZZT出借记录
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileZZTlendRecord")
    @ResponseBody
    public OpResult FindLendRecordZZT(Integer page, String OID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    if (page == null) {
		page = 1;
	    }
	    String str = null;
	    Pagination lr = lendRecordService.findByZZTBusinessOID(page, OID);
	    List<LendRecord> zzt = lr.getData();
	    List<MobileLendRecord> resultList = new ArrayList<>();
	    for (LendRecord ZZtLendRecord : zzt) {
		// List<MobileLendRecord> resultList = new ArrayList<>();
		MobileLendRecord result = new MobileLendRecord();
		result.setName(ZZtLendRecord.getName());
		result.setNameCN(getFullNameStr(ZZtLendRecord.getNameCN()));
		result.setAddDateTime(ZZtLendRecord.getSj());
		result.setMoney(ZZtLendRecord.getMoney().toString());
		result.setPrincipalMoney(ZZtLendRecord.getPrincipalMoney().toString());
		resultList.add(result);
	    }
	    lr.setData(resultList);
	    return OpResult.getSuccessResult(lr);
	} catch (Exception e) {
	    logger.error(
		    "public String FindLendRecordZZT(Integer page, String OID, String pkversion)" + e.getMessage());
	    return null;
	}

    }

    /**
     * 环环投散标详情债转信息
     * 
     * @param OID
     * @return
     */
    @RequestMapping(value = "/mobileHHTZZInfo")
    @ResponseBody
    public OpResult FindResultHHTZZInfo(String OID, String financetransfer, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    financetransfer = "nromal";
	    HHTZZInfo info = hhtZZInfoService.findByOID(OID);
	    HHTTagInfo tagInfo = new HHTTagInfo();
	    tagInfo.setNhll(info.getNhll().toString());
	    tagInfo.setHkzt(info.getHkzt());
	    tagInfo.setOriginAmount(info.getJkze().toString());
	    if (!financetransfer.equals("normal")) {
		tagInfo.setZrr(info.getZzr());
		tagInfo.setZzje(info.getZrje().toString());
		tagInfo.setDslx(info.getDqdslx().toString());
		tagInfo.setDsbj(info.getDsbj().toString());
		tagInfo.setLoanNumber(info.getZzbh());
	    } else {
		WebP2pBiddingExamine biddingExamine = exBorrowerDetialService.findByConditionHHT(OID);
		if (biddingExamine != null) {
		    tagInfo.setLoanNumber(biddingExamine.getLoanNumber());
		} else {
		    ExBorrowerHHTCDInfo cdInfo = exBorrowerDetialService.findCDHHT(OID);
		    if (cdInfo != null) {
			tagInfo.setLoanNumber(cdInfo.getLoanNumber());
		    }

		}
	    }
	    return OpResult.getSuccessResult(tagInfo);
	} catch (Exception e) {
	    logger.error("public OpResult FindResultHHTZZInfo(String OID, String financetransfer, String pkversion)"
		    + e.getMessage());
	    return null;
	}
    }

    /**
     * 环环投车抵风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileAuditCDHHTInfo")
    @ResponseBody
    public OpResult findCDAuditHHTInfo(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    List<AuditInfo> list = auditInfoService.findByConditionHHTCD(OID);
	    List<HHTTagInfo> listResult = new ArrayList<>();
	    for (AuditInfo auditInfo : list) {
		HHTTagInfo info = new HHTTagInfo();
		info.setInfoshow(auditInfo.getPhotoOID());
		listResult.add(info);
	    }
	    return OpResult.getSuccessResult(listResult);
	} catch (Exception e) {
	    logger.error("public OpResult findCDAuditHHTInfo(String OID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 环环投风控信息、
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileAuditHHTInfo")
    @ResponseBody
    public OpResult findAuditHHTInfo(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    List<AuditInfo> list = auditInfoService.findByConditionHHT(OID);
	    List<HHTTagInfo> listResult = new ArrayList<>();
	    for (AuditInfo auditInfo : list) {
		HHTTagInfo info = new HHTTagInfo();
		info.setInfoshow(auditInfo.getPhotoOID());
		listResult.add(info);
	    }
	    return OpResult.getSuccessResult(listResult);
	} catch (Exception e) {
	    logger.error("public OpResult findAuditHHTInfo(String OID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 
     * 环环投原标借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileExHHTDetial")
    @ResponseBody
    public OpResult FindResulExHHTDetial(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    WebP2pBiddingExamine biddingExamine = exBorrowerDetialService.findByConditionHHT(OID);
	    return OpResult.getSuccessResult(biddingExamine);
	} catch (Exception e) {
	    logger.error("public OpResult FindResulExHHTDetial(String OID, String pkversion)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 环环投车抵借款人信息
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileExBorrowHHTCDZInfo")
    @ResponseBody
    public OpResult findexBorrowHHTCDZInfo(String OID, String pkversion) {
	String str = null;
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    OID = rsac.serverDecrypt(OID, pkversion);
	    ExBorrowerHHTCDInfo cdInfo = exBorrowerDetialService.findCDHHT(OID);
	    return OpResult.getSuccessResult(cdInfo);
	} catch (Exception e) {
	    logger.error("public OpResult findexBorrowHHTCDZInfo(String OID, String pkversion)" + e.getMessage());
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
}
