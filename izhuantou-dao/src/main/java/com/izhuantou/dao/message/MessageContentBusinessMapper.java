package com.izhuantou.dao.message;

import com.izhuantou.damain.message.MessageContentBusiness;

/**
 * 消息相关表实体
 * 
 * @author dear
 * @version 1.0
 */
public interface MessageContentBusinessMapper {
    /**
     * 保存一条操作消息到数据库
     * 
     * @param business
     * @return
     */
    public int saveMessageBusiness(MessageContentBusiness business);

}
