package com.izhuantou.damain.vo;

import java.math.BigDecimal;

import com.izhuantou.damain.BasePojo;

/**
 * 用來用戶钱数和用户memberOID
 * 
 * @author dear
 * @version 1.0
 */
public class JoinSelectAllDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -7863814070927639702L;

    private BigDecimal principalMoney;

    private BigDecimal interestMoney;

    private String memberOID;

    public BigDecimal getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(BigDecimal principalMoney) {
	this.principalMoney = principalMoney;
    }

    public BigDecimal getInterestMoney() {
	return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
	this.interestMoney = interestMoney;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    @Override
    public String toString() {
	return "JoinSelectAll [principalMoney=" + principalMoney + ", interestMoney=" + interestMoney + ", memberOID="
		+ memberOID + "]";
    }

}
