package com.izhuantou.dao.pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.vo.member.CustomerQueryConditionDTO;

/**
 * 资金池操作记录 显示资金操作状态，如冻结金额
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PayCashPoolMapper extends Mapper<PayCashPool> {
	/**
	 * 查询tqOID,businessOID ,principalMoney
	 * 
	 * @param memberOID
	 * @return
	 */
	public List<Map<String, Object>> gainByMemebrOIDAndTQOne(String memberOID);

	/**
	 * 查询tqOID,businessOID ,principalMoney
	 * 
	 * @param memberOID
	 * @return
	 */
	public List<Map<String, Object>> gainByMemebrOIDAndTQTwo(@Param("memberOID") String memberOID,
			@Param("state") String state);

	/**
	 * 查询 state,JXinterest ,privilegeIncome
	 * 
	 * @param memberOID
	 * @return
	 */
	public List<PayCashPool> gainByMemberOID(String memberOID);

	/**
	 * 按照OID查找信息
	 * 
	 * @param OID
	 * @return
	 */
	public PayCashPool findByOID(String OID);

	/**
	 * 根据业务businessOID和有钱大于0元 查看资金池的内容
	 * 
	 * @param businessOID
	 * @return
	 */
	public List<PayCashPool> findByBusinessOIDAndHaveMoney(String businessOID);

	/**
	 * 根据Business查看
	 * 
	 * @param businessOID
	 */
	public List<PayCashPool> findPayCashPoolByBusiness(String businessOID);

	/**
	 * 更新资金池
	 * 
	 * @param payCashPool
	 * @return
	 */
	public int updatePayCashPool(PayCashPool payCashPool);

	/**
	 * 保存资金池
	 * 
	 * @param payCashPool
	 * @return
	 */
	public int savePayCashPool(PayCashPool payCashPool);

	/**
	 * 根据会员OID查询已投数量
	 * 
	 * @param memberOID
	 * @return
	 */
	public int countByMemberOID(String memberOID);

	/**
	 * 根据会员OID以及时间范围获取信息
	 * 
	 * @param oid
	 * @param cqcDTO
	 * @return
	 */
	public List<PayCashPool> findByMemberOID(@Param("oid") String oid,
			@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

}
