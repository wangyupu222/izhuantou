package com.izhuantou.damain.vo;

import java.io.Serializable;

public class UserDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -505580488531509324L;

    /**
     * 用户OID
     */
    private String OID;

    /**
     * 用户登陆名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;
    /**
     * 密码
     */
    private String password2;

    /**
     * 验证码
     */
    private String yzm;

    /**
     * 短信验证码
     */
    private String msm;
    /**
     * 短信发送的种类
     */
    private String msmType;
    /**
     * 推荐人
     */
    private String yqmReg;

    /**
     * 是否记住密码
     */
    private String isRememberMe;
    /**
     * 状态
     */
    private String state;

    /**
     * 答案一
     */
    private String answerOne;
    /**
     * 答案二
     */
    private String answerTwo;

    /**
     * 答案一
     */
    private String questionOne;
    /**
     * 答案二
     */
    private String questionTwo;

    /**
     * 用户来源
     */
    private String Channel;

    /**
     * 用户来源
     */
    private String typeVal;

    /**
     * 用户来源
     */
    private String register;

    public String getRegister() {
	return register;
    }

    public void setRegister(String register) {
	this.register = register;
    }

    public String getTypeVal() {
	return typeVal;
    }

    public void setTypeVal(String typeVal) {
	this.typeVal = typeVal;
    }

  
    public String getChannel() {
	return Channel;
    }

    public void setChannel(String channel) {
	Channel = channel;
    }

    public String getQuestionOne() {
	return questionOne;
    }

    public void setQuestionOne(String questionOne) {
	this.questionOne = questionOne;
    }

    public String getQuestionTwo() {
	return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
	this.questionTwo = questionTwo;
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

    public String getPassword2() {
	return password2;
    }

    public void setPassword2(String password2) {
	this.password2 = password2;
    }

    public String getMsmType() {
	return msmType;
    }

    public void setMsmType(String msmType) {
	this.msmType = msmType;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getMsm() {
	return msm;
    }

    public void setMsm(String msm) {
	this.msm = msm;
    }

    public String getIsRememberMe() {
	return isRememberMe;
    }

    public void setIsRememberMe(String isRememberMe) {
	this.isRememberMe = isRememberMe;
    }

    public String getYzm() {
	return yzm;
    }

    public void setYzm(String yzm) {
	this.yzm = yzm;
    }

	public String getYqmReg() {
		return yqmReg;
	}

	public void setYqmReg(String yqmReg) {
		this.yqmReg = yqmReg;
	}

	@Override
	public String toString() {
		return "UserDTO [OID=" + OID + ", name=" + name + ", password=" + password + ", password2=" + password2
				+ ", yzm=" + yzm + ", msm=" + msm + ", msmType=" + msmType + ", yqmReg=" + yqmReg + ", isRememberMe="
				+ isRememberMe + ", state=" + state + ", answerOne=" + answerOne + ", answerTwo=" + answerTwo
				+ ", questionOne=" + questionOne + ", questionTwo=" + questionTwo + ", Channel=" + Channel
				+ ", typeVal=" + typeVal + ", register=" + register + "]";
	}

}
