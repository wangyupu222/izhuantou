package com.izhuantou.portal.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.damain.lend.DDTou;
import com.izhuantou.damain.lend.HHTou;
import com.izhuantou.damain.lend.XinShouZhuan;
import com.izhuantou.damain.lend.ZZTou;
import com.izhuantou.damain.vo.CustomerTyjDTO;
import com.izhuantou.damain.webp2p.WebP2pFourAmounts;
import com.izhuantou.service.api.lend.DDTouService;
import com.izhuantou.service.api.lend.ExperienceMoneyService;
import com.izhuantou.service.api.lend.HHTouService;
import com.izhuantou.service.api.lend.XinShouZhuanService;
import com.izhuantou.service.api.lend.ZZTouService;
import com.izhuantou.service.api.p2p.FourNumService;

/***
 * 用于显示首页信息展示
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "/index", produces = "application/json;charset=UTF-8")
public class IndexController {
    @Autowired
    private FourNumService fourNumberService;
    @Autowired
    private XinShouZhuanService xinShouZhuanService;
    @Autowired
    private HHTouService hhTouService;
    @Autowired
    private DDTouService ddTouService;
    @Autowired
    private ZZTouService zzTouService;
    @Autowired
    private ExperienceMoneyService experienceMoneyService;

    /**
     * 首页四个主数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/fourNum")
    @ResponseBody
    public OpResult findFourNum() {

	WebP2pFourAmounts fourAmounts = this.fourNumberService.findFourNum();

	return OpResult.getSuccessResult(fourAmounts);
    }

    /**
     * 首页头笔赚数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/touBiZhuan")
    @ResponseBody
    public OpResult findXinShouZhuan() {
	String str = null;
	XinShouZhuan xinShouZhuan = xinShouZhuanService.FindAll();

	return OpResult.getSuccessResult(xinShouZhuan);
    }

    /**
     * 首页环环投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/HHTou")
    @ResponseBody
    public OpResult FindResulthh() {
	List<HHTou> list = hhTouService.findResult();

	return OpResult.getSuccessResult(list);
    }

    /**
     * 首页点点投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/DDTou")
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
    @RequestMapping(value = "/ZZTou")
    @ResponseBody
    public OpResult FindResultzz() {
	List<ZZTou> list = zzTouService.findResult();
	return OpResult.getSuccessResult(list);
    }

    /**
     * 体验金人数
     * 
     * @return json
     */
    @RequestMapping(value = "/TYMoney")
    @ResponseBody
    public OpResult findMember() {
	CustomerTyjDTO dto = experienceMoneyService.findMember();
	return OpResult.getSuccessResult(dto);
    }

    /**
     * 返回主页
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/main")
    public String findByAll1() {

	return "index";
    }

}
