package com.izhuantou.app.controller.mobileDisplay;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayDDT;
import com.izhuantou.damain.lend.DisplayHHT;
import com.izhuantou.damain.lend.DisplayTBZ;
import com.izhuantou.damain.lend.DisplayZZT;
import com.izhuantou.damain.mobile.DisplayModel;
import com.izhuantou.service.api.lend.DisplayDDTService;
import com.izhuantou.service.api.lend.DisplayHHTService;
import com.izhuantou.service.api.lend.DisplayTBZService;
import com.izhuantou.service.api.lend.DisplayZZTService;

/***
 * 用于显示我的出借
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "app/mobieLend", produces = "application/json;charset=UTF-8")
public class MobileDisplayController {
    @Autowired
    private DisplayTBZService displayTBZService;
    @Autowired
    private DisplayHHTService displayHHTService;
    @Autowired
    private DisplayDDTService displayDDTService;
    @Autowired
    private DisplayZZTService displayZZTService;
    private static final Logger logger = LoggerFactory.getLogger(MobileDisplayController.class);

    /**
     * 我的出借->头笔赚数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileTBZList", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResulttbz(Integer page) {

	if (page == null) {
	    page = 1;
	}
	String str = null;
	String sortp = "";
	Pagination<DisplayTBZ> list = this.displayTBZService.showProductsByPage(page, sortp);
	List<DisplayTBZ> tbzlist = list.getData();

	List<DisplayModel> tbzResult = new ArrayList<DisplayModel>();
	try {
	    for (DisplayTBZ displayTBZ : tbzlist) {
		DisplayModel model = new DisplayModel();
		model.setOID(displayTBZ.getOID());
		model.setXmmc(displayTBZ.getXmmc());
		model.setNhll(displayTBZ.getNhll());
		model.setCjqx(displayTBZ.getCjqx().toString());
		model.setProductStatus(displayTBZ.getProductStatus());
		model.setKtje(displayTBZ.getSykt());
		tbzResult.add(model);
	    }
	    list.setData(tbzlist);
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    logger.error("public OpResult FindResulttbz(Integer page)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 我的出借->环环投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileHHTList", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResulthh(Integer page) {
	try {
	    if (page == null) { // TODO 手机端显示什么？是所有的可投还是单个的可投
		page = 1;
	    }
	    String str = null;
	    String sortp = "";
	    String status="";
	    Pagination<DisplayHHT> list = this.displayHHTService.showProductsByPage(page,status,sortp);
	    List<DisplayHHT> hhtlist = list.getData();
	    List<DisplayModel> hhtResult = new ArrayList<DisplayModel>();
	    for (DisplayHHT displayHHT : hhtlist) {
		DisplayModel model = new DisplayModel();
		model.setOID(displayHHT.getOID());
		model.setXmmc(displayHHT.getXmmc());
		model.setNhll(displayHHT.getNhll());
		model.setCjqx(displayHHT.getCjqx().toString());
		if (displayHHT.getJXother() != null) {
		    model.setJxother(displayHHT.getJXother().toString());
		}

		model.setProductStatus(displayHHT.getProductStatus());
		model.setKtje(displayHHT.getSykt());
		hhtResult.add(model);
	    }
	    list.setData(hhtlist);
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    logger.error("public OpResult FindResulthh(Integer page)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 我的出借->点点投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileDDTList", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResultdd(Integer page) {
	try {
	    if (page == null) {
		page = 1;
	    }
	    String sortp = "";
	    String str = null;
	    Pagination<DisplayDDT> list = this.displayDDTService.showProductsByPage(page, sortp);
	    List<DisplayDDT> ddtlist = list.getData();
	    List<DisplayModel> ddtResult = new ArrayList<DisplayModel>();
	    for (DisplayDDT displayDDT : ddtlist) {
		DisplayModel model = new DisplayModel();
		model.setOID(displayDDT.getOID());
		model.setXmmc(displayDDT.getXmmc());
		model.setNhll(displayDDT.getNhll());
		model.setCjqx(displayDDT.getCjqx().toString());
		model.setProductStatus(displayDDT.getProductStatus());
		model.setKtje(displayDDT.getSykt());
		ddtResult.add(model);
	    }
	    list.setData(ddtlist);

	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    logger.error("public OpResult FindResultdd(Integer page)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 我的出借->转转投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/mobileZZTList", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResultzz(Integer page) {
	try {
	    if (page == null) {
		page = 1;
	    }
	    String sortp = "";
	    String str = null;
	    Pagination<DisplayZZT> list = this.displayZZTService.showProductsByPage(page, sortp);
	    List<DisplayZZT> zztlist = list.getData();
	    List<DisplayModel> zztResult = new ArrayList<DisplayModel>();
	    for (DisplayZZT displayZZT : zztlist) {
		DisplayModel model = new DisplayModel();
		model.setOID(displayZZT.getOID());
		model.setXmmc(displayZZT.getXmmc());
		model.setNhll(displayZZT.getNhll());
		model.setCjqx(displayZZT.getCjqx().toString());
		model.setProductStatus(displayZZT.getProductStatus());
		model.setKtje(displayZZT.getSykt());
		zztResult.add(model);
	    }
	    list.setData(zztlist);
	    return OpResult.getSuccessResult(list);
	} catch (Exception e) {
	    logger.error("public OpResult FindResultzz(Integer page)" + e.getMessage());
	    return null;
	}
    }

}
