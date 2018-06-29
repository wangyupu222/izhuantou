package com.izhuantou.third.rpc.impl.message;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.constant.Constant;
import com.izhuantou.common.tool.ToolString;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.message.MessageSmsHistory;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.user.MemberIsSend;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.PropPrivilegeMapper;
import com.izhuantou.dao.message.MessageContentBusinessMapper;
import com.izhuantou.dao.message.MessageSmsHistoryMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.user.MemberIsSendMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.third.rpc.api.message.SendMessageService;
import com.izhuantou.third.rpc.impl.ReadPropertiesl;

@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {
	private static final Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);
	@Autowired
	private MemberIsSendMapper sendSmsDao;
	@Autowired
	private MemberMemberMapper userDao;
	@Autowired
	private PayPrivilegeMapper privilegeMapper;
	@Autowired
	private PayPrivilegeMemberMappingMapper payPrivilegeMapper;
	@Autowired
	private MessageContentBusinessMapper messageContentBusinessMapper;
	@Autowired
	private MessageSmsHistoryMapper messageSmsHistoryMapper;
	@Autowired
	private PropPrivilegeMapper  propPrivilegeMapper;
	@Override
	public String sendMessage(String mobile, String contentKey, Map<String, String> reDate) {
		try {
			if (StringUtil.isEmpty(mobile) && !reDate.isEmpty()) {
				return null;
			}
			// name是发送那种短信
			if (StringUtil.isEmpty(contentKey)) {
				contentKey =Constant.SMS_VALIDATE_CODE;
				MemberMember member = userDao.findUserByName(mobile);
				if (member != null) {
					return "该手机号已注册";
				}
			}
			// 记录次数
			int c = 0;
			MemberMember member = userDao.findUserByName(mobile);
			Map<String, String> result = null;

			if (member != null) {
				result = this.messageContent(mobile, contentKey, reDate);
			} else {
				MemberIsSend issend = sendSmsDao.gainMemberIsSendByName(mobile);
				if (issend != null) {
					if (!issend.getSendCount().isEmpty()) {
						c = Integer.parseInt(issend.getSendCount());
					} else {
						c = 0;
					}
					if (c < 3) {
						result = this.messageContent(mobile, contentKey, reDate);
						int x = c + 1;
						issend.setSendCount(Integer.toString(x));
						sendSmsDao.updateSendTime(issend);
					} else {
						return "注册只能发送3次";
					}
				} else {
					MemberIsSend issendnew = new MemberIsSend();
					result = this.messageContent(mobile, contentKey, reDate);
					issendnew.setName(mobile);
					int x = c + 1;
					issendnew.setSendCount(Integer.toString(x));
					String OID = UUID.randomUUID().toString();
					OID = OID.replace("-", "");
					issendnew.setOID(OID);
					sendSmsDao.saveSendTime(issendnew);
				}
			}
			return result.get("SuccessCounts");
		} catch (Exception e) {
			logger.error(" Send(String mobile, String name)" + e.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, String> sendMessage(String mobile, String content) {
		try {
			Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("sms.properties");
			String userName = smsMap.get("account");
			String password = smsMap.get("password");
			Map<String, String> msm = new HashMap<String, String>();
			msm.put("mobiles", mobile);
			msm.put("userName", userName);
			msm.put("password", password);
			msm.put("content", content);
			msm.put("url",smsMap.get("url"));
			return MessageClient.sendSMSContent(msm);
		} catch (Exception e) {
			logger.error("SendMessageImpl中sessMessage方法调用短信接口失败" + e.getMessage());
		}
		return null;
	}

	private Map<String, String> messageContent(String mobile, String contentKey, Map<String, String> reDate) {
		try {
			Map<String, String> smsMap = ReadPropertiesl.gainPropertiesMap("sms.properties");
			String userName = smsMap.get("account");
			String password = smsMap.get("password");
			String content = smsMap.get(contentKey);
			String strContent=ToolString.replaceContent(content, reDate);
			Map<String, String> msm = new HashMap<String, String>();
			msm.put("mobiles", mobile);
			msm.put("userName", userName);
			msm.put("password", password);
			msm.put("content", strContent);
			msm.put("url",smsMap.get("url"));
			return MessageClient.sendSMSContent(msm);
		} catch (Exception e) {
			logger.error("SendMessageImpl中sessMessage方法调用短信接口失败" + e.getMessage());
		}
		return null;
	}

	@Override
	public void spreadRegistWeb(String mobile) {
		try {
			if (StringUtil.isNotEmpty(mobile)) {
				MemberMember member = userDao.findUserByName(mobile);
				Map<String, String> propMap = propPrivilegeMapper.findPrivilege();
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
						String st = DateUtils.formatDate(member.getAddDateTime(), "");
						int day = Integer.valueOf(days);
						if ("zcty".equals(zc[i1])) {
							day = Integer.valueOf(tyDays);
						}

						PayPrivilegeMemberMapping privi = new PayPrivilegeMemberMapping();
						String oid = StringUtil.getUUID();
						privi.setOID(oid);
						privi.setPrivilegeOID(tqOID);
						privi.setMemberOID(member.getOID());
						privi.setIsUsed("0");
						String et = DateUtils.gainPlusAndDateTime(st, day, 0);
						Date endDate = DateUtils.getJustDate(et);
						privi.setStartDate(member.getAddDateTime());
						privi.setEndDate(endDate);
						privi.setUseTerm(i);
						payPrivilegeMapper.saveUserPrivelege(privi);
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

					messageContentBusinessMapper.saveMessageBusiness(messageBusiness);

					MessageSmsHistory smsHistory = new MessageSmsHistory();
					String sOID = StringUtil.getUUID();
					smsHistory.setOID(sOID);
					smsHistory.setTitle("您已获得新用户加息劵");
					String scontent = "【砖头网】尊敬的用户：感谢您成功注册砖头网， 9%的加息券（2%+3%+4%）已返到您的账户中，详情请在【我的特权】-【加息券】中进行查看！出借即可使用(App端需v2.0.0以上版本)，"
							+ days + "天内有效。如有问题，请联系客服400-900-9677，感谢您的支持，祝您生活愉快！";
					smsHistory.setContent(scontent);
					smsHistory.setSendUser("系统");
					smsHistory.setReceiveUser(member.getName());
					Map<String, String> result = this.sendMessage(mobile, scontent);
					String state = result.get("Description");
					smsHistory.setState(state);
					messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);

				}
			}
		} catch (Exception e) {
			logger.error("tianyaRegist(String mobile)", e.getMessage());
		}
	}

	@Override
	public void tianyaRegist(String mobile) {
		try {
			if (StringUtil.isNotEmpty(mobile)) {
				MemberMember member = userDao.findUserByName(mobile);
				Map<String, String> propMap = propPrivilegeMapper.findPrivilege();
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
						String st = DateUtils.formatDate(member.getAddDateTime(), "");
						int day = Integer.valueOf(days);
						if ("zcty".equals(zc[i1])) {
							day = Integer.valueOf(tyDays);
						}
						PayPrivilegeMemberMapping privi = new PayPrivilegeMemberMapping();
						String oid = StringUtil.getUUID();
						privi.setOID(oid);
						privi.setPrivilegeOID(tqOID);
						privi.setMemberOID(member.getOID());
						privi.setIsUsed("0");
						privi.setStartDate(member.getAddDateTime());
						String et = DateUtils.gainPlusAndDateTime(st, day, 0);
						Date endTime = DateUtils.getJustDate(et);
						privi.setEndDate(endTime);
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
					messageContentBusinessMapper.saveMessageBusiness(messageBusiness);
					MessageSmsHistory smsHistory = new MessageSmsHistory();
					String sOID = StringUtil.getUUID();
					smsHistory.setOID(sOID);
					smsHistory.setTitle("您已获得新用户加息劵");
					String scontent = "【砖头网】尊敬的用户：感谢您注册砖头网， 9%加息券（2%+3%+4%）及天涯3%专享券已发放到您的账户中，详情请在【我的特权】-【加息券】中进行查看！如有问题，请联系客服400-900-9677，感谢您的支持，祝您生活愉快。退订回N";
					smsHistory.setContent(scontent);
					smsHistory.setSendUser("系统");
					smsHistory.setReceiveUser(member.getName());
					Map<String, String> result = this.sendMessage(mobile, scontent);
					String state = result.get("Description");
					smsHistory.setState(state);
					messageSmsHistoryMapper.saveSMSMessageHistory(smsHistory);
				}
			}
		} catch (Exception e) {
			logger.error("tianyaRegist(String mobile)", e.getMessage());
		}

	}

}
