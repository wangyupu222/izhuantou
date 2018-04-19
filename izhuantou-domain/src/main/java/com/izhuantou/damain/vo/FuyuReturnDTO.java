package com.izhuantou.damain.vo;

import java.io.Serializable;

public class FuyuReturnDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8405973310153057825L;

    /**
     * 返回的状态码 只有是0000的时候为成功
     */
    private String resp_code;
    /**
     * 用户的手机号，登录账户
     */
    private String login_id;
    /**
     * 金额
     */
    private String amt;
    /**
     * 流水号
     */
    private String mchnt_txn_ssn;
    /**
     * 备注
     */
    private String rem;

    public String getResp_code() {
	return resp_code;
    }

    public void setResp_code(String resp_code) {
	this.resp_code = resp_code;
    }

    public String getLogin_id() {
	return login_id;
    }

    public void setLogin_id(String login_id) {
	this.login_id = login_id;
    }

    public String getAmt() {
	return amt;
    }

    public void setAmt(String amt) {
	this.amt = amt;
    }

    public String getMchnt_txn_ssn() {
	return mchnt_txn_ssn;
    }

    public void setMchnt_txn_ssn(String mchnt_txn_ssn) {
	this.mchnt_txn_ssn = mchnt_txn_ssn;
    }

    public String getRem() {
	return rem;
    }

    public void setRem(String rem) {
	this.rem = rem;
    }

    @Override
    public String toString() {
	return "FuyuReturnDTO [resp_code=" + resp_code + ", login_id=" + login_id + ", amt=" + amt + ", mchnt_txn_ssn="
		+ mchnt_txn_ssn + ", rem=" + rem + "]";
    }

}
