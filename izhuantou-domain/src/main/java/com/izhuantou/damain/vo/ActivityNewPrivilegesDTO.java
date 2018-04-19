package com.izhuantou.damain.vo;

/**
 * 注册加息活动页面
 * 
 * @author yangbosen
 *
 */
public class ActivityNewPrivilegesDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 环环投标的产品OID
     */
    private String OID;
    /**
     * 环环投标的产品期限
     */
    private Integer productTerm;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public Integer getProductTerm() {
	return productTerm;
    }

    public void setProductTerm(Integer productTerm) {
	this.productTerm = productTerm;
    }

    @Override
    public String toString() {
	return "ActivityNewPrivilegesDTO [OID=" + OID + ", productTerm=" + productTerm + "]";
    }

}
