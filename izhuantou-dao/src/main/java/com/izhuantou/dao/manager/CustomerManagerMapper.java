package com.izhuantou.dao.manager;

import java.util.List;
import java.util.Map;

import com.izhuantou.damain.vo.member.CustomerManagerDTO;

/**
 * 客户经理
 * @author aries
 * @version 1.0
 */
public interface CustomerManagerMapper {
	/**
	 * 根据参数查询客户经理分组信息
	 * @param customerManagerDTO 客户经理DTO
	 */
	public List<String> getCustomerManagerGroup(CustomerManagerDTO customerManagerDTO);
	
	/**
	 * 获取客户经理list
	 * @param customerManagerDTO 客户经理DTO
	 * @return
	 */
	public List<Map<String,Object>> getCustomerManagerList(CustomerManagerDTO customerManagerDTO);
}
