package com.izhuantou.fund.rpc.redismq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.redismq.MessageType;
import com.koomii.redismq.Message;
import com.koomii.redismq.RedisMQMessageSender;

/**
 * 
 * @author fucheng
 * 测试消息发送
 * @since 2018年5月8日
 *
 */

@Service
public class MessageTest {
	
	@Autowired
	RedisMQMessageSender messageSender;

	public String sendMessage(String msg) {
		Long result = null;
		try {
			if(msg == null || msg == "") {
				msg = "NO MESSAGE";
			}
			result = messageSender.put(new Message(MessageType.DEMO_MESSAGE, msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result != null) {
			return "success:"+result;
		}else {
			return "fail";
		}
	}
}
