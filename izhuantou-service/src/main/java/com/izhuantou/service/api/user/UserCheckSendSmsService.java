package com.izhuantou.service.api.user;

public interface UserCheckSendSmsService {

    /**
     * 发送验证码 手机号，短信类型，短信内容
     * 
     * @param name
     * @param msmType
     * @param strValidateCode
     * @return
     */
    public String Send(String name, String msmType, String strValidateCode);

}
