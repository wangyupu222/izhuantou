package com.izhuantou.damain.mobile.personalCenter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人资产统计 DTO
 * 
 * @author Administrator
 *
 */
public class MobilePropertyTJDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5119934705452611619L;
    /**
     * ：总资产
     */
    private BigDecimal zzc;
    /**
     * ：待收本金
     */
    private BigDecimal dsbj;
    /**
     * ：待收利息
     */
    private BigDecimal dslx;
    /**
     * ：待收特权收益
     */
    private BigDecimal tqsyds;
    /**
     * ：点点投出借金额
     */
    private BigDecimal DDchujie;
    /**
     * ：环环投出借金额
     */
    private BigDecimal HHchujie;
    /**
     * ：转转投出借金额
     */
    private BigDecimal ZZchujie;
    /**
     * :注册天数
     */
    private Integer registerdays;
    /**
     * :注册天数
     */
    private String registertime;
    /**
     * ：累计出借
     */
    private BigDecimal ljcj;
    /**
     * ：累计收益
     */
    private BigDecimal ljsy;
    /**
     * ：累计充值
     */
    private BigDecimal ljcz;
    /**
     * ：累计提现
     */
    private BigDecimal ljtx;
    /**
     * ：可用余额
     */
    private BigDecimal kyye;

    public String getRegistertime() {
	return registertime;
    }

    public void setRegistertime(String registertime) {
	this.registertime = registertime;
    }

    public BigDecimal getZzc() {
	return zzc;
    }

    public void setZzc(BigDecimal zzc) {
	this.zzc = zzc;
    }

    public BigDecimal getDsbj() {
	return dsbj;
    }

    public void setDsbj(BigDecimal dsbj) {
	this.dsbj = dsbj;
    }

    public BigDecimal getDslx() {
	return dslx;
    }

    public void setDslx(BigDecimal dslx) {
	this.dslx = dslx;
    }

    public BigDecimal getTqsyds() {
	return tqsyds;
    }

    public void setTqsyds(BigDecimal tqsyds) {
	this.tqsyds = tqsyds;
    }

    public BigDecimal getDDchujie() {
	return DDchujie;
    }

    public void setDDchujie(BigDecimal dDchujie) {
	DDchujie = dDchujie;
    }

    public BigDecimal getHHchujie() {
	return HHchujie;
    }

    public void setHHchujie(BigDecimal hHchujie) {
	HHchujie = hHchujie;
    }

    public BigDecimal getZZchujie() {
	return ZZchujie;
    }

    public void setZZchujie(BigDecimal zZchujie) {
	ZZchujie = zZchujie;
    }

    public Integer getRegisterdays() {
	return registerdays;
    }

    public void setRegisterdays(Integer registerdays) {
	this.registerdays = registerdays;
    }

    public BigDecimal getLjcj() {
	return ljcj;
    }

    public void setLjcj(BigDecimal ljcj) {
	this.ljcj = ljcj;
    }

    public BigDecimal getLjsy() {
	return ljsy;
    }

    public void setLjsy(BigDecimal ljsy) {
	this.ljsy = ljsy;
    }

    public BigDecimal getLjcz() {
	return ljcz;
    }

    public void setLjcz(BigDecimal ljcz) {
	this.ljcz = ljcz;
    }

    public BigDecimal getLjtx() {
	return ljtx;
    }

    public void setLjtx(BigDecimal ljtx) {
	this.ljtx = ljtx;
    }

    public BigDecimal getKyye() {
	return kyye;
    }

    public void setKyye(BigDecimal kyye) {
	this.kyye = kyye;
    }

    @Override
    public String toString() {
	return "MobilePropertyTJDTO [zzc=" + zzc + ", dsbj=" + dsbj + ", dslx=" + dslx + ", tqsyds=" + tqsyds
		+ ", DDchujie=" + DDchujie + ", HHchujie=" + HHchujie + ", ZZchujie=" + ZZchujie + ", registerdays="
		+ registerdays + ", registertime=" + registertime + ", ljcj=" + ljcj + ", ljsy=" + ljsy + ", ljcz="
		+ ljcz + ", ljtx=" + ljtx + ", kyye=" + kyye + "]";
    }

}
