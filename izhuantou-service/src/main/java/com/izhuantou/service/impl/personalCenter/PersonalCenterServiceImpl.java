package com.izhuantou.service.impl.personalCenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.LendMoneyDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.personalCenter.PersonalCenterMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.personalCenter.PersonalCenterService;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.service.impl.user.ReadPropertiesl;

/**
 * 我的消息投实现类
 * 
 * @author yangbosen
 *
 */
@Service("personalCenterService")
@Transactional
public class PersonalCenterServiceImpl implements PersonalCenterService {

    private static final Logger logger = LoggerFactory.getLogger(PersonalCenterServiceImpl.class);
    private static final UtilsMD5 md5 = new UtilsMD5();
    @Autowired
    private PersonalCenterMapper personalMessageMapper;

    @Autowired
    private PayCustomerMapper customerMapper;

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private UserService userService;

    /** 查询所有未读消息 */
    @Override
    public Pagination<MessageContentBusiness> findMessage(Integer page, String memberOID) {
	List<MessageContentBusiness> list = new ArrayList<>();
	Pagination<MessageContentBusiness> pageController = new Pagination<>();
	try {
	    // Integer page = 1;
	    // "370518b20a5dca712804025f2e15867a"
	    /** 接受前台当前页参数 */
	    if (page != null) {
		pageController.setCurrentPage(page);
	    }
	    pageController.setPageSize(13);
	    Integer totalNumber = (int) personalMessageMapper.countMessage(memberOID);
	    pageController.setTotalNumber(totalNumber);
	    // Integer totalPage = pageController.getTotalPage();
	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		list = personalMessageMapper.findAllMessage(memberOID, StartPos, pageController.getPageSize());
	    } else {
		list = personalMessageMapper.findAllMessage(memberOID, 0, 10);
	    }
	    pageController.setData(list);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findMessage()", e.getMessage());
	    return null;
	}
	return pageController;
    }

    /** 查询所有已读消息 */
    @Override
    public Pagination<MessageContentBusiness> findHistoryMessage(Integer page, String memberOID) {
	List<MessageContentBusiness> list = new ArrayList<>();
	Pagination<MessageContentBusiness> pageController = new Pagination<>();
	try {
	    /** 接受前台当前页参数 */
	    if (page != null) {
		pageController.setCurrentPage(page);
	    }
	    pageController.setPageSize(13);
	    Integer totalNumber = (int) personalMessageMapper.countMessage(memberOID);
	    pageController.setTotalNumber(totalNumber);
	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		list = personalMessageMapper.findALLHistoryMessage(memberOID, StartPos, pageController.getPageSize());
	    } else {
		list = personalMessageMapper.findALLHistoryMessage(memberOID, 0, 10);
	    }
	    pageController.setData(list);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
	return pageController;
    }

    /** 查询未读消息数和消息数 */
    @Override
    public Map<String, Integer> findMessageNum(String memberOID) {
	try {
	    Map<String, Integer> map = new HashMap<String, Integer>();

	    Integer count = personalMessageMapper.countMessage(memberOID);
	    Integer historyCount = personalMessageMapper.countHistoryMessage(memberOID);

	    Integer allCount = count + historyCount;
	    map.put("countMessageAll", allCount);
	    map.put("countMessage", count);
	    return map;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findResult()", e.getMessage());
	    return null;
	}
    }

    /** 把已读消息加入到未读消息 */
    @Override
    public void insertMessage(String OID) {
	MessageContentBusiness message = new MessageContentBusiness();
	// String OID = "22afe938f5d3f6e25144ef48ed387158";
	try {
	    message = personalMessageMapper.selectOneMessage(OID);
	    Integer s = personalMessageMapper.insertMessage(message);
	    if (s == 1) {
		personalMessageMapper.moveMessageContentNoPush(OID);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("insertMessage()", e.getMessage());
	}

    }

    /**
     * 查询个人中心 中心首页的信息
     */
    @Override
    public Map<String, Object> userCenterIndex(String memberOID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID)) {
		PayCustomer payCustomer = customerMapper.findByMemberOID(memberOID);
		MemberMember member = userDao.findUserByOID(memberOID);

		Map<String, Object> map = userService.findIndexNumber(memberOID);
		// 资料完整度
		Integer dataIntegrity = 25;
		// 判断有无密保问题
		if (StringUtil.isNotEmpty(member.getQuestionOne())) {
		    map.put("mibaoTrue", "true");
		    dataIntegrity = dataIntegrity + 25;
		}
		// 判断有无绑定银行卡
		if (payCustomer != null) {
		    map.put("idCardTrue", "true");
		    dataIntegrity = dataIntegrity + 50;
		}
		// 资料认证程度
		map.put("dataIntegrity", dataIntegrity.toString() + "%");
		/**
		 * 读取Properties文件 临时使用
		 */
		String path = ReadPropertiesl.gainPropertiesValue("fileServerLink", "File.properties");
		// 获取用户头像
		String picOID = (String) member.getPicOID();
		// 读取图片的路径
		map.put("imgSrc", path);
		// 图片的id
		map.put("picOID", picOID);
		// 可用余额
		map.put("availablemoney", new BigDecimal(payCustomer.getUseMoney().toString()));
		return map;
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error("userCenterIndex(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 借款账户
     */
    @Override
    public List<LendMoneyDTO> findLendMoneyBymemberOID(String memberOID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID)) {
		List<LendMoneyDTO> lendMoney = personalMessageMapper.findLendMoneyBymemberOID(memberOID);
		for (LendMoneyDTO dto : lendMoney) {
		    Date addDate = DateUtils.getJustDate(dto.getAddDateTime());
		    String addDateTime = DateUtils.formatJustDate(addDate.getTime());
		    dto.setAddDateTime(addDateTime);
		    // 下个还款日
		    Date repayDate = DateUtils.getJustDate(dto.getRepayDate());
		    int date = DateUtils.daysBetween(repayDate, new Date());
		    dto.setDateFlag(date);
		}
		return lendMoney;
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("findLendMoneyBymemberOID(String memberOID)", e.getMessage());
	    return null;
	}
    }

    /**
     * 用来返回用的安全认证信息
     */
    @Override
    public Map<String, Object> SecurityCenter(String memberOID) {
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (StringUtil.isEmpty(memberOID)) {
		return null;
	    }
	    // 普通账户
	    MemberMember member = userDao.findUserByOID(memberOID);
	    // 是否注册资金帐户
	    PayCustomer customer = customerMapper.findByMemberOID(memberOID);
	    // 手机号
	    if (StringUtil.isNotEmpty(member.getName())) {
		map.put("mobile", "*******" + member.getName().substring(7));
	    }
	    if (customer != null) {
		map.put("iscreatefy", "1");
		map.put("nameCN", member.getNameCN());
	    } else {
		map.put("iscreatefy", "0");
		map.put("nameCN", "");
	    }
	    // 是否已有密保问题
	    if (StringUtil.isNotEmpty(member.getQuestionOne())) {
		map.put("question", "1");
	    } else {
		map.put("question", "0");
	    }
	    return map;
	} catch (Exception e) {
	    logger.error("SecurityCenter(String Usermobile)", e.getMessage());
	    return null;
	}
    }

    /**
     * 根据原来密码修改密码
     */
    @Override
    public String updatePasswordByOld(UserDTO user) {
	try {
	    if (StringUtil.isNotEmpty(user.getOID())) {
		if (StringUtil.isEmpty(user.getPassword()) || StringUtil.isEmpty(user.getPassword2())) {
		    return "新密码或旧密码不能为空";
		}
		MemberMember member = userDao.findUserByOID(user.getOID());
		if (member != null) {
		    String oldPassword = md5.Md5(user.getPassword());
		    if (StringUtil.isNotEmpty(member.getPassword()) && oldPassword.equals(member.getPassword())) {
			String newPassword = md5.Md5(user.getPassword2());
			if (oldPassword.equals(newPassword)) {
			    return "原密码和密码相同";
			}
			member.setPassword(newPassword);
			int rows = userDao.updatePassword(member);
			return String.valueOf(rows);
		    } else {
			return "密码有误";
		    }
		}
	    } else {
		return "用户未登陆";
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("updatePasswordByOld(String memberOID, String oldPwd, String newPwd) ", e.getMessage());
	    return null;
	}

    }

    /**
     * 添加或修改密保问题
     */
    @Override
    public String insertTwoQuestion(UserDTO user) {
	if (user != null) {
	    if (StringUtil.isNotEmpty(user.getOID())) {
		if (StringUtil.isNotEmpty(user.getQuestionOne()) && StringUtil.isNotEmpty(user.getQuestionTwo())
			&& StringUtil.isNotEmpty(user.getAnswerOne()) && StringUtil.isNotEmpty(user.getAnswerTwo())) {
		    MemberMember member = new MemberMember();
		    member.setOID(user.getOID());
		    member.setQuestionOne(user.getQuestionOne());
		    member.setQuestionTwo(user.getQuestionTwo());
		    member.setAnswerOne(user.getAnswerOne());
		    member.setAnswerTwo(user.getAnswerTwo());
		    // 根据OID查找到version值 并进行并发比对
		    Integer version = userDao.findUserByOID(user.getOID()).getVersion();
		    member.setVersion(version);
		    int rows = userDao.insertTwoQuestion(member);
		    return String.valueOf(rows);
		}
		return null;
	    } else {
		return "用户OID不能为空";
	    }
	}
	return null;
    }

}
