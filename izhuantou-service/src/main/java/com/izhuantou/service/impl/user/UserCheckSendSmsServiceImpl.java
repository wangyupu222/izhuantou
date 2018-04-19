package com.izhuantou.service.impl.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolClient;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.user.MemberIsSend;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.dao.user.MemberIsSendMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.user.UserCheckSendSmsService;

@Service("userCheckSendSmsService")
public class UserCheckSendSmsServiceImpl implements UserCheckSendSmsService {
    private static final Logger logger = LoggerFactory.getLogger(UserCheckSendSmsServiceImpl.class);
    public static final String SMS_VALIDATE_CODE = "smsValidateCode";
    @Autowired
    private MemberIsSendMapper sendSmsDao;

    @Autowired
    private MemberMemberMapper userDao;

    @Override
    public String Send(String mobile, String name, String strValidateCode) {
	try {
	    if (StringUtil.isEmpty(mobile)) {
		return null;
	    }
	    // name是发送那种短信
	    if (StringUtil.isEmpty(name)) {
		name = SMS_VALIDATE_CODE;
		MemberMember member = userDao.findUserByName(mobile);
		if (member != null) {
		    return "账号已存在";
		}
	    }
	    // 将随机验证码Map发送用户，作为模板替换
	    Map<String, String> dtoDat = new HashMap<String, String>();
	    dtoDat.put(SMS_VALIDATE_CODE, strValidateCode);
	    String userName = ReadPropertiesl.gainSMSValue("account");
	    String password = ReadPropertiesl.gainSMSValue("password");
	    String strOld = ReadPropertiesl.gainSMSValue(name);
	    // 记录次数
	    int c = 0;
	    MemberMember member = userDao.findUserByName(mobile);
	    Map<String, String> result = null;

	    if (member != null) {
		result = ToolClient.sendSMS(mobile, userName, password, strOld, dtoDat);
	    } else {
		MemberIsSend issend = sendSmsDao.gainMemberIsSendByName(mobile);
		if (issend != null) {
		    if (!issend.getSendCount().isEmpty()) {
			c = Integer.parseInt(issend.getSendCount());
		    } else {
			c = 0;
		    }
		    if (c < 3) {
			result = ToolClient.sendSMS(mobile, userName, password, strOld, dtoDat);
			int x = c + 1;
			issend.setSendCount(Integer.toString(x));
			sendSmsDao.updateSendTime(issend);
		    } else {
			return "注册只能发送3次";
		    }
		} else {
		    MemberIsSend issendnew = new MemberIsSend();
		    result = ToolClient.sendSMS(mobile, userName, password, strOld, dtoDat);
		    issendnew.setName(mobile);
		    int x = c + 1;
		    issendnew.setSendCount(Integer.toString(x));
		    String OID = UUID.randomUUID().toString();
		    OID = OID.replace("-", "");
		    issendnew.setOID(OID);
		    sendSmsDao.saveSendTime(issendnew);
		}
	    }
	    /*
	     * System.out.println("返回信息提示：" + map.get("Description"));
	     * System.out.println("返回状态为：" + map.get("StatusCode"));
	     * System.out.println("返回余额：" + map.get("Amount"));
	     * System.out.println("返回本次任务ID：" + map.get("MsgId"));
	     * System.out.println("返回成功短信数：" + map.get("SuccessCounts"))
	     */
	    return result.get("SuccessCounts");
	} catch (Exception e) {
	    logger.error(" Send(String mobile, String name)" + e.getMessage());
	    return null;
	}

    }

}
