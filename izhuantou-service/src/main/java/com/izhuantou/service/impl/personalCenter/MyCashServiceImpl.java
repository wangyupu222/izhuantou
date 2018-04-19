package com.izhuantou.service.impl.personalCenter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ChangeCard2ReqData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.QueryBalanceReqData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.data.RegReqData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.Wy500012ReqData;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.tool.ToolClient;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.loan.LoanDistribeTask;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.p2p.P2pHuiYuanUser;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.pay.PayChangeCard;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.pay.PayCustomerOperation;
import com.izhuantou.damain.pay.PaySeller;
import com.izhuantou.damain.pay.PayWithdrawalRecord;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.MyChangeDTO;
import com.izhuantou.damain.vo.PayCustomerDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;
import com.izhuantou.dao.loan.LoanDistribeTaskMapper;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.p2p.P2pHuiYuanUserMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.pay.PayChangeCardMapper;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayCustomerOperationMapper;
import com.izhuantou.dao.pay.PaySellerMapper;
import com.izhuantou.dao.pay.PayWithdrawalRecordMapper;
import com.izhuantou.dao.personalCenter.MyCashMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.personalCenter.MyCashService;
import com.izhuantou.service.impl.BaseServiceImpl;
import com.izhuantou.service.impl.user.ReadPropertiesl;

/**
 * 我的消息投实现类
 * 
 * @author yangbosen
 *
 */
@Service("myCashService")
@Transactional
public class MyCashServiceImpl extends BaseServiceImpl<PayCustomerBusiness> implements MyCashService {
    private static final Logger logger = LoggerFactory.getLogger(MyCashServiceImpl.class);
    private static final UtilsMD5 md5 = new UtilsMD5();
    @Autowired
    private MyCashMapper myCashMapper;

    @Autowired
    private PayChangeCardMapper payChangeCardDao;

    @Autowired
    private PayCustomerOperationMapper payCustomerOperationDao;

    @Autowired
    private PayCustomerMapper PayCustomerMapper;

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private PaySellerMapper paySellerMapper;

    @Autowired
    private P2pHuiYuanUserMapper p2pHuiYuanUserDao;

    @Autowired
    private LoanDistribeTaskMapper loanDistribeTaskMapper;

    @Autowired
    private PayCustomerBusinessMapper customerBusinessMapper;

    @Autowired
    private PayWithdrawalRecordMapper PayWithdrawalRecordMapper;

    @Autowired
    private MessageContentBusinessMapper messageContentBusinessMapper;

    @Autowired
    private MessageSmsHistoryMapper messageSmsHistoryMapper;
    @Autowired
    private PayCashPoolOperationMapper paycashPoolOperationMapper;

    /**
     * 资金流水列表
     */
    @Override
    public Pagination<PayCustomerBusiness> findMessage(Integer page, String memberOID, String strDate, String endDate) {
	List<PayCustomerBusiness> list = new ArrayList<>();
	Pagination<PayCustomerBusiness> pageController = new Pagination<>();
	try {
	    /** 接受前台当前页参数 */
	    pageController.setCurrentPage(page);
	    if (strDate != null && strDate != "" && !strDate.equals("") && endDate != null && endDate != ""
		    && !endDate.equals("")) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(strDate);
		Date date2 = sdf.parse(endDate);

		Integer totalNumber = (int) myCashMapper.findcountByCondition(memberOID, date1, date2);
		pageController.setTotalNumber(totalNumber);
	    } else {
		if (strDate == null || strDate == "" || strDate.equals("")) {
		    strDate = "1999-01-01";
		}
		if (endDate == null || endDate == "" || endDate.equals("")) {
		    endDate = "3000-01-01";
		}
		Integer totalNumber = (int) myCashMapper.findcount(memberOID);
		pageController.setTotalNumber(totalNumber);

	    }

	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		if (!StringUtils.isBlank(strDate) && !StringUtils.isBlank(endDate)) {
		    if (strDate == null || strDate == "" || strDate.equals("")) {
			strDate = "1999-01-01";
		    }
		    if (endDate == null || endDate == "" || endDate.equals("")) {
			endDate = "3000-01-01";
		    }
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date date1 = sdf.parse(strDate);
		    Date date2 = sdf.parse(endDate);

		    list = myCashMapper.findCashDetialByCondition(memberOID, StartPos, pageController.getPageSize(),
			    date1, date2);
		    for (PayCustomerBusiness pay_CustomerBusiness : list) {
			// Date s = pay_CustomerBusiness.getAddDateTime();
			if (pay_CustomerBusiness.getMoney() == null) {
			    pay_CustomerBusiness.setMoney(new BigDecimal("0"));
			}
			pay_CustomerBusiness.setSj(sdf.format(pay_CustomerBusiness.getAddDateTime()));

		    }
		} else {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    list = myCashMapper.findCashDetial(memberOID, StartPos, pageController.getPageSize());
		    for (PayCustomerBusiness pay_CustomerBusiness : list) {
			pay_CustomerBusiness.setSj(sdf.format(pay_CustomerBusiness.getAddDateTime()));
			if (pay_CustomerBusiness.getMoney() == null) {
			    pay_CustomerBusiness.setMoney(new BigDecimal("0"));
			}
		    }
		}
	    } else {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		list = myCashMapper.findCashDetial(memberOID, 0, 10);
		for (PayCustomerBusiness pay_CustomerBusiness : list) {
		    pay_CustomerBusiness.setSj(sdf.format(pay_CustomerBusiness.getAddDateTime()));
		    if (pay_CustomerBusiness.getMoney() == null) {
			pay_CustomerBusiness.setMoney(new BigDecimal("0"));
		    }

		}
	    }
	    pageController.setData(list);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findMessage()", e.getMessage());
	    return null;
	}
	return pageController;
    }

    /**
     * 查银行卡的信息变更列表
     */
    @Override
    public Map<String, Object> findMyBanckCard(String memberOID, Integer currentPage) {
	try {
	    if (StringUtil.isEmpty(memberOID)) {
		return null;
	    }
	    // 分页信息
	    Pagination<PayChangeCard> pageController = new Pagination<>();
	    /** 接受前台当前页参数 */
	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = this.payChangeCardDao.getRowCount(memberOID);
	    pageController.setTotalNumber(rows);
	    // 取得总页数，总页数=总记录数/总页数
	    Integer totalPage = pageController.getTotalPage();
	    // 设置每页显示5条
	    pageController.setPageSize(5);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    // 获取查询记录
	    List<PayChangeCard> change = payChangeCardDao.findChangeCardList(memberOID, startIndex, pageSize);

	    List<MyChangeDTO> changeCard = new ArrayList<MyChangeDTO>();
	    for (PayChangeCard pay : change) {
		MyChangeDTO dto = new MyChangeDTO();
		Date dte = DateUtils.getDate(pay.getAddDateTime().getTime(), "");
		String addDate = DateUtils.formatDate(dte.getTime(), "");
		dto.setAddDateTime(addDate);
		dto.setRequestStatus(pay.getRequestStatus());
		dto.setSuccessStatus(pay.getSuccessStatus());
		changeCard.add(dto);
	    }
	    // 用来返回卡的信息
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (changeCard.size() > 0) {
		map.put("changCardList", changeCard);
		map.put("changeCardInfo", "true");
	    } else {
		map.put("changeCardInfo", "false");
	    }
	    map.put("Pagination", pageController);
	    return map;

	} catch (Exception e) {
	    logger.error(" findMyBanckCard(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 银行卡信息
     */
    @Override
    public Map<String, Object> findMyBanckMessage(String memberOID) {
	// 用来返回卡的信息
	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    if (customer != null) {
		String bankNumber = customer.getBankNumber();
		// bankNumber = "尾号：" + bankNumber.substring(15, 19);
		int length = bankNumber.length();
		if (length == 16) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{8}(\\w{4})", "$1********$2");
		} else if (length == 17) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{9}(\\w{4})", "$1********$2");
		} else if (length == 18) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1********$2");
		} else if (length == 19) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{11}(\\w{4})", "$1********$2");
		} else if (length == 20) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{12}(\\w{4})", "$1********$2");
		} else if (length == 21) {
		    bankNumber = bankNumber.replaceAll("(\\d{4})\\d{13}(\\w{4})", "$1********$2");
		}
		map.put("bankNumber", bankNumber);
		map.put("nameCN", customer.getNameCN());
		map.put("bankCode", customer.getBankCode());
		map.put("isCreate", "true");
		return map;
	    } else {
		map.put("isCreate", "false");
		return map;
	    }
	} catch (Exception e) {
	    logger.error("findMyBanckMessage(String memberOID)", e.getMessage());
	    return null;
	}
    }

    /**
     * 查询银行卡充值记录充值或体现记录
     */
    @Override
    public Map<String, Object> findRechargeRecord(String memberOID, String state, Integer currentPage) {
	try {
	    if (StringUtil.isEmpty(memberOID)) {
		return null;
	    }
	    // 分页信息
	    Pagination<PayCustomerOperation> pageController = new Pagination<>();
	    // 返回分页信息和内容
	    Map<String, Object> map = new HashMap<String, Object>();

	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = payCustomerOperationDao.getRowCount(memberOID, state);
	    pageController.setTotalNumber(rows);
	    // 取得总页数，总页数=总记录数/总页数
	    Integer totalPage = pageController.getTotalPage();
	    // 设置每页显示12条
	    pageController.setPageSize(12);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    // 获取查询记录
	    List<PayCustomerOperation> select = payCustomerOperationDao.findRechargeRecord(memberOID, state, startIndex,
		    pageSize);
	    // 用于返回
	    List<PayCustomerDTO> dto = new ArrayList<PayCustomerDTO>();
	    for (PayCustomerOperation pco : select) {
		PayCustomerDTO payCustomer = new PayCustomerDTO();

		String addDate = DateUtils.formatDate(pco.getAddDateTime(), "");
		payCustomer.setAddDateTime(addDate);

		payCustomer.setContent(pco.getContent());

		payCustomer.setMoney(pco.getMoney());

		payCustomer.setRequestID(pco.getRequestID());

		dto.add(payCustomer);
	    }
	    map.put("Pagination", pageController);
	    map.put("RechargeList", dto);

	    return map;
	} catch (Exception e) {
	    logger.error("findRechargeRecord(String memberOID, Integer currentPage) ", e.getMessage());
	    return null;
	}
    }

    /**
     * 提现或充值页面数据
     */
    @Override
    public Map<String, Object> findMyCardmessage(String memberOID) {
	try {
	    PayCustomer payCustomer = PayCustomerMapper.findByMemberOID(memberOID);
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (payCustomer != null) {
		map.put("useMoney", payCustomer.getUseMoney());
		map.put("mobile", "*******" + payCustomer.getMobile().substring(7));
		String freemoney = payCustomer.getFreeline().toString();
		if (StringUtil.isNotEmpty(freemoney)) {
		    map.put("freeline", payCustomer.getFreeline());
		} else {
		    map.put("freeline", 0.00);
		}
	    }
	    return map;
	} catch (Exception e) {
	    logger.error("findMyCardmessage(String memberOID) ", e.getMessage());
	    return null;
	}

    }

    /**
     * 注册富友
     * 
     * @param customerDto
     * @return
     */
    @Override
    public String insertCustomer(CustomerDTO customerDto) {
	try {
	    PayCustomer payCustomer = new PayCustomer();

	    payCustomer.setBankNameCN(customerDto.getBanckName());
	    // 添加OID
	    String OID = StringUtil.getUUID();
	    payCustomer.setOID(OID);
	    // name
	    payCustomer.setName(customerDto.getPhone());
	    // memberOID
	    payCustomer.setMemberOID(customerDto.getMemberOID());

	    // 持卡人姓名
	    payCustomer.setNameCN(customerDto.getCardName());

	    // 身份证号
	    payCustomer.setCardNO(customerDto.getIdCard());

	    // 证件类型
	    payCustomer.setCardType("0");
	    // 绑卡银行银行编号
	    payCustomer.setBankCode(customerDto.getBindBank());

	    // 开户地址
	    payCustomer.setBankCityCode(customerDto.getCity());

	    // 银行卡号
	    payCustomer.setBankNumber(customerDto.getBankNumber());
	    // 手机号
	    String phone = customerDto.getPhone();
	    payCustomer.setMobile(phone);

	    // 支付密码 加密
	    String withdrawalsPassword = md5.Md5(customerDto.getWithdrawalsPwd());
	    payCustomer.setWithdrawalsPassword(withdrawalsPassword);

	    // 登录密码加密 默认是手机后6位
	    String loginPwd = phone.substring(5, 11);// 登录密码设为默认手机后6位
	    loginPwd = md5.Md5(loginPwd);
	    payCustomer.setLoginPassword(loginPwd);
	    /**
	     * 注册富友
	     */
	    String sellerName = ReadPropertiesl.gainPropertiesValue("defaultSeller", "Pay.properties");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    PaySeller py = new PaySeller();

	    RegReqData data = new RegReqData();

	    // data.setCapAcntNm("123");
	    // 商户代码 mchnt_cd
	    data.setMchnt_cd(paySeller.getID());
	    // 流水号 mchnt_txn_ssn
	    String strRequestID = StringUtil.getUUID().substring(2);
	    data.setMchnt_txn_ssn(strRequestID);
	    // 客户姓名 cust_nm
	    data.setCust_nm(payCustomer.getNameCN());
	    // 证件类型 certif_tp
	    data.setCertif_tp(payCustomer.getCardType());
	    // 身份证号码/证件 certif_id
	    data.setCertif_id(payCustomer.getCardNO());
	    // 手机号码 mobile_no
	    data.setMobile_no(payCustomer.getMobile());
	    // 邮箱地址 email
	    // data.setEmail("nnnn");//payCustomer.getEmail());
	    // 开户行地区代码 city_id
	    data.setCity_id(payCustomer.getBankCityCode());
	    // 开户行行别 parent_bank_id
	    data.setParent_bank_id(payCustomer.getBankCode());
	    // 开户行支行名称 bank_nm
	    data.setBank_nm(payCustomer.getBankNameCN());
	    // 帐号 capAcntNo
	    data.setCapAcntNo(payCustomer.getBankNumber());
	    // 提现密码 password
	    data.setPassword(payCustomer.getWithdrawalsPassword());
	    // 登录密码 lpassword
	    data.setLpassword(payCustomer.getLoginPassword());
	    // 备注 rem
	    data.setRem(payCustomer.getDescribe0());
	    // 签名信息 signature
	    // System.out.println(data.createSignValueForReg().toString());
	    // String fsjm =
	    // SecurityUtils.sign(data.createSignValueForReg().toString());
	    CommonRspData resultData = FuiouService.reg(data);
	    String strCode = resultData.getResp_code();
	    String result = "";
	    if (strCode.equals("0000")) {
		payCustomer.setSellerOID(paySeller.getOID());
		payCustomer.setRequestID(resultData.getMchnt_txn_ssn());

		payCustomer.setNoTransferMoney(new BigDecimal("0"));
		PayCustomer customeList = PayCustomerMapper.findPAYCustomerByNameAndOID(payCustomer.getName(),
			payCustomer.getOID());
		if (customeList != null) {
		    return "用户已开户";
		}
		int rows = PayCustomerMapper.insertCustomer(payCustomer);
		if (rows == 1) {
		    result = rows + "";
		}
	    }
	    String rowsresult = "";
	    if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
		// 调用member更新实名认证
		MemberMember member = userDao.findUserByOID(payCustomer.getMemberOID());
		member.setMobile(payCustomer.getMobile());
		member.setCity(payCustomer.getBankCityCode());
		member.setNameCN(payCustomer.getNameCN());
		member.setRealName(payCustomer.getNameCN());
		member.setIdCard(payCustomer.getCardNO());
		/*
		 * 会员表中内容为写
		 */
		if (StringUtil.isNotEmpty(member.getIdCard())) {
		    P2pHuiYuanUser huiyuanUser = p2pHuiYuanUserDao.findHuiYuanUserByCard(member.getIdCard());
		    if (huiyuanUser != null) {
			huiyuanUser.setCard(member.getIdCard());
			huiyuanUser.setPhone(member.getMobile());
			huiyuanUser.setName(member.getNameCN());
			huiyuanUser.setKehulaiyuan("0");
			huiyuanUser.setKehutype("1");
			LoanDistribeTask task = loanDistribeTaskMapper
				.gainLoanDistribeTaskByRoleName("d64522530a5dca7106d4e8d0d5b0aab9");
			Integer taskNum = Integer.parseInt(task.getTasknum()) + 1;
			task.setTasknum(taskNum + "");
			task.setVersion(taskNum);
			loanDistribeTaskMapper.updateTaskNum(task);
			String kefuuserOID = task.getNndOID();
			huiyuanUser.setKefuuserOID(kefuuserOID);
			// version进行比对用于解决并发
			// huiyuanUser.setVersion(huiyuanUser.getVersion());
			int rows = p2pHuiYuanUserDao.updateHuiYuanUserByOID(huiyuanUser);
			if (rows == 1) {
			    rowsresult = rows + "";
			}
		    } else {
			P2pHuiYuanUser huiUser = new P2pHuiYuanUser();
			String oid = StringUtil.getUUID();
			huiUser.setOID(oid);
			huiUser.setCard(member.getIdCard());
			huiUser.setPhone(member.getMobile());
			huiUser.setName(member.getNameCN());
			huiUser.setKehulaiyuan("0");
			huiUser.setKehutype("1");
			LoanDistribeTask task = loanDistribeTaskMapper
				.gainLoanDistribeTaskByRoleName("d64522530a5dca7106d4e8d0d5b0aab9");
			Integer taskNum = Integer.parseInt(task.getTasknum()) + 1;
			task.setTasknum(taskNum + "");
			task.setVersion(taskNum);
			loanDistribeTaskMapper.updateTaskNum(task);
			String kefuuserOID = task.getNndOID();
			huiUser.setKefuuserOID(kefuuserOID);
			int rows = p2pHuiYuanUserDao.saveHuiYuanUserByOID(huiUser);
			if (rows == 1) {
			    rowsresult = rows + "";
			}
		    }
		}
		if (StringUtil.isNotEmpty(rowsresult) && rowsresult.equals("1")) {
		    // 更新member中的信息

		    int rows = userDao.updateRealNameByOID(member);
		    if (rows == 1) {
			rowsresult = rows + "";
		    } else {
			return "Member实名失败";
		    }
		}
	    } else if (StringUtil.isEmpty(result)) {
		return "调用接口失败";
	    }
	    return rowsresult;
	} catch (Exception e) {
	    logger.error("insertCustomer(CustomerDTO customerDto) ", e.getMessage());
	    return null;
	}

    }

    /**
     * 网页用户充值
     */
    @Override
    public Map<String, Object> rechargeWY(RechargeDTO recharge) {
	try {
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    String sellerName = ReadPropertiesl.gainPropertiesValue("defaultSeller", "Pay.properties");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    String memberOID = recharge.getMemberOID();
	    BigDecimal fen = new BigDecimal(100);
	    BigDecimal money = recharge.getMoney();
	    BigDecimal fenMoney = money.multiply(fen);
	    String bankNO = recharge.getCard_id();
	    String url = recharge.getUrl();

	    Wy500012ReqData data = new Wy500012ReqData();

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
	    // 充值金额
	    data.setAmt(String.valueOf(fenMoney.intValue()));
	    data.setOrder_pay_type("B2C");
	    // 银行卡
	    data.setIss_ins_cd(bankNO);
	    Map<String, String> pMap = ReadPropertiesl.gainPropertiesMap("Pay.properties");
	    // 商户返回地址
	    if (StringUtil.isEmpty(url)) {
		/*
		 * 商户返回地址 商户接收交易结果通知地址
		 */
		url = pMap.get("rechargeForwardURL");

	    }
	    data.setPage_notify_url(url);
	    // 商户接收后台结果通知地址
	    data.setBack_notify_url(pMap.get("rechargeCallbackURL"));
	    String fsjm = SecurityUtils.sign(data.createSignValue());
	    resultmap.put("data", data);
	    return resultmap;
	} catch (Exception e) {
	    logger.error("rechargeWY(RechargeDTO recharge) ", e.getMessage());
	    return null;
	}

    }

    /**
     * 账户充值(跳转网银界面充值)快捷
     */
    @Override
    public Map<String, Object> recharge(RechargeDTO recharge) {
	try {
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    String sellerName = ReadPropertiesl.gainPropertiesValue("defaultSeller", "Pay.properties");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    String memberOID = recharge.getMemberOID();
	    BigDecimal fen = new BigDecimal(100);
	    BigDecimal money = recharge.getMoney();
	    BigDecimal fenMoney = money.multiply(fen);
	    String bankNO = recharge.getCard_id();
	    String url = recharge.getUrl();

	    AppTransReqData data = new AppTransReqData();

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
	    // 充值金额
	    data.setAmt(String.valueOf(fenMoney.intValue()));
	    Map<String, String> pMap = ReadPropertiesl.gainPropertiesMap("Pay.properties");
	    // 商户返回地址
	    if (StringUtil.isEmpty(url)) {
		url = pMap.get("rechargeForwardURL");
	    }
	    data.setPage_notify_url(url);
	    data.setBack_notify_url(pMap.get("rechargeCallbackURL"));
	    String fsjm = SecurityUtils.sign(data.createSignValue());
	    resultmap.put("data", data);
	    return resultmap;
	} catch (Exception e) {
	    logger.error("recharge(RechargeDTO recharge) ", e.getMessage());
	    return null;
	}
    }

    /**
     * 充值完成返回的结果写入数据库
     * 
     * @return
     */
    public String rechargeFinish(Map<String, String> map) {
	try {
	    String resp_code = map.get("resp_code");
	    String login_id = map.get("login_id");
	    String amt = map.get("amt");
	    String requestID = map.get("mchnt_txn_ssn");
	    String describe = map.get("rem");
	    String ly = map.get("ly");
	    if ("0000".equals(resp_code)) {
		PayCustomer customer = PayCustomerMapper.findPAYCustomerByName(login_id);
		// 充值金额
		BigDecimal fenMoney = new BigDecimal(amt);
		BigDecimal fen = new BigDecimal(100);
		BigDecimal money = fenMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);

		PayCustomerOperation operation = new PayCustomerOperation();
		// OID
		String OID = StringUtil.getUUID();
		operation.setOID(OID);
		// 资金账户OID
		operation.setCustomerOID(customer.getOID());
		// 用户memeberOID
		operation.setMemberOID(customer.getMemberOID());

		operation.setContent("充值完成");
		// 金额
		operation.setMoney(money);
		// 类型（充值还是提现）
		operation.setState("recharge");
		// 流水号
		operation.setRequestID(requestID);
		// 备注
		operation.setDescribe0(describe);
		// 来源
		operation.setLaiyuan(ly);
		// 插入一条充值记录
		int rows = payCustomerOperationDao.insertRechargeRecord(operation);
		String resultbrows = "";
		if (rows == 1) {
		    PayCustomerBusiness business = new PayCustomerBusiness();
		    // OID
		    String bOID = StringUtil.getUUID();
		    business.setOID(bOID);
		    business.setCustomerOID(customer.getOID());
		    business.setMemberOID(customer.getMemberOID());
		    business.setContent("用户发起充值时的到账金额");
		    business.setType("充值");
		    business.setState("已完成");
		    business.setDescribe0(describe);
		    int brows = customerBusinessMapper.insertCustomerBussiness(business);
		    if (brows == 1) {
			resultbrows = brows + "";
		    }
		}

		return resultbrows;
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("rechargeFinish(Map<String,String> map)", e.getMessage());
	    return null;
	}

    }

    /**
     * 通过memberOID改变数据库中的资金表
     * 
     * @param data
     * @return
     */
    public String updateCustomerMoney(String phnoe) {
	try {
	    PayCustomer customer = PayCustomerMapper.findPAYCustomerByName(phnoe);

	    String sellerName = ReadPropertiesl.gainPropertiesValue("defaultSeller", "Pay.properties");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);
	    QueryBalanceReqData data = new QueryBalanceReqData();
	    // 商户代码
	    data.setMchnt_cd(paySeller.getID());
	    // 流水号
	    String strRequestID = StringUtil.getUUID().substring(2);
	    data.setMchnt_txn_ssn(strRequestID);
	    // 交易日期
	    data.setMchnt_txn_dt(DateUtils.formatJustDate(new Date().getTime()));
	    data.setCust_no(customer.getName());
	    QueryBalanceRspData resultData = FuiouService.balanceAction(data);

	    // 账户余额
	    BigDecimal feeMoney = new BigDecimal(resultData.getResults().get(0).getCt_balance());
	    BigDecimal fen = new BigDecimal(100);
	    BigDecimal money = feeMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);
	    customer.setMoney(money);
	    // 冻结余额
	    feeMoney = new BigDecimal(resultData.getResults().get(0).getCf_balance());
	    money = feeMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);
	    customer.setFrozenMoney(money);
	    // 可用余额
	    feeMoney = new BigDecimal(resultData.getResults().get(0).getCa_balance());
	    money = feeMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);
	    customer.setUseMoney(money);
	    // 未转结余额
	    feeMoney = new BigDecimal(resultData.getResults().get(0).getCu_balance());
	    money = feeMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);
	    customer.setNoTransferMoney(money);
	    String resultrows = "";

	    int rowss = PayCustomerMapper.updataCutomer(customer);

	    // 比对版本号
	    Integer version = customer.getVersion();
	    customer.setVersion(version);
	    if (rowss == 1) {
		resultrows = rowss + "";
	    }
	    return resultrows;
	} catch (Exception e) {
	    logger.error("updateCustomerMoney(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 提现
     */
    @Override
    public Map<String, Object> withdrawal(WithdrawalDTO withdrawal) {
	try {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    String memberOID = withdrawal.getMemberOID();
	    BigDecimal money = withdrawal.getMoney();
	    String url = withdrawal.getUrl();
	    if (StringUtil.isEmpty(memberOID)) {
		resultMap.put("message", "membeerOID不能为空");
		return resultMap;
	    }
	    String txfl = ReadPropertiesl.gainPropertiesValue("txfl", "privilege.properties");
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    BigDecimal useMoney = customer.getUseMoney();
	    // 提现金额小于可用余额
	    if (money.compareTo(useMoney) <= 0) {
		// 免费额度
		BigDecimal mfed = new BigDecimal("0");
		// 判断用户是否有免费额度 terry
		if (null != customer.getFreeline()) {
		    mfed = mfed.add(customer.getFreeline());
		}
		// 提现金额
		BigDecimal txje = money;
		// 提现金额减去可用免费额度获取差额
		BigDecimal ce = txje.subtract(mfed);
		BigDecimal freeline = new BigDecimal("0");
		// 提现手续费
		BigDecimal sxf = new BigDecimal("0");

		if (ce.compareTo(new BigDecimal("0")) > 0) {
		    sxf = sxf.add(ce.multiply(new BigDecimal(txfl))).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		    if (sxf.compareTo(new BigDecimal("0")) == 0) {
			sxf = new BigDecimal("0.01");
		    }
		}
		// 替换用户可用额度
		if (ce.compareTo(new BigDecimal("0")) <= 0) {
		    freeline = ce.abs();
		}
		String strRequestID = StringUtil.getUUID().substring(2);
		PayWithdrawalRecord withdrawalRecord = new PayWithdrawalRecord();
		String oid = StringUtil.getUUID();
		withdrawalRecord.setMemberOID(memberOID);
		withdrawalRecord.setOID(oid);
		withdrawalRecord.setSxf(sxf);
		withdrawalRecord.setMfed(freeline);
		withdrawalRecord.setMfedbefor(mfed);
		withdrawalRecord.setMoney(money);
		withdrawalRecord.setStatus("0");
		withdrawalRecord.setRequestID(strRequestID);
		int rows = PayWithdrawalRecordMapper.saveWithdrawalrecord(withdrawalRecord);
		if (rows == 1) {
		    Map<String, String> propMap = ReadPropertiesl.gainPropertiesMap("Pay.properties");
		    String sellerName = propMap.get("defaultSeller");
		    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    AppTransReqData data = new AppTransReqData();
		    // 商户代码
		    data.setMchnt_cd(paySeller.getID());
		    // 流水号
		    data.setMchnt_txn_ssn(strRequestID);
		    // 用户登录名
		    data.setLogin_id(customer.getName());
		    // 提现金额
		    data.setAmt(String.valueOf(fenMoney.intValue()));

		    // 商户返回地址
		    if (StringUtil.isEmpty(url)) {
			url = propMap.get("withdrawalsForwardURL");
		    }
		    data.setPage_notify_url(url);
		    // 商户后台通知地址
		    data.setBack_notify_url(propMap.get("withdrawalsCallbackURL"));
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    resultMap.put("message", "1");
		    resultMap.put("data", data);
		} else {
		    resultMap.put("message", "提现失败");
		}
		return resultMap;
	    } else {
		resultMap.put("message", "可用余额不足");
		return resultMap;
	    }
	} catch (Exception e) {
	    logger.error("withdrawal(WithdrawalDTO withdrawal) ", e.getMessage());
	    return null;
	}

    }

    /**
     * 提现返回
     */
    @Override
    public String withdrawalsFinish(Map<String, String> map) {
	try {
	    String resp_code = map.get("resp_code");
	    String login_id = map.get("login_id");
	    String amt = map.get("amt");
	    String requestID = map.get("mchnt_txn_ssn");
	    String describe = map.get("remark");
	    String ly = map.get("ly");
	    if ("0000".equals(resp_code)) {
		PayCustomer customer = PayCustomerMapper.findPAYCustomerByName(login_id);
		// 充值金额
		BigDecimal fenMoney = new BigDecimal(amt);
		BigDecimal fen = new BigDecimal(100);
		BigDecimal money = fenMoney.divide(fen, 2, BigDecimal.ROUND_HALF_EVEN);
		PayCustomerOperation operation = new PayCustomerOperation();
		// OID
		String OID = StringUtil.getUUID();
		operation.setOID(OID);
		// 资金账户OID
		operation.setCustomerOID(customer.getOID());
		// 用户memeberOID
		operation.setMemberOID(customer.getMemberOID());

		operation.setContent("提现完成");
		// 金额
		operation.setMoney(money);
		// 类型（充值还是提现）
		operation.setState("withdrawals");
		// 流水号
		operation.setRequestID(requestID);
		// 备注
		operation.setDescribe0(describe);
		// 来源
		operation.setLaiyuan(ly);
		// 插入一条充值记录
		int rows = payCustomerOperationDao.insertRechargeRecord(operation);
		String resultbrows = "";
		if (rows == 1) {
		    PayCustomerBusiness business = new PayCustomerBusiness();
		    // OID
		    String bOID = StringUtil.getUUID();
		    business.setOID(bOID);
		    business.setCustomerOID(customer.getOID());
		    business.setMemberOID(customer.getMemberOID());
		    business.setContent("用户发起提现时实际到账金额");
		    business.setType("提现");
		    business.setState("已完成");
		    business.setDescribe0(describe);
		    business.setMoney(money);
		    int brows = customerBusinessMapper.insertCustomerBussiness(business);
		    if (brows == 1) {
			MessageContentBusiness messageBusiness = new MessageContentBusiness();
			String mOID = StringUtil.getUUID();
			messageBusiness.setOID(mOID);
			messageBusiness.setTitle("提现");
			String content = "您的提现实际到账金额为" + money + "元";
			messageBusiness.setContent(content);
			messageBusiness.setSendUser("系统");
			messageBusiness.setReceiveUserOID(customer.getMemberOID());

			int row = messageContentBusinessMapper.saveMessageBusiness(messageBusiness);
			if (row == 1) {
			    Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
			    String userName = smsMap.get("account");
			    String password = smsMap.get("password");

			    MessageSmsHistory smsHistory = new MessageSmsHistory();
			    String sOID = StringUtil.getUUID();
			    smsHistory.setOID(sOID);
			    smsHistory.setTitle("提现");
			    String scontent = "【砖头网】您的提现实际到账金额为" + money + "元。如果该操作不是您的操作，请与管理员联系。";
			    smsHistory.setContent(scontent);
			    smsHistory.setSendUser("系统");
			    smsHistory.setReceiveUser("测试");
			    smsHistory.setReceiveUser(customer.getName());
			    Map<String, String> result = ToolClient.sendSMS(customer.getName(), userName, password,
				    scontent, null);
			    String state = result.get("Description");
			    smsHistory.setState(state);
			    int rowa = messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
			    resultbrows = rows + "";
			}
		    }
		}
		return resultbrows;
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("withdrawalsFinish(Map<String, String> map)", e.getMessage());
	    return null;
	}
    }

    /**
     * 提现回调 手续费
     */
    @Override
    public String withdrawalsSxfFinish(Map<String, String> map) {
	try {
	    String login_id = map.get("login_id");
	    String requestID = map.get("mchnt_txn_ssn");
	    String ly = map.get("ly");
	    PayCustomer customer = PayCustomerMapper.findPAYCustomerByName(login_id);
	    PayWithdrawalRecord payWithdrawalRecord = PayWithdrawalRecordMapper.findWithdeawalByRequestID(requestID);
	    BigDecimal sxf = payWithdrawalRecord.getSxf();
	    String resultStr = "";
	    if (sxf.compareTo(new BigDecimal("0")) > 0) {
		// oid
		String oid = StringUtil.getUUID();
		String businessOID = requestID;
		String outMemberOID = customer.getMemberOID();
		String inMemberOID = "manageAccount";
		BigDecimal money = sxf;
		String content = "缴纳提现手续费";
		PayCashPoolOperation operation = new PayCashPoolOperation();
		operation.setOID(oid);
		operation.setBusinessOID(businessOID);
		operation.setOutMemberOID(outMemberOID);
		operation.setInMemberOID(inMemberOID);
		operation.setContent(content);
		operation.setMoney(money);
		operation.setLaiyuan(ly);
		int rows = paycashPoolOperationMapper.saveCashPoolOperation(operation);
		if (rows == 1) {
		    PayCustomerBusiness business = new PayCustomerBusiness();
		    // OID
		    String bOID = StringUtil.getUUID();
		    business.setOID(bOID);
		    business.setCustomerOID(customer.getOID());
		    business.setMemberOID(customer.getMemberOID());
		    business.setContent("用户发起提现时手续费金额");
		    business.setType("提现手续费");
		    business.setState("已完成");
		    business.setMoney(money);
		    int brows = customerBusinessMapper.insertCustomerBussiness(business);
		    if (brows == 1) {
			resultStr = brows + "";
		    }
		}
	    }
	    return resultStr;
	} catch (Exception e) {
	    logger.error(" withdrawalsSxfFinish(Map<String, String> map)", e.getMessage());
	    return null;
	}

    }

    /**
     * 修改提现密码
     */
    @Override
    public Map<String, Object> updatePayPassword(String memberOID) {
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
	    data.setBack_url(pay.get("updatePayPasswordForwardURL"));

	    String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
	    resultmap.put("data", data);

	    return resultmap;
	} catch (Exception e) {
	    logger.error("updatePayPassword(String memberOID) ", e.getMessage());
	    return null;
	}
    }

    /**
     * 申请换卡
     */
    @Override
    public Map<String, Object> changeBankCard(String memberOID) {
	try {
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    // 用户的资金账户
	    PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
	    if (customer != null) {
		// id
		String OID = StringUtil.getUUID();
		// 流水号
		String strRequestID = StringUtil.getUUID();
		PayChangeCard change = new PayChangeCard();
		change.setOID(OID);
		change.setMemberOID(memberOID);
		change.setBankCodeOld(customer.getBankCode());
		change.setBankNumOld(customer.getBankNumber());
		change.setRequestStatus("0000");
		change.setSuccessStatus("0000");
		change.setMobile(customer.getName());
		change.setRequestID(strRequestID);
		int row = payChangeCardDao.saveChangeCardnotes(change);
		if (row == 1) {
		    Map<String, String> pay = ReadPropertiesl.gainPropertiesMap("Pay.properties");
		    String sellerName = pay.get("defaultSeller");
		    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);
		    ChangeCard2ReqData data = new ChangeCard2ReqData();
		    // 商户代码
		    data.setMchnt_cd(paySeller.getID());
		    data.setMchnt_txn_ssn(strRequestID);
		    // 用户登录名
		    data.setLogin_id(customer.getName());
		    // 商户返回地址
		    data.setPage_notify_url(pay.get("changeCardCallbackURL"));
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    resultmap.put("data", data);
		    resultmap.put("message", "1");
		}
		return resultmap;
	    }
	    resultmap.put("message", "没有开通资金账户");
	    return resultmap;
	} catch (Exception e) {
	    logger.error("changeBankCard(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 换卡回调
     */
    @Override
    public String changeBankCardReturn(Map<String, String> map) {
	try {
	    String login_id = map.get("login_id");
	    String requestID = map.get("mchnt_txn_ssn");
	    String resp_code = map.get("resp_code");
	    PayChangeCard change = payChangeCardDao.findChangeByRequestID(requestID);
	    if ("0000".equals(resp_code)) {
		change.setRequestStatus("1111");
		int row = payChangeCardDao.updataChangeCardStart(change);
		if (row == 1) {
		    return "1";
		}
	    } else {
		change.setRequestStatus("2222");
		int row = payChangeCardDao.updataChangeCardStart(change);
		if (row == 1) {
		    return "0";
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("changeBankCardReturn(Map<String, String> map)", e.getMessage());
	    return null;
	}

    }

    /**
     * 授权
     */
    @Override
    public Map<String, Object> authorization(String memberOID) {
	try {
	    if (StringUtil.isEmpty(memberOID)) {
		return null;
	    }
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    Map<String, String> pay = ReadPropertiesl.gainPropertiesMap("Pay.properties");
	    String sellerName = pay.get("defaultSeller");
	    PaySeller paySeller = paySellerMapper.gainSellerByName(sellerName);

	    AuthorizationReqData data = new AuthorizationReqData();
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
	    data.setBusi_tp("2");
	    // 商户返回地址
	    data.setPage_notify_url(pay.get("authorizationCallbackURL"));

	    String fsjm = SecurityUtils.sign(data.createSignValue());
	    resultmap.put("data", data);
	    return resultmap;
	} catch (Exception e) {
	    logger.error("authorization(String memberOID)", e.getMessage());
	    return null;
	}

    }

    /**
     * 用户授权回调
     */
    @Override
    public String authorizationCallback(Map<String, String> map) {
	// 富友返回状态码
	String resp_code = map.get("resp_code");
	// 会员账户
	String login_id = map.get("login_id");
	// 流水号
	String requestID = map.get("mchnt_txn_ssn");
	// 委托充值
	String entrustRecharge = map.get("wtCzParam");
	// 委托提现
	String entrustWithdrawals = map.get("wtTxparam");
	try {
	    if ("0000".equals(resp_code)) {
		// 资金账户信息
		PayCustomer customer = PayCustomerMapper.findPAYCustomerByName(login_id);

		PayCustomerOperation operation = new PayCustomerOperation();
		// OID
		String OID = StringUtil.getUUID();
		operation.setOID(OID);
		// 资金账户OID
		operation.setCustomerOID(customer.getOID());
		// 用户memeberOID
		operation.setMemberOID(customer.getMemberOID());

		operation.setContent("授权完成");
		// 类型（充值还是提现）
		operation.setState("authorization");
		// 流水号
		operation.setRequestID(requestID);
		// 交易记录
		int rows = payCustomerOperationDao.insertRechargeRecord(operation);
		if (rows == 1) {
		    customer.setEntrustRecharge(entrustRecharge);
		    customer.setEntrustWithdrawals(entrustWithdrawals);
		    int srow = PayCustomerMapper.updataCutomer(customer);
		    if (srow == 1) {
			return String.valueOf(srow);
		    }
		}
		return "数据更新失败";
	    } else {
		return "接口调用失败";
	    }
	} catch (Exception e) {
	    logger.error("authorizationCallback(Map<String, String> map)", e.getMessage());
	    return null;
	}

    }

}
