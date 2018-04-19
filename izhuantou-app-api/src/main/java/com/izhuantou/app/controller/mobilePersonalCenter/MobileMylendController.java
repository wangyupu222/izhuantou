package com.izhuantou.app.controller.mobilePersonalCenter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.rsa.RsaCodeTool;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendCYZDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialDDT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendDetialWCHHT;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendYWCDTO;
import com.izhuantou.damain.vo.MylendResultDTO;
import com.izhuantou.damain.vo.MylendZQZRDTO;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileMyLendDetialService;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileMyLendListService;
import com.izhuantou.service.api.personalCenter.MylendService;

@Controller
@RequestMapping(value = "app/mobilemylend", produces = "application/json;charset=UTF-8")
public class MobileMylendController {

    @Autowired
    private MylendService mylendService;
    @Autowired
    private MobileMyLendDetialService mobileMyLendDetialService;
    @Autowired
    private MobileMyLendListService mobileMyLendListService;

    /**
     * 头笔赚持有列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobiletbzList")
    @ResponseBody
    public OpResult newTBZList(String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	memberOID = "370518b20a5dca712804025f2e15867a";
	List<MylendResultDTO> map = mylendService.findTBZHavingBymemberOID(memberOID);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 头笔赚完成列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobiletbzWCList")
    @ResponseBody
    public OpResult newTBZWCList(String memberOID, String pkversion) {
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
	List<MylendResultDTO> map = mylendService.findTBZWCBymemberOID(memberOID);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 债权转让可转列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobilezqzrList")
    @ResponseBody
    public OpResult zqzrList(String memberOID, Integer currentPage, String pkversion) {
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
	Map<String, Object> map = mylendService.findZQKZBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 债权转让记录列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobilezqzrWCList")
    @ResponseBody
    public OpResult zqzrWCList(String memberOID, Integer currentPage, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "baada6fb0a5dca7142f8e9b7d84dae6b";
	if (currentPage == null) {
	    currentPage = 1;
	}

	Map<String, Object> map = mylendService.findZQWCBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 点击申请
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilezqzrSq")
    @ResponseBody
    public OpResult zqzrSq(String memberOID, String PaydebitOID, String businessOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    PaydebitOID = rsac.serverDecrypt(businessOID, pkversion);
	    businessOID = rsac.serverDecrypt(PaydebitOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// memberOID = "baada6fb0a5dca7142f8e9b7d84dae6b";
	MylendZQZRDTO dto = mylendService.findZQSQBymemberOIDAndPaydebitOID(memberOID, PaydebitOID, businessOID);
	if (dto != null) {
	    return OpResult.getSuccessResult(dto);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 点击确认申请转让
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobilesaveZRMemage")
    public String saveZRMemage(String memberOID, MylendZQZRDTO mylendZQZRDTO, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密 并放回实体
	    mylendZQZRDTO.setXmmc(rsac.serverDecrypt(mylendZQZRDTO.getXmmc(), pkversion));
	    String cjje = rsac.serverDecrypt(mylendZQZRDTO.getCjje().toString(), pkversion);
	    mylendZQZRDTO.setCjje(new BigDecimal(cjje));
	    mylendZQZRDTO.setCjsjDate(rsac.serverDecrypt(mylendZQZRDTO.getCjsjDate().toString(), pkversion));
	    mylendZQZRDTO.setSyqs(Integer.valueOf(rsac.serverDecrypt(mylendZQZRDTO.getSyqs().toString(), pkversion)));
	    mylendZQZRDTO.setZqs(rsac.serverDecrypt(mylendZQZRDTO.getTatalTerm().toString(), pkversion));
	    mylendZQZRDTO.setYsbx(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getYsbx().toString(), pkversion)));
	    mylendZQZRDTO.setZrsybj(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getYsbj().toString(), pkversion)));
	    mylendZQZRDTO.setZrsylx(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getYslx().toString(), pkversion)));
	    mylendZQZRDTO.setBiddingType(
		    Integer.valueOf(rsac.serverDecrypt(mylendZQZRDTO.getBiddingType().toString(), pkversion)));
	    mylendZQZRDTO.setNhll(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getNhll().toString(), pkversion)));
	    mylendZQZRDTO.setCjsjTime(rsac.serverDecrypt(mylendZQZRDTO.getCjsjTime(), pkversion));
	    mylendZQZRDTO.setZrsxf(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getSxf(), pkversion)));
	    mylendZQZRDTO.setJg(new BigDecimal(rsac.serverDecrypt(mylendZQZRDTO.getJg().toString(), pkversion)));
	    mylendZQZRDTO.setPaydebitOID(rsac.serverDecrypt(mylendZQZRDTO.getPaydebitOID().toString(), pkversion));
	    mylendZQZRDTO.setBusinessOID(rsac.serverDecrypt(mylendZQZRDTO.getBusinessOID().toString(), pkversion));

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	String dto = mylendService.ZQZRBoxsave(mylendZQZRDTO);
	if (StringUtil.isNotEmpty(dto) && dto.equals("1")) {
	    return "success";
	} else {
	    return "success";
	}
    }

    /**
     * 个人中心我的出借环环投已完成详情测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilehhtDetialYWC")
    @ResponseBody
    public OpResult mobilehhtDetial(String payCashPoolOID, String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    payCashPoolOID = rsac.serverDecrypt(payCashPoolOID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "baada6fb0a5dca7142f8e9b7d84dae6b";
	// payCashPoolOID = "2ba6d6960a5dca7149b2b591271f2916";
	MobileMyLendDetialWCHHT hht = mobileMyLendDetialService.FindByCashPoolWC(payCashPoolOID, memberOID);
	MobileMyLendDetialDTO result = new MobileMyLendDetialDTO();
	try {
	    result.setCashPoolOID(hht.getCashPoolOID());
	    result.setJxother(hht.getJXother().toString());
	    result.setCjje(hht.getCjje().toString());
	    result.setCjsjTime(hht.getCjsj());
	    result.setDqsj(hht.getDqsjTime());
	    result.setJssj(hht.getJssj());
	    result.setJxsj(hht.getJxsjTime());
	    result.setNhll(hht.getNhll());
	    result.setXmmc(hht.getXmmc());
	    result.setYsbx(hht.getYsbx().toString());
	    result.setZqs(hht.getZqs().toString());
	    if (hht.getTqOID() != null) {
		result.setTqOID(hht.getTqOID());
		result.setPrivilegeName(hht.getPrivilegeName());
		result.setPrivilegeRange(hht.getPrivilegeRange().toString());
		result.setPrivilegeTerm(hht.getPrivilegeTerm().toString());
		result.setTqsy(hht.getTqsy().toString());
		result.setSyqs(hht.getSyqs());
	    }
	    result.setCreditType(hht.getCreditType());
	    result.setDOID(hht.getDOID());
	    result.setXghkr(hht.getXghkrTime());
	    result.setBiOID(hht.getBiOID());
	    result.setProductType(hht.getProductType());
	} catch (Exception e) {
	    // TODO: handle exception
	}

	if (hht != null) {
	    return OpResult.getSuccessResult(result);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 个人中心我的出借环环进行详情测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilehhtDetialJxz")
    @ResponseBody
    public OpResult mobilehhtDetialJxz(String payCashPoolOID, String memberOID, String pkversion) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    payCashPoolOID = rsac.serverDecrypt(payCashPoolOID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "baada6fb0a5dca7142f8e9b7d84dae6b";
	// payCashPoolOID = "2ba6d6960a5dca7149b2b591271f2916";
	MobileMyLendDetialHHT hht = mobileMyLendDetialService.FindByCashPool(payCashPoolOID, memberOID);

	if (hht != null) {
	    return OpResult.getSuccessResult(hht);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 个人中心我的出借出借中DD投测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileddtDetial")
    @ResponseBody
    public OpResult mobileddtDetial(String pkversion, String debitCreditOID, String memberOID) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    debitCreditOID = rsac.serverDecrypt(debitCreditOID, pkversion);
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "72730d8d0a5dca7122b26d135e708bbb";
	// debitCreditOID = "0001c1cb0a5dca7161e972e97562452a";
	MobileMyLendDetialDDT ddt = mobileMyLendDetialService.FindByDDTDebitCreditOID(memberOID, debitCreditOID);
	if (ddt != null) {
	    return OpResult.getSuccessResult(ddt);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 个人中心我的出借出借中DD投测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileddtDetialYWC")
    @ResponseBody
    public OpResult mobileddtDetialCYZ(String pkversion, String memberOID, String debitCreditOID) {
	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	    debitCreditOID = rsac.serverDecrypt(debitCreditOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "72730d8d0a5dca7122b26d135e708bbb";
	// String debitCreditOID = "0001c1cb0a5dca7161e972e97562452a";
	MobileMyLendDetialDDT ddt = mobileMyLendDetialService.FindByDDTDebitCreditOID(memberOID, debitCreditOID);
	if (ddt != null) {
	    return OpResult.getSuccessResult(ddt);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 个人中心我的出借持有中列表测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/MobileMyLendListCYZ")
    @ResponseBody
    public OpResult MobileMyLendListXin(String memberOID, String pkversion) {
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
	List<MobileMyLendCYZDTO> ddt = mobileMyLendListService.LendCYZList(memberOID);
	if (ddt != null) {
	    return OpResult.getSuccessResult(ddt);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 个人中心我的出借已完成列表测试
     * 
     * @param request
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/MobileMyLendListYWC")
    @ResponseBody
    public OpResult MobileMyLendListXinYWC(String pkversion, String memberOID) {

	// 初始化操作类，包括初始化钥匙对集合
	RsaCodeTool rsac = new RsaCodeTool();
	try {
	    // 对密文进行解密
	    memberOID = rsac.serverDecrypt(memberOID, pkversion);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// memberOID = "00d963010a5dca713ee0482d707f5ef9";
	List<MobileMyLendYWCDTO> ddt = mobileMyLendListService.LendYWCList(memberOID);
	if (ddt != null) {
	    return OpResult.getSuccessResult(ddt);
	}
	return OpResult.getFailedResult("失败");
    }

}
