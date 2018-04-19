package com.izhuantou.damain.mobile.personalCenter;

import java.io.Serializable;

public class MobileUpdatePwdDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2010736649040795066L;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 类型
     */
    private String question;
    /**
     * 新密码
     */
    private String newpassword;
    /**
     * 答案一
     */
    private String answerOne;
    /**
     * 答案二
     */
    private String answerTwo;
    /**
     * 秘钥
     */
    private String pkversion;

    public String getPkversion() {
	return pkversion;
    }

    public void setPkversion(String pkversion) {
	this.pkversion = pkversion;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public String getNewpassword() {
	return newpassword;
    }

    public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
    }

    public String getAnswerOne() {
	return answerOne;
    }

    public void setAnswerOne(String answerOne) {
	this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
	return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
	this.answerTwo = answerTwo;
    }

    @Override
    public String toString() {
	return "MobileUpdatePwdDTO [phone=" + phone + ", question=" + question + ", newpassword=" + newpassword
		+ ", answerOne=" + answerOne + ", answerTwo=" + answerTwo + ", pkversion=" + pkversion + "]";
    }

}
