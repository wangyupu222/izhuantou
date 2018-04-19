package com.izhuantou.damain.mobile.personalCenter;

import com.izhuantou.damain.BasePojo;

public class MobileTyj extends BasePojo {
    /**
     * 手机端体验金
     */
    private static final long serialVersionUID = 1L;
    /**
     * :体验金人数
     */
    private String TyjSize;
    /**
     * ：体验标资格
     */
    private String tybInfo;
    /**
     * ：体验天数
     */
    private String TYDay;
    /**
     * ：体验金额
     */
    private String TYJPrincipalMoney;
    /**
     * ：体验金收益
     */
    private String TYJSY;
    /**
     * ：活动状态
     */
    private String TYJhdzt;
    /**
     * 体验金年化}
     */
    private String TYJnhl;
    /**
     * 体验金标记(0,1,2,3,4,5)
     */
    private String TyjFlag;

    public String getTyjSize() {
	return TyjSize;
    }

    public void setTyjSize(String tyjSize) {
	TyjSize = tyjSize;
    }

    public String getTybInfo() {
	return tybInfo;
    }

    public void setTybInfo(String tybInfo) {
	this.tybInfo = tybInfo;
    }

    public String getTYDay() {
	return TYDay;
    }

    public void setTYDay(String tYDay) {
	TYDay = tYDay;
    }

    public String getTYJPrincipalMoney() {
	return TYJPrincipalMoney;
    }

    public void setTYJPrincipalMoney(String tYJPrincipalMoney) {
	TYJPrincipalMoney = tYJPrincipalMoney;
    }

    public String getTYJSY() {
	return TYJSY;
    }

    public void setTYJSY(String tYJSY) {
	TYJSY = tYJSY;
    }

    public String getTYJhdzt() {
	return TYJhdzt;
    }

    public void setTYJhdzt(String tYJhdzt) {
	TYJhdzt = tYJhdzt;
    }

    public String getTYJnhl() {
	return TYJnhl;
    }

    public void setTYJnhl(String tYJnhl) {
	TYJnhl = tYJnhl;
    }

    public String getTyjFlag() {
	return TyjFlag;
    }

    public void setTyjFlag(String tyjFlag) {
	TyjFlag = tyjFlag;
    }

    @Override
    public String toString() {
	return "MobileTyj [TyjSize=" + TyjSize + ", tybInfo=" + tybInfo + ", TYDay=" + TYDay + ", TYJPrincipalMoney="
		+ TYJPrincipalMoney + ", TYJSY=" + TYJSY + ", TYJhdzt=" + TYJhdzt + ", TYJnhl=" + TYJnhl + ", TyjFlag="
		+ TyjFlag + "]";
    }

}
