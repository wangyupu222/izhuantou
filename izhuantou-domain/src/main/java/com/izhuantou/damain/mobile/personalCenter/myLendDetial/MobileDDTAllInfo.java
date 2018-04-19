package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

import com.izhuantou.damain.BasePojo;

/**
 * 查询点点投的所有信息
 * 
 * @author Administrator
 *
 */
public class MobileDDTAllInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * OID
     */
    private String OID;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 产品名
     */
    private String biddingName;
    /**
     * 起投金额
     */
    private String StartBidAmount;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getBiddingName() {
	return biddingName;
    }

    public void setBiddingName(String biddingName) {
	this.biddingName = biddingName;
    }

    public String getStartBidAmount() {
	return StartBidAmount;
    }

    public void setStartBidAmount(String startBidAmount) {
	StartBidAmount = startBidAmount;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileDDTAllInfo [OID=" + OID + ", memberOID=" + memberOID + ", biddingName=" + biddingName
		+ ", StartBidAmount=" + StartBidAmount + "]";
    }

}
