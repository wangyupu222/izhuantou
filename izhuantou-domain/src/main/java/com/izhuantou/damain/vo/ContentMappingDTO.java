package com.izhuantou.damain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContentMappingDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8169821897231596409L;
    /**
     * 产品期限（月）
     */
    private double productTerm;
    /**
     * 年利率
     */
    private BigDecimal yearRate;
    /**
     * 还款方式（文字）
     */
    private String repaymentType;

    public double getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(double productTerm) {
	this.productTerm = productTerm;
    }

    public BigDecimal getYearRate() {
	return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
	this.yearRate = yearRate;
    }

    public String getRepaymentType() {
	return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
	this.repaymentType = repaymentType;
    }

    @Override
    public String toString() {
	return "ContentMappingDTO [productTerm=" + productTerm + ", yearRate=" + yearRate + ", repaymentType="
		+ repaymentType + "]";
    }

}
