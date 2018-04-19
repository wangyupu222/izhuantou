package com.izhuantou.damain.mobile.personalCenter;

import com.izhuantou.damain.BasePojo;

public class MobileMyRepayment extends BasePojo {

    /**
     * 手机端还款实体
     */
    private static final long serialVersionUID = 1L;
    /**
     * yuqiflag：是否逾期
     */
    private String yuqiflag;
    /**
     * ：时间
     */
    private String repayDate;
    /**
     * ：金额
     */
    private String allmoney;
    /**
     * ：是否可还
     */
    private String but;
    /**
     * ：含利息
     */
    private String interestMoney;
    /**
     * ：还款OID
     */
    private String planOID;
    /**
     * ：借款时间
     */
    private String loandatetime;
    /**
     * ：总期数
     */
    private String term;
    /**
     * ：第几期
     */
    private String qs;
    /**
     * ：金额
     */
    private String principalMoney;
    /**
     * ：金额
     */
    private String serviceMoney;
    /**
     * ：金额
     */
    private String manageMoney;

    /**
     * 逾期本金
     */
    private String yqbj;
    /**
     * 逾期利息
     */
    private String yqlx;
    /**
     * 咨询服务费
     */
    private String glzxf;
    /**
     * 平台管理费
     */
    private String ptglf;
    /**
     * 违约金
     */
    private String wyj;
    /**
     * 逾期管理费
     */
    private String yqglf;
    /**
     * 续期罚息
     */
    private String yqfx;
    /**
     * 金额
     */
    private String allje;
    /**
     * 应还本金
     */
    private String yhbj;
    /**
     * 应还利息
     */
    private String yhlx;
    /**
     * 借款手续费
     */
    private String jksxf;
    /**
     * 还款总额
     */
    private String oncepunish;
    /**
     * 产品OID
     */
    private String businessOID;

    public String getYhbj() {
	return yhbj;
    }

    public void setYhbj(String yhbj) {
	this.yhbj = yhbj;
    }

    public String getYhlx() {
	return yhlx;
    }

    public void setYhlx(String yhlx) {
	this.yhlx = yhlx;
    }

    public String getJksxf() {
	return jksxf;
    }

    public void setJksxf(String jksxf) {
	this.jksxf = jksxf;
    }

    public String getOncepunish() {
	return oncepunish;
    }

    public void setOncepunish(String oncepunish) {
	this.oncepunish = oncepunish;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getYqbj() {
	return yqbj;
    }

    public void setYqbj(String yqbj) {
	this.yqbj = yqbj;
    }

    public String getYqlx() {
	return yqlx;
    }

    public void setYqlx(String yqlx) {
	this.yqlx = yqlx;
    }

    public String getGlzxf() {
	return glzxf;
    }

    public void setGlzxf(String glzxf) {
	this.glzxf = glzxf;
    }

    public String getPtglf() {
	return ptglf;
    }

    public void setPtglf(String ptglf) {
	this.ptglf = ptglf;
    }

    public String getWyj() {
	return wyj;
    }

    public void setWyj(String wyj) {
	this.wyj = wyj;
    }

    public String getYqglf() {
	return yqglf;
    }

    public void setYqglf(String yqglf) {
	this.yqglf = yqglf;
    }

    public String getYqfx() {
	return yqfx;
    }

    public void setYqfx(String yqfx) {
	this.yqfx = yqfx;
    }

    public String getAllje() {
	return allje;
    }

    public void setAllje(String allje) {
	this.allje = allje;
    }

    public String getYuqiflag() {
	return yuqiflag;
    }

    public void setYuqiflag(String yuqiflag) {
	this.yuqiflag = yuqiflag;
    }

    public String getRepayDate() {
	return repayDate;
    }

    public void setRepayDate(String repayDate) {
	this.repayDate = repayDate;
    }

    public String getAllmoney() {
	return allmoney;
    }

    public void setAllmoney(String allmoney) {
	this.allmoney = allmoney;
    }

    public String getBut() {
	return but;
    }

    public void setBut(String but) {
	this.but = but;
    }

    public String getInterestMoney() {
	return interestMoney;
    }

    public void setInterestMoney(String interestMoney) {
	this.interestMoney = interestMoney;
    }

    public String getPlanOID() {
	return planOID;
    }

    public void setPlanOID(String planOID) {
	this.planOID = planOID;
    }

    public String getLoandatetime() {
	return loandatetime;
    }

    public void setLoandatetime(String loandatetime) {
	this.loandatetime = loandatetime;
    }

    public String getTerm() {
	return term;
    }

    public void setTerm(String term) {
	this.term = term;
    }

    public String getQs() {
	return qs;
    }

    public void setQs(String qs) {
	this.qs = qs;
    }

    public String getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(String principalMoney) {
	this.principalMoney = principalMoney;
    }

    public String getServiceMoney() {
	return serviceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
	this.serviceMoney = serviceMoney;
    }

    public String getManageMoney() {
	return manageMoney;
    }

    public void setManageMoney(String manageMoney) {
	this.manageMoney = manageMoney;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MyRepayment [yuqiflag=" + yuqiflag + ", repayDate=" + repayDate + ", allmoney=" + allmoney + ", but="
		+ but + ", interestMoney=" + interestMoney + ", planOID=" + planOID + ", loandatetime=" + loandatetime
		+ ", term=" + term + ", qs=" + qs + ", principalMoney=" + principalMoney + ", serviceMoney="
		+ serviceMoney + ", manageMoney=" + manageMoney + ", yqbj=" + yqbj + ", yqlx=" + yqlx + ", glzxf="
		+ glzxf + ", ptglf=" + ptglf + ", wyj=" + wyj + ", yqglf=" + yqglf + ", yqfx=" + yqfx + ", allje="
		+ allje + ", yhbj=" + yhbj + ", yhlx=" + yhlx + ", jksxf=" + jksxf + ", oncepunish=" + oncepunish
		+ ", businessOID=" + businessOID + "]";
    }

}
