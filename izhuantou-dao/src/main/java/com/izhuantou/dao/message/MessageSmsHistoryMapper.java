package com.izhuantou.dao.message;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.message.MessageSmsHistory;

/**
 * 短息消息记录
 * 
 * @author dear
 * @version 1.0
 */
public interface MessageSmsHistoryMapper extends Mapper<MessageSmsHistory> {
    /**
     * 保存一条短信操作消息到数据库
     * 
     * @param business
     * @return
     */
    public int saveSMSMessageHistory(MessageSmsHistory smsHistory);

}
