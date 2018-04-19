package com.izhuantou.damain.mobile.detial;

import com.izhuantou.damain.BasePojo;

/**
 * 环环投详情
 * 
 * @author yangbosen
 *
 */
public class MobileDetialHHT extends BasePojo {

    /**
     * 环环投手机端详情展示实体
     */
    private static final long serialVersionUID = 1L;
    /**
     * Jxother：是否有加息
     */
    private String jxother;
    /**
     * nhll：年化利率
     */
    private String nhll;
    /**
     * zbsl：分散标的
     */
    private String zbsl;
    /**
     * cjqx：出借期限
     */
    private String cjqx;
    /**
     * ktje：可投金额
     */
    private String ktje;
    /**
     * hkfs：回款方式
     */
    private String hkfs;

    private String xmmc;

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public String getJxother() {
	return jxother;
    }

    public void setJxother(String jxother) {
	this.jxother = jxother;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public String getZbsl() {
	return zbsl;
    }

    public void setZbsl(String zbsl) {
	this.zbsl = zbsl;
    }

    public String getCjqx() {
	return cjqx;
    }

    public void setCjqx(String cjqx) {
	this.cjqx = cjqx;
    }

    public String getKtje() {
	return ktje;
    }

    public void setKtje(String ktje) {
	this.ktje = ktje;
    }

    public String getHkfs() {
	return hkfs;
    }

    public void setHkfs(String hkfs) {
	this.hkfs = hkfs;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileDetialHHT [jxother=" + jxother + ", nhll=" + nhll + ", zbsl=" + zbsl + ", cjqx=" + cjqx
		+ ", ktje=" + ktje + ", hkfs=" + hkfs + ", xmmc=" + xmmc + "]";
    }

}
