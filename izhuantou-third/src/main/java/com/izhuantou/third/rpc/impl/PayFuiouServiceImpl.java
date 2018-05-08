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

	private boolean flag = true;
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
				long startTime = System.currentTimeMillis();
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
					logger.info("PayFuiouServiceImpl类中changeCard方法参数封装，免登陆更换银行卡接口，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中changeCard方法根据用户memberOID和requestID更换银行卡接口参数封装condition={}"
						+ memberOID + "," + requestID, e.getMessage());

				return null;
			}
		}
		return null;
	}

	@Override
	public String createCustomerAccount(PayCustomer payCustomer) {
		if (flag) {
			try {
				if (payCustomer != null) {
					long startTime = System.currentTimeMillis();
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
						logger.info("PayFuiouServiceImpl类中createCustomerAccount方法，富友开户，总耗时时间："
								+ (System.currentTimeMillis() - startTime));
						return "1";
					} else {
						logger.info("PayFuiouServiceImpl类中createCustomerAccount方法，富友开户返回错误信息"
								+ FuiouErrorCode.errorCode(strCode));
						return FuiouErrorCode.errorCode(strCode);
					}
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中createCustomerAccount方法，根据用户信息开通富友账户condition={}" + payCustomer,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public QueryBalanceRspData updateAccount(String memberOID) {
		if (flag) {
			try {
				long startTime = System.currentTimeMillis();
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
					logger.info("PayFuiouServiceImpl类中updateAccount方法，同步资金账户参数封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中updateAccount方法，根据memberOID同步资金账户参数封装condition={}" + memberOID,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public AuthorizationReqData authorization(String memberOID) {
		if (flag) {
			try {
				if (StringUtil.isNotEmpty(memberOID)) {
					long startTime = System.currentTimeMillis();
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
					logger.info("PayFuiouServiceImpl类中authorization方法，PC金账户免登陆授权配置参数封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error(
						"PayFuiouServiceImpl类中authorization方法 根据memberOID更改 PC金账户免登陆授权配置参数封装condition={}" + memberOID,
						e.getMessage());
				return null;
			}
		}
		return null;

	}

	@Override
	public AppTransReqData recharge(RechargeDTO recharge) {
		if (flag) {
			try {
				if (recharge != null) {
					long startTime = System.currentTimeMillis();
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					String memberOID = recharge.getMemberOID();
					this.updateAccount(memberOID);
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

					data.setLogin_id(customer.getName());

					data.setAmt(String.valueOf(fenMoney.intValue()));

					if (StringUtil.isEmpty(url)) {
						url = rechargeForwardURL;
					}
					data.setPage_notify_url(url);
					data.setBack_notify_url(rechargeCallbackURL);
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中recharge方法，PC端个人用户免登录快捷充值参数封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中recharge方法 PC端个人用户免登录快捷充值参数封装condition={}" + recharge,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public Wy500012ReqData rechargeWY(RechargeDTO recharge) {
		if (flag) {
			try {
				if (recharge != null) {
					long startTime = System.currentTimeMillis();
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

					data.setMchnt_cd(ID);

					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);

					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);

					data.setLogin_id(customer.getName());

					data.setAmt(String.valueOf(fenMoney.intValue()));
					data.setOrder_pay_type("B2C");

					data.setIss_ins_cd(bankNO);

					if (StringUtil.isEmpty(url)) {
						url = rechargeForwardURL;
					}
					data.setPage_notify_url(url);

					data.setBack_notify_url(rechargeCallbackURL);
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中rechargeWY方法，P2P免登录直接跳转网银界面充值接口参数封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中rechargeWY方法，P2P免登录直接跳转网银界面充值接口参数封装condition={}" + recharge,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public AppTransReqData withdrawals(WithdrawalDTO withdrawal) {
		if (flag) {
			try {
				if (withdrawal != null) {
					long startTime = System.currentTimeMillis();
					String memberOID = withdrawal.getMemberOID();
					BigDecimal money = withdrawal.getMoney();
					String requestID = withdrawal.getRequestID();
					String url = withdrawal.getUrl();
					this.updateAccount(memberOID);
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);

					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					AppTransReqData data = new AppTransReqData();
					data.setMchnt_cd(paySeller.getID());
					data.setMchnt_txn_ssn(requestID);
					data.setLogin_id(customer.getName());
					data.setAmt(String.valueOf(fenMoney.intValue()));
					if (StringUtil.isEmpty(url)) {
						url = withdrawalsForwardURL;
					}
					data.setPage_notify_url(url);
					data.setBack_notify_url(withdrawalsCallbackURL);
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中withdrawals方法，商户P2P网站免登录提现接口封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中withdrawals方法商户P2P网站免登录提现接口封装condition={}" + withdrawal,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public ResetPassWordReqData updatePayPassword(String memberOID) {
		if (flag) {
			try {
				if (StringUtil.isNotEmpty(memberOID)) {
					long startTime = System.currentTimeMillis();
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					ResetPassWordReqData data = new ResetPassWordReqData();
					String ID = paySeller.getID();
					data.setMchnt_cd(ID);
					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
					data.setLogin_id(customer.getName());
					data.setBusi_tp("3");
					data.setBack_url(updatePayPasswordForwardURL);
					String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中updatePayPassword方法，密码修改重置免登陆接口(网页版)封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中updatePayPassword方法，密码修改重置免登陆接口(网页版)封装condition={}" + memberOID,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public AppTransReqData appRecharge(String memberOID, BigDecimal money) {
		if (flag) {
			try {
				if (StringUtil.isNotEmpty(memberOID) && money != null && (money.compareTo(new BigDecimal("0")) > 0)) {
					long startTime = System.currentTimeMillis();
					this.updateAccount(memberOID);
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					AppTransReqData data = new AppTransReqData();
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					String ID = paySeller.getID();
					data.setMchnt_cd(ID);
					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
					data.setLogin_id(customer.getName());
					data.setAmt(String.valueOf(fenMoney.intValue()));
					data.setPage_notify_url(mobilerechargeCallbackURL);
					data.setBack_notify_url(appRechargeForwardURL);
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中appRecharge方法，个人用户免登录快捷充值封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中appRecharge方法，个人用户免登录快捷充值封装condition={}" + memberOID + "" + money,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public AppTransReqData appWithdrawals(WithdrawalDTO withdrawal) {
		if (flag) {
			try {
				if (withdrawal != null) {
					long startTime = System.currentTimeMillis();
					String memberOID = withdrawal.getMemberOID();
					BigDecimal money = withdrawal.getMoney();
					String requestID = withdrawal.getRequestID();
					this.updateAccount(memberOID);
					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					AppTransReqData data = new AppTransReqData();
					data.setMchnt_cd(paySeller.getID());
					data.setMchnt_txn_ssn(requestID);
					data.setLogin_id(customer.getName());
					data.setAmt(String.valueOf(fenMoney.intValue()));
					data.setPage_notify_url(mobilewithdrawalsCallbackURL);
					data.setBack_notify_url(appWithdrawalsForwardURL);
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中appWithdrawals方法，个人用户免登录提现封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中appWithdrawals方法，个人用户免登录提现封装condition={}" + withdrawal,
						e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public ResetPassWordReqData appUpdatePayPassword(String memberOID) {
		if (flag) {
			try {
				if (StringUtil.isNotEmpty(memberOID)) {
					long startTime = System.currentTimeMillis();
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					ResetPassWordReqData data = new ResetPassWordReqData();
					String ID = paySeller.getID();
					data.setMchnt_cd(ID);
					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					PayCustomer customer = payCustomerMapper.findByMemberOID(memberOID);
					data.setLogin_id(customer.getName());
					data.setBusi_tp("3");
					data.setBack_url(appUpdatePayPasswordForwardURL);
					String fsjm = SecurityUtils.sign(data.createSignValueFor().toString());
					logger.info(fsjm);
					logger.info("PayFuiouServiceImpl类中appUpdatePayPassword方法，用户密码修改重置免登陆接口(app版)封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return data;
				}
			} catch (Exception e) {
				logger.error(
						"PayFuiouServiceImpl类中appUpdatePayPassword方法，用户密码修改重置免登陆接口(app版)封装condition={}" + memberOID,
						e.getMessage());
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
					long startTime = System.currentTimeMillis();
					PaySeller paySeller = paySellerMapper.gainSellerByName(defaultSeller);
					TransferBmuReqData data = new TransferBmuReqData();
					data.setMchnt_cd(paySeller.getID());
					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					data.setAmt(String.valueOf(fenMoney.intValue()));
					PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(strOutMemberOID);
					data.setOut_cust_no(dtoOutCustomer.getName());

					PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(strInMemberOID);
					data.setIn_cust_no(dtoInCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					CommonRspData resultData = FuiouService.transferBu(data);
					String strCode = resultData.getResp_code();
					logger.info(strCode);
					logger.info("PayFuiouServiceImpl类中transfer方法，划拨 (个人与个人之间)封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中transfer方法，划拨 (个人与个人之间)封装condition={}" + strOutMemberOID + ","
						+ strInMemberOID + "," + money, e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public CommonRspData transferFreeze(String outMemberOID, String inMemberOID, BigDecimal money) {
		if (flag) {
			try {
				if (StringUtil.isNotEmpty(outMemberOID) && StringUtil.isNotEmpty(inMemberOID) && money != null) {
					long startTime = System.currentTimeMillis();
					PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
					TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
					data.setMchnt_cd(dtoSeller.getID());
					String strRequestID = ToolString.gainUUID(this).substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					data.setAmt(String.valueOf(fenMoney.intValue()));
					PayCustomer outCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
					data.setOut_cust_no(outCustomer.getName());

					PayCustomer inCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
					data.setIn_cust_no(inCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					CommonRspData resultData = FuiouService.transferBuAndFreeze(data);
					logger.info("PayFuiouServiceImpl类中transferFreeze方法，划拨预冻结封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中transferFreeze方法，划拨预冻结封装condition={}"+outMemberOID+","+inMemberOID+","+money,
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
					long startTime = System.currentTimeMillis();
					PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
					TransferBmuAndFreezeReqData data = new TransferBmuAndFreezeReqData();
					data.setMchnt_cd(dtoSeller.getID());
					String strRequestID = StringUtil.getUUID().substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					data.setAmt(String.valueOf(fenMoney.intValue()));

					PayCustomer dtoOutCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
					data.setOut_cust_no(dtoOutCustomer.getName());

					PayCustomer dtoInCustomer = payCustomerMapper.findByMemberOID(inMemberOID);
					data.setIn_cust_no(dtoInCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					UnFreezeRspData resultData = FuiouService.transferBuAndFreeze2Freeze(data);
					logger.info("PayFuiouServiceImpl类中transferFreezeToFreeze方法，冻结到冻结接口封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error(
						"PayFuiouServiceImpl类中transferFreezeToFreeze方法，冻结到冻结接封装condition={}"+outMemberOID+","+inMemberOID+","+money,
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
					long startTime = System.currentTimeMillis();
					PaySeller dtoSeller = paySellerMapper.gainSellerByName(defaultSeller);
					FreezeReqData data = new FreezeReqData();
					data.setMchnt_cd((String) dtoSeller.getID());
					String strRequestID = ToolString.gainUUID(this).substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					data.setAmt(String.valueOf(fenMoney.intValue()));
					PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
					data.setCust_no((String) dtoCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					UnFreezeRspData resultData = FuiouService.unFreeze(data);
					logger.info("PayFuiouServiceImpl类中unFreeze方法，解冻接口封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中unFreeze方法，解冻接口封装condition={}"+memberOID+","+money, e.getMessage());
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
					long startTime = System.currentTimeMillis();
					PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
					FreezeReqData data = new FreezeReqData();
					data.setMchnt_cd(dtoSeller.getID());
					String strRequestID = ToolString.gainUUID(this).substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					BigDecimal fen = new BigDecimal(100);
					BigDecimal fenMoney = money.multiply(fen);
					data.setAmt(String.valueOf(fenMoney.intValue()));
					PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
					data.setCust_no(dtoCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					CommonRspData resultData = FuiouService.freeze(data);
					logger.info("PayFuiouServiceImpl类中freeze方法，冻结封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中freeze方法，冻结封装condition={}"+memberOID+","+money,e.getMessage());
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
					long startTime = System.currentTimeMillis();
					PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
					QueryBalanceReqData data = new QueryBalanceReqData();
					data.setMchnt_cd(dtoSeller.getID());
					String strRequestID = ToolString.gainUUID(this).substring(2);
					data.setMchnt_txn_ssn(strRequestID);
					String orderDate = ToolsDatas.gainTimeNow();
					data.setMchnt_txn_dt(orderDate);
					PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(outMemberOID);
					data.setCust_no(dtoCustomer.getName());
					String fsjm = SecurityUtils.sign(data.createSignValue());
					logger.info(fsjm);
					QueryBalanceRspData resultData = FuiouService.balanceAction(data);
					logger.info("PayFuiouServiceImpl类中BalanceAction方法，余额查询封装，总耗时时间："
							+ (System.currentTimeMillis() - startTime));
					return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中BalanceAction方法，余额查询封装condition={}"+outMemberOID, e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public QueryChangeCardRspData queryChangeCard(String memberOID, String requestID) {
		if (flag) {
			try {
				if(StringUtil.isNotEmpty(memberOID)&&StringUtil.isNotEmpty(requestID)){
					long startTime = System.currentTimeMillis();
				
				PaySeller dtoSeller = paySellerMapper.gainSellerByName(this.defaultSeller);
				QueryChangeCardReqData data = new QueryChangeCardReqData();
				data.setMchnt_cd(dtoSeller.getID());
				String strRequestID = ToolString.gainUUID(this).substring(2);
				data.setMchnt_txn_ssn(strRequestID);
				PayCustomer dtoCustomer = payCustomerMapper.findByMemberOID(memberOID);
				data.setLogin_id(dtoCustomer.getName());
				data.setTxn_ssn(requestID);
				String fsjm = SecurityUtils.sign(data.createSignValue());
				logger.info(fsjm);
				QueryChangeCardRspData resultData = FuiouService.queryChangeCard(data);
				logger.info("PayFuiouServiceImpl类中queryChangeCard方法，查询银行卡更换结果，总耗时时间："
						+ (System.currentTimeMillis() - startTime));
				return resultData;
				}
			} catch (Exception e) {
				logger.error("PayFuiouServiceImpl类中queryChangeCard方法，查询银行卡更换结果condition={}"+memberOID+","+requestID, e.getMessage());
				return null;
			}
		}
		return null;
	}

}
