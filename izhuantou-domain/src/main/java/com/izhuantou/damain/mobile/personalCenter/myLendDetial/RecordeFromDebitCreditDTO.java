package com.izhuantou.damain.mobile.personalCenter.myLendDetial;

public class RecordeFromDebitCreditDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String OID;
    private String businessOID;
    private String cashPoolOID;
    private String outMemberOID;
    private String inMemberOID;
    private String crditType;
    private String returnNumber;
    private String returnCycle;
    private String creditRate;
    private String money;
    private String principalMoney;
    private String state;
    private String addDateTime;
    private String updDateTime;
    private String startDateTime;
    private String endDateTime;
    private String returnSurplusNumber;
    private String interest;
    private String surplusPrincipalMoney;
    private String laiyuan;
    private String contetnOID;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getBusinessOID() {
	return businessOID;
    }

    public void setBusinessOID(String businessOID) {
	this.businessOID = businessOID;
    }

    public String getCashPoolOID() {
	return cashPoolOID;
    }

    public void setCashPoolOID(String cashPoolOID) {
	this.cashPoolOID = cashPoolOID;
    }

    public String getOutMemberOID() {
	return outMemberOID;
    }

    public void setOutMemberOID(String outMemberOID) {
	this.outMemberOID = outMemberOID;
    }

    public String getInMemberOID() {
	return inMemberOID;
    }

    public void setInMemberOID(String inMemberOID) {
	this.inMemberOID = inMemberOID;
    }

    public String getCrditType() {
	return crditType;
    }

    public void setCrditType(String crditType) {
	this.crditType = crditType;
    }

    public String getReturnNumber() {
	return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
	this.returnNumber = returnNumber;
    }

    public String getReturnCycle() {
	return returnCycle;
    }

    public void setReturnCycle(String returnCycle) {
	this.returnCycle = returnCycle;
    }

    public String getCreditRate() {
	return creditRate;
    }

    public void setCreditRate(String creditRate) {
	this.creditRate = creditRate;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getPrincipalMoney() {
	return principalMoney;
    }

    public void setPrincipalMoney(String principalMoney) {
	this.principalMoney = principalMoney;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    public String getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(String updDateTime) {
	this.updDateTime = updDateTime;
    }

    public String getStartDateTime() {
	return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
	this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
	return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
	this.endDateTime = endDateTime;
    }

    public String getReturnSurplusNumber() {
	return returnSurplusNumber;
    }

    public void setReturnSurplusNumber(String returnSurplusNumber) {
	this.returnSurplusNumber = returnSurplusNumber;
    }

    public String getInterest() {
	return interest;
    }

    public void setInterest(String interest) {
	this.interest = interest;
    }

    public String getSurplusPrincipalMoney() {
	return surplusPrincipalMoney;
    }

    public void setSurplusPrincipalMoney(String surplusPrincipalMoney) {
	this.surplusPrincipalMoney = surplusPrincipalMoney;
    }

    public String getLaiyuan() {
	return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
	this.laiyuan = laiyuan;
    }

    public String getContetnOID() {
	return contetnOID;
    }

    public void setContetnOID(String contetnOID) {
	this.contetnOID = contetnOID;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "RecordeFromDebitCreditDTO [OID=" + OID + ", businessOID=" + businessOID + ", cashPoolOID=" + cashPoolOID
		+ ", outMemberOID=" + outMemberOID + ", inMemberOID=" + inMemberOID + ", crditType=" + crditType
		+ ", returnNumber=" + returnNumber + ", returnCycle=" + returnCycle + ", creditRate=" + creditRate
		+ ", money=" + money + ", principalMoney=" + principalMoney + ", state=" + state + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + ", startDateTime=" + startDateTime + ", endDateTime="
		+ endDateTime + ", returnSurplusNumber=" + returnSurplusNumber + ", interest=" + interest
		+ ", surplusPrincipalMoney=" + surplusPrincipalMoney + ", laiyuan=" + laiyuan + ", contetnOID="
		+ contetnOID + "]";
    }

}
