package com.izhuantou.damain.mobile.personalCenter;

import java.io.Serializable;

public class MobileUpPayPasswordDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -9135864213579877700L;

    /**
     * 用户的手机号，登录账户
     */
    private String login_id;
    /**
     * 流水号
     */
    private String mchnt_txn_ssn;
    /**
     * 密码类型
     */
    private String busi_tp;

}
