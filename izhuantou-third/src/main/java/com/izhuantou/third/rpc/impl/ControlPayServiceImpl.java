package com.izhuantou.third.rpc.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ChangeCard2ReqData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.QueryBalanceResultData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.data.QueryChangeCardRspData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.UnFreezeRspData;
import com.fuiou.data.Wy500012ReqData;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.common.tool.ToolClient;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.code.CodeBankInfo;
import com.izhuantou.damain.loan.LoanDistribeTask;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.p2p.P2pHuiYuanUser;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.pay.PayChangeCard;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.pay.PayCustomerOperation;
import com.izhuantou.damain.pay.PayWithdrawalRecord;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.CustomerDTO;
import com.izhuantou.damain.vo.FuyuReturnDTO;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;
import com.izhuantou.dao.code.CodeBankInfoMapper;
import com.izhuantou.dao.loan.LoanDistribeTaskMapper;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.p2p.P2pHuiYuanUserMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.pay.PayChangeCardMapper;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayCustomerOperationMapper;
import com.izhuantou.dao.pay.PayWithdrawalRecordMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.PayFuiouService;

@Service("controlPayService")
public class ControlPayServiceImpl implements ControlPayService {
    private static final Logger logger = LoggerFactory.getLogger(ControlPayServiceImpl.class);
    private static final UtilsMD5 md5 = new UtilsMD5();
    @Value("${txfl}")
    private String txfl;
    @Autowired
    private PayCustomerOperationMapper payCustomerOperationMapper;

    @Autowired
    private PayCustomerMapper payCustomerMapper;

    @Autowired
    private PayChangeCardMapper payChangeCardMapper;

    @Autowired
    private MemberMemberMapper userDao;

    @Autowired
    private P2pHuiYuanUserMapper p2pHuiYuanUserDao;

    @Autowired
    private LoanDistribeTaskMapper loanDistribeTaskMapper;

    @Autowired
    private PayCustomerBusinessMapper customerBusinessMapper;

    @Autowired
    private MessageContentBusinessMapper messageContentBusinessMapper;

    @Autowired
    private MessageSmsHistoryMapper messageSmsHistoryMapper;

    @Autowired
    private PayWithdrawalRecordMapper payWithdrawalRecordMapper;

    @Autowired
    private CodeBankInfoMapper codeBankInfoMapper;
    @Autowired
    private PayCashPoolOperationMapper payCashPoolOperationMapper;

    @Autowired
    private PayFuiouService payFuiService;

    @Override
    public Map<String, Object> changeCard(String memberOID) {
	try {
	    Map<String, Object> resultmap = new HashMap<String, Object>();
	    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	    if (customer != null) {
		List<PayChangeCard> changeList = payChangeCardMapper.findChangeByRequestAndSuccess(memberOID, "0000",
			"0000");
		if (changeList.size() > 0) {
		    String strRequestID = StringUtil.getUUID().substring(2);
		    for (PayChangeCard card : changeList) {
			card.setRequestID(strRequestID);
			payChangeCardMapper.updataChangeCard(card);
		    }
		    ChangeCard2ReqData data = payFuiService.changeCard(memberOID, strRequestID);
		    if (data != null) {
			resultmap.put("data", data);
			resultmap.put("message", "1");
		    } else {
			resultmap.put("message", "接口调用失败");
		    }
		} else {
		    PayChangeCard change = new PayChangeCard();
		    String strRequestID = StringUtil.getUUID().substring(2);
		    String OID = StringUtil.getUUID();
		    change.setOID(OID);
		    change.setMemberOID(memberOID);
		    change.setBankCodeOld(customer.getBankCode());
		    change.setBankNumOld(customer.getBankNumber());
		    change.setRequestStatus("0000");
		    change.setSuccessStatus("0000");
		    change.setMobile(customer.getName());
		    change.setRequestID(strRequestID);
		    payChangeCardMapper.saveChangeCardnotes(change);
		    ChangeCard2ReqData data = payFuiService.changeCard(memberOID, strRequestID);
		    if (data != null) {
			resultmap.put("data", data);
			resultmap.put("message", "1");
		    } else {
			resultmap.put("message", "接口调用失败");
		    }
		}
		return resultmap;
	    }
	    resultmap.put("message", "尚未开通资金账户");
	    return resultmap;

	} catch (Exception e) {
	    logger.error("public  Map<String, Object> changeCard(String memberOID)", e.getMessage());
	    return null;
	}

    }

    @Override
    public String changeBankCardReturn(Map<String, String> map) {
	try {
	    String requestID = map.get("mchnt_txn_ssn");
	    String resp_code = map.get("resp_code");
	    PayChangeCard change = payChangeCardMapper.findChangeByRequestID(requestID);
	    if ("0000".equals(resp_code)) {
		change.setRequestStatus("1111");
		payChangeCardMapper.updataChangeCardStart(change);
		return "1";
	    } else {
		change.setRequestStatus("2222");
		payChangeCardMapper.updataChangeCardStart(change);
		return FuiouErrorCode.errorCode(resp_code);
	    }
	} catch (Exception e) {
	    logger.error("public String changeBankCardReturn(Map<String, String> map)", e.getMessage());
	    return null;
	}

    }

    @Override
    public String createCustomerAccount(CustomerDTO customerDto) {
	try {
	    String memberOID = customerDto.getMemberOID();
	    String name = customerDto.getName();
	    if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(name)) {
		PayCustomer customer = payCustomerMapper.findPAYCustomerByNameAndOID(name, memberOID);
		if (customer != null) {
		    return "账户已实名认证";
		}
	    }
	    PayCustomer payCustomer = new PayCustomer();

	    payCustomer.setBankNameCN(customerDto.getBanckName());

	    payCustomer.setOID(StringUtil.getUUID());

	    payCustomer.setName(customerDto.getPhone());

	    payCustomer.setMemberOID(customerDto.getMemberOID());

	    payCustomer.setNameCN(customerDto.getCardName());

	    payCustomer.setCardNO(customerDto.getIdCard());

	    payCustomer.setCardType("0");

	    payCustomer.setBankCode(customerDto.getBindBank());

	    payCustomer.setBankCityCode(customerDto.getCity());

	    payCustomer.setBankNumber(customerDto.getBankNumber());

	    String phone = customerDto.getPhone();
	    payCustomer.setMobile(phone);

	    String withdrawalsPassword = md5.Md5(customerDto.getWithdrawalsPwd());
	    payCustomer.setWithdrawalsPassword(withdrawalsPassword);

	    String loginPwd = phone.substring(5, 11);
	    loginPwd = md5.Md5(loginPwd);
	    payCustomer.setLoginPassword(loginPwd);
	    // 调用富友的接口
	    String result = this.payFuiService.createCustomerAccount(payCustomer);

	    String rowsresult = "";
	    if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
		MemberMember member = userDao.findUserByOID(payCustomer.getMemberOID());
		member.setMobile(payCustomer.getMobile());
		member.setCity(payCustomer.getBankCityCode());
		member.setNameCN(payCustomer.getNameCN());
		member.setRealName(payCustomer.getNameCN());
		member.setIdCard(payCustomer.getCardNO());
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

			p2pHuiYuanUserDao.updateHuiYuanUserByOID(huiyuanUser);

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
			p2pHuiYuanUserDao.saveHuiYuanUserByOID(huiUser);
		    }
		}
		userDao.updateRealNameByOID(member);
		rowsresult = "1";

	    } else if (StringUtil.isEmpty(result)) {
		return "调用接口失败";
	    } else {
		return result;
	    }
	    return rowsresult;
	} catch (Exception e) {
	    logger.error("public String createCustomerAccount(CustomerDTO customerDto) ", e.getMessage());
	    return null;
	}
    }

    @Override
    public String authorizationFinish(FuyuReturnDTO result) {
	// 富友返回状态码
	String resp_code = result.getResp_code();
	// 会员账户
	String login_id = result.getLogin_id();
	// 流水号
	String requestID = result.getMchnt_txn_ssn();
	// 委托充值
	String entrustRecharge = result.getWtCzParam();
	// 委托提现
	String entrustWithdrawals = result.getWtTxparam();
	try {
	    if ("0000".equals(resp_code)) {
		// 资金账户信息
		PayCustomer customer = payCustomerMapper.findPAYCustomerByName(login_id);

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
		payCustomerOperationMapper.insertRechargeRecord(operation);

		customer.setEntrustRecharge(entrustRecharge);
		customer.setEntrustWithdrawals(entrustWithdrawals);
		payCustomerMapper.updataCutomer(customer);
		return "1";
	    } else {
		return FuiouErrorCode.errorCode(resp_code);
	    }
	} catch (Exception e) {
	    logger.error("authorizationCallback(Map<String, String> map)", e.getMessage());
	    return null;
	}

    }

    @Override
    public String rechargeFinish(Map<String, String> map) {
	try {
	    String resp_code = map.get("resp_code");
	    String login_id = map.get("login_id");
	    String amt = map.get("amt");
	    String requestID = map.get("mchnt_txn_ssn");
	    String describe = map.get("rem");
	    String ly = map.get("ly");
	    if ("0000".equals(resp_code)) {
		PayCustomer customer = payCustomerMapper.findPAYCustomerByName(login_id);
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
		payCustomerOperationMapper.insertRechargeRecord(operation);
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
		customerBusinessMapper.insertCustomerBussiness(business);
		this.updateAccount(customer.getMemberOID());
		return "1";
	    } else {
		return FuiouErrorCode.errorCode(resp_code);
	    }
	} catch (Exception e) {
	    logger.error("rechargeFinish(Map<String,String> map)", e.getMessage());
	    return null;
	}

    }

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
		PayCustomer customer = payCustomerMapper.findPAYCustomerByName(login_id);
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
		payCustomerOperationMapper.insertRechargeRecord(operation);

		PayCustomerBusiness business = new PayCustomerBusiness();

		String bOID = StringUtil.getUUID();
		business.setOID(bOID);
		business.setCustomerOID(customer.getOID());
		business.setMemberOID(customer.getMemberOID());
		business.setContent("用户发起提现时实际到账金额");
		business.setType("提现");
		business.setState("已完成");
		business.setDescribe0(describe);
		business.setMoney(money);
		customerBusinessMapper.insertCustomerBussiness(business);

		MessageContentBusiness messageBusiness = new MessageContentBusiness();
		String mOID = StringUtil.getUUID();
		messageBusiness.setOID(mOID);
		messageBusiness.setTitle("提现");
		String content = "您的提现实际到账金额为" + money + "元";
		messageBusiness.setContent(content);
		messageBusiness.setSendUser("系统");
		messageBusiness.setReceiveUserOID(customer.getMemberOID());

		// TODO 发消息的工具应在数据层封装
		messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

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
		Map<String, String> result = ToolClient.sendSMS(customer.getName(), userName, password, scontent, null);
		String state = result.get("Description");
		smsHistory.setState(state);
		messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
		return "1";
	    } else {
		return FuiouErrorCode.errorCode(resp_code);
	    }
	} catch (Exception e) {
	    logger.error("withdrawalsFinish(Map<String, String> map)", e.getMessage());
	    return null;
	}
    }

    @Override
    public Map<String, Object> withdrawals(WithdrawalDTO withdrawal) {
	try {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    String memberOID = withdrawal.getMemberOID();
	    BigDecimal money = withdrawal.getMoney();
	    if (StringUtil.isEmpty(memberOID)) {
		resultMap.put("message", "membeerOID不能为空");
		return resultMap;
	    }
	    // this.updateAccount(memberOID);

	    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	    BigDecimal useMoney = customer.getUseMoney();
	    // 提现金额小于可用余额
	    if (money.compareTo(useMoney) <= 0) {

		BigDecimal mfed = new BigDecimal("0");
		// 判断用户是否有免费额度
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
		payWithdrawalRecordMapper.saveWithdrawalrecord(withdrawalRecord);
		withdrawal.setMemberOID(memberOID);
		withdrawal.setMoney(money.subtract(sxf));
		withdrawal.setRequestID(strRequestID);

		AppTransReqData data = this.payFuiService.withdrawals(withdrawal);
		if (data != null) {
		    resultMap.put("message", "1");
		    resultMap.put("data", data);
		} else {
		    resultMap.put("message", "接口调用失败");
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

    @Override
    public Map<String, Object> appWithdrawals(String memberOID, BigDecimal money) {
	try {
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    if (StringUtil.isEmpty(memberOID)) {
		resultMap.put("message", "membeerOID不能为空");
		return resultMap;
	    }
	    this.updateAccount(memberOID);
	    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
	    BigDecimal useMoney = customer.getUseMoney();
	    // 提现金额小于可用余额
	    if (money.compareTo(useMoney) <= 0) {
		// 免费额度
		BigDecimal mfed = new BigDecimal("0");
		// 判断用户是否有免费额度
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
		payWithdrawalRecordMapper.saveWithdrawalrecord(withdrawalRecord);
		WithdrawalDTO withdrawal = new WithdrawalDTO();

		withdrawal.setMemberOID(memberOID);
		withdrawal.setMoney(money.subtract(sxf));
		withdrawal.setRequestID(strRequestID);

		AppTransReqData data = this.payFuiService.appWithdrawals(withdrawal);
		if (data != null) {
		    resultMap.put("data", data);
		    resultMap.put("message", "1");
		} else {
		    resultMap.put("message", "接口调用失败");
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

    @Override
    public void queryChangeCard(String memberOID, String requestID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(requestID)) {

		QueryChangeCardRspData resultData = this.payFuiService.queryChangeCard(memberOID, requestID);
		if (resultData != null) {
		    String resp_code = resultData.getResp_code();
		    if ("0000".equals(resp_code)) {
			PayChangeCard changeCard = payChangeCardMapper.findChangeByRequestID(requestID);
			PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
			if ("1".equals(resultData.getExamine_st())) {
			    String sp[] = resultData.getBank_nm().split("银行");
			    String bankCode = "00";
			    if ("中国".equals(sp[0])) {
				bankCode = "0104";
			    } else {
				CodeBankInfo dtochang = codeBankInfoMapper.findByBankName(sp[0]);
				bankCode = dtochang.getBankCode();
			    }
			    changeCard.setBankCodeNew(bankCode);
			    changeCard.setSuccessStatus("1111");
			    changeCard.setBankNumNew(resultData.getCard_no());
			    changeCard.setRemark(resultData.getRemark());
			    payChangeCardMapper.updataChangeCard(changeCard);

			    customer.setBankNumber(resultData.getCard_no());
			    customer.setBankCode(bankCode);
			    payCustomerMapper.updataCutomer(customer);

			} else if ("2".equals(resultData.getExamine_st())) {
			    changeCard.setSuccessStatus("2222");
			    changeCard.setRemark(resultData.getRemark());
			    payChangeCardMapper.updataChangeCard(changeCard);
			}
		    } else {
			PayChangeCard changeCard = payChangeCardMapper.findChangeByRequestID(requestID);
			changeCard.setSuccessStatus("3333");
			changeCard.setRemark("操作中断");
			payChangeCardMapper.updataChangeCard(changeCard);
		    }
		} else {
		    logger.error("接口调用失败");
		}
	    }
	} catch (Exception e) {
	    logger.error("public void queryChangeCard(String memberOID, String requestID)", e.getMessage());
	}

    }

    @Override
    public String transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(strOutMemberOID) && StringUtil.isNotEmpty(strInMemberOID) && money != null) {
		CommonRspData resultData = this.payFuiService.transfer(strOutMemberOID, strInMemberOID, money);
		if (resultData != null) {
		    String strCode = resultData.getResp_code();
		    if (strCode.equals("0000")) {
			// 用户的资金账户
			PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(strOutMemberOID);

			PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(strInMemberOID);

			PayCustomerOperation operation = new PayCustomerOperation();
			// OID
			String OID = StringUtil.getUUID();
			operation.setOID(OID);
			// 资金账户OID
			operation.setCustomerOID(dtoOutCustomer.getOID());
			// 用户memeberOID
			operation.setMemberOID(dtoOutCustomer.getMemberOID());

			operation.setContent("划拨完成");
			// 金额
			operation.setMoney(money);
			// 类型（充值还是提现）
			operation.setState("outTransfer");
			// 流水号
			operation.setRequestID(resultData.getMchnt_txn_ssn());
			// 插入一条充值记录
			payCustomerOperationMapper.insertRechargeRecord(operation);

			PayCustomerOperation inOperation = new PayCustomerOperation();
			// OID
			String oid = StringUtil.getUUID();
			inOperation.setOID(oid);
			// 资金账户OID
			inOperation.setCustomerOID(dtoInCustomer.getOID());
			// 用户memeberOID
			inOperation.setMemberOID(dtoInCustomer.getMemberOID());

			inOperation.setContent("划拨完成");
			// 金额
			inOperation.setMoney(money);
			// 类型（充值还是提现）
			inOperation.setState("inTransfer");
			// 流水号
			inOperation.setRequestID(resultData.getMchnt_txn_ssn());
			// 插入一条充值记录
			payCustomerOperationMapper.insertRechargeRecord(inOperation);
			this.updateAccount(strOutMemberOID);
			this.updateAccount(strInMemberOID);
			return "1";
		    } else {
			logger.info(FuiouErrorCode.errorCode(strCode));
			return FuiouErrorCode.errorCode(strCode);
		    }
		} else {
		    return "调用接口失败";
		}
	    }
	} catch (Exception e) {
	    logger.error("transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money) ", e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public String transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(outMemberOID) && StringUtil.isNotEmpty(inMemberOID) && money != null) {
		CommonRspData resultData = this.payFuiService.transferFreeze(outMemberOID, inMemberOID, money);
		if (resultData != null) {
		    String strCode = resultData.getResp_code();
		    if (strCode.equals("0000")) {
			PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
			PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
			PayCustomerOperation dtoCustomerOperation = new PayCustomerOperation();
			dtoCustomerOperation.setContent("划拨完成");
			dtoCustomerOperation.setMoney(money);
			dtoCustomerOperation.setState("outTransferFrozen");
			dtoCustomerOperation.setMemberOID(outMemberOID);
			dtoCustomerOperation.setCustomerOID(dtoOutCustomer.getOID());
			dtoCustomerOperation.setRequestID(resultData.getMchnt_txn_ssn());
			dtoCustomerOperation.setOID(StringUtil.getUUID());
			payCustomerOperationMapper.insertRechargeRecord(dtoCustomerOperation);

			PayCustomerOperation operation = new PayCustomerOperation();
			operation.setContent("划拨冻结");
			operation.setMoney(money);
			operation.setState("outTransferFrozen");
			operation.setMemberOID(outMemberOID);
			operation.setCustomerOID(dtoInCustomer.getOID());
			operation.setRequestID(resultData.getMchnt_txn_ssn());
			operation.setOID(StringUtil.getUUID());
			payCustomerOperationMapper.insertRechargeRecord(dtoCustomerOperation);
			this.updateAccount(outMemberOID);
			this.updateAccount(inMemberOID);
			return "1";
		    } else {
			logger.info(FuiouErrorCode.errorCode(strCode));
			return FuiouErrorCode.errorCode(strCode);
		    }
		} else {
		    return "接口调用失败";
		}
	    }
	} catch (Exception e) {
	    logger.error("public String transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money)",
		    e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public String unFreeze(String memberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(memberOID) && money != null) {
		UnFreezeRspData resultData = this.payFuiService.unFreeze(memberOID, money);
		String strCode = resultData.getResp_code();
		if (strCode.equals("0000")) {
		    PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
		    PayCustomerOperation dtoCustomerOperation = new PayCustomerOperation();
		    dtoCustomerOperation.setContent("解冻完成");
		    dtoCustomerOperation.setMoney(money);
		    dtoCustomerOperation.setState("unFreeze");
		    dtoCustomerOperation.setMemberOID(memberOID);
		    dtoCustomerOperation.setCustomerOID(dtoCustomer.getOID());
		    dtoCustomerOperation.setRequestID(resultData.getMchnt_txn_ssn());
		    dtoCustomerOperation.setOID(StringUtil.getUUID());
		    payCustomerOperationMapper.insertRechargeRecord(dtoCustomerOperation);
		    this.updateAccount(memberOID);
		    return "1";
		} else {
		    logger.info(FuiouErrorCode.errorCode(strCode));
		    return FuiouErrorCode.errorCode(strCode);
		}
	    }
	} catch (Exception e) {
	    logger.error("public String unFreeze(String memberOID, BigDecimal money)", e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public String freeze(String memberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(memberOID) && money != null) {
		CommonRspData resultData = this.payFuiService.freeze(memberOID, money);
		if (resultData != null) {
		    String strCode = resultData.getResp_code();
		    if (strCode.equals("0000")) {
			PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
			PayCustomerOperation dtoCustomerOperation = new PayCustomerOperation();
			dtoCustomerOperation.setContent("冻结完成");
			dtoCustomerOperation.setMoney(money);
			dtoCustomerOperation.setState("freeze");
			dtoCustomerOperation.setMemberOID(memberOID);
			dtoCustomerOperation.setCustomerOID(dtoCustomer.getOID());
			dtoCustomerOperation.setRequestID(resultData.getMchnt_txn_ssn());
			dtoCustomerOperation.setOID(StringUtil.getUUID());
			payCustomerOperationMapper.insertRechargeRecord(dtoCustomerOperation);
			this.updateAccount(memberOID);
		    } else {
			logger.info(FuiouErrorCode.errorCode(strCode));
			return FuiouErrorCode.errorCode(strCode);
		    }
		} else {
		    return "接口调用失败";
		}
	    }
	} catch (Exception e) {
	    logger.error("public String unFreeze(String memberOID, BigDecimal money)", e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public Map<String, Object> BalanceAction(String outMemberOID) {
	try {
	    Map<String, Object> customerInfo = new HashMap<String, Object>();
	    if (StringUtil.isNotEmpty(outMemberOID)) {
		QueryBalanceRspData resultData = this.payFuiService.BalanceAction(outMemberOID);
		if (resultData != null) {
		    String strCode = resultData.getResp_code();
		    if (strCode.equals("0000")) {
			List<QueryBalanceResultData> rd = resultData.getResults();
			String name = rd.get(0).getUser_id();
			BigDecimal fen = new BigDecimal(100);
			BigDecimal money = new BigDecimal(rd.get(0).getCf_balance());
			BigDecimal useMoney = new BigDecimal(rd.get(0).getCa_balance());
			BigDecimal frozenMoney = new BigDecimal(rd.get(0).getCf_balance());
			BigDecimal failTransferMoney = new BigDecimal(rd.get(0).getCu_balance());
			customerInfo.put("memberOID", outMemberOID);
			customerInfo.put("name", name);
			customerInfo.put("money", money.divide(fen, 2, BigDecimal.ROUND_HALF_UP));
			customerInfo.put("useMoney", useMoney.divide(fen, 2, BigDecimal.ROUND_HALF_UP));
			customerInfo.put("frozenMoney", frozenMoney.divide(fen, 2, BigDecimal.ROUND_HALF_UP));
			customerInfo.put("failTransferMoney",
				failTransferMoney.divide(fen, 2, BigDecimal.ROUND_HALF_UP));
			return customerInfo;
		    } else {
			customerInfo.put("message", FuiouErrorCode.errorCode(strCode));
			logger.info(FuiouErrorCode.errorCode(strCode));
			return customerInfo;
		    }
		} else {
		    customerInfo.put("message", "接口调用失败");
		    return customerInfo;
		}
	    }
	} catch (Exception e) {
	    logger.error("public Map<String, Object> BalanceAction(String outMemberOID)", e.getMessage());
	    return null;
	}
	return null;
    }

    @Override
    public void updateAccount(String memberOID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID)) {
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		QueryBalanceRspData resultData = this.payFuiService.updateAccount(memberOID);
		if (resultData != null) {
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
		    payCustomerMapper.updataCutomer(customer);
		} else {
		    logger.error("接口调用失败");
		}
	    }
	} catch (Exception e) {
	    logger.error("updateCustomerMoney(String memberOID)", e.getMessage());
	}
    }

    @Override
    public AppTransReqData recharge(RechargeDTO recharge) {
	try {
	    if (recharge != null) {
		return this.payFuiService.recharge(recharge);
	    }
	    return null;
	} catch (Exception e) {
	    logger.error(" AppTransReqData recharge(RechargeDTO recharge)", e.getMessage());
	    return null;
	}

    }

    @Override
    public Wy500012ReqData rechargeWY(RechargeDTO recharge) {
	try {
	    if (recharge != null) {
		return this.payFuiService.rechargeWY(recharge);
	    }
	    return null;
	} catch (Exception e) {
	    logger.error(" Wy500012ReqData rechargeWY(RechargeDTO recharge)", e.getMessage());
	    return null;
	}
    }

    @Override
    public ResetPassWordReqData updatePayPassword(String memberOID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID)) {
		return this.payFuiService.updatePayPassword(memberOID);
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("ResetPassWordReqData updatePayPassword(String memberOID)", e.getMessage());
	    return null;
	}
    }

    @Override
    public String withdrawalsSxfFinish(Map<String, String> map) {
	try {
	    String resp_code = map.get("resp_code");
	    String login_id = map.get("login_id");
	    String amt = map.get("amt");
	    String requestID = map.get("mchnt_txn_ssn");
	    String ly = map.get("ly");
	    if ("0000".equals(resp_code)) {
		PayCustomer customer = payCustomerMapper.findPAYCustomerByName(login_id);
		PayWithdrawalRecord payWithdrawalRecord = payWithdrawalRecordMapper
			.findWithdeawalByRequestID(requestID);
		BigDecimal sxf = payWithdrawalRecord.getSxf();
		String resultStr = "";
		if (sxf.compareTo(new BigDecimal("0")) > 0) {
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
		    payCashPoolOperationMapper.saveCashPoolOperation(operation);

		    PayCustomerBusiness business = new PayCustomerBusiness();

		    String bOID = StringUtil.getUUID();
		    business.setOID(bOID);
		    business.setCustomerOID(customer.getOID());
		    business.setMemberOID(customer.getMemberOID());
		    business.setContent("用户发起提现时手续费金额");
		    business.setType("提现手续费");
		    business.setState("已完成");
		    business.setMoney(money);
		    customerBusinessMapper.insertCustomerBussiness(business);
		    this.updateAccount(customer.getOID());
		}
		return resultStr;
	    }
	} catch (Exception e) {
	    logger.error(" withdrawalsSxfFinish(Map<String, String> map)", e.getMessage());
	    return null;
	}

	return null;
    }

    @Override
    public AuthorizationReqData authorization(String strMemberOID) {
	try {
	    if (strMemberOID != null) {
		AuthorizationReqData data = payFuiService.authorization(strMemberOID);
		if (data != null) {
		    return data;
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error(" withdrawalsSxfFinish(Map<String, String> map)", e.getMessage());
	    return null;
	}
    }

    @Override
    public AppTransReqData appRecharge(String memberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(memberOID) && money != null) {
		AppTransReqData data = payFuiService.appRecharge(memberOID, money);
		if (data != null) {
		    return data;
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("appRecharge(String memberOID, BigDecimal money)", e.getMessage());
	    return null;
	}

    }

    @Override
    public ResetPassWordReqData appUpdatePayPassword(String memberOID) {
	try {
	    if (StringUtil.isNotEmpty(memberOID)) {
		return this.payFuiService.appUpdatePayPassword(memberOID);
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("ResetPassWordReqData appUpdatePayPassword(String memberOID)", e.getMessage());
	    return null;
	}
    }

    @Override
    public String transferFreezeToFreeze(String outMemberOID, String inMemberOID, BigDecimal money) {
	try {
	    if (StringUtil.isNotEmpty(outMemberOID) && StringUtil.isNotEmpty(inMemberOID) && money != null) {
		UnFreezeRspData resultData = this.payFuiService.transferFreezeToFreeze(outMemberOID, inMemberOID,
			money);
		if (resultData != null) {
		    String strCode = resultData.getResp_code();
		    if (strCode.equals("0000")) {
			PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(outMemberOID);

			PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
			PayCustomerOperation operation = new PayCustomerOperation();
			operation.setContent("划拨完成");
			operation.setMoney(money);
			operation.setState("outTransferFrozen");
			operation.setMemberOID(outMemberOID);
			operation.setCustomerOID(dtoOutCustomer.getOID());
			operation.setRequestID(resultData.getMchnt_txn_ssn());
			operation.setOID(StringUtil.getUUID());
			payCustomerOperationMapper.insertRechargeRecord(operation);

			operation = new PayCustomerOperation();
			operation.setContent("划拨完成");
			operation.setMoney(money);
			operation.setState("inTransferFrozen");
			operation.setMemberOID(inMemberOID);
			operation.setCustomerOID(dtoInCustomer.getOID());
			operation.setRequestID(resultData.getMchnt_txn_ssn());
			operation.setOID(StringUtil.getUUID());
			payCustomerOperationMapper.insertRechargeRecord(operation);
			this.updateAccount(outMemberOID);
			this.updateAccount(inMemberOID);
			return "1";
		    } else {
			return FuiouErrorCode.errorCode(strCode);
		    }
		}
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("transferFreezeToFreeze(String outMemberOID, String inMemberOID,BigDecimal money)",
		    e.getMessage());
	    return null;
	}
    }

}
