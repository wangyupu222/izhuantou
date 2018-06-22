package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 后台客户列表
 * 
 * @author sweet
 * @date 2018年6月11日
 *
 */
public class CustomerListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3722207189642268895L;

	/**
	 * oid
	 */
	private String OID;
	/**
	 * 会员账号
	 */
	private String memberAccount;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 客户类型
	 */
	private String userType;
	/**
	 * 身份证
	 */
	private String idCard;
	/**
	 * 银行卡
	 */
	private String bankNumber;
	/**
	 * 注册时间
	 */
	private String addDateTime;
	/**
	 * 生日
	 * 
	 */
	private String birthday;
	/**
	 * 年龄
	 * 
	 */
	private String age;
	/**
	 * 性别
	 * 
	 */
	private String sex;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 风险测评
	 * 
	 */
	private String riskAppraisal;
	/**
	 * 邀请人
	 * 
	 */
	private String inviter;
	/**
	 * 邀请码（客户经理编号）
	 */
	private String yewuOID;

	public String getYewuOID() {
		return yewuOID;
	}

	public void setYewuOID(String yewuOID) {
		this.yewuOID = yewuOID;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRiskAppraisal() {
		return riskAppraisal;
	}

	public void setRiskAppraisal(String riskAppraisal) {
		this.riskAppraisal = riskAppraisal;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}

}
