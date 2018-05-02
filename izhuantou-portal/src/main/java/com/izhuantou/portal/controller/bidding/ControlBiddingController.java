package com.izhuantou.portal.controller.bidding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.fund.rpc.api.ProcessPageBidding;

@Controller
@RequestMapping("control")
public class ControlBiddingController {

    @Autowired
    private ProcessPageBidding processPageBidding;

    @RequestMapping(value = "/tzdetailsok")
    public String tzdetailsok() {
	return "tzdetailsok";
    }

    @RequestMapping(value = "/errorpage")
    public String errorpage() {
	return "errorpage";
    }

    /**
     * 新手投标相关流程
     * 
     * @return
     */
    @RequestMapping(value = "/newbidding", method = RequestMethod.POST)
    public String intoNewBidding(HttpServletRequest request, BiddingDTO biddto) {
	HttpSession session = request.getSession();
	// String memberOID =(String) session.getAttribute("memberOID");
	// biddto.setMemberOID(memberOID);
	String resuluType = processPageBidding.ProcessPageNewChujiesave(biddto);
	if (resuluType != null && resuluType.equals("1")) {

	    return "redirect:/portal/control/tzdetailsok";
	} else {
	    return "redirect:/portal/control/errorpage";
	}

    }

    /**
     * 环环投标相关流程
     * 
     * @return
     */
    @RequestMapping(value = "/hhbidding", method = RequestMethod.POST)
    public String intoHHBidding(HttpServletRequest request, BiddingDTO biddto) {
	HttpSession session = request.getSession();
	String memberOID = (String) session.getAttribute("memberOID");
	biddto.setMemberOID(memberOID);
	// String resuluType =
	// processPageBidding.ProcessPageHHChujiesave(biddto);
	String resuluType = "1";
	if (resuluType != null && resuluType.equals("1")) {

	    return "redirect:/portal/control/tzdetailsok";
	} else {
	    return "redirect:/portal/control/errorpage";
	}
    }

}
