package com.izhuantou.portal.controller.personalCenter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.constant.Constant;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.LendMoneyDTO;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.vo.PersonalMessageDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.service.api.p2p.PictureServcie;
import com.izhuantou.service.api.personalCenter.PersonalCenterService;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;

/***
 * 个人中心
 * 
 * @author yangbosen
 *
 */
@Controller
@RequestMapping(value = "personal", produces = "application/json;charset=UTF-8")
public class PersonalCenterController {
	@Autowired
	private PersonalCenterService personalMessage;
	@Autowired
	private MemberMemberAgreementService memberAgreementService;
	@Autowired
	private PictureServcie pictureServcie;
	@Value("${userHeadPath}")
	private String userHeadPath;
	@Value("${pdfPicPath}")
	private String pdfPicPath;
	

	/**
	 * 个人中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personalcenter")
	public String personalcenter() {

		return "personalcenter";
	}

	/**
	 * 安全中心页面
	 */
	@RequestMapping(value = "/Securitypre")
	public String Securitypre() {

		return "Securitypre";
	}

	/**
	 * 用户中心-》我的消息页面
	 */
	@RequestMapping(value = "/MessageNotification")
	public String findAllMessage() {

		return "MessageNotification";
	}

	/**
	 * 用户中心我的消息-》已读消息
	 * 
	 * @return MessageNotification.jsp
	 */
	@RequestMapping(value = "/MessageHistoryNotification")
	public String findHistoryMessage() {

		return "MessageHistoryNotification";
	}
	/**
	 * 密码修改成功页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/PwdUpOk")
	public String updataPasswordOK() {
		return "phone3";
	}

	/**
	 * 查看协议的页面
	 */
	@RequestMapping(value = "/agreement_zCenter")
	public String agreement_zCenter() {

		return "agreement_zCenter";
	}

	/**
	 * 查看协议的页面
	 */
	@RequestMapping(value = "/agreement_z")
	public String agreement_z() {

		return "agreement_z";
	}

	/**
	 * 返回绑卡页面
	 */
	@RequestMapping(value = "/addbankcard")
	public String directAddBackCard() {
		return "addbankcard";
	}

	@RequestMapping(value = "/uploadTxImg_iframe")
	public String uploadTxImgiframe() {

		return "uploadTxImg_iframe";
	}

	@RequestMapping(value = "AgreementList_iframe")
	public String AgreementList_iframe() {

		return "AgreementList_iframe";
	}

	
	
	/**
	 * 用户中心 中心首页数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findPersonalIndex")
	@ResponseBody
	public OpResult findPersonalIndex(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			long start = System.currentTimeMillis();
			PersonalDTO personal = personalMessage.userCenterIndex(memberOID);
			System.err.println("中心首页耗时" + (System.currentTimeMillis() - start));
			if (personal != null) {
				return OpResult.getSuccessResult(personal);
			} else {
				return OpResult.getFailedResult("失败");
			}
		} else {
			return OpResult.getFailedResult("memberOID不能为空");
		}

	}

	/**
	 * 中心首页 借款账户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/PersonalLean")
	@ResponseBody
	public OpResult PersonalLean(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			List<LendMoneyDTO> lendMoney = personalMessage.findLendMoneyBymemberOID(memberOID);
			if (lendMoney != null) {
				return OpResult.getSuccessResult(lendMoney);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	

	/**
	 * 安全中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/security")
	@ResponseBody
	public OpResult security(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			Map<String, Object> map = personalMessage.SecurityCenter(memberOID);

			if (map != null) {
				return OpResult.getSuccessResult(map);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	
	/**
	 * 安全中心修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updataPassword")
	public String updataPassword(HttpServletRequest request, RedirectAttributes view, UserDTO userdto) {
		/**
		 * password password2
		 */
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		userdto.setOID(memberOID);
		if (StringUtil.isNotEmpty(userdto.getOID())) {
			String message = personalMessage.updatePasswordByOld(userdto);
			if (message != null) {
				if ("1".equals(message)) {
					session.invalidate();
					return "redirect:/portal/personal/PwdUpOk";
				} else {
					view.addFlashAttribute(Constant.MSG, message);
					return "redirect:/portal/personal/Securitypre";
				}
			}
			view.addFlashAttribute(Constant.MSG, "修改失败");
			return "redirect:/portal/personal/Securitypre";
		} else {
			view.addFlashAttribute(Constant.MSG, "用户未登录");
			return "redirect:/portal/personal/Securitypre";
		}
	}

	/**
	 * 安全中心修改或添加密保问题
	 */
	@RequestMapping(value = "/insertTwoQuestion")
	public String insertTwoQuestion(HttpServletRequest request, UserDTO userdto) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		userdto.setOID(memberOID);
		personalMessage.insertTwoQuestion(userdto);
		return "redirect:/portal/personal/Securitypre";
	}


	/**
	 * 查询消息条数
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/num")
	@ResponseBody
	public OpResult findResultnum(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		PersonalMessageDTO pm = new PersonalMessageDTO();
		if (StringUtil.isNotEmpty(memberOID)) {
			Integer message = this.personalMessage.findMessageNum(memberOID);
			if (message != null) {
				Integer history = this.personalMessage.findHistoryMessageNum(memberOID);
				int allcount = message + history;
				pm.setCountMessageAll(allcount);
				pm.setCountMessage(message);
				return OpResult.getSuccessResult(pm);
			}
		}
		return OpResult.getFailedResult("消息条数查询失败");
	}

	@RequestMapping(value = "/leftnum")
	@ResponseBody
	public OpResult findLeftNum(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			long start = System.currentTimeMillis();
			Integer message = this.personalMessage.findMessageNum(memberOID);
			System.err.println("未读消息总数耗时" + (System.currentTimeMillis() - start));
			if (message != null) {
				return OpResult.getSuccessResult(message);
			}
		}
		return OpResult.getFailedResult("消息条数查询失败");
	}

	/**
	 * 查询所有未读消息
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/message")
	@ResponseBody
	public OpResult FindResultmm(HttpServletRequest request, Integer currentpage) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		Pagination<PersonalMessageDTO> list = null;
		if (StringUtil.isNotEmpty(memberOID)) {
			list = this.personalMessage.findMessage(currentpage, memberOID);
			if (list != null) {
				return OpResult.getSuccessResult(list);
			}
		}
		return OpResult.getFailedResult("未读消息查询失败");

	}

	/**
	 * 查询所有已读消息
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/historyMessage")
	@ResponseBody
	public OpResult FindResultzz(HttpServletRequest request, Integer currentpage) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		Pagination<PersonalMessageDTO> list = null;
		if (StringUtil.isNotEmpty(memberOID)) {
			list = this.personalMessage.findHistoryMessage(currentpage, memberOID);
			return OpResult.getSuccessResult(list);
		}
		return OpResult.getFailedResult("已读消息查询失败");

	}

	/**
	 * 删除未读消息添加到已读中
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/insert")
	public String FindResultinsert(@RequestParam(value = "OID", defaultValue = "") String OID) {
		if(StringUtil.isNotEmpty(OID)){
			this.personalMessage.insertMessage(OID);
		}
		return "redirect:/portal/personal/MessageNotification";

	}

	/**
	 * 查看协议模板
	 */
	@RequestMapping(value = "/agreement")
	@ResponseBody
	public OpResult agreement(HttpServletRequest request) {
		String type = request.getParameter("type");
		MemberAgreement agreement = memberAgreementService.gainMemberAgreement(type);
		if (agreement != null) {
			return OpResult.getSuccessResult(agreement);
		} else {
			return OpResult.getFailedResult("没有该协议");
		}
	}

	/**
	 * 查看协议
	 */
	@RequestMapping(value = "/memberAgreement")
	@ResponseBody
	public OpResult findMemberAgreement(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		MemberAgrennmentDTO dto = new MemberAgrennmentDTO();
		String biOID = request.getParameter("biOID");
		String type = request.getParameter("type");
		String DOID = request.getParameter("dOID");
		String CashPoolOID = request.getParameter("cashPoolOID");
		String loanNumber = request.getParameter("loanNumber");
		dto.setBiOID(biOID);// "0097fb030a5dca710035cb89571caf68"
		dto.setType(type); // "6";
		dto.setDOID(DOID);
		dto.setCashPoolOID(CashPoolOID);
		dto.setLoanNumber(loanNumber);
		dto.setMemberOID(memberOID);
		MemberMemberAgreement Agreement = memberAgreementService.gainAgreement(dto);
		if (Agreement != null) {
			return OpResult.getSuccessResult(Agreement);
		} else {
			return OpResult.getFailedResult("失败");
		}
	}

	/**
	 * 更换头像
	 * 
	 * @author fucheng
	 * @date 2018-03-01
	 */
	@RequestMapping(value = "/updHeadPic")
	@ResponseBody
	public OpResult updHeadPic(HttpServletRequest request, MultipartFile file) {
		String memberOID = (String) request.getSession().getAttribute("memberOID");
		System.out.println(memberOID);

		// 接收图片并保存
		if (file != null) {
			String result = pictureServcie.updHeadPic(file, memberOID, userHeadPath);
			if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
				return OpResult.getSuccessResult(result);
			}
		}
		return OpResult.getFailedResult("更换头像失败");
	}

	/**
	 * 根据图片OID获取图片的路径
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findHeadPic")
	@ResponseBody
	public OpResult findHeadPic(String picOID) {
		if (StringUtil.isNotEmpty(picOID)) {
			String path = pictureServcie.findPicPath(picOID, userHeadPath);
			if (StringUtil.isNotEmpty(path)) {
				return OpResult.getSuccessResult(path);
			}
		}
		return OpResult.getFailedResult("获取头像失败");
	}
	
	@RequestMapping(value = "/findPdfPic")
	@ResponseBody
	public OpResult findPdfPic(String picOIDs) {
		if(StringUtil.isNotEmpty(picOIDs)){
			List<String> path = pictureServcie.findPdfPicPath(picOIDs,pdfPicPath);
			if(path.size()>0){
				return OpResult.getSuccessResult(path);
			}
		}
		return OpResult.getFailedResult("图片查询失败");
	}
	
	
}
