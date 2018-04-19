package com.izhauntou.portal.controller.lend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayDDT;
import com.izhuantou.damain.lend.DisplayHHT;
import com.izhuantou.damain.lend.DisplayTBZ;
import com.izhuantou.damain.lend.DisplayZZT;
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
@RequestMapping(value = "lend", produces = "application/json;charset=UTF-8")
public class DisplaylendController {
    @Autowired
    private DisplayTBZService displayTBZService;
    @Autowired
    private DisplayHHTService displayHHTService;
    @Autowired
    private DisplayDDTService displayDDTService;
    @Autowired
    private DisplayZZTService displayZZTService;

    /**
     * 我的出借->头笔赚数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/displayTBZ/{page}", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResulttbz(@PathVariable(value = "page") String page,
	    @RequestParam(value = "sortp", defaultValue = "") String sortp) {

	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	if (StringUtils.isBlank(sortp)) {
	    sortp = "";
	}

	Pagination<DisplayTBZ> list = this.displayTBZService.showProductsByPage(Integer.parseInt(page), sortp);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 我的出借->环环投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/displayHHT/{page}", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResulthh(@PathVariable(value = "page") String page,
	    @RequestParam(value = "sortp", defaultValue = "") String sortp) {

	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	if (StringUtils.isBlank(sortp)) {
	    sortp = "";
	}

	Pagination<DisplayHHT> list = this.displayHHTService.showProductsByPage(Integer.parseInt(page), sortp);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 我的出借->点点投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/displayDDT/{page}", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResultdd(@PathVariable(value = "page") String page,
	    @RequestParam(value = "sortp", defaultValue = "") String sortp) {
	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	if (StringUtils.isBlank(sortp)) {
	    sortp = "";
	}
	Pagination<DisplayDDT> list = this.displayDDTService.showProductsByPage(Integer.parseInt(page), sortp);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 我的出借->转转投数据展示
     * 
     * @return json
     */
    @RequestMapping(value = "/displayZZT/{page}", method = RequestMethod.GET)
    @ResponseBody
    public OpResult FindResultzz(@PathVariable(value = "page") String page,
	    @RequestParam(value = "sortp", defaultValue = "") String sortp) {
	if (StringUtils.isBlank(page)) {
	    page = "1";
	}
	if (StringUtils.isBlank(sortp)) {
	    sortp = "";
	}
	Pagination<DisplayZZT> list = this.displayZZTService.showProductsByPage(Integer.parseInt(page), sortp);

	return OpResult.getSuccessResult(list);
    }

    /**
     * 返回我的主页jsp
     * 
     * @return HuanhuanProjects.jsp
     */
    @RequestMapping(value = "/HuanhuanProjects")
    public String findByAll1() {

	return "HuanhuanProjects";
    }

    /**
     * 头笔赚
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/NewProjects")
    public String findByAll2() {

	return "NewProjects";
    }

    /**
     * 返回主页
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/DiandianProjects")
    public String findByAll3() {

	return "DiandianProjects";
    }

    /**
     * 返回主页
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/ZhuanzhuanProjects")
    public String findByAll4() {

	return "ZhuanzhuanProjects";
    }

    /**
     * 我要出借页面
     * 
     * @return index.jsp
     */
    @RequestMapping(value = "/projects")
    public String projects() {

	return "projects";
    }
}
