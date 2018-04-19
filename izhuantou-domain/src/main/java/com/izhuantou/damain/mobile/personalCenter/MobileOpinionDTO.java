package com.izhuantou.damain.mobile.personalCenter;

import java.util.Date;

import com.izhuantou.damain.BasePojo;

public class MobileOpinionDTO extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;
    /**
     * 用户OID
     */
    private String memberOID;
    /**
     * 联系方式
     */
    private String contact_way;
    /**
     * 反馈内容
     */
    private String opinion_content;
    /**
     * 反馈时间
     */
    private Date commit_time;

    private Date addDateTime;

    private Date updDateTime;

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

    public String getContact_way() {
	return contact_way;
    }

    public void setContact_way(String contact_way) {
	this.contact_way = contact_way;
    }

    public String getOpinion_content() {
	return opinion_content;
    }

    public void setOpinion_content(String opinion_content) {
	this.opinion_content = opinion_content;
    }

    public Date getCommit_time() {
	return commit_time;
    }

    public void setCommit_time(Date commit_time) {
	this.commit_time = commit_time;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    @Override
    public String toString() {
	return "MobileOpinionDTO [OID=" + OID + ", memberOID=" + memberOID + ", contact_way=" + contact_way
		+ ", opinion_content=" + opinion_content + ", commit_time=" + commit_time + ", addDateTime="
		+ addDateTime + ", updDateTime=" + updDateTime + "]";
    }

}
