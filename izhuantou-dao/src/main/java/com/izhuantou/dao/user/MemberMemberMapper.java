package com.izhuantou.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.member.CustomerListDTO;
import com.izhuantou.damain.vo.member.CustomerQueryConditionDTO;

/**
 * 用户表
 * 
 * @author dear
 * @version 1.0
 */
public interface MemberMemberMapper extends Mapper<MemberMember> {
	/**
	 * 
	 * @param namename为用户名也是手机号查询用户
	 * @return
	 */
	public MemberMember findUserByName(String name);

	/**
	 * 根据用户输入的账号和密码进行注册
	 * 
	 * @param user
	 */
	public int registUser(MemberMember user);

	/**
	 * 根据用户输入的账号进行密码修改
	 * 
	 * @param user
	 */
	public int updatePassword(MemberMember user);

	/**
	 * 根据OID查询用户信息
	 * 
	 * @param OID
	 * @return
	 */
	public MemberMember findUserByOID(String OID);

	/**
	 * 设置密保
	 * 
	 * @param member
	 * @return
	 */
	public int insertTwoQuestion(MemberMember member);

	/**
	 * 更新用户实名后的信息
	 * 
	 * @param member
	 * @return
	 */
	public int updateRealNameByOID(MemberMember member);

	/**
	 * 判断是不是新手
	 * 
	 * @return
	 */
	public int findXSNum(String memberOID);

	/**
	 * 跟新整个member
	 * 
	 * @return
	 */
	public int updateMemberMember(MemberMember member);

	/**
	 * 根据时间段获取总数据数
	 * 
	 * @param cqcDTO
	 * @return
	 */
	public int countByDate(@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

	/**
	 * 获取分页数据
	 * 
	 * @param i
	 * @param pageSize
	 * @param cqcDTO
	 * @return
	 */
	public List<CustomerListDTO> listCustomer(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 根据条件获取数据总数
	 * 
	 * @param cqcDTO
	 * @return
	 */
	public int conditionCount(@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

	/**
	 * 根据条件获取分页数据
	 * 
	 * @param i
	 * @param pageSize
	 * @param cqcDTO
	 * @return
	 */
	public List<CustomerListDTO> conditionList(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize,
			@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

	/**
	 * 根据oid获取数据集
	 * 
	 * @param oids
	 * @return
	 */
	public List<CustomerListDTO> selectByOID(String[] oids);

	/**
	 * 根据条件获取数据
	 * 
	 * @param cqcDTO
	 * @return
	 */
	public List<CustomerListDTO> listByCondition(@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);
	
	/**
	 * 根据手机号获取客户姓名
	 * @param mobile
	 * @return
	 */
	public MemberMember selectByMobile(String mobile);
	/**
	 * 修改用户密码错误的次数
	 * @return
	 */
	public int updateOpportunity(MemberMember member);
}
