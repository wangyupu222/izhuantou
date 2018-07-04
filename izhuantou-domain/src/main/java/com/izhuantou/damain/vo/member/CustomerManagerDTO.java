package com.izhuantou.damain.vo.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
/**
 * 客户经理变更DTO
 * @author aries
 * @version 1.0
 * @date 2018-06-27
 *
 */
public class CustomerManagerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6151951616808503366L;
	/**
	 * 唯一主键
	 */
	private String OId;
	/**
	 * 一级部门(财富中心分公司)
	 */
	private String oneDept;
	/**
	 * 二级部门(所属区域 1 2 3 4 5)
	 */
	private String twoDept;
	/**
	 * 三级部门(城市分公司)
	 */
	private String thrDept;
	/**
	 * 四级部门(所属营业部)
	 */
	private String fourDept;
	/**
	 * 五级部门(所属团队)
	 */
	private String fiveDept;
	/**
	 * 查询类型（1：二级部门（第一列）,2：三级部门（第二列）,3:四级部门（第三列），4：五级部门（第四列） ,5:客户经理（第五列））
	 * 忽略一级部门
	 */
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOId() {
		return OId;
	}
	public void setOId(String oId) {
		OId = oId;
	}
	public String getOneDept() {
		return oneDept;
	}
	public void setOneDept(String oneDept) {
		this.oneDept = oneDept;
	}
	public String getTwoDept() {
		return twoDept;
	}
	public void setTwoDept(String twoDept) {
		this.twoDept = twoDept;
	}
	public String getThrDept() {
		return thrDept;
	}
	public void setThrDept(String thrDept) {
		this.thrDept = thrDept;
	}
	public String getFourDept() {
		return fourDept;
	}
	public void setFourDept(String fourDept) {
		this.fourDept = fourDept;
	}
	public String getFiveDept() {
		return fiveDept;
	}
	public void setFiveDept(String fiveDept) {
		this.fiveDept = fiveDept;
	}
	@Override
	public String toString() {
		return "CustomerManagerDTO [OId=" + OId + ", oneDept=" + oneDept + ", twoDept=" + twoDept + ", thrDept="
				+ thrDept + ", fourDept=" + fourDept + ", fiveDept=" + fiveDept + ", type=" + type + "]";
	}
	
}
