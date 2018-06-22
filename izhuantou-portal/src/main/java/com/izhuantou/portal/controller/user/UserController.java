package com.izhuantou.portal.controller.user;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.tool.ToolMath;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.utils.ImageUtil;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.message.SendMessageService;

@Controller
@RequestMapping("user")
public class UserController {
	public static final String VALIDATE_CODE = "validateCode";
	public static final String SMS_VALIDATE_CODE = "smsValidateCode";
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private MemberMemberAgreementService memberAgreementService;
	@Autowired
	private SendMessageService  sendMessage;
	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUI() {
		return "login";
	}

	/**
	 * 返回主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexUI() {
		return "index";
	}

	/**
	 * 返回注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUI() {
		return "register";
	}

	/**
	 * 找回密码第一个页面 短信找回和密保找回切换
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forgetpwd01", method = RequestMethod.GET)
	public String updatePwd() {

		return "forgetpwd01";
	}

	/**
	 * 找回密码第二个页面 输入新密码的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forgetpwd02", method = RequestMethod.GET)
	public String updatePwd2() {

		return "forgetpwd02";
	}

	/**
	 * 修改成功
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forgetpwd03", method = RequestMethod.GET)
	public String updatePwd3() {

		return "forgetpwd03";
	}

	@RequestMapping(value = "/forgetpwd0102", method = RequestMethod.GET)
	public String updatePwd4() {

		return "forgetpwd0102";
	}

	/**
	 * 安全退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exit")
	public String exit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/portal/user/index";
	}

	/**
	 * 生成图片验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkcode")
	public void createImg(HttpServletRequest request, HttpServletResponse response) {
		OutputStream output = null;
		try {
			Object[] objs = ImageUtil.createImage();
			BufferedImage image = (BufferedImage) objs[1];
			// 将验证码存入session
			String number = (String) objs[0];
			HttpSession session = request.getSession();
			session.setAttribute(VALIDATE_CODE, objs[0]);
			output = response.getOutputStream();
			/**
			 * 将原始图片按照指定的压缩算法(jpeg) 进行压缩.,然后发送给客户端.
			 * 
			 */
			javax.imageio.ImageIO.write(image, "jpeg", output);
			output.close();
		} catch (Exception e) {
			logger.error("验证码生成失败");
		}
	}

	/**
	 * 手机验证码
	 * 
	 * @param request
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/checksms", method = RequestMethod.POST) // ,method=RequestMethod.POST
	@ResponseBody
	public OpResult checksms(HttpServletRequest request, UserDTO us) {
		if (us == null) {
			return OpResult.getFailedResult("请输入用户信息");
		}
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute(VALIDATE_CODE);
		// 获取图片验证码
		String yzm = us.getYzm();
		String name = us.getName();
		if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {
			session.removeAttribute(VALIDATE_CODE);
			if (StringUtil.isEmpty(name)) {
				return OpResult.getFailedResult("手机号不能为空");
			}
			// 获取随机短信验证码
			int iValidateCode = ToolMath.gainRandomInt(0, 999999);
			String strValidateCode = ToolString.addLengthAtLeft((new Integer(iValidateCode)).toString(), "0", 6);
			// 将短信验证码放入session
			request.getSession().setAttribute(SMS_VALIDATE_CODE, strValidateCode);
			String msmType = us.getMsmType();
			Map<String, String> reMap=new HashMap<String, String>();
			reMap.put(SMS_VALIDATE_CODE, strValidateCode);
			String result =sendMessage.sendMessage(name, msmType, reMap); 
			if (StringUtil.isNotEmpty(result) && result.equals("1")) {
				return OpResult.getSuccessResult("发送成功");
			} else {
				return OpResult.getFailedResult(result);
			}
		}
		return OpResult.getFailedResult("验证码有误");
	}

	/**
	 * 登录页面用户提交处理
	 * 
	 * @param request
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.POST)
	public String toLogin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes view,
			UserDTO us) {
		if (us == null) {
			view.addFlashAttribute("msg", "请输入用户信息");
			return "redirect:/portal/user/login";
		}
		// 获取图片验证码
		String yzm = us.getYzm();
		HttpSession session = request.getSession();
		// 获取session中的验证码
		String check = (String) session.getAttribute(VALIDATE_CODE);
		// 转化成大写，
		// 使用拖动条验证
		// if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {
		session.removeAttribute(VALIDATE_CODE);
		MemberMember member = this.userService.findUserByName(us);
		if (member == null) {
			view.addFlashAttribute("msg", "账号或密码错误");
			return "redirect:/portal/user/login";
		} else {
			PayCustomer custome = userService.findCustomerByMemberOID(member.getOID());
			if (custome != null) {
				session.setAttribute("isCreate", "true");
			} else {
				session.setAttribute("isCreate", "false");
			}
			session.setAttribute("userMobile", member.getName());
			session.setAttribute("memberOID", member.getOID());

			String isRemeber = us.getIsRememberMe();
			if (StringUtil.isNotEmpty(isRemeber) && "ok".equals(isRemeber)) {
				Cookie c = new Cookie("password", us.getPassword());
				Cookie c1 = new Cookie("userMobile", us.getName());
				c.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(c);
				c1.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(c1);
			}
			return "redirect:/portal/user/index";
		}
		// }
		// view.addFlashAttribute("msg", "验证码有误");
		// return "redirect:/portal/user/login";
	}

	/**
	 * 修改密码的第一个页面 短信验证修改提交的
	 * 
	 * @param request
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/updatePasswordOne", method = RequestMethod.POST)
	public String updatePassword(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		if (us == null) {
			view.addFlashAttribute("msg", "信息不能为空");
			return "redirect:/portal/user/forgetpwd01";
		}
		// 获取短信验证码
		String sms = us.getMsm();
		HttpSession session = request.getSession();
		// 获取session中的手机号验证码
		String checksms = (String) session.getAttribute(SMS_VALIDATE_CODE);
		session.removeAttribute(VALIDATE_CODE);
		if (StringUtil.isNotEmpty(sms) && !sms.equals(checksms)) { // 测试关闭验证码
			session.removeAttribute(SMS_VALIDATE_CODE);
			session.setAttribute("userName", us.getName());
			return "redirect:/portal/user/forgetpwd02";
		}
		view.addFlashAttribute("msg", "短信验证码不正确");
		return "redirect:/portal/user/forgetpwd01";
		
	}

	/**
	 * 密码修改页面,填写新密码的页面所提交的
	 * 
	 * @param request
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/updatePasswordTwo", method = RequestMethod.POST)
	public String updatePassword2(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		String pwd = us.getPassword();
		String pwd2 = us.getPassword2();
		HttpSession session = request.getSession();
		if (StringUtil.isNotEmpty(pwd) && StringUtil.isNotEmpty(pwd2) && pwd.equals(pwd2)) {
			Integer rows = userService.updatePassword(us);
			if (rows != null && rows == 1) {
				// 重定向修改成功页面
				session.removeAttribute("userName");
				return "redirect:/portal/user/forgetpwd03";
			} else {
				// 重定向修改页面
				view.addFlashAttribute("msg", "修改失败");
				return "redirect:/portal/user/forgetpwd01";
			}
		} else {
			// 重定向修改页面
			view.addFlashAttribute("msg", "两次密码不一致");
			return "redirect:/portal/user/forgetpwd02";
		}
	}

	/**
	 * 返回密保信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forgetpwd0102", method = RequestMethod.POST)
	public String updatePwd0102(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		String yzm = us.getYzm();
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute(VALIDATE_CODE);
		if (StringUtil.isNotEmpty(yzm) && yzm.toUpperCase().equals(check)) {
			session.removeAttribute(VALIDATE_CODE);
			Map<String, String> member = this.userService.findTwoQuestion(us);
			if (member == null) {
				session.setAttribute("unsetproblem", "0");
				return "redirect:/portal/user/forgetpwd0102";
			} else {
				session.setAttribute("questionOne", member.get("questionOne"));
				session.setAttribute("questionTwo", member.get("questionTwo"));
				session.setAttribute("UserQuestion", member);
				session.setAttribute("userName", us.getName());
				session.setAttribute("unsetproblem", "1");
				return "redirect:/portal/user/forgetpwd0102";
			}
		} else {
			view.addFlashAttribute("msg", "验证码有误");
			return "redirect:/portal/user/forgetpwd01";
		}

	}

	/**
	 * 密保找回 回答问题以后提交的表单
	 * 
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/updatePasswordOneTwo", method = RequestMethod.POST)
	public String updatePassword0102(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		HttpSession session = request.getSession();
		String answerOne = us.getAnswerOne();
		String answerTwo = us.getAnswerTwo();
		Map<String, String> map = (Map<String, String>) session.getAttribute("UserQuestion");
		if (answerOne.equals(map.get("answerOne")) && answerTwo.equals(map.get("answerTwo"))) {
			session.setAttribute("userName", us.getName());
			return "redirect:/portal/user/forgetpwd02";
		} else {
			view.addFlashAttribute("msg", "答案不对");
			return "redirect:/portal/user/forgetpwd0102";
		}
	}

	@RequestMapping(value = "/threeNumber") // ,method=RequestMethod.POST
	@ResponseBody
	public OpResult ThreeNumber(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberOID = (String) session.getAttribute("memberOID");
		if (StringUtil.isNotEmpty(memberOID)) {
			long startTime=System.currentTimeMillis();
			PersonalDTO personal = userService.findIndexNumber(memberOID);
			System.err.print("首页数据查寻耗时"+(System.currentTimeMillis()-startTime));
			if (personal != null) {
				return OpResult.getSuccessResult(personal);
			} else {
				return OpResult.getFailedResult("暂无数据");
			}
		} else {
			return OpResult.getFailedResult("用户OID不能为空");
		}
	}

	/**
	 * 普通注册
	 * 
	 * @param request
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		String url = "";
		// 判断用户是从哪个页面注册的
		if (StringUtil.isNotEmpty(us.getChannel()) && StringUtil.isNotEmpty(us.getTypeVal())) {
			// 判断类型是从移动端 还是pc端
			if (StringUtil.isNotEmpty(us.getRegister()) && "wapRegister".equals(us.getRegister())) {
				url = "redirect:/portal/ZTYMain/App/" + us.getTypeVal() + "RegisterM";
			}
			if (StringUtil.isNotEmpty(us.getRegister()) && "webRegister".equals(us.getRegister())) {
				url = "redirect:/portal/ZTYMain/" + us.getTypeVal() + "Register";
			}
		} else {
			url = "redirect:/portal/user/register";
		}
		// 获取短信验证码
		String sms = us.getMsm();
		HttpSession session = request.getSession();
		// 获取session中的手机号验证码
		String checksms = (String) session.getAttribute(SMS_VALIDATE_CODE);
		if (StringUtil.isNotEmpty(sms) && !sms.equals(checksms)) {// 测试>>>>>>>>>>>
			session.removeAttribute(VALIDATE_CODE);
			session.removeAttribute(SMS_VALIDATE_CODE);
			// 邀请人
			String yqr = "";
			if (StringUtil.isNotEmpty(us.getYqmReg())) {
				Map<String, String> yqmReturn = userService.registUseryqmReg(us.getYqmReg());
				if (yqmReturn != null) {
					String message = yqmReturn.get("message");
					String yqm_reg = yqmReturn.get("yqm_reg");
					if (message.equals("0") && !yqm_reg.equals("0")) {
						yqr = yqm_reg;
					} else {
						view.addFlashAttribute("msg", message);
						return url;
					}
				} else {
					view.addFlashAttribute("msg", "邀请人失败");
					return url;
				}
			}
			// 放入邀请人
			us.setYqmReg(yqr);
			// 用户注册
			String rows = userService.registUser(us);
			if (StringUtil.isNotEmpty(rows) && "1".equals(rows)) {

				memberAgreementService.gainupdateMemberAgreement(us.getName(), "1");
				sendMessage.spreadRegistWeb(us.getName());
				
				view.addFlashAttribute("msg", "注册成功");
				return "redirect:/portal/user/index";
			} else {
				view.addFlashAttribute("msg", rows);
				return url;
			}
		}
		view.addFlashAttribute("msg", "手机验证码不正确");
		return url;
	}

	/**
	 * 天涯注册
	 * 
	 * @param request
	 * @param view
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/registTianya", method = RequestMethod.POST)
	public String registTianya(HttpServletRequest request, RedirectAttributes view, UserDTO us) {
		String url = "";
		String register = us.getRegister();
		if (StringUtil.isNotEmpty(register) && "webRegister".equals(register)) {
			url = "redirect:/portal/ZTYMain/ActivityRegister";
		}
		if (StringUtil.isNotEmpty(register) && "wapRegister".equals(register)) {
			url = "redirect:/portal/ZTYMain/App/ActivityRegisterM";
		}
		// 获取短信验证码
		String sms = us.getMsm();
		HttpSession session = request.getSession();
		// 获取session中的手机号验证码
		String checksms = (String) session.getAttribute(SMS_VALIDATE_CODE);
		if (StringUtil.isNotEmpty(sms) && !sms.equals(checksms)) {// 测试>>>>>>>>>>>
			session.removeAttribute(VALIDATE_CODE);
			session.removeAttribute(SMS_VALIDATE_CODE);
			// 邀请人
			String yqr = "";
			if (StringUtil.isNotEmpty(us.getYqmReg())) {
				Map<String, String> yqmReturn = userService.registUseryqmReg(us.getYqmReg());
				if (yqmReturn != null) {
					String message = yqmReturn.get("message");
					String yqm_reg = yqmReturn.get("yqm_reg");
					if (message.equals("0") && !yqm_reg.equals("0")) {
						yqr = yqm_reg;
					} else {
						view.addFlashAttribute("msg", message);
						return url;
					}
				} else {
					view.addFlashAttribute("msg", "邀请人失败");
					return url;
				}
			}
			
			us.setYqmReg(yqr);
			String rows = userService.registUser(us);
			if (StringUtil.isNotEmpty(rows) && "1".equals(rows)) {
				memberAgreementService.gainupdateMemberAgreement(us.getName(), "1");
				// 发送加息券
				sendMessage.tianyaRegist(us.getName());
				view.addFlashAttribute("msg", "注册成功");
				return "redirect:/portal/user/index";
			} else {
				view.addFlashAttribute("msg", rows);
				return url;
			}
		}
		view.addFlashAttribute("msg", "手机验证码不正确");
		return url;
	}

	@RequestMapping(value = "/agreement_zDemo")
	public String agreement_zDemo() {
		return "agreement_zDemo";
	}

}
