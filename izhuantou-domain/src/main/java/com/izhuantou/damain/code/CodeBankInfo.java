package com.izhuantou.damain.code;

import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 银行卡对应的code码和名字
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "code_content")
public class CodeBankInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -929928680898193L;
    /**
     * 银行卡编号
     */
    private String bankCode;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行卡的限额
     */
    private String bankQuota;

    public String getBankCode() {
	return bankCode;
    }

    public void setBankCode(String bankCode) {
	this.bankCode = bankCode;
    }

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }

    public String getBankQuota() {
	return bankQuota;
    }

    public void setBankQuota(String bankQuota) {
	this.bankQuota = bankQuota;
    }

    @Override
    public String toString() {
	return "CodeBankInfo [bankCode=" + bankCode + ", bankName=" + bankName + ", bankQuota=" + bankQuota + "]";
    }

}
