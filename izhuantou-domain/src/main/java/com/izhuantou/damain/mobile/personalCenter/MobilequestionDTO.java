package com.izhuantou.damain.mobile.personalCenter;

import java.io.Serializable;

public class MobilequestionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1875761412697073784L;

    /**
     * ：用户OID
     */
    private String memberOID;
    /**
     * ：问题一
     */
    private String problem1;
    /**
     * ：问题二
     */
    private String problem2;
    /**
     * ：答案一
     */
    private String answer1;
    /**
     * :答案二
     */
    private String answer2;
    /**
     * 秘钥
     */
    private String pkversion;

    public String getPkversion() {
	return pkversion;
    }

    public void setPkversion(String pkversion) {
	this.pkversion = pkversion;
    }

    public String getMemberOID() {
	return memberOID;
    }

    public void setMemberOID(String memberOID) {
	this.memberOID = memberOID;
    }

    public String getProblem1() {
	return problem1;
    }

    public void setProblem1(String problem1) {
	this.problem1 = problem1;
    }

    public String getProblem2() {
	return problem2;
    }

    public void setProblem2(String problem2) {
	this.problem2 = problem2;
    }

    public String getAnswer1() {
	return answer1;
    }

    public void setAnswer1(String answer1) {
	this.answer1 = answer1;
    }

    public String getAnswer2() {
	return answer2;
    }

    public void setAnswer2(String answer2) {
	this.answer2 = answer2;
    }

    @Override
    public String toString() {
	return "MobilequestionDTO [memberOID=" + memberOID + ", problem1=" + problem1 + ", problem2=" + problem2
		+ ", answer1=" + answer1 + ", answer2=" + answer2 + ", pkversion=" + pkversion + "]";
    }

}
