package com.izhuantou.damain.lend;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 原借款人信息
 * 
 * @author yangbosen
 *
 */
public class ExBorrowerInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
    private Integer age;
    /**
     * 民族
     */
    private String minzu;
    /**
     * 身份证好
     */
    private BigDecimal idcard;
    /**
     * 手机号
     */
    private BigDecimal mobile;
    /**
     * 学历
     */
    private String highesteduback;
    /**
     * 户口所在地
     */
    private String hkszd;
    /**
     * 工作城市
     */
    private String gzcs;
    /**
     * 工作年限
     */
    private String ljgznx;
    /**
     * 职位
     */
    private String zhiwei;
    /**
     * 公司性质
     */
    private String dwxz;
    /**
     * 所属行业
     */
    private String sshy;
    /**
     * 个人收入
     */
    private BigDecimal grsr;
    /**
     * 有无购车
     */
    private String ywgc;
    /**
     * 有无购房
     */
    private String ywgf;

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

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public String getMinzu() {
	return minzu;
    }

    public void setMinzu(String minzu) {
	this.minzu = minzu;
    }

    public BigDecimal getIdcard() {
	return idcard;
    }

    public void setIdcard(BigDecimal idcard) {
	this.idcard = idcard;
    }

    public BigDecimal getMobile() {
	return mobile;
    }

    public void setMobile(BigDecimal mobile) {
	this.mobile = mobile;
    }

    public String getHighesteduback() {
	return highesteduback;
    }

    public void setHighesteduback(String highesteduback) {
	this.highesteduback = highesteduback;
    }

    public String getHkszd() {
	return hkszd;
    }

    public void setHkszd(String hkszd) {
	this.hkszd = hkszd;
    }

    public String getGzcs() {
	return gzcs;
    }

    public void setGzcs(String gzcs) {
	this.gzcs = gzcs;
    }

    public String getLjgznx() {
	return ljgznx;
    }

    public void setLjgznx(String ljgznx) {
	this.ljgznx = ljgznx;
    }

    public String getZhiwei() {
	return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
	this.zhiwei = zhiwei;
    }

    public String getDwxz() {
	return dwxz;
    }

    public void setDwxz(String dwxz) {
	this.dwxz = dwxz;
    }

    public String getSshy() {
	return sshy;
    }

    public void setSshy(String sshy) {
	this.sshy = sshy;
    }

    public BigDecimal getGrsr() {
	return grsr;
    }

    public void setGrsr(BigDecimal grsr) {
	this.grsr = grsr;
    }

    public String getYwgc() {
	return ywgc;
    }

    public void setYwgc(String ywgc) {
	this.ywgc = ywgc;
    }

    public String getYwgf() {
	return ywgf;
    }

    public void setYwgf(String ywgf) {
	this.ywgf = ywgf;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "ExBorrowerInfo [loanuse=" + loanuse + ", realName=" + realName + ", gender=" + gender + ", age=" + age
		+ ", minzu=" + minzu + ", idcard=" + idcard + ", mobile=" + mobile + ", highesteduback="
		+ highesteduback + ", hkszd=" + hkszd + ", gzcs=" + gzcs + ", ljgznx=" + ljgznx + ", zhiwei=" + zhiwei
		+ ", dwxz=" + dwxz + ", sshy=" + sshy + ", grsr=" + grsr + ", ywgc=" + ywgc + ", ywgf=" + ywgf + "]";
    }

}
