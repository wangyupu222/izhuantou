package com.izhuantou.damain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 用户表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "member_member")
public class MemberMember extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -3154830400273454498L;

    /**
     * 用户OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 用户登陆名称
     */
    private String name;
    /**
     * 客户OID
     */
    @Column(name = "clientOID")
    private String clientOID;
    /**
     * 会员编号（用户自定义编号或生成编号，如员工编号）
     */
    @Column(name = "memberNumber")
    private String memberNumber;
    /**
     * 会员账号（英文+数字组合，登录账号）"ZT132412341"
     */
    @Column(name = "memberAccount")
    private String memberAccount;
    /**
     * 会员昵称
     */
    @Column(name = "nickName")
    private String nickName;
    /**
     * 真实姓名
     */
    @Column(name = "realName")
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名（中文）
     */
    @Column(name = "nameCN")
    private String nameCN;

    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;
    /**
     * 身份证
     */
    @Column(name = "idCard")
    private String idCard;
    /**
     * 电话
     */
    private String mobile;
    /**
     * Email
     */
    private String email;
    /**
     * 婚姻状况
     */
    private String marriage;
    /**
     * 地址
     */
    private String address;
    /**
     * 推荐人
     */
    private String referrals;
    /**
     * 客户经理
     */
    private String provider;
    /**
     * 所在城市
     */
    private String city;

    /**
     * 注册时间
     */
    @Column(name = "registerTime")
    private Date registerTime;
    /**
     * 用户类型（0：普通用户，1：财务用户，2：保理用户）
     */
    @Column(name = "userType")
    private String userType;
    /**
     * 借贷投资类型（0：全是，1：出借用户，2：借款用户;默认是1）
     */
    @Column(name = "loanInvestmentType")
    private String loanInvestmentType;

    /**
     * 职务
     */
    @Column(name = "viewType")
    private String viewType;

    /**
     * 真实OID
     */
    @Column(name = "realOID")
    private String realOID;

    /**
     * 状态
     */
    private String state;
    /**
     * 邀请码（客户经理编号）
     */
    @Column(name = "yewuOID")
    private String yewuOID;

    /**
     * 最高学历
     */
    private String xieli;
    /**
     * 毕业院校
     */
    private String school;

    /**
     * 公司行业
     */
    private String company;

    /**
     * 公司规模
     */
    private String comscale;

    /**
     * 职位
     */
    private String position;
    /**
     * 月收入
     */
    private String income;
    /**
     * 是否购房
     */
    private String house;
    /**
     * 是否购车
     */
    private String car;

    /**
     * 头像地址
     */
    @Column(name = "picOID")
    private String picOID;

    /**
     * 密保1
     */
    @Column(name = "questionOne")
    private String questionOne;

    /**
     * 答案1
     */
    @Column(name = "answerOne")
    private String answerOne;

    /**
     * 密保2
     */
    @Column(name = "questionTwo")
    private String questionTwo;

    /**
     * 答案2
     */
    @Column(name = "answerTwo")
    private String answerTwo;
    /**
     * 黑名单
     */
    private String blacklist;
    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "addUserOID")
    private String addUserOID;
    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */
    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 添加的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;

    /**
     * 是否更新
     */
    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 面剩余输错的次数
     */
    private String opportunity;
    
    public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
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

    public String getClientOID() {
	return clientOID;
    }

    public void setClientOID(String clientOID) {
	this.clientOID = clientOID;
    }

    public String getMemberNumber() {
	return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
	this.memberNumber = memberNumber;
    }

    public String getMemberAccount() {
	return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
	this.memberAccount = memberAccount;
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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public Date getBirthday() {
	return birthday;
    }

    public void setBirthday(Date birthday) {
	this.birthday = birthday;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getMarriage() {
	return marriage;
    }

    public void setMarriage(String marriage) {
	this.marriage = marriage;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getReferrals() {
	return referrals;
    }

    public void setReferrals(String referrals) {
	this.referrals = referrals;
    }

    public String getProvider() {
	return provider;
    }

    public void setProvider(String provider) {
	this.provider = provider;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public Date getRegisterTime() {
	return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
	this.registerTime = registerTime;
    }

    public String getUserType() {
	return userType;
    }

    public void setUserType(String userType) {
	this.userType = userType;
    }

    public String getLoanInvestmentType() {
	return loanInvestmentType;
    }

    public void setLoanInvestmentType(String loanInvestmentType) {
	this.loanInvestmentType = loanInvestmentType;
    }

    public String getViewType() {
	return viewType;
    }

    public void setViewType(String viewType) {
	this.viewType = viewType;
    }

    public String getRealOID() {
	return realOID;
    }

    public void setRealOID(String realOID) {
	this.realOID = realOID;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getYewuOID() {
	return yewuOID;
    }

    public void setYewuOID(String yewuOID) {
	this.yewuOID = yewuOID;
    }

    public String getXieli() {
	return xieli;
    }

    public void setXieli(String xieli) {
	this.xieli = xieli;
    }

    public String getSchool() {
	return school;
    }

    public void setSchool(String school) {
	this.school = school;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public String getComscale() {
	return comscale;
    }

    public void setComscale(String comscale) {
	this.comscale = comscale;
    }

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public String getIncome() {
	return income;
    }

    public void setIncome(String income) {
	this.income = income;
    }

    public String getHouse() {
	return house;
    }

    public void setHouse(String house) {
	this.house = house;
    }

    public String getCar() {
	return car;
    }

    public void setCar(String car) {
	this.car = car;
    }

    public String getPicOID() {
	return picOID;
    }

    public void setPicOID(String picOID) {
	this.picOID = picOID;
    }

    public String getQuestionOne() {
	return questionOne;
    }

    public void setQuestionOne(String questionOne) {
	this.questionOne = questionOne;
    }

    public String getAnswerOne() {
	return answerOne;
    }

    public void setAnswerOne(String answerOne) {
	this.answerOne = answerOne;
    }

    public String getQuestionTwo() {
	return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
	this.questionTwo = questionTwo;
    }

    public String getAnswerTwo() {
	return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
	this.answerTwo = answerTwo;
    }

    public String getBlacklist() {
	return blacklist;
    }

    public void setBlacklist(String blacklist) {
	this.blacklist = blacklist;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
    }

    public String getAddUserOID() {
	return addUserOID;
    }

    public void setAddUserOID(String addUserOID) {
	this.addUserOID = addUserOID;
    }

    public String getUpdUserOID() {
	return updUserOID;
    }

    public void setUpdUserOID(String updUserOID) {
	this.updUserOID = updUserOID;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
	public String toString() {
		return "MemberMember [OID=" + OID + ", name=" + name + ", clientOID=" + clientOID + ", memberNumber="
				+ memberNumber + ", memberAccount=" + memberAccount + ", nickName=" + nickName + ", realName="
				+ realName + ", password=" + password + ", nameCN=" + nameCN + ", gender=" + gender + ", age=" + age
				+ ", birthday=" + birthday + ", idCard=" + idCard + ", mobile=" + mobile + ", email=" + email
				+ ", marriage=" + marriage + ", address=" + address + ", referrals=" + referrals + ", provider="
				+ provider + ", city=" + city + ", registerTime=" + registerTime + ", userType=" + userType
				+ ", loanInvestmentType=" + loanInvestmentType + ", viewType=" + viewType + ", realOID=" + realOID
				+ ", state=" + state + ", yewuOID=" + yewuOID + ", xieli=" + xieli + ", school=" + school + ", company="
				+ company + ", comscale=" + comscale + ", position=" + position + ", income=" + income + ", house="
				+ house + ", car=" + car + ", picOID=" + picOID + ", questionOne=" + questionOne + ", answerOne="
				+ answerOne + ", questionTwo=" + questionTwo + ", answerTwo=" + answerTwo + ", blacklist=" + blacklist
				+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
				+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
				+ ", refresh=" + refresh + ", version=" + version + ", opportunity=" + opportunity + "]";
	}

}
