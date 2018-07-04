package com.izhuantou.damain.manager;

import java.util.Date;

import com.izhuantou.damain.BasePojo;
/**
 * 客户经理实体
 * @author aries
 * @version 1.0
 * @date 2018-06-27
 */
public class CustomerManager extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8488773439538667247L;
	/**
	 * 员工编号
	 */
	private String empNo;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 * 员工手机号
	 */
	private String mobile;
	/**
	 * 员工职位
	 */
	private String job;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 入职时间
	 */
	private Date entryTime;
	/**
	 * 所属上级
	 */
	private String upEmp;
	/**
	 * 一级部门(财富中心分公司)
	 */
	private String oneDept;
	/**
	 * 二级部门(所属区域 1 2 3 4)
	 */
	private String twoDept;
	/**
	 * 三级部门(城市)
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
	 * 资料状态(审核中，批复，被拒)
	 */
	private String dataState;
	/**
	 * 员工资料新旧(0:原信息 1:总部人力待批复的新信息 2:分公司人力待批复)
	 */
	private String ziliaoTime;
	/**
	 * 职位状态(在职or离职)
	 */
	private String positionStatus;
}
