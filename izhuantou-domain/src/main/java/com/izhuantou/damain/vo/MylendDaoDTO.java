package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询个人中心我的出借
 * 
 * @author Administrator
 *
 */
public class MylendDaoDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5664477401226962807L;

    /**
     * 查询时间
     */
    private String xtTime;
    /**
     * 理财产品配置及费率表的OID
     */
    private String productInfoOID;

    /**
     * 在投中团标产品表OID
     */
    private String OID;

    /**
     * 资金池特权OID
     */
    private String tqOID;

    /**
     * 资金池业务OID
     */
    private String businessOID;
    /**
     * 资金池OID
     */
    private String CashPoolOID;

    /**
     * 应收本息
     */
    private BigDecimal ysbx;

    /**
     * 产品期限（月）
     */
    private String zqs;

    /**
     * 标的的状态
     */
    private String productStatus;

    /**
     * 上限金额
     */
    private BigDecimal sxAmount;
    /**
     * 已投金额
     */
    private BigDecimal holdingAmount;

    /**
     * 剩余返还其数
     */
    private Integer syqs;

    /**
     * 利息合计
     */
    private BigDecimal money;

    /**
     * 还款方式（文字）
     */
    private String creditType;

    /**
     * 出借开始时间
     */
    private String cjsjTime;
    /**
     * 下个还款日
     */
    private String xghkr;

    /**
     * 到期时间
     */
    private String dqsj;

    /**
     * 团标名称
     */
    private String xmmc;

    /**
     * 出借金额（本金）
     */
    private BigDecimal cjje;
    /**
     * 年化利率
     */
    private BigDecimal nhll;

    /**
     * 结束时间
     */
    private String Timejssj;
    /**
     * 返还次数
     */
    private String tatalTerm;

    /**
     * 回款状态
     */
    private String hkzt;
    /**
     * 特权名字
     */
    private String privilegeName;

    /**
     * 特权域(红包数额/加息百分位)
     */
    private BigDecimal privilegeRange;
    /**
     * 特权期限
     */
    private Integer privilegeTerm;
    /**
     * 特权收益
     */
    private BigDecimal tqsy;

    /**
     * 
     */
    private Integer judeg;

    /**
     * // 额外加息利率
     */
    private BigDecimal JXother;
    /**
     * 额外加息
     */
    private BigDecimal  JXinterest;
    
    public BigDecimal getJXinterest() {
		return JXinterest;
	}

	public void setJXinterest(BigDecimal jXinterest) {
		JXinterest = jXinterest;
	}

	public String getHkzt() {
		return hkzt;
	}

	public void setHkzt(String hkzt) {
		this.hkzt = hkzt;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public BigDecimal getPrivilegeRange() {
		return privilegeRange;
	}

	public void setPrivilegeRange(BigDecimal privilegeRange) {
		this.privilegeRange = privilegeRange;
	}

	public Integer getPrivilegeTerm() {
		return privilegeTerm;
	}

	public void setPrivilegeTerm(Integer privilegeTerm) {
		this.privilegeTerm = privilegeTerm;
	}

	public String getTimejssj() {
	return Timejssj;
    }

    public void setTimejssj(String timejssj) {
	Timejssj = timejssj;
    }

    public String getTatalTerm() {
	return tatalTerm;
    }

    public void setTatalTerm(String tatalTerm) {
	this.tatalTerm = tatalTerm;
    }

    public String getXtTime() {
	return xtTime;
    }

    public void setXtTime(String xtTime) {
	this.xtTime = xtTime;
    }

    public String getProductInfoOID() {
	return productInfoOID;
    }

    public void setProductInfoOID(String productInfoOID) {
	this.productInfoOID = productInfoOID;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getTqOID() {
	return tqOID;
    }

    public void setTqOID(String tqOID) {
	this.tqOID = tqOID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getCashPoolOID() {
	return CashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	CashPoolOID = cashPoolOID;
    }

    public BigDecimal getYsbx() {
	return ysbx;
    }

    public void setYsbx(BigDecimal ysbx) {
	this.ysbx = ysbx;
    }

    public String getZqs() {
	return zqs;
    }

    public void setZqs(String zqs) {
	this.zqs = zqs;
    }

    public String getProductStatus() {
	return productStatus;
    }

    public void setProductStatus(String productStatus) {
	this.productStatus = productStatus;
    }

    public BigDecimal getSxAmount() {
	return sxAmount;
    }

    public void setSxAmount(BigDecimal sxAmount) {
	this.sxAmount = sxAmount;
    }

    public BigDecimal getHoldingAmount() {
	return holdingAmount;
    }

    public void setHoldingAmount(BigDecimal holdingAmount) {
	this.holdingAmount = holdingAmount;
    }

    public Integer getSyqs() {
	return syqs;
    }

    public void setSyqs(Integer syqs) {
	this.syqs = syqs;
    }

    public BigDecimal getMoney() {
	return money;
    }

    public void setMoney(BigDecimal money) {
	this.money = money;
    }

    public String getCreditType() {
	return creditType;
    }

    public void setCreditType(String creditType) {
	this.creditType = creditType;
    }

    public String getCjsjTime() {
	return cjsjTime;
    }

    public void setCjsjTime(String cjsjTime) {
	this.cjsjTime = cjsjTime;
    }

    public String getXghkr() {
	return xghkr;
    }

    public void setXghkr(String xghkr) {
	this.xghkr = xghkr;
    }

    public String getDqsj() {
	return dqsj;
    }

    public void setDqsj(String dqsj) {
	this.dqsj = dqsj;
    }

    public String getXmmc() {
	return xmmc;
    }

    public void setXmmc(String xmmc) {
	this.xmmc = xmmc;
    }

    public BigDecimal getCjje() {
	return cjje;
    }

    public void setCjje(BigDecimal cjje) {
	this.cjje = cjje;
    }

    public BigDecimal getNhll() {
	return nhll;
    }

    public void setNhll(BigDecimal nhll) {
	this.nhll = nhll;
    }

    public BigDecimal getTqsy() {
		return tqsy;
	}

	public void setTqsy(BigDecimal tqsy) {
		this.tqsy = tqsy;
	}

	public Integer getJudeg() {
		return judeg;
	}

	public void setJudeg(Integer judeg) {
		this.judeg = judeg;
	}

	public BigDecimal getJXother() {
		return JXother;
	}

	public void setJXother(BigDecimal jXother) {
		JXother = jXother;
	}

	@Override
	public String toString() {
		return "MylendDaoDTO [xtTime=" + xtTime + ", productInfoOID=" + productInfoOID + ", OID=" + OID + ", tqOID="
				+ tqOID + ", businessOID=" + businessOID + ", CashPoolOID=" + CashPoolOID + ", ysbx=" + ysbx + ", zqs="
				+ zqs + ", productStatus=" + productStatus + ", sxAmount=" + sxAmount + ", holdingAmount="
				+ holdingAmount + ", syqs=" + syqs + ", money=" + money + ", creditType=" + creditType + ", cjsjTime="
				+ cjsjTime + ", xghkr=" + xghkr + ", dqsj=" + dqsj + ", xmmc=" + xmmc + ", cjje=" + cjje + ", nhll="
				+ nhll + ", Timejssj=" + Timejssj + ", tatalTerm=" + tatalTerm + ", hkzt=" + hkzt + ", privilegeName="
				+ privilegeName + ", privilegeRange=" + privilegeRange + ", privilegeTerm=" + privilegeTerm + ", tqsy="
				+ tqsy + ", judeg=" + judeg + ", JXother=" + JXother + ", JXinterest=" + JXinterest + "]";
	}

}
