package com.izhuantou.dao.user;

import com.izhuantou.damain.user.MemberIsSend;

/**
 * 信息发送记录表
 * 
 * @author dear
 * @version 1.0
 */
public interface MemberIsSendMapper {
    /**
     * 获取短信发送的次数和其他信息
     * 
     * @param name
     * @return
     */
    public MemberIsSend gainMemberIsSendByName(String name);

    /**
     * 保存用户发送短息的信息
     * 
     * @param isSend
     * @return
     */
    public int saveSendTime(MemberIsSend isSend);

    /**
     * 更新发送的过的次数
     * 
     * @param isSend
     * @return
     */
    public int updateSendTime(MemberIsSend isSend);

}
