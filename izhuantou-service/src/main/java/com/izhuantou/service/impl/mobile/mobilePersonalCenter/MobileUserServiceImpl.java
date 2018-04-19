package com.izhuantou.service.impl.mobile.mobilePersonalCenter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.util.SecurityUtils;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.code.CodeContent;
import com.izhuantou.damain.mobile.personalCenter.MobileLoginReturnDTO;
import com.izhuantou.damain.mobile.personalCenter.MobilequestionDTO;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PaySeller;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.code.CodeContentMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PaySellerMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.mobile.mobilePersonalCenter.MobileUserService;
import com.izhuantou.service.impl.user.ReadPropertiesl;

@Service("mobileUserService")
public class MobileUserServiceImpl implements MobileUserService {
    private static final UtilsMD5 md5 = new UtilsMD5();
    private static final Logger logger = LoggerFactory.getLogger(MobileUserServiceImpl.class);
    @Autowired
    private MemberMemberMapper userDao;
    @Autowired
    private PayCustomerMapper PayCustomerMapper;
    @Autowired
    private CodeContentMapper codeContentDao;
    @Autowired
    private PaySellerMapper paySellerMapper;

    /**
     * 手机端用户登录
     */
    @Override
    public MobileLoginReturnDTO mobileUserLogin(String name, String password) {
	try {
	    MemberMember member = userDao.findUserByName(name);
	    MobileLoginReturnDTO returndto = new MobileLoginReturnDTO();
	    if (member != null) {
		String passw = member.getPassword();
		String pwd = md5.Md5(password);
		if (passw.equals(pwd)) {
		    returndto.setOID(member.getOID());// 用户OID
		    returndto.setSmtype("false"); // 是否实名 默认是false
		    String addDateTime = DateUtils.formatDate(member.getAddDateTime().getTime());
		    returndto.setAddDateTime(addDateTime);// 注册时间
		    returndto.setMemberAccount(member.getMemberAccount());// 砖头账户
		    returndto.setMobile(member.getMobile());
		    returndto.setIsNew("0"); // 是否为新手 默认是新手
		    if (StringUtil.isNotEmpty(member.getQuestionOne())) {// 密保
			returndto.setQuestionOne(member.getQuestionOne());
			returndto.setQuestionTwo(member.getQuestionTwo());
		    }
		    returndto.setPicOID(member.getPicOID());
		    String memberOID = member.getOID();
		    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
		    if (customer != null) {
			returndto.setSmtype("true");
			returndto.setBankcardnumber(customer.getBankNumber());
			returndto.setBankname(customer.getBankCode());
			returndto.setIdCard(member.getIdCard());
			returndto.setNameCN(member.getNameCN());
			returndto.setRealName(member.getRealName());

			boolean pd = true;
			String isnew = "0";
			int num = userDao.findXSNum(memberOID);
			if (StringUtil.isNotEmpty(member.getUserType()) && "6".equals(member.getUserType())) {
			    isnew = "0";
			    pd = false;
			}
			if (pd) {
			    isnew = num + "";
			}
			returndto.setIsNew(isnew);
		    }
		    return returndto;
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("mobileUserLogin(String name, String password)", e.getMessage());
	    return null;
	}

    }

    /**
     * 验证用户是否存在
     */
    @Override
    public String checkMemberExist(String phone) {
	try {
	    if (StringUtil.isNotEmpty(phone)) {
		MemberMember member = userDao.findUserByName(phone);
		if (member == null) {
		    return "1";
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("checkMemberExist(String phone)", e.getMessage());
	    return null;
	}
    }

    /**
     * 查看密保问题
     */
    @Override
    public Map<String, String> findAppTwoQuestion(String phone) {
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    MemberMember member = userDao.findUserByName(phone);
	    if (StringUtil.isNotEmpty(member.getQuestionOne()) && StringUtil.isNotEmpty(member.getQuestionTwo())) {
		Integer questionOneType = Integer.parseInt(member.getQuestionOne());
		Integer questionTwoType = Integer.parseInt(member.getQuestionTwo());
		CodeContent Code1 = codeContentDao.findTwoQuestion(questionOneType);
		CodeContent Code2 = codeContentDao.findTwoQuestion(questionTwoType);
		String questionOne = Code1.getNameCN();
		String questionTwo = Code2.getNameCN();
		map.put("unsetproblem", "1");
		map.put("problem1", questionOne);
		map.put("problem2", questionTwo);
		return map;
	    } else {
		map.put("unsetproblem", "0");
		map.put("problem1", "no");
		map.put("problem2", "no");
		return map;
	    }
	} catch (Exception e) {
	    logger.error("findAppTwoQuestion(String phone)", e.getMessage());
	    return null;
	}
    }

    /**
     * 验证用户回答的问题
     */
    @Override
    public Map<String, String> checkAppTwoQuestion(String phone, String answer1, String answer2) {
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    MemberMember member = userDao.findUserByName(phone);
	    if (StringUtil.isNotEmpty(answer1) && StringUtil.isNotEmpty(answer1)) {
		String answerOne = member.getAnswerOne();
		String answerTwo = member.getAnswerTwo();
		if (answer1.equals(answerOne) && answer2.equals(answerTwo)) {
		    map.put("jiaoyan", "success");
		    return map;
		} else {
		    map.put("jiaoyan", "fail");
		    return map;
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("checkAppTwoQuestion(String phone, String answer1, String answer2)", e.getMessage());
	    return null;
	}
    }

    /**
     * 根据用户修改密码
     */
    @Override
    public Map<String, String> updatePassword(String phone, String password) {
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    if (StringUtil.isNotEmpty(phone) && StringUtil.isNotEmpty(password)) {
		MemberMember member = userDao.findUserByName(phone);
		String pwd = md5.Md5(password);
		member.setPassword(pwd);
		int rows = userDao.updatePassword(member);
		if (rows == 1) {
		    map.put("tijiao", "true");
		    return map;
		}
	    }
	    map.put("tijiao", "false");
	    return map;
	} catch (Exception e) {
	    logger.error("updatePassword(String phone, String password)", e.getMessage());
	    return null;
	}
    }

    /**
     * 设置何修改密保问题
     */
    @Override
    public String MobileSetSecurityQuestion(MobilequestionDTO question) {
	try {
	    if (question != null) {
		if (StringUtil.isNotEmpty(question.getProblem1()) && StringUtil.isNotEmpty(question.getProblem2())
			&& StringUtil.isNotEmpty(question.getAnswer1())
			&& StringUtil.isNotEmpty(question.getAnswer2())) {
		    MemberMember member = new MemberMember();
		    member.setOID(question.getMemberOID());
		    member.setQuestionOne(question.getProblem1());
		    member.setQuestionTwo(question.getProblem2());
		    member.setAnswerOne(question.getAnswer1());
		    member.setAnswerTwo(question.getAnswer2());
		    int rows = userDao.insertTwoQuestion(member);
		    if (rows == 1) {
			return String.valueOf(rows);
		    }
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("MobileSetSecurityQuestion(MobilequestionDTO question)", e.getMessage());
	    return null;
	}
    }

    /**
     * app端修改支付密码
     */
    @Override
    public Map<String, Object> AppUpdatePayPassword(String memberOID) {
	try {
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    Map<String, String> pay = ReadPropertiesl.gainPropertiesMap("Pay.properties");
	    String sellerName = pay.get("defaultSeller");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    ResetPassWordReqData data = new ResetPassWordReqData();
	    String ID = paySeller.getID();
	    // 商户代码
	    data.setMchnt_cd(ID);
	    // 流水号
	    String strRequestID = StringUtil.getUUID().substring(2);
	    data.setMchnt_txn_ssn(strRequestID);
	    // 用户的资金账户
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    // 用户登录名
	    data.setLogin_id(customer.getName());
	    // 类型
	    data.setBusi_tp("3");
	    // 商户返回地址
	    data.setBack_url(pay.get("appUpdatePayPasswordForwardURL"));

	    String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
	    resultmap.put("data", data);

	    return resultmap;
	} catch (Exception e) {
	    logger.error(" AppUpdatePayPassword(String memberOID) ", e.getMessage());
	    return null;
	}

    }
}
