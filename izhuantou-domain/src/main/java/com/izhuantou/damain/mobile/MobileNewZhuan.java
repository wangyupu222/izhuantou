package com.izhuantou.damain.mobile;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 移动端头笔赚实体
 * 
 * @author yangbosen
 *
 */
public class MobileNewZhuan extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 标的ID
     */
    private String OID;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 年化利率
     */
    private Double nhll;
    /**
     * 出借期限
     */
    private Integer cjqx;
    /**
     * 标的状态
     */
    private Integer productStatus;
    /**
     * 剩余金额
     */
    private BigDecimal syje;
    /**
     * 是否是新手债转标
     */
    private String cond;
    /**
     * 是否显示新手
     */
    private String result;
    /**
     * 设置定时
     */
    private Integer szds;
    /**
     * 倒计时时间差
     */
    private String xtcz;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public Double getNhll() {
	return nhll;
    }

    public void setNhll(Double nhll) {
	this.nhll = nhll;
    }

    public Integer getCjqx() {
	return cjqx;
    }

    public void setCjqx(Integer cjqx) {
	this.cjqx = cjqx;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getSyje() {
	return syje;
    }

    public void setSyje(BigDecimal syje) {
	this.syje = syje;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public String getResult() {
	return result;
    }

    public void setResult(String result) {
	this.result = result;
    }

    public Integer getSzds() {
	return szds;
    }

    public void setSzds(Integer szds) {
	this.szds = szds;
    }

    public String getXtcz() {
	return xtcz;
    }

    public void setXtcz(String xtcz) {
	this.xtcz = xtcz;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileNewZhuan [OID=" + OID + ", xmmc=" + xmmc + ", nhll=" + nhll + ", cjqx=" + cjqx
		+ ", productStatus=" + productStatus + ", syje=" + syje + ", cond=" + cond + ", result=" + result
		+ ", szds=" + szds + ", xtcz=" + xtcz + "]";
    }

}
