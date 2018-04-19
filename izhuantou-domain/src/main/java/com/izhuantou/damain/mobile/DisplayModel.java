package com.izhuantou.damain.mobile;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 展示实体
 * 
 * @author yangbosen
 *
 */
public class DisplayModel extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 是否有加息
     */
    private String Jxother;
    /**
     * 年化利率
     */
    private Double nhll;
    /**
     * 出借期限
     */
    private String cjqx;
    /**
     * 标的状态
     */
    private Integer productStatus;
    /**
     * 可投金额
     */
    private BigDecimal ktje;
    /**
     * 标的ID
     */
    private String OID;

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public String getJxother() {
	return Jxother;
    }

    public void setJxother(String jxother) {
	Jxother = jxother;
    }

    public Double getNhll() {
	return nhll;
    }

    public void setNhll(Double nhll) {
	this.nhll = nhll;
    }

    public String getCjqx() {
	return cjqx;
    }

    public void setCjqx(String cjqx) {
	this.cjqx = cjqx;
    }

    public Integer getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getKtje() {
	return ktje;
    }

    public void setKtje(BigDecimal ktje) {
	this.ktje = ktje;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "DisplayModel [xmmc=" + xmmc + ", Jxother=" + Jxother + ", nhll=" + nhll + ", cjqx=" + cjqx
		+ ", productStatus=" + productStatus + ", ktje=" + ktje + ", OID=" + OID + "]";
    }

}
