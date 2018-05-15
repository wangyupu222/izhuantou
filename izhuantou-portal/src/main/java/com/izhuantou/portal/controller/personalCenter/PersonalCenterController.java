package com.izhuantou.portal.controller.personalCenter;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberAgreement;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.LendMoneyDTO;
import com.izhuantou.damain.vo.MemberAgrennmentDTO;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.vo.PersonalMessageDTO;
import com.izhuantou.damain.vo.UpdLendRecord;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanApply;
import com.izhuantou.service.api.personalCenter.LoanApplicationService;
import com.izhuantou.service.api.personalCenter.MyLendRecordService;
import com.izhuantou.service.api.personalCenter.MyRepaymentService;
import com.izhuantou.service.api.personalCenter.PersonalCenterService;
import com.izhuantou.service.api.personalCenter.UpdPicService;
import com.izhuantou.service.impl.user.ReadPropertiesl;
import com.izhuantou.third.rpc.api.memberAgrement.MemberMemberAgreementService;

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
	private UpdPicService updPicService;
	@Autowired
	private LoanApplicationService loanApplicationService;
	@Autowired
	private MyLendRecordService myLendRecordService;
	@Autowired
	private MyRepaymentService repaymentService;
	@Value("${fileServerLink}")
	private String fileServerLink;
	
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
				personal.setImgSrc(fileServerLink);
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
	 * 安全中心页面
	 */
	@RequestMapping(value = "/Securitypre")
	public String Securitypre() {

		return "Securitypre";
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
	 * 密码修改成功页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/PwdUpOk")
	public String updataPasswordOK() {
		return "phone3";
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
					view.addFlashAttribute("msg", message);
					return "redirect:/portal/personal/Securitypre";
				}
			}
			view.addFlashAttribute("msg", "修改失败");
			return "redirect:/portal/personal/Securitypre";
		} else {
			view.addFlashAttribute("msg", "用户未登录");
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
	 * 查询消息条数
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/num")
	@ResponseBody
	public OpResult findResultnum(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		PersonalMessageDTO pm=new PersonalMessageDTO();
		if(StringUtil.isNotEmpty(memberOID)){
			Integer message = this.personalMessage.findMessageNum(memberOID);
			if(message!=null){
				Integer history= this.personalMessage.findHistoryMessageNum(memberOID);
				int allcount=message+history;
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
		if(StringUtil.isNotEmpty(memberOID)){
			long start=System.currentTimeMillis();
			Integer message = this.personalMessage.findMessageNum(memberOID);
			System.err.println("未读消息总数耗时"+(System.currentTimeMillis()-start));
			if(message!=null){
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
		Pagination<PersonalMessageDTO> list=null;
		if(StringUtil.isNotEmpty(memberOID)){
			list =this.personalMessage.findMessage(currentpage, memberOID);
			if(list!=null){
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
		Pagination<PersonalMessageDTO> list =null;
		if(StringUtil.isNotEmpty(memberOID)){
			list =this.personalMessage.findHistoryMessage(currentpage, memberOID);
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

		this.personalMessage.insertMessage(OID);
		return "MessageNotification";

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

	@RequestMapping(value = "/uploadTxImg_iframe")
	public String uploadTxImgiframe() {

		return "uploadTxImg_iframe";
	}

	/**
	 * 更换头像
	 * 
	 * @author fucheng
	 * @date 2018-03-01
	 */
	@RequestMapping(value = "/updHeadPic")
	@ResponseBody
	public String updHeadPic(MultipartFile file) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String a = "66666";
		try {
			// 接收图片并保存
			if (file != null) {

				// String pic_path = fileConfig.getImgFilePath();
				String pic_path = "F://test";

				String imageFolder = dateFormat.format(new Date());
				// 定义临时路径用来判断指定文件夹是否存在
				String tempPath = pic_path + "/" + imageFolder;
				// 判断对应的文件夹是否存在
				File createMkdir = new File(tempPath);
				if (!createMkdir.exists()) {
					createMkdir.mkdir();
				}
				// 获取图片的原始名称
				String filename = file.getOriginalFilename();
				// 新图片名称
				String newfilename = UUID.randomUUID().toString().replaceAll("-", "")
						+ filename.substring(filename.lastIndexOf("."));
				// 新图片
				File newImg = new File(tempPath + "/" + newfilename);
				// 写入磁盘
				file.transferTo(newImg);
				// 返回得到的图片存储名
				String newFilePath = imageFolder + "/" + newfilename;
				// 将图片的路径保存到数据库
				System.out.println(newFilePath);

			}
		} catch (Exception e) {
			a = "5555";
		}
		return a;

	}

	/** 个人中心借款审核 */
	@RequestMapping(value = "/loanAudit")
	public String loanAudit(HttpServletRequest request, RedirectAttributes view) {
		WebP2pLoanApply loanApply = new WebP2pLoanApply();
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		String sys_msg = (String) session.getAttribute("smsLoanApplication");
		String userName = request.getParameter("name");
		String idCard = request.getParameter("card");
		String mobile = request.getParameter("phone");
		String money = request.getParameter("money");
		String loanTerm = request.getParameter("loanTerm");
		String loanYT = request.getParameter("loanYT");// 产品名
		String mesYzm = request.getParameter("mesYzm");// 短信验证码
		// Integer loanAmount = (Integer) dtoFormApply.get("money");
		String s_province = request.getParameter("s_province");// 省
		String s_city = request.getParameter("s_city");// 城市
		String s_county = request.getParameter("s_county");// 村
		String rate = request.getParameter("rate");// 借贷利率
		String loanOID = request.getParameter("loanOID");// 用于修改的OID，如果有值即修改没有是添加
		// String city = (String) dtoFormApply.get("city");
		try {
			if (StringUtil.isNotEmpty(mesYzm) && !mesYzm.toLowerCase().equals(sys_msg)) {// 测试关闭短信

				String city = "";
				if (s_province != null) {
					if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
							|| s_province.equals("重庆市")) {
						city = s_province + "," + s_county;
					} else {
						city = s_province + "," + s_city + "," + s_county;
					}
				}
				loanApply.setMemberOID(memberOID);
				loanApply.setUserName(userName);
				loanApply.setIdCard(idCard);
				loanApply.setMobile(mobile);
				if (money != null) {
					loanApply.setLoanAmount(new BigDecimal(money));
				}
				loanApply.setLoanTerm(loanTerm);
				loanApply.setLoanYT(loanYT);
				loanApply.setCity(city);
				loanApply.setRate(rate);
				loanApply.setOID(loanOID);

				String s = loanApplicationService.updAppInfo(loanApply);
				if (StringUtil.isNotEmpty(s) && s.equals("-1")) {
					// 为进行委托授权
					view.addFlashAttribute("msg", "您尚未进行委托授权");
					return "redirect:/portal/personal/LoanApplication?type=1";

				} else if (StringUtil.isNotEmpty(s) && s.equals("1")) {
					return "successLoanApplication";
				}
			} else {
				// 短信验证错误
				view.addFlashAttribute("msg", "短信验证错误");
				return "redirect:/portal/personal/LoanApplication?type=1";
			}
			return "successLoanApplication";
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * 审核状态查询
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/lendprocess/{page}")
	@ResponseBody
	public OpResult lendprocess(HttpServletRequest request, @PathVariable(value = "page") String page) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			// String page = "2";
			Pagination<WebP2pLoanApply> list = loanApplicationService.findData(memberOID, Integer.valueOf(page));

			if (list != null) {

				return OpResult.getSuccessResult(list);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	/**
	 * 客户信息查询
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/findInfo")
	@ResponseBody
	public OpResult findInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			PayCustomer customer = new PayCustomer();
			// memberOID= "370518b20a5dca712804025f2e15867a";
			customer = loanApplicationService.findInfo(memberOID);

			if (customer != null) {

				return OpResult.getSuccessResult(customer);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	@RequestMapping(value = "/successLoanApplication")
	public String successLoanApplication() {

		return "successLoanApplication";
	}

	@RequestMapping(value = "/LoanApplication")
	public String LoanApplication() {

		return "LoanApplication";
	}

	@RequestMapping(value = "/LoanApplactiomUpdateErro")
	public String LoanApplactiomUpdateErro() {

		return "LoanApplactiomUpdateErro";
	}

	@RequestMapping(value = "/LoanApplication_iframe")
	public String LoanApplication_iframe() {

		return "LoanApplication_iframe";
	}

	@RequestMapping(value = "/BorrowingRecords")
	public String BorrowingRecords() {

		return "BorrowingRecords";
	}

	// 还款
	@RequestMapping(value = "/Repayment")
	public String Repayment() {

		return "Repayment";
	}

	/**
	 * 个人中心借款记录
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/myLendRecord")
	@ResponseBody
	public OpResult myLendRecord(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			String str = null;
			Map<String, Object> map = new HashMap<>();
			map = myLendRecordService.MylendRecord(memberOID);
			return OpResult.getSuccessResult(map);
		} else {
			return null;
		}

	}

	/** 个人中心借款审核修改 */
	@RequestMapping(value = "/updloanAudit")
	public String loanAuditupd(HttpServletRequest request, RedirectAttributes view) {
		WebP2pLoanApply loanApply = new WebP2pLoanApply();
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		String sys_msg = (String) session.getAttribute("smsLoanApplication");
		String userName = request.getParameter("name");
		String idCard = request.getParameter("card");
		String mobile = request.getParameter("phone");
		String money = request.getParameter("money");
		String loanTerm = request.getParameter("loanTerm");
		String loanYT = request.getParameter("loanYT");// 产品名
		String mesYzm = request.getParameter("mesYzm");// 短信验证码
		// Integer loanAmount = (Integer) dtoFormApply.get("money");
		String s_province = request.getParameter("s_province");// 省
		String s_city = request.getParameter("s_city");// 城市
		String s_county = request.getParameter("s_county");// 村
		String rate = request.getParameter("rate");// 借贷利率
		String loanOID = request.getParameter("loanOID");// 用于修改的OID，如果有值即修改没有是添加
		// String city = (String) dtoFormApply.get("city");
		try {
			if (StringUtil.isNotEmpty(mesYzm) && !mesYzm.toLowerCase().equals(sys_msg)) {// 测试用
				String city = "";
				if (s_province != null) {
					if (s_province.equals("天津市") || s_province.equals("北京市") || s_province.equals("上海市")
							|| s_province.equals("重庆市")) {
						city = s_province + "," + s_county;
					} else {
						city = s_province + "," + s_city + "," + s_county;
					}
				}
				loanApply.setMemberOID(memberOID);
				loanApply.setUserName(userName);
				loanApply.setIdCard(idCard);
				loanApply.setMobile(mobile);
				if (money != null) {
					loanApply.setLoanAmount(new BigDecimal(money));
				}
				loanApply.setLoanTerm(loanTerm);
				loanApply.setLoanYT(loanYT);
				loanApply.setCity(city);
				loanApply.setRate(rate);
				loanApply.setOID(loanOID);
				String s = loanApplicationService.update(loanApply, memberOID);
				if ("1".equals(s)) {
					return "redirect:/portal/personal/successLoanApplication";
				} else {
					return "redirect:/portal/personal/LoanApplication?type=1";
				}
			} else {
				// 短信验证错误
				view.addFlashAttribute("msg", "短信验证错误");
				return "redirect:/portal/personal/LoanApplication_iframe";
			}

		} catch (Exception ex) {
			// this.sendErrorMessage("发生错误:" + ex.getMessage());
			// 此处指定异常时跳转页面
			// forward = "";
			// this.forward(forwardErro);
			view.addFlashAttribute("msg", "发生错误");
			return "redirect:/portal/personal/LoanApplication?type=1";
		}

	}

	/**
	 * 还款
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/repaymentBack")
	@ResponseBody
	public OpResult repaymentBack(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			List list = new ArrayList<>();
			list = repaymentService.findMyRepayment(memberOID);
			return OpResult.getSuccessResult(list);
		} else {
			return null;
		}

	}

	/**
	 * 客户OID信息查询
	 * 
	 * @return
	 * 
	 * @return json
	 */
	@RequestMapping(value = "/findoidInfo")
	@ResponseBody
	public OpResult findoidInfo(@RequestParam(value = "OID", defaultValue = "") String OID,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {

			UpdLendRecord upLendRecord = new UpdLendRecord();
			upLendRecord = loanApplicationService.findDataByOID(memberOID, OID);
			if (upLendRecord != null) {

				return OpResult.getSuccessResult(upLendRecord);
			}
			return OpResult.getFailedResult("失败");
		} else {
			return OpResult.getFailedResult("OID为空");
		}

	}

	/**
	 * 返回绑卡页面
	 */
	@RequestMapping(value = "/addbankcard")
	public String directAddBackCard() {
		return "addbankcard";
	}

}
