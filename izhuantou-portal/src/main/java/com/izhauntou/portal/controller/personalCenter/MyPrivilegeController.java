package com.izhauntou.portal.controller.personalCenter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.vo.MyInvitationDaoDTO;
import com.izhuantou.damain.vo.MyPrivilegeDaoDTO;
import com.izhuantou.service.api.personalCenter.MyPrivilegeService;

/***
 * 个人中心
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "mytq", produces = "application/json;charset=UTF-8")
public class MyPrivilegeController {
    @Autowired
    private MyPrivilegeService myPrivilegeService;

    /**
     * 邀请好友
     * 
     * @return
     */
    @RequestMapping(value = "/Invitefriends")
    public String Invitefriends() {

	return "Invitefriends";
    }

    /**
     * 红包
     * 
     * @return
     */
    @RequestMapping(value = "/MyPrivilege01")
    public String MyPrivilege01() {

	return "MyPrivilege01";
    }

    /**
     * 加息券
     * 
     * @return
     */
    @RequestMapping(value = "/MyPrivilege02")
    public String MyPrivilege02() {

	return "MyPrivilege02";
    }

    /**
     * 体验金
     * 
     * @return
     */
    @RequestMapping(value = "/TYJbaglist")
    public String TYJbaglist() {

	return "TYJbaglist";
    }

    /**
     * 可用的红包特权
     * 
     * @return json
     */
    @RequestMapping(value = "/canUsehb")
    @ResponseBody
    public OpResult canUsehb(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCanUseHBByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已使用红包特权
     * 
     * @return json
     */
    @RequestMapping(value = "/Usehb")
    @ResponseBody
    public OpResult Usehb(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findUseHBByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已过期的红包特权
     * 
     * @return json
     */
    @RequestMapping(value = "/countUsehb")
    @ResponseBody
    public OpResult countUsehb(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCannotUseHBByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 可用的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/canUseJxq")
    @ResponseBody
    public OpResult canUseJxq(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCanUseJxqByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已使用的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/isUseJxq")
    @ResponseBody
    public OpResult isUseJxq(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findUseJxqByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 已过期的加息券
     * 
     * @return json
     */
    @RequestMapping(value = "/GqJxq")
    @ResponseBody
    public OpResult GqJxq(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyPrivilegeDaoDTO> list = myPrivilegeService.findCannotUseJxqByMemberOID(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 邀请好友
     * 
     * @return json
     */
    @RequestMapping(value = "/invitation")
    @ResponseBody
    public OpResult invitation(HttpServletRequest request, Integer currentPage) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Pagination<MyInvitationDaoDTO> list = myPrivilegeService.findInvitationRecord(memberOID, currentPage);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

    /**
     * 体验金
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/experience")
    @ResponseBody
    public OpResult Experience(HttpServletRequest request) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	Map<String, Object> list = myPrivilegeService.findExperience(memberOID);
	if (list != null) {
	    return OpResult.getSuccessResult(list);
	} else {
	    return OpResult.getFailedResult("查询失败");
	}
    }

}
