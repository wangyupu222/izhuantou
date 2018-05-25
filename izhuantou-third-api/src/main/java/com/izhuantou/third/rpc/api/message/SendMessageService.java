package com.izhuantou.third.rpc.api.message;

import java.util.Map;

public interface SendMessageService {
	/**
	 * 需要替换
	 * @param mobile
	 * @param content
	 * @param reDate
	 * @return
	 */
	 String sendMessage(String mobile, String contentKey,Map<String,String> reDate);
	/**
	 * 不需要替换
	 * @param mobile
	 * @param content
	 * @return
	 */
	Map<String,String> sendMessage(String mobile,String content);
	
	/**
	 * 魔积分注册要发的短信
	 * @param mobile
	 */
	void spreadRegistWeb(String mobile);
	/**
	 * 天涯和正常用户的
	 * @param mobile
	 */
	void tianyaRegist(String mobile);
}
