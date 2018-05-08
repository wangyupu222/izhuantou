package com.izhuantou.data.rpc.redismq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.redismq.MessageType;
import com.izhuantou.common.utils.JsonUtil;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.data.rpc.api.bidding.ControlPackageBiddingMainRuning;
import com.koomii.redismq.AbstarctMessageHandler;

/**
 * DemoMessage消息的处理器
 * 
 * @category @author fucheng
 * @since 2018年5月8日
 */
@Service
public class DemoHandler extends AbstarctMessageHandler {
    private static Log logger = LogFactory.getLog(DemoHandler.class);

    @Autowired
    private ControlPackageBiddingMainRuning controlPackageBidding;

    public DemoHandler() {
	// 说明该handler监控的消息类型
	super(MessageType.HH_BIDDING_MESSAGE, 10);
    }

    /**
     * 监听到消息后处理方法
     */
    @Override
    public void handle(String message) {
	System.err.println("接收到的信息为" + message);
	BiddingDTO biddingDTO = JsonUtil.toObj(message, BiddingDTO.class);
	// System.err.println("获取到的message为：" + biddingDTO);
	String s = this.controlPackageBidding.bidPackageBiddingDifferent(biddingDTO);
	System.out.println(s);

    }

    @Override
    public void handleFailed(String obj) {
	StringBuilder sb = new StringBuilder();
	sb.append("msg:[").append(obj).append("], 超过失败次数，停止重试。");
	logger.warn(sb.toString());
    }

}
