package com.izhuantou.data.rpc.redismq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.izhuantou.common.redismq.MessageType;
import com.koomii.redismq.AbstarctMessageHandler;


/**
 * DemoMessage消息的处理器
 * 
 * @category @author fucheng
 * @since 2018年5月8日 
 */
@Service
public class DemoHandler extends AbstarctMessageHandler
{
	private static Log logger = LogFactory.getLog(DemoHandler.class);

	public DemoHandler()
	{
		// 说明该handler监控的消息类型
		super(MessageType.DEMO_MESSAGE, 10);
	}

	/**
	 * 监听到消息后处理方法
	 */
	@Override
	public void handle(String message)
	{
		System.out.println("正在处理消息：" + message);
	}

	@Override
	public void handleFailed(String obj)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("msg:[").append(obj).append("], 超过失败次数，停止重试。");
		logger.warn(sb.toString());
	}

}
