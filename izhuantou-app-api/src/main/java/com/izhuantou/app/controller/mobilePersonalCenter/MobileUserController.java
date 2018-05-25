package com.izhuantou.app.controller.mobilePersonalCenter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.service.FuiouService;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.rsa.RsaCodeTool;
import com.izhuantou.common.tool.ToolMath;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.mobile.personalCenter.MobileLoginReturnDTO;
import com.izhuantou.damain.mobile.personalCenter.MobileUpdatePwdDTO;
import com.izhuantou.damain.mobile.personalCenter.MobilequestionDTO;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileUserService;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.message.SendMessageService;

@Controller
@RequestMapping(value = "app/user", produces = "application/json;charset=UTF-8")
public class MobileUserController {
	private static final Logger logger = LoggerFactory.getLogger(MobileUserController.class);
	public static final String VALIDATE_CODE = "validateCode";
	public static final String SMS_VALIDATE_CODE = "smsValidateCode";
	@Autowired
	private MobileUserService mobileUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private SendMessageService sendMessageService;
	@Autowired
	private MyCashService myCashService;
	@Autowired
	private ControlPayService controlPayService;

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mobileLogin")
	@ResponseBody
	public OpResult mobileLogin11(String phone, String password, String pkversion) {
		RsaCodeTool rsac = new RsaCodeTool();
		try {
			// 对密文进行解密
			phone = rsac.serverDecrypt(phone, pkversion);
			password = rsac.serverDecrypt(password, pkversion);
			if (StringUtil.isNotEmpty(phone) && StringUtil.isNotEmpty(password)) {
				MobileLoginReturnDTO mbileLogin = mobileUserService.mobileUserLogin(phone, password);
				if (mbileLogin != null) {
					return OpResult.getSuccessResult(mbileLogin);
				} else {
					return OpResult.getFailedResult("账号或密码有误");
				}
			} else {
				return OpResult.getFailedResult("账户和密码不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 用户注册
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/mobileSuccessRegist")
	@ResponseBody
	public OpResult mobileSuccessRegist(String phone_reg, String password_reg, String yqm_reg, String pkversion) {
		RsaCodeTool rsac = new RsaCodeTool();
		try {
			// 对密文进行解密
			phone_reg = rsac.serverDecrypt(phone_reg, pkversion);
			password_reg = rsac.serverDecrypt(password_reg, pkversion);
			yqm_reg = rsac.serverDecrypt(yqm_reg, pkversion);
			if (StringUtil.isNotEmpty(phone_reg) && StringUtil.isNotEmpty(password_reg)) {
				UserDTO user = new UserDTO();
				user.setName(phone_reg);
				user.setPassword(password_reg);
				user.setYqmReg(yqm_reg);
				String row = userService.registUser(user);
				if (row != null && row == "1") {
					return OpResult.getSuccessResult("success");
				} else {
					return OpResult.getFailedResult("注册失败");
				}
			} else {
				return OpResult.getFailedResult("账号或密码不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 手机验证码
	 * 
	 * @param request
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/mobileYzmRegist")
	@ResponseBody
	public OpResult checksms(String phone_reg, String name, String pkversion) {
		RsaCodeTool rsac = new RsaCodeTool();
		try {
			// 对密文进行解密
			phone_reg = rsac.serverDecrypt(phone_reg, pkversion);
			name = rsac.serverDecrypt(name, pkversion);
			if (StringUtil.isEmpty(phone_reg)) {
				return OpResult.getFailedResult("手机号不能为空");
			}
			// 获取随机短信验证码
			int iValidateCode = ToolMath.gainRandomInt(0, 999999);
			String strValidateCode = ToolString.addLengthAtLeft((new Integer(iValidateCode)).toString(), "0", 6);
			Map<String, String> map = new HashMap<String, String>();
			map.put(SMS_VALIDATE_CODE, strValidateCode);
			String result = sendMessageService.sendMessage(phone_reg, name, map);
			if (StringUtil.isNotEmpty(result) && result.equals("1")) {
				return OpResult.getSuccessResult(strValidateCode);
			} else {
				return OpResult.getFailedResult(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 验证用户是不是存在 success为不存在 fail为存在
	 * 
	 * @param request
	 * @param us
	 * @return
	 */
	@RequestMapping(value = "/mobileExistRegist")
	@ResponseBody
	public OpResult mobileExistRegist(String phone_reg, String pkversion) {
		RsaCodeTool rsac = new RsaCodeTool();
		// 对密文进行解密
		try {
			phone_reg = rsac.serverDecrypt(phone_reg, pkversion);
			if (StringUtil.isNotEmpty(phone_reg)) {
				String row = mobileUserService.checkMemberExist(phone_reg);
				if (StringUtil.isNotEmpty(row) && "1".equals(row)) {
					return OpResult.getSuccessResult("success");
				}
				return OpResult.getSuccessResult("fail");
			}
			return OpResult.getFailedResult("手机号不能为空");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 注册富友
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/mobileCreateCustomerAccount")
	@ResponseBody
	public OpResult mobileCreateCustomerAccount(CustomerDTO customer) {
		RsaCodeTool rsac = new RsaCodeTool();
		// 对密文进行解密
		try {
			String pkversion = customer.getPkversion();
			customer.setMemberOID(rsac.serverDecrypt(customer.getMemberOID(), pkversion));
			customer.setCity(rsac.serverDecrypt(customer.getCity(), pkversion));
			customer.setCardName(rsac.serverDecrypt(customer.getCardName(), pkversion));
			customer.setPhone(rsac.serverDecrypt(customer.getPhone(), pkversion));
			customer.setBankNumber(rsac.serverDecrypt(customer.getBanckName(), pkversion));
			customer.setIdCard(rsac.serverDecrypt(customer.getIdCard(), pkversion));
			customer.setWithdrawalsPwd(rsac.serverDecrypt(customer.getWithdrawalsPwd(), pkversion));
			customer.setBindBank(rsac.serverDecrypt(customer.getBindBank(), pkversion));
			if (customer != null) {
				String rows = controlPayService.createCustomerAccount(customer);
				if (StringUtil.isNotEmpty(rows)) {
					if ("1".equals(rows)) {
						return OpResult.getSuccessResult("success");
					} else {
						return OpResult.getFailedResult(rows);
					}
				} else {
					return OpResult.getSuccessResult("fail");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看密保 校验密保问题 修改密码
	 * 
	 * @param request
	 * @param updto
	 * @return
	 */
	@RequestMapping(value = "/mobileUpdatePwd")
	@ResponseBody
	public OpResult mobileUpdatePwd(MobileUpdatePwdDTO updto) {
		RsaCodeTool rsac = new RsaCodeTool();
		// 对密文进行解密
		String pkversion = updto.getPkversion();
		try {
			String phone = rsac.serverDecrypt(updto.getPhone(), pkversion);
			String newpassword = rsac.serverDecrypt(updto.getNewpassword(), pkversion);
			String answerOne = rsac.serverDecrypt(updto.getAnswerOne(), pkversion);
			String answerTwo = rsac.serverDecrypt(updto.getAnswerTwo(), pkversion);
			String question = rsac.serverDecrypt(updto.getQuestion(), pkversion);
			if (StringUtil.isEmpty(phone)) {
				return OpResult.getFailedResult("手机号不能为空");
			}
			if (StringUtil.isNotEmpty(question)) {
				if (question.equals("mibao")) {
					Map<String, String> map = mobileUserService.findAppTwoQuestion(phone);
					if (map == null) {
						return OpResult.getFailedResult("密保查看失败");
					} else {
						return OpResult.getSuccessResult(map);
					}
				}
				if (question.equals("jiaoyan")) {
					Map<String, String> map = mobileUserService.checkAppTwoQuestion(phone, answerOne, answerTwo);
					if (map == null) {
						return OpResult.getFailedResult("校验失败");
					} else {
						return OpResult.getSuccessResult(map);
					}
				}
				return OpResult.getFailedResult("没有匹配的类型");
			} else {// 密保和短信通用找回密码
				if (StringUtil.isNotEmpty(newpassword)) {
					Map<String, String> map = mobileUserService.updatePassword(phone, newpassword);
					if (map != null) {
						return OpResult.getSuccessResult(map);
					} else {
						return OpResult.getFailedResult("修改密码失败");
					}
				} else {
					return OpResult.getFailedResult("新密码不能为空");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 添加密保问题或修改
	 * 
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "/pageMobileSecurityQuestion")
	@ResponseBody
	public OpResult pageMobileSecurityQuestion(MobilequestionDTO question) {
		RsaCodeTool rsac = new RsaCodeTool();
		// 对密文进行解密
		String pkversion = question.getPkversion();
		try {
			question.setMemberOID(rsac.serverDecrypt(question.getMemberOID(), pkversion));
			question.setProblem1(rsac.serverDecrypt(question.getProblem1(), pkversion));
			question.setProblem2(rsac.serverDecrypt(question.getProblem2(), pkversion));
			question.setAnswer1(rsac.serverDecrypt(question.getAnswer1(), pkversion));
			question.setAnswer2(rsac.serverDecrypt(question.getAnswer2(), pkversion));
			if (question != null) {
				String result = mobileUserService.MobileSetSecurityQuestion(question);
				if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
					return OpResult.getSuccessResult("success");
				}
			}
			return OpResult.getSuccessResult("fail");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改支付密码 在富友页面修改，修改完以后没有回调参数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/AppUpdatePayPassword")
	@ResponseBody
	public String AppUpdatePayPassword(HttpServletResponse response, String memberOID, String pkversion) {
		try {
			RsaCodeTool rsac = new RsaCodeTool();
			memberOID = rsac.serverDecrypt(memberOID, pkversion);
			if (StringUtil.isNotEmpty(memberOID)) {
				ResetPassWordReqData data =controlPayService.appUpdatePayPassword(memberOID); 
				if (data != null) {
					FuiouService.appResetPassWord(data, response);
					return null;
				}
			}
			return "memberOID不能为空";
		} catch (Exception e) {
			logger.error(" AppUpdatePayPassword(HttpServletResponse response,String memberOID)", e.getMessage());
			return "接口调用失败";
		}
	}

}
