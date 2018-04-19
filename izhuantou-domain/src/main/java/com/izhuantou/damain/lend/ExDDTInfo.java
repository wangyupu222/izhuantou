package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * DDT元借款人信息
 * 
 * @author yangbosen
 *
 */
public class ExDDTInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String style;
    /**
     * 标的描述
     */
    private String loanuse;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private String age;
    /**
     * 
     */
    private String dywxx;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 户口所在地
     */
    private String hkszd;
    /**
     * 婚姻状况
     */
    private String hyzk;
    /**
     * 教育程度
     */
    private String education;
    /**
     * 
     */
    private String jycd;
    /**
     * 
     */
    private String jkqy;
    /**
     * 
     */
    private String clsj;
    /**
     * 
     */
    private String qydz;
    /**
     * 所属行业
     */
    private String sshy;
    /**
     * 
     */
    private String zczb;
    /**
     * 
     */
    private String xyzk;

    public String getJkqy() {
	return jkqy;
    }

    public void setJkqy(String jkqy) {
	this.jkqy = jkqy;
    }

    public String getClsj() {
	return clsj;
    }

    public void setClsj(String clsj) {
	this.clsj = clsj;
    }

    public String getQydz() {
	return qydz;
    }

    public void setQydz(String qydz) {
	this.qydz = qydz;
    }

    public String getSshy() {
	return sshy;
    }

    public void setSshy(String sshy) {
	this.sshy = sshy;
    }

    public String getZczb() {
	return zczb;
    }

    public void setZczb(String zczb) {
	this.zczb = zczb;
    }

    public String getXyzk() {
	return xyzk;
    }

    public void setXyzk(String xyzk) {
	this.xyzk = xyzk;
    }

    public String getStyle() {
	return style;
    }

    public void setStyle(String style) {
	this.style = style;
    }

    public String getLoanuse() {
	return loanuse;
    }

    public void setLoanuse(String loanuse) {
	this.loanuse = loanuse;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getAge() {
	return age;
    }

    public void setAge(String age) {
	this.age = age;
    }

    public String getDywxx() {
	return dywxx;
    }

    public void setDywxx(String dywxx) {
	this.dywxx = dywxx;
    }

    public String getIdcard() {
	return idcard;
    }

    public void setIdcard(String idcard) {
	this.idcard = idcard;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getHkszd() {
	return hkszd;
    }

    public void setHkszd(String hkszd) {
	this.hkszd = hkszd;
    }

    public String getHyzk() {
	return hyzk;
    }

    public void setHyzk(String hyzk) {
	this.hyzk = hyzk;
    }

    public String getEducation() {
	return education;
    }

    public void setEducation(String education) {
	this.education = education;
    }

    public String getJycd() {
	return jycd;
    }

    public void setJycd(String jycd) {
	this.jycd = jycd;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ExDDTInfo [style=" + style + ", loanuse=" + loanuse + ", realName=" + realName + ", gender=" + gender
		+ ", age=" + age + ", dywxx=" + dywxx + ", idcard=" + idcard + ", mobile=" + mobile + ", hkszd=" + hkszd
		+ ", hyzk=" + hyzk + ", education=" + education + ", jycd=" + jycd + ", jkqy=" + jkqy + ", clsj=" + clsj
		+ ", qydz=" + qydz + ", sshy=" + sshy + ", zczb=" + zczb + ", xyzk=" + xyzk + "]";
    }

}
