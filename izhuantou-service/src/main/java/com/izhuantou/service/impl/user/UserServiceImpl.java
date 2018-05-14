package com.izhuantou.service.impl.user;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolClient;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.code.CodeContent;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.p2p.P2pCompanyPer;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.JoinSelectAllDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.dao.code.CodeContentMapper;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.p2p.P2pCompanyPerMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.personalCenter.PersonalCenterMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.service.impl.BaseServiceImpl;

import net.sf.jsqlparser.expression.StringValue;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<MemberMember> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final UtilsMD5 md5 = new UtilsMD5();
    @Autowired
    private MemberMemberMapper userDao;
    @Autowired
    private PayCashPoolMapper payCashPoolDao;

    @Autowired
    private PayDebitCreditMapper payDebitcreditDao;

    @Autowired
    private PayReturnPlanMapper payReturnPlanDao;
    @Autowired
    private PersonalCenterMapper personalCenterMapper;

    @Autowired
    private CodeContentMapper codeContentDao;

    @Autowired
    private PayCustomerMapper PayCustomerMapper;
    @Autowired
    private P2pCompanyPerMapper p2pCompanyPerDao;
    @Autowired
    private PayPrivilegeMapper privilegeMapper;
    @Autowired
    private PayPrivilegeMemberMappingMapper payPrivilegeMapper;
    @Autowired
    private MessageSmsHistoryMapper messageSmsHistoryMapper;
    @Autowired
    private MessageContentBusinessMapper messageContentBusinessMapper;

    /** 登录验证账户密码 */
    @Override
    public MemberMember findUserByName(UserDTO user) {
	MemberMember member = null;
	String pwd = null;
	try {
	    if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
		pwd = md5.Md5(user.getPassword());
		member = userDao.findUserByName(user.getName());
		if (member != null) {
		    String passw = member.getPassword();
		    if (passw.equals(pwd)) {
			logger.info("findUserByName()" + "返回结果为");
			return member;
		    } else {
			return null;
		    }
		} else {
		    return null;
		}
	    }
	} catch (Exception e) {
	    logger.error("findUserByName()", e.getMessage());
	    return null;
	}
	return null;
    }

    /** 根据用户名修改密码 */
    @Override
    public Integer updatePassword(UserDTO user) {
	try {
	    if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
		MemberMember member = userDao.findUserByName(user.getName());
		if (member != null) {
		    String password = md5.Md5(user.getPassword());
		    MemberMember us = new MemberMember();
		    us.setName(user.getName());
		    us.setPassword(password);
		    us.setVersion(member.getVersion());
		    int rows = userDao.updatePassword(us);
		    if (rows == 1) {
			return rows;
		    }
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("registByNameAndPassword()", e.getMessage());
	    return null;
	}
    }

    /**
     * 查询用户密保的问题
     */
    @Override
    public Map<String, String> findTwoQuestion(UserDTO user) {
	MemberMember member = null;
	try {
	    if (StringUtil.isNotEmpty(user.getName())) {
		member = userDao.findUserByName(user.getName());
		if (StringUtil.isNotEmpty(member.getQuestionOne()) && StringUtil.isNotEmpty(member.getQuestionTwo())) {
		    Integer questionOneType = Integer.parseInt(member.getQuestionOne());
		    Integer questionTwoType = Integer.parseInt(member.getQuestionTwo());
		    CodeContent Code1 = codeContentDao.findTwoQuestion(questionOneType);
		    CodeContent Code2 = codeContentDao.findTwoQuestion(questionTwoType);
		    String questionOne = Code1.getNameCN();
		    String questionTwo = Code2.getNameCN();
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("questionOne", questionOne);
		    map.put("questionTwo", questionTwo);
		    map.put("answerOne", member.getAnswerOne());
		    map.put("answerTwo", member.getAnswerTwo());
		    return map;
		} else {
		    return null;
		}
	    }
	} catch (Exception e) {
	    logger.error("findTwoQuestionOne(UserDTO user)", e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public Map<String, Object> findIndexNumber(String memberOID) {
	try {
	    // 将返回的3个数字放入此Map用来放返回的
	    Map<String, Object> dtoii = new HashMap<String, Object>();
	    // 将数据转换成 BigDecimal进行计算的更精确
	    BigDecimal cybj = new BigDecimal(0.0);
	    BigDecimal yjsy = new BigDecimal(0.0);
	    // 用来盛放用户参加环环头和点点头的数据信息 // -----------
	    List<Map<String, Object>> dtorps = new ArrayList<Map<String, Object>>();
	    List<JoinSelectAllDTO> hhdtorps = payReturnPlanDao.gainReturnPlanByMemberOIDAndState(memberOID,"agentPlan");
		 
	    List<JoinSelectAllDTO> dddtorps = personalCenterMapper.gainDDReturnMoneyBymemberOIDANDState(memberOID, "plan");
		   

	    if (hhdtorps.size() > 0) {
		for (JoinSelectAllDTO payReturnPlan : hhdtorps) {
		    Map<String, Object> map1 = new HashMap<String, Object>();

		    map1.put("interestMoney", new BigDecimal(payReturnPlan.getInterestMoney().toString()));
		    map1.put("principalMoney", new BigDecimal(payReturnPlan.getPrincipalMoney().toString()));
		    dtorps.add(map1);
		}
	    }
	    if (dddtorps.size() > 0) {
		for (JoinSelectAllDTO JoinSelectAll : dddtorps) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("interestMoney", new BigDecimal(JoinSelectAll.getInterestMoney().toString()));
		    map.put("principalMoney", new BigDecimal(JoinSelectAll.getPrincipalMoney().toString()));
		    dtorps.add(map);
		}
	    }
	    for (Map<String, Object> map : dtorps) {
		yjsy = yjsy.add((BigDecimal) map.get("interestMoney"));
		cybj = cybj.add((BigDecimal) map.get("principalMoney"));
	    }

	    BigDecimal jxsy = new BigDecimal(0.0);
	    BigDecimal tqsyds = new BigDecimal(0.0);
	    List<PayCashPool> memberCashPool = payCashPoolDao.gainByMemberOID(memberOID);
	    if (memberCashPool != null) {
		for (PayCashPool pcp : memberCashPool) {
		    if ("investment".equals(pcp.getState()) && null != pcp.getJXinterest()) {
			jxsy = jxsy.add(pcp.getJXinterest());
		    }
		    if ("investment".equals(pcp.getState()) && null != pcp.getPrivilegeIncome()) {
			tqsyds = tqsyds.add(pcp.getPrivilegeIncome());
		    }
		}
	    }
	    // 代收本金：cybj，代收利息：yjsy，代收特权：tqsyds
	    yjsy = yjsy.add(jxsy);
	    dtoii.put("cybj", cybj);
	    dtoii.put("yjsy", yjsy);
	    // dtoii.put("yjzq", yjzq);
	    dtoii.put("tqsyds", tqsyds);
	    logger.info("findIndexNumber(String memberOID)" + "返回结果为");
	    return dtoii;

	} catch (Exception e) {
	    logger.error("findIndexNumber(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 判断状态返回 根据特权OID和产品OID
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<Map<String, Object>> gainDDTTQBymemberANDstate(String memberOID, String state) {
	if (StringUtil.isNotEmpty(memberOID)) {
	    if (StringUtil.isNotEmpty(state)) {
		if (state.equals("1")) {
		    return payDebitcreditDao.gainDDTTQBymemberANDstateOne(memberOID);
		} else if (state.equals("2")) {
		    return payDebitcreditDao.gainDDTTQBymemberANDstateTwo(memberOID);
		}
	    }
	    return null;
	} else {
	    return null;
	}

    }

    /**
     * 判断状态返回对应得数据 特权代收的
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<Map<String, Object>> gainByMemebrOIDAndTQ(String memberOID, String state) {
	if (StringUtil.isNotEmpty(state)) {
	    return payCashPoolDao.gainByMemebrOIDAndTQTwo(memberOID, state);
	} else {
	    return payCashPoolDao.gainByMemebrOIDAndTQOne(memberOID);
	}
    }

    /**
     * 判断用户是否开通资金账户
     */
    @Override
    public PayCustomer findCustomerByMemberOID(String memberOID) {
	try {
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    return customer;
	} catch (Exception e) {
	    logger.error("findIndexNumber(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /** 注册验证用户名是否存在 */
    @Override
    public String registUser(UserDTO user) {
	try {
	    if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
		MemberMember member = userDao.findUserByName(user.getName());
		if (member == null) {
		    String OID = StringUtil.getUUID();
		    String password = md5.Md5(user.getPassword());
		    MemberMember us = new MemberMember();
		    us.setName(user.getName());
		    us.setMemberAccount("ZT" + user.getName());
		    us.setOID(OID);
		    us.setPassword(password);
		    us.setYewuOID(user.getYqm_reg());
		    int rows = userDao.registUser(us);
		    return String.valueOf(rows);
		} else {
		    return "账号已存在";
		}
	    }
	    return "账号和密码不能为空";
	} catch (Exception e) {
	    logger.error("findUserByName()", e.getMessage());
	    return null;
	}
    }

    /**
     * 获取邀请ren的OID
     */
    @Override
    public Map<String, String> registUseryqm_reg(String yqm_reg) {
	Map<String, String> map = new HashMap<String, String>();
	try {
	    if (StringUtil.isNotEmpty(yqm_reg) && yqm_reg.contains("手机")) {
		yqm_reg = "";
	    }
	    if (StringUtil.isNotEmpty(yqm_reg)) {
		if (("18606563456").equals(yqm_reg) || ("13105797175").equals(yqm_reg)
			|| ("15023178890").equals(yqm_reg) || ("15070681024").equals(yqm_reg)
			|| ("18798669898").equals(yqm_reg)) {
		    map.put("message", "推荐用户不存在");
		    map.put("yqm_reg", "0");
		    return map;
		} else {
		    MemberMember member = userDao.findUserByName(yqm_reg);

		    P2pCompanyPer compan = p2pCompanyPerDao.findComperByNumber(yqm_reg);
		    if (member == null) {
			if (compan == null) {
			    map.put("message", "推荐用户不存在");
			    map.put("yqm_reg", "0");
			    return map;
			} else {
			    yqm_reg = compan.getOID();
			    map.put("message", "0");
			    map.put("yqm_reg", yqm_reg);
			}
		    } else {
			yqm_reg = member.getOID();
			map.put("message", "0");
			map.put("yqm_reg", yqm_reg);
		    }
		}
	    }
	    return map;
	} catch (Exception e) {
	    logger.error("findUserByName()", e.getMessage());
	    return null;
	}
    }

    /**
     * 赠送天涯加息券
     */
    @Override
    public String tianyaRegist(UserDTO user) {
	try {
	    MemberMember member = userDao.findUserByName(user.getName());
	    Map<String, String> propMap = ReadPropertiesl.gainPropertiesMap("privilege.properties");
	    String isJX = propMap.get("isJX");
	    String days = propMap.get("days");
	    String tyDays = propMap.get("tyDays");
	    String[] zc = { "zc2", "zc3", "zc5", "zcty" };
	    if ("1".equals(isJX)) {
		for (int i1 = 0; i1 <= zc.length - 1; i1++) {
		    String key = zc[i1];
		    String tqOID = propMap.get(key);
		    PayPrivilege privilege = privilegeMapper.findByOID(tqOID);
		    int i = privilege.getPrivilegeTerm();
		    int j = DateUtils.daysBetween(privilege.getBeginDateTime(), privilege.getEndDateTime());

		    String k = DateUtils.getDateFormatter();
		    String st = DateUtils.formatDate(member.getAddDateTime(), "");
		    int day = Integer.valueOf(days);
		    if ("zcty".equals(zc[i1])) {
			day = Integer.valueOf(tyDays);
		    }
		    String et = DateUtils.gainPlusAndDateTime(st, day, 0);
		    DateUtils.getJustDate(et);
		    PayPrivilegeMemberMapping privi = new PayPrivilegeMemberMapping();

		    String oid = StringUtil.getUUID();
		    privi.setOID(oid);
		    privi.setPrivilegeOID(tqOID);
		    privi.setMemberOID(member.getOID());
		    privi.setIsUsed("0");
		    String addTime = DateUtils.formatDate(member.getAddDateTime(), "");
		    Timestamp addT = Timestamp.valueOf(addTime);
		    privi.setStartDate(addT);

		    String endTime = DateUtils.formatDate(member.getAddDateTime(), "");
		    Timestamp endT = Timestamp.valueOf(endTime);
		    privi.setEndDate(endT);
		    privi.setUseTerm(i);
		    payPrivilegeMapper.saveUserPrivelege(privi);
		}
		MessageContentBusiness messageBusiness = new MessageContentBusiness();
		String mOID = StringUtil.getUUID();
		messageBusiness.setOID(mOID);
		messageBusiness.setTitle("您已获得新用户加息劵");
		String content = "【砖头网】尊敬的用户：感谢您注册砖头网， 9%加息券（2%+3%+4%）及天涯3%专享券已发放到您的账户中，详情请在【我的特权】-【加息券】中进行查看！如有问题，请联系客服400-900-9677，感谢您的支持，祝您生活愉快。";
		messageBusiness.setContent(content);
		messageBusiness.setSendUser("系统");
		messageBusiness.setReceiveUserOID(member.getOID());

		int row = messageContentBusinessMapper.saveMessageBusiness(messageBusiness);
		if (row == 1) {
		    Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
		    String userName = smsMap.get("account");
		    String password = smsMap.get("password");

		    MessageSmsHistory smsHistory = new MessageSmsHistory();
		    String sOID = StringUtil.getUUID();
		    smsHistory.setOID(sOID);
		    smsHistory.setTitle("您已获得新用户加息劵");
		    String scontent = "【砖头网】尊敬的用户：感谢您注册砖头网， 9%加息券（2%+3%+4%）及天涯3%专享券已发放到您的账户中，详情请在【我的特权】-【加息券】中进行查看！如有问题，请联系客服400-900-9677，感谢您的支持，祝您生活愉快。退订回N";
		    smsHistory.setContent(scontent);
		    smsHistory.setSendUser("系统");
		    smsHistory.setReceiveUser("测试");
		    smsHistory.setReceiveUser(member.getName());
		    Map<String, String> result = ToolClient.sendSMS(member.getName(), userName, password, scontent,
			    null);
		    String state = result.get("Description");
		    smsHistory.setState(state);
		    messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
		}
	    }
	    member.setAddUserOID(user.getChannel());
	    int srow = userDao.updateMemberMember(member);
	    return String.valueOf(srow);
	} catch (Exception e) {
	    logger.error("tianyaRegist(UserDTO user)", e.getMessage());
	    return null;
	}

    }

    /**
     * 注册 魔积分
     */
    @Override
    public String SpreadRegistWeb(UserDTO user) {
	try {
	    MemberMember member = userDao.findUserByName(user.getName());
	    Map<String, String> propMap = ReadPropertiesl.gainPropertiesMap("privilege.properties");
	    String isJX = propMap.get("isJX");
	    String days = propMap.get("days");
	    String tyDays = propMap.get("tyDays");
	    String[] zc = { "zc2", "zc3", "zc5" };
	    if ("1".equals(isJX)) {
		for (int i1 = 0; i1 <= zc.length - 1; i1++) {
		    String key = zc[i1];
		    String tqOID = propMap.get(key);
		    PayPrivilege privilege = privilegeMapper.findByOID(tqOID);
		    int i = privilege.getPrivilegeTerm();
		    int j = DateUtils.daysBetween(privilege.getBeginDateTime(), privilege.getEndDateTime());

		    String k = DateUtils.getDateFormatter();
		    String st = DateUtils.formatDate(member.getAddDateTime(), "");
		    int day = Integer.valueOf(days);
		    if ("zcty".equals(zc[i1])) {
			day = Integer.valueOf(tyDays);
		    }
		    String et = DateUtils.gainPlusAndDateTime(st, day, 0);
		    DateUtils.getJustDate(et);
		    PayPrivilegeMemberMapping privi = new PayPrivilegeMemberMapping();

		    String oid = StringUtil.getUUID();
		    privi.setOID(oid);
		    privi.setPrivilegeOID(tqOID);
		    privi.setMemberOID(member.getOID());
		    privi.setIsUsed("0");
		    String addTime = DateUtils.formatDate(member.getAddDateTime(), "");
		    Timestamp addT = Timestamp.valueOf(addTime);
		    privi.setStartDate(addT);

		    String endTime = DateUtils.formatDate(member.getAddDateTime(), "");
		    Timestamp endT = Timestamp.valueOf(endTime);
		    privi.setEndDate(endT);
		    privi.setUseTerm(i);
		    int rows = payPrivilegeMapper.saveUserPrivelege(privi);
		}
		MessageContentBusiness messageBusiness = new MessageContentBusiness();
		String mOID = StringUtil.getUUID();
		messageBusiness.setOID(mOID);
		messageBusiness.setTitle("您已获得新用户加息劵");
		String content = "【砖头网】尊敬的用户：感谢您成功注册砖头网， 9%的加息券（2%+3%+4%）已返到您的账户中，详情请在【我的特权】-【加息券】中进行查看！出借即可使用(App端需v2.0.0以上版本)，"
			+ days + "天内有效。祝您生活愉快！";
		messageBusiness.setContent(content);
		messageBusiness.setSendUser("系统");
		messageBusiness.setReceiveUserOID(member.getOID());

		int row = messageContentBusinessMapper.saveMessageBusiness(messageBusiness);
		if (row == 1) {
		    Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
		    String userName = smsMap.get("account");
		    String password = smsMap.get("password");

		    MessageSmsHistory smsHistory = new MessageSmsHistory();
		    String sOID = StringUtil.getUUID();
		    smsHistory.setOID(sOID);
		    smsHistory.setTitle("您已获得新用户加息劵");
		    String scontent = "【砖头网】尊敬的用户：感谢您成功注册砖头网， 9%的加息券（2%+3%+4%）已返到您的账户中，详情请在【我的特权】-【加息券】中进行查看！出借即可使用(App端需v2.0.0以上版本)，"
			    + days + "天内有效。如有问题，请联系客服400-900-9677，感谢您的支持，祝您生活愉快！";
		    smsHistory.setContent(scontent);
		    smsHistory.setSendUser("系统");
		    smsHistory.setReceiveUser("测试");
		    smsHistory.setReceiveUser(member.getName());
		    Map<String, String> result = ToolClient.sendSMS(member.getName(), userName, password, scontent,
			    null);
		    String state = result.get("Description");
		    smsHistory.setState(state);
		    int rowa = messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
		}
	    }
	    member.setAddUserOID(user.getChannel());
	    int srow = userDao.updateMemberMember(member);
	    return String.valueOf(srow);
	} catch (Exception e) {
	    logger.error("tianyaRegist(UserDTO user)", e.getMessage());
	    return null;
	}
    }

}
