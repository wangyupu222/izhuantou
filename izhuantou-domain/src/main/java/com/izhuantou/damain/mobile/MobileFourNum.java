package com.izhuantou.damain.mobile;

import com.izhuantou.damain.BasePojo;

/**
 * 移动端运营数据实体
 * 
 * @author yangbosen
 *
 */
public class MobileFourNum extends BasePojo {

    /**
     * 手机端运营数据
     */
    private static final long serialVersionUID = 1L;
    /**
     * 交易额
     */
    private String money_new;
    /**
     * 为用户赚取额
     */
    private String interestMoney_new;
    /**
     * 注册用户
     */
    private String member_new;
    /**
     * 运营时间
     */
    private String operationTime;

    public String getMoney_new() {
	return money_new;
    }

    public void setMoney_new(String money_new) {
	this.money_new = money_new;
    }

    public String getInterestMoney_new() {
	return interestMoney_new;
    }

    public void setInterestMoney_new(String interestMoney_new) {
	this.interestMoney_new = interestMoney_new;
    }

    public String getMember_new() {
	return member_new;
    }

    public void setMember_new(String member_new) {
	this.member_new = member_new;
    }

    public String getOperationTime() {
	return operationTime;
    }

    public void setOperationTime(String operationTime) {
	this.operationTime = operationTime;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileFourNum [money_new=" + money_new + ", interestMoney_new=" + interestMoney_new + ", member_new="
		+ member_new + ", operationTime=" + operationTime + "]";
    }

}
