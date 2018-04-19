package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import com.izhuantou.damain.BasePojo;

public class MobileMyLendDetialDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资金池OID
     */
    private String CashPoolOID;
    /**
     * 加息
     */
    private String Jxother;
    /**
     * 出借金额
     */
    private String cjje;
    /**
     * 出借时间
     */
    private String cjsjTime;
    /**
     * 到期时间
     */
    private String dqsj;
    /**
     * 结算时间
     */
    private String jssj;
    /**
     * 计息时间
     */
    private String jxsj;
    /**
     * 年化利率
     */
    private String nhll;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 应收本息
     */
    private String ysbx;
    /**
     * 出借期限
     */
    private String zqs;
    /**
     * 特权OID
     */
    private String tqOID;
    /**
     * 加息券名称
     */
    private String privilegeName;
    /**
     * 收益参数
     */
    private String privilegeRange;
    /**
     * 加息期限
     */
    private String privilegeTerm;
    /**
     * 特权收益
     */
    private String tqsy;
    /**
     * 剩余期限
     */
    private String syqs;
    /**
     * 债转
     */
    private String cond;
    /**
     * 还款方式
     */
    private String creditType;
    /**
     * DIOD
     */
    private String DOID;
    /**
     * 下个还款日
     */
    private String xghkr;
    /**
     * 标的ID
     */
    private String biOID;
    /**
     * 未知
     */
    private String bingtype;
    /**
     * 产品类型
     */
    private String productType;

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public String getJxother() {
	return Jxother;
    }

    public void setJxother(String jxother) {
	Jxother = jxother;
    }

    public String getCjje() {
	return cjje;
    }

    public void setCjje(String cjje) {
	this.cjje = cjje;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getJssj() {
	return jssj;
    }

    public void setJssj(String jssj) {
	this.jssj = jssj;
    }

    public String getJxsj() {
	return jxsj;
    }

    public void setJxsj(String jxsj) {
	this.jxsj = jxsj;
    }

    public String getNhll() {
	return nhll;
    }

    public void setNhll(String nhll) {
	this.nhll = nhll;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public String getYsbx() {
	return ysbx;
    }

    public void setYsbx(String ysbx) {
	this.ysbx = ysbx;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getPrivilegeName() {
	return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
    }

    public String getPrivilegeRange() {
	return privilegeRange;
    }

    public void setPrivilegeRange(String privilegeRange) {
	this.privilegeRange = privilegeRange;
    }

    public String getPrivilegeTerm() {
	return privilegeTerm;
    }

    public void setPrivilegeTerm(String privilegeTerm) {
	this.privilegeTerm = privilegeTerm;
    }

    public String getTqsy() {
	return tqsy;
    }

    public void setTqsy(String tqsy) {
	this.tqsy = tqsy;
    }

    public String getSyqs() {
	return syqs;
    }

    public void setSyqs(String syqs) {
	this.syqs = syqs;
    }

    public String getCond() {
	return cond;
    }

    public void setCond(String cond) {
	this.cond = cond;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public String getDOID() {
	return DOID;
    }

    public void setDOID(String dOID) {
	DOID = dOID;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    public String getBiOID() {
	return biOID;
    }

    public void setBiOID(String biOID) {
	this.biOID = biOID;
    }

    public String getBingtype() {
	return bingtype;
    }

    public void setBingtype(String bingtype) {
	this.bingtype = bingtype;
    }

    public String getProductType() {
	return productType;
    }

    public void setProductType(String productType) {
	this.productType = productType;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileMyLendDetialDTO [CashPoolOID=" + CashPoolOID + ", Jxother=" + Jxother + ", cjje=" + cjje
		+ ", cjsjTime=" + cjsjTime + ", dqsj=" + dqsj + ", jssj=" + jssj + ", jxsj=" + jxsj + ", nhll=" + nhll
		+ ", xmmc=" + xmmc + ", ysbx=" + ysbx + ", zqs=" + zqs + ", tqOID=" + tqOID + ", privilegeName="
		+ privilegeName + ", privilegeRange=" + privilegeRange + ", privilegeTerm=" + privilegeTerm + ", tqsy="
		+ tqsy + ", syqs=" + syqs + ", cond=" + cond + ", creditType=" + creditType + ", DOID=" + DOID
		+ ", xghkr=" + xghkr + ", biOID=" + biOID + ", bingtype=" + bingtype + ", productType=" + productType
		+ "]";
    }

}
