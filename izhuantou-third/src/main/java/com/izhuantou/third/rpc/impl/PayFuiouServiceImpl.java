package com.izhuantou.third.rpc.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fuiou.data.AppTransReqData;
import com.fuiou.data.ChangeCard2ReqData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.FreezeReqData;
import com.fuiou.data.QueryBalanceReqData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.data.QueryChangeCardReqData;
import com.fuiou.data.QueryChangeCardRspData;
import com.fuiou.data.RegReqData;
import com.fuiou.data.ResetPassWordReqData;
import com.fuiou.data.TransferBmuAndFreezeReqData;
import com.fuiou.data.TransferBmuReqData;
import com.fuiou.data.UnFreezeRspData;
import com.fuiou.data.Wy500012ReqData;
import com.fuiou.service.FuiouService;
import com.fuiou.util.SecurityUtils;
import com.izhuantou.common.AuthorizationReqData;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PaySeller;
import com.izhuantou.damain.vo.RechargeDTO;
import com.izhuantou.damain.vo.WithdrawalDTO;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PaySellerMapper;
import com.izhuantou.third.rpc.api.PayFuiouService;

@Service("payFuiouService")
public class PayFuiouServiceImpl implements PayFuiouService {

    private static final Logger logger = LoggerFactory.getLogger(PayFuiouServiceImpl.class);

    private boolean flag = false;
    @Value("${defaultSeller}")
    private String defaultSeller;

    @Value("${changeCardCallbackURL}")
    private String changeCardCallbackURL;

    @Value("${authorizationCallbackURL}")
    private String authorizationCallbackURL;

    @Value("${rechargeForwardURL}")
    private String rechargeForwardURL;

    @Value("${rechargeCallbackURL}")
    private String rechargeCallbackURL;

    @Value("${withdrawalsForwardURL}")
    private String withdrawalsForwardURL;

    @Value("${withdrawalsCallbackURL}")
    private String withdrawalsCallbackURL;

    @Value("${updatePayPasswordForwardURL}")
    private String updatePayPasswordForwardURL;

    @Value("${appUpdatePayPasswordForwardURL}")
    private String appUpdatePayPasswordForwardURL;

    @Value("${mobilerechargeCallbackURL}")
    private String mobilerechargeCallbackURL;

    @Value("${appRechargeForwardURL}")
    private String appRechargeForwardURL;

    @Value("${mobilewithdrawalsCallbackURL}")
    private String mobilewithdrawalsCallbackURL;

    @Value("${appWithdrawalsForwardURL}")
    private String appWithdrawalsForwardURL;
    @Autowired
    private PayCustomerMapper payCustomerMapper;
    @Autowired
    private PaySellerMapper paySellerMapper;

    @Override
    public ChangeCard2ReqData changeCard(String memberOID, String requestID) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(requestID)) {
		    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		    PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		    ChangeCard2ReqData data = new ChangeCard2ReqData();
		    data.setMchnt_cd(paySeller.getID());
		    data.setMchnt_txn_ssn(requestID);
		    data.setLogin_id(customer.getName());
		    data.setPage_notify_url(changeCardCallbackURL);
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    return data;
		}
	    } catch (Exception e) {
		logger.error("public  Map<String, Object> changeCard(String memberOID)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public String createCustomerAccount(PayCustomer payCustomer) {
	if (flag) {
	    try {
		if (payCustomer == null) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);

		RegReqData data = new RegReqData();

		data.setMchnt_cd(paySeller.getID());
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		data.setCust_nm(payCustomer.getNameCN());
		data.setCertif_tp(payCustomer.getCardType());
		data.setCertif_id(payCustomer.getCardNO());
		data.setMobile_no(payCustomer.getMobile());
		data.setCity_id(payCustomer.getBankCityCode());
		data.setParent_bank_id(payCustomer.getBankCode());
		data.setBank_nm(payCustomer.getBankNameCN());
		data.setCapAcntNo(payCustomer.getBankNumber());
		data.setPassword(payCustomer.getWithdrawalsPassword());
		data.setLpassword(payCustomer.getLoginPassword());
		CommonRspData resultData = FuiouService.reg(data);
		String strCode = resultData.getResp_code();
		if (strCode.equals("0000")) {
		    payCustomer.setSellerOID(paySeller.getOID());
		    payCustomer.setRequestID(resultData.getMchnt_txn_ssn());
		    payCustomer.setNoTransferMoney(new BigDecimal("0"));
		    payCustomerMapper.insertCustomer(payCustomer);
		    return "1";
		} else {
		    return FuiouErrorCode.errorCode(strCode);
		}
	    } catch (Exception e) {
		logger.error("private String createCustomerAccount(CustomerDTO customerDto) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public QueryBalanceRspData updateAccount(String memberOID) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(memberOID)) {
		    PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		    PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		    QueryBalanceReqData data = new QueryBalanceReqData();
		    data.setMchnt_cd(paySeller.getID());
		    String strRequestID = StringUtil.getUUID().substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    data.setMchnt_txn_dt(DateUtils.formatJustDate(new Date().getTime()));
		    data.setCust_no(customer.getName());
		    QueryBalanceRspData resultData = FuiouService.balanceAction(data);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error("updateCustomerMoney(String memberOID)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public AuthorizationReqData authorization(String memberOID) {
	if (flag) {
	    try {
		if (StringUtil.isEmpty(memberOID)) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);

		AuthorizationReqData data = new AuthorizationReqData();
		String ID = paySeller.getID();
		data.setMchnt_cd(ID);
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		data.setLogin_id(customer.getName());
		data.setBusi_tp("2");
		data.setPage_notify_url(authorizationCallbackURL);

		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("authorization(String memberOID)", e.getMessage());
		return null;
	    }
	}
	return null;

    }

    @Override
    public AppTransReqData recharge(RechargeDTO recharge) {
	if (flag) {
	    try {
		if (recharge == null) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		String memberOID = recharge.getMemberOID();
		// this.updateAccount(memberOID);

		BigDecimal fen = new BigDecimal(100);
		BigDecimal money = recharge.getMoney();
		BigDecimal fenMoney = money.multiply(fen);
		String url = recharge.getUrl();
		AppTransReqData data = new AppTransReqData();

		String ID = paySeller.getID();
		data.setMchnt_cd(ID);
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 閸忓懎锟藉ジ鍣炬０锟�
		data.setAmt(String.valueOf(fenMoney.intValue()));
		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		if (StringUtil.isEmpty(url)) {
		    url = rechargeForwardURL;
		}
		data.setPage_notify_url(url);
		data.setBack_notify_url(rechargeCallbackURL);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("recharge(RechargeDTO recharge) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public Wy500012ReqData rechargeWY(RechargeDTO recharge) {
	if (flag) {
	    try {
		if (recharge == null) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		String memberOID = recharge.getMemberOID();

		this.updateAccount(memberOID);
		BigDecimal fen = new BigDecimal(100);
		BigDecimal money = recharge.getMoney();
		BigDecimal fenMoney = money.multiply(fen);
		String bankNO = recharge.getCard_id();
		String url = recharge.getUrl();

		Wy500012ReqData data = new Wy500012ReqData();

		String ID = paySeller.getID();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(ID);
		// 濞翠焦鎸夐崣锟�
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		// 閻€劍鍩涢惃鍕カ闁叉垼澶勯幋锟�
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 閸忓懎锟藉ジ鍣炬０锟�
		data.setAmt(String.valueOf(fenMoney.intValue()));
		data.setOrder_pay_type("B2C");
		// 闁炬儼顢戦崡锟�
		data.setIss_ins_cd(bankNO);
		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		if (StringUtil.isEmpty(url)) {
		    // 閸熷棙鍩涙潻鏂挎礀閸︽澘娼� 閸熷棙鍩涢幒銉︽暪娴溿倖妲楃紒鎾寸亯闁氨鐓￠崷鏉挎絻
		    url = rechargeForwardURL;
		}
		data.setPage_notify_url(url);
		// 閸熷棙鍩涢幒銉︽暪閸氬骸褰寸紒鎾寸亯闁氨鐓￠崷鏉挎絻
		data.setBack_notify_url(rechargeCallbackURL);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("rechargeWY(RechargeDTO recharge) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public AppTransReqData withdrawals(WithdrawalDTO withdrawal) {
	if (flag) {
	    try {
		String memberOID = withdrawal.getMemberOID();
		BigDecimal money = withdrawal.getMoney();
		String requestID = withdrawal.getRequestID();
		String url = withdrawal.getUrl();
		if (StringUtil.isEmpty(memberOID)) {
		    return null;
		}
		this.updateAccount(memberOID);

		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);

		BigDecimal fen = new BigDecimal(100);
		BigDecimal fenMoney = money.multiply(fen);
		AppTransReqData data = new AppTransReqData();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(paySeller.getID());
		// 濞翠焦鎸夐崣锟�
		data.setMchnt_txn_ssn(requestID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 閹绘劗骞囬柌鎴︻杺
		data.setAmt(String.valueOf(fenMoney.intValue()));
		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		if (StringUtil.isEmpty(url)) {
		    url = withdrawalsForwardURL;
		}
		data.setPage_notify_url(url);
		// 閸熷棙鍩涢崥搴″酱闁氨鐓￠崷鏉挎絻
		data.setBack_notify_url(withdrawalsCallbackURL);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("withdrawal(WithdrawalDTO withdrawal) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public ResetPassWordReqData updatePayPassword(String memberOID) {
	if (flag) {
	    try {
		if (StringUtil.isEmpty(memberOID)) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);

		ResetPassWordReqData data = new ResetPassWordReqData();

		String ID = paySeller.getID();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(ID);
		// 濞翠焦鎸夐崣锟�
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		// 閻€劍鍩涢惃鍕カ闁叉垼澶勯幋锟�
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 缁鐎�
		data.setBusi_tp("3");
		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		data.setBack_url(updatePayPasswordForwardURL);
		String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("updatePayPassword(String memberOID) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public AppTransReqData appRecharge(String memberOID, BigDecimal money) {
	if (flag) {
	    try {
		if (memberOID == null) {
		    return null;
		}
		this.updateAccount(memberOID);
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);

		AppTransReqData data = new AppTransReqData();

		BigDecimal fen = new BigDecimal(100);
		BigDecimal fenMoney = money.multiply(fen);
		String ID = paySeller.getID();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(ID);
		// 濞翠焦鎸夐崣锟�
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		// 閻€劍鍩涢惃鍕カ闁叉垼澶勯幋锟�
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 閸忓懎锟藉ジ鍣炬０锟�
		data.setAmt(String.valueOf(fenMoney.intValue()));
		data.setPage_notify_url(mobilerechargeCallbackURL);
		data.setBack_notify_url(appRechargeForwardURL);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("AppRecharge(String memberOID, BigDecimal money)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public AppTransReqData appWithdrawals(WithdrawalDTO withdrawal) {
	if (flag) {
	    try {
		String memberOID = withdrawal.getMemberOID();
		BigDecimal money = withdrawal.getMoney();
		String requestID = withdrawal.getRequestID();
		if (StringUtil.isEmpty(memberOID)) {
		    return null;
		}
		this.updateAccount(memberOID);
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		BigDecimal fen = new BigDecimal(100);
		BigDecimal fenMoney = money.multiply(fen);
		AppTransReqData data = new AppTransReqData();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(paySeller.getID());
		// 濞翠焦鎸夐崣锟�
		data.setMchnt_txn_ssn(requestID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 閹绘劗骞囬柌鎴︻杺
		data.setAmt(String.valueOf(fenMoney.intValue()));

		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		data.setPage_notify_url(mobilewithdrawalsCallbackURL);
		// 閸熷棙鍩涢崥搴″酱闁氨鐓￠崷鏉挎絻
		data.setBack_notify_url(appWithdrawalsForwardURL);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error("withdrawal(WithdrawalDTO withdrawal) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public ResetPassWordReqData appUpdatePayPassword(String memberOID) {
	if (flag) {
	    try {
		if (StringUtil.isEmpty(memberOID)) {
		    return null;
		}
		PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);

		ResetPassWordReqData data = new ResetPassWordReqData();
		String ID = paySeller.getID();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(ID);
		// 濞翠焦鎸夐崣锟�
		String strRequestID = StringUtil.getUUID().substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		// 閻€劍鍩涢惃鍕カ闁叉垼澶勯幋锟�
		PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(customer.getName());
		// 缁鐎�
		data.setBusi_tp("3");
		// 閸熷棙鍩涙潻鏂挎礀閸︽澘娼�
		data.setBack_url(appUpdatePayPasswordForwardURL);

		String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
		logger.info(fsjm);
		return data;
	    } catch (Exception e) {
		logger.error(" AppUpdatePayPassword(String memberOID) ", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public CommonRspData transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(strOutMemberOID) && StringUtil.isNotEmpty(strInMemberOID) && money != null) {
		    PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
		    TransferBmuReqData data = new TransferBmuReqData();
		    // 閸熷棙鍩涙禒锝囩垳
		    data.setMchnt_cd(paySeller.getID());
		    // 濞翠焦鎸夐崣锟�
		    String strRequestID = StringUtil.getUUID().substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    // 妫板嫭宸块弶鍐櫨妫帮拷
		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    data.setAmt(String.valueOf(fenMoney.intValue()));
		    // 閻€劍鍩涢惃鍕カ闁叉垼澶勯幋锟�
		    PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(strOutMemberOID);
		    // 閸戦缚澶勭拹锔藉煕
		    data.setOut_cust_no(dtoOutCustomer.getName());

		    PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(strInMemberOID);
		    // 閸忋儴澶勭拹锔藉煕
		    data.setIn_cust_no(dtoInCustomer.getName());
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    CommonRspData resultData = FuiouService.transferBu(data);
		    String strCode = resultData.getResp_code();
		    logger.info(strCode);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error("transfer(String strOutMemberOID, String strInMemberOID, BigDecimal money) ",
			e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public CommonRspData transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money) {
	if (flag) {
	    try {
		PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
		TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(dtoSeller.getID());
		// 濞翠焦鎸夐崣锟�
		String strRequestID = ToolString.gainUUID(this).substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		// 閸掓帗瀚ч崘鑽ょ波闁叉垿顤�
		BigDecimal fen = new BigDecimal(100);
		BigDecimal fenMoney = money.multiply(fen);
		data.setAmt(String.valueOf(fenMoney.intValue()));
		PayCustomer outCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
		// 閸戦缚澶勭拹锔藉煕
		data.setOut_cust_no(outCustomer.getName());

		PayCustomer inCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
		// 閸忋儴澶勭拹锔藉煕
		data.setIn_cust_no(inCustomer.getName());
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		CommonRspData resultData = FuiouService.transferBuAndFreeze(data);// 閸欘垳鏁ら崚鎺撳閸掓澘鍠曠紒锟�
		return resultData;
	    } catch (Exception e) {
		logger.error("public String transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money)",
			e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public UnFreezeRspData transferFreezeToFreeze(String outMemberOID, String inMemberOID, BigDecimal money) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(outMemberOID) && StringUtil.isNotEmpty(inMemberOID) && money != null) {
		    PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
		    TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
		    // 閸熷棙鍩涙禒锝囩垳
		    data.setMchnt_cd(dtoSeller.getID());
		    // 濞翠焦鎸夐崣锟�
		    String strRequestID = StringUtil.getUUID().substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    // 閸掓帗瀚ч崘鑽ょ波闁叉垿顤�
		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    data.setAmt(String.valueOf(fenMoney.intValue()));

		    PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
		    // 閸戦缚澶勭拹锔藉煕
		    data.setOut_cust_no(dtoOutCustomer.getName());

		    PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
		    // 閸忋儴澶勭拹锔藉煕
		    data.setIn_cust_no(dtoInCustomer.getName());
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    UnFreezeRspData resultData = FuiouService.transferBuAndFreeze2Freeze(data);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error(
			"public String transferFreezeToFreeze(String outMemberOID, String inMemberOID, BigDecimal money)",
			e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public UnFreezeRspData unFreeze(String memberOID, BigDecimal money) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(memberOID) && money != null) {
		    PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
		    FreezeReqData data = new FreezeReqData();
		    // 閸熷棙鍩涙禒锝囩垳
		    data.setMchnt_cd((String) dtoSeller.getID());
		    // 濞翠焦鎸夐崣锟�
		    String strRequestID = ToolString.gainUUID(this).substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    // 鐟欙絽鍠曢柌鎴︻杺
		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    data.setAmt(String.valueOf(fenMoney.intValue()));
		    PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
		    data.setCust_no((String) dtoCustomer.getName());
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    UnFreezeRspData resultData = FuiouService.unFreeze(data);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error("public String unFreeze(String memberOID, BigDecimal money)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public CommonRspData freeze(String memberOID, BigDecimal money) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(memberOID) && money != null) {
		    PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
		    FreezeReqData data = new FreezeReqData();
		    // 閸熷棙鍩涙禒锝囩垳
		    data.setMchnt_cd(dtoSeller.getID());
		    // 濞翠焦鎸夐崣锟�
		    String strRequestID = ToolString.gainUUID(this).substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    // 閸愯崵绮ㄩ柌鎴︻杺
		    BigDecimal fen = new BigDecimal(100);
		    BigDecimal fenMoney = money.multiply(fen);
		    data.setAmt(String.valueOf(fenMoney.intValue()));
		    PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
		    // 鐠愶附鍩�
		    data.setCust_no(dtoCustomer.getName());
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    CommonRspData resultData = FuiouService.freeze(data);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error("public String unFreeze(String memberOID, BigDecimal money)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public QueryBalanceRspData BalanceAction(String outMemberOID) {
	if (flag) {
	    try {
		if (StringUtil.isNotEmpty(outMemberOID)) {
		    PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
		    QueryBalanceReqData data = new QueryBalanceReqData();
		    // 閸熷棙鍩涙禒锝囩垳
		    data.setMchnt_cd(dtoSeller.getID());
		    // 濞翠焦鎸夐崣锟�
		    String strRequestID = ToolString.gainUUID(this).substring(2);
		    data.setMchnt_txn_ssn(strRequestID);
		    // 閸熷棙鍩涢弻銉嚄瑜版挸澧犻弮銉︽埂
		    String orderDate = ToolsDatas.gainTimeNow();
		    data.setMchnt_txn_dt(orderDate);
		    PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
		    // 閸戦缚澶勭拹锔藉煕
		    data.setCust_no(dtoCustomer.getName());
		    String fsjm = SecurityUtils.sign(data.createSignValue());
		    logger.info(fsjm);
		    QueryBalanceRspData resultData = FuiouService.balanceAction(data);
		    return resultData;
		}
	    } catch (Exception e) {
		logger.error("public Map<String, Object> BalanceAction(String outMemberOID)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

    @Override
    public QueryChangeCardRspData queryChangeCard(String memberOID, String requestID) {
	if (flag) {
	    try {
		PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
		QueryChangeCardReqData data = new QueryChangeCardReqData();
		// 閸熷棙鍩涙禒锝囩垳
		data.setMchnt_cd(dtoSeller.getID());
		// 濞翠焦鎸夐崣锟�
		String strRequestID = ToolString.gainUUID(this).substring(2);
		data.setMchnt_txn_ssn(strRequestID);
		PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
		// 閻€劍鍩涢惂璇茬秿閸氾拷
		data.setLogin_id(dtoCustomer.getName());
		// 鐠囬攱鐪板ù浣规寜
		data.setTxn_ssn(requestID);
		String fsjm = SecurityUtils.sign(data.createSignValue());
		logger.info(fsjm);
		QueryChangeCardRspData resultData = FuiouService.queryChangeCard(data);
		return resultData;
	    } catch (Exception e) {
		logger.error("public String queryChangeCard(String memberOID, String requestID)", e.getMessage());
		return null;
	    }
	}
	return null;
    }

}
