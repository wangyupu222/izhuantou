package com.izhuantou.portal.controller.personalCenter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.vo.HHThavingDetailsDTO;
import com.izhuantou.damain.vo.MylendResultDTO;
import com.izhuantou.damain.vo.MylendZQZRDTO;
import com.izhuantou.service.api.personalCenter.MylendService;

@Controller
@RequestMapping(value = "mylend", produces = "application/json;charset=UTF-8")
public class MylendController {

    @Autowired
    private MylendService mylendService;

    /**
     * 环环头页面
     * 
     * @return
     */
    @RequestMapping(value = "/Creditorbaglist")
    public String Withdrawals() {

	return "Creditorbaglist";
    }

    /**
     * 环环列表详情页面
     * 
     * @return
     */
    @RequestMapping(value = "/Creditorbaglist_iframe")
    public String Creditorbaglist_iframe() {

	return "Creditorbaglist_iframe";
    }

    /**
     * 环环头持有列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/hhhavingList")
    @ResponseBody
    public OpResult hhhavingList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Map<String, Object> map = mylendService.findHHhavingBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 环环头持有详情
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/hhhavingDetails")
    @ResponseBody
    public OpResult hhhavingDetails(HttpServletRequest request) {
	String cashPoolOID = request.getParameter("cashPoolOID");
	List<HHThavingDetailsDTO> list = mylendService.findHHhavingDetails(cashPoolOID);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 环环头完成列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/hhWCList")
    @ResponseBody
    public OpResult hhWCList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Map<String, Object> map = mylendService.findHHTWCBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 头笔赚页面
     * 
     * @return
     */
    @RequestMapping(value = "/Newlist")
    public String Newlist() {

	return "Newlist";
    }

    /**
     * 头笔赚列表详情页面
     * 
     * @return
     */
    @RequestMapping(value = "/AgreementList_iframe")
    public String AgreementList_iframe() {

	return "AgreementList_iframe";
    }

    /**
     * 头笔赚持有列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/tbzList")
    @ResponseBody
    public OpResult newTBZList(HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
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
    @RequestMapping(value = "/tbzWCList")
    @ResponseBody
    public OpResult newTBZWCList(HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	List<MylendResultDTO> map = mylendService.findTBZWCBymemberOID(memberOID);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 点点投页面
     * 
     * @return
     */
    @RequestMapping(value = "/Standardlist")
    public String Standardlist() {

	return "Standardlist";
    }

    /**
     * 点点投持有列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/ddtList")
    @ResponseBody
    public OpResult ddtList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Map<String, Object> map = mylendService.findDDTBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 点点投完成列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/ddtWCList")
    @ResponseBody
    public OpResult ddtWCList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Map<String, Object> map = mylendService.findDDTWCBymemberOID(memberOID, currentPage);
	if (map != null) {
	    return OpResult.getSuccessResult(map);
	}
	return OpResult.getFailedResult("失败");
    }

    /**
     * 债权转让申请列表页面
     * 
     * @return
     */
    @RequestMapping(value = "/Creditorlist")
    public String Creditorlist() {

	return "Creditorlist";
    }

    /**
     * 弹出框页面
     * 
     * @return
     */
    @RequestMapping(value = "/zqzrbox_iframe")
    public String zqzrboxiframe() {

	return "zqzrbox_iframe";
    }

    /**
     * 转转页面
     * 
     * @return
     */
    @RequestMapping(value = "/Ztandardlist")
    public String Ztandardlist() {

	return "Ztandardlist";
    }

    /**
     * 债权转让可转列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/zqzrList")
    @ResponseBody
    public OpResult zqzrList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
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
    @RequestMapping(value = "/zqzrWCList")
    @ResponseBody
    public OpResult zqzrWCList(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
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
    @RequestMapping(value = "/zqzrSq")
    @ResponseBody
    public OpResult zqzrSq(HttpServletRequest request, String PaydebitOID, String businessOID) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
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
    @RequestMapping(value = "/saveZRMemage")
    public String saveZRMemage(HttpServletRequest request, MylendZQZRDTO mylendZQZRDTO) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	mylendZQZRDTO.setMemberOID(memberOID);
	String dto = mylendService.ZQZRBoxsave(mylendZQZRDTO);
	if (StringUtil.isNotEmpty(dto) && dto.equals("1")) {
	    return "success";
	} else {
	    return "success";
	}
    }

    /**
     * 成功页面
     * 
     * @return
     */
    @RequestMapping(value = "/success")
    public String success() {
	return "success";
    }

}
