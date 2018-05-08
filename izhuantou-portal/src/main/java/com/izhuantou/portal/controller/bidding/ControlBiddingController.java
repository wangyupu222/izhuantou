package com.izhuantou.portal.controller.bidding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.fund.rpc.api.bidding.ProcessPageBidding;

@Controller
@RequestMapping("control")
public class ControlBiddingController {
	private static final Logger logger = LoggerFactory.getLogger(ControlBiddingController.class);
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
	public String intoNewBidding(HttpServletRequest request,RedirectAttributes view, BiddingDTO biddto) {
		HttpSession session = request.getSession();
		String memberOID =(String) session.getAttribute("memberOID");
		if(biddto!=null){
			biddto.setMemberOID(memberOID);
			long startTime=System.currentTimeMillis();
			String result = processPageBidding.ProcessPageNewChujiesave(biddto);
			logger.info("ControlBiddingController类中intoNewBidding方法，新手用户投标，总耗时时间："
					+ (System.currentTimeMillis() - startTime));
			if (StringUtil.isNotEmpty(result)&& "1".equals(result)) {
				return "redirect:/portal/control/tzdetailsok";
			} else {
				view.addFlashAttribute("msg", result);
				return "redirect:/portal/control/errorpage";
			}
		}
		return "redirect:/portal/control/errorpage";
	}

	/**
	 * 环环投标相关流程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/hhbidding", method = RequestMethod.POST)
	public String intoHHBidding(HttpServletRequest request,RedirectAttributes view, BiddingDTO biddto) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if(biddto!=null){
			biddto.setMemberOID(memberOID);
			long startTime=System.currentTimeMillis();
			String result = processPageBidding.ProcessPageHHChujiesave(biddto);
			logger.info("ControlBiddingController类中intoNewBidding方法，环环投标，总耗时时间："
					+ (System.currentTimeMillis() - startTime));
			if (StringUtil.isNotEmpty(result)&& "1".equals(result)) {
				return "redirect:/portal/control/tzdetailsok";
			} else {
				view.addFlashAttribute("msg", result);
				return "redirect:/portal/control/errorpage";
			}
		}
		return "redirect:/portal/control/errorpage";
	}

}
