package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import java.util.Map;

import com.izhuantou.damain.mobile.personalCenter.MobileLoginReturnDTO;
import com.izhuantou.damain.mobile.personalCenter.MobilequestionDTO;

public interface MobileUserService {

    /**
     * 手机端用户登录
     * 
     * @param name
     * @param password
     * @return
     */
    public MobileLoginReturnDTO mobileUserLogin(String name, String password);

    /***
     * 验证用户是否存在
     * 
     * @param phone
     * @return
     */
    public String checkMemberExist(String phone);

    /**
     * 查看用户的密码问题
     * 
     * @param phone
     * @return
     */
    public Map<String, String> findAppTwoQuestion(String phone);

    /**
     * 验证用户回答的问题
     * 
     * @param answerOne
     * @param answerTwo
     * @return
     */
    public Map<String, String> checkAppTwoQuestion(String phone, String answerOne, String answerTwo);

    /**
     * 根据用户修改密码
     * 
     * @param phone
     * @param password
     * @return
     */
    public Map<String, String> updatePassword(String phone, String password);

    /**
     * 设置密保问题
     * 
     * @param question
     * @return
     */
    public String MobileSetSecurityQuestion(MobilequestionDTO question);

}
