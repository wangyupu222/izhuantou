package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.fund.rpc.api.ControlCashPool;
import com.izhuantou.fund.rpc.api.ControlCustomerBusiness;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.message.SendMessageService;

@Service("controlCashPool")
public class ControlCashPoolImpl extends BaseServiceImpl<PayCashPool> implements ControlCashPool {
	private static final Logger logger = LoggerFactory.getLogger(ControlCashPoolImpl.class);
	@Autowired
	private ControlPayService controlPay;
	@Autowired
	private PayCashPoolOperationMapper paycashPoolOperationMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private ControlCustomerBusiness controlCustomerBusiness;
	@Autowired
	private MessageContentBusinessMapper messageContentBusinessMapper;
	@Autowired
	private MemberMemberMapper memberMapper;
	@Autowired
	private MessageSmsHistoryMapper messageSmsHistoryMapper;
	@Autowired
	private SendMessageService sendMessageService;

	@Override
	public void onceProceduresMoney(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "manageAccount", money);
			controlPay.unFreeze("manageAccount", money);

			PayCashPoolOperation operation = new PayCashPoolOperation();
			operation.setOID(StringUtil.getUUID());
			operation.setBusinessOID(businessOID);
			operation.setOutMemberOID(memberOID);
			operation.setInMemberOID("manageAccount");
			operation.setContent("缴纳一次性手续费");
			operation.setMoney(money);
			paycashPoolOperationMapper.saveCashPoolOperation(operation);

			controlCustomerBusiness.saveCustomerBusiness("借款一次性手续费", money, "用户借款时应扣除的手续费", memberOID);
		} catch (Exception e) {
			logger.error(
					"onceProceduresMoney(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void finishInvestment(String businessOID, String memberOID, BigDecimal money) {
		try {

			controlPay.unFreeze(memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setOID(StringUtil.getUUID());
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("投资完成，解冻资金");
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

			controlCustomerBusiness.saveCustomerBusiness("借款到账", money, "用户借款成功实际到账金额", memberOID);
		} catch (Exception e) {
			logger.error("finishInvestment(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void finishTransferReturnUnFreeze(String businessOID, String memberOID, BigDecimal money,
			BigDecimal privilegeMoney) {
		try {
			PayCashPoolOperation dto = new PayCashPoolOperation();
			dto.setBusinessOID(businessOID);
			dto.setInMemberOID(memberOID);
			if (privilegeMoney == null) {
				dto.setMoney(money);
			} else {
				dto.setMoney(money.subtract(privilegeMoney));
			}
			dto.setContent("债转完成");
			dto.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(dto);

			controlCustomerBusiness.saveCustomerBusiness("债权转让", money, "债转成功实际到账金额", memberOID);

		} catch (Exception e) {
			logger.error("finishTransferReturnUnFreeze(String businessOID, String memberOID, BigDecimal money,"
					+ "BigDecimal privilegeMoney)" + e.getMessage());
		}

	}

	@Override
	public void realInvestment(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, money);
			PayCashPoolOperation dto = new PayCashPoolOperation();
			dto.setBusinessOID(businessOID);
			dto.setOutMemberOID(outMemberOID);
			dto.setInMemberOID(inMemberOID);
			dto.setMoney(money);
			dto.setContent("进行投资，划拨冻结");
			dto.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(dto);
		} catch (Exception e) {
			logger.error("realInvestment(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money)"
					+ e.getMessage());
		}
	}

	@Override
	public void transferReturnCashPool(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
		try {
			this.controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, money);
			PayCashPoolOperation dto = new PayCashPoolOperation();
			dto.setBusinessOID(businessOID);
			dto.setOutMemberOID(outMemberOID);
			dto.setInMemberOID(inMemberOID);
			dto.setMoney(money);
			dto.setContent("债转冻结");
			dto.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(dto);

			if (this.noviceBiddingRuningMapper.findByOID(businessOID) != null) {
				this.controlCustomerBusiness.saveCustomerBusiness("出借", money, "用户出借的金额", outMemberOID);

				MessageContentBusiness messageBusiness = new MessageContentBusiness();
				String mOID = StringUtil.getUUID();
				messageBusiness.setOID(mOID);
				messageBusiness.setTitle("出借");
				String content = "您已经出借头笔赚，出借金额为" + money + "元";
				messageBusiness.setContent(content);
				messageBusiness.setSendUser("系统");
				messageBusiness.setReceiveUserOID(outMemberOID);

				messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

				MemberMember member = memberMapper.findUserByOID(outMemberOID);
				MessageSmsHistory smsHistory = new MessageSmsHistory();
				String sOID = StringUtil.getUUID();
				smsHistory.setOID(sOID);
				smsHistory.setTitle("出借");
				String scontent = "您已经出借头笔赚，出借金额为" + money + "元";
				smsHistory.setContent(scontent);
				smsHistory.setSendUser("系统");
				smsHistory.setReceiveUser(member.getName());
				Map<String, String> result = sendMessageService.sendMessage(member.getName(), scontent);
				String state = result.get("Description");
				smsHistory.setState(state);
				messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
				logger.info(state);
			}
		} catch (Exception e) {
			logger.error(
					"transferReturnCashPool(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money)"
							+ e.getMessage());
		}
	}

	@Override
	public void freezeRepay(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.freeze(memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("冻结还款");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
			controlCustomerBusiness.saveCustomerBusiness("还款", money, "借款人还款", memberOID);
		} catch (Exception e) {
			logger.error("freezeRepay(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void manageMoney(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "manageAccount", money);
			controlPay.unFreeze("manageAccount", money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("manageAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("缴纳平台管理费");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("manageMoney(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void serviceMoney(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "manageAccount", money);
			controlPay.unFreeze("manageAccount", money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("manageAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("缴纳咨询服务费");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("serviceMoney(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void overdueInterest(String businessOID, String outmemberOID, String inmemberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(outmemberOID, inmemberOID, money);
			controlPay.unFreeze(inmemberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(outmemberOID);
			cashPoolOperation.setInMemberOID(inmemberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("逾期罚息");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error(
					"overdueInterest(String businessOID, String outmemberOID, String inmemberOID, BigDecimal money)"
							+ e.getMessage());
		}

	}

	@Override
	public void overdueManage(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "fineAccount", money);
			controlPay.unFreeze("fineAccount", money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("fineAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("逾期管理费");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("overdueManage(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void overduePenalty(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "fineAccount", money);
			controlPay.unFreeze("fineAccount", money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("fineAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("逾期违约金");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("overduePenalty(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void earlyRepayPenalty(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "fineAccount", money);
			controlPay.unFreeze("fineAccount", money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("fineAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("提前还款违约金");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("earlyRepayPenalty(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void freezeOverdue(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.freeze(memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("冻结逾期费用");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("earlyRepayPenalty(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void discount(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreeze("discountAccount", memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID("discountAccount");
			cashPoolOperation.setInMemberOID(memberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("贴息完成");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("discount(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void repayNCPNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money,
			BigDecimal suplusFreezeMoney) {
		try {
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(outMemberOID);
			cashPoolOperation.setInMemberOID(inMemberOID);
			if (money.compareTo(suplusFreezeMoney) > 0) {
				controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, suplusFreezeMoney);
				cashPoolOperation.setMoney(suplusFreezeMoney);
				this.discount(businessOID, inMemberOID, money.subtract(suplusFreezeMoney));
			} else {
				controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, money);
				cashPoolOperation.setMoney(money);
			}
			cashPoolOperation.setContent("还款");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("repayNCPNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money,"
					+ "BigDecimal suplusFreezeMoney)" + e.getMessage());
		}
	}

	@Override
	public void help(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.transferFreezeToFreeze(memberOID, "helpAccount", money.abs());
			controlPay.unFreeze("helpAccount", money.abs());
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("helpAccount");
			cashPoolOperation.setMoney(money.abs());
			cashPoolOperation.setContent("增加约定收益部分完成");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
		} catch (Exception e) {
			logger.error("help(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void finishRepayNCP(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.unFreeze(memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(memberOID);
			cashPoolOperation.setInMemberOID("helpAccount");
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("还款完成");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

			controlCustomerBusiness.saveCustomerBusiness("回款", money, "回款的本金利息金额", memberOID);

		} catch (Exception e) {
			logger.error("help(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void repayNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money,
			BigDecimal suplusFreezeMoney) {
		try {
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(outMemberOID);
			cashPoolOperation.setInMemberOID(inMemberOID);
			if (money.compareTo(suplusFreezeMoney) > 0) {
				controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, suplusFreezeMoney);
				cashPoolOperation.setMoney(suplusFreezeMoney);
				this.discount(businessOID, inMemberOID, money.subtract(suplusFreezeMoney));
			} else {
				controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID, money);
				cashPoolOperation.setMoney(money);
			}
			cashPoolOperation.setContent("还款");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

			controlCustomerBusiness.saveCustomerBusiness("回款", money, "回款的本金利息金额", inMemberOID);

			MessageContentBusiness messageBusiness = new MessageContentBusiness();
			String mOID = StringUtil.getUUID();
			messageBusiness.setOID(mOID);
			messageBusiness.setTitle("回款");
			String content = "您收到还款金额为" + money + "元";
			messageBusiness.setContent(content);
			messageBusiness.setSendUser("系统");
			messageBusiness.setReceiveUserOID(inMemberOID);
			messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

			MemberMember member = memberMapper.findUserByOID(inMemberOID);
			MessageSmsHistory smsHistory = new MessageSmsHistory();
			String sOID = StringUtil.getUUID();
			smsHistory.setOID(sOID);
			smsHistory.setTitle("提现");
			String scontent = "【砖头网】您收到还款金额为" + money + "元";
			smsHistory.setContent(scontent);
			smsHistory.setSendUser("系统");
			smsHistory.setReceiveUser(member.getName());
			Map<String, String> result = sendMessageService.sendMessage(member.getName(), scontent);
			String state = result.get("Description");
			smsHistory.setState(state);
			messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
		} catch (Exception e) {
			logger.error("help(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

	@Override
	public void finishRepay(String businessOID, String memberOID, BigDecimal money) {
		try {
			controlPay.unFreeze(memberOID, money);
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setInMemberOID(memberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("还款完成");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

			controlCustomerBusiness.saveCustomerBusiness("回款", money, "回款的本金利息金额", memberOID);
		} catch (Exception e) {
			logger.error("help(String businessOID, String memberOID, BigDecimal money)" + e.getMessage());
		}
	}

}
