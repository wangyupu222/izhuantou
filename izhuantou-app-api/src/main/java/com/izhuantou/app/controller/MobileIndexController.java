package com.izhuantou.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.app.controller.mobilePersonalCenter.MobileUserController;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.rsa.RsaCodeTool;
import com.izhuantou.damain.lend.DDTou;
import com.izhuantou.damain.lend.HHTou;
import com.izhuantou.damain.lend.XinShouZhuan;
import com.izhuantou.damain.lend.ZZTou;
import com.izhuantou.damain.mobile.ActivityModel;
import com.izhuantou.damain.mobile.MobileFourNum;
import com.izhuantou.damain.mobile.MobileNewZhuan;
import com.izhuantou.damain.mobile.MobileTYJPoJo;
import com.izhuantou.damain.webp2p.WebP2pFourAmounts;
import com.izhuantou.service.api.lend.DDTouService;
import com.izhuantou.service.api.lend.HHTouService;
import com.izhuantou.service.api.lend.XinShouZhuanService;
import com.izhuantou.service.api.lend.ZZTouService;
import com.izhuantou.service.api.mobile.ActivityBiaoService;
import com.izhuantou.service.api.mobile.MobileTYJService;
import com.izhuantou.service.api.p2p.FourNumService;

/***
 * 用于显示首页信息展示
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "app/index", produces = "application/json;charset=UTF-8")
public class MobileIndexController {
    @Autowired
    private FourNumService fourNumberService;
    @Autowired
    private XinShouZhuanService xinShouZhuanService;
    @Autowired
    private MobileTYJService mobileTYJService;
    @Autowired
    private ActivityBiaoService activityBiaoService;
    @Autowired
    private HHTouService hhTouService;
    @Autowired
    private DDTouService ddTouService;
    @Autowired
    private ZZTouService zzTouService;
    private static final Logger logger = LoggerFactory.getLogger(MobileUserController.class);

    /**
     * 首页四个主数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileFourNum")
    @ResponseBody
    public OpResult findFourNum() {
	String str = null;
	WebP2pFourAmounts fourAmounts = this.fourNumberService.findFourNum();
	MobileFourNum fourNum = new MobileFourNum();
	fourNum.setMoney_new(fourAmounts.getMoney1());
	fourNum.setInterestMoney_new(fourAmounts.getInsertMoney1());
	fourNum.setMember_new(fourAmounts.getMember1());
	fourNum.setOperationTime(fourAmounts.getSumDay1());

	return OpResult.getSuccessResult(fourNum);
    }

    /**
     * 首页头笔赚数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileNewZhuan")
    @ResponseBody
    public OpResult mobileNewZhuan() {
	String str = null;
	XinShouZhuan xin = xinShouZhuanService.FindAll();
	MobileNewZhuan newZhuan = new MobileNewZhuan();

	newZhuan.setOID(xin.getOID());
	newZhuan.setXmmc(xin.getBiddingName());
	newZhuan.setNhll(xin.getYearRate());
	newZhuan.setCjqx(xin.getDays());
	newZhuan.setProductStatus(xin.getProductStatus());
	newZhuan.setSyje(xin.getSy());
	newZhuan.setSzds(xin.getSzds());
	newZhuan.setXtcz(xin.getSj());
	if (xin.getDebitCreditOID() == null) {
	    newZhuan.setCond("0");
	} else {
	    newZhuan.setCond(xin.getDebitCreditOID());
	}
	if (xin.getOID() != null) {
	    newZhuan.setResult("success");
	} else {
	    newZhuan.setResult("error");
	}

	return OpResult.getSuccessResult(newZhuan);
    }

    /**
     * 首页头体验金
     * 
     * @return json
     */
    @RequestMapping(value = "/tyj")
    @ResponseBody
    public OpResult MobileTYJ(String memberOID, String pkversion) {
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
	// String memberOID = "370518b20a5dca712804025f2e15867a";
	MobileTYJPoJo mobileTYJ1 = mobileTYJService.tybInfo(memberOID);

	return OpResult.getSuccessResult(mobileTYJ1);
    }

    /**
     * 活动标
     * 
     * @return json
     */
    @RequestMapping(value = "/activityBiao")
    @ResponseBody
    public OpResult activityBiao() {
	String str = null;
	List<ActivityModel> model = new ArrayList<>();
	model = activityBiaoService.findActBiao();

	return OpResult.getSuccessResult(model);
    }

    /**
     * 首页环环投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileHH")
    @ResponseBody
    public OpResult FindResulthh() {
	String str = null;
	List<HHTou> list = hhTouService.findResult();

	return OpResult.getSuccessResult(list);
    }

    /**
     * 首页点点投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileDD")
    @ResponseBody
    public OpResult FindResultdd() {
	String str = null;
	List<DDTou> list = ddTouService.findResult();

	return OpResult.getSuccessResult(list);
    }

    /**
     * 首页转转投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileZZ")
    @ResponseBody
    public OpResult FindResultzz() {
	String str = null;
	List<ZZTou> list = zzTouService.findResult();

	return OpResult.getSuccessResult(list);
    }

}
