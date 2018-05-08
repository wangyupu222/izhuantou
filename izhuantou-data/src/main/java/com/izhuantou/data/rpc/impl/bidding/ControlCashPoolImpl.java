package com.izhuantou.data.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolClient;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.data.rpc.api.bidding.ControlCashPool;
import com.izhuantou.data.rpc.api.bidding.ControlCustomerBusiness;
import com.izhuantou.data.rpc.impl.BaseServiceImpl;

@Service("controlCashPool")
public class ControlCashPoolImpl extends BaseServiceImpl<PayCashPool> implements ControlCashPool {
	@Autowired
	private PayCashPoolMapper payCashPoolDao;
	@Autowired
	private PayCashPoolOperationMapper paycashPoolOperationMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private ControlCustomerBusiness controlCustomerBusiness;
	@Autowired
	private MessageContentBusinessMapper messageContentBusinessMapper;
	@Autowired
	private MemberMemberMapper memberMapper;
	@Autowired
	private MessageSmsHistoryMapper messageSmsHistoryMapper;
	private static final Logger logger = LoggerFactory.getLogger(ControlCashPoolImpl.class);


	@Override
	public PayCashPool investment(BiddingDTO bidto) {
		PayCashPool dtoCashPool = null;
		try {
			if (bidto != null) {
				WebP2pPackageBiddingMainRuning maindto = packageBiddingMainRuningMapper
						.findByOID(bidto.getBiddingOID());
				WebP2pProductRateInfo productInfodto = productRateInfoMapper.findByOID(maindto.getProductRateInfoID());

				dtoCashPool = new PayCashPool();
				dtoCashPool.setOID(StringUtil.getUUID());
				dtoCashPool.setBusinessOID(bidto.getBiddingOID());
				dtoCashPool.setMemberOID(bidto.getOutMemberOID());
				dtoCashPool.setMoney(bidto.getAmount());
				dtoCashPool.setInterest(new BigDecimal("0"));
				dtoCashPool.setPrincipalMoney(bidto.getAmount());
				dtoCashPool.setInterestMoney(bidto.getInterest());
				dtoCashPool.setCreditRate(bidto.getCreditRate());
				dtoCashPool.setCreditType(bidto.getCreditType());
				dtoCashPool.setReturnCycle(bidto.getReturnCycle());
				dtoCashPool.setTotalInterestMoney(
						bidto.getInterest().multiply(new BigDecimal((String.valueOf(bidto.getReturnNumber()))))
								.setScale(2, BigDecimal.ROUND_HALF_EVEN));
				dtoCashPool.setPrivilegePrincipal(null);
				dtoCashPool.setPrivilegeInterest(null);
				dtoCashPool.setTqOID(bidto.getTqOID());
				dtoCashPool.setState("investment");
				dtoCashPool.setReturnNumber(bidto.getReturnNumber());
				dtoCashPool.setReturnSurplusNumber(bidto.getReturnNumber());
				dtoCashPool.setStartDateTime(bidto.getBeginDate());
				dtoCashPool.setEndDateTime(bidto.getEndDate());
				dtoCashPool.setLaiyuan(bidto.getLaiyuan());// App/Web

				// 额外加息 terry
				BigDecimal zero = new BigDecimal(0);
				if (null != maindto.getJXother() && (maindto.getJXother()).compareTo(zero) > 0) {
					BigDecimal lx = maindto.getJXother();
					double qishu = productInfodto.getProductTerm();
					dtoCashPool.setJXother(lx);
					BigDecimal creditRateTemp = lx.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN)
							.divide(new BigDecimal("100"), 8, BigDecimal.ROUND_HALF_EVEN);
					BigDecimal JXinterest = bidto.getAmount().multiply(creditRateTemp).multiply(new BigDecimal(qishu))
							.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					dtoCashPool.setJXother(lx);
					dtoCashPool.setJXinterest(JXinterest);
				} else {
					dtoCashPool.setJXother(new BigDecimal("0"));
				}
				payCashPoolDao.savePayCashPool(dtoCashPool);

				BigDecimal mone = new BigDecimal("0");

				PayCashPoolOperation dto = new PayCashPoolOperation();
				// TODO 此逻辑没有走过
				if (bidto.getPrivilegePrincipal() == null) {
					mone = bidto.getAmount();
				} else {
					mone = bidto.getAmount().subtract(bidto.getPrivilegePrincipal());
				}
				dto.setBusinessOID(bidto.getBiddingOID());
				dto.setOutMemberOID(bidto.getOutMemberOID());
				dto.setMoney(mone);
				dto.setContent("进行投资，冻结资金，准备投资");
				dto.setLaiyuan(bidto.getLaiyuan());
				dto.setOID(StringUtil.getUUID());
				paycashPoolOperationMapper.saveCashPoolOperation(dto);

				controlCustomerBusiness.saveCustomerBusiness("出借", mone, "用户出借的金额", bidto.getOutMemberOID());

				MessageContentBusiness messageBusiness = new MessageContentBusiness();
				String mOID = StringUtil.getUUID();
				messageBusiness.setOID(mOID);
				messageBusiness.setTitle("出借");
				String content = "您已经出借环环投，出借金额为" + bidto.getAmount() + "元";
				messageBusiness.setContent(content);
				messageBusiness.setSendUser("系统");
				messageBusiness.setReceiveUserOID(bidto.getOutMemberOID());

				messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

				MemberMember member = memberMapper.findUserByOID(bidto.getOutMemberOID());
				Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
				String userName = smsMap.get("account");
				String password = smsMap.get("password");

				MessageSmsHistory smsHistory = new MessageSmsHistory();
				String sOID = StringUtil.getUUID();
				smsHistory.setOID(sOID);
				smsHistory.setTitle("出借");
				String scontent = "【砖头网】您已经出借环环投，出借金额为" + bidto.getAmount() + "元";
				smsHistory.setContent(scontent);
				smsHistory.setSendUser("系统");
				smsHistory.setReceiveUser(member.getName());
				Map<String, String> result = ToolClient.sendSMS(member.getName(), userName, password, scontent, null);
				String state = result.get("Description");
				smsHistory.setState(state);
				messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
				logger.info(state);
			}
		} catch (Exception e) {
			logger.error("PayCashPool investment(BiddingDTO bidto)" + e.getMessage());
		}
		return dtoCashPool;
	}


	@Override
	public void transferReturn(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
		try {
			PayCashPoolOperation operation = new PayCashPoolOperation();
			operation.setBusinessOID(businessOID);
			operation.setOutMemberOID(outMemberOID);
			operation.setInMemberOID(inMemberOID);
			operation.setMoney(money);
			operation.setContent("债转冻结");
			operation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(operation);

			WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(businessOID);
			if (novice != null) {
				controlCustomerBusiness.saveCustomerBusiness("出借", money, "用户出借的金额", outMemberOID);

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
				Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
				String userName = smsMap.get("account");
				String password = smsMap.get("password");

				MessageSmsHistory smsHistory = new MessageSmsHistory();
				String sOID = StringUtil.getUUID();
				smsHistory.setOID(sOID);
				smsHistory.setTitle("出借");
				String scontent = "您已经出借头笔赚，出借金额为" + money + "元";
				smsHistory.setContent(scontent);
				smsHistory.setSendUser("系统");
				smsHistory.setReceiveUser(member.getName());
				Map<String, String> result = ToolClient.sendSMS(member.getName(), userName, password, scontent, null);
				String state = result.get("Description");
				smsHistory.setState(state);
				messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
				logger.info(state);
			}
		} catch (Exception e) {
			logger.error("transferReturn(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money)"
					+ e.getMessage());
		}
	}

	@Override
	public void discount(String businessOID, String memberOID, BigDecimal money) {
		try {
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
	public void investmentNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
		try {
			PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
			cashPoolOperation.setBusinessOID(businessOID);
			cashPoolOperation.setOutMemberOID(outMemberOID);
			cashPoolOperation.setInMemberOID(inMemberOID);
			cashPoolOperation.setMoney(money);
			cashPoolOperation.setContent("进行投资，划拨冻结");
			cashPoolOperation.setOID(StringUtil.getUUID());
			paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

			controlCustomerBusiness.saveCustomerBusiness("出借", money, "用户出借的金额", outMemberOID);
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
			Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("SMS.properties");
			String userName = smsMap.get("account");
			String password = smsMap.get("password");

			MessageSmsHistory smsHistory = new MessageSmsHistory();
			String sOID = StringUtil.getUUID();
			smsHistory.setOID(sOID);
			smsHistory.setTitle("出借");
			String scontent = "您已经出借头笔赚，出借金额为" + money + "元";
			smsHistory.setContent(scontent);
			smsHistory.setSendUser("系统");
			smsHistory.setReceiveUser(member.getName());
			Map<String, String> result = ToolClient.sendSMS(member.getName(), userName, password, scontent, null);
			String state = result.get("Description");
			smsHistory.setState(state);
			messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
			logger.info(state);
		} catch (Exception e) {
			logger.error("investmentNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money)"
					+ e.getMessage());
		}

	}
}
