package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 个人中心数据展示
 * @author dear
 * @version 1.0
 */
public class PersonalDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5064804472487415577L;
	/**
	 * 代收本金
	 */
	private BigDecimal cybj;
	/**
	 * 代收利息
	 */
	private BigDecimal yjsy;
	/**
	 * 代收特权
	 */
	private BigDecimal tqsyds;
	/**
	 *是否绑定手机
	 */
	private String mibaoTrue;
	/**
	 * 是否绑卡 实名
	 */
	private String idCardTrue;
	/**
	 * 资料完整度
	 */
	private String dataIntegrity;
	
	private String imgSrc;
	
	private String picOID;
	
	private BigDecimal availablemoney;
	
	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getPicOID() {
		return picOID;
	}

	public void setPicOID(String picOID) {
		this.picOID = picOID;
	}

	public BigDecimal getAvailablemoney() {
		return availablemoney;
	}

	public void setAvailablemoney(BigDecimal availablemoney) {
		this.availablemoney = availablemoney;
	}

	public String getMibaoTrue() {
		return mibaoTrue;
	}

	public void setMibaoTrue(String mibaoTrue) {
		this.mibaoTrue = mibaoTrue;
	}

	public String getIdCardTrue() {
		return idCardTrue;
	}

	public void setIdCardTrue(String idCardTrue) {
		this.idCardTrue = idCardTrue;
	}

	public String getDataIntegrity() {
		return dataIntegrity;
	}

	public void setDataIntegrity(String dataIntegrity) {
		this.dataIntegrity = dataIntegrity;
	}

	public BigDecimal getCybj() {
		return cybj;
	}

	public void setCybj(BigDecimal cybj) {
		this.cybj = cybj;
	}

	public BigDecimal getYjsy() {
		return yjsy;
	}

	public void setYjsy(BigDecimal yjsy) {
		this.yjsy = yjsy;
	}

	public BigDecimal getTqsyds() {
		return tqsyds;
	}

	public void setTqsyds(BigDecimal tqsyds) {
		this.tqsyds = tqsyds;
	}

	@Override
	public String toString() {
		return "PersonalDTO [cybj=" + cybj + ", yjsy=" + yjsy + ", tqsyds=" + tqsyds + ", mibaoTrue=" + mibaoTrue
				+ ", idCardTrue=" + idCardTrue + ", dataIntegrity=" + dataIntegrity + ", imgSrc=" + imgSrc + ", picOID="
				+ picOID + ", availablemoney=" + availablemoney + "]";
	}
	
	
	
	
}
