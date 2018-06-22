package com.izhuantou.damain.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

/**
 * 风险测评
 * 
 * @author sweet
 * @date 2018年6月11日
 *
 */
@Table(name = "member_score")
public class MemberScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035258298808797596L;

	/**
	 * 
	 */
	private String OID;
	/**
	 * 用户OID
	 */
	private String memberOID;
	/**
	 * 测评分数
	 */
	private String score;
	/**
	 * 描述
	 */
	private String describe0;
	/**
	 * 序号
	 */
	private Integer NO;
	/**
	 * 增加用户OID
	 */
	private String addUserOID;
	/**
	 * 修改用户OID
	 */
	private String updUserOID;
	/**
	 * 是否有效
	 */
	private Boolean valid;
	/**
	 * 添加的时间
	 */
	private Date addDateTime;
	/**
	 * 修改的时间
	 */
	private Date updDateTime;
	/**
	 * 是否更新
	 */
	private Boolean refresh;
	/**
	 * 版本号
	 */
	private Integer version;

	@Override
	public String toString() {
		return "MemberScore [OID=" + OID + ", memberOID=" + memberOID + ", score=" + score + ", describe0=" + describe0
				+ ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
				+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
				+ ", version=" + version + "]";
	}

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDescribe0() {
		return describe0;
	}

	public void setDescribe0(String describe0) {
		this.describe0 = describe0;
	}

	public Integer getNO() {
		return NO;
	}

	public void setNO(Integer nO) {
		NO = nO;
	}

	public String getAddUserOID() {
		return addUserOID;
	}

	public void setAddUserOID(String addUserOID) {
		this.addUserOID = addUserOID;
	}

	public String getUpdUserOID() {
		return updUserOID;
	}

	public void setUpdUserOID(String updUserOID) {
		this.updUserOID = updUserOID;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
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

	public Boolean getRefresh() {
		return refresh;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
