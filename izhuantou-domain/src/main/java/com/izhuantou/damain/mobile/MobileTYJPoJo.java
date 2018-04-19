package com.izhuantou.damain.mobile;

import com.izhuantou.damain.BasePojo;

/**
 * 移动端体验金实体
 * 
 * @author yangbosen
 *
 */
public class MobileTYJPoJo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 体验天数
     */
    private String TYDay;
    /**
     * 体验金额
     */
    private String TYJPrincipalMoney;
    /**
     * 体验金收益
     */
    private String TYJSY;
    /**
     * 是否有体验资格
     */
    private String TYJType;
    /**
     * 活动状态
     */
    private String TYJhdzt;
    /**
     * 体验金利率
     */
    private String TYJnhll;

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

    public String getTYJType() {
	return TYJType;
    }

    public void setTYJType(String tYJType) {
	TYJType = tYJType;
    }

    public String getTYJhdzt() {
	return TYJhdzt;
    }

    public void setTYJhdzt(String tYJhdzt) {
	TYJhdzt = tYJhdzt;
    }

    public String getTYJnhll() {
	return TYJnhll;
    }

    public void setTYJnhll(String tYJnhll) {
	TYJnhll = tYJnhll;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileTYJ [TYDay=" + TYDay + ", TYJPrincipalMoney=" + TYJPrincipalMoney + ", TYJSY=" + TYJSY
		+ ", TYJType=" + TYJType + ", TYJhdzt=" + TYJhdzt + ", TYJnhll=" + TYJnhll + "]";
    }

}
